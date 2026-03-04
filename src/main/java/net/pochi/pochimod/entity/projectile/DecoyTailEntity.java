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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.entity.ModEntityTypes;

import java.util.List;
import java.util.UUID;

public class DecoyTailEntity extends PathfinderMob {

    private static final int LIFETIME_TICKS = 60; // 3秒
    private static final double TAUNT_RADIUS = 8.0; // 注意を引く範囲

    // データシンク
    private static final EntityDataAccessor<Integer> OWNER_ID =
            SynchedEntityData.defineId(DecoyTailEntity.class, EntityDataSerializers.INT);

    // フィールド
    private int lifeTicks = 0;
    private UUID ownerUUID;
    private boolean hasTauntedEnemies = false;

    public DecoyTailEntity(EntityType<? extends DecoyTailEntity> type, Level level) {
        super(type, level);
        this.setNoGravity(false);
    }

    public DecoyTailEntity(Level level, LivingEntity owner, Vec3 position) {
        this(ModEntityTypes.DECOY_TAIL.get(), level);
        this.ownerUUID = owner.getUUID();
        this.entityData.set(OWNER_ID, owner.getId());

        // 位置設定
        this.setPos(position.x, position.y, position.z);

        // ランダムな初期速度（地面で跳ね回るイメージ）
        double vx = (this.random.nextDouble() - 0.5) * 0.4;
        double vy = 0.2 + this.random.nextDouble() * 0.3;
        double vz = (this.random.nextDouble() - 0.5) * 0.4;
        this.setDeltaMovement(vx, vy, vz);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
    }

    @Override
    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(OWNER_ID, -1);
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

        // 初回のみ近くの敵の注意を引く
        if (!hasTauntedEnemies && lifeTicks == 1) {
            this.tauntNearbyEnemies();
            hasTauntedEnemies = true;
        }

        // パーティクル効果
        if (this.level().isClientSide) {
            this.spawnWriggleParticles();
        }

        // 地面で跳ね回る動き
        if (this.onGround() && this.random.nextInt(10) == 0) {
            this.setDeltaMovement(
                    this.getDeltaMovement().x,
                    0.2 + this.random.nextDouble() * 0.1,
                    this.getDeltaMovement().z
            );
        }
    }

    /**
     * 近くの敵の注意を引く
     */
    private void tauntNearbyEnemies() {
        if (this.level().isClientSide) {
            return;
        }

        LivingEntity owner = this.getOwnerEntity();
        if (owner == null) {
            return;
        }

        // 範囲内の敵対Mobを取得
        AABB searchArea = this.getBoundingBox().inflate(TAUNT_RADIUS);
        List<Mob> nearbyMobs = this.level().getEntitiesOfClass(
                Mob.class,
                searchArea,
                mob -> mob.isAlive() && mob.getTarget() == owner
        );

        // ターゲットを囮に変更
        for (Mob mob : nearbyMobs) {
            mob.setTarget(this);
        }

        if (!nearbyMobs.isEmpty()) {
            // タウント成功音
            this.level().playSound(
                    null,
                    this.getX(), this.getY(), this.getZ(),
                    SoundEvents.ENDER_DRAGON_GROWL,
                    SoundSource.NEUTRAL,
                    0.3F,
                    2.0F
            );
        }
    }

    /**
     * 尻尾が動くパーティクル
     */
    private void spawnWriggleParticles() {
        if (this.random.nextInt(2) == 0) {
            this.level().addParticle(
                    ParticleTypes.HAPPY_VILLAGER,
                    this.getX() + (this.random.nextDouble() - 0.5) * 0.3,
                    this.getY() + 0.1,
                    this.getZ() + (this.random.nextDouble() - 0.5) * 0.3,
                    0, 0.02, 0
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
                    ParticleTypes.POOF,
                    this.getX(), this.getY() + 0.3, this.getZ(),
                    10,
                    0.2, 0.2, 0.2,
                    0.05
            );
        }

        this.level().playSound(
                null,
                this.getX(), this.getY(), this.getZ(),
                SoundEvents.CHICKEN_EGG,
                SoundSource.NEUTRAL,
                0.5F,
                1.5F
        );

        this.discard();
    }

    /**
     * オーナーエンティティを取得
     */
    private LivingEntity getOwnerEntity() {
        if (this.ownerUUID != null && this.level() instanceof ServerLevel serverLevel) {
            Entity entity = serverLevel.getEntity(this.ownerUUID);
            if (entity instanceof LivingEntity living) {
                return living;
            }
        }
        return null;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        // 一撃で消える
        if (!this.level().isClientSide) {
            this.despawnWithEffect();
        }
        return super.hurt(source, amount);
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("LifeTicks", this.lifeTicks);
        compound.putBoolean("HasTaunted", this.hasTauntedEnemies);
        if (this.ownerUUID != null) {
            compound.putUUID("OwnerUUID", this.ownerUUID);
        }
        compound.putInt("OwnerId", this.entityData.get(OWNER_ID));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.lifeTicks = compound.getInt("LifeTicks");
        this.hasTauntedEnemies = compound.getBoolean("HasTaunted");
        if (compound.hasUUID("OwnerUUID")) {
            this.ownerUUID = compound.getUUID("OwnerUUID");
        }
        if (compound.contains("OwnerId")) {
            this.entityData.set(OWNER_ID, compound.getInt("OwnerId"));
        }
    }
}