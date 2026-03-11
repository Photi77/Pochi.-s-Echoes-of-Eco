package net.pochi.pochimod.datagen;


import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.pochi.pochimod.PochiMod;

@EventBusSubscriber(modid = PochiMod.MOD_ID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherServerData(GatherDataEvent.Server event) {
        event.addProvider(new ModWorldGenProvider(event.getGenerator().getPackOutput(), event.getLookupProvider()));
    }

    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        //event.addProvider(new ModRecipeProvider(event.getGenerator().getPackOutput(), event.getLookupProvider()));
        //event.addProvider(ModLootTableProvider.create(event.getGenerator().getPackOutput(), event.getLookupProvider()));
        //event.addProvider(new ModBlockStateProvider(event.getGenerator().getPackOutput(), existingFileHelper));
        //event.addProvider(new ModItemModelProvider(event.getGenerator().getPackOutput(), existingFileHelper));
        //ModBlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(event.getGenerator().getPackOutput(), event.getLookupProvider(), existingFileHelper);
        //event.addProvider(blockTagsProvider);
        //event.addProvider(new ModItemTagsProvider(event.getGenerator().getPackOutput(), event.getLookupProvider(), blockTagsProvider.contentsGetter(), existingFileHelper));
        event.addProvider(new ModLangProvider.ModLangJP(event.getGenerator().getPackOutput()));
        event.addProvider(new ModLangProvider.ModLangUS(event.getGenerator().getPackOutput()));
    }
}