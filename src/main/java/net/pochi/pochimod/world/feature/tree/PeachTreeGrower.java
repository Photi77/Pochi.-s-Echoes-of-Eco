package net.pochi.pochimod.world.feature.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.pochi.pochimod.world.feature.ModConfiguredFeatures;
import java.util.Optional;

public class PeachTreeGrower {
    public static final TreeGrower INSTANCE = new TreeGrower(
        "pochimod_peach", 0.2F, Optional.empty(), Optional.empty(),
        Optional.of(ModConfiguredFeatures.TALL_PEACH_KEY), Optional.of(ModConfiguredFeatures.PEACH_KEY),
        Optional.empty(), Optional.empty());
}
