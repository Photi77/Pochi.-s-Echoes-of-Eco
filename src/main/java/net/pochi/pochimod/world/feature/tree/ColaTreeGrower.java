package net.pochi.pochimod.world.feature.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.pochi.pochimod.world.feature.ModConfiguredFeatures;
import java.util.Optional;

public class ColaTreeGrower {
    public static final TreeGrower INSTANCE = new TreeGrower(
        "pochimod_cola", Optional.empty(), Optional.of(ModConfiguredFeatures.COLA_KEY), Optional.empty());
}
