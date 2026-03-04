package net.pochi.pochimod.mineral;

import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;

import javax.annotation.Nullable;
import java.util.*;

/**
 * バイオームの temperature / downfall から地質インデックスを算出し、
 * 不純物タイプ・ratioを決定するクラス。
 *
 * ===== 地質インデックス定義 =====
 *   igneous      = clamp(temp / 2.0, 0, 1)       → 火成岩系（高温）
 *   sedimentary  = downfall                        → 堆積岩系（湿潤）
 *   arid         = clamp(1.0 - downfall, 0, 1)    → 乾燥系
 *   glacial      = clamp(-temp, 0, 1)              → 氷河系（極寒）
 *   tropical     = clamp(temp * downfall, 0, 1)   → 熱帯系（高温多雨）
 *   hydrothermal = clamp(temp * (1-downfall), 0,1)→ 熱水系（高温乾燥）
 *
 * ===== 地質 → 不純物対応 =====
 *   igneous      → CHROMIUM
 *   hydrothermal → IRON_3
 *   tropical     → VANADIUM
 *   sedimentary  → MANGANESE
 *   arid         → CARBON
 *   glacial      → TITANIUM
 *   (余剰/低値)  → IRON_2（デフォルト）
 *
 * ===== バイオーム合成 =====
 *   採掘地点から最近傍バイオーム（river除く）を2つ取得し、
 *   距離の逆数で重み付けして地質インデックスを合成する。
 */
public final class BiomeGeologyCalculator {

    private BiomeGeologyCalculator() {}

    // ---- 地質タイプ定数 ----
    public static final String IGNEOUS      = "igneous";
    public static final String SEDIMENTARY  = "sedimentary";
    public static final String ARID         = "arid";
    public static final String GLACIAL      = "glacial";
    public static final String TROPICAL     = "tropical";
    public static final String HYDROTHERMAL = "hydrothermal";

    // 地質 → ImpurityType 対応（順序保証のためLinkedHashMap）
    private static final Map<String, ImpurityType> GEOLOGY_TO_IMPURITY = new LinkedHashMap<>();
    static {
        GEOLOGY_TO_IMPURITY.put(IGNEOUS,      ImpurityType.CHROMIUM);
        GEOLOGY_TO_IMPURITY.put(HYDROTHERMAL, ImpurityType.IRON_3);
        GEOLOGY_TO_IMPURITY.put(TROPICAL,     ImpurityType.VANADIUM);
        GEOLOGY_TO_IMPURITY.put(SEDIMENTARY,  ImpurityType.MANGANESE);
        GEOLOGY_TO_IMPURITY.put(ARID,         ImpurityType.CARBON);
        GEOLOGY_TO_IMPURITY.put(GLACIAL,      ImpurityType.TITANIUM);
    }

    // 川バイオームセット（地質計算から除外）
    private static final Set<String> RIVER_BIOMES = Set.of(
            "minecraft:river",
            "minecraft:frozen_river"
    );

    // ==============================
    //  メイン: バイオームホルダーから直接計算
    // ==============================

    /**
     * バイオームホルダーから地質インデックスを算出
     *
     * @param biome バイオームHolder
     * @return 地質名 → 値（0.0〜1.0）のMap
     */
    public static Map<String, Float> calcGeologyIndex(Holder<Biome> biome) {
        float temp     = biome.value().getBaseTemperature();
        float downfall = biome.value().getModifiedClimateSettings().downfall();
        return calcGeologyIndex(temp, downfall);
    }

    /**
     * temperature / downfall から地質インデックスを算出
     */
    public static Map<String, Float> calcGeologyIndex(float temp, float downfall) {
        Map<String, Float> geology = new LinkedHashMap<>();
        geology.put(IGNEOUS,      clamp(temp / 2.0f, 0f, 1f));
        geology.put(SEDIMENTARY,  clamp(downfall, 0f, 1f));
        geology.put(ARID,         clamp(1.0f - downfall, 0f, 1f));
        geology.put(GLACIAL,      clamp(-temp, 0f, 1f));
        geology.put(TROPICAL,     clamp(temp * downfall, 0f, 1f));
        geology.put(HYDROTHERMAL, clamp(temp * (1.0f - downfall), 0f, 1f));
        return geology;
    }

    /**
     * 2バイオームの地質インデックスを距離重みで合成
     *
     * @param biome1         1番目のバイオーム（近い方）
     * @param biome2         2番目のバイオーム（遠い方）
     * @param primaryWeight  1番目の重み（0.0〜1.0）。2番目は (1.0 - primaryWeight)
     */
    public static Map<String, Float> mergeGeology(
            Holder<Biome> biome1, Holder<Biome> biome2, float primaryWeight) {

        Map<String, Float> geo1 = calcGeologyIndex(biome1);
        Map<String, Float> geo2 = calcGeologyIndex(biome2);
        return mergeGeology(geo1, geo2, primaryWeight);
    }

    public static Map<String, Float> mergeGeology(
            Map<String, Float> geo1, Map<String, Float> geo2, float primaryWeight) {

        float secondaryWeight = 1.0f - primaryWeight;
        Map<String, Float> merged = new LinkedHashMap<>();
        for (String key : geo1.keySet()) {
            float v1 = geo1.getOrDefault(key, 0f);
            float v2 = geo2.getOrDefault(key, 0f);
            merged.put(key, v1 * primaryWeight + v2 * secondaryWeight);
        }
        return merged;
    }

    // ==============================
    //  不純物決定
    // ==============================

    /**
     * 地質インデックスから主不純物・副不純物を決定
     *
     * 地質値上位2位のタイプがそれぞれ主/副不純物になる。
     * ratioはそのまま地質値を使用（0.0〜1.0）。
     *
     * @param geology 合成済み地質インデックスMap
     * @return 主不純物, 副不純物の順で最大2要素のList
     */
    /**
     * 地質インデックスから主不純物・副不純物を決定
     *
     * ===== ランダム要素 =====
     * 1. Jitter      : 各地質値に ±JITTER_RANGE のノイズを加算
     *                  → 上位2位の順位が時々入れ替わり、主/副不純物が変化する
     * 2. Ratio変動   : 決定したratioに ±RATIO_VARIANCE の変動を加算
     *                  → 同じ場所でも濃度がブレる
     * 3. Upset確率   : UPSET_CHANCE の確率で3位の地質が2位に割り込む
     *                  → 稀に予想外の副不純物が出現する
     *
     * @param geology 合成済み地質インデックスMap
     * @param random  乱数（null の場合はランダム要素なし）
     */
    public static List<MineralImpurity> determineImpurities(
            Map<String, Float> geology, @Nullable Random random) {

        // ---- パラメータ ----
        final float JITTER_RANGE   = 0.12f; // 地質値のブレ幅（±12%）
        final float RATIO_VARIANCE = 0.10f; // ratio の最終変動幅（±10%）
        final float UPSET_CHANCE   = 0.15f; // 3位が2位に割り込む確率（15%）

        // 1. Jitter適用
        Map<String, Float> jittered = new LinkedHashMap<>();
        for (Map.Entry<String, Float> e : geology.entrySet()) {
            float jitter = (random != null)
                    ? (random.nextFloat() * 2 - 1) * JITTER_RANGE
                    : 0f;
            jittered.put(e.getKey(), clamp(e.getValue() + jitter, 0f, 1f));
        }

        // 2. 降順ソート
        List<Map.Entry<String, Float>> sorted = new ArrayList<>(jittered.entrySet());
        sorted.sort((a, b) -> Float.compare(b.getValue(), a.getValue()));

        // 3. Upset: 15%の確率で3位が2位に割り込む
        if (random != null && sorted.size() >= 3 && random.nextFloat() < UPSET_CHANCE) {
            // 2位と3位を入れ替え
            Map.Entry<String, Float> tmp = sorted.get(1);
            sorted.set(1, sorted.get(2));
            sorted.set(2, tmp);
        }

        // 4. 上位2つを不純物として採用
        List<MineralImpurity> result = new ArrayList<>();
        for (int i = 0; i < Math.min(2, sorted.size()); i++) {
            String geoType  = sorted.get(i).getKey();
            float  geoValue = sorted.get(i).getValue();

            if (geoValue <= 0.01f) break;

            // 5. Ratio変動
            float variance = (random != null)
                    ? (random.nextFloat() * 2 - 1) * RATIO_VARIANCE
                    : 0f;
            float finalRatio = clamp(geoValue + variance, 0.05f, 1.0f);

            ImpurityType impType = GEOLOGY_TO_IMPURITY.getOrDefault(geoType, ImpurityType.IRON_2);
            result.add(new MineralImpurity(impType, finalRatio));
        }

        if (result.isEmpty()) {
            result.add(new MineralImpurity(ImpurityType.IRON_2, 0.3f));
        }
        return result;
    }

    /** ランダムなし版（後方互換） */
    public static List<MineralImpurity> determineImpurities(Map<String, Float> geology) {
        return determineImpurities(geology, null);
    }

    /**
     * BiomeScanResult から不純物を決定するメインエントリポイント
     *
     * river以外の上位2バイオームを距離重み付きで合成して計算する。
     *
     * @param scanResult  スキャン結果（距離付きバイオームマップ）
     * @param depthLevel  採掘深度（ratio補正に使用）
     */
    public static List<MineralImpurity> determineImpuritiesFromScan(
            BiomeScanResult scanResult, int depthLevel, Random random) {

        List<BiomeScanResult.WeightedBiome> nonRiver = scanResult.getNonRiverBiomes(RIVER_BIOMES);

        Map<String, Float> geology;

        if (nonRiver.isEmpty()) {
            // 周辺がすべて川 → river自体の地質を使用
            geology = calcGeologyIndex(scanResult.getMainBiome());
        } else if (nonRiver.size() == 1) {
            // 1バイオームのみ
            geology = calcGeologyIndex(nonRiver.get(0).biome());
        } else {
            // 上位2バイオームを距離重みで合成
            BiomeScanResult.WeightedBiome b1 = nonRiver.get(0);
            BiomeScanResult.WeightedBiome b2 = nonRiver.get(1);
            float totalWeight = b1.weight() + b2.weight();
            float primaryWeight = (totalWeight > 0) ? b1.weight() / totalWeight : 0.6f;
            geology = mergeGeology(b1.biome(), b2.biome(), primaryWeight);
        }

        // 深度ボーナスを全地質値に加算（深いほど濃縮）
        float depthBonus = depthLevel * 0.08f;
        Map<String, Float> boosted = new LinkedHashMap<>();
        for (Map.Entry<String, Float> e : geology.entrySet()) {
            boosted.put(e.getKey(), clamp(e.getValue() + depthBonus, 0f, 1f));
        }

        return determineImpurities(boosted, random);
    }

    // ==============================
    //  デバッグ用: 地質インデックス文字列化
    // ==============================

    public static String formatGeology(Map<String, Float> geology) {
        StringBuilder sb = new StringBuilder("[");
        geology.forEach((k, v) -> sb.append(k).append(":").append(String.format("%.2f", v)).append(" "));
        sb.append("]");
        return sb.toString();
    }

    // ==============================
    //  ユーティリティ
    // ==============================

    private static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }
}



