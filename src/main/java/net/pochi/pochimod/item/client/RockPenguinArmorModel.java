package net.pochi.pochimod.item.client;

import net.minecraft.resources.Identifier;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.item.custom.armor.RockPenguinArmor;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class RockPenguinArmorModel extends GeoModel<RockPenguinArmor> {
    @Override
    public Identifier getModelResource(GeoRenderState state) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/rock_penguin_armor.geo.json");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState state) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/armor/rock_penguin_armor.png");
    }

    @Override
    public Identifier getAnimationResource(RockPenguinArmor animatable) {
        return Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/model.animation.json");
    }
}
