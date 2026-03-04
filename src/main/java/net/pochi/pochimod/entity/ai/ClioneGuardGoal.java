package net.pochi.pochimod.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.pochi.pochimod.entity.projectile.SummonedClioneEntity;

import java.util.EnumSet;
import java.util.List;

public class ClioneGuardGoal extends Goal {

    private final SummonedClioneEntity clione;
    private final double attackRange;
    //private final int attackCooldown;
    private LivingEntity currentTarget;
    private int cooldownTicks;

    public ClioneGuardGoal(SummonedClioneEntity clione, double attackRange) {
        this.clione = clione;
        this.attackRange = attackRange;
        //this.attackCooldown = attackCooldown;
        this.cooldownTicks = 0;
        this.setFlags(EnumSet.of(Goal.Flag.TARGET));
    }

    @Override
    public boolean canUse() {
        // オーナーが存在し、クールダウンが終了している
        Player owner = this.clione.getOwnerPlayer();
        if (owner == null || !owner.isAlive()) {
            return false;
        }

        if (this.cooldownTicks > 0) {
            return false;
        }

        // 近くの敵を探す
        this.currentTarget = this.findNearestEnemy(owner);
        return this.currentTarget != null;
    }

    @Override
    public boolean canContinueToUse() {
        // ターゲットが有効かチェック
        if (this.currentTarget == null || !this.currentTarget.isAlive()) {
            return false;
        }

        // 攻撃範囲外に出た
        if (this.clione.distanceToSqr(this.currentTarget) > this.attackRange * this.attackRange) {
            return false;
        }

        return true;
    }

    @Override
    public void start() {
        // 攻撃開始
        this.clione.performAttack(this.currentTarget);
    }

    @Override
    public void stop() {
        this.currentTarget = null;
    }

    @Override
    public void tick() {
        // クールダウン減少
        if (this.cooldownTicks >= 0) {
            this.cooldownTicks--;
        }
    }

    /**
     * 最も近い敵を見つける
     * 優先順位：
     * 1. オーナーを攻撃している敵
     * 2. オーナーに最も近い敵
     * 3. クリオネに最も近い敵
     */
    private LivingEntity findNearestEnemy(Player owner) {
        // 攻撃範囲内のすべての敵対的なエンティティを取得
        List<LivingEntity> enemies = this.clione.level().getEntitiesOfClass(
                LivingEntity.class,
                this.clione.getBoundingBox().inflate(this.attackRange),
                entity -> this.isValidTarget(entity, owner)
        );

        if (enemies.isEmpty()) {
            return null;
        }

        //// 優先度1: オーナーを攻撃している敵
        //for (LivingEntity enemy : enemies) {
        //    if (enemy.getLastHurtByMob() == owner || enemy.getTarget() == owner) {
        //        return enemy;
        //    }
        //}

        // 優先度2: オーナーに最も近い敵
        LivingEntity nearestToOwner = null;
        double minDistanceToOwner = Double.MAX_VALUE;

        for (LivingEntity enemy : enemies) {
            double distanceToOwner = owner.distanceToSqr(enemy);
            if (distanceToOwner < minDistanceToOwner) {
                minDistanceToOwner = distanceToOwner;
                nearestToOwner = enemy;
            }
        }

        return nearestToOwner;
    }

    /**
     * 有効な攻撃対象かチェック
     */
    private boolean isValidTarget(LivingEntity entity, Player owner) {
        // 自分自身は対象外
        if (entity == this.clione) {
            return false;
        }

        // オーナーは対象外
        if (entity == owner) {
            return false;
        }

        // 他の召喚クリオネは対象外
        if (entity instanceof SummonedClioneEntity) {
            return false;
        }

        // 死んでいる、無敵状態は対象外
        if (!entity.isAlive() || entity.isInvulnerable()) {
            return false;
        }

        // オーナーと同じチームは対象外
        if (entity.isAlliedTo(owner)) {
            return false;
        }

        // 視線が通っているかチェック
        if (!this.clione.hasLineOfSight(entity)) {
            return false;
        }

        return true;
    }
}