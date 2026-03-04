package net.pochi.pochimod.block.custom.modleaves;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.pochi.pochimod.block.custom.fruits.BananaBlock;

public class BananaLeavesBlock extends LeavesBlock implements BonemealableBlock {
    public BananaLeavesBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader p_256559_, BlockPos pPos, BlockState pState) {
        return p_256559_.getBlockState(pPos.below()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        pLevel.setBlock(pPos.below(), BananaBlock.createNewHangingPropagule(),2);
    }
}
