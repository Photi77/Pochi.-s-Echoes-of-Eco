package net.pochi.pochimod.world.feature.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.pochi.pochimod.world.feature.ModConfiguredFeatures;
import java.util.Optional;

public class KiwiTreeGrower {
    public static final TreeGrower INSTANCE = new TreeGrower(
        "pochimod_kiwi", 0.2F, Optional.empty(), Optional.empty(),
        Optional.of(ModConfiguredFeatures.TALL_KIWI_KEY), Optional.of(ModConfiguredFeatures.KIWI_KEY),
        Optional.empty(), Optional.empty());
}
