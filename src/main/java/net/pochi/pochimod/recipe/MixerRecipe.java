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

public class MixerRecipe implements Recipe<SimpleContainerRecipeInput> {
    final ItemStack result;
    final NonNullList<Ingredient> ingredients;

    public MixerRecipe(ItemStack pResult, NonNullList<Ingredient> pIngredients) {
        this.result = pResult;
        this.ingredients = pIngredients;
    }

    @Override
    public RecipeSerializer<MixerRecipe> getSerializer() {
        return MixerRecipe.Serializer.INSTANCE;
    }

    @Override
    public RecipeType<MixerRecipe> getType() {
        return MixerRecipe.Type.INSTANCE;
    }

    public static class Type implements RecipeType<MixerRecipe> {
        private Type() { }
        public static final MixerRecipe.Type INSTANCE = new MixerRecipe.Type();
        public static final String ID = "mixer";
    }

    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public boolean matches(SimpleContainerRecipeInput input, Level pLevel) {
        java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
        for (int j = 0; j < input.size(); ++j) {
            ItemStack itemstack = input.getItem(j);
            if (!itemstack.isEmpty()) {
                inputs.add(itemstack);
            }
        }
        return inputs.size() == this.ingredients.size() &&
               net.neoforged.neoforge.common.util.RecipeMatcher.findMatches(inputs, this.ingredients) != null;
    }

    @Override
    public ItemStack assemble(SimpleContainerRecipeInput input, HolderLookup.Provider registries) {
        return this.result.copy();
    }

    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= this.ingredients.size();
    }

    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return this.result;
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

    public static class Serializer implements RecipeSerializer<MixerRecipe> {
        public static final MixerRecipe.Serializer INSTANCE = new MixerRecipe.Serializer();

        public static final MapCodec<MixerRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                ItemStack.STRICT_CODEC.fieldOf("output").forGetter(r -> r.result),
                Ingredient.CODEC.listOf().xmap(
                        list -> {
                            NonNullList<Ingredient> nl = NonNullList.create();
                            nl.addAll(list);
                            return nl;
                        },
                        nl -> nl
                ).fieldOf("ingredients").forGetter(r -> r.ingredients)
        ).apply(inst, MixerRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, MixerRecipe> STREAM_CODEC =
                StreamCodec.of(
                        (buf, recipe) -> {
                            buf.writeVarInt(recipe.ingredients.size());
                            for (Ingredient ing : recipe.ingredients) {
                                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ing);
                            }
                            ItemStack.STREAM_CODEC.encode(buf, recipe.result);
                        },
                        buf -> {
                            int size = buf.readVarInt();
                            NonNullList<Ingredient> inputs = NonNullList.create();
                            for (int i = 0; i < size; i++) {
                                inputs.add(Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
                            }
                            ItemStack result = ItemStack.STREAM_CODEC.decode(buf);
                            return new MixerRecipe(result, inputs);
                        }
                );

        @Override
        public MapCodec<MixerRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, MixerRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
