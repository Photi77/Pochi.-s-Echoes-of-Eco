package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.Porcupine;

public class PorcupineModel<T extends Porcupine> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    //public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "ant"), "main");
    private final ModelPart hystricidae;
    private final ModelPart body;
    private final ModelPart torso;
    private final ModelPart bone2;
    private final ModelPart torsonidle;
    private final ModelPart head;
    private final ModelPart bone;
    private final ModelPart lefthand;
    private final ModelPart leftleg;
    private final ModelPart rightleg;
    private final ModelPart righthand;

    public PorcupineModel(ModelPart root) {
        this.hystricidae = root.getChild("hystricidae");
        this.body = this.hystricidae.getChild("body");
        this.torso = this.body.getChild("torso");
        this.bone2 = this.torso.getChild("bone2");
        this.torsonidle = this.torso.getChild("torsonidle");
        this.head = this.body.getChild("head");
        this.bone = this.head.getChild("bone");
        this.lefthand = this.body.getChild("lefthand");
        this.leftleg = this.body.getChild("leftleg");
        this.rightleg = this.body.getChild("rightleg");
        this.righthand = this.body.getChild("righthand");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition hystricidae = partdefinition.addOrReplaceChild("hystricidae", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = hystricidae.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.0F, 1.0F, 8.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 22).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 14).addBox(-3.0F, -8.0F, 1.0F, 6.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(20, 22).addBox(4.0F, -6.0F, 1.0F, 1.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(30, 0).addBox(-5.0F, -6.0F, 1.0F, 1.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(14, 34).addBox(-2.0F, -7.0F, -3.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 39).addBox(3.0F, -5.0F, -3.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(10, 39).addBox(-4.0F, -5.0F, -3.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 31).addBox(-3.0F, -7.0F, 8.0F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(26, 14).addBox(3.0F, -6.0F, 8.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(48, 30).addBox(-4.0F, -6.0F, 8.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(30, 34).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(36, 21).addBox(-2.0F, -6.0F, 8.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -2.0F));

        PartDefinition bone2 = torso.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r1 = bone2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(48, 56).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.9083F, -0.0117F, -0.3273F));

        PartDefinition cube_r2 = bone2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(50, 56).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, -1.0F, -0.9675F, 0.0125F, 0.3732F));

        PartDefinition cube_r3 = bone2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(56, 50).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.9083F, -0.0117F, -0.3273F));

        PartDefinition cube_r4 = bone2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 57).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(14, 57).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 1.0F, -0.9675F, 0.0125F, 0.3732F));

        PartDefinition cube_r5 = bone2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(2, 57).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(12, 57).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 1.0F, -0.9083F, -0.0117F, -0.3273F));

        PartDefinition cube_r6 = bone2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(4, 57).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, -1.0F, -1.0036F, 0.0F, 0.0F));

        PartDefinition cube_r7 = bone2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(6, 57).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 2.0F, -1.0036F, 0.0F, 0.0F));

        PartDefinition cube_r8 = bone2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(8, 57).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.9675F, 0.0125F, 0.3732F));

        PartDefinition cube_r9 = bone2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(10, 57).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, -1.0F, -0.9083F, -0.0117F, -0.3273F));

        PartDefinition cube_r10 = bone2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(16, 57).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 3.0F, -1.0036F, 0.0F, 0.0F));

        PartDefinition cube_r11 = bone2.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(18, 57).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, -0.9675F, 0.0125F, 0.3732F));

        PartDefinition torsonidle = torso.addOrReplaceChild("torsonidle", CubeListBuilder.create().texOffs(46, 10).addBox(-1.0F, -9.0F, 5.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r12 = torsonidle.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(38, 56).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -7.0F, 8.0F, -0.8933F, -0.5214F, 0.5543F));

        PartDefinition cube_r13 = torsonidle.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(32, 56).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -7.0F, 8.0F, -1.0405F, 0.6711F, -0.8147F));

        PartDefinition cube_r14 = torsonidle.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(56, 20).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -6.0F, 3.0F, -1.0405F, 0.6711F, -0.8147F));

        PartDefinition cube_r15 = torsonidle.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(20, 56).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -5.0F, 2.0F, -0.8156F, 0.7926F, -1.1932F));

        PartDefinition cube_r16 = torsonidle.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(56, 10).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -6.0F, 5.0F, -1.0405F, 0.6711F, -0.8147F));

        PartDefinition cube_r17 = torsonidle.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(56, 0).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -8.0F, 5.0F, -1.0405F, 0.6711F, -0.8147F));

        PartDefinition cube_r18 = torsonidle.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(54, 50).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -7.0F, 6.0F, -1.0405F, 0.6711F, -0.8147F));

        PartDefinition cube_r19 = torsonidle.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(54, 40).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -8.0F, 7.0F, -1.0405F, 0.6711F, -0.8147F));

        PartDefinition cube_r20 = torsonidle.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(54, 30).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -3.0F, 5.0F, -0.8156F, 0.7926F, -1.1932F));

        PartDefinition cube_r21 = torsonidle.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(54, 20).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -2.0F, 7.0F, -0.8156F, 0.7926F, -1.1932F));

        PartDefinition cube_r22 = torsonidle.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(54, 10).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -1.0F, 6.0F, -0.8156F, 0.7926F, -1.1932F));

        PartDefinition cube_r23 = torsonidle.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(54, 0).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 7.0F, -0.8156F, 0.7926F, -1.1932F));

        PartDefinition cube_r24 = torsonidle.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(38, 46).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -3.0F, 3.0F, -0.8156F, 0.7926F, -1.1932F));

        PartDefinition cube_r25 = torsonidle.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(40, 46).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -3.0F, 1.0F, -0.8156F, 0.7926F, -1.1932F));

        PartDefinition cube_r26 = torsonidle.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(50, 0).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 7.0F, -0.8156F, -0.7926F, 1.1932F));

        PartDefinition cube_r27 = torsonidle.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(50, 10).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -1.0F, 6.0F, -0.8156F, -0.7926F, 1.1932F));

        PartDefinition cube_r28 = torsonidle.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(50, 20).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -2.0F, 7.0F, -0.8156F, -0.7926F, 1.1932F));

        PartDefinition cube_r29 = torsonidle.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(50, 46).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -3.0F, 5.0F, -0.8156F, -0.7926F, 1.1932F));

        PartDefinition cube_r30 = torsonidle.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(52, 0).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -5.0F, 5.0F, -0.8156F, -0.7926F, 1.1932F));

        PartDefinition cube_r31 = torsonidle.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(52, 10).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -4.0F, 4.0F, -0.8156F, -0.7926F, 1.1932F));

        PartDefinition cube_r32 = torsonidle.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(52, 20).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -5.0F, 3.0F, -0.8156F, -0.7926F, 1.1932F));

        PartDefinition cube_r33 = torsonidle.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(52, 30).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -3.0F, 3.0F, -0.8156F, -0.7926F, 1.1932F));

        PartDefinition cube_r34 = torsonidle.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(52, 40).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -5.0F, 2.0F, -0.8156F, -0.7926F, 1.1932F));

        PartDefinition cube_r35 = torsonidle.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(52, 50).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -3.0F, 1.0F, -0.8156F, -0.7926F, 1.1932F));

        PartDefinition cube_r36 = torsonidle.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(24, 46).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -6.0F, 3.0F, -0.8933F, -0.5214F, 0.5543F));

        PartDefinition cube_r37 = torsonidle.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(26, 46).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -8.0F, 4.0F, -0.8933F, -0.5214F, 0.5543F));

        PartDefinition cube_r38 = torsonidle.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(4, 47).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -6.0F, 7.0F, -0.8933F, -0.5214F, 0.5543F));

        PartDefinition cube_r39 = torsonidle.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(28, 46).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -6.0F, 5.0F, -0.8933F, -0.5214F, 0.5543F));

        PartDefinition cube_r40 = torsonidle.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(48, 10).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -2.0F, 10.0F, -0.8933F, -0.5214F, 0.5543F));

        PartDefinition cube_r41 = torsonidle.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(48, 20).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -5.0F, 10.0F, -1.0405F, 0.6711F, -0.8147F));

        PartDefinition cube_r42 = torsonidle.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(48, 46).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -3.0F, 10.0F, -1.0405F, 0.6711F, -0.8147F));

        PartDefinition cube_r43 = torsonidle.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(10, 47).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 10.0F, -0.7418F, 0.0F, 0.0F));

        PartDefinition cube_r44 = torsonidle.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(14, 47).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -2.0F, 9.0F, -1.0405F, 0.6711F, -0.8147F));

        PartDefinition cube_r45 = torsonidle.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(12, 47).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -4.0F, 9.0F, -1.0405F, 0.6711F, -0.8147F));

        PartDefinition cube_r46 = torsonidle.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(18, 47).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -4.0F, 8.0F, -0.8933F, -0.5214F, 0.5543F));

        PartDefinition cube_r47 = torsonidle.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(48, 0).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -3.0F, 9.0F, -0.8933F, -0.5214F, 0.5543F));

        PartDefinition cube_r48 = torsonidle.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(16, 47).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -5.0F, 9.0F, -0.8933F, -0.5214F, 0.5543F));

        PartDefinition cube_r49 = torsonidle.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(8, 47).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 9.0F, -0.7418F, 0.0F, 0.0F));

        PartDefinition cube_r50 = torsonidle.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(6, 47).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -6.0F, 9.0F, -1.0405F, 0.6711F, -0.8147F));

        PartDefinition cube_r51 = torsonidle.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(44, 46).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -5.0F, 5.0F, -0.8156F, 0.7926F, -1.1932F));

        PartDefinition cube_r52 = torsonidle.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(46, 46).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -8.0F, 8.0F, -0.7418F, 0.0F, 0.0F));

        PartDefinition cube_r53 = torsonidle.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(0, 47).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 7.0F, -0.8933F, -0.5214F, 0.5543F));

        PartDefinition cube_r54 = torsonidle.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(2, 47).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -8.0F, 7.0F, -0.8933F, -0.5214F, 0.5543F));

        PartDefinition cube_r55 = torsonidle.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(32, 46).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -5.0F, 3.0F, -0.8156F, 0.7926F, -1.1932F));

        PartDefinition cube_r56 = torsonidle.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(34, 46).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -8.0F, 6.0F, -0.7418F, 0.0F, 0.0F));

        PartDefinition cube_r57 = torsonidle.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(36, 46).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -8.0F, 6.0F, -0.8933F, -0.5214F, 0.5543F));

        PartDefinition cube_r58 = torsonidle.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(46, 28).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -8.0F, 5.0F, -0.8933F, -0.5214F, 0.5543F));

        PartDefinition cube_r59 = torsonidle.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(42, 46).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -4.0F, 4.0F, -0.8156F, 0.7926F, -1.1932F));

        PartDefinition cube_r60 = torsonidle.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(30, 46).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -7.0F, 6.0F, -0.8933F, -0.5214F, 0.5543F));

        PartDefinition cube_r61 = torsonidle.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(20, 46).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -8.0F, 4.0F, -1.0405F, 0.6711F, -0.8147F));

        PartDefinition cube_r62 = torsonidle.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(22, 46).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 3.0F, -0.8933F, -0.5214F, 0.5543F));

        PartDefinition cube_r63 = torsonidle.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(46, 0).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 5.0F, -0.8933F, -0.5214F, 0.5543F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(30, 12).addBox(-2.5F, -3.0F, -3.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(-0.003F))
                .texOffs(36, 28).addBox(-2.0F, -2.0F, -5.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -6.0F, -5.0F, 0.3054F, 0.0F, 0.0F));

        PartDefinition bone = head.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(-0.5F, -2.0F, -6.0F));

        PartDefinition cube_r64 = bone.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(46, 56).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 5.0F, -0.9083F, -0.0117F, -0.3273F));

        PartDefinition cube_r65 = bone.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(44, 56).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, 4.0F, -0.9675F, 0.0125F, 0.3732F));

        PartDefinition cube_r66 = bone.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(42, 56).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 3.0F, -0.9083F, -0.0117F, -0.3273F));

        PartDefinition cube_r67 = bone.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(56, 40).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(26, 56).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, 6.0F, -0.9675F, 0.0125F, 0.3732F));

        PartDefinition cube_r68 = bone.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(40, 56).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(28, 56).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, 6.0F, -0.9083F, -0.0117F, -0.3273F));

        PartDefinition cube_r69 = bone.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(36, 56).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, 4.0F, -1.0036F, 0.0F, 0.0F));

        PartDefinition cube_r70 = bone.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(34, 56).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, 7.0F, -1.0036F, 0.0F, 0.0F));

        PartDefinition cube_r71 = bone.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(56, 30).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 5.0F, -0.9675F, 0.0125F, 0.3732F));

        PartDefinition cube_r72 = bone.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(30, 56).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, 4.0F, -0.9083F, -0.0117F, -0.3273F));

        PartDefinition cube_r73 = bone.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(24, 56).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, 8.0F, -1.0036F, 0.0F, 0.0F));

        PartDefinition cube_r74 = bone.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(22, 56).addBox(0.0F, -10.0F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 7.0F, -0.9675F, 0.0125F, 0.3732F));

        PartDefinition lefthand = body.addOrReplaceChild("lefthand", CubeListBuilder.create().texOffs(20, 39).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.003F))
                .texOffs(58, 0).addBox(-1.0F, 3.0F, -2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -4.0F, -3.0F));

        PartDefinition leftleg = body.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(36, 39).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.003F))
                .texOffs(58, 2).addBox(-1.0F, 3.0F, -2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -4.0F, 4.0F));

        PartDefinition rightleg = body.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(28, 39).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.003F))
                .texOffs(14, 31).addBox(-1.0F, 3.0F, -2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -4.0F, 4.0F));

        PartDefinition righthand = body.addOrReplaceChild("righthand", CubeListBuilder.create().texOffs(44, 39).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.003F))
                .texOffs(58, 4).addBox(-1.0F, 3.0F, -2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -4.0F, -3.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);

    }

    @Override
    public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(p_102622_, p_102623_, p_102621_);
        if(p_102618_.walkAnimation.isMoving()){
            this.animateWalk(ModAnimationDefinitions.PORCUPINE_WALK, p_102619_, p_102620_, 2f, 2.5f);
        } else {
            this.animate(p_102618_.idleAnimationState, ModAnimationDefinitions.PORCUPINE_IDLE, p_102621_, 1f);
        }
        this.animate(p_102618_.attackAnimationState, ModAnimationDefinitions.PORCUPINE_ATTACH, p_102621_, 1f);
    }

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        hystricidae.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return hystricidae;
    }
}