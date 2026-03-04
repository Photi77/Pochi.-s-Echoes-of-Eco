package net.pochi.pochimod.world.feature.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.pochi.pochimod.world.feature.ModConfiguredFeatures;
import java.util.Optional;

public class PlumTreeGrower {
    public static final TreeGrower INSTANCE = new TreeGrower(
        "pochimod_plum", 0.2F, Optional.empty(), Optional.empty(),
        Optional.of(ModConfiguredFeatures.TALL_PLUM_KEY), Optional.of(ModConfiguredFeatures.PLUM_KEY),
        Optional.empty(), Optional.empty());
}
