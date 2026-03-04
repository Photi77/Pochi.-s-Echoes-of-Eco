package net.pochi.pochimod.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.projectile.PickaxeHead;
import software.bernie.geckolib.model.GeoModel;

public class PickaxeHeadModel extends GeoModel<PickaxeHead> {
    @Override
    public ResourceLocation getModelResource(PickaxeHead object) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/iron_head.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PickaxeHead object) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/iron_pickaxe.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PickaxeHead animatable) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/iron_head.animation.json");
    }
}
