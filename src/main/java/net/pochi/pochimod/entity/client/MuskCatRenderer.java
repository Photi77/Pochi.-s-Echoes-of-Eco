package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.MuskCat;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class MuskCatRenderer extends MobRenderer<MuskCat, MuskCatModel<MuskCat>> {
    private static final ResourceLocation HERMIT_CRAB_LOCATION = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/musk_cat.png");

    public MuskCatRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MuskCatModel<>(pContext.bakeLayer(ModModelLayers.MUSK_LAYER)), 2f);
        this.shadowRadius = 0.5F;
    }

    @Override
    public void render(MuskCat pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(MuskCat pEntity) {
        return HERMIT_CRAB_LOCATION;
    }
}