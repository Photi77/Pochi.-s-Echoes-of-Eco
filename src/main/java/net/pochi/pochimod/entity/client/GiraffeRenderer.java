package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.Giraffe;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class GiraffeRenderer extends MobRenderer<Giraffe, GiraffeModel<Giraffe>> {

    private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/giraffe.png");

    public GiraffeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new GiraffeModel<>(pContext.bakeLayer(ModModelLayers.GIRAFFE_LAYER)), 2f);
        this.shadowRadius = 0.5F;
    }

    @Override
    public void render(Giraffe pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        } else {
            pMatrixStack.scale(1.5f, 1.5f, 1.5f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Giraffe pEntity) {
        return TEXTURE_LOCATION;
    }
}
