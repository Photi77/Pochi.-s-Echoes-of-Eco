package net.pochi.pochimod.entity.custom;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.pochi.pochimod.PochiMod;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Betta extends PathfinderMob {
    private static final EntityDataAccessor<Integer> BODY_COLOR =
            SynchedEntityData.defineId(Betta.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> FIN_COLOR =
            SynchedEntityData.defineId(Betta.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PATTERN_TYPE =
            SynchedEntityData.defineId(Betta.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PATTERN_COLOR =
            SynchedEntityData.defineId(Betta.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ACCENT_COLOR =
            SynchedEntityData.defineId(Betta.class, EntityDataSerializers.INT);

    // クライアント側でキャッシュされるテクスチャ
    @OnlyIn(Dist.CLIENT)
    private ResourceLocation cachedTexture;

    public Betta(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(BODY_COLOR, 0);
        builder.define(FIN_COLOR, 0);
        builder.define(PATTERN_TYPE, 0);
        builder.define(PATTERN_COLOR, 0);
        builder.define(ACCENT_COLOR, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("BodyColor", this.getBodyColor());
        tag.putInt("FinColor", this.getFinColor());
        tag.putInt("PatternType", this.getPatternType());
        tag.putInt("PatternColor", this.getPatternColor());
        tag.putInt("AccentColor", this.getAccentColor());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setBodyColor(tag.getInt("BodyColor"));
        this.setFinColor(tag.getInt("FinColor"));
        this.setPatternType(tag.getInt("PatternType"));
        this.setPatternColor(tag.getInt("PatternColor"));
        this.setAccentColor(tag.getInt("AccentColor"));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty,
                                        MobSpawnType spawnType, @Nullable SpawnGroupData spawnData) {
        spawnData = super.finalizeSpawn(level, difficulty, spawnType, spawnData);

        // ランダムな色とパターンを生成
        generateRandomAppearance(level.getRandom());

        return spawnData;
    }

    private void generateRandomAppearance(RandomSource random) {
        // 体の色（RGB）
        this.setBodyColor(generateVibrantColor(random));

        // ヒレの色（体と調和する色 or コントラスト色）
        if (random.nextBoolean()) {
            // 調和する色（類似色）
            this.setFinColor(generateHarmoniousColor(this.getBodyColor(), random));
        } else {
            // コントラスト色（補色）
            this.setFinColor(generateComplementaryColor(this.getBodyColor(), random));
        }

        // パターンタイプ（0-6: 無地、斑点、縞模様、グラデーション、マーブル、バイカラー、トライカラー）
        this.setPatternType(random.nextInt(7));

        // パターンの色
        this.setPatternColor(generateVibrantColor(random));

        // アクセントカラー（メタリックな輝き用）
        this.setAccentColor(generateMetallicColor(random));
    }

    private int generateVibrantColor(RandomSource random) {
        // 鮮やかな色を生成（彩度と明度を高めに設定）
        float hue = random.nextFloat(); // 0-1
        float saturation = 0.7F + random.nextFloat() * 0.3F; // 0.7-1.0
        float brightness = 0.6F + random.nextFloat() * 0.4F; // 0.6-1.0

        int rgb = java.awt.Color.HSBtoRGB(hue, saturation, brightness);
        return rgb & 0xFFFFFF; // アルファチャンネルを除去
    }

    private int generateHarmoniousColor(int baseColor, RandomSource random) {
        // 類似色を生成（色相を少しずらす）
        float[] hsb = rgbToHSB(baseColor);

        float hueShift = (random.nextFloat() - 0.5F) * 0.15F; // ±0.075の範囲で色相をずらす
        hsb[0] = (hsb[0] + hueShift + 1.0F) % 1.0F;

        // 彩度と明度も少し変化
        hsb[1] = Math.max(0.5F, Math.min(1.0F, hsb[1] + (random.nextFloat() - 0.5F) * 0.3F));
        hsb[2] = Math.max(0.5F, Math.min(1.0F, hsb[2] + (random.nextFloat() - 0.5F) * 0.3F));

        return java.awt.Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]) & 0xFFFFFF;
    }

    private int generateComplementaryColor(int baseColor, RandomSource random) {
        // 補色を生成（色相を180度反転）
        float[] hsb = rgbToHSB(baseColor);

        hsb[0] = (hsb[0] + 0.5F) % 1.0F; // 180度回転

        // 少しランダム性を加える
        hsb[0] = (hsb[0] + (random.nextFloat() - 0.5F) * 0.1F + 1.0F) % 1.0F;
        hsb[1] = 0.7F + random.nextFloat() * 0.3F;
        hsb[2] = 0.6F + random.nextFloat() * 0.4F;

        return java.awt.Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]) & 0xFFFFFF;
    }

    private int generateMetallicColor(RandomSource random) {
        // メタリックカラー（金、銀、青メタリックなど）
        int[] metallicColors = {
                0xFFD700, // ゴールド
                0xC0C0C0, // シルバー
                0x4169E1, // ロイヤルブルーメタリック
                0xFF1493, // ディープピンクメタリック
                0x00CED1, // ダークターコイズメタリック
                0x9370DB, // ミディアムパープルメタリック
        };

        return metallicColors[random.nextInt(metallicColors.length)];
    }

    private float[] rgbToHSB(int rgb) {
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;

        return java.awt.Color.RGBtoHSB(r, g, b, null);
    }

    // Getter/Setter
    public int getBodyColor() {
        return this.entityData.get(BODY_COLOR);
    }

    public void setBodyColor(int color) {
        this.entityData.set(BODY_COLOR, color);
    }

    public int getFinColor() {
        return this.entityData.get(FIN_COLOR);
    }

    public void setFinColor(int color) {
        this.entityData.set(FIN_COLOR, color);
    }

    public int getPatternType() {
        return this.entityData.get(PATTERN_TYPE);
    }

    public void setPatternType(int type) {
        this.entityData.set(PATTERN_TYPE, type);
    }

    public int getPatternColor() {
        return this.entityData.get(PATTERN_COLOR);
    }

    public void setPatternColor(int color) {
        this.entityData.set(PATTERN_COLOR, color);
    }

    public int getAccentColor() {
        return this.entityData.get(ACCENT_COLOR);
    }

    public void setAccentColor(int color) {
        this.entityData.set(ACCENT_COLOR, color);
    }

    @OnlyIn(Dist.CLIENT)
    public ResourceLocation getCachedTexture() {
        return this.cachedTexture;
    }

    @OnlyIn(Dist.CLIENT)
    public void setCachedTexture(ResourceLocation texture) {
        this.cachedTexture = texture;
    }


    @OnlyIn(Dist.CLIENT)
    public class BettaTextureGenerator {
        private static final Map<Integer, ResourceLocation> TEXTURE_CACHE = new HashMap<>();
        private static final String TEXTURE_PREFIX = "betta_dynamic_";

        // ベーステクスチャ（各レイヤー用）
        private static NativeImage baseBodyTexture;
        private static NativeImage baseFinTexture;
        private static NativeImage basePatternTexture;

        public static void loadBaseTextures() {
            try {
                // ベーステクスチャをロード（resources/assets/yourmodid/textures/entity/betta/ 内）
                baseBodyTexture = loadTexture("textures/entity/betta/base_body.png");
                baseFinTexture = loadTexture("textures/entity/betta/base_fins.png");
                basePatternTexture = loadTexture("textures/entity/betta/base_pattern.png");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static NativeImage loadTexture(String path) throws IOException {
            ResourceLocation location = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, path);
            Resource resource = Minecraft.getInstance().getResourceManager().getResource(location).orElseThrow();
            return NativeImage.read(resource.open());
        }

        public static ResourceLocation generateTexture(Betta betta) {
            // すでにキャッシュがある場合は再利用
            if (betta.getCachedTexture() != null) {
                return betta.getCachedTexture();
            }

            // ユニークなハッシュを生成
            int hash = generateHash(betta);

            // キャッシュを確認
            if (TEXTURE_CACHE.containsKey(hash)) {
                ResourceLocation cached = TEXTURE_CACHE.get(hash);
                betta.setCachedTexture(cached);
                return cached;
            }

            // 新規テクスチャを生成
            try {
                NativeImage generatedTexture = generateBettaTexture(
                        betta.getBodyColor(),
                        betta.getFinColor(),
                        betta.getPatternType(),
                        betta.getPatternColor(),
                        betta.getAccentColor()
                );

                // 動的テクスチャとして登録
                ResourceLocation textureLocation = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, TEXTURE_PREFIX + hash);

                Minecraft.getInstance().getTextureManager().register(
                        textureLocation,
                        new DynamicTexture(generatedTexture)
                );

                // キャッシュに保存
                TEXTURE_CACHE.put(hash, textureLocation);
                betta.setCachedTexture(textureLocation);

                return textureLocation;

            } catch (Exception e) {
                e.printStackTrace();
                // フォールバック用のデフォルトテクスチャ
                return ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "textures/entity/betta/default.png");
            }
        }

        private static int generateHash(Betta betta) {
            // 全ての色情報からハッシュを生成
            return Objects.hash(
                    betta.getBodyColor(),
                    betta.getFinColor(),
                    betta.getPatternType(),
                    betta.getPatternColor(),
                    betta.getAccentColor()
            );
        }

        private static NativeImage generateBettaTexture(int bodyColor, int finColor,
                                                        int patternType, int patternColor,
                                                        int accentColor) {
            // テクスチャサイズ（64x64など、モデルに合わせる）
            int width = 64;
            int height = 64;

            NativeImage texture = new NativeImage(width, height, true);

            // 1. ベース体色を適用
            applyColorToLayer(texture, baseBodyTexture, bodyColor);

            // 2. ヒレの色を適用
            applyColorToLayer(texture, baseFinTexture, finColor);

            // 3. パターンを適用
            applyPattern(texture, basePatternTexture, patternType, patternColor);

            // 4. メタリックアクセントを適用
            applyMetallicAccent(texture, accentColor);

            return texture;
        }

        private static void applyColorToLayer(NativeImage target, NativeImage source, int color) {
            int r = (color >> 16) & 0xFF;
            int g = (color >> 8) & 0xFF;
            int b = color & 0xFF;

            for (int y = 0; y < target.getHeight(); y++) {
                for (int x = 0; x < target.getWidth(); x++) {
                    int sourcePixel = source.getPixelRGBA(x, y);
                    int alpha = (sourcePixel >> 24) & 0xFF;

                    if (alpha > 0) {
                        // グレースケール値を取得
                        int gray = sourcePixel & 0xFF;
                        float intensity = gray / 255.0F;

                        // 色を適用
                        int newR = (int)(r * intensity);
                        int newG = (int)(g * intensity);
                        int newB = (int)(b * intensity);

                        // 既存のピクセルとブレンド
                        int existingPixel = target.getPixelRGBA(x, y);
                        int existingAlpha = (existingPixel >> 24) & 0xFF;

                        if (existingAlpha == 0) {
                            // ABGRフォーマットで設定
                            target.setPixelRGBA(x, y, (alpha << 24) | (newB << 16) | (newG << 8) | newR);
                        } else {
                            // アルファブレンディング
                            int blendedR = blendChannel(existingPixel & 0xFF, newR, alpha, existingAlpha);
                            int blendedG = blendChannel((existingPixel >> 8) & 0xFF, newG, alpha, existingAlpha);
                            int blendedB = blendChannel((existingPixel >> 16) & 0xFF, newB, alpha, existingAlpha);
                            int blendedA = Math.max(existingAlpha, alpha);

                            target.setPixelRGBA(x, y, (blendedA << 24) | (blendedB << 16) | (blendedG << 8) | blendedR);
                        }
                    }
                }
            }
        }

        private static void applyPattern(NativeImage target, NativeImage patternMask,
                                         int patternType, int patternColor) {
            int r = (patternColor >> 16) & 0xFF;
            int g = (patternColor >> 8) & 0xFF;
            int b = patternColor & 0xFF;

            for (int y = 0; y < target.getHeight(); y++) {
                for (int x = 0; x < target.getWidth(); x++) {
                    boolean shouldApplyPattern = false;

                    switch (patternType) {
                        case 0: // 無地（パターンなし）
                            continue;
                        case 1: // 斑点
                            shouldApplyPattern = isSpotPattern(x, y, target.getWidth(), target.getHeight());
                            break;
                        case 2: // 横縞
                            shouldApplyPattern = (y / 4) % 2 == 0;
                            break;
                        case 3: // グラデーション
                            shouldApplyPattern = applyGradientPattern(target, x, y, patternColor);
                            continue;
                        case 4: // マーブル
                            shouldApplyPattern = isMarblePattern(x, y, target.getWidth(), target.getHeight());
                            break;
                        case 5: // バイカラー（上下で色分け）
                            shouldApplyPattern = y < target.getHeight() / 2;
                            break;
                        case 6: // トライカラー（3分割）
                            shouldApplyPattern = (y / (target.getHeight() / 3)) % 2 == 1;
                            break;
                    }

                    if (shouldApplyPattern) {
                        int patternMaskPixel = patternMask.getPixelRGBA(x, y);
                        int maskAlpha = (patternMaskPixel >> 24) & 0xFF;

                        if (maskAlpha > 0) {
                            int existingPixel = target.getPixelRGBA(x, y);
                            int existingAlpha = (existingPixel >> 24) & 0xFF;

                            if (existingAlpha > 0) {
                                // パターンをブレンド
                                int blendAlpha = maskAlpha / 2; // 50%透過
                                int blendedR = blendChannel(existingPixel & 0xFF, r, blendAlpha, existingAlpha);
                                int blendedG = blendChannel((existingPixel >> 8) & 0xFF, g, blendAlpha, existingAlpha);
                                int blendedB = blendChannel((existingPixel >> 16) & 0xFF, b, blendAlpha, existingAlpha);

                                target.setPixelRGBA(x, y, (existingAlpha << 24) | (blendedB << 16) | (blendedG << 8) | blendedR);
                            }
                        }
                    }
                }
            }
        }

        private static boolean isSpotPattern(int x, int y, int width, int height) {
            // 簡易的なノイズベースの斑点パターン
            int seed = (x * 374761393 + y * 668265263) ^ 0x12345678;
            return (seed & 0xFF) < 80; // 約30%の確率で斑点
        }

        private static boolean isMarblePattern(int x, int y, int width, int height) {
            // より複雑なノイズパターン
            double noise = simplexNoise(x * 0.1, y * 0.1);
            return noise > 0.3;
        }

        private static double simplexNoise(double x, double y) {
            // 簡易的なパーリンノイズもどき
            return Math.sin(x * 0.5) * Math.cos(y * 0.5) +
                    Math.sin(x * 1.2 + y * 0.8) * 0.5;
        }

        private static boolean applyGradientPattern(NativeImage target, int x, int y, int gradientColor) {
            // グラデーション（上から下へ）
            float gradient = y / (float)target.getHeight();

            int existingPixel = target.getPixelRGBA(x, y);
            int existingAlpha = (existingPixel >> 24) & 0xFF;

            if (existingAlpha > 0) {
                int r = (gradientColor >> 16) & 0xFF;
                int g = (gradientColor >> 8) & 0xFF;
                int b = gradientColor & 0xFF;

                int existingR = existingPixel & 0xFF;
                int existingG = (existingPixel >> 8) & 0xFF;
                int existingB = (existingPixel >> 16) & 0xFF;

                int newR = (int)(existingR * (1 - gradient) + r * gradient);
                int newG = (int)(existingG * (1 - gradient) + g * gradient);
                int newB = (int)(existingB * (1 - gradient) + b * gradient);

                target.setPixelRGBA(x, y, (existingAlpha << 24) | (newB << 16) | (newG << 8) | newR);
            }

            return false;
        }

        private static void applyMetallicAccent(NativeImage target, int accentColor) {
            int r = (accentColor >> 16) & 0xFF;
            int g = (accentColor >> 8) & 0xFF;
            int b = accentColor & 0xFF;

            // ヒレの端や体の一部にメタリックな輝きを追加
            for (int y = 0; y < target.getHeight(); y++) {
                for (int x = 0; x < target.getWidth(); x++) {
                    int pixel = target.getPixelRGBA(x, y);
                    int alpha = (pixel >> 24) & 0xFF;

                    if (alpha > 200) { // 不透明度の高い部分のみ
                        // エッジ検出的な処理で輝きを追加
                        boolean isEdge = isEdgePixel(target, x, y);

                        if (isEdge) {
                            int existingR = pixel & 0xFF;
                            int existingG = (pixel >> 8) & 0xFF;
                            int existingB = (pixel >> 16) & 0xFF;

                            // メタリックカラーを20%ブレンド
                            int newR = (existingR * 4 + r) / 5;
                            int newG = (existingG * 4 + g) / 5;
                            int newB = (existingB * 4 + b) / 5;

                            target.setPixelRGBA(x, y, (alpha << 24) | (newB << 16) | (newG << 8) | newR);
                        }
                    }
                }
            }
        }

        private static boolean isEdgePixel(NativeImage image, int x, int y) {
            // 簡易的なエッジ検出
            int width = image.getWidth();
            int height = image.getHeight();

            if (x == 0 || y == 0 || x == width - 1 || y == height - 1) {
                return false;
            }

            int centerAlpha = (image.getPixelRGBA(x, y) >> 24) & 0xFF;

            // 周囲8ピクセルをチェック
            for (int dy = -1; dy <= 1; dy++) {
                for (int dx = -1; dx <= 1; dx++) {
                    if (dx == 0 && dy == 0) continue;

                    int neighborAlpha = (image.getPixelRGBA(x + dx, y + dy) >> 24) & 0xFF;
                    if (Math.abs(centerAlpha - neighborAlpha) > 100) {
                        return true;
                    }
                }
            }

            return false;
        }

        private static int blendChannel(int base, int overlay, int overlayAlpha, int baseAlpha) {
            float alpha = overlayAlpha / 255.0F;
            return (int) (base * (1 - alpha) + overlay * alpha);
        }

        public static void clearCache() {
            TEXTURE_CACHE.clear();
        }
    }
}