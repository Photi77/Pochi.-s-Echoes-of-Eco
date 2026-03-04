package net.pochi.pochimod.effect;

import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class MoonWalk extends MobEffect {

    protected MoonWalk(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        Level level = pLivingEntity.level();
        Player player = (Player) pLivingEntity;

        if(!level.isClientSide()) {
            if (Minecraft.getInstance().options.keyJump.consumeClick() && !player.onGround()) {
                player.getPersistentData().putBoolean("jump", true);
            } else {
                player.getPersistentData().putBoolean("jump", false);
            }
        }

        if (player.getPersistentData().getBoolean("jump")) {
            if(!level.isClientSide()) {
                player.hurtMarked = true;
                player.setDeltaMovement( player.getDeltaMovement().x + (player.getDeltaMovement().x + player.getLookAngle().x * 0.5) * 2, 0.8 ,
                        player.getDeltaMovement().z + (player.getDeltaMovement().z + player.getLookAngle().z * 0.5) * 2);
            }
        }

        return super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }
}
