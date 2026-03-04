package net.pochi.pochimod.mineral;

import java.util.List;

/**
 * 不純物の色をブレンドしてcolor_hexを自動計算するクラス
 *
 * アルゴリズム:
 *   1. 各不純物のbaseColorをratioで重み付け平均
 *   2. 結果を "#RRGGBB" 形式の文字列に変換
 */
public final class MineralColorCalculator {

    private MineralColorCalculator() {}

    /**
     * 不純物リストからcolor_hexを計算
     *
     * @param impurities 不純物リスト（ratioの合計が必ずしも1.0でなくてよい）
     * @return "#RRGGBB" 形式の文字列
     */
    public static String calculate(List<MineralImpurity> impurities) {
        if (impurities == null || impurities.isEmpty()) {
            return "#FFFFFF"; // デフォルト: 白
        }

        float totalWeight = 0f;
        float r = 0f, g = 0f, b = 0f;

        for (MineralImpurity imp : impurities) {
            float weight = imp.getRatio();
            int   color  = imp.getType().baseColor;

            r += ((color >> 16) & 0xFF) * weight;
            g += ((color >>  8) & 0xFF) * weight;
            b += ( color        & 0xFF) * weight;
            totalWeight += weight;
        }

        if (totalWeight <= 0f) return "#FFFFFF";

        int ri = Math.min(255, (int)(r / totalWeight));
        int gi = Math.min(255, (int)(g / totalWeight));
        int bi = Math.min(255, (int)(b / totalWeight));

        return String.format("#%02X%02X%02X", ri, gi, bi);
    }

    /**
     * color_hex文字列をint（0xRRGGBB）に変換
     * アイテムレンダリング用
     */
    public static int hexToInt(String colorHex) {
        if (colorHex == null || colorHex.isEmpty()) return 0xFFFFFF;
        try {
            String stripped = colorHex.startsWith("#") ? colorHex.substring(1) : colorHex;
            return Integer.parseInt(stripped, 16);
        } catch (NumberFormatException e) {
            return 0xFFFFFF;
        }
    }
}
