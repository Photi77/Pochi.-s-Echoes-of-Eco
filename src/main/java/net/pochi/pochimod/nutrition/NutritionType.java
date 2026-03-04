package net.pochi.pochimod.nutrition;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 栄養素の種類を定義するEnum
 * 将来的な栄養素追加に対応できるよう設計
 */
public enum NutritionType {
    CARBOHYDRATE("carbohydrate", ChatFormatting.YELLOW, 0xFFDD55),    // 炭水化物 - 黄色
    PROTEIN("protein", ChatFormatting.RED, 0xFF5555),                 // タンパク質 - 赤色
    LIPID("lipid", ChatFormatting.GOLD, 0xFFAA00),                    // 脂質 - オレンジ
    VITAMIN("vitamin", ChatFormatting.GREEN, 0x55FF55),               // ビタミン - 緑色
    MINERAL("mineral", ChatFormatting.AQUA, 0x55FFFF);                // ミネラル - 水色

    private final String name;
    private final ChatFormatting formatting;
    private final int color;
    private final String translationKey;

    private static final Map<String, NutritionType> BY_NAME = Arrays.stream(values())
            .collect(Collectors.toMap(NutritionType::getName, Function.identity()));

    NutritionType(String name, ChatFormatting formatting, int color) {
        this.name = name;
        this.formatting = formatting;
        this.color = color;
        this.translationKey = "nutrition.type." + name;
    }

    public String getName() {
        return name;
    }

    public ChatFormatting getFormatting() {
        return formatting;
    }

    /**
     * UIでの表示色（RGB）を取得
     */
    public int getColor() {
        return color;
    }

    public String getTranslationKey() {
        return translationKey;
    }

    public MutableComponent getDisplayName() {
        return Component.translatable(translationKey).withStyle(formatting);
    }

    public static NutritionType fromName(String name) {
        return BY_NAME.get(name);
    }

    public static NutritionType[] getAllTypes() {
        return values();
    }

    public static int getTypeCount() {
        return values().length;
    }

    public static final int DEFAULT_VALUE = 100;  // 健全ゾーン中央
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 150;      // 過剰ゾーン含む上限
}