package net.pochi.pochimod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import net.pochi.pochimod.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

public class ModFlammableRotatedPillarBlock extends RotatedPillarBlock {
    public ModFlammableRotatedPillarBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility toolAction, boolean simulate) {
        if (context.getItemInHand().getItem() instanceof AxeItem){
            if(state.is(ModBlocks.CABERNET_SAUVIGNON_LOG.get())){
                return ModBlocks.STRIPPED_CABERNET_SAUVIGNON_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.CABERNET_SAUVIGNON_WOOD.get())){
                return ModBlocks.STRIPPED_CABERNET_SAUVIGNON_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.MAPLE_LOG.get())){
                return ModBlocks.STRIPPED_MAPLE_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.MAPLE_WOOD.get())){
                return ModBlocks.STRIPPED_MAPLE_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.CINNAMON_WOOD.get())){
                return ModBlocks.STRIPPED_CINNAMON_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.COLA_LOG.get())){
                return ModBlocks.STRIPPED_COLA_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.COLA_WOOD.get())){
                return ModBlocks.STRIPPED_COLA_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.LEMON_LOG.get())){
                return ModBlocks.STRIPPED_LEMON_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.LEMON_WOOD.get())) {
                return ModBlocks.STRIPPED_LEMON_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.PLUM_LOG.get())){
                return ModBlocks.STRIPPED_PLUM_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.PLUM_WOOD.get())) {
                return ModBlocks.STRIPPED_PLUM_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.CHERRY_LOG.get())){
                return ModBlocks.STRIPPED_CHERRY_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.CHERRY_WOOD.get())) {
                return ModBlocks.STRIPPED_CHERRY_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.BANANA_LOG.get())){
                return ModBlocks.STRIPPED_BANANA_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.BANANA_WOOD.get())) {
                return ModBlocks.STRIPPED_BANANA_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.COCONUT_LOG.get())){
                return ModBlocks.STRIPPED_COCONUT_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.COCONUT_WOOD.get())) {
                return ModBlocks.STRIPPED_COCONUT_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.PEACH_LOG.get())){
                return ModBlocks.STRIPPED_PEACH_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.PEACH_WOOD.get())) {
                return ModBlocks.STRIPPED_PEACH_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.ALMOND_LOG.get())){
                return ModBlocks.STRIPPED_ALMOND_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.ALMOND_WOOD.get())) {
                return ModBlocks.STRIPPED_ALMOND_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.DURIAN_LOG.get())){
                return ModBlocks.STRIPPED_DURIAN_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.DURIAN_WOOD.get())) {
                return ModBlocks.STRIPPED_DURIAN_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.KIWI_LOG.get())){
                return ModBlocks.STRIPPED_KIWI_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.KIWI_WOOD.get())) {
                return ModBlocks.STRIPPED_KIWI_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }




        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
