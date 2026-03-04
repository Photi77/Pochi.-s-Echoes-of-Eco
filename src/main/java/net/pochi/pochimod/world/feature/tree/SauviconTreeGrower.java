package net.pochi.pochimod.world.feature.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.pochi.pochimod.world.feature.ModConfiguredFeatures;
import java.util.Optional;

public class SauviconTreeGrower {
    public static final TreeGrower INSTANCE = new TreeGrower(
        "pochimod_sauvicon", 0.2F, Optional.empty(), Optional.empty(),
        Optional.of(ModConfiguredFeatures.TALL_SAUVICON_KEY), Optional.of(ModConfiguredFeatures.SAUVICON_KEY),
        Optional.empty(), Optional.empty());
}
