package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.Platypus;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class PlatypusRenderer extends MobRenderer<Platypus, PlatypusModel<Platypus>> {

    private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/platypus.png");

    public PlatypusRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new PlatypusModel<>(pContext.bakeLayer(ModModelLayers.PLATYPUS_LAYER)), 2f);
        this.shadowRadius = 0.5F;
    }

    @Override
    public void render(Platypus pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Platypus pEntity) {
        return TEXTURE_LOCATION;
    }
}
