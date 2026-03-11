package net.pochi.pochimod.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.pochi.pochimod.entity.projectile.PickaxeHead;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PickaxeHeadRenderer extends GeoEntityRenderer<PickaxeHead, GeckolibEntityRenderState> {
    public PickaxeHeadRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PickaxeHeadModel());
        this.shadowRadius = 0.5f;
    }
}
