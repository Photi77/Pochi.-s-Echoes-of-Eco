package net.pochi.pochimod.nutrition;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

import java.util.EnumMap;
import java.util.Map;

/**
 * プレイヤーのバイタルデータ（水分・栄養）を統合管理
 * 既存の水分ゲージシステムと新規栄養システムを一元管理
 */
public class PlayerVitalData implements INBTSerializable<CompoundTag> {

    // 水分値（既存システム）
    private int hydration;
    private static final int MAX_HYDRATION = 100;
    private static final int MIN_HYDRATION = 0;

    // 栄養値（新規システム）- EnumMapで効率的に管理
    private final Map<NutritionType, Integer> nutritionValues;

    // NBT保存用キー
    private static final String NBT_HYDRATION = "Hydration";
    private static final String NBT_NUTRITION_PREFIX = "Nutrition_";

    /**
     * デフォルトコンストラクタ
     * 初期値で初期化
     */
    public PlayerVitalData() {
        this.hydration = MAX_HYDRATION ; // 初期値50
        this.nutritionValues = new EnumMap<>(NutritionType.class);

        // 全栄養素をデフォルト値で初期化
        for (NutritionType type : NutritionType.getAllTypes()) {
            nutritionValues.put(type, NutritionType.DEFAULT_VALUE);
        }
    }

    // ========== 水分管理メソッド（既存システム互換） ==========

    /**
     * 水分値を取得
     */
    public int getHydration() {
        return hydration;
    }

    /**
     * 水分値を設定
     */
    public void setHydration(int value) {
        this.hydration = clamp(value, MIN_HYDRATION, MAX_HYDRATION);
    }

    /**
     * 水分を追加
     */
    public void addHydration(int amount) {
        setHydration(this.hydration + amount);
    }

    /**
     * 水分を減少
     */
    public void consumeHydration(int amount) {
        setHydration(this.hydration - amount);
    }

    /**
     * 水分が最大値か
     */
    public boolean isHydrationFull() {
        return hydration >= MAX_HYDRATION;
    }

    // ========== 栄養管理メソッド ==========

    /**
     * 特定栄養素の値を取得
     */
    public int getNutrition(NutritionType type) {
        return nutritionValues.getOrDefault(type, NutritionType.DEFAULT_VALUE);
    }

    /**
     * 特定栄養素の値を設定
     */
    public void setNutrition(NutritionType type, int value) {
        int clampedValue = clamp(value, NutritionType.MIN_VALUE, NutritionType.MAX_VALUE);
        nutritionValues.put(type, clampedValue);
    }

    /**
     * 特定栄養素を追加
     */
    public void addNutrition(NutritionType type, int amount) {
        int currentValue = getNutrition(type);
        setNutrition(type, currentValue + amount);
    }

    /**
     * 特定栄養素を減少
     */
    public void consumeNutrition(NutritionType type, int amount) {
        int currentValue = getNutrition(type);
        setNutrition(type, currentValue - amount);
    }

    /**
     * 全栄養素の値を取得（読み取り専用）
     */
    public Map<NutritionType, Integer> getAllNutrition() {
        return new EnumMap<>(nutritionValues);
    }

    /**
     * 特定栄養素が最大値か
     */
    public boolean isNutritionFull(NutritionType type) {
        return getNutrition(type) >= NutritionType.MAX_VALUE;
    }

    /**
     * 全栄養素の平均値を取得
     * 総合的な栄養状態の判定に使用
     */
    public double getAverageNutrition() {
        if (nutritionValues.isEmpty()) return 0;

        int sum = 0;
        for (int value : nutritionValues.values()) {
            sum += value;
        }
        return (double) sum / nutritionValues.size();
    }

    /**
     * 栄養バランスをチェック
     * @return true: バランスが良い（全栄養素が30以上）
     */
    public boolean isNutritionBalanced() {
        for (int value : nutritionValues.values()) {
            if (value < 30) {
                return false;
            }
        }
        return true;
    }

    /**
     * 最も不足している栄養素を取得
     * 病気システムやUI表示に使用
     */
    public NutritionType getMostDeficientNutrition() {
        NutritionType mostDeficient = null;
        int lowestValue = Integer.MAX_VALUE;

        for (Map.Entry<NutritionType, Integer> entry : nutritionValues.entrySet()) {
            if (entry.getValue() < lowestValue) {
                lowestValue = entry.getValue();
                mostDeficient = entry.getKey();
            }
        }

        return mostDeficient;
    }

    // ========== NBT保存・読み込み ==========

    /**
     * データをNBTに保存
     */
    public CompoundTag saveToNBT() {
        CompoundTag tag = new CompoundTag();

        // 水分を保存
        tag.putInt(NBT_HYDRATION, hydration);

        // 各栄養素を保存
        for (Map.Entry<NutritionType, Integer> entry : nutritionValues.entrySet()) {
            String key = NBT_NUTRITION_PREFIX + entry.getKey().getName();
            tag.putInt(key, entry.getValue());
        }

        return tag;
    }

    /**
     * NBTからデータを読み込み
     */
    public void loadFromNBT(CompoundTag tag) {
        // 水分を読み込み
        if (tag.contains(NBT_HYDRATION)) {
            this.hydration = tag.getInt(NBT_HYDRATION);
        }

        // 各栄養素を読み込み
        for (NutritionType type : NutritionType.getAllTypes()) {
            String key = NBT_NUTRITION_PREFIX + type.getName();
            if (tag.contains(key)) {
                nutritionValues.put(type, tag.getInt(key));
            } else {
                // データが存在しない場合はデフォルト値を設定
                nutritionValues.put(type, NutritionType.DEFAULT_VALUE);
            }
        }
    }

    /**
     * データをリセット（デバッグ・初期化用）
     */
    public void reset() {
        this.hydration = MAX_HYDRATION / 2;
        for (NutritionType type : NutritionType.getAllTypes()) {
            nutritionValues.put(type, NutritionType.DEFAULT_VALUE);
        }
    }

    /**
     * データをコピー
     * プレイヤー復活時などに使用
     */
    public void copyFrom(PlayerVitalData other) {
        this.hydration = other.hydration;
        this.nutritionValues.clear();
        this.nutritionValues.putAll(other.nutritionValues);
    }

    // ========== INBTSerializable (Attachment API) ==========

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        return saveToNBT();
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        loadFromNBT(nbt);
    }

    // ========== ユーティリティメソッド ==========

    /**
     * 値を範囲内にクランプ
     */
    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * デバッグ用文字列表現
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PlayerVitalData{");
        sb.append("hydration=").append(hydration);
        for (Map.Entry<NutritionType, Integer> entry : nutritionValues.entrySet()) {
            sb.append(", ").append(entry.getKey().getName())
                    .append("=").append(entry.getValue());
        }
        sb.append("}");
        return sb.toString();
    }
}
