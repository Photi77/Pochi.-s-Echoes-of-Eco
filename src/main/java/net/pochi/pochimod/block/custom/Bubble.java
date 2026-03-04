package net.pochi.pochimod.block.custom;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.pochi.pochimod.block.entity.ModBlockEntities;
import net.pochi.pochimod.block.entity.custom.BubbleEntity;
import org.jetbrains.annotations.Nullable;

public class Bubble extends BaseEntityBlock {
    public static final MapCodec<Bubble> CODEC = simpleCodec(Bubble::new);

    @Override
    public MapCodec<Bubble> codec() { return CODEC; }

    private static final VoxelShape SHAPE = Bubble.box(0, 0, 0, 16, 16, 16);

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public Bubble(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.getStateDefinition().any());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new BubbleEntity(p_153215_, p_153216_);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, ModBlockEntities.BUBBLE_ENTITY.get(), BubbleEntity::tick);
    }


}
