package net.pochi.pochimod.world.feature.misk;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;

import java.util.Random;

public class HighPlatformFeature extends Feature<NoneFeatureConfiguration> {

    private final PerlinSimplexNoise noiseGenerator;
    Random random = new Random();

    BlockState[] baseBlocks = new BlockState[] {
            Blocks.MOSSY_COBBLESTONE.defaultBlockState(),
            Blocks.RED_TERRACOTTA.defaultBlockState(),
            Blocks.ORANGE_TERRACOTTA.defaultBlockState(),
            Blocks.SANDSTONE.defaultBlockState(),
            Blocks.RED_SANDSTONE.defaultBlockState(),
            Blocks.DRIPSTONE_BLOCK.defaultBlockState(),
            Blocks.TERRACOTTA.defaultBlockState()
    };

    BlockState[] midBlocks = new BlockState[] {
            Blocks.DIRT.defaultBlockState(),
            Blocks.MUD.defaultBlockState(),
            Blocks.SAND.defaultBlockState(),
            Blocks.RED_SAND.defaultBlockState(),
            Blocks.GRAVEL.defaultBlockState(),
            Blocks.COARSE_DIRT.defaultBlockState(),
            Blocks.MOSS_BLOCK.defaultBlockState(),
            Blocks.CLAY.defaultBlockState(),
    };

    BlockState[] surfaceBlocks = new BlockState[] {
            Blocks.GRASS_BLOCK.defaultBlockState(),
    };

    public HighPlatformFeature(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
        this.noiseGenerator = new PerlinSimplexNoise(new WorldgenRandom(new LegacyRandomSource(0L)), ImmutableList.of(0));
    }

    //少しリアルになった
    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        // ワールド情報と乱数の取得
        WorldGenLevel world = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();

        // 高台のベース高さと変動範囲
        int startHeight = 120;
        int baseHeight = 280;                    // ベースとなる高さ
        int maxHeightVariation = 10;             // 最大高さの変動
        int maxRadius = 22;                      // 麓の最大半径
        int topRadius = 16;                       // 頂上の半径

        // ノイズのスケール設定
        double noiseScale = 0.01;                // ノイズスケールで滑らかさを調整

        // 高台の生成ループ
        for (int y = startHeight; y < baseHeight + maxHeightVariation; y++) {
            // 現在の高さに基づいて半径を線形に補間
            double heightFactor = (double)(baseHeight + maxHeightVariation - y) / (baseHeight + maxHeightVariation - startHeight);
            int currentRadius = (int)(topRadius + (maxRadius - topRadius) * heightFactor);

            for (int dx = -currentRadius; dx <= currentRadius; dx++) {
                for (int dz = -currentRadius; dz <= currentRadius; dz++) {
                    // 外周にノイズを適用
                    if (dx * dx + dz * dz <= currentRadius * currentRadius) {
                        // ノイズで外周の位置をわずかに変動
                        double noiseValue = noiseGenerator.getValue((origin.getX() + dx) * noiseScale, (origin.getZ() + dz)  * noiseScale,false);
                        int heightVariation = (int)(maxHeightVariation * noiseValue);
                        BlockPos newPos = origin.offset(dx, y + heightVariation, dz);
                        if(world.getBlockState(newPos).is(Blocks.AIR) || world.getBlockState(newPos).is(Blocks.GRASS_BLOCK)) {
                            if (y < baseHeight + maxHeightVariation - 10) {
                                world.setBlock(newPos, baseBlocks[random.nextInt(baseBlocks.length)], 2);
                            } else if (y < baseHeight + maxHeightVariation - 1) {
                                world.setBlock(newPos, midBlocks[random.nextInt(midBlocks.length)], 2);
                            } else {
                                world.setBlock(newPos, surfaceBlocks[random.nextInt(surfaceBlocks.length)], 2);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}