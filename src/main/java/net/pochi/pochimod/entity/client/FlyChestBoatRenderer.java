package net.pochi.pochimod.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.pochi.pochimod.entity.custom.FlyingChestBoatEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FlyChestBoatRenderer extends GeoEntityRenderer<FlyingChestBoatEntity, GeckolibEntityRenderState> {

    public FlyChestBoatRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FlyChestBoatModel());
    }
}
