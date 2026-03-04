package net.pochi.pochimod.world.feature.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.pochi.pochimod.world.feature.ModConfiguredFeatures;
import java.util.Optional;

public class MapleTreeGrower {
    public static final TreeGrower INSTANCE = new TreeGrower(
        "pochimod_maple", Optional.empty(), Optional.of(ModConfiguredFeatures.MAPLE_KEY), Optional.empty());
}
