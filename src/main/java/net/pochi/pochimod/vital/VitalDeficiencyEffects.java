package net.pochi.pochimod.vital;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.pochi.pochimod.nutrition.NutritionType;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * 栄養不足・過剰時のデバフ効果を定義
 *
 * [ゾーン設計 (MAX=150)]
 *   0   ~ 5   : SEVERE  deficiency (重篤な欠乏)
 *   5   ~ 15  : MODERATE deficiency
 *   15  ~ 30  : MILD    deficiency
 *   30  ~ 120 : HEALTHY  (健全ゾーン)
 *   120 ~ 135 : MILD    excess (軽度の過剰)
 *   135 ~ 145 : MODERATE excess
 *   145 ~ 150 : SEVERE  excess (重篤な過剰)
 */
public class VitalDeficiencyEffects {

    // =========================================================================
    // 欠乏レベル (低値側)
    // =========================================================================

    public enum DeficiencyLevel {
        NONE    (200, 30),   // 30超 = 欠乏なし (MAX_VALUE より大きい値で受け止める)
        MILD    (30,  15),
        MODERATE(15,   5),
        SEVERE  ( 5,   0);

        private final int maxValue;   // このレベルの上限 (exclusive → ≤maxValue)
        private final int minValue;   // このレベルの下限 (exclusive)

        DeficiencyLevel(int maxValue, int minValue) {
            this.maxValue = maxValue;
            this.minValue = minValue;
        }

        public static DeficiencyLevel fromValue(int value) {
            for (DeficiencyLevel level : values()) {
                if (value > level.minValue && value <= level.maxValue) {
                    return level;
                }
            }
            return SEVERE;
        }

        public boolean isSevere()   { return this == SEVERE; }
        public boolean isModerate() { return this == MODERATE; }
        public boolean isMild()     { return this == MILD; }
    }

    // =========================================================================
    // 過剰レベル (高値側)
    // =========================================================================

    public enum ExcessLevel {
        NONE,      // ≤ 120
        MILD,      // 121 ~ 135
        MODERATE,  // 136 ~ 145
        SEVERE;    // 146 ~ 150

        public static ExcessLevel fromValue(int value) {
            if (value > 145) return SEVERE;
            if (value > 135) return MODERATE;
            if (value > 120) return MILD;
            return NONE;
        }
    }

    // =========================================================================
    // 欠乏デバフ (水分)
    // =========================================================================

    public static class HydrationEffects {
        public static Holder<MobEffect> getMildEffect()        { return MobEffects.DIG_SLOWDOWN; }
        public static int               getMildAmplifier()     { return 0; }
        public static Holder<MobEffect> getModerateEffect()    { return MobEffects.MOVEMENT_SLOWDOWN; }
        public static int               getModerateAmplifier() { return 0; }
        public static Holder<MobEffect> getSevereEffect()      { return MobEffects.WEAKNESS; }
        public static int               getSevereAmplifier()   { return 1; }
    }

    // =========================================================================
    // 欠乏デバフ (栄養素)
    // =========================================================================

    private static final Map<NutritionType, NutritionDeficiencyEffect> NUTRITION_EFFECTS =
            new EnumMap<>(NutritionType.class);

    static {
        NUTRITION_EFFECTS.put(NutritionType.CARBOHYDRATE, new NutritionDeficiencyEffect(
                MobEffects.DIG_SLOWDOWN,       // mild:     採掘遅延
                MobEffects.MOVEMENT_SLOWDOWN,  // moderate: 移動遅延
                MobEffects.WEAKNESS            // severe:   脱力
        ));

        NUTRITION_EFFECTS.put(NutritionType.PROTEIN, new NutritionDeficiencyEffect(
                MobEffects.WEAKNESS,           // mild:     脱力
                MobEffects.DIG_SLOWDOWN,       // moderate: 採掘遅延
                MobEffects.WITHER              // severe:   衰弱
        ));

        NUTRITION_EFFECTS.put(NutritionType.LIPID, new NutritionDeficiencyEffect(
                MobEffects.HUNGER,             // mild:     空腹感
                MobEffects.MOVEMENT_SLOWDOWN,  // moderate: 移動遅延
                MobEffects.WEAKNESS            // severe:   脱力
        ));

        NUTRITION_EFFECTS.put(NutritionType.VITAMIN, new NutritionDeficiencyEffect(
                MobEffects.HUNGER,             // mild:     食欲不振
                MobEffects.CONFUSION,          // moderate: 混乱
                MobEffects.POISON              // severe:   ポイズン
        ));

        NUTRITION_EFFECTS.put(NutritionType.MINERAL, new NutritionDeficiencyEffect(
                MobEffects.DIG_SLOWDOWN,       // mild:     採掘遅延
                MobEffects.WEAKNESS,           // moderate: 脱力
                MobEffects.MOVEMENT_SLOWDOWN   // severe:   移動遅延
        ));
    }

    public static NutritionDeficiencyEffect getNutritionEffect(NutritionType type) {
        return NUTRITION_EFFECTS.get(type);
    }

    public static class NutritionDeficiencyEffect {
        private final Holder<MobEffect> mildEffect;
        private final Holder<MobEffect> moderateEffect;
        private final Holder<MobEffect> severeEffect;

        public NutritionDeficiencyEffect(Holder<MobEffect> mild,
                                         Holder<MobEffect> moderate,
                                         Holder<MobEffect> severe) {
            this.mildEffect     = mild;
            this.moderateEffect = moderate;
            this.severeEffect   = severe;
        }

        public Holder<MobEffect> getEffect(DeficiencyLevel level) {
            return switch (level) {
                case MILD     -> mildEffect;
                case MODERATE -> moderateEffect;
                case SEVERE   -> severeEffect;
                default       -> null;
            };
        }

        public int getAmplifier(DeficiencyLevel level) {
            return switch (level) {
                case MILD     -> 0;
                case MODERATE -> 0;
                case SEVERE   -> 1;
                default       -> 0;
            };
        }
    }

    // =========================================================================
    // 過剰デバフ (栄養素)
    // =========================================================================

    /** 1つのエフェクト + アンプリファイアのペア */
    public record EffectEntry(Holder<MobEffect> effect, int amplifier) {}

    private static final Map<NutritionType, NutritionExcessEffect> EXCESS_EFFECTS =
            new EnumMap<>(NutritionType.class);

    static {
        // 脂質過剰: 肥満 → 移動速度低下
        // Mild:     少し重い     MOVEMENT_SLOWDOWN I
        // Moderate: かなり遅い  MOVEMENT_SLOWDOWN II
        // Severe:   肥満状態    MOVEMENT_SLOWDOWN III + DIG_SLOWDOWN I
        EXCESS_EFFECTS.put(NutritionType.LIPID, new NutritionExcessEffect(
                List.of(new EffectEntry(MobEffects.MOVEMENT_SLOWDOWN, 0)),
                List.of(new EffectEntry(MobEffects.MOVEMENT_SLOWDOWN, 1)),
                List.of(
                        new EffectEntry(MobEffects.MOVEMENT_SLOWDOWN, 2),
                        new EffectEntry(MobEffects.DIG_SLOWDOWN,       0)
                )
        ));

        // 炭水化物過剰: 血糖値スパイク → 空腹感(インスリン反応)
        EXCESS_EFFECTS.put(NutritionType.CARBOHYDRATE, new NutritionExcessEffect(
                List.of(new EffectEntry(MobEffects.HUNGER, 0)),
                List.of(new EffectEntry(MobEffects.HUNGER, 1)),
                List.of(
                        new EffectEntry(MobEffects.HUNGER,    1),
                        new EffectEntry(MobEffects.CONFUSION, 0)
                )
        ));

        // タンパク質過剰: 腎臓負担・吐き気
        EXCESS_EFFECTS.put(NutritionType.PROTEIN, new NutritionExcessEffect(
                List.of(new EffectEntry(MobEffects.CONFUSION, 0)),
                List.of(new EffectEntry(MobEffects.CONFUSION, 0)),
                List.of(new EffectEntry(MobEffects.POISON,    0))
        ));

        // ビタミン過剰: 過剰症(ビタミンA・D中毒)
        EXCESS_EFFECTS.put(NutritionType.VITAMIN, new NutritionExcessEffect(
                List.of(new EffectEntry(MobEffects.POISON, 0)),
                List.of(new EffectEntry(MobEffects.POISON, 0)),
                List.of(new EffectEntry(MobEffects.POISON, 1))
        ));

        // ミネラル過剰: 重金属様症状 → 脱力
        EXCESS_EFFECTS.put(NutritionType.MINERAL, new NutritionExcessEffect(
                List.of(new EffectEntry(MobEffects.WEAKNESS, 0)),
                List.of(new EffectEntry(MobEffects.WEAKNESS, 1)),
                List.of(
                        new EffectEntry(MobEffects.WEAKNESS,          1),
                        new EffectEntry(MobEffects.MOVEMENT_SLOWDOWN, 0)
                )
        ));
    }

    public static NutritionExcessEffect getNutritionExcessEffect(NutritionType type) {
        return EXCESS_EFFECTS.get(type);
    }

    public static class NutritionExcessEffect {
        private final List<EffectEntry> mildEffects;
        private final List<EffectEntry> moderateEffects;
        private final List<EffectEntry> severeEffects;

        public NutritionExcessEffect(List<EffectEntry> mild,
                                     List<EffectEntry> moderate,
                                     List<EffectEntry> severe) {
            this.mildEffects     = mild;
            this.moderateEffects = moderate;
            this.severeEffects   = severe;
        }

        public List<EffectEntry> getEffects(ExcessLevel level) {
            return switch (level) {
                case MILD     -> mildEffects;
                case MODERATE -> moderateEffects;
                case SEVERE   -> severeEffects;
                default       -> List.of();
            };
        }
    }
}
