package net.pochi.pochimod.entity.client;// Made with Blockbench 5.0.4
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
import net.pochi.pochimod.entity.custom.LeopardGecko;

public class LeopardGeckoModel<T extends LeopardGecko> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	//public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "LeopardGeckoModel"), "main");
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

	public LeopardGeckoModel(ModelPart root) {
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

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -5.0F, -6.0F, 4.0F, 3.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(0, 25).addBox(2.0F, -5.0F, -5.0F, 2.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 14).addBox(-2.0F, -6.0F, -5.0F, 4.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(24, 25).addBox(-4.0F, -5.0F, -5.0F, 2.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck = root.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(46, 0).addBox(-1.5F, -4.25F, -8.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(30, 7).addBox(-3.0F, -6.0F, -2.0F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(30, 0).addBox(-2.5F, -6.25F, -5.0F, 5.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(26, 38).addBox(-1.5F, -4.5F, -8.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -8.0F));

		PartDefinition ear_l = head.addOrReplaceChild("ear_l", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 8.0F));

		PartDefinition ear_r = head.addOrReplaceChild("ear_r", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 8.0F));

		PartDefinition hand_r = root.addOrReplaceChild("hand_r", CubeListBuilder.create().texOffs(46, 4).addBox(-3.0F, -1.0F, -3.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(38, 42).addBox(-3.0F, -3.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.0F, -4.0F));

		PartDefinition hand_l = root.addOrReplaceChild("hand_l", CubeListBuilder.create().texOffs(26, 43).addBox(-1.0F, -3.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(46, 16).addBox(1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 0.0F, -4.0F));

		PartDefinition leg_r = root.addOrReplaceChild("leg_r", CubeListBuilder.create().texOffs(14, 43).addBox(-3.0F, -3.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(46, 12).addBox(-3.0F, -1.0F, -3.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.0F, 4.0F));

		PartDefinition leg_l = root.addOrReplaceChild("leg_l", CubeListBuilder.create().texOffs(38, 38).addBox(-1.0F, -3.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(46, 8).addBox(1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 0.0F, 4.0F));

		PartDefinition tail = root.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(46, 20).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(28, 14).addBox(-2.0F, -5.0F, 2.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(14, 38).addBox(-1.5F, -4.5F, 7.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 44).addBox(-1.0F, -4.0F, 10.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 38).addBox(-1.0F, -6.0F, 2.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 5.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);

	}

	@Override
	public void setupAnim(LeopardGecko entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		if(entity.walkAnimation.isMoving()) {
			this.animateWalk(ModAnimationDefinitions.LEOPARD_GECKO_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		} else {
			//this.animate(entity.idleAnimationState, ModAnimationDefinitions.ANT_IDLE, ageInTicks, 1f);
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
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}