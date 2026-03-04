package net.pochi.pochimod.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.Snake;
import software.bernie.geckolib.model.GeoModel;

public class SnakeModel extends GeoModel<Snake> {
    @Override
    public ResourceLocation getModelResource(Snake animatable) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/snake.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Snake animatable) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/snake.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Snake animatable) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/snake.animation.json");
    }
}
