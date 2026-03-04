package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.Capybara;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class CapybaraRenderer extends MobRenderer<Capybara, CapybaraModel<Capybara>> {

    private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/capybara.png");

    public CapybaraRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CapybaraModel<>(pContext.bakeLayer(ModModelLayers.CAPYBARA_LAYER)), 2f);
        this.shadowRadius = 0.5F;
    }

    @Override
    public void render(Capybara pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Capybara pEntity) {
        return TEXTURE_LOCATION;
    }
}
