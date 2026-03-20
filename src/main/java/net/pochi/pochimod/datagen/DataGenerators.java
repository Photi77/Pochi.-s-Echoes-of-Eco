package net.pochi.pochimod.datagen;


import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.pochi.pochimod.PochiMod;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = PochiMod.MOD_ID)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        generator.addProvider(true, new ModLangProvider.ModLangJP(
                packOutput));
        generator.addProvider(true, new ModLangProvider.ModLangUS(
                packOutput));
    }

    @SubscribeEvent
    public static void gatherServerData(GatherDataEvent.Server event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        generator.addProvider(true, new ModWorldGenProvider(
                packOutput,
                lookupProvider
        ));
    }
}