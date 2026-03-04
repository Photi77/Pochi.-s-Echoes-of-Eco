package net.pochi.pochimod.world.biome;

import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.pochi.pochimod.world.feature.ModPlacedFeatures;

public class ModBiomesFeatures {
    public ModBiomesFeatures() {
    }

    public static void addBasaltFeatures(BiomeGenerationSettings.Builder gen) {
        gen.addFeature(GenerationStep.Decoration.RAW_GENERATION, ModPlacedFeatures.OBSIDIAN_PILLAR);
        //gen.addFeature(GenerationStep.Decoration.RAW_GENERATION, ModPlacedFeatures.SMALL_BASALT_COLUMN);
        //gen.addFeature(GenerationStep.Decoration.RAW_GENERATION, ModPlacedFeatures.BASALT_DELTA);
    }
}
