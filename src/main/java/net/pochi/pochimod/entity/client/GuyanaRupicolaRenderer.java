package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.GuyanaRupicola;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class GuyanaRupicolaRenderer extends MobRenderer<GuyanaRupicola, LivingEntityRenderState, GuyanaRupicolaModel> {

    private static final Identifier HERMIT_CRAB_LOCATION = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/guyana_rupicola.png");

    public GuyanaRupicolaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new GuyanaRupicolaModel(pContext.bakeLayer(ModModelLayers.RUPICO_LAYER)), 2f);
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
