package net.pochi.pochimod.entity.ai;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.pochi.pochimod.entity.custom.Mongoose;

public class MonGooseAttackGoal extends MeleeAttackGoal {
    private final Mongoose entity;
    public MonGooseAttackGoal(PathfinderMob p_25552_, double p_25553_, boolean p_25554_) {
        super(p_25552_, p_25553_, p_25554_);
        entity = ((Mongoose) p_25552_);
    }

    public void start() {
        super.start();
        entity.setAttacking(true);
    }

    public void stop() {
        super.stop();
        entity.setAttacking(false);
    }

}
