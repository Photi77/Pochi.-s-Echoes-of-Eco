package net.pochi.pochimod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;

import java.util.function.Consumer;

public class UnfiredPotteryItem extends Item {

    public UnfiredPotteryItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context,
                                net.minecraft.world.item.component.TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            CompoundTag tag = customData.copyTag();
            tooltip.accept(Component.translatable("item.yourmod.pottery.shape",
                    tag.getStringOr("Shape", "")).withStyle(ChatFormatting.GRAY));
            tooltip.accept(Component.translatable("item.yourmod.pottery.height",
                    tag.getIntOr("Height", 0)).withStyle(ChatFormatting.GRAY));
            tooltip.accept(Component.translatable("item.yourmod.pottery.diameter",
                    tag.getIntOr("Diameter", 0)).withStyle(ChatFormatting.GRAY));
            tooltip.accept(Component.translatable("item.yourmod.pottery.thickness",
                    tag.getIntOr("WallThickness", 0)).withStyle(ChatFormatting.GRAY));
            tooltip.accept(Component.translatable("item.yourmod.pottery.mouth",
                    tag.getIntOr("MouthWidth", 0)).withStyle(ChatFormatting.GRAY));
        }
        super.appendHoverText(stack, context, display, tooltip, flag);
    }

    @Override
    public Component getName(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            CompoundTag tag = customData.copyTag();
            if (tag.getString("Shape").isPresent()) {
                String shape = tag.getStringOr("Shape", "").toLowerCase();
                return Component.translatable("item.yourmod.unfired_pottery." + shape);
            }
        }
        return super.getName(stack);
    }
}
