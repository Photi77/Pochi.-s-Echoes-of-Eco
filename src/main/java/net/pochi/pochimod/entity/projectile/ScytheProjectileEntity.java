package net.pochi.pochimod.entity.projectile;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.entity.ModEntityTypes;

public class ScytheProjectileEntity extends ThrowableItemProjectile {

    // 跳弾関連の定数
    private static final int MAX_BOUNCES = 15; // 最大跳弾回数
    private static final float BOUNCE_DAMAGE_MULTIPLIER = 0.8F; // 跳弾ごとのダメージ減衰

    // フィールド
    private int tickCount = 0;
    private int maxFlightTicks = 200;
    private double maxDistance = 12.0;
    private Vec3 initialPos;
    private int bounceCount = 0; // 現在の跳弾回数

    // ダメージ
    private float baseDamage = 5.0F;

    // データシンク
    private static int OWNER_ID;
    private static boolean IS_RETURNING;
    private static float ROTATION_ANGLE;

    // フィールド
    //private Vec3 initialPos;
    private int flightTicks = 0;
    private boolean hasHitEntity = false; // 往路でエンティティにヒットしたか

    public ScytheProjectileEntity(EntityType<? extends ScytheProjectileEntity> type, Level level) {
        super(type, level);
    }

    public ScytheProjectileEntity(Level level, LivingEntity shooter) {
        super(ModEntityTypes.SCYTHE_PROJECTILE.get(), shooter, level);
        this.OWNER_ID= shooter.getId();
        this.IS_RETURNING= false;
        this.ROTATION_ANGLE= 0.0F;
        this.initialPos = shooter.position();
    }


    @Override
    protected Item getDefaultItem() {
        return Items.STONE_SWORD;
    }

    /**
     * 帰還モードを設定
     */
    public void setReturning(boolean returning) {
        this.IS_RETURNING = returning;
    }

    /**
     * 帰還中かチェック
     */
    //public boolean isReturning() {
    //    return this.IS_RETURNING;
    //}


    @Override
    public void tick() {
        super.tick();

        tickCount++;

        // ブーメランモード
        Entity owner = this.getOwner();
        if (owner == null || !owner.isAlive()) {
            this.discard();
            return;
        }


            if (tickCount >= maxFlightTicks) {
                Vec3 toOwner = owner.position().add(0, owner.getEyeHeight() * 0.5, 0).subtract(this.position());
                double distance = toOwner.length();

                // オーナーに到達したら回収
                if (distance < 1.0) {
                    this.discard();
                    this.playSound(SoundEvents.ITEM_PICKUP, 0.5F, 1.5F);
                    return;
                }

                // オーナーに向かって移動
                Vec3 velocity = toOwner.normalize().scale(0.5);
                this.setDeltaMovement(velocity);
            }


        // ブーメランパーティクル
        if (this.level().isClientSide) {
            this.spawnPetalParticles();
        }
    }

    /**
     * 帰還開始
     */
    private void startReturning() {
        if (!this.isReturning()) {
            IS_RETURNING= true;

            // 帰還音
            this.level().playSound(
                    null,
                    this.getX(), this.getY(), this.getZ(),
                    SoundEvents.PLAYER_ATTACK_SWEEP,
                    SoundSource.PLAYERS,
                    0.5F,
                    1.5F
            );
        }
    }

    /**
     * 帰還中かチェック
     */
    public boolean isReturning() {
        return IS_RETURNING;
    }

    /**
     * 回転角度取得
     */
    public float getRotationAngle() {
        return ROTATION_ANGLE;
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

            // LivingEntityのみ対象
            if (!(target instanceof LivingEntity livingTarget)) {
                return;
            }

            // ダメージ計算（跳弾ごとに減衰）
            float damage = this.baseDamage;
            damage *= Math.pow(BOUNCE_DAMAGE_MULTIPLIER, this.bounceCount);


            livingTarget.invulnerableTime = 0;
            // ダメージ適用
            DamageSource damageSource = this.damageSources().thrown(this, owner);

            livingTarget.hurt(damageSource, damage);

            // ヒット音
            this.level().playSound(
                    null,
                    target.getX(), target.getY(), target.getZ(),
                    SoundEvents.PLAYER_ATTACK_SWEEP,
                    SoundSource.PLAYERS,
                    0.7F,
                    1.2F + this.bounceCount * 0.2F
            );

            // ヒットパーティクル
            if (this.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        ParticleTypes.HEART,
                        target.getX(), target.getY() + target.getBbHeight() / 2, target.getZ(),
                        10,
                        0.3, 0.3, 0.3,
                        0.1
                );
            }

            if (this.bounceCount < MAX_BOUNCES) {
                this.performBounce();
            } else {
                // 最大跳弾回数に達したら帰還
                this.setReturning(true);
            }
        }
    }

    /**
     * 跳弾処理
     */
    private void performBounce() {
        this.bounceCount++;

        // ランダムな方向へ跳弾
        Vec3 randomDirection = new Vec3(
                (this.random.nextDouble() - 0.5),
                (this.random.nextDouble() - 0.5),
                (this.random.nextDouble() - 0.5)
        ).normalize();

        // 現在の速度の大きさを維持
        double currentSpeed = this.getDeltaMovement().length();
        Vec3 newVelocity = randomDirection.scale(0.5);

        this.setDeltaMovement(newVelocity);

        // 跳弾エフェクト
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    ParticleTypes.FIREWORK,
                    this.getX(), this.getY(), this.getZ(),
                    15,
                    0.3, 0.3, 0.3,
                    0.1
            );
        }

        // 跳弾音
        this.level().playSound(
                null,
                this.getX(), this.getY(), this.getZ(),
                SoundEvents.ARROW_HIT_PLAYER,
                SoundSource.PLAYERS,
                0.5F,
                1.5F + this.bounceCount * 0.3F
        );
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);

        if (this.bounceCount < MAX_BOUNCES) {
            this.performBounce();
        } else {
            // 最大跳弾回数に達したら帰還
            this.setReturning(true);
        }
    }

    /**
     * 鎌のパーティクル
     */
    private void spawnScytheParticles() {
        if (this.random.nextInt(2) == 0) {
            this.level().addParticle(
                    ParticleTypes.SWEEP_ATTACK,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    0, 0, 0
            );
        }
    }

    /**
     * 花びらのパーティクル
     */
    private void spawnPetalParticles() {
        if (this.random.nextInt(2) == 0) {
            this.level().addParticle(
                    ParticleTypes.CHERRY_LEAVES,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    0, 0, 0
            );
        }

        if (this.random.nextInt(5) == 0) {
            this.level().addParticle(
                    ParticleTypes.SCRAPE,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    0, 0.02, 0
            );
        }
    }

    @Override
    protected double getDefaultGravity() {
        return 0.01;
    }

    @Override
    public boolean ignoreExplosion(net.minecraft.world.level.Explosion pExplosion) {
        return true;
    }

}