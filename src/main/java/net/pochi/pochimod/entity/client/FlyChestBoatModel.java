package net.pochi.pochimod.entity.client;

import net.minecraft.resources.Identifier;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.FlyingChestBoatEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class FlyChestBoatModel extends GeoModel<FlyingChestBoatEntity> {

    @Override
    public Identifier getModelResource(GeoRenderState state) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/chest_flyboat.geo.json");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState state) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/chest_flyboat.png");
    }

    @Override
    public Identifier getAnimationResource(FlyingChestBoatEntity animatable) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/yoshi.animation.json");
    }
}
