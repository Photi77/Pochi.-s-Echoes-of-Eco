package net.pochi.pochimod.entity.client;// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.animal.polarbear.PolarBearModel;
import net.minecraft.client.model.geom.ModelPart;

public class AsianBearModel extends PolarBearModel {

	public AsianBearModel(ModelPart p_170829_) {
		super(p_170829_);
	}

	public static net.minecraft.client.model.geom.builders.LayerDefinition createBodyLayer() {
		return PolarBearModel.createBodyLayer(false);
	}
}