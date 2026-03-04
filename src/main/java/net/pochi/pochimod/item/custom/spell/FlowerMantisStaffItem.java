package net.pochi.pochimod.item.custom.spell;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.entity.projectile.ScytheProjectileEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlowerMantisStaffItem extends Item {

    private static final int SCYTHE_ATTACK_COOLDOWN_TICKS = 30; // 1.5秒
    private static final int FLOWER_BURST_COOLDOWN_TICKS = 200; // 30秒
    private static final int FLOWER_BURST_DURABILITY_COST = 20;
    private static final double FLOWER_DETECTION_RADIUS = 20.0;
    private static final int MAX_FLOWER_COUNT = 30;
    private static final int PETALS_PER_FLOWER = 3;
    private static final float PETAL_SPEED = 0.8F;

    private static final String FLOWER_BURST_COOLDOWN_TAG = "flower_mantis_staff_burst_cooldown";

    public FlowerMantisStaffItem(Properties properties) {
        super(properties
                .durability(600)
                .setNoRepair()
        );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        // Shift+右クリック：花びらバースト
        if (player.isCrouching()) {
            return this.triggerFlowerBurst(level, player, hand, itemStack);
        }
        // 通常右クリック：鎌発射
        else {
            return this.shootScythe(level, player, hand, itemStack);
        }
    }

    /**
     * ブーメラン鎌発射（基本攻撃）
     */
    private InteractionResultHolder<ItemStack> shootScythe(Level level, Player player, InteractionHand hand, ItemStack itemStack) {
        if (player.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.pass(itemStack);
        }

        if (!level.isClientSide) {
            // 鎌モード（false = 花びらモードではない）
            ScytheProjectileEntity scythe = new ScytheProjectileEntity(level, player);
            scythe.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.2F, 1.0F);
            level.addFreshEntity(scythe);

            itemStack.hurtAndBreak(1, player, hand == InteractionHand.MAIN_HAND ? net.minecraft.world.entity.EquipmentSlot.MAINHAND : net.minecraft.world.entity.EquipmentSlot.OFFHAND);
            player.getCooldowns().addCooldown(this, SCYTHE_ATTACK_COOLDOWN_TICKS);
            player.awardStat(Stats.ITEM_USED.get(this));
        }

        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS,
                0.8F, 1.2F + level.random.nextFloat() * 0.4F);

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    /**
     * 花びらバースト（特殊能力）
     */
    private InteractionResultHolder<ItemStack> triggerFlowerBurst(Level level, Player player, InteractionHand hand, ItemStack itemStack) {
        // クールダウン中
        if (this.isFlowerBurstOnCooldown(player)) {
            if (level.isClientSide) {
                level.playSound(player, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.DISPENSER_FAIL, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
            return InteractionResultHolder.pass(itemStack);
        }

        // 耐久値チェック
        if (itemStack.getDamageValue() + FLOWER_BURST_DURABILITY_COST > itemStack.getMaxDamage()) {
            if (level.isClientSide) {
                level.playSound(player, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
            return InteractionResultHolder.fail(itemStack);
        }

        // サーバー側でのみ処理
        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
            // 近くの花を検出
            List<BlockPos> flowers = this.detectNearbyFlowers(level, player.blockPosition());

            if (flowers.isEmpty()) {
                // 花が見つからない
                player.displayClientMessage(
                        net.minecraft.network.chat.Component.translatable("message.yourmod.no_flowers_nearby"),
                        true
                );

                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.DISPENSER_FAIL, SoundSource.PLAYERS, 0.5F, 0.8F);

                return InteractionResultHolder.fail(itemStack);
            }

            // 発動音
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.EVOKER_CAST_SPELL, SoundSource.PLAYERS, 1.0F, 1.3F);

            // 初期エフェクト
            this.spawnActivationEffect(serverLevel, player);

            // 花びら発射開始
            this.startPetalBurst(serverLevel, player, flowers);

            // 耐久値消費
            itemStack.hurtAndBreak(FLOWER_BURST_DURABILITY_COST, player, hand == InteractionHand.MAIN_HAND ? net.minecraft.world.entity.EquipmentSlot.MAINHAND : net.minecraft.world.entity.EquipmentSlot.OFFHAND);

            // クールダウン設定
            this.setFlowerBurstCooldown(player);

            // 統計
            player.awardStat(Stats.ITEM_USED.get(this));
        } else if (level.isClientSide) {
            level.playSound(player, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.EVOKER_CAST_SPELL, SoundSource.PLAYERS, 1.0F, 1.3F);
        }

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    /**
     * 近くの花を検出
     */
    private List<BlockPos> detectNearbyFlowers(Level level, BlockPos center) {
        List<BlockPos> flowers = new ArrayList<>();
        int radius = (int) FLOWER_DETECTION_RADIUS;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos checkPos = center.offset(x, y, z);

                    if (center.distSqr(checkPos) > FLOWER_DETECTION_RADIUS * FLOWER_DETECTION_RADIUS) {
                        continue;
                    }

                    BlockState state = level.getBlockState(checkPos);

                    if (this.isFlowerBlock(state.getBlock())) {
                        flowers.add(checkPos.immutable());

                        if (flowers.size() >= MAX_FLOWER_COUNT) {
                            Collections.shuffle(flowers);
                            return flowers.subList(0, MAX_FLOWER_COUNT);
                        }
                    }
                }
            }
        }

        Collections.shuffle(flowers);
        return flowers;
    }

    /**
     * 花ブロックかチェック
     */
    private boolean isFlowerBlock(Block block) {
        // バニラの花
        if (block == Blocks.DANDELION || block == Blocks.POPPY ||
                block == Blocks.BLUE_ORCHID || block == Blocks.ALLIUM ||
                block == Blocks.AZURE_BLUET || block == Blocks.RED_TULIP ||
                block == Blocks.ORANGE_TULIP || block == Blocks.WHITE_TULIP ||
                block == Blocks.PINK_TULIP || block == Blocks.OXEYE_DAISY ||
                block == Blocks.CORNFLOWER || block == Blocks.LILY_OF_THE_VALLEY ||
                block == Blocks.WITHER_ROSE || block == Blocks.SUNFLOWER ||
                block == Blocks.LILAC || block == Blocks.ROSE_BUSH ||
                block == Blocks.PEONY || block == Blocks.TORCHFLOWER ||
                block == Blocks.PINK_PETALS) {
            return true;
        }

        // 桜の葉
        if (block == Blocks.CHERRY_LEAVES || block == Blocks.FLOWERING_AZALEA_LEAVES) {
            return true;
        }

        return false;
    }

    /**
     * 花びら発射開始
     */
    private void startPetalBurst(ServerLevel level, Player player, List<BlockPos> flowers) {
        int totalPetals = 0;

        for (BlockPos flowerPos : flowers) {
            // 各花から3発の花びらを発射
            for (int i = 0; i < PETALS_PER_FLOWER; i++) {
                final int petalIndex = totalPetals;
                totalPetals++;

                // 少しずつ時間差で発射（視覚的効果）
                int delay = petalIndex; // 1tickずつ遅延

                level.getServer().tell(new net.minecraft.server.TickTask(
                        level.getServer().getTickCount() + delay,
                        () -> {
                            this.shootPetalFromFlower(level, player, flowerPos);
                        }
                ));
            }
        }
    }

    /**
     * 花から花びら弾を1発発射
     */
    private void shootPetalFromFlower(ServerLevel level, Player player, BlockPos flowerPos) {
        // 花の位置
        Vec3 flowerCenter = new Vec3(
                flowerPos.getX() + 0.5,
                flowerPos.getY() + 0.5,
                flowerPos.getZ() + 0.5
        );

        // ランダムな方向（水平方向重視）
        double horizontalAngle = level.random.nextDouble() * Math.PI * 2;
        double verticalAngle = (level.random.nextDouble() - 0.5) * Math.PI * 0.5; // -45度～+45度

        double horizontalSpeed = Math.cos(verticalAngle) * PETAL_SPEED;
        double vx = Math.cos(horizontalAngle) * horizontalSpeed;
        double vy = Math.sin(verticalAngle) * PETAL_SPEED;
        double vz = Math.sin(horizontalAngle) * horizontalSpeed;

        // 花びらモードの発射体を作成
        ScytheProjectileEntity petal = new ScytheProjectileEntity(level, player);
        petal.setPos(flowerCenter.x, flowerCenter.y, flowerCenter.z);
        petal.setDeltaMovement(vx, vy, vz);

        level.addFreshEntity(petal);

        // 発射エフェクト
        level.sendParticles(
                ParticleTypes.CHERRY_LEAVES,
                flowerCenter.x, flowerCenter.y, flowerCenter.z,
                5,
                0.2, 0.2, 0.2,
                0.1
        );

        // 発射音（小さめ）
        level.playSound(
                null,
                flowerCenter.x, flowerCenter.y, flowerCenter.z,
                SoundEvents.FLOWERING_AZALEA_BREAK,
                SoundSource.BLOCKS,
                0.3F,
                1.5F + level.random.nextFloat() * 0.5F
        );
    }

    /**
     * 発動時のエフェクト
     */
    private void spawnActivationEffect(ServerLevel level, Player player) {
        // プレイヤー周囲の円形エフェクト
        for (int i = 0; i < 50; i++) {
            double angle = Math.PI * 2 * i / 50;
            double radius = FLOWER_DETECTION_RADIUS;
            double x = player.getX() + Math.cos(angle) * radius;
            double z = player.getZ() + Math.sin(angle) * radius;

            level.sendParticles(
                    ParticleTypes.CHERRY_LEAVES,
                    x, player.getY() + 0.1, z,
                    1,
                    0, 0.2, 0,
                    0.05
            );
        }

        // プレイヤー中心の上昇パーティクル
        level.sendParticles(
                ParticleTypes.GLOW,
                player.getX(), player.getY() + 1, player.getZ(),
                30,
                2, 1, 2,
                0.1
        );
    }

    /**
     * 花びらバーストクールダウン中かチェック
     */
    private boolean isFlowerBurstOnCooldown(Player player) {
        return player.getPersistentData().contains(FLOWER_BURST_COOLDOWN_TAG) &&
                player.getPersistentData().getLong(FLOWER_BURST_COOLDOWN_TAG) > player.level().getGameTime();
    }

    /**
     * 花びらバーストクールダウンを設定
     */
    private void setFlowerBurstCooldown(Player player) {
        long cooldownEndTime = player.level().getGameTime() + FLOWER_BURST_COOLDOWN_TICKS;
        player.getPersistentData().putLong(FLOWER_BURST_COOLDOWN_TAG, cooldownEndTime);
    }


}