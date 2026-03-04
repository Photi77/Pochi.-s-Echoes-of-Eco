package net.pochi.pochimod.config;


import net.pochi.pochimod.nutrition.NutritionType;

import java.util.EnumMap;
import java.util.Map;

/**
 * 行動別の栄養消費設定（Config完全対応版）
 */
public class ActionConsumptionConfig {

    /**
     * ダッシュ時の栄養消費
     */
    public static Map<NutritionType, Integer> getSprintNutritionConsumption() {
        Map<NutritionType, Integer> consumption = new EnumMap<>(NutritionType.class);

        int carbohydrateCost = VitalConfig.SPRINT_CARBOHYDRATE_COST.get();
        if (carbohydrateCost > 0) {
            consumption.put(NutritionType.CARBOHYDRATE, carbohydrateCost);
        }

        int lipidCost = VitalConfig.SPRINT_LIPID_COST.get();
        if (lipidCost > 0) {
            consumption.put(NutritionType.LIPID, lipidCost);
        }

        return consumption;
    }

    /**
     * 水泳時の栄養消費
     */
    public static Map<NutritionType, Integer> getSwimNutritionConsumption() {
        Map<NutritionType, Integer> consumption = new EnumMap<>(NutritionType.class);

        int lipidCost = VitalConfig.SWIM_LIPID_COST.get();
        if (lipidCost > 0) {
            consumption.put(NutritionType.LIPID, lipidCost);
        }

        int carbohydrateCost = VitalConfig.SWIM_CARBOHYDRATE_COST.get();
        if (carbohydrateCost > 0) {
            consumption.put(NutritionType.CARBOHYDRATE, carbohydrateCost);
        }

        int mineralCost = VitalConfig.SWIM_MINERAL_COST.get();
        if (mineralCost > 0) {
            consumption.put(NutritionType.MINERAL, mineralCost);
        }

        return consumption;
    }

    /**
     * ジャンプ時の栄養消費
     */
    public static Map<NutritionType, Integer> getJumpNutritionConsumption() {
        Map<NutritionType, Integer> consumption = new EnumMap<>(NutritionType.class);

        int carbohydrateCost = VitalConfig.JUMP_CARBOHYDRATE_COST.get();
        if (carbohydrateCost > 0) {
            consumption.put(NutritionType.CARBOHYDRATE, carbohydrateCost);
        }

        int proteinCost = VitalConfig.JUMP_PROTEIN_COST.get();
        if (proteinCost > 0) {
            consumption.put(NutritionType.PROTEIN, proteinCost);
        }

        return consumption;
    }

    /**
     * ブロック破壊時の栄養消費
     */
    public static Map<NutritionType, Integer> getBlockBreakNutritionConsumption() {
        Map<NutritionType, Integer> consumption = new EnumMap<>(NutritionType.class);

        int proteinCost = VitalConfig.BLOCK_BREAK_PROTEIN_COST.get();
        if (proteinCost > 0) {
            consumption.put(NutritionType.PROTEIN, proteinCost);
        }

        int carbohydrateCost = VitalConfig.BLOCK_BREAK_CARBOHYDRATE_COST.get();
        if (carbohydrateCost > 0) {
            consumption.put(NutritionType.CARBOHYDRATE, carbohydrateCost);
        }

        int mineralCost = VitalConfig.BLOCK_BREAK_MINERAL_COST.get();
        if (mineralCost > 0) {
            consumption.put(NutritionType.MINERAL, mineralCost);
        }

        return consumption;
    }

    /**
     * 攻撃時の栄養消費
     */
    public static Map<NutritionType, Integer> getAttackNutritionConsumption() {
        Map<NutritionType, Integer> consumption = new EnumMap<>(NutritionType.class);

        int proteinCost = VitalConfig.ATTACK_PROTEIN_COST.get();
        if (proteinCost > 0) {
            consumption.put(NutritionType.PROTEIN, proteinCost);
        }

        int carbohydrateCost = VitalConfig.ATTACK_CARBOHYDRATE_COST.get();
        if (carbohydrateCost > 0) {
            consumption.put(NutritionType.CARBOHYDRATE, carbohydrateCost);
        }

        int vitaminCost = VitalConfig.ATTACK_VITAMIN_COST.get();
        if (vitaminCost > 0) {
            consumption.put(NutritionType.VITAMIN, vitaminCost);
        }

        return consumption;
    }

    /**
     * ダメージ受けた時の栄養消費
     */
    public static Map<NutritionType, Integer> getDamageNutritionConsumption() {
        Map<NutritionType, Integer> consumption = new EnumMap<>(NutritionType.class);

        int proteinCost = VitalConfig.DAMAGE_PROTEIN_COST.get();
        if (proteinCost > 0) {
            consumption.put(NutritionType.PROTEIN, proteinCost);
        }

        int vitaminCost = VitalConfig.DAMAGE_VITAMIN_COST.get();
        if (vitaminCost > 0) {
            consumption.put(NutritionType.VITAMIN, vitaminCost);
        }

        int mineralCost = VitalConfig.DAMAGE_MINERAL_COST.get();
        if (mineralCost > 0) {
            consumption.put(NutritionType.MINERAL, mineralCost);
        }

        return consumption;
    }

    /**
     * クライミング時の栄養消費
     */
    public static Map<NutritionType, Integer> getClimbNutritionConsumption() {
        Map<NutritionType, Integer> consumption = new EnumMap<>(NutritionType.class);

        int proteinCost = VitalConfig.CLIMB_PROTEIN_COST.get();
        if (proteinCost > 0) {
            consumption.put(NutritionType.PROTEIN, proteinCost);
        }

        int carbohydrateCost = VitalConfig.CLIMB_CARBOHYDRATE_COST.get();
        if (carbohydrateCost > 0) {
            consumption.put(NutritionType.CARBOHYDRATE, carbohydrateCost);
        }

        return consumption;
    }
}