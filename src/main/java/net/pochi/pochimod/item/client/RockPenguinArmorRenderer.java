package net.pochi.pochimod.item.client;

import net.pochi.pochimod.item.custom.armor.RockPenguinArmor;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class RockPenguinArmorRenderer extends GeoArmorRenderer<RockPenguinArmor, GeoHumanoidRenderState> {

    public RockPenguinArmorRenderer() {
        super(new RockPenguinArmorModel());
    }
}
