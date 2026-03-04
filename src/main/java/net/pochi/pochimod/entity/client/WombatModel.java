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
import net.pochi.pochimod.entity.custom.Wombat;

public class WombatModel<T extends Wombat> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	//public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "WombatModel"), "main");
	private final ModelPart bone;
	private final ModelPart leftlegrear;
	private final ModelPart rightlegrear;
	private final ModelPart leftlegfront;
	private final ModelPart rightlegfront;
	private final ModelPart head;
	private final ModelPart bone2;
	private final ModelPart body;

	public WombatModel(ModelPart root) {
		this.bone = root.getChild("bone");
		this.leftlegrear = this.bone.getChild("leftlegrear");
		this.rightlegrear = this.bone.getChild("rightlegrear");
		this.leftlegfront = this.bone.getChild("leftlegfront");
		this.rightlegfront = this.bone.getChild("rightlegfront");
		this.head = this.bone.getChild("head");
		this.bone2 = this.head.getChild("bone2");
		this.body = this.bone.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition leftlegrear = bone.addOrReplaceChild("leftlegrear", CubeListBuilder.create().texOffs(54, 35).addBox(3.0F, 5.0F, -3.0F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(78, 57).addBox(3.0F, -2.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, -5.0F, 5.0F));

		PartDefinition rightlegrear = bone.addOrReplaceChild("rightlegrear", CubeListBuilder.create().texOffs(40, 80).addBox(-7.0F, 5.0F, -3.0F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(70, 80).addBox(-7.0F, -2.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, -5.0F, 5.0F));

		PartDefinition leftlegfront = bone.addOrReplaceChild("leftlegfront", CubeListBuilder.create().texOffs(24, 77).addBox(3.0F, -1.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(44, 35).addBox(3.0F, 6.0F, -3.0F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -7.0F));

		PartDefinition rightlegfront = bone.addOrReplaceChild("rightlegfront", CubeListBuilder.create().texOffs(0, 81).addBox(-7.0F, -1.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(88, 79).addBox(-7.0F, 6.0F, -3.0F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -7.0F));

		PartDefinition head = bone.addOrReplaceChild("head", CubeListBuilder.create().texOffs(70, 12).addBox(-4.0F, -6.0F, -4.0F, 8.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(86, 52).addBox(-3.0F, -7.0F, -4.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(38, 90).addBox(-2.0F, -6.0F, -6.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(24, 88).addBox(-5.0F, -5.0F, -4.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(54, 88).addBox(4.0F, -5.0F, -4.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(30, 88).addBox(-6.0F, -3.0F, -4.0F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(90, 0).addBox(5.0F, -3.0F, -4.0F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(52, 70).addBox(-3.0F, -5.0F, -7.0F, 6.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(90, 40).addBox(-2.0F, -4.0F, -9.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(54, 81).addBox(-3.0F, -3.0F, -9.0F, 6.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(86, 90).addBox(-2.0F, -2.0F, -10.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(86, 80).addBox(-4.0F, -4.0F, -7.0F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(90, 33).addBox(-5.0F, -2.0F, -7.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(16, 81).addBox(3.0F, -4.0F, -7.0F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(90, 26).addBox(4.0F, -2.0F, -7.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(88, 70).addBox(3.0F, -4.0F, -2.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(60, 88).addBox(-5.0F, -4.0F, -2.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(66, 47).addBox(-4.0F, -5.0F, -2.0F, 8.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(90, 43).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, -7.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(78, 91).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -5.0F, -2.0F, -0.1732F, 0.0151F, 0.1739F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(16, 91).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -5.0F, -2.0F, -0.1745F, 0.0F, -0.1745F));

		PartDefinition bone2 = head.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 92).addBox(-9.0F, -11.0F, -12.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(6, 92).addBox(-16.0F, -11.0F, -12.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(12.0F, 10.0F, 3.0F));

		PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.0F, 1.0F, 8.0F, 9.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(0, 21).addBox(-4.0F, 2.0F, 0.0F, 8.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(68, 91).addBox(-2.0F, 2.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(40, 77).addBox(-2.0F, 2.0F, 14.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 36).addBox(-4.0F, -9.0F, 1.0F, 8.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(86, 47).addBox(-4.0F, -9.0F, 13.0F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(40, 0).addBox(4.0F, -7.0F, 1.0F, 1.0F, 9.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(52, 57).addBox(4.0F, -8.0F, 1.0F, 1.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(0, 50).addBox(5.0F, -6.0F, 1.0F, 1.0F, 8.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(66, 12).addBox(5.0F, -5.0F, 13.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(66, 0).addBox(5.0F, -7.0F, 1.0F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(40, 36).addBox(-5.0F, -7.0F, 1.0F, 1.0F, 9.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(26, 57).addBox(-6.0F, -6.0F, 1.0F, 1.0F, 8.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(44, 21).addBox(-5.0F, -8.0F, 1.0F, 1.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(66, 35).addBox(-6.0F, -7.0F, 1.0F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(50, 90).addBox(-6.0F, -5.0F, 13.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 70).addBox(-5.0F, -7.0F, 13.0F, 10.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(78, 68).addBox(-4.0F, -8.0F, 14.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(70, 70).addBox(-4.0F, -7.0F, 15.0F, 8.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(40, 81).addBox(-3.0F, -6.0F, 16.0F, 6.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(26, 50).addBox(-2.0F, -8.0F, 16.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(70, 24).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(90, 17).addBox(4.0F, -6.0F, 0.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(90, 8).addBox(-6.0F, -6.0F, 0.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(86, 50).addBox(-4.0F, -8.0F, 0.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(86, 55).addBox(-3.0F, -8.0F, -1.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -7.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);


	}

	@Override
	public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(p_102622_, p_102623_, p_102621_);
		if(p_102618_.walkAnimation.isMoving()) {
			this.animateWalk(ModAnimationDefinitions.WOMBAT_WALK, p_102619_, p_102620_, 2f, 2.5f);
		} else {
			this.animate(p_102618_.idleAnimationState, ModAnimationDefinitions.WOMBAT_IDLE, p_102621_, 1f);
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
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return bone;
	}
}