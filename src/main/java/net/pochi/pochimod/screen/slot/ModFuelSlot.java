package net.pochi.pochimod.screen.slot;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class ModFuelSlot extends SlotItemHandler {
    private final Level level;

    public ModFuelSlot(IItemHandler itemHandler, int index, int x, int y, Level level) {
        super(itemHandler, index, x, y);
        this.level = level;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return level.fuelValues().isFuel(stack) || ModFuelSlot.isBucket(stack);
    }

    @Override
    public int getMaxStackSize(ItemStack pStack) {
        return ModFuelSlot.isBucket(pStack) ? 1 : super.getMaxStackSize(pStack);
    }

    public static boolean isBucket(ItemStack stack) {
        return stack.is(Items.BUCKET);
    }
}
