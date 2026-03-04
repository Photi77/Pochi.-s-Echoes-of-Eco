package net.pochi.pochimod.item.client;

import net.minecraft.resources.ResourceLocation;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.item.custom.armor.RockPenguinArmor;
import software.bernie.geckolib.model.GeoModel;

public class RockPenguinArmorModel extends GeoModel<RockPenguinArmor> {
    @Override
    public ResourceLocation getModelResource(RockPenguinArmor animatable) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "geo/rock_penguin_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RockPenguinArmor animatable) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/armor/rock_penguin_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RockPenguinArmor animatable) {
        return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "animations/model.animation.json");
    }
}
