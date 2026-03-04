package net.pochi.pochimod.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class SnowWalk extends MobEffect {

    protected SnowWalk(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        Level level = pLivingEntity.level();
        if (pLivingEntity instanceof Player player) {
            for (int x = -3;x <= 3;x++){
                for (int y = -10;y <= 0;y++){
                    for (int z = -3;z <= 3;z++){
                        BlockPos blockPos1 = BlockPos.containing(player.getX()+x,player.getY()+y,player.getZ()+z);
                        if(level.getBlockState(blockPos1).is(Blocks.POWDER_SNOW)){
                            level.setBlock(blockPos1,Blocks.SNOW_BLOCK.defaultBlockState(), 11);
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }
}
