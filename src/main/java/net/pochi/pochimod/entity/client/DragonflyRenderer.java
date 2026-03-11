package net.pochi.pochimod.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.pochi.pochimod.entity.custom.Dragonfly;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DragonflyRenderer extends GeoEntityRenderer<Dragonfly, GeckolibEntityRenderState> {
    public DragonflyRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DragonflyModel());
        this.shadowRadius = 0.5f;
    }
}
