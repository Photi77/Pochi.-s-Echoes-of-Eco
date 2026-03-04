package net.pochi.pochimod.item.custom.weapon;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.pochi.pochimod.entity.projectile.HookArrow;

public class HookShot extends BowItem {

    public HookShot(Properties p_41383_) {
        super(new Properties());
    }

    public InteractionResultHolder<ItemStack> use(Level p_40672_, Player p_40673_, InteractionHand p_40674_) {
        ItemStack itemstack1 = p_40673_.getItemInHand(p_40674_);
        ItemStack itemstack2 = Items.AIR.getDefaultInstance();
        p_40673_.getCooldowns().addCooldown(this, 5);
        if (!p_40672_.isClientSide) {
            HookArrow abstractarrow = new HookArrow(p_40672_, p_40673_,itemstack2);
            abstractarrow.shootFromRotation(p_40673_, p_40673_.getXRot(), p_40673_.getYRot(), 0.0F, 10.0F, 0.0F);
            p_40672_.addFreshEntity(abstractarrow);
            itemstack1.hurtAndBreak(1, p_40673_, p_40674_ == net.minecraft.world.InteractionHand.MAIN_HAND ? net.minecraft.world.entity.EquipmentSlot.MAINHAND : net.minecraft.world.entity.EquipmentSlot.OFFHAND);
        }

        p_40672_.playSound((Player)null, p_40673_.getX(), p_40673_.getY(), p_40673_.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (p_40672_.getRandom().nextFloat() * 0.4F + 1.2F) + 1 * 0.5F);
        p_40673_.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.fail(itemstack1);
    }
}
