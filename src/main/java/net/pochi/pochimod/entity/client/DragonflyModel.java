package net.pochi.pochimod.entity.client;

import net.minecraft.resources.Identifier;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.Dragonfly;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class DragonflyModel extends GeoModel<Dragonfly> {
    @Override
    public Identifier getModelResource(GeoRenderState state) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/dragonfly.geo.json");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState state) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/dragonfly_texture.png");
    }

    @Override
    public Identifier getAnimationResource(Dragonfly animatable) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/dragonfly.animation.json");
    }
}
