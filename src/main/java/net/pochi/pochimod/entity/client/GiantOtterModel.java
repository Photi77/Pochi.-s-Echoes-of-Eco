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
import net.pochi.pochimod.entity.custom.GiantOtter;

public class GiantOtterModel<T extends GiantOtter> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	//public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "GiantOtterModel"), "main");
	private final ModelPart ginatotter;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart leftlegfront;
	private final ModelPart leftregfront;
	private final ModelPart leftregrear;
	private final ModelPart rightlegrear;
	private final ModelPart body;
	private final ModelPart tail;

	public GiantOtterModel(ModelPart root) {
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

		PartDefinition ginatotter = partdefinition.addOrReplaceChild("ginatotter", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition neck = ginatotter.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(54, 71).addBox(-3.0F, -2.5F, -6.0F, 6.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 92).addBox(-2.0F, -3.5F, -6.0F, 4.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(92, 93).addBox(-1.5F, -4.5F, -6.0F, 3.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(92, 14).addBox(-2.0F, 3.5F, -6.0F, 4.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 100).addBox(3.0F, -1.5F, -6.0F, 1.0F, 4.0F, 7.0F, new CubeDeformation(-0.01F))
				.texOffs(92, 101).addBox(-4.0F, -1.5F, -6.0F, 1.0F, 4.0F, 7.0F, new CubeDeformation(-0.01F))
				.texOffs(72, 102).addBox(-2.6F, -2.0F, -9.1F, 5.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(28, 113).addBox(2.2F, -0.8F, -9.1F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(20, 113).addBox(-3.4F, -0.8F, -9.1F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(112, 95).addBox(-1.4F, -2.8F, -9.1F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(106, 113).addBox(-0.9F, -3.8F, -9.1F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(112, 91).addBox(-1.4F, 2.8F, -9.1F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -11.0F, -13.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(102, 78).addBox(-2.5F, -2.0F, -3.0F, 5.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(102, 86).addBox(-2.5F, 3.0F, -3.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(72, 84).addBox(2.5F, -1.0F, -3.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(98, 112).addBox(-3.5F, -1.0F, -3.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(106, 52).addBox(-2.0F, -3.0F, -3.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(40, 112).addBox(-2.0F, -3.0F, -4.0F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(78, 116).addBox(2.0F, -1.0F, -4.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(116, 113).addBox(2.0F, -1.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(38, 48).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(82, 116).addBox(-3.0F, -1.0F, -4.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(86, 112).addBox(-1.0F, 3.0F, -7.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(26, 64).addBox(-2.0F, -2.0F, -6.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(10, 113).addBox(-1.5F, 0.0F, -8.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -10.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(102, 36).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -2.0F, -1.0F, -0.2739F, 0.2947F, -0.0814F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(92, 22).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -2.0F, -1.0F, -0.3604F, -0.2457F, 0.0914F));

		PartDefinition leftlegfront = ginatotter.addOrReplaceChild("leftlegfront", CubeListBuilder.create().texOffs(102, 29).addBox(3.0F, 10.0F, -1.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(-0.001F))
				.texOffs(108, 36).addBox(4.0F, 6.0F, 0.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -12.0F, -12.0F));

		PartDefinition cube_r3 = leftlegfront.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(114, 13).addBox(-2.0F, -7.0F, -1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(28, 107).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(-0.001F))
				.texOffs(40, 85).addBox(-2.0F, -6.0F, -2.0F, 2.0F, 6.0F, 5.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(6.0F, 5.0F, 0.4F, 0.1745F, 0.0F, 0.0F));

		PartDefinition leftregfront = ginatotter.addOrReplaceChild("leftregfront", CubeListBuilder.create().texOffs(102, 64).addBox(-7.0F, 10.0F, -1.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(-0.001F))
				.texOffs(64, 110).addBox(-6.0F, 6.0F, 0.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -12.0F, -12.0F));

		PartDefinition cube_r4 = leftregfront.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(114, 17).addBox(0.0F, -7.0F, -1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(108, 107).addBox(0.0F, 0.0F, -1.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(-0.001F))
				.texOffs(58, 97).addBox(0.0F, -6.0F, -2.0F, 2.0F, 6.0F, 5.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-6.0F, 5.0F, 0.4F, 0.1745F, 0.0F, 0.0F));

		PartDefinition leftregrear = ginatotter.addOrReplaceChild("leftregrear", CubeListBuilder.create().texOffs(54, 108).addBox(5.0F, 6.0F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.001F))
				.texOffs(102, 57).addBox(4.0F, 10.0F, -3.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, -12.0F, 9.0F));

		PartDefinition cube_r5 = leftregrear.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(108, 101).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(-0.001F))
				.texOffs(104, 7).addBox(-2.0F, -7.0F, -2.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(54, 84).addBox(-2.0F, -6.0F, -3.0F, 2.0F, 6.0F, 7.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(7.0F, 5.0F, -1.6F, 0.1745F, 0.0F, 0.0F));

		PartDefinition rightlegrear = ginatotter.addOrReplaceChild("rightlegrear", CubeListBuilder.create().texOffs(0, 111).addBox(-7.0F, 6.0F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.001F))
				.texOffs(102, 71).addBox(-8.0F, 10.0F, -3.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, -12.0F, 9.0F));

		PartDefinition cube_r6 = rightlegrear.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(74, 110).addBox(0.0F, 0.0F, -1.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(-0.001F))
				.texOffs(40, 106).addBox(0.0F, -7.0F, -2.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(22, 85).addBox(0.0F, -6.0F, -3.0F, 2.0F, 6.0F, 7.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-7.0F, 5.0F, -1.6F, 0.1745F, 0.0F, 0.0F));

		PartDefinition body = ginatotter.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 26).addBox(-4.0F, -14.0F, -13.0F, 8.0F, 8.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.0F, -6.0F, -12.0F, 6.0F, 1.0F, 25.0F, new CubeDeformation(0.0F))
				.texOffs(102, 22).addBox(-4.0F, -13.0F, -14.0F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(44, 46).addBox(-3.0F, -15.0F, -13.0F, 6.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(0, 48).addBox(-2.0F, -16.0F, -13.0F, 4.0F, 1.0F, 15.0F, new CubeDeformation(0.0F))
				.texOffs(76, 61).addBox(-2.0F, -17.0F, -6.0F, 4.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(62, 0).addBox(-5.0F, -12.0F, -12.0F, 1.0F, 5.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(38, 55).addBox(-5.0F, -11.0F, -13.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(80, 71).addBox(-5.0F, -13.0F, -11.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 81).addBox(4.0F, -13.0F, -11.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(74, 116).addBox(4.0F, -11.0F, -13.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 64).addBox(4.0F, -12.0F, -12.0F, 1.0F, 5.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(86, 25).addBox(5.0F, -14.0F, 3.0F, 1.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(106, 46).addBox(5.0F, -15.0F, 4.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(16, 100).addBox(5.0F, -13.0F, 2.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 113).addBox(5.0F, -13.0F, 10.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(58, 116).addBox(-6.0F, -13.0F, 10.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(16, 107).addBox(-6.0F, -15.0F, 4.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(54, 116).addBox(-6.0F, -13.0F, 2.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(88, 0).addBox(-6.0F, -14.0F, 3.0F, 1.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(44, 26).addBox(-5.0F, -15.0F, 1.0F, 10.0F, 9.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(80, 82).addBox(3.0F, -6.0F, 2.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(84, 46).addBox(-4.0F, -6.0F, 2.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(38, 61).addBox(-5.0F, -16.0F, 2.0F, 10.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(62, 17).addBox(-4.0F, -17.0F, 3.0F, 8.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(86, 39).addBox(-3.0F, -18.0F, 4.0F, 6.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(72, 93).addBox(-4.0F, -13.0F, 12.0F, 8.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(104, 0).addBox(-3.0F, -12.0F, 14.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(88, 102).addBox(4.0F, -13.0F, 12.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(50, 112).addBox(-5.0F, -13.0F, 12.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(84, 57).addBox(-4.0F, -14.0F, 12.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, -14.0F, 9.0F));

		PartDefinition cube_r7 = tail.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(22, 98).addBox(0.0F, -1.0F, 13.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.2374F, 3.1533F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r8 = tail.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(40, 97).addBox(-1.0F, -2.0F, 7.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 1.7374F, 3.1533F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r9 = tail.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(26, 71).addBox(-2.0F, -3.0F, -3.0F, 4.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.2374F, 3.1533F, -0.3927F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);

	}

	@Override
	public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(p_102622_, p_102623_, p_102621_);
		if(p_102618_.walkAnimation.isMoving()) {
			this.animateWalk(ModAnimationDefinitions.GIANT_OTTER_WALK, p_102619_, p_102620_, 2f, 2.5f);
		} else {
			this.animate(p_102618_.idleAnimationState, ModAnimationDefinitions.MINIHIPO_IDLE, p_102621_, 1f);
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