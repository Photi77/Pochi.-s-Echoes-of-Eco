package net.pochi.pochimod.datagen;


import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.pochi.pochimod.PochiMod;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = PochiMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        //generator.addProvider(true, new ModRecipeProvider(packOutput, lookupProvider));
        //generator.addProvider(true, ModLootTableProvider.create(packOutput, lookupProvider));
        //generator.addProvider(true, new ModBlockStateProvider(packOutput, existingFileHelper));
        //generator.addProvider(true, new ModItemModelProvider(packOutput, existingFileHelper));

        //ModBlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(packOutput, lookupProvider, existingFileHelper);
        //generator.addProvider(event.includeServer(), blockTagsProvider);
        //generator.addProvider(event.includeServer(), new ModItemTagsProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(), new ModWorldGenProvider(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new ModLangProvider.ModLangJP(generator.getPackOutput()));
        generator.addProvider(event.includeServer(), new ModLangProvider.ModLangUS(generator.getPackOutput()));
    }
}