package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.Ant;

public class AntModel <T extends Ant> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    //public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "ant"), "main");
    private final ModelPart ant;
    private final ModelPart head;
    private final ModelPart rightstag;
    private final ModelPart leftstag;
    private final ModelPart righthone;
    private final ModelPart lefthone;
    private final ModelPart body;
    private final ModelPart rigthleg1;
    private final ModelPart leftleg1;
    private final ModelPart rigthleg2;
    private final ModelPart leftleg2;
    private final ModelPart rigthleg3;
    private final ModelPart leftleg3;
    private final ModelPart tail;

    public AntModel(ModelPart root) {
        this.ant = root.getChild("ant");
        this.head = this.ant.getChild("head");
        this.rightstag = this.head.getChild("rightstag");
        this.leftstag = this.head.getChild("leftstag");
        this.righthone = this.head.getChild("righthone");
        this.lefthone = this.head.getChild("lefthone");
        this.body = this.ant.getChild("body");
        this.rigthleg1 = this.ant.getChild("rigthleg1");
        this.leftleg1 = this.ant.getChild("leftleg1");
        this.rigthleg2 = this.ant.getChild("rigthleg2");
        this.leftleg2 = this.ant.getChild("leftleg2");
        this.rigthleg3 = this.ant.getChild("rigthleg3");
        this.leftleg3 = this.ant.getChild("leftleg3");
        this.tail = this.ant.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition ant = partdefinition.addOrReplaceChild("ant", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head = ant.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 13).addBox(-3.0F, -8.0F, -9.0F, 6.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rightstag = head.addOrReplaceChild("rightstag", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = rightstag.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 27).addBox(0.0F, 0.001F, -3.0F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-3.9173F, -4.9152F, -10.218F, 0.2686F, -0.4115F, -0.201F));

        PartDefinition cube_r2 = rightstag.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(22, 13).addBox(-2.0F, -2.0F, -2.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.02F)), PartPose.offsetAndRotation(-1.2F, -3.5F, -8.6F, 0.2618F, 0.3491F, 0.0F));

        PartDefinition leftstag = head.addOrReplaceChild("leftstag", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r3 = leftstag.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(8, 27).addBox(-1.0F, 0.001F, -3.0F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(3.9173F, -4.9152F, -10.218F, 0.2686F, 0.4115F, 0.201F));

        PartDefinition cube_r4 = leftstag.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(22, 17).addBox(0.0F, -2.0F, -2.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.02F)), PartPose.offsetAndRotation(1.2F, -3.5F, -8.6F, 0.2618F, -0.3491F, 0.0F));

        PartDefinition righthone = head.addOrReplaceChild("righthone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r5 = righthone.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(22, 28).addBox(1.0F, -2.0F, -4.0F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.01F))
                .texOffs(26, 21).addBox(1.0F, -2.0F, -3.0F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-3.0F, -7.0F, -8.0F, -0.5526F, 0.3713F, -0.3195F));

        PartDefinition lefthone = head.addOrReplaceChild("lefthone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r6 = lefthone.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(30, 4).addBox(-1.0F, -2.0F, -4.0F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.01F))
                .texOffs(26, 26).addBox(-1.0F, -2.0F, -3.0F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(3.0F, -7.0F, -8.0F, -0.5526F, -0.3713F, 0.3195F));

        PartDefinition body = ant.addOrReplaceChild("body", CubeListBuilder.create().texOffs(14, 21).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 21).addBox(-2.0F, -6.0F, -4.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rigthleg1 = ant.addOrReplaceChild("rigthleg1", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

        PartDefinition cube_r7 = rigthleg1.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(24, 27).addBox(-3.0F, -1.0F, -1.0F, 1.0F, 6.0F, 0.0F, new CubeDeformation(0.01F))
                .texOffs(0, 30).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-3.0F, -3.0F, -2.0F, 0.0F, 0.0F, 0.4363F));

        PartDefinition leftleg1 = ant.addOrReplaceChild("leftleg1", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

        PartDefinition cube_r8 = leftleg1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(16, 28).addBox(2.0F, -1.0F, -1.0F, 1.0F, 6.0F, 0.0F, new CubeDeformation(0.01F))
                .texOffs(30, 2).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(3.0F, -3.0F, -2.0F, 0.0F, 0.0F, -0.4363F));

        PartDefinition rigthleg2 = ant.addOrReplaceChild("rigthleg2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r9 = rigthleg2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(28, 1).addBox(-3.0F, -1.0F, 1.0F, 1.0F, 6.0F, 0.0F, new CubeDeformation(0.01F))
                .texOffs(30, 1).addBox(-2.0F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-1.9F, -3.0F, -1.3F, 0.0F, 0.0F, 0.3927F));

        PartDefinition leftleg2 = ant.addOrReplaceChild("leftleg2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r10 = leftleg2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(18, 28).addBox(2.0F, -1.0F, 1.0F, 1.0F, 6.0F, 0.0F, new CubeDeformation(0.01F))
                .texOffs(30, 3).addBox(-1.0F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(1.9F, -3.0F, -1.3F, 0.0F, 0.0F, -0.3927F));

        PartDefinition rigthleg3 = ant.addOrReplaceChild("rigthleg3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, 0.3491F, 0.0F));

        PartDefinition cube_r11 = rigthleg3.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(28, 7).addBox(-4.0F, -1.0F, 1.0F, 1.0F, 6.0F, 0.0F, new CubeDeformation(0.01F))
                .texOffs(16, 27).addBox(-3.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-2.0F, -3.0F, 1.0F, 0.0F, 0.0F, 0.3491F));

        PartDefinition leftleg3 = ant.addOrReplaceChild("leftleg3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, -0.3491F, 0.0F));

        PartDefinition cube_r12 = leftleg3.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(20, 28).addBox(3.0F, -1.0F, 1.0F, 1.0F, 6.0F, 0.0F, new CubeDeformation(0.01F))
                .texOffs(28, 0).addBox(-1.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(2.0F, -3.0F, 1.0F, 0.0F, 0.0F, -0.3491F));

        PartDefinition tail = ant.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -7.0F, 3.0F, 6.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);

    }


    @Override
    public void setupAnim(Ant entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
        if(entity.walkAnimation.isMoving()) {
            this.animateWalk(ModAnimationDefinitions.ANT_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        } else {
            this.animate(entity.idleAnimationState, ModAnimationDefinitions.ANT_IDLE, ageInTicks, 1f);
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
        ant.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return ant;
    }

    public ModelPart getHead() {
        return head;
    }
}