package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.Peacock;

public class PeacockModel<T extends Peacock> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    //public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "peacock"), "main");
    private final ModelPart peacock;
    private final ModelPart body;
    private final ModelPart leftleg;
    private final ModelPart rightleg;
    private final ModelPart leftwing;
    private final ModelPart rightwing;
    private final ModelPart tail;
    private final ModelPart tail1;
    private final ModelPart tail2;
    private final ModelPart tail3;
    private final ModelPart tail4;
    private final ModelPart tail5;
    private final ModelPart tail6;
    private final ModelPart tail7;
    private final ModelPart tail8;
    private final ModelPart tail9;
    private final ModelPart head;
    private final ModelPart neck;
    private final ModelPart head1;

    public PeacockModel(ModelPart root) {
        this.peacock = root.getChild("peacock");
        this.body = this.peacock.getChild("body");
        this.leftleg = this.body.getChild("leftleg");
        this.rightleg = this.body.getChild("rightleg");
        this.leftwing = this.body.getChild("leftwing");
        this.rightwing = this.body.getChild("rightwing");
        this.tail = this.body.getChild("tail");
        this.tail1 = this.tail.getChild("tail1");
        this.tail2 = this.tail.getChild("tail2");
        this.tail3 = this.tail.getChild("tail3");
        this.tail4 = this.tail.getChild("tail4");
        this.tail5 = this.tail.getChild("tail5");
        this.tail6 = this.tail.getChild("tail6");
        this.tail7 = this.tail.getChild("tail7");
        this.tail8 = this.tail.getChild("tail8");
        this.tail9 = this.tail.getChild("tail9");
        this.head = this.peacock.getChild("head");
        this.neck = this.head.getChild("neck");
        this.head1 = this.head.getChild("head1");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition peacock = partdefinition.addOrReplaceChild("peacock", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = peacock.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 259).addBox(-3.0F, -8.0F, -5.0F, 6.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(34, 259).addBox(-3.0F, -15.0F, -4.0F, 6.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(258, 222).addBox(-4.0F, -14.0F, -5.0F, 8.0F, 6.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(118, 259).addBox(4.0F, -13.0F, -4.0F, 1.0F, 5.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(140, 259).addBox(-5.0F, -13.0F, -4.0F, 1.0F, 5.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(204, 259).addBox(-3.0F, -13.0F, -9.0F, 6.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(178, 269).addBox(-3.0F, -13.0F, -11.0F, 6.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(16, 271).addBox(-2.0F, -12.0F, -12.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(118, 274).addBox(-4.0F, -12.0F, -9.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(212, 276).addBox(4.0F, -12.0F, -7.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(168, 277).addBox(-5.0F, -12.0F, -7.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(128, 274).addBox(3.0F, -12.0F, -9.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(34, 270).addBox(-2.0F, -14.0F, -9.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(66, 274).addBox(-2.0F, -15.0F, -7.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(50, 270).addBox(-2.0F, -8.0F, -9.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(224, 259).addBox(-3.0F, -13.0F, 6.0F, 6.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(184, 259).addBox(-2.0F, -13.0F, 10.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(204, 268).addBox(-1.0F, -12.0F, 13.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(162, 259).addBox(-2.0F, -14.0F, 6.0F, 4.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 271).addBox(-2.0F, -8.0F, 6.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(148, 274).addBox(-4.0F, -12.0F, 6.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(266, 277).addBox(4.0F, -12.0F, 6.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(138, 274).addBox(3.0F, -12.0F, 6.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(30, 275).addBox(2.0F, -12.0F, 10.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(40, 275).addBox(-3.0F, -12.0F, 10.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(258, 277).addBox(-5.0F, -12.0F, 6.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(80, 274).addBox(-2.0F, -15.0F, 6.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leftleg = body.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(116, 281).addBox(1.0F, 4.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(94, 274).addBox(1.0F, 7.0F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, -3.0F));

        PartDefinition cube_r1 = leftleg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(100, 280).addBox(0.0F, -4.0F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 11.0F, 0.0F, 0.0F, -0.6109F, 0.0F));

        PartDefinition cube_r2 = leftleg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(108, 280).addBox(0.0F, -4.0F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.134F, 11.0F, 0.5F, 0.0F, 0.5236F, 0.0F));

        PartDefinition cube_r3 = leftleg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(252, 280).addBox(0.0F, -4.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 4.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition rightleg = body.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(124, 281).addBox(-2.0F, 4.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(106, 274).addBox(-2.0F, 7.0F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, -3.0F));

        PartDefinition cube_r4 = rightleg.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(236, 280).addBox(-1.0F, -4.0F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 11.0F, 0.0F, 0.0F, 0.6109F, 0.0F));

        PartDefinition cube_r5 = rightleg.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(228, 280).addBox(-1.0F, -4.0F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.134F, 11.0F, 0.5F, 0.0F, -0.5236F, 0.0F));

        PartDefinition cube_r6 = rightleg.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(120, 281).addBox(-1.0F, -4.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 4.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition leftwing = body.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(244, 280).addBox(-0.9F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -14.0F, -7.0F));

        PartDefinition cube_r7 = leftwing.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(234, 269).addBox(1.0F, -3.0F, 13.0F, 1.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(244, 259).addBox(1.0F, -3.0F, 18.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(66, 259).addBox(1.0F, 2.0F, 1.0F, 1.0F, 3.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(258, 239).addBox(1.0F, -3.0F, -1.0F, 1.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 1.0F, -0.1F, 0.0F, 0.0F, -0.6109F));

        PartDefinition rightwing = body.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(280, 277).addBox(-1.1F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -14.0F, -7.0F));

        PartDefinition cube_r8 = rightwing.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(246, 269).addBox(-2.0F, -3.0F, 13.0F, 1.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(220, 268).addBox(-2.0F, -3.0F, 18.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(92, 259).addBox(-2.0F, 2.0F, 1.0F, 1.0F, 3.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(258, 258).addBox(-2.0F, -3.0F, -1.0F, 1.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 1.0F, -0.1F, 0.0F, 0.0F, 0.6109F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, -14.0F, 5.0F));

        PartDefinition tail_1 = tail.addOrReplaceChild("tail1", CubeListBuilder.create(), PartPose.offset(2.0F, -0.8F, 0.0F));

        PartDefinition cube_r9 = tail_1.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 0.0F, 37.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-2.0F, -0.2F, 0.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition tail_2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create(), PartPose.offset(-1.7F, -0.1F, 0.0F));

        PartDefinition cube_r10 = tail_2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 111).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 0.0F, 37.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(2.7F, -0.1F, 0.0F, -0.0774F, 0.0403F, 0.4784F));

        PartDefinition tail_3 = tail.addOrReplaceChild("tail3", CubeListBuilder.create(), PartPose.offset(-4.1F, 0.3F, 0.0F));

        PartDefinition cube_r11 = tail_3.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(86, 37).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 0.0F, 37.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(2.7F, -0.1F, 0.0F, -0.0715F, -0.05F, -0.6091F));

        PartDefinition tail_4 = tail.addOrReplaceChild("tail4", CubeListBuilder.create(), PartPose.offset(-2.7F, -0.9F, 0.0F));

        PartDefinition cube_r12 = tail_4.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 185).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 0.0F, 37.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(2.7F, -0.1F, 0.0F, -0.0873F, 0.0873F, 0.0F));

        PartDefinition tail_5 = tail.addOrReplaceChild("tail5", CubeListBuilder.create(), PartPose.offset(-1.4F, 0.2F, 0.0F));

        PartDefinition cube_r13 = tail_5.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(258, 148).addBox(-2.0F, 0.0F, 0.0F, 6.0F, 0.0F, 37.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-2.7F, 0.1F, 0.0F, -0.1214F, 0.0216F, -0.6084F));

        PartDefinition tail_6 = tail.addOrReplaceChild("tail6", CubeListBuilder.create(), PartPose.offset(3.0F, 0.0F, 0.0F));

        PartDefinition cube_r14 = tail_6.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(86, 148).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 0.0F, 37.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-2.0F, -0.2F, 0.0F, -0.037F, 0.1177F, 0.4816F));

        PartDefinition tail_7 = tail.addOrReplaceChild("tail7", CubeListBuilder.create(), PartPose.offset(-2.7F, -0.9F, 0.0F));

        PartDefinition cube_r15 = tail_7.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(258, 74).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 0.0F, 37.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(2.7F, -0.1F, 0.0F, -0.0873F, -0.0873F, 0.0F));

        PartDefinition tail_8 = tail.addOrReplaceChild("tail8", CubeListBuilder.create(), PartPose.offset(-1.4F, 0.2F, 0.0F));

        PartDefinition cube_r16 = tail_8.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(258, 185).addBox(-2.0F, 0.0F, 0.0F, 6.0F, 0.0F, 37.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-2.7F, 0.1F, 0.0F, -0.0213F, -0.1215F, -0.6134F));

        PartDefinition tail_9 = tail.addOrReplaceChild("tail9", CubeListBuilder.create(), PartPose.offset(3.0F, 0.0F, 0.0F));

        PartDefinition cube_r17 = tail_9.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 222).addBox(-2.0F, 0.0F, 0.0F, 6.0F, 0.0F, 37.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-4.7F, -0.1F, 0.0F, -0.1176F, -0.0372F, 0.4783F));

        PartDefinition head = peacock.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, -11.0F));

        PartDefinition neck = head.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(162, 267).addBox(-2.0F, -4.0F, -6.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(178, 267).addBox(-1.0F, 2.0F, -6.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(90, 280).addBox(-2.0F, 2.0F, -5.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(10, 277).addBox(-1.0F, -12.0F, -5.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(190, 278).addBox(-1.0F, -14.0F, -4.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(194, 269).addBox(-1.0F, -10.0F, -5.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(50, 275).addBox(-3.0F, -4.0F, -5.0F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(58, 275).addBox(2.0F, -4.0F, -5.0F, 1.0F, 6.0F, 3.0F, new CubeDeformation(-0.001F))
                .texOffs(274, 277).addBox(1.0F, -10.0F, -4.0F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(66, 278).addBox(-2.0F, -10.0F, -4.0F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(72, 278).addBox(-1.0F, -4.0F, -7.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(78, 278).addBox(-1.0F, -10.0F, -6.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 276).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(84, 278).addBox(-1.0F, -10.0F, -2.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 3.0F));

        PartDefinition head1 = head.addOrReplaceChild("head1", CubeListBuilder.create().texOffs(178, 276).addBox(-3.0F, -3.0F, -1.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(198, 278).addBox(-2.0F, -3.0F, 1.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(158, 277).addBox(-2.5F, -3.0F, -3.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(220, 278).addBox(-1.5F, -1.6F, -6.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(20, 277).addBox(-2.0F, -4.0F, -2.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(204, 276).addBox(-1.0F, -8.0F, -2.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(1.0F, -15.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 512, 512);

    }

    @Override
    public void setupAnim(Peacock entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
       if(entity.walkAnimation.isMoving() && entity.onGround()) {
           this.animateWalk(ModAnimationDefinitions.PEACOCK_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
       } else if(!entity.onGround()){
           this.animateWalk(ModAnimationDefinitions.PEACOCK_FLY, limbSwing, limbSwingAmount, 2f, 2.5f);
       }else {
           this.animate(entity.idleAnimationState, ModAnimationDefinitions.PEACOCK_IDLE, ageInTicks, 1f);
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
        peacock.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return peacock;
    }
}