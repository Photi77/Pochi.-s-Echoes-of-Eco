package net.pochi.pochimod.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.block.ModBlocks;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> SAUVICON_PLACED_KEY = createKey("cabernet_sauvignon_placed");
    public static final ResourceKey<PlacedFeature> MAPLE_PLACED_KEY = createKey("maple_placed");
    public static final ResourceKey<PlacedFeature> CINNAMON_PLACED_KEY = createKey("cinnamon_placed");
    public static final ResourceKey<PlacedFeature> COLA_PLACED_KEY = createKey("cola_placed");
    public static final ResourceKey<PlacedFeature> LEMON_PLACED_KEY = createKey("lemon_placed");
    public static final ResourceKey<PlacedFeature> PLUM_PLACED_KEY = createKey("plum_placed");
    public static final ResourceKey<PlacedFeature> CHERRY_PLACED_KEY = createKey("cherry_placed");
    public static final ResourceKey<PlacedFeature> BANANA_PLACED_KEY = createKey("banana_placed");
    public static final ResourceKey<PlacedFeature> COCONUT_PLACED_KEY = createKey("coconut_placed");
    public static final ResourceKey<PlacedFeature> PEACH_PLACED_KEY = createKey("peach_placed");
    public static final ResourceKey<PlacedFeature> ALMOND_PLACED_KEY = createKey("almond_placed");
    public static final ResourceKey<PlacedFeature> DURIAN_PLACED_KEY = createKey("durian_placed");
    public static final ResourceKey<PlacedFeature> COFFEE_PLACED_KEY = createKey("coffee_placed");

    public static final ResourceKey<PlacedFeature> KIWI_PLACED_KEY = createKey("kiwi_placed");



    public static final ResourceKey<PlacedFeature> CHROMITE_PLACED_KEY = createKey("chromite_placed");
    public static final ResourceKey<PlacedFeature> NETHER_CHROMITE_PLACED_KEY = createKey("nether_chromite_placed");
    public static final ResourceKey<PlacedFeature> FLUORITE_PLACED_KEY = createKey("fluorite_placed");
    public static final ResourceKey<PlacedFeature> NETHER_FLUORITE_PLACED_KEY = createKey("nether_fluorite_placed");
    public static final ResourceKey<PlacedFeature> ALUNITE_PLACED_KEY = createKey("alunite_placed");
    public static final ResourceKey<PlacedFeature> NETHER_ALUNITE_PLACED_KEY = createKey("nether_alunite_placed");
    public static final ResourceKey<PlacedFeature> BAUXITE_PLACED_KEY = createKey("bauxite_placed");
    public static final ResourceKey<PlacedFeature> NETHER_BAUXITE_PLACED_KEY = createKey("nether_bauxite_placed");
    public static final ResourceKey<PlacedFeature> TITANIUM_PLACED_KEY = createKey("titanium_placed");
    public static final ResourceKey<PlacedFeature> NETHER_TITANIUM_PLACED_KEY = createKey("nether_titanium_placed");
    public static final ResourceKey<PlacedFeature> MAGUNESIUM_PLACED_KEY = createKey("magunesium_placed");
    public static final ResourceKey<PlacedFeature> NETHER_MAGUNESIUM_PLACED_KEY = createKey("nether_magunesium_placed");
    public static final ResourceKey<PlacedFeature> VANADIUM_PLACED_KEY = createKey("vanadium_placed");
    public static final ResourceKey<PlacedFeature> NETHER_VANADIUM_PLACED_KEY = createKey("nether_vanadium_placed");

    public static final ResourceKey<PlacedFeature> TEA_PLACED_KEY = createKey("tea_placed");
    public static final ResourceKey<PlacedFeature> OLIVE_PLACED_KEY = createKey("olive_placed");
    public static final ResourceKey<PlacedFeature> BLUE_BERRY_PLACED_KEY = createKey("blue_berry_placed");
    public static final ResourceKey<PlacedFeature> HOP_PLACED_KEY = createKey("hop_placed");
    public static final ResourceKey<PlacedFeature> PEPPER_PLACED_KEY = createKey("pepper_placed");

    public static final ResourceKey<PlacedFeature> QUEEN_OF_NIGHT_PLACED_KEY = createKey("queen_of_night_placed");
    public static final ResourceKey<PlacedFeature> ROSE_PLACED_KEY = createKey("rose_placed");
    public static final ResourceKey<PlacedFeature> CAMOMILE_PLACED_KEY = createKey("camomile_placed");
    public static final ResourceKey<PlacedFeature> THYME_PLACED_KEY = createKey("thyme_placed");
    public static final ResourceKey<PlacedFeature> OREGANO_PLACED_KEY = createKey("oregano_placed");

    public static final ResourceKey<PlacedFeature> PLAIN_PLACED_KEY = createKey("plain_placed");

    public static final ResourceKey<PlacedFeature> JUNGLE_PLACED_KEY = createKey("jungle_placed");

    public static final ResourceKey<PlacedFeature> TAIGA_PLACED_KEY = createKey("taiga_placed");

    public static final ResourceKey<PlacedFeature> SAVANNA_PLACED_KEY = createKey("savanna_placed");


    public static final ResourceKey<PlacedFeature> BASALT_DELTA = createKey("basalt_delta");
    public static final ResourceKey<PlacedFeature> LARGE_BASALT_COLUMN = createKey("large_basalt_column");
    public static final ResourceKey<PlacedFeature> SMALL_BASALT_COLUMN = createKey("small_basalt_column");

    public static final ResourceKey<PlacedFeature> OBSIDIAN_PILLAR = createKey("obsidian_pillar");
    public static final ResourceKey<PlacedFeature> STONE_PECK = createKey("stone_peck");
    public static final ResourceKey<PlacedFeature> POINTY_ROCK = createKey("pointy_rock");
    public static final ResourceKey<PlacedFeature> LAKE_PAMUKALE_SURFACE = createKey("lake_pamukale_surface");
    public static final ResourceKey<PlacedFeature> GUYANA_LAND = createKey("guyana_land");
    public static final ResourceKey<PlacedFeature> GUYANA_LAKE = createKey("guyana_lake");
    public static final ResourceKey<PlacedFeature> STONE_PILLAR_PLACED = createKey("stone_pillar_placed");
    public static final ResourceKey<PlacedFeature> NATURAL_BRIDGE_PLACED = createKey("natural_bridge_placed");

    public static final ResourceKey<PlacedFeature> TENSHI_LAND = createKey("tenshi_land");

    public static final ResourceKey<PlacedFeature> CINNABAR_GEODE = createKey("cinnabar_geode_p");
    public static final ResourceKey<PlacedFeature> BLACK_DIAMOND_GEODE = createKey("black_diamond_geode_p");
    public static final ResourceKey<PlacedFeature> YELLOW_DIAMOND_GEODE = createKey("yellow_diamond_geode_p");
    public static final ResourceKey<PlacedFeature> MORGANITE_GEODE = createKey("morganite_geode_p");
    public static final ResourceKey<PlacedFeature> GOSHENITE_GEODE = createKey("goshenite_geode_p");
    public static final ResourceKey<PlacedFeature> RUTILE_GEODE = createKey("rutile_geode_p");
    public static final ResourceKey<PlacedFeature> JADE_GEODE = createKey("jade_geode_p");
    public static final ResourceKey<PlacedFeature> EUCLASE_GEODE = createKey("euclase_geode_p");
    public static final ResourceKey<PlacedFeature> ALEXANDRITE_GEODE = createKey("alexandrite_geode_p");
    public static final ResourceKey<PlacedFeature> PHOSPHOPHYLITE_GEODE = createKey("phosphophylite_geode_p");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> holder = configuredFeatures.getOrThrow(ModConfiguredFeatures.CALCITE_PILLAR_KEY);
        Holder<ConfiguredFeature<?, ?>> holder1 = configuredFeatures.getOrThrow(ModConfiguredFeatures.LOW_PECK_KEY);
        Holder<ConfiguredFeature<?, ?>> holder2 = configuredFeatures.getOrThrow(ModConfiguredFeatures.POINTY_ROCK_KEY);
        Holder<ConfiguredFeature<?, ?>> holder3 = configuredFeatures.getOrThrow(ModConfiguredFeatures.PAMUKALE_LAKE_KEY);
        Holder<ConfiguredFeature<?, ?>> holder4 = configuredFeatures.getOrThrow(ModConfiguredFeatures.GUYANA_LAND);
        Holder<ConfiguredFeature<?, ?>> holder5 = configuredFeatures.getOrThrow(ModConfiguredFeatures.GUYANA_LAKE);
        Holder<ConfiguredFeature<?, ?>> holder6 = configuredFeatures.getOrThrow(ModConfiguredFeatures.TENSHI_LAND);
        Holder<ConfiguredFeature<?, ?>> holder7 = configuredFeatures.getOrThrow(ModConfiguredFeatures.STONE_PILLAR_CONFIGURED);
        Holder<ConfiguredFeature<?, ?>> holder8 = configuredFeatures.getOrThrow(ModConfiguredFeatures.NATURAL_BRIDGE_CONFIGURED);
        Holder<ConfiguredFeature<?, ?>> geode1 = configuredFeatures.getOrThrow(ModConfiguredFeatures.CINNABAR_GEODE);
        Holder<ConfiguredFeature<?, ?>> geode2 = configuredFeatures.getOrThrow(ModConfiguredFeatures.BLACK_DIAMOND_GEODE);
        Holder<ConfiguredFeature<?, ?>> geode3 = configuredFeatures.getOrThrow(ModConfiguredFeatures.YELLOW_DIAMOND_GEODE);
        Holder<ConfiguredFeature<?, ?>> geode4 = configuredFeatures.getOrThrow(ModConfiguredFeatures.MORGANITE_GEODE);
        Holder<ConfiguredFeature<?, ?>> geode5 = configuredFeatures.getOrThrow(ModConfiguredFeatures.GOSHENITE_GEODE);
        Holder<ConfiguredFeature<?, ?>> geode6 = configuredFeatures.getOrThrow(ModConfiguredFeatures.RUTILE_GEODE);
        Holder<ConfiguredFeature<?, ?>> geode7 = configuredFeatures.getOrThrow(ModConfiguredFeatures.JADE_GEODE);
        Holder<ConfiguredFeature<?, ?>> geode8 = configuredFeatures.getOrThrow(ModConfiguredFeatures.EUCLASE_GEODE);
        Holder<ConfiguredFeature<?, ?>> geode9 = configuredFeatures.getOrThrow(ModConfiguredFeatures.ALEXANDRITE_GEODE);
        Holder<ConfiguredFeature<?, ?>> geode10 = configuredFeatures.getOrThrow(ModConfiguredFeatures.PHOSPHOPHYLITE_GEODE);
        //Holder<ConfiguredFeature<?, ?>> holder3 = configuredFeatures.getOrThrow(ModConfiguredFeatures.JUNGLE_PILLAR);
        PlacementUtils.register(context, OBSIDIAN_PILLAR, holder, CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        PlacementUtils.register(context, STONE_PECK, holder1, CountPlacement.of(20), InSquarePlacement.spread(), BiomeFilter.biome());
        PlacementUtils.register(context, POINTY_ROCK, holder2, CountPlacement.of(2), InSquarePlacement.spread(), BiomeFilter.biome());
        PlacementUtils.register(context, LAKE_PAMUKALE_SURFACE, holder3, CountPlacement.of(255), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
        PlacementUtils.register(context, GUYANA_LAND, holder4, CountPlacement.of(10),InSquarePlacement.spread(), BiomeFilter.biome());
        PlacementUtils.register(context, GUYANA_LAKE, holder5, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
        PlacementUtils.register(context, TENSHI_LAND, holder6, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
        PlacementUtils.register(context, STONE_PILLAR_PLACED, holder7, CountPlacement.of(1), InSquarePlacement.spread(),  BiomeFilter.biome());
        PlacementUtils.register(context, NATURAL_BRIDGE_PLACED, holder8, CountPlacement.of(8), InSquarePlacement.spread(),  BiomeFilter.biome());
        //PlacementUtils.register(context, STONE_PILLAR, holder3, CountPlacement.of(2), InSquarePlacement.spread(), BiomeFilter.biome());


        register(context,SAUVICON_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SAUVICON_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.CABERNET_SAUVIGNON_SAPLING.get()));

        register(context,MAPLE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MAPLE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),ModBlocks.MAPLE_SAPLING.get()));

        register(context,CINNAMON_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CINNAMON_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),ModBlocks.CINNAMON_SAPLING.get()));

        register(context,COLA_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.COLA_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),ModBlocks.COLA_SAPLING.get()));

        register(context,LEMON_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LEMON_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.LEMON_SAPLING.get()));

        register(context,PLUM_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PLUM_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.PLUM_SAPLING.get()));

        register(context,CHERRY_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CHERRY_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.CHERRY_SAPLING.get()));

        register(context,BANANA_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BANANA_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.BANANA_SAPLING.get()));

        register(context,COCONUT_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.COCONUT_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.COCONUT_SAPLING.get()));

        register(context,PEACH_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PEACH_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.PEACH_SAPLING.get()));

        register(context,ALMOND_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ALMOND_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.ALMOND_SAPLING.get()));

        register(context,DURIAN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DURIAN_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.DURIAN_SAPLING.get()));

        register(context,COFFEE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.COFFEE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.COFFEE_SAPLING.get()));

        register(context,KIWI_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.KIWI_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.KIWI_SAPLING.get()));


        register(context,CHROMITE_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_CHROMITE_ORE_KEY),
                commonOrePlacement(10, // VeinsPerChunk
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))));
        register(context,NETHER_CHROMITE_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_CHROMITE_ORE_KEY),
                commonOrePlacement(7, // VeinsPerChunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

        register(context,FLUORITE_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_FLUORITE_ORE_KEY),
                commonOrePlacement(4, // VeinsPerChunk
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32))));
        register(context,NETHER_FLUORITE_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_FLUORITE_ORE_KEY),
                commonOrePlacement(7, // VeinsPerChunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

        register(context,ALUNITE_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_ALUNITE_ORE_KEY),
                commonOrePlacement(20, // VeinsPerChunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(192))));
        register(context,NETHER_ALUNITE_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_ALUNITE_ORE_KEY),
                commonOrePlacement(7, // VeinsPerChunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

        register(context,BAUXITE_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_BAUXITE_ORE_KEY),
                commonOrePlacement(20, // VeinsPerChunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(192))));
        register(context,NETHER_BAUXITE_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_BAUXITE_ORE_KEY),
                commonOrePlacement(7, // VeinsPerChunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

        register(context,TITANIUM_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_TITANIUM_ORE_KEY),
                commonOrePlacement(10, // VeinsPerChunk
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))));
        register(context,NETHER_TITANIUM_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_TITANIUM_ORE_KEY),
                commonOrePlacement(7, // VeinsPerChunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

        register(context,MAGUNESIUM_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_MAGUNESIUM_ORE_KEY),
                commonOrePlacement(4, // VeinsPerChunk
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32))));
        register(context,NETHER_MAGUNESIUM_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_MAGUNESIUM_ORE_KEY),
                commonOrePlacement(7, // VeinsPerChunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

        register(context,VANADIUM_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_VANADIUM_ORE_KEY),
                commonOrePlacement(20, // VeinsPerChunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32))));
        register(context,NETHER_VANADIUM_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_VANADIUM_ORE_KEY),
                commonOrePlacement(7, // VeinsPerChunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));



        register(context,TEA_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TEA_BUSH_KEY),
                List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(context,OLIVE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OLIVE_BUSH_KEY),
                List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(context,BLUE_BERRY_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLUE_BERRY_BUSH_KEY),
                List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(context,HOP_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HOP_BUSH_KEY),
                List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(context,PEPPER_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PEPPER_BUSH_KEY),
                List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));


        register(context,QUEEN_OF_NIGHT_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.QUEEN_OF_NIGHT_KEY),
                List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(context,ROSE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ROSE_KEY),
                List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(context,CAMOMILE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CAMOMILE_KEY),
                List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(context,THYME_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.THYME_KEY),
                List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(context,OREGANO_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OREGANO_KEY),
                List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(context,PLAIN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SEED_PLAIN_KEY),
                List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(context,JUNGLE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SEED_JUNGLE_KEY),
                List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(context,TAIGA_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SEED_TAIGA_KEY),
                List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(context,SAVANNA_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SEED_SAVANNA_KEY),
                List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));


        register(context, CINNABAR_GEODE, geode1, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(30)), BiomeFilter.biome());
        register(context, BLACK_DIAMOND_GEODE, geode2, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(30)), BiomeFilter.biome());
        register(context, YELLOW_DIAMOND_GEODE, geode3, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(30)), BiomeFilter.biome());
        register(context, MORGANITE_GEODE, geode4, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(30)), BiomeFilter.biome());
        register(context, GOSHENITE_GEODE, geode5, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(30)), BiomeFilter.biome());
        register(context, RUTILE_GEODE, geode6, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(30)), BiomeFilter.biome());
        register(context, JADE_GEODE, geode7, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(30)), BiomeFilter.biome());
        register(context, EUCLASE_GEODE, geode8, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(30)), BiomeFilter.biome());
        register(context, ALEXANDRITE_GEODE, geode9, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(30)), BiomeFilter.biome());
        register(context, PHOSPHOPHYLITE_GEODE, geode10, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(30)), BiomeFilter.biome());

    }


    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
