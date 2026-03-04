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

public class ModOverworldCommonRegion extends Region {
    public ModOverworldCommonRegion(ResourceLocation name, int weight){
        super(name, RegionType.OVERWORLD,weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
       this.addModifiedVanillaOverworldBiomes(mapper, modifiedVanillaOverworldBuilder -> {
           modifiedVanillaOverworldBuilder.replaceBiome(Biomes.BADLANDS, ModBiomes.RAINBOW_WOOD_BIOME);
           modifiedVanillaOverworldBuilder.replaceBiome(Biomes.STONY_SHORE, ModBiomes.BLACKROCK_BIOME);
           modifiedVanillaOverworldBuilder.replaceBiome(Biomes.DESERT, ModBiomes.CAPPADOCIA_BIOME);
           modifiedVanillaOverworldBuilder.replaceBiome(Biomes.MEADOW, ModBiomes.PAMUKKALE_BIOME);
           modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SAVANNA_PLATEAU, ModBiomes.GUYANA_BIOME);
           modifiedVanillaOverworldBuilder.replaceBiome(Biomes.JUNGLE, ModBiomes.ZHANGJAI_BIOME);
           //modifiedVanillaOverworldBuilder.replaceBiome(Biomes.STONY_PEAKS, ModBiomes.CALDERA_BIOME);
           //modifiedVanillaOverworldBuilder.replaceBiome(Biomes.JUNGLE, ModBiomes.TENSHI_BIOME);
       });

        //addBiome(mapper,
        //        Climate.Parameter.point(0.8F),      // 温度: 高温
        //        Climate.Parameter.point(-0.9F),     // 湿度: 極乾燥
        //        Climate.Parameter.point(0.3F),      // 大陸性: やや大陸的
        //        Climate.Parameter.point(0.0F),      // 侵食度: 普通
        //        Climate.Parameter.point(0.8F),      // 深度: 高地
        //        Climate.Parameter.point(0.0F),      // 奇異度: 普通
        //        0.0F,                               // オフセット
        //        ModBiomes.CALDERA_BIOME
        //);
//
        //// 追加のバリエーション（より稀な生成）
        //addBiome(mapper,
        //        Climate.Parameter.point(0.7F),      // やや低い温度
        //        Climate.Parameter.point(-0.8F),     // 乾燥
        //        Climate.Parameter.point(0.4F),      // 大陸的
        //        Climate.Parameter.point(-0.2F),     // 侵食度
        //        Climate.Parameter.point(0.9F),      // より高地
        //        Climate.Parameter.point(0.2F),      // やや奇異
        //        0.0F,
        //        ModBiomes.CALDERA_BIOME
        //);
    }
}
