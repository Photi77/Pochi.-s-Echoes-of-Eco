package net.pochi.pochimod.entity.projectile;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.pochi.pochimod.entity.ModEntityTypes;

public class IceProjectileEntity extends ThrowableItemProjectile {

    private static final float DAMAGE = 4.0F;
    private static final int SLOWNESS_DURATION = 60; // 3秒 (20tick = 1秒)
    private static final int SLOWNESS_AMPLIFIER = 1; // レベル2（移動速度-30%）

    public IceProjectileEntity(EntityType<? extends IceProjectileEntity> type, Level level) {
        super(type, level);
    }

    public IceProjectileEntity(Level level, LivingEntity shooter) {
        super(ModEntityTypes.ICE_PROJECTILE.get(), shooter, level);
    }

    public IceProjectileEntity(Level p_37394_, double p_37395_, double p_37396_, double p_37397_) {
        super(ModEntityTypes.ICE_PROJECTILE.get(), p_37395_, p_37396_, p_37397_, p_37394_);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.EGG;
    }

    @Override
    public void tick() {
        super.tick();

        // クライアント側：飛行中のパーティクル
        if (this.level().isClientSide) {
            // 氷晶の軌跡
            for (int i = 0; i < 2; i++) {
                this.level().addParticle(
                        ParticleTypes.SNOWFLAKE,
                        this.getX() + (this.random.nextDouble() - 0.5) * 0.3,
                        this.getY() + (this.random.nextDouble() - 0.5) * 0.3,
                        this.getZ() + (this.random.nextDouble() - 0.5) * 0.3,
                        0, -0.05, 0
                );
            }

            // 青白い光のパーティクル
            if (this.random.nextInt(3) == 0) {
                this.level().addParticle(
                        ParticleTypes.GLOW,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        0, 0, 0
                );
            }
        }

        // 20秒経過で自動消滅
        if (this.tickCount > 400) {
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        if (!this.level().isClientSide) {
            Entity target = result.getEntity();
            Entity owner = this.getOwner();

            // オーナー自身には当たらない
            if (target == owner) {
                return;
            }

            // ダメージ適用
            DamageSource damageSource = this.damageSources().indirectMagic(this, owner);
            boolean hit = target.hurt(damageSource, DAMAGE);

            if (hit && target instanceof LivingEntity livingTarget) {
                // 移動速度低下効果
                livingTarget.addEffect(
                        new MobEffectInstance(
                                MobEffects.MOVEMENT_SLOWDOWN,
                                SLOWNESS_DURATION,
                                SLOWNESS_AMPLIFIER,
                                false, // アンビエント効果ではない
                                true,  // パーティクル表示
                                true   // アイコン表示
                        )
                );
            }

            // 着弾エフェクト
            spawnImpactEffects();

            // 発射体を消滅
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);

        if (!this.level().isClientSide) {
            // 着弾エフェクト
            spawnImpactEffects();

            // 発射体を消滅
            this.discard();
        }
    }

    /**
     * 着弾時のエフェクト
     */
    private void spawnImpactEffects() {
        // パーティクル爆発
        for (int i = 0; i < 20; i++) {
            double offsetX = (this.random.nextDouble() - 0.5) * 0.5;
            double offsetY = (this.random.nextDouble() - 0.5) * 0.5;
            double offsetZ = (this.random.nextDouble() - 0.5) * 0.5;

            this.level().addParticle(
                    ParticleTypes.SNOWFLAKE,
                    this.getX() + offsetX,
                    this.getY() + offsetY,
                    this.getZ() + offsetZ,
                    offsetX * 0.5,
                    offsetY * 0.5,
                    offsetZ * 0.5
            );
        }

        // 氷が砕ける音
        this.level().playSound(
                null,
                this.getX(), this.getY(), this.getZ(),
                SoundEvents.GLASS_BREAK,
                SoundSource.PLAYERS,
                0.5F,
                1.2F + this.random.nextFloat() * 0.4F
        );
    }

    @Override
    protected double getDefaultGravity() {
        return 0.0;
    }

    @Override
    public boolean ignoreExplosion(net.minecraft.world.level.Explosion pExplosion) {
        return true;
    }

}