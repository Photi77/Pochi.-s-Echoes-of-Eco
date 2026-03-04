package net.pochi.pochimod.entity.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.pochi.pochimod.effect.ModEffects;

public class FruitFly extends Bee {

    public FruitFly(EntityType<? extends Bee> p_27717_, Level p_27718_) {
        super(p_27717_, p_27718_);
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 5, false, false, (p_28879_) -> {
            return p_28879_.hasEffect(ModEffects.DROWNED);
        }));
        super.registerGoals();
    }

    public boolean doHurtTarget(Entity p_27722_) {
        boolean flag = p_27722_.hurt(this.damageSources().sting(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            if (p_27722_ instanceof LivingEntity) {
                ((LivingEntity)p_27722_).setStingerCount(((LivingEntity)p_27722_).getStingerCount() + 1);
                int i = 0;
                if (this.level().getDifficulty() == Difficulty.NORMAL) {
                    i = 10;
                } else if (this.level().getDifficulty() == Difficulty.HARD) {
                    i = 18;
                }

                if (i > 0) {
                    ((LivingEntity)p_27722_).addEffect(new MobEffectInstance(ModEffects.WATER_FALL, i * 20, 0), this);
                }
            }

            this.playSound(SoundEvents.BEE_STING, 1.0F, 1.0F);
        }

        return flag;
    }
}
