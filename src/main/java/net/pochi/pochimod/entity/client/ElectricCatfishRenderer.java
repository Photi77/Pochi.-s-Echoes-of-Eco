package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.ElectricCatfish;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class ElectricCatfishRenderer extends MobRenderer<ElectricCatfish, ElectricCatfishModel<ElectricCatfish>> {

    private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/electric_catfish.png");

    public ElectricCatfishRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ElectricCatfishModel<>(pContext.bakeLayer(ModModelLayers.ELECTRIC_CATFISH_LAYER)), 2f);
        this.shadowRadius = 0.5F;
    }

    @Override
    public void render(ElectricCatfish pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(ElectricCatfish pEntity) {
        return TEXTURE_LOCATION;
    }
}
