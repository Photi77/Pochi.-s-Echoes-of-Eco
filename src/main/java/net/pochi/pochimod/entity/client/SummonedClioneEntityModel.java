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
import net.pochi.pochimod.entity.projectile.SummonedClioneEntity;

public class SummonedClioneEntityModel<T extends SummonedClioneEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	//public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "ClioneModel"), "main");
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

	public SummonedClioneEntityModel(ModelPart root) {
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

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -1.0F, -2.0F, 6.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(32, 17).addBox(-1.0F, 12.0F, -1.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(30, 27).addBox(-2.0F, 0.0F, 2.0F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -15.0F, 0.0F));

		PartDefinition neck = root.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 12).addBox(-4.0F, -21.0F, -2.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(20, 0).addBox(-3.0F, -22.0F, -2.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(32, 23).addBox(2.0F, -24.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(10, 32).addBox(-2.0F, -17.0F, -1.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(24, 12).addBox(-3.0F, -21.0F, -3.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 27).addBox(-3.0F, -21.0F, 2.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 32).addBox(-3.0F, -24.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ear_l = head.addOrReplaceChild("ear_l", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ear_r = head.addOrReplaceChild("ear_r", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hand_r = root.addOrReplaceChild("hand_r", CubeListBuilder.create().texOffs(20, 5).addBox(-8.0F, -3.0F, -1.0F, 8.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -13.0F, 0.0F));

		PartDefinition hand_l = root.addOrReplaceChild("hand_l", CubeListBuilder.create().texOffs(16, 20).addBox(0.0F, -3.0F, -1.0F, 8.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -13.0F, 0.0F));

		PartDefinition leg_r = root.addOrReplaceChild("leg_r", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg_l = root.addOrReplaceChild("leg_l", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = root.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(SummonedClioneEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if(entity.walkAnimation.isMoving()) {
			this.animateWalk(ModAnimationDefinitions.CLIONE_SWIM, limbSwing, limbSwingAmount, 2f, 2.5f);
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