package net.pochi.pochimod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;

import java.util.List;

public class UnfiredPotteryItem extends Item {

    public UnfiredPotteryItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context,
                                List<Component> tooltip, TooltipFlag flag) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            CompoundTag tag = customData.copyTag();
            tooltip.add(Component.translatable("item.yourmod.pottery.shape",
                    tag.getString("Shape")).withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.translatable("item.yourmod.pottery.height",
                    tag.getInt("Height")).withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.translatable("item.yourmod.pottery.diameter",
                    tag.getInt("Diameter")).withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.translatable("item.yourmod.pottery.thickness",
                    tag.getInt("WallThickness")).withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.translatable("item.yourmod.pottery.mouth",
                    tag.getInt("MouthWidth")).withStyle(ChatFormatting.GRAY));
        }
        super.appendHoverText(stack, context, tooltip, flag);
    }

    @Override
    public Component getName(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            CompoundTag tag = customData.copyTag();
            if (tag.contains("Shape")) {
                String shape = tag.getString("Shape").toLowerCase();
                return Component.translatable("item.yourmod.unfired_pottery." + shape);
            }
        }
        return super.getName(stack);
    }
}
