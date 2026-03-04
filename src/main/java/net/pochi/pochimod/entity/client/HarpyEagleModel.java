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
import net.pochi.pochimod.entity.custom.HarpyEagle;

public class HarpyEagleModel<T extends HarpyEagle> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	//public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "HarpyEagleModel"), "main");
	private final ModelPart etupirka;
	private final ModelPart entire;
	private final ModelPart leftleg;
	private final ModelPart rightleg;
	private final ModelPart head;
	private final ModelPart leftwing;
	private final ModelPart rightwing;
	private final ModelPart body;

	public HarpyEagleModel(ModelPart root) {
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

		PartDefinition leftleg = entire.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(24, 61).addBox(1.0F, 1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(50, 52).addBox(0.5F, -2.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(10, 65).addBox(1.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -6.0F, -1.0F));

		PartDefinition cube_r1 = leftleg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(42, 63).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 6.0F, -0.5F, 0.0F, 0.0873F, 0.0F));

		PartDefinition cube_r2 = leftleg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(60, 62).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(2.0F, 6.0F, -0.5F, 0.0F, -0.6109F, 0.0F));

		PartDefinition cube_r3 = leftleg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(62, 54).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(2.0F, 6.0F, -0.5F, 0.0F, 0.7854F, 0.0F));

		PartDefinition rightleg = entire.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(50, 63).addBox(-3.0F, 1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(36, 53).addBox(-3.5F, -2.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(14, 65).addBox(-2.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -6.0F, -1.0F));

		PartDefinition cube_r4 = rightleg.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(64, 33).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 6.0F, -0.5F, 0.0F, -0.0873F, 0.0F));

		PartDefinition cube_r5 = rightleg.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(64, 4).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-2.0F, 6.0F, -0.5F, 0.0F, 0.6109F, 0.0F));

		PartDefinition cube_r6 = rightleg.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(64, 0).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-2.0F, 6.0F, -0.5F, 0.0F, -0.7854F, 0.0F));

		PartDefinition head = entire.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 57).addBox(-2.0F, -7.0F, -2.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 58).addBox(-1.0F, -8.0F, -3.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(42, 59).addBox(-1.0F, -7.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 62).addBox(-1.5F, -7.0F, -4.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(12, 50).addBox(-1.0F, -6.3F, -6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -11.1F, -8.1F));

		PartDefinition cube_r7 = head.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(40, 9).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, -4.5F, -5.7F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r8 = head.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(60, 58).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -6.0F, 0.0F, 0.7687F, 0.504F, 0.437F));

		PartDefinition cube_r9 = head.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(12, 61).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -6.0F, 0.0F, 0.6848F, -0.2748F, -0.218F));

		PartDefinition cube_r10 = head.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(60, 29).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -7.0F, 0.0F, 0.7527F, -0.4724F, -0.4029F));

		PartDefinition cube_r11 = head.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(52, 42).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -7.0F, 0.0F, 0.7025F, 0.3419F, 0.2766F));

		PartDefinition cube_r12 = head.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(30, 9).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 1.0F, 0.6545F, 0.0F, 0.0F));

		PartDefinition leftwing = entire.addOrReplaceChild("leftwing", CubeListBuilder.create(), PartPose.offset(3.5F, -13.0F, -5.0F));

		PartDefinition cube_r13 = leftwing.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(50, 46).addBox(0.0F, 2.0F, -1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(62, 13).addBox(0.0F, 0.0F, 6.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(20, 37).addBox(0.0F, -2.0F, 6.0F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(32, 54).addBox(0.0F, -2.0F, -4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(24, 23).addBox(0.0F, -3.0F, -3.0F, 1.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -0.534F, -0.1886F, -0.4568F));

		PartDefinition rightwing = entire.addOrReplaceChild("rightwing", CubeListBuilder.create(), PartPose.offset(-3.5F, -13.0F, -5.0F));

		PartDefinition cube_r14 = rightwing.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(52, 36).addBox(-1.0F, 2.0F, -1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(62, 8).addBox(-1.0F, 0.0F, 6.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(36, 37).addBox(-1.0F, -2.0F, 6.0F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(18, 65).addBox(-1.0F, -2.0F, -4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 36).addBox(-1.0F, -3.0F, -3.0F, 1.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -0.534F, 0.1886F, 0.4568F));

		PartDefinition body = entire.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r15 = body.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(24, 12).addBox(-1.0F, -0.8331F, 0.6472F, 2.0F, 1.0F, 10.0F, new CubeDeformation(-0.01F))
				.texOffs(0, 0).addBox(-2.0F, 0.1669F, -0.3528F, 4.0F, 1.0F, 11.0F, new CubeDeformation(-0.01F))
				.texOffs(0, 12).addBox(-3.0F, 1.1669F, -0.3528F, 1.0F, 1.0F, 11.0F, new CubeDeformation(-0.01F))
				.texOffs(0, 24).addBox(2.0F, 1.1669F, -0.3528F, 1.0F, 1.0F, 11.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r16 = body.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(22, 54).addBox(2.0F, -7.0F, -3.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(48, 9).addBox(1.0F, -6.0F, -7.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(48, 16).addBox(8.0F, -6.0F, -7.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(12, 54).addBox(7.0F, -7.0F, -3.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(36, 46).addBox(4.0F, -4.0F, -5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(50, 0).addBox(3.0F, -4.0F, -3.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(64, 37).addBox(4.0F, -6.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(62, 50).addBox(3.0F, -7.0F, 1.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(44, 23).addBox(3.0F, -8.0F, -3.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(44, 31).addBox(3.0F, -9.0F, -7.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(62, 18).addBox(4.0F, -9.0F, -3.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(64, 41).addBox(4.0F, -3.0F, -10.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(50, 4).addBox(3.0F, -3.0F, -8.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(62, 46).addBox(4.0F, -8.0F, -10.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(60, 23).addBox(7.0F, -6.0F, -11.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(32, 59).addBox(2.0F, -6.0F, -11.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(20, 46).addBox(3.0F, -7.0F, -11.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(30, 0).addBox(2.0F, -8.0F, -7.0F, 6.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -4.0F, -5.0F, -0.7418F, 0.0F, 0.0F));

		PartDefinition cube_r17 = body.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 50).addBox(-2.0F, -7.0F, -9.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(1.0F, -7.2F, -13.0F, -1.309F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);

	}

	@Override
	public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(p_102622_, p_102623_, p_102621_);
		if(p_102618_.onGround()) {
			this.animateWalk(ModAnimationDefinitions.HARPY_WALK, p_102619_, p_102620_, 2f, 2.5f);
		} else {
			this.animateWalk(ModAnimationDefinitions.HARPY_FLY, p_102619_, p_102620_, 2f, 2.5f);
		}

		this.animate(p_102618_.idleAnimationState, ModAnimationDefinitions.HERMIT_IDLE, p_102621_, 1f);
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