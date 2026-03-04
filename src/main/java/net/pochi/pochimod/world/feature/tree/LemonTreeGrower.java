package net.pochi.pochimod.world.feature.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.pochi.pochimod.world.feature.ModConfiguredFeatures;
import java.util.Optional;

public class LemonTreeGrower {
    public static final TreeGrower INSTANCE = new TreeGrower(
        "pochimod_lemon", 0.2F, Optional.empty(), Optional.empty(),
        Optional.of(ModConfiguredFeatures.TALL_LEMON_KEY), Optional.of(ModConfiguredFeatures.LEMON_KEY),
        Optional.empty(), Optional.empty());
}
