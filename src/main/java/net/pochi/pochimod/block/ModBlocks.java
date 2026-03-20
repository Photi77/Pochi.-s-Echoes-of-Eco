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

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {


    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(PochiMod.MOD_ID);

    public static final DeferredHolder<Block, Block> SOLIDIFIED_LAVA =
            registryBlock("solidified_lava", SolidifiedLavaBlock::new,
                    () -> BlockBehaviour.Properties.of()
                            .mapColor(MapColor.FIRE)
                            .strength(0.5F)
                            .sound(SoundType.STONE)
                            .lightLevel((state) -> 15)
                            .noOcclusion());

    //宝石の国
    public static final DeferredHolder<Block, Block> CINNABAR_BLOCK = registryBlock("cinnabar_block",
            AmethystBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_50874_) -> {
                return 8;})
                    .requiresCorrectToolForDrops());
    public static final DeferredHolder<Block, Block> BUDDING_CINNABAR = registryBlock("budding_cinnabar", BuddingCinnabar::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f)
            .requiresCorrectToolForDrops());
    public static final DeferredHolder<Block, Block> CINNABAR_CLUSTER = registryBlock("cinnabar_cluster", props -> new AmethystClusterBlock(7, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152632_) -> {
        return 5;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> LARGE_CINNABAR_BUD = registryBlock("large_cinnabar_bud", props -> new AmethystClusterBlock(5, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152629_) -> {
        return 4;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> MEDIUM_CINNABAR_BUD = registryBlock("medium_cinnabar_bud", props -> new AmethystClusterBlock(4, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152617_) -> {
        return 2;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> SMALL_CINNABAR_BUD = registryBlock("small_cinnabar_bud", props -> new AmethystClusterBlock(3, 4, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_187409_) -> {
        return 1;
    }).pushReaction(PushReaction.DESTROY));

    public static final DeferredHolder<Block, Block> BLACK_DIAMOND_BLOCK = registryBlock("black_diamond_block",
            AmethystBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_50874_) -> {
                        return 8;})
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> BUDDING_BLACK_DIAMOND = registryBlock("budding_black_diamond", BuddingBlackDiamond::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f)
            .requiresCorrectToolForDrops());
    public static final DeferredHolder<Block, Block> BLACK_DIAMOND_CLUSTER = registryBlock("black_diamond_cluster", props -> new AmethystClusterBlock(7, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152632_) -> {
        return 5;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> LARGE_BLACK_DIAMOND_BUD = registryBlock("large_black_diamond_bud", props -> new AmethystClusterBlock(5, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152629_) -> {
        return 4;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> MEDIUM_BLACK_DIAMOND_BUD = registryBlock("medium_black_diamond_bud", props -> new AmethystClusterBlock(4, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152617_) -> {
        return 2;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> SMALL_BLACK_DIAMOND_BUD = registryBlock("small_black_diamond_bud", props -> new AmethystClusterBlock(3, 4, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_187409_) -> {
        return 1;
    }).pushReaction(PushReaction.DESTROY));

    public static final DeferredHolder<Block, Block> YELLOW_DIAMOND_BLOCK = registryBlock("yellow_diamond_block",
            AmethystBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_50874_) -> {
                        return 8;})
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> BUDDING_YELLOW_DIAMOND = registryBlock("budding_yellow_diamond", BuddingYellowDiamond::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f)
            .requiresCorrectToolForDrops());
    public static final DeferredHolder<Block, Block> YELLOW_DIAMOND_CLUSTER = registryBlock("yellow_diamond_cluster", props -> new AmethystClusterBlock(7, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152632_) -> {
        return 5;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> LARGE_YELLOW_DIAMOND_BUD = registryBlock("large_yellow_diamond_bud", props -> new AmethystClusterBlock(5, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152629_) -> {
        return 4;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> MEDIUM_YELLOW_DIAMOND_BUD = registryBlock("medium_yellow_diamond_bud", props -> new AmethystClusterBlock(4, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152617_) -> {
        return 2;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> SMALL_YELLOW_DIAMOND_BUD = registryBlock("small_yellow_diamond_bud", props -> new AmethystClusterBlock(3, 4, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_187409_) -> {
        return 1;
    }).pushReaction(PushReaction.DESTROY));

    public static final DeferredHolder<Block, Block> MORGANITE_BLOCK = registryBlock("morganite_block",
            AmethystBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_50874_) -> {
                        return 8;})
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> BUDDING_MORGANITE = registryBlock("budding_morganite", BuddingMorganite::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f)
            .requiresCorrectToolForDrops());
    public static final DeferredHolder<Block, Block> MORGANITE_CLUSTER = registryBlock("morganite_cluster", props -> new AmethystClusterBlock(7, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152632_) -> {
        return 5;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> LARGE_MORGANITE_BUD = registryBlock("large_morganite_bud", props -> new AmethystClusterBlock(5, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152629_) -> {
        return 4;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> MEDIUM_MORGANITE_BUD = registryBlock("medium_morganite_bud", props -> new AmethystClusterBlock(4, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152617_) -> {
        return 2;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> SMALL_MORGANITE_BUD = registryBlock("small_morganite_bud", props -> new AmethystClusterBlock(3, 4, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_187409_) -> {
        return 1;
    }).pushReaction(PushReaction.DESTROY));

    public static final DeferredHolder<Block, Block> GOSHENITE_BLOCK = registryBlock("goshenite_block",
            AmethystBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_50874_) -> {
                        return 8;})
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> BUDDING_GOSHENITE = registryBlock("budding_goshenite", BuddingGoshenite::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f)
            .requiresCorrectToolForDrops());
    public static final DeferredHolder<Block, Block> GOSHENITE_CLUSTER = registryBlock("goshenite_cluster", props -> new AmethystClusterBlock(7, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152632_) -> {
        return 5;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> LARGE_GOSHENITE_BUD = registryBlock("large_goshenite_bud", props -> new AmethystClusterBlock(5, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152629_) -> {
        return 4;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> MEDIUM_GOSHENITE_BUD = registryBlock("medium_goshenite_bud", props -> new AmethystClusterBlock(4, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152617_) -> {
        return 2;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> SMALL_GOSHENITE_BUD = registryBlock("small_goshenite_bud", props -> new AmethystClusterBlock(3, 4, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_187409_) -> {
        return 1;
    }).pushReaction(PushReaction.DESTROY));

    public static final DeferredHolder<Block, Block> RUTILE_BLOCK = registryBlock("rutile_block",
            AmethystBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_50874_) -> {
                        return 8;})
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> BUDDING_RUTILE = registryBlock("budding_rutile",
            BuddingRutile::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f)
            .requiresCorrectToolForDrops());
    public static final DeferredHolder<Block, Block> RUTILE_CLUSTER = registryBlock("rutile_cluster", props -> new AmethystClusterBlock(7, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152632_) -> {
        return 5;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> LARGE_RUTILE_BUD = registryBlock("large_rutile_bud", props -> new AmethystClusterBlock(5, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152629_) -> {
        return 4;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> MEDIUM_RUTILE_BUD = registryBlock("medium_rutile_bud", props -> new AmethystClusterBlock(4, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152617_) -> {
        return 2;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> SMALL_RUTILE_BUD = registryBlock("small_rutile_bud", props -> new AmethystClusterBlock(3, 4, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_187409_) -> {
        return 1;
    }).pushReaction(PushReaction.DESTROY));

    public static final DeferredHolder<Block, Block> JADE_BLOCK = registryBlock("jade_block",
            AmethystBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_50874_) -> {
                        return 8;})
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> BUDDING_JADE = registryBlock("budding_jade", BuddingJade::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f)
            .requiresCorrectToolForDrops());
    public static final DeferredHolder<Block, Block> JADE_CLUSTER = registryBlock("jade_cluster", props -> new AmethystClusterBlock(7, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152632_) -> {
        return 5;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> LARGE_JADE_BUD = registryBlock("large_jade_bud", props -> new AmethystClusterBlock(5, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152629_) -> {
        return 4;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> MEDIUM_JADE_BUD = registryBlock("medium_jade_bud", props -> new AmethystClusterBlock(4, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152617_) -> {
        return 2;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> SMALL_JADE_BUD = registryBlock("small_jade_bud", props -> new AmethystClusterBlock(3, 4, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_187409_) -> {
        return 1;
    }).pushReaction(PushReaction.DESTROY));

    public static final DeferredHolder<Block, Block> EUCLASE_BLOCK = registryBlock("euclase_block",
            AmethystBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_50874_) -> {
                        return 8;})
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> BUDDING_EUCLASE = registryBlock("budding_euclase", BuddingEuclase::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f)
            .requiresCorrectToolForDrops());
    public static final DeferredHolder<Block, Block> EUCLASE_CLUSTER = registryBlock("euclase_cluster", props -> new AmethystClusterBlock(7, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152632_) -> {
        return 5;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> LARGE_EUCLASE_BUD = registryBlock("large_euclase_bud", props -> new AmethystClusterBlock(5, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152629_) -> {
        return 4;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> MEDIUM_EUCLASE_BUD = registryBlock("medium_euclase_bud", props -> new AmethystClusterBlock(4, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152617_) -> {
        return 2;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> SMALL_EUCLASE_BUD = registryBlock("small_euclase_bud", props -> new AmethystClusterBlock(3, 4, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_187409_) -> {
        return 1;
    }).pushReaction(PushReaction.DESTROY));

    public static final DeferredHolder<Block, Block> ALEXANDRITE_BLOCK = registryBlock("alexandrite_block",
            AmethystBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_50874_) -> {
                        return 8;})
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> BUDDING_ALEXANDRITE = registryBlock("budding_alexandrite", BuddingAlexandrite::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f)
            .requiresCorrectToolForDrops());
    public static final DeferredHolder<Block, Block> ALEXANDRITE_CLUSTER = registryBlock("alexandrite_cluster", props -> new AmethystClusterBlock(7, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152632_) -> {
        return 5;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> LARGE_ALEXANDRITE_BUD = registryBlock("large_alexandrite_bud", props -> new AmethystClusterBlock(5, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152629_) -> {
        return 4;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> MEDIUM_ALEXANDRITE_BUD = registryBlock("medium_alexandrite_bud", props -> new AmethystClusterBlock(4, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152617_) -> {
        return 2;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> SMALL_ALEXANDRITE_BUD = registryBlock("small_alexandrite_bud", props -> new AmethystClusterBlock(3, 4, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_187409_) -> {
        return 1;
    }).pushReaction(PushReaction.DESTROY));

    public static final DeferredHolder<Block, Block> PHOSPHOPHYLITE_BLOCK = registryBlock("phosphophylite_block",
            AmethystBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_50874_) -> {
                        return 8;})
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> BUDDING_PHOSPHOPHYLITE = registryBlock("budding_phosphophylite", BuddingPhosphophylite::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f)
            .requiresCorrectToolForDrops());
    public static final DeferredHolder<Block, Block> PHOSPHOPHYLITE_CLUSTER = registryBlock("phosphophylite_cluster", props -> new AmethystClusterBlock(7, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152632_) -> {
        return 5;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> LARGE_PHOSPHOPHYLITE_BUD = registryBlock("large_phosphophylite_bud", props -> new AmethystClusterBlock(5, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152629_) -> {
        return 4;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> MEDIUM_PHOSPHOPHYLITE_BUD = registryBlock("medium_phosphophylite_bud", props -> new AmethystClusterBlock(4, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_152617_) -> {
        return 2;
    }).pushReaction(PushReaction.DESTROY));
    public static final DeferredHolder<Block, Block> SMALL_PHOSPHOPHYLITE_BUD = registryBlock("small_phosphophylite_bud", props -> new AmethystClusterBlock(3, 4, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(3f).lightLevel((p_187409_) -> {
        return 1;
    }).pushReaction(PushReaction.DESTROY));

    //鉱石
    public static final DeferredHolder<Block, Block> SALT_BLOCK = registryBlock("salt_block",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).strength(3f)
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> RAINBOW_WOOD = registryBlock("rainbow_wood",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> CHROMITE_ORE = registryBlock("chromite_ore",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> DEEPSLATE_CHROMITE_ORE = registryBlock("deepslate_chromite_ore",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops());


    public static final DeferredHolder<Block, Block> NETHERRACK_CHROMITE_ORE = registryBlock("netherrack_chromite_ore",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops());
    //ここまでで一つの鉱石
    //蛍石
    public static final DeferredHolder<Block, Block> FLUORITE_ORE = registryBlock("fluorite_ore",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(4f)
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> DEEPSLATE_FLUORITE_ORE = registryBlock("deepslate_fluorite_ore",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(4f)
                    .requiresCorrectToolForDrops());


    public static final DeferredHolder<Block, Block> NETHERRACK_FLUORITE_ORE = registryBlock("netherrack_fluorite_ore",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(4f)
                    .requiresCorrectToolForDrops());
    //ここまでで一つの鉱石
    //ミョウバン
    public static final DeferredHolder<Block, Block> ALUNITE_ORE = registryBlock("alunite_ore",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> DEEPSLATE_ALUNITE_ORE = registryBlock("deepslate_alunite_ore",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops());


    public static final DeferredHolder<Block, Block> NETHERRACK_ALUNITE_ORE = registryBlock("netherrack_alunite_ore",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops());


    //ボーキサイト
    public static final DeferredHolder<Block, Block> BAUXITE_ORE = registryBlock("bauxite_ore",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> DEEPSLATE_BAUXITE_ORE = registryBlock("deepslate_bauxite_ore",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops());


    public static final DeferredHolder<Block, Block> NETHERRACK_BAUXITE_ORE = registryBlock("netherrack_bauxite_ore",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops());


    //チタン
    public static final DeferredHolder<Block, Block> TITANIUM_ORE = registryBlock("titanium_ore",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(6f)
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> DEEPSLATE_TITANIUM_ORE = registryBlock("deepslate_titanium_ore",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(6f)
                    .requiresCorrectToolForDrops());


    public static final DeferredHolder<Block, Block> NETHERRACK_TITANIUM_ORE = registryBlock("netherrack_titanium_ore",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(6f)
                    .requiresCorrectToolForDrops());


    //マグネシウム
    public static final DeferredHolder<Block, Block> MAGUNESIUM_ORE = registryBlock("magunesium_ore",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> DEEPSLATE_MAGUNESIUM_ORE = registryBlock("deepslate_magunesium_ore",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops());


    public static final DeferredHolder<Block, Block> NETHERRACK_MAGUNESIUM_ORE = registryBlock("netherrack_magunesium_ore",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops());

    //バナジウム
    public static final DeferredHolder<Block, Block> VANADIUM_ORE = registryBlock("vanadium_ore",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> DEEPSLATE_VANADIUM_ORE = registryBlock("deepslate_vanadium_ore",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops());


    public static final DeferredHolder<Block, Block> NETHERRACK_VANADIUM_ORE = registryBlock("netherrack_vanadium_ore",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops());


    //建材・塊
    public static final DeferredHolder<Block, Block> CHROMITE_BLOCK = registryBlock("chromite_block",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(5f)
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> STAINLESS_BLOCK = registryBlock("stainless_block",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(5f)
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> FLUORITE_BLOCK = registryBlock("fluorite_block",
            TransparentBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(5f)
                    .noOcclusion().requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> ALUNITE_BLOCK = registryBlock("alunite_block",
            TransparentBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(5f)
                    .noOcclusion().requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> ALUMINUM_BLOCK = registryBlock("aluminum_block",
            TransparentBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(5f)
                    .noOcclusion().requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> TITANIUM_BLOCK = registryBlock("titanium_block",
            TransparentBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(5f)
                    .noOcclusion().requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> MAGUNESIUM_BLOCK = registryBlock("magunesium_block",
            TransparentBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(5f)
                    .noOcclusion().requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> VANADIUM_BLOCK = registryBlock("vanadium_block",
            TransparentBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(5f)
                    .noOcclusion().requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> PAMMUKALE_BLOCK = registryBlock("pammukale_block",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops());


    //特殊ブロック
    public static final DeferredHolder<Block, Block> SANITARY_BLOCK = registryBlock("sanitary_block",
            SanitaryBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(5f)
                    .requiresCorrectToolForDrops());


    //農作物
    public static final DeferredHolder<Block, Block> ASPARAGUS = BLOCKS.registerBlock("asparagus",
            AsparagusBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());

    public static final DeferredHolder<Block, Block> CABBAGE = BLOCKS.registerBlock("cabbage",
            CabbageBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());

    public static final DeferredHolder<Block, Block> RICE_BLOCK = BLOCKS.registerBlock("rice",
            RiceBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());

    public static final DeferredHolder<Block, Block> MINT_BLOCK = BLOCKS.registerBlock("mint",
            MintBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());

    public static final DeferredHolder<Block, Block> TOMATO_BLOCK = BLOCKS.registerBlock("tomato",
            TomatoBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());

    public static final DeferredHolder<Block, Block> CORN_BLOCK = BLOCKS.registerBlock("corn",
            CornBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());

    public static final DeferredHolder<Block, Block> ONION_BLOCK = BLOCKS.registerBlock("onion",
            OnionBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());

    public static final DeferredHolder<Block, Block> GINGER_BLOCK = BLOCKS.registerBlock("ginger",
            GingerBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());

    public static final DeferredHolder<Block, Block> GREEN_PEPPER_BLOCK = BLOCKS.registerBlock("green_pepper",
            GreenPepperBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());

    public static final DeferredHolder<Block, Block> PAPRIKA_BLOCK = BLOCKS.registerBlock("paprika",
            PaprikaBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());

    public static final DeferredHolder<Block, Block> EGGPLANT_BLOCK = BLOCKS.registerBlock("eggplant",
            EggplantBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());

    public static final DeferredHolder<Block, Block> WHITE_RADISH_BLOCK = BLOCKS.registerBlock("white_radish",
            WhiteRadishBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());

    public static final DeferredHolder<Block, Block> CHILI_PEPPER_BLOCK = BLOCKS.registerBlock("chili_pepper",
            ChiliPepperBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());

    public static final DeferredHolder<Block, Block> BASIL_BLOCK = BLOCKS.registerBlock("basil",
            BasilBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());

    public static final DeferredHolder<Block, Block> LOTUS_ROOT_BLOCK = BLOCKS.registerBlock("lotus_root",
            LotusRootBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());


    //鉱石の作物
    public static final DeferredHolder<Block, Block> CROP_DIAMOND = BLOCKS.registerBlock("crop_diamond",
            CropDiamond::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());

    public static final DeferredHolder<Block, Block> CROP_COAL = BLOCKS.registerBlock("crop_coal",
            CropCoal::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());

    public static final DeferredHolder<Block, Block> CROP_COPPER = BLOCKS.registerBlock("crop_copper",
            CropCopper::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());

    public static final DeferredHolder<Block, Block> CROP_REDSTONE = BLOCKS.registerBlock("crop_redstone",
            CropRedStone::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());

    public static final DeferredHolder<Block, Block> CROP_LAPIS = BLOCKS.registerBlock("crop_lapis",
            CropLapis::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());

    public static final DeferredHolder<Block, Block> CROP_IRON = BLOCKS.registerBlock("crop_iron",
            CropIron::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());

    public static final DeferredHolder<Block, Block> CROP_GOLD = BLOCKS.registerBlock("crop_gold",
            CropGold::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());

    public static final DeferredHolder<Block, Block> CROP_EMERALD = BLOCKS.registerBlock("crop_emerald",
            CropEmerald::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)
                    .strength(0.2f).noCollision().noOcclusion());


    //作業台
    public static final DeferredHolder<Block, Block> BFURNACE_BLOCK = registryBlock("bfurnace_block",
            BFurnaceBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(4f)
                    .requiresCorrectToolForDrops().noOcclusion());

    public static final DeferredHolder<Block, Block> DISTILLER_BLOCK = registryBlock("distiller_block",
            DistillerBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(4f)
                    .noOcclusion());

    public static final DeferredHolder<Block, Block> SAKE_DARU_BLOCK = registryBlock("sakedaru_block",
            SakeDaruBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(4f)
                    .noOcclusion());

    public static final DeferredHolder<Block, Block> MIXER_BLOCK = registryBlock("mixer_block",
            MixerBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(1f)
                    .noOcclusion());

    public static final DeferredHolder<Block, Block> FRYPAN_BLOCK = registryBlock("frypan_block",
            FryPanBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(1f)
                    .noOcclusion());


    //木
    //カベルネソービニヨン
    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_LOG = registryBlock("cabernet_sauvignon_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));
    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_WOOD = registryBlock("cabernet_sauvignon_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD));
    public static final DeferredHolder<Block, Block> STRIPPED_CABERNET_SAUVIGNON_LOG = registryBlock("stripped_cabernet_sauvignon_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG));
    public static final DeferredHolder<Block, Block> STRIPPED_CABERNET_SAUVIGNON_WOOD = registryBlock("stripped_cabernet_sauvignon_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD));


    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_PLANKS = registryBlock("cabernet_sauvignon_planks",
            props -> new Block(props){

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_LEAVES = registryBlock("cabernet_sauvignon_leaves",
            props -> new CabernetLeavesBlock(props){

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion());

    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_SAPLING = registryBlock("cabernet_sauvignon_sapling",
            props -> new SaplingBlock(SauviconTreeGrower.INSTANCE, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));

    //ブドウブロック
    //葉っぱにぶらさがるやつ
    public static final DeferredHolder<Block, Block> GRAPE_BLOCK = BLOCKS.registerBlock("grape_block",
            GrapeBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PROPAGULE).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));


    //ここまでで一種類の木

    //楓
    public static final DeferredHolder<Block, Block> MAPLE_LOG = registryBlock("maple_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));
    public static final DeferredHolder<Block, Block> MAPLE_WOOD = registryBlock("maple_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD));
    public static final DeferredHolder<Block, Block> STRIPPED_MAPLE_LOG = registryBlock("stripped_maple_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG));
    public static final DeferredHolder<Block, Block> STRIPPED_MAPLE_WOOD = registryBlock("stripped_maple_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD));


    public static final DeferredHolder<Block, Block> MAPLE_PLANKS = registryBlock("maple_planks",
            props -> new Block(props){

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    public static final DeferredHolder<Block, Block> MAPLE_LEAVES = registryBlock("maple_leaves",
            props -> new LeavesBlock(0.1f, props){
                @Override public com.mojang.serialization.MapCodec<? extends net.minecraft.world.level.block.LeavesBlock> codec() { throw new UnsupportedOperationException(); }
                @Override protected void spawnFallingLeavesParticle(net.minecraft.world.level.Level lvl, net.minecraft.core.BlockPos bpos, net.minecraft.util.RandomSource rnd) {}

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));

    public static final DeferredHolder<Block, Block> MAPLE_SAPLING = registryBlock("maple_sapling",
            props -> new SaplingBlock(MapleTreeGrower.INSTANCE, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));


    //シナモン
    public static final DeferredHolder<Block, Block> CINNAMON_LOG = registryBlock("cinnamon_log",
            ModFlammableRotatedPillarDropBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));
    public static final DeferredHolder<Block, Block> CINNAMON_WOOD = registryBlock("cinnamon_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD));
    public static final DeferredHolder<Block, Block> STRIPPED_CINNAMON_LOG = registryBlock("stripped_cinnamon_log",
            ModFlammableRotatedPillarDropBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG));
    public static final DeferredHolder<Block, Block> STRIPPED_CINNAMON_WOOD = registryBlock("stripped_cinnamon_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD));


    public static final DeferredHolder<Block, Block> CINNAMON_PLANKS = registryBlock("cinnamon_planks",
            props -> new Block(props){

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    public static final DeferredHolder<Block, Block> CINNAMON_LEAVES = registryBlock("cinnamon_leaves",
            props -> new LeavesBlock(0.1f, props){
                @Override public com.mojang.serialization.MapCodec<? extends net.minecraft.world.level.block.LeavesBlock> codec() { throw new UnsupportedOperationException(); }
                @Override protected void spawnFallingLeavesParticle(net.minecraft.world.level.Level lvl, net.minecraft.core.BlockPos bpos, net.minecraft.util.RandomSource rnd) {}

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));

    public static final DeferredHolder<Block, Block> CINNAMON_SAPLING = registryBlock("cinnamon_sapling",
            props -> new SaplingBlock(CinnamonTreeGrower.INSTANCE, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));


    //コーラの木
    public static final DeferredHolder<Block, Block> COLA_LOG = registryBlock("cola_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));
    public static final DeferredHolder<Block, Block> COLA_WOOD = registryBlock("cola_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD));
    public static final DeferredHolder<Block, Block> STRIPPED_COLA_LOG = registryBlock("stripped_cola_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG));
    public static final DeferredHolder<Block, Block> STRIPPED_COLA_WOOD = registryBlock("stripped_cola_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD));


    public static final DeferredHolder<Block, Block> COLA_PLANKS = registryBlock("cola_planks",
            props -> new Block(props){

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    public static final DeferredHolder<Block, Block> COLA_LEAVES = registryBlock("cola_leaves",
            props -> new LeavesBlock(0.1f, props){
                @Override public com.mojang.serialization.MapCodec<? extends net.minecraft.world.level.block.LeavesBlock> codec() { throw new UnsupportedOperationException(); }
                @Override protected void spawnFallingLeavesParticle(net.minecraft.world.level.Level lvl, net.minecraft.core.BlockPos bpos, net.minecraft.util.RandomSource rnd) {}

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));


    public static final DeferredHolder<Block, Block> COLA_SAPLING = registryBlock("cola_sapling",
            props -> new SaplingBlock(ColaTreeGrower.INSTANCE, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));

    public static final DeferredHolder<Block, Block> COLA_FRUIT = BLOCKS.registerBlock("cola_fruit",
            ColaBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COCOA).randomTicks().strength(0.2F,3.0F).sound(SoundType.WOOD).noOcclusion());


    public static final DeferredHolder<Block, Block> LEMON_LOG = registryBlock("lemon_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));
    public static final DeferredHolder<Block, Block> LEMON_WOOD = registryBlock("lemon_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD));
    public static final DeferredHolder<Block, Block> STRIPPED_LEMON_LOG = registryBlock("stripped_lemon_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG));
    public static final DeferredHolder<Block, Block> STRIPPED_LEMON_WOOD = registryBlock("stripped_lemon_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD));


    public static final DeferredHolder<Block, Block> LEMON_PLANKS = registryBlock("lemon_planks",
            props -> new Block(props){

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    public static final DeferredHolder<Block, Block> LEMON_LEAVES = registryBlock("lemon_leaves",
            props -> new LemonLeavesBlock(props){

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion());

    public static final DeferredHolder<Block, Block> LEMON_SAPLING = registryBlock("lemon_sapling",
            props -> new SaplingBlock(LemonTreeGrower.INSTANCE, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));

    //ブドウブロック
    //葉っぱにぶらさがるやつ
    public static final DeferredHolder<Block, Block> LEMON_BLOCK = BLOCKS.registerBlock("lemon_block",
            LemonBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PROPAGULE).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));


    public static final DeferredHolder<Block, Block> PLUM_LOG = registryBlock("plum_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));
    public static final DeferredHolder<Block, Block> PLUM_WOOD = registryBlock("plum_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD));
    public static final DeferredHolder<Block, Block> STRIPPED_PLUM_LOG = registryBlock("stripped_plum_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG));
    public static final DeferredHolder<Block, Block> STRIPPED_PLUM_WOOD = registryBlock("stripped_plum_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD));


    public static final DeferredHolder<Block, Block> PLUM_PLANKS = registryBlock("plum_planks",
            props -> new Block(props){

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    public static final DeferredHolder<Block, Block> PLUM_LEAVES = registryBlock("plum_leaves",
            props -> new PlumLeavesBlock(props){

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion());

    public static final DeferredHolder<Block, Block> PLUM_SAPLING = registryBlock("plum_sapling",
            props -> new SaplingBlock(PlumTreeGrower.INSTANCE, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));

    //ブドウブロック
    //葉っぱにぶらさがるやつ
    public static final DeferredHolder<Block, Block> PLUM_BLOCK = BLOCKS.registerBlock("plum_block",
            PlumBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PROPAGULE).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));


    public static final DeferredHolder<Block, Block> CHERRY_LOG = registryBlock("cherry_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));
    public static final DeferredHolder<Block, Block> CHERRY_WOOD = registryBlock("cherry_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD));
    public static final DeferredHolder<Block, Block> STRIPPED_CHERRY_LOG = registryBlock("stripped_cherry_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG));
    public static final DeferredHolder<Block, Block> STRIPPED_CHERRY_WOOD = registryBlock("stripped_cherry_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD));


    public static final DeferredHolder<Block, Block> CHERRY_PLANKS = registryBlock("cherry_planks",
            props -> new Block(props){

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    public static final DeferredHolder<Block, Block> CHERRY_LEAVES = registryBlock("cherry_leaves",
            props -> new CheryLeavesBlock(props){

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion());

    public static final DeferredHolder<Block, Block> CHERRY_SAPLING = registryBlock("cherry_sapling",
            props -> new SaplingBlock(CherryTreeGrower.INSTANCE, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));

    //ブドウブロック
    //葉っぱにぶらさがるやつ
    public static final DeferredHolder<Block, Block> CHERRY_BLOCK = BLOCKS.registerBlock("cherry_block",
            CherryBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PROPAGULE).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));


    public static final DeferredHolder<Block, Block> BANANA_LOG = registryBlock("banana_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));
    public static final DeferredHolder<Block, Block> BANANA_WOOD = registryBlock("banana_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD));
    public static final DeferredHolder<Block, Block> STRIPPED_BANANA_LOG = registryBlock("stripped_banana_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG));
    public static final DeferredHolder<Block, Block> STRIPPED_BANANA_WOOD = registryBlock("stripped_banana_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD));


    public static final DeferredHolder<Block, Block> BANANA_PLANKS = registryBlock("banana_planks",
            props -> new Block(props){

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    public static final DeferredHolder<Block, Block> BANANA_LEAVES = registryBlock("banana_leaves",
            props -> new BananaLeavesBlock(props){

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion());

    public static final DeferredHolder<Block, Block> BANANA_SAPLING = registryBlock("banana_sapling",
            props -> new SaplingBlock(BananaTreeGrower.INSTANCE, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));

    //ブドウブロック
    //葉っぱにぶらさがるやつ
    public static final DeferredHolder<Block, Block> BANANA_BLOCK = BLOCKS.registerBlock("banana_block",
            BananaBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PROPAGULE).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));


    public static final DeferredHolder<Block, Block> PEACH_LOG = registryBlock("peach_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));
    public static final DeferredHolder<Block, Block> PEACH_WOOD = registryBlock("peach_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD));
    public static final DeferredHolder<Block, Block> STRIPPED_PEACH_LOG = registryBlock("stripped_peach_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG));
    public static final DeferredHolder<Block, Block> STRIPPED_PEACH_WOOD = registryBlock("stripped_peach_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD));


    public static final DeferredHolder<Block, Block> PEACH_PLANKS = registryBlock("peach_planks",
            props -> new Block(props){

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    public static final DeferredHolder<Block, Block> PEACH_LEAVES = registryBlock("peach_leaves",
            props -> new PeachLeavesBlock(props){

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion());

    public static final DeferredHolder<Block, Block> PEACH_SAPLING = registryBlock("peach_sapling",
            props -> new SaplingBlock(PeachTreeGrower.INSTANCE, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));

    //ブドウブロック
    //葉っぱにぶらさがるやつ
    public static final DeferredHolder<Block, Block> PEACH_BLOCK = BLOCKS.registerBlock("peach_block",
            PeachBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PROPAGULE).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));


    public static final DeferredHolder<Block, Block> COCONUT_LOG = registryBlock("coconut_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));
    public static final DeferredHolder<Block, Block> COCONUT_WOOD = registryBlock("coconut_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD));
    public static final DeferredHolder<Block, Block> STRIPPED_COCONUT_LOG = registryBlock("stripped_coconut_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG));
    public static final DeferredHolder<Block, Block> STRIPPED_COCONUT_WOOD = registryBlock("stripped_coconut_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD));


    public static final DeferredHolder<Block, Block> COCONUT_PLANKS = registryBlock("coconut_planks",
            props -> new Block(props){

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    public static final DeferredHolder<Block, Block> COCONUT_LEAVES = registryBlock("coconut_leaves",
            props -> new CoconutLeavesBlock(props){

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion());

    public static final DeferredHolder<Block, Block> COCONUT_SAPLING = registryBlock("coconut_sapling",
            props -> new SaplingBlock(CoconutTreeGrower.INSTANCE, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));

    //ブドウブロック
    //葉っぱにぶらさがるやつ
    public static final DeferredHolder<Block, Block> COCONUT_BLOCK = BLOCKS.registerBlock("coconut_block",
            CoconutBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PROPAGULE).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));


    public static final DeferredHolder<Block, Block> ALMOND_LOG = registryBlock("almond_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));
    public static final DeferredHolder<Block, Block> ALMOND_WOOD = registryBlock("almond_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD));
    public static final DeferredHolder<Block, Block> STRIPPED_ALMOND_LOG = registryBlock("stripped_almond_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG));
    public static final DeferredHolder<Block, Block> STRIPPED_ALMOND_WOOD = registryBlock("stripped_almond_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD));


    public static final DeferredHolder<Block, Block> ALMOND_PLANKS = registryBlock("almond_planks",
            props -> new Block(props){

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    public static final DeferredHolder<Block, Block> ALMOND_LEAVES = registryBlock("almond_leaves",
            props -> new LeavesBlock(0.1f, props){
                @Override public com.mojang.serialization.MapCodec<? extends net.minecraft.world.level.block.LeavesBlock> codec() { throw new UnsupportedOperationException(); }
                @Override protected void spawnFallingLeavesParticle(net.minecraft.world.level.Level lvl, net.minecraft.core.BlockPos bpos, net.minecraft.util.RandomSource rnd) {}

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));


    public static final DeferredHolder<Block, Block> ALMOND_SAPLING = registryBlock("almond_sapling",
            props -> new SaplingBlock(AlmondTreeGrower.INSTANCE, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));

    public static final DeferredHolder<Block, Block> ALMOND_FRUIT = BLOCKS.registerBlock("almond_fruit",
            AlmondBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COCOA).randomTicks().strength(0.2F,3.0F).sound(SoundType.WOOD).noOcclusion());


    public static final DeferredHolder<Block, Block> DURIAN_LOG = registryBlock("durian_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));
    public static final DeferredHolder<Block, Block> DURIAN_WOOD = registryBlock("durian_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD));
    public static final DeferredHolder<Block, Block> STRIPPED_DURIAN_LOG = registryBlock("stripped_durian_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG));
    public static final DeferredHolder<Block, Block> STRIPPED_DURIAN_WOOD = registryBlock("stripped_durian_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD));


    public static final DeferredHolder<Block, Block> DURIAN_PLANKS = registryBlock("durian_planks",
            props -> new Block(props){

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    public static final DeferredHolder<Block, Block> DURIAN_LEAVES = registryBlock("durian_leaves",
            props -> new LeavesBlock(0.1f, props){
                @Override public com.mojang.serialization.MapCodec<? extends net.minecraft.world.level.block.LeavesBlock> codec() { throw new UnsupportedOperationException(); }
                @Override protected void spawnFallingLeavesParticle(net.minecraft.world.level.Level lvl, net.minecraft.core.BlockPos bpos, net.minecraft.util.RandomSource rnd) {}

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));


    public static final DeferredHolder<Block, Block> DURIAN_SAPLING = registryBlock("durian_sapling",
            props -> new SaplingBlock(DurianTreeGrower.INSTANCE, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));

    public static final DeferredHolder<Block, Block> DURIAN_FRUIT = BLOCKS.registerBlock("durian_fruit",
            DurianBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COCOA).randomTicks().strength(0.2F,3.0F).sound(SoundType.WOOD).noOcclusion());


    public static final DeferredHolder<Block, Block> COFFEE_LOG = registryBlock("coffee_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));
    public static final DeferredHolder<Block, Block> COFFEE_WOOD = registryBlock("coffee_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD));
    public static final DeferredHolder<Block, Block> STRIPPED_COFFEE_LOG = registryBlock("stripped_coffee_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG));
    public static final DeferredHolder<Block, Block> STRIPPED_COFFEE_WOOD = registryBlock("stripped_coffee_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD));


    public static final DeferredHolder<Block, Block> COFFEE_PLANKS = registryBlock("coffee_planks",
            props -> new Block(props){

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    public static final DeferredHolder<Block, Block> COFFEE_LEAVES = registryBlock("coffee_leaves",
            props -> new LeavesBlock(0.1f, props){
                @Override public com.mojang.serialization.MapCodec<? extends net.minecraft.world.level.block.LeavesBlock> codec() { throw new UnsupportedOperationException(); }
                @Override protected void spawnFallingLeavesParticle(net.minecraft.world.level.Level lvl, net.minecraft.core.BlockPos bpos, net.minecraft.util.RandomSource rnd) {}

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));


    public static final DeferredHolder<Block, Block> COFFEE_SAPLING = registryBlock("coffee_sapling",
            props -> new SaplingBlock(CoffeeTreeGrower.INSTANCE, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));

    public static final DeferredHolder<Block, Block> COFFEE_FRUIT = BLOCKS.registerBlock("coffee_fruit",
            CoffeeBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COCOA).randomTicks().strength(0.2F,3.0F).sound(SoundType.WOOD).noOcclusion());



    public static final DeferredHolder<Block, Block> KIWI_LOG = registryBlock("kiwi_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));
    public static final DeferredHolder<Block, Block> KIWI_WOOD = registryBlock("kiwi_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD));
    public static final DeferredHolder<Block, Block> STRIPPED_KIWI_LOG = registryBlock("stripped_kiwi_log",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG));
    public static final DeferredHolder<Block, Block> STRIPPED_KIWI_WOOD = registryBlock("stripped_kiwi_wood",
            ModFlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD));


    public static final DeferredHolder<Block, Block> KIWI_PLANKS = registryBlock("kiwi_planks",
            props -> new Block(props){

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    public static final DeferredHolder<Block, Block> KIWI_LEAVES = registryBlock("kiwi_leaves",
            props -> new KiwiLeavesBlock(props){

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
            },
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion());

    public static final DeferredHolder<Block, Block> KIWI_SAPLING = registryBlock("kiwi_sapling",
            props -> new SaplingBlock(KiwiTreeGrower.INSTANCE, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));

    //ブドウブロック
    //葉っぱにぶらさがるやつ
    public static final DeferredHolder<Block, Block> KIWI_BLOCK = BLOCKS.registerBlock("kiwi_block",
            KiwiBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PROPAGULE).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));


    public static final DeferredHolder<Block, Block> TEA_BUSH = registryBlock("tea_bush",
            TeaBushBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA).randomTicks().strength(0.2F,3.0F).sound(SoundType.WOOD).noOcclusion());

    public static final DeferredHolder<Block, Block> OLIVE_BUSH = registryBlock("olive_bush",
            OliveBushBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA).randomTicks().strength(0.2F,3.0F).sound(SoundType.WOOD).noOcclusion());

    public static final DeferredHolder<Block, Block> BLUE_BERRY_BUSH = registryBlock("blue_berry_bush",
            BlueBerryBushBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA).randomTicks().strength(0.2F,3.0F).sound(SoundType.WOOD).noOcclusion());

    public static final DeferredHolder<Block, Block> HOP_BUSH = registryBlock("hop_bush",
            HopBushBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA).randomTicks().strength(0.2F,3.0F).sound(SoundType.WOOD).noOcclusion());

    public static final DeferredHolder<Block, Block> PEPPER_BUSH = registryBlock("pepper_bush",
            PepperBushBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA).randomTicks().strength(0.2F,3.0F).sound(SoundType.WOOD).noOcclusion());


    //ハーフブロック、フェンス等
    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_STAIRS = registryBlock("cabernet_sauvignon_stairs",
            props -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_SLAB = registryBlock("cabernet_sauvignon_slab",
            SlabBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_FENCE = registryBlock("cabernet_sauvignon_fence",
            FenceBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_FENCE_GATE = registryBlock("cabernet_sauvignon_fence_gate",
            props -> new FenceGateBlock(WoodType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE).strength(2.0F,3.0F));
    //public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_BUTTON = registryBlock("cabernet_sauvignon_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollision()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                ,  BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_PRESSURE_PLATE = registryBlock("cabernet_sauvignon_pressure_plate",
            props -> new PressurePlateBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE).noCollision().strength(0.5F));
    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_DOOR = registryBlock("cabernet_sauvignon_door",
            props -> new DoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never));
    public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_TRAPDOOR = registryBlock("cabernet_sauvignon_trapdoor",
            props -> new TrapDoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never));

    public static final DeferredHolder<Block, Block> MAPLE_STAIRS = registryBlock("maple_stairs",
            props -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredHolder<Block, Block> MAPLE_SLAB = registryBlock("maple_slab",
            SlabBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> MAPLE_FENCE = registryBlock("maple_fence",
            FenceBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> MAPLE_FENCE_GATE = registryBlock("maple_fence_gate",
            props -> new FenceGateBlock(WoodType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F));
    //public static final DeferredHolder<Block, Block> MAPLE_BUTTON = registryBlock("maple_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollision()
    //                .strength(0.5F).sound(SoundType.OAK_PLANKS).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                ,  BlockSetType.OAK,30, true));

    public static final DeferredHolder<Block, Block> MAPLE_PRESSURE_PLATE = registryBlock("maple_pressure_plate",
            props -> new PressurePlateBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollision().strength(0.5F));
    public static final DeferredHolder<Block, Block> MAPLE_DOOR = registryBlock("maple_door",
            props -> new DoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never));
    public static final DeferredHolder<Block, Block> MAPLE_TRAPDOOR = registryBlock("maple_trapdoor",
            props -> new TrapDoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never));

    public static final DeferredHolder<Block, Block> CINNAMON_STAIRS = registryBlock("cinnamon_stairs",
            props -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredHolder<Block, Block> CINNAMON_SLAB = registryBlock("cinnamon_slab",
            SlabBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> CINNAMON_FENCE = registryBlock("cinnamon_fence",
            FenceBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> CINNAMON_FENCE_GATE = registryBlock("cinnamon_fence_gate",
            props -> new FenceGateBlock(WoodType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F));
    //public static final DeferredHolder<Block, Block> CINNAMON_BUTTON = registryBlock("cinnamon_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollision()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                ,  BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> CINNAMON_PRESSURE_PLATE = registryBlock("cinnamon_pressure_plate",
            props -> new PressurePlateBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollision().strength(0.5F));
    public static final DeferredHolder<Block, Block> CINNAMON_DOOR = registryBlock("cinnamon_door",
            props -> new DoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never));
    public static final DeferredHolder<Block, Block> CINNAMON_TRAPDOOR = registryBlock("cinnamon_trapdoor",
            props -> new TrapDoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never));

    public static final DeferredHolder<Block, Block> COLA_STAIRS = registryBlock("cola_stairs",
            props -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredHolder<Block, Block> COLA_SLAB = registryBlock("cola_slab",
            SlabBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> COLA_FENCE = registryBlock("cola_fence",
            FenceBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> COLA_FENCE_GATE = registryBlock("cola_fence_gate",
            props -> new FenceGateBlock(WoodType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F));
    //public static final DeferredHolder<Block, Block> COLA_BUTTON = registryBlock("cola_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollision()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                ,  BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> COLA_PRESSURE_PLATE = registryBlock("cola_pressure_plate",
            props -> new PressurePlateBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollision().strength(0.5F).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> COLA_DOOR = registryBlock("cola_door",
            props -> new DoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never));
    public static final DeferredHolder<Block, Block> COLA_TRAPDOOR = registryBlock("cola_trapdoor",
            props -> new TrapDoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never));

    public static final DeferredHolder<Block, Block> LEMON_STAIRS = registryBlock("lemon_stairs",
            props -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredHolder<Block, Block> LEMON_SLAB = registryBlock("lemon_slab",
            SlabBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> LEMON_FENCE = registryBlock("lemon_fence",
            FenceBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> LEMON_FENCE_GATE = registryBlock("lemon_fence_gate",
            props -> new FenceGateBlock(WoodType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F));
    //public static final DeferredHolder<Block, Block> LEMON_BUTTON = registryBlock("lemon_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollision()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                , BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> LEMON_PRESSURE_PLATE = registryBlock("lemon_pressure_plate",
            props -> new PressurePlateBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollision().strength(0.5F).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> LEMON_DOOR = registryBlock("lemon_door",
            props -> new DoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never));
    public static final DeferredHolder<Block, Block> LEMON_TRAPDOOR = registryBlock("lemon_trapdoor",
            props -> new TrapDoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never));


    public static final DeferredHolder<Block, Block> PLUM_STAIRS = registryBlock("plum_stairs",
            props -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredHolder<Block, Block> PLUM_SLAB = registryBlock("plum_slab",
            SlabBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> PLUM_FENCE = registryBlock("plum_fence",
            FenceBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> PLUM_FENCE_GATE = registryBlock("plum_fence_gate",
            props -> new FenceGateBlock(WoodType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F));
    //public static final DeferredHolder<Block, Block> PLUM_BUTTON = registryBlock("plum_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollision()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                , BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> PLUM_PRESSURE_PLATE = registryBlock("plum_pressure_plate",
            props -> new PressurePlateBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollision().strength(0.5F).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> PLUM_DOOR = registryBlock("plum_door",
            props -> new DoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never));
    public static final DeferredHolder<Block, Block> PLUM_TRAPDOOR = registryBlock("plum_trapdoor",
            props -> new TrapDoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never));


    public static final DeferredHolder<Block, Block> CHERRY_STAIRS = registryBlock("cherry_stairs",
            props -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredHolder<Block, Block> CHERRY_SLAB = registryBlock("cherry_slab",
            SlabBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> CHERRY_FENCE = registryBlock("cherry_fence",
            FenceBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> CHERRY_FENCE_GATE = registryBlock("cherry_fence_gate",
            props -> new FenceGateBlock(WoodType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F));
    //public static final DeferredHolder<Block, Block> CHERRY_BUTTON = registryBlock("cherry_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollision()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                , BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> CHERRY_PRESSURE_PLATE = registryBlock("cherry_pressure_plate",
            props -> new PressurePlateBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollision().strength(0.5F).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> CHERRY_DOOR = registryBlock("cherry_door",
            props -> new DoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never));
    public static final DeferredHolder<Block, Block> CHERRY_TRAPDOOR = registryBlock("cherry_trapdoor",
            props -> new TrapDoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never));


    public static final DeferredHolder<Block, Block> BANANA_STAIRS = registryBlock("banana_stairs",
            props -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredHolder<Block, Block> BANANA_SLAB = registryBlock("banana_slab",
            SlabBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> BANANA_FENCE = registryBlock("banana_fence",
            FenceBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> BANANA_FENCE_GATE = registryBlock("banana_fence_gate",
            props -> new FenceGateBlock(WoodType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F));
    //public static final DeferredHolder<Block, Block> BANANA_BUTTON = registryBlock("banana_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollision()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                , BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> BANANA_PRESSURE_PLATE = registryBlock("banana_pressure_plate",
            props -> new PressurePlateBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollision().strength(0.5F).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> BANANA_DOOR = registryBlock("banana_door",
            props -> new DoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never));
    public static final DeferredHolder<Block, Block> BANANA_TRAPDOOR = registryBlock("banana_trapdoor",
            props -> new TrapDoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never));


    public static final DeferredHolder<Block, Block> COCONUT_STAIRS = registryBlock("coconut_stairs",
            props -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredHolder<Block, Block> COCONUT_SLAB = registryBlock("coconut_slab",
            SlabBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> COCONUT_FENCE = registryBlock("coconut_fence",
            FenceBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> COCONUT_FENCE_GATE = registryBlock("coconut_fence_gate",
            props -> new FenceGateBlock(WoodType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F));
    //public static final DeferredHolder<Block, Block> COCONUT_BUTTON = registryBlock("coconut_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollision()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                , BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> COCONUT_PRESSURE_PLATE = registryBlock("coconut_pressure_plate",
            props -> new PressurePlateBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollision().strength(0.5F).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> COCONUT_DOOR = registryBlock("coconut_door",
            props -> new DoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never));
    public static final DeferredHolder<Block, Block> COCONUT_TRAPDOOR = registryBlock("coconut_trapdoor",
            props -> new TrapDoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never));


    public static final DeferredHolder<Block, Block> PEACH_STAIRS = registryBlock("peach_stairs",
            props -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredHolder<Block, Block> PEACH_SLAB = registryBlock("peach_slab",
            SlabBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> PEACH_FENCE = registryBlock("peach_fence",
            FenceBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> PEACH_FENCE_GATE = registryBlock("peach_fence_gate",
            props -> new FenceGateBlock(WoodType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F));
    //public static final DeferredHolder<Block, Block> PEACH_BUTTON = registryBlock("peach_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollision()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                , BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> PEACH_PRESSURE_PLATE = registryBlock("peach_pressure_plate",
            props -> new PressurePlateBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollision().strength(0.5F).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> PEACH_DOOR = registryBlock("peach_door",
            props -> new DoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never));
    public static final DeferredHolder<Block, Block> PEACH_TRAPDOOR = registryBlock("peach_trapdoor",
            props -> new TrapDoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never));



    public static final DeferredHolder<Block, Block> ALMOND_STAIRS = registryBlock("almond_stairs",
            props -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredHolder<Block, Block> ALMOND_SLAB = registryBlock("almond_slab",
            SlabBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> ALMOND_FENCE = registryBlock("almond_fence",
            FenceBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> ALMOND_FENCE_GATE = registryBlock("almond_fence_gate",
            props -> new FenceGateBlock(WoodType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F));
    //public static final DeferredHolder<Block, Block> ALMOND_BUTTON = registryBlock("almond_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollision()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                , BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> ALMOND_PRESSURE_PLATE = registryBlock("almond_pressure_plate",
            props -> new PressurePlateBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollision().strength(0.5F).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> ALMOND_DOOR = registryBlock("almond_door",
            props -> new DoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never));
    public static final DeferredHolder<Block, Block> ALMOND_TRAPDOOR = registryBlock("almond_trapdoor",
            props -> new TrapDoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never));


    public static final DeferredHolder<Block, Block> DURIAN_STAIRS = registryBlock("durian_stairs",
            props -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredHolder<Block, Block> DURIAN_SLAB = registryBlock("durian_slab",
            SlabBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> DURIAN_FENCE = registryBlock("durian_fence",
            FenceBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> DURIAN_FENCE_GATE = registryBlock("durian_fence_gate",
            props -> new FenceGateBlock(WoodType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F));
    //public static final DeferredHolder<Block, Block> DURIAN_BUTTON = registryBlock("durian_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollision()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                , BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> DURIAN_PRESSURE_PLATE = registryBlock("durian_pressure_plate",
            props -> new PressurePlateBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollision().strength(0.5F).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> DURIAN_DOOR = registryBlock("durian_door",
            props -> new DoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never));
    public static final DeferredHolder<Block, Block> DURIAN_TRAPDOOR = registryBlock("durian_trapdoor",
            props -> new TrapDoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never));

    public static final DeferredHolder<Block, Block> COFFEE_STAIRS = registryBlock("coffee_stairs",
            props -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredHolder<Block, Block> COFFEE_SLAB = registryBlock("coffee_slab",
            SlabBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> COFFEE_FENCE = registryBlock("coffee_fence",
            FenceBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> COFFEE_FENCE_GATE = registryBlock("coffee_fence_gate",
            props -> new FenceGateBlock(WoodType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F,3.0F));
    //public static final DeferredHolder<Block, Block> DURIAN_BUTTON = registryBlock("durian_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollision()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                , BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> COFFEE_PRESSURE_PLATE = registryBlock("coffee_pressure_plate",
            props -> new PressurePlateBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollision().strength(0.5F).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> COFFEE_DOOR = registryBlock("coffee_door",
            props -> new DoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never));
    public static final DeferredHolder<Block, Block> COFFEE_TRAPDOOR = registryBlock("coffee_trapdoor",
            props -> new TrapDoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never));


    public static final DeferredHolder<Block, Block> RAW_COFFEE_BLOCK = registryBlock("raw_coffee_block",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> COFFEE_BLOCK = registryBlock("coffee_block",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> MUSK_COFFEE_BLOCK = registryBlock("musk_coffee_block",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f)
                    .requiresCorrectToolForDrops());

    public static final DeferredHolder<Block, Block> KIWI_STAIRS = registryBlock("kiwi_stairs",
            props -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredHolder<Block, Block> KIWI_SLAB = registryBlock("kiwi_slab",
            SlabBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> KIWI_FENCE = registryBlock("kiwi_fence",
            FenceBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD));
    public static final DeferredHolder<Block, Block> KIWI_FENCE_GATE = registryBlock("kiwi_fence_gate",
            props -> new FenceGateBlock(WoodType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE).strength(2.0F,3.0F));
    //public static final DeferredHolder<Block, Block> CABERNET_SAUVIGNON_BUTTON = registryBlock("cabernet_sauvignon_button",
    //        () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATION).noCollision()
    //                .strength(0.5F).sound(SoundType.WOOD).requiredFeatures(FeatureFlags.UPDATE_1_20)
    //                ,  BlockSetType.OAK,30, true));
    public static final DeferredHolder<Block, Block> KIWI_PRESSURE_PLATE = registryBlock("kiwi_pressure_plate",
            props -> new PressurePlateBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE).noCollision().strength(0.5F));
    public static final DeferredHolder<Block, Block> KIWI_DOOR = registryBlock("kiwi_door",
            props -> new DoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never));
    public static final DeferredHolder<Block, Block> KIWI_TRAPDOOR = registryBlock("kiwi_trapdoor",
            props -> new TrapDoorBlock(BlockSetType.OAK, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never));

    //花
    public static final DeferredHolder<Block, Block> QUEEN_OF_NIGHT = registryBlock("queen_of_night",
            TallFlowerBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SUNFLOWER));

    public static final DeferredHolder<Block, Block> ROSE = registryBlock("rose",
            props -> new FlowerBlock(MobEffects.GLOWING, 5, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));

    public static final DeferredHolder<Block, Block> CAMOMILE = registryBlock("camomile",
            props -> new FlowerBlock(MobEffects.GLOWING, 5, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));

    public static final DeferredHolder<Block, Block> THYME = registryBlock("thyme",
            props -> new FlowerBlock(MobEffects.GLOWING, 5, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));

    public static final DeferredHolder<Block, Block> OREGANO = registryBlock("oregano",
            props -> new FlowerBlock(MobEffects.GLOWING, 5, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));

    public static final DeferredHolder<Block, Block> SEED_PLAIN = registryBlock("seed_plain",
            props -> new FlowerBlock(MobEffects.GLOWING, 5, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));

    public static final DeferredHolder<Block, Block> SEED_JUNGLE = registryBlock("seed_jungle",
            props -> new FlowerBlock(MobEffects.GLOWING, 5, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));

    public static final DeferredHolder<Block, Block> SEED_TAIGA = registryBlock("seed_taiga",
            props -> new FlowerBlock(MobEffects.GLOWING, 5, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));

    public static final DeferredHolder<Block, Block> SEED_SAVANNA = registryBlock("seed_savanna",
            props -> new FlowerBlock(MobEffects.GLOWING, 5, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));


    //public static final DeferredHolder<Block, Block> CHAIR = registryBlock("chair",
    //        () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(4f)
    //                .requiresCorrectToolForDrops().noOcclusion()));


    //液体とCauldron
    public static final DeferredHolder<Block, LiquidBlock> CLEAN_WATER_BLOCK = BLOCKS.registerBlock("clean_water_block",
            props -> new LiquidBlock(ModFluids.SOURCE_CLEAN.get(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable());
    public static final DeferredHolder<Block, Block> CLEAN_WATER_CAULDRON = BLOCKS.registerBlock("clean_water_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.CLEAN_WATER),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());
    public static final DeferredHolder<Block, LiquidBlock> WHISKEY_BLOCK = BLOCKS.registerBlock("whiskey_block",
            props -> new LiquidBlock(ModFluids.SOURCE_WHISKEY.get(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable());

    public static final DeferredHolder<Block, Block> WHISKEY_CAULDRON = BLOCKS.registerBlock("whiskey_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.WHISKEY),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> ROW_WHISKEY_CAULDRON = BLOCKS.registerBlock("row_whiskey_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.CLEAN_WATER),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, LiquidBlock> MAPLE_BLOCK = BLOCKS.registerBlock("maple_block",
            props -> new LiquidBlock(ModFluids.SOURCE_MAPLE.get(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable());

    public static final DeferredHolder<Block, Block> MAPLE_CAULDRON = BLOCKS.registerBlock("maple_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.MAPLE),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, LiquidBlock> SAKE_BLOCK = BLOCKS.registerBlock("sake_block",
            props -> new LiquidBlock(ModFluids.SOURCE_SAKE.get(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable());

    public static final DeferredHolder<Block, Block> SAKE_CAULDRON = BLOCKS.registerBlock("sake_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.SAKE),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> WHITE_LIQUEUR_CAULDRON = BLOCKS.registerBlock("white_liqueur_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.WHITE_LIQUEUR),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> HABU_LIQUEUR_CAULDRON = BLOCKS.registerBlock("habu_liqueur_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.HABU_LIQUEUR),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, LiquidBlock> WINE_BLOCK = BLOCKS.registerBlock("wine_block",
            props -> new LiquidBlock(ModFluids.SOURCE_WINE.get(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable());

    public static final DeferredHolder<Block, Block> WINE_CAULDRON = BLOCKS.registerBlock("wine_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.WINE),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, LiquidBlock> WHITE_WINE_BLOCK = BLOCKS.registerBlock("white_wine_block",
            props -> new LiquidBlock(ModFluids.SOURCE_WHITE_WINE.get(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable());

    public static final DeferredHolder<Block, Block> WHITE_WINE_CAULDRON = BLOCKS.registerBlock("white_wine_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.WHITE_WINE),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> RICE_CAULDRON_ROW = BLOCKS.registerBlock("rice_cauldron_row",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.R_RICE),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> RICE_CAULDRON_PROCESSED = BLOCKS.registerBlock("rice_cauldron_processed",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.P_RICE),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());
    public static final DeferredHolder<Block, Block> J_MALT_CAULDRON = BLOCKS.registerBlock("j_malt_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.J_MALT),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> YEAST_STARTER_CAULDRON_ROW = BLOCKS.registerBlock("yeast_starter_cauldron_row",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.R_YEAST),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> YEAST_STARTER_CAULDRON_PROCESSED = BLOCKS.registerBlock("yeast_starter_cauldron_processed",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.P_YEAST),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> MASH_CAULDRON_ROW = BLOCKS.registerBlock("mash_cauldron_row",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.R_MASH),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> MASH_CAULDRON_PROCESSED = BLOCKS.registerBlock("mash_cauldron_processed",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.P_MASH),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> MASH_RICE_CAULDRON_PROCESSED = BLOCKS.registerBlock("mash_rice_cauldron_processed",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.P_MASH_RICE),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> BOILED_RICE_BLOCK = registryBlock("boiled_rice_block",
            RiceMaltBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK).noLootTable());

    public static final DeferredHolder<Block, Block> JAPANESE_MALT_P = registryBlock("japanese_malt_p",
            RiceMaltBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK).noLootTable());

    public static final DeferredHolder<Block, Block> JAPANESE_MALT_R = registryBlock("japanese_malt_r",
            RiceMaltBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK).noLootTable());

    public static final DeferredHolder<Block, LiquidBlock> MASH_BLOCK = BLOCKS.registerBlock("mash_block",
            props -> new LiquidBlock(ModFluids.SOURCE_MASH.get(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable());

    public static final DeferredHolder<Block, LiquidBlock> YEAST_BLOCK = BLOCKS.registerBlock("yeast_block",
            props -> new LiquidBlock(ModFluids.SOURCE_YEAST.get(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable());

    public static final DeferredHolder<Block, Block> GRAPE_CAULDRON = BLOCKS.registerBlock("gapre_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.GRAPE),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> GRAPE_JUICE_CAULDRON = BLOCKS.registerBlock("grape_juice_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.GRAPE_JUICE),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> WHITE_GRAPE_JUICE_CAULDRON = BLOCKS.registerBlock("white_grape_juice_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.WHITE_GRAPE_JUICE),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> APPLE_CAULDRON = BLOCKS.registerBlock("apple_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.APPLE),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> APPLE_JUICE_CAULDRON = BLOCKS.registerBlock("apple_juice_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.APPLE_JUICE),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> APPLE_LIQUEUR_CAULDRON = BLOCKS.registerBlock("apple_liqueur_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.APPLE_LIQUEUR),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> LEMON_CAULDRON = BLOCKS.registerBlock("lemon_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.LEMON),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> LEMON_JUICE_CAULDRON = BLOCKS.registerBlock("lemon_juice_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.LEMON_JUICE),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> LEMON_LIQUEUR_CAULDRON = BLOCKS.registerBlock("lemon_liqueur_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.LEMON_LIQUEUR),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> PEACH_CAULDRON = BLOCKS.registerBlock("peach_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.PEACH),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> PEACH_JUICE_CAULDRON = BLOCKS.registerBlock("peach_juice_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.PEACH_JUICE),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> PEACH_LIQUEUR_CAULDRON = BLOCKS.registerBlock("peach_liqueur_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.PEACH_LIQUEUR),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> PLUM_CAULDRON = BLOCKS.registerBlock("plum_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.PLUM),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> PLUM_JUICE_CAULDRON = BLOCKS.registerBlock("plum_juice_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.PLUM_JUICE),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> PLUM_LIQUEUR_CAULDRON = BLOCKS.registerBlock("plum_liqueur_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.PLUM_LIQUEUR),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> BANANA_CAULDRON = BLOCKS.registerBlock("banana_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.BANANA),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> BANANA_JUICE_CAULDRON = BLOCKS.registerBlock("banana_juice_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.BANANA_JUICE),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> ALMON_CAULDRON = BLOCKS.registerBlock("almond_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.ALMOND),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> ALMON_JUICE_CAULDRON = BLOCKS.registerBlock("almond_juice_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.ALMOND_JUICE),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> COCONUT_CAULDRON = BLOCKS.registerBlock("coconut_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.COCONUT),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> COCONUT_JUICE_CAULDRON = BLOCKS.registerBlock("coconut_juice_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.COCONUT_JUICE),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> COFFEE_CAULDRON = BLOCKS.registerBlock("coffee_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.COFFEE),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> MUSK_COFFEE_CAULDRON = BLOCKS.registerBlock("musk_coffee_cauldron",
            props -> new ModCauldronBlock(props, ModCauldronInteraction.MUSK_COFFEE),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noLootTable());

    public static final DeferredHolder<Block, Block> POTTERS_WHEEL = BLOCKS.registerBlock("potters_wheel",
            PottersWheelBlock::new,
            () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(2.0f)
                    .sound(SoundType.WOOD)
                    .noOcclusion());

    public static final DeferredHolder<Block, Block> POTTERY_ON_WHEEL = BLOCKS.registerBlock("pottery_on_wheel",
            PotteryOnWheelBlock::new,
            () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.CLAY)
                    .strength(0.5f)
                    .sound(SoundType.GRAVEL)
                    .noOcclusion());

    //僕が創造する世界で
    //public static final DeferredHolder<Block, Block> CREATE_TNT = registryBlock("create_tnt",
    //        () -> new CreateTnt(BlockBehaviour.Properties.ofFullCopy(Blocks.TNT).mapColor(MapColor.GRASS)));

    public static final DeferredHolder<Block, Block> BUBBLE = registryBlock("bubble",
            Bubble::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(6f).noOcclusion()
                    .requiresCorrectToolForDrops());



    private static boolean never(BlockState p_50779_, BlockGetter p_50780_, BlockPos p_50781_, EntityType<?> p_50782_) {
        return (boolean)false;
    }

    private static <T extends Block> DeferredHolder<Block, T> registryBlock(String name, Function<BlockBehaviour.Properties, T> factory, Supplier<BlockBehaviour.Properties> props) {
        DeferredHolder<Block, T> toReturn = BLOCKS.registerBlock(name, factory, props);
        registryBlockItem(name, toReturn);
        return toReturn;
    }

    private static void registryBlockItem(String name, DeferredHolder<Block, ?> block){
        ModItems.ITEMS.registerItem(name, props -> new BlockItem(block.get(), props));
    }

    public static void register (IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
