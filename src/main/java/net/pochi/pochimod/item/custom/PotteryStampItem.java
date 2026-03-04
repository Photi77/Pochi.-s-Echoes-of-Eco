package net.pochi.pochimod.item.custom;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.pochi.pochimod.item.ModItems;
import net.pochi.pochimod.pottery.PotteryPattern;

public class PotteryStampItem extends Item {

    private final PotteryPattern pattern;

    public PotteryStampItem(Properties properties, PotteryPattern pattern) {
        super(properties);
        this.pattern = pattern;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack heldItem = player.getItemInHand(hand);
        ItemStack offhandItem = player.getOffhandItem();

        if (offhandItem.is(ModItems.UNFIRED_POTTERY.get())) {
            if (!level.isClientSide) {
                CompoundTag tag = offhandItem.has(DataComponents.CUSTOM_DATA)
                        ? offhandItem.get(DataComponents.CUSTOM_DATA).copyTag()
                        : new CompoundTag();
                tag.putString("Pattern", pattern.name());
                offhandItem.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
            }
            return InteractionResultHolder.success(heldItem);
        }

        return InteractionResultHolder.pass(heldItem);
    }

    public PotteryPattern getPattern() {
        return pattern;
    }
}
