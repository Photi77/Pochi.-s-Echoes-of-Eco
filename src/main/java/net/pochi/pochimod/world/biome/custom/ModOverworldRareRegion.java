package net.pochi.pochimod.world.biome.custom;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.pochi.pochimod.world.biome.ModBiomes;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class ModOverworldRareRegion extends Region {
    public ModOverworldRareRegion(ResourceLocation name, int weight){
        super(name, RegionType.OVERWORLD,weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
       this.addModifiedVanillaOverworldBiomes(mapper, modifiedVanillaOverworldBuilder -> {
           modifiedVanillaOverworldBuilder.replaceBiome(Biomes.BADLANDS, ModBiomes.RAINBOW_WOOD_BIOME);
           modifiedVanillaOverworldBuilder.replaceBiome(Biomes.STONY_PEAKS, ModBiomes.SALT_FIELD_BIOME);
           modifiedVanillaOverworldBuilder.replaceBiome(Biomes.STONY_SHORE, ModBiomes.BLACKROCK_BIOME);
           modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SAVANNA, ModBiomes.CAPPADOCIA_BIOME);
           modifiedVanillaOverworldBuilder.replaceBiome(Biomes.MEADOW, ModBiomes.PAMUKKALE_BIOME);
           modifiedVanillaOverworldBuilder.replaceBiome(Biomes.LUSH_CAVES, ModBiomes.CRYSTAL_CAVE);
           modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SAVANNA_PLATEAU, ModBiomes.GUYANA_BIOME);
       });
    }
}
