package net.pochi.pochimod.entity.client;

import net.minecraft.resources.Identifier;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.Cicada;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class CicadaModel extends GeoModel<Cicada> {
    @Override
    public Identifier getModelResource(GeoRenderState state) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/cicada.geo.json");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState state) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/cicada_texture.png");
    }

    @Override
    public Identifier getAnimationResource(Cicada animatable) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/cicada.animation.json");
    }
}
