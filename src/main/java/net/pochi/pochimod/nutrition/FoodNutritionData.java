package net.pochi.pochimod.nutrition;

import java.util.EnumMap;
import java.util.Map;

/**
 * 食べ物の栄養値データ
 * 各食べ物がどの栄養素をどれだけ持つかを定義
 */
public class FoodNutritionData {

    private final Map<NutritionType, Integer> nutritionValues;
    private final int hydrationValue;
    private int carbohydrate;
    private int protein;
    private int lipid;
    private int vitamin;
    private int mineral;
    private int hydration;

    private FoodNutritionData(Builder builder) {
        this.nutritionValues = new EnumMap<>(builder.nutritionValues);
        this.hydrationValue = builder.hydrationValue;
    }

    /**
     * 特定栄養素の値を取得
     */
    public int getNutrition(NutritionType type) {
        return nutritionValues.getOrDefault(type, 0);
    }

    /**
     * 水分値を取得
     */
    public int getHydration() {
        return hydrationValue;
    }

    /**
     * 全栄養素の値を取得
     */
    public Map<NutritionType, Integer> getAllNutrition() {
        return new EnumMap<>(nutritionValues);
    }

    // FoodNutritionData.java に追加
    public FoodNutritionData scaled(float factor) {
        Builder builder = FoodNutritionData.builder();
        for (Map.Entry<NutritionType, Integer> entry : nutritionValues.entrySet()) {
            builder.nutrition(entry.getKey(), (int)(entry.getValue() * factor));
        }
        return builder.hydration((int)(hydrationValue * factor)).build();
    }

    /**
     * ビルダークラス
     */
    public static class Builder {
        private final Map<NutritionType, Integer> nutritionValues = new EnumMap<>(NutritionType.class);
        private int hydrationValue = 0;

        public Builder nutrition(NutritionType type, int value) {
            this.nutritionValues.put(type, Math.max(0, Math.min(100, value)));
            return this;
        }

        // 五大栄養素のショートカット
        public Builder carbohydrate(int value) {
            return nutrition(NutritionType.CARBOHYDRATE, value);
        }

        public Builder protein(int value) {
            return nutrition(NutritionType.PROTEIN, value);
        }

        public Builder lipid(int value) {
            return nutrition(NutritionType.LIPID, value);
        }

        public Builder vitamin(int value) {
            return nutrition(NutritionType.VITAMIN, value);
        }

        public Builder mineral(int value) {
            return nutrition(NutritionType.MINERAL, value);
        }

        public Builder hydration(int value) {
            this.hydrationValue = Math.max(0, Math.min(100, value));
            return this;
        }

        public FoodNutritionData build() {
            return new FoodNutritionData(this);
        }

    }



    /**
     * 新しいビルダーを作成
     */
    public static Builder builder() {
        return new Builder();
    }
}