package net.pochi.pochimod.ferm;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;
import java.util.Map;

// CropNutrientWeight.java
public record CropNutrientWeight(float wN, float wP, float wK) {
    public static final CropNutrientWeight DEFAULT = new CropNutrientWeight(1f, 1f, 1f);
}


