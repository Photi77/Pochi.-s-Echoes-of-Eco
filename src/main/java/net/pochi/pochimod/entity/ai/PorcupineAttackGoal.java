package net.pochi.pochimod.entity.ai;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.pochi.pochimod.entity.custom.Porcupine;

public class PorcupineAttackGoal extends MeleeAttackGoal {
    private final Porcupine entity;
    public PorcupineAttackGoal(PathfinderMob p_25552_, double p_25553_, boolean p_25554_) {
        super(p_25552_, p_25553_, p_25554_);
        entity = ((Porcupine) p_25552_);
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
