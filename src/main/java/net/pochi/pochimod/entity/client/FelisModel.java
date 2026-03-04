package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.Felis;

public class FelisModel<T extends Felis> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    //public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "ant"), "main");
    private final ModelPart felis;
    private final ModelPart body;
    private final ModelPart torso;
    private final ModelPart righthand;
    private final ModelPart rightleg;
    private final ModelPart lefthand;
    private final ModelPart leftleg;
    private final ModelPart tail;
    private final ModelPart head;

    public FelisModel(ModelPart root) {
        this.felis = root.getChild("felis");
        this.body = this.felis.getChild("body");
        this.torso = this.body.getChild("torso");
        this.righthand = this.torso.getChild("righthand");
        this.rightleg = this.torso.getChild("rightleg");
        this.lefthand = this.torso.getChild("lefthand");
        this.leftleg = this.torso.getChild("leftleg");
        this.tail = this.torso.getChild("tail");
        this.head = this.body.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition felis = partdefinition.addOrReplaceChild("felis", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = felis.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -1.0F, -1.0F, 6.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, -7.0F));

        PartDefinition righthand = torso.addOrReplaceChild("righthand", CubeListBuilder.create().texOffs(0, 51).addBox(-1.0F, 10.0F, -4.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.002F)), PartPose.offset(-3.0F, -1.0F, 3.0F));

        PartDefinition cube_r1 = righthand.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(44, 44).addBox(-4.0F, -1.0F, -2.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(3.0F, 5.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r2 = righthand.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 34).addBox(-4.0F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 1.0F, -1.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition rightleg = torso.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(46, 27).addBox(-1.0F, 7.0F, -3.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.002F)), PartPose.offset(-3.0F, 2.0F, 12.0F));

        PartDefinition cube_r3 = rightleg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(28, 40).addBox(-4.0F, -1.0F, -2.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(3.0F, 2.0F, 1.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r4 = rightleg.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(38, 36).addBox(-4.0F, 0.0F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -2.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition lefthand = torso.addOrReplaceChild("lefthand", CubeListBuilder.create().texOffs(14, 37).addBox(-1.0F, 9.0F, -3.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.002F)), PartPose.offset(3.0F, 0.0F, 2.0F));

        PartDefinition cube_r5 = lefthand.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(44, 11).addBox(2.0F, -1.0F, -2.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(-3.0F, 4.0F, 1.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r6 = lefthand.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(24, 30).addBox(1.0F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition leftleg = torso.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(22, 49).addBox(-1.0F, 7.0F, -3.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.002F)), PartPose.offset(3.0F, 2.0F, 12.0F));

        PartDefinition cube_r7 = leftleg.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(36, 44).addBox(2.0F, -1.0F, -2.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(-3.0F, 2.0F, 1.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r8 = leftleg.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(14, 40).addBox(1.0F, 0.0F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -2.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition tail = torso.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 15.0F));

        PartDefinition cube_r9 = tail.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 22).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(44, 0).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(24, 22).addBox(-3.0F, -3.0F, -4.0F, 6.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(46, 20).addBox(3.0F, -3.0F, -3.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(-0.002F))
                .texOffs(38, 30).addBox(-2.0F, 0.0F, -4.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(14, 34).addBox(-2.0F, -2.0F, -5.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(30, 49).addBox(-1.0F, -2.0F, -6.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(12, 48).addBox(-4.0F, -3.0F, -3.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(-0.002F)), PartPose.offset(0.0F, -10.0F, -7.0F));

        PartDefinition cube_r10 = head.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(44, 4).addBox(-3.0F, -7.0F, -8.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 7.0F, 0.0F, 0.0F, -0.6545F));

        PartDefinition cube_r11 = head.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 44).addBox(-1.0F, -7.0F, -8.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 7.0F, 0.0F, 0.0F, 0.6545F));

        return LayerDefinition.create(meshdefinition, 64, 64);

    }

    @Override
    public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(p_102622_, p_102623_, p_102621_);
        if(p_102618_.walkAnimation.isMoving()) {
            this.animateWalk(ModAnimationDefinitions.FELIS_WALK, p_102619_, p_102620_, 2f, 2.5f);
        } else {
            this.animate(p_102618_.idleAnimationState, ModAnimationDefinitions.FELIS_IDLE, p_102621_, 1f);
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
        felis.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return felis;
    }
}