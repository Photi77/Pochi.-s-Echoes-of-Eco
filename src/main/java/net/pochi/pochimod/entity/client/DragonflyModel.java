package net.pochi.pochimod.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.Dragonfly;
import software.bernie.geckolib.model.GeoModel;

public class DragonflyModel extends GeoModel<Dragonfly> {
    @Override
    public ResourceLocation getModelResource(Dragonfly object) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/dragonfly.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Dragonfly object) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/dragonfly_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Dragonfly animatable) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/dragonfly.animation.json");
    }
}
