package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.LeafySeaDragon;

public class LeafySeaDragonModel<T extends LeafySeaDragon> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    //public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "ant"), "main");
    private final ModelPart leeafyseadragon;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leaf;

    public LeafySeaDragonModel(ModelPart root) {
        this.leeafyseadragon = root.getChild("leeafyseadragon");
        this.body = this.leeafyseadragon.getChild("body");
        this.head = this.leeafyseadragon.getChild("head");
        this.leaf = this.leeafyseadragon.getChild("leaf");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition leeafyseadragon = partdefinition.addOrReplaceChild("leeafyseadragon", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = leeafyseadragon.addOrReplaceChild("body", CubeListBuilder.create().texOffs(50, 0).addBox(-2.0F, -13.0F, -6.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(50, 16).addBox(-2.0F, -19.0F, -9.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(10, 53).addBox(-2.0F, -18.0F, -12.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 36).addBox(-0.5F, -16.0F, -17.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(-0.01F))
                .texOffs(58, 36).addBox(-1.0F, -16.0F, -8.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(50, 24).addBox(-2.0F, -11.0F, -2.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(50, 8).addBox(-2.0F, -12.0F, 1.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(58, 31).addBox(-2.0F, 1.0F, 2.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(10, 45).addBox(-1.5F, 8.0F, 5.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(32, 53).addBox(-2.0F, 1.0F, 5.0F, 3.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -8.0F, 2.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(42, 53).addBox(-2.0F, 13.0F, 6.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -7.5F, 2.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition head = leeafyseadragon.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leaf = leeafyseadragon.addOrReplaceChild("leaf", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r3 = leaf.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(24, 53).addBox(0.0F, -8.0F, -4.0F, 0.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.0F, -6.0F, 0.48F, 0.0F, 0.0F));

        PartDefinition cube_r4 = leaf.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(40, 0).addBox(-1.0F, -12.0F, -5.0F, 0.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 15.0F, -0.5659F, -0.0151F, -0.1739F));

        PartDefinition cube_r5 = leaf.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(30, 0).addBox(-1.0F, -13.0F, -5.0F, 0.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -12.0F, 3.0F, -0.48F, 0.0F, -0.1745F));

        PartDefinition cube_r6 = leaf.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(20, 18).addBox(1.0F, -13.0F, -5.0F, 0.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -12.0F, 3.0F, -0.48F, 0.0F, 0.1745F));

        PartDefinition cube_r7 = leaf.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(20, 0).addBox(-1.0F, -13.0F, -5.0F, 0.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -13.0F, -3.0F, -0.48F, 0.0F, -0.1745F));

        PartDefinition cube_r8 = leaf.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(28, 36).addBox(-1.0F, -12.0F, -5.0F, 0.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 12.0F, -0.5659F, -0.0151F, -0.1739F));

        PartDefinition cube_r9 = leaf.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(10, 18).addBox(-1.0F, -13.0F, -5.0F, 0.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 15.0F, -1.2204F, -0.0151F, -0.1739F));

        PartDefinition cube_r10 = leaf.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(38, 36).addBox(1.0F, -12.0F, -5.0F, 0.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 15.0F, -0.5659F, 0.0151F, 0.1739F));

        PartDefinition cube_r11 = leaf.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(30, 18).addBox(1.0F, -13.0F, -5.0F, 0.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 15.0F, -1.2204F, 0.0151F, 0.1739F));

        PartDefinition cube_r12 = leaf.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(18, 36).addBox(1.0F, -12.0F, -5.0F, 0.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 12.0F, -0.5659F, 0.0151F, 0.1739F));

        PartDefinition cube_r13 = leaf.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 18).addBox(1.0F, -13.0F, -5.0F, 0.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -13.0F, -3.0F, -0.48F, 0.0F, 0.1745F));

        PartDefinition cube_r14 = leaf.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(10, 0).addBox(-1.0F, -11.0F, -1.0F, 0.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, -3.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition cube_r15 = leaf.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(48, 48).addBox(-1.0F, -11.0F, -1.0F, 0.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 5.0F, 0.2182F, 0.0F, 0.2618F));

        PartDefinition cube_r16 = leaf.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(40, 17).addBox(-1.0F, -11.0F, -1.0F, 0.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -3.0F, -11.0F, -0.3491F, 0.0F, 0.2618F));

        PartDefinition cube_r17 = leaf.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 45).addBox(1.0F, -11.0F, -1.0F, 0.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -3.0F, -11.0F, -0.3491F, 0.0F, -0.2618F));

        PartDefinition cube_r18 = leaf.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, -11.0F, -1.0F, 0.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, -3.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition cube_r19 = leaf.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(48, 33).addBox(1.0F, -11.0F, -1.0F, 0.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 5.0F, 0.2182F, 0.0F, -0.2618F));

        return LayerDefinition.create(meshdefinition, 128, 128);

    }

    @Override
    public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(p_102622_, p_102623_, p_102621_);
        this.animateWalk(ModAnimationDefinitions.LEAFY_SWIM, p_102619_, p_102620_, 2f, 2.5f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        leeafyseadragon.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public ModelPart root() {
        return leeafyseadragon;
    }

}