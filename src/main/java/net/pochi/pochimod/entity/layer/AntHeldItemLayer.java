package net.pochi.pochimod.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.pochi.pochimod.entity.client.AntModel;
import net.pochi.pochimod.entity.custom.Ant;

public class AntHeldItemLayer extends RenderLayer<Ant, AntModel<Ant>> {
    private final ItemInHandRenderer itemInHandRenderer;

    public AntHeldItemLayer(RenderLayerParent<Ant, AntModel<Ant>> p_234838_, ItemInHandRenderer p_234839_) {
        super(p_234838_);
        this.itemInHandRenderer = p_234839_;
    }

    public void render(PoseStack p_117007_, MultiBufferSource p_117008_, int p_117009_, Ant p_117010_, float p_117011_, float p_117012_, float p_117013_, float p_117014_, float p_117015_, float p_117016_) {
        //boolean flag = p_117010_.isSleeping();
        //boolean flag1 = p_117010_.isBaby();
        p_117007_.pushPose();
        //p_117007_.scale(0.75F, 0.75F, 0.75F);
        //p_117007_.translate(0.0F, 0.5F, 0.209375F);
        //p_117007_.translate((this.getParentModel()).getHead().x , (this.getParentModel()).getHead().y , (this.getParentModel()).getHead().z );
        //p_117007_.mulPose(Axis.ZP.rotation(p_117013_));
        //p_117007_.mulPose(Axis.YP.rotationDegrees(p_117015_));
        //p_117007_.mulPose(Axis.XP.rotationDegrees(p_117016_));
        p_117007_.translate(0.06F, 0.27F, -0.5F);
        //p_117007_.mulPose(Axis.XP.rotationDegrees(90.0F));
        ItemStack itemstack = p_117010_.getInventory().getItem(0);
        this.itemInHandRenderer.renderItem(p_117010_, itemstack, ItemDisplayContext.GROUND, false, p_117007_, p_117008_, p_117009_);
        p_117007_.popPose();
    }
}