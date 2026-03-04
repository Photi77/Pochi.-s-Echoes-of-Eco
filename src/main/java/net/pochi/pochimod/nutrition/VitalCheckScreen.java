package net.pochi.pochimod.nutrition;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

/**
 * バイタルチェック画面
 * 水分と栄養素をカラーバーで視覚的に表示
 */
@OnlyIn(Dist.CLIENT)
public class VitalCheckScreen extends Screen {

    // 画面サイズ
    private static final int WINDOW_WIDTH = 256;
    private static final int WINDOW_HEIGHT = 200;

    // カラーバーの設定
    private static final int BAR_WIDTH = 200;
    private static final int BAR_HEIGHT = 16;
    private static final int BAR_SPACING = 24;
    private static final int BAR_START_Y = 50;

    // 色定義
    private static final int BACKGROUND_COLOR = 0xC0101010; // 半透明黒
    private static final int BORDER_COLOR = 0xFF8B8B8B;
    private static final int BAR_BACKGROUND_COLOR = 0xFF3A3A3A;
    private static final int HYDRATION_COLOR = 0xFF55AAFF; // 水色

    private final PlayerVitalData vitalData;
    private int leftPos;
    private int topPos;

    public VitalCheckScreen(PlayerVitalData vitalData) {
        super(Component.translatable("gui.yourmod.vital_check.title"));
        this.vitalData = vitalData;
    }

    @Override
    protected void init() {
        super.init();

        // 画面中央に配置
        this.leftPos = (this.width - WINDOW_WIDTH) / 2;
        this.topPos = (this.height - WINDOW_HEIGHT) / 2;

        // 閉じるボタンを追加
        this.addRenderableWidget(Button.builder(
                Component.translatable("gui.yourmod.vital_check.close"),
                button -> this.onClose()
        ).bounds(leftPos + WINDOW_WIDTH / 2 - 50, topPos + WINDOW_HEIGHT - 30, 100, 20).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // 背景を暗くする
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);

        // ウィンドウ背景
        renderWindow(guiGraphics);

        // タイトル
        renderTitle(guiGraphics);

        // 水分ゲージ
        renderHydrationBar(guiGraphics);

        // 栄養素バー
        renderNutritionBars(guiGraphics);

        // ボタンなどのウィジェット
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        // ホバーテキスト（ツールチップ）
        renderTooltips(guiGraphics, mouseX, mouseY);
    }

    /**
     * ウィンドウ背景を描画
     */
    private void renderWindow(GuiGraphics guiGraphics) {
        // 背景
        guiGraphics.fill(leftPos, topPos, leftPos + WINDOW_WIDTH, topPos + WINDOW_HEIGHT, BACKGROUND_COLOR);

        // 枠線
        guiGraphics.fill(leftPos, topPos, leftPos + WINDOW_WIDTH, topPos + 2, BORDER_COLOR); // 上
        guiGraphics.fill(leftPos, topPos + WINDOW_HEIGHT - 2, leftPos + WINDOW_WIDTH, topPos + WINDOW_HEIGHT, BORDER_COLOR); // 下
        guiGraphics.fill(leftPos, topPos, leftPos + 2, topPos + WINDOW_HEIGHT, BORDER_COLOR); // 左
        guiGraphics.fill(leftPos + WINDOW_WIDTH - 2, topPos, leftPos + WINDOW_WIDTH, topPos + WINDOW_HEIGHT, BORDER_COLOR); // 右
    }

    /**
     * タイトルを描画
     */
    private void renderTitle(GuiGraphics guiGraphics) {
        Component title = Component.translatable("gui.yourmod.vital_check.title");
        int titleWidth = this.font.width(title);
        int titleX = leftPos + (WINDOW_WIDTH - titleWidth) / 2;
        int titleY = topPos + 15;

        guiGraphics.drawString(this.font, title, titleX, titleY, 0xFFFFFF, true);
    }

    /**
     * 水分ゲージを描画
     */
    private void renderHydrationBar(GuiGraphics guiGraphics) {
        int barX = leftPos + (WINDOW_WIDTH - BAR_WIDTH) / 2;
        int barY = topPos + BAR_START_Y;

        // ラベル
        Component label = Component.translatable("gui.yourmod.vital_check.hydration");
        guiGraphics.drawString(this.font, label, barX, barY - 12, 0xFFFFFF, false);

        // バー背景
        guiGraphics.fill(barX, barY, barX + BAR_WIDTH, barY + BAR_HEIGHT, BAR_BACKGROUND_COLOR);

        // バー（水分値に応じた長さ）
        int hydration = vitalData.getHydration();
        int filledWidth = (int) ((hydration / 100.0) * BAR_WIDTH);
        guiGraphics.fill(barX, barY, barX + filledWidth, barY + BAR_HEIGHT, HYDRATION_COLOR);

        // 数値表示
        String valueText = hydration + " / 100";
        int textWidth = this.font.width(valueText);
        int textX = barX + (BAR_WIDTH - textWidth) / 2;
        int textY = barY + (BAR_HEIGHT - 8) / 2;
        guiGraphics.drawString(this.font, valueText, textX, textY, 0xFFFFFF, true);
    }

    /**
     * 栄養素バーを描画
     */
    private void renderNutritionBars(GuiGraphics guiGraphics) {
        int barX = leftPos + (WINDOW_WIDTH - BAR_WIDTH) / 2;
        int startY = topPos + BAR_START_Y + BAR_SPACING;

        NutritionType[] types = NutritionType.getAllTypes();
        for (int i = 0; i < types.length; i++) {
            NutritionType type = types[i];
            int barY = startY + (i * BAR_SPACING);

            renderNutritionBar(guiGraphics, type, barX, barY);
        }
    }

    /**
     * 個別の栄養素バーを描画
     */
    private void renderNutritionBar(GuiGraphics guiGraphics, NutritionType type, int x, int y) {
        // ラベル（栄養素名）
        Component label = type.getDisplayName();
        guiGraphics.drawString(this.font, label, x, y - 12, 0xFFFFFF, false);

        // バー背景
        guiGraphics.fill(x, y, x + BAR_WIDTH, y + BAR_HEIGHT, BAR_BACKGROUND_COLOR);

        // バー（栄養値に応じた長さ）
        int nutritionValue = vitalData.getNutrition(type);
        int filledWidth = (int) ((nutritionValue / 100.0) * BAR_WIDTH);
        int barColor = type.getColor();
        guiGraphics.fill(x, y, x + filledWidth, y + BAR_HEIGHT, barColor);

        // 数値表示
        String valueText = nutritionValue + " / 100";
        int textWidth = this.font.width(valueText);
        int textX = x + (BAR_WIDTH - textWidth) / 2;
        int textY = y + (BAR_HEIGHT - 8) / 2;
        guiGraphics.drawString(this.font, valueText, textX, textY, 0xFFFFFF, true);
    }

    /**
     * ホバー時のツールチップを描画
     */
    private void renderTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        int barX = leftPos + (WINDOW_WIDTH - BAR_WIDTH) / 2;

        // 水分ゲージのツールチップ
        int hydrationBarY = topPos + BAR_START_Y;
        if (isMouseOver(mouseX, mouseY, barX, hydrationBarY, BAR_WIDTH, BAR_HEIGHT)) {
            guiGraphics.renderTooltip(this.font,
                    Component.translatable("gui.yourmod.vital_check.hydration.tooltip"),
                    mouseX, mouseY);
        }

        // 栄養素バーのツールチップ
        int startY = topPos + BAR_START_Y + BAR_SPACING;
        NutritionType[] types = NutritionType.getAllTypes();
        for (int i = 0; i < types.length; i++) {
            NutritionType type = types[i];
            int barY = startY + (i * BAR_SPACING);

            if (isMouseOver(mouseX, mouseY, barX, barY, BAR_WIDTH, BAR_HEIGHT)) {
                String tooltipKey = "gui.yourmod.vital_check.nutrition." + type.getName() + ".tooltip";
                guiGraphics.renderTooltip(this.font,
                        Component.translatable(tooltipKey),
                        mouseX, mouseY);
            }
        }
    }

    /**
     * マウスが指定領域上にあるか判定
     */
    private boolean isMouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }

    @Override
    public boolean isPauseScreen() {
        // この画面を開いてもゲームを一時停止しない
        return false;
    }
}