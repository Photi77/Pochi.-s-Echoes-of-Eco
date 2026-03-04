package net.pochi.pochimod.nutrition;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.pochi.pochimod.PochiMod;

import java.util.Optional;

public class FoodNutritionHelper {
    private static final String NBT_PREFIX = "nutrition_";
    private static final String NBT_HYDRATION = "nutrition_hydration";

    // 栄養値をNBTに書き込む
    public static void setNutrition(ItemStack stack, FoodNutritionData data) {
        CompoundTag tag = stack.has(DataComponents.CUSTOM_DATA)
                ? stack.get(DataComponents.CUSTOM_DATA).copyTag()
                : new CompoundTag();
        for (NutritionType type : NutritionType.values()) {
            tag.putInt(NBT_PREFIX + type.getName(), data.getNutrition(type));
        }
        tag.putInt(NBT_HYDRATION, data.getHydration());
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));

        // デバッグ
        PochiMod.LOGGER.info("[NutritionNBT] written to {} : C={} P={} L={} V={} M={}",
                stack.getItem().getName(stack),
                data.getNutrition(NutritionType.CARBOHYDRATE),
                data.getNutrition(NutritionType.PROTEIN),
                data.getNutrition(NutritionType.LIPID),
                data.getNutrition(NutritionType.VITAMIN),
                data.getNutrition(NutritionType.MINERAL));
    }

    // NBTから栄養値を読み込む
    public static Optional<FoodNutritionData> getNutrition(ItemStack stack) {
        if (!stack.has(DataComponents.CUSTOM_DATA)) return Optional.empty();
        CompoundTag tag = stack.get(DataComponents.CUSTOM_DATA).copyTag();

        // NBTに栄養データがあるか確認
        if (!tag.contains(NBT_PREFIX + NutritionType.CARBOHYDRATE.getName())) {
            return Optional.empty();
        }

        FoodNutritionData.Builder builder = FoodNutritionData.builder();
        for (NutritionType type : NutritionType.values()) {
            builder.nutrition(type, tag.getInt(NBT_PREFIX + type.getName()));
        }
        builder.hydration(tag.getInt(NBT_HYDRATION));
        return Optional.of(builder.build());
    }

    public static boolean hasNutrition(ItemStack stack) {
        if (!stack.has(DataComponents.CUSTOM_DATA)) return false;
        CompoundTag tag = stack.get(DataComponents.CUSTOM_DATA).copyTag();
        return tag.contains(NBT_PREFIX + NutritionType.CARBOHYDRATE.getName());
    }
}
