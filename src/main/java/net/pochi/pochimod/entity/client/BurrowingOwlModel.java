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
import net.pochi.pochimod.entity.custom.BurrowingOwl;

public class BurrowingOwlModel<T extends BurrowingOwl> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	//public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "BurrowingOwlModel"), "main");
	private final ModelPart etupirka;
	private final ModelPart entire;
	private final ModelPart leftleg;
	private final ModelPart rightleg;
	private final ModelPart head;
	private final ModelPart leftwing;
	private final ModelPart rightwing;
	private final ModelPart body;

	public BurrowingOwlModel(ModelPart root) {
		this.etupirka = root.getChild("etupirka");
		this.entire = this.etupirka.getChild("entire");
		this.leftleg = this.entire.getChild("leftleg");
		this.rightleg = this.entire.getChild("rightleg");
		this.head = this.entire.getChild("head");
		this.leftwing = this.entire.getChild("leftwing");
		this.rightwing = this.entire.getChild("rightwing");
		this.body = this.entire.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition etupirka = partdefinition.addOrReplaceChild("etupirka", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition entire = etupirka.addOrReplaceChild("entire", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftleg = entire.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(38, 32).addBox(1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(40, 47).addBox(1.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -6.0F, -1.0F));

		PartDefinition cube_r1 = leftleg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(46, 26).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 6.0F, -0.5F, 0.0F, 0.0873F, 0.0F));

		PartDefinition cube_r2 = leftleg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(44, 45).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(2.0F, 6.0F, -0.5F, 0.0F, -0.6109F, 0.0F));

		PartDefinition cube_r3 = leftleg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(44, 41).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(2.0F, 6.0F, -0.5F, 0.0F, 0.7854F, 0.0F));

		PartDefinition rightleg = entire.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(8, 43).addBox(-3.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(8, 48).addBox(-2.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -6.0F, -1.0F));

		PartDefinition cube_r4 = rightleg.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(32, 47).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 6.0F, -0.5F, 0.0F, -0.0873F, 0.0F));

		PartDefinition cube_r5 = rightleg.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(46, 34).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-2.0F, 6.0F, -0.5F, 0.0F, 0.6109F, 0.0F));

		PartDefinition cube_r6 = rightleg.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(46, 30).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-2.0F, 6.0F, -0.5F, 0.0F, -0.7854F, 0.0F));

		PartDefinition head = entire.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 24).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(38, 26).addBox(-3.0F, -3.0F, -2.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(36, 41).addBox(2.0F, -3.0F, -2.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(36, 37).addBox(-2.0F, -3.0F, 1.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(16, 43).addBox(-1.5F, -3.5F, -3.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(14, 24).addBox(-2.5F, -3.0F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(32, 42).addBox(1.5F, -3.0F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(46, 38).addBox(-0.5F, -1.4F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -15.0F, -2.0F));

		PartDefinition leftwing = entire.addOrReplaceChild("leftwing", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0F, -13.0F, 0.0F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r7 = leftwing.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 37).addBox(0.0F, 2.0F, -1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(24, 42).addBox(0.0F, -2.0F, 6.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, -2.0F, -2.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -0.534F, -0.1886F, -0.4568F));

		PartDefinition rightwing = entire.addOrReplaceChild("rightwing", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, -13.0F, 0.0F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r8 = rightwing.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(12, 37).addBox(-1.0F, 2.0F, -1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 43).addBox(-1.0F, -2.0F, 6.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 12).addBox(-1.0F, -2.0F, -2.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -0.534F, 0.1886F, 0.4568F));

		PartDefinition body = entire.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -3.0F, -6.0F, -0.7418F, 0.0F, 0.0F));

		PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(24, 37).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(-0.01F))
				.texOffs(18, 23).addBox(-2.0F, -8.0F, -4.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(-0.01F))
				.texOffs(36, 14).addBox(-3.0F, -7.0F, -4.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(-0.01F))
				.texOffs(36, 20).addBox(2.0F, -7.0F, -4.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 1.0F, 5.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(26, 29).addBox(2.0F, -7.0F, -1.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(14, 29).addBox(7.0F, -7.0F, -1.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(18, 17).addBox(3.0F, -4.0F, -1.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(18, 0).addBox(3.0F, -8.0F, -1.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(36, 0).addBox(3.0F, -9.0F, -4.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 31).addBox(4.0F, -9.0F, -1.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(36, 4).addBox(3.0F, -3.0F, -4.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(36, 8).addBox(3.0F, -7.0F, -6.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.001F))
				.texOffs(18, 9).addBox(2.0F, -8.0F, -4.0F, 6.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -4.0F, -5.0F, -0.7418F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);

	}

	@Override
	public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(p_102622_, p_102623_, p_102621_);
		if(p_102618_.onGround()) {
			this.animateWalk(ModAnimationDefinitions.BURROWING_WALK, p_102619_, p_102620_, 2f, 2.5f);
		} else {
			this.animateWalk(ModAnimationDefinitions.BURROWING_FLY, p_102619_, p_102620_, 2f, 2.5f);
		}

		this.animate(p_102618_.idleAnimationState, ModAnimationDefinitions.BURROWING_IDLE, p_102621_, 1f);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		etupirka.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return etupirka;
	}
}