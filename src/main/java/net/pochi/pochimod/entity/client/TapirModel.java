package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.Tapir;

public class TapirModel<T extends Tapir> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    //public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "ant"), "main");
    private final ModelPart tapiridae;
    private final ModelPart body;
    private final ModelPart torso;
    private final ModelPart head;
    private final ModelPart nose;
    private final ModelPart n1;
    private final ModelPart n2;
    private final ModelPart n3;
    private final ModelPart lefthand;
    private final ModelPart rightleg;
    private final ModelPart righthand;
    private final ModelPart leftleg;

    public TapirModel(ModelPart root) {
        this.tapiridae = root.getChild("tapiridae");
        this.body = this.tapiridae.getChild("body");
        this.torso = this.body.getChild("torso");
        this.head = this.body.getChild("head");
        this.nose = this.head.getChild("nose");
        this.n1 = this.nose.getChild("n1");
        this.n2 = this.n1.getChild("n2");
        this.n3 = this.n2.getChild("n3");
        this.lefthand = this.body.getChild("lefthand");
        this.rightleg = this.body.getChild("rightleg");
        this.righthand = this.body.getChild("righthand");
        this.leftleg = this.body.getChild("leftleg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition tapiridae = partdefinition.addOrReplaceChild("tapiridae", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = tapiridae.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(42, 44).addBox(-5.0F, -11.0F, -9.0F, 10.0F, 10.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 22).addBox(-6.0F, -12.0F, -2.0F, 12.0F, 11.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(48, 61).addBox(-5.0F, -10.0F, -14.0F, 10.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(24, 61).addBox(6.0F, -11.0F, -2.0F, 1.0F, 10.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(0, 56).addBox(-7.0F, -11.0F, -2.0F, 1.0F, 10.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(78, 56).addBox(5.0F, -10.0F, -9.0F, 1.0F, 9.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(80, 17).addBox(-6.0F, -10.0F, -9.0F, 1.0F, 9.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(96, 13).addBox(5.0F, -9.0F, -13.0F, 1.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(96, 25).addBox(-6.0F, -9.0F, -13.0F, 1.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-5.0F, -1.0F, -12.0F, 10.0F, 1.0F, 21.0F, new CubeDeformation(0.0F))
                .texOffs(76, 45).addBox(-5.0F, -11.0F, 9.0F, 10.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(96, 95).addBox(-3.0F, -9.0F, 11.0F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(70, 83).addBox(-4.0F, -10.0F, 9.0F, 8.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 44).addBox(-5.0F, -13.0F, -2.0F, 10.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(70, 75).addBox(-4.0F, -12.0F, -9.0F, 8.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(46, 37).addBox(-4.0F, -11.0F, -14.0F, 8.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 2.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(46, 22).addBox(-5.0F, -7.0F, -7.0F, 10.0F, 8.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(76, 37).addBox(-4.0F, -8.0F, -7.0F, 8.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(84, 0).addBox(5.0F, -6.0F, -7.0F, 1.0F, 6.0F, 7.0F, new CubeDeformation(-0.002F))
                .texOffs(0, 77).addBox(-4.0F, -7.0F, -10.0F, 8.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 101).addBox(4.0F, -6.0F, -10.0F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(24, 56).addBox(-3.0F, -8.0F, -10.0F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 88).addBox(-6.0F, -6.0F, -7.0F, 1.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(8, 101).addBox(-5.0F, -6.0F, -10.0F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(80, 103).addBox(4.0F, -11.0F, -4.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(90, 103).addBox(-7.0F, -11.0F, -4.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.0F, -10.0F));

        PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -7.0F, -10.0F, 0.2618F, 0.0F, 0.0F));

        PartDefinition n1 = nose.addOrReplaceChild("n1", CubeListBuilder.create().texOffs(98, 45).addBox(3.0F, 0.9659F, -5.2588F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(94, 56).addBox(-2.0F, -1.0341F, -5.2588F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(22, 82).addBox(-3.0F, -0.0341F, -5.2588F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(100, 0).addBox(-4.0F, 0.9659F, -5.2588F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

        PartDefinition n2 = n1.addOrReplaceChild("n2", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, -5.0F));

        PartDefinition cube_r1 = n2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(80, 95).addBox(-2.0F, 0.887F, -4.4617F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition n3 = n2.addOrReplaceChild("n3", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, -4.0F));

        PartDefinition cube_r2 = n3.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(100, 74).addBox(-1.0F, 0.6828F, -4.4871F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition lefthand = body.addOrReplaceChild("lefthand", CubeListBuilder.create().texOffs(90, 83).addBox(3.0F, 3.0F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.002F))
                .texOffs(78, 72).addBox(3.0F, 9.0F, -2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -11.0F, -8.0F));

        PartDefinition cube_r3 = lefthand.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(94, 63).addBox(-3.0F, -1.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -2.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition rightleg = body.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(62, 17).addBox(-8.0F, 11.0F, -4.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 92).addBox(-8.0F, 5.0F, -3.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 11.0F));

        PartDefinition cube_r4 = rightleg.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(62, 0).addBox(-2.0F, -5.0F, -2.0F, 5.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 0.0F, -2.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition righthand = body.addOrReplaceChild("righthand", CubeListBuilder.create().texOffs(84, 13).addBox(-7.0F, 9.0F, -3.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(48, 92).addBox(-7.0F, 3.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -11.0F, -7.0F));

        PartDefinition cube_r5 = righthand.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(64, 94).addBox(-1.0F, -1.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -2.0F, -1.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition leftleg = body.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(32, 92).addBox(4.0F, 5.0F, -3.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.002F))
                .texOffs(80, 33).addBox(4.0F, 11.0F, -4.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 11.0F));

        PartDefinition cube_r6 = leftleg.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(48, 75).addBox(-3.0F, -5.0F, -2.0F, 5.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 0.0F, -2.0F, 0.2182F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);

    }

    @Override
    public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(p_102622_, p_102623_, p_102621_);
        if(p_102618_.walkAnimation.isMoving()) {
            this.animateWalk(ModAnimationDefinitions.TAPIR_WALK, p_102619_, p_102620_, 2f, 2.5f);
        } else {
            this.animate(p_102618_.idleAnimationState, ModAnimationDefinitions.TAPIR_IDLE, p_102621_, 1f);
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
        tapiridae.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return tapiridae;
    }
}