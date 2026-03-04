package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.MiniHipo;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class MiniHipoRenderer extends MobRenderer<MiniHipo, MiniHipoModel<MiniHipo>> {
    private static final ResourceLocation HERMIT_CRAB_LOCATION = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/minihipo.png");

    public MiniHipoRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MiniHipoModel<>(pContext.bakeLayer(ModModelLayers.MINI_HIPO_LAYER)), 2f);
        this.shadowRadius = 0.5F;
    }

    @Override
    public void render(MiniHipo pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(MiniHipo pEntity) {
        return HERMIT_CRAB_LOCATION;
    }
}