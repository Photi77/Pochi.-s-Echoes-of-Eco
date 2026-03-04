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
import net.pochi.pochimod.entity.custom.Mole;

public class MoleModel<T extends Mole> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	//public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "MoleModel"), "main");
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart ear_l;
	private final ModelPart ear_r;
	private final ModelPart tail;
	private final ModelPart leg_l;
	private final ModelPart leg_r;
	private final ModelPart hand_l;
	private final ModelPart hand_r;

	public MoleModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.neck = this.root.getChild("neck");
		this.head = this.neck.getChild("head");
		this.ear_l = this.head.getChild("ear_l");
		this.ear_r = this.head.getChild("ear_r");
		this.tail = this.root.getChild("tail");
		this.leg_l = this.root.getChild("leg_l");
		this.leg_r = this.root.getChild("leg_r");
		this.hand_l = this.root.getChild("hand_l");
		this.hand_r = this.root.getChild("hand_r");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 22).addBox(-4.0F, -4.0F, -5.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(28, 13).addBox(-3.0F, -5.0F, -5.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-5.0F, -5.0F, -1.0F, 10.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(-3.0F, -6.0F, -1.0F, 6.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck = root.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 22).addBox(-3.0F, -4.0F, -1.0F, 6.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(-2.5F, -4.0F, -3.0F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 36).addBox(-1.5F, -2.45F, -5.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(28, 44).addBox(-1.0F, -1.45F, -7.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -7.0F));

		PartDefinition ear_l = head.addOrReplaceChild("ear_l", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 7.0F));

		PartDefinition ear_r = head.addOrReplaceChild("ear_r", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 7.0F));

		PartDefinition tail = root.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(28, 34).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(20, 44).addBox(-0.5F, -1.0F, 3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 8.0F));

		PartDefinition leg_l = root.addOrReplaceChild("leg_l", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition cube_r1 = leg_l.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(40, 39).addBox(3.0F, -1.0F, -7.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 4.0F, 0.0F, -0.2182F, 0.0F));

		PartDefinition cube_r2 = leg_l.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 41).addBox(1.0F, -1.0F, -7.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 0.0F, 4.0F, 0.0F, -0.2182F, 0.0F));

		PartDefinition cube_r3 = leg_l.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(42, 18).addBox(-1.0F, -1.0F, -7.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(14, 30).addBox(-1.0F, -1.0F, -3.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 4.0F, 0.0F, -0.2182F, 0.0F));

		PartDefinition leg_r = root.addOrReplaceChild("leg_r", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition cube_r4 = leg_r.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(30, 39).addBox(-2.0F, -1.0F, -7.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 4.0F, 0.0F, 0.2182F, 0.0F));

		PartDefinition cube_r5 = leg_r.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(38, 34).addBox(0.0F, -1.0F, -7.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(28, 18).addBox(-3.0F, -1.0F, -3.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 4.0F, 0.0F, 0.2182F, 0.0F));

		PartDefinition cube_r6 = leg_r.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(38, 5).addBox(-4.0F, -1.0F, -7.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 4.0F, 0.0F, 0.2182F, 0.0F));

		PartDefinition hand_l = root.addOrReplaceChild("hand_l", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -4.0F));

		PartDefinition cube_r7 = hand_l.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(20, 39).addBox(3.0F, -1.0F, -7.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 3.0F, 0.0F, -0.2182F, 0.0F));

		PartDefinition cube_r8 = hand_l.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(10, 38).addBox(1.0F, -1.0F, -7.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 0.0F, 3.0F, 0.0F, -0.2182F, 0.0F));

		PartDefinition cube_r9 = hand_l.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(38, 0).addBox(-1.0F, -1.0F, -7.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(28, 30).addBox(-1.0F, -1.0F, -3.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 3.0F, 0.0F, -0.2182F, 0.0F));

		PartDefinition hand_r = root.addOrReplaceChild("hand_r", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -4.0F));

		PartDefinition cube_r10 = hand_r.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(10, 43).addBox(0.0F, -1.0F, -7.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(14, 34).addBox(-3.0F, -1.0F, -3.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 3.0F, 0.0F, 0.2182F, 0.0F));

		PartDefinition cube_r11 = hand_r.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(42, 28).addBox(-2.0F, -1.0F, -7.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 3.0F, 0.0F, 0.2182F, 0.0F));

		PartDefinition cube_r12 = hand_r.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(42, 23).addBox(-4.0F, -1.0F, -7.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 3.0F, 0.0F, 0.2182F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Mole entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if(entity.walkAnimation.isMoving()) {
			this.animateWalk(ModAnimationDefinitions.MOLE_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
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