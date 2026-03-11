package net.pochi.pochimod.item.client;

import net.minecraft.resources.Identifier;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.item.custom.armor.PerissoArmor;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class PerissoArmorModel extends GeoModel<PerissoArmor> {
    @Override
    public Identifier getModelResource(GeoRenderState state) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/periso_armor.geo.json");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState state) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/armor/periso_armor.png");
    }

    @Override
    public Identifier getAnimationResource(PerissoArmor animatable) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/model.animation.json");
    }
}
