package net.pochi.pochimod.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class ItemCarryLayer<T extends LivingEntityRenderState, M extends EntityModel<? super T> & ArmedModel>
        extends RenderLayer<T, M> {

    public ItemCarryLayer(RenderLayerParent<T, M> p_234846_) {
        super(p_234846_);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int packedLight, T state, float yRot, float xRot) {
        // Item-in-hand rendering requires MultiBufferSource which is not available in the new
        // RenderLayer.submit() API in 1.21.11. Stubbed out.
    }
}
