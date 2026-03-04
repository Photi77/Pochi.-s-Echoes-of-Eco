package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.DeerEntity;

public class DeerModel<T extends DeerEntity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    //public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "deer"), "main");
    private final ModelPart deer;
    private final ModelPart rear;
    private final ModelPart leftfront;
    private final ModelPart rightfront;
    private final ModelPart leftrear;
    private final ModelPart rightrear;
    private final ModelPart body;
    private final ModelPart front;
    private final ModelPart neck;
    private final ModelPart head;

    public DeerModel(ModelPart root) {
        this.deer = root.getChild("deer");
        this.rear = this.deer.getChild("rear");
        this.leftfront = this.rear.getChild("leftfront");
        this.rightfront = this.rear.getChild("rightfront");
        this.leftrear = this.rear.getChild("leftrear");
        this.rightrear = this.rear.getChild("rightrear");
        this.body = this.rear.getChild("body");
        this.front = this.deer.getChild("front");
        this.neck = this.front.getChild("neck");
        this.head = this.front.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition deer = partdefinition.addOrReplaceChild("deer", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition rear = deer.addOrReplaceChild("rear", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leftfront = rear.addOrReplaceChild("leftfront", CubeListBuilder.create().texOffs(96, 47).addBox(5.5F, 9.0F, -1.7F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(102, 94).addBox(5.0F, 18.0F, -2.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(36, 89).addBox(5.0F, -2.0F, -2.0F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.001F))
                .texOffs(52, 105).addBox(5.0F, -1.0F, 1.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, -7.0F));

        PartDefinition rightfront = rear.addOrReplaceChild("rightfront", CubeListBuilder.create().texOffs(98, 60).addBox(-7.5F, 9.0F, -1.7F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(104, 36).addBox(-8.0F, 18.0F, -2.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(90, 8).addBox(-8.0F, -2.0F, -2.0F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.001F))
                .texOffs(58, 105).addBox(-7.0F, -1.0F, 1.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, -7.0F));

        PartDefinition leftrear = rear.addOrReplaceChild("leftrear", CubeListBuilder.create().texOffs(72, 10).addBox(5.0F, -3.0F, -3.0F, 3.0F, 9.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(18, 100).addBox(5.0F, 6.0F, -2.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(32, 103).addBox(5.0F, 8.0F, -1.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(102, 6).addBox(5.0F, 18.0F, -0.1F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(10, 93).addBox(5.5F, 9.0F, 0.6F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(98, 73).addBox(5.0F, -4.0F, -3.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(72, 96).addBox(5.0F, -3.0F, 3.0F, 2.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -21.0F, 9.0F));

        PartDefinition rightrear = rear.addOrReplaceChild("rightrear", CubeListBuilder.create().texOffs(0, 78).addBox(-8.0F, -3.0F, -3.0F, 3.0F, 9.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(86, 101).addBox(-8.0F, 6.0F, -2.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(104, 41).addBox(-8.0F, 8.0F, -1.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(102, 88).addBox(-8.0F, 18.0F, -0.1F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(78, 93).addBox(-7.5F, 9.0F, 0.6F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(100, 101).addBox(-7.0F, -4.0F, -3.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(104, 46).addBox(-7.0F, -3.0F, 3.0F, 2.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -21.0F, 9.0F));

        PartDefinition body = rear.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 20).addBox(-4.0F, -25.0F, -6.0F, 8.0F, 11.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 58).addBox(4.0F, -24.0F, -6.0F, 1.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(60, 49).addBox(-5.0F, -24.0F, -6.0F, 1.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(36, 20).addBox(-4.0F, -15.0F, -6.0F, 8.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(28, 49).addBox(-3.0F, -14.0F, -5.0F, 6.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(36, 32).addBox(-5.0F, -26.0F, -11.0F, 10.0F, 12.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(66, 38).addBox(-5.0F, -25.0F, -12.0F, 10.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 78).addBox(-4.0F, -24.0F, -13.0F, 8.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 90).addBox(-3.0F, -23.0F, -14.0F, 6.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(54, 80).addBox(5.0F, -25.0F, -11.0F, 1.0F, 11.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(66, 80).addBox(-6.0F, -25.0F, -11.0F, 1.0F, 11.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(92, 88).addBox(-7.0F, -24.0F, -10.0F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 93).addBox(6.0F, -24.0F, -10.0F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(66, 32).addBox(-5.0F, -15.0F, -10.0F, 10.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(38, 10).addBox(-4.0F, -26.0F, 5.0F, 8.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-5.0F, -25.0F, 4.0F, 10.0F, 11.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(36, 78).addBox(-4.0F, -24.0F, 13.0F, 8.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(92, 79).addBox(-3.0F, -23.0F, 14.0F, 6.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(88, 38).addBox(-3.0F, -24.0F, 15.0F, 6.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(22, 61).addBox(5.0F, -24.0F, 4.0F, 1.0F, 9.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(40, 61).addBox(-6.0F, -24.0F, 4.0F, 1.0F, 9.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(1, -4).addBox(-7.0F, -23.0F, 5.0F, 1.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(38, 0).addBox(-5.0F, -15.0F, 4.0F, 10.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition front = deer.addOrReplaceChild("front", CubeListBuilder.create(), PartPose.offset(0.0F, -20.0F, -12.0F));

        PartDefinition neck = front.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 41).addBox(-3.0F, -25.0F, -19.0F, 6.0F, 9.0F, 8.0F, new CubeDeformation(-0.001F))
                .texOffs(78, 80).addBox(3.0F, -24.0F, -18.0F, 1.0F, 7.0F, 6.0F, new CubeDeformation(-0.001F))
                .texOffs(82, 49).addBox(-4.0F, -24.0F, -18.0F, 1.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(74, 0).addBox(-2.0F, -17.0F, -17.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(102, 12).addBox(-2.0F, -24.0F, -20.0F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(80, 69).addBox(-2.0F, -30.0F, -17.0F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(48, 89).addBox(-1.0F, -30.0F, -18.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 105).addBox(2.0F, -30.0F, -16.0F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(28, 41).addBox(-3.0F, -30.0F, -16.0F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 12.0F));

        PartDefinition head = front.addOrReplaceChild("head", CubeListBuilder.create().texOffs(58, 69).addBox(-3.0F, -5.0F, -4.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(72, 25).addBox(-2.5F, -6.0F, -3.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(94, 30).addBox(-3.0F, -4.0F, -5.0F, 6.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(82, 62).addBox(-2.5F, -3.4F, -7.0F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(94, 0).addBox(-2.0F, -2.4F, -10.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                //.texOffs(106, 24).addBox(-1.0F, -1.9F, -10.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(90, 22).addBox(-3.0F, -5.0F, 1.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(104, 56).addBox(-2.0F, -3.0F, 1.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(48, 96).addBox(3.0F, -4.0F, -4.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(60, 96).addBox(-4.0F, -4.0F, -4.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, -3.0F));

        PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(22, 106).addBox(-0.1583F, -9.5496F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.4742F, -8.7839F, 2.4633F, -0.4385F, -0.1572F, -0.3315F));

        PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(18, 106).addBox(-0.1583F, -10.5496F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.4388F, -8.6295F, 2.4633F, -0.4628F, 0.0353F, 0.0614F));

        PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(8, 107).addBox(-0.1583F, -5.5496F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.4919F, -8.6823F, 2.4633F, -0.3638F, -0.2948F, -0.6611F));

        PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(68, 10).addBox(-0.1583F, -16.5496F, 0.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.0111F, -8.6096F, 2.4633F, -0.4611F, 0.0548F, 0.1005F));

        PartDefinition cube_r5 = head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(106, 79).addBox(-2.0F, -7.0F, 5.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0182F, -8.7875F, -3.1055F, -0.2927F, -0.3654F, -0.8794F));

        PartDefinition cube_r6 = head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(106, 66).addBox(-2.0F, -7.0F, 5.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -3.0F, -6.0F, -0.4623F, -0.0427F, -0.0948F));

        PartDefinition cube_r7 = head.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(32, 90).addBox(-0.8417F, -16.5496F, 0.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0111F, -8.6096F, 2.4633F, -0.4611F, -0.0548F, -0.1005F));

        PartDefinition cube_r8 = head.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(106, 20).addBox(-0.8417F, -10.5496F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.4388F, -8.6295F, 2.4633F, -0.4628F, -0.0353F, -0.0614F));

        PartDefinition cube_r9 = head.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(86, 93).addBox(-0.8417F, -9.5496F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.4742F, -8.7839F, 2.4633F, -0.4385F, 0.1572F, 0.3315F));

        PartDefinition cube_r10 = head.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(72, 106).addBox(-0.8417F, -5.5496F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.4919F, -8.6823F, 2.4633F, -0.3638F, 0.2948F, 0.6611F));

        PartDefinition cube_r11 = head.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(106, 60).addBox(1.0F, -7.0F, 5.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0182F, -8.7875F, -3.1055F, -0.2927F, 0.3654F, 0.8794F));

        PartDefinition cube_r12 = head.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(26, 106).addBox(1.0F, -7.0F, 5.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -3.0F, -6.0F, -0.4623F, 0.0427F, 0.0948F));

        PartDefinition cube_r13 = head.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 106).addBox(-5.0F, -5.0F, 6.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -5.1F, -6.4F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r14 = head.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(64, 105).addBox(2.0F, -5.0F, 6.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -5.1F, -6.4F, 0.0F, 0.0F, 0.3927F));

        return LayerDefinition.create(meshdefinition, 128, 128);



    }


    @Override
    public void setupAnim(DeerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
        if(entity.walkAnimation.isMoving()) {
            this.animateWalk(ModAnimationDefinitions.DEER_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        } else {
            this.animate(entity.idleAnimationState, ModAnimationDefinitions.DEER_IDLE, ageInTicks, 1f);
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
        deer.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return deer;
    }

}