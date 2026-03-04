package net.pochi.pochimod.ferm;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

public class SoilNutrientHelper {
    // NBTキー
    public static final String KEY_NITROGEN   = "soil_nitrogen";    // 窒素 N
    public static final String KEY_PHOSPHORUS = "soil_phosphorus";  // リン P
    public static final String KEY_POTASSIUM  = "soil_potassium";   // カリウム K

    // 値の範囲：0.0〜1.0（1.0が最高品質）
    public static final float DEFAULT = 0.5f;
    public static final float MIN = 0.0f;
    public static final float MAX = 1.0f;

    // Farmlandブロックの座標ベースでNBTをWorld保存するためのヘルパー
    // BlockEntityを使わずにLevelChunkCapabilityで管理する方式を採用

    public static float get(Level level, BlockPos pos, String key) {
        return SoilDataCapability.get(level, pos)
                .map(data -> data.getValue(pos, key))  // posを渡す
                .orElse(DEFAULT);
    }

    public static void set(Level level, BlockPos pos, String key, float value) {
        SoilDataCapability.get(level, pos).ifPresent(data -> {
            // posを渡していなかった
            data.setValue(pos, key, Mth.clamp(value, MIN, MAX));
            data.setDirty();
        });
    }

    // N/P/K → 品質係数への変換（加重平均）
    // 作物ごとに重みを変えられるよう設計
    public static float calcQuality(float n, float p, float k,
                                    float wN, float wP, float wK) {
        float total = wN + wP + wK;
        float raw = (n * wN + p * wP + k * wK) / total;
        // 0.0〜1.0 → 品質係数0.5〜1.5にマッピング
        return 0.5f + raw;
    }
}