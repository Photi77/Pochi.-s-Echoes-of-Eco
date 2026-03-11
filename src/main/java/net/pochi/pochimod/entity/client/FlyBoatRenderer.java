package net.pochi.pochimod.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.pochi.pochimod.entity.custom.FlyingBoatEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.RenderPassInfo;

public class FlyBoatRenderer extends GeoEntityRenderer<FlyingBoatEntity, GeckolibEntityRenderState> {

    public FlyBoatRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FlyBoatModel());
    }
}
