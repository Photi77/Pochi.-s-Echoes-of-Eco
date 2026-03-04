package net.pochi.pochimod.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.pochi.pochimod.ferm.CropNutrientRegistry;
import net.pochi.pochimod.item.food.FoodQualityHelper;
import net.pochi.pochimod.nutrition.FoodNutritionData;
import net.pochi.pochimod.nutrition.FoodNutritionHelper;
import net.pochi.pochimod.nutrition.FoodNutritionRegistry;
import net.pochi.pochimod.nutrition.NutritionType;

import java.util.EnumMap;
import java.util.Map;

public class QualityShapelessRecipe extends ShapelessRecipe {

    // Store locally so the serializer codec can access it
    private final ItemStack result;

    public QualityShapelessRecipe(String group, CraftingBookCategory category,
                                  ItemStack result, NonNullList<Ingredient> ingredients) {
        super(group, category, result, ingredients);
        this.result = result;
    }

    @Override
    public ItemStack assemble(CraftingInput container, HolderLookup.Provider access) {
        ItemStack result = super.assemble(container, access);

        float totalQuality = 0f;
        int foodCount = 0;

        // 栄養素の合計
        Map<NutritionType, Integer> totalNutrition = new EnumMap<>(NutritionType.class);
        for (NutritionType type : NutritionType.values()) {
            totalNutrition.put(type, 0);
        }
        int totalHydration = 0;

        for (int i = 0; i < container.size(); i++) {
            ItemStack ingredient = container.getItem(i);
            FoodProperties food = ingredient.get(DataComponents.FOOD);
            if (ingredient.isEmpty()) continue;

            // isEdible だけでなく Registry・ホワイトリストも対象に
            boolean isFoodIngredient =
                    food != null ||
                            FoodNutritionRegistry.getNutritionData(ingredient.getItem()).isPresent() ||
                            CropNutrientRegistry.shouldApplyQuality(ingredient.getItem());

            if (!isFoodIngredient) continue;

            totalQuality += FoodQualityHelper.getQuality(ingredient);
            foodCount++;


            // NBT栄養値優先、なければRegistry固定値
            FoodNutritionData data = FoodNutritionHelper.getNutrition(ingredient)
                    .orElseGet(() ->
                            FoodNutritionRegistry.getNutritionData(ingredient.getItem())
                                    .orElse(null)
                    );

            if (data != null) {
                for (NutritionType type : NutritionType.values()) {
                    totalNutrition.merge(type, data.getNutrition(type), Integer::sum);
                }
                totalHydration += data.getHydration();
            }
        }

        if (foodCount > 0) {
            // 品質係数：平均
            float avgQuality = totalQuality / foodCount;
            FoodQualityHelper.setQuality(result, avgQuality);

            // 栄養素：合計（加工で増えるイメージ）or 平均（お好みで）
            FoodNutritionData.Builder builder = FoodNutritionData.builder();
            for (NutritionType type : NutritionType.values()) {
                builder.nutrition(type, totalNutrition.get(type));
            }
            builder.hydration(totalHydration);
            FoodNutritionHelper.setNutrition(result, builder.build());
        }

        return result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.QUALITY_SHAPELESS.get();
    }


    // --- Serializer ---
    public static class Serializer implements RecipeSerializer<QualityShapelessRecipe> {

        private static final MapCodec<QualityShapelessRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(ShapelessRecipe::getGroup),
                CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(ShapelessRecipe::category),
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(r -> r.result),
                Ingredient.CODEC_NONEMPTY.listOf().xmap(
                        list -> {
                            NonNullList<Ingredient> nl = NonNullList.withSize(list.size(), Ingredient.EMPTY);
                            for (int i = 0; i < list.size(); i++) nl.set(i, list.get(i));
                            return nl;
                        },
                        nl -> nl
                ).fieldOf("ingredients").forGetter(Recipe::getIngredients)
        ).apply(inst, QualityShapelessRecipe::new));

        private static final StreamCodec<RegistryFriendlyByteBuf, QualityShapelessRecipe> STREAM_CODEC =
                StreamCodec.of(
                        (buf, recipe) -> {
                            buf.writeUtf(recipe.getGroup());
                            buf.writeEnum(recipe.category());
                            buf.writeVarInt(recipe.getIngredients().size());
                            for (Ingredient ing : recipe.getIngredients()) {
                                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ing);
                            }
                            ItemStack.STREAM_CODEC.encode(buf, recipe.result);
                        },
                        buf -> {
                            String group = buf.readUtf();
                            CraftingBookCategory category = buf.readEnum(CraftingBookCategory.class);
                            int size = buf.readVarInt();
                            NonNullList<Ingredient> ingredients = NonNullList.withSize(size, Ingredient.EMPTY);
                            for (int i = 0; i < size; i++) {
                                ingredients.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
                            }
                            ItemStack result = ItemStack.STREAM_CODEC.decode(buf);
                            return new QualityShapelessRecipe(group, category, result, ingredients);
                        }
                );

        @Override
        public MapCodec<QualityShapelessRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, QualityShapelessRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }

}
