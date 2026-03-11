package net.pochi.pochimod.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.pochi.pochimod.entity.client.AntModel;

public class AntHeldItemLayer extends RenderLayer<LivingEntityRenderState, AntModel> {
    private final ItemInHandRenderer itemInHandRenderer;

    public AntHeldItemLayer(RenderLayerParent<LivingEntityRenderState, AntModel> p_234838_, ItemInHandRenderer p_234839_) {
        super(p_234838_);
        this.itemInHandRenderer = p_234839_;
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int packedLight, LivingEntityRenderState state, float yRot, float xRot) {
        // Item rendering in layers requires MultiBufferSource; stubbed out in 1.21.11 render state API
    }
}
