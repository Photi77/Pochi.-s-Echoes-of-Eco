package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.Ant;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class AntRenderer extends MobRenderer<Ant, AntModel<Ant>> {

    private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/ant.png");

    public AntRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AntModel<>(pContext.bakeLayer(ModModelLayers.ANT_LAYER)), 2f);
        //this.addLayer(new AntHeldItemLayer(this,pContext.getItemInHandRenderer()));
        this.shadowRadius = 0.5F;
    }

    @Override
    public void render(Ant pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Ant pEntity) {
        return TEXTURE_LOCATION;
    }
}
