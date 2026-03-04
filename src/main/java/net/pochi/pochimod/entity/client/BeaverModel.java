package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.Beaver;

public class BeaverModel<T extends Beaver> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    //public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "ant"), "main");
    private final ModelPart bone;
    private final ModelPart leftlegrear;
    private final ModelPart rightlegrear;
    private final ModelPart leftlegfront;
    private final ModelPart rightlegfront;
    private final ModelPart head;
    private final ModelPart bone2;
    private final ModelPart body;
    private final ModelPart fin;
    private final ModelPart fintop;

    public BeaverModel(ModelPart root) {
        this.bone = root.getChild("bone");
        this.leftlegrear = this.bone.getChild("leftlegrear");
        this.rightlegrear = this.bone.getChild("rightlegrear");
        this.leftlegfront = this.bone.getChild("leftlegfront");
        this.rightlegfront = this.bone.getChild("rightlegfront");
        this.head = this.bone.getChild("head");
        this.bone2 = this.head.getChild("bone2");
        this.body = this.bone.getChild("body");
        this.fin = this.body.getChild("fin");
        this.fintop = this.fin.getChild("fintop");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition leftlegrear = bone.addOrReplaceChild("leftlegrear", CubeListBuilder.create().texOffs(50, 65).addBox(-7.0F, 1.0F, -4.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F))
                .texOffs(36, 54).addBox(-7.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, -2.0F, 1.0F));

        PartDefinition rightlegrear = bone.addOrReplaceChild("rightlegrear", CubeListBuilder.create().texOffs(64, 38).addBox(3.0F, 1.0F, -4.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F))
                .texOffs(20, 54).addBox(3.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, -2.0F, 1.0F));

        PartDefinition leftlegfront = bone.addOrReplaceChild("leftlegfront", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, -6.0F));

        PartDefinition cube_r1 = leftlegfront.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(64, 31).addBox(4.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -3.0F, 1.0F, -0.4272F, 0.0916F, 0.1983F));

        PartDefinition rightlegfront = bone.addOrReplaceChild("rightlegfront", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, -6.0F));

        PartDefinition cube_r2 = rightlegfront.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(20, 65).addBox(-6.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -3.0F, 1.0F, -0.4272F, -0.0916F, -0.1983F));

        PartDefinition head = bone.addOrReplaceChild("head", CubeListBuilder.create().texOffs(36, 60).addBox(-2.5F, -3.0F, -1.0F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(46, 33).addBox(-3.0F, -3.0F, -4.0F, 6.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(56, 26).addBox(-2.0F, -4.0F, -4.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(60, 44).addBox(-2.0F, -3.0F, -6.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(68, 8).addBox(-1.0F, -4.0F, -6.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(52, 68).addBox(-1.0F, -3.0F, -7.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, -7.0F));

        PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(24, 31).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -3.0F, 0.0F, -0.1732F, 0.0151F, 0.1739F));

        PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(24, 29).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -3.0F, 0.0F, -0.1745F, 0.0F, -0.1745F));

        PartDefinition bone2 = head.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(12.0F, 14.0F, 5.0F));

        PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 14).addBox(-4.0F, -4.0F, 5.0F, 8.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 26).addBox(-4.0F, 4.0F, 4.0F, 8.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(22, 45).addBox(-3.0F, -5.0F, -1.0F, 6.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(52, 12).addBox(-2.0F, -6.0F, -1.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(28, 65).addBox(3.0F, -4.0F, 0.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 66).addBox(-4.0F, -4.0F, 0.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(20, 60).addBox(-3.0F, 0.0F, 2.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(66, 18).addBox(-2.0F, 0.0F, 1.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(36, 12).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(58, 59).addBox(-3.0F, -4.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(62, 41).addBox(-2.0F, 4.0F, 10.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 54).addBox(4.0F, -4.0F, 5.0F, 1.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(52, 54).addBox(5.0F, -3.0F, 5.0F, 1.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(48, 68).addBox(5.0F, -2.0F, 9.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(66, 21).addBox(5.0F, -4.0F, 5.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(10, 54).addBox(-5.0F, -4.0F, 5.0F, 1.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(58, 0).addBox(-6.0F, -3.0F, 5.0F, 1.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(36, 66).addBox(-6.0F, -4.0F, 5.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(68, 50).addBox(-6.0F, -2.0F, 9.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(-5.0F, -4.0F, 9.0F, 10.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(46, 41).addBox(-3.0F, -5.0F, 9.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(42, 45).addBox(-4.0F, -4.0F, 11.0F, 8.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 18).addBox(-3.0F, -3.0F, 12.0F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 14).addBox(-4.0F, -5.0F, 3.0F, 8.0F, 9.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 49).addBox(-3.0F, -6.0F, 4.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(44, 66).addBox(4.0F, -4.0F, 3.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(68, 0).addBox(-5.0F, -4.0F, 3.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(62, 59).addBox(4.0F, -4.0F, 4.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(62, 50).addBox(-6.0F, -4.0F, 4.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -7.0F));

        PartDefinition fin = body.addOrReplaceChild("fin", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 13.0F));

        PartDefinition cube_r5 = fin.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 39).addBox(-2.0F, -5.0F, 10.0F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -11.0F, -0.5672F, 0.0F, 0.0F));

        PartDefinition fintop = fin.addOrReplaceChild("fintop", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -1.0F, -2.0F, 6.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(24, 33).addBox(-4.0F, -1.0F, -1.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(36, 0).addBox(3.0F, -1.0F, -1.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(8, 66).addBox(-2.0F, -1.0F, 10.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 5.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);

    }

    @Override
    public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(p_102622_, p_102623_, p_102621_);
        if(p_102618_.walkAnimation.isMoving()) {
            if(p_102618_.isInWater()) {
                this.animateWalk(ModAnimationDefinitions.BEAVER_SWIM, p_102619_, p_102620_, 2f, 2.5f);
            } else {
                this.animateWalk(ModAnimationDefinitions.BEAVER_WALK, p_102619_, p_102620_, 2f, 2.5f);
            }
        } else {
            this.animate(p_102618_.idleAnimationState, ModAnimationDefinitions.BEAVER_IDLE, p_102621_, 1f);
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
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return bone;
    }

}