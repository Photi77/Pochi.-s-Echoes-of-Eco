package net.pochi.pochimod.world.biome;

import net.minecraft.resources.Identifier;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.world.biome.custom.ModOverworldCommonRegion;
import net.pochi.pochimod.world.biome.custom.ModOverworldRareRegion;
import terrablender.api.Regions;

public class ModTerraBlenderAPI {
    public static void registerRegions(){
        Regions.register(new ModOverworldCommonRegion(Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "overworld"), 3));
        Regions.register(new ModOverworldRareRegion(Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "overworld_rare"), 1));
    }
}
