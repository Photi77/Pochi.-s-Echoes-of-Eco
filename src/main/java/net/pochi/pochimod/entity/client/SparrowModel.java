package net.pochi.pochimod.entity.client;

import net.minecraft.resources.Identifier;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.SparrowEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class SparrowModel extends GeoModel<SparrowEntity> {
    @Override
    public Identifier getModelResource(GeoRenderState state) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/sparrow.geo.json");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState state) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/sparrow_texture.png");
    }

    @Override
    public Identifier getAnimationResource(SparrowEntity animatable) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/sparrow.animation.json");
    }
}
