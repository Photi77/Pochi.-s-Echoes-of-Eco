package net.pochi.pochimod.mineral.tools;

import net.pochi.pochimod.mineral.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 複数の mineral_chunk から MineralData を集約するユーティリティ
 *
 * ===== マージ仕様 =====
 *
 * 【不純物】
 *   全チャンクの impurities を ImpurityType ごとに収集し、
 *   チャンク数で重み付け平均を取る。
 *   その後 ratio 降順で上位2種を主/副不純物として採用する。
 *
 * 【BaseGem】
 *   最も多く使われた種類を採用（同数の場合は最初に見つかったもの）
 *
 * 【depthLevel】
 *   最大値を採用（最も深いチャンクに合わせる）
 *
 * 【color_hex】
 *   マージ後の不純物リストから MineralColorCalculator で再計算
 *
 * ===== 例 =====
 *   chunk1: chromium 0.80, titanium 0.60
 *   chunk2: chromium 0.70, manganese 0.50
 *   chunk3: titanium 0.90, chromium 0.40
 *
 *   chromium 平均 = (0.80 + 0.70 + 0.40) / 3 = 0.633
 *   titanium 平均 = (0.60 + 0.00 + 0.90) / 2 = 0.750  ← 持っているチャンクのみで平均
 *   manganese平均 = 0.50 / 1 = 0.500
 *
 *   → 主: titanium 0.750 / 副: chromium 0.633
 */
public final class MineralDataMerger {

    private MineralDataMerger() {}

    /**
     * 複数の MineralData を1つにマージして返す
     *
     * @param dataList マージ対象のMineralDataリスト（空の場合はnullを返す）
     * @return マージ済み MineralData、またはnull
     */
    public static MineralData merge(List<MineralData> dataList) {
        if (dataList == null || dataList.isEmpty()) return null;
        if (dataList.size() == 1) return dataList.get(0);

        // ---- 位置別マージ（主は主同士、副は副同士） ----
        // index 0 = 主不純物、index 1 = 副不純物
        List<MineralImpurity> mergedImpurities = new ArrayList<>();

        for (int pos = 0; pos < 2; pos++) {
            final int p = pos;

            // このポジションを持つチャンクだけ収集
            Map<ImpurityType, float[]> accum = new LinkedHashMap<>();
            for (MineralData data : dataList) {
                if (data.getImpurities().size() <= p) continue;
                MineralImpurity imp = data.getImpurities().get(p);
                accum.computeIfAbsent(imp.getType(), k -> new float[]{0f, 0f});
                accum.get(imp.getType())[0] += imp.getRatio();
                accum.get(imp.getType())[1] += 1f;
            }

            if (accum.isEmpty()) continue;

            // 最多登場タイプを採用（同数の場合はratio合計が高い方）
            ImpurityType winner = null;
            float bestScore = -1f;
            for (Map.Entry<ImpurityType, float[]> e : accum.entrySet()) {
                float count = e.getValue()[1];
                float sum   = e.getValue()[0];
                // 登場回数優先、同数ならratio合計で比較
                float score = count * 1000f + sum;
                if (score > bestScore) {
                    bestScore = score;
                    winner = e.getKey();
                }
            }

            if (winner == null) continue;

            float[] wv = accum.get(winner);
            float avgRatio = wv[0] / wv[1];
            mergedImpurities.add(new MineralImpurity(winner, avgRatio));
        }

        if (mergedImpurities.isEmpty()) {
            mergedImpurities.add(new MineralImpurity(ImpurityType.IRON_2, 0.3f));
        }

        // ---- BaseGem: 最多使用種 ----
        Map<BaseGem, Integer> gemCount = new LinkedHashMap<>();
        for (MineralData data : dataList) {
            gemCount.merge(data.getBaseGem(), 1, Integer::sum);
        }
        BaseGem baseGem = gemCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(BaseGem.CRYSTAL);

        // ---- depthLevel: 最大値 ----
        int depthLevel = dataList.stream()
                .mapToInt(MineralData::getDepthLevel)
                .max()
                .orElse(0);

        // ---- comboBiomes: 全チャンクのバイオームを重複なしで結合（最大3個） ----
        List<net.minecraft.resources.ResourceLocation> comboBiomes = new ArrayList<>();
        for (MineralData data : dataList) {
            for (var biome : data.getComboBiomes()) {
                if (!comboBiomes.contains(biome)) comboBiomes.add(biome);
                if (comboBiomes.size() >= 3) break;
            }
            if (comboBiomes.size() >= 3) break;
        }

        // ---- color_hex: マージ後の不純物から再計算 ----
        String colorHex = MineralColorCalculator.calculate(mergedImpurities);

        return new MineralData(baseGem, mergedImpurities, colorHex, comboBiomes, depthLevel);
    }
}