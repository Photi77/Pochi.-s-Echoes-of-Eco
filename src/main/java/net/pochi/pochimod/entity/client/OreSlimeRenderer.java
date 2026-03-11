package net.pochi.pochimod.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.pochi.pochimod.entity.projectile.OreSlime;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OreSlimeRenderer extends GeoEntityRenderer<OreSlime, GeckolibEntityRenderState> {
    public OreSlimeRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OreSlimeModel());
        this.shadowRadius = 0.5f;
    }
}
