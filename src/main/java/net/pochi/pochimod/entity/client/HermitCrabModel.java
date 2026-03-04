package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.HermitCrab;

public class HermitCrabModel<T extends HermitCrab> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	//public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "yadokari_java_- converted - converted"), "main");
	private final ModelPart hermitcrab;
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart l3;
	private final ModelPart l2;
	private final ModelPart l1;
	private final ModelPart eye;
	private final ModelPart r1;
	private final ModelPart r2;
	private final ModelPart r3;
	private final ModelPart rs;
	private final ModelPart ls;
	public boolean carrying;

	public HermitCrabModel(ModelPart root) {
		this.hermitcrab = root.getChild("hermitcrab");
		this.body = hermitcrab.getChild("body");
		this.tail = hermitcrab.getChild("tail");
		this.l3 = hermitcrab.getChild("l3");
		this.l2 = hermitcrab.getChild("l2");
		this.l1 = hermitcrab.getChild("l1");
		this.eye = hermitcrab.getChild("eye");
		this.r1 = hermitcrab.getChild("r1");
		this.r2 = hermitcrab.getChild("r2");
		this.r3 = hermitcrab.getChild("r3");
		this.rs = hermitcrab.getChild("rs");
		this.ls = hermitcrab.getChild("ls");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition hermitcrab = partdefinition.addOrReplaceChild("hermitcrab", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = hermitcrab.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -5.0F, -1.0F, 8.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -4.0F, -1.0F, 12.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(28, 9).addBox(-4.0F, -5.0F, 5.0F, 8.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 9).addBox(-4.0F, -1.0F, -1.0F, 8.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(26, 16).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -4.0F));

		PartDefinition tail = hermitcrab.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 34).addBox(-3.0F, -3.0F, 2.0F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(28, 40).addBox(-2.0F, -4.0F, 2.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 1.0036F, 0.0F, 0.0F));

		PartDefinition cube_r1 = tail.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(48, 10).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F))
				.texOffs(14, 39).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 10.0F, 0.0F, -1.4835F, 0.0F));

		PartDefinition cube_r2 = tail.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(18, 34).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 2.0F, 3.0F, new CubeDeformation(-0.001F))
				.texOffs(40, 29).addBox(-2.0F, -4.0F, 0.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 5.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r3 = tail.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(42, 40).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(50, 33).addBox(-0.5F, -4.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.9F, 0.0F, 10.0F, 0.0F, -2.2253F, 0.0F));

		PartDefinition cube_r4 = tail.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 39).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(48, 6).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 8.0F, 0.0F, -0.829F, 0.0F));

		PartDefinition l3 = hermitcrab.addOrReplaceChild("l3", CubeListBuilder.create(), PartPose.offsetAndRotation(5.0F, -5.0F, 1.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition cube_r5 = l3.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(20, 49).addBox(0.0426F, -2.9754F, 3.7836F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.6F, -6.2F, -0.218F, -0.0094F, 1.2664F));

		PartDefinition cube_r6 = l3.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(50, 4).addBox(0.7887F, -2.9531F, 4.7836F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, 0.7F, -6.6F, -0.2132F, 0.0469F, 1.5221F));

		PartDefinition cube_r7 = l3.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(30, 44).addBox(-0.057F, -3.2527F, 3.7836F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -6.0F, -0.0573F, -0.2106F, 0.0061F));

		PartDefinition l2 = hermitcrab.addOrReplaceChild("l2", CubeListBuilder.create().texOffs(0, 44).addBox(-1.0F, -3.5F, -1.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -5.0F, -2.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition cube_r8 = l2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(48, 14).addBox(0.0F, -2.0F, 1.5F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.6F, -3.2F, 0.0F, 0.0F, 1.2654F));

		PartDefinition cube_r9 = l2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(28, 14).addBox(1.0F, -2.0F, 2.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, 0.7F, -3.6F, 0.0F, 0.0F, 1.5272F));

		PartDefinition l1 = hermitcrab.addOrReplaceChild("l1", CubeListBuilder.create(), PartPose.offset(5.0F, -5.0F, -5.0F));

		PartDefinition cube_r10 = l1.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(36, 14).addBox(1.0F, -2.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, 0.7F, -0.6F, 0.4272F, -0.0916F, 1.8127F));

		PartDefinition cube_r11 = l1.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 49).addBox(0.0F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.6F, -0.2F, 0.436F, 0.0184F, 1.5749F));

		PartDefinition cube_r12 = l1.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(20, 44).addBox(-1.0F, -3.0F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.1201F, 0.4205F, 0.3311F));

		PartDefinition eye = hermitcrab.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(50, 0).addBox(-3.5F, -8.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(40, 50).addBox(1.5F, -8.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(50, 45).addBox(-3.0F, -6.0F, -3.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 50).addBox(2.0F, -6.0F, -3.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, -5.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition r1 = hermitcrab.addOrReplaceChild("r1", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.0F, -5.0F, -5.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition cube_r13 = r1.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(44, 27).addBox(-4.0F, -2.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, 0.7F, -0.6F, 0.4272F, 0.0916F, -1.5073F));

		PartDefinition cube_r14 = r1.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(10, 49).addBox(-3.0F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.6F, -0.2F, 0.436F, -0.0184F, -1.2694F));

		PartDefinition cube_r15 = r1.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(44, 22).addBox(-2.0F, -3.0F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.1201F, -0.4205F, -0.0257F));

		PartDefinition r2 = hermitcrab.addOrReplaceChild("r2", CubeListBuilder.create().texOffs(40, 45).addBox(-2.0F, -3.5F, -1.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -5.0F, -2.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition cube_r16 = r2.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(30, 49).addBox(-3.0F, -2.0F, 1.5F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.6F, -3.2F, 0.0F, 0.0F, -1.2654F));

		PartDefinition cube_r17 = r2.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(50, 37).addBox(-4.0F, -2.0F, 2.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, 0.7F, -3.6F, 0.0F, 0.0F, -1.5272F));

		PartDefinition r3 = hermitcrab.addOrReplaceChild("r3", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.0F, -5.0F, 1.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition cube_r18 = r3.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(36, 6).addBox(-3.7887F, -2.9531F, 4.7836F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, 0.7F, -6.6F, -0.2132F, -0.0469F, -1.5221F));

		PartDefinition cube_r19 = r3.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(48, 18).addBox(-3.0426F, -2.9754F, 3.7836F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.6F, -6.2F, -0.218F, 0.0094F, -1.2664F));

		PartDefinition cube_r20 = r3.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(10, 44).addBox(-2.943F, -3.2527F, 3.7836F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -6.0F, -0.0573F, 0.2106F, -0.0061F));

		PartDefinition rs = hermitcrab.addOrReplaceChild("rs", CubeListBuilder.create(), PartPose.offset(-4.0F, -4.0F, -8.0F));

		PartDefinition cube_r21 = rs.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(36, 0).addBox(-4.0F, -4.0F, -1.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(-0.001F))
				.texOffs(0, 22).addBox(-11.0F, -6.0F, -1.0F, 8.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.2217F, 0.0F));

		PartDefinition cube_r22 = rs.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(20, 29).addBox(-5.8992F, 0.0F, -0.5716F, 7.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -2.0F, -4.0F, 0.6905F, -1.1111F, -0.7448F));

		PartDefinition ls = hermitcrab.addOrReplaceChild("ls", CubeListBuilder.create(), PartPose.offset(4.0F, -4.0F, -8.0F));

		PartDefinition cube_r23 = ls.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(22, 22).addBox(3.0F, -6.0F, -1.0F, 8.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(36, 34).addBox(0.0F, -4.0F, -1.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.2217F, 0.0F));

		PartDefinition cube_r24 = ls.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(0, 29).addBox(-1.1008F, 0.0F, -0.5716F, 7.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -2.0F, -4.0F, 0.6905F, 1.1111F, 0.7448F));

		return LayerDefinition.create(meshdefinition, 64, 64);

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		hermitcrab.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}


	@Override
	public ModelPart root() {
		return hermitcrab;
	}

	@Override
	public void setupAnim(HermitCrab p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if(p_102618_.walkAnimation.isMoving()) {
			this.animateWalk(ModAnimationDefinitions.HERMIT_WALK, p_102619_, p_102620_, 2f, 2.5f);
		} else {
			this.animate(p_102618_.idleAnimationState, ModAnimationDefinitions.HERMIT_IDLE, p_102621_, 1f);
		}
	}
}