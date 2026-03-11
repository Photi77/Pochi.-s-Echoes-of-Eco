package net.pochi.pochimod.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.pochi.pochimod.entity.custom.SparrowEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SparrowRenderer extends GeoEntityRenderer<SparrowEntity, GeckolibEntityRenderState> {

    public SparrowRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SparrowModel());
        this.shadowRadius = 0.3f;
    }
}
