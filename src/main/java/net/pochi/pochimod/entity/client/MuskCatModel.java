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
import net.pochi.pochimod.entity.custom.MuskCat;

public class MuskCatModel<T extends MuskCat> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	//public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "MuskCatModel"), "main");
	private final ModelPart ginatotter;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart leftlegfront;
	private final ModelPart righlegfront;
	private final ModelPart leftregrear;
	private final ModelPart rightregrear;
	private final ModelPart body;
	private final ModelPart tail;

	public MuskCatModel(ModelPart root) {
		this.ginatotter = root.getChild("ginatotter");
		this.neck = this.ginatotter.getChild("neck");
		this.head = this.neck.getChild("head");
		this.leftlegfront = this.ginatotter.getChild("leftlegfront");
		this.righlegfront = this.ginatotter.getChild("righlegfront");
		this.leftregrear = this.ginatotter.getChild("leftregrear");
		this.rightregrear = this.ginatotter.getChild("rightregrear");
		this.body = this.ginatotter.getChild("body");
		this.tail = this.body.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition ginatotter = partdefinition.addOrReplaceChild("ginatotter", CubeListBuilder.create(), PartPose.offset(0.0F, 26.0F, 0.0F));

		PartDefinition neck = ginatotter.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.0F, -11.0F, -7.0F));

		PartDefinition cube_r1 = neck.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(42, 31).addBox(-2.0F, -2.0F, -8.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.75F, 0.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r2 = neck.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(56, 40).addBox(-2.0F, -3.0F, -5.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(58, 0).addBox(-2.5F, -3.0F, -3.0F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(72, 68).addBox(-0.5F, 0.0F, -8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(60, 62).addBox(-3.0F, -2.0F, -1.0F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(72, 66).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 64).addBox(-2.0F, -2.0F, -5.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(72, 13).addBox(-1.0F, -1.0F, -7.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -6.0F));

		PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(54, 68).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -3.75F, -2.0F, -0.2739F, 0.2947F, -0.0814F));

		PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(66, 11).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -3.75F, -2.0F, -0.3604F, -0.2457F, 0.0914F));

		PartDefinition leftlegfront = ginatotter.addOrReplaceChild("leftlegfront", CubeListBuilder.create().texOffs(54, 71).addBox(4.0F, 9.0F, -0.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F))
				.texOffs(36, 68).addBox(4.0F, 4.0F, 0.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offset(-1.0F, -12.0F, -7.0F));

		PartDefinition cube_r5 = leftlegfront.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(58, 11).addBox(-2.0F, -7.0F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 62).addBox(-2.0F, -6.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(6.0F, 5.0F, 0.4F, 0.1745F, 0.0F, 0.0F));

		PartDefinition righlegfront = ginatotter.addOrReplaceChild("righlegfront", CubeListBuilder.create().texOffs(72, 0).addBox(-6.0F, 9.0F, -1.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F))
				.texOffs(10, 71).addBox(-6.0F, 4.0F, -0.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offset(1.0F, -12.0F, -6.0F));

		PartDefinition cube_r6 = righlegfront.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(72, 27).addBox(0.0F, -7.0F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(26, 62).addBox(0.0F, -6.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-6.0F, 5.0F, -0.6F, 0.1745F, 0.0F, 0.0F));

		PartDefinition leftregrear = ginatotter.addOrReplaceChild("leftregrear", CubeListBuilder.create().texOffs(28, 71).addBox(3.0F, 6.0F, 0.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.001F))
				.texOffs(64, 71).addBox(3.0F, 9.0F, -0.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, -12.0F, 8.0F));

		PartDefinition cube_r7 = leftregrear.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(44, 68).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.001F))
				.texOffs(64, 49).addBox(-2.0F, -7.0F, -1.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(32, 50).addBox(-2.0F, -6.0F, -2.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(5.0F, 5.0F, -0.6F, 0.1745F, 0.0F, 0.0F));

		PartDefinition rightregrear = ginatotter.addOrReplaceChild("rightregrear", CubeListBuilder.create().texOffs(72, 8).addBox(-5.0F, 6.0F, 0.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.001F))
				.texOffs(72, 4).addBox(-5.0F, 9.0F, -0.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, -12.0F, 8.0F));

		PartDefinition cube_r8 = rightregrear.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(18, 71).addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.001F))
				.texOffs(60, 66).addBox(0.0F, -7.0F, -1.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(48, 50).addBox(0.0F, -6.0F, -2.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-5.0F, 5.0F, -0.6F, 0.1745F, 0.0F, 0.0F));

		PartDefinition body = ginatotter.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 6.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(58, 6).addBox(-3.0F, -1.0F, -1.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(30, 22).addBox(-2.0F, -3.0F, 1.0F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(20, 39).addBox(-4.0F, 0.0F, 0.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 56).addBox(-4.0F, -1.0F, 0.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(56, 14).addBox(3.0F, -1.0F, 0.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(38, 39).addBox(3.0F, 0.0F, 0.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 44).addBox(3.0F, 0.0F, 11.0F, 1.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(36, 62).addBox(3.0F, -1.0F, 12.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(16, 44).addBox(3.0F, 0.0F, 10.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(72, 17).addBox(2.0F, 1.0F, 18.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(72, 22).addBox(-4.0F, 1.0F, 18.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(48, 62).addBox(-4.0F, -1.0F, 12.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(12, 64).addBox(-4.0F, 0.0F, 10.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(16, 50).addBox(-4.0F, 0.0F, 11.0F, 1.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.0F, -2.0F, 9.0F, 6.0F, 7.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(30, 14).addBox(-3.0F, 5.0F, 10.0F, 6.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 31).addBox(-2.0F, -2.0F, 1.0F, 4.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(20, 31).addBox(-2.0F, -3.0F, 10.0F, 4.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(64, 54).addBox(-2.0F, -1.0F, 18.0F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 69).addBox(-2.0F, 0.0F, 19.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, -8.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 18.0F));

		PartDefinition cube_r9 = tail.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(56, 31).addBox(0.0F, -1.0F, 13.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, 1.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r10 = tail.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(54, 22).addBox(-1.0F, -2.0F, 7.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 1.5F, 1.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r11 = tail.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(30, 0).addBox(-2.0F, -3.0F, -3.0F, 4.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 1.0F, -0.3927F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);


	}

	@Override
	public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(p_102622_, p_102623_, p_102621_);
		if(p_102618_.walkAnimation.isMoving()) {
			this.animateWalk(ModAnimationDefinitions.MUSK_WALK, p_102619_, p_102620_, 2f, 2.5f);
		} else {
			this.animate(p_102618_.idleAnimationState, ModAnimationDefinitions.MUSK_IDLE, p_102621_, 1f);
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