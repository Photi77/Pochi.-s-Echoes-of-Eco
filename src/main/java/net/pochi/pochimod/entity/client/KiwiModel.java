package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.Kiwi;

public class KiwiModel<T extends Kiwi> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    //public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "ant"), "main");
    private final ModelPart kiwi;
    private final ModelPart leftleg;
    private final ModelPart rightleg;
    private final ModelPart body;
    private final ModelPart right;
    private final ModelPart left;
    private final ModelPart head;

    public KiwiModel(ModelPart root) {
        this.kiwi = root.getChild("kiwi");
        this.leftleg = this.kiwi.getChild("leftleg");
        this.rightleg = this.kiwi.getChild("rightleg");
        this.body = this.kiwi.getChild("body");
        this.right = this.kiwi.getChild("right");
        this.left = this.kiwi.getChild("left");
        this.head = this.kiwi.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition kiwi = partdefinition.addOrReplaceChild("kiwi", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));

        PartDefinition leftleg = kiwi.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(56, 25).addBox(1.0F, -1.0F, -1.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(-0.01F))
                .texOffs(28, 64).addBox(2.0F, -1.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(56, 33).addBox(1.0F, 5.0F, -2.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

        PartDefinition rightleg = kiwi.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(56, 47).addBox(-4.0F, -1.0F, -1.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(-0.01F))
                .texOffs(0, 65).addBox(-3.0F, -1.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(58, 0).addBox(-4.0F, 5.0F, -2.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

        PartDefinition body = kiwi.addOrReplaceChild("body", CubeListBuilder.create().texOffs(18, 40).addBox(-4.0F, -13.0F, 3.0F, 8.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(-6.0F, -14.0F, -6.0F, 1.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(40, 0).addBox(5.0F, -14.0F, -6.0F, 1.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(46, 55).addBox(-3.0F, -10.0F, 4.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(34, 19).addBox(-4.0F, -14.0F, -9.0F, 8.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 19).addBox(-4.0F, -16.0F, -7.0F, 8.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(56, 21).addBox(-4.0F, -15.0F, -8.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(56, 23).addBox(-4.0F, -8.0F, -8.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-5.0F, -15.0F, -7.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(-4.0F, -6.0F, -6.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 2.0F));

        PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(38, 47).addBox(-2.0F, -6.0F, 7.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(40, 16).addBox(-4.0F, -12.0F, -6.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, -10.0F, -2.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(18, 38).addBox(-4.0F, -12.0F, -6.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -10.0F, -2.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition right = kiwi.addOrReplaceChild("right", CubeListBuilder.create().texOffs(34, 55).addBox(-1.0F, -6.0F, -1.0F, 1.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -6.0F, -1.0F));

        PartDefinition left = kiwi.addOrReplaceChild("left", CubeListBuilder.create().texOffs(0, 54).addBox(0.0F, -6.0F, -1.0F, 1.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -6.0F, -1.0F));

        PartDefinition head = kiwi.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 29).addBox(-3.0F, -5.0F, -5.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(18, 49).addBox(-2.0F, -5.0F, -4.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(46, 60).addBox(2.0F, -6.0F, -4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(56, 60).addBox(-3.0F, -6.0F, -4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(58, 5).addBox(3.0F, -5.0F, -4.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(58, 38).addBox(-4.0F, -5.0F, -4.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(38, 40).addBox(-2.0F, -6.0F, -5.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(22, 59).addBox(-2.0F, -5.0F, 1.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(56, 16).addBox(-2.0F, -7.0F, -4.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(12, 59).addBox(-2.0F, -5.0F, -6.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(12, 54).addBox(-1.0F, -4.0F, -7.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(62, 55).addBox(-1.0F, -3.0F, -9.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, -5.0F));

        PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(20, 64).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -10.9F, 0.1309F, 0.0F, 0.0F));

        PartDefinition cube_r5 = head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(12, 64).addBox(-1.0F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -0.8F, -12.6F, 0.3491F, 0.0F, 0.0F));

        PartDefinition cube_r6 = head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(58, 13).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -9.0F, 0.0436F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);

    }

    @Override
    public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(p_102622_, p_102623_, p_102621_);
        if(p_102618_.walkAnimation.isMoving()) {
            this.animateWalk(ModAnimationDefinitions.KIWI_WALK, p_102619_, p_102620_, 2f, 2.5f);
        } else {
            this.animate(p_102618_.idleAnimationState, ModAnimationDefinitions.KIWI_IDLE, p_102621_, 1f);
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
        kiwi.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return kiwi;
    }

}