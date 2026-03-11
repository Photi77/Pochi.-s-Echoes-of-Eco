package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.FruitFly;

public class FruitFlyModel extends EntityModel<LivingEntityRenderState> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    //public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath("modid", "ant"), "main");
    private final ModelPart drosophila;
    private final ModelPart body;
    private final ModelPart lwing;
    private final ModelPart rwing;

    public FruitFlyModel(ModelPart root) {
        super(root.getChild("drosophila"));
        this.drosophila = root.getChild("drosophila");
        this.body = this.drosophila.getChild("body");
        this.lwing = this.drosophila.getChild("lwing");
        this.rwing = this.drosophila.getChild("rwing");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition drosophila = partdefinition.addOrReplaceChild("drosophila", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = drosophila.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 26).addBox(-4.0F, -3.0F, -7.0F, 8.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 26).addBox(-3.0F, -3.0F, -1.0F, 6.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(28, 36).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

        PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(40, 3).addBox(-9.0F, 0.0F, -1.0F, 7.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 4.0F, 0.0F, 0.0F, -0.7418F));

        PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(40, 1).addBox(-9.0F, 0.0F, -1.0F, 7.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 1.0F, 0.0F, -0.1745F, -0.7418F));

        PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 40).addBox(-9.0F, 0.0F, -1.0F, 7.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 6.0F, 0.0F, 0.1745F, -0.7418F));

        PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(40, 2).addBox(2.0F, 0.0F, -1.0F, 7.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 6.0F, 0.0F, -0.1745F, 0.7418F));

        PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(40, 0).addBox(2.0F, 0.0F, -1.0F, 7.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 4.0F, 0.0F, 0.0F, 0.7418F));

        PartDefinition cube_r6 = body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 39).addBox(2.0F, 0.0F, -1.0F, 7.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 1.0F, 0.0F, 0.1745F, 0.7418F));

        PartDefinition lwing = drosophila.addOrReplaceChild("lwing", CubeListBuilder.create(), PartPose.offset(1.0F, -7.0F, 0.0F));

        PartDefinition cube_r7 = lwing.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 7.0F, 0.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -0.1F, 0.0F, 0.0709F, 0.1741F, 0.0123F));

        PartDefinition rwing = drosophila.addOrReplaceChild("rwing", CubeListBuilder.create(), PartPose.offset(-1.0F, -7.0F, 0.0F));

        PartDefinition cube_r8 = rwing.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 13).addBox(-6.0F, 0.0F, -1.0F, 7.0F, 0.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -0.1F, 0.0F, 0.0709F, -0.1741F, -0.0123F));

        return LayerDefinition.create(meshdefinition, 64, 64);

    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        ModAnimationDefinitions.FRUIT_FLY.bake(this.root()).applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 2f, 2.5f);
    }
}