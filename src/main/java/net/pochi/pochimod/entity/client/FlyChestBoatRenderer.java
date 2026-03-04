package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.FlyingChestBoatEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class FlyChestBoatRenderer extends GeoEntityRenderer<FlyingChestBoatEntity> {

    public FlyChestBoatRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FlyChestBoatModel());
    }

    @Override
    protected void applyRotations(FlyingChestBoatEntity animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick) {
        poseStack.translate(0,0.2,0);
        poseStack.mulPose(Axis.YP.rotationDegrees(-animatable.getYRot()));
        poseStack.mulPose(Axis.XP.rotationDegrees(-animatable.getXRot()));
        super.applyRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick);
    }

    @Override
    public ResourceLocation getTextureLocation(FlyingChestBoatEntity instance) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/chest_flyboat.png");
    }

    @Override
    public RenderType getRenderType(FlyingChestBoatEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }
}
