package net.pochi.pochimod.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.recipe.BFurnaceRecipe;
import net.pochi.pochimod.screen.BFurnaceScreen;

import java.util.List;

@JeiPlugin
public class JEIPochiModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new BFurnaceCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<BFurnaceRecipe> bFurnaceRecipes = recipeManager.getAllRecipesFor(BFurnaceRecipe.Type.INSTANCE)
                .stream().map(net.minecraft.world.item.crafting.RecipeHolder::value).toList();
        registration.addRecipes(BFurnaceCategory.GEM_POLISHING_TYPE, bFurnaceRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(BFurnaceScreen.class, 60, 30, 20, 30,
                BFurnaceCategory.GEM_POLISHING_TYPE);
    }
}