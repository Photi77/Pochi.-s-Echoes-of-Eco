package net.pochi.pochimod.item.client;

import net.pochi.pochimod.item.custom.armor.RockPenguinArmor;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class RockPenguinArmorRenderer extends GeoArmorRenderer<RockPenguinArmor> {

    public RockPenguinArmorRenderer() {
        super(new RockPenguinArmorModel());
    }
}
