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

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return output.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<DistillerRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "distiller";
    }

    public static class Serializer implements RecipeSerializer<DistillerRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        public static final MapCodec<DistillerRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                ItemStack.STRICT_CODEC.fieldOf("output").forGetter(r -> r.output),
                Ingredient.CODEC_NONEMPTY.listOf().xmap(
                        list -> {
                            NonNullList<Ingredient> nl = NonNullList.withSize(list.size(), Ingredient.EMPTY);
                            for (int i = 0; i < list.size(); i++) nl.set(i, list.get(i));
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
                            NonNullList<Ingredient> inputs = NonNullList.withSize(size, Ingredient.EMPTY);
                            for (int i = 0; i < size; i++) {
                                inputs.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
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
