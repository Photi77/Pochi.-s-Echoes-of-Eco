package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.Sturgeon;

public class SturgeonModel<T extends Sturgeon> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    //public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "ant"), "main");
    private final ModelPart sturgeon;
    private final ModelPart body;
    private final ModelPart right;
    private final ModelPart body1;
    private final ModelPart body2;
    private final ModelPart body3;
    private final ModelPart body4;
    private final ModelPart body5;
    private final ModelPart body6;
    private final ModelPart tail;
    private final ModelPart head;

    public SturgeonModel(ModelPart root) {
        this.sturgeon = root.getChild("sturgeon");
        this.body = this.sturgeon.getChild("body");
        this.right = this.body.getChild("right");
        this.body1 = this.body.getChild("body1");
        this.body2 = this.body1.getChild("body2");
        this.body3 = this.body2.getChild("body3");
        this.body4 = this.body3.getChild("body4");
        this.body5 = this.body4.getChild("body5");
        this.body6 = this.body5.getChild("body6");
        this.tail = this.body6.getChild("tail");
        this.head = this.sturgeon.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition sturgeon = partdefinition.addOrReplaceChild("sturgeon", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = sturgeon.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right = body.addOrReplaceChild("right", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body1 = body.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 23).addBox(-4.5F, -8.5F, -2.7F, 9.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(44, 55).addBox(-3.6F, -4.7F, -2.7F, 7.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.7F, -4.6F));

        PartDefinition cube_r1 = body1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(68, 49).addBox(-2.72F, -6.48F, -2.72F, 6.0F, 2.0F, 5.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -3.6F, 0.0F, 0.0F, 0.0F, -0.4363F));

        PartDefinition cube_r2 = body1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(66, 44).addBox(-10.6F, -3.7F, -2.7F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, 0.0F, -0.0385F, 0.2148F, -0.1787F));

        PartDefinition cube_r3 = body1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(68, 56).addBox(2.6F, -3.7F, -2.7F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, 0.0F, -0.0385F, -0.2148F, 0.1787F));

        PartDefinition cube_r4 = body1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 69).addBox(-3.28F, -6.48F, -2.72F, 6.0F, 2.0F, 5.0F, new CubeDeformation(-0.21F)), PartPose.offsetAndRotation(0.0F, -3.6F, 0.0F, 0.0F, 0.0F, 0.4363F));

        PartDefinition body2 = body1.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 0).addBox(-4.75F, -9.0F, -3.0F, 10.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(28, 19).addBox(-3.75F, -5.0F, -3.0F, 8.0F, 2.0F, 6.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, 0.3F, 4.6F));

        PartDefinition cube_r5 = body2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(26, 36).addBox(-3.0F, -7.0F, -3.0F, 7.0F, 2.0F, 6.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(-0.25F, -4.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

        PartDefinition cube_r6 = body2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -7.0F, -3.0F, 7.0F, 2.0F, 6.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.25F, -4.0F, 0.0F, 0.0F, 0.0F, 0.4363F));

        PartDefinition cube_r7 = body2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 10).addBox(-3.0F, -7.0F, -3.0F, 7.0F, 7.0F, 6.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(2.25F, -5.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition body3 = body2.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(54, 27).addBox(-3.4F, -2.0F, -2.7F, 7.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(26, 10).addBox(-4.5F, -5.8F, -2.7F, 9.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 4.4F));

        PartDefinition cube_r8 = body3.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(66, 62).addBox(-2.72F, -6.48F, -2.72F, 6.0F, 2.0F, 5.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -0.9F, 0.0F, 0.0F, 0.0F, -0.4363F));

        PartDefinition cube_r9 = body3.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(22, 44).addBox(-2.39F, -5.99F, -2.71F, 6.0F, 6.0F, 5.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(1.8F, -1.8F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition cube_r10 = body3.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(22, 64).addBox(-3.28F, -6.48F, -2.72F, 6.0F, 2.0F, 5.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -0.9F, 0.0F, 0.0F, 0.0F, 0.4363F));

        PartDefinition body4 = body3.addOrReplaceChild("body4", CubeListBuilder.create().texOffs(52, 36).addBox(-3.12F, -4.16F, -2.16F, 6.0F, 2.0F, 6.0F, new CubeDeformation(-0.01F))
                .texOffs(28, 27).addBox(-3.6F, -6.6F, -2.16F, 7.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.16F, 4.32F));

        PartDefinition cube_r11 = body4.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 61).addBox(-2.216F, -5.544F, -2.216F, 5.0F, 2.0F, 6.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -2.88F, 0.0F, 0.0F, 0.0F, -0.4363F));

        PartDefinition cube_r12 = body4.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(44, 44).addBox(-2.908F, -4.972F, -2.188F, 5.0F, 5.0F, 6.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-1.44F, -3.6F, 0.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r13 = body4.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(80, 77).addBox(-7.88F, -3.16F, -2.16F, 5.0F, 1.0F, 3.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(1.0F, -1.0F, 0.0F, -0.0289F, 0.2163F, -0.134F));

        PartDefinition cube_r14 = body4.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(80, 4).addBox(2.88F, -3.16F, -2.16F, 5.0F, 1.0F, 3.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-1.0F, -1.0F, 0.0F, -0.0289F, -0.2163F, 0.134F));

        PartDefinition cube_r15 = body4.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(56, 17).addBox(-2.784F, -5.544F, -2.216F, 5.0F, 2.0F, 6.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -2.88F, 0.0F, 0.0F, 0.0F, 0.4363F));

        PartDefinition body5 = body4.addOrReplaceChild("body5", CubeListBuilder.create().texOffs(0, 32).addBox(-3.12F, -4.88F, -4.728F, 6.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(54, 8).addBox(-2.696F, -3.728F, -4.728F, 5.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.432F, 7.056F));

        PartDefinition cube_r16 = body5.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(22, 55).addBox(-1.8128F, -4.7952F, -4.8128F, 4.0F, 2.0F, 7.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -2.304F, 0.0F, 0.0F, 0.0F, -0.4363F));

        PartDefinition cube_r17 = body5.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 41).addBox(-2.3464F, -3.9576F, -4.7704F, 4.0F, 4.0F, 7.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-1.152F, -2.88F, 0.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r18 = body5.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 85).addBox(-0.696F, -12.728F, -2.728F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 1.0F, -2.0F, -0.3054F, 0.0F, 0.0F));

        PartDefinition cube_r19 = body5.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 52).addBox(-2.1872F, -4.7952F, -4.8128F, 4.0F, 2.0F, 7.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -2.304F, 0.0F, 0.0F, 0.0F, 0.4363F));

        PartDefinition body6 = body5.addOrReplaceChild("body6", CubeListBuilder.create().texOffs(66, 69).addBox(-2.1568F, -2.0F, -3.7824F, 4.0F, 2.0F, 6.0F, new CubeDeformation(-0.01F))
                .texOffs(58, 0).addBox(-2.696F, -2.9216F, -3.7824F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.728F, 5.4448F));

        PartDefinition cube_r20 = body6.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(76, 34).addBox(-1.4902F, -4.1962F, -3.8902F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -0.4608F, 0.0F, 0.0F, 0.0F, -0.4363F));

        PartDefinition cube_r21 = body6.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(36, 79).addBox(-5.8432F, -3.3824F, -2.7824F, 6.0F, 1.0F, 3.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 1.8824F, -1.0F, -0.0786F, 0.3405F, -0.2317F));

        PartDefinition cube_r22 = body6.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(44, 70).addBox(-1.1029F, -2.9461F, -3.8363F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.7216F, -1.9216F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition cube_r23 = body6.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(62, 77).addBox(-1.5098F, -4.1962F, -3.8902F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -0.4608F, 0.0F, 0.0F, 0.0F, 0.4363F));

        PartDefinition cube_r24 = body6.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(80, 0).addBox(-0.1568F, -3.3824F, -2.7824F, 6.0F, 1.0F, 3.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 1.8824F, -1.0F, -0.0786F, -0.3405F, 0.2317F));

        PartDefinition tail = body6.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(80, 81).addBox(-1.196F, -3.4216F, 0.4384F, 2.0F, 3.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, 0.0F, 0.7792F));

        PartDefinition cube_r25 = tail.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(78, 22).addBox(-0.696F, -9.9216F, 6.2176F, 2.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -5.5F, -2.7792F, -0.6545F, 0.0F, 0.0F));

        PartDefinition cube_r26 = tail.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(50, 83).addBox(-0.696F, -2.9216F, 1.2176F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -0.5F, 1.2208F, -0.7418F, 0.0F, 0.0F));

        PartDefinition head = sturgeon.addOrReplaceChild("head", CubeListBuilder.create().texOffs(18, 78).addBox(-2.48F, -3.508F, -8.128F, 5.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(44, 62).addBox(-3.15F, -4.26F, -4.91F, 6.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(22, 71).addBox(-2.52F, -2.0F, -4.91F, 5.0F, 2.0F, 5.0F, new CubeDeformation(-0.001F))
                .texOffs(36, 83).addBox(-1.52F, -4.508F, -8.128F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(8, 85).addBox(-1.52F, -2.508F, -11.128F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(20, 85).addBox(-0.52F, -1.508F, -15.128F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -7.0F));

        PartDefinition cube_r27 = head.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(0, 76).addBox(-3.61F, -5.99F, -2.71F, 4.0F, 4.0F, 5.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-1.6F, 0.2F, -2.1F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r28 = head.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(78, 15).addBox(-1.964F, -5.076F, -2.964F, 4.0F, 2.0F, 5.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -0.63F, -2.02F, 0.0F, 0.0F, -0.4363F));

        PartDefinition cube_r29 = head.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(78, 8).addBox(-2.036F, -5.076F, -2.964F, 4.0F, 2.0F, 5.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -0.63F, -2.02F, 0.0F, 0.0F, 0.4363F));

        return LayerDefinition.create(meshdefinition, 128, 128);

    }

    @Override
    public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(p_102622_, p_102623_, p_102621_);
        this.animateWalk(ModAnimationDefinitions.STURGEON_SWIM, p_102619_, p_102620_, 2f, 2.5f);
    }

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        sturgeon.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return sturgeon;
    }

}