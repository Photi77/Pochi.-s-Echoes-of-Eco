package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.HermitCrab;
import net.pochi.pochimod.entity.layer.HermitCrabBlockLayer;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class HermitCrabRenderer extends MobRenderer<HermitCrab, HermitCrabModel<HermitCrab>> {
    private static final ResourceLocation HERMIT_CRAB_LOCATION = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/hermit_crab_textures.png");

    public HermitCrabRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new HermitCrabModel<>(pContext.bakeLayer(ModModelLayers.HERMIT_CRAB_LAYER)), 2f);
        this.addLayer(new HermitCrabBlockLayer(this, pContext.getBlockRenderDispatcher()));
        this.shadowRadius = 0.5F;
    }

    @Override
    public ResourceLocation getTextureLocation(HermitCrab pEntity) {
        return HERMIT_CRAB_LOCATION;
    }

    @Override
    public void render(HermitCrab pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        BlockState blockstate = pEntity.getCarriedBlock();
        HermitCrabModel<HermitCrab> endermanmodel = this.getModel();
        endermanmodel.carrying = blockstate != null;
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        }

        //BlockRenderDispatcher blockRenderDispatcher = Minecraft.getInstance().getBlockRenderer();
        //TextureAtlasSprite sprite = blockRenderDispatcher.getBlockModelShaper().getParticleIcon(blockstate);
        //VertexConsumer vertexConsumer = pBuffer.getBuffer(RenderType.entityCutoutNoCull(sprite.atlasLocation()));
        //this.model.renderToBuffer(pMatrixStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}