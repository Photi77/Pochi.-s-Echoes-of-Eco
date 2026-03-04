package net.pochi.pochimod.item.custom.spell;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.pochi.pochimod.effect.ModEffects;

public class ShieldCast extends Item {

    public ShieldCast(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        ItemStack itemStack = p_41433_.getItemInHand(p_41434_);
        if(!p_41432_.isClientSide){
           p_41433_.addEffect(new MobEffectInstance(ModEffects.SHIELD,200,1,true,false,false));
        }
        p_41433_.getCooldowns().addCooldown(this,1000);
        return InteractionResultHolder.fail(itemStack);
    }
}
