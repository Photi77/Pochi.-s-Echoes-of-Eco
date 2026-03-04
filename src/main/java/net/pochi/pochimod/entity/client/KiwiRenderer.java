package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.Kiwi;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class KiwiRenderer extends MobRenderer<Kiwi, KiwiModel<Kiwi>> {

    private static final ResourceLocation HERMIT_CRAB_LOCATION = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/kiwi.png");

    public KiwiRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new KiwiModel<>(pContext.bakeLayer(ModModelLayers.KIWI_LAYER)), 2f);
        this.shadowRadius = 0.5F;
    }

    @Override
    public void render(Kiwi pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        } else {
            pMatrixStack.scale(0.8f, 0.8f, 0.8f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Kiwi pEntity) {
        return HERMIT_CRAB_LOCATION;
    }
}
