package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.DirtGolem;

public class DirtGolemRenderer extends MobRenderer<DirtGolem, LivingEntityRenderState, DirtGolemModel> {
    private static final Identifier GOLEM_LOCATION = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/dirt_golem.png");

    public DirtGolemRenderer(EntityRendererProvider.Context p_174188_) {
        super(p_174188_, new DirtGolemModel(p_174188_.bakeLayer(ModelLayers.IRON_GOLEM)), 0.7F);
    }

    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    
    public Identifier getTextureLocation(LivingEntityRenderState pState) {
        return GOLEM_LOCATION;
    }

    @Override
    protected void setupRotations(LivingEntityRenderState state, PoseStack p_115015_, float p_115016_, float p_115017_) {
        super.setupRotations(state, p_115015_, p_115016_, p_115017_);
        if (state.walkAnimationSpeed >= 0.01F) {
            float f = 13.0F;
            float f1 = state.walkAnimationPos + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            p_115015_.mulPose(Axis.ZP.rotationDegrees(6.5F * f2));
        }
    }
}
