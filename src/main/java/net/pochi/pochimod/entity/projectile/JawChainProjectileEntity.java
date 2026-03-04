package net.pochi.pochimod.entity.projectile;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.entity.ModEntityTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JawChainProjectileEntity extends ThrowableItemProjectile {

    private static final int MAX_FLIGHT_TICKS = 100; // 5秒
    private static final double MAX_DISTANCE = 10.0;
    private static final int MAX_HIT_COUNT = 3; // 最大3体まで
    private static final double PULL_SPEED = 1.5;
    private static final double BITE_DISTANCE = 4.0; // プレイヤーから2ブロック
    private static final float BITE_DAMAGE = 16.0F;

    // データシンク

    // フィールド
    private Vec3 initialPos;
    private int flightTicks = 0;
    private List<UUID> caughtEntities = new ArrayList<>(); // 捕まえたエンティティのUUID
    private boolean isReturning = false;

    public JawChainProjectileEntity(EntityType<? extends JawChainProjectileEntity> type, Level level) {
        super(type, level);
    }

    public JawChainProjectileEntity(Level level, LivingEntity shooter) {
        super(ModEntityTypes.JAW_CHAIN_PROJECTILE.get(), shooter, level);
        this.initialPos = shooter.position();
    }

    @Override
    public void tick() {
        super.tick();

        flightTicks++;

        // オーナー取得
        Entity owner = this.getOwner();
        if (owner == null || !owner.isAlive()) {
            this.discard();
            return;
        }

        // 最大飛行距離または時間に達したら消滅

        if (flightTicks >= MAX_FLIGHT_TICKS) {
            this.discard();
            return;
        }

        // 引き寄せ処理（Phase 2で実装）
        if (!this.caughtEntities.isEmpty() && owner instanceof Player player) {
            this.pullCaughtEntities(player);
        }

        // パーティクル効果
        if (this.level().isClientSide) {
            this.spawnTrailParticles();
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

            // LivingEntityのみ対象
            if (!(target instanceof LivingEntity livingTarget)) {
                return;
            }

            // 既に捕まえている場合はスキップ
            if (this.caughtEntities.contains(target.getUUID())) {
                return;
            }

            // 最大数チェック
            if (this.caughtEntities.size() >= MAX_HIT_COUNT) {
                return;
            }

            // エンティティを捕獲リストに追加
            this.caughtEntities.add(target.getUUID());

            // ヒット音
            this.level().playSound(
                    null,
                    target.getX(), target.getY(), target.getZ(),
                    SoundEvents.IRON_GOLEM_HURT,
                    SoundSource.PLAYERS,
                    0.8F,
                    1.2F
            );

            // ヒットパーティクル
            if (this.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        ParticleTypes.CRIT,
                        target.getX(), target.getY() + target.getBbHeight() / 2, target.getZ(),
                        10,
                        0.3, 0.3, 0.3,
                        0.1
                );
            }

            // 最大数に達したら消滅（引き寄せ開始）
            if (this.caughtEntities.size() >= MAX_HIT_COUNT) {
                this.isReturning = true;
            }

            // 貫通して飛び続ける（最大3体まで）
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);

        if (!this.level().isClientSide) {
            // ブロックに当たったら消滅
            this.discard();
        }
    }

    /**
     * 捕まえたエンティティを引き寄せる（Phase 2で実装）
     */
    private void pullCaughtEntities(Player player) {
        List<UUID> toRemove = new ArrayList<>();

        for (UUID entityUUID : this.caughtEntities) {
            if (this.level() instanceof ServerLevel serverLevel) {
                Entity entity = serverLevel.getEntity(entityUUID);

                if (entity instanceof LivingEntity livingEntity && livingEntity.isAlive()) {
                    // プレイヤーへの方向ベクトル
                    Vec3 toPlayer = player.position().subtract(livingEntity.position());
                    double distance = toPlayer.length();

                    // 十分近づいたら噛みつき
                    if (distance <= BITE_DISTANCE) {
                        this.performBite(livingEntity, player);
                        toRemove.add(entityUUID);
                    } else {
                        // 引き寄せ
                        Vec3 pullVelocity = toPlayer.normalize().scale(PULL_SPEED);
                        livingEntity.setDeltaMovement(pullVelocity);

                        // 引き寄せ中のパーティクル
                        serverLevel.sendParticles(
                                ParticleTypes.SMOKE,
                                livingEntity.getX(),
                                livingEntity.getY() + livingEntity.getBbHeight() / 2,
                                livingEntity.getZ(),
                                2,
                                0.2, 0.2, 0.2,
                                0.01
                        );
                    }
                } else {
                    // エンティティが死んだか存在しない
                    toRemove.add(entityUUID);
                }
            }
        }

        // 処理済みエンティティを削除
        this.caughtEntities.removeAll(toRemove);

        // 全て処理完了したら消滅
        if (this.caughtEntities.isEmpty()) {
            this.discard();
        }
    }

    /**
     * 噛みつき攻撃
     */
    private void performBite(LivingEntity target, Player player) {
        // ダメージ適用
        DamageSource damageSource = this.damageSources().playerAttack(player);
        target.hurt(damageSource, BITE_DAMAGE);

        // 噛みつきエフェクト
        if (this.level() instanceof ServerLevel serverLevel) {
            // 血しぶき（赤パーティクル）
            serverLevel.sendParticles(
                    ParticleTypes.DAMAGE_INDICATOR,
                    target.getX(), target.getY() + target.getBbHeight() / 2, target.getZ(),
                    20,
                    0.5, 0.5, 0.5,
                    0.2
            );

            // クリティカルヒットパーティクル
            serverLevel.sendParticles(
                    ParticleTypes.CRIT,
                    target.getX(), target.getY() + target.getBbHeight() / 2, target.getZ(),
                    15,
                    0.3, 0.3, 0.3,
                    0.1
            );
        }

        // 噛みつき音
        this.level().playSound(
                null,
                target.getX(), target.getY(), target.getZ(),
                SoundEvents.WOLF_GROWL,
                SoundSource.PLAYERS,
                1.0F,
                0.8F
        );

        this.level().playSound(
                null,
                target.getX(), target.getY(), target.getZ(),
                SoundEvents.PLAYER_ATTACK_CRIT,
                SoundSource.PLAYERS,
                1.0F,
                1.0F
        );
    }

    /**
     * 飛行中のパーティクル
     */
    private void spawnTrailParticles() {
        // 鎖のイメージ（暗い煙）
        for (int i = 0; i < 2; i++) {
            this.level().addParticle(
                    ParticleTypes.SMOKE,
                    this.getX() + (this.random.nextDouble() - 0.5) * 0.3,
                    this.getY() + (this.random.nextDouble() - 0.5) * 0.3,
                    this.getZ() + (this.random.nextDouble() - 0.5) * 0.3,
                    0, 0, 0
            );
        }

        // 顎のイメージ（水しぶき）
        if (this.random.nextInt(3) == 0) {
            this.level().addParticle(
                    ParticleTypes.SPLASH,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    0, 0, 0
            );
        }
    }

    @Override
    protected double getDefaultGravity() {
        return 0.0;
    }

    @Override
    public boolean ignoreExplosion(net.minecraft.world.level.Explosion pExplosion) {
        return true;
    }

    @Override
    protected Item getDefaultItem() {
        return Items.SLIME_BALL;
    }
}
