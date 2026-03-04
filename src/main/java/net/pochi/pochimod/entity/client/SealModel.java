package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.pochi.pochimod.entity.animations.ModAnimationDefinitions;
import net.pochi.pochimod.entity.custom.Seal;

public class SealModel <T extends Seal> extends HierarchicalModel<T> {
    private final ModelPart bone;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart body1;
    private final ModelPart boy2;
    private final ModelPart bone6;
    private final ModelPart bone7;
    private final ModelPart bone8;
    private final ModelPart bone2;

    public SealModel(ModelPart root) {
        this.bone = root.getChild("bone");
        this.head = this.bone.getChild("head");
        this.body = this.bone.getChild("body");
        this.body1 = this.body.getChild("body1");
        this.boy2 = this.body.getChild("boy2");
        this.bone6 = this.body.getChild("bone6");
        this.bone7 = this.body.getChild("bone7");
        this.bone8 = this.body.getChild("bone8");
        this.bone2 = this.body.getChild("bone2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head = bone.addOrReplaceChild("head", CubeListBuilder.create().texOffs(28, 13).addBox(-3.0F, -5.0F, -5.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(52, 15).addBox(3.0F, -4.0F, -4.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(54, 24).addBox(-2.0F, -4.0F, -6.0F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(54, 34).addBox(-1.0F, -2.0F, -8.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(44, 51).addBox(-2.0F, -6.0F, -4.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 53).addBox(-4.0F, -4.0F, -4.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -5.0F));

        PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body1 = body.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-5.0F, -3.0F, 0.0F, 10.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(32, 7).addBox(-4.0F, 1.0F, -2.0F, 8.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(32, 0).addBox(-4.0F, 4.0F, 0.0F, 8.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -4.0F));

        PartDefinition boy2 = body.addOrReplaceChild("boy2", CubeListBuilder.create().texOffs(0, 46).addBox(-3.0F, -4.0F, -1.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 13).addBox(-4.0F, -3.0F, -1.0F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(50, 44).addBox(-3.0F, 3.0F, -1.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 3.0F));

        PartDefinition bone6 = body.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(54, 30).addBox(-2.0F, 0.0F, 3.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 25).addBox(-5.0F, 0.0F, 5.0F, 10.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(52, 7).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(12, 53).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 15.0F));

        PartDefinition bone7 = body.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(28, 37).addBox(-13.0F, -3.0F, -4.0F, 8.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone8 = body.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(0, 39).addBox(5.0F, -3.0F, -4.0F, 8.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone2 = body.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(30, 25).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(24, 51).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 8.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);

    }



    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
        this.animateWalk(ModAnimationDefinitions.SEAL_SWIM, limbSwing, limbSwingAmount, 1f, 1.5f);
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
