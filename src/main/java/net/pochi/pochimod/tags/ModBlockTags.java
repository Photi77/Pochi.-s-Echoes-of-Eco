package net.pochi.pochimod.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.pochi.pochimod.PochiMod;

public class ModBlockTags {
    public static final TagKey<Block> STONE_ONLY = tag("stone_only");
    public static final TagKey<Block> VOLCANO_BARRIER = tag("volcano_barrier");
    public static final TagKey<Block> VOLCANO_BLOCKS = tag("volcano_blocks");

    private static TagKey<Block> tag(String name) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, name));
    }
}
