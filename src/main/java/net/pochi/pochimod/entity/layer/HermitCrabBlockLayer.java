package net.pochi.pochimod.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.state.BlockState;
import net.pochi.pochimod.entity.client.HermitCrabModel;
import net.pochi.pochimod.entity.custom.HermitCrab;

public class HermitCrabBlockLayer extends RenderLayer<HermitCrab, HermitCrabModel<HermitCrab>> {
    private final BlockRenderDispatcher blockRenderer;

    public HermitCrabBlockLayer(RenderLayerParent<HermitCrab, HermitCrabModel<HermitCrab>> p_234814_, BlockRenderDispatcher p_234815_) {
        super(p_234814_);
        this.blockRenderer = p_234815_;
    }

    public void render(PoseStack p_116639_, MultiBufferSource p_116640_, int p_116641_, HermitCrab p_116642_, float p_116643_, float p_116644_, float p_116645_, float p_116646_, float p_116647_, float p_116648_) {
        BlockState blockstate = p_116642_.getCarriedBlock();
        if (blockstate != null) {
            p_116639_.pushPose();
            p_116639_.mulPose(Axis.XP.rotationDegrees(135.0F));
            p_116639_.translate(0.7F, -0.7F, -0.6F);
            float f = 0.5F;
            p_116639_.scale(-1.2F, 1.2F, -1.2F);
            p_116639_.mulPose(Axis.YP.rotationDegrees(30.0F));
            this.blockRenderer.renderSingleBlock(blockstate, p_116639_, p_116640_, p_116641_, OverlayTexture.NO_OVERLAY);
            p_116639_.popPose();
        }
    }
}
