package net.pochi.pochimod.networking.packet;

import net.minecraft.core.BlockPos;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

// SoilDataClientCache.java
@OnlyIn(Dist.CLIENT)
public class SoilDataClientCache {
    // 最後に受信した座標のN/P/K値を保持
    private static BlockPos cachedPos = null;
    private static float cachedN, cachedP, cachedK;

    public static void update(BlockPos pos, float n, float p, float k) {
        cachedPos = pos;
        cachedN = n;
        cachedP = p;
        cachedK = k;
    }

    public static boolean hasData(BlockPos pos) {
        return pos.equals(cachedPos);
    }

    public static float getN() { return cachedN; }
    public static float getP() { return cachedP; }
    public static float getK() { return cachedK; }
}
