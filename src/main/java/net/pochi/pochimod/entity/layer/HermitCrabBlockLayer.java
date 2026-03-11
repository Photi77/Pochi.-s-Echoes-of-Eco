package net.pochi.pochimod.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.level.block.state.BlockState;
import net.pochi.pochimod.entity.client.HermitCrabModel;
import net.pochi.pochimod.entity.custom.HermitCrab;

public class HermitCrabBlockLayer extends RenderLayer<LivingEntityRenderState, HermitCrabModel> {
    private final BlockRenderDispatcher blockRenderer;

    public HermitCrabBlockLayer(RenderLayerParent<LivingEntityRenderState, HermitCrabModel> p_234814_, BlockRenderDispatcher p_234815_) {
        super(p_234814_);
        this.blockRenderer = p_234815_;
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int packedLight, LivingEntityRenderState state, float yRot, float xRot) {
        // Block-on-back rendering requires entity reference; skipped in render-state API
    }
}
