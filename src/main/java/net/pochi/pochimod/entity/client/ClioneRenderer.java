package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.Clione;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class ClioneRenderer extends MobRenderer<Clione, ClioneModel<Clione>> {

    private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/clione.png");

    public ClioneRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ClioneModel<>(pContext.bakeLayer(ModModelLayers.CLIONE_LAYER)), 2f);
        this.shadowRadius = 0.5F;
    }

    @Override
    public void render(Clione pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Clione pEntity) {
        return TEXTURE_LOCATION;
    }
}
