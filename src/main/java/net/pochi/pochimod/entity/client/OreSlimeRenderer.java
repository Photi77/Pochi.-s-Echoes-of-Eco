package net.pochi.pochimod.entity.client;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.projectile.OreSlime;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class OreSlimeRenderer extends GeoEntityRenderer<OreSlime> {
    public OreSlimeRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OreSlimeModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(OreSlime instance) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/oreslime.png");
    }

    @Override
    public RenderType getRenderType(OreSlime animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }
}
