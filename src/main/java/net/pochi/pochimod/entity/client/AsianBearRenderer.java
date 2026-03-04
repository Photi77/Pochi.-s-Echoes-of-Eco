package net.pochi.pochimod.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PolarBearRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.PolarBear;
import net.pochi.pochimod.PochiMod;

public class AsianBearRenderer extends PolarBearRenderer {

    private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/asian_bear.png");


    public AsianBearRenderer(EntityRendererProvider.Context p_174356_) {
        super(p_174356_);
    }

    @Override
    public ResourceLocation getTextureLocation(PolarBear p_115732_) {
        return TEXTURE_LOCATION;
    }
}
