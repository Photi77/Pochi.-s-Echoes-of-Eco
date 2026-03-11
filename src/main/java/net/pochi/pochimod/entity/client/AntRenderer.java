package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.Ant;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class AntRenderer extends MobRenderer<Ant, LivingEntityRenderState, AntModel> {

    private static final Identifier TEXTURE_LOCATION = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/ant.png");

    public AntRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AntModel(pContext.bakeLayer(ModModelLayers.ANT_LAYER)), 2f);
        //this.addLayer(new AntHeldItemLayer(this,pContext.getItemInHandRenderer()));
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
