package net.pochi.pochimod.item.custom.weapon;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MantisGrab extends Item {
    public MantisGrab(Properties p_41383_) {
        super(p_41383_);
    }

    public void hurtEnemy(ItemStack p_43278_, LivingEntity p_43279_, LivingEntity p_43280_) {
        p_43278_.hurtAndBreak(1, p_43280_, EquipmentSlot.MAINHAND);
        if(p_43280_.isInWater()) {
            p_43279_.setDeltaMovement((p_43279_.getX() - p_43280_.getX()) * 3, (p_43279_.getY() - p_43280_.getY()) * 3, (p_43279_.getZ() - p_43280_.getZ()) * 3);
        }
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, net.minecraft.server.level.ServerLevel p_41405_, Entity p_41406_, net.minecraft.world.entity.EquipmentSlot p_41407_) {
        if(p_41406_ instanceof Player player && p_41406_.isInWater()){
            if(player.getMainHandItem().is(this)){
                player.addEffect(new MobEffectInstance(MobEffects.STRENGTH,200,3));
            }

        }
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_);
    }
}

