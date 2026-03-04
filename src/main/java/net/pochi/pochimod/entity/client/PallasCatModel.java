package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.PallasCat;

public class PallasCatModel<T extends PallasCat> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    //public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "ant"), "main");
    private final ModelPart otocolobus;
    private final ModelPart head;
    private final ModelPart torso;
    private final ModelPart tail;
    private final ModelPart righthand;
    private final ModelPart lefthand;
    private final ModelPart rightleg;
    private final ModelPart leftleg;

    public PallasCatModel(ModelPart root) {
        this.otocolobus = root.getChild("otocolobus");
        this.head = this.otocolobus.getChild("head");
        this.torso = this.otocolobus.getChild("torso");
        this.tail = this.torso.getChild("tail");
        this.righthand = this.otocolobus.getChild("righthand");
        this.lefthand = this.otocolobus.getChild("lefthand");
        this.rightleg = this.otocolobus.getChild("rightleg");
        this.leftleg = this.otocolobus.getChild("leftleg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition otocolobus = partdefinition.addOrReplaceChild("otocolobus", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head = otocolobus.addOrReplaceChild("head", CubeListBuilder.create().texOffs(56, 0).addBox(-4.0F, -5.0F, -5.0F, 8.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(72, 43).addBox(-3.0F, -6.0F, -4.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(74, 20).addBox(-3.0F, -4.0F, -6.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 75).addBox(-5.0F, -4.0F, -4.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(-0.002F))
                .texOffs(74, 25).addBox(-1.0F, -3.0F, -7.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 43).addBox(-3.0F, 0.0F, -4.0F, 6.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(72, 68).addBox(4.0F, -4.0F, -4.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(-0.002F)), PartPose.offset(0.0F, -10.0F, -8.0F));

        PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(46, 75).addBox(-2.0F, -2.0F, 0.0F, 5.0F, 6.0F, 0.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(4.0F, -2.0F, -2.0F, 0.0F, -0.2182F, 0.0F));

        PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(66, 77).addBox(-4.0F, -3.0F, -1.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.2F, -3.6F, -1.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(56, 75).addBox(0.0F, -3.0F, -1.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2F, -3.6F, -1.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(36, 75).addBox(-3.0F, -2.0F, 0.0F, 5.0F, 6.0F, 0.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(-4.0F, -2.0F, -2.0F, 0.0F, 0.2182F, 0.0F));

        PartDefinition torso = otocolobus.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 49).addBox(5.0F, -9.0F, -8.0F, 1.0F, 9.0F, 17.0F, new CubeDeformation(0.0F))
                .texOffs(36, 49).addBox(-6.0F, -9.0F, -8.0F, 1.0F, 9.0F, 17.0F, new CubeDeformation(0.0F))
                .texOffs(56, 11).addBox(-4.0F, -8.0F, 9.0F, 8.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-5.0F, -10.0F, -9.0F, 10.0F, 10.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(-4.0F, -11.0F, -9.0F, 8.0F, 4.0F, 17.0F, new CubeDeformation(0.0F))
                .texOffs(56, 20).addBox(-4.0F, -8.0F, -10.0F, 8.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(72, 60).addBox(-3.0F, -7.0F, 10.0F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition tail = torso.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, 8.0F));

        PartDefinition cube_r5 = tail.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(50, 28).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

        PartDefinition righthand = otocolobus.addOrReplaceChild("righthand", CubeListBuilder.create().texOffs(0, 75).addBox(-5.0F, 1.0F, -1.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, -7.0F));

        PartDefinition lefthand = otocolobus.addOrReplaceChild("lefthand", CubeListBuilder.create().texOffs(74, 11).addBox(2.0F, 1.0F, -1.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, -7.0F));

        PartDefinition rightleg = otocolobus.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(72, 48).addBox(-5.0F, -2.0F, -2.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 7.0F));

        PartDefinition leftleg = otocolobus.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(12, 75).addBox(2.0F, 1.0F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 7.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
        if(entity.walkAnimation.isMoving()) {
            this.animateWalk(ModAnimationDefinitions.PALLAS_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        } else {
            this.animate(entity.idleAnimationState, ModAnimationDefinitions.PALLAS_IDLE, ageInTicks, 1f);
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
        otocolobus.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return otocolobus;
    }
}