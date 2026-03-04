package net.pochi.pochimod.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class FrypanRecipe implements Recipe<SimpleContainerRecipeInput> {
    final ItemStack result;
    final NonNullList<Ingredient> ingredients;
    private final boolean isSimple;

    public FrypanRecipe(ItemStack pResult, NonNullList<Ingredient> pIngredients) {
        this.result = pResult;
        this.ingredients = pIngredients;
        this.isSimple = pIngredients.stream().allMatch(Ingredient::isSimple);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return FrypanRecipe.Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return FrypanRecipe.Type.INSTANCE;
    }

    public static class Type implements RecipeType<FrypanRecipe> {
        private Type() { }
        public static final FrypanRecipe.Type INSTANCE = new FrypanRecipe.Type();
        public static final String ID = "frypan";
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public boolean matches(SimpleContainerRecipeInput input, Level pLevel) {
        StackedContents stackedcontents = new StackedContents();
        java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
        int i = 0;

        for (int j = 0; j < input.size(); ++j) {
            ItemStack itemstack = input.getItem(j);
            if (!itemstack.isEmpty()) {
                ++i;
                if (isSimple)
                    stackedcontents.accountStack(itemstack, 1);
                else inputs.add(itemstack);
            }
        }

        return i == this.ingredients.size() && (isSimple ? stackedcontents.canCraft(this, (IntList) null)
                : net.neoforged.neoforge.common.util.RecipeMatcher.findMatches(inputs, this.ingredients) != null);
    }

    @Override
    public ItemStack assemble(SimpleContainerRecipeInput input, HolderLookup.Provider registries) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= this.ingredients.size();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return this.result;
    }

    public static class Serializer implements RecipeSerializer<FrypanRecipe> {
        public static final FrypanRecipe.Serializer INSTANCE = new FrypanRecipe.Serializer();

        public static final MapCodec<FrypanRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                ItemStack.STRICT_CODEC.fieldOf("output").forGetter(r -> r.result),
                Ingredient.CODEC_NONEMPTY.listOf().xmap(
                        list -> {
                            NonNullList<Ingredient> nl = NonNullList.withSize(list.size(), Ingredient.EMPTY);
                            for (int i = 0; i < list.size(); i++) nl.set(i, list.get(i));
                            return nl;
                        },
                        nl -> nl
                ).fieldOf("ingredients").forGetter(r -> r.ingredients)
        ).apply(inst, FrypanRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, FrypanRecipe> STREAM_CODEC =
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
                            NonNullList<Ingredient> inputs = NonNullList.withSize(size, Ingredient.EMPTY);
                            for (int i = 0; i < size; i++) {
                                inputs.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
                            }
                            ItemStack result = ItemStack.STREAM_CODEC.decode(buf);
                            return new FrypanRecipe(result, inputs);
                        }
                );

        @Override
        public MapCodec<FrypanRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, FrypanRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
