package net.pochi.pochimod.entity.client;// Made with Blockbench 5.0.4
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
import net.pochi.pochimod.entity.custom.FlowerMantis;

public class FlowerMantisModel<T extends FlowerMantis> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	//public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "FlowerMantisModel"), "main");
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart ear_l;
	private final ModelPart ear_r;
	private final ModelPart hand_r;
	private final ModelPart hand_l;
	private final ModelPart leg_r;
	private final ModelPart leg_l;
	private final ModelPart tail;

	public FlowerMantisModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.neck = this.root.getChild("neck");
		this.head = this.neck.getChild("head");
		this.ear_l = this.neck.getChild("ear_l");
		this.ear_r = this.neck.getChild("ear_r");
		this.hand_r = this.root.getChild("hand_r");
		this.hand_l = this.root.getChild("hand_l");
		this.leg_r = this.root.getChild("leg_r");
		this.leg_l = this.root.getChild("leg_l");
		this.tail = this.root.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(14, 0).addBox(-1.0F, -5.0F, -3.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck = root.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(20, 16).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(20, 23).addBox(-1.0F, -3.0F, -2.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -3.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, -1.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(26, 14).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(22, 14).addBox(1.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, -1.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 23).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.829F, 0.0F, 0.0F));

		PartDefinition ear_l = neck.addOrReplaceChild("ear_l", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 3.0F));

		PartDefinition cube_r3 = ear_l.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(12, 25).addBox(0.0F, -5.0F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -5.0F, -4.0F, 0.3175F, -0.2627F, 0.0936F));

		PartDefinition cube_r4 = ear_l.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 0).addBox(-0.4716F, -0.9696F, -8.2367F, 0.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -11.0F, -4.0F, 0.9284F, -0.2627F, 0.0936F));

		PartDefinition ear_r = neck.addOrReplaceChild("ear_r", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 3.0F));

		PartDefinition cube_r5 = ear_r.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 8).addBox(0.4716F, -0.9696F, -8.2367F, 0.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -11.0F, -4.0F, 0.9284F, 0.2627F, -0.0936F));

		PartDefinition cube_r6 = ear_r.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(8, 25).addBox(-1.0F, -5.0F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -5.0F, -4.0F, 0.3175F, 0.2627F, -0.0936F));

		PartDefinition hand_r = root.addOrReplaceChild("hand_r", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -2.0F));

		PartDefinition cube_r7 = hand_r.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 26).addBox(-2.0F, 0.0F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -5.0F, 0.0F, -0.0724F, -0.3305F, 0.1803F));

		PartDefinition cube_r8 = hand_r.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(22, 6).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -4.0F, 0.0F, -0.1961F, -0.2772F, 0.5884F));

		PartDefinition hand_l = root.addOrReplaceChild("hand_l", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -2.0F));

		PartDefinition cube_r9 = hand_l.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(22, 12).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -4.0F, 0.0F, -0.1961F, 0.2772F, -0.5884F));

		PartDefinition cube_r10 = hand_l.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(4, 26).addBox(1.0F, 0.0F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -5.0F, 0.0F, -0.0724F, 0.3305F, -0.1803F));

		PartDefinition leg_r = root.addOrReplaceChild("leg_r", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r11 = leg_r.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(26, 0).addBox(-2.0F, 0.0F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -5.0F, 1.0F, 0.0776F, 0.4857F, 0.193F));

		PartDefinition cube_r12 = leg_r.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(22, 10).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -4.0F, 1.0F, 0.2679F, 0.417F, 0.6235F));

		PartDefinition leg_l = root.addOrReplaceChild("leg_l", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r13 = leg_l.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(22, 8).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -4.0F, 1.0F, 0.2679F, -0.417F, -0.6235F));

		PartDefinition cube_r14 = leg_l.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(16, 25).addBox(1.0F, 0.0F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -5.0F, 1.0F, 0.0776F, -0.4857F, -0.193F));

		PartDefinition tail = root.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(14, 6).addBox(-1.0F, -7.0F, 0.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(8, 16).addBox(1.0F, -6.0F, 0.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 16).addBox(-1.0F, -5.0F, 2.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(14, 16).addBox(-2.0F, -6.0F, 0.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);

	}

	@Override
	public void setupAnim(FlowerMantis entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		if(entity.walkAnimation.isMoving()) {
			this.animateWalk(ModAnimationDefinitions.FLOWER_MANTIS, limbSwing, limbSwingAmount, 2f, 2.5f);
		} else {
			//this.animate(entity.idleAnimationState, ModAnimationDefinitions.ANT_IDLE, ageInTicks, 1f);
		}
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}