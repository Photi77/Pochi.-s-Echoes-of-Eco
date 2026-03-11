package net.pochi.pochimod.block.custom.modleaves;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.pochi.pochimod.block.custom.fruits.CoconutBlock;

public class CoconutLeavesBlock extends LeavesBlock implements BonemealableBlock {
    public static final MapCodec<CoconutLeavesBlock> CODEC = simpleCodec(CoconutLeavesBlock::new);

    @Override
    public MapCodec<CoconutLeavesBlock> codec() { return CODEC; }

    public CoconutLeavesBlock(Properties pProperties) {
        super(0.02f, pProperties);
    }

    @Override
    protected void spawnFallingLeavesParticle(Level level, BlockPos pos, RandomSource random) {
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
        pLevel.setBlock(pPos.below(), CoconutBlock.createNewHangingPropagule(),2);
    }
}
