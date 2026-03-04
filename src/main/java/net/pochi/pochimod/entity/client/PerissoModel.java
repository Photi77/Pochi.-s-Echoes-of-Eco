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
import net.pochi.pochimod.entity.custom.Perisso;

public class PerissoModel<T extends Perisso> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	//public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "PerissoModel"), "main");
	private final ModelPart etupirka;
	private final ModelPart entire;
	private final ModelPart leftleg;
	private final ModelPart rightleg;
	private final ModelPart head;
	private final ModelPart leftwing;
	private final ModelPart rightwing;
	private final ModelPart body;

	public PerissoModel(ModelPart root) {
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

		PartDefinition leftleg = entire.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(62, 63).addBox(1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(18, 52).addBox(1.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -6.0F, -1.0F));

		PartDefinition cube_r1 = leftleg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(64, 25).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 6.0F, -0.5F, 0.0F, 0.0873F, 0.0F));

		PartDefinition cube_r2 = leftleg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(64, 21).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(2.0F, 6.0F, -0.5F, 0.0F, -0.6109F, 0.0F));

		PartDefinition cube_r3 = leftleg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(64, 17).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(2.0F, 6.0F, -0.5F, 0.0F, 0.7854F, 0.0F));

		PartDefinition rightleg = entire.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(64, 6).addBox(-3.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 66).addBox(-2.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -6.0F, -1.0F));

		PartDefinition cube_r4 = rightleg.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(36, 65).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 6.0F, -0.5F, 0.0F, -0.0873F, 0.0F));

		PartDefinition cube_r5 = rightleg.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(64, 33).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-2.0F, 6.0F, -0.5F, 0.0F, 0.6109F, 0.0F));

		PartDefinition cube_r6 = rightleg.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(64, 29).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-2.0F, 6.0F, -0.5F, 0.0F, -0.7854F, 0.0F));

		PartDefinition head = entire.addOrReplaceChild("head", CubeListBuilder.create().texOffs(22, 51).addBox(-2.0F, -4.0F, -3.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(46, 37).addBox(-2.0F, -3.0F, 1.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(52, 65).addBox(-1.5F, -3.3F, -4.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(54, 17).addBox(-1.0F, -2.3F, -5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, -7.0F));

		PartDefinition cube_r7 = head.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(52, 59).addBox(0.0F, -1.0F, -9.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -1.9F, 1.2F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r8 = head.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(48, 17).addBox(0.0F, -1.0F, -11.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -2.1F, 1.3F, 0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r9 = head.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(62, 59).addBox(0.0F, -1.0F, -9.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-1.0F, -1.3F, 1.2F, 0.1309F, 0.0F, 0.0F));

		PartDefinition leftwing = entire.addOrReplaceChild("leftwing", CubeListBuilder.create(), PartPose.offset(3.5F, -12.0F, -3.0F));

		PartDefinition cube_r10 = leftwing.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(56, 51).addBox(0.0F, 3.0F, -2.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(12, 59).addBox(0.0F, -2.0F, 7.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(24, 20).addBox(0.0F, -3.0F, -2.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 16).addBox(0.0F, -2.0F, -4.0F, 1.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -0.534F, -0.1886F, -0.4568F));

		PartDefinition rightwing = entire.addOrReplaceChild("rightwing", CubeListBuilder.create(), PartPose.offset(-3.5F, -12.0F, -3.0F));

		PartDefinition cube_r11 = rightwing.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(38, 57).addBox(-1.0F, 3.0F, -2.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(24, 59).addBox(-1.0F, -2.0F, 7.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(24, 31).addBox(-1.0F, -3.0F, -2.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, -2.0F, -4.0F, 1.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -0.534F, 0.1886F, 0.4568F));

		PartDefinition body = entire.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(46, 29).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 1.0F, 7.0F, new CubeDeformation(-0.01F))
				.texOffs(24, 11).addBox(-2.0F, -8.0F, -4.0F, 4.0F, 1.0F, 8.0F, new CubeDeformation(-0.01F))
				.texOffs(40, 42).addBox(-3.0F, -7.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(-0.01F))
				.texOffs(46, 20).addBox(2.0F, -7.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 1.0F, 5.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r13 = body.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(58, 37).addBox(2.0F, -7.0F, -1.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 58).addBox(7.0F, -7.0F, -1.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(48, 11).addBox(3.0F, -4.0F, -1.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(22, 42).addBox(3.0F, -8.0F, -1.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(50, 6).addBox(3.0F, -9.0F, -4.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(58, 45).addBox(4.0F, -9.0F, -1.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(50, 0).addBox(3.0F, -3.0F, -6.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 52).addBox(1.0F, -7.0F, -5.0F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(38, 51).addBox(3.0F, -7.0F, -5.0F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 32).addBox(2.0F, -8.0F, -6.0F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -4.0F, -5.0F, -0.7418F, 0.0F, 0.0F));

		PartDefinition cube_r14 = body.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 42).addBox(-3.0F, -7.0F, -9.0F, 5.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(44, 65).addBox(-2.0F, -8.0F, -12.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(24, 0).addBox(-3.0F, -8.0F, -11.0F, 5.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -5.2F, -10.0F, -1.309F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);

	}

	@Override
	public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(p_102622_, p_102623_, p_102621_);
		if(p_102618_.onGround()) {
			this.animateWalk(ModAnimationDefinitions.PERRISO_WALK, p_102619_, p_102620_, 2f, 2.5f);
		} else {
			this.animateWalk(ModAnimationDefinitions.PERRISO_FLY, p_102619_, p_102620_, 2f, 2.5f);
		}

		this.animate(p_102618_.idleAnimationState, ModAnimationDefinitions.PERRISO_IDLE, p_102621_, 1f);
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