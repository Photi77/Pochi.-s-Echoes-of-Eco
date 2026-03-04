package net.pochi.pochimod.world.feature.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.pochi.pochimod.world.feature.ModConfiguredFeatures;
import java.util.Optional;

public class DurianTreeGrower {
    public static final TreeGrower INSTANCE = new TreeGrower(
        "pochimod_durian", Optional.empty(), Optional.of(ModConfiguredFeatures.DURIAN_KEY), Optional.empty());
}
