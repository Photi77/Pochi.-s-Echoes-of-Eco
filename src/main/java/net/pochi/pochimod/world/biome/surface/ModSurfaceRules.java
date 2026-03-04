package net.pochi.pochimod.world.biome.surface;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.pochi.pochimod.block.ModBlocks;
import net.pochi.pochimod.world.biome.ModBiomes;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource CHROMITE = makeStateRule(ModBlocks.CHROMITE_BLOCK.get());
    private static final SurfaceRules.RuleSource RAW_CHROMITE = makeStateRule(ModBlocks.CHROMITE_ORE.get());
    private static final SurfaceRules.RuleSource SALT = makeStateRule(ModBlocks.SALT_BLOCK.get());
    private static final SurfaceRules.RuleSource RAINBOW_WOOD = makeStateRule(ModBlocks.RAINBOW_WOOD.get());
    private static final SurfaceRules.RuleSource AIR = makeStateRule(Blocks.AIR);
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final SurfaceRules.RuleSource LIGHT_BLUE_TERRACOTTA = makeStateRule(Blocks.LIGHT_BLUE_TERRACOTTA);
    private static final SurfaceRules.RuleSource CYAN_TERRACOTTA = makeStateRule(Blocks.CYAN_TERRACOTTA);
    private static final SurfaceRules.RuleSource LIGHT_GRAY_TERRACOTTA = makeStateRule(Blocks.LIGHT_GRAY_TERRACOTTA);
    private static final SurfaceRules.RuleSource TERRACOTTA = makeStateRule(Blocks.TERRACOTTA);
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);
    private static final SurfaceRules.RuleSource PODZOL = makeStateRule(Blocks.PODZOL);
    private static final SurfaceRules.RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final SurfaceRules.RuleSource GRAVEL = makeStateRule(Blocks.GRAVEL);
    private static final SurfaceRules.RuleSource SAND = makeStateRule(Blocks.SAND);
    private static final SurfaceRules.RuleSource SANDSTONE = makeStateRule(Blocks.SANDSTONE);
    private static final SurfaceRules.RuleSource SNOW_BLOCK = makeStateRule(Blocks.SNOW_BLOCK);
    private static final SurfaceRules.RuleSource POWDER_SNOW = makeStateRule(Blocks.POWDER_SNOW);
    private static final SurfaceRules.RuleSource WATER = makeStateRule(Blocks.WATER);
    private static final SurfaceRules.RuleSource LAVA = makeStateRule(Blocks.LAVA);
    private static final SurfaceRules.RuleSource MAGMA = makeStateRule(Blocks.MAGMA_BLOCK);
    private static final SurfaceRules.RuleSource OBSIDIAN = makeStateRule(Blocks.OBSIDIAN);
    private static final SurfaceRules.RuleSource TUFF = makeStateRule(Blocks.TUFF);
    private static final SurfaceRules.RuleSource SMOOTH_BASALT = makeStateRule(Blocks.SMOOTH_BASALT);
    private static final SurfaceRules.RuleSource CALCITE = makeStateRule(Blocks.CALCITE);
    private static final SurfaceRules.RuleSource DIORITE = makeStateRule(Blocks.DIORITE);

    private static final SurfaceRules.RuleSource PAMUKKALE = makeStateRule(ModBlocks.PAMMUKALE_BLOCK.get());

    private static final SurfaceRules.RuleSource BLACKSTONE = makeStateRule(Blocks.BLACKSTONE);
    private static final SurfaceRules.RuleSource MUD = makeStateRule(Blocks.MUD);

    private static final SurfaceRules.RuleSource BASALT = makeStateRule(Blocks.BASALT);
    //private static final SurfaceRules.RuleSource BLACKSTONE = makeStateRule(Blocks.BLACKSTONE);
    //private static final SurfaceRules.RuleSource TUFF = makeStateRule(Blocks.TUFF);
    private static final SurfaceRules.RuleSource ANDESITE = makeStateRule(Blocks.ANDESITE);
    //private static final SurfaceRules.RuleSource GRAVEL = makeStateRule(Blocks.GRAVEL);
    private static final SurfaceRules.RuleSource MAGMA_BLOCK = makeStateRule(Blocks.MAGMA_BLOCK);

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.ConditionSource sixBelowWater = SurfaceRules.waterStartCheck(-6, -1);
        SurfaceRules.ConditionSource isAbove62 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0);
        SurfaceRules.ConditionSource isAbove63 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0);
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);

        SurfaceRules.RuleSource mixedLushDesertSurface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(surfaceNoiseAbove(1.9D), GRAVEL),
                SAND
        );


        return SurfaceRules.sequence(
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.RAINBOW_WOOD_BIOME),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(3.7D), LAVA),
                                SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(2.6D), BLACKSTONE),
                                        SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.5D), SMOOTH_BASALT)))))
                ),

                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.SALT_FIELD_BIOME),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(3.7D), SALT),
                                SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(2.6D), SAND),
                                        SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.5D), SANDSTONE)))))
                ),


                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.BLACKROCK_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SMOOTH_BASALT)
                )),

                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.CAPPADOCIA_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SANDSTONE))
                ),

                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.PAMUKKALE_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, PAMUKKALE))
                ),

                // Default to a grass and dirt surface
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

    private static SurfaceRules.ConditionSource surfaceNoiseAbove(double p_194809_) {
        return SurfaceRules.noiseCondition(Noises.SURFACE, p_194809_ / 8.25D, Double.MAX_VALUE);
    }

    private static SurfaceRules.ConditionSource surfaceNoisePillar(double p_194809_) {
        return SurfaceRules.noiseCondition(Noises.BADLANDS_PILLAR, p_194809_ / 8.25D, Double.MAX_VALUE);
    }

    private static SurfaceRules.ConditionSource surfaceNoiseErosion(double p_194809_) {
        return SurfaceRules.noiseCondition(Noises.EROSION_LARGE, p_194809_ / 8.25D, Double.MAX_VALUE);
    }
}
