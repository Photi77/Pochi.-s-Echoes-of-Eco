package net.pochi.pochimod.mineral.tools;

import net.pochi.pochimod.mineral.ImpurityType;
import net.pochi.pochimod.mineral.MineralImpurity;

/**
 * ネザライト超え条件チェッカー
 *
 * ===== 条件（全て満たす必要がある） =====
 *   1. ratio両方 0.85以上
 *   2. デメリット重複がない（penaltyTypeが異なる）
 *   3. depth_level == 3（最深部限定）
 *
 * ===== 相性良い組み合わせ（ネザライト超え可） =====
 *   chromium + titanium  → 攻撃特化最強（攻撃力↑↑、耐久↑↑でデメリット相殺）
 *   carbon   + iron_2    → 採掘特化最強（採掘速度↑↑、耐久↓↓補正）
 *   vanadium + uranium   → 魔法特化最強（魔法↑↑、付与適性↑↑）
 *   iron_3   + manganese → 攻防バランス型
 *
 * ===== デメリット重複（ネザライト超え不可） =====
 *   chromium + carbon    → 耐久↓ + 耐久↓ = DURABILITY重複
 *   vanadium + carbon    → 耐久↓ + 耐久↓ = DURABILITY重複
 *   manganese + iron_3   → 速度↓ + 採掘速度↓ = MINING_SPEED重複（異なるが類似）
 *
 * ===== 超え時の性能乗数 =====
 *   攻撃力   : ×1.25（相性良い組み合わせのみ）
 *   採掘速度 : ×1.20
 *   耐久値   : ×1.30
 */
public final class NetheriteExceedChecker {

    /** ネザライト超えに必要な最低ratio */
    public static final float REQUIRED_RATIO = 0.85f;

    /** ネザライト超え時の性能乗数 */
    public static final float EXCEED_ATTACK_MULT  = 1.25f;
    public static final float EXCEED_SPEED_MULT   = 1.20f;
    public static final float EXCEED_DURA_MULT    = 1.30f;

    private NetheriteExceedChecker() {}

    // ==============================
    //  メイン判定
    // ==============================

    /**
     * ネザライト超えが可能かどうかを判定する
     *
     * @param primary    主不純物
     * @param secondary  副不純物
     * @param depthLevel 採掘深度レベル（3のみ有効）
     * @return ネザライト超えの結果（理由付き）
     */
    public static ExceedResult check(MineralImpurity primary,
                                     MineralImpurity secondary,
                                     int depthLevel) {
        // 条件1: depth_level == 3
        if (depthLevel < 3) {
            return ExceedResult.fail(FailReason.DEPTH_TOO_SHALLOW,
                    "depth_level=" + depthLevel + "（3以上が必要）");
        }

        // 条件2: ratio両方 0.85以上
        if (primary.getRatio() < REQUIRED_RATIO) {
            return ExceedResult.fail(FailReason.RATIO_TOO_LOW,
                    String.format("主不純物ratio=%.2f（%.2f以上が必要）",
                            primary.getRatio(), REQUIRED_RATIO));
        }
        if (secondary == null || secondary.getRatio() < REQUIRED_RATIO) {
            float r = (secondary == null) ? 0f : secondary.getRatio();
            return ExceedResult.fail(FailReason.RATIO_TOO_LOW,
                    String.format("副不純物ratio=%.2f（%.2f以上が必要）", r, REQUIRED_RATIO));
        }

        // 条件3: デメリット重複なし
        if (isPenaltyDuplicated(primary, secondary)) {
            return ExceedResult.fail(FailReason.PENALTY_DUPLICATED,
                    primary.getType() + "重複（" +
                    primary.getType().id + " + " + secondary.getType().id + "）");
        }

        // 全条件クリア → 相性ボーナスを計算
        Synergy synergy = detectSynergy(primary, secondary);
        return ExceedResult.succeed(synergy);
    }

    /**
     * ToolNBTHelper.ToolData からまとめてチェック
     */
    public static ExceedResult check(ToolNBTHelper.ToolData data, int depthLevel) {
        if (data == null) return ExceedResult.fail(FailReason.NO_DATA, "ToolDataがnull");
        MineralImpurity primary   = data.primary();
        MineralImpurity secondary = data.secondary();
        if (primary == null) return ExceedResult.fail(FailReason.NO_DATA, "主不純物なし");
        return check(primary, secondary, depthLevel);
    }

    // ==============================
    //  内部ロジック
    // ==============================

    /** デメリット種別が重複しているか */
    private static boolean isPenaltyDuplicated(MineralImpurity primary, MineralImpurity secondary) {
        PenaltyType p1 = primary.getType().penaltyType;
        PenaltyType p2 = secondary.getType().penaltyType;
        // NONE は重複扱いしない
        return p1 != PenaltyType.NONE && p1 == p2;
    }

    /** 相性の良い組み合わせを検出してSynergyを返す */
    private static Synergy detectSynergy(MineralImpurity primary, MineralImpurity secondary) {
        ImpurityType t1 = primary.getType();
        ImpurityType t2 = secondary.getType();

        // 組み合わせは順不同で判定
        if (isPair(t1, t2, ImpurityType.CHROMIUM, ImpurityType.TITANIUM)) {
            return Synergy.ATTACK_SUPREME;   // 攻撃特化最強
        }
        if (isPair(t1, t2, ImpurityType.CARBON, ImpurityType.IRON_2)) {
            return Synergy.MINING_SUPREME;   // 採掘特化最強
        }
        if (isPair(t1, t2, ImpurityType.VANADIUM, ImpurityType.URANIUM)) {
            return Synergy.MAGIC_SUPREME;    // 魔法特化最強
        }
        if (isPair(t1, t2, ImpurityType.IRON_3, ImpurityType.MANGANESE)) {
            return Synergy.BALANCED;         // 攻防バランス型
        }
        // 条件は満たすが特定シナジーなし
        return Synergy.GENERIC;
    }

    private static boolean isPair(ImpurityType a, ImpurityType b,
                                   ImpurityType x, ImpurityType y) {
        return (a == x && b == y) || (a == y && b == x);
    }

    // ==============================
    //  結果型
    // ==============================

    /**
     * ネザライト超え判定結果
     */
    public record ExceedResult(
            boolean exceeds,
            Synergy synergy,       // exceeds=true の場合のシナジー種別
            FailReason failReason, // exceeds=false の場合の失敗理由
            String message) {

        /** 失敗結果を生成 */
        static ExceedResult fail(FailReason reason, String message) {
            return new ExceedResult(false, Synergy.NONE, reason, message);
        }

        /** 成功結果を生成 */
        static ExceedResult succeed(Synergy synergy) {
            return new ExceedResult(true, synergy, null,
                    "ネザライト超え達成: " + synergy.description);
        }

        /**
         * 攻撃力乗数を返す（超えていない場合は1.0f）
         */
        public float attackMultiplier() {
            if (!exceeds) return 1.0f;
            return switch (synergy) {
                case ATTACK_SUPREME -> EXCEED_ATTACK_MULT;
                case BALANCED       -> 1.10f;
                default             -> 1.0f;
            };
        }

        /**
         * 採掘速度乗数を返す
         */
        public float speedMultiplier() {
            if (!exceeds) return 1.0f;
            return switch (synergy) {
                case MINING_SUPREME -> EXCEED_SPEED_MULT;
                default             -> 1.0f;
            };
        }

        /**
         * 耐久乗数を返す
         */
        public float durabilityMultiplier() {
            if (!exceeds) return 1.0f;
            return switch (synergy) {
                case ATTACK_SUPREME -> EXCEED_DURA_MULT;
                case MINING_SUPREME -> EXCEED_DURA_MULT;
                case MAGIC_SUPREME  -> 1.10f;
                case BALANCED       -> EXCEED_DURA_MULT;
                case GENERIC        -> 1.15f;
                default             -> 1.0f;
            };
        }

        /** ツールチップ表示用サマリ */
        public String toTooltip() {
            if (!exceeds) return "§7" + message;
            return switch (synergy) {
                case ATTACK_SUPREME -> "§c⚔ 攻撃特化最強 §l[ネザライト超]";
                case MINING_SUPREME -> "§6⛏ 採掘特化最強 §l[ネザライト超]";
                case MAGIC_SUPREME  -> "§d✦ 魔法特化最強 §l[ネザライト超]";
                case BALANCED       -> "§a⬡ 攻防バランス §l[ネザライト超]";
                case GENERIC        -> "§e★ §l[ネザライト超]";
                default             -> "";
            };
        }
    }

    /**
     * 相性シナジー種別
     */
    public enum Synergy {
        ATTACK_SUPREME ("chromium+titanium: 攻撃特化最強"),
        MINING_SUPREME ("carbon+iron_2: 採掘特化最強"),
        MAGIC_SUPREME  ("vanadium+uranium: 魔法特化最強"),
        BALANCED       ("iron_3+manganese: 攻防バランス"),
        GENERIC        ("高ratio組み合わせ"),
        NONE           ("なし");

        public final String description;
        Synergy(String description) { this.description = description; }
    }

    /**
     * 失敗理由
     */
    public enum FailReason {
        DEPTH_TOO_SHALLOW,  // 深度不足
        RATIO_TOO_LOW,      // ratio不足
        PENALTY_DUPLICATED, // デメリット重複
        NO_DATA             // データなし
    }
}
