package net.pochi.pochimod.config;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * バイタルシステムの設定（バニラ準拠デフォルト値）
 */
public class VitalConfig {

    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    // ========== 時間経過での自然減少 ==========
    public static final ModConfigSpec.IntValue HYDRATION_DEPLETION_RATE;
    public static final ModConfigSpec.IntValue NUTRITION_DEPLETION_RATE;
    public static final ModConfigSpec.IntValue DEPLETION_INTERVAL;

    // ========== アクション消費の有効/無効 ==========
    public static final ModConfigSpec.BooleanValue ENABLE_ACTION_CONSUMPTION;

    // ========== ダッシュ消費設定 ==========
    public static final ModConfigSpec.IntValue SPRINT_NUTRITION_INTERVAL;
    public static final ModConfigSpec.IntValue SPRINT_CARBOHYDRATE_COST;
    public static final ModConfigSpec.IntValue SPRINT_LIPID_COST;
    public static final ModConfigSpec.IntValue SPRINT_HYDRATION_INTERVAL;
    public static final ModConfigSpec.IntValue SPRINT_HYDRATION_COST;

    // ========== 水泳消費設定 ==========
    public static final ModConfigSpec.IntValue SWIM_NUTRITION_INTERVAL;
    public static final ModConfigSpec.IntValue SWIM_LIPID_COST;
    public static final ModConfigSpec.IntValue SWIM_CARBOHYDRATE_COST;
    public static final ModConfigSpec.IntValue SWIM_MINERAL_COST;
    public static final ModConfigSpec.IntValue SWIM_HYDRATION_INTERVAL;
    public static final ModConfigSpec.IntValue SWIM_HYDRATION_COST;

    // ========== ジャンプ消費設定 ==========
    public static final ModConfigSpec.IntValue JUMP_COUNTER_THRESHOLD;
    public static final ModConfigSpec.IntValue JUMP_CARBOHYDRATE_COST;
    public static final ModConfigSpec.IntValue JUMP_PROTEIN_COST;

    // ========== ブロック破壊消費設定 ==========
    public static final ModConfigSpec.IntValue BLOCK_BREAK_COUNTER_THRESHOLD;
    public static final ModConfigSpec.IntValue BLOCK_BREAK_PROTEIN_COST;
    public static final ModConfigSpec.IntValue BLOCK_BREAK_CARBOHYDRATE_COST;
    public static final ModConfigSpec.IntValue BLOCK_BREAK_MINERAL_COST;

    // ========== 攻撃消費設定 ==========
    public static final ModConfigSpec.IntValue ATTACK_COUNTER_THRESHOLD;
    public static final ModConfigSpec.IntValue ATTACK_PROTEIN_COST;
    public static final ModConfigSpec.IntValue ATTACK_CARBOHYDRATE_COST;
    public static final ModConfigSpec.IntValue ATTACK_VITAMIN_COST;

    // ========== ダメージ受けた時の消費設定 ==========
    public static final ModConfigSpec.IntValue DAMAGE_PROTEIN_COST;
    public static final ModConfigSpec.IntValue DAMAGE_VITAMIN_COST;
    public static final ModConfigSpec.IntValue DAMAGE_MINERAL_COST;

    // ========== クライミング消費設定 ==========
    public static final ModConfigSpec.IntValue CLIMB_NUTRITION_INTERVAL;
    public static final ModConfigSpec.IntValue CLIMB_PROTEIN_COST;
    public static final ModConfigSpec.IntValue CLIMB_CARBOHYDRATE_COST;

    // ========== 難易度スケーリング ==========
    public static final ModConfigSpec.BooleanValue SCALE_WITH_DIFFICULTY;

    // ========== デバフ効果 ==========
    public static final ModConfigSpec.BooleanValue ENABLE_DEFICIENCY_EFFECTS;
    public static final ModConfigSpec.BooleanValue ENABLE_HYDRATION_DEBUFFS;
    public static final ModConfigSpec.BooleanValue ENABLE_NUTRITION_DEBUFFS;
    public static final ModConfigSpec.BooleanValue ENABLE_EXCESS_EFFECTS;

    //モード
    //public static final ModConfigSpec.ConfigValue<HungerSystemMode> HUNGER_SYSTEM_MODE;

    static {
        BUILDER.push("Vital System Configuration");

        // ===== 時間経過での自然減少 =====
        BUILDER.comment("Natural Depletion Over Time");
        BUILDER.comment("Based on vanilla Minecraft food system (107 minutes to deplete fully when idle)");

        HYDRATION_DEPLETION_RATE = BUILDER
                .comment("How much hydration depletes per interval (default: 1)")
                .defineInRange("hydrationDepletionRate", 1, 0, 100);

        NUTRITION_DEPLETION_RATE = BUILDER
                .comment("How much nutrition depletes per interval (default: 1)")
                .comment("Applied to all nutrients equally including lipid")
                .defineInRange("nutritionDepletionRate", 1, 0, 100);

        DEPLETION_INTERVAL = BUILDER
                .comment("Interval between depletions in ticks (20 ticks = 1 second)")
                .comment("Default: 2140 ticks = 107 seconds (vanilla equivalent)")
                .defineInRange("depletionInterval", 2140, 20, 24000);

        // ===== アクション消費 =====
        BUILDER.comment(" ");
        BUILDER.comment("Action-Based Consumption");

        ENABLE_ACTION_CONSUMPTION = BUILDER
                .comment("Enable additional consumption during actions")
                .define("enableActionConsumption", true);

        // ===== ダッシュ =====
        BUILDER.comment(" ");
        BUILDER.comment("Sprint Settings (continuous running)");
        BUILDER.comment("Vanilla: Can sprint for ~13 minutes continuously");

        SPRINT_NUTRITION_INTERVAL = BUILDER
                .comment("How often to consume nutrition while sprinting (ticks)")
                .comment("Default: 100 = 5 seconds")
                .defineInRange("sprintNutritionInterval", 100, 1, 1200);

        SPRINT_CARBOHYDRATE_COST = BUILDER
                .comment("Carbohydrate cost per sprint interval (primary fast fuel)")
                .defineInRange("sprintCarbohydrateCost", 2, 0, 100);

        SPRINT_LIPID_COST = BUILDER
                .comment("Lipid cost per sprint interval (aerobic fat burning)")
                .comment("Sprinting is the primary way to reduce lipid in daily gameplay.")
                .comment("Swimming (swimLipidCost) should be higher for dedicated fat burning.")
                .defineInRange("sprintLipidCost", 1, 0, 100);

        SPRINT_HYDRATION_INTERVAL = BUILDER
                .comment("How often to consume hydration while sprinting (ticks)")
                .comment("Default: 160 = 8 seconds (vanilla equivalent)")
                .defineInRange("sprintHydrationInterval", 160, 20, 1200);

        SPRINT_HYDRATION_COST = BUILDER
                .comment("Hydration cost per sprint interval")
                .defineInRange("sprintHydrationCost", 1, 0, 100);

        // ===== 水泳 =====
        BUILDER.comment(" ");
        BUILDER.comment("Swim Settings");
        BUILDER.comment("Vanilla: Very slow consumption while swimming");

        SWIM_NUTRITION_INTERVAL = BUILDER
                .comment("How often to consume nutrition while swimming (ticks)")
                .comment("Default: 40 = 2 seconds")
                .defineInRange("swimNutritionInterval", 40, 1, 1200);

        SWIM_LIPID_COST = BUILDER
                .comment("Lipid cost per swim interval (primary depletion source for lipid)")
                .defineInRange("swimLipidCost", 2, 0, 100);

        SWIM_CARBOHYDRATE_COST = BUILDER
                .comment("Carbohydrate cost per swim interval")
                .defineInRange("swimCarbohydrateCost", 0, 0, 100);

        SWIM_MINERAL_COST = BUILDER
                .comment("Mineral cost per swim interval (electrolyte loss from exertion)")
                .defineInRange("swimMineralCost", 1, 0, 100);

        SWIM_HYDRATION_INTERVAL = BUILDER
                .comment("How often to consume hydration while swimming (ticks)")
                .comment("Default: 800 = 40 seconds (vanilla equivalent)")
                .defineInRange("swimHydrationInterval", 800, 20, 2400);

        SWIM_HYDRATION_COST = BUILDER
                .comment("Hydration cost per swim interval")
                .defineInRange("swimHydrationCost", 1, 0, 100);

        // ===== ジャンプ =====
        BUILDER.comment(" ");
        BUILDER.comment("Jump Settings");
        BUILDER.comment("Vanilla: ~800 jumps to deplete food bar");

        JUMP_COUNTER_THRESHOLD = BUILDER
                .comment("Consume nutrition every N jumps")
                .comment("Default: 5")
                .defineInRange("jumpCounterThreshold", 5, 1, 100);

        JUMP_CARBOHYDRATE_COST = BUILDER
                .comment("Carbohydrate cost per jump threshold")
                .defineInRange("jumpCarbohydrateCost", 1, 0, 100);

        JUMP_PROTEIN_COST = BUILDER
                .comment("Protein cost per jump threshold (leg muscle exertion)")
                .defineInRange("jumpProteinCost", 1, 0, 100);

        // ===== ブロック破壊 =====
        BUILDER.comment(" ");
        BUILDER.comment("Block Break Settings");
        BUILDER.comment("Vanilla: ~8000 block (without tools) to deplete food bar");

        BLOCK_BREAK_COUNTER_THRESHOLD = BUILDER
                .comment("Consume nutrition every N block broken")
                .comment("Default: 10 (fast feedback for mining loops)")
                .defineInRange("blockBreakCounterThreshold", 10, 1, 1000);

        BLOCK_BREAK_PROTEIN_COST = BUILDER
                .comment("Protein cost per block threshold (muscle use)")
                .defineInRange("blockBreakProteinCost", 2, 0, 100);

        BLOCK_BREAK_CARBOHYDRATE_COST = BUILDER
                .comment("Carbohydrate cost per block threshold (energy)")
                .defineInRange("blockBreakCarbohydrateCost", 1, 0, 100);

        BLOCK_BREAK_MINERAL_COST = BUILDER
                .comment("Mineral cost per block threshold (bone/nerve load)")
                .defineInRange("blockBreakMineralCost", 2, 0, 100);

        // ===== 攻撃 =====
        BUILDER.comment(" ");
        BUILDER.comment("Attack Settings");
        BUILDER.comment("Vanilla: ~400 attacks to deplete food bar");

        ATTACK_COUNTER_THRESHOLD = BUILDER
                .comment("Consume nutrition every N attacks")
                .comment("Default: 3")
                .defineInRange("attackCounterThreshold", 3, 1, 100);

        ATTACK_PROTEIN_COST = BUILDER
                .comment("Protein cost per attack threshold (muscle exertion)")
                .defineInRange("attackProteinCost", 2, 0, 100);

        ATTACK_CARBOHYDRATE_COST = BUILDER
                .comment("Carbohydrate cost per attack threshold (burst energy)")
                .defineInRange("attackCarbohydrateCost", 2, 0, 100);

        ATTACK_VITAMIN_COST = BUILDER
                .comment("Vitamin cost per attack threshold (stress response, immune activation)")
                .defineInRange("attackVitaminCost", 1, 0, 100);

        // ===== ダメージ =====
        BUILDER.comment(" ");
        BUILDER.comment("Damage Received Settings");

        DAMAGE_PROTEIN_COST = BUILDER
                .comment("Protein cost when taking damage (tissue repair demand)")
                .defineInRange("damageProteinCost", 3, 0, 100);

        DAMAGE_VITAMIN_COST = BUILDER
                .comment("Vitamin cost when taking damage (immune/antioxidant response)")
                .defineInRange("damageVitaminCost", 3, 0, 100);

        DAMAGE_MINERAL_COST = BUILDER
                .comment("Mineral cost when taking damage (electrolyte/bone stress)")
                .defineInRange("damageMineralCost", 2, 0, 100);

        // ===== クライミング =====
        BUILDER.comment(" ");
        BUILDER.comment("Climbing Settings (ladders, vines)");

        CLIMB_NUTRITION_INTERVAL = BUILDER
                .comment("How often to consume nutrition while climbing (ticks)")
                .comment("Default: 60 = 3 seconds")
                .defineInRange("climbNutritionInterval", 60, 1, 1200);

        CLIMB_PROTEIN_COST = BUILDER
                .comment("Protein cost per climb interval (upper body muscle use)")
                .defineInRange("climbProteinCost", 2, 0, 100);

        CLIMB_CARBOHYDRATE_COST = BUILDER
                .comment("Carbohydrate cost per climb interval (sustained effort)")
                .defineInRange("climbCarbohydrateCost", 1, 0, 100);

        // ===== 難易度スケーリング =====
        BUILDER.comment(" ");
        BUILDER.comment("Difficulty Scaling");

        SCALE_WITH_DIFFICULTY = BUILDER
                .comment("Scale depletion rates with game difficulty")
                .comment("Peaceful=0.5x, Easy=1x, Normal=1.5x, Hard=2x")
                .define("scaleWithDifficulty", true);

        // ===== デバフ効果 =====
        BUILDER.comment(" ");
        BUILDER.comment("Deficiency Effects (debuffs when stats are low)");

        ENABLE_DEFICIENCY_EFFECTS = BUILDER
                .comment("Enable debuff effects when vital stats are low")
                .define("enableDeficiencyEffects", true);

        ENABLE_HYDRATION_DEBUFFS = BUILDER
                .comment("Enable debuffs from low hydration")
                .define("enableHydrationDebuffs", true);

        ENABLE_NUTRITION_DEBUFFS = BUILDER
                .comment("Enable debuffs from low nutrition")
                .define("enableNutritionDebuffs", true);

        ENABLE_EXCESS_EFFECTS = BUILDER
                .comment("Enable debuffs from excess nutrition (over 120/150)")
                .comment("Lipid excess causes movement slowdown (obesity), carb excess causes hunger, etc.")
                .define("enableExcessEffects", true);

        ////モード
        //BUILDER.comment(" ");
        //BUILDER.comment("Hunger System Integration");
        //BUILDER.comment("How to handle vanilla hunger system:");
        //BUILDER.comment("  vanilla: Vanilla hunger works normally (not recommended with this mod)");
        //BUILDER.comment("  coexist: Both hunger and nutrition work (best for mod compatibility)");
        //BUILDER.comment("  nutrition_only: Disable hunger, use nutrition only (single-player recommended)");
//
        //HUNGER_SYSTEM_MODE = BUILDER
        //        .comment("Hunger system mode")
        //        .defineEnum("hungerSystemMode", HungerSystemMode.COEXIST);


        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}