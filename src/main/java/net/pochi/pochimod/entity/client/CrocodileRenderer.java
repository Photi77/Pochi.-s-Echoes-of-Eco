package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.Crocodile;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class CrocodileRenderer extends MobRenderer<Crocodile, CrocodileModel<Crocodile>> {

    private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/crocodile.png");

    public CrocodileRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CrocodileModel<>(pContext.bakeLayer(ModModelLayers.CROCODILE_LAYER)), 2f);
        this.shadowRadius = 0.5F;
    }

    @Override
    public void render(Crocodile pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        } else {
            pMatrixStack.scale(1.2f, 1.2f, 1.2f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Crocodile pEntity) {
        return TEXTURE_LOCATION;
    }
}
