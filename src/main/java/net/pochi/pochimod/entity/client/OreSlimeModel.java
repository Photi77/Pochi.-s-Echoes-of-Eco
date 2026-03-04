package net.pochi.pochimod.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.projectile.OreSlime;
import software.bernie.geckolib.model.GeoModel;

public class OreSlimeModel extends GeoModel<OreSlime> {
    @Override
    public ResourceLocation getModelResource(OreSlime object) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/oreslime.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OreSlime object) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/oreslime.png");
    }

    @Override
    public ResourceLocation getAnimationResource(OreSlime animatable) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/model.animation.json");
    }
}
