package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.Quokka;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class QuokkaRenderer extends MobRenderer<Quokka, QuokkaModel<Quokka>> {

    private static final ResourceLocation HERMIT_CRAB_LOCATION = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/quokka.png");

    public QuokkaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new QuokkaModel<>(pContext.bakeLayer(ModModelLayers.QUOKKA_LAYER)), 2f);
        this.shadowRadius = 0.5F;
    }

    @Override
    public void render(Quokka pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.4f, 0.4f, 0.4f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Quokka pEntity) {
        return HERMIT_CRAB_LOCATION;
    }
}
