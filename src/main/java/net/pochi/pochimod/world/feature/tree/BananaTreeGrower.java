package net.pochi.pochimod.world.feature.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.pochi.pochimod.world.feature.ModConfiguredFeatures;
import java.util.Optional;

public class BananaTreeGrower {
    public static final TreeGrower INSTANCE = new TreeGrower(
        "pochimod_banana", 0.2F, Optional.empty(), Optional.empty(),
        Optional.of(ModConfiguredFeatures.TALL_BANANA_KEY), Optional.of(ModConfiguredFeatures.BANANA_KEY),
        Optional.empty(), Optional.empty());
}
