package net.pochi.pochimod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class Honey extends MobEffect {

    protected Honey(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        Level level = pLivingEntity.level();
        if (!level.isClientSide()) {
            pLivingEntity.spawnAtLocation(Items.HONEYCOMB.getDefaultInstance(),1.5F);
            pLivingEntity.spawnAtLocation(Items.HONEY_BOTTLE.getDefaultInstance(),1.5F);
        }
        return super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }
}
