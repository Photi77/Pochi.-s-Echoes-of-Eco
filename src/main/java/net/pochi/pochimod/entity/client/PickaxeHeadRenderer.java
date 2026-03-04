package net.pochi.pochimod.entity.client;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.projectile.PickaxeHead;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class PickaxeHeadRenderer extends GeoEntityRenderer<PickaxeHead> {
    public PickaxeHeadRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PickaxeHeadModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(PickaxeHead instance) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/iron_pickaxe.png");
    }

    @Override
    public RenderType getRenderType(PickaxeHead animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }
}
