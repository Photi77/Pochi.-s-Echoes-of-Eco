package net.pochi.pochimod.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.SparrowEntity;
import software.bernie.geckolib.model.GeoModel;

public class SparrowModel extends GeoModel<SparrowEntity> {
    @Override
    public ResourceLocation getModelResource(SparrowEntity object) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/sparrow.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SparrowEntity object) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/sparrow_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SparrowEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/sparrow.animation.json");
    }
}
