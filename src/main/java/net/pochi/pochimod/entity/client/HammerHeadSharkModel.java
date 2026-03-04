package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.HammerHeadShark;

public class HammerHeadSharkModel<T extends HammerHeadShark> extends HierarchicalModel<T> {
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

    public HammerHeadSharkModel(ModelPart root) {
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

        PartDefinition body1 = body.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 10).addBox(-4.5F, -7.5F, -2.7F, 9.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(48, 40).addBox(-3.5F, -8.5F, -2.7F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(68, 46).addBox(-1.5F, -9.5F, -2.7F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 40).addBox(-3.6F, -3.7F, -2.7F, 7.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.7F, -4.6F));

        PartDefinition cube_r1 = body1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(52, 32).addBox(-10.6F, -3.7F, -2.7F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, -0.0385F, 0.2148F, -0.1787F));

        PartDefinition cube_r2 = body1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(44, 53).addBox(2.6F, -3.7F, -2.7F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, -0.0385F, -0.2148F, 0.1787F));

        PartDefinition body2 = body1.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 0).addBox(-4.75F, -2.0F, 1.0F, 10.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(32, 15).addBox(-3.75F, -3.0F, 1.0F, 8.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(24, 40).addBox(-2.75F, -4.0F, 1.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(28, 24).addBox(-3.75F, 2.0F, 1.0F, 8.0F, 2.0F, 6.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, -5.7F, 0.6F));

        PartDefinition cube_r3 = body2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(38, 66).addBox(-1.0F, -18.0F, -3.0F, 1.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.45F, 5.0F, 1.9F, -0.3054F, 0.0F, 0.0F));

        PartDefinition body3 = body2.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(0, 33).addBox(-3.4F, 2.0F, -0.7F, 7.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(44, 47).addBox(-3.5F, -2.8F, -0.7F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(60, 7).addBox(-2.5F, -3.8F, -0.7F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 24).addBox(-4.5F, -1.8F, -0.7F, 9.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 6.4F));

        PartDefinition body4 = body3.addOrReplaceChild("body4", CubeListBuilder.create().texOffs(28, 32).addBox(-3.12F, 0.84F, 0.84F, 6.0F, 2.0F, 6.0F, new CubeDeformation(-0.01F))
                .texOffs(32, 0).addBox(-3.6F, -1.6F, 0.84F, 7.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(22, 55).addBox(-2.6F, -2.6F, 0.84F, 5.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(20, 62).addBox(-1.6F, -3.6F, 0.84F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.16F, 3.32F));

        PartDefinition cube_r4 = body4.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(68, 52).addBox(-7.88F, -3.16F, -2.16F, 5.0F, 1.0F, 3.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(1.0F, 4.0F, 3.0F, -0.0289F, 0.2163F, -0.134F));

        PartDefinition cube_r5 = body4.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(56, 28).addBox(2.88F, -3.16F, -2.16F, 5.0F, 1.0F, 3.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-1.0F, 4.0F, 3.0F, -0.0289F, -0.2163F, 0.134F));

        PartDefinition body5 = body4.addOrReplaceChild("body5", CubeListBuilder.create().texOffs(0, 47).addBox(-2.12F, -0.88F, -0.728F, 4.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(60, 13).addBox(-1.12F, -1.88F, -0.728F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 56).addBox(-1.696F, 0.272F, -0.728F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.568F, 6.056F));

        PartDefinition cube_r6 = body5.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(30, 69).addBox(-0.696F, -12.728F, -2.728F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 5.0F, 2.0F, -0.3054F, 0.0F, 0.0F));

        PartDefinition body6 = body5.addOrReplaceChild("body6", CubeListBuilder.create().texOffs(0, 65).addBox(-1.1568F, 0.0F, 0.2176F, 2.0F, 2.0F, 6.0F, new CubeDeformation(-0.01F))
                .texOffs(44, 58).addBox(-1.696F, -0.9216F, 0.2176F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.272F, 5.4448F));

        PartDefinition cube_r7 = body6.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(52, 37).addBox(-2.8432F, -3.3824F, -2.7824F, 3.0F, 1.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 3.8824F, 3.0F, -0.0786F, 0.3405F, -0.2317F));

        PartDefinition cube_r8 = body6.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(62, 37).addBox(-0.1568F, -3.3824F, -2.7824F, 3.0F, 1.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 3.8824F, 3.0F, -0.0786F, -0.3405F, 0.2317F));

        PartDefinition tail = body6.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(58, 71).addBox(-1.196F, -1.4216F, 0.4384F, 2.0F, 3.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, 0.0F, 4.7792F));

        PartDefinition cube_r9 = tail.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(48, 66).addBox(-0.696F, -9.9216F, 6.2176F, 2.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -3.5F, -2.7792F, -0.6545F, 0.0F, 0.0F));

        PartDefinition cube_r10 = tail.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(72, 37).addBox(-0.696F, -2.9216F, 1.2176F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 1.5F, 1.2208F, -0.7418F, 0.0F, 0.0F));

        PartDefinition head = sturgeon.addOrReplaceChild("head", CubeListBuilder.create().texOffs(56, 22).addBox(-3.48F, -4.508F, -19.128F, 7.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(22, 47).addBox(-3.15F, -4.26F, -11.91F, 6.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(16, 69).addBox(-1.15F, -6.26F, -11.91F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(62, 65).addBox(-2.15F, -5.26F, -11.91F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(58, 0).addBox(-2.52F, -2.0F, -11.91F, 5.0F, 2.0F, 5.0F, new CubeDeformation(-0.001F))
                .texOffs(70, 71).addBox(-1.52F, -4.508F, -15.128F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(62, 58).addBox(-2.52F, -3.508F, -15.128F, 5.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r11 = head.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(28, 10).addBox(0.52F, -4.008F, -12.312F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.3F, -5.816F, 0.0F, -0.1745F, 0.0F));

        PartDefinition cube_r12 = head.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 19).addBox(-12.52F, -4.008F, -12.312F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.3F, -5.816F, 0.0F, 0.1745F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(p_102622_, p_102623_, p_102621_);
        this.animateWalk(ModAnimationDefinitions.HAMMER_HEAD_SWIM, p_102619_, p_102620_, 2f, 2.5f);
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