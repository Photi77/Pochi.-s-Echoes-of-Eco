package net.pochi.pochimod.world.feature;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.bus.api.IEventBus;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.world.feature.config.PointyRockConfig;
import net.pochi.pochimod.world.feature.misk.*;

public class ModFeature{
    public static final DeferredRegister<Feature<?>> FEATURE =
            DeferredRegister.create(Registries.FEATURE, PochiMod.MOD_ID);

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> CALCITE_PILLAR = FEATURE.register("calcite_pillar",
            () -> new CalciteSpikeFeature(NoneFeatureConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> HIGH_PLATFORM = FEATURE.register("high_platform",
            () -> new HighPlatformFeature(NoneFeatureConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> MTTENSHI_PLATFORM = FEATURE.register("mttenshi_platform",
            () -> new MtTenchiFeature(NoneFeatureConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, Feature<BlockStateConfiguration>> LOW_STONE_PECK = FEATURE.register("low_stone_peck",
            () -> new StoneLowPeackFeature(BlockStateConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, Feature<PointyRockConfig>> POINTY_ROCK = FEATURE.register("pointy_rock",
            () -> new PointyRockFeature(PointyRockConfig.CODEC));

    public static final DeferredHolder<Feature<?>, Feature<PamukaleLakeFeature.Configuration>> PAMUKALE_LAKE = FEATURE.register("pamukale_lake",
            () -> new PamukaleLakeFeature(PamukaleLakeFeature.Configuration.CODEC));

    public static final DeferredHolder<Feature<?>, Feature<BlockStateConfiguration>> GUYANA_LAKE = FEATURE.register("guyana_lake",
            () -> new GuyanaLakeFeature(BlockStateConfiguration.CODEC));

    // 石柱生成機能
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> STONE_PILLAR = FEATURE.register("stone_pillar",
                    () -> new StonePillarFeature(NoneFeatureConfiguration.CODEC));

    // 天然橋生成機能
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> NATURAL_BRIDGE = FEATURE.register("natural_bridge",
                    () -> new NaturalBridgeFeature(NoneFeatureConfiguration.CODEC));


    public static void register(IEventBus eventBus) {
        FEATURE.register(eventBus);
    }
}
