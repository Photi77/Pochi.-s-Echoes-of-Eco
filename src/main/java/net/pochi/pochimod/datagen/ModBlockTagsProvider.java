package net.pochi.pochimod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.block.ModBlocks;
import net.pochi.pochimod.tags.ModBlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, PochiMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Custom pochimod block tags
        tag(ModBlockTags.STONE_ONLY)
                .add(Blocks.STONE);

        tag(ModBlockTags.VOLCANO_BARRIER)
                .add(Blocks.GRAVEL, Blocks.BLACKSTONE, Blocks.BASALT, Blocks.SMOOTH_BASALT)
                .addTag(BlockTags.BASE_STONE_OVERWORLD);

        tag(ModBlockTags.VOLCANO_BLOCKS)
                .addTag(BlockTags.BASE_STONE_OVERWORLD)
                .add(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.BASALT,
                        Blocks.BLACKSTONE, Blocks.GRAVEL, Blocks.SMOOTH_BASALT, Blocks.WATER);

        // minecraft:mineable/pickaxe
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(
                        ModBlocks.BFURNACE_BLOCK.get(),
                        ModBlocks.CHROMITE_ORE.get(), ModBlocks.DEEPSLATE_CHROMITE_ORE.get(), ModBlocks.NETHERRACK_CHROMITE_ORE.get(),
                        ModBlocks.FLUORITE_ORE.get(), ModBlocks.DEEPSLATE_FLUORITE_ORE.get(), ModBlocks.NETHERRACK_FLUORITE_ORE.get(),
                        ModBlocks.ALUNITE_ORE.get(), ModBlocks.DEEPSLATE_ALUNITE_ORE.get(), ModBlocks.NETHERRACK_ALUNITE_ORE.get(),
                        ModBlocks.BAUXITE_ORE.get(), ModBlocks.DEEPSLATE_BAUXITE_ORE.get(), ModBlocks.NETHERRACK_BAUXITE_ORE.get(),
                        ModBlocks.MAGUNESIUM_ORE.get(), ModBlocks.DEEPSLATE_MAGUNESIUM_ORE.get(), ModBlocks.NETHERRACK_MAGUNESIUM_ORE.get(),
                        ModBlocks.VANADIUM_ORE.get(), ModBlocks.DEEPSLATE_VANADIUM_ORE.get(), ModBlocks.NETHERRACK_VANADIUM_ORE.get(),
                        ModBlocks.CHROMITE_BLOCK.get(), ModBlocks.STAINLESS_BLOCK.get(), ModBlocks.FLUORITE_BLOCK.get(),
                        ModBlocks.ALUNITE_BLOCK.get(),
                        ModBlocks.SANITARY_BLOCK.get(),
                        ModBlocks.TITANIUM_ORE.get(), ModBlocks.DEEPSLATE_TITANIUM_ORE.get(), ModBlocks.NETHERRACK_TITANIUM_ORE.get(),
                        ModBlocks.PAMMUKALE_BLOCK.get(),
                        ModBlocks.CINNABAR_BLOCK.get(), ModBlocks.BLACK_DIAMOND_BLOCK.get(), ModBlocks.YELLOW_DIAMOND_BLOCK.get(),
                        ModBlocks.GOSHENITE_BLOCK.get(), ModBlocks.RUTILE_BLOCK.get(), ModBlocks.JADE_BLOCK.get(),
                        ModBlocks.EUCLASE_BLOCK.get(), ModBlocks.ALEXANDRITE_BLOCK.get(), ModBlocks.PHOSPHOPHYLITE_BLOCK.get(),
                        ModBlocks.MORGANITE_BLOCK.get(),
                        ModBlocks.BUDDING_CINNABAR.get(), ModBlocks.BUDDING_BLACK_DIAMOND.get(), ModBlocks.BUDDING_YELLOW_DIAMOND.get(),
                        ModBlocks.BUDDING_MORGANITE.get(), ModBlocks.BUDDING_GOSHENITE.get(), ModBlocks.BUDDING_RUTILE.get(),
                        ModBlocks.BUDDING_JADE.get(), ModBlocks.BUDDING_EUCLASE.get(), ModBlocks.BUDDING_ALEXANDRITE.get(),
                        ModBlocks.BUDDING_PHOSPHOPHYLITE.get(),
                        ModBlocks.CINNABAR_CLUSTER.get(), ModBlocks.BLACK_DIAMOND_CLUSTER.get(), ModBlocks.YELLOW_DIAMOND_CLUSTER.get(),
                        ModBlocks.MORGANITE_CLUSTER.get(), ModBlocks.GOSHENITE_CLUSTER.get(), ModBlocks.RUTILE_CLUSTER.get(),
                        ModBlocks.JADE_CLUSTER.get(), ModBlocks.EUCLASE_CLUSTER.get(), ModBlocks.ALEXANDRITE_CLUSTER.get(),
                        ModBlocks.PHOSPHOPHYLITE_CLUSTER.get(),
                        ModBlocks.LARGE_CINNABAR_BUD.get(), ModBlocks.LARGE_BLACK_DIAMOND_BUD.get(), ModBlocks.LARGE_YELLOW_DIAMOND_BUD.get(),
                        ModBlocks.LARGE_MORGANITE_BUD.get(), ModBlocks.LARGE_GOSHENITE_BUD.get(), ModBlocks.LARGE_RUTILE_BUD.get(),
                        ModBlocks.LARGE_JADE_BUD.get(), ModBlocks.LARGE_EUCLASE_BUD.get(), ModBlocks.LARGE_ALEXANDRITE_BUD.get(),
                        ModBlocks.LARGE_PHOSPHOPHYLITE_BUD.get(),
                        ModBlocks.MEDIUM_CINNABAR_BUD.get(), ModBlocks.MEDIUM_BLACK_DIAMOND_BUD.get(), ModBlocks.MEDIUM_YELLOW_DIAMOND_BUD.get(),
                        ModBlocks.MEDIUM_MORGANITE_BUD.get(), ModBlocks.MEDIUM_GOSHENITE_BUD.get(), ModBlocks.MEDIUM_RUTILE_BUD.get(),
                        ModBlocks.MEDIUM_JADE_BUD.get(), ModBlocks.MEDIUM_EUCLASE_BUD.get(), ModBlocks.MEDIUM_ALEXANDRITE_BUD.get(),
                        ModBlocks.MEDIUM_PHOSPHOPHYLITE_BUD.get(),
                        ModBlocks.SMALL_CINNABAR_BUD.get(), ModBlocks.SMALL_BLACK_DIAMOND_BUD.get(), ModBlocks.SMALL_YELLOW_DIAMOND_BUD.get(),
                        ModBlocks.SMALL_MORGANITE_BUD.get(), ModBlocks.SMALL_GOSHENITE_BUD.get(), ModBlocks.SMALL_RUTILE_BUD.get(),
                        ModBlocks.SMALL_JADE_BUD.get(), ModBlocks.SMALL_EUCLASE_BUD.get(), ModBlocks.SMALL_ALEXANDRITE_BUD.get(),
                        ModBlocks.SMALL_PHOSPHOPHYLITE_BUD.get()
                );

        // minecraft:needs_stone_tool
        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(
                        ModBlocks.BFURNACE_BLOCK.get(),
                        ModBlocks.CHROMITE_ORE.get(), ModBlocks.DEEPSLATE_CHROMITE_ORE.get(), ModBlocks.NETHERRACK_CHROMITE_ORE.get(),
                        ModBlocks.FLUORITE_ORE.get(), ModBlocks.DEEPSLATE_FLUORITE_ORE.get(), ModBlocks.NETHERRACK_FLUORITE_ORE.get(),
                        ModBlocks.ALUNITE_ORE.get(), ModBlocks.DEEPSLATE_ALUNITE_ORE.get(), ModBlocks.NETHERRACK_ALUNITE_ORE.get(),
                        ModBlocks.BAUXITE_ORE.get(), ModBlocks.DEEPSLATE_BAUXITE_ORE.get(), ModBlocks.NETHERRACK_BAUXITE_ORE.get(),
                        ModBlocks.MAGUNESIUM_ORE.get(), ModBlocks.DEEPSLATE_MAGUNESIUM_ORE.get(), ModBlocks.NETHERRACK_MAGUNESIUM_ORE.get(),
                        ModBlocks.VANADIUM_ORE.get(), ModBlocks.DEEPSLATE_VANADIUM_ORE.get(), ModBlocks.NETHERRACK_VANADIUM_ORE.get(),
                        ModBlocks.CHROMITE_BLOCK.get(), ModBlocks.STAINLESS_BLOCK.get(), ModBlocks.FLUORITE_BLOCK.get(),
                        ModBlocks.ALUNITE_BLOCK.get(),
                        ModBlocks.SANITARY_BLOCK.get(),
                        ModBlocks.PAMMUKALE_BLOCK.get()
                );

        // minecraft:needs_iron_tool
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(
                        ModBlocks.TITANIUM_ORE.get(), ModBlocks.DEEPSLATE_TITANIUM_ORE.get(), ModBlocks.NETHERRACK_TITANIUM_ORE.get()
                );

        // minecraft:needs_diamond_tool
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.STAINLESS_BLOCK.get());

        // minecraft:logs
        tag(BlockTags.LOGS)
                .add(
                        ModBlocks.CABERNET_SAUVIGNON_LOG.get(), ModBlocks.CABERNET_SAUVIGNON_WOOD.get(),
                        ModBlocks.STRIPPED_CABERNET_SAUVIGNON_LOG.get(), ModBlocks.STRIPPED_CABERNET_SAUVIGNON_WOOD.get(),
                        ModBlocks.MAPLE_LOG.get(), ModBlocks.MAPLE_WOOD.get(),
                        ModBlocks.STRIPPED_MAPLE_LOG.get(), ModBlocks.STRIPPED_MAPLE_WOOD.get(),
                        ModBlocks.CINNAMON_LOG.get(), ModBlocks.CINNAMON_WOOD.get(),
                        ModBlocks.STRIPPED_CINNAMON_LOG.get(), ModBlocks.STRIPPED_CINNAMON_WOOD.get(),
                        ModBlocks.COLA_LOG.get(), ModBlocks.COLA_WOOD.get(),
                        ModBlocks.STRIPPED_COLA_LOG.get(), ModBlocks.STRIPPED_COLA_WOOD.get(),
                        ModBlocks.LEMON_LOG.get(), ModBlocks.LEMON_WOOD.get(),
                        ModBlocks.STRIPPED_LEMON_LOG.get(), ModBlocks.STRIPPED_LEMON_WOOD.get(),
                        ModBlocks.PLUM_LOG.get(), ModBlocks.PLUM_WOOD.get(),
                        ModBlocks.STRIPPED_PLUM_LOG.get(), ModBlocks.STRIPPED_PLUM_WOOD.get(),
                        ModBlocks.CHERRY_LOG.get(), ModBlocks.CHERRY_WOOD.get(),
                        ModBlocks.STRIPPED_CHERRY_LOG.get(), ModBlocks.STRIPPED_CHERRY_WOOD.get(),
                        ModBlocks.BANANA_LOG.get(), ModBlocks.BANANA_WOOD.get(),
                        ModBlocks.STRIPPED_BANANA_LOG.get(), ModBlocks.STRIPPED_BANANA_WOOD.get(),
                        ModBlocks.COCONUT_LOG.get(), ModBlocks.COCONUT_WOOD.get(),
                        ModBlocks.STRIPPED_COCONUT_LOG.get(), ModBlocks.STRIPPED_COCONUT_WOOD.get(),
                        ModBlocks.PEACH_LOG.get(), ModBlocks.PEACH_WOOD.get(),
                        ModBlocks.STRIPPED_PEACH_LOG.get(), ModBlocks.STRIPPED_PEACH_WOOD.get(),
                        ModBlocks.ALMOND_LOG.get(), ModBlocks.ALMOND_WOOD.get(),
                        ModBlocks.STRIPPED_ALMOND_LOG.get(), ModBlocks.STRIPPED_ALMOND_WOOD.get(),
                        ModBlocks.DURIAN_LOG.get(), ModBlocks.DURIAN_WOOD.get(),
                        ModBlocks.STRIPPED_DURIAN_LOG.get(), ModBlocks.STRIPPED_DURIAN_WOOD.get(),
                        ModBlocks.COFFEE_LOG.get(), ModBlocks.COFFEE_WOOD.get(),
                        ModBlocks.STRIPPED_COFFEE_LOG.get(), ModBlocks.STRIPPED_COFFEE_WOOD.get(),
                        ModBlocks.KIWI_LOG.get(), ModBlocks.KIWI_WOOD.get(),
                        ModBlocks.STRIPPED_KIWI_LOG.get(), ModBlocks.STRIPPED_KIWI_WOOD.get()
                );

        // minecraft:logs_that_burn (same as logs for our mod)
        tag(BlockTags.LOGS_THAT_BURN)
                .add(
                        ModBlocks.CABERNET_SAUVIGNON_LOG.get(), ModBlocks.CABERNET_SAUVIGNON_WOOD.get(),
                        ModBlocks.STRIPPED_CABERNET_SAUVIGNON_LOG.get(), ModBlocks.STRIPPED_CABERNET_SAUVIGNON_WOOD.get(),
                        ModBlocks.MAPLE_LOG.get(), ModBlocks.MAPLE_WOOD.get(),
                        ModBlocks.STRIPPED_MAPLE_LOG.get(), ModBlocks.STRIPPED_MAPLE_WOOD.get(),
                        ModBlocks.CINNAMON_LOG.get(), ModBlocks.CINNAMON_WOOD.get(),
                        ModBlocks.STRIPPED_CINNAMON_LOG.get(), ModBlocks.STRIPPED_CINNAMON_WOOD.get(),
                        ModBlocks.COLA_LOG.get(), ModBlocks.COLA_WOOD.get(),
                        ModBlocks.STRIPPED_COLA_LOG.get(), ModBlocks.STRIPPED_COLA_WOOD.get(),
                        ModBlocks.LEMON_LOG.get(), ModBlocks.LEMON_WOOD.get(),
                        ModBlocks.STRIPPED_LEMON_LOG.get(), ModBlocks.STRIPPED_LEMON_WOOD.get(),
                        ModBlocks.PLUM_LOG.get(), ModBlocks.PLUM_WOOD.get(),
                        ModBlocks.STRIPPED_PLUM_LOG.get(), ModBlocks.STRIPPED_PLUM_WOOD.get(),
                        ModBlocks.CHERRY_LOG.get(), ModBlocks.CHERRY_WOOD.get(),
                        ModBlocks.STRIPPED_CHERRY_LOG.get(), ModBlocks.STRIPPED_CHERRY_WOOD.get(),
                        ModBlocks.BANANA_LOG.get(), ModBlocks.BANANA_WOOD.get(),
                        ModBlocks.STRIPPED_BANANA_LOG.get(), ModBlocks.STRIPPED_BANANA_WOOD.get(),
                        ModBlocks.COCONUT_LOG.get(), ModBlocks.COCONUT_WOOD.get(),
                        ModBlocks.STRIPPED_COCONUT_LOG.get(), ModBlocks.STRIPPED_COCONUT_WOOD.get(),
                        ModBlocks.PEACH_LOG.get(), ModBlocks.PEACH_WOOD.get(),
                        ModBlocks.STRIPPED_PEACH_LOG.get(), ModBlocks.STRIPPED_PEACH_WOOD.get(),
                        ModBlocks.ALMOND_LOG.get(), ModBlocks.ALMOND_WOOD.get(),
                        ModBlocks.STRIPPED_ALMOND_LOG.get(), ModBlocks.STRIPPED_ALMOND_WOOD.get(),
                        ModBlocks.DURIAN_LOG.get(), ModBlocks.DURIAN_WOOD.get(),
                        ModBlocks.STRIPPED_DURIAN_LOG.get(), ModBlocks.STRIPPED_DURIAN_WOOD.get(),
                        ModBlocks.COFFEE_LOG.get(), ModBlocks.COFFEE_WOOD.get(),
                        ModBlocks.STRIPPED_COFFEE_LOG.get(), ModBlocks.STRIPPED_COFFEE_WOOD.get(),
                        ModBlocks.KIWI_LOG.get(), ModBlocks.KIWI_WOOD.get(),
                        ModBlocks.STRIPPED_KIWI_LOG.get(), ModBlocks.STRIPPED_KIWI_WOOD.get()
                );

        // minecraft:planks
        tag(BlockTags.PLANKS)
                .add(
                        ModBlocks.CABERNET_SAUVIGNON_PLANKS.get(),
                        ModBlocks.MAPLE_PLANKS.get(),
                        ModBlocks.CINNAMON_PLANKS.get(),
                        ModBlocks.COLA_PLANKS.get(),
                        ModBlocks.LEMON_PLANKS.get(),
                        ModBlocks.PLUM_PLANKS.get(),
                        ModBlocks.CHERRY_PLANKS.get(),
                        ModBlocks.BANANA_PLANKS.get(),
                        ModBlocks.COCONUT_PLANKS.get(),
                        ModBlocks.PEACH_PLANKS.get(),
                        ModBlocks.ALMOND_PLANKS.get(),
                        ModBlocks.DURIAN_PLANKS.get(),
                        ModBlocks.COFFEE_PLANKS.get(),
                        ModBlocks.KIWI_PLANKS.get()
                );

        // minecraft:fences
        tag(BlockTags.FENCES)
                .add(
                        ModBlocks.CABERNET_SAUVIGNON_FENCE.get(),
                        ModBlocks.MAPLE_FENCE.get(),
                        ModBlocks.CINNAMON_FENCE.get(),
                        ModBlocks.COLA_FENCE.get(),
                        ModBlocks.LEMON_FENCE.get(),
                        ModBlocks.PLUM_FENCE.get(),
                        ModBlocks.CHERRY_FENCE.get(),
                        ModBlocks.BANANA_FENCE.get(),
                        ModBlocks.COCONUT_FENCE.get(),
                        ModBlocks.PEACH_FENCE.get(),
                        ModBlocks.ALMOND_FENCE.get(),
                        ModBlocks.DURIAN_FENCE.get(),
                        ModBlocks.COFFEE_FENCE.get(),
                        ModBlocks.KIWI_FENCE.get()
                );
    }
}
