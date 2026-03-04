package net.pochi.pochimod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.block.ModBlocks;
import net.pochi.pochimod.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PochiMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.BAKED_ALUM);
        simpleItem(ModItems.CHROMITE_INGOT);
        simpleItem(ModItems.ALUMINIUM_INGOT);
        simpleItem(ModItems.TITANIUM_INGOT);
        simpleItem(ModItems.MAGUNESIUM_INGOT);
        simpleItem(ModItems.VANADIUM_INGOT);
        simpleItem(ModItems.STAINLESS);
        simpleItem(ModItems.DURALUMIN);
        simpleItem(ModItems.TITAN_ALLOY);
        simpleItem(ModItems.ELECTRON);
        simpleItem(ModItems.VANADIUM_ALLOY);
        simpleItem(ModItems.ROW_CHROMITE);
        simpleItem(ModItems.ROW_FLUORITE);
        simpleItem(ModItems.ROW_ALUNITE);
        simpleItem(ModItems.ROW_BAUXITE);
        simpleItem(ModItems.ROW_TITANIUM);
        simpleItem(ModItems.ROW_MAGUNESIUM);
        simpleItem(ModItems.ROW_VANADIUM);

        handheldItem(ModItems.CHROMITE_SWORD);
        handheldItem(ModItems.CHROMITE_PICKAXE);
        handheldItem(ModItems.CHROMITE_SHOVEL);
        handheldItem(ModItems.CHROMITE_AXE);
        handheldItem(ModItems.CHROMITE_HOE);
        handheldItem(ModItems.FLUORITE_SWORD);
        handheldItem(ModItems.FLUORITE_PICKAXE);
        handheldItem(ModItems.FLUORITE_SHOVEL);
        handheldItem(ModItems.FLUORITE_AXE);
        handheldItem(ModItems.FLUORITE_HOE);
        handheldItem(ModItems.ALUNITE_SWORD);
        handheldItem(ModItems.ALUNITE_PICKAXE);
        handheldItem(ModItems.ALUNITE_SHOVEL);
        handheldItem(ModItems.ALUNITE_AXE);
        handheldItem(ModItems.ALUNITE_HOE);
        handheldItem(ModItems.STAINLESS_SWORD);
        handheldItem(ModItems.STAINLESS_PICKAXE);
        handheldItem(ModItems.STAINLESS_SHOVEL);
        handheldItem(ModItems.STAINLESS_AXE);
        handheldItem(ModItems.STAINLESS_HOE);
        handheldItem(ModItems.ALUMINIUM_SWORD);
        handheldItem(ModItems.ALUMINIUM_PICKAXE);
        handheldItem(ModItems.ALUMINIUM_SHOVEL);
        handheldItem(ModItems.ALUMINIUM_AXE);
        handheldItem(ModItems.ALUMINIUM_HOE);
        handheldItem(ModItems.TITANIUM_SWORD);
        handheldItem(ModItems.TITANIUM_PICKAXE);
        handheldItem(ModItems.TITANIUM_SHOVEL);
        handheldItem(ModItems.TITANIUM_AXE);
        handheldItem(ModItems.TITANIUM_HOE);
        handheldItem(ModItems.MAGUNESIUM_SWORD);
        handheldItem(ModItems.MAGUNESIUM_PICKAXE);
        handheldItem(ModItems.MAGUNESIUM_SHOVEL);
        handheldItem(ModItems.MAGUNESIUM_AXE);
        handheldItem(ModItems.MAGUNESIUM_HOE);
        handheldItem(ModItems.VANADIUM_SWORD);
        handheldItem(ModItems.VANADIUM_PICKAXE);
        handheldItem(ModItems.VANADIUM_SHOVEL);
        handheldItem(ModItems.VANADIUM_AXE);
        handheldItem(ModItems.VANADIUM_HOE);
        handheldItem(ModItems.DURALUMIN_SWORD);
        handheldItem(ModItems.DURALUMIN_PICKAXE);
        handheldItem(ModItems.DURALUMIN_SHOVEL);
        handheldItem(ModItems.DURALUMIN_AXE);
        handheldItem(ModItems.DURALUMIN_HOE);
        handheldItem(ModItems.TITAN_ALLOY_SWORD);
        handheldItem(ModItems.TITAN_ALLOY_PICKAXE);
        handheldItem(ModItems.TITAN_ALLOY_SHOVEL);
        handheldItem(ModItems.TITAN_ALLOY_AXE);
        handheldItem(ModItems.TITAN_ALLOY_HOE);
        handheldItem(ModItems.ELECTRON_SWORD);
        handheldItem(ModItems.ELECTRON_PICKAXE);
        handheldItem(ModItems.ELECTRON_SHOVEL);
        handheldItem(ModItems.ELECTRON_AXE);
        handheldItem(ModItems.ELECTRON_HOE);
        handheldItem(ModItems.VANADIUM_ALLOY_SWORD);
        handheldItem(ModItems.VANADIUM_ALLOY_PICKAXE);
        handheldItem(ModItems.VANADIUM_ALLOY_SHOVEL);
        handheldItem(ModItems.VANADIUM_ALLOY_AXE);
        handheldItem(ModItems.VANADIUM_ALLOY_HOE);

        simpleItem(ModItems.CHROMITE_HELMET);
        simpleItem(ModItems.CHROMITE_CHESTPLATE);
        simpleItem(ModItems.CHROMITE_LEGGINGS);
        simpleItem(ModItems.CHROMITE_BOOTS);
        simpleItem(ModItems.FLUORITE_HELMET);
        simpleItem(ModItems.FLUORITE_CHESTPLATE);
        simpleItem(ModItems.FLUORITE_LEGGINGS);
        simpleItem(ModItems.FLUORITE_BOOTS);
        simpleItem(ModItems.STAINLESS_HELMET);
        simpleItem(ModItems.STAINLESS_CHESTPLATE);
        simpleItem(ModItems.STAINLESS_LEGGINGS);
        simpleItem(ModItems.STAINLESS_BOOTS);
        simpleItem(ModItems.ALUMINIUM_HELMET);
        simpleItem(ModItems.ALUMINIUM_CHESTPLATE);
        simpleItem(ModItems.ALUMINIUM_LEGGINGS);
        simpleItem(ModItems.ALUMINIUM_BOOTS);
        simpleItem(ModItems.TITANIUM_HELMET);
        simpleItem(ModItems.TITANIUM_CHESTPLATE);
        simpleItem(ModItems.TITANIUM_LEGGINGS);
        simpleItem(ModItems.TITANIUM_BOOTS);
        simpleItem(ModItems.MAGUNESIUM_HELMET);
        simpleItem(ModItems.MAGUNESIUM_CHESTPLATE);
        simpleItem(ModItems.MAGUNESIUM_LEGGINGS);
        simpleItem(ModItems.MAGUNESIUM_BOOTS);
        simpleItem(ModItems.VANADIUM_HELMET);
        simpleItem(ModItems.VANADIUM_CHESTPLATE);
        simpleItem(ModItems.VANADIUM_LEGGINGS);
        simpleItem(ModItems.VANADIUM_BOOTS);
        simpleItem(ModItems.DURALUMIN_HELMET);
        simpleItem(ModItems.DURALUMIN_CHESTPLATE);
        simpleItem(ModItems.DURALUMIN_LEGGINGS);
        simpleItem(ModItems.DURALUMIN_BOOTS);
        simpleItem(ModItems.TITAN_ALLOY_HELMET);
        simpleItem(ModItems.TITAN_ALLOY_CHESTPLATE);
        simpleItem(ModItems.TITAN_ALLOY_LEGGINGS);
        simpleItem(ModItems.TITAN_ALLOY_BOOTS);
        simpleItem(ModItems.ELECTRON_HELMET);
        simpleItem(ModItems.ELECTRON_CHESTPLATE);
        simpleItem(ModItems.ELECTRON_LEGGINGS);
        simpleItem(ModItems.ELECTRON_BOOTS);
        simpleItem(ModItems.VANADIUM_ALLOY_HELMET);
        simpleItem(ModItems.VANADIUM_ALLOY_CHESTPLATE);
        simpleItem(ModItems.VANADIUM_ALLOY_LEGGINGS);
        simpleItem(ModItems.VANADIUM_ALLOY_BOOTS);

        simpleItem(ModItems.CABBAGE_LEAF);
        simpleItem(ModItems.ASPARAGUS);
        simpleItem(ModItems.GRAPE);
        simpleItem(ModItems.LEMON);
        simpleItem(ModItems.PLUM);
        simpleItem(ModItems.CHERRY);
        simpleItem(ModItems.BANANA);
        simpleItem(ModItems.COCONUT);
        simpleItem(ModItems.PEACH);
        simpleItem(ModItems.ALMOND);
        simpleItem(ModItems.DURIAN);
        simpleItem(ModItems.CINNAMON);
        simpleItem(ModItems.ROW_RICE);
        simpleItem(ModItems.RICE);
        simpleItem(ModItems.MINT);
        simpleItem(ModItems.COLA);
        simpleItem(ModItems.TOMATO);
        simpleItem(ModItems.CORN);
        simpleItem(ModItems.ONION);
        simpleItem(ModItems.GINGER);
        simpleItem(ModItems.GREEN_PEPPER);
        simpleItem(ModItems.PAPRIKA);
        simpleItem(ModItems.EGGPLANT);
        simpleItem(ModItems.WHITE_RADISH);
        simpleItem(ModItems.CHILI_PEPPER);
        simpleItem(ModItems.BASIL);
        simpleItem(ModItems.LOTUS_ROOT);
        simpleItem(ModItems.ASPARAGUS_BACON);
        simpleItem(ModItems.GINGER_PORK);
        simpleItem(ModItems.FRIED_EGGPLANT);
        simpleItem(ModItems.CHINJAOLOSE);
        simpleItem(ModItems.POPCORN);
        simpleItem(ModItems.PIZZA_BRED);
        simpleItem(ModItems.BOILED_FISH);
        simpleItem(ModItems.CORN_SOUP);
        simpleItem(ModItems.HAMBURGER);
        simpleItem(ModItems.TEA);
        simpleItem(ModItems.OLIVE);
        simpleItem(ModItems.BLUE_BERRY);
        simpleItem(ModItems.HOP);
        simpleItem(ModItems.PEPPER);
        simpleItem(ModItems.FIREWOOD);
        simpleItem(ModItems.FILTERED_WATER);
        simpleItem(ModItems.BOTTLE_OF_MILK);
        simpleItem(ModItems.BOTTLE_OF_WHISKEY);
        simpleItem(ModItems.BOTTLE_OF_RED_WINE);
        simpleItem(ModItems.BOTTLE_OF_WHITE_WINE);
        simpleItem(ModItems.BOTTLE_OF_SAKE);
        simpleItem(ModItems.BOTTLE_OF_WHITE_LIQUOR);
        simpleItem(ModItems.PEACH_LIQUEUR);
        simpleItem(ModItems.PLUM_LIQUEUR);
        simpleItem(ModItems.MINT_LIQUEUR);
        simpleItem(ModItems.LEMON_LIQUEUR);
        simpleItem(ModItems.APPLE_LIQUEUR);
        simpleItem(ModItems.MAPLE_WATER);
        simpleItem(ModItems.MAPLE_SYRUP);
        simpleItem(ModItems.BOTTLE_OF_COLA);
        simpleItem(ModItems.GRAPE_JUICE);
        simpleItem(ModItems.APPLE_JUICE);
        simpleItem(ModItems.LEMON_JUICE);
        simpleItem(ModItems.PLUM_JUICE);
        simpleItem(ModItems.PEACH_JUICE);
        simpleItem(ModItems.BANANA_JUICE);
        simpleItem(ModItems.ALMOND_MILK);
        simpleItem(ModItems.COCONUT_MILK);
        simpleItem(ModItems.SMOOTHIE);
        simpleItem(ModItems.MIX_JUICE);
        simpleItem(ModItems.MIX_AU_LAIT);
        simpleItem(ModItems.CHOCOLATE);
        simpleItem(ModItems.CHOCO_MINT);
        simpleItem(ModItems.ASPARAGUS_SEEDS);
        simpleItem(ModItems.CABBAGE_SEEDS);
        simpleItem(ModItems.RICE_SEEDS);
        simpleItem(ModItems.MINT_SEEDS);
        simpleItem(ModItems.COLA_SEEDS);
        simpleItem(ModItems.TOMATO_SEEDS);
        simpleItem(ModItems.CORN_SEEDS);
        simpleItem(ModItems.ONION_SEEDS);
        simpleItem(ModItems.GINGER_SEEDS);
        simpleItem(ModItems.GREEN_PEPPER_SEEDS);
        simpleItem(ModItems.PAPRIKA_SEEDS);
        simpleItem(ModItems.EGGPLANT_SEEDS);
        simpleItem(ModItems.WHITE_RADISH_SEEDS);
        simpleItem(ModItems.CHILI_PEPPER_SEEDS);
        simpleItem(ModItems.BASIL_SEEDS);
        simpleItem(ModItems.LOTUS_ROOT_SEEDS);
        simpleItemBlock(ModBlocks.CABERNET_SAUVIGNON_DOOR);
        simpleItemBlock(ModBlocks.MAPLE_DOOR);
        simpleItemBlock(ModBlocks.CINNAMON_DOOR);
        simpleItemBlock(ModBlocks.COLA_DOOR);
        simpleItemBlock(ModBlocks.LEMON_DOOR);
        simpleItemBlock(ModBlocks.PLUM_DOOR);
        simpleItemBlock(ModBlocks.CHERRY_DOOR);
        simpleItemBlock(ModBlocks.BANANA_DOOR);
        simpleItemBlock(ModBlocks.COCONUT_DOOR);
        simpleItemBlock(ModBlocks.PEACH_DOOR);
        simpleItemBlock(ModBlocks.ALMOND_DOOR);
        simpleItemBlock(ModBlocks.DURIAN_DOOR);
        simpleItem(ModItems.PEPERONCINO);
        simpleItem(ModItems.MABO_NASU);
        simpleItem(ModItems.BAKED_CORN);
        simpleItem(ModItems.RADISH_MINCED_MEAT);
        simpleItem(ModItems.CHICKEN_EGG);
        simpleItem(ModItems.GENOVESE);
        simpleItem(ModItems.FRIED_ALMOND);
        simpleItem(ModItems.GREEN_CARRY);
        simpleItem(ModItems.GREEN_PEPPER_MINCED_MEAT);
        simpleItem(ModItems.PEPE_CABBAGE);
        simpleItem(ModItems.FRIED_LOTUS_ROOT);
        simpleItem(ModItems.LOTUS_ROOT_MINCED_MEAT);
        simpleItem(ModItems.GAPRAO);
        simpleItem(ModItems.TACOS);
        simpleItem(ModItems.TOMATO_SAND);
        simpleItem(ModItems.BANANA_SAND);
        simpleItem(ModItems.PEACH_SAND);
        simpleItem(ModItems.APPLE_SAND);
        simpleItem(ModItems.GRAPE_SAND);




        saplingItem(ModBlocks.CABERNET_SAUVIGNON_SAPLING);
        saplingItem(ModBlocks.MAPLE_SAPLING);
        saplingItem(ModBlocks.CINNAMON_SAPLING);
        saplingItem(ModBlocks.COLA_SAPLING);
        saplingItem(ModBlocks.LEMON_SAPLING);
        saplingItem(ModBlocks.PLUM_SAPLING);
        saplingItem(ModBlocks.CHERRY_SAPLING);
        saplingItem(ModBlocks.BANANA_SAPLING);
        saplingItem(ModBlocks.COCONUT_SAPLING);
        saplingItem(ModBlocks.PEACH_SAPLING);
        saplingItem(ModBlocks.ALMOND_SAPLING);
        saplingItem(ModBlocks.DURIAN_SAPLING);

    }

    private ItemModelBuilder saplingItem(DeferredHolder<Block, Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItem(DeferredHolder<Item, Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemBlock(DeferredHolder<Block, Block> block) {
        return withExistingParent(block.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "item/" + block.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(DeferredHolder<Item, Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "item/" + item.getId().getPath()));
    }



}
