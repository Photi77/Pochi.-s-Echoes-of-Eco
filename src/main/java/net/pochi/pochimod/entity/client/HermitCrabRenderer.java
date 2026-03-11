package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockState;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.HermitCrab;
import net.pochi.pochimod.entity.layer.HermitCrabBlockLayer;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class HermitCrabRenderer extends MobRenderer<HermitCrab, LivingEntityRenderState, HermitCrabModel> {
    private static final Identifier HERMIT_CRAB_LOCATION = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/hermit_crab_textures.png");

    public HermitCrabRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new HermitCrabModel(pContext.bakeLayer(ModModelLayers.HERMIT_CRAB_LAYER)), 2f);
        this.addLayer(new net.pochi.pochimod.entity.layer.HermitCrabBlockLayer(this, pContext.getBlockRenderDispatcher()));
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