package net.pochi.pochimod.mineral;

/**
 * 単一不純物データ
 * NBT格納・取得・エフェクト計算を担う
 */
public class MineralImpurity {

    private final ImpurityType type;
    private final float ratio;  // 0.0 〜 1.0

    public MineralImpurity(ImpurityType type, float ratio) {
        if (ratio < 0f || ratio > 1f) {
            throw new IllegalArgumentException("ratio must be 0.0-1.0, got: " + ratio);
        }
        this.type = type;
        this.ratio = ratio;
    }

    // ---- ゲッター ----

    public ImpurityType getType()  { return type; }
    public float        getRatio() { return ratio; }

    /**
     * 副不純物として付与するエフェクトレベルを計算
     * エフェクトレベル = (int)(ratio × 2)
     * 例: ratio=0.73 → level=1 (Amplifier=0 → 効果Lv1)
     *     ratio=0.90 → level=1
     *     ratio=0.50 → level=1
     *     ratio=0.25 → level=0 (Amplifier=-1 → 付与なし扱いにする場合は呼び出し側で判定)
     * ※ Minecraft の MobEffect amplifier は 0-origin なので level-1 を渡すこと
     */
    public int getEffectLevel() {
        return (int)(ratio * 2);
    }

    /**
     * amplifier値（= getEffectLevel() - 1）を返す
     * amplifier が負になる場合（level=0）はエフェクト付与不可を意味する
     */
    public int getEffectAmplifier() {
        return getEffectLevel() - 1;
    }

    /** エフェクト付与可能かどうか（level >= 1） */
    public boolean canApplyEffect() {
        return getEffectLevel() >= 1;
    }

    @Override
    public String toString() {
        return String.format("MineralImpurity{type=%s, ratio=%.3f, effectLevel=%d}",
                type.id, ratio, getEffectLevel());
    }
}
