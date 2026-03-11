package net.pochi.pochimod.entity.client;

import net.minecraft.resources.Identifier;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.projectile.PickaxeHead;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class PickaxeHeadModel extends GeoModel<PickaxeHead> {
    @Override
    public Identifier getModelResource(GeoRenderState state) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/iron_head.geo.json");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState state) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/iron_pickaxe.png");
    }

    @Override
    public Identifier getAnimationResource(PickaxeHead animatable) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/iron_head.animation.json");
    }
}
