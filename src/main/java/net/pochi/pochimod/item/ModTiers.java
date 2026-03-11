package net.pochi.pochimod.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ToolMaterial;

public class ModTiers {

    public static final ToolMaterial FLUORITE = new ToolMaterial(BlockTags.NEEDS_IRON_TOOL, 64, 1.5f, 1f, 50, ItemTags.IRON_TOOL_MATERIALS);

    public static final ToolMaterial STAINLESS = new ToolMaterial(BlockTags.NEEDS_IRON_TOOL, 1000, 4.5f, 2f, 12, ItemTags.IRON_TOOL_MATERIALS);
}
