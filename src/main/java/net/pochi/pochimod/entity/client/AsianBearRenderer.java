package net.pochi.pochimod.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PolarBearRenderer;
import net.minecraft.client.renderer.entity.state.PolarBearRenderState;
import net.minecraft.resources.Identifier;
import net.pochi.pochimod.PochiMod;

public class AsianBearRenderer extends PolarBearRenderer {

    private static final Identifier TEXTURE_LOCATION = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/asian_bear.png");


    public AsianBearRenderer(EntityRendererProvider.Context p_174356_) {
        super(p_174356_);
    }

    @Override
    public Identifier getTextureLocation(PolarBearRenderState state) {
        return TEXTURE_LOCATION;
    }
}
