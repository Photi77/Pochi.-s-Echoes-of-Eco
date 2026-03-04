package net.pochi.pochimod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.pochi.pochimod.block.ModBlocks;

public class PottersWheelBlock extends Block{

    public PottersWheelBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected net.minecraft.world.ItemInteractionResult useItemOn(net.minecraft.world.item.ItemStack heldItem, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return net.minecraft.world.ItemInteractionResult.sidedSuccess(level.isClientSide);

        BlockPos above = pos.above();
        BlockState aboveState = level.getBlockState(above);

        // 粘土ブロックを持っている場合、轆轤の上に陶器ブロックを設置
        if (heldItem.is(Items.CLAY) && aboveState.isAir()) {
            level.setBlock(above, ModBlocks.POTTERY_ON_WHEEL.get().defaultBlockState(), 3);
            level.playSound(null, above, SoundEvents.MUD_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);

            if (!player.getAbilities().instabuild) {
                heldItem.shrink(1);
            }

            return net.minecraft.world.ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        return net.minecraft.world.ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}