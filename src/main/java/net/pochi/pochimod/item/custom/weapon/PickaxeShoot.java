package net.pochi.pochimod.item.custom.weapon;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.pochi.pochimod.entity.ModEntityTypes;
import net.pochi.pochimod.entity.projectile.PickaxeHead;

public class PickaxeShoot extends Item {

    static boolean use = false;

    public PickaxeShoot(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        ItemStack itemStack = p_41433_.getItemInHand(p_41434_);
        if (!p_41432_.isClientSide) {
            PickaxeHead dirtGolem = ModEntityTypes.PICKAXE_HEAD.get().create(p_41432_);
            dirtGolem.moveTo(p_41433_.getX(), p_41433_.getY(), p_41433_.getZ());
            p_41432_.addFreshEntity(dirtGolem);
            p_41433_.getCooldowns().addCooldown(this,600);
            use = true;
        }

        return InteractionResultHolder.fail(itemStack);
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
        Player player = (Player) p_41406_;
        if(!player.getCooldowns().isOnCooldown(this)){
            use = false;
        }
    }
    public static boolean isCast() {
        return use;
    }
}
