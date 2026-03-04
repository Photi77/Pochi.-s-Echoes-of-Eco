package net.pochi.pochimod.item.client;

import net.pochi.pochimod.item.custom.armor.PerissoArmor;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class PerissoArmorRenderer extends GeoArmorRenderer<PerissoArmor> {

    public PerissoArmorRenderer() {
        super(new PerissoArmorModel());
    }
}
