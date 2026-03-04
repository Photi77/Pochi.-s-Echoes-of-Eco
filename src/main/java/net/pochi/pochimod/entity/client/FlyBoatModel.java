package net.pochi.pochimod.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.FlyingBoatEntity;
import software.bernie.geckolib.model.GeoModel;

public class FlyBoatModel extends GeoModel<FlyingBoatEntity> {

    @Override
    public ResourceLocation getModelResource(FlyingBoatEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/flyboat.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FlyingBoatEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/flyboat.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FlyingBoatEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/yoshi.animation.json");
    }
}
