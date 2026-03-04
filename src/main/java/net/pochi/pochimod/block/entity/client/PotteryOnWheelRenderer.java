package net.pochi.pochimod.block.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.pochi.pochimod.block.entity.custom.PotteryOnWheelBlockEntity;
import net.pochi.pochimod.pottery.PotteryShape;
import net.pochi.pochimod.pottery.PotteryState;
import org.joml.Matrix4f;

public class PotteryOnWheelRenderer implements BlockEntityRenderer<PotteryOnWheelBlockEntity> {

    public PotteryOnWheelRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(PotteryOnWheelBlockEntity be, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int combinedLight, int combinedOverlay) {

        poseStack.pushPose();
        poseStack.translate(0.5, 0, 0.5);

        // パラメータ取得
        int height = be.getHeight();
        int diameter = be.getDiameter();
        int thickness = be.getWallThickness();
        int mouth = be.getMouthWidth();
        PotteryShape shape = be.getShape();
        int glazeColor = be.getGlazeColor();
        PotteryState state = be.getState();

        // テクスチャと色
        TextureAtlasSprite sprite = getTextureForState(state);
        float[] color = getColorForState(state, glazeColor);

        VertexConsumer consumer = buffer.getBuffer(RenderType.translucent());

        // 形状に応じたボクセル配置
        switch (shape) {
            case BOWL -> renderVoxelBowl(poseStack, consumer, height, diameter, mouth, thickness,
                    color, sprite, combinedLight, combinedOverlay);
            case CUP -> renderVoxelCup(poseStack, consumer, height, diameter, mouth, thickness,
                    color, sprite, combinedLight, combinedOverlay);
            case PLATE -> renderVoxelPlate(poseStack, consumer, height, diameter, mouth, thickness,
                    color, sprite, combinedLight, combinedOverlay);
            case VASE -> renderVoxelVase(poseStack, consumer, height, diameter, mouth, thickness,
                    color, sprite, combinedLight, combinedOverlay);
            case TEAPOT -> renderVoxelTeapot(poseStack, consumer, height, diameter, mouth, thickness,
                    color, sprite, combinedLight, combinedOverlay);
        }

        poseStack.popPose();
    }

    private TextureAtlasSprite getTextureForState(PotteryState state) {
        ResourceLocation textureLocation = switch (state) {
            case SHAPING -> ResourceLocation.fromNamespaceAndPath("minecraft", "block/clay");
            case DRYING -> ResourceLocation.fromNamespaceAndPath("minecraft", "block/terracotta");
            case GLAZEABLE -> ResourceLocation.fromNamespaceAndPath("minecraft", "block/white_terracotta");
            case FIRING -> ResourceLocation.fromNamespaceAndPath("minecraft", "block/bricks");
            case FINISHED -> ResourceLocation.fromNamespaceAndPath("minecraft", "block/smooth_stone");
        };

        return Minecraft.getInstance()
                .getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
                .apply(textureLocation);
    }

    private float[] getColorForState(PotteryState state, int glazeColor) {
        float r, g, b;

        if (state == PotteryState.SHAPING || state == PotteryState.DRYING) {
            r = g = b = 1.0f;
        } else {
            r = ((glazeColor >> 16) & 0xFF) / 255f;
            g = ((glazeColor >> 8) & 0xFF) / 255f;
            b = (glazeColor & 0xFF) / 255f;

            if (state == PotteryState.FIRING) {
                r = Math.min(1.0f, r + 0.3f);
            }
        }

        return new float[]{r, g, b};
    }

    /**
     * 碗型をボクセルで表現
     */
    private void renderVoxelBowl(PoseStack poseStack, VertexConsumer consumer,
                                 int height, int diameter, int mouth, int thickness,
                                 float[] color, TextureAtlasSprite sprite,
                                 int light, int overlay) {

        // 分割数（パラメータに応じて変更可能）
        int segments = 8 + (diameter / 2); // 直径が大きいほど滑らか

        // 高さ方向の分割
        int heightLayers = height;

        for (int layer = 0; layer < heightLayers; layer++) {
            float y = layer * 0.1f; // 各レイヤーの高さ
            float nextY = (layer + 1) * 0.1f;

            // 層ごとの半径を計算（下が大きく、上が小さい）
            float t = layer / (float) (heightLayers - 1);
            float baseRadius = diameter * 0.05f;
            float topRadius = mouth * 0.05f;
            float currentRadius = baseRadius + (topRadius - baseRadius) * t;

            // 壁の厚み
            float innerRadius = Math.max(0.02f, currentRadius - thickness * 0.01f);

            // 八角形（または多角形）でボクセル配置
            for (int i = 0; i < segments; i++) {
                float angle1 = (float) (2 * Math.PI * i / segments);
                float angle2 = (float) (2 * Math.PI * (i + 1) / segments);

                // 外側のボクセル位置
                float x1 = (float) Math.cos(angle1) * currentRadius;
                float z1 = (float) Math.sin(angle1) * currentRadius;
                float x2 = (float) Math.cos(angle2) * currentRadius;
                float z2 = (float) Math.sin(angle2) * currentRadius;

                // 内側のボクセル位置
                float innerX1 = (float) Math.cos(angle1) * innerRadius;
                float innerZ1 = (float) Math.sin(angle1) * innerRadius;
                float innerX2 = (float) Math.cos(angle2) * innerRadius;
                float innerZ2 = (float) Math.sin(angle2) * innerRadius;

                // ボクセルの厚みを計算（外側と内側の差）
                float voxelThickness = currentRadius - innerRadius;

                // ボクセルブロックを描画（台形状）
                renderVoxelBlock(poseStack, consumer,
                        x1, y, z1,
                        x2, y, z2,
                        innerX2, nextY, innerZ2,
                        innerX1, nextY, innerZ1,
                        voxelThickness,
                        color, sprite, light, overlay);
            }
        }

        // 底面
        renderVoxelBottom(poseStack, consumer, diameter * 0.05f, thickness,
                color, sprite, light, overlay);
    }

    /**
     * カップ型（碗＋取っ手）
     */
    private void renderVoxelCup(PoseStack poseStack, VertexConsumer consumer,
                                int height, int diameter, int mouth, int thickness,
                                float[] color, TextureAtlasSprite sprite,
                                int light, int overlay) {
        // 本体は碗と同じ
        renderVoxelBowl(poseStack, consumer, height, diameter, mouth, thickness,
                color, sprite, light, overlay);

        // 取っ手を追加
        renderVoxelHandle(poseStack, consumer, height, diameter,
                color, sprite, light, overlay);
    }

    /**
     * 皿型（平たい碗）
     */
    private void renderVoxelPlate(PoseStack poseStack, VertexConsumer consumer,
                                  int height, int diameter, int mouth, int thickness,
                                  float[] color, TextureAtlasSprite sprite,
                                  int light, int overlay) {
        int adjustedHeight = Math.max(1, height / 3);
        renderVoxelBowl(poseStack, consumer, adjustedHeight, diameter, mouth, thickness,
                color, sprite, light, overlay);
    }

    /**
     * 壺型（中央が膨らむ）
     */
    private void renderVoxelVase(PoseStack poseStack, VertexConsumer consumer,
                                 int height, int diameter, int mouth, int thickness,
                                 float[] color, TextureAtlasSprite sprite,
                                 int light, int overlay) {

        int segments = 8 + (diameter / 2);
        int heightLayers = height * 2; // より詳細に

        for (int layer = 0; layer < heightLayers; layer++) {
            float y = layer * 0.05f;
            float nextY = (layer + 1) * 0.05f;

            float t = layer / (float) (heightLayers - 1);

            // sin曲線で膨らみを表現
            float bulge = 1.0f + 0.5f * (float) Math.sin(t * Math.PI);
            float baseRadius = diameter * 0.05f * bulge;

            // 上部は細くなる
            if (t > 0.7f) {
                float narrowFactor = 1.0f - (t - 0.7f) / 0.3f;
                baseRadius = baseRadius * narrowFactor + mouth * 0.05f * (1 - narrowFactor);
            }

            float innerRadius = Math.max(0.02f, baseRadius - thickness * 0.01f);

            for (int i = 0; i < segments; i++) {
                float angle1 = (float) (2 * Math.PI * i / segments);
                float angle2 = (float) (2 * Math.PI * (i + 1) / segments);

                float x1 = (float) Math.cos(angle1) * baseRadius;
                float z1 = (float) Math.sin(angle1) * baseRadius;
                float x2 = (float) Math.cos(angle2) * baseRadius;
                float z2 = (float) Math.sin(angle2) * baseRadius;

                float innerX1 = (float) Math.cos(angle1) * innerRadius;
                float innerZ1 = (float) Math.sin(angle1) * innerRadius;
                float innerX2 = (float) Math.cos(angle2) * innerRadius;
                float innerZ2 = (float) Math.sin(angle2) * innerRadius;

                renderVoxelBlock(poseStack, consumer,
                        x1, y, z1,
                        x2, y, z2,
                        innerX2, nextY, innerZ2,
                        innerX1, nextY, innerZ1,
                        baseRadius - innerRadius,
                        color, sprite, light, overlay);
            }
        }

        renderVoxelBottom(poseStack, consumer, diameter * 0.05f, thickness,
                color, sprite, light, overlay);
    }

    /**
     * 急須型（壺＋注ぎ口）
     */
    private void renderVoxelTeapot(PoseStack poseStack, VertexConsumer consumer,
                                   int height, int diameter, int mouth, int thickness,
                                   float[] color, TextureAtlasSprite sprite,
                                   int light, int overlay) {
        renderVoxelVase(poseStack, consumer, height, diameter, mouth, thickness,
                color, sprite, light, overlay);

        renderVoxelSpout(poseStack, consumer, height, diameter,
                color, sprite, light, overlay);
    }

    /**
     * 1つのボクセルブロックを描画（台形状の立体）
     */
    private void renderVoxelBlock(PoseStack poseStack, VertexConsumer consumer,
                                  float x1, float y1, float z1,
                                  float x2, float y2, float z2,
                                  float x3, float y3, float z3,
                                  float x4, float y4, float z4,
                                  float thickness,
                                  float[] color, TextureAtlasSprite sprite,
                                  int light, int overlay) {

        Matrix4f matrix = poseStack.last().pose();
        float r = color[0], g = color[1], b = color[2];

        // 外側の面（4つの頂点で台形を構成）
        // 底面の外側
        addQuad(consumer, matrix,
                x1, y1, z1,
                x2, y2, z2,
                x3, y3, z3,
                x4, y4, z4,
                r, g, b, sprite, light, overlay);

        // 内側の面（少し暗く）
        float innerR = r * 0.8f;
        float innerG = g * 0.8f;
        float innerB = b * 0.8f;

        // 中央寄りの内側位置を計算
        float centerX = (x1 + x2 + x3 + x4) / 4;
        float centerZ = (z1 + z2 + z3 + z4) / 4;

        float factor = (float) (thickness / Math.hypot(x1 - centerX, z1 - centerZ));

        float innerX1 = x1 - (x1 - centerX) * factor;
        float innerZ1 = z1 - (z1 - centerZ) * factor;
        float innerX2 = x2 - (x2 - centerX) * factor;
        float innerZ2 = z2 - (z2 - centerZ) * factor;
        float innerX3 = x3 - (x3 - centerX) * factor;
        float innerZ3 = z3 - (z3 - centerZ) * factor;
        float innerX4 = x4 - (x4 - centerX) * factor;
        float innerZ4 = z4 - (z4 - centerZ) * factor;

        addQuad(consumer, matrix,
                innerX4, y4, innerZ4,
                innerX3, y3, innerZ3,
                innerX2, y2, innerZ2,
                innerX1, y1, innerZ1,
                innerR, innerG, innerB, sprite, light, overlay);

        // 側面（上面）
        if (y3 > y1) {
            addQuad(consumer, matrix,
                    x1, y1, z1,
                    x4, y4, z4,
                    innerX4, y4, innerZ4,
                    innerX1, y1, innerZ1,
                    r * 0.9f, g * 0.9f, b * 0.9f, sprite, light, overlay);

            addQuad(consumer, matrix,
                    x2, y2, z2,
                    x1, y1, z1,
                    innerX1, y1, innerZ1,
                    innerX2, y2, innerZ2,
                    r * 0.9f, g * 0.9f, b * 0.9f, sprite, light, overlay);
        }
    }

    /**
     * 底面の描画
     */
    private void renderVoxelBottom(PoseStack poseStack, VertexConsumer consumer,
                                   float radius, int thickness,
                                   float[] color, TextureAtlasSprite sprite,
                                   int light, int overlay) {
        Matrix4f matrix = poseStack.last().pose();

        int segments = 8;
        float innerRadius = Math.max(0.02f, radius - thickness * 0.01f);

        for (int i = 0; i < segments; i++) {
            float angle1 = (float) (2 * Math.PI * i / segments);
            float angle2 = (float) (2 * Math.PI * (i + 1) / segments);

            float x1 = (float) Math.cos(angle1) * radius;
            float z1 = (float) Math.sin(angle1) * radius;
            float x2 = (float) Math.cos(angle2) * radius;
            float z2 = (float) Math.sin(angle2) * radius;

            float innerX1 = (float) Math.cos(angle1) * innerRadius;
            float innerZ1 = (float) Math.sin(angle1) * innerRadius;
            float innerX2 = (float) Math.cos(angle2) * innerRadius;
            float innerZ2 = (float) Math.sin(angle2) * innerRadius;

            // 底面のリング状ブロック
            addQuad(consumer, matrix,
                    x1, 0.02f, z1,
                    x2, 0.02f, z2,
                    innerX2, 0.02f, innerZ2,
                    innerX1, 0.02f, innerZ1,
                    color[0] * 0.7f, color[1] * 0.7f, color[2] * 0.7f,
                    sprite, light, overlay);
        }
    }

    /**
     * 取っ手の描画
     */
    private void renderVoxelHandle(PoseStack poseStack, VertexConsumer consumer,
                                   int height, int diameter,
                                   float[] color, TextureAtlasSprite sprite,
                                   int light, int overlay) {
        Matrix4f matrix = poseStack.last().pose();

        float handleX = diameter * 0.06f;
        float handleY1 = height * 0.03f;
        float handleY2 = height * 0.08f;
        float handleWidth = 0.03f;

        // 取っ手を小さなボクセルで構成
        for (int i = 0; i < 5; i++) {
            float t = i / 4.0f;
            float x = handleX + (float) Math.cos(t * Math.PI * 0.5) * handleX * 0.3f;
            float y = handleY1 + t * (handleY2 - handleY1);
            float z = 0;

            addCube(consumer, matrix, x, y, z, handleWidth, handleWidth, handleWidth * 2,
                    color[0], color[1], color[2], sprite, light, overlay);
        }
    }

    /**
     * 注ぎ口の描画
     */
    private void renderVoxelSpout(PoseStack poseStack, VertexConsumer consumer,
                                  int height, int diameter,
                                  float[] color, TextureAtlasSprite sprite,
                                  int light, int overlay) {
        Matrix4f matrix = poseStack.last().pose();

        float spoutX = diameter * 0.05f;
        float spoutY = height * 0.06f;
        float spoutLength = diameter * 0.04f;

        for (int i = 0; i < 3; i++) {
            float x = spoutX + i * spoutLength / 3;
            float size = 0.04f - i * 0.01f;

            addCube(consumer, matrix, x, spoutY, 0, size, size, size,
                    color[0], color[1], color[2], sprite, light, overlay);
        }
    }

    /**
     * 四角形を描画
     */
    private void addQuad(VertexConsumer consumer, Matrix4f matrix,
                         float x1, float y1, float z1,
                         float x2, float y2, float z2,
                         float x3, float y3, float z3,
                         float x4, float y4, float z4,
                         float r, float g, float b,
                         TextureAtlasSprite sprite, int light, int overlay) {

        // 法線計算
        float dx1 = x2 - x1, dy1 = y2 - y1, dz1 = z2 - z1;
        float dx2 = x4 - x1, dy2 = y4 - y1, dz2 = z4 - z1;
        float nx = dy1 * dz2 - dz1 * dy2;
        float ny = dz1 * dx2 - dx1 * dz2;
        float nz = dx1 * dy2 - dy1 * dx2;
        float len = (float) Math.sqrt(nx * nx + ny * ny + nz * nz);
        nx /= len; ny /= len; nz /= len;

        // 三角形1
        addVertex(consumer, matrix, x1, y1, z1, r, g, b, sprite.getU0(), sprite.getV0(), nx, ny, nz, light, overlay);
        addVertex(consumer, matrix, x2, y2, z2, r, g, b, sprite.getU1(), sprite.getV0(), nx, ny, nz, light, overlay);
        addVertex(consumer, matrix, x3, y3, z3, r, g, b, sprite.getU1(), sprite.getV1(), nx, ny, nz, light, overlay);

        // 三角形2
        addVertex(consumer, matrix, x1, y1, z1, r, g, b, sprite.getU0(), sprite.getV0(), nx, ny, nz, light, overlay);
        addVertex(consumer, matrix, x3, y3, z3, r, g, b, sprite.getU1(), sprite.getV1(), nx, ny, nz, light, overlay);
        addVertex(consumer, matrix, x4, y4, z4, r, g, b, sprite.getU0(), sprite.getV1(), nx, ny, nz, light, overlay);
    }

    /**
     * 立方体を描画
     */
    private void addCube(VertexConsumer consumer, Matrix4f matrix,
                         float x, float y, float z,
                         float width, float height, float depth,
                         float r, float g, float b,
                         TextureAtlasSprite sprite, int light, int overlay) {
        float x1 = x - width / 2, x2 = x + width / 2;
        float y1 = y, y2 = y + height;
        float z1 = z - depth / 2, z2 = z + depth / 2;

        // 6面を描画
        // 上
        addQuad(consumer, matrix, x1, y2, z1, x2, y2, z1, x2, y2, z2, x1, y2, z2, r, g, b, sprite, light, overlay);
        // 下
        addQuad(consumer, matrix, x1, y1, z2, x2, y1, z2, x2, y1, z1, x1, y1, z1, r, g, b, sprite, light, overlay);
        // 前
        addQuad(consumer, matrix, x1, y1, z2, x2, y1, z2, x2, y2, z2, x1, y2, z2, r, g, b, sprite, light, overlay);
        // 後
        addQuad(consumer, matrix, x2, y1, z1, x1, y1, z1, x1, y2, z1, x2, y2, z1, r, g, b, sprite, light, overlay);
        // 左
        addQuad(consumer, matrix, x1, y1, z1, x1, y1, z2, x1, y2, z2, x1, y2, z1, r, g, b, sprite, light, overlay);
        // 右
        addQuad(consumer, matrix, x2, y1, z2, x2, y1, z1, x2, y2, z1, x2, y2, z2, r, g, b, sprite, light, overlay);
    }

    private void addVertex(VertexConsumer consumer, Matrix4f matrix,
                           float x, float y, float z,
                           float r, float g, float b,
                           float u, float v,
                           float nx, float ny, float nz,
                           int light, int overlay) {
        consumer.addVertex(matrix, x, y, z)
                .setColor(r, g, b, 1.0f)
                .setUv(u, v)
                .setOverlay(overlay)
                .setLight(light)
                .setNormal(nx, ny, nz);
    }
}