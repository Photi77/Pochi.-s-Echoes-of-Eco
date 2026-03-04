package net.pochi.pochimod.item.custom.weapon;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.util.MoonPhaseUtil;

import java.util.List;

public class LunarClawBladeItem extends SwordItem {
    private static final String BEAR_CRUSH_COOLDOWN_TAG = "lunar_claw_bear_crush_cd";
    private static final String SHADOW_CLAW_COOLDOWN_TAG = "lunar_claw_shadow_claw_cd";

    private static final int BEAR_CRUSH_COOLDOWN = 200; // 15秒
    private static final int SHADOW_CLAW_COOLDOWN = 100; // 12秒

    private static final int BEAR_CRUSH_DURABILITY = 20;
    private static final int SHADOW_CLAW_DURABILITY = 15;

    // ベアークラッシュの定数
    private static final float BEAR_CRUSH_DAMAGE = 12.0F;
    private static final double BEAR_CRUSH_RANGE = 3.0;
    private static final double BEAR_CRUSH_ANGLE = 120.0; // 前方120度
    private static final float BEAR_CRUSH_KNOCKBACK = 2.0F;

    // シャドウクロウの定数
    // クラスの定数部分
    private static final float SHADOW_CLAW_DAMAGE = 3.0F;
    private static final int SHADOW_CLAW_HITS = 3;
    private static final int SHADOW_CLAW_HIT_INTERVAL = 20; // 20tick = 1秒
    private static final int INVISIBILITY_DURATION = 40; // 2秒
    private static final double SHADOW_CLAW_RANGE = 4.0;
    private static int times = 0;

    public LunarClawBladeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        int moonPhase = MoonPhaseUtil.getMoonPhase(level);
        int mode = MoonPhaseUtil.getMode(moonPhase);

        if (mode == 2) {
            if (level.isClientSide) {
                player.displayClientMessage(
                        Component.literal("移行期は特殊攻撃を使用できません")
                                .withStyle(ChatFormatting.RED),
                        true
                );
                level.playSound(player, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.DISPENSER_FAIL, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
            return InteractionResultHolder.pass(itemStack);
        }

        if (mode == 0) {
            return this.useBearCrush(level, player, hand, itemStack);
        } else if (mode == 1) {
            times = 15;
            return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
        }

        return InteractionResultHolder.pass(itemStack);
    }

    /**
     * ベアークラッシュ（満月期の特殊攻撃）
     */
    private InteractionResultHolder<ItemStack> useBearCrush(Level level, Player player, InteractionHand hand, ItemStack itemStack) {
        if (this.isOnCooldown(player, BEAR_CRUSH_COOLDOWN_TAG)) {
            if (level.isClientSide) {
                level.playSound(player, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.DISPENSER_FAIL, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
            return InteractionResultHolder.pass(itemStack);
        }

        if (itemStack.getDamageValue() + BEAR_CRUSH_DURABILITY > itemStack.getMaxDamage()) {
            if (level.isClientSide) {
                level.playSound(player, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
            return InteractionResultHolder.fail(itemStack);
        }

        if (!level.isClientSide) {
            boolean success = this.performBearCrush(level, player);

            if (success) {
                EquipmentSlot bearSlot = hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
                itemStack.hurtAndBreak(BEAR_CRUSH_DURABILITY, player, bearSlot);
                this.setCooldown(player, BEAR_CRUSH_COOLDOWN_TAG, BEAR_CRUSH_COOLDOWN);
                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    /**
     * シャドウクロウ（新月期の特殊攻撃）
     */
    private InteractionResultHolder<ItemStack> useShadowClaw(Level level, Player player, InteractionHand hand, ItemStack itemStack) {
        if (this.isOnCooldown(player, SHADOW_CLAW_COOLDOWN_TAG)) {
            if (level.isClientSide) {
                level.playSound(player, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.DISPENSER_FAIL, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
            return InteractionResultHolder.pass(itemStack);
        }

        if (itemStack.getDamageValue() + SHADOW_CLAW_DURABILITY > itemStack.getMaxDamage()) {
            if (level.isClientSide) {
                level.playSound(player, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
            return InteractionResultHolder.fail(itemStack);
        }

        if (!level.isClientSide) {
            boolean success = this.performShadowClaw(level, player);

            if (success) {
                EquipmentSlot shadowSlot = hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
                itemStack.hurtAndBreak(SHADOW_CLAW_DURABILITY, player, shadowSlot);
                this.setCooldown(player, SHADOW_CLAW_COOLDOWN_TAG, SHADOW_CLAW_COOLDOWN);
                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    /**
     * ベアークラッシュ実行
     */
    private boolean performBearCrush(Level level, Player player) {
        // プレイヤーの視線方向
        Vec3 lookVec = player.getLookAngle();
        Vec3 playerPos = player.position().add(0, player.getEyeHeight(), 0);

        // 範囲内の敵を取得
        AABB searchArea = player.getBoundingBox().inflate(BEAR_CRUSH_RANGE);
        List<LivingEntity> targets = level.getEntitiesOfClass(
                LivingEntity.class,
                searchArea,
                entity -> entity != player && entity.isAlive() && this.isInFrontCone(player, entity, lookVec, playerPos)
        );

        if (targets.isEmpty()) {
            return false;
        }

        // 各ターゲットにダメージとノックバック
        for (LivingEntity target : targets) {
            // ダメージ適用
            DamageSource damageSource = level.damageSources().playerAttack(player);
            target.hurt(damageSource, BEAR_CRUSH_DAMAGE);

            // ノックバック
            Vec3 knockbackVec = target.position().subtract(player.position()).normalize().scale(BEAR_CRUSH_KNOCKBACK);
            target.setDeltaMovement(target.getDeltaMovement().add(knockbackVec.x, 0.4, knockbackVec.z));
        }

        // エフェクト
        this.spawnBearCrushEffects((ServerLevel) level, player);

        return true;
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
        if(!p_41405_.isClientSide()) {
            if (times == 10) {
                this.performShadowClaw(p_41405_, (Player) p_41406_);
            } else if (times == 5) {
                this.performShadowClaw(p_41405_, (Player) p_41406_);
            } else if (times == 0) {
                this.performShadowClaw(p_41405_, (Player) p_41406_);
            }
            times--;
        }
    }


    /**
     * シャドウクロウ実行
     */
    /**
     * シャドウクロウ実行（範囲攻撃版）
     */
    /**
     * シャドウクロウ実行（範囲攻撃・1秒間隔版）
     */
    private boolean performShadowClaw(Level level, Player player) {
        // 範囲内の敵を取得
        List<LivingEntity> targets = this.findNearbyEnemies(player, SHADOW_CLAW_RANGE);

        if (targets.isEmpty()) {
            return false;
        }

        // ServerLevelにキャスト
        ServerLevel serverLevel = (ServerLevel) level;

        // 各ターゲットに対して3連撃をスケジュール
        for (LivingEntity target : targets) {
            // 1回目の攻撃（即座）
            this.performSingleHit(serverLevel, player, target, 0);

        }

        // 攻撃後に透明化（3回目の攻撃の0.25秒後 = 45tick後）
        serverLevel.getServer().tell(new net.minecraft.server.TickTask(
                serverLevel.getServer().getTickCount() + 45,
                () -> {
                    if (player.isAlive()) {
                        player.addEffect(new MobEffectInstance(
                                MobEffects.INVISIBILITY,
                                INVISIBILITY_DURATION,
                                0,
                                false,
                                false,
                                true
                        ));

                        this.spawnInvisibilityEffect(serverLevel, player);
                    }
                }
        ));

        return true;
    }

    /**
     * 単発の攻撃を実行
     */
    private void performSingleHit(ServerLevel level, Player player, LivingEntity target, int hitIndex) {
        // 無敵時間をリセット
        target.invulnerableTime = 0;

        // ダメージ適用
        DamageSource damageSource = level.damageSources().playerAttack(player);
        target.hurt(damageSource, SHADOW_CLAW_DAMAGE);

        // ヒットエフェクト
        this.spawnShadowClawHitEffect(level, target, hitIndex);

    }

    /**
     * 範囲内の敵を全て取得
     */
    private List<LivingEntity> findNearbyEnemies(Player player, double range) {
        AABB searchArea = player.getBoundingBox().inflate(range);
        return player.level().getEntitiesOfClass(
                LivingEntity.class,
                searchArea,
                entity -> entity != player &&
                        entity.isAlive() &&
                        !entity.isAlliedTo(player) &&
                        player.hasLineOfSight(entity)
        );
    }

    /**
     * 前方の扇状範囲内にいるかチェック
     */
    private boolean isInFrontCone(Player player, LivingEntity target, Vec3 lookVec, Vec3 playerPos) {
        Vec3 toTarget = target.position().add(0, target.getBbHeight() / 2, 0).subtract(playerPos);
        double distance = toTarget.length();

        if (distance > BEAR_CRUSH_RANGE) {
            return false;
        }

        // 角度チェック
        toTarget = toTarget.normalize();
        double dotProduct = lookVec.dot(toTarget);
        double angle = Math.toDegrees(Math.acos(dotProduct));

        return angle <= BEAR_CRUSH_ANGLE / 2;
    }

    /**
     * ベアークラッシュのエフェクト
     */
    private void spawnBearCrushEffects(ServerLevel level, Player player) {
        Vec3 lookVec = player.getLookAngle();
        Vec3 playerPos = player.position().add(0, player.getEyeHeight(), 0);

        // 前方に衝撃波パーティクル
        for (int i = 0; i < 30; i++) {
            double distance = 1.0 + level.random.nextDouble() * BEAR_CRUSH_RANGE;
            double angle = Math.toRadians(level.random.nextDouble() * BEAR_CRUSH_ANGLE - BEAR_CRUSH_ANGLE / 2);

            Vec3 particlePos = playerPos.add(
                    lookVec.scale(distance).yRot((float) angle)
            );

            level.sendParticles(
                    ParticleTypes.SWEEP_ATTACK,
                    particlePos.x, particlePos.y, particlePos.z,
                    1,
                    0.2, 0.2, 0.2,
                    0.01
            );
        }

        // 爆発パーティクル
        level.sendParticles(
                ParticleTypes.EXPLOSION,
                playerPos.x + lookVec.x * 2,
                playerPos.y,
                playerPos.z + lookVec.z * 2,
                3,
                0.5, 0.5, 0.5,
                0.1
        );

        // 音
        level.playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.GENERIC_EXPLODE,
                SoundSource.PLAYERS,
                1.0F,
                0.8F
        );

        level.playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.RAVAGER_ROAR,
                SoundSource.PLAYERS,
                0.8F,
                1.2F
        );
    }

    /**
     * シャドウクロウのヒットエフェクト
     */
    private void spawnShadowClawHitEffect(ServerLevel level, LivingEntity target, int hitIndex) {
        // ダークパーティクル
        level.sendParticles(
                ParticleTypes.SMOKE,
                target.getX(), target.getY() + target.getBbHeight() / 2, target.getZ(),
                10,
                0.3, 0.3, 0.3,
                0.05
        );

        // クリティカルパーティクル
        level.sendParticles(
                ParticleTypes.CRIT,
                target.getX(), target.getY() + target.getBbHeight() / 2, target.getZ(),
                5,
                0.2, 0.2, 0.2,
                0.1
        );

        // 音（ピッチを変えて連撃感を出す）
        level.playSound(
                null,
                target.getX(), target.getY(), target.getZ(),
                SoundEvents.PLAYER_ATTACK_SWEEP,
                SoundSource.PLAYERS,
                0.7F,
                1.3F + hitIndex * 0.2F
        );
    }

    /**
     * 透明化エフェクト
     */
    private void spawnInvisibilityEffect(ServerLevel level, Player player) {
        level.sendParticles(
                ParticleTypes.LARGE_SMOKE,
                player.getX(), player.getY() + 1.0, player.getZ(),
                20,
                0.5, 1.0, 0.5,
                0.05
        );

        level.playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.ENDERMAN_TELEPORT,
                SoundSource.PLAYERS,
                0.8F,
                1.5F
        );
    }

    private boolean isOnCooldown(Player player, String tag) {
        return player.getPersistentData().contains(tag) &&
                player.getPersistentData().getLong(tag) > player.level().getGameTime();
    }

    private void setCooldown(Player player, String tag, int ticks) {
        long cooldownEnd = player.level().getGameTime() + ticks;
        player.getPersistentData().putLong(tag, cooldownEnd);
    }

    private int getRemainingCooldown(Player player, String tag) {
        if (!isOnCooldown(player, tag)) {
            return 0;
        }

        long remaining = player.getPersistentData().getLong(tag) - player.level().getGameTime();
        return (int)(remaining / 20);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);

        Level level = context.level();
        if (level == null) {
            return;
        }

        int moonPhase = MoonPhaseUtil.getMoonPhase(level);
        int mode = MoonPhaseUtil.getMode(moonPhase);
        String phaseName = MoonPhaseUtil.getMoonPhaseName(moonPhase);
        String modeName = MoonPhaseUtil.getModeName(mode);
        boolean isNight = MoonPhaseUtil.isNightTime(level);

        tooltip.add(Component.literal(""));
        tooltip.add(Component.literal("現在の月齢: " + phaseName)
                .withStyle(ChatFormatting.AQUA));
        tooltip.add(Component.literal("モード: " + modeName)
                .withStyle(mode == 0 ? ChatFormatting.RED :
                        mode == 1 ? ChatFormatting.DARK_PURPLE : ChatFormatting.GRAY));

        if (mode == 0) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("【パワーモード】").withStyle(ChatFormatting.RED, ChatFormatting.BOLD));
            tooltip.add(Component.literal("攻撃力: 9.0").withStyle(ChatFormatting.YELLOW));
            if (isNight) {
                float nightBonus = moonPhase == 0 ? 3.0F : 2.0F;
                tooltip.add(Component.literal("夜間攻撃力: " + (9.0F + nightBonus))
                        .withStyle(ChatFormatting.GOLD));
            }
            tooltip.add(Component.literal("パッシブ: ノックバック耐性+30%").withStyle(ChatFormatting.GREEN));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("特殊攻撃: ベアークラッシュ").withStyle(ChatFormatting.GREEN));
            tooltip.add(Component.literal("  範囲攻撃 12.0ダメージ").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("  クールダウン: 15秒").withStyle(ChatFormatting.GRAY));
        } else if (mode == 1) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("【スピードモード】").withStyle(ChatFormatting.DARK_PURPLE, ChatFormatting.BOLD));
            tooltip.add(Component.literal("攻撃力: 6.0 (攻撃速度+25%)").withStyle(ChatFormatting.YELLOW));
            if (isNight) {
                tooltip.add(Component.literal("夜間: 移動速度+20%").withStyle(ChatFormatting.GOLD));
                if (moonPhase == 4) {
                    tooltip.add(Component.literal("新月の夜: 完全透明化").withStyle(ChatFormatting.GOLD));
                }
            }
            tooltip.add(Component.literal("パッシブ: 敵検知範囲-30%").withStyle(ChatFormatting.GREEN));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("特殊攻撃: シャドウクロウ").withStyle(ChatFormatting.GREEN));
            tooltip.add(Component.literal("  3連撃 3.0×3 + 透明化2秒").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("  クールダウン: 12秒").withStyle(ChatFormatting.GRAY));
        } else {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("【移行期】").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("攻撃力: 7.5").withStyle(ChatFormatting.YELLOW));
            tooltip.add(Component.literal("特殊攻撃: 使用不可").withStyle(ChatFormatting.RED));
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
