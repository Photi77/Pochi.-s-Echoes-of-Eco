package net.pochi.pochimod.datagen;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.block.ModBlocks;
import net.pochi.pochimod.item.ModItems;
import net.pochi.pochimod.tags.ModItemTags;

import java.util.List;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, java.util.concurrent.CompletableFuture<net.minecraft.core.HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {

        //食事
        simpleCookingRecipe(consumer, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600, ModItems.CORN.get(), ModItems.BAKED_CORN.get(), 0.35F);
        simpleCookingRecipe(consumer, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600, ModItems.EGGPLANT.get(), ModItems.FRIED_EGGPLANT.get(), 0.35F);
        simpleCookingRecipe(consumer, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600, ModItems.ALMOND.get(), ModItems.FRIED_ALMOND.get(), 0.35F);
        simpleCookingRecipe(consumer, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600, ModItems.LOTUS_ROOT.get(), ModItems.FRIED_LOTUS_ROOT.get(), 0.35F);
        simpleCookingRecipe(consumer, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600, ModItems.ROW_COFFEE_BEANS.get(), ModItems.COFFEE_BEANS.get(), 0.35F);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ASPARAGUS_BACON.get(),3)
                .requires(Items.COOKED_PORKCHOP,3)
                .requires(ModItems.ASPARAGUS.get(),3)
                .unlockedBy("has_asparagus", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ASPARAGUS.get()).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GINGER_PORK.get(),3)
                .requires(Items.COOKED_PORKCHOP,3)
                .requires(ModItems.GINGER.get(),1)
                .unlockedBy("has_asparagus", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ASPARAGUS.get()).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CHINJAOLOSE.get(),5)
                .requires(Items.COOKED_BEEF,1)
                .requires(Items.BAMBOO,2)
                .requires(ModItems.GREEN_PEPPER.get(),2)
                .unlockedBy("has_asparagus", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ASPARAGUS.get()).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PIZZA_BRED.get(),3)
                .requires(Items.BREAD,1)
                .requires(ModItems.TOMATO.get(),1)
                .requires(ModItems.GREEN_PEPPER.get(),1)
                .unlockedBy("has_asparagus", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ASPARAGUS.get()).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BOILED_FISH.get(),3)
                .requires(Items.COOKED_COD,1)
                .requires(ModItems.GINGER.get(),1)
                .unlockedBy("has_asparagus", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ASPARAGUS.get()).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.HAMBURGER.get(),5)
                .requires(Items.BREAD,2)
                .requires(ModItems.GREEN_PEPPER.get(),1)
                .requires(ModItems.TOMATO.get(),1)
                .requires(ModItems.CABBAGE_LEAF.get(),1)
                .requires(Items.COOKED_BEEF,1)
                .unlockedBy("has_asparagus", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ASPARAGUS.get()).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PEPERONCINO.get(),3)
                .requires(Items.WHEAT,3)
                .requires(ModItems.CHILI_PEPPER.get(),1)
                .requires(ModItems.ONION.get(),1)
                .unlockedBy("has_asparagus", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ASPARAGUS.get()).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MABO_NASU.get(),3)
                .requires(ModItems.EGGPLANT.get(),3)
                .requires(Items.COOKED_PORKCHOP,1)
                .requires(ModItems.CHILI_PEPPER.get(),1)
                .unlockedBy("has_asparagus", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ASPARAGUS.get()).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RADISH_MINCED_MEAT.get(),3)
                .requires(Items.COOKED_PORKCHOP,1)
                .requires(ModItems.FRIED_LOTUS_ROOT.get(),3)
                .unlockedBy("has_asparagus", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ASPARAGUS.get()).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CHICKEN_EGG.get(),3)
                .requires(Items.COOKED_CHICKEN,1)
                .requires(Items.EGG,1)
                .requires(ModItems.RICE.get(),1)
                .requires(ModItems.ONION.get(),1)
                .unlockedBy("has_asparagus", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ASPARAGUS.get()).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GENOVESE.get(),3)
                .requires(Items.WHEAT,3)
                .requires(ModItems.BASIL.get(),1)
                .unlockedBy("has_asparagus", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ASPARAGUS.get()).build()))
                .save(consumer);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GREEN_CARRY.get(),5)
                .requires(Items.COOKED_CHICKEN,1)
                .requires(ModItems.BASIL.get(),1)
                .requires(ModItems.CINNAMON.get(),1)
                .requires(ModItems.TOMATO.get(),1)
                .requires(ModItems.ONION.get(),1)
                .requires(ModItems.RICE.get(),1)
                .unlockedBy("has_asparagus", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ASPARAGUS.get()).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GREEN_PEPPER_MINCED_MEAT.get(),3)
                .requires(Items.COOKED_BEEF,1)
                .requires(ModItems.GREEN_PEPPER.get(),3)
                .unlockedBy("has_asparagus", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ASPARAGUS.get()).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PEPE_CABBAGE.get(),6)
                .requires(ModItems.CHILI_PEPPER.get(),1)
                .requires(ModItems.CABBAGE_LEAF.get(),3)
                .unlockedBy("has_asparagus", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ASPARAGUS.get()).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LOTUS_ROOT_MINCED_MEAT.get(),3)
                .requires(Items.COOKED_PORKCHOP,1)
                .requires(ModItems.FRIED_LOTUS_ROOT.get(),3)
                .unlockedBy("has_asparagus", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ASPARAGUS.get()).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GAPRAO.get(),5)
                .requires(ModItems.RICE.get(),1)
                .requires(Items.COOKED_BEEF,1)
                .requires(ModItems.TOMATO.get(),1)
                .requires(ModItems.PAPRIKA.get(),1)
                .requires(ModItems.ONION.get(),1)
                .unlockedBy("has_asparagus", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ASPARAGUS.get()).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TACOS.get(),5)
                .requires(Items.BREAD,1)
                .requires(Items.COOKED_BEEF,1)
                .requires(ModItems.TOMATO.get(),1)
                .requires(ModItems.PAPRIKA.get(),1)
                .requires(ModItems.CHILI_PEPPER.get(),1)
                .unlockedBy("has_asparagus", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ASPARAGUS.get()).build()))
                .save(consumer);

        //鉱石かまど
        oreSmelting(consumer, List.of(ModItems.ROW_CHROMITE.get()), RecipeCategory.MISC,
                ModItems.CHROMITE_INGOT.get(), 0.7f, 200, "chromite");

        oreSmelting(consumer, List.of(ModItems.ROW_ALUNITE.get()), RecipeCategory.MISC,
                ModItems.BAKED_ALUM.get(), 0.7f, 200, "alunite");

        oreSmelting(consumer, List.of(ModItems.ROW_BAUXITE.get()), RecipeCategory.MISC,
                ModItems.ALUMINIUM_INGOT.get(), 0.7f, 200, "bauxite");

        oreSmelting(consumer, List.of(ModItems.ROW_TITANIUM.get()), RecipeCategory.MISC,
                ModItems.TITANIUM_INGOT.get(), 0.7f, 200, "titanium");

        oreSmelting(consumer, List.of(ModItems.ROW_MAGUNESIUM.get()), RecipeCategory.MISC,
                ModItems.MAGUNESIUM_INGOT.get(), 0.7f, 200, "magunesium");

        oreSmelting(consumer, List.of(ModItems.ROW_VANADIUM.get()), RecipeCategory.MISC,
                ModItems.VANADIUM_INGOT.get(), 0.7f, 200, "vanadium");

        //鉱石ブロック
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CHROMITE_INGOT.get(),9)
                 .requires(ModBlocks.CHROMITE_BLOCK.get())
                 .unlockedBy("has_chromite_block", inventoryTrigger(ItemPredicate.Builder.item()
                         .of(ModBlocks.CHROMITE_BLOCK.get()).build()))
                 .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STAINLESS.get(),9)
                .requires(ModBlocks.STAINLESS_BLOCK.get())
                .unlockedBy("has_stainless", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.STAINLESS_BLOCK.get()).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ROW_FLUORITE.get(),9)
                .requires(ModBlocks.FLUORITE_BLOCK.get())
                .unlockedBy("has_fluorite", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.FLUORITE_BLOCK.get()).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ROW_ALUNITE.get(),9)
                .requires(ModBlocks.ALUNITE_BLOCK.get())
                .unlockedBy("has_alunite", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.ALUNITE_BLOCK.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CHROMITE_BLOCK.get())
                 .define('B', ModItems.CHROMITE_INGOT.get())
                 .pattern("BBB")
                 .pattern("BBB")
                 .pattern("BBB")
                 .unlockedBy("has_chromite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                         .of(ModItems.CHROMITE_INGOT.get()).build()))
                 .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STAINLESS_BLOCK.get())
                .define('B', ModItems.STAINLESS.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .unlockedBy("has_stainless", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.STAINLESS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALUNITE_BLOCK.get())
                .define('B', ModItems.ROW_ALUNITE.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .unlockedBy("has_alunite", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ROW_ALUNITE.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.FLUORITE_BLOCK.get())
                .define('B', ModItems.ROW_FLUORITE.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .unlockedBy("has_fluorite", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ROW_FLUORITE.get()).build()))
                .save(consumer);

        //ツール
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHROMITE_SWORD.get())
                .define('B', ModItems.CHROMITE_INGOT.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern("E")
                .unlockedBy("has_chromite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.CHROMITE_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHROMITE_PICKAXE.get())
                .define('B', ModItems.CHROMITE_INGOT.get())
                .define('E', Items.STICK)
                .pattern("BBB")
                .pattern(" E ")
                .pattern(" E ")
                .unlockedBy("has_chromite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.CHROMITE_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHROMITE_SHOVEL.get())
                .define('B', ModItems.CHROMITE_INGOT.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("E")
                .pattern("E")
                .unlockedBy("has_chromite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.CHROMITE_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHROMITE_AXE.get())
                .define('B', ModItems.CHROMITE_INGOT.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern("BE")
                .pattern(" E")
                .unlockedBy("has_chromite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.CHROMITE_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHROMITE_HOE.get())
                .define('B', ModItems.CHROMITE_INGOT.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern(" E")
                .pattern(" E")
                .unlockedBy("has_chromite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.CHROMITE_INGOT.get()).build()))
                .save(consumer);
        //ここまでで一種類

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FLUORITE_SWORD.get())
                .define('B', ModItems.ROW_FLUORITE.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern("E")
                .unlockedBy("has_fluorite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ROW_FLUORITE.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FLUORITE_PICKAXE.get())
                .define('B', ModItems.ROW_FLUORITE.get())
                .define('E', Items.STICK)
                .pattern("BBB")
                .pattern(" E ")
                .pattern(" E ")
                .unlockedBy("has_fluorite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ROW_FLUORITE.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FLUORITE_SHOVEL.get())
                .define('B', ModItems.ROW_FLUORITE.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("E")
                .pattern("E")
                .unlockedBy("has_fluorite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ROW_FLUORITE.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FLUORITE_AXE.get())
                .define('B', ModItems.ROW_FLUORITE.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern("BE")
                .pattern(" E")
                .unlockedBy("has_fluorite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ROW_FLUORITE.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FLUORITE_HOE.get())
                .define('B', ModItems.ROW_FLUORITE.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern(" E")
                .pattern(" E")
                .unlockedBy("has_fluorite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ROW_FLUORITE.get()).build()))
                .save(consumer);
        //ここまでで一種類
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ALUNITE_SWORD.get())
                .define('B', ModItems.ROW_ALUNITE.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern("E")
                .unlockedBy("has_alunite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ROW_ALUNITE.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ALUNITE_PICKAXE.get())
                .define('B', ModItems.ROW_ALUNITE.get())
                .define('E', Items.STICK)
                .pattern("BBB")
                .pattern(" E ")
                .pattern(" E ")
                .unlockedBy("has_alunite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ROW_ALUNITE.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ALUNITE_SHOVEL.get())
                .define('B', ModItems.ROW_ALUNITE.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("E")
                .pattern("E")
                .unlockedBy("has_alunite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ROW_ALUNITE.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ALUNITE_AXE.get())
                .define('B', ModItems.ROW_ALUNITE.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern("BE")
                .pattern(" E")
                .unlockedBy("has_alunite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ROW_ALUNITE.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ALUNITE_HOE.get())
                .define('B', ModItems.ROW_ALUNITE.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern(" E")
                .pattern(" E")
                .unlockedBy("has_alunite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ROW_ALUNITE.get()).build()))
                .save(consumer);
        //ここまでで一種類
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STAINLESS_SWORD.get())
                .define('B', ModItems.STAINLESS.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern("E")
                .unlockedBy("has_stainless_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.STAINLESS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STAINLESS_PICKAXE.get())
                .define('B', ModItems.STAINLESS.get())
                .define('E', Items.STICK)
                .pattern("BBB")
                .pattern(" E ")
                .pattern(" E ")
                .unlockedBy("has_stainless_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.STAINLESS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STAINLESS_SHOVEL.get())
                .define('B', ModItems.STAINLESS.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("E")
                .pattern("E")
                .unlockedBy("has_stainless_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.STAINLESS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STAINLESS_AXE.get())
                .define('B', ModItems.STAINLESS.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern("BE")
                .pattern(" E")
                .unlockedBy("has_stainless_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.STAINLESS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STAINLESS_HOE.get())
                .define('B', ModItems.STAINLESS.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern(" E")
                .pattern(" E")
                .unlockedBy("has_stainless_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.STAINLESS.get()).build()))
                .save(consumer);
        //ここまでで一種類
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ALUMINIUM_SWORD.get())
                .define('B', ModItems.ALUMINIUM_INGOT.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern("E")
                .unlockedBy("has_aluminium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ALUMINIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ALUMINIUM_PICKAXE.get())
                .define('B', ModItems.ALUMINIUM_INGOT.get())
                .define('E', Items.STICK)
                .pattern("BBB")
                .pattern(" E ")
                .pattern(" E ")
                .unlockedBy("has_aluminium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ALUMINIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ALUMINIUM_SHOVEL.get())
                .define('B', ModItems.ALUMINIUM_INGOT.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("E")
                .pattern("E")
                .unlockedBy("has_aluminium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ALUMINIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ALUMINIUM_AXE.get())
                .define('B', ModItems.ALUMINIUM_INGOT.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern("BE")
                .pattern(" E")
                .unlockedBy("has_aluminium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ALUMINIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ALUMINIUM_HOE.get())
                .define('B', ModItems.ALUMINIUM_INGOT.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern(" E")
                .pattern(" E")
                .unlockedBy("has_aluminium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ALUMINIUM_INGOT.get()).build()))
                .save(consumer);
        //ここまでで一種類
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TITANIUM_SWORD.get())
                .define('B', ModItems.TITANIUM_INGOT.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern("E")
                .unlockedBy("has_titanium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.TITANIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TITANIUM_PICKAXE.get())
                .define('B', ModItems.TITANIUM_INGOT.get())
                .define('E', Items.STICK)
                .pattern("BBB")
                .pattern(" E ")
                .pattern(" E ")
                .unlockedBy("has_titanium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.TITANIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TITANIUM_SHOVEL.get())
                .define('B', ModItems.TITANIUM_INGOT.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("E")
                .pattern("E")
                .unlockedBy("has_titanium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.TITANIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TITANIUM_AXE.get())
                .define('B', ModItems.TITANIUM_INGOT.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern("BE")
                .pattern(" E")
                .unlockedBy("has_titanium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.TITANIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TITANIUM_HOE.get())
                .define('B', ModItems.TITANIUM_INGOT.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern(" E")
                .pattern(" E")
                .unlockedBy("has_titanium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.TITANIUM_INGOT.get()).build()))
                .save(consumer);
        //ここまでで一種類
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MAGUNESIUM_SWORD.get())
                .define('B', ModItems.MAGUNESIUM_INGOT.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern("E")
                .unlockedBy("has_magunesium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.MAGUNESIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MAGUNESIUM_PICKAXE.get())
                .define('B', ModItems.MAGUNESIUM_INGOT.get())
                .define('E', Items.STICK)
                .pattern("BBB")
                .pattern(" E ")
                .pattern(" E ")
                .unlockedBy("has_magunesium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.MAGUNESIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MAGUNESIUM_SHOVEL.get())
                .define('B', ModItems.MAGUNESIUM_INGOT.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("E")
                .pattern("E")
                .unlockedBy("has_magunesium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.MAGUNESIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MAGUNESIUM_AXE.get())
                .define('B', ModItems.MAGUNESIUM_INGOT.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern("BE")
                .pattern(" E")
                .unlockedBy("has_magunesium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.MAGUNESIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MAGUNESIUM_HOE.get())
                .define('B', ModItems.MAGUNESIUM_INGOT.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern(" E")
                .pattern(" E")
                .unlockedBy("has_magunesium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.MAGUNESIUM_INGOT.get()).build()))
                .save(consumer);
        //ここまでで一種類
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VANADIUM_SWORD.get())
                .define('B', ModItems.VANADIUM_INGOT.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern("E")
                .unlockedBy("has_vanadium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.VANADIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VANADIUM_PICKAXE.get())
                .define('B', ModItems.VANADIUM_INGOT.get())
                .define('E', Items.STICK)
                .pattern("BBB")
                .pattern(" E ")
                .pattern(" E ")
                .unlockedBy("has_vanadium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.VANADIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VANADIUM_SHOVEL.get())
                .define('B', ModItems.VANADIUM_INGOT.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("E")
                .pattern("E")
                .unlockedBy("has_vanadium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.VANADIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VANADIUM_AXE.get())
                .define('B', ModItems.VANADIUM_INGOT.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern("BE")
                .pattern(" E")
                .unlockedBy("has_vanadium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.VANADIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VANADIUM_HOE.get())
                .define('B', ModItems.VANADIUM_INGOT.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern(" E")
                .pattern(" E")
                .unlockedBy("has_vanadium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.VANADIUM_INGOT.get()).build()))
                .save(consumer);
        //ここまでで一種類
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DURALUMIN_SWORD.get())
                .define('B', ModItems.DURALUMIN.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern("E")
                .unlockedBy("has_duralumin", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.DURALUMIN.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DURALUMIN_PICKAXE.get())
                .define('B', ModItems.DURALUMIN.get())
                .define('E', Items.STICK)
                .pattern("BBB")
                .pattern(" E ")
                .pattern(" E ")
                .unlockedBy("has_duralumin", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.DURALUMIN.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DURALUMIN_SHOVEL.get())
                .define('B', ModItems.DURALUMIN.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("E")
                .pattern("E")
                .unlockedBy("has_duralumin", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.DURALUMIN.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DURALUMIN_AXE.get())
                .define('B', ModItems.DURALUMIN.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern("BE")
                .pattern(" E")
                .unlockedBy("has_duralumin", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.DURALUMIN.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DURALUMIN_HOE.get())
                .define('B', ModItems.DURALUMIN.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern(" E")
                .pattern(" E")
                .unlockedBy("has_duralumin", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.DURALUMIN.get()).build()))
                .save(consumer);
        //ここまでで一種類
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TITAN_ALLOY_SWORD.get())
                .define('B', ModItems.TITAN_ALLOY.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern("E")
                .unlockedBy("has_titan_alloy", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.TITAN_ALLOY.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TITAN_ALLOY_PICKAXE.get())
                .define('B', ModItems.TITAN_ALLOY.get())
                .define('E', Items.STICK)
                .pattern("BBB")
                .pattern(" E ")
                .pattern(" E ")
                .unlockedBy("has_titan_alloy", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.TITAN_ALLOY.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TITAN_ALLOY_SHOVEL.get())
                .define('B', ModItems.TITAN_ALLOY.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("E")
                .pattern("E")
                .unlockedBy("has_titan_alloy", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.TITAN_ALLOY.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TITAN_ALLOY_AXE.get())
                .define('B', ModItems.TITAN_ALLOY.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern("BE")
                .pattern(" E")
                .unlockedBy("has_titan_alloy", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.TITAN_ALLOY.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TITAN_ALLOY_HOE.get())
                .define('B', ModItems.TITAN_ALLOY.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern(" E")
                .pattern(" E")
                .unlockedBy("has_titan_alloy", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.TITAN_ALLOY.get()).build()))
                .save(consumer);
        //ここまでで一種類
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ELECTRON_SWORD.get())
                .define('B', ModItems.ELECTRON.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern("E")
                .unlockedBy("has_electron", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ELECTRON.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ELECTRON_PICKAXE.get())
                .define('B', ModItems.ELECTRON.get())
                .define('E', Items.STICK)
                .pattern("BBB")
                .pattern(" E ")
                .pattern(" E ")
                .unlockedBy("has_electron", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ELECTRON.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ELECTRON_SHOVEL.get())
                .define('B', ModItems.ELECTRON.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("E")
                .pattern("E")
                .unlockedBy("has_electron", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ELECTRON.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ELECTRON_AXE.get())
                .define('B', ModItems.ELECTRON.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern("BE")
                .pattern(" E")
                .unlockedBy("has_electron", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ELECTRON.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ELECTRON_HOE.get())
                .define('B', ModItems.ELECTRON.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern(" E")
                .pattern(" E")
                .unlockedBy("has_electron", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ELECTRON.get()).build()))
                .save(consumer);
        //ここまでで一種類
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VANADIUM_ALLOY_SWORD.get())
                .define('B', ModItems.VANADIUM_ALLOY.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern("E")
                .unlockedBy("has_vanadium_alloy", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.VANADIUM_ALLOY.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VANADIUM_ALLOY_PICKAXE.get())
                .define('B', ModItems.VANADIUM_ALLOY.get())
                .define('E', Items.STICK)
                .pattern("BBB")
                .pattern(" E ")
                .pattern(" E ")
                .unlockedBy("has_vanadium_alloy", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.VANADIUM_ALLOY.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VANADIUM_ALLOY_SHOVEL.get())
                .define('B', ModItems.VANADIUM_ALLOY.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("E")
                .pattern("E")
                .unlockedBy("has_vanadium_alloy", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.VANADIUM_ALLOY.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VANADIUM_ALLOY_AXE.get())
                .define('B', ModItems.VANADIUM_ALLOY.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern("BE")
                .pattern(" E")
                .unlockedBy("has_vanadium_alloy", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.VANADIUM_ALLOY.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VANADIUM_ALLOY_HOE.get())
                .define('B', ModItems.VANADIUM_ALLOY.get())
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern(" E")
                .pattern(" E")
                .unlockedBy("has_vanadium_alloy", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.VANADIUM_ALLOY.get()).build()))
                .save(consumer);
        //ここまでで一種類

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HAMMER_HEAD_PICKAXE.get())
                .define('B', ModItems.HAMMER_HEAD.get())
                .define('E', Items.STICK)
                .pattern("B")
                .pattern("E")
                .pattern("E")
                .unlockedBy("has_hammer_head", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.HAMMER_HEAD.get()).build()))
                .save(consumer);


        //防具
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHROMITE_HELMET.get())
                .define('B', ModItems.CHROMITE_INGOT.get())
                .pattern("BBB")
                .pattern("B B")
                .unlockedBy("has_chromite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.CHROMITE_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHROMITE_CHESTPLATE.get())
                .define('B', ModItems.CHROMITE_INGOT.get())
                .pattern("B B")
                .pattern("BBB")
                .pattern("BBB")
                .unlockedBy("has_chromite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.CHROMITE_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHROMITE_LEGGINGS.get())
                .define('B', ModItems.CHROMITE_INGOT.get())
                .pattern("BBB")
                .pattern("B B")
                .pattern("B B")
                .unlockedBy("has_chromite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.CHROMITE_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHROMITE_BOOTS.get())
                .define('B', ModItems.CHROMITE_INGOT.get())
                .pattern("B B")
                .pattern("B B")
                .unlockedBy("has_chromite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.CHROMITE_INGOT.get()).build()))
                .save(consumer);
        //ここまでで一種類
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FLUORITE_HELMET.get())
                .define('B', ModItems.ROW_FLUORITE.get())
                .pattern("BBB")
                .pattern("B B")
                .unlockedBy("has_fluorite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ROW_FLUORITE.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FLUORITE_CHESTPLATE.get())
                .define('B', ModItems.ROW_FLUORITE.get())
                .pattern("B B")
                .pattern("BBB")
                .pattern("BBB")
                .unlockedBy("has_fluorite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ROW_FLUORITE.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FLUORITE_LEGGINGS.get())
                .define('B', ModItems.ROW_FLUORITE.get())
                .pattern("BBB")
                .pattern("B B")
                .pattern("B B")
                .unlockedBy("has_fluorite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ROW_FLUORITE.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FLUORITE_BOOTS.get())
                .define('B', ModItems.ROW_FLUORITE.get())
                .pattern("B B")
                .pattern("B B")
                .unlockedBy("has_fluorite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ROW_FLUORITE.get()).build()))
                .save(consumer);
        //ここまでで一種類
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STAINLESS_HELMET.get())
                .define('B', ModItems.STAINLESS.get())
                .pattern("BBB")
                .pattern("B B")
                .unlockedBy("has_stainless_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.STAINLESS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STAINLESS_CHESTPLATE.get())
                .define('B', ModItems.STAINLESS.get())
                .pattern("B B")
                .pattern("BBB")
                .pattern("BBB")
                .unlockedBy("has_stainless_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.STAINLESS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STAINLESS_LEGGINGS.get())
                .define('B', ModItems.STAINLESS.get())
                .pattern("BBB")
                .pattern("B B")
                .pattern("B B")
                .unlockedBy("has_stainless_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.STAINLESS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STAINLESS_BOOTS.get())
                .define('B', ModItems.STAINLESS.get())
                .pattern("B B")
                .pattern("B B")
                .unlockedBy("has_stainless_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.STAINLESS.get()).build()))
                .save(consumer);
        //ここまでで一種類
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ALUMINIUM_HELMET.get())
                .define('B', ModItems.ALUMINIUM_INGOT.get())
                .pattern("BBB")
                .pattern("B B")
                .unlockedBy("has_aluminium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ALUMINIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ALUMINIUM_CHESTPLATE.get())
                .define('B', ModItems.ALUMINIUM_INGOT.get())
                .pattern("B B")
                .pattern("BBB")
                .pattern("BBB")
                .unlockedBy("has_aluminium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ALUMINIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ALUMINIUM_LEGGINGS.get())
                .define('B', ModItems.ALUMINIUM_INGOT.get())
                .pattern("BBB")
                .pattern("B B")
                .pattern("B B")
                .unlockedBy("has_aluminium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ALUMINIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ALUMINIUM_BOOTS.get())
                .define('B', ModItems.ALUMINIUM_INGOT.get())
                .pattern("B B")
                .pattern("B B")
                .unlockedBy("has_aluminium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ALUMINIUM_INGOT.get()).build()))
                .save(consumer);
        //ここまでで一種類
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TITANIUM_HELMET.get())
                .define('B', ModItems.TITANIUM_INGOT.get())
                .pattern("BBB")
                .pattern("B B")
                .unlockedBy("has_titanium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.TITANIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TITANIUM_CHESTPLATE.get())
                .define('B', ModItems.TITANIUM_INGOT.get())
                .pattern("B B")
                .pattern("BBB")
                .pattern("BBB")
                .unlockedBy("has_titanium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.TITANIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TITANIUM_LEGGINGS.get())
                .define('B', ModItems.TITANIUM_INGOT.get())
                .pattern("BBB")
                .pattern("B B")
                .pattern("B B")
                .unlockedBy("has_titanium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.TITANIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TITANIUM_BOOTS.get())
                .define('B', ModItems.TITANIUM_INGOT.get())
                .pattern("B B")
                .pattern("B B")
                .unlockedBy("has_titanium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.TITANIUM_INGOT.get()).build()))
                .save(consumer);
        //ここまでで一種類
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MAGUNESIUM_HELMET.get())
                .define('B', ModItems.MAGUNESIUM_INGOT.get())
                .pattern("BBB")
                .pattern("B B")
                .unlockedBy("has_magunesium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.MAGUNESIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MAGUNESIUM_CHESTPLATE.get())
                .define('B', ModItems.MAGUNESIUM_INGOT.get())
                .pattern("B B")
                .pattern("BBB")
                .pattern("BBB")
                .unlockedBy("has_magunesium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.MAGUNESIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MAGUNESIUM_LEGGINGS.get())
                .define('B', ModItems.MAGUNESIUM_INGOT.get())
                .pattern("BBB")
                .pattern("B B")
                .pattern("B B")
                .unlockedBy("has_magunesium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.MAGUNESIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MAGUNESIUM_BOOTS.get())
                .define('B', ModItems.MAGUNESIUM_INGOT.get())
                .pattern("B B")
                .pattern("B B")
                .unlockedBy("has_magunesium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.MAGUNESIUM_INGOT.get()).build()))
                .save(consumer);
        //ここまでで一種類
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VANADIUM_HELMET.get())
                .define('B', ModItems.VANADIUM_INGOT.get())
                .pattern("BBB")
                .pattern("B B")
                .unlockedBy("has_vanadium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.VANADIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VANADIUM_CHESTPLATE.get())
                .define('B', ModItems.VANADIUM_INGOT.get())
                .pattern("B B")
                .pattern("BBB")
                .pattern("BBB")
                .unlockedBy("has_vanadium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.VANADIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VANADIUM_LEGGINGS.get())
                .define('B', ModItems.VANADIUM_INGOT.get())
                .pattern("BBB")
                .pattern("B B")
                .pattern("B B")
                .unlockedBy("has_vanadium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.VANADIUM_INGOT.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VANADIUM_BOOTS.get())
                .define('B', ModItems.VANADIUM_INGOT.get())
                .pattern("B B")
                .pattern("B B")
                .unlockedBy("has_vanadium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.VANADIUM_INGOT.get()).build()))
                .save(consumer);
        //ここまでで一種類
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DURALUMIN_HELMET.get())
                .define('B', ModItems.DURALUMIN.get())
                .pattern("BBB")
                .pattern("B B")
                .unlockedBy("has_duralumin_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.DURALUMIN.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DURALUMIN_CHESTPLATE.get())
                .define('B', ModItems.DURALUMIN.get())
                .pattern("B B")
                .pattern("BBB")
                .pattern("BBB")
                .unlockedBy("has_duralumin_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.DURALUMIN.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DURALUMIN_LEGGINGS.get())
                .define('B', ModItems.DURALUMIN.get())
                .pattern("BBB")
                .pattern("B B")
                .pattern("B B")
                .unlockedBy("has_duralumin_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.DURALUMIN.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DURALUMIN_BOOTS.get())
                .define('B', ModItems.DURALUMIN.get())
                .pattern("B B")
                .pattern("B B")
                .unlockedBy("has_duralumin_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.DURALUMIN.get()).build()))
                .save(consumer);
        //ここまでで一種類
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TITAN_ALLOY_HELMET.get())
                .define('B', ModItems.TITAN_ALLOY.get())
                .pattern("BBB")
                .pattern("B B")
                .unlockedBy("has_titan_alloy_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.TITAN_ALLOY.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TITAN_ALLOY_CHESTPLATE.get())
                .define('B', ModItems.TITAN_ALLOY.get())
                .pattern("B B")
                .pattern("BBB")
                .pattern("BBB")
                .unlockedBy("has_titan_alloy_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.TITAN_ALLOY.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TITAN_ALLOY_LEGGINGS.get())
                .define('B', ModItems.TITAN_ALLOY.get())
                .pattern("BBB")
                .pattern("B B")
                .pattern("B B")
                .unlockedBy("has_titan_alloy_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.TITAN_ALLOY.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TITAN_ALLOY_BOOTS.get())
                .define('B', ModItems.TITAN_ALLOY.get())
                .pattern("B B")
                .pattern("B B")
                .unlockedBy("has_titan_alloy_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.TITAN_ALLOY.get()).build()))
                .save(consumer);
        //ここまでで一種類
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ELECTRON_HELMET.get())
                .define('B', ModItems.ELECTRON.get())
                .pattern("BBB")
                .pattern("B B")
                .unlockedBy("has_electron_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ELECTRON.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ELECTRON_CHESTPLATE.get())
                .define('B', ModItems.ELECTRON.get())
                .pattern("B B")
                .pattern("BBB")
                .pattern("BBB")
                .unlockedBy("has_electron_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ELECTRON.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ELECTRON_LEGGINGS.get())
                .define('B', ModItems.ELECTRON.get())
                .pattern("BBB")
                .pattern("B B")
                .pattern("B B")
                .unlockedBy("has_electron_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ELECTRON.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ELECTRON_BOOTS.get())
                .define('B', ModItems.ELECTRON.get())
                .pattern("B B")
                .pattern("B B")
                .unlockedBy("has_electron_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ELECTRON.get()).build()))
                .save(consumer);
        //ここまでで一種類
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VANADIUM_ALLOY_HELMET.get())
                .define('B', ModItems.VANADIUM_ALLOY.get())
                .pattern("BBB")
                .pattern("B B")
                .unlockedBy("has_vanadium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.VANADIUM_ALLOY.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VANADIUM_ALLOY_CHESTPLATE.get())
                .define('B', ModItems.VANADIUM_ALLOY.get())
                .pattern("B B")
                .pattern("BBB")
                .pattern("BBB")
                .unlockedBy("has_vanadium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.VANADIUM_ALLOY.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VANADIUM_ALLOY_LEGGINGS.get())
                .define('B', ModItems.VANADIUM_ALLOY.get())
                .pattern("BBB")
                .pattern("B B")
                .pattern("B B")
                .unlockedBy("has_vanadium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.VANADIUM_ALLOY.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VANADIUM_ALLOY_BOOTS.get())
                .define('B', ModItems.VANADIUM_ALLOY.get())
                .pattern("B B")
                .pattern("B B")
                .unlockedBy("has_vanadium_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.VANADIUM_ALLOY.get()).build()))
                .save(consumer);


        //網
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WOODEN_NET.get())
                .define('B', Items.STRING)
                .define('C', Items.OAK_PLANKS)
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern("C ")
                .pattern("E ")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.STICK).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_NET.get())
                .define('B', Items.STRING)
                .define('C', Items.IRON_INGOT)
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern("C ")
                .pattern("E ")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.STICK).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DIAMOND_NET.get())
                .define('B', Items.STRING)
                .define('C', Items.DIAMOND)
                .define('E', Items.STICK)
                .pattern("BB")
                .pattern("C ")
                .pattern("E ")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.STICK).build()))
                .save(consumer);
        //ここまでで一種類

        //木材
        planksFromLog(consumer, ModBlocks.CABERNET_SAUVIGNON_PLANKS.get(), ModItemTags.Items.CABERNET_SAUVIGNON_LOGS,4);
        woodFromLogs(consumer, ModBlocks.CABERNET_SAUVIGNON_WOOD.get(), ModBlocks.CABERNET_SAUVIGNON_LOG.get());
        woodFromLogs(consumer, ModBlocks.STRIPPED_CABERNET_SAUVIGNON_WOOD.get(), ModBlocks.STRIPPED_CABERNET_SAUVIGNON_LOG.get());

        //ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.CABERNET_SAUVIGNON_BUTTON.get())
        //        .requires(ModBlocks.CABERNET_SAUVIGNON_PLANKS.get())
        //        .unlockedBy("has_cabernet_sauvignon_planks", inventoryTrigger(ItemPredicate.Builder.item()
        //                .of(ModBlocks.CABERNET_SAUVIGNON_PLANKS.get()).build()))
        //        .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CABERNET_SAUVIGNON_DOOR.get(),3)
                .define('B', ModBlocks.CABERNET_SAUVIGNON_PLANKS.get())
                .pattern("BB")
                .pattern("BB")
                .pattern("BB")
                .unlockedBy("has_cabernet_sauvignon_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.CABERNET_SAUVIGNON_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CABERNET_SAUVIGNON_FENCE.get(),3)
                .define('B', ModBlocks.CABERNET_SAUVIGNON_PLANKS.get())
                .define('E', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_cabernet_sauvignon_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.CABERNET_SAUVIGNON_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CABERNET_SAUVIGNON_FENCE_GATE.get())
                .define('E', ModBlocks.CABERNET_SAUVIGNON_PLANKS.get())
                .define('B', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_cabernet_sauvignon_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.CABERNET_SAUVIGNON_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CABERNET_SAUVIGNON_PRESSURE_PLATE.get())
                .define('E', ModBlocks.CABERNET_SAUVIGNON_PLANKS.get())
                .pattern("EE")
                .unlockedBy("has_cabernet_sauvignon_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.CABERNET_SAUVIGNON_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CABERNET_SAUVIGNON_SLAB.get(),6)
                .define('E', ModBlocks.CABERNET_SAUVIGNON_PLANKS.get())
                .pattern("EEE")
                .unlockedBy("has_cabernet_sauvignon_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.CABERNET_SAUVIGNON_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CABERNET_SAUVIGNON_STAIRS.get(),4)
                .define('E', ModBlocks.CABERNET_SAUVIGNON_PLANKS.get())
                .pattern("E  ")
                .pattern("EE ")
                .pattern("EEE")
                .unlockedBy("has_cabernet_sauvignon_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.CABERNET_SAUVIGNON_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CABERNET_SAUVIGNON_TRAPDOOR.get(),2)
                .define('E', ModBlocks.CABERNET_SAUVIGNON_PLANKS.get())
                .pattern("EEE")
                .pattern("EEE")
                .unlockedBy("has_cabernet_sauvignon_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.CABERNET_SAUVIGNON_PLANKS.get()).build()))
                .save(consumer);
        //ここまでで一種類

        planksFromLog(consumer, ModBlocks.MAPLE_PLANKS.get(), ModItemTags.Items.MAPLE_LOGS,4);
        woodFromLogs(consumer, ModBlocks.MAPLE_WOOD.get(), ModBlocks.MAPLE_LOG.get());
        woodFromLogs(consumer, ModBlocks.STRIPPED_MAPLE_WOOD.get(), ModBlocks.STRIPPED_MAPLE_LOG.get());

        //ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.MAPLE_BUTTON.get())
        //        .requires(ModBlocks.MAPLE_PLANKS.get())
        //        .unlockedBy("has_maple_planks", inventoryTrigger(ItemPredicate.Builder.item()
        //                .of(ModBlocks.MAPLE_PLANKS.get()).build()))
        //        .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MAPLE_DOOR.get(),3)
                .define('B', ModBlocks.MAPLE_PLANKS.get())
                .pattern("BB")
                .pattern("BB")
                .pattern("BB")
                .unlockedBy("has_maple_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MAPLE_FENCE.get(),3)
                .define('B', ModBlocks.MAPLE_PLANKS.get())
                .define('E', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_maple_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MAPLE_FENCE_GATE.get())
                .define('E', ModBlocks.MAPLE_PLANKS.get())
                .define('B', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_maple_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MAPLE_PRESSURE_PLATE.get())
                .define('E', ModBlocks.MAPLE_PLANKS.get())
                .pattern("EE")
                .unlockedBy("has_maple_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MAPLE_SLAB.get(),6)
                .define('E', ModBlocks.MAPLE_PLANKS.get())
                .pattern("EEE")
                .unlockedBy("has_maple_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MAPLE_STAIRS.get(),4)
                .define('E', ModBlocks.MAPLE_PLANKS.get())
                .pattern("E  ")
                .pattern("EE ")
                .pattern("EEE")
                .unlockedBy("has_maple_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MAPLE_TRAPDOOR.get(),2)
                .define('E', ModBlocks.MAPLE_PLANKS.get())
                .pattern("EEE")
                .pattern("EEE")
                .unlockedBy("has_maple_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(consumer);
        //ここまでで一種類

        planksFromLog(consumer, ModBlocks.CHERRY_PLANKS.get(), ModItemTags.Items.CHERRY_LOGS,4);
        woodFromLogs(consumer, ModBlocks.CHERRY_WOOD.get(), ModBlocks.CHERRY_LOG.get());
        woodFromLogs(consumer, ModBlocks.STRIPPED_CHERRY_WOOD.get(), ModBlocks.STRIPPED_CHERRY_LOG.get());

        //ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.CHERRY_BUTTON.get())
        //        .requires(ModBlocks.CHERRY_PLANKS.get())
        //        .unlockedBy("has_cherry_planks", inventoryTrigger(ItemPredicate.Builder.item()
        //                .of(ModBlocks.CHERRY_PLANKS.get()).build()))
        //        .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CHERRY_DOOR.get(),3)
                .define('B', ModBlocks.CHERRY_PLANKS.get())
                .pattern("BB")
                .pattern("BB")
                .pattern("BB")
                .unlockedBy("has_cherry_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.CHERRY_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CHERRY_FENCE.get(),3)
                .define('B', ModBlocks.CHERRY_PLANKS.get())
                .define('E', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_cherry_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.CHERRY_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CHERRY_FENCE_GATE.get())
                .define('E', ModBlocks.CHERRY_PLANKS.get())
                .define('B', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_cherry_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.CHERRY_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CHERRY_PRESSURE_PLATE.get())
                .define('E', ModBlocks.CHERRY_PLANKS.get())
                .pattern("EE")
                .unlockedBy("has_cherry_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.CHERRY_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CHERRY_SLAB.get(),6)
                .define('E', ModBlocks.CHERRY_PLANKS.get())
                .pattern("EEE")
                .unlockedBy("has_cherry_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.CHERRY_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CHERRY_STAIRS.get(),4)
                .define('E', ModBlocks.CHERRY_PLANKS.get())
                .pattern("E  ")
                .pattern("EE ")
                .pattern("EEE")
                .unlockedBy("has_cherry_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.CHERRY_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CHERRY_TRAPDOOR.get(),2)
                .define('E', ModBlocks.CHERRY_PLANKS.get())
                .pattern("EEE")
                .pattern("EEE")
                .unlockedBy("has_cherry_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.CHERRY_PLANKS.get()).build()))
                .save(consumer);
        //ここまでで一種類

        planksFromLog(consumer, ModBlocks.BANANA_PLANKS.get(), ModItemTags.Items.BANANA_LOGS,4);
        woodFromLogs(consumer, ModBlocks.BANANA_WOOD.get(), ModBlocks.BANANA_LOG.get());
        woodFromLogs(consumer, ModBlocks.STRIPPED_BANANA_WOOD.get(), ModBlocks.STRIPPED_BANANA_LOG.get());

        //ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.BANANA_BUTTON.get())
        //        .requires(ModBlocks.BANANA_PLANKS.get())
        //        .unlockedBy("has_banana_planks", inventoryTrigger(ItemPredicate.Builder.item()
        //                .of(ModBlocks.BANANA_PLANKS.get()).build()))
        //        .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BANANA_DOOR.get(),3)
                .define('B', ModBlocks.BANANA_PLANKS.get())
                .pattern("BB")
                .pattern("BB")
                .pattern("BB")
                .unlockedBy("has_banana_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.BANANA_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BANANA_FENCE.get(),3)
                .define('B', ModBlocks.BANANA_PLANKS.get())
                .define('E', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_banana_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.BANANA_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BANANA_FENCE_GATE.get())
                .define('E', ModBlocks.BANANA_PLANKS.get())
                .define('B', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_banana_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.BANANA_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BANANA_PRESSURE_PLATE.get())
                .define('E', ModBlocks.BANANA_PLANKS.get())
                .pattern("EE")
                .unlockedBy("has_banana_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.BANANA_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BANANA_SLAB.get(),6)
                .define('E', ModBlocks.BANANA_PLANKS.get())
                .pattern("EEE")
                .unlockedBy("has_banana_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.BANANA_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BANANA_STAIRS.get(),4)
                .define('E', ModBlocks.BANANA_PLANKS.get())
                .pattern("E  ")
                .pattern("EE ")
                .pattern("EEE")
                .unlockedBy("has_banana_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.BANANA_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BANANA_TRAPDOOR.get(),2)
                .define('E', ModBlocks.BANANA_PLANKS.get())
                .pattern("EEE")
                .pattern("EEE")
                .unlockedBy("has_banana_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.BANANA_PLANKS.get()).build()))
                .save(consumer);
        //ここまでで一種類

        planksFromLog(consumer, ModBlocks.COCONUT_PLANKS.get(), ModItemTags.Items.COCONUT_LOGS,4);
        woodFromLogs(consumer, ModBlocks.COCONUT_WOOD.get(), ModBlocks.COCONUT_LOG.get());
        woodFromLogs(consumer, ModBlocks.STRIPPED_COCONUT_WOOD.get(), ModBlocks.STRIPPED_COCONUT_LOG.get());

        //ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.COCONUT_BUTTON.get())
        //        .requires(ModBlocks.COCONUT_PLANKS.get())
        //        .unlockedBy("has_coconut_planks", inventoryTrigger(ItemPredicate.Builder.item()
        //                .of(ModBlocks.COCONUT_PLANKS.get()).build()))
        //        .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COCONUT_DOOR.get(),3)
                .define('B', ModBlocks.COCONUT_PLANKS.get())
                .pattern("BB")
                .pattern("BB")
                .pattern("BB")
                .unlockedBy("has_coconut_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.COCONUT_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COCONUT_FENCE.get(),3)
                .define('B', ModBlocks.COCONUT_PLANKS.get())
                .define('E', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_coconut_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.COCONUT_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COCONUT_FENCE_GATE.get())
                .define('E', ModBlocks.COCONUT_PLANKS.get())
                .define('B', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_coconut_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.COCONUT_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COCONUT_PRESSURE_PLATE.get())
                .define('E', ModBlocks.COCONUT_PLANKS.get())
                .pattern("EE")
                .unlockedBy("has_coconut_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.COCONUT_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COCONUT_SLAB.get(),6)
                .define('E', ModBlocks.COCONUT_PLANKS.get())
                .pattern("EEE")
                .unlockedBy("has_coconut_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.COCONUT_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COCONUT_STAIRS.get(),4)
                .define('E', ModBlocks.COCONUT_PLANKS.get())
                .pattern("E  ")
                .pattern("EE ")
                .pattern("EEE")
                .unlockedBy("has_coconut_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.COCONUT_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COCONUT_TRAPDOOR.get(),2)
                .define('E', ModBlocks.COCONUT_PLANKS.get())
                .pattern("EEE")
                .pattern("EEE")
                .unlockedBy("has_coconut_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.COCONUT_PLANKS.get()).build()))
                .save(consumer);
        //ここまでで一種類

        planksFromLog(consumer, ModBlocks.PEACH_PLANKS.get(), ModItemTags.Items.PEACH_LOGS,4);
        woodFromLogs(consumer, ModBlocks.PEACH_WOOD.get(), ModBlocks.PEACH_LOG.get());
        woodFromLogs(consumer, ModBlocks.STRIPPED_PEACH_WOOD.get(), ModBlocks.STRIPPED_PEACH_LOG.get());

        //ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.PEACH_BUTTON.get())
        //        .requires(ModBlocks.PEACH_PLANKS.get())
        //        .unlockedBy("has_peach_planks", inventoryTrigger(ItemPredicate.Builder.item()
        //                .of(ModBlocks.PEACH_PLANKS.get()).build()))
        //        .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PEACH_DOOR.get(),3)
                .define('B', ModBlocks.PEACH_PLANKS.get())
                .pattern("BB")
                .pattern("BB")
                .pattern("BB")
                .unlockedBy("has_peach_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.PEACH_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PEACH_FENCE.get(),3)
                .define('B', ModBlocks.PEACH_PLANKS.get())
                .define('E', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_peach_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.PEACH_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PEACH_FENCE_GATE.get())
                .define('E', ModBlocks.PEACH_PLANKS.get())
                .define('B', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_peach_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.PEACH_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PEACH_PRESSURE_PLATE.get())
                .define('E', ModBlocks.PEACH_PLANKS.get())
                .pattern("EE")
                .unlockedBy("has_peach_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.PEACH_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PEACH_SLAB.get(),6)
                .define('E', ModBlocks.PEACH_PLANKS.get())
                .pattern("EEE")
                .unlockedBy("has_peach_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.PEACH_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PEACH_STAIRS.get(),4)
                .define('E', ModBlocks.PEACH_PLANKS.get())
                .pattern("E  ")
                .pattern("EE ")
                .pattern("EEE")
                .unlockedBy("has_peach_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.PEACH_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PEACH_TRAPDOOR.get(),2)
                .define('E', ModBlocks.PEACH_PLANKS.get())
                .pattern("EEE")
                .pattern("EEE")
                .unlockedBy("has_peach_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.PEACH_PLANKS.get()).build()))
                .save(consumer);
        //ここまでで一種類

        planksFromLog(consumer, ModBlocks.ALMOND_PLANKS.get(), ModItemTags.Items.ALMOND_LOGS,4);
        woodFromLogs(consumer, ModBlocks.ALMOND_WOOD.get(), ModBlocks.ALMOND_LOG.get());
        woodFromLogs(consumer, ModBlocks.STRIPPED_ALMOND_WOOD.get(), ModBlocks.STRIPPED_ALMOND_LOG.get());

        //ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.ALMOND_BUTTON.get())
        //        .requires(ModBlocks.ALMOND_PLANKS.get())
        //        .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item()
        //                .of(ModBlocks.ALMOND_PLANKS.get()).build()))
        //        .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALMOND_DOOR.get(),3)
                .define('B', ModBlocks.ALMOND_PLANKS.get())
                .pattern("BB")
                .pattern("BB")
                .pattern("BB")
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.ALMOND_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALMOND_FENCE.get(),3)
                .define('B', ModBlocks.ALMOND_PLANKS.get())
                .define('E', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.ALMOND_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALMOND_FENCE_GATE.get())
                .define('E', ModBlocks.ALMOND_PLANKS.get())
                .define('B', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.ALMOND_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALMOND_PRESSURE_PLATE.get())
                .define('E', ModBlocks.ALMOND_PLANKS.get())
                .pattern("EE")
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.ALMOND_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALMOND_SLAB.get(),6)
                .define('E', ModBlocks.ALMOND_PLANKS.get())
                .pattern("EEE")
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.ALMOND_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALMOND_STAIRS.get(),4)
                .define('E', ModBlocks.ALMOND_PLANKS.get())
                .pattern("E  ")
                .pattern("EE ")
                .pattern("EEE")
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.ALMOND_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALMOND_TRAPDOOR.get(),2)
                .define('E', ModBlocks.ALMOND_PLANKS.get())
                .pattern("EEE")
                .pattern("EEE")
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.ALMOND_PLANKS.get()).build()))
                .save(consumer);
        //ここまでで一種類

        planksFromLog(consumer, ModBlocks.DURIAN_PLANKS.get(), ModItemTags.Items.DURIAN_LOGS,4);
        woodFromLogs(consumer, ModBlocks.DURIAN_WOOD.get(), ModBlocks.DURIAN_LOG.get());
        woodFromLogs(consumer, ModBlocks.STRIPPED_DURIAN_WOOD.get(), ModBlocks.STRIPPED_DURIAN_LOG.get());

        //ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.DURIAN_BUTTON.get())
        //        .requires(ModBlocks.DURIAN_PLANKS.get())
        //        .unlockedBy("has_durian_planks", inventoryTrigger(ItemPredicate.Builder.item()
        //                .of(ModBlocks.DURIAN_PLANKS.get()).build()))
        //        .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DURIAN_DOOR.get(),3)
                .define('B', ModBlocks.DURIAN_PLANKS.get())
                .pattern("BB")
                .pattern("BB")
                .pattern("BB")
                .unlockedBy("has_durian_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.DURIAN_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DURIAN_FENCE.get(),3)
                .define('B', ModBlocks.DURIAN_PLANKS.get())
                .define('E', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_durian_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.DURIAN_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DURIAN_FENCE_GATE.get())
                .define('E', ModBlocks.DURIAN_PLANKS.get())
                .define('B', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_durian_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.DURIAN_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DURIAN_PRESSURE_PLATE.get())
                .define('E', ModBlocks.DURIAN_PLANKS.get())
                .pattern("EE")
                .unlockedBy("has_durian_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.DURIAN_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DURIAN_SLAB.get(),6)
                .define('E', ModBlocks.DURIAN_PLANKS.get())
                .pattern("EEE")
                .unlockedBy("has_durian_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.DURIAN_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DURIAN_STAIRS.get(),4)
                .define('E', ModBlocks.DURIAN_PLANKS.get())
                .pattern("E  ")
                .pattern("EE ")
                .pattern("EEE")
                .unlockedBy("has_durian_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.DURIAN_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DURIAN_TRAPDOOR.get(),2)
                .define('E', ModBlocks.DURIAN_PLANKS.get())
                .pattern("EEE")
                .pattern("EEE")
                .unlockedBy("has_durian_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.DURIAN_PLANKS.get()).build()))
                .save(consumer);
        //ここまでで一種類

        planksFromLog(consumer, ModBlocks.PLUM_PLANKS.get(), ModItemTags.Items.PLUM_LOGS,4);
        woodFromLogs(consumer, ModBlocks.PLUM_WOOD.get(), ModBlocks.PLUM_LOG.get());
        woodFromLogs(consumer, ModBlocks.STRIPPED_PLUM_WOOD.get(), ModBlocks.STRIPPED_PLUM_LOG.get());

        //ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.PLUM_BUTTON.get())
        //        .requires(ModBlocks.PLUM_PLANKS.get())
        //        .unlockedBy("has_plum_planks", inventoryTrigger(ItemPredicate.Builder.item()
        //                .of(ModBlocks.PLUM_PLANKS.get()).build()))
        //        .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PLUM_DOOR.get(),3)
                .define('B', ModBlocks.PLUM_PLANKS.get())
                .pattern("BB")
                .pattern("BB")
                .pattern("BB")
                .unlockedBy("has_plum_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.PLUM_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PLUM_FENCE.get(),3)
                .define('B', ModBlocks.PLUM_PLANKS.get())
                .define('E', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_plum_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.PLUM_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PLUM_FENCE_GATE.get())
                .define('E', ModBlocks.PLUM_PLANKS.get())
                .define('B', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_plum_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.PLUM_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PLUM_PRESSURE_PLATE.get())
                .define('E', ModBlocks.PLUM_PLANKS.get())
                .pattern("EE")
                .unlockedBy("has_plum_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.PLUM_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PLUM_SLAB.get(),6)
                .define('E', ModBlocks.PLUM_PLANKS.get())
                .pattern("EEE")
                .unlockedBy("has_plum_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.PLUM_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PLUM_STAIRS.get(),4)
                .define('E', ModBlocks.PLUM_PLANKS.get())
                .pattern("E  ")
                .pattern("EE ")
                .pattern("EEE")
                .unlockedBy("has_plum_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.PLUM_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PLUM_TRAPDOOR.get(),2)
                .define('E', ModBlocks.PLUM_PLANKS.get())
                .pattern("EEE")
                .pattern("EEE")
                .unlockedBy("has_plum_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.PLUM_PLANKS.get()).build()))
                .save(consumer);
        //ここまでで一種類

        planksFromLog(consumer, ModBlocks.COLA_PLANKS.get(), ModItemTags.Items.COLA_LOGS,4);
        woodFromLogs(consumer, ModBlocks.COLA_WOOD.get(), ModBlocks.COLA_LOG.get());
        woodFromLogs(consumer, ModBlocks.STRIPPED_COLA_WOOD.get(), ModBlocks.STRIPPED_COLA_LOG.get());

        //ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.COLA_BUTTON.get())
        //        .requires(ModBlocks.COLA_PLANKS.get())
        //        .unlockedBy("has_cola_planks", inventoryTrigger(ItemPredicate.Builder.item()
        //                .of(ModBlocks.COLA_PLANKS.get()).build()))
        //        .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COLA_DOOR.get(),3)
                .define('B', ModBlocks.COLA_PLANKS.get())
                .pattern("BB")
                .pattern("BB")
                .pattern("BB")
                .unlockedBy("has_cola_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.COLA_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COLA_FENCE.get(),3)
                .define('B', ModBlocks.COLA_PLANKS.get())
                .define('E', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_cola_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.COLA_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COLA_FENCE_GATE.get())
                .define('E', ModBlocks.COLA_PLANKS.get())
                .define('B', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_cola_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.COLA_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COLA_PRESSURE_PLATE.get())
                .define('E', ModBlocks.COLA_PLANKS.get())
                .pattern("EE")
                .unlockedBy("has_cola_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.COLA_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COLA_SLAB.get(),6)
                .define('E', ModBlocks.COLA_PLANKS.get())
                .pattern("EEE")
                .unlockedBy("has_cola_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.COLA_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COLA_STAIRS.get(),4)
                .define('E', ModBlocks.COLA_PLANKS.get())
                .pattern("E  ")
                .pattern("EE ")
                .pattern("EEE")
                .unlockedBy("has_cola_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.COLA_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COLA_TRAPDOOR.get(),2)
                .define('E', ModBlocks.COLA_PLANKS.get())
                .pattern("EEE")
                .pattern("EEE")
                .unlockedBy("has_cola_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.COLA_PLANKS.get()).build()))
                .save(consumer);
        //ここまでで一種類

        planksFromLog(consumer, ModBlocks.LEMON_PLANKS.get(), ModItemTags.Items.LEMON_LOGS,4);
        woodFromLogs(consumer, ModBlocks.LEMON_WOOD.get(), ModBlocks.LEMON_LOG.get());
        woodFromLogs(consumer, ModBlocks.STRIPPED_LEMON_WOOD.get(), ModBlocks.STRIPPED_LEMON_LOG.get());

        //ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.LEMON_BUTTON.get())
        //        .requires(ModBlocks.LEMON_PLANKS.get())
        //        .unlockedBy("has_lemon_planks", inventoryTrigger(ItemPredicate.Builder.item()
        //                .of(ModBlocks.LEMON_PLANKS.get()).build()))
        //        .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LEMON_DOOR.get(),3)
                .define('B', ModBlocks.LEMON_PLANKS.get())
                .pattern("BB")
                .pattern("BB")
                .pattern("BB")
                .unlockedBy("has_lemon_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.LEMON_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LEMON_FENCE.get(),3)
                .define('B', ModBlocks.LEMON_PLANKS.get())
                .define('E', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_lemon_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.LEMON_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LEMON_FENCE_GATE.get())
                .define('E', ModBlocks.LEMON_PLANKS.get())
                .define('B', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_lemon_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.LEMON_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LEMON_PRESSURE_PLATE.get())
                .define('E', ModBlocks.LEMON_PLANKS.get())
                .pattern("EE")
                .unlockedBy("has_lemon_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.LEMON_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LEMON_SLAB.get(),6)
                .define('E', ModBlocks.LEMON_PLANKS.get())
                .pattern("EEE")
                .unlockedBy("has_lemon_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.LEMON_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LEMON_STAIRS.get(),4)
                .define('E', ModBlocks.LEMON_PLANKS.get())
                .pattern("E  ")
                .pattern("EE ")
                .pattern("EEE")
                .unlockedBy("has_lemon_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.LEMON_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LEMON_TRAPDOOR.get(),2)
                .define('E', ModBlocks.LEMON_PLANKS.get())
                .pattern("EEE")
                .pattern("EEE")
                .unlockedBy("has_lemon_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.LEMON_PLANKS.get()).build()))
                .save(consumer);
        //ここまでで一種類

        planksFromLog(consumer, ModBlocks.CINNAMON_PLANKS.get(), ModItemTags.Items.CINNAMON_LOGS,4);
        woodFromLogs(consumer, ModBlocks.CINNAMON_WOOD.get(), ModBlocks.CINNAMON_LOG.get());
        woodFromLogs(consumer, ModBlocks.STRIPPED_CINNAMON_WOOD.get(), ModBlocks.STRIPPED_CINNAMON_LOG.get());

        //ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.CINNAMON_BUTTON.get())
        //        .requires(ModBlocks.CINNAMON_PLANKS.get())
        //        .unlockedBy("has_cinnamon_planks", inventoryTrigger(ItemPredicate.Builder.item()
        //                .of(ModBlocks.CINNAMON_PLANKS.get()).build()))
        //        .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CINNAMON_DOOR.get(),3)
                .define('B', ModBlocks.CINNAMON_PLANKS.get())
                .pattern("BB")
                .pattern("BB")
                .pattern("BB")
                .unlockedBy("has_cinnamon_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.CINNAMON_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CINNAMON_FENCE.get(),3)
                .define('B', ModBlocks.CINNAMON_PLANKS.get())
                .define('E', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_cinnamon_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.CINNAMON_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CINNAMON_FENCE_GATE.get())
                .define('E', ModBlocks.CINNAMON_PLANKS.get())
                .define('B', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_cinnamon_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.CINNAMON_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CINNAMON_PRESSURE_PLATE.get())
                .define('E', ModBlocks.CINNAMON_PLANKS.get())
                .pattern("EE")
                .unlockedBy("has_cinnamon_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.CINNAMON_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CINNAMON_SLAB.get(),6)
                .define('E', ModBlocks.CINNAMON_PLANKS.get())
                .pattern("EEE")
                .unlockedBy("has_cinnamon_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.CINNAMON_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CINNAMON_STAIRS.get(),4)
                .define('E', ModBlocks.CINNAMON_PLANKS.get())
                .pattern("E  ")
                .pattern("EE ")
                .pattern("EEE")
                .unlockedBy("has_cinnamon_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.CINNAMON_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CINNAMON_TRAPDOOR.get(),2)
                .define('E', ModBlocks.CINNAMON_PLANKS.get())
                .pattern("EEE")
                .pattern("EEE")
                .unlockedBy("has_cinnamon_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.CINNAMON_PLANKS.get()).build()))
                .save(consumer);



        planksFromLog(consumer, ModBlocks.COFFEE_PLANKS.get(), ModItemTags.Items.COFFEE_LOGS,4);
        woodFromLogs(consumer, ModBlocks.COFFEE_WOOD.get(), ModBlocks.COFFEE_LOG.get());
        woodFromLogs(consumer, ModBlocks.STRIPPED_COFFEE_WOOD.get(), ModBlocks.STRIPPED_COFFEE_LOG.get());

        //ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.COFFEE_BUTTON.get())
        //        .requires(ModBlocks.COFFEE_PLANKS.get())
        //        .unlockedBy("has_COFFEE_planks", inventoryTrigger(ItemPredicate.Builder.item()
        //                .of(ModBlocks.COFFEE_PLANKS.get()).build()))
        //        .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COFFEE_DOOR.get(),3)
                .define('B', ModBlocks.COFFEE_PLANKS.get())
                .pattern("BB")
                .pattern("BB")
                .pattern("BB")
                .unlockedBy("has_coffee_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.COFFEE_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COFFEE_FENCE.get(),3)
                .define('B', ModBlocks.COFFEE_PLANKS.get())
                .define('E', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_coffee_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.COFFEE_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COFFEE_FENCE_GATE.get())
                .define('E', ModBlocks.COFFEE_PLANKS.get())
                .define('B', Items.STICK)
                .pattern("BEB")
                .pattern("BEB")
                .unlockedBy("has_coffee_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.COFFEE_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COFFEE_PRESSURE_PLATE.get())
                .define('E', ModBlocks.COFFEE_PLANKS.get())
                .pattern("EE")
                .unlockedBy("has_coffee_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.COFFEE_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COFFEE_SLAB.get(),6)
                .define('E', ModBlocks.COFFEE_PLANKS.get())
                .pattern("EEE")
                .unlockedBy("has_coffee_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.COFFEE_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COFFEE_STAIRS.get(),4)
                .define('E', ModBlocks.COFFEE_PLANKS.get())
                .pattern("E  ")
                .pattern("EE ")
                .pattern("EEE")
                .unlockedBy("has_coffee_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.COFFEE_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COFFEE_TRAPDOOR.get(),2)
                .define('E', ModBlocks.COFFEE_PLANKS.get())
                .pattern("EEE")
                .pattern("EEE")
                .unlockedBy("has_coffee_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.COFFEE_PLANKS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_COFFEE_BLOCK.get())
                .define('B', ModItems.ROW_COFFEE_BEANS.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .unlockedBy("has_raw_coffee_beans", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ROW_COFFEE_BEANS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COFFEE_BLOCK.get())
                .define('B', ModItems.COFFEE_BEANS.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .unlockedBy("has_raw_coffee_beans", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ROW_COFFEE_BEANS.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MUSK_COFFEE_BLOCK.get())
                .define('B', ModItems.MUSK_COFFEE_BEANS.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .unlockedBy("has_raw_coffee_beans", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ROW_COFFEE_BEANS.get()).build()))
                .save(consumer);


        //サンドウィッチ
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TOMATO_SAND.get())
                .define('B', Items.BREAD)
                .define('E', ModItems.TOMATO.get())
                .pattern("B")
                .pattern("E")
                .pattern("B")
                .unlockedBy("has_bread", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BREAD).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BANANA_SAND.get())
                .define('B', Items.BREAD)
                .define('E', ModItems.BANANA.get())
                .pattern("B")
                .pattern("E")
                .pattern("B")
                .unlockedBy("has_bread", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BREAD).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PEACH_SAND.get())
                .define('B', Items.BREAD)
                .define('E', ModItems.PEACH.get())
                .pattern("B")
                .pattern("E")
                .pattern("B")
                .unlockedBy("has_bread", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BREAD).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.APPLE_SAND.get())
                .define('B', Items.BREAD)
                .define('E', Items.APPLE)
                .pattern("B")
                .pattern("E")
                .pattern("B")
                .unlockedBy("has_bread", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BREAD).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GRAPE_SAND.get())
                .define('B', Items.BREAD)
                .define('E', ModItems.GRAPE.get())
                .pattern("B")
                .pattern("E")
                .pattern("B")
                .unlockedBy("has_bread", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BREAD).build()))
                .save(consumer);

        //特殊アイテム
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.REVERSAL.get())
                .define('B', Items.ARROW)
                .define('E', Items.BOOK)
                .pattern(" B ")
                .pattern("BEB")
                .pattern(" B ")
                .unlockedBy("has_bread", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BOOK).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLEM_SUMMON.get())
                .define('B', Items.DIRT)
                .define('E', Items.BOOK)
                .pattern(" B ")
                .pattern("BEB")
                .pattern(" B ")
                .unlockedBy("has_bread", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BOOK).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENDER_SPELL.get())
                .define('B', Items.ENDER_PEARL)
                .define('E', Items.BOOK)
                .pattern("B")
                .pattern("E")
                .pattern("B")
                .unlockedBy("has_bread", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BOOK).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SOUL_SPELL.get())
                .define('B', Items.SOUL_SAND)
                .define('E', Items.BOOK)
                .pattern(" B ")
                .pattern("BEB")
                .pattern(" B ")
                .unlockedBy("has_bread", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BOOK).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SHIELD_CAST.get())
                .define('B', Items.SHIELD)
                .define('E', Items.BOOK)
                .pattern("B")
                .pattern("E")
                .pattern("B")
                .unlockedBy("has_bread", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BOOK).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HOOK_SHOT.get())
                .define('B', Items.IRON_PICKAXE)
                .define('E', Items.STRING)
                .define('C', Items.CROSSBOW)
                .pattern("B")
                .pattern("E")
                .pattern("C")
                .unlockedBy("has_bread", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.IRON_INGOT).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TONBOKIRI.get())
                .define('B', ModItems.STAINLESS.get())
                .define('E', Items.STICK)
                .define('D', ModItems.DRAGONFLY_WINGS.get())
                .pattern(" DB")
                .pattern(" ED")
                .pattern("E  ")
                .unlockedBy("has_stainless_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.STAINLESS.get()).build()))
                .save(consumer);

       //ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HOOK_SHOT.get())
       //        .define('B', ModItems.CHROMITE_INGOT.get())
       //        .define('E', Items.STRING)
       //        .define('C', Items.CROSSBOW)
       //        .pattern("B")
       //        .pattern("E")
       //        .pattern("C")
       //        .unlockedBy("has_bread", inventoryTrigger(ItemPredicate.Builder.item()
       //                .of(Items.IRON_INGOT).build()))
       //        .save(consumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FLY_BOAT.get())
                .define('B', Blocks.WHITE_WOOL)
                .define('E', Blocks.CAMPFIRE)
                .define('D', Items.OAK_BOAT)
                .pattern("B")
                .pattern("E")
                .pattern("D")
                .unlockedBy("has_boat", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.OAK_BOAT).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FLY_CHEST_BOAT.get())
                .define('B', Blocks.WHITE_WOOL)
                .define('E', Blocks.CAMPFIRE)
                .define('D', Items.OAK_CHEST_BOAT)
                .pattern("B")
                .pattern("E")
                .pattern("D")
                .unlockedBy("has_boat", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.OAK_CHEST_BOAT).build()))
                .save(consumer);


    }

    protected static void oreSmelting(RecipeOutput p_250654_, List<ItemLike> p_250172_, RecipeCategory p_250588_, ItemLike p_251868_, float p_250789_, int p_252144_, String p_251687_) {
        oreCooking(p_250654_, RecipeSerializer.SMELTING_RECIPE, p_250172_, p_250588_, p_251868_, p_250789_, p_252144_, p_251687_, "_from_smelting");
    }

    protected static void oreCooking(RecipeOutput p_250791_, RecipeSerializer<? extends AbstractCookingRecipe> p_251817_, List<ItemLike> p_249619_, RecipeCategory p_251154_, ItemLike p_250066_, float p_251871_, int p_251316_, String p_251450_, String p_249236_) {
        for(ItemLike itemlike : p_249619_) {
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(itemlike), p_251154_, p_250066_, p_251871_, p_251316_).group(p_251450_)
                    .unlockedBy(getHasName(itemlike), has(itemlike)).save(p_250791_, ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, getItemName(p_250066_)) + p_249236_ + "_" + getItemName(itemlike));
        }
    }


}
