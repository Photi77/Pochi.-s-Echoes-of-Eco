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
import net.pochi.pochimod.entity.projectile.FlowerBombEntity;
import net.pochi.pochimod.entity.projectile.ScytheProjectileEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlowerMantisStaffBombItem extends Item {

    private static final int SCYTHE_ATTACK_COOLDOWN_TICKS = 30; // 1.5秒
    private static final int FLOWER_BOMB_COOLDOWN_TICKS = 200; // 35秒
    private static final int FLOWER_BOMB_DURABILITY_COST = 80;
    private static final double FLOWER_DETECTION_RADIUS = 12.0;
    private static final int MAX_FLOWER_COUNT = 30;
    private static final int EXPLOSION_DELAY_TICKS = 2; // 0.1秒間隔

    private static final String FLOWER_BOMB_COOLDOWN_TAG = "flower_mantis_staff_bomb_cooldown";

    public FlowerMantisStaffBombItem(Properties properties) {
        super(properties
                .durability(600)
                .setNoRepair()
        );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        // Shift+右クリック：花爆発
        if (player.isCrouching()) {
            return this.triggerFlowerBomb(level, player, hand, itemStack);
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
        // クールダウン中は発射しない
        if (player.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.pass(itemStack);
        }

        // サーバー側でのみ処理
        if (!level.isClientSide) {
            // ブーメラン鎌を発射
            ScytheProjectileEntity scythe = new ScytheProjectileEntity(level, player);
            scythe.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.2F, 1.0F);
            level.addFreshEntity(scythe);

            // 耐久値消費
            itemStack.hurtAndBreak(1, player, hand == InteractionHand.MAIN_HAND ? net.minecraft.world.entity.EquipmentSlot.MAINHAND : net.minecraft.world.entity.EquipmentSlot.OFFHAND);

            // クールダウン設定
            player.getCooldowns().addCooldown(this, SCYTHE_ATTACK_COOLDOWN_TICKS);

            // 統計
            player.awardStat(Stats.ITEM_USED.get(this));
        }

        // サウンド再生
        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS,
                0.8F, 1.2F + level.random.nextFloat() * 0.4F);

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    /**
     * 花爆発（特殊能力）
     */
    private InteractionResultHolder<ItemStack> triggerFlowerBomb(Level level, Player player, InteractionHand hand, ItemStack itemStack) {
        // 花爆発クールダウン中
        if (this.isFlowerBombOnCooldown(player)) {
            if (level.isClientSide) {
                level.playSound(player, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.DISPENSER_FAIL, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
            return InteractionResultHolder.pass(itemStack);
        }

        // 耐久値チェック
        if (itemStack.getDamageValue() + FLOWER_BOMB_DURABILITY_COST > itemStack.getMaxDamage()) {
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

            // 連鎖爆発を開始
            this.startChainExplosion(serverLevel, player, flowers, itemStack, hand);

            // クールダウン設定
            this.setFlowerBombCooldown(player);

            // 統計
            player.awardStat(Stats.ITEM_USED.get(this));
        } else if (level.isClientSide) {
            // クライアント側の発動音
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

        // 球状に検索
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos checkPos = center.offset(x, y, z);

                    // 距離チェック
                    if (center.distSqr(checkPos) > FLOWER_DETECTION_RADIUS * FLOWER_DETECTION_RADIUS) {
                        continue;
                    }

                    BlockState state = level.getBlockState(checkPos);

                    // 花かどうかチェック
                    if (this.isFlowerBlock(state.getBlock())) {
                        flowers.add(checkPos.immutable());

                        // 最大数に達したら終了
                        if (flowers.size() >= MAX_FLOWER_COUNT) {
                            Collections.shuffle(flowers);
                            return flowers.subList(0, MAX_FLOWER_COUNT);
                        }
                    }
                }
            }
        }

        // ランダムシャッフル（爆発順をランダムに）
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

        // TODO: モッドで追加した花があればここに追加

        return false;
    }

    /**
     * 連鎖爆発開始
     */
    private void startChainExplosion(ServerLevel level, Player player, List<BlockPos> flowers, ItemStack itemStack, InteractionHand hand) {
        // 各花に時間差で爆発エンティティを生成
        for (int i = 0; i < flowers.size(); i++) {
            BlockPos flowerPos = flowers.get(i);
            int delay = i * EXPLOSION_DELAY_TICKS;

            // 遅延実行
            level.getServer().tell(new net.minecraft.server.TickTask(
                    level.getServer().getTickCount() + delay,
                    () -> {
                        // 花爆発エンティティを生成
                        FlowerBombEntity bomb = new FlowerBombEntity(level, flowerPos, player);
                        level.addFreshEntity(bomb);
                    }
            ));
        }

        // 最後の爆発後に耐久値消費
        int totalDelay = flowers.size() * EXPLOSION_DELAY_TICKS + 20; // 爆発完了後
        net.minecraft.world.entity.EquipmentSlot slot = hand == InteractionHand.MAIN_HAND ? net.minecraft.world.entity.EquipmentSlot.MAINHAND : net.minecraft.world.entity.EquipmentSlot.OFFHAND;
        level.getServer().tell(new net.minecraft.server.TickTask(
                level.getServer().getTickCount() + totalDelay,
                () -> {
                    itemStack.hurtAndBreak(FLOWER_BOMB_DURABILITY_COST, player, slot);
                }
        ));
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
     * 花爆発クールダウン中かチェック
     */
    private boolean isFlowerBombOnCooldown(Player player) {
        return player.getPersistentData().contains(FLOWER_BOMB_COOLDOWN_TAG) &&
                player.getPersistentData().getLong(FLOWER_BOMB_COOLDOWN_TAG) > player.level().getGameTime();
    }

    /**
     * 花爆発クールダウンを設定
     */
    private void setFlowerBombCooldown(Player player) {
        long cooldownEndTime = player.level().getGameTime() + FLOWER_BOMB_COOLDOWN_TICKS;
        player.getPersistentData().putLong(FLOWER_BOMB_COOLDOWN_TAG, cooldownEndTime);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}