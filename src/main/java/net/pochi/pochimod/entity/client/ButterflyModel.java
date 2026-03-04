package net.pochi.pochimod.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.Butterfly;
import software.bernie.geckolib.model.GeoModel;

public class ButterflyModel extends GeoModel<Butterfly> {
    @Override
    public ResourceLocation getModelResource(Butterfly object) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/butterfly.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Butterfly object) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/butterfly_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Butterfly animatable) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/butterfly.animation.json");
    }
}
