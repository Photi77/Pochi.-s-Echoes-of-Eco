package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.HarpyEagle;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class HarpyEagleRenderer extends MobRenderer<HarpyEagle, HarpyEagleModel<HarpyEagle>> {

    private static final ResourceLocation HERMIT_CRAB_LOCATION = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/harpy_eagle.png");

    public HarpyEagleRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new HarpyEagleModel<>(pContext.bakeLayer(ModModelLayers.HARPY_LAYER)), 2f);
        this.shadowRadius = 0.5F;
    }

    @Override
    public void render(HarpyEagle pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.scale(1.8f, 1.8f, 1.8f);
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(HarpyEagle pEntity) {
        return HERMIT_CRAB_LOCATION;
    }

}
