package net.pochi.pochimod.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.pochi.pochimod.PochiMod;

public class MixerScreen extends AbstractContainerScreen<MixerMenu> {
    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/gui/mixer_gui.png");

    public MixerScreen(MixerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(GuiGraphics p_283065_, float p_97788_, int p_97789_, int p_97790_) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        p_283065_.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x, y, 0f, 0f, imageWidth, imageHeight, 256, 256);

        if(menu.isCrafting()) {
            p_283065_.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x + 89, y + 34, 176f, 14f, menu.getScaledProgress(), 36, 256, 256);
        }
    }
}
