package net.pochi.pochimod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Random;

public class Inv extends MobEffect {

    protected Inv(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        Random random = new Random();
        Level level = pLivingEntity.level();
        if (!pLivingEntity.level().isClientSide()) {
            List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, pLivingEntity.getBoundingBox().inflate(10,10,10));
            if (!list.isEmpty()) {
                for (LivingEntity entity : list) {
                    double d0 = pLivingEntity.distanceToSqr(entity.getX(), entity.getY(), entity.getZ());
                    if (d0 < 30.0D) {
                        if(entity instanceof Monster monster){
                            if(monster.getTarget() == pLivingEntity){
                                monster.setAggressive(false);
                                monster.setTarget(null);
                            }

                            if(monster instanceof Creeper creeper){
                                creeper.setSwellDir(-1);
                            }
                        }
                    }
                }
            }
        }
        return super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }
}