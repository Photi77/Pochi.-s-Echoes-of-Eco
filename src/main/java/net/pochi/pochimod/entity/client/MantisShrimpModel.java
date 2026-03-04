package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.MantisShrimp;

public class MantisShrimpModel<T extends MantisShrimp> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    //public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "ant"), "main");
    private final ModelPart entire;
    private final ModelPart torso;
    private final ModelPart head;
    private final ModelPart righthand;
    private final ModelPart rightfinger;
    private final ModelPart lefthand;
    private final ModelPart leftfinger;
    private final ModelPart body;
    private final ModelPart main;
    private final ModelPart leftfin;
    private final ModelPart tail;
    private final ModelPart rightfin;
    private final ModelPart leftleg;
    private final ModelPart l1;
    private final ModelPart l2;
    private final ModelPart l3;
    private final ModelPart rightleg;
    private final ModelPart r1;
    private final ModelPart r2;
    private final ModelPart r3;

    public MantisShrimpModel(ModelPart root) {
        this.entire = root.getChild("entire");
        this.torso = this.entire.getChild("torso");
        this.head = this.torso.getChild("head");
        this.righthand = this.torso.getChild("righthand");
        this.rightfinger = this.righthand.getChild("rightfinger");
        this.lefthand = this.torso.getChild("lefthand");
        this.leftfinger = this.lefthand.getChild("leftfinger");
        this.body = this.entire.getChild("body");
        this.main = this.body.getChild("main");
        this.leftfin = this.main.getChild("leftfin");
        this.tail = this.main.getChild("tail");
        this.rightfin = this.main.getChild("rightfin");
        this.leftleg = this.body.getChild("leftleg");
        this.l1 = this.leftleg.getChild("l1");
        this.l2 = this.leftleg.getChild("l2");
        this.l3 = this.leftleg.getChild("l3");
        this.rightleg = this.body.getChild("rightleg");
        this.r1 = this.rightleg.getChild("r1");
        this.r2 = this.rightleg.getChild("r2");
        this.r3 = this.rightleg.getChild("r3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition entire = partdefinition.addOrReplaceChild("entire", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition torso = entire.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(32, 32).addBox(-3.0F, -5.0F, -2.3F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(26, 32).addBox(3.0F, -5.0F, -2.3F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(14, 18).addBox(-1.5F, -5.0F, 0.0F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 18).addBox(-2.0F, -6.0F, -2.0F, 5.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(18, 4).addBox(-2.0F, -5.0F, -3.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -2.0F, -4.0F));

        PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create().texOffs(14, 36).addBox(-1.5F, -9.5F, -5.8F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(36, 16).addBox(0.5F, -9.5F, -5.8F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 2.0F, 4.0F));

        PartDefinition righthand = torso.addOrReplaceChild("righthand", CubeListBuilder.create(), PartPose.offset(0.5F, -1.0F, -2.0F));

        PartDefinition cube_r1 = righthand.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(10, 36).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, -0.5F, 0.1745F, 0.0F, 0.0F));

        PartDefinition rightfinger = righthand.addOrReplaceChild("rightfinger", CubeListBuilder.create(), PartPose.offset(-2.5F, -4.0F, -1.4F));

        PartDefinition cube_r2 = rightfinger.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(34, 20).addBox(-1.0F, 1.0F, -3.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r3 = rightfinger.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(18, 32).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4F, -0.6F, -0.2182F, 0.0F, 0.0F));

        PartDefinition lefthand = torso.addOrReplaceChild("lefthand", CubeListBuilder.create(), PartPose.offset(0.5F, -1.0F, -2.0F));

        PartDefinition cube_r4 = lefthand.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 34).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, -0.5F, 0.1745F, 0.0F, 0.0F));

        PartDefinition leftfinger = lefthand.addOrReplaceChild("leftfinger", CubeListBuilder.create(), PartPose.offset(2.5F, -4.0F, -1.4F));

        PartDefinition cube_r5 = leftfinger.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(4, 34).addBox(-1.0F, 1.0F, -3.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r6 = leftfinger.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(10, 31).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4F, -0.6F, -0.2182F, 0.0F, 0.0F));

        PartDefinition body = entire.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition main = body.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0).addBox(-0.4F, -1.4F, -6.3F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(18, 9).addBox(0.6F, -0.4F, -4.3F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F))
                .texOffs(18, 13).addBox(0.6F, -0.4F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F))
                .texOffs(0, 6).addBox(-0.4F, -1.4F, -3.0F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(14, 24).addBox(0.6F, 0.6F, 2.3F, 4.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F))
                .texOffs(0, 12).addBox(-0.4F, -1.4F, 0.3F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.6F, -2.6F, 1.3F));

        PartDefinition leftfin = main.addOrReplaceChild("leftfin", CubeListBuilder.create(), PartPose.offset(5.2F, 0.0F, 3.3F));

        PartDefinition cube_r7 = leftfin.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(30, 28).addBox(0.0F, -1.0F, -3.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(-0.002F))
                .texOffs(0, 30).addBox(0.0F, -1.0F, -6.3F, 2.0F, 1.0F, 3.0F, new CubeDeformation(-0.002F))
                .texOffs(20, 28).addBox(0.0F, -1.0F, -9.6F, 2.0F, 1.0F, 3.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6109F));

        PartDefinition tail = main.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(2.6F, 1.1F, 4.4F));

        PartDefinition cube_r8 = tail.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(10, 27).addBox(-3.0F, -1.0F, -1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3099F, -0.1664F, 0.053F));

        PartDefinition cube_r9 = tail.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(26, 24).addBox(1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(1.0F, 0.2F, 0.0F, -0.3492F, 0.4971F, -0.1719F));

        PartDefinition cube_r10 = tail.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(24, 17).addBox(-2.0F, -1.0F, 2.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, -0.3F, -1.0F, -0.3054F, 0.0F, 0.0F));

        PartDefinition cube_r11 = tail.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 26).addBox(-3.0F, -1.0F, -1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-1.0F, 0.2F, 0.0F, -0.3492F, -0.4971F, 0.1719F));

        PartDefinition cube_r12 = tail.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(32, 2).addBox(0.0F, -1.0F, 2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.2F, 0.0F, -0.3099F, 0.1664F, -0.053F));

        PartDefinition cube_r13 = tail.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(32, 0).addBox(-3.0F, -1.0F, 2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.2F, 0.0F, -0.3099F, -0.1664F, 0.053F));

        PartDefinition cube_r14 = tail.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(24, 20).addBox(1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3099F, 0.1664F, -0.053F));

        PartDefinition cube_r15 = tail.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(18, 0).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, -0.5F, -1.0F, -0.3054F, 0.0F, 0.0F));

        PartDefinition rightfin = main.addOrReplaceChild("rightfin", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -3.3F));

        PartDefinition cube_r16 = rightfin.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(30, 4).addBox(-2.0F, -1.0F, -3.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(-0.002F))
                .texOffs(30, 8).addBox(-2.0F, -1.0F, 0.3F, 2.0F, 1.0F, 3.0F, new CubeDeformation(-0.002F))
                .texOffs(30, 12).addBox(-2.0F, -1.0F, 3.6F, 2.0F, 1.0F, 3.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6109F));

        PartDefinition leftleg = body.addOrReplaceChild("leftleg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition l1 = leftleg.addOrReplaceChild("l1", CubeListBuilder.create(), PartPose.offset(2.0F, -1.5F, -3.5F));

        PartDefinition cube_r17 = l1.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(36, 23).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.5F, 0.5F, 0.0F, 0.0F, -0.7854F));

        PartDefinition l2 = leftleg.addOrReplaceChild("l2", CubeListBuilder.create(), PartPose.offset(2.0F, -1.3F, -0.6F));

        PartDefinition cube_r18 = l2.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(4, 37).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.3F, 0.6F, 0.0F, 0.0F, -0.7854F));

        PartDefinition l3 = leftleg.addOrReplaceChild("l3", CubeListBuilder.create(), PartPose.offset(2.0F, -1.0F, 3.5F));

        PartDefinition cube_r19 = l3.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(18, 37).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.0F, 0.5F, 0.0F, 0.0F, -0.7854F));

        PartDefinition rightleg = body.addOrReplaceChild("rightleg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition r1 = rightleg.addOrReplaceChild("r1", CubeListBuilder.create(), PartPose.offset(-2.0F, -1.0F, -3.5F));

        PartDefinition cube_r20 = r1.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(22, 37).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, 0.5F, 0.0F, 0.0F, 0.7854F));

        PartDefinition r2 = rightleg.addOrReplaceChild("r2", CubeListBuilder.create(), PartPose.offset(-2.0F, -1.0F, -0.5F));

        PartDefinition cube_r21 = r2.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(26, 38).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, 0.5F, 0.0F, 0.0F, 0.7854F));

        PartDefinition r3 = rightleg.addOrReplaceChild("r3", CubeListBuilder.create(), PartPose.offset(-2.0F, -1.0F, 3.5F));

        PartDefinition cube_r22 = r3.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(30, 38).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, 0.5F, 0.0F, 0.0F, 0.7854F));

        return LayerDefinition.create(meshdefinition, 64, 64);

    }

    @Override
    public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(p_102622_, p_102623_, p_102621_);
        if(p_102618_.walkAnimation.isMoving()){
            this.animateWalk(ModAnimationDefinitions.MANTIS_SHRIMP_WALK, p_102619_, p_102620_, 2f, 2.5f);
        } else {
            this.animate(p_102618_.idleAnimationState, ModAnimationDefinitions.MANTIS_SHRIMP_IDLE, p_102621_, 1f);
        }
        this.animate(p_102618_.attackAnimationState, ModAnimationDefinitions.MANTIS_SHRIMP_ATTACH, p_102621_, 1f);
    }

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        entire.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return entire;
    }
}