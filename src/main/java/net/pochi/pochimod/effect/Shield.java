package net.pochi.pochimod.effect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public class Shield extends MobEffect {

    protected Shield(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        Level level = pLivingEntity.level();
        Player player = (Player) pLivingEntity;
        if (!level.isClientSide()) {



            List<Entity> list = level.getEntitiesOfClass(Entity.class, pLivingEntity.getBoundingBox().inflate(10,10,10));
            if (!list.isEmpty()) {
                for (Entity entity : list) {
                    double d0 = pLivingEntity.distanceToSqr(entity.getX(), entity.getY(), entity.getZ());
                    if(!(entity == player)) {
                        if (d0 < 10.0D) {
                            if(level instanceof ServerLevel serverLevel) {
                                for (int t = 0; t < 360; t++) {
                                    serverLevel.sendParticles(ParticleTypes.GLOW_SQUID_INK,
                                            entity.getX() , entity.getY() , entity.getZ() ,3,
                                            0, 0, 0,0);
                                }
                            }
                            entity.setDeltaMovement((entity.getX() - pLivingEntity.getX()) * 0.1,(entity.getY() - pLivingEntity.getY()) * 0.1,(entity.getZ() - pLivingEntity.getZ()) * 0.1);
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
