package net.pochi.pochimod.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class IndicateHoney extends MobEffect {

    BlockPos pos = null;

    protected IndicateHoney(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        Level level = pLivingEntity.level();
        if (!level.isClientSide()) {
            pos = null;
            if(pos == null) {
                for (int x = -50; x <= 50; x++) {
                    for (int y = -50; y <= 50; y++) {
                        for (int z = -50; z <= 50; z++) {
                            BlockPos ind = BlockPos.containing(pLivingEntity.getX()+x,pLivingEntity.getY()+y,pLivingEntity.getZ()+z);
                            if (level.getBlockState(ind).is(Blocks.BEE_NEST)) {
                                pos = BlockPos.containing(pLivingEntity.getX() + x, pLivingEntity.getY() + y, pLivingEntity.getZ() + z);
                            }
                        }
                    }
                }
            }
            if(pos != null) {
                if (level instanceof ServerLevel serverLevel) {
                    for (double l = 0; l < 100; l++) {
                        serverLevel.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, Items.HONEYCOMB.getDefaultInstance()),
                                pLivingEntity.getX() + (pos.getX() - pLivingEntity.getX()) * 1 / l,
                                pLivingEntity.getY() +0.5 + (pos.getY() - pLivingEntity.getY()) * 1 / l,
                                pLivingEntity.getZ() + (pos.getZ() - pLivingEntity.getZ()) * 1 / l, 1,
                                0, 0, 0, 0);
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
