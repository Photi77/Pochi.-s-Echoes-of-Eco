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

public class DistillerRecipe implements Recipe<SimpleContainerRecipeInput> {
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public DistillerRecipe(ItemStack output, NonNullList<Ingredient> recipeItems) {
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(SimpleContainerRecipeInput input, Level pLevel) {
        if (recipeItems.get(0).test(input.getItem(1))) {
            if (recipeItems.get(1).test(input.getItem(2))) {
                return recipeItems.get(2).test(input.getItem(3));
            }
        }
        return false;
    }

    @Override
    public ItemStack assemble(SimpleContainerRecipeInput input, HolderLookup.Provider registries) {
        return output.copy();
    }

    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return output.copy();
    }

    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public RecipeSerializer<DistillerRecipe> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<DistillerRecipe> getType() {
        return Type.INSTANCE;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return CATEGORY;
    }

    private static final RecipeBookCategory CATEGORY = new RecipeBookCategory();

    public static class Type implements RecipeType<DistillerRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "distiller";
    }

    public static class Serializer implements RecipeSerializer<DistillerRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        public static final MapCodec<DistillerRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                ItemStack.STRICT_CODEC.fieldOf("output").forGetter(r -> r.output),
                Ingredient.CODEC.listOf().xmap(
                        list -> {
                            NonNullList<Ingredient> nl = NonNullList.create();
                            nl.addAll(list);
                            return nl;
                        },
                        nl -> nl
                ).fieldOf("ingredients").forGetter(r -> r.recipeItems)
        ).apply(inst, DistillerRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, DistillerRecipe> STREAM_CODEC =
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
                            NonNullList<Ingredient> inputs = NonNullList.create();
                            for (int i = 0; i < size; i++) {
                                inputs.add(Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
                            }
                            ItemStack output = ItemStack.STREAM_CODEC.decode(buf);
                            return new DistillerRecipe(output, inputs);
                        }
                );

        @Override
        public MapCodec<DistillerRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, DistillerRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
