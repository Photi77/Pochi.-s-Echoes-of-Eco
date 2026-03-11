package net.pochi.pochimod.entity.client;

import net.minecraft.resources.Identifier;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.projectile.OreSlime;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class OreSlimeModel extends GeoModel<OreSlime> {
    @Override
    public Identifier getModelResource(GeoRenderState state) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/oreslime.geo.json");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState state) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/oreslime.png");
    }

    @Override
    public Identifier getAnimationResource(OreSlime animatable) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/model.animation.json");
    }
}
