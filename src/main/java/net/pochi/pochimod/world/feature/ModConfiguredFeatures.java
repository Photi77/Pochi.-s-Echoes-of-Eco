package net.pochi.pochimod.world.feature;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.block.ModBlocks;
import net.pochi.pochimod.block.custom.fruits.*;
import net.pochi.pochimod.world.feature.config.PointyRockConfig;
import net.pochi.pochimod.world.feature.misk.PamukaleLakeFeature;
import net.pochi.pochimod.world.feature.treedecorators.AlmondDecorator;
import net.pochi.pochimod.world.feature.treedecorators.CoffeeDecorator;
import net.pochi.pochimod.world.feature.treedecorators.ColaDecorator;
import net.pochi.pochimod.world.feature.treedecorators.DurianDecorator;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModConfiguredFeatures {

    public static final Map<ResourceKey<ConfiguredFeature<?, ?>>, ConfiguredFeatureFactory> CONFIGURED_FEATURES_FACTORIES = new Reference2ObjectOpenHashMap<>();

    //test
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROCKY_STONE_BOULDER = registerKey("rocky_stone_boulder");


    public static final ResourceKey<ConfiguredFeature<?, ?>> SAUVICON_KEY = registerKey("cabernet_sauvignon");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_SAUVICON_KEY = registerKey("tall_cabernet_sauvignon");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_KEY = registerKey("maple");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CINNAMON_KEY = registerKey("cinnamon");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COLA_KEY = registerKey("cola");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEMON_KEY = registerKey("lemon");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_LEMON_KEY = registerKey("tall_lemon");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PLUM_KEY = registerKey("plum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_PLUM_KEY = registerKey("tall_plum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHERRY_KEY = registerKey("cherry");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_CHERRY_KEY = registerKey("tall_cherry");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BANANA_KEY = registerKey("banana");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_BANANA_KEY = registerKey("tall_banana");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COCONUT_KEY = registerKey("coconut");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_COCONUT_KEY = registerKey("tall_coconut");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PEACH_KEY = registerKey("peach");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_PEACH_KEY = registerKey("tall_peach");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALMOND_KEY = registerKey("almond");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DURIAN_KEY = registerKey("durian");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COFFEE_KEY = registerKey("coffee");
    public static final ResourceKey<ConfiguredFeature<?, ?>> KIWI_KEY = registerKey("kiwi");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_KIWI_KEY = registerKey("tall_kiwi");



    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_CHROMITE_ORE_KEY = registerKey("overworld_chromite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_CHROMITE_ORE_KEY = registerKey("nether_chromite_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_ALUNITE_ORE_KEY = registerKey("overworld_alunite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_ALUNITE_ORE_KEY = registerKey("nether_alunite_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_FLUORITE_ORE_KEY = registerKey("overworld_fluorite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_FLUORITE_ORE_KEY = registerKey("nether_fluorite_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_BAUXITE_ORE_KEY = registerKey("overworld_bauxite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_BAUXITE_ORE_KEY = registerKey("nether_bauxite_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TITANIUM_ORE_KEY = registerKey("overworld_titanium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_TITANIUM_ORE_KEY = registerKey("nether_titanium_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_MAGUNESIUM_ORE_KEY = registerKey("overworld_magunesium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_MAGUNESIUM_ORE_KEY = registerKey("nether_magunesium_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_VANADIUM_ORE_KEY = registerKey("overworld_vanadium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_VANADIUM_ORE_KEY = registerKey("nether_vanadium_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> TEA_BUSH_KEY = registerKey("tea_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OLIVE_BUSH_KEY = registerKey("olive_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_BERRY_BUSH_KEY = registerKey("blue_berry_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HOP_BUSH_KEY = registerKey("hop_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PEPPER_BUSH_KEY = registerKey("pepper_bush");

    public static final ResourceKey<ConfiguredFeature<?, ?>> QUEEN_OF_NIGHT_KEY = registerKey("queen_of_night");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROSE_KEY = registerKey("rose");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CAMOMILE_KEY = registerKey("camomile");
    public static final ResourceKey<ConfiguredFeature<?, ?>> THYME_KEY = registerKey("thyme");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OREGANO_KEY = registerKey("oregano");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SEED_PLAIN_KEY = registerKey("seed_plain_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SEED_JUNGLE_KEY = registerKey("seed_jungle_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SEED_TAIGA_KEY = registerKey("seed_taiga_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SEED_SAVANNA_KEY = registerKey("seed_savanna_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> CALCITE_PILLAR_KEY = registerKey("calcite_pillar");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LOW_PECK_KEY = registerKey("low_peck");
    public static final ResourceKey<ConfiguredFeature<?, ?>> POINTY_ROCK_KEY = registerKey("pointy_rock");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PAMUKALE_LAKE_KEY = registerKey("pamukale_lake");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GUYANA_LAND = registerKey("guyana_land");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TENSHI_LAND = registerKey("tenshi_land");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GUYANA_LAKE = registerKey("guyana_lake");

    //public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_PILLAR = registerKey("jungle_pillar");

    public static final ResourceKey<ConfiguredFeature<?, ?>> STONE_PILLAR_CONFIGURED = registerKey("stone_pillar_configured");

    public static final ResourceKey<ConfiguredFeature<?, ?>> NATURAL_BRIDGE_CONFIGURED = registerKey("natural_bridge_configured");

    public static final ResourceKey<ConfiguredFeature<?, ?>> CINNABAR_GEODE = registerKey("cinnabar_geode");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_DIAMOND_GEODE = registerKey("black_diamond_geode");
    public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_DIAMOND_GEODE = registerKey("yellow_diamond_geode");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MORGANITE_GEODE = registerKey("morganite_geode");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOSHENITE_GEODE = registerKey("goshenite_geode");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RUTILE_GEODE = registerKey("rutile_geode");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JADE_GEODE = registerKey("jade_geode");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EUCLASE_GEODE = registerKey("euclase_geode");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALEXANDRITE_GEODE = registerKey("alexandrite_geode");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PHOSPHOPHYLITE_GEODE = registerKey("phosphophylite_geode");




    //クローム
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_CHROMITE_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.CHROMITE_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), ModBlocks.DEEPSLATE_CHROMITE_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_CHROMITE_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.NETHERRACK), ModBlocks.NETHERRACK_CHROMITE_ORE.get().defaultBlockState())));

    //蛍石
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_FLUORITE_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.FLUORITE_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), ModBlocks.DEEPSLATE_FLUORITE_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_FLUORITE_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.NETHERRACK), ModBlocks.NETHERRACK_FLUORITE_ORE.get().defaultBlockState())));

    //ミョウバン
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_ALUNITE_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.ALUNITE_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), ModBlocks.DEEPSLATE_ALUNITE_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_ALUNITE_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.NETHERRACK), ModBlocks.NETHERRACK_ALUNITE_ORE.get().defaultBlockState())));

    //ボーキサイト
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_BAUXITE_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.BAUXITE_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), ModBlocks.DEEPSLATE_BAUXITE_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_BAUXITE_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.NETHERRACK), ModBlocks.NETHERRACK_BAUXITE_ORE.get().defaultBlockState())));

    //チタン
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_TITANIUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.TITANIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), ModBlocks.DEEPSLATE_TITANIUM_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_TITANIUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.NETHERRACK), ModBlocks.NETHERRACK_TITANIUM_ORE.get().defaultBlockState())));

    //マグネシウム
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_MAGUNESIUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.MAGUNESIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), ModBlocks.DEEPSLATE_MAGUNESIUM_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_MAGUNESIUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.NETHERRACK), ModBlocks.NETHERRACK_MAGUNESIUM_ORE.get().defaultBlockState())));

    //バナジウム
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_VANADIUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.VANADIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), ModBlocks.DEEPSLATE_VANADIUM_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_VANADIUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.NETHERRACK), ModBlocks.NETHERRACK_VANADIUM_ORE.get().defaultBlockState())));




    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        register(context, CALCITE_PILLAR_KEY, ModFeature.CALCITE_PILLAR.get(),new NoneFeatureConfiguration());
        register(context, GUYANA_LAND, ModFeature.HIGH_PLATFORM.get(),new NoneFeatureConfiguration());
        register(context, TENSHI_LAND, ModFeature.MTTENSHI_PLATFORM.get(),new NoneFeatureConfiguration());
        register(context, LOW_PECK_KEY, ModFeature.LOW_STONE_PECK.get(),new BlockStateConfiguration(Blocks.BASALT.defaultBlockState()));
        register(context, PAMUKALE_LAKE_KEY, ModFeature.PAMUKALE_LAKE.get(), new PamukaleLakeFeature.Configuration(BlockStateProvider
                .simple(Blocks.WATER.defaultBlockState()), BlockStateProvider.simple(ModBlocks.PAMMUKALE_BLOCK.get().defaultBlockState())));
        register(context, GUYANA_LAKE, ModFeature.GUYANA_LAKE.get(),new BlockStateConfiguration(Blocks.MUD.defaultBlockState()));
        register(context, STONE_PILLAR_CONFIGURED, ModFeature.STONE_PILLAR.get(),new NoneFeatureConfiguration());
        register(context, NATURAL_BRIDGE_CONFIGURED, ModFeature.NATURAL_BRIDGE.get(),new NoneFeatureConfiguration());

        register(context, POINTY_ROCK_KEY, ModFeature.POINTY_ROCK.get(), new PointyRockConfig.Builder().setBlock(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(Blocks.SANDSTONE.defaultBlockState(), 3)
                        .add(Blocks.SMOOTH_BASALT.defaultBlockState(), 2))
                )
                .setSeed(85)
                .build());

        //register(context,GUYANA_LAND,Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(nre));

        //カベルネソービニヨン
        register(context,SAUVICON_KEY ,Feature.TREE,new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.CABERNET_SAUVIGNON_LOG.get()),
                new StraightTrunkPlacer(5, 6, 3),
                BlockStateProvider.simple(ModBlocks.CABERNET_SAUVIGNON_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                        new AttachedToLeavesDecorator(0.14F, 1, 0,
                                new RandomizedIntStateProvider(BlockStateProvider.simple(ModBlocks.GRAPE_BLOCK.get().defaultBlockState().setValue(GrapeBlock.HANGING,
                                        Boolean.valueOf(true))),
                                        GrapeBlock.AGE,
                                        UniformInt.of(0, 4)), 2, List.of(Direction.DOWN)))).ignoreVines().build());

        register(context,TALL_SAUVICON_KEY ,Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.CABERNET_SAUVIGNON_LOG.get()),
                new StraightTrunkPlacer(4, 5, 2),
                BlockStateProvider.simple(ModBlocks.CABERNET_SAUVIGNON_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                        new AttachedToLeavesDecorator(0.14F, 1, 0,
                                new RandomizedIntStateProvider(BlockStateProvider.simple(ModBlocks.GRAPE_BLOCK.get().defaultBlockState().setValue(GrapeBlock.HANGING,
                                        Boolean.valueOf(true))),
                                        GrapeBlock.AGE,
                                        UniformInt.of(0, 4)), 2, List.of(Direction.DOWN)))).ignoreVines().build());


        //楓
        register(context,MAPLE_KEY ,Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.MAPLE_LOG.get()),
                new StraightTrunkPlacer(5, 6, 3),
                BlockStateProvider.simple(ModBlocks.MAPLE_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        //シナモン
        register(context,CINNAMON_KEY ,Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.CINNAMON_LOG.get()),
                new StraightTrunkPlacer(5, 6, 3),
                BlockStateProvider.simple(ModBlocks.CINNAMON_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        //コーラ
        register(context,COLA_KEY ,Feature.TREE, createColaTree().decorators(ImmutableList.of(new ColaDecorator(0.5F),
                TrunkVineDecorator.INSTANCE,
                new LeaveVineDecorator(0.25F))).ignoreVines().build());

        //lemon
        register(context,LEMON_KEY ,Feature.TREE,new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LEMON_LOG.get()),
                new StraightTrunkPlacer(5, 6, 3),
                BlockStateProvider.simple(ModBlocks.LEMON_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                        new AttachedToLeavesDecorator(0.14F, 1, 0,
                                new RandomizedIntStateProvider(BlockStateProvider.simple(ModBlocks.LEMON_BLOCK.get().defaultBlockState().setValue(LemonBlock.HANGING,
                                        Boolean.valueOf(true))),
                                        LemonBlock.AGE,
                                        UniformInt.of(0, 4)), 2, List.of(Direction.DOWN)))).ignoreVines().build());



        register(context,TALL_LEMON_KEY ,Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LEMON_LOG.get()),
                new StraightTrunkPlacer(4, 5, 2),
                BlockStateProvider.simple(ModBlocks.LEMON_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                        new AttachedToLeavesDecorator(0.14F, 1, 0,
                                new RandomizedIntStateProvider(BlockStateProvider.simple(ModBlocks.LEMON_BLOCK.get().defaultBlockState().setValue(LemonBlock.HANGING,
                                        Boolean.valueOf(true))),
                                        LemonBlock.AGE,
                                        UniformInt.of(0, 4)), 2, List.of(Direction.DOWN)))).ignoreVines().build());

        register(context,PLUM_KEY ,Feature.TREE,new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.PLUM_LOG.get()),
                new StraightTrunkPlacer(5, 6, 3),
                BlockStateProvider.simple(ModBlocks.PLUM_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                        new AttachedToLeavesDecorator(0.14F, 1, 0,
                                new RandomizedIntStateProvider(BlockStateProvider.simple(ModBlocks.PLUM_BLOCK.get().defaultBlockState().setValue(PlumBlock.HANGING,
                                        Boolean.valueOf(true))),
                                        PlumBlock.AGE,
                                        UniformInt.of(0, 4)), 2, List.of(Direction.DOWN)))).ignoreVines().build());

        register(context,TALL_PLUM_KEY ,Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.PLUM_LOG.get()),
                new StraightTrunkPlacer(4, 5, 2),
                BlockStateProvider.simple(ModBlocks.PLUM_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                        new AttachedToLeavesDecorator(0.14F, 1, 0,
                                new RandomizedIntStateProvider(BlockStateProvider.simple(ModBlocks.PLUM_BLOCK.get().defaultBlockState().setValue(PlumBlock.HANGING,
                                        Boolean.valueOf(true))),
                                        PlumBlock.AGE,
                                        UniformInt.of(0, 4)), 2, List.of(Direction.DOWN)))).ignoreVines().build());

        register(context,CHERRY_KEY ,Feature.TREE,new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.CHERRY_LOG.get()),
                new StraightTrunkPlacer(5, 6, 3),
                BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                        new AttachedToLeavesDecorator(0.14F, 1, 0,
                                new RandomizedIntStateProvider(BlockStateProvider.simple(ModBlocks.CHERRY_BLOCK.get().defaultBlockState().setValue(CherryBlock.HANGING,
                                        Boolean.valueOf(true))),
                                        CherryBlock.AGE,
                                        UniformInt.of(0, 4)), 2, List.of(Direction.DOWN)))).ignoreVines().build());

        register(context,TALL_CHERRY_KEY ,Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.CHERRY_LOG.get()),
                new StraightTrunkPlacer(4, 5, 2),
                BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                        new AttachedToLeavesDecorator(0.14F, 1, 0,
                                new RandomizedIntStateProvider(BlockStateProvider.simple(ModBlocks.CHERRY_BLOCK.get().defaultBlockState().setValue(CherryBlock.HANGING,
                                        Boolean.valueOf(true))),
                                        CherryBlock.AGE,
                                        UniformInt.of(0, 4)), 2, List.of(Direction.DOWN)))).ignoreVines().build());

        register(context,BANANA_KEY ,Feature.TREE,new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.BANANA_LOG.get()),
                new StraightTrunkPlacer(5, 6, 3),
                BlockStateProvider.simple(ModBlocks.BANANA_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                        new AttachedToLeavesDecorator(0.14F, 1, 0,
                                new RandomizedIntStateProvider(BlockStateProvider.simple(ModBlocks.BANANA_BLOCK.get().defaultBlockState().setValue(BananaBlock.HANGING,
                                        Boolean.valueOf(true))),
                                        BananaBlock.AGE,
                                        UniformInt.of(0, 4)), 2, List.of(Direction.DOWN)))).ignoreVines().build());

        register(context,TALL_BANANA_KEY ,Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.BANANA_LOG.get()),
                new StraightTrunkPlacer(4, 5, 2),
                BlockStateProvider.simple(ModBlocks.BANANA_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                        new AttachedToLeavesDecorator(0.14F, 1, 0,
                                new RandomizedIntStateProvider(BlockStateProvider.simple(ModBlocks.BANANA_BLOCK.get().defaultBlockState().setValue(BananaBlock.HANGING,
                                        Boolean.valueOf(true))),
                                        BananaBlock.AGE,
                                        UniformInt.of(0, 4)), 2, List.of(Direction.DOWN)))).ignoreVines().build());

        register(context,COCONUT_KEY ,Feature.TREE,new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.COCONUT_LOG.get()),
                new StraightTrunkPlacer(5, 6, 3),
                BlockStateProvider.simple(ModBlocks.COCONUT_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                        new AttachedToLeavesDecorator(0.14F, 1, 0,
                                new RandomizedIntStateProvider(BlockStateProvider.simple(ModBlocks.COCONUT_BLOCK.get().defaultBlockState().setValue(CoconutBlock.HANGING,
                                        Boolean.valueOf(true))),
                                        CoconutBlock.AGE,
                                        UniformInt.of(0, 4)), 2, List.of(Direction.DOWN)))).ignoreVines().build());

        register(context,TALL_COCONUT_KEY ,Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.COCONUT_LOG.get()),
                new StraightTrunkPlacer(4, 5, 2),
                BlockStateProvider.simple(ModBlocks.COCONUT_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                        new AttachedToLeavesDecorator(0.14F, 1, 0,
                                new RandomizedIntStateProvider(BlockStateProvider.simple(ModBlocks.COCONUT_BLOCK.get().defaultBlockState().setValue(CoconutBlock.HANGING,
                                        Boolean.valueOf(true))),
                                        CoconutBlock.AGE,
                                        UniformInt.of(0, 4)), 2, List.of(Direction.DOWN)))).ignoreVines().build());

        register(context,PEACH_KEY ,Feature.TREE,new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.PEACH_LOG.get()),
                new StraightTrunkPlacer(5, 6, 3),
                BlockStateProvider.simple(ModBlocks.PEACH_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                        new AttachedToLeavesDecorator(0.14F, 1, 0,
                                new RandomizedIntStateProvider(BlockStateProvider.simple(ModBlocks.PEACH_BLOCK.get().defaultBlockState().setValue(PeachBlock.HANGING,
                                        Boolean.valueOf(true))),
                                        PeachBlock.AGE,
                                        UniformInt.of(0, 4)), 2, List.of(Direction.DOWN)))).ignoreVines().build());

        register(context,TALL_PEACH_KEY ,Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.PEACH_LOG.get()),
                new StraightTrunkPlacer(4, 5, 2),
                BlockStateProvider.simple(ModBlocks.PEACH_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                        new AttachedToLeavesDecorator(0.14F, 1, 0,
                                new RandomizedIntStateProvider(BlockStateProvider.simple(ModBlocks.PEACH_BLOCK.get().defaultBlockState().setValue(PeachBlock.HANGING,
                                        Boolean.valueOf(true))),
                                        PeachBlock.AGE,
                                        UniformInt.of(0, 4)), 2, List.of(Direction.DOWN)))).ignoreVines().build());

        register(context,ALMOND_KEY,Feature.TREE, creatAlmondTree().decorators(ImmutableList.of(new AlmondDecorator(0.5F),
                TrunkVineDecorator.INSTANCE,
                new LeaveVineDecorator(0.25F))).ignoreVines().build());

        register(context,DURIAN_KEY ,Feature.TREE, createDurianTree().decorators(ImmutableList.of(new DurianDecorator(0.5F),
                TrunkVineDecorator.INSTANCE,
                new LeaveVineDecorator(0.25F))).ignoreVines().build());

        register(context,COFFEE_KEY ,Feature.TREE, createCoffeeTree().decorators(ImmutableList.of(new CoffeeDecorator(0.5F),
                TrunkVineDecorator.INSTANCE,
                new LeaveVineDecorator(0.25F))).ignoreVines().build());


        register(context,KIWI_KEY ,Feature.TREE,new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.KIWI_LOG.get()),
                new StraightTrunkPlacer(5, 6, 3),
                BlockStateProvider.simple(ModBlocks.KIWI_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                        new AttachedToLeavesDecorator(0.14F, 1, 0,
                                new RandomizedIntStateProvider(BlockStateProvider.simple(ModBlocks.KIWI_BLOCK.get().defaultBlockState().setValue(GrapeBlock.HANGING,
                                        Boolean.valueOf(true))),
                                        KiwiBlock.AGE,
                                        UniformInt.of(0, 4)), 2, List.of(Direction.DOWN)))).ignoreVines().build());

        register(context,TALL_KIWI_KEY ,Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.KIWI_LOG.get()),
                new StraightTrunkPlacer(4, 5, 2),
                BlockStateProvider.simple(ModBlocks.KIWI_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                        new AttachedToLeavesDecorator(0.14F, 1, 0,
                                new RandomizedIntStateProvider(BlockStateProvider.simple(ModBlocks.KIWI_BLOCK.get().defaultBlockState().setValue(GrapeBlock.HANGING,
                                        Boolean.valueOf(true))),
                                        KiwiBlock.AGE,
                                        UniformInt.of(0, 4)), 2, List.of(Direction.DOWN)))).ignoreVines().build());



        register(context,OVERWORLD_CHROMITE_ORE_KEY,Feature.ORE, new OreConfiguration(OVERWORLD_CHROMITE_ORES.get(),12));
        register(context,NETHER_CHROMITE_ORE_KEY,Feature.ORE, new OreConfiguration(NETHER_CHROMITE_ORES.get(), 12));
        register(context,OVERWORLD_FLUORITE_ORE_KEY,Feature.ORE, new OreConfiguration(OVERWORLD_FLUORITE_ORES.get(),12));
        register(context,NETHER_FLUORITE_ORE_KEY,Feature.ORE, new OreConfiguration(NETHER_FLUORITE_ORES.get(), 12));
        register(context,OVERWORLD_ALUNITE_ORE_KEY,Feature.ORE, new OreConfiguration(OVERWORLD_ALUNITE_ORES.get(),12));
        register(context,NETHER_ALUNITE_ORE_KEY,Feature.ORE, new OreConfiguration(NETHER_ALUNITE_ORES.get(), 12));
        register(context,OVERWORLD_BAUXITE_ORE_KEY,Feature.ORE, new OreConfiguration(OVERWORLD_BAUXITE_ORES.get(),12));
        register(context,NETHER_BAUXITE_ORE_KEY,Feature.ORE, new OreConfiguration(NETHER_BAUXITE_ORES.get(), 12));
        register(context,OVERWORLD_TITANIUM_ORE_KEY,Feature.ORE, new OreConfiguration(OVERWORLD_TITANIUM_ORES.get(),12));
        register(context,NETHER_TITANIUM_ORE_KEY,Feature.ORE, new OreConfiguration(NETHER_TITANIUM_ORES.get(), 12));
        register(context,OVERWORLD_MAGUNESIUM_ORE_KEY,Feature.ORE, new OreConfiguration(OVERWORLD_MAGUNESIUM_ORES.get(),12));
        register(context,NETHER_MAGUNESIUM_ORE_KEY,Feature.ORE, new OreConfiguration(NETHER_MAGUNESIUM_ORES.get(), 12));
        register(context,OVERWORLD_VANADIUM_ORE_KEY,Feature.ORE, new OreConfiguration(OVERWORLD_VANADIUM_ORES.get(),12));
        register(context,NETHER_VANADIUM_ORE_KEY,Feature.ORE, new OreConfiguration(NETHER_VANADIUM_ORES.get(), 12));



        register(context, QUEEN_OF_NIGHT_KEY,Feature.FLOWER,
                new RandomPatchConfiguration(64, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.QUEEN_OF_NIGHT.get())))));

        register(context, ROSE_KEY,Feature.FLOWER,
                new RandomPatchConfiguration(64, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.ROSE.get())))));

        register(context, CAMOMILE_KEY,Feature.FLOWER,
                new RandomPatchConfiguration(64, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.CAMOMILE.get())))));

        register(context, THYME_KEY,Feature.FLOWER,
                new RandomPatchConfiguration(64, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.THYME.get())))));

        register(context, OREGANO_KEY,Feature.FLOWER,
                new RandomPatchConfiguration(64, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.OREGANO.get())))));


        register(context, SEED_PLAIN_KEY,Feature.FLOWER,
                new RandomPatchConfiguration(64, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SEED_PLAIN.get())))));

        register(context, SEED_JUNGLE_KEY,Feature.FLOWER,
                new RandomPatchConfiguration(64, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SEED_JUNGLE.get())))));

        register(context, SEED_TAIGA_KEY,Feature.FLOWER,
                new RandomPatchConfiguration(64, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SEED_TAIGA.get())))));

        register(context, SEED_SAVANNA_KEY,Feature.FLOWER,
                new RandomPatchConfiguration(64, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SEED_SAVANNA.get())))));


        register(context, TEA_BUSH_KEY,Feature.FLOWER,
                new RandomPatchConfiguration(16, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.TEA_BUSH.get())))));

        register(context, OLIVE_BUSH_KEY,Feature.FLOWER,
                new RandomPatchConfiguration(16, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.OLIVE_BUSH.get())))));

        register(context, BLUE_BERRY_BUSH_KEY,Feature.FLOWER,
                new RandomPatchConfiguration(16, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BLUE_BERRY_BUSH.get())))));

        register(context, HOP_BUSH_KEY,Feature.FLOWER,
                new RandomPatchConfiguration(16, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.HOP_BUSH.get())))));

        register(context, PEPPER_BUSH_KEY,Feature.FLOWER,
                new RandomPatchConfiguration(16, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.PEPPER_BUSH.get())))));


        register(context, CINNABAR_GEODE, Feature.GEODE,
                new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(ModBlocks.CINNABAR_BLOCK.get()), BlockStateProvider.simple(ModBlocks.BUDDING_CINNABAR.get()),
                        BlockStateProvider.simple(Blocks.CALCITE), BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                        List.of(ModBlocks.SMALL_CINNABAR_BUD.get().defaultBlockState(), ModBlocks.MEDIUM_CINNABAR_BUD.get().defaultBlockState(),
                                ModBlocks.LARGE_CINNABAR_BUD.get().defaultBlockState(), ModBlocks.CINNABAR_CLUSTER.get().defaultBlockState()),
                        BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS), new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackSettings(0.95D, 2.0D, 2), 0.35D, 0.083D, true,
                        UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2), -16, 16, 0.05D, 1));

        register(context, BLACK_DIAMOND_GEODE, Feature.GEODE,
                new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(ModBlocks.BLACK_DIAMOND_BLOCK.get()), BlockStateProvider.simple(ModBlocks.BUDDING_BLACK_DIAMOND.get()),
                        BlockStateProvider.simple(Blocks.CALCITE), BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                        List.of(ModBlocks.SMALL_BLACK_DIAMOND_BUD.get().defaultBlockState(), ModBlocks.MEDIUM_BLACK_DIAMOND_BUD.get().defaultBlockState(),
                                ModBlocks.LARGE_BLACK_DIAMOND_BUD.get().defaultBlockState(), ModBlocks.BLACK_DIAMOND_CLUSTER.get().defaultBlockState()),
                        BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS), new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackSettings(0.95D, 2.0D, 2), 0.35D, 0.083D, true,
                        UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2), -16, 16, 0.05D, 1));

        register(context, YELLOW_DIAMOND_GEODE, Feature.GEODE,
                new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(ModBlocks.YELLOW_DIAMOND_BLOCK.get()), BlockStateProvider.simple(ModBlocks.BUDDING_YELLOW_DIAMOND.get()),
                        BlockStateProvider.simple(Blocks.CALCITE), BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                        List.of(ModBlocks.SMALL_YELLOW_DIAMOND_BUD.get().defaultBlockState(), ModBlocks.MEDIUM_YELLOW_DIAMOND_BUD.get().defaultBlockState(),
                                ModBlocks.LARGE_YELLOW_DIAMOND_BUD.get().defaultBlockState(), ModBlocks.YELLOW_DIAMOND_CLUSTER.get().defaultBlockState()),
                        BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS), new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackSettings(0.95D, 2.0D, 2), 0.35D, 0.083D, true,
                        UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2), -16, 16, 0.05D, 1));


        register(context, MORGANITE_GEODE, Feature.GEODE,
                new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(ModBlocks.MORGANITE_BLOCK.get()), BlockStateProvider.simple(ModBlocks.BUDDING_MORGANITE.get()),
                        BlockStateProvider.simple(Blocks.CALCITE), BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                        List.of(ModBlocks.SMALL_MORGANITE_BUD.get().defaultBlockState(), ModBlocks.MEDIUM_MORGANITE_BUD.get().defaultBlockState(),
                                ModBlocks.LARGE_MORGANITE_BUD.get().defaultBlockState(), ModBlocks.MORGANITE_CLUSTER.get().defaultBlockState()),
                        BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS), new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackSettings(0.95D, 2.0D, 2), 0.35D, 0.083D, true,
                        UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2), -16, 16, 0.05D, 1));

        register(context, GOSHENITE_GEODE, Feature.GEODE,
                new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(ModBlocks.GOSHENITE_BLOCK.get()), BlockStateProvider.simple(ModBlocks.BUDDING_GOSHENITE.get()),
                        BlockStateProvider.simple(Blocks.CALCITE), BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                        List.of(ModBlocks.SMALL_GOSHENITE_BUD.get().defaultBlockState(), ModBlocks.MEDIUM_GOSHENITE_BUD.get().defaultBlockState(),
                                ModBlocks.LARGE_GOSHENITE_BUD.get().defaultBlockState(), ModBlocks.GOSHENITE_CLUSTER.get().defaultBlockState()),
                        BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS), new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackSettings(0.95D, 2.0D, 2), 0.35D, 0.083D, true,
                        UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2), -16, 16, 0.05D, 1));

        register(context, RUTILE_GEODE, Feature.GEODE,
                new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(ModBlocks.RUTILE_BLOCK.get()), BlockStateProvider.simple(ModBlocks.BUDDING_RUTILE.get()),
                        BlockStateProvider.simple(Blocks.CALCITE), BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                        List.of(ModBlocks.SMALL_RUTILE_BUD.get().defaultBlockState(), ModBlocks.MEDIUM_RUTILE_BUD.get().defaultBlockState(),
                                ModBlocks.LARGE_RUTILE_BUD.get().defaultBlockState(), ModBlocks.RUTILE_CLUSTER.get().defaultBlockState()),
                        BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS), new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackSettings(0.95D, 2.0D, 2), 0.35D, 0.083D, true,
                        UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2), -16, 16, 0.05D, 1));

        register(context, JADE_GEODE, Feature.GEODE,
                new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(ModBlocks.JADE_BLOCK.get()), BlockStateProvider.simple(ModBlocks.BUDDING_JADE.get()),
                        BlockStateProvider.simple(Blocks.CALCITE), BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                        List.of(ModBlocks.SMALL_JADE_BUD.get().defaultBlockState(), ModBlocks.MEDIUM_JADE_BUD.get().defaultBlockState(),
                                ModBlocks.LARGE_JADE_BUD.get().defaultBlockState(), ModBlocks.JADE_CLUSTER.get().defaultBlockState()),
                        BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS), new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackSettings(0.95D, 2.0D, 2), 0.35D, 0.083D, true,
                        UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2), -16, 16, 0.05D, 1));

        register(context, EUCLASE_GEODE, Feature.GEODE,
                new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(ModBlocks.EUCLASE_BLOCK.get()), BlockStateProvider.simple(ModBlocks.BUDDING_EUCLASE.get()),
                        BlockStateProvider.simple(Blocks.CALCITE), BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                        List.of(ModBlocks.SMALL_EUCLASE_BUD.get().defaultBlockState(), ModBlocks.MEDIUM_EUCLASE_BUD.get().defaultBlockState(),
                                ModBlocks.LARGE_EUCLASE_BUD.get().defaultBlockState(), ModBlocks.EUCLASE_CLUSTER.get().defaultBlockState()),
                        BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS), new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackSettings(0.95D, 2.0D, 2), 0.35D, 0.083D, true,
                        UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2), -16, 16, 0.05D, 1));

        register(context, ALEXANDRITE_GEODE, Feature.GEODE,
                new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(ModBlocks.ALEXANDRITE_BLOCK.get()), BlockStateProvider.simple(ModBlocks.BUDDING_ALEXANDRITE.get()),
                        BlockStateProvider.simple(Blocks.CALCITE), BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                        List.of(ModBlocks.SMALL_ALEXANDRITE_BUD.get().defaultBlockState(), ModBlocks.MEDIUM_ALEXANDRITE_BUD.get().defaultBlockState(),
                                ModBlocks.LARGE_ALEXANDRITE_BUD.get().defaultBlockState(), ModBlocks.ALEXANDRITE_CLUSTER.get().defaultBlockState()),
                        BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS), new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackSettings(0.95D, 2.0D, 2), 0.35D, 0.083D, true,
                        UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2), -16, 16, 0.05D, 1));

        register(context, PHOSPHOPHYLITE_GEODE, Feature.GEODE,
                new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(ModBlocks.PHOSPHOPHYLITE_BLOCK.get()), BlockStateProvider.simple(ModBlocks.BUDDING_PHOSPHOPHYLITE.get()),
                        BlockStateProvider.simple(Blocks.CALCITE), BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                        List.of(ModBlocks.SMALL_PHOSPHOPHYLITE_BUD.get().defaultBlockState(), ModBlocks.MEDIUM_PHOSPHOPHYLITE_BUD.get().defaultBlockState(),
                                ModBlocks.LARGE_PHOSPHOPHYLITE_BUD.get().defaultBlockState(), ModBlocks.PHOSPHOPHYLITE_CLUSTER.get().defaultBlockState()),
                        BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS), new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackSettings(0.95D, 2.0D, 2), 0.35D, 0.083D, true,
                        UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2), -16, 16, 0.05D, 1));
    }

    private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(DeferredHolder<Block, Block> wBlock, DeferredHolder<Block, Block> lBlock, int pBaseHeight, int pHeightRandA, int pHeightRandB, int p_195152_) {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(wBlock.get()), new StraightTrunkPlacer(pBaseHeight, pHeightRandA, pHeightRandB), BlockStateProvider.simple(lBlock.get()), new BlobFoliagePlacer(ConstantInt.of(p_195152_), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1));
    }

    private static TreeConfiguration.TreeConfigurationBuilder createColaTree() {
        return createStraightBlobTree(ModBlocks.COLA_LOG, ModBlocks.COLA_LEAVES, 4, 8, 0, 2);
    }

    private static TreeConfiguration.TreeConfigurationBuilder creatAlmondTree() {
        return createStraightBlobTree(ModBlocks.ALMOND_LOG, ModBlocks.ALMOND_LEAVES, 4, 8, 0, 2);
    }

    private static TreeConfiguration.TreeConfigurationBuilder createDurianTree() {
        return createStraightBlobTree(ModBlocks.DURIAN_LOG, ModBlocks.DURIAN_LEAVES, 4, 8, 0, 2);
    }

    private static TreeConfiguration.TreeConfigurationBuilder createCoffeeTree() {
        return createStraightBlobTree(ModBlocks.COFFEE_LOG, ModBlocks.COFFEE_LEAVES, 4, 8, 0, 2);
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> ResourceKey<ConfiguredFeature<?, ?>> createConfiguredFeature(String id, Supplier<? extends F> feature, Function<BootstrapContext<ConfiguredFeature<?, ?>>, ? extends FC> config) {
        ResourceLocation bygID = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, id);

        ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureResourceKey = ResourceKey.create(Registries.CONFIGURED_FEATURE, bygID);

        CONFIGURED_FEATURES_FACTORIES.put(configuredFeatureResourceKey, configuredFeatureHolderGetter -> new ConfiguredFeature<>(feature.get(), config.apply(configuredFeatureHolderGetter)));

        return configuredFeatureResourceKey;
    }

    @FunctionalInterface
    public interface ConfiguredFeatureFactory {
        ConfiguredFeature<?, ?> generate(BootstrapContext<ConfiguredFeature<?, ?>> configuredFeatureHolderGetter);
    }
}
