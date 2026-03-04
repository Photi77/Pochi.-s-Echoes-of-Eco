package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.Etupirka;

public class EtupirkaModel<T extends Etupirka> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    //public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "etupirka"), "main");
    private final ModelPart etupirka;
    private final ModelPart entire;
    private final ModelPart leftleg;
    private final ModelPart rightleg;
    private final ModelPart head;
    private final ModelPart leftwing;
    private final ModelPart rightwing;
    private final ModelPart body;

    public EtupirkaModel(ModelPart root) {
        this.etupirka = root.getChild("etupirka");
        this.entire = this.etupirka.getChild("entire");
        this.leftleg = this.entire.getChild("leftleg");
        this.rightleg = this.entire.getChild("rightleg");
        this.head = this.entire.getChild("head");
        this.leftwing = this.entire.getChild("leftwing");
        this.rightwing = this.entire.getChild("rightwing");
        this.body = this.entire.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition etupirka = partdefinition.addOrReplaceChild("etupirka", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition entire = etupirka.addOrReplaceChild("entire", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leftleg = entire.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(46, 48).addBox(1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(18, 49).addBox(1.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -6.0F, -1.0F));

        PartDefinition cube_r1 = leftleg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(50, 42).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 6.0F, -0.5F, 0.0F, 0.0873F, 0.0F));

        PartDefinition cube_r2 = leftleg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(42, 6).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 5.8F, -0.1F, 0.0F, 0.0873F, 0.0F));

        PartDefinition cube_r3 = leftleg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(50, 23).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(2.0F, 6.0F, -0.5F, 0.0F, -0.6109F, 0.0F));

        PartDefinition cube_r4 = leftleg.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 50).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(2.0F, 6.0F, -0.5F, 0.0F, 0.7854F, 0.0F));

        PartDefinition rightleg = entire.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(10, 49).addBox(-3.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 54).addBox(-2.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -6.0F, -1.0F));

        PartDefinition cube_r5 = rightleg.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(46, 53).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 6.0F, -0.5F, 0.0F, -0.0873F, 0.0F));

        PartDefinition cube_r6 = rightleg.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(48, 36).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 5.8F, -0.1F, 0.0F, -0.0873F, 0.0F));

        PartDefinition cube_r7 = rightleg.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(52, 19).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-2.0F, 6.0F, -0.5F, 0.0F, 0.6109F, 0.0F));

        PartDefinition cube_r8 = rightleg.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(52, 15).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-2.0F, 6.0F, -0.5F, 0.0F, -0.7854F, 0.0F));

        PartDefinition head = entire.addOrReplaceChild("head", CubeListBuilder.create().texOffs(36, 23).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(22, 48).addBox(-3.0F, -3.0F, -2.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(30, 48).addBox(2.0F, -3.0F, -2.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 46).addBox(-2.0F, -3.0F, 1.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(12, 44).addBox(-1.5F, -3.0F, -4.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(50, 27).addBox(-1.5F, -3.5F, -3.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 44).addBox(-2.5F, -3.0F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 54).addBox(1.5F, -3.0F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(10, 54).addBox(-1.0F, -2.3F, -5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 46).addBox(-1.0F, -1.4F, -6.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, -7.0F));

        PartDefinition cube_r9 = head.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(54, 8).addBox(2.0F, -5.0F, -2.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-5.0F, 0.5F, 1.0F, 0.0F, 0.0F, 0.2182F));

        PartDefinition cube_r10 = head.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(5, 54).addBox(2.0F, -5.0F, -2.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.02F)), PartPose.offsetAndRotation(-5.0081F, 0.5367F, 0.0966F, -0.7418F, 0.0F, 0.2182F));

        PartDefinition cube_r11 = head.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(54, 4).addBox(-2.0F, -5.0F, -2.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.02F)), PartPose.offsetAndRotation(5.0081F, 0.5367F, 0.0966F, -0.7418F, 0.0F, -0.2182F));

        PartDefinition cube_r12 = head.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(54, 0).addBox(-2.0F, -5.0F, -2.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(5.0F, 0.5F, 1.0F, 0.0F, 0.0F, -0.2182F));

        PartDefinition leftwing = entire.addOrReplaceChild("leftwing", CubeListBuilder.create(), PartPose.offset(3.0F, -12.0F, -3.0F));

        PartDefinition cube_r13 = leftwing.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(26, 42).addBox(0.0F, 2.0F, -1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(48, 30).addBox(0.0F, -2.0F, 6.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 9).addBox(0.0F, -2.0F, -2.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -0.534F, -0.1886F, -0.4568F));

        PartDefinition rightwing = entire.addOrReplaceChild("rightwing", CubeListBuilder.create(), PartPose.offset(-3.0F, -12.0F, -3.0F));

        PartDefinition cube_r14 = rightwing.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(38, 42).addBox(-1.0F, 2.0F, -1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(38, 48).addBox(-1.0F, -2.0F, 6.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(18, 9).addBox(-1.0F, -2.0F, -2.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -0.534F, 0.1886F, 0.4568F));

        PartDefinition body = entire.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r15 = body.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(18, 30).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 1.0F, 7.0F, new CubeDeformation(-0.01F))
                .texOffs(0, 0).addBox(-2.0F, -8.0F, -4.0F, 4.0F, 1.0F, 8.0F, new CubeDeformation(-0.01F))
                .texOffs(18, 21).addBox(-3.0F, -7.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(-0.01F))
                .texOffs(24, 0).addBox(2.0F, -7.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 1.0F, 5.0F, 0.0436F, 0.0F, 0.0F));

        PartDefinition cube_r16 = body.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 38).addBox(2.0F, -7.0F, -1.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(36, 30).addBox(7.0F, -7.0F, -1.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(36, 9).addBox(3.0F, -4.0F, -1.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 21).addBox(3.0F, -8.0F, -1.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(26, 38).addBox(3.0F, -9.0F, -4.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(12, 38).addBox(4.0F, -9.0F, -1.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(40, 38).addBox(3.0F, -3.0F, -4.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(42, 0).addBox(3.0F, -7.0F, -6.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 30).addBox(2.0F, -8.0F, -4.0F, 6.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -4.0F, -5.0F, -0.7418F, 0.0F, 0.0F));

        PartDefinition cube_r17 = body.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(36, 15).addBox(-2.0F, -7.0F, -9.0F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -5.2F, -10.0F, -1.309F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);

    }

    @Override
    public void setupAnim(Etupirka p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(p_102622_, p_102623_, p_102621_);
        if(p_102618_.onGround()) {
            this.animateWalk(ModAnimationDefinitions.ETUPIRKA_WALK, p_102619_, p_102620_, 2f, 2.5f);
        } else {
            this.animateWalk(ModAnimationDefinitions.ETUPIRKA_FY, p_102619_, p_102620_, 2f, 2.5f);
        }

        this.animate(p_102618_.idleAnimationState, ModAnimationDefinitions.ETUPIRKA_IDLE, p_102621_, 1f);
    }

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        etupirka.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return etupirka;
    }
}
