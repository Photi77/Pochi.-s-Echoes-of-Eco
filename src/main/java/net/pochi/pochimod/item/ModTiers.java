package net.pochi.pochimod.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModTiers {

    public static final SimpleTier FLUORITE = new SimpleTier(
            BlockTags.NEEDS_IRON_TOOL, 64, 1.5f, 1f, 50,
            () -> Ingredient.of(ModItems.ROW_FLUORITE.get()));

    public static final SimpleTier STAINLESS = new SimpleTier(
            BlockTags.NEEDS_IRON_TOOL, 1000, 4.5f, 2f, 12,
            () -> Ingredient.of(ModItems.STAINLESS.get()));
}
