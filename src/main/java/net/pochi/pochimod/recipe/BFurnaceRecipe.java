package net.pochi.pochimod.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class BFurnaceRecipe implements Recipe<SimpleContainerRecipeInput> {
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    // Static singleton RecipeBookCategory for custom recipe
    private static final RecipeBookCategory BOOK_CATEGORY = new RecipeBookCategory();

    public BFurnaceRecipe(ItemStack output, NonNullList<Ingredient> recipeItems) {
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(SimpleContainerRecipeInput input, Level pLevel) {
        if (recipeItems.get(0).test(input.getItem(1))) {
            return recipeItems.get(1).test(input.getItem(2));
        }
        return false;
    }

    @Override
    public ItemStack assemble(SimpleContainerRecipeInput input, HolderLookup.Provider registries) {
        return output.copy();
    }

    // Not in Recipe interface in 1.21.11, but kept for internal use
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    // Not in Recipe interface in 1.21.11, but kept for JEI/display use
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return output.copy();
    }

    // Not in Recipe interface in 1.21.11, but kept for ingredient access
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.create(recipeItems);
    }

    @Override
    public RecipeSerializer<BFurnaceRecipe> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<BFurnaceRecipe> getType() {
        return Type.INSTANCE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return BOOK_CATEGORY;
    }

    public static class Type implements RecipeType<BFurnaceRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "bfurnace";
    }

    public ItemStack getOutput() {
        return output;
    }

    public static class Serializer implements RecipeSerializer<BFurnaceRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        public static final MapCodec<BFurnaceRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                ItemStack.STRICT_CODEC.fieldOf("output").forGetter(r -> r.output),
                Ingredient.CODEC.listOf().xmap(
                        list -> {
                            NonNullList<Ingredient> nl = NonNullList.withSize(list.size(), Ingredient.of());
                            for (int i = 0; i < list.size(); i++) nl.set(i, list.get(i));
                            return nl;
                        },
                        nl -> nl
                ).fieldOf("ingredients").forGetter(r -> r.recipeItems)
        ).apply(inst, BFurnaceRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, BFurnaceRecipe> STREAM_CODEC =
                StreamCodec.of(
                        (buf, recipe) -> {
                            buf.writeVarInt(recipe.recipeItems.size());
                            for (Ingredient ing : recipe.recipeItems) {
                                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ing);
                            }
                            ItemStack.STREAM_CODEC.encode(buf, recipe.output);
                        },
                        buf -> {
                            int size = buf.readVarInt();
                            NonNullList<Ingredient> inputs = NonNullList.withSize(size, Ingredient.of());
                            for (int i = 0; i < size; i++) {
                                inputs.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
                            }
                            ItemStack output = ItemStack.STREAM_CODEC.decode(buf);
                            return new BFurnaceRecipe(output, inputs);
                        }
                );

        @Override
        public MapCodec<BFurnaceRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, BFurnaceRecipe> streamCodec() {
            return STREAM_CODEC;
        }


    }


}
