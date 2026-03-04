package net.pochi.pochimod.world.feature.misk;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

import java.util.Random;

public class GuyanaLakeFeature extends Feature<BlockStateConfiguration> {
    Random random = new Random();
    private static final BlockState AIR = Blocks.CAVE_AIR.defaultBlockState();

    public GuyanaLakeFeature(Codec<BlockStateConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<BlockStateConfiguration> context) {
        BlockPos pos = context.origin();
        LevelAccessor level = context.level();

        // 池の半径を設定（例：3～5のランダムな半径）
        int radius = 3 + random.nextInt(3);
        int depth = 2 + random.nextInt(3); // 池の深さ（例：2～3の深さ）

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                double distance = Math.sqrt(x * x + z * z);

                // 池の範囲内でのみブロックを配置

                    for (int y = -depth; y <= 0; y++) {
                        BlockPos currentPos = pos.offset(x, y, z);
                        if (distance < radius) {
                            level.setBlock(currentPos, Blocks.WATER.defaultBlockState(), 3);
                        } else {
                            level.setBlock(currentPos, Blocks.MUD.defaultBlockState(), 3);
                        }

                    }
                }
            }

        return true;
    }
}