package net.pochi.pochimod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.block.custom.*;
import net.pochi.pochimod.block.custom.fruits.*;
import net.pochi.pochimod.block.custom.budding.*;
import net.pochi.pochimod.block.custom.bush.*;
import net.pochi.pochimod.block.custom.cauldrons.ModCauldronBlock;
import net.pochi.pochimod.block.custom.cauldrons.RiceMaltBlock;
import net.pochi.pochimod.block.custom.crops.*;
import net.pochi.pochimod.block.custom.modleaves.*;
import net.pochi.pochimod.fluid.ModFluids;
import net.pochi.pochimod.item.ModItems;
import net.pochi.pochimod.util.ModCauldronInteraction;
import net.pochi.pochimod.world.feature.tree.*;

import java.util.function.Supplier;

public class ModBlocks {


    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, PochiMod.MOD_ID);

    public static final DeferredHolder<Block, Block> SOLIDIFIED_LAVA =
            BLOCKS.register("solidified_lava", SolidifiedLavaBlock::new);

    //宝石の国
    public static final DeferredHolder<Block, Block> CINNABAR_BLOCK = registryBlock("cinnabar_block",
            () -> new AmethystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_50874_) -> {
                return 8;})
                    .requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> BUDDING_CINNABAR = registryBlock("budding_cinnabar", () ->new BuddingCinnabar(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f)
            .requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> CINNABAR_CLUSTER = registryBlock("cinnabar_cluster", () ->new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152632_) -> {
        return 5;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> LARGE_CINNABAR_BUD = registryBlock("large_cinnabar_bud", () ->new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152629_) -> {
        return 4;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> MEDIUM_CINNABAR_BUD = registryBlock("medium_cinnabar_bud", () ->new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152617_) -> {
        return 2;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> SMALL_CINNABAR_BUD = registryBlock("small_cinnabar_bud", () ->new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_187409_) -> {
        return 1;
    }).pushReaction(PushReaction.DESTROY)));

    public static final DeferredHolder<Block, Block> BLACK_DIAMOND_BLOCK = registryBlock("black_diamond_block",
            () -> new AmethystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_50874_) -> {
                        return 8;})
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> BUDDING_BLACK_DIAMOND = registryBlock("budding_black_diamond", () ->new BuddingBlackDiamond(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f)
            .requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> BLACK_DIAMOND_CLUSTER = registryBlock("black_diamond_cluster", () ->new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152632_) -> {
        return 5;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> LARGE_BLACK_DIAMOND_BUD = registryBlock("large_black_diamond_bud", () ->new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152629_) -> {
        return 4;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> MEDIUM_BLACK_DIAMOND_BUD = registryBlock("medium_black_diamond_bud", () ->new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152617_) -> {
        return 2;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> SMALL_BLACK_DIAMOND_BUD = registryBlock("small_black_diamond_bud", () ->new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_187409_) -> {
        return 1;
    }).pushReaction(PushReaction.DESTROY)));

    public static final DeferredHolder<Block, Block> YELLOW_DIAMOND_BLOCK = registryBlock("yellow_diamond_block",
            () -> new AmethystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_50874_) -> {
                        return 8;})
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> BUDDING_YELLOW_DIAMOND = registryBlock("budding_yellow_diamond", () ->new BuddingYellowDiamond(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f)
            .requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> YELLOW_DIAMOND_CLUSTER = registryBlock("yellow_diamond_cluster", () ->new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152632_) -> {
        return 5;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> LARGE_YELLOW_DIAMOND_BUD = registryBlock("large_yellow_diamond_bud", () ->new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152629_) -> {
        return 4;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> MEDIUM_YELLOW_DIAMOND_BUD = registryBlock("medium_yellow_diamond_bud", () ->new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152617_) -> {
        return 2;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> SMALL_YELLOW_DIAMOND_BUD = registryBlock("small_yellow_diamond_bud", () ->new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_187409_) -> {
        return 1;
    }).pushReaction(PushReaction.DESTROY)));

    public static final DeferredHolder<Block, Block> MORGANITE_BLOCK = registryBlock("morganite_block",
            () -> new AmethystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_50874_) -> {
                        return 8;})
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> BUDDING_MORGANITE = registryBlock("budding_morganite", () ->new BuddingMorganite(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f)
            .requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> MORGANITE_CLUSTER = registryBlock("morganite_cluster", () ->new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152632_) -> {
        return 5;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> LARGE_MORGANITE_BUD = registryBlock("large_morganite_bud", () ->new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152629_) -> {
        return 4;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> MEDIUM_MORGANITE_BUD = registryBlock("medium_morganite_bud", () ->new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152617_) -> {
        return 2;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> SMALL_MORGANITE_BUD = registryBlock("small_morganite_bud", () ->new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_187409_) -> {
        return 1;
    }).pushReaction(PushReaction.DESTROY)));

    public static final DeferredHolder<Block, Block> GOSHENITE_BLOCK = registryBlock("goshenite_block",
            () -> new AmethystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_50874_) -> {
                        return 8;})
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> BUDDING_GOSHENITE = registryBlock("budding_goshenite", () ->new BuddingGoshenite(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f)
            .requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> GOSHENITE_CLUSTER = registryBlock("goshenite_cluster", () ->new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152632_) -> {
        return 5;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> LARGE_GOSHENITE_BUD = registryBlock("large_goshenite_bud", () ->new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152629_) -> {
        return 4;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> MEDIUM_GOSHENITE_BUD = registryBlock("medium_goshenite_bud", () ->new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152617_) -> {
        return 2;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> SMALL_GOSHENITE_BUD = registryBlock("small_goshenite_bud", () ->new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_187409_) -> {
        return 1;
    }).pushReaction(PushReaction.DESTROY)));

    public static final DeferredHolder<Block, Block> RUTILE_BLOCK = registryBlock("rutile_block",
            () -> new AmethystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_50874_) -> {
                        return 8;})
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> BUDDING_RUTILE = registryBlock("budding_rutile",
            () ->new BuddingRutile(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f)
            .requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> RUTILE_CLUSTER = registryBlock("rutile_cluster", () ->new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152632_) -> {
        return 5;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> LARGE_RUTILE_BUD = registryBlock("large_rutile_bud", () ->new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152629_) -> {
        return 4;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> MEDIUM_RUTILE_BUD = registryBlock("medium_rutile_bud", () ->new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152617_) -> {
        return 2;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> SMALL_RUTILE_BUD = registryBlock("small_rutile_bud", () ->new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_187409_) -> {
        return 1;
    }).pushReaction(PushReaction.DESTROY)));

    public static final DeferredHolder<Block, Block> JADE_BLOCK = registryBlock("jade_block",
            () -> new AmethystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_50874_) -> {
                        return 8;})
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> BUDDING_JADE = registryBlock("budding_jade", () ->new BuddingJade(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f)
            .requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> JADE_CLUSTER = registryBlock("jade_cluster", () ->new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152632_) -> {
        return 5;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> LARGE_JADE_BUD = registryBlock("large_jade_bud", () ->new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152629_) -> {
        return 4;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> MEDIUM_JADE_BUD = registryBlock("medium_jade_bud", () ->new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152617_) -> {
        return 2;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> SMALL_JADE_BUD = registryBlock("small_jade_bud", () ->new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_187409_) -> {
        return 1;
    }).pushReaction(PushReaction.DESTROY)));

    public static final DeferredHolder<Block, Block> EUCLASE_BLOCK = registryBlock("euclase_block",
            () -> new AmethystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_50874_) -> {
                        return 8;})
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> BUDDING_EUCLASE = registryBlock("budding_euclase", () ->new BuddingEuclase(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f)
            .requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> EUCLASE_CLUSTER = registryBlock("euclase_cluster", () ->new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152632_) -> {
        return 5;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> LARGE_EUCLASE_BUD = registryBlock("large_euclase_bud", () ->new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152629_) -> {
        return 4;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> MEDIUM_EUCLASE_BUD = registryBlock("medium_euclase_bud", () ->new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152617_) -> {
        return 2;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> SMALL_EUCLASE_BUD = registryBlock("small_euclase_bud", () ->new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_187409_) -> {
        return 1;
    }).pushReaction(PushReaction.DESTROY)));

    public static final DeferredHolder<Block, Block> ALEXANDRITE_BLOCK = registryBlock("alexandrite_block",
            () -> new AmethystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_50874_) -> {
                        return 8;})
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> BUDDING_ALEXANDRITE = registryBlock("budding_alexandrite", () ->new BuddingAlexandrite(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f)
            .requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> ALEXANDRITE_CLUSTER = registryBlock("alexandrite_cluster", () ->new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152632_) -> {
        return 5;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> LARGE_ALEXANDRITE_BUD = registryBlock("large_alexandrite_bud", () ->new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152629_) -> {
        return 4;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> MEDIUM_ALEXANDRITE_BUD = registryBlock("medium_alexandrite_bud", () ->new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152617_) -> {
        return 2;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> SMALL_ALEXANDRITE_BUD = registryBlock("small_alexandrite_bud", () ->new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_187409_) -> {
        return 1;
    }).pushReaction(PushReaction.DESTROY)));

    public static final DeferredHolder<Block, Block> PHOSPHOPHYLITE_BLOCK = registryBlock("phosphophylite_block",
            () -> new AmethystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_50874_) -> {
                        return 8;})
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> BUDDING_PHOSPHOPHYLITE = registryBlock("budding_phosphophylite", () ->new BuddingPhosphophylite(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f)
            .requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> PHOSPHOPHYLITE_CLUSTER = registryBlock("phosphophylite_cluster", () ->new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152632_) -> {
        return 5;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> LARGE_PHOSPHOPHYLITE_BUD = registryBlock("large_phosphophylite_bud", () ->new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152629_) -> {
        return 4;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> MEDIUM_PHOSPHOPHYLITE_BUD = registryBlock("medium_phosphophylite_bud", () ->new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152617_) -> {
        return 2;
    }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> SMALL_PHOSPHOPHYLITE_BUD = registryBlock("small_phosphophylite_bud", () ->new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_187409_) -> {
        return 1;
    }).pushReaction(PushReaction.DESTROY)));

    //鉱石
    public static final DeferredHolder<Block, Block> SALT_BLOCK = registryBlock("salt_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).strength(3f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> RAINBOW_WOOD = registryBlock("rainbow_wood",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> CHROMITE_ORE = registryBlock("chromite_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> DEEPSLATE_CHROMITE_ORE = registryBlock("deepslate_chromite_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops()));


    public static final DeferredHolder<Block, Block> NETHERRACK_CHROMITE_ORE = registryBlock("netherrack_chromite_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops()));
    //ここまでで一つの鉱石
    //蛍石
    public static final DeferredHolder<Block, Block> FLUORITE_ORE = registryBlock("fluorite_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(4f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> DEEPSLATE_FLUORITE_ORE = registryBlock("deepslate_fluorite_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(4f)
                    .requiresCorrectToolForDrops()));


    public static final DeferredHolder<Block, Block> NETHERRACK_FLUORITE_ORE = registryBlock("netherrack_fluorite_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(4f)
                    .requiresCorrectToolForDrops()));
    //ここまでで一つの鉱石
    //ミョウバン
    public static final DeferredHolder<Block, Block> ALUNITE_ORE = registryBlock("alunite_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> DEEPSLATE_ALUNITE_ORE = registryBlock("deepslate_alunite_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops()));


    public static final DeferredHolder<Block, Block> NETHERRACK_ALUNITE_ORE = registryBlock("netherrack_alunite_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops()));


    //ボーキサイト
    public static final DeferredHolder<Block, Block> BAUXITE_ORE = registryBlock("bauxite_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> DEEPSLATE_BAUXITE_ORE = registryBlock("deepslate_bauxite_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops()));


    public static final DeferredHolder<Block, Block> NETHERRACK_BAUXITE_ORE = registryBlock("netherrack_bauxite_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops()));


    //チタン
    public static final DeferredHolder<Block, Block> TITANIUM_ORE = registryBlock("titanium_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(6f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> DEEPSLATE_TITANIUM_ORE = registryBlock("deepslate_titanium_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(6f)
                    .requiresCorrectToolForDrops()));


    public static final DeferredHolder<Block, Block> NETHERRACK_TITANIUM_ORE = registryBlock("netherrack_titanium_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(6f)
                    .requiresCorrectToolForDrops()));


    //マグネシウム
    public static final DeferredHolder<Block, Block> MAGUNESIUM_ORE = registryBlock("magunesium_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> DEEPSLATE_MAGUNESIUM_ORE = registryBlock("deepslate_magunesium_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops()));


    public static final DeferredHolder<Block, Block> NETHERRACK_MAGUNESIUM_ORE = registryBlock("netherrack_magunesium_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops()));

    //バナジウム
    public static final DeferredHolder<Block, Block> VANADIUM_ORE = registryBlock("vanadium_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> DEEPSLATE_VANADIUM_ORE = registryBlock("deepslate_vanadium_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops()));


    public static final DeferredHolder<Block, Block> NETHERRACK_VANADIUM_ORE = registryBlock("netherrack_vanadium_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops()));


    //建材・塊
    public static final DeferredHolder<Block, Block> CHROMITE_BLOCK = registryBlock("chromite_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(5f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> STAINLESS_BLOCK = registryBlock("stainless_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(5f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> FLUORITE_BLOCK = registryBlock("fluorite_block",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(5f)
                    .noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> ALUNITE_BLOCK = registryBlock("alunite_block",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(5f)
                    .noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> ALUMINUM_BLOCK = registryBlock("aluminum_block",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(5f)
                    .noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> TITANIUM_BLOCK = registryBlock("titanium_block",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(5f)
                    .noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> MAGUNESIUM_BLOCK = registryBlock("magunesium_block",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(5f)
                    .noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> VANADIUM_BLOCK = registryBlock("vanadium_block",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(5f)
                    .noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> PAMMUKALE_BLOCK = registryBlock("pammukale_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops()));


    //特殊ブロック
    public static final DeferredHolder<Block, Block> SANITARY_BLOCK = registryBlock("sanitary_block",
            () -> new SanitaryBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(5f)
                    .requiresCorrectToolForDrops()));


    //農作物
    public static final DeferredHolder<Block, Block> ASPARAGUS = BLOCKS.register("asparagus",
            () -> new AsparagusBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));

    public static final DeferredHolder<Block, Block> CABBAGE = BLOCKS.register("cabbage",
            () -> new CabbageBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));

    public static final DeferredHolder<Block, Block> RICE_BLOCK = BLOCKS.register("rice",
            () -> new RiceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));

    public static final DeferredHolder<Block, Block> MINT_BLOCK = BLOCKS.register("mint",
            () -> new MintBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));

    public static final DeferredHolder<Block, Block> TOMATO_BLOCK = BLOCKS.register("tomato",
            () -> new TomatoBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));

    public static final DeferredHolder<Block, Block> CORN_BLOCK = BLOCKS.register("corn",
            () -> new CornBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));

    public static final DeferredHolder<Block, Block> ONION_BLOCK = BLOCKS.register("onion",
            () -> new OnionBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));

    public static final DeferredHolder<Block, Block> GINGER_BLOCK = BLOCKS.register("ginger",
            () -> new GingerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));

    public static final DeferredHolder<Block, Block> GREEN_PEPPER_BLOCK = BLOCKS.register("green_pepper",
            () -> new GreenPepperBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));

    public static final DeferredHolder<Block, Block> PAPRIKA_BLOCK = BLOCKS.register("paprika",
            () -> new PaprikaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));

    public static final DeferredHolder<Block, Block> EGGPLANT_BLOCK = BLOCKS.register("eggplant",
            () -> new EggplantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));

    public static final DeferredHolder<Block, Block> WHITE_RADISH_BLOCK = BLOCKS.register("white_radish",
            () -> new WhiteRadishBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));

    public static final DeferredHolder<Block, Block> CHILI_PEPPER_BLOCK = BLOCKS.register("chili_pepper",
            () -> new ChiliPepperBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));

    public static final DeferredHolder<Block, Block> BASIL_BLOCK = BLOCKS.register("basil",
            () -> new BasilBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));

    public static final DeferredHolder<Block, Block> LOTUS_ROOT_BLOCK = BLOCKS.register("lotus_root",
            () -> new LotusRootBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));


    //鉱石の作物
    public static final DeferredHolder<Block, Block> CROP_DIAMOND = BLOCKS.register("crop_diamond",
            () -> new CropDiamond(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));

    public static final DeferredHolder<Block, Block> CROP_COAL = BLOCKS.register("crop_coal",
            () -> new CropCoal(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));

    public static final DeferredHolder<Block, Block> CROP_COPPER = BLOCKS.register("crop_copper",
            () -> new CropCopper(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));

    public static final DeferredHolder<Block, Block> CROP_REDSTONE = BLOCKS.register("crop_redstone",
            () -> new CropRedStone(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));

    public static final DeferredHolder<Block, Block> CROP_LAPIS = BLOCKS.register("crop_lapis",
            () -> new CropLapis(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));

    public static final DeferredHolder<Block, Block> CROP_IRON = BLOCKS.register("crop_iron",
            () -> new CropIron(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));

    public static final DeferredHolder<Block, Block> CROP_GOLD = BLOCKS.register("crop_gold",
            () -> new CropGold(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));

    public static final DeferredHolder<Block, Block> CROP_EMERALD = BLOCKS.register("crop_emerald",
            () -> new CropEmerald(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollission().noOcclusion()));


    //作業台
    public static final DeferredHolder<Block, Block> BFURNACE_BLOCK = registryBlock("bfurnace_block",
            () -> new BFurnaceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(4f)
                    .requiresCorrectToolForDrops().noOcclusion()));

    public static final DeferredHolder<Block, Block> DISTILLER_BLOCK = registryBlock("distiller_block",
            () -> new DistillerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(4f)
                    .noOcclusion()));

    public static final DeferredHolder<Block, Block> SAKE_DARU_BLOCK = registryBlock("sakedaru_block",
            () -> new SakeDaruBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(4f)
                    .noOcclusion()));

    public static final DeferredHolder<Block, Block> MIXER_BLOCK = registryBlock("mixer_block",
            () -> new MixerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(1f)
                    .noOcclusion()));

    public static final DeferredHolder<Block, Block> FRYPAN_BLOCK = registryBlock("frypan_block",
            () -> new FryPanBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(1f)
                    .noOcclusion()));


    //木
    //カベルネソービニヨン
    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_LOG = registryBlock("cabernet_sauvignon_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
                    ));
    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_WOOD = registryBlock("cabernet_sauvignon_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
                    ));
    public static final DeferredHolder<Block, Block> STRIPPED_CABERNET_SAUVIGNON_LOG = registryBlock("stripped_cabernet_sauvignon_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
                    ));
    public static final DeferredHolder<Block, Block> STRIPPED_CABERNET_SAUVIGNON_WOOD = registryBlock("stripped_cabernet_sauvignon_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
                    ));


    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_PLANKS = registryBlock("cabernet_sauvignon_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            });

    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_LEAVES = registryBlock("cabernet_sauvignon_leaves",
            () -> new CabernetLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
                    ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            });

    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_SAPLING = registryBlock("cabernet_sauvignon_sapling",
            () -> new SaplingBlock(SauviconTreeGrower.INSTANCE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    //ブドウブロック
    //葉っぱにぶらさがるやつ
    public static final DeferredHolder<Block, Block> GRAPE_BLOCK = BLOCKS.register("grape_block",
            () -> new GrapeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PROPAGULE).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));


    //ここまでで一種類の木

    //楓
    public static final DeferredHolder<Block, Block> MAPLE_LOG = registryBlock("maple_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
                    ));
    public static final DeferredHolder<Block, Block> MAPLE_WOOD = registryBlock("maple_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
                    ));
    public static final DeferredHolder<Block, Block> STRIPPED_MAPLE_LOG = registryBlock("stripped_maple_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
                    ));
    public static final DeferredHolder<Block, Block> STRIPPED_MAPLE_WOOD = registryBlock("stripped_maple_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
                    ));


    public static final DeferredHolder<Block, Block> MAPLE_PLANKS = registryBlock("maple_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            });

    public static final DeferredHolder<Block, Block> MAPLE_LEAVES = registryBlock("maple_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                    ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            });

    public static final DeferredHolder<Block, Block> MAPLE_SAPLING = registryBlock("maple_sapling",
            () -> new SaplingBlock(MapleTreeGrower.INSTANCE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));


    //シナモン
    public static final DeferredHolder<Block, Block> CINNAMON_LOG = registryBlock("cinnamon_log",
            () -> new ModFlammableRotatedPillarDropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
                    ));
    public static final DeferredHolder<Block, Block> CINNAMON_WOOD = registryBlock("cinnamon_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
                    ));
    public static final DeferredHolder<Block, Block> STRIPPED_CINNAMON_LOG = registryBlock("stripped_cinnamon_log",
            () -> new ModFlammableRotatedPillarDropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
                    ));
    public static final DeferredHolder<Block, Block> STRIPPED_CINNAMON_WOOD = registryBlock("stripped_cinnamon_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
                    ));


    public static final DeferredHolder<Block, Block> CINNAMON_PLANKS = registryBlock("cinnamon_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            });

    public static final DeferredHolder<Block, Block> CINNAMON_LEAVES = registryBlock("cinnamon_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                    ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            });

    public static final DeferredHolder<Block, Block> CINNAMON_SAPLING = registryBlock("cinnamon_sapling",
            () -> new SaplingBlock(CinnamonTreeGrower.INSTANCE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));


    //コーラの木
    public static final DeferredHolder<Block, Block> COLA_LOG = registryBlock("cola_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
                    ));
    public static final DeferredHolder<Block, Block> COLA_WOOD = registryBlock("cola_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
                    ));
    public static final DeferredHolder<Block, Block> STRIPPED_COLA_LOG = registryBlock("stripped_cola_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
                    ));
    public static final DeferredHolder<Block, Block> STRIPPED_COLA_WOOD = registryBlock("stripped_cola_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
                    ));


    public static final DeferredHolder<Block, Block> COLA_PLANKS = registryBlock("cola_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            });

    public static final DeferredHolder<Block, Block> COLA_LEAVES = registryBlock("cola_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                    ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            });


    public static final DeferredHolder<Block, Block> COLA_SAPLING = registryBlock("cola_sapling",
            () -> new SaplingBlock(ColaTreeGrower.INSTANCE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredHolder<Block, Block> COLA_FRUIT = BLOCKS.register("cola_fruit",
            () -> new ColaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COCOA).randomTicks().strength(0.2F,3.0F).sound(SoundType.WOOD).noOcclusion()));


    public static final DeferredHolder<Block, Block> LEMON_LOG = registryBlock("lemon_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
            ));
    public static final DeferredHolder<Block, Block> LEMON_WOOD = registryBlock("lemon_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
            ));
    public static final DeferredHolder<Block, Block> STRIPPED_LEMON_LOG = registryBlock("stripped_lemon_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
            ));
    public static final DeferredHolder<Block, Block> STRIPPED_LEMON_WOOD = registryBlock("stripped_lemon_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
            ));


    public static final DeferredHolder<Block, Block> LEMON_PLANKS = registryBlock("lemon_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            });

    public static final DeferredHolder<Block, Block> LEMON_LEAVES = registryBlock("lemon_leaves",
            () -> new LemonLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            });

    public static final DeferredHolder<Block, Block> LEMON_SAPLING = registryBlock("lemon_sapling",
            () -> new SaplingBlock(LemonTreeGrower.INSTANCE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    //ブドウブロック
    //葉っぱにぶらさがるやつ
    public static final DeferredHolder<Block, Block> LEMON_BLOCK = BLOCKS.register("lemon_block",
            () -> new LemonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PROPAGULE).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));


    public static final DeferredHolder<Block, Block> PLUM_LOG = registryBlock("plum_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
            ));
    public static final DeferredHolder<Block, Block> PLUM_WOOD = registryBlock("plum_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
            ));
    public static final DeferredHolder<Block, Block> STRIPPED_PLUM_LOG = registryBlock("stripped_plum_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
            ));
    public static final DeferredHolder<Block, Block> STRIPPED_PLUM_WOOD = registryBlock("stripped_plum_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
            ));


    public static final DeferredHolder<Block, Block> PLUM_PLANKS = registryBlock("plum_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            });

    public static final DeferredHolder<Block, Block> PLUM_LEAVES = registryBlock("plum_leaves",
            () -> new PlumLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            });

    public static final DeferredHolder<Block, Block> PLUM_SAPLING = registryBlock("plum_sapling",
            () -> new SaplingBlock(PlumTreeGrower.INSTANCE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    //ブドウブロック
    //葉っぱにぶらさがるやつ
    public static final DeferredHolder<Block, Block> PLUM_BLOCK = BLOCKS.register("plum_block",
            () -> new PlumBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PROPAGULE).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));


    public static final DeferredHolder<Block, Block> CHERRY_LOG = registryBlock("cherry_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
            ));
    public static final DeferredHolder<Block, Block> CHERRY_WOOD = registryBlock("cherry_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
            ));
    public static final DeferredHolder<Block, Block> STRIPPED_CHERRY_LOG = registryBlock("stripped_cherry_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
            ));
    public static final DeferredHolder<Block, Block> STRIPPED_CHERRY_WOOD = registryBlock("stripped_cherry_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
            ));


    public static final DeferredHolder<Block, Block> CHERRY_PLANKS = registryBlock("cherry_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            });

    public static final DeferredHolder<Block, Block> CHERRY_LEAVES = registryBlock("cherry_leaves",
            () -> new CherryLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            });

    public static final DeferredHolder<Block, Block> CHERRY_SAPLING = registryBlock("cherry_sapling",
            () -> new SaplingBlock(CherryTreeGrower.INSTANCE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    //ブドウブロック
    //葉っぱにぶらさがるやつ
    public static final DeferredHolder<Block, Block> CHERRY_BLOCK = BLOCKS.register("cherry_block",
            () -> new CherryBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PROPAGULE).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));


    public static final DeferredHolder<Block, Block> BANANA_LOG = registryBlock("banana_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
            ));
    public static final DeferredHolder<Block, Block> BANANA_WOOD = registryBlock("banana_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
            ));
    public static final DeferredHolder<Block, Block> STRIPPED_BANANA_LOG = registryBlock("stripped_banana_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
            ));
    public static final DeferredHolder<Block, Block> STRIPPED_BANANA_WOOD = registryBlock("stripped_banana_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
            ));


    public static final DeferredHolder<Block, Block> BANANA_PLANKS = registryBlock("banana_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            });

    public static final DeferredHolder<Block, Block> BANANA_LEAVES = registryBlock("banana_leaves",
            () -> new BananaLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            });

    public static final DeferredHolder<Block, Block> BANANA_SAPLING = registryBlock("banana_sapling",
            () -> new SaplingBlock(BananaTreeGrower.INSTANCE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    //ブドウブロック
    //葉っぱにぶらさがるやつ
    public static final DeferredHolder<Block, Block> BANANA_BLOCK = BLOCKS.register("banana_block",
            () -> new BananaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PROPAGULE).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));


    public static final DeferredHolder<Block, Block> PEACH_LOG = registryBlock("peach_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
            ));
    public static final DeferredHolder<Block, Block> PEACH_WOOD = registryBlock("peach_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
            ));
    public static final DeferredHolder<Block, Block> STRIPPED_PEACH_LOG = registryBlock("stripped_peach_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
            ));
    public static final DeferredHolder<Block, Block> STRIPPED_PEACH_WOOD = registryBlock("stripped_peach_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
            ));


    public static final DeferredHolder<Block, Block> PEACH_PLANKS = registryBlock("peach_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            });

    public static final DeferredHolder<Block, Block> PEACH_LEAVES = registryBlock("peach_leaves",
            () -> new PeachLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            });

    public static final DeferredHolder<Block, Block> PEACH_SAPLING = registryBlock("peach_sapling",
            () -> new SaplingBlock(PeachTreeGrower.INSTANCE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    //ブドウブロック
    //葉っぱにぶらさがるやつ
    public static final DeferredHolder<Block, Block> PEACH_BLOCK = BLOCKS.register("peach_block",
            () -> new PeachBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PROPAGULE).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));


    public static final DeferredHolder<Block, Block> COCONUT_LOG = registryBlock("coconut_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
            ));
    public static final DeferredHolder<Block, Block> COCONUT_WOOD = registryBlock("coconut_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
            ));
    public static final DeferredHolder<Block, Block> STRIPPED_COCONUT_LOG = registryBlock("stripped_coconut_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
            ));
    public static final DeferredHolder<Block, Block> STRIPPED_COCONUT_WOOD = registryBlock("stripped_coconut_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
            ));


    public static final DeferredHolder<Block, Block> COCONUT_PLANKS = registryBlock("coconut_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            });

    public static final DeferredHolder<Block, Block> COCONUT_LEAVES = registryBlock("coconut_leaves",
            () -> new CoconutLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            });

    public static final DeferredHolder<Block, Block> COCONUT_SAPLING = registryBlock("coconut_sapling",
            () -> new SaplingBlock(CoconutTreeGrower.INSTANCE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    //ブドウブロック
    //葉っぱにぶらさがるやつ
    public static final DeferredHolder<Block, Block> COCONUT_BLOCK = BLOCKS.register("coconut_block",
            () -> new CoconutBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PROPAGULE).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));


    public static final DeferredHolder<Block, Block> ALMOND_LOG = registryBlock("almond_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
            ));
    public static final DeferredHolder<Block, Block> ALMOND_WOOD = registryBlock("almond_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
            ));
    public static final DeferredHolder<Block, Block> STRIPPED_ALMOND_LOG = registryBlock("stripped_almond_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
            ));
    public static final DeferredHolder<Block, Block> STRIPPED_ALMOND_WOOD = registryBlock("stripped_almond_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
            ));


    public static final DeferredHolder<Block, Block> ALMOND_PLANKS = registryBlock("almond_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            });

    public static final DeferredHolder<Block, Block> ALMOND_LEAVES = registryBlock("almond_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
            ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            });


    public static final DeferredHolder<Block, Block> ALMOND_SAPLING = registryBlock("almond_sapling",
            () -> new SaplingBlock(AlmondTreeGrower.INSTANCE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredHolder<Block, Block> ALMOND_FRUIT = BLOCKS.register("almond_fruit",
            () -> new AlmondBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COCOA).randomTicks().strength(0.2F,3.0F).sound(SoundType.WOOD).noOcclusion()));


    public static final DeferredHolder<Block, Block> DURIAN_LOG = registryBlock("durian_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
            ));
    public static final DeferredHolder<Block, Block> DURIAN_WOOD = registryBlock("durian_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
            ));
    public static final DeferredHolder<Block, Block> STRIPPED_DURIAN_LOG = registryBlock("stripped_durian_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
            ));
    public static final DeferredHolder<Block, Block> STRIPPED_DURIAN_WOOD = registryBlock("stripped_durian_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
            ));


    public static final DeferredHolder<Block, Block> DURIAN_PLANKS = registryBlock("durian_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            });

    public static final DeferredHolder<Block, Block> DURIAN_LEAVES = registryBlock("durian_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
            ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            });


    public static final DeferredHolder<Block, Block> DURIAN_SAPLING = registryBlock("durian_sapling",
            () -> new SaplingBlock(DurianTreeGrower.INSTANCE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredHolder<Block, Block> DURIAN_FRUIT = BLOCKS.register("durian_fruit",
            () -> new DurianBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COCOA).randomTicks().strength(0.2F,3.0F).sound(SoundType.WOOD).noOcclusion()));


    public static final DeferredHolder<Block, Block> COFFEE_LOG = registryBlock("coffee_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
            ));
    public static final DeferredHolder<Block, Block> COFFEE_WOOD = registryBlock("coffee_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
            ));
    public static final DeferredHolder<Block, Block> STRIPPED_COFFEE_LOG = registryBlock("stripped_coffee_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
            ));
    public static final DeferredHolder<Block, Block> STRIPPED_COFFEE_WOOD = registryBlock("stripped_coffee_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
            ));


    public static final DeferredHolder<Block, Block> COFFEE_PLANKS = registryBlock("coffee_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            });

    public static final DeferredHolder<Block, Block> COFFEE_LEAVES = registryBlock("coffee_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
            ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            });


    public static final DeferredHolder<Block, Block> COFFEE_SAPLING = registryBlock("coffee_sapling",
            () -> new SaplingBlock(CoffeeTreeGrower.INSTANCE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredHolder<Block, Block> COFFEE_FRUIT = BLOCKS.register("coffee_fruit",
            () -> new CoffeeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COCOA).randomTicks().strength(0.2F,3.0F).sound(SoundType.WOOD).noOcclusion()));



    public static final DeferredHolder<Block, Block> KIWI_LOG = registryBlock("kiwi_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
            ));
    public static final DeferredHolder<Block, Block> KIWI_WOOD = registryBlock("kiwi_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
            ));
    public static final DeferredHolder<Block, Block> STRIPPED_KIWI_LOG = registryBlock("stripped_kiwi_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
            ));
    public static final DeferredHolder<Block, Block> STRIPPED_KIWI_WOOD = registryBlock("stripped_kiwi_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
            ));


    public static final DeferredHolder<Block, Block> KIWI_PLANKS = registryBlock("kiwi_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            });

    public static final DeferredHolder<Block, Block> KIWI_LEAVES = registryBlock("kiwi_leaves",
            () -> new KiwiLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            ){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            });

    public static final DeferredHolder<Block, Block> KIWI_SAPLING = registryBlock("kiwi_sapling",
            () -> new SaplingBlock(KiwiTreeGrower.INSTANCE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    //ブドウブロック
    //葉っぱにぶらさがるやつ
    public static final DeferredHolder<Block, Block> KIWI_BLOCK = BLOCKS.register("kiwi_block",
            () -> new KiwiBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PROPAGULE).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));


    public static final DeferredHolder<Block, Block> TEA_BUSH = registryBlock("tea_bush",
            () -> new TeaBushBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA).randomTicks().strength(0.2F,3.0F).sound(SoundType.WOOD).noOcclusion()));

    public static final DeferredHolder<Block, Block> OLIVE_BUSH = registryBlock("olive_bush",
            () -> new OliveBushBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA).randomTicks().strength(0.2F,3.0F).sound(SoundType.WOOD).noOcclusion()));

    public static final DeferredHolder<Block, Block> BLUE_BERRY_BUSH = registryBlock("blue_berry_bush",
            () -> new BlueBerryBushBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA).randomTicks().strength(0.2F,3.0F).sound(SoundType.WOOD).noOcclusion()));

    public static final DeferredHolder<Block, Block> HOP_BUSH = registryBlock("hop_bush",
            () -> new HopBushBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA).randomTicks().strength(0.2F,3.0F).sound(SoundType.WOOD).noOcclusion()));

    public static final DeferredHolder<Block, Block> PEPPER_BUSH = registryBlock("pepper_bush",
            () -> new PepperBushBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA).randomTicks().strength(0.2F,3.0F).sound(SoundType.WOOD).noOcclusion()));


    //ハーフブロック、フェンス等
    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_STAIRS = registryBlock("cabernet_sauvignon_stairs",
            () -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_SLAB = registryBlock("cabernet_sauvignon_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_FENCE = registryBlock("cabernet_sauvignon_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_FENCE_GATE = registryBlock("cabernet_sauvignon_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE).strength(2.0F,3.0F)));
    //public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_BUTTON = registryBlock("cabernet_sauvignon_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollission()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                ,  BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_PRESSURE_PLATE = registryBlock("cabernet_sauvignon_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE).noCollission().strength(0.5F)));
    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_DOOR = registryBlock("cabernet_sauvignon_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never)));
    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_TRAPDOOR = registryBlock("cabernet_sauvignon_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never)));

    public static final DeferredHolder<Block, Block> MAPLE_STAIRS = registryBlock("maple_stairs",
            () -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> MAPLE_SLAB = registryBlock("maple_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> MAPLE_FENCE = registryBlock("maple_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> MAPLE_FENCE_GATE = registryBlock("maple_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F)));
    //public static final DeferredHolder<Block, Block> MAPLE_BUTTON = registryBlock("maple_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollission()
    //                .strength(0.5F).sound(SoundType.OAK_PLANKS).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                ,  BlockSetType.OAK,30, true));

    public static final DeferredHolder<Block, Block> MAPLE_PRESSURE_PLATE = registryBlock("maple_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollission().strength(0.5F)));
    public static final DeferredHolder<Block, Block> MAPLE_DOOR = registryBlock("maple_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never)));
    public static final DeferredHolder<Block, Block> MAPLE_TRAPDOOR = registryBlock("maple_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never)));

    public static final DeferredHolder<Block, Block> CINNAMON_STAIRS = registryBlock("cinnamon_stairs",
            () -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> CINNAMON_SLAB = registryBlock("cinnamon_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> CINNAMON_FENCE = registryBlock("cinnamon_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> CINNAMON_FENCE_GATE = registryBlock("cinnamon_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F)));
    //public static final DeferredHolder<Block, Block> CINNAMON_BUTTON = registryBlock("cinnamon_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollission()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                ,  BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> CINNAMON_PRESSURE_PLATE = registryBlock("cinnamon_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollission().strength(0.5F)));
    public static final DeferredHolder<Block, Block> CINNAMON_DOOR = registryBlock("cinnamon_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never)));
    public static final DeferredHolder<Block, Block> CINNAMON_TRAPDOOR = registryBlock("cinnamon_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never)));

    public static final DeferredHolder<Block, Block> COLA_STAIRS = registryBlock("cola_stairs",
            () -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> COLA_SLAB = registryBlock("cola_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> COLA_FENCE = registryBlock("cola_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> COLA_FENCE_GATE = registryBlock("cola_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F)));
    //public static final DeferredHolder<Block, Block> COLA_BUTTON = registryBlock("cola_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollission()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                ,  BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> COLA_PRESSURE_PLATE = registryBlock("cola_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> COLA_DOOR = registryBlock("cola_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never)));
    public static final DeferredHolder<Block, Block> COLA_TRAPDOOR = registryBlock("cola_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never)));

    public static final DeferredHolder<Block, Block> LEMON_STAIRS = registryBlock("lemon_stairs",
            () -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> LEMON_SLAB = registryBlock("lemon_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> LEMON_FENCE = registryBlock("lemon_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> LEMON_FENCE_GATE = registryBlock("lemon_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F)));
    //public static final DeferredHolder<Block, Block> LEMON_BUTTON = registryBlock("lemon_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollission()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                , BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> LEMON_PRESSURE_PLATE = registryBlock("lemon_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> LEMON_DOOR = registryBlock("lemon_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never)));
    public static final DeferredHolder<Block, Block> LEMON_TRAPDOOR = registryBlock("lemon_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never)));


    public static final DeferredHolder<Block, Block> PLUM_STAIRS = registryBlock("plum_stairs",
            () -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> PLUM_SLAB = registryBlock("plum_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> PLUM_FENCE = registryBlock("plum_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> PLUM_FENCE_GATE = registryBlock("plum_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F)));
    //public static final DeferredHolder<Block, Block> PLUM_BUTTON = registryBlock("plum_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollission()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                , BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> PLUM_PRESSURE_PLATE = registryBlock("plum_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> PLUM_DOOR = registryBlock("plum_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never)));
    public static final DeferredHolder<Block, Block> PLUM_TRAPDOOR = registryBlock("plum_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never)));


    public static final DeferredHolder<Block, Block> CHERRY_STAIRS = registryBlock("cherry_stairs",
            () -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> CHERRY_SLAB = registryBlock("cherry_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> CHERRY_FENCE = registryBlock("cherry_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> CHERRY_FENCE_GATE = registryBlock("cherry_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F)));
    //public static final DeferredHolder<Block, Block> CHERRY_BUTTON = registryBlock("cherry_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollission()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                , BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> CHERRY_PRESSURE_PLATE = registryBlock("cherry_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> CHERRY_DOOR = registryBlock("cherry_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never)));
    public static final DeferredHolder<Block, Block> CHERRY_TRAPDOOR = registryBlock("cherry_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never)));


    public static final DeferredHolder<Block, Block> BANANA_STAIRS = registryBlock("banana_stairs",
            () -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> BANANA_SLAB = registryBlock("banana_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> BANANA_FENCE = registryBlock("banana_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> BANANA_FENCE_GATE = registryBlock("banana_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F)));
    //public static final DeferredHolder<Block, Block> BANANA_BUTTON = registryBlock("banana_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollission()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                , BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> BANANA_PRESSURE_PLATE = registryBlock("banana_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> BANANA_DOOR = registryBlock("banana_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never)));
    public static final DeferredHolder<Block, Block> BANANA_TRAPDOOR = registryBlock("banana_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never)));


    public static final DeferredHolder<Block, Block> COCONUT_STAIRS = registryBlock("coconut_stairs",
            () -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> COCONUT_SLAB = registryBlock("coconut_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> COCONUT_FENCE = registryBlock("coconut_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> COCONUT_FENCE_GATE = registryBlock("coconut_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F)));
    //public static final DeferredHolder<Block, Block> COCONUT_BUTTON = registryBlock("coconut_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollission()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                , BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> COCONUT_PRESSURE_PLATE = registryBlock("coconut_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> COCONUT_DOOR = registryBlock("coconut_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never)));
    public static final DeferredHolder<Block, Block> COCONUT_TRAPDOOR = registryBlock("coconut_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never)));


    public static final DeferredHolder<Block, Block> PEACH_STAIRS = registryBlock("peach_stairs",
            () -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> PEACH_SLAB = registryBlock("peach_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> PEACH_FENCE = registryBlock("peach_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> PEACH_FENCE_GATE = registryBlock("peach_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F)));
    //public static final DeferredHolder<Block, Block> PEACH_BUTTON = registryBlock("peach_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollission()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                , BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> PEACH_PRESSURE_PLATE = registryBlock("peach_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> PEACH_DOOR = registryBlock("peach_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never)));
    public static final DeferredHolder<Block, Block> PEACH_TRAPDOOR = registryBlock("peach_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never)));



    public static final DeferredHolder<Block, Block> ALMOND_STAIRS = registryBlock("almond_stairs",
            () -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> ALMOND_SLAB = registryBlock("almond_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> ALMOND_FENCE = registryBlock("almond_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> ALMOND_FENCE_GATE = registryBlock("almond_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F)));
    //public static final DeferredHolder<Block, Block> ALMOND_BUTTON = registryBlock("almond_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollission()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                , BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> ALMOND_PRESSURE_PLATE = registryBlock("almond_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> ALMOND_DOOR = registryBlock("almond_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never)));
    public static final DeferredHolder<Block, Block> ALMOND_TRAPDOOR = registryBlock("almond_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never)));


    public static final DeferredHolder<Block, Block> DURIAN_STAIRS = registryBlock("durian_stairs",
            () -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> DURIAN_SLAB = registryBlock("durian_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> DURIAN_FENCE = registryBlock("durian_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> DURIAN_FENCE_GATE = registryBlock("durian_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F)));
    //public static final DeferredHolder<Block, Block> DURIAN_BUTTON = registryBlock("durian_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollission()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                , BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> DURIAN_PRESSURE_PLATE = registryBlock("durian_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> DURIAN_DOOR = registryBlock("durian_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never)));
    public static final DeferredHolder<Block, Block> DURIAN_TRAPDOOR = registryBlock("durian_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never)));

    public static final DeferredHolder<Block, Block> COFFEE_STAIRS = registryBlock("coffee_stairs",
            () -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> COFFEE_SLAB = registryBlock("coffee_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> COFFEE_FENCE = registryBlock("coffee_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> COFFEE_FENCE_GATE = registryBlock("coffee_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F)));
    //public static final DeferredHolder<Block, Block> DURIAN_BUTTON = registryBlock("durian_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollission()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                , BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> COFFEE_PRESSURE_PLATE = registryBlock("coffee_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> COFFEE_DOOR = registryBlock("coffee_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never)));
    public static final DeferredHolder<Block, Block> COFFEE_TRAPDOOR = registryBlock("coffee_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never)));


    public static final DeferredHolder<Block, Block> RAW_COFFEE_BLOCK = registryBlock("raw_coffee_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).strength(3f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> COFFEE_BLOCK = registryBlock("coffee_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).strength(3f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> MUSK_COFFEE_BLOCK = registryBlock("musk_coffee_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).strength(3f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> KIWI_STAIRS = registryBlock("kiwi_stairs",
            () -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> KIWI_SLAB = registryBlock("kiwi_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> KIWI_FENCE = registryBlock("kiwi_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredHolder<Block, Block> KIWI_FENCE_GATE = registryBlock("kiwi_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE).strength(2.0F,3.0F)));
    //public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_BUTTON = registryBlock("cabernet_sauvignon_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollission()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                ,  BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> KIWI_PRESSURE_PLATE = registryBlock("kiwi_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE).noCollission().strength(0.5F)));
    public static final DeferredHolder<Block, Block> KIWI_DOOR = registryBlock("kiwi_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never)));
    public static final DeferredHolder<Block, Block> KIWI_TRAPDOOR = registryBlock("kiwi_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never)));

    //花
    public static final DeferredHolder<Block, Block> QUEEN_OF_NIGHT = registryBlock("queen_of_night",
            () -> new TallFlowerBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.SUNFLOWER)));

    public static final DeferredHolder<Block, Block> ROSE = registryBlock("rose",
            () -> new FlowerBlock(MobEffects.GLOWING, 5,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));

    public static final DeferredHolder<Block, Block> CAMOMILE = registryBlock("camomile",
            () -> new FlowerBlock(MobEffects.GLOWING, 5,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));

    public static final DeferredHolder<Block, Block> THYME = registryBlock("thyme",
            () -> new FlowerBlock(MobEffects.GLOWING, 5,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));

    public static final DeferredHolder<Block, Block> OREGANO = registryBlock("oregano",
            () -> new FlowerBlock(MobEffects.GLOWING, 5,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));

    public static final DeferredHolder<Block, Block> SEED_PLAIN = registryBlock("seed_plain",
            () -> new FlowerBlock(MobEffects.GLOWING, 5,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));

    public static final DeferredHolder<Block, Block> SEED_JUNGLE = registryBlock("seed_jungle",
            () -> new FlowerBlock(MobEffects.GLOWING, 5,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));

    public static final DeferredHolder<Block, Block> SEED_TAIGA = registryBlock("seed_taiga",
            () -> new FlowerBlock(MobEffects.GLOWING, 5,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));

    public static final DeferredHolder<Block, Block> SEED_SAVANNA = registryBlock("seed_savanna",
            () -> new FlowerBlock(MobEffects.GLOWING, 5,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));


    //public static final DeferredHolder<Block, Block> CHAIR = registryBlock("chair",
    //        () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(4f)
    //                .requiresCorrectToolForDrops().noOcclusion()));


    //液体とCauldron
    public static final DeferredHolder<Block, LiquidBlock> CLEAN_WATER_BLOCK = BLOCKS.register("clean_water_block",
            () -> new LiquidBlock(ModFluids.SOURCE_CLEAN.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));
    public static final DeferredHolder<Block, Block> CLEAN_WATER_CAULDRON = BLOCKS.register("clean_water_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.CLEAN_WATER));
    public static final DeferredHolder<Block, LiquidBlock> WHISKEY_BLOCK = BLOCKS.register("whiskey_block",
            () -> new LiquidBlock(ModFluids.SOURCE_WHISKEY.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));

    public static final DeferredHolder<Block, Block> WHISKEY_CAULDRON = BLOCKS.register("whiskey_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.WHISKEY));

    public static final DeferredHolder<Block, Block> ROW_WHISKEY_CAULDRON = BLOCKS.register("row_whiskey_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.CLEAN_WATER));

    public static final DeferredHolder<Block, LiquidBlock> MAPLE_BLOCK = BLOCKS.register("maple_block",
            () -> new LiquidBlock(ModFluids.SOURCE_MAPLE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));

    public static final DeferredHolder<Block, Block> MAPLE_CAULDRON = BLOCKS.register("maple_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.MAPLE));

    public static final DeferredHolder<Block, LiquidBlock> SAKE_BLOCK = BLOCKS.register("sake_block",
            () -> new LiquidBlock(ModFluids.SOURCE_SAKE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));

    public static final DeferredHolder<Block, Block> SAKE_CAULDRON = BLOCKS.register("sake_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.SAKE));

    public static final DeferredHolder<Block, Block> WHITE_LIQUEUR_CAULDRON = BLOCKS.register("white_liqueur_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.WHITE_LIQUEUR));

    public static final DeferredHolder<Block, Block> HABU_LIQUEUR_CAULDRON = BLOCKS.register("habu_liqueur_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.HABU_LIQUEUR));

    public static final DeferredHolder<Block, LiquidBlock> WINE_BLOCK = BLOCKS.register("wine_block",
            () -> new LiquidBlock(ModFluids.SOURCE_WINE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));

    public static final DeferredHolder<Block, Block> WINE_CAULDRON = BLOCKS.register("wine_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.WINE));

    public static final DeferredHolder<Block, LiquidBlock> WHITE_WINE_BLOCK = BLOCKS.register("white_wine_block",
            () -> new LiquidBlock(ModFluids.SOURCE_WHITE_WINE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));

    public static final DeferredHolder<Block, Block> WHITE_WINE_CAULDRON = BLOCKS.register("white_wine_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.WHITE_WINE));

    public static final DeferredHolder<Block, Block> RICE_CAULDRON_ROW = BLOCKS.register("rice_cauldron_row",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.R_RICE));

    public static final DeferredHolder<Block, Block> RICE_CAULDRON_PROCESSED = BLOCKS.register("rice_cauldron_processed",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.P_RICE));
    public static final DeferredHolder<Block, Block> J_MALT_CAULDRON = BLOCKS.register("j_malt_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.J_MALT));

    public static final DeferredHolder<Block, Block> YEAST_STARTER_CAULDRON_ROW = BLOCKS.register("yeast_starter_cauldron_row",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.R_YEAST));

    public static final DeferredHolder<Block, Block> YEAST_STARTER_CAULDRON_PROCESSED = BLOCKS.register("yeast_starter_cauldron_processed",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.P_YEAST));

    public static final DeferredHolder<Block, Block> MASH_CAULDRON_ROW = BLOCKS.register("mash_cauldron_row",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.R_MASH));

    public static final DeferredHolder<Block, Block> MASH_CAULDRON_PROCESSED = BLOCKS.register("mash_cauldron_processed",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.P_MASH));

    public static final DeferredHolder<Block, Block> MASH_RICE_CAULDRON_PROCESSED = BLOCKS.register("mash_rice_cauldron_processed",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.P_MASH_RICE));

    public static final DeferredHolder<Block, Block> BOILED_RICE_BLOCK = registryBlock("boiled_rice_block",
            () -> new RiceMaltBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK).noLootTable()));

    public static final DeferredHolder<Block, Block> JAPANESE_MALT_P = registryBlock("japanese_malt_p",
            () -> new RiceMaltBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK).noLootTable()));

    public static final DeferredHolder<Block, Block> JAPANESE_MALT_R = registryBlock("japanese_malt_r",
            () -> new RiceMaltBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK).noLootTable()));

    public static final DeferredHolder<Block, LiquidBlock> MASH_BLOCK = BLOCKS.register("mash_block",
            () -> new LiquidBlock(ModFluids.SOURCE_MASH.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));

    public static final DeferredHolder<Block, LiquidBlock> YEAST_BLOCK = BLOCKS.register("yeast_block",
            () -> new LiquidBlock(ModFluids.SOURCE_YEAST.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));

    public static final DeferredHolder<Block, Block> GRAPE_CAULDRON = BLOCKS.register("gapre_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.GRAPE));

    public static final DeferredHolder<Block, Block> GRAPE_JUICE_CAULDRON = BLOCKS.register("grape_juice_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.GRAPE_JUICE));

    public static final DeferredHolder<Block, Block> WHITE_GRAPE_JUICE_CAULDRON = BLOCKS.register("white_grape_juice_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.WHITE_GRAPE_JUICE));

    public static final DeferredHolder<Block, Block> APPLE_CAULDRON = BLOCKS.register("apple_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.APPLE));

    public static final DeferredHolder<Block, Block> APPLE_JUICE_CAULDRON = BLOCKS.register("apple_juice_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.APPLE_JUICE));

    public static final DeferredHolder<Block, Block> APPLE_LIQUEUR_CAULDRON = BLOCKS.register("apple_liqueur_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.APPLE_LIQUEUR));

    public static final DeferredHolder<Block, Block> LEMON_CAULDRON = BLOCKS.register("lemon_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.LEMON));

    public static final DeferredHolder<Block, Block> LEMON_JUICE_CAULDRON = BLOCKS.register("lemon_juice_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.LEMON_JUICE));

    public static final DeferredHolder<Block, Block> LEMON_LIQUEUR_CAULDRON = BLOCKS.register("lemon_liqueur_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.LEMON_LIQUEUR));

    public static final DeferredHolder<Block, Block> PEACH_CAULDRON = BLOCKS.register("peach_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.PEACH));

    public static final DeferredHolder<Block, Block> PEACH_JUICE_CAULDRON = BLOCKS.register("peach_juice_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.PEACH_JUICE));

    public static final DeferredHolder<Block, Block> PEACH_LIQUEUR_CAULDRON = BLOCKS.register("peach_liqueur_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.PEACH_LIQUEUR));

    public static final DeferredHolder<Block, Block> PLUM_CAULDRON = BLOCKS.register("plum_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.PLUM));

    public static final DeferredHolder<Block, Block> PLUM_JUICE_CAULDRON = BLOCKS.register("plum_juice_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.PLUM_JUICE));

    public static final DeferredHolder<Block, Block> PLUM_LIQUEUR_CAULDRON = BLOCKS.register("plum_liqueur_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.PLUM_LIQUEUR));

    public static final DeferredHolder<Block, Block> BANANA_CAULDRON = BLOCKS.register("banana_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.BANANA));

    public static final DeferredHolder<Block, Block> BANANA_JUICE_CAULDRON = BLOCKS.register("banana_juice_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.BANANA_JUICE));

    public static final DeferredHolder<Block, Block> ALMON_CAULDRON = BLOCKS.register("almond_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.ALMOND));

    public static final DeferredHolder<Block, Block> ALMON_JUICE_CAULDRON = BLOCKS.register("almond_juice_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.ALMOND_JUICE));

    public static final DeferredHolder<Block, Block> COCONUT_CAULDRON = BLOCKS.register("coconut_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.COCONUT));

    public static final DeferredHolder<Block, Block> COCONUT_JUICE_CAULDRON = BLOCKS.register("coconut_juice_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.COCONUT_JUICE));

    public static final DeferredHolder<Block, Block> COFFEE_CAULDRON = BLOCKS.register("coffee_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.COFFEE));

    public static final DeferredHolder<Block, Block> MUSK_COFFEE_CAULDRON = BLOCKS.register("musk_coffee_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable(), ModCauldronInteraction.MUSK_COFFEE));

    public static final DeferredHolder<Block, Block> POTTERS_WHEEL = BLOCKS.register("potters_wheel",
            () -> new PottersWheelBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(2.0f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredHolder<Block, Block> POTTERY_ON_WHEEL = BLOCKS.register("pottery_on_wheel",
            () -> new PotteryOnWheelBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.CLAY)
                    .strength(0.5f)
                    .sound(SoundType.GRAVEL)
                    .noOcclusion()));

    //僕が創造する世界で
    //public static final DeferredHolder<Block, Block> CREATE_TNT = registryBlock("create_tnt",
    //        () -> new CreateTnt(BlockBehaviour.Properties.ofFullCopy(Blocks.TNT).mapColor(MapColor.GRASS)));

    public static final DeferredHolder<Block, Block> BUBBLE = registryBlock("bubble",
            () -> new Bubble(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(6f).noOcclusion()
                    .requiresCorrectToolForDrops()));



    private static boolean never(BlockState p_50779_, BlockGetter p_50780_, BlockPos p_50781_, EntityType<?> p_50782_) {
        return (boolean)false;
    }

    private static <T extends Block> DeferredHolder<Block, T> registryBlock(String name, Supplier<T> block) {
        DeferredHolder<Block, T> toReturn = BLOCKS.register(name, block);
        registryBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>DeferredHolder<Item, Item> registryBlockItem(String name, DeferredHolder<Block, T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register (IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
