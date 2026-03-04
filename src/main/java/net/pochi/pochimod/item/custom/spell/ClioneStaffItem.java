package net.pochi.pochimod.item.custom.spell;

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
import net.pochi.pochimod.entity.projectile.IceProjectileEntity;
import net.pochi.pochimod.entity.projectile.SummonedClioneEntity;

public class ClioneStaffItem extends Item {

    private static final int BASIC_ATTACK_COOLDOWN_TICKS = 10; // 0.5秒
    private static final int SUMMON_COOLDOWN_TICKS = 200; // 60秒
    private static final int SUMMON_DURABILITY_COST = 10; // 召喚時の耐久値消費
    private static final int SUMMON_COUNT = 3; // 召喚数

    // クールダウン管理用の識別子（内部用）
    private static final String SUMMON_COOLDOWN_TAG = "clione_staff_summon_cooldown";

    public ClioneStaffItem(Properties properties) {
        super(properties
                .durability(500)
                .setNoRepair()
        );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        // Shift+右クリック：召喚
        if (player.isCrouching()) {
            return this.summonCliones(level, player, hand, itemStack);
        }
        // 通常右クリック：氷弾発射
        else {
            return this.shootIceProjectile(level, player, hand, itemStack);
        }
    }

    /**
     * 氷弾発射（基本攻撃）
     */
    private InteractionResultHolder<ItemStack> shootIceProjectile(Level level, Player player, InteractionHand hand, ItemStack itemStack) {
        // クールダウン中は発射しない
        if (player.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.pass(itemStack);
        }

        // サーバー側でのみ処理
        if (!level.isClientSide) {
            // 氷弾を発射
            IceProjectileEntity projectile = new IceProjectileEntity(level, player);
            projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(projectile);

            // 耐久値消費（1発あたり1消費）
            itemStack.hurtAndBreak(1, player, hand == InteractionHand.MAIN_HAND ? net.minecraft.world.entity.EquipmentSlot.MAINHAND : net.minecraft.world.entity.EquipmentSlot.OFFHAND);

            // クールダウン設定
            player.getCooldowns().addCooldown(this, BASIC_ATTACK_COOLDOWN_TICKS);

            // 統計
            player.awardStat(Stats.ITEM_USED.get(this));
        }

        // サウンド再生
        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.SNOW_GOLEM_SHOOT, SoundSource.PLAYERS,
                0.8F, 0.8F + level.random.nextFloat() * 0.4F);

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    /**
     * クリオネ召喚（特殊能力）
     */
    private InteractionResultHolder<ItemStack> summonCliones(Level level, Player player, InteractionHand hand, ItemStack itemStack) {
        // 召喚クールダウン中
        if (this.isSummonOnCooldown(player)) {
            if (level.isClientSide) {
                // クールダウン中の音（クライアントのみ）
                level.playSound(player, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.DISPENSER_FAIL, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
            return InteractionResultHolder.pass(itemStack);
        }

        // 耐久値チェック
        if (itemStack.getDamageValue() + SUMMON_DURABILITY_COST > itemStack.getMaxDamage()) {
            if (level.isClientSide) {
                // 耐久値不足の音
                level.playSound(player, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
            return InteractionResultHolder.fail(itemStack);
        }

        // サーバー側でのみ処理
        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
            // 召喚魔法陣エフェクト
            this.spawnSummonEffect(serverLevel, player);

            // 3体のクリオネを召喚
            for (int i = 0; i < SUMMON_COUNT; i++) {
                SummonedClioneEntity clione = new SummonedClioneEntity(level, player, i);

                // 初期位置をプレイヤーの位置に設定
                clione.moveTo(
                        player.getX(),
                        player.getY() + 1.5,
                        player.getZ(),
                        0, 0
                );

                level.addFreshEntity(clione);
            }

            // 耐久値消費
            itemStack.hurtAndBreak(SUMMON_DURABILITY_COST, player, hand == InteractionHand.MAIN_HAND ? net.minecraft.world.entity.EquipmentSlot.MAINHAND : net.minecraft.world.entity.EquipmentSlot.OFFHAND);

            // 召喚クールダウン設定
            this.setSummonCooldown(player);

            // 統計
            player.awardStat(Stats.ITEM_USED.get(this));
        }

        // 召喚音
        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.EVOKER_CAST_SPELL, SoundSource.PLAYERS,
                1.0F, 1.0F);

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    /**
     * 召喚時の魔法陣エフェクト
     */
    private void spawnSummonEffect(ServerLevel level, Player player) {
        // 円形の魔法陣パーティクル
        for (int i = 0; i < 50; i++) {
            double angle = Math.PI * 2 * i / 50;
            double radius = 2.0;
            double x = player.getX() + Math.cos(angle) * radius;
            double y = player.getY() + 0.1;
            double z = player.getZ() + Math.sin(angle) * radius;

            level.sendParticles(
                    ParticleTypes.GLOW,
                    x, y, z,
                    1,
                    0, 0.1, 0,
                    0.01
            );

            level.sendParticles(
                    ParticleTypes.SNOWFLAKE,
                    x, y, z,
                    1,
                    0, 0.2, 0,
                    0.02
            );
        }

        // 中心から上昇するパーティクル
        for (int i = 0; i < 30; i++) {
            level.sendParticles(
                    ParticleTypes.ENCHANT,
                    player.getX() + (level.random.nextDouble() - 0.5) * 2,
                    player.getY(),
                    player.getZ() + (level.random.nextDouble() - 0.5) * 2,
                    1,
                    0, 0.5, 0,
                    0.1
            );
        }
    }

    /**
     * 召喚クールダウン中かチェック
     */
    private boolean isSummonOnCooldown(Player player) {
        return player.getPersistentData().contains(SUMMON_COOLDOWN_TAG) &&
                player.getPersistentData().getLong(SUMMON_COOLDOWN_TAG) > player.level().getGameTime();
    }

    /**
     * 召喚クールダウンを設定
     */
    private void setSummonCooldown(Player player) {
        long cooldownEndTime = player.level().getGameTime() + SUMMON_COOLDOWN_TICKS;
        player.getPersistentData().putLong(SUMMON_COOLDOWN_TAG, cooldownEndTime);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}