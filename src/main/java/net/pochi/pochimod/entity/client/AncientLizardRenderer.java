package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.AncientLizard;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class AncientLizardRenderer extends MobRenderer<AncientLizard, LivingEntityRenderState, AncientLizardModel> {

    private static final Identifier TEXTURE_LOCATION = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/ancient_lizard.png");

    public AncientLizardRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AncientLizardModel(pContext.bakeLayer(ModModelLayers.ANCIENT_LIZARD_LAYER)), 2f);
        this.shadowRadius = 0.5F;
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    
    public Identifier getTextureLocation(LivingEntityRenderState pState) {
        return TEXTURE_LOCATION;
    }
}
