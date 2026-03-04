package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.Indicatoridae;

public class IndicatoridaeModel<T extends Indicatoridae> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    //public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "ant"), "main");
    private final ModelPart all;
    private final ModelPart body;
    private final ModelPart face;
    private final ModelPart tale;
    private final ModelPart rightleg;
    private final ModelPart rightwing;
    private final ModelPart leftleg;
    private final ModelPart leftwing;

    public IndicatoridaeModel(ModelPart root) {
        this.all = root.getChild("all");
        this.body = this.all.getChild("body");
        this.face = this.all.getChild("face");
        this.tale = this.all.getChild("tale");
        this.rightleg = this.all.getChild("rightleg");
        this.rightwing = this.all.getChild("rightwing");
        this.leftleg = this.all.getChild("leftleg");
        this.leftwing = this.all.getChild("leftwing");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = all.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(-2.0F, -7.0F, -1.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 10).addBox(-2.0F, -3.0F, -1.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(22, 0).addBox(2.0F, -6.0F, -1.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(12, 22).addBox(-3.0F, -6.0F, -1.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition face = all.addOrReplaceChild("face", CubeListBuilder.create().texOffs(14, 30).addBox(-1.0F, -5.0F, -3.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(-0.01F))
                .texOffs(34, 30).addBox(-1.5F, -5.0F, -2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(34, 34).addBox(0.5F, -5.0F, -2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(34, 4).addBox(-1.0F, -6.0F, -2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -2.0F));

        PartDefinition cube_r1 = face.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(26, 35).addBox(-1.0F, -8.0F, -6.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 3.5F, 2.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r2 = face.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(26, 30).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 4.0F, 0.2618F, 0.0F, 0.0F));

        PartDefinition tale = all.addOrReplaceChild("tale", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, 4.0F));

        PartDefinition cube_r3 = tale.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(18, 16).addBox(-2.3681F, -1.0F, 1.2412F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -0.3491F, 0.0F));

        PartDefinition cube_r4 = tale.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 30).addBox(0.2028F, -1.0F, 1.1851F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.3054F, 0.0F));

        PartDefinition cube_r5 = tale.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(18, 10).addBox(-1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

        PartDefinition rightleg = all.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(22, 9).addBox(-1.0F, 2.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(4, 36).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.02F)), PartPose.offset(-1.0F, -2.0F, 0.0F));

        PartDefinition rightwing = all.addOrReplaceChild("rightwing", CubeListBuilder.create(), PartPose.offset(-3.0F, -6.0F, 0.0F));

        PartDefinition cube_r6 = rightwing.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(32, 13).addBox(-1.0F, 0.0F, 4.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(34, 0).addBox(-1.0F, 3.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 22).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

        PartDefinition leftleg = all.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(0, 36).addBox(0.0F, -1.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.02F))
                .texOffs(22, 8).addBox(0.0F, 2.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -2.0F, 0.0F));

        PartDefinition leftwing = all.addOrReplaceChild("leftwing", CubeListBuilder.create(), PartPose.offset(3.0F, -6.0F, 0.0F));

        PartDefinition cube_r7 = leftwing.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(32, 8).addBox(0.0F, 0.0F, 4.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(24, 22).addBox(0.0F, 0.0F, -1.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(32, 18).addBox(0.0F, 3.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

        return LayerDefinition.create(meshdefinition, 64, 64);

    }

    @Override
    public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(p_102622_, p_102623_, p_102621_);
        if(p_102618_.onGround()) {
            this.animateWalk(ModAnimationDefinitions.INDICATOR_WALK, p_102619_, p_102620_, 2f, 2.5f);
        } else {
            this.animateWalk(ModAnimationDefinitions.INDICATOR_FLY, p_102619_, p_102620_, 2f, 2.5f);
        }
    }

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.face.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.face.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        all.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return all;
    }
}