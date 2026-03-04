package net.pochi.pochimod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.block.ModBlocks;



public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,PochiMod.MOD_ID);
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> POCHI_TAB = CREATIVE_MODE_TABS.register( "pochi_tab",() ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TEA.get())).title(Component.literal("Pochi Tab")).build());
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> POCHI_PLANT = CREATIVE_MODE_TABS.register( "pochi_plant",() ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.CABERNET_SAUVIGNON_LEAVES.get())).title(Component.literal("Pochi Plants")).build());
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> POCHI_ORE  = CREATIVE_MODE_TABS.register( "pochi_ore",() ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ROW_CHROMITE.get())).title(Component.literal("Pochi Ores")).build());
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> POCHI_FOOD = CREATIVE_MODE_TABS.register( "pochi_food",() ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TEA.get())).title(Component.literal("Pochi Foods")).build());
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> POCHI_DRINK = CREATIVE_MODE_TABS.register( "pochi_drink",() ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BOTTLE_OF_RED_WINE.get())).title(Component.literal("Pochi Drinks")).build());
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> POCHI_BUCKET = CREATIVE_MODE_TABS.register( "pochi_bucket",() ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SAKE_BUCKET.get())).title(Component.literal("Pochi Buckets")).build());
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> POCHI_TOOL = CREATIVE_MODE_TABS.register( "pochi_tool",() ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.STAINLESS_PICKAXE.get())).title(Component.literal("Pochi Tools")).build());
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> POCHI_ARMOR = CREATIVE_MODE_TABS.register( "pochi_armor",() ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.STAINLESS_HELMET.get())).title(Component.literal("Pochi Armors")).build());
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> POCHI_CRAFT = CREATIVE_MODE_TABS.register( "pochi_craft",() ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.BFURNACE_BLOCK.get())).title(Component.literal("Pochi Craft")).build());
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> POCHI_ENTITY = CREATIVE_MODE_TABS.register( "pochi_entity",() ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SPARROW_SPAWN_EGG.get())).title(Component.literal("Pochi Entities")).build());
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> POCHI_SPELL = CREATIVE_MODE_TABS.register( "pochi_spell",() ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SPARROW_SPAWN_EGG.get())).title(Component.literal("Pochi Spell")).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
