package net.pochi.pochimod.item.custom.spell;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.pochi.pochimod.entity.ModEntityTypes;
import net.pochi.pochimod.entity.custom.DirtGolem;

import java.util.Random;

public class GolemSummon extends Item {

    Random random = new Random();
    public GolemSummon(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        ItemStack itemStack = p_41433_.getItemInHand(p_41434_);
        if (!p_41432_.isClientSide) {
            DirtGolem dirtGolem = ModEntityTypes.DIRT_GOLEM.get().create(p_41432_);
            dirtGolem.moveTo(p_41433_.getX() + random.nextInt(10) - 5, p_41433_.getY(), p_41433_.getZ() + random.nextInt(10) - 5);
            dirtGolem.setOwnerUUID(p_41433_.getUUID());
            dirtGolem.setTame(true, true);
            dirtGolem.setOrderedToSit(false);
            p_41432_.addFreshEntity(dirtGolem);
            p_41433_.getCooldowns().addCooldown(this,1000);
        }

        return InteractionResultHolder.fail(itemStack);
    }
}
