package net.pochi.pochimod.mineral.tools;

import net.pochi.pochimod.mineral.ImpurityType;
import net.pochi.pochimod.mineral.MineralData;
import net.pochi.pochimod.mineral.MineralImpurity;

import javax.annotation.Nullable;

/**
 * 不純物タイプ + ratio → 各種装備性能値への変換
 *
 * ===== 設計方針 =====
 * 主不純物タイプが「どのステータスを伸ばすか」を決定する
 * ratio（0.0〜1.0）が「どれだけ伸ばすか」を決定する
 *
 * ===== 数値レンジ設計（主不純物 ratio=1.0 時の最大値） =====
 *
 * 武器:
 *   attack_damage  : バニラ鉄剣=6.0 を基準に +0〜+4.0
 *   attack_speed   : バニラ鉄剣=-2.4 を基準に +0〜+1.6（速くなる方向）
 *   durability     : バニラ鉄=250 を基準に ×1〜×3
 *
 * 採掘ツール:
 *   mining_speed   : バニラ鉄=6.0 を基準に +0〜+6.0
 *   harvest_level  : 0〜4（0=木, 1=石, 2=鉄, 3=ダイヤ, 4=ネザライト相当）
 *   durability     : 同上
 *
 * 防具:
 *   armor          : 各部位ベース値（Helmet:2, Chest:6, Legs:5, Boots:2）に +0〜+2
 *   toughness      : +0〜+2.0
 *   knockback_res  : +0〜+0.5
 *
 * リング（アクセサリー）:
 *   主不純物のMobEffectをレベル付きで常時付与
 */
public final class MineralStatCalculator {

    // ---- 武器ベース値 ----
    public static final float BASE_SWORD_DAMAGE       = 6.0f;  // 鉄剣相当
    public static final float BASE_AXE_DAMAGE         = 8.0f;  // 鉄斧相当
    public static final float BASE_SWORD_SPEED        = -2.4f; // 鉄剣相当（AttributeModifier加算値）
    public static final float BASE_AXE_SPEED          = -3.1f; // 鉄斧相当
    public static final int   BASE_WEAPON_DURABILITY  = 250;

    // ---- ツールベース値 ----
    public static final float BASE_PICKAXE_SPEED     = 6.0f;
    public static final float BASE_SHOVEL_SPEED      = 6.0f;
    public static final float BASE_AXE_MINE_SPEED    = 6.0f;
    public static final int   BASE_TOOL_DURABILITY   = 250;

    // ---- 防具ベース値（部位別） ----
    public static final int[] BASE_ARMOR_VALUES = {2, 6, 5, 2}; // Helmet/Chest/Legs/Boots
    public static final float BASE_TOUGHNESS    = 0.0f;

    private MineralStatCalculator() {}

    // ===================================================
    //  武器性能計算
    // ===================================================

    /**
     * 攻撃力加算値を計算
     * iron_3, vanadium, uranium → 攻撃力特化
     * それ以外 → 軽微な補正のみ
     */
    public static float calcAttackDamageBonus(MineralImpurity primary) {
        float ratio = primary.getRatio();
        return switch (primary.getType()) {
            case IRON_3    -> ratio * 4.0f;   // 最大 +4.0（合計10.0）
            case VANADIUM  -> ratio * 3.5f;   // 最大 +3.5（毒付与と両立）
            case URANIUM   -> ratio * 4.0f;   // 最大 +4.0（最強だが副不純物次第）
            case MANGANESE -> ratio * 2.0f;   // 最大 +2.0（耐久寄り）
            case COPPER    -> ratio * 1.5f;   // 最大 +1.5（範囲寄り）
            default        -> ratio * 1.0f;   // それ以外は微補正
        };
    }

    /**
     * 攻撃速度加算値を計算（負の値ほど遅い、大きいほど速い）
     * chromium, titanium → 速度特化
     */
    public static float calcAttackSpeedBonus(MineralImpurity primary) {
        float ratio = primary.getRatio();
        return switch (primary.getType()) {
            case CHROMIUM  -> ratio * 1.6f;   // 最大 +1.6（かなり速い）
            case TITANIUM  -> ratio * 1.0f;   // 最大 +1.0
            case CARBON    -> ratio * 0.8f;   // 最大 +0.8
            case IRON_2    -> ratio * 0.4f;   // 最大 +0.4
            default        -> 0.0f;
        };
    }

    /**
     * 武器耐久値を計算
     */
    public static int calcWeaponDurability(MineralImpurity primary) {
        float ratio = primary.getRatio();
        int bonus = switch (primary.getType()) {
            case IRON_2    -> (int)(ratio * 500);  // 最大 +500（耐久特化）
            case CARBON    -> (int)(ratio * 450);  // 最大 +450
            case MANGANESE -> (int)(ratio * 400);  // 最大 +400
            case TITANIUM  -> (int)(ratio * 300);  // 最大 +300
            default        -> (int)(ratio * 150);
        };
        return BASE_WEAPON_DURABILITY + bonus;
    }

    // ===================================================
    //  採掘ツール性能計算
    // ===================================================

    /**
     * 採掘速度を計算
     * titanium, carbon → 採掘特化
     */
    public static float calcMiningSpeed(MineralImpurity primary) {
        float ratio = primary.getRatio();
        float bonus = switch (primary.getType()) {
            case TITANIUM  -> ratio * 6.0f;   // 最大 12.0（ネザライト超え）
            case CARBON    -> ratio * 5.0f;   // 最大 11.0
            case CHROMIUM  -> ratio * 4.0f;   // 最大 10.0
            case IRON_3    -> ratio * 3.0f;   // 最大 9.0
            case IRON_2    -> ratio * 2.5f;   // 最大 8.5
            default        -> ratio * 1.5f;
        };
        return BASE_PICKAXE_SPEED + bonus;
    }

    /**
     * 採掘ハーベストレベルを計算（0〜4）
     * ratio 0.0〜0.25 → 1（石）
     * ratio 0.26〜0.50 → 2（鉄）
     * ratio 0.51〜0.75 → 3（ダイヤ）
     * ratio 0.76〜1.00 → 4（ネザライト相当）
     */
    public static int calcHarvestLevel(MineralImpurity primary) {
        float ratio = primary.getRatio();
        if (ratio >= 0.76f) return 4;
        if (ratio >= 0.51f) return 3;
        if (ratio >= 0.26f) return 2;
        return 1;
    }

    /**
     * ツール耐久値を計算
     */
    public static int calcToolDurability(MineralImpurity primary) {
        float ratio = primary.getRatio();
        int bonus = switch (primary.getType()) {
            case IRON_2    -> (int)(ratio * 600);
            case CARBON    -> (int)(ratio * 500);
            case MANGANESE -> (int)(ratio * 450);
            case TITANIUM  -> (int)(ratio * 350);
            default        -> (int)(ratio * 200);
        };
        return BASE_TOOL_DURABILITY + bonus;
    }

    // ===================================================
    //  防具性能計算
    // ===================================================

    /**
     * 防御値ボーナスを計算
     * 主: 防御特化系が強く伸ばす
     * 副: 主のデメリット補正 + 微増
     */
    public static int calcArmorBonus(MineralImpurity primary, @Nullable MineralImpurity secondary) {
        float ratio = primary.getRatio();
        float bonus = switch (primary.getType()) {
            case MANGANESE -> ratio * 3.0f;  // 防御↑↑ 最大+3
            case IRON_3    -> ratio * 2.5f;  // 防御↑   最大+2
            case IRON_2    -> ratio * 2.0f;  // 防御↑   最大+2
            case TITANIUM  -> ratio * 1.0f;  // タフネス寄りだが防御も微増
            default        -> ratio * 0.5f;
        };
        // 副不純物補正（PenaltyType.DEFENSEがある主不純物のデメリットを軽減）
        if (secondary != null && primary.getType().penaltyType == PenaltyType.DEFENSE) {
            bonus += secondary.getRatio() * 0.5f;
        }
        return Math.max(0, (int) bonus);
    }

    /**
     * タフネスボーナスを計算
     * 主: titanium・uranium特化
     * 副: 補助的に加算
     */
    public static float calcToughnessBonus(MineralImpurity primary, @Nullable MineralImpurity secondary) {
        float ratio = primary.getRatio();
        float base = switch (primary.getType()) {
            case TITANIUM  -> ratio * 2.0f;
            case URANIUM   -> ratio * 1.5f;
            case IRON_3    -> ratio * 1.0f;
            case MANGANESE -> ratio * 0.5f;
            default        -> 0.0f;
        };
        // 副不純物がtitaniumまたはuraniumなら追加
        float sub = 0f;
        if (secondary != null) {
            sub = switch (secondary.getType()) {
                case TITANIUM -> secondary.getRatio() * 0.8f;
                case URANIUM  -> secondary.getRatio() * 0.6f;
                default       -> 0f;
            };
        }
        return base + sub;
    }

    /**
     * ノックバック耐性を計算（0〜1）
     * 主: manganese特化
     * 副: copper・iron_2が補助
     */
    public static float calcKnockbackResistance(MineralImpurity primary, @Nullable MineralImpurity secondary) {
        float ratio = primary.getRatio();
        float base = switch (primary.getType()) {
            case MANGANESE -> ratio * 0.5f;
            case COPPER    -> ratio * 0.4f;
            case IRON_2    -> ratio * 0.2f;
            case TITANIUM  -> ratio * 0.1f;
            default        -> 0.0f;
        };
        float sub = 0f;
        if (secondary != null) {
            sub = switch (secondary.getType()) {
                case MANGANESE -> secondary.getRatio() * 0.2f;
                case COPPER    -> secondary.getRatio() * 0.15f;
                default        -> 0f;
            };
        }
        return Math.min(1.0f, base + sub);
    }

    /**
     * 防具耐久値を計算
     */
    public static int calcArmorDurability(MineralImpurity primary, @Nullable MineralImpurity secondary,
                                          int slotIndex) {
        int[] slotMultipliers = {11, 16, 15, 13};
        int base = slotMultipliers[slotIndex] * 15;
        float ratio = primary.getRatio();
        int primaryBonus = switch (primary.getType()) {
            case IRON_2    -> (int)(ratio * slotMultipliers[slotIndex] * 20);
            case CARBON    -> (int)(ratio * slotMultipliers[slotIndex] * 18);
            case MANGANESE -> (int)(ratio * slotMultipliers[slotIndex] * 15);
            case TITANIUM  -> (int)(ratio * slotMultipliers[slotIndex] * 12);
            default        -> (int)(ratio * slotMultipliers[slotIndex] * 8);
        };
        // 副不純物が耐久系なら追加
        int secondaryBonus = 0;
        if (secondary != null) {
            secondaryBonus = switch (secondary.getType()) {
                case IRON_2 -> (int)(secondary.getRatio() * slotMultipliers[slotIndex] * 10);
                case CARBON -> (int)(secondary.getRatio() * slotMultipliers[slotIndex] * 8);
                default     -> 0;
            };
        }
        return base + primaryBonus + secondaryBonus;
    }

    // ===================================================
    //  ユーティリティ
    // ===================================================

    /**
     * ToolDataから主不純物を安全に取得（nullの場合はデフォルト）
     */
    public static MineralImpurity getPrimaryOrDefault(ToolNBTHelper.ToolData data) {
        if (data == null || data.primary() == null) return new MineralImpurity(ImpurityType.IRON_2, 0.3f);
        return data.primary();
    }

    /**
     * ToolDataから副不純物を安全に取得（nullの場合はデフォルト）
     */
    public static MineralImpurity getSecondaryOrDefault(ToolNBTHelper.ToolData data) {
        if (data == null || data.secondary() == null) return new MineralImpurity(ImpurityType.MANGANESE, 0.1f);
        return data.secondary();
    }

    /**
     * MineralDataから主不純物を安全に取得（nullの場合はデフォルト）
     * @deprecated ツールアイテムはgetPrimaryOrDefault(ToolData)を使うこと
     */
    @Deprecated
    public static MineralImpurity getPrimaryOrDefault(MineralData data) {
        if (data == null) return new MineralImpurity(ImpurityType.IRON_2, 0.3f);
        MineralImpurity primary = data.getPrimaryImpurity();
        if (primary == null) return new MineralImpurity(ImpurityType.IRON_2, 0.3f);
        return primary;
    }

    /**
     * ツールチップ用の性能サマリー文字列を生成
     */
    public static String formatStat(String label, float value) {
        return String.format("§7%s: §f%.1f", label, value);
    }

    public static String formatStatInt(String label, int value) {
        return String.format("§7%s: §f%d", label, value);
    }
}
