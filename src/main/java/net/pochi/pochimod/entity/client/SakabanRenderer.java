package net.pochi.pochimod.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.pochi.pochimod.entity.custom.Sakaban;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SakabanRenderer extends GeoEntityRenderer<Sakaban, GeckolibEntityRenderState> {

    public SakabanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SakabanModel());
        this.shadowRadius = 0.5f;
    }
}
