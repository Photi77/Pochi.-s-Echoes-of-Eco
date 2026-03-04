package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.FruitFly;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class FruitFlyRenderer extends MobRenderer<FruitFly, FruitFlyModel<FruitFly>> {

    private static final ResourceLocation HERMIT_CRAB_LOCATION = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/fruit_fly.png");

    public FruitFlyRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FruitFlyModel<>(pContext.bakeLayer(ModModelLayers.FRUIT_FLY_LAYER)), 2f);
        this.shadowRadius = 0.5F;
    }

    @Override
    public void render(FruitFly pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(FruitFly pEntity) {
        return HERMIT_CRAB_LOCATION;
    }
}
