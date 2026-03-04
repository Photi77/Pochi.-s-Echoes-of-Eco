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
import net.pochi.pochimod.entity.custom.Ratel;

public class RatelModel<T extends Ratel> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	//public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "RatelModel"), "main");
	private final ModelPart ginatotter;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart leftlegfront;
	private final ModelPart leftregfront;
	private final ModelPart leftregrear;
	private final ModelPart rightlegrear;
	private final ModelPart body;
	private final ModelPart tail;

	public RatelModel(ModelPart root) {
		this.ginatotter = root.getChild("ginatotter");
		this.neck = this.ginatotter.getChild("neck");
		this.head = this.neck.getChild("head");
		this.leftlegfront = this.ginatotter.getChild("leftlegfront");
		this.leftregfront = this.ginatotter.getChild("leftregfront");
		this.leftregrear = this.ginatotter.getChild("leftregrear");
		this.rightlegrear = this.ginatotter.getChild("rightlegrear");
		this.body = this.ginatotter.getChild("body");
		this.tail = this.body.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition ginatotter = partdefinition.addOrReplaceChild("ginatotter", CubeListBuilder.create(), PartPose.offset(0.0F, 26.0F, 0.0F));

		PartDefinition neck = ginatotter.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, -12.0F));

		PartDefinition cube_r1 = neck.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(70, 0).addBox(-1.4F, -11.4F, -5.0F, 3.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(40, 51).addBox(-2.35F, -10.6F, -5.0F, 5.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.6F, -2.1F, -0.1309F, 0.0F, 0.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(80, 77).addBox(-2.5F, -5.0F, -3.0F, 5.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(44, 16).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(54, 85).addBox(2.5F, -4.0F, -3.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(88, 8).addBox(-3.5F, -4.0F, -3.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(84, 46).addBox(-2.0F, -5.0F, -5.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(62, 90).addBox(2.0F, -4.0F, -4.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 88).addBox(-2.0F, -4.0F, 0.0F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(66, 90).addBox(-3.0F, -4.0F, -4.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(84, 71).addBox(-1.0F, 0.0F, -7.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(84, 65).addBox(-1.5F, -3.0F, -8.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(42, 39).addBox(-1.5F, -4.0F, -6.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -6.0F));

		PartDefinition leftlegfront = ginatotter.addOrReplaceChild("leftlegfront", CubeListBuilder.create().texOffs(70, 8).addBox(3.0F, 8.0F, -1.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(-0.001F))
				.texOffs(14, 85).addBox(4.0F, 4.0F, 0.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -12.0F, -10.0F));

		PartDefinition cube_r2 = leftlegfront.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(24, 88).addBox(-2.0F, -7.0F, -1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(16, 74).addBox(-2.0F, -6.0F, -2.0F, 2.0F, 6.0F, 5.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(6.0F, 5.0F, 0.4F, 0.1745F, 0.0F, 0.0F));

		PartDefinition leftregfront = ginatotter.addOrReplaceChild("leftregfront", CubeListBuilder.create().texOffs(62, 77).addBox(-7.0F, 8.0F, -1.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(-0.001F))
				.texOffs(74, 85).addBox(-6.0F, 4.0F, 0.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -12.0F, -10.0F));

		PartDefinition cube_r3 = leftregfront.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(88, 27).addBox(0.0F, -7.0F, -1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(48, 74).addBox(0.0F, -6.0F, -2.0F, 2.0F, 6.0F, 5.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-6.0F, 5.0F, 0.4F, 0.1745F, 0.0F, 0.0F));

		PartDefinition leftregrear = ginatotter.addOrReplaceChild("leftregrear", CubeListBuilder.create().texOffs(44, 85).addBox(5.0F, 4.0F, -0.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.001F))
				.texOffs(30, 74).addBox(4.0F, 8.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, -12.0F, 8.0F));

		PartDefinition cube_r4 = leftregrear.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(84, 15).addBox(-2.0F, -7.0F, -2.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(66, 51).addBox(-2.0F, -6.0F, -3.0F, 2.0F, 6.0F, 7.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(7.0F, 5.0F, -0.6F, 0.1745F, 0.0F, 0.0F));

		PartDefinition rightlegrear = ginatotter.addOrReplaceChild("rightlegrear", CubeListBuilder.create().texOffs(84, 85).addBox(-7.0F, 4.0F, -0.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.001F))
				.texOffs(78, 39).addBox(-8.0F, 8.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, -12.0F, 8.0F));

		PartDefinition cube_r5 = rightlegrear.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(84, 21).addBox(0.0F, -7.0F, -2.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(66, 64).addBox(0.0F, -6.0F, -3.0F, 2.0F, 6.0F, 7.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-7.0F, 5.0F, -0.6F, 0.1745F, 0.0F, 0.0F));

		PartDefinition body = ginatotter.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -14.0F, -13.0F, 8.0F, 7.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(22, 56).addBox(-4.0F, -13.0F, -14.0F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 41).addBox(-3.0F, -15.0F, -13.0F, 6.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(42, 21).addBox(-5.0F, -12.0F, -12.0F, 1.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(62, 16).addBox(-5.0F, -11.0F, -13.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 56).addBox(-5.0F, -13.0F, -11.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(22, 63).addBox(4.0F, -13.0F, -11.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(78, 46).addBox(4.0F, -11.0F, -13.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(44, 0).addBox(4.0F, -12.0F, -12.0F, 1.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(0, 67).addBox(5.0F, -14.0F, 3.0F, 1.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(84, 53).addBox(5.0F, -15.0F, 4.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(16, 67).addBox(5.0F, -13.0F, 2.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(34, 88).addBox(5.0F, -13.0F, 10.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(90, 0).addBox(-6.0F, -13.0F, 10.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(84, 59).addBox(-6.0F, -15.0F, 4.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(38, 88).addBox(-6.0F, -13.0F, 2.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(68, 16).addBox(-6.0F, -14.0F, 3.0F, 1.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 21).addBox(-5.0F, -15.0F, 1.0F, 10.0F, 9.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(40, 41).addBox(-5.0F, -16.0F, 2.0F, 10.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(68, 30).addBox(-4.0F, -13.0F, 12.0F, 8.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 81).addBox(-3.0F, -12.0F, 14.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(10, 88).addBox(4.0F, -13.0F, 12.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(88, 31).addBox(-5.0F, -13.0F, 12.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(42, 37).addBox(-4.0F, -14.0F, 12.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, -14.0F, 12.0F));

		PartDefinition cube_r6 = tail.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(30, 81).addBox(0.0F, -1.0F, 7.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 2.0F, 1.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r7 = tail.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(62, 84).addBox(-1.0F, -2.0F, 4.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 2.5F, 1.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r8 = tail.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(44, 63).addBox(-2.0F, -3.0F, -3.0F, 4.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 1.0F, -0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);


	}

	@Override
	public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(p_102622_, p_102623_, p_102621_);
		if(p_102618_.walkAnimation.isMoving()) {
			this.animateWalk(ModAnimationDefinitions.RATEL_WALK, p_102619_, p_102620_, 2f, 2.5f);
		} else {
			this.animate(p_102618_.idleAnimationState, ModAnimationDefinitions.RATEL_IDLE, p_102621_, 1f);
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
		ginatotter.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return ginatotter;
	}
}