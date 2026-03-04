package net.pochi.pochimod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Crime extends MobEffect {

    boolean crimable  = false;

    protected Crime(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        Level level = pLivingEntity.level();
        Player player = (Player) pLivingEntity;
        player.getPersistentData().putBoolean("crime", player.horizontalCollision);
        if (level.isClientSide()) {
            if(player.getPersistentData().getBoolean("crime")) {
                player.hurtMarked = true;
                player.setDeltaMovement(player.getDeltaMovement().x, 0.5, player.getDeltaMovement().z);
            }
        }

        return super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }
}
