package net.pochi.pochimod.entity.client;// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.Cassowary;

public class CassowaryModel<T extends Cassowary> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	//public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "CassowaryModel"), "main");
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

	public CassowaryModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.neck = this.root.getChild("neck");
		this.head = this.neck.getChild("head");
		this.ear_l = this.head.getChild("ear_l");
		this.ear_r = this.head.getChild("ear_r");
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

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -11.0F, -2.0F, 6.0F, 11.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(24, 0).addBox(-3.0F, -10.0F, 4.0F, 6.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(28, 11).addBox(-3.0F, -10.0F, -4.0F, 6.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(14, 34).addBox(-3.0F, -9.0F, 6.0F, 6.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(30, 34).addBox(-3.0F, -8.0F, -6.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(40, 0).addBox(-3.0F, -6.0F, -8.0F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(42, 22).addBox(-3.0F, -7.0F, 8.0F, 6.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(42, 55).addBox(3.0F, -5.0F, 8.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(42, 48).addBox(3.0F, -7.0F, 6.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(3.0F, -9.0F, -2.0F, 1.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(22, 43).addBox(3.0F, -8.0F, 4.0F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 43).addBox(3.0F, -8.0F, -4.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(50, 39).addBox(3.0F, -6.0F, -6.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(50, 18).addBox(3.0F, -4.0F, -8.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(54, 33).addBox(4.0F, -6.0F, 6.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 31).addBox(4.0F, -8.0F, -2.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(12, 53).addBox(4.0F, -7.0F, 4.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(6, 52).addBox(4.0F, -7.0F, -4.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(32, 53).addBox(-5.0F, -6.0F, 6.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(28, 22).addBox(-5.0F, -8.0F, -2.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(20, 51).addBox(-5.0F, -7.0F, 4.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 52).addBox(-5.0F, -7.0F, -4.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(54, 6).addBox(-5.0F, -5.0F, -6.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(54, 45).addBox(-4.0F, -4.0F, -8.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(26, 53).addBox(-4.0F, -6.0F, -6.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(6, 43).addBox(-4.0F, -8.0F, -4.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(44, 11).addBox(-4.0F, -8.0F, 4.0F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(14, 17).addBox(-4.0F, -9.0F, -2.0F, 1.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(48, 48).addBox(-4.0F, -7.0F, 6.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(46, 55).addBox(-4.0F, -5.0F, 8.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 0.0F));

		PartDefinition neck = root.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(30, 42).addBox(-1.0F, -8.0F, -2.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(38, 48).addBox(-0.5F, -4.0F, -3.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.0F, -8.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(38, 42).addBox(-1.5F, -2.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(50, 11).addBox(0.0F, -6.0F, -2.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(12, 43).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, -2.0F));

		PartDefinition ear_l = head.addOrReplaceChild("ear_l", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 10.0F));

		PartDefinition ear_r = head.addOrReplaceChild("ear_r", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 10.0F));

		PartDefinition hand_r = root.addOrReplaceChild("hand_r", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hand_l = root.addOrReplaceChild("hand_l", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg_r = root.addOrReplaceChild("leg_r", CubeListBuilder.create().texOffs(40, 6).addBox(-3.5F, 8.0F, -2.5F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 0.0F));

		PartDefinition cube_r1 = leg_r.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(54, 49).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 4.0F, 0.5F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r2 = leg_r.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(46, 33).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition leg_l = root.addOrReplaceChild("leg_l", CubeListBuilder.create().texOffs(42, 28).addBox(0.5F, 8.0F, -2.5F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 0.0F));

		PartDefinition cube_r3 = leg_l.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(12, 47).addBox(0.0F, 0.0F, 0.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r4 = leg_l.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(24, 11).addBox(0.0F, 0.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 4.0F, 0.5F, -0.1745F, 0.0F, 0.0F));

		PartDefinition tail = root.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Cassowary entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if(entity.walkAnimation.isMoving()) {
			this.animateWalk(ModAnimationDefinitions.CASSWARY_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		} else {
			//this.animate(entity.idleAnimationState, ModAnimationDefinitions.ANT_IDLE, ageInTicks, 1f);
		}
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