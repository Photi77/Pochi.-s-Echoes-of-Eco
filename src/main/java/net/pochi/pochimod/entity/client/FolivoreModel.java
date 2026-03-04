package net.pochi.pochimod.entity.client;// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.Folivore;

public class FolivoreModel<T extends Folivore> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	//public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "FolivoreModel"), "main");
	private final ModelPart peacock;
	private final ModelPart body;
	private final ModelPart leftleg;
	private final ModelPart rightleg;
	private final ModelPart leftwing;
	private final ModelPart rightwing;
	private final ModelPart tail;
	private final ModelPart tail1;
	private final ModelPart tail2;
	private final ModelPart tail3;
	private final ModelPart tail4;
	private final ModelPart tail5;
	private final ModelPart tail6;
	private final ModelPart tail7;
	private final ModelPart tail8;
	private final ModelPart tail9;
	private final ModelPart head;
	private final ModelPart neck;
	private final ModelPart head1;

	public FolivoreModel(ModelPart root) {
		this.peacock = root.getChild("peacock");
		this.body = this.peacock.getChild("body");
		this.leftleg = this.body.getChild("leftleg");
		this.rightleg = this.body.getChild("rightleg");
		this.leftwing = this.body.getChild("leftwing");
		this.rightwing = this.body.getChild("rightwing");
		this.tail = this.body.getChild("tail");
		this.tail1 = this.tail.getChild("tail1");
		this.tail2 = this.tail.getChild("tail2");
		this.tail3 = this.tail.getChild("tail3");
		this.tail4 = this.tail.getChild("tail4");
		this.tail5 = this.tail.getChild("tail5");
		this.tail6 = this.tail.getChild("tail6");
		this.tail7 = this.tail.getChild("tail7");
		this.tail8 = this.tail.getChild("tail8");
		this.tail9 = this.tail.getChild("tail9");
		this.head = this.peacock.getChild("head");
		this.neck = this.head.getChild("neck");
		this.head1 = this.head.getChild("head1");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition peacock = partdefinition.addOrReplaceChild("peacock", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = peacock.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 101).addBox(-3.0F, -5.0F, -5.0F, 6.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(106, 72).addBox(-3.0F, -12.0F, -4.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 90).addBox(-4.0F, -11.0F, -5.0F, 8.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(68, 90).addBox(4.0F, -10.0F, -4.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(110, 0).addBox(-5.0F, -10.0F, -4.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(26, 98).addBox(-3.0F, -10.0F, -9.0F, 6.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(106, 100).addBox(-3.0F, -10.0F, -11.0F, 6.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 109).addBox(-2.0F, -9.0F, -12.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(16, 112).addBox(-4.0F, -9.0F, -9.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(62, 113).addBox(4.0F, -9.0F, -7.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(70, 113).addBox(-5.0F, -9.0F, -7.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(112, 35).addBox(3.0F, -9.0F, -9.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(16, 107).addBox(-2.0F, -11.0F, -9.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(110, 9).addBox(-2.0F, -12.0F, -7.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(106, 107).addBox(-2.0F, -5.0F, -9.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(46, 100).addBox(-3.0F, -10.0F, 0.0F, 6.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(48, 90).addBox(-2.0F, -10.0F, 4.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(26, 90).addBox(-2.0F, -11.0F, 0.0F, 4.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(32, 109).addBox(-2.0F, -5.0F, 0.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(112, 49).addBox(-4.0F, -9.0F, 0.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(8, 114).addBox(4.0F, -9.0F, 0.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(112, 42).addBox(3.0F, -9.0F, 0.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(112, 56).addBox(2.0F, -9.0F, 4.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(112, 63).addBox(-3.0F, -9.0F, 4.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 114).addBox(-5.0F, -9.0F, 0.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(110, 13).addBox(-2.0F, -12.0F, 0.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftleg = body.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(62, 109).addBox(1.0F, 5.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(110, 23).addBox(1.0F, 7.0F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, -3.0F));

		PartDefinition cube_r1 = leftleg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(116, 116).addBox(0.0F, -4.0F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 11.0F, 0.0F, 0.0F, -0.6109F, 0.0F));

		PartDefinition cube_r2 = leftleg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(118, 77).addBox(0.0F, -4.0F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.134F, 11.0F, 0.5F, 0.0F, 0.5236F, 0.0F));

		PartDefinition cube_r3 = leftleg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(22, 101).addBox(0.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 5.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition rightleg = body.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(90, 119).addBox(-2.0F, 5.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(110, 29).addBox(-2.0F, 7.0F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, -3.0F));

		PartDefinition cube_r4 = rightleg.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(118, 85).addBox(-1.0F, -4.0F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 11.0F, 0.0F, 0.0F, 0.6109F, 0.0F));

		PartDefinition cube_r5 = rightleg.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(118, 81).addBox(-1.0F, -4.0F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.134F, 11.0F, 0.5F, 0.0F, -0.5236F, 0.0F));

		PartDefinition cube_r6 = rightleg.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(86, 119).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 5.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition leftwing = body.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(102, 118).addBox(-0.9F, 2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -14.0F, -7.0F));

		PartDefinition cube_r7 = leftwing.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(106, 77).addBox(1.0F, -3.0F, 13.0F, 1.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(106, 90).addBox(1.0F, -3.0F, 18.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(80, 72).addBox(1.0F, 2.0F, 1.0F, 1.0F, 5.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(80, 38).addBox(1.0F, -5.0F, 1.0F, 1.0F, 2.0F, 15.0F, new CubeDeformation(0.0F))
				.texOffs(80, 0).addBox(1.0F, -3.0F, -1.0F, 1.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, 5.0F, -3.1F, 0.0F, 0.0F, -0.6109F));

		PartDefinition rightwing = body.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(16, 119).addBox(-1.1F, 2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -14.0F, -7.0F));

		PartDefinition cube_r8 = rightwing.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(80, 106).addBox(-2.0F, -3.0F, 13.0F, 1.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(92, 106).addBox(-2.0F, -3.0F, 18.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(80, 89).addBox(-2.0F, 2.0F, 1.0F, 1.0F, 5.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(80, 55).addBox(-2.0F, -5.0F, 1.0F, 1.0F, 2.0F, 15.0F, new CubeDeformation(0.0F))
				.texOffs(80, 19).addBox(-2.0F, -3.0F, -1.0F, 1.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1F, 5.0F, -3.1F, 0.0F, 0.0F, 0.6109F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, -14.0F, 5.0F));

		PartDefinition tail_1 = tail.addOrReplaceChild("tail1", CubeListBuilder.create(), PartPose.offset(2.0F, -0.8F, 0.0F));

		PartDefinition tail_2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create(), PartPose.offset(-1.7F, -0.1F, 0.0F));

		PartDefinition cube_r9 = tail_2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(40, 36).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 18.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 3.0F, -6.0F, -0.0774F, 0.0403F, 0.4784F));

		PartDefinition cube_r10 = tail_2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 18.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(2.7F, 2.9F, -6.0F, -0.0774F, 0.0403F, 0.4784F));

		PartDefinition tail_3 = tail.addOrReplaceChild("tail3", CubeListBuilder.create(), PartPose.offset(-4.1F, 0.3F, 0.0F));

		PartDefinition cube_r11 = tail_3.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 18.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(4.7F, 3.1F, -6.0F, -0.0715F, -0.05F, -0.6091F));

		PartDefinition cube_r12 = tail_3.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(40, 18).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 18.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(2.7F, 2.9F, -6.0F, -0.0715F, -0.05F, -0.6091F));

		PartDefinition tail_4 = tail.addOrReplaceChild("tail4", CubeListBuilder.create(), PartPose.offset(-2.7F, -0.9F, 0.0F));

		PartDefinition tail_5 = tail.addOrReplaceChild("tail5", CubeListBuilder.create(), PartPose.offset(-1.4F, 0.2F, 0.0F));

		PartDefinition cube_r13 = tail_5.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(40, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 18.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 3.0F, -6.0F, -0.1214F, 0.0216F, -0.6084F));

		PartDefinition cube_r14 = tail_5.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(40, 72).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 18.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(2.0F, 3.2F, -6.0F, -0.1214F, 0.0216F, -0.6084F));

		PartDefinition tail_6 = tail.addOrReplaceChild("tail6", CubeListBuilder.create(), PartPose.offset(3.0F, 0.0F, 0.0F));

		PartDefinition cube_r15 = tail_6.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 36).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 18.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-4.7F, 2.9F, -6.0F, -0.037F, 0.1177F, 0.4816F));

		PartDefinition cube_r16 = tail_6.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 72).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 18.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-2.0F, 2.8F, -6.0F, -0.037F, 0.1177F, 0.4816F));

		PartDefinition tail_7 = tail.addOrReplaceChild("tail7", CubeListBuilder.create(), PartPose.offset(-2.7F, -0.9F, 0.0F));

		PartDefinition tail_8 = tail.addOrReplaceChild("tail8", CubeListBuilder.create(), PartPose.offset(-1.4F, 0.2F, 0.0F));

		PartDefinition cube_r17 = tail_8.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(40, 54).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 18.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(2.0F, 3.2F, -6.0F, -0.0213F, -0.1215F, -0.6134F));

		PartDefinition tail_9 = tail.addOrReplaceChild("tail9", CubeListBuilder.create(), PartPose.offset(3.0F, 0.0F, 0.0F));

		PartDefinition cube_r18 = tail_9.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 54).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 18.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-2.0F, 2.8F, -6.0F, -0.1176F, -0.0372F, 0.4783F));

		PartDefinition head = peacock.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, -11.0F));

		PartDefinition neck = head.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 107).addBox(-2.0F, -1.0F, -6.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(26, 112).addBox(-1.0F, 5.0F, -6.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(32, 107).addBox(-2.0F, 5.0F, -5.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(110, 17).addBox(-1.0F, -3.0F, -6.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(116, 112).addBox(-1.0F, -5.0F, -4.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(106, 112).addBox(-1.0F, 2.0F, -5.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(26, 114).addBox(-3.0F, -1.0F, -5.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(34, 114).addBox(2.0F, -1.0F, -5.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(-0.001F))
				.texOffs(56, 119).addBox(1.0F, 2.0F, -4.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(62, 119).addBox(-2.0F, 2.0F, -4.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(110, 118).addBox(-1.0F, -1.0F, -7.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(50, 119).addBox(-1.0F, 2.0F, -6.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(42, 115).addBox(-2.0F, 2.0F, -2.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(68, 119).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 3.0F));

		PartDefinition head1 = head.addOrReplaceChild("head1", CubeListBuilder.create().texOffs(92, 116).addBox(-3.0F, 7.0F, -1.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(80, 119).addBox(-2.0F, 7.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(42, 119).addBox(-2.5F, 7.0F, -2.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(74, 119).addBox(-1.5F, 7.9F, -4.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(52, 115).addBox(-2.0F, 6.0F, -2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(66, 100).addBox(-1.0F, 0.0F, -4.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.001F)), PartPose.offset(1.0F, -15.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);

	}

	@Override
	public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(p_102622_, p_102623_, p_102621_);
		if(p_102618_.onGround()) {
			this.animateWalk(ModAnimationDefinitions.FOLOVORE_WALK, p_102619_, p_102620_, 2f, 2.5f);
		} else {
			this.animateWalk(ModAnimationDefinitions.FOLOVORE_FLY, p_102619_, p_102620_, 2f, 2.5f);
		}

		this.animate(p_102618_.idleAnimationState, ModAnimationDefinitions.FOLOVORE_IDLE, p_102621_, 1f);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		peacock.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return peacock;
	}
}