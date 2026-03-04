package net.pochi.pochimod.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.pochi.pochimod.block.ModBlocks;

public class BubbleWalk extends MobEffect {

    protected BubbleWalk(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        Level level = pLivingEntity.level();
        Player player = (Player) pLivingEntity;

        if(!level.isClientSide()) {
            if (player.isShiftKeyDown()) {
                player.getPersistentData().putBoolean("jump", true);
            } else {
                player.getPersistentData().putBoolean("jump", false);
            }
        }

        if (player.getPersistentData().getBoolean("jump")) {
            for(int i = 1; i<=4 ; i++) {
                BlockPos pos = BlockPos.containing(player.getOnPos().getX() + player.getLookAngle().x * i,
                         player.getOnPos().getY() + player.getLookAngle().y * i * 2.5, player.getOnPos().getZ() + player.getLookAngle().z * i);
                if (player.level().getBlockState(pos).is(Blocks.AIR)) {
                    player.level().setBlockAndUpdate(pos,ModBlocks.BUBBLE.get().defaultBlockState());
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
