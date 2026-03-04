package net.pochi.pochimod.world.feature.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.pochi.pochimod.world.feature.ModConfiguredFeatures;
import java.util.Optional;

public class CinnamonTreeGrower {
    public static final TreeGrower INSTANCE = new TreeGrower(
        "pochimod_cinnamon", Optional.empty(), Optional.of(ModConfiguredFeatures.CINNAMON_KEY), Optional.empty());
}
