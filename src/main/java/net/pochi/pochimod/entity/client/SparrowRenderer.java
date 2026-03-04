package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.SparrowEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class SparrowRenderer extends GeoEntityRenderer<SparrowEntity> {

    public SparrowRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SparrowModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(SparrowEntity instance) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/sparrow_texture.png");
    }

    @Override
    public void render(SparrowEntity pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public RenderType getRenderType(SparrowEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }
}
