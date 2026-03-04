package net.pochi.pochimod.mineral;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.biome.Biome;

import java.util.*;

/**
 * バイオームスキャン結果
 *
 * 採掘地点から各バイオームまでの距離（最小値）を記録し、
 * 距離の逆数を重みとして返す。
 *
 * ===== 距離重みの計算 =====
 *   weight = 1.0 / (minDistance + 1)
 *   近いバイオームほど重みが大きい。
 *   同一バイオームが複数スキャン点で検出された場合は最小距離を使用。
 */
public class BiomeScanResult {

    /** 距離付きバイオームエントリー */
    public record WeightedBiome(
            ResourceLocation id,
            Holder<Biome> biome,
            float minDistance,
            float weight) {}

    private final Holder<Biome>              mainBiome;
    private final ResourceLocation           mainBiomeId;
    private final List<WeightedBiome>        sortedBiomes; // 重み降順（mainBiome含む）

    private BiomeScanResult(Holder<Biome> mainBiome, ResourceLocation mainBiomeId,
                             List<WeightedBiome> sortedBiomes) {
        this.mainBiome    = mainBiome;
        this.mainBiomeId  = mainBiomeId;
        this.sortedBiomes = Collections.unmodifiableList(sortedBiomes);
    }

    // ==============================
    //  スキャン実行（staticファクトリ）
    // ==============================

    /**
     * 採掘地点周辺のバイオームをスキャンしてBiomeScanResultを返す
     *
     * @param level     ServerLevel
     * @param center    採掘地点
     * @param maxRadius スキャン半径（ブロック）
     * @param step      サンプリング間隔（ブロック）
     */
    public static BiomeScanResult scan(ServerLevel level, BlockPos center, int maxRadius, int step) {
        // 採掘地点のメインバイオーム
        Holder<Biome> mainHolder = level.getBiome(center);
        ResourceLocation mainId = mainHolder.unwrapKey()
                .map(k -> k.location())
                .orElse(ResourceLocation.parse("minecraft:plains"));

        // バイオームID → 最小距離マップ
        Map<ResourceLocation, Float>       minDistMap  = new HashMap<>();
        Map<ResourceLocation, Holder<Biome>> holderMap = new HashMap<>();

        // 中心点（距離0）を登録
        minDistMap.put(mainId, 0f);
        holderMap.put(mainId, mainHolder);

        // 円形スキャン
        for (int x = -maxRadius; x <= maxRadius; x += step) {
            for (int z = -maxRadius; z <= maxRadius; z += step) {
                float dist = (float) Math.sqrt(x * x + z * z);
                if (dist > maxRadius) continue;

                BlockPos scanPos = center.offset(x, 0, z);
                Holder<Biome> holder = level.getBiome(scanPos);
                ResourceLocation biomeId = holder.unwrapKey()
                        .map(k -> k.location())
                        .orElse(null);
                if (biomeId == null) continue;

                holderMap.put(biomeId, holder);
                minDistMap.merge(biomeId, dist, Math::min);
            }
        }

        // WeightedBiomeリストを構築（重み降順ソート）
        List<WeightedBiome> sorted = new ArrayList<>();
        for (Map.Entry<ResourceLocation, Float> e : minDistMap.entrySet()) {
            float minDist = e.getValue();
            float weight  = 1.0f / (minDist + 1.0f);
            sorted.add(new WeightedBiome(e.getKey(), holderMap.get(e.getKey()), minDist, weight));
        }
        sorted.sort((a, b) -> Float.compare(b.weight(), a.weight()));

        return new BiomeScanResult(mainHolder, mainId, sorted);
    }

    // ==============================
    //  アクセサ
    // ==============================

    public Holder<Biome>   getMainBiome()   { return mainBiome; }
    public ResourceLocation getMainBiomeId() { return mainBiomeId; }
    public List<WeightedBiome> getAllBiomes() { return sortedBiomes; }

    /**
     * 川バイオームを除外した上位バイオームリストを返す
     * @param riverBiomes 除外するバイオームIDセット（文字列）
     */
    public List<WeightedBiome> getNonRiverBiomes(Set<String> riverBiomes) {
        List<WeightedBiome> result = new ArrayList<>();
        for (WeightedBiome wb : sortedBiomes) {
            if (!riverBiomes.contains(wb.id().toString())) {
                result.add(wb);
            }
        }
        return result;
    }

    /**
     * 採掘地点が川バイオームかどうか
     */
    public boolean isRiverBiome(Set<String> riverBiomes) {
        return riverBiomes.contains(mainBiomeId.toString());
    }

    /**
     * デバッグ用文字列
     */
    public String toDebugString() {
        StringBuilder sb = new StringBuilder("BiomeScanResult{\n");
        sb.append("  main=").append(mainBiomeId).append("\n");
        for (WeightedBiome wb : sortedBiomes) {
            sb.append(String.format("  %s dist=%.1f weight=%.3f\n",
                    wb.id(), wb.minDistance(), wb.weight()));
        }
        sb.append("}");
        return sb.toString();
    }
}
