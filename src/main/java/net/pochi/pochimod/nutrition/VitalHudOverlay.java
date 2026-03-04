package net.pochi.pochimod.nutrition;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.world.entity.player.Player;
import net.pochi.pochimod.attachment.ModAttachments;
import net.pochi.pochimod.vital.VitalDeficiencyEffects;

/**
 * HUDにバイタル情報を表示するオーバーレイ (LayeredDraw.Layer)
 *
 * [バーの見方]
 *   全体幅 = NutritionType.MAX_VALUE (150)
 *   ─────┬──────────────────────────────────┬──────────┐
 *         │ 欠乏ライン(黄)      過剰ライン(橙) │
 *   ★    │    ↓                    ↓        │
 *   赤  橙│ 黄  緑  緑  緑  緑  黄  橙  赤  │
 *   0   30│                       120      150
 *
 *   欠乏ライン (黄色) : 値 30 の位置 ← この左側はデバフ発生
 *   過剰ライン (橙色) : 値 120 の位置 ← この右側はデバフ発生
 */
public class VitalHudOverlay implements LayeredDraw.Layer {

    private static final int BAR_WIDTH   = 80;
    private static final int BAR_HEIGHT  = 8;
    private static final int BAR_SPACING = 2;
    private static final int MARGIN      = 5;

    // ゾーン閾値
    private static final int DEFICIENCY_THRESHOLD = 30;   // 欠乏ラインの位置
    private static final int EXCESS_THRESHOLD     = 120;  // 過剰ラインの位置

    // 水分は過剰なし (MAX=100)
    private static final int HYDRATION_MAX = 100;

    // バーの塗り色
    private static final int COLOR_SEVERE     = 0xFFCC2200;  // 深刻 (欠乏/過剰ともに)
    private static final int COLOR_MODERATE   = 0xFFFF4400;  // 中程度
    private static final int COLOR_MILD       = 0xFFFF8800;  // 軽度 (橙)
    private static final int COLOR_HEALTHY    = 0xFF44CC44;  // 健全 (緑)
    private static final int COLOR_HYDRATION  = 0xFF44AAFF;  // 水分健全 (青)

    // マーカーライン色
    private static final int MARKER_DEFICIENCY = 0xFFFFFF00;  // 黄色: 欠乏ライン
    private static final int MARKER_EXCESS     = 0xFFFF6600;  // 橙色: 過剰ライン

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;

        if (player == null) return;
        if (minecraft.options.hideGui) return;
        if (minecraft.getDebugOverlay().showDebugScreen()) return;

        int screenHeight = minecraft.getWindow().getGuiScaledHeight();

        PlayerVitalData vitalData = player.getData(ModAttachments.PLAYER_VITAL);
        int startX = MARGIN;
        int startY = screenHeight - (BAR_HEIGHT + BAR_SPACING) * 5 - MARGIN;

        // 水分バー (MAX=100, 過剰なし)
        renderBar(guiGraphics, "H", startX, startY,
                vitalData.getHydration(), HYDRATION_MAX, false);

        // 栄養素バー (MAX=150, 過剰あり)
        NutritionType[] types = NutritionType.getAllTypes();
        for (int i = 0; i < types.length; i++) {
            NutritionType type = types[i];
            int y = startY + BAR_HEIGHT * (i + 1);
            renderBar(guiGraphics, getShortLabel(type), startX, y,
                    vitalData.getNutrition(type), NutritionType.MAX_VALUE, true);
        }
    }

    /**
     * バーを描画する
     *
     * @param hasExcess true の場合、過剰ゾーンマーカーを表示 (MAX=150 系)
     */
    private void renderBar(GuiGraphics g, String label, int x, int y,
                           int value, int maxValue, boolean hasExcess) {
        Minecraft mc = Minecraft.getInstance();

        // ラベル
        g.drawString(mc.font, label, x, y, 0xFFFFFF, true);

        int labelWidth = mc.font.width(label);
        int barX = x + labelWidth + 5;

        // 背景
        g.fill(barX, y, barX + BAR_WIDTH, y + BAR_HEIGHT, 0x88000000);

        // 塗り色: ゾーンに応じて変化
        int fillColor = getZoneColor(value, hasExcess);
        int filledWidth = Math.min((int) ((value / (float) maxValue) * BAR_WIDTH), BAR_WIDTH);
        if (filledWidth > 0) {
            g.fill(barX, y, barX + filledWidth, y + BAR_HEIGHT, fillColor);
        }

        // 欠乏ラインマーカー (黄色縦線): 欠乏境界位置
        int defX = barX + (int) ((DEFICIENCY_THRESHOLD / (float) maxValue) * BAR_WIDTH);
        g.fill(defX, y, defX + 1, y + BAR_HEIGHT, MARKER_DEFICIENCY);

        // 過剰ラインマーカー (橙色縦線): 過剰境界位置 (栄養素のみ)
        if (hasExcess) {
            int excessX = barX + (int) ((EXCESS_THRESHOLD / (float) maxValue) * BAR_WIDTH);
            g.fill(excessX, y, excessX + 1, y + BAR_HEIGHT, MARKER_EXCESS);
        }

        // 枠線
        g.fill(barX,              y,              barX + BAR_WIDTH, y + 1,            0xFFFFFFFF);
        g.fill(barX,              y + BAR_HEIGHT - 1, barX + BAR_WIDTH, y + BAR_HEIGHT, 0xFFFFFFFF);
        g.fill(barX,              y,              barX + 1,          y + BAR_HEIGHT,  0xFFFFFFFF);
        g.fill(barX + BAR_WIDTH - 1, y,           barX + BAR_WIDTH,  y + BAR_HEIGHT,  0xFFFFFFFF);
    }

    /**
     * 値とゾーンに応じたバー塗り色を返す
     */
    private int getZoneColor(int value, boolean hasExcess) {
        // 欠乏チェック
        VitalDeficiencyEffects.DeficiencyLevel defLevel =
                VitalDeficiencyEffects.DeficiencyLevel.fromValue(value);
        if (defLevel == VitalDeficiencyEffects.DeficiencyLevel.SEVERE)   return COLOR_SEVERE;
        if (defLevel == VitalDeficiencyEffects.DeficiencyLevel.MODERATE) return COLOR_MODERATE;
        if (defLevel == VitalDeficiencyEffects.DeficiencyLevel.MILD)     return COLOR_MILD;

        // 過剰チェック (栄養素のみ)
        if (hasExcess) {
            VitalDeficiencyEffects.ExcessLevel exLevel =
                    VitalDeficiencyEffects.ExcessLevel.fromValue(value);
            if (exLevel == VitalDeficiencyEffects.ExcessLevel.SEVERE)   return COLOR_SEVERE;
            if (exLevel == VitalDeficiencyEffects.ExcessLevel.MODERATE) return COLOR_MODERATE;
            if (exLevel == VitalDeficiencyEffects.ExcessLevel.MILD)     return COLOR_MILD;
        }

        // 健全ゾーン: 水分は青、栄養素は緑
        return hasExcess ? COLOR_HEALTHY : COLOR_HYDRATION;
    }

    private String getShortLabel(NutritionType type) {
        return switch (type) {
            case CARBOHYDRATE -> "C";
            case PROTEIN      -> "P";
            case LIPID        -> "L";
            case VITAMIN      -> "V";
            case MINERAL      -> "M";
        };
    }
}
