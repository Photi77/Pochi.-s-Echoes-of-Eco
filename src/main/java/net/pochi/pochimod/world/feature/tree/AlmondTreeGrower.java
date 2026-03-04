package net.pochi.pochimod.world.feature.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.pochi.pochimod.world.feature.ModConfiguredFeatures;
import java.util.Optional;

public class AlmondTreeGrower {
    public static final TreeGrower INSTANCE = new TreeGrower(
        "pochimod_almond", Optional.empty(), Optional.of(ModConfiguredFeatures.ALMOND_KEY), Optional.empty());
}
