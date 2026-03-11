package net.pochi.pochimod.entity.client;

import net.minecraft.resources.Identifier;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.FlyingBoatEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class FlyBoatModel extends GeoModel<FlyingBoatEntity> {

    @Override
    public Identifier getModelResource(GeoRenderState state) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/flyboat.geo.json");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState state) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/flyboat.png");
    }

    @Override
    public Identifier getAnimationResource(FlyingBoatEntity animatable) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/yoshi.animation.json");
    }
}
