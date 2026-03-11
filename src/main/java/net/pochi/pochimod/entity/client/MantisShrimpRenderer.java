package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.MantisShrimp;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class MantisShrimpRenderer extends MobRenderer<MantisShrimp, LivingEntityRenderState, MantisShrimpModel> {

    private static final Identifier HERMIT_CRAB_LOCATION = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/mantis_shrimp.png");

    public MantisShrimpRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MantisShrimpModel(pContext.bakeLayer(ModModelLayers.MANTIS_SHRIMP_LAYER)), 2f);
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
