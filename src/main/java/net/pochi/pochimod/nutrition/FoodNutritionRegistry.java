package net.pochi.pochimod.nutrition;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 食べ物アイテムと栄養値のマッピング
 */
public class FoodNutritionRegistry {

    private static final Map<Item, FoodNutritionData> NUTRITION_MAP = new HashMap<>();

    /**
     * 初期化（バニラアイテムの栄養値を登録）
     */
    public static void init() {
        // === 肉類（高タンパク質、中脂質） ===
        registerVanillaFood(Items.COOKED_BEEF, FoodNutritionData.builder()
                .protein(25)
                .lipid(15)
                .mineral(5)
                .hydration(5)
                .build());

        registerVanillaFood(Items.COOKED_PORKCHOP, FoodNutritionData.builder()
                .protein(20)
                .lipid(18)
                .mineral(5)
                .hydration(5)
                .build());

        registerVanillaFood(Items.COOKED_CHICKEN, FoodNutritionData.builder()
                .protein(22)
                .lipid(10)
                .mineral(5)
                .hydration(5)
                .build());

        registerVanillaFood(Items.COOKED_MUTTON, FoodNutritionData.builder()
                .protein(20)
                .lipid(16)
                .mineral(5)
                .hydration(5)
                .build());

        registerVanillaFood(Items.COOKED_RABBIT, FoodNutritionData.builder()
                .protein(18)
                .lipid(8)
                .mineral(5)
                .hydration(5)
                .build());

        // 生肉（栄養価低め）
        registerVanillaFood(Items.BEEF, FoodNutritionData.builder()
                .protein(12)
                .lipid(8)
                .mineral(3)
                .hydration(3)
                .build());

        registerVanillaFood(Items.PORKCHOP, FoodNutritionData.builder()
                .protein(10)
                .lipid(10)
                .mineral(3)
                .hydration(3)
                .build());

        registerVanillaFood(Items.CHICKEN, FoodNutritionData.builder()
                .protein(10)
                .lipid(5)
                .mineral(3)
                .hydration(3)
                .build());

        // === 魚類（高タンパク質、高脂質） ===
        registerVanillaFood(Items.COOKED_COD, FoodNutritionData.builder()
                .protein(20)
                .lipid(12)
                .mineral(8)
                .hydration(8)
                .build());

        registerVanillaFood(Items.COOKED_SALMON, FoodNutritionData.builder()
                .protein(22)
                .lipid(18)
                .mineral(8)
                .vitamin(5)
                .hydration(8)
                .build());

        registerVanillaFood(Items.COD, FoodNutritionData.builder()
                .protein(10)
                .lipid(6)
                .mineral(5)
                .hydration(5)
                .build());

        registerVanillaFood(Items.SALMON, FoodNutritionData.builder()
                .protein(12)
                .lipid(10)
                .mineral(5)
                .hydration(5)
                .build());

        // === 野菜・果物（高ビタミン、高ミネラル） ===
        registerVanillaFood(Items.APPLE, FoodNutritionData.builder()
                .carbohydrate(8)
                .vitamin(12)
                .mineral(5)
                .hydration(8)
                .build());

        registerVanillaFood(Items.GOLDEN_APPLE, FoodNutritionData.builder()
                .carbohydrate(10)
                .vitamin(18)
                .mineral(12)
                .hydration(10)
                .build());

        registerVanillaFood(Items.MELON_SLICE, FoodNutritionData.builder()
                .carbohydrate(5)
                .vitamin(8)
                .mineral(3)
                .hydration(12)
                .build());

        registerVanillaFood(Items.SWEET_BERRIES, FoodNutritionData.builder()
                .carbohydrate(6)
                .vitamin(10)
                .mineral(4)
                .hydration(7)
                .build());

        registerVanillaFood(Items.GLOW_BERRIES, FoodNutritionData.builder()
                .carbohydrate(6)
                .vitamin(10)
                .mineral(5)
                .hydration(7)
                .build());

        registerVanillaFood(Items.CARROT, FoodNutritionData.builder()
                .carbohydrate(8)
                .vitamin(12)
                .mineral(5)
                .hydration(5)
                .build());

        registerVanillaFood(Items.GOLDEN_CARROT, FoodNutritionData.builder()
                .carbohydrate(10)
                .vitamin(18)
                .mineral(10)
                .hydration(8)
                .build());

        registerVanillaFood(Items.BEETROOT, FoodNutritionData.builder()
                .carbohydrate(6)
                .vitamin(10)
                .mineral(8)
                .hydration(6)
                .build());

        // === 穀物・パン（高炭水化物） ===
        registerVanillaFood(Items.BREAD, FoodNutritionData.builder()
                .carbohydrate(20)
                .protein(5)
                .mineral(3)
                .hydration(3)
                .build());

        registerVanillaFood(Items.BAKED_POTATO, FoodNutritionData.builder()
                .carbohydrate(18)
                .vitamin(5)
                .mineral(6)
                .hydration(5)
                .build());

        registerVanillaFood(Items.POTATO, FoodNutritionData.builder()
                .carbohydrate(12)
                .vitamin(4)
                .mineral(5)
                .hydration(4)
                .build());

        registerVanillaFood(Items.COOKIE, FoodNutritionData.builder()
                .carbohydrate(8)
                .lipid(3)
                .hydration(1)
                .build());

        registerVanillaFood(Items.CAKE, FoodNutritionData.builder()
                .carbohydrate(15)
                .lipid(8)
                .protein(3)
                .hydration(5)
                .build());

        registerVanillaFood(Items.PUMPKIN_PIE, FoodNutritionData.builder()
                .carbohydrate(16)
                .vitamin(8)
                .lipid(5)
                .hydration(5)
                .build());

        // === 複合食品（バランス良い） ===
        registerVanillaFood(Items.MUSHROOM_STEW, FoodNutritionData.builder()
                .carbohydrate(8)
                .protein(6)
                .vitamin(12)
                .mineral(8)
                .hydration(10)
                .build());

        registerVanillaFood(Items.RABBIT_STEW, FoodNutritionData.builder()
                .carbohydrate(10)
                .protein(18)
                .lipid(8)
                .vitamin(10)
                .mineral(8)
                .hydration(12)
                .build());

        registerVanillaFood(Items.BEETROOT_SOUP, FoodNutritionData.builder()
                .carbohydrate(10)
                .vitamin(15)
                .mineral(10)
                .hydration(12)
                .build());

        // === その他 ===
        registerVanillaFood(Items.ROTTEN_FLESH, FoodNutritionData.builder()
                .protein(5)
                .lipid(2)
                .hydration(2)
                .build());

        registerVanillaFood(Items.DRIED_KELP, FoodNutritionData.builder()
                .carbohydrate(4)
                .vitamin(5)
                .mineral(8)
                .hydration(3)
                .build());

        registerVanillaFood(Items.WHEAT, FoodNutritionData.builder()
                .carbohydrate(10)
                .protein(18)
                .lipid(8)
                .vitamin(10)
                .mineral(8)
                .hydration(12)
                .build());
    }

    private static void registerVanillaFood(Item item, FoodNutritionData data) {
        NUTRITION_MAP.put(item, data);
    }

    public static void register(Item item, FoodNutritionData data) {
        NUTRITION_MAP.put(item, data);
    }

    public static Optional<FoodNutritionData> getNutritionData(Item item) {
        return Optional.ofNullable(NUTRITION_MAP.get(item));
    }

    public static boolean hasNutritionData(Item item) {
        return NUTRITION_MAP.containsKey(item);
    }


}
