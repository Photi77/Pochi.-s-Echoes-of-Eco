package net.pochi.pochimod.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.Cicada;
import software.bernie.geckolib.model.GeoModel;

public class CicadaModel extends GeoModel<Cicada> {
    @Override
    public ResourceLocation getModelResource(Cicada object) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/cicada.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Cicada object) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/cicada_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Cicada animatable) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/cicada.animation.json");
    }
}
