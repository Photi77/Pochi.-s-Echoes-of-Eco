package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.Quokka;

public class QuokkaModel<T extends Quokka> extends HierarchicalModel<T> {
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

    public QuokkaModel(ModelPart root) {
        this.bone = root.getChild("bone");
        this.leftlegrear = this.bone.getChild("leftlegrear");
        this.rightlegrear = this.bone.getChild("rightlegrear");
        this.leftlegfront = this.bone.getChild("leftlegfront");
        this.rightlegfront = this.bone.getChild("rightlegfront");
        this.head = this.bone.getChild("head");
        this.bone2 = this.head.getChild("bone2");
        this.body = this.bone.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition leftlegrear = bone.addOrReplaceChild("leftlegrear", CubeListBuilder.create().texOffs(56, 5).addBox(3.0F, 5.0F, -3.0F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 22).addBox(3.0F, -2.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, -5.0F, 2.0F));

        PartDefinition rightlegrear = bone.addOrReplaceChild("rightlegrear", CubeListBuilder.create().texOffs(56, 6).addBox(-7.0F, 5.0F, -3.0F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 55).addBox(-7.0F, -2.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, -5.0F, 2.0F));

        PartDefinition leftlegfront = bone.addOrReplaceChild("leftlegfront", CubeListBuilder.create(), PartPose.offset(0.0F, -16.0F, -4.0F));

        PartDefinition cube_r1 = leftlegfront.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(48, 64).addBox(-2.0F, -1.0F, -2.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 1.0F, 0.0F, -0.2618F, 0.0F, 0.4363F));

        PartDefinition rightlegfront = bone.addOrReplaceChild("rightlegfront", CubeListBuilder.create(), PartPose.offset(0.0F, -16.0F, -4.0F));

        PartDefinition cube_r2 = rightlegfront.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 66).addBox(-1.0F, -1.0F, -2.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 1.0F, 0.0F, -0.2618F, 0.0F, -0.3927F));

        PartDefinition head = bone.addOrReplaceChild("head", CubeListBuilder.create().texOffs(30, 19).addBox(-4.0F, -9.0F, -2.0F, 8.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(66, 47).addBox(-3.0F, -10.0F, -2.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(76, 0).addBox(-2.0F, -9.0F, -4.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(12, 73).addBox(-5.0F, -8.0F, -2.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(26, 73).addBox(4.0F, -8.0F, -2.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(18, 73).addBox(-6.0F, -6.0F, -2.0F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(32, 73).addBox(5.0F, -6.0F, -2.0F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 46).addBox(-3.0F, -8.0F, -5.0F, 6.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(62, 57).addBox(-3.0F, 0.0F, -4.0F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(56, 76).addBox(-2.0F, -7.0F, -7.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(62, 50).addBox(-3.0F, -6.0F, -7.0F, 6.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(20, 36).addBox(-2.0F, -5.0F, -8.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(72, 70).addBox(-4.0F, -7.0F, -5.0F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 76).addBox(-5.0F, -5.0F, -5.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(40, 66).addBox(3.0F, -7.0F, -5.0F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(48, 74).addBox(4.0F, -5.0F, -5.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(78, 3).addBox(4.0F, -7.0F, 0.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(40, 76).addBox(-5.0F, -7.0F, 0.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 36).addBox(-4.0F, -8.0F, 0.0F, 8.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(66, 40).addBox(-3.0F, -7.0F, 2.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(68, 22).addBox(-2.0F, -6.0F, 2.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(76, 62).addBox(-2.0F, -9.0F, 0.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -17.0F, -1.0F));

        PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(78, 50).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -8.0F, 0.0F, -0.1732F, 0.0151F, 0.1739F));

        PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(76, 65).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -8.0F, 0.0F, -0.1745F, 0.0F, -0.1745F));

        PartDefinition bone2 = head.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(60, 79).addBox(-9.0F, -11.0F, -12.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(80, 22).addBox(-16.0F, -11.0F, -12.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(12.0F, 7.0F, 5.0F));

        PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -6.8846F, 7.2218F, 8.0F, 9.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(30, 31).addBox(-5.0F, -6.8846F, 6.2218F, 10.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(68, 76).addBox(-6.0F, -5.8846F, 6.2218F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(56, 79).addBox(5.0F, -5.8846F, 6.2218F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(66, 20).addBox(-4.0F, -7.8846F, 6.2218F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(56, 0).addBox(-3.0F, -6.8846F, 2.2218F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(-4.0F, 2.1154F, 7.2218F, 8.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(72, 35).addBox(-2.0F, 2.1154F, 14.2218F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(28, 0).addBox(-4.0F, -8.8846F, 7.2218F, 8.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(66, 15).addBox(-4.0F, -8.8846F, 13.2218F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 40).addBox(4.0F, -6.8846F, 7.2218F, 1.0F, 9.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(12, 66).addBox(4.0F, -7.8846F, 7.2218F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(48, 50).addBox(5.0F, -5.8846F, 7.2218F, 1.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(14, 57).addBox(5.0F, -4.8846F, 13.2218F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(60, 70).addBox(5.0F, -6.8846F, 7.2218F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(34, 40).addBox(-5.0F, -6.8846F, 7.2218F, 1.0F, 9.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(52, 8).addBox(-6.0F, -5.8846F, 7.2218F, 1.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(62, 62).addBox(-5.0F, -7.8846F, 7.2218F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(72, 29).addBox(-6.0F, -6.8846F, 7.2218F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(8, 76).addBox(-6.0F, -4.8846F, 13.2218F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 8).addBox(-5.0F, -6.8846F, 13.2218F, 10.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(66, 18).addBox(-4.0F, -7.8846F, 14.2218F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(48, 40).addBox(-4.0F, -6.8846F, 15.2218F, 8.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 57).addBox(-3.0F, -5.8846F, 16.2218F, 6.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(26, 66).addBox(-2.0F, -7.8846F, 16.2218F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(66, 5).addBox(-5.0F, -4.8846F, 1.2218F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 15).addBox(-4.0F, -5.8846F, 1.2218F, 8.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(34, 55).addBox(4.0F, -4.8846F, 1.2218F, 1.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(52, 33).addBox(-2.0F, 1.1154F, 1.2218F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -17.25F, -5.0F, -1.0908F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);


    }

    @Override
    public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(p_102622_, p_102623_, p_102621_);
        if(p_102618_.walkAnimation.isMoving()) {
            this.animateWalk(ModAnimationDefinitions.QUOKKA_WALK, p_102619_, p_102620_, 2f, 2.5f);
        } else {
            this.animate(p_102618_.idleAnimationState, ModAnimationDefinitions.QUOKKA_IDLE, p_102621_, 1f);
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