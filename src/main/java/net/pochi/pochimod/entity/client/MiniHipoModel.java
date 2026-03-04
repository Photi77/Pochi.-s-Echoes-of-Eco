package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.MiniHipo;

public class MiniHipoModel<T extends MiniHipo> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    //public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "minihipo"), "main");
    private final ModelPart minihipo;
    private final ModelPart head;
    private final ModelPart rightear;
    private final ModelPart leftear;
    private final ModelPart frontleftt;
    private final ModelPart rearleft;
    private final ModelPart rearright;
    private final ModelPart frontright;
    private final ModelPart body;
    private final ModelPart tail;

    public MiniHipoModel(ModelPart root) {
        this.minihipo = root.getChild("minihipo");
        this.head = minihipo.getChild("head");
        this.rightear = head.getChild("rightear");
        this.leftear = head.getChild("leftear");
        this.frontleftt = minihipo.getChild("frontleftt");
        this.rearleft = minihipo.getChild("rearleft");
        this.rearright = minihipo.getChild("rearright");
        this.frontright = minihipo.getChild("frontright");
        this.body = minihipo.getChild("body");
        this.tail = body.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition minihipo = partdefinition.addOrReplaceChild("minihipo", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head = minihipo.addOrReplaceChild("head", CubeListBuilder.create().texOffs(58, 82).addBox(-3.0F, -2.2575F, -18.7667F, 6.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(88, 16).addBox(-3.0F, -4.2575F, -16.7667F, 6.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(100, 65).addBox(-3.0F, -5.2575F, -15.7667F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(84, 29).addBox(-3.0F, -3.2575F, -17.7667F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(50, 109).addBox(-2.0F, -4.2575F, -11.7667F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(14, 105).addBox(-3.0F, -3.2575F, -11.7667F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 95).addBox(3.0F, -2.2575F, -11.7667F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 84).addBox(-2.0F, 2.7425F, -17.7667F, 4.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(0, 74).addBox(-3.0F, 0.7425F, -18.7667F, 6.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 104).addBox(3.0F, -2.2575F, -17.7667F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(66, 109).addBox(3.0F, -3.2575F, -16.7667F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(90, 78).addBox(3.0F, -4.2575F, -15.7667F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(108, 29).addBox(-5.0F, -2.2575F, -16.7667F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(108, 40).addBox(-4.0F, -3.2575F, -16.7667F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(104, 7).addBox(-4.0F, -4.2575F, -15.7667F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(104, 98).addBox(-4.0F, -2.2575F, -17.7667F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(40, 109).addBox(4.0F, -2.2575F, -16.7667F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(84, 23).addBox(-5.0F, -5.2575F, -8.7667F, 10.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(54, 23).addBox(-3.0F, -5.2575F, -9.7667F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(88, 11).addBox(-4.0F, -6.2575F, -8.7667F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(74, 45).addBox(-6.0F, -4.2575F, -8.7667F, 12.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(26, 84).addBox(-5.0F, -4.2575F, -4.7667F, 10.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(86, 0).addBox(-3.0F, -3.2575F, -2.7667F, 6.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(76, 102).addBox(3.0F, -2.2575F, -2.7667F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(90, 69).addBox(-4.0F, -4.2575F, -10.7667F, 8.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(50, 84).addBox(4.0F, -3.2575F, -10.7667F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(14, 110).addBox(-5.0F, -3.2575F, -10.7667F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(110, 75).addBox(-4.0F, -2.2575F, -11.7667F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(60, 109).addBox(-4.0F, -2.2575F, -2.7667F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -18.0F, -10.0F, 0.829F, 0.0F, 0.0F));

        PartDefinition rightear = head.addOrReplaceChild("rightear", CubeListBuilder.create(), PartPose.offset(5.0F, -5.3809F, -4.5924F));

        PartDefinition cube_r1 = rightear.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(104, 107).addBox(-2.1463F, 0.9077F, 2.4212F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, 0.3216F, 0.445F, 0.681F));

        PartDefinition leftear = head.addOrReplaceChild("leftear", CubeListBuilder.create(), PartPose.offset(-5.0F, -5.3809F, -7.5924F));

        PartDefinition cube_r2 = leftear.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(28, 108).addBox(0.1463F, 0.9077F, 2.4212F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3216F, -0.445F, -0.681F));

        PartDefinition frontleftt = minihipo.addOrReplaceChild("frontleftt", CubeListBuilder.create().texOffs(106, 45).addBox(-3.0F, 3.0F, -2.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(100, 91).addBox(-3.0F, 5.0F, -3.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 94).addBox(-3.0F, -3.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -8.0F, -5.0F));

        PartDefinition cube_r3 = frontleftt.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(76, 23).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, 0.0F, -0.1745F, 0.0F));

        PartDefinition cube_r4 = frontleftt.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(110, 69).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 8.0F, 0.0F, 0.0F, 0.1745F, 0.0F));

        PartDefinition rearleft = minihipo.addOrReplaceChild("rearleft", CubeListBuilder.create().texOffs(106, 60).addBox(-1.0F, 4.0F, -2.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(44, 102).addBox(-1.0F, 6.0F, -3.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(50, 92).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -9.0F, 13.0F));

        PartDefinition cube_r5 = rearleft.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(20, 110).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.0F, 0.0F, 0.0F, 0.1745F, 0.0F));

        PartDefinition cube_r6 = rearleft.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(110, 19).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 9.0F, 0.0F, 0.0F, -0.1745F, 0.0F));

        PartDefinition rearright = minihipo.addOrReplaceChild("rearright", CubeListBuilder.create().texOffs(106, 55).addBox(-3.0F, 3.9F, -2.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(16, 95).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(104, 0).addBox(-3.0F, 5.9F, -3.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -9.0F, 13.0F));

        PartDefinition cube_r7 = rearright.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(110, 16).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.0F, 0.0F, 0.0F, -0.1745F, 0.0F));

        PartDefinition cube_r8 = rearright.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(68, 23).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 8.9F, 0.0F, 0.0F, 0.1745F, 0.0F));

        PartDefinition frontright = minihipo.addOrReplaceChild("frontright", CubeListBuilder.create().texOffs(66, 92).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(106, 50).addBox(-3.0F, 4.0F, -2.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(60, 102).addBox(-3.0F, 6.0F, -3.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -9.0F, -5.0F));

        PartDefinition cube_r9 = frontright.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(110, 72).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.0F, 0.0F, 0.0F, -0.1745F, 0.0F));

        PartDefinition cube_r10 = frontright.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(110, 36).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 9.0F, 0.0F, 0.0F, 0.1745F, 0.0F));

        PartDefinition body = minihipo.addOrReplaceChild("body", CubeListBuilder.create().texOffs(84, 40).addBox(-4.0F, -7.0F, -30.0F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(74, 58).addBox(-5.0F, -8.0F, -25.0F, 10.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 26).addBox(-6.0F, -9.0F, -18.0F, 12.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(74, 65).addBox(-5.0F, -8.0F, -6.0F, 10.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(94, 99).addBox(6.0F, -6.0F, -6.0F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(46, 45).addBox(7.0F, -7.0F, -19.0F, 1.0F, 11.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(28, 50).addBox(6.0F, -6.0F, -26.0F, 1.0F, 10.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(32, 95).addBox(5.0F, -5.0F, -31.0F, 1.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(100, 78).addBox(-7.0F, -6.0F, -6.0F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 50).addBox(-8.0F, -7.0F, -19.0F, 1.0F, 11.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(84, 82).addBox(-7.0F, -6.0F, -26.0F, 1.0F, 10.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(82, 99).addBox(-6.0F, -5.0F, -31.0F, 1.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(54, 0).addBox(-6.0F, -7.0F, -6.0F, 12.0F, 11.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-7.0F, -8.0F, -19.0F, 14.0F, 13.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(46, 26).addBox(-6.0F, -7.0F, -26.0F, 12.0F, 12.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(28, 69).addBox(-5.0F, -6.0F, -31.0F, 10.0F, 10.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(84, 36).addBox(-5.0F, 4.0F, -6.0F, 10.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(-6.0F, 5.0F, -18.0F, 12.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(54, 15).addBox(-5.0F, 5.0F, -25.0F, 10.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 20.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(110, 80).addBox(-0.5F, -6.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(110, 85).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.2F, 0.3054F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);

    }


    @Override
    public void setupAnim(MiniHipo p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(p_102622_, p_102623_, p_102621_);
        if(p_102618_.walkAnimation.isMoving()) {
            this.animateWalk(ModAnimationDefinitions.MINIHIPO_WALK, p_102619_, p_102620_, 2f, 2.5f);
        } else {
            this.animate(p_102618_.idleAnimationState, ModAnimationDefinitions.MINIHIPO_IDLE, p_102621_, 1f);
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
        minihipo.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return minihipo;
    }
}