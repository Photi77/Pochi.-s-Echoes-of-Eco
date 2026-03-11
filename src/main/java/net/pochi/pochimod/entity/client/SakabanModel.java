package net.pochi.pochimod.entity.client;

import net.minecraft.resources.Identifier;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.Sakaban;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class SakabanModel extends GeoModel<Sakaban> {
    @Override
    public Identifier getModelResource(GeoRenderState state) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/sakaban.geo.json");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState state) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/sakaban_texture.png");
    }

    @Override
    public Identifier getAnimationResource(Sakaban animatable) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/sakaban.animation.json");
    }
}
