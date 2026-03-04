package net.pochi.pochimod.entity.client;// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.pochi.pochimod.entity.custom.Betta;

public class BettaModel<T extends Betta> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	//public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "BettaModel"), "main");
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

	public BettaModel(ModelPart root) {
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

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(46, 48).addBox(-1.0F, -2.0F, 6.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 20).addBox(-1.0F, -3.0F, -5.0F, 2.0F, 7.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(46, 12).addBox(-1.0F, -2.0F, 3.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(46, 0).addBox(1.0F, -2.0F, -5.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 47).addBox(-2.0F, -2.0F, -5.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 0.0F));

		PartDefinition neck = root.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, -3.0F));

		PartDefinition cube_r1 = neck.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 0).addBox(1.0F, -18.0F, -4.0F, 0.0F, 9.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 9.0F, 11.0F, 0.5672F, 0.0F, 0.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(36, 48).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, -2.0F));

		PartDefinition ear_l = head.addOrReplaceChild("ear_l", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, 5.0F));

		PartDefinition ear_r = head.addOrReplaceChild("ear_r", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, 5.0F));

		PartDefinition hand_r = root.addOrReplaceChild("hand_r", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, -4.0F));

		PartDefinition cube_r2 = hand_r.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(18, 35).addBox(-1.0F, -5.0F, -1.0F, 0.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.25F, 1.0F, 1.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition hand_l = root.addOrReplaceChild("hand_l", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, -4.0F));

		PartDefinition cube_r3 = hand_l.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 30).addBox(1.0F, -5.0F, -1.0F, 0.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.25F, 1.0F, 1.0F, 0.0F, 0.1745F, 0.0F));

		PartDefinition leg_r = root.addOrReplaceChild("leg_r", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r4 = leg_r.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(36, 35).addBox(-1.0F, -2.0F, -1.0F, 0.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -1.5F, -2.0F, -0.3927F, -0.1745F, 0.0F));

		PartDefinition leg_l = root.addOrReplaceChild("leg_l", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r5 = leg_l.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(44, 20).addBox(1.0F, -2.0F, -1.0F, 0.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, -1.5F, -2.0F, -0.3927F, 0.1745F, 0.0F));

		PartDefinition tail = root.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -8.0F, 0.0F, 0.0F, 18.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 7.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Betta entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

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