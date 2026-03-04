package net.pochi.pochimod.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.FlyingChestBoatEntity;
import software.bernie.geckolib.model.GeoModel;

public class FlyChestBoatModel extends GeoModel<FlyingChestBoatEntity> {

    @Override
    public ResourceLocation getModelResource(FlyingChestBoatEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/chest_flyboat.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FlyingChestBoatEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/chest_flyboat.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FlyingChestBoatEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/yoshi.animation.json");
    }
}
