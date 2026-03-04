package net.pochi.pochimod.item.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.pochi.pochimod.pottery.PotteryShape;
import org.joml.Matrix4f;

public class PotteryItemRenderer {

    public static void renderPottery(ItemStack stack, ItemDisplayContext context,
                                     PoseStack poseStack, MultiBufferSource buffer,
                                     int combinedLight, int combinedOverlay) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData == null) return;
        CompoundTag tag = customData.copyTag();

        int height = tag.getInt("Height");
        int diameter = tag.getInt("Diameter");
        int thickness = tag.getInt("WallThickness");
        int mouth = tag.getInt("MouthWidth");
        String shapeStr = tag.getString("Shape");
        int color = tag.getInt("GlazeColor");

        PotteryShape shape = PotteryShape.valueOf(shapeStr);

        poseStack.pushPose();

        // スケール調整
        float scale = 0.1f;
        poseStack.scale(scale, scale, scale);
        poseStack.translate(0, 0, 0);

        VertexConsumer consumer = buffer.getBuffer(RenderType.solid());

        // 形状に応じた描画
        switch (shape) {
            case BOWL -> renderBowl(poseStack, consumer, height, diameter, mouth,
                    thickness, color, combinedLight, combinedOverlay);
            case CUP -> renderCup(poseStack, consumer, height, diameter, mouth,
                    thickness, color, combinedLight, combinedOverlay);
            case PLATE -> renderPlate(poseStack, consumer, height, diameter, mouth,
                    thickness, color, combinedLight, combinedOverlay);
            case VASE -> renderVase(poseStack, consumer, height, diameter, mouth,
                    thickness, color, combinedLight, combinedOverlay);
            case TEAPOT -> renderTeapot(poseStack, consumer, height, diameter, mouth,
                    thickness, color, combinedLight, combinedOverlay);
        }

        poseStack.popPose();
    }

    private static void renderBowl(PoseStack poseStack, VertexConsumer consumer,
                                   int height, int diameter, int mouth, int thickness,
                                   int color, int light, int overlay) {
        Matrix4f matrix = poseStack.last().pose();

        float r = ((color >> 16) & 0xFF) / 255f;
        float g = ((color >> 8) & 0xFF) / 255f;
        float b = (color & 0xFF) / 255f;

        float baseRadius = diameter * 0.5f;
        float topRadius = mouth * 0.5f;
        float h = height * 1.0f;

        int segments = 16;

        // 外側の面を描画
        for (int i = 0; i < segments; i++) {
            float angle1 = (float) (2 * Math.PI * i / segments);
            float angle2 = (float) (2 * Math.PI * (i + 1) / segments);

            float x1Bottom = (float) (Math.cos(angle1) * baseRadius);
            float z1Bottom = (float) (Math.sin(angle1) * baseRadius);
            float x2Bottom = (float) (Math.cos(angle2) * baseRadius);
            float z2Bottom = (float) (Math.sin(angle2) * baseRadius);

            float x1Top = (float) (Math.cos(angle1) * topRadius);
            float z1Top = (float) (Math.sin(angle1) * topRadius);
            float x2Top = (float) (Math.cos(angle2) * topRadius);
            float z2Top = (float) (Math.sin(angle2) * topRadius);

            // 下三角
            consumer.addVertex(matrix, x1Bottom, 0, z1Bottom)
                    .setColor(r, g, b, 1.0f).setUv(0, 0).setOverlay(overlay).setLight(light).setNormal(0, 1, 0);
            consumer.addVertex(matrix, x2Bottom, 0, z2Bottom)
                    .setColor(r, g, b, 1.0f).setUv(1, 0).setOverlay(overlay).setLight(light).setNormal(0, 1, 0);
            consumer.addVertex(matrix, x2Top, h, z2Top)
                    .setColor(r, g, b, 1.0f).setUv(1, 1).setOverlay(overlay).setLight(light).setNormal(0, 1, 0);

            // 上三角
            consumer.addVertex(matrix, x1Bottom, 0, z1Bottom)
                    .setColor(r, g, b, 1.0f).setUv(0, 0).setOverlay(overlay).setLight(light).setNormal(0, 1, 0);
            consumer.addVertex(matrix, x2Top, h, z2Top)
                    .setColor(r, g, b, 1.0f).setUv(1, 1).setOverlay(overlay).setLight(light).setNormal(0, 1, 0);
            consumer.addVertex(matrix, x1Top, h, z1Top)
                    .setColor(r, g, b, 1.0f).setUv(0, 1).setOverlay(overlay).setLight(light).setNormal(0, 1, 0);
        }

        // 底面
        for (int i = 0; i < segments; i++) {
            float angle1 = (float) (2 * Math.PI * i / segments);
            float angle2 = (float) (2 * Math.PI * (i + 1) / segments);

            float x1 = (float) (Math.cos(angle1) * baseRadius);
            float z1 = (float) (Math.sin(angle1) * baseRadius);
            float x2 = (float) (Math.cos(angle2) * baseRadius);
            float z2 = (float) (Math.sin(angle2) * baseRadius);

            consumer.addVertex(matrix, 0, 0, 0)
                    .setColor(r, g, b, 1.0f).setUv(0.5f, 0.5f).setOverlay(overlay).setLight(light).setNormal(0, -1, 0);
            consumer.addVertex(matrix, x2, 0, z2)
                    .setColor(r, g, b, 1.0f).setUv(1, 0).setOverlay(overlay).setLight(light).setNormal(0, -1, 0);
            consumer.addVertex(matrix, x1, 0, z1)
                    .setColor(r, g, b, 1.0f).setUv(0, 0).setOverlay(overlay).setLight(light).setNormal(0, -1, 0);
        }
    }

    private static void renderCup(PoseStack poseStack, VertexConsumer consumer,
                                  int height, int diameter, int mouth, int thickness,
                                  int color, int light, int overlay) {
        renderBowl(poseStack, consumer, height, diameter, mouth, thickness,
                color, light, overlay);

        Matrix4f matrix = poseStack.last().pose();
        float r = ((color >> 16) & 0xFF) / 255f;
        float g = ((color >> 8) & 0xFF) / 255f;
        float b = (color & 0xFF) / 255f;

        float handleRadius = diameter * 0.6f;
        float handleHeight = height * 0.7f;
        float handleThickness = 0.3f;

        consumer.addVertex(matrix, handleRadius, handleHeight * 0.3f, 0)
                .setColor(r, g, b, 1.0f).setUv(0, 0).setOverlay(overlay).setLight(light).setNormal(1, 0, 0);
        consumer.addVertex(matrix, handleRadius + handleThickness, handleHeight * 0.3f, 0)
                .setColor(r, g, b, 1.0f).setUv(1, 0).setOverlay(overlay).setLight(light).setNormal(1, 0, 0);
        consumer.addVertex(matrix, handleRadius + handleThickness, handleHeight, 0)
                .setColor(r, g, b, 1.0f).setUv(1, 1).setOverlay(overlay).setLight(light).setNormal(1, 0, 0);
        consumer.addVertex(matrix, handleRadius, handleHeight, 0)
                .setColor(r, g, b, 1.0f).setUv(0, 1).setOverlay(overlay).setLight(light).setNormal(1, 0, 0);
    }

    private static void renderPlate(PoseStack poseStack, VertexConsumer consumer,
                                    int height, int diameter, int mouth, int thickness,
                                    int color, int light, int overlay) {
        renderBowl(poseStack, consumer, Math.max(1, height / 3), diameter, mouth,
                thickness, color, light, overlay);
    }

    private static void renderVase(PoseStack poseStack, VertexConsumer consumer,
                                   int height, int diameter, int mouth, int thickness,
                                   int color, int light, int overlay) {
        Matrix4f matrix = poseStack.last().pose();

        float r = ((color >> 16) & 0xFF) / 255f;
        float g = ((color >> 8) & 0xFF) / 255f;
        float b = (color & 0xFF) / 255f;

        int segments = 16;
        int heightSegments = 10;

        for (int j = 0; j < heightSegments; j++) {
            float y1 = (height * 1.0f / heightSegments) * j;
            float y2 = (height * 1.0f / heightSegments) * (j + 1);

            float t1 = j / (float) heightSegments;
            float t2 = (j + 1) / (float) heightSegments;

            float radius1 = diameter * 0.5f * (1 + 0.5f * (float) Math.sin(t1 * Math.PI));
            float radius2 = diameter * 0.5f * (1 + 0.5f * (float) Math.sin(t2 * Math.PI));

            if (j >= heightSegments - 2) {
                radius2 = mouth * 0.5f;
            }

            for (int i = 0; i < segments; i++) {
                float angle1 = (float) (2 * Math.PI * i / segments);
                float angle2 = (float) (2 * Math.PI * (i + 1) / segments);

                float x1Bottom = (float) (Math.cos(angle1) * radius1);
                float z1Bottom = (float) (Math.sin(angle1) * radius1);
                float x2Bottom = (float) (Math.cos(angle2) * radius1);
                float z2Bottom = (float) (Math.sin(angle2) * radius1);

                float x1Top = (float) (Math.cos(angle1) * radius2);
                float z1Top = (float) (Math.sin(angle1) * radius2);
                float x2Top = (float) (Math.cos(angle2) * radius2);
                float z2Top = (float) (Math.sin(angle2) * radius2);

                consumer.addVertex(matrix, x1Bottom, y1, z1Bottom)
                        .setColor(r, g, b, 1.0f).setUv(0, 0).setOverlay(overlay).setLight(light).setNormal(0, 1, 0);
                consumer.addVertex(matrix, x2Bottom, y1, z2Bottom)
                        .setColor(r, g, b, 1.0f).setUv(1, 0).setOverlay(overlay).setLight(light).setNormal(0, 1, 0);
                consumer.addVertex(matrix, x2Top, y2, z2Top)
                        .setColor(r, g, b, 1.0f).setUv(1, 1).setOverlay(overlay).setLight(light).setNormal(0, 1, 0);

                consumer.addVertex(matrix, x1Bottom, y1, z1Bottom)
                        .setColor(r, g, b, 1.0f).setUv(0, 0).setOverlay(overlay).setLight(light).setNormal(0, 1, 0);
                consumer.addVertex(matrix, x2Top, y2, z2Top)
                        .setColor(r, g, b, 1.0f).setUv(1, 1).setOverlay(overlay).setLight(light).setNormal(0, 1, 0);
                consumer.addVertex(matrix, x1Top, y2, z1Top)
                        .setColor(r, g, b, 1.0f).setUv(0, 1).setOverlay(overlay).setLight(light).setNormal(0, 1, 0);
            }
        }
    }

    private static void renderTeapot(PoseStack poseStack, VertexConsumer consumer,
                                     int height, int diameter, int mouth, int thickness,
                                     int color, int light, int overlay) {
        renderVase(poseStack, consumer, height, diameter, mouth, thickness,
                color, light, overlay);

        Matrix4f matrix = poseStack.last().pose();
        float r = ((color >> 16) & 0xFF) / 255f;
        float g = ((color >> 8) & 0xFF) / 255f;
        float b = (color & 0xFF) / 255f;

        float spoutLength = diameter * 0.6f;
        float spoutHeight = height * 0.6f;

        consumer.addVertex(matrix, diameter * 0.5f, spoutHeight, 0)
                .setColor(r, g, b, 1.0f).setUv(0, 0).setOverlay(overlay).setLight(light).setNormal(1, 0, 0);
        consumer.addVertex(matrix, diameter * 0.5f + spoutLength, spoutHeight, 0)
                .setColor(r, g, b, 1.0f).setUv(1, 0).setOverlay(overlay).setLight(light).setNormal(1, 0, 0);
        consumer.addVertex(matrix, diameter * 0.5f + spoutLength * 0.5f, spoutHeight + 0.5f, 0)
                .setColor(r, g, b, 1.0f).setUv(0.5f, 1).setOverlay(overlay).setLight(light).setNormal(1, 0, 0);
    }
}
