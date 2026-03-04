package net.pochi.pochimod.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.pochi.pochimod.entity.custom.Tapir;

import java.util.List;

public class Dreamer extends MobEffect {

    static BlockPos pos;

    static ResourceKey<Level> dimensionKey;
    protected Dreamer(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        Level level = pLivingEntity.level();
        Player player = (Player) pLivingEntity;
        if (!level.isClientSide()) {
            List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(10,10,10));
            if (!list.isEmpty()) {
                for (LivingEntity entity : list) {
                    double d0 = player.distanceToSqr(entity.getX(), entity.getY(), entity.getZ());
                    if(entity instanceof Tapir) {
                        if (d0 < 5.0D) {
                            //pos = BlockPos.containing(entity.getX(), entity.getY(), entity.getZ());
                            //dimensionKey = player.level().dimension();
                        }
                    }
                }
            }
        }
        return super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    public static BlockPos getPos() {
        return pos;
    }

    public static void setPos(BlockPos pos) {
        Dreamer.pos = pos;
    }

    public static ResourceKey<Level> getDimensionKey(){
        return dimensionKey;
    }

    public static void setDimensionKey(ResourceKey<Level> dimensionKey) {
        Dreamer.dimensionKey = dimensionKey;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }
}
