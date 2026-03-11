package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.ElectricCatfish;
import net.pochi.pochimod.entity.layer.ModModelLayers;

public class ElectricCatfishRenderer extends MobRenderer<ElectricCatfish, LivingEntityRenderState, ElectricCatfishModel> {

    private static final Identifier TEXTURE_LOCATION = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/electric_catfish.png");

    public ElectricCatfishRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ElectricCatfishModel(pContext.bakeLayer(ModModelLayers.ELECTRIC_CATFISH_LAYER)), 2f);
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
