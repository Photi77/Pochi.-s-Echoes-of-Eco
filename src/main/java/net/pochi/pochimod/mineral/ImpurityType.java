package net.pochi.pochimod.mineral;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.pochi.pochimod.mineral.tools.PenaltyType;

/**
 * 川採掘鉱物の不純物タイプ定義
 * 9種類の不純物それぞれにエフェクトと付与条件を定義
 */
public enum ImpurityType {

    // ---- 定義: (id, trigger, baseColor, mainStat, penaltyType) ----
    //                                                メイン強化       デメリット
    IRON_3    ("iron_3",    EffectTrigger.EQUIPPED,  0xC0392B, MainStat.ATTACK,        PenaltyType.MINING_SPEED),
    IRON_2    ("iron_2",    EffectTrigger.EQUIPPED,  0x7F8C8D, MainStat.DURABILITY,    PenaltyType.ATTACK),
    CHROMIUM  ("chromium",  EffectTrigger.EQUIPPED,  0x27AE60, MainStat.ATTACK,        PenaltyType.DURABILITY),
    TITANIUM  ("titanium",  EffectTrigger.EQUIPPED,  0xF0F0F0, MainStat.DURABILITY,    PenaltyType.ATTACK),
    MANGANESE ("manganese", EffectTrigger.EQUIPPED,  0xFF69B4, MainStat.DEFENSE,       PenaltyType.MINING_SPEED),
    COPPER    ("copper",    EffectTrigger.EQUIPPED,  0xE67E22, MainStat.KNOCKBACK_RES, PenaltyType.DEFENSE),
    VANADIUM  ("vanadium",  EffectTrigger.ON_HIT,    0x6C3483, MainStat.ENCHANTMENT,   PenaltyType.DURABILITY),
    CARBON    ("carbon",    EffectTrigger.HELD,      0x1C2833, MainStat.MINING_SPEED,  PenaltyType.DURABILITY),
    URANIUM   ("uranium",   EffectTrigger.HELD,      0x00FF41, MainStat.ENCHANTABILITY,PenaltyType.DEFENSE);

    // ---- フィールド ----
    public final String      id;
    public final EffectTrigger trigger;
    public final int         baseColor;
    public final MainStat    mainStat;    // このタイプが主不純物の場合に強化するステータス
    public final PenaltyType penaltyType; // このタイプが持つデメリット種別

    ImpurityType(String id, EffectTrigger trigger, int baseColor,
                 MainStat mainStat, PenaltyType penaltyType) {
        this.id          = id;
        this.trigger     = trigger;
        this.baseColor   = baseColor;
        this.mainStat    = mainStat;
        this.penaltyType = penaltyType;
    }

    public static ImpurityType fromId(String id) {
        for (ImpurityType type : values()) {
            if (type.id.equals(id)) return type;
        }
        throw new IllegalArgumentException("Unknown impurity type: " + id);
    }

    public Holder<MobEffect> getMobEffect() {
        return switch (this) {
            case IRON_3    -> MobEffects.DAMAGE_BOOST;
            case IRON_2    -> MobEffects.DAMAGE_RESISTANCE;
            case CHROMIUM  -> MobEffects.MOVEMENT_SPEED;
            case TITANIUM  -> MobEffects.DIG_SPEED;
            case MANGANESE -> MobEffects.REGENERATION;
            case COPPER    -> MobEffects.JUMP;
            case VANADIUM  -> MobEffects.POISON;
            case CARBON    -> MobEffects.DIG_SPEED;
            case URANIUM   -> MobEffects.NIGHT_VISION;
        };
    }

    /**
     * 主不純物として強化するステータス種別
     */
    public enum MainStat {
        ATTACK,          // 攻撃力
        DURABILITY,      // 耐久値
        MINING_SPEED,    // 採掘速度
        DEFENSE,         // 防御値
        KNOCKBACK_RES,   // ノックバック耐性
        ENCHANTMENT,     // 魔法付与強化（vanadium）
        ENCHANTABILITY   // エンチャント適性（uranium）
    }

    /**
     * エフェクト付与条件
     */
    public enum EffectTrigger {
        EQUIPPED,
        ON_HIT,
        HELD
    }
}
