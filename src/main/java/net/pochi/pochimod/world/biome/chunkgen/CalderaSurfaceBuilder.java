package net.pochi.pochimod.world.biome.chunkgen;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Heightmap;

public class CalderaSurfaceBuilder {

    // カルデラの基本パラメータ
    private static final int CALDERA_RADIUS = 120;
    private static final int INNER_CRATER_RADIUS = 80;
    private static final int BASE_HEIGHT = 80;
    private static final int PEAK_HEIGHT = 180;
    private static final int CRATER_DEPTH = 40;
    private static final int LAVA_LAKE_RADIUS = 25;

    public void buildSurface(WorldGenLevel level, ChunkAccess chunk, int chunkX, int chunkZ) {
        RandomSource random = RandomSource.create();

        // チャンクの中心座標を計算
        int chunkCenterX = chunkX * 16 + 8;
        int chunkCenterZ = chunkZ * 16 + 8;

        // カルデラの中心座標（バイオーム中心に配置）
        int calderaCenterX = 0; // バイオーム座標系での中心
        int calderaCenterZ = 0;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = chunkX * 16 + x;
                int worldZ = chunkZ * 16 + z;

                // カルデラ中心からの距離
                double distanceFromCenter = Math.sqrt(
                        Math.pow(worldX - calderaCenterX, 2) +
                                Math.pow(worldZ - calderaCenterZ, 2)
                );

                // 高度計算
                int surfaceHeight = calculateHeight(distanceFromCenter, random);

                // ブロック配置
                generateColumn(chunk, x, z, surfaceHeight, distanceFromCenter, random);
            }
        }
    }

    private int calculateHeight(double distanceFromCenter, RandomSource random) {
        if (distanceFromCenter <= INNER_CRATER_RADIUS) {
            // クレーター内部
            return calculateCraterHeight(distanceFromCenter, random);
        } else if (distanceFromCenter <= CALDERA_RADIUS) {
            // 外輪山
            return calculateRimHeight(distanceFromCenter, random);
        } else {
            // 外側斜面
            return calculateOuterSlope(distanceFromCenter, random);
        }
    }

    private int calculateCraterHeight(double distance, RandomSource random) {
        double normalizedDistance = distance / INNER_CRATER_RADIUS;
        double heightFactor = Math.pow(normalizedDistance, 2.5);

        int craterBottom = BASE_HEIGHT - CRATER_DEPTH;
        int rimHeight = PEAK_HEIGHT - 20;

        // ランダムな変動を追加
        int variation = random.nextInt(8) - 4;

        return craterBottom + (int)((rimHeight - craterBottom) * heightFactor) + variation;
    }

    private int calculateRimHeight(double distance, RandomSource random) {
        double rimStart = INNER_CRATER_RADIUS;
        double rimEnd = CALDERA_RADIUS * 0.8;

        if (distance <= rimEnd) {
            // ピーク部分の変動
            double angle = distance * 0.1;
            double peakVariation = (Math.sin(angle) * 10) + (Math.cos(angle * 0.5) * 15);
            int randomVariation = random.nextInt(20) - 10;

            return PEAK_HEIGHT + (int)peakVariation + randomVariation;
        } else {
            // 外側への下降
            double falloff = (distance - rimEnd) / (CALDERA_RADIUS - rimEnd);
            int variation = random.nextInt(12) - 6;

            return PEAK_HEIGHT - (int)(falloff * 60) + variation;
        }
    }

    private int calculateOuterSlope(double distance, RandomSource random) {
        double slopeDistance = distance - CALDERA_RADIUS;
        double maxSlopeDistance = 200; // 外側斜面の最大距離

        if (slopeDistance > maxSlopeDistance) {
            return BASE_HEIGHT + random.nextInt(10) - 5;
        }

        double falloff = slopeDistance / maxSlopeDistance;
        int slopeHeight = PEAK_HEIGHT - 40; // 外輪山より40ブロック低い開始点
        int variation = random.nextInt(15) - 7;

        return slopeHeight - (int)(falloff * (slopeHeight - BASE_HEIGHT)) + variation;
    }

    private void generateColumn(ChunkAccess chunk, int x, int z, int surfaceHeight,
                                double distanceFromCenter, RandomSource random) {

        // 地表ブロックの決定
        BlockState surfaceBlock = getSurfaceBlock(distanceFromCenter, surfaceHeight, random);
        BlockState subSurfaceBlock = getSubSurfaceBlock(distanceFromCenter, random);

        // 基盤岩から地表まで生成
        for (int y = chunk.getMinBuildHeight(); y <= Math.min(surfaceHeight, chunk.getMaxBuildHeight()); y++) {
            BlockPos pos = new BlockPos(x, y, z);

            if (y < 5) {
                // 基盤岩
                chunk.setBlockState(pos, Blocks.BEDROCK.defaultBlockState(), false);
            } else if (y < surfaceHeight - 8) {
                // 深層部
                chunk.setBlockState(pos, getDeepBlock(distanceFromCenter, y), false);
            } else if (y < surfaceHeight - 3) {
                // 中間層
                chunk.setBlockState(pos, subSurfaceBlock, false);
            } else if (y <= surfaceHeight) {
                // 表層
                chunk.setBlockState(pos, surfaceBlock, false);
            }
        }

        // 溶岩湖の生成
        if (distanceFromCenter <= LAVA_LAKE_RADIUS && surfaceHeight <= BASE_HEIGHT - CRATER_DEPTH + 10) {
            generateLavaLake(chunk, x, z, surfaceHeight, random);
        }

        // 地熱フィーチャーの追加
        if (distanceFromCenter <= INNER_CRATER_RADIUS * 1.2 && random.nextFloat() < 0.1f) {
            addGeothermalFeatures(chunk, x, z, surfaceHeight, random);
        }
    }

    private BlockState getSurfaceBlock(double distance, int height, RandomSource random) {
        if (distance <= INNER_CRATER_RADIUS) {
            // クレーター内部
            return random.nextFloat() < 0.7f ?
                    Blocks.BASALT.defaultBlockState() :
                    Blocks.BLACKSTONE.defaultBlockState();
        } else if (distance <= CALDERA_RADIUS * 0.8) {
            // 外輪山
            return random.nextFloat() < 0.6f ?
                    Blocks.TUFF.defaultBlockState() :
                    Blocks.ANDESITE.defaultBlockState();
        } else {
            // 外側斜面
            return random.nextFloat() < 0.5f ?
                    Blocks.COARSE_DIRT.defaultBlockState() :
                    Blocks.GRAVEL.defaultBlockState();
        }
    }

    private BlockState getSubSurfaceBlock(double distance, RandomSource random) {
        if (distance <= INNER_CRATER_RADIUS) {
            return random.nextFloat() < 0.8f ?
                    Blocks.BLACKSTONE.defaultBlockState() :
                    Blocks.DEEPSLATE.defaultBlockState();
        } else {
            return random.nextFloat() < 0.6f ?
                    Blocks.ANDESITE.defaultBlockState() :
                    Blocks.STONE.defaultBlockState();
        }
    }

    private BlockState getDeepBlock(double distance, int y) {
        if (distance <= INNER_CRATER_RADIUS) {
            return y % 3 == 0 ?
                    Blocks.DEEPSLATE.defaultBlockState() :
                    Blocks.TUFF.defaultBlockState();
        } else {
            return Blocks.STONE.defaultBlockState();
        }
    }

    private void generateLavaLake(ChunkAccess chunk, int x, int z, int surfaceHeight, RandomSource random) {
        BlockPos pos = new BlockPos(x, surfaceHeight - 2, z);

        // 湖底
        chunk.setBlockState(pos.below(2), Blocks.MAGMA_BLOCK.defaultBlockState(), false);
        chunk.setBlockState(pos.below(), Blocks.MAGMA_BLOCK.defaultBlockState(), false);

        // 溶岩
        chunk.setBlockState(pos, Blocks.LAVA.defaultBlockState(), false);
        if (random.nextFloat() < 0.3f) {
            chunk.setBlockState(pos.above(), Blocks.LAVA.defaultBlockState(), false);
        }
    }

    private void addGeothermalFeatures(ChunkAccess chunk, int x, int z, int surfaceHeight, RandomSource random) {
        BlockPos pos = new BlockPos(x, surfaceHeight, z);

        if (random.nextFloat() < 0.3f) {
            // 蒸気孔
            chunk.setBlockState(pos, Blocks.MAGMA_BLOCK.defaultBlockState(), false);
        } else if (random.nextFloat() < 0.2f) {
            // 硫黄鉱床
            chunk.setBlockState(pos, Blocks.YELLOW_TERRACOTTA.defaultBlockState(), false);
        } else if (random.nextFloat() < 0.1f) {
            // 鉄鉱床（火山性）
            chunk.setBlockState(pos, Blocks.IRON_ORE.defaultBlockState(), false);
        }
    }

    // SurfaceRule用のメソッド
    public BlockState getBlockForPosition(int x, int y, int z, double distanceFromCenter, RandomSource random) {
        // カルデラ中心からの距離に基づいてブロックを決定
        if (distanceFromCenter <= INNER_CRATER_RADIUS) {
            return getSurfaceBlock(distanceFromCenter, y, random);
        } else if (distanceFromCenter <= CALDERA_RADIUS) {
            return getSurfaceBlock(distanceFromCenter, y, random);
        } else if (distanceFromCenter <= CALDERA_RADIUS * 2) {
            // 外側斜面
            return random.nextFloat() < 0.5f ?
                    Blocks.COARSE_DIRT.defaultBlockState() :
                    Blocks.GRAVEL.defaultBlockState();
        }

        return null; // バニラの処理に委ねる
    }

    public int getHeightForPosition(int x, int z, int calderaCenterX, int calderaCenterZ, RandomSource random) {
        double distanceFromCenter = Math.sqrt(
                Math.pow(x - calderaCenterX, 2) +
                        Math.pow(z - calderaCenterZ, 2)
        );

        return calculateHeight(distanceFromCenter, random);
    }

    public void buildSurfaceForChunk(ChunkAccess chunk, int chunkX, int chunkZ) {
        // チャンク内の全ブロック位置を処理
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = chunkX * 16 + x;
                int worldZ = chunkZ * 16 + z;

                // カルデラ中心からの距離
                double distanceFromCenter = Math.sqrt(worldX * worldX + worldZ * worldZ);

                // 現在の地表高度を取得
                int currentHeight = chunk.getHeight(Heightmap.Types.WORLD_SURFACE, x, z);

                // カルデラ地形の目標高度を計算
                int targetHeight = calculateHeight(distanceFromCenter,
                        RandomSource.create(worldX * 341873128712L + worldZ * 132897987541L));

                // 高度差がある場合のみ処理
                if (Math.abs(currentHeight - targetHeight) > 2) {
                    modifyColumn(chunk, x, z, currentHeight, targetHeight, distanceFromCenter);
                }
            }
        }
    }

    private void modifyColumn(ChunkAccess chunk, int x, int z, int currentHeight,
                              int targetHeight, double distanceFromCenter) {
        RandomSource random = RandomSource.create(x * 341873128712L + z * 132897987541L);

        if (targetHeight > currentHeight) {
            // 土地を盛り上げる（外輪山）
            for (int y = currentHeight + 1; y <= targetHeight; y++) {
                BlockPos pos = new BlockPos(x, y, z);
                BlockState block = getSurfaceBlock(distanceFromCenter, y, random);
                chunk.setBlockState(pos, block, false);
            }
        } else {
            // 土地を削る（クレーター）
            for (int y = targetHeight + 1; y <= currentHeight; y++) {
                BlockPos pos = new BlockPos(x, y, z);
                chunk.setBlockState(pos, Blocks.AIR.defaultBlockState(), false);
            }

            // クレーター底面の設定
            if (targetHeight > chunk.getMinBuildHeight()) {
                BlockPos pos = new BlockPos(x, targetHeight, z);
                BlockState block = getSurfaceBlock(distanceFromCenter, targetHeight, random);
                chunk.setBlockState(pos, block, false);
            }
        }

        // 溶岩湖とその他フィーチャー
        if (distanceFromCenter <= LAVA_LAKE_RADIUS) {
            addLavaLakeFeatures(chunk, x, z, targetHeight, random);
        }
    }

    private void addLavaLakeFeatures(ChunkAccess chunk, int x, int z, int height, RandomSource random) {
        // 溶岩湖の生成
        if (height <= BASE_HEIGHT - CRATER_DEPTH + 5) {
            BlockPos lavaPos = new BlockPos(x, height - 1, z);
            chunk.setBlockState(lavaPos, Blocks.LAVA.defaultBlockState(), false);

            // 湖底
            chunk.setBlockState(lavaPos.below(), Blocks.MAGMA_BLOCK.defaultBlockState(), false);
        }
    }

}