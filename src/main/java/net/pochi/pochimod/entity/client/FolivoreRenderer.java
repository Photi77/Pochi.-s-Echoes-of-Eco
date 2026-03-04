package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.Folivore;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class FolivoreRenderer extends MobRenderer<Folivore, FolivoreModel<Folivore>> {

    private static final ResourceLocation HERMIT_CRAB_LOCATION = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/folivore.png");

    public FolivoreRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FolivoreModel<>(pContext.bakeLayer(ModModelLayers.FPLIVORE_LAYER)), 2f);
        this.shadowRadius = 0.5F;
    }

    @Override
    public void render(Folivore pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.scale(0.7f,0.7f,0.7f);
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Folivore pEntity) {
        return HERMIT_CRAB_LOCATION;
    }

}
