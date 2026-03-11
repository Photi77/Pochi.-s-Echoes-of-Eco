package net.pochi.pochimod.entity.client;

import net.minecraft.resources.Identifier;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.Butterfly;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class ButterflyModel extends GeoModel<Butterfly> {
    @Override
    public Identifier getModelResource(GeoRenderState state) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/butterfly.geo.json");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState state) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/butterfly_texture.png");
    }

    @Override
    public Identifier getAnimationResource(Butterfly animatable) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/butterfly.animation.json");
    }
}
