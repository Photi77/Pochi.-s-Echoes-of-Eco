package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.Pangolin;

public class PangolinModel<T extends Pangolin> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    //public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "ant"), "main");
    private final ModelPart pangolin;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart t1;
    private final ModelPart t2;
    private final ModelPart t3;
    private final ModelPart neck;
    private final ModelPart n2;
    private final ModelPart n1;
    private final ModelPart head;
    private final ModelPart torso;
    private final ModelPart righthand;
    private final ModelPart rightleg;
    private final ModelPart lefthand;
    private final ModelPart leftleg;
    private final ModelPart back;

    public PangolinModel(ModelPart root) {
        this.pangolin = root.getChild("pangolin");
        this.body = this.pangolin.getChild("body");
        this.tail = this.body.getChild("tail");
        this.t1 = this.tail.getChild("t1");
        this.t2 = this.t1.getChild("t2");
        this.t3 = this.t2.getChild("t3");
        this.neck = this.body.getChild("neck");
        this.n2 = this.neck.getChild("n2");
        this.n1 = this.neck.getChild("n1");
        this.head = this.neck.getChild("head");
        this.torso = this.body.getChild("torso");
        this.righthand = this.torso.getChild("righthand");
        this.rightleg = this.torso.getChild("rightleg");
        this.lefthand = this.torso.getChild("lefthand");
        this.leftleg = this.torso.getChild("leftleg");
        this.back = this.torso.getChild("back");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition pangolin = partdefinition.addOrReplaceChild("pangolin", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = pangolin.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, 5.0F));

        PartDefinition t1 = tail.addOrReplaceChild("t1", CubeListBuilder.create().texOffs(0, 30).addBox(-1.8F, -3.0F, -0.3F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(26, 39).addBox(-0.8F, -4.0F, -0.3F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 42).addBox(2.2F, -2.0F, -0.3F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(42, 0).addBox(-2.8F, -2.0F, -0.3F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(14, 30).addBox(-1.8F, -2.0F, 2.7F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(40, 12).addBox(-0.8F, -3.0F, 2.7F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.2F, 1.0F, 0.3F));

        PartDefinition cube_r1 = t1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(84, 36).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, -1.0F, -0.7181F, 0.2544F, 0.1282F));

        PartDefinition cube_r2 = t1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(84, 32).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.2F, 0.0F, 0.4F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition cube_r3 = t1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(84, 28).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9F, 0.0F, 1.7F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition cube_r4 = t1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(48, 83).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9F, 0.0F, 2.8F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition cube_r5 = t1.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(42, 83).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4F, 0.0F, 3.9F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition cube_r6 = t1.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(66, 80).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6F, 0.7F, -1.0F, -0.7181F, -0.2544F, -0.1282F));

        PartDefinition cube_r7 = t1.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(60, 80).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3F, 0.7F, 0.4F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r8 = t1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(80, 16).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.7F, 1.7F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r9 = t1.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(80, 12).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.7F, 2.8F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r10 = t1.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(80, 8).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 0.7F, 3.9F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r11 = t1.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(86, 16).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.2F, -1.5F, -0.7F, -0.6049F, -0.2348F, 0.7039F));

        PartDefinition cube_r12 = t1.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(72, 60).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.2F, -1.5F, 0.4F, -0.6049F, -0.2348F, 0.7039F));

        PartDefinition cube_r13 = t1.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(60, 72).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.2F, -1.5F, 1.7F, -0.6049F, -0.2348F, 0.7039F));

        PartDefinition cube_r14 = t1.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(72, 56).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9F, -1.5F, 2.8F, -0.6049F, -0.2348F, 0.7039F));

        PartDefinition cube_r15 = t1.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(72, 52).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4F, -1.5F, 3.9F, -0.6049F, -0.2348F, 0.7039F));

        PartDefinition cube_r16 = t1.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(86, 12).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6F, -3.4F, -0.7F, -0.6414F, 0.0556F, -0.9543F));

        PartDefinition cube_r17 = t1.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(72, 36).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6F, -3.4F, 0.4F, -0.6414F, 0.0556F, -0.9543F));

        PartDefinition cube_r18 = t1.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(72, 32).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6F, -3.4F, 1.7F, -0.6414F, 0.0556F, -0.9543F));

        PartDefinition cube_r19 = t1.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(72, 28).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3F, -3.4F, 2.8F, -0.6414F, 0.0556F, -0.9543F));

        PartDefinition cube_r20 = t1.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(48, 71).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.8F, -3.1F, 3.9F, -0.6414F, 0.0556F, -0.9543F));

        PartDefinition cube_r21 = t1.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(68, 0).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.2F, -3.2F, -0.9F, -0.3263F, 0.1026F, 0.6851F));

        PartDefinition cube_r22 = t1.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(48, 67).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -1.5F, -1.0F, -0.3263F, 0.1026F, 0.6851F));

        PartDefinition cube_r23 = t1.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(42, 67).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2F, 0.7F, -1.0F, -0.7181F, 0.2544F, 0.1282F));

        PartDefinition cube_r24 = t1.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(0, 67).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, -3.1F, 3.9F, -0.6414F, -0.0556F, 0.9543F));

        PartDefinition cube_r25 = t1.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(66, 64).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1F, 0.7F, 3.9F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition cube_r26 = t1.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(48, 63).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.9F, 0.0F, -1.0F, -0.7181F, -0.2544F, -0.1282F));

        PartDefinition cube_r27 = t1.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(54, 85).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, 0.0F, -0.7F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r28 = t1.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(60, 36).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, 0.0F, 0.4F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r29 = t1.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(60, 32).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3F, 0.0F, 1.7F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r30 = t1.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(60, 28).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3F, 0.0F, 2.8F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r31 = t1.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(48, 59).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8F, 0.0F, 3.9F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r32 = t1.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(6, 59).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8F, -1.5F, 3.9F, -0.6049F, 0.2348F, -0.7039F));

        PartDefinition cube_r33 = t1.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(0, 59).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3F, -1.5F, 2.8F, -0.6049F, 0.2348F, -0.7039F));

        PartDefinition cube_r34 = t1.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(58, 40).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, -1.5F, 1.7F, -0.6049F, 0.2348F, -0.7039F));

        PartDefinition cube_r35 = t1.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(58, 24).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, -1.5F, 0.4F, -0.6049F, 0.2348F, -0.7039F));

        PartDefinition cube_r36 = t1.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(84, 84).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, -1.5F, -0.7F, -0.6049F, 0.2348F, -0.7039F));

        PartDefinition cube_r37 = t1.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(56, 8).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9F, -3.4F, 2.8F, -0.6414F, -0.0556F, 0.9543F));

        PartDefinition cube_r38 = t1.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(56, 4).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.2F, -3.4F, 1.7F, -0.6414F, -0.0556F, 0.9543F));

        PartDefinition cube_r39 = t1.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(56, 0).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.2F, -3.4F, 0.4F, -0.6414F, -0.0556F, 0.9543F));

        PartDefinition cube_r40 = t1.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(84, 80).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.2F, -3.4F, -0.7F, -0.6414F, -0.0556F, 0.9543F));

        PartDefinition cube_r41 = t1.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(84, 76).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.2F, 0.0F, -0.7F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition cube_r42 = t1.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(6, 55).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9F, 0.7F, 0.4F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition cube_r43 = t1.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(0, 55).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.6F, 0.7F, 1.7F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition cube_r44 = t1.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(54, 53).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.6F, 0.7F, 2.8F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition t2 = t1.addOrReplaceChild("t2", CubeListBuilder.create().texOffs(28, 30).addBox(-0.9F, 0.5F, 2.5F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(42, 5).addBox(-0.9F, -0.5F, 0.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.1F, -2.0F, 5.2F));

        PartDefinition cube_r45 = t2.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(36, 83).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 2.0F, 0.0F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition cube_r46 = t2.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(30, 83).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4F, 2.0F, 1.2F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition cube_r47 = t2.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(80, 4).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4F, 2.7F, 0.0F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r48 = t2.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(80, 0).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3F, 2.7F, 1.2F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r49 = t2.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(74, 8).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9F, 2.0F, 5.0F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r50 = t2.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(72, 48).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.5F, 0.0F, -0.6049F, -0.2348F, 0.7039F));

        PartDefinition cube_r51 = t2.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(72, 44).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.5F, 1.2F, -0.6049F, -0.2348F, 0.7039F));

        PartDefinition cube_r52 = t2.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(42, 71).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, -0.8F, 0.0F, -0.6414F, 0.0556F, -0.9543F));

        PartDefinition cube_r53 = t2.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(36, 71).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, -0.4F, 1.2F, -0.6414F, 0.0556F, -0.9543F));

        PartDefinition cube_r54 = t2.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(36, 67).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8F, 2.0F, 2.2F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition cube_r55 = t2.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(30, 67).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1F, 2.7F, 1.2F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition cube_r56 = t2.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(24, 67).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.2F, 2.7F, 0.0F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition cube_r57 = t2.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(18, 67).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -0.8F, 0.0F, -0.6414F, -0.0556F, 0.9543F));

        PartDefinition cube_r58 = t2.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(12, 67).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9F, 0.2F, 2.2F, -0.8897F, 0.001F, 0.8998F));

        PartDefinition cube_r59 = t2.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(6, 67).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -0.4F, 1.2F, -0.6414F, -0.0556F, 0.9543F));

        PartDefinition cube_r60 = t2.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(66, 32).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3F, 0.3F, 4.2F, -0.6276F, -0.1527F, -1.2352F));

        PartDefinition cube_r61 = t2.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(66, 28).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9F, 2.0F, 3.7F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r62 = t2.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(42, 59).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7F, 2.0F, 0.0F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r63 = t2.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(36, 59).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6F, 2.0F, 1.2F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r64 = t2.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(30, 59).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 2.0F, 2.2F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r65 = t2.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(24, 59).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, 0.2F, 2.2F, -0.8897F, -0.001F, -0.8998F));

        PartDefinition cube_r66 = t2.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(18, 59).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7F, 0.5F, 1.2F, -0.6049F, 0.2348F, -0.7039F));

        PartDefinition cube_r67 = t2.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(12, 59).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7F, 0.5F, 0.0F, -0.6049F, 0.2348F, -0.7039F));

        PartDefinition cube_r68 = t2.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(56, 12).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1F, 0.3F, 4.2F, -0.6276F, 0.1527F, 1.2352F));

        PartDefinition cube_r69 = t2.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(54, 49).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, 2.0F, 3.7F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition cube_r70 = t2.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(54, 45).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, 2.0F, 5.0F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition t3 = t2.addOrReplaceChild("t3", CubeListBuilder.create().texOffs(0, 37).addBox(-0.6F, 0.0F, 0.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.2F, 1.0F, 6.5F));

        PartDefinition cube_r71 = t3.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(18, 75).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 2.0F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r72 = t3.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(12, 75).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.7F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r73 = t3.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(6, 75).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 1.0F, -0.3F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r74 = t3.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(0, 75).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, -0.5F, 0.7F, -0.6276F, -0.1527F, -1.2352F));

        PartDefinition cube_r75 = t3.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(74, 16).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, 0.0F, 2.2F, -0.6276F, 0.1527F, 1.2352F));

        PartDefinition cube_r76 = t3.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(74, 12).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, -0.5F, -0.5F, -0.6276F, -0.1527F, -1.2352F));

        PartDefinition cube_r77 = t3.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(74, 4).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 1.0F, 7.0F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition cube_r78 = t3.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(74, 0).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 1.0F, 5.7F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition cube_r79 = t3.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(54, 73).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 1.0F, 4.7F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition cube_r80 = t3.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(72, 72).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6F, 0.1F, 5.7F, -0.6276F, 0.1527F, 1.2352F));

        PartDefinition cube_r81 = t3.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(72, 68).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.4F, 7.2F, -0.6276F, -0.1527F, -1.2352F));

        PartDefinition cube_r82 = t3.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(66, 72).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6F, 0.1F, 4.5F, -0.6276F, 0.1527F, 1.2352F));

        PartDefinition cube_r83 = t3.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(72, 64).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2F, 1.0F, 3.5F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition cube_r84 = t3.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(68, 8).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, 1.0F, 5.7F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r85 = t3.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(68, 4).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2F, 1.0F, 2.0F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition cube_r86 = t3.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(66, 60).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, 1.0F, 7.0F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r87 = t3.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(66, 56).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 1.0F, 4.7F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r88 = t3.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(66, 52).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 3.5F, -0.773F, 0.1785F, -0.1261F));

        PartDefinition cube_r89 = t3.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(66, 48).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, 0.1F, 4.5F, -0.6276F, -0.1527F, -1.2352F));

        PartDefinition cube_r90 = t3.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(66, 44).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 2.2F, -0.6276F, -0.1527F, -1.2352F));

        PartDefinition cube_r91 = t3.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(66, 36).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, 0.1F, 5.7F, -0.6276F, -0.1527F, -1.2352F));

        PartDefinition cube_r92 = t3.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(58, 20).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, 0.4F, 7.2F, -0.6276F, 0.1527F, 1.2352F));

        PartDefinition cube_r93 = t3.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(54, 57).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3F, -0.5F, 0.7F, -0.6276F, 0.1527F, 1.2352F));

        PartDefinition cube_r94 = t3.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(56, 16).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3F, -0.5F, -0.5F, -0.6276F, 0.1527F, 1.2352F));

        PartDefinition cube_r95 = t3.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(54, 36).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8F, 1.0F, -0.3F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition cube_r96 = t3.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(54, 32).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2F, 1.0F, 0.7F, -0.773F, -0.1785F, 0.1261F));

        PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(1.5F, -2.0F, -13.0F));

        PartDefinition n2 = neck.addOrReplaceChild("n2", CubeListBuilder.create().texOffs(38, 36).addBox(-1.1F, -1.4F, 0.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, -2.6F, 1.0F));

        PartDefinition cube_r97 = n2.addOrReplaceChild("cube_r97", CubeListBuilder.create().texOffs(48, 79).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r98 = n2.addOrReplaceChild("cube_r98", CubeListBuilder.create().texOffs(42, 79).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 0.8F, -1.0F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r99 = n2.addOrReplaceChild("cube_r99", CubeListBuilder.create().texOffs(36, 79).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8F, 1.3F, 0.0F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r100 = n2.addOrReplaceChild("cube_r100", CubeListBuilder.create().texOffs(30, 79).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.3F, -1.5F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r101 = n2.addOrReplaceChild("cube_r101", CubeListBuilder.create().texOffs(24, 79).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2F, 1.3F, -1.5F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r102 = n2.addOrReplaceChild("cube_r102", CubeListBuilder.create().texOffs(18, 79).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r103 = n2.addOrReplaceChild("cube_r103", CubeListBuilder.create().texOffs(12, 79).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.8F, 0.0F, 0.0F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r104 = n2.addOrReplaceChild("cube_r104", CubeListBuilder.create().texOffs(78, 76).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6F, 1.3F, 0.0F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r105 = n2.addOrReplaceChild("cube_r105", CubeListBuilder.create().texOffs(78, 72).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8F, 0.0F, 0.0F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r106 = n2.addOrReplaceChild("cube_r106", CubeListBuilder.create().texOffs(62, 0).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, 0.8F, -1.0F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r107 = n2.addOrReplaceChild("cube_r107", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -3.0F, 0.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(0.9F, 2.6F, -1.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition n1 = neck.addOrReplaceChild("n1", CubeListBuilder.create().texOffs(48, 37).addBox(-1.3F, -2.0F, 2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(48, 47).addBox(3.7F, -2.0F, 2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(44, 26).addBox(0.7F, -4.0F, 2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(32, 20).addBox(-0.3F, -3.0F, 2.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(44, 41).addBox(0.7F, -4.0F, 2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(3.7F, -2.0F, 2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(50, 4).addBox(-1.3F, -2.0F, 2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(14, 36).addBox(-0.3F, -2.0F, 0.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(44, 44).addBox(0.7F, -3.0F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.2F, -2.0F, 3.0F));

        PartDefinition cube_r108 = n1.addOrReplaceChild("cube_r108", CubeListBuilder.create().texOffs(84, 72).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r109 = n1.addOrReplaceChild("cube_r109", CubeListBuilder.create().texOffs(72, 84).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, 0.0F, 1.5F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r110 = n1.addOrReplaceChild("cube_r110", CubeListBuilder.create().texOffs(24, 83).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1F, 0.7F, 0.0F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r111 = n1.addOrReplaceChild("cube_r111", CubeListBuilder.create().texOffs(18, 83).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 0.7F, 1.5F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r112 = n1.addOrReplaceChild("cube_r112", CubeListBuilder.create().texOffs(24, 47).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.7F, 2.7F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r113 = n1.addOrReplaceChild("cube_r113", CubeListBuilder.create().texOffs(6, 79).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3F, 0.7F, 0.0F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r114 = n1.addOrReplaceChild("cube_r114", CubeListBuilder.create().texOffs(0, 79).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r115 = n1.addOrReplaceChild("cube_r115", CubeListBuilder.create().texOffs(78, 52).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.7F, 1.5F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r116 = n1.addOrReplaceChild("cube_r116", CubeListBuilder.create().texOffs(78, 48).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, -1.5F, 1.5F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r117 = n1.addOrReplaceChild("cube_r117", CubeListBuilder.create().texOffs(78, 44).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9F, -2.4F, 1.6F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r118 = n1.addOrReplaceChild("cube_r118", CubeListBuilder.create().texOffs(78, 36).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.4F, 0.0F, 0.0F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r119 = n1.addOrReplaceChild("cube_r119", CubeListBuilder.create().texOffs(78, 32).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.4F, -1.5F, 0.0F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r120 = n1.addOrReplaceChild("cube_r120", CubeListBuilder.create().texOffs(78, 28).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, -1.8F, 0.1F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r121 = n1.addOrReplaceChild("cube_r121", CubeListBuilder.create().texOffs(54, 77).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.8F, 0.0F, 1.5F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r122 = n1.addOrReplaceChild("cube_r122", CubeListBuilder.create().texOffs(72, 76).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.1F, -1.5F, 1.5F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r123 = n1.addOrReplaceChild("cube_r123", CubeListBuilder.create().texOffs(66, 76).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -2.4F, 1.6F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r124 = n1.addOrReplaceChild("cube_r124", CubeListBuilder.create().texOffs(6, 47).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.3F, 0.0F, 2.7F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r125 = n1.addOrReplaceChild("cube_r125", CubeListBuilder.create().texOffs(0, 47).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.3F, -1.5F, 2.7F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r126 = n1.addOrReplaceChild("cube_r126", CubeListBuilder.create().texOffs(30, 71).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.8F, -1.8F, 0.1F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition cube_r127 = head.addOrReplaceChild("cube_r127", CubeListBuilder.create().texOffs(36, 87).addBox(-2.0F, -2.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(26, 43).addBox(-2.5F, -3.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, -9.0F));

        PartDefinition cube_r128 = torso.addOrReplaceChild("cube_r128", CubeListBuilder.create().texOffs(64, 20).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.7F, -3.5F, 13.3F, -0.3263F, -0.1026F, -0.6851F));

        PartDefinition cube_r129 = torso.addOrReplaceChild("cube_r129", CubeListBuilder.create().texOffs(64, 24).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4F, -5.2F, 13.4F, -0.3263F, -0.1026F, -0.6851F));

        PartDefinition righthand = torso.addOrReplaceChild("righthand", CubeListBuilder.create().texOffs(40, 29).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -1.0F, 5.0F));

        PartDefinition cube_r130 = righthand.addOrReplaceChild("cube_r130", CubeListBuilder.create().texOffs(40, 87).addBox(-1.0F, 1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1305F, 1.4001F, 0.0705F, -0.3957F, -0.1209F, 0.0503F));

        PartDefinition cube_r131 = righthand.addOrReplaceChild("cube_r131", CubeListBuilder.create().texOffs(10, 37).addBox(-1.0F, 1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1305F, 1.4383F, 0.0462F, -0.3957F, 0.1209F, -0.0503F));

        PartDefinition cube_r132 = righthand.addOrReplaceChild("cube_r132", CubeListBuilder.create().texOffs(18, 87).addBox(-1.0F, 1.0F, -3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 2.1F, 1.4F, -0.746F, -0.0964F, 0.0887F));

        PartDefinition cube_r133 = righthand.addOrReplaceChild("cube_r133", CubeListBuilder.create().texOffs(12, 87).addBox(-1.0F, 1.0F, -3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.1F, 1.4F, -0.746F, 0.0964F, -0.0887F));

        PartDefinition rightleg = torso.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(18, 41).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -1.0F, 12.0F));

        PartDefinition cube_r134 = rightleg.addOrReplaceChild("cube_r134", CubeListBuilder.create().texOffs(42, 10).addBox(-1.0F, 1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1305F, 0.9383F, 0.2462F, 0.0F, 0.1309F, 0.0F));

        PartDefinition cube_r135 = rightleg.addOrReplaceChild("cube_r135", CubeListBuilder.create().texOffs(48, 87).addBox(-1.0F, 1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1305F, 0.9001F, 0.2705F, 0.0F, -0.1309F, 0.0F));

        PartDefinition lefthand = torso.addOrReplaceChild("lefthand", CubeListBuilder.create().texOffs(10, 41).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -1.0F, 5.0F));

        PartDefinition cube_r136 = lefthand.addOrReplaceChild("cube_r136", CubeListBuilder.create().texOffs(10, 39).addBox(0.0F, 1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1305F, 1.4383F, 0.0462F, -0.3957F, -0.1209F, 0.0503F));

        PartDefinition cube_r137 = lefthand.addOrReplaceChild("cube_r137", CubeListBuilder.create().texOffs(44, 87).addBox(0.0F, 1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1305F, 1.4001F, 0.0705F, -0.3957F, 0.1209F, -0.0503F));

        PartDefinition cube_r138 = lefthand.addOrReplaceChild("cube_r138", CubeListBuilder.create().texOffs(24, 87).addBox(0.0F, 1.0F, -3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 2.1F, 1.4F, -0.746F, 0.0964F, -0.0887F));

        PartDefinition cube_r139 = lefthand.addOrReplaceChild("cube_r139", CubeListBuilder.create().texOffs(30, 87).addBox(0.0F, 1.0F, -3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.1F, 1.4F, -0.746F, -0.0964F, 0.0887F));

        PartDefinition leftleg = torso.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(36, 41).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -1.0F, 12.0F));

        PartDefinition cube_r140 = leftleg.addOrReplaceChild("cube_r140", CubeListBuilder.create().texOffs(46, 10).addBox(0.0F, 1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1305F, 0.9383F, 0.2462F, 0.0F, -0.1309F, 0.0F));

        PartDefinition cube_r141 = leftleg.addOrReplaceChild("cube_r141", CubeListBuilder.create().texOffs(88, 20).addBox(0.0F, 1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1305F, 0.9001F, 0.2705F, 0.0F, 0.1309F, 0.0F));

        PartDefinition back = torso.addOrReplaceChild("back", CubeListBuilder.create().texOffs(0, 0).addBox(0.3F, -4.0F, 2.0F, 6.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 12).addBox(1.3F, -5.0F, 2.0F, 4.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(6.3F, -3.0F, 2.0F, 1.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(16, 20).addBox(-0.7F, -3.0F, 2.0F, 1.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(22, 12).addBox(2.3F, -6.0F, 2.0F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(26, 0).addBox(0.3F, -3.0F, 9.0F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(32, 26).addBox(1.3F, -4.0F, 9.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(44, 20).addBox(2.3F, -5.0F, 9.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(36, 47).addBox(6.3F, -2.0F, 9.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(42, 47).addBox(-0.7F, -2.0F, 9.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(48, 29).addBox(-0.7F, -2.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(48, 33).addBox(6.3F, -2.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(44, 23).addBox(2.3F, -5.0F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(26, 36).addBox(1.3F, -4.0F, 0.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(26, 6).addBox(0.3F, -3.0F, 0.0F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.3F, -2.0F, 3.0F));

        PartDefinition cube_r142 = back.addOrReplaceChild("cube_r142", CubeListBuilder.create().texOffs(84, 68).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r143 = back.addOrReplaceChild("cube_r143", CubeListBuilder.create().texOffs(30, 47).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, 0.0F, -1.3F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r144 = back.addOrReplaceChild("cube_r144", CubeListBuilder.create().texOffs(66, 84).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, 0.0F, 2.3F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r145 = back.addOrReplaceChild("cube_r145", CubeListBuilder.create().texOffs(84, 64).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, 0.0F, 1.2F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r146 = back.addOrReplaceChild("cube_r146", CubeListBuilder.create().texOffs(84, 60).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, 0.0F, 3.6F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r147 = back.addOrReplaceChild("cube_r147", CubeListBuilder.create().texOffs(60, 84).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, 0.0F, 4.7F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r148 = back.addOrReplaceChild("cube_r148", CubeListBuilder.create().texOffs(84, 56).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, 0.0F, 5.8F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r149 = back.addOrReplaceChild("cube_r149", CubeListBuilder.create().texOffs(84, 52).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, 0.0F, 7.1F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r150 = back.addOrReplaceChild("cube_r150", CubeListBuilder.create().texOffs(84, 48).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, 0.0F, 8.3F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r151 = back.addOrReplaceChild("cube_r151", CubeListBuilder.create().texOffs(84, 44).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, 0.0F, 9.3F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r152 = back.addOrReplaceChild("cube_r152", CubeListBuilder.create().texOffs(12, 83).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.3F, 0.7F, 0.0F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r153 = back.addOrReplaceChild("cube_r153", CubeListBuilder.create().texOffs(6, 83).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.7F, 0.7F, 2.3F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r154 = back.addOrReplaceChild("cube_r154", CubeListBuilder.create().texOffs(0, 83).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.7F, 0.7F, 1.2F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r155 = back.addOrReplaceChild("cube_r155", CubeListBuilder.create().texOffs(82, 40).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.7F, 0.7F, 3.6F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r156 = back.addOrReplaceChild("cube_r156", CubeListBuilder.create().texOffs(82, 24).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.7F, 0.7F, 4.7F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r157 = back.addOrReplaceChild("cube_r157", CubeListBuilder.create().texOffs(82, 20).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.7F, 0.7F, 5.8F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r158 = back.addOrReplaceChild("cube_r158", CubeListBuilder.create().texOffs(54, 81).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.7F, 0.7F, 7.1F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r159 = back.addOrReplaceChild("cube_r159", CubeListBuilder.create().texOffs(78, 80).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.7F, 0.7F, 8.3F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r160 = back.addOrReplaceChild("cube_r160", CubeListBuilder.create().texOffs(72, 80).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.7F, 0.7F, 9.3F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r161 = back.addOrReplaceChild("cube_r161", CubeListBuilder.create().texOffs(78, 68).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.4F, -4.6F, 1.3F, -0.8742F, 0.3215F, -1.2077F));

        PartDefinition cube_r162 = back.addOrReplaceChild("cube_r162", CubeListBuilder.create().texOffs(78, 64).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2F, -4.6F, 1.3F, -0.8742F, -0.3215F, 1.2077F));

        PartDefinition cube_r163 = back.addOrReplaceChild("cube_r163", CubeListBuilder.create().texOffs(78, 60).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2F, -4.0F, 0.0F, -0.8742F, -0.3215F, 1.2077F));

        PartDefinition cube_r164 = back.addOrReplaceChild("cube_r164", CubeListBuilder.create().texOffs(78, 56).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.4F, -4.0F, 0.0F, -0.8742F, 0.3215F, -1.2077F));

        PartDefinition cube_r165 = back.addOrReplaceChild("cube_r165", CubeListBuilder.create().texOffs(18, 47).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.7F, -1.3F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r166 = back.addOrReplaceChild("cube_r166", CubeListBuilder.create().texOffs(12, 47).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, -1.5F, -1.3F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r167 = back.addOrReplaceChild("cube_r167", CubeListBuilder.create().texOffs(6, 87).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3F, -2.7F, -1.2F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r168 = back.addOrReplaceChild("cube_r168", CubeListBuilder.create().texOffs(60, 76).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3F, 0.7F, 0.0F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r169 = back.addOrReplaceChild("cube_r169", CubeListBuilder.create().texOffs(76, 40).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r170 = back.addOrReplaceChild("cube_r170", CubeListBuilder.create().texOffs(76, 24).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3F, -3.2F, 0.1F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r171 = back.addOrReplaceChild("cube_r171", CubeListBuilder.create().texOffs(0, 87).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.3F, -2.7F, -1.2F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r172 = back.addOrReplaceChild("cube_r172", CubeListBuilder.create().texOffs(76, 20).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.7F, 1.2F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r173 = back.addOrReplaceChild("cube_r173", CubeListBuilder.create().texOffs(48, 75).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, -1.5F, 1.2F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r174 = back.addOrReplaceChild("cube_r174", CubeListBuilder.create().texOffs(42, 75).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9F, -3.2F, 1.3F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r175 = back.addOrReplaceChild("cube_r175", CubeListBuilder.create().texOffs(36, 75).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.6F, 0.0F, 0.0F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r176 = back.addOrReplaceChild("cube_r176", CubeListBuilder.create().texOffs(30, 75).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.6F, -1.5F, 0.0F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r177 = back.addOrReplaceChild("cube_r177", CubeListBuilder.create().texOffs(24, 75).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.3F, -3.2F, 0.1F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r178 = back.addOrReplaceChild("cube_r178", CubeListBuilder.create().texOffs(86, 8).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9F, -3.2F, 2.4F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r179 = back.addOrReplaceChild("cube_r179", CubeListBuilder.create().texOffs(24, 71).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9F, -3.2F, 3.7F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r180 = back.addOrReplaceChild("cube_r180", CubeListBuilder.create().texOffs(18, 71).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9F, -3.2F, 4.8F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r181 = back.addOrReplaceChild("cube_r181", CubeListBuilder.create().texOffs(12, 71).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9F, -3.2F, 5.9F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r182 = back.addOrReplaceChild("cube_r182", CubeListBuilder.create().texOffs(6, 71).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9F, -3.2F, 7.2F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r183 = back.addOrReplaceChild("cube_r183", CubeListBuilder.create().texOffs(0, 71).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9F, -3.2F, 8.4F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r184 = back.addOrReplaceChild("cube_r184", CubeListBuilder.create().texOffs(70, 40).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9F, -3.2F, 9.4F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r185 = back.addOrReplaceChild("cube_r185", CubeListBuilder.create().texOffs(86, 4).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.4F, -4.6F, 2.4F, -0.8742F, 0.3215F, -1.2077F));

        PartDefinition cube_r186 = back.addOrReplaceChild("cube_r186", CubeListBuilder.create().texOffs(70, 20).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.4F, -4.6F, 3.7F, -0.8742F, 0.3215F, -1.2077F));

        PartDefinition cube_r187 = back.addOrReplaceChild("cube_r187", CubeListBuilder.create().texOffs(54, 69).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.4F, -4.6F, 4.8F, -0.8742F, 0.3215F, -1.2077F));

        PartDefinition cube_r188 = back.addOrReplaceChild("cube_r188", CubeListBuilder.create().texOffs(66, 68).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.4F, -4.6F, 5.9F, -0.8742F, 0.3215F, -1.2077F));

        PartDefinition cube_r189 = back.addOrReplaceChild("cube_r189", CubeListBuilder.create().texOffs(60, 68).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.4F, -4.6F, 7.2F, -0.8742F, 0.3215F, -1.2077F));

        PartDefinition cube_r190 = back.addOrReplaceChild("cube_r190", CubeListBuilder.create().texOffs(68, 16).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.4F, -4.6F, 8.4F, -0.8742F, 0.3215F, -1.2077F));

        PartDefinition cube_r191 = back.addOrReplaceChild("cube_r191", CubeListBuilder.create().texOffs(68, 12).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.4F, -4.6F, 9.4F, -0.8742F, 0.3215F, -1.2077F));

        PartDefinition cube_r192 = back.addOrReplaceChild("cube_r192", CubeListBuilder.create().texOffs(54, 65).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2F, -4.6F, 9.4F, -0.8742F, -0.3215F, 1.2077F));

        PartDefinition cube_r193 = back.addOrReplaceChild("cube_r193", CubeListBuilder.create().texOffs(60, 64).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, -1.5F, 9.3F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r194 = back.addOrReplaceChild("cube_r194", CubeListBuilder.create().texOffs(64, 40).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.7F, 9.3F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r195 = back.addOrReplaceChild("cube_r195", CubeListBuilder.create().texOffs(42, 63).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.7F, 2.3F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r196 = back.addOrReplaceChild("cube_r196", CubeListBuilder.create().texOffs(36, 63).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.7F, 3.6F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r197 = back.addOrReplaceChild("cube_r197", CubeListBuilder.create().texOffs(30, 63).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.7F, 4.7F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r198 = back.addOrReplaceChild("cube_r198", CubeListBuilder.create().texOffs(24, 63).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.7F, 5.8F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r199 = back.addOrReplaceChild("cube_r199", CubeListBuilder.create().texOffs(18, 63).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.7F, 7.1F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r200 = back.addOrReplaceChild("cube_r200", CubeListBuilder.create().texOffs(12, 63).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.7F, 8.3F, -0.8025F, -0.3948F, 0.1344F));

        PartDefinition cube_r201 = back.addOrReplaceChild("cube_r201", CubeListBuilder.create().texOffs(6, 63).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, -1.5F, 8.3F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r202 = back.addOrReplaceChild("cube_r202", CubeListBuilder.create().texOffs(0, 63).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, -1.5F, 7.1F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r203 = back.addOrReplaceChild("cube_r203", CubeListBuilder.create().texOffs(62, 16).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, -1.5F, 5.8F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r204 = back.addOrReplaceChild("cube_r204", CubeListBuilder.create().texOffs(62, 12).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, -1.5F, 4.7F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r205 = back.addOrReplaceChild("cube_r205", CubeListBuilder.create().texOffs(62, 8).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, -1.5F, 3.6F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r206 = back.addOrReplaceChild("cube_r206", CubeListBuilder.create().texOffs(62, 4).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, -1.5F, 2.3F, -0.7574F, -0.398F, 0.7516F));

        PartDefinition cube_r207 = back.addOrReplaceChild("cube_r207", CubeListBuilder.create().texOffs(86, 0).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2F, -4.6F, 2.4F, -0.8742F, -0.3215F, 1.2077F));

        PartDefinition cube_r208 = back.addOrReplaceChild("cube_r208", CubeListBuilder.create().texOffs(60, 60).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2F, -4.6F, 3.7F, -0.8742F, -0.3215F, 1.2077F));

        PartDefinition cube_r209 = back.addOrReplaceChild("cube_r209", CubeListBuilder.create().texOffs(60, 56).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2F, -4.6F, 4.8F, -0.8742F, -0.3215F, 1.2077F));

        PartDefinition cube_r210 = back.addOrReplaceChild("cube_r210", CubeListBuilder.create().texOffs(60, 52).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2F, -4.6F, 5.9F, -0.8742F, -0.3215F, 1.2077F));

        PartDefinition cube_r211 = back.addOrReplaceChild("cube_r211", CubeListBuilder.create().texOffs(60, 48).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2F, -4.6F, 8.4F, -0.8742F, -0.3215F, 1.2077F));

        PartDefinition cube_r212 = back.addOrReplaceChild("cube_r212", CubeListBuilder.create().texOffs(60, 44).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2F, -4.6F, 7.2F, -0.8742F, -0.3215F, 1.2077F));

        PartDefinition cube_r213 = back.addOrReplaceChild("cube_r213", CubeListBuilder.create().texOffs(48, 55).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.7F, -3.2F, 9.4F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r214 = back.addOrReplaceChild("cube_r214", CubeListBuilder.create().texOffs(42, 55).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.7F, -3.2F, 8.4F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r215 = back.addOrReplaceChild("cube_r215", CubeListBuilder.create().texOffs(36, 55).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.7F, -3.2F, 7.2F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r216 = back.addOrReplaceChild("cube_r216", CubeListBuilder.create().texOffs(30, 55).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.7F, -3.2F, 5.9F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r217 = back.addOrReplaceChild("cube_r217", CubeListBuilder.create().texOffs(24, 55).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.7F, -3.2F, 4.8F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r218 = back.addOrReplaceChild("cube_r218", CubeListBuilder.create().texOffs(18, 55).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.7F, -3.2F, 3.7F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r219 = back.addOrReplaceChild("cube_r219", CubeListBuilder.create().texOffs(78, 84).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.7F, -3.2F, 2.4F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r220 = back.addOrReplaceChild("cube_r220", CubeListBuilder.create().texOffs(12, 55).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.7F, -3.2F, 1.3F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r221 = back.addOrReplaceChild("cube_r221", CubeListBuilder.create().texOffs(54, 28).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -1.5F, 1.2F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r222 = back.addOrReplaceChild("cube_r222", CubeListBuilder.create().texOffs(52, 41).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -1.5F, 2.3F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r223 = back.addOrReplaceChild("cube_r223", CubeListBuilder.create().texOffs(52, 24).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -1.5F, 3.6F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r224 = back.addOrReplaceChild("cube_r224", CubeListBuilder.create().texOffs(52, 20).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -1.5F, 4.7F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r225 = back.addOrReplaceChild("cube_r225", CubeListBuilder.create().texOffs(48, 51).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -1.5F, 5.8F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r226 = back.addOrReplaceChild("cube_r226", CubeListBuilder.create().texOffs(42, 51).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -1.5F, 7.1F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r227 = back.addOrReplaceChild("cube_r227", CubeListBuilder.create().texOffs(36, 51).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -1.5F, 8.3F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r228 = back.addOrReplaceChild("cube_r228", CubeListBuilder.create().texOffs(30, 51).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -1.5F, 9.3F, -0.7574F, 0.398F, -0.7516F));

        PartDefinition cube_r229 = back.addOrReplaceChild("cube_r229", CubeListBuilder.create().texOffs(24, 51).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 9.3F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r230 = back.addOrReplaceChild("cube_r230", CubeListBuilder.create().texOffs(18, 51).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 8.3F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r231 = back.addOrReplaceChild("cube_r231", CubeListBuilder.create().texOffs(12, 51).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 7.1F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r232 = back.addOrReplaceChild("cube_r232", CubeListBuilder.create().texOffs(6, 51).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 5.8F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r233 = back.addOrReplaceChild("cube_r233", CubeListBuilder.create().texOffs(0, 51).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 4.7F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r234 = back.addOrReplaceChild("cube_r234", CubeListBuilder.create().texOffs(50, 16).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 3.6F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r235 = back.addOrReplaceChild("cube_r235", CubeListBuilder.create().texOffs(50, 12).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 2.3F, -0.8025F, 0.3948F, -0.1344F));

        PartDefinition cube_r236 = back.addOrReplaceChild("cube_r236", CubeListBuilder.create().texOffs(50, 8).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 1.2F, -0.8025F, 0.3948F, -0.1344F));

        return LayerDefinition.create(meshdefinition, 128, 128);

    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
        if(entity.walkAnimation.isMoving()) {
            this.animateWalk(ModAnimationDefinitions.PANGOLIN_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        } else {
            this.animate(entity.idleAnimationState, ModAnimationDefinitions.PANGOLIN_IDLE, ageInTicks, 1f);
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
        pangolin.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return pangolin;
    }
}