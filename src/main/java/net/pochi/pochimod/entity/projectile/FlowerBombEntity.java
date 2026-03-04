package net.pochi.pochimod.entity.projectile;

import net.minecraft.core.BlockPos;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.entity.ModEntityTypes;

import java.util.List;
import java.util.UUID;

public class FlowerBombEntity extends Entity {

    private static final int WARN_DURATION_TICKS = 20; // 1秒の警告時間
    private static final float EXPLOSION_DAMAGE = 4.0F;
    private static final double EXPLOSION_RADIUS = 3.0;
    private static final float KNOCKBACK_STRENGTH = 0.5F;

    // データシンク
    private static final EntityDataAccessor<Integer> OWNER_ID =
            SynchedEntityData.defineId(FlowerBombEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> WARN_TICKS =
            SynchedEntityData.defineId(FlowerBombEntity.class, EntityDataSerializers.INT);

    // フィールド
    private UUID ownerUUID;
    private BlockPos flowerPos;

    public FlowerBombEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.noPhysics = true;
    }

    public FlowerBombEntity(Level level, BlockPos flowerPos, Player owner) {
        this(ModEntityTypes.FLOWER_BOMB.get(), level);
        this.flowerPos = flowerPos;
        this.ownerUUID = owner.getUUID();
        this.entityData.set(OWNER_ID, owner.getId());
        this.entityData.set(WARN_TICKS, WARN_DURATION_TICKS);

        // 花の位置に配置
        this.setPos(flowerPos.getX() + 0.5, flowerPos.getY() + 0.5, flowerPos.getZ() + 0.5);
    }

    @Override
    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
        builder.define(OWNER_ID, -1);
        builder.define(WARN_TICKS, WARN_DURATION_TICKS);
    }

    @Override
    public void tick() {
        super.tick();

        int warnTicks = this.entityData.get(WARN_TICKS);

        if (warnTicks > 0) {
            // 警告期間
            warnTicks--;
            this.entityData.set(WARN_TICKS, warnTicks);

            // 警告パーティクル
            if (this.level().isClientSide) {
                this.spawnWarningParticles();
            }

            // 警告音（0.5秒ごと）
            if (warnTicks % 10 == 0) {
                this.level().playSound(
                        null,
                        this.getX(), this.getY(), this.getZ(),
                        SoundEvents.NOTE_BLOCK_BELL.value(),
                        SoundSource.BLOCKS,
                        0.5F,
                        1.5F + (float)(WARN_DURATION_TICKS - warnTicks) / 20.0F
                );
            }
        } else {
            // 爆発実行
            if (!this.level().isClientSide) {
                this.explode();
            }
            this.discard();
        }
    }

    /**
     * 警告パーティクル
     */
    private void spawnWarningParticles() {
        int warnTicks = this.entityData.get(WARN_TICKS);
        int particleCount = Math.max(1, warnTicks / 4);

        for (int i = 0; i < particleCount; i++) {
            // 花の周りに上昇するパーティクル
            double offsetX = (this.random.nextDouble() - 0.5) * 0.5;
            double offsetZ = (this.random.nextDouble() - 0.5) * 0.5;

            this.level().addParticle(
                    ParticleTypes.CHERRY_LEAVES,
                    this.getX() + offsetX,
                    this.getY(),
                    this.getZ() + offsetZ,
                    0,
                    0.1,
                    0
            );
        }

        // 脈動する赤いパーティクル
        if (warnTicks % 5 == 0) {
            for (int i = 0; i < 8; i++) {
                double angle = Math.PI * 2 * i / 8;
                double radius = 0.5;

                this.level().addParticle(
                        ParticleTypes.FLAME,
                        this.getX() + Math.cos(angle) * radius,
                        this.getY() + 0.2,
                        this.getZ() + Math.sin(angle) * radius,
                        0, 0.05, 0
                );
            }
        }
    }

    /**
     * 爆発処理
     */
    private void explode() {
        Player owner = this.getOwnerPlayer();

        // 爆発エフェクト
        if (this.level() instanceof ServerLevel serverLevel) {
            this.spawnExplosionParticles(serverLevel);
        }

        // 爆発音
        this.level().playSound(
                null,
                this.getX(), this.getY(), this.getZ(),
                SoundEvents.GENERIC_EXPLODE,
                SoundSource.BLOCKS,
                1.0F,
                1.2F + this.random.nextFloat() * 0.3F
        );

        // 範囲内の敵にダメージ
        AABB explosionArea = new AABB(this.position(), this.position()).inflate(EXPLOSION_RADIUS);
        List<LivingEntity> entities = this.level().getEntitiesOfClass(
                LivingEntity.class,
                explosionArea,
                entity -> entity != owner && !entity.isAlliedTo(owner)
        );

        for (LivingEntity entity : entities) {
            double distance = entity.position().distanceTo(this.position());
            if (distance <= EXPLOSION_RADIUS) {
                // 距離に応じてダメージ減衰
                float damageMultiplier = 1.0F - (float)(distance / EXPLOSION_RADIUS) * 0.5F;
                float actualDamage = EXPLOSION_DAMAGE * damageMultiplier;

                // ダメージ適用
                DamageSource damageSource = this.damageSources().explosion(this, owner);
                entity.hurt(damageSource, actualDamage);

                // ノックバック
                Vec3 knockback = entity.position().subtract(this.position()).normalize().scale(KNOCKBACK_STRENGTH);
                entity.setDeltaMovement(entity.getDeltaMovement().add(knockback.x, 0.3, knockback.z));
            }
        }
    }

    /**
     * 爆発パーティクル
     */
    private void spawnExplosionParticles(ServerLevel level) {
        // 大きな爆発パーティクル
        level.sendParticles(
                ParticleTypes.EXPLOSION,
                this.getX(), this.getY(), this.getZ(),
                1,
                0, 0, 0,
                0
        );

        // 花びらの飛散
        level.sendParticles(
                ParticleTypes.CHERRY_LEAVES,
                this.getX(), this.getY(), this.getZ(),
                30,
                EXPLOSION_RADIUS * 0.5, EXPLOSION_RADIUS * 0.5, EXPLOSION_RADIUS * 0.5,
                0.2
        );

        // ピンクの煙
        level.sendParticles(
                ParticleTypes.CAMPFIRE_COSY_SMOKE,
                this.getX(), this.getY(), this.getZ(),
                15,
                EXPLOSION_RADIUS * 0.3, EXPLOSION_RADIUS * 0.3, EXPLOSION_RADIUS * 0.3,
                0.05
        );

        // 火花
        level.sendParticles(
                ParticleTypes.LAVA,
                this.getX(), this.getY(), this.getZ(),
                10,
                EXPLOSION_RADIUS * 0.2, EXPLOSION_RADIUS * 0.2, EXPLOSION_RADIUS * 0.2,
                0.1
        );
    }

    /**
     * オーナープレイヤーを取得
     */
    private Player getOwnerPlayer() {
        if (this.ownerUUID != null && this.level() instanceof ServerLevel serverLevel) {
            Entity entity = serverLevel.getEntity(this.ownerUUID);
            if (entity instanceof Player player) {
                return player;
            }
        }
        return null;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        if (compound.hasUUID("OwnerUUID")) {
            this.ownerUUID = compound.getUUID("OwnerUUID");
        }
        if (compound.contains("OwnerId")) {
            this.entityData.set(OWNER_ID, compound.getInt("OwnerId"));
        }
        if (compound.contains("WarnTicks")) {
            this.entityData.set(WARN_TICKS, compound.getInt("WarnTicks"));
        }
        if (compound.contains("FlowerX")) {
            this.flowerPos = new BlockPos(
                    compound.getInt("FlowerX"),
                    compound.getInt("FlowerY"),
                    compound.getInt("FlowerZ")
            );
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        if (this.ownerUUID != null) {
            compound.putUUID("OwnerUUID", this.ownerUUID);
        }
        compound.putInt("OwnerId", this.entityData.get(OWNER_ID));
        compound.putInt("WarnTicks", this.entityData.get(WARN_TICKS));
        if (this.flowerPos != null) {
            compound.putInt("FlowerX", this.flowerPos.getX());
            compound.putInt("FlowerY", this.flowerPos.getY());
            compound.putInt("FlowerZ", this.flowerPos.getZ());
        }
    }
}