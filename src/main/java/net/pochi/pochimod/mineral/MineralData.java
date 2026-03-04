package net.pochi.pochimod.mineral;

import net.minecraft.resources.ResourceLocation;

import java.util.Collections;
import java.util.List;

/**
 * 鉱物1個分のデータクラス（NBTとの橋渡し）
 *
 * 仕様:
 *   impurities[0] = 主不純物（ratio高 → 性能値計算に使用）
 *   impurities[1] = 副不純物（ratio低 → エフェクト付与に使用）
 */
public class MineralData {

    private final BaseGem               baseGem;       // ランダム選択された宝石種
    private final List<MineralImpurity> impurities;    // 最大2要素
    private final String                colorHex;      // "#9B59B6" 形式
    private final List<ResourceLocation> comboBiomes;  // コンボを構成したバイオーム（最大3個）
    private final int                   depthLevel;    // 0〜3

    public MineralData(
            BaseGem baseGem,
            List<MineralImpurity> impurities,
            String colorHex,
            List<ResourceLocation> comboBiomes,
            int depthLevel) {

        this.baseGem      = baseGem;
        this.impurities   = Collections.unmodifiableList(impurities);
        this.colorHex     = colorHex;
        this.comboBiomes  = Collections.unmodifiableList(comboBiomes);
        this.depthLevel   = depthLevel;
    }

    // ---- ゲッター ----
    public BaseGem                getBaseGem()     { return baseGem; }
    public String                 getBaseMineral() { return baseGem.id; } // 後方互換
    public List<MineralImpurity>  getImpurities()  { return impurities; }
    public String                 getColorHex()    { return colorHex; }
    public List<ResourceLocation> getComboBiomes() { return comboBiomes; }
    public int                    getDepthLevel()  { return depthLevel; }

    /** 後方互換：最初のバイオームを返す */
    public ResourceLocation getOriginBiome() {
        return comboBiomes.isEmpty() ? ResourceLocation.parse("minecraft:plains") : comboBiomes.get(0);
    }

    /** 主不純物（[0]）を取得 */
    public MineralImpurity getPrimaryImpurity() {
        return impurities.isEmpty() ? null : impurities.get(0);
    }

    /** 副不純物（[1]）を取得 */
    public MineralImpurity getSecondaryImpurity() {
        return impurities.size() < 2 ? null : impurities.get(1);
    }

    @Override
    public String toString() {
        return String.format("MineralData{gem=%s, color=%s, biomes=%s, depth=%d, impurities=%s}",
                baseGem.id, colorHex, comboBiomes, depthLevel, impurities);
    }
}
