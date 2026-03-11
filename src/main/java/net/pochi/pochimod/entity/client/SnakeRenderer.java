package net.pochi.pochimod.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.pochi.pochimod.entity.custom.Snake;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SnakeRenderer extends GeoEntityRenderer<Snake, GeckolibEntityRenderState> {
    public SnakeRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SnakeModel());
        this.shadowRadius = 0.5f;
    }
}
