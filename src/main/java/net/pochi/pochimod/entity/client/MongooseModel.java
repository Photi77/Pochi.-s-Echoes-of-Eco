package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.Mongoose;

public class MongooseModel<T extends Mongoose> extends HierarchicalModel<T> {
    private final ModelPart mangoose;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart head;
    private final ModelPart frontleft;
    private final ModelPart rearright;
    private final ModelPart rearleft;
    private final ModelPart frontright;

    public MongooseModel(ModelPart root) {
        this.mangoose = root.getChild("mangoose");
        this.body = mangoose.getChild("body");
        this.tail = body.getChild("tail");
        this.head = mangoose.getChild("head");
        this.frontleft = mangoose.getChild("frontleft");
        this.rearright = mangoose.getChild("rearright");
        this.rearleft = mangoose.getChild("rearleft");
        this.frontright = mangoose.getChild("frontright");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition mangoose = partdefinition.addOrReplaceChild("mangoose", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = mangoose.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 11).addBox(-1.5F, -3.0F, -2.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(40, 21).addBox(-1.0F, -3.6F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(40, 24).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(20, 39).addBox(2.0F, -2.0F, 1.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 26).addBox(2.0F, -3.0F, 4.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(20, 0).addBox(-2.0F, -3.0F, 1.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(10, 34).addBox(-1.0F, -4.0F, 1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.0F, 0.0F, 1.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(20, 35).addBox(-1.0F, -1.0F, 9.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(30, 35).addBox(-1.0F, -4.0F, 9.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(18, 15).addBox(-1.0F, -5.0F, 4.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 9).addBox(-2.0F, -4.0F, 4.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(12, 29).addBox(-2.0F, -3.0F, 9.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(16, 21).addBox(-3.0F, -3.0F, 4.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(28, 39).addBox(-3.0F, -2.0F, 1.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -5.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(32, 6).addBox(-1.0F, -0.6415F, 4.665F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 18).addBox(-1.0F, -0.9415F, -1.335F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(32, 16).addBox(-0.5F, -0.6415F, 8.665F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 13.0F));

        PartDefinition head = mangoose.addOrReplaceChild("head", CubeListBuilder.create().texOffs(18, 9).addBox(-2.0F, -2.1F, -4.2F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(42, 43).addBox(-1.0F, -1.1F, -5.2F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 44).addBox(-1.0F, -2.0F, -5.2F, 2.0F, 2.0F, 1.0F, new CubeDeformation(-0.001F))
                .texOffs(12, 26).addBox(1.0F, -1.1F, -5.2F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 13).addBox(-1.0F, -0.9F, -6.2F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 44).addBox(-1.0F, -0.3F, -6.2F, 2.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F))
                .texOffs(10, 38).addBox(-1.5F, -2.6F, -4.2F, 3.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F))
                .texOffs(44, 39).addBox(-2.0F, -1.1F, -5.2F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 6).addBox(-2.0F, 0.4F, -4.2F, 4.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F))
                .texOffs(6, 45).addBox(-0.5F, -0.7F, -6.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 0).addBox(-1.0F, -1.1F, -1.2F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.4F, -6.8F));

        PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(16, 19).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -2.3F, -3.2F, -0.9482F, -0.1241F, -0.4806F));

        PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(16, 18).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -2.3F, -3.2F, -0.9482F, 0.1241F, 0.4806F));

        PartDefinition frontleft = mangoose.addOrReplaceChild("frontleft", CubeListBuilder.create().texOffs(24, 44).addBox(-0.3F, 3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 34).addBox(-0.9F, -1.0F, -2.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(36, 39).addBox(-0.9F, 2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -6.0F, -2.7F, -0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r3 = frontleft.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(40, 33).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.3F, -0.5F, 0.1745F, 0.0F, 0.0F));

        PartDefinition rearright = mangoose.addOrReplaceChild("rearright", CubeListBuilder.create().texOffs(44, 3).addBox(-0.7F, 0.8144F, -0.0704F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -5.0F, 5.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r4 = rearright.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(40, 27).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.1144F, -0.0704F, 0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r5 = rearright.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(28, 21).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, -0.9856F, -0.5704F, -0.5672F, 0.0F, 0.0F));

        PartDefinition rearleft = mangoose.addOrReplaceChild("rearleft", CubeListBuilder.create().texOffs(44, 8).addBox(-0.3F, 0.8144F, -0.0704F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -5.0F, 5.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r6 = rearleft.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(40, 30).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.1144F, -0.0704F, 0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r7 = rearleft.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(28, 28).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, -0.9856F, -0.5704F, -0.5672F, 0.0F, 0.0F));

        PartDefinition frontright = mangoose.addOrReplaceChild("frontright", CubeListBuilder.create().texOffs(20, 44).addBox(-0.7F, 3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(34, 0).addBox(-1.1F, -1.0F, -2.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 40).addBox(-1.1F, 2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -6.0F, -2.7F, -0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r8 = frontright.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(40, 36).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.3F, -0.5F, 0.1745F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);

    }

    @Override
    public void setupAnim(Mongoose p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(p_102622_, p_102623_, p_102621_);

        if(p_102618_.walkAnimation.isMoving()){
            this.animateWalk(ModAnimationDefinitions.MONGOOSE_WALK, p_102619_, p_102620_, 2f, 2.5f);
        } else {
            this.animate(p_102618_.idleAnimationState, ModAnimationDefinitions.MONGOOSE_IDLE, p_102621_, 1f);
        }
        this.animate(p_102618_.attackAnimationState, ModAnimationDefinitions.MONGOOSE_ATTACH, p_102621_, 1f);
    }

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        mangoose.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return mangoose;
    }
}