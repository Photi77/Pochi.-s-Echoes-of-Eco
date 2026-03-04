package net.pochi.pochimod.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.Sakaban;
import software.bernie.geckolib.model.GeoModel;

public class SakabanModel extends GeoModel<Sakaban> {
    @Override
    public ResourceLocation getModelResource(Sakaban object) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/sakaban.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Sakaban object) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/sakaban_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Sakaban animatable) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/sakaban.animation.json");
    }
}
