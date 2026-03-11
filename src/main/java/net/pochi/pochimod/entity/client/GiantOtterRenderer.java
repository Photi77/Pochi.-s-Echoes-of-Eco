package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.GiantOtter;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class GiantOtterRenderer extends MobRenderer<GiantOtter, LivingEntityRenderState, GiantOtterModel> {
    private static final Identifier HERMIT_CRAB_LOCATION = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/giant_otter.png");

    public GiantOtterRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new GiantOtterModel(pContext.bakeLayer(ModModelLayers.GIANT_OTTER_LAYER)), 2f);
        this.shadowRadius = 0.5F;
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    
    public Identifier getTextureLocation(LivingEntityRenderState pState) {
        return HERMIT_CRAB_LOCATION;
    }
}