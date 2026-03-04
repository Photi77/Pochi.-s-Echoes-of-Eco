package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.PallasCat;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class PallasCatRenderer extends MobRenderer<PallasCat, PallasCatModel<PallasCat>> {

    private static final ResourceLocation HERMIT_CRAB_LOCATION = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/pallas_cat.png");

    public PallasCatRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new PallasCatModel<>(pContext.bakeLayer(ModModelLayers.PALLAS_CAT_LAYER)), 2f);
        this.shadowRadius = 0.5F;
    }

    @Override
    public void render(PallasCat pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(PallasCat pEntity) {
        return HERMIT_CRAB_LOCATION;
    }
}
