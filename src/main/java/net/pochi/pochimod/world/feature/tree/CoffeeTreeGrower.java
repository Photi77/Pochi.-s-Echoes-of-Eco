package net.pochi.pochimod.world.feature.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.pochi.pochimod.world.feature.ModConfiguredFeatures;
import java.util.Optional;

public class CoffeeTreeGrower {
    public static final TreeGrower INSTANCE = new TreeGrower(
        "pochimod_coffee", Optional.empty(), Optional.of(ModConfiguredFeatures.COFFEE_KEY), Optional.empty());
}
