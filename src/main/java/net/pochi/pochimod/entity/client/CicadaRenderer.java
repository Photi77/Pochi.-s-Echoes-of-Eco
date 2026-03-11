package net.pochi.pochimod.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.pochi.pochimod.entity.custom.Cicada;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CicadaRenderer extends GeoEntityRenderer<Cicada, GeckolibEntityRenderState> {
    public CicadaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CicadaModel());
        this.shadowRadius = 0.5f;
    }
}
