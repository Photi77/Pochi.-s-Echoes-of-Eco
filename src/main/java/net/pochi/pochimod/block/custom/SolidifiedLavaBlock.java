package net.pochi.pochimod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class SolidifiedLavaBlock extends Block {

    public SolidifiedLavaBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.FIRE)
                .strength(0.5F)
                .sound(SoundType.STONE)
                .lightLevel((state) -> 15)
                .noOcclusion()
        );
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        if (!level.isClientSide) {
            // 5秒後に溶岩に戻すスケジュール
            level.scheduleTick(pos, this, 100);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // 溶岩に戻す
        level.setBlock(pos, Blocks.LAVA.defaultBlockState(), 3);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide) {
            // 周囲のブロック更新時にもスケジュール確認
            if (!level.getBlockTicks().hasScheduledTick(pos, this)) {
                level.scheduleTick(pos, this, 100);
            }
        }
    }
}