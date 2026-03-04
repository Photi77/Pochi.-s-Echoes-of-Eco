package net.pochi.pochimod.item.client;

import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.item.custom.armor.PerissoArmor;
import software.bernie.geckolib.model.GeoModel;

public class PerissoArmorModel extends GeoModel<PerissoArmor> {
    @Override
    public ResourceLocation getModelResource(PerissoArmor animatable) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/periso_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PerissoArmor animatable) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/armor/periso_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PerissoArmor animatable) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/model.animation.json");
    }
}
