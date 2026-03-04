package net.pochi.pochimod.entity.projectile;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.entity.ModEntityTypes;
import net.pochi.pochimod.entity.ai.ClioneGuardGoal;

import java.util.UUID;

public class SummonedClioneEntity extends PathfinderMob {

    // 定数
    private static final int LIFETIME_TICKS = 600; // 30秒
    private static final double ORBIT_RADIUS = 3.0; // 旋回半径
    private static final double ORBIT_SPEED = 0.05; // 旋回速度
    private static final double VERTICAL_OFFSET = 1.5; // プレイヤーからの高さオフセット
    private static final float ATTACK_DAMAGE = 3.0F;
    private static final int FREEZE_DURATION = 100; // 5秒
    private static final int FREEZE_AMPLIFIER = 3; // レベル4（移動速度-70%）
    private static final double ATTACK_RANGE = 5.0; // 攻撃範囲
    private static final int ATTACK_COOLDOWN = 40; // 2秒

    // データシンク
    private static final EntityDataAccessor<Integer> OWNER_ID =
            SynchedEntityData.defineId(SummonedClioneEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> INDEX =
            SynchedEntityData.defineId(SummonedClioneEntity.class, EntityDataSerializers.INT);

    // フィールド
    private int lifeTicks = 0;
    private UUID ownerUUID;
    private double orbitAngle = 0.0;
    private int attackCooldown = 0;

    public SummonedClioneEntity(EntityType<? extends SummonedClioneEntity> type, Level level) {
        super(type, level);
        this.setNoGravity(true); // 浮遊
    }

    public SummonedClioneEntity(Level level, Player owner, int index) {
        this(ModEntityTypes.SUMMONED_CLIONE.get(), level);
        this.ownerUUID = owner.getUUID();
        this.entityData.set(OWNER_ID, owner.getId());
        this.entityData.set(INDEX, index);

        // 初期角度をindexに基づいて設定（3体が均等に配置される）
        this.orbitAngle = (Math.PI * 2 / 3) * index;
    }


    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.FOLLOW_RANGE, ATTACK_RANGE)
                .add(Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE);
    }

    @Override
    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(OWNER_ID, -1);
        builder.define(INDEX, 0);
    }

    @Override
    public void tick() {
        super.tick();

        // 寿命チェック
        lifeTicks++;
        if (lifeTicks >= LIFETIME_TICKS) {
            if (!this.level().isClientSide) {
                this.despawnWithEffect();
            }
            return;
        }

        // オーナーの取得
        Player owner = this.getOwnerPlayer();
        if (owner == null || !owner.isAlive()) {
            if (!this.level().isClientSide) {
                this.despawnWithEffect();
            }
            return;
        }

        // 旋回移動
        this.updateOrbitPosition(owner);

        // パーティクル効果（クライアント側）
        if (this.level().isClientSide) {
            this.spawnOrbitParticles();
            this.spawnBodyGlow(); // 本体の発光エフェクト
        }

        // 攻撃クールダウン減少（常に減少させる）
        if (attackCooldown > 0) {
            attackCooldown--;
        }
    }

    public boolean isAttackOnCooldown() {
        return this.attackCooldown > 0;
    }



    /**
     * オーナー周囲を旋回
     */
    private void updateOrbitPosition(Player owner) {
        // 旋回角度を更新
        orbitAngle += ORBIT_SPEED;
        if (orbitAngle > Math.PI * 2) {
            orbitAngle -= Math.PI * 2;
        }

        // 目標位置計算
        double targetX = owner.getX() + Math.cos(orbitAngle) * ORBIT_RADIUS;
        double targetY = owner.getY() + VERTICAL_OFFSET;
        double targetZ = owner.getZ() + Math.sin(orbitAngle) * ORBIT_RADIUS;

        // スムーズに移動
        Vec3 currentPos = this.position();
        Vec3 targetPos = new Vec3(targetX, targetY, targetZ);
        Vec3 moveVec = targetPos.subtract(currentPos).scale(0.2);

        this.setDeltaMovement(moveVec);
        this.move(net.minecraft.world.entity.MoverType.SELF, this.getDeltaMovement());

        // オーナーの方を向く
        this.lookAt(owner, 30.0F, 30.0F);
    }

    /**
     * 攻撃時のエフェクト
     */
    private void spawnAttackEffects(LivingEntity target) {
        if (this.level() instanceof ServerLevel serverLevel) {
            // 氷の線エフェクト（クリオネ→ターゲット）
            Vec3 start = this.position().add(0, 0.5, 0);
            Vec3 end = target.position().add(0, target.getBbHeight() / 2, 0);
            Vec3 direction = end.subtract(start);
            double distance = direction.length();
            direction = direction.normalize();

            for (int i = 0; i < (int)(distance * 5); i++) {
                Vec3 pos = start.add(direction.scale(i * 0.2));
                serverLevel.sendParticles(
                        ParticleTypes.SNOWFLAKE,
                        pos.x, pos.y, pos.z,
                        1,
                        0.05, 0.05, 0.05,
                        0.01
                );
            }

            // ターゲットに氷結パーティクル
            serverLevel.sendParticles(
                    ParticleTypes.SNOWFLAKE,
                    target.getX(), target.getY() + target.getBbHeight() / 2, target.getZ(),
                    15,
                    0.3, 0.5, 0.3,
                    0.05
            );
        }

        // 攻撃音
        this.level().playSound(
                null,
                target.getX(), target.getY(), target.getZ(),
                SoundEvents.PLAYER_HURT_FREEZE,
                SoundSource.HOSTILE,
                0.5F,
                1.5F
        );
    }

    /**
     * 旋回軌道のパーティクル
     */
    private void spawnOrbitParticles() {
        if (this.random.nextInt(2) == 0) {
            this.level().addParticle(
                    ParticleTypes.GLOW,
                    this.getX() + (this.random.nextDouble() - 0.5) * 0.3,
                    this.getY() + (this.random.nextDouble() - 0.5) * 0.3,
                    this.getZ() + (this.random.nextDouble() - 0.5) * 0.3,
                    0, 0, 0
            );
        }
    }

    /**
     * エフェクト付きで消滅
     */
    private void despawnWithEffect() {
        if (this.level() instanceof ServerLevel serverLevel) {
            // 消滅パーティクル
            serverLevel.sendParticles(
                    ParticleTypes.SNOWFLAKE,
                    this.getX(), this.getY() + 0.5, this.getZ(),
                    20,
                    0.3, 0.3, 0.3,
                    0.05
            );
        }

        this.level().playSound(
                null,
                this.getX(), this.getY(), this.getZ(),
                SoundEvents.SNOW_BREAK,
                SoundSource.NEUTRAL,
                0.5F,
                1.5F
        );

        this.discard();
    }

    /**
     * オーナープレイヤーを取得
     */
    public Player getOwnerPlayer() {
        if (this.ownerUUID != null && this.level() instanceof ServerLevel serverLevel) {
            Entity entity = serverLevel.getEntity(this.ownerUUID);
            if (entity instanceof Player player) {
                return player;
            }
        }
        return null;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        // オーナーからのダメージは無効
        if (source.getEntity() == this.getOwnerPlayer()) {
            return false;
        }
        return super.hurt(source, amount);
    }

    @Override
    public boolean isPushable() {
        return false; // ノックバック無効
    }

    @Override
    public boolean canBeCollidedWith() {
        return false; // 当たり判定なし
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("LifeTicks", this.lifeTicks);
        compound.putInt("AttackCooldown", this.attackCooldown);
        compound.putDouble("OrbitAngle", this.orbitAngle);
        if (this.ownerUUID != null) {
            compound.putUUID("OwnerUUID", this.ownerUUID);
        }
        compound.putInt("OwnerId", this.entityData.get(OWNER_ID));
        compound.putInt("Index", this.entityData.get(INDEX));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.lifeTicks = compound.getInt("LifeTicks");
        this.attackCooldown = compound.getInt("AttackCooldown");
        this.orbitAngle = compound.getDouble("OrbitAngle");
        if (compound.hasUUID("OwnerUUID")) {
            this.ownerUUID = compound.getUUID("OwnerUUID");
        }
        if (compound.contains("OwnerId")) {
            this.entityData.set(OWNER_ID, compound.getInt("OwnerId"));
        }
        if (compound.contains("Index")) {
            this.entityData.set(INDEX, compound.getInt("Index"));
        }
    }

    private ClioneGuardGoal guardGoal;

    // ... 既存のコンストラクタ ...

    @Override
    protected void registerGoals() {
        // カスタムAIゴールを登録
        this.guardGoal = new ClioneGuardGoal(this, ATTACK_RANGE);
        this.goalSelector.addGoal(1, this.guardGoal);
    }

    /**
     * 攻撃を実行（AIから呼び出される）
     */
    public void performAttack(LivingEntity target) {
        // ダメージ適用
        DamageSource damageSource = this.damageSources().indirectMagic(this, this.getOwnerPlayer());
        boolean hit = target.hurt(damageSource, ATTACK_DAMAGE);

        if (hit) {
            // 氷結効果
            target.addEffect(
                    new MobEffectInstance(
                            MobEffects.MOVEMENT_SLOWDOWN,
                            FREEZE_DURATION,
                            FREEZE_AMPLIFIER,
                            false,
                            true,
                            true
                    )
            );

            // 攻撃エフェクト
            this.spawnAttackEffects(target);

            this.attackCooldown = ATTACK_COOLDOWN;
        }
    }

    /**
     * 本体の発光エフェクト
     */
    private void spawnBodyGlow() {
        if (this.random.nextInt(3) == 0) {
            this.level().addParticle(
                    ParticleTypes.END_ROD,
                    this.getX() + (this.random.nextDouble() - 0.5) * 0.4,
                    this.getY() + (this.random.nextDouble() - 0.5) * 0.4,
                    this.getZ() + (this.random.nextDouble() - 0.5) * 0.4,
                    0, 0.01, 0
            );
        }
    }
}