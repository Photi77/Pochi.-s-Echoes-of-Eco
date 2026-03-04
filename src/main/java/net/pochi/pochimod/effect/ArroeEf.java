package net.pochi.pochimod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;

import java.util.List;

public class ArroeEf extends MobEffect {

    int cooltime = 0;

    protected ArroeEf(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        Level level = pLivingEntity.level();

        if (!level.isClientSide()) {

            List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, pLivingEntity.getBoundingBox().inflate(30,30,30));
            if (!list.isEmpty()) {
                for (LivingEntity entity : list) {
                    double d0 = pLivingEntity.distanceToSqr(entity);
                    if(entity instanceof Monster) {
                        if (d0 < 100.0D) {
                            if(cooltime++ >=20){
                                Arrow arrow =new Arrow(pLivingEntity.level(),entity.getX(),entity.getY(),entity.getZ(), new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.ARROW), net.minecraft.world.item.ItemStack.EMPTY);
                                arrow.setPos(pLivingEntity.getEyePosition());
                                arrow.setDeltaMovement((entity.getX() - pLivingEntity.getX()) * 0.3,(entity.getY() - pLivingEntity.getY()) * 0.3,(entity.getZ() - pLivingEntity.getZ()) * 0.3);
                                arrow.setOwner(pLivingEntity);
                                pLivingEntity.level().addFreshEntity(arrow);
                                cooltime = 0;
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
