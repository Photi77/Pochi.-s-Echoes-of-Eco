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
import net.pochi.pochimod.entity.custom.LongTailTit;

public class LongTailTitModel extends EntityModel<LivingEntityRenderState> {
    //public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath("modid", "shimaenaga_- converted"), "main");
    //private final ModelPart longtail;
    private final ModelPart bone;
    private final ModelPart body;
    private final ModelPart left;
    private final ModelPart right;

    public LongTailTitModel(ModelPart root) {
        super(root.getChild("bone"));
        this.bone = root.getChild("bone");
        this.body = this.bone.getChild("body");
        this.left = this.bone.getChild("left");
        this.right = this.bone.getChild("right");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(42, 25).addBox(-4.0F, -8.0F, 3.0F, 8.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(34, 36).addBox(-6.0F, -9.0F, -6.0F, 1.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(40, 0).addBox(5.0F, -9.0F, -6.0F, 1.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(24, 52).addBox(-3.0F, -5.0F, 4.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(52, 47).addBox(-1.0F, -6.7F, -11.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(42, 16).addBox(-4.0F, -9.0F, -9.0F, 8.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 36).addBox(-4.0F, -11.0F, -7.0F, 8.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(42, 34).addBox(-4.0F, -10.0F, -8.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 36).addBox(-4.0F, -3.0F, -8.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-5.0F, -10.0F, -7.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 2.0F));

        PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 19).addBox(-2.0F, -3.0F, 7.0F, 4.0F, 0.0F, 17.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(40, 52).addBox(-4.0F, -12.0F, -6.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, -5.0F, -2.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(52, 38).addBox(-4.0F, -12.0F, -6.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -5.0F, -2.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition left = bone.addOrReplaceChild("left", CubeListBuilder.create().texOffs(0, 46).addBox(0.0F, -1.0F, -1.0F, 1.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(52, 40).addBox(0.0F, -1.0F, 4.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(46, 54).addBox(0.0F, -1.0F, 6.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -6.0F, -1.0F));

        PartDefinition right = bone.addOrReplaceChild("right", CubeListBuilder.create().texOffs(12, 46).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(40, 54).addBox(-1.0F, -1.0F, 4.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(24, 46).addBox(-1.0F, -1.0F, 6.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -6.0F, -1.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        //this.animateWalk(ModAnimationDefinitions.LONG_TAIL_FLY, state.walkAnimationPos, state.walkAnimationSpeed, 2f, 2.5f);
        if(state.walkAnimationSpeed <= 0.01F){
            ModAnimationDefinitions.LONG_TAIL_FLY.bake(this.root()).applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 2f, 2.5f);
        }else {
            // TODO: idle animation state not available in render state
        }
    }
}
