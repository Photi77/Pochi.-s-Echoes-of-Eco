package net.pochi.pochimod.util;

import net.minecraft.world.level.Level;

public class MoonPhaseUtil {

    /**
     * 月齢を取得（0-7）
     * 0: 満月
     * 1: 欠け始め
     * 2: 下弦の月
     * 3: 欠け月
     * 4: 新月
     * 5: 三日月
     * 6: 上弦の月
     * 7: 満ち始め
     */
    public static int getMoonPhase(Level level) {
        long dayTime = level.getDayTime();
        long day = dayTime / 24000L;
        return (int)(day % 8L);
    }

    /**
     * 月齢名を取得
     */
    public static String getMoonPhaseName(int phase) {
        return switch (phase) {
            case 0 -> "満月";
            case 1 -> "欠け始め";
            case 2 -> "下弦の月";
            case 3 -> "欠け月";
            case 4 -> "新月";
            case 5 -> "三日月";
            case 6 -> "上弦の月";
            case 7 -> "満ち始め";
            default -> "不明";
        };
    }

    /**
     * 月齢名（英語）を取得
     */
    public static String getMoonPhaseNameEn(int phase) {
        return switch (phase) {
            case 0 -> "Full Moon";
            case 1 -> "Waning Gibbous";
            case 2 -> "Last Quarter";
            case 3 -> "Waning Crescent";
            case 4 -> "New Moon";
            case 5 -> "Waxing Crescent";
            case 6 -> "First Quarter";
            case 7 -> "Waxing Gibbous";
            default -> "Unknown";
        };
    }

    /**
     * 夜間かチェック
     */
    public static boolean isNightTime(Level level) {
        long timeOfDay = level.getDayTime() % 24000L;
        return timeOfDay >= 13000L && timeOfDay < 23000L;
    }

    /**
     * 月齢に応じた発光レベルを取得
     */
    public static float getMoonBrightness(int phase) {
        return switch (phase) {
            case 0 -> 1.0F; // 満月：最大
            case 1, 7 -> 0.8F;
            case 2, 6 -> 0.5F;
            case 3, 5 -> 0.3F;
            case 4 -> 0.1F; // 新月：最小
            default -> 0.5F;
        };
    }

    /**
     * モードを取得
     * 0: 満月期, 1: 新月期, 2: 移行期
     */
    public static int getMode(int moonPhase) {
        if (moonPhase == 0 || moonPhase == 1 || moonPhase == 7) {
            return 0; // 満月期
        } else if (moonPhase == 3 || moonPhase == 4 || moonPhase == 5) {
            return 1; // 新月期
        } else {
            return 2; // 移行期
        }
    }

    /**
     * モード名を取得
     */
    public static String getModeName(int mode) {
        return switch (mode) {
            case 0 -> "パワーモード";
            case 1 -> "スピードモード";
            case 2 -> "通常状態";
            default -> "不明";
        };
    }
}