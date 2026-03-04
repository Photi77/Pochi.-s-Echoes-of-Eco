package net.pochi.pochimod.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.block.ModBlocks;
import net.pochi.pochimod.recipe.BFurnaceRecipe;

public class BFurnaceCategory implements IRecipeCategory<BFurnaceRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "bfurnace");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/gui/bfurnace_gui.png");

    public static final RecipeType<BFurnaceRecipe> GEM_POLISHING_TYPE =
            new RecipeType<>(UID, BFurnaceRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public BFurnaceCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.BFURNACE_BLOCK.get()));
    }

    @Override
    public mezz.jei.api.recipe.RecipeType<BFurnaceRecipe> getRecipeType() {
        return GEM_POLISHING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.pochimod.bfurnace_block");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, BFurnaceRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 18, 50).addIngredients(recipe.getIngredients().set(0,null));
        builder.addSlot(RecipeIngredientRole.INPUT, 66, 16).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 66, 50).addIngredients(recipe.getIngredients().get(2));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 114, 33).addItemStack(recipe.getResultItem(null));
    }
}