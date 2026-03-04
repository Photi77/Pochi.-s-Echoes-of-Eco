package net.pochi.pochimod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.ItemAbilities;
import net.pochi.pochimod.block.ModBlocks;
import net.pochi.pochimod.item.ModItems;

public class ModFlammableRotatedPillarDropBlock extends RotatedPillarBlock {

    public ModFlammableRotatedPillarDropBlock(BlockBehaviour.Properties pProperties) {
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
    protected ItemInteractionResult useItemOn(ItemStack itemstack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (itemstack.canPerformAction(ItemAbilities.AXE_DIG)) {
            if (!pLevel.isClientSide) {
                if(pState.is(ModBlocks.CINNAMON_LOG.get())) {
                    Direction direction = pHit.getDirection();
                    Direction direction1 = direction.getAxis() == Direction.Axis.Y ? pPlayer.getDirection().getOpposite() : direction;
                    pLevel.playSound((Player) null, pPos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
                    pLevel.setBlock(pPos, ModBlocks.STRIPPED_CINNAMON_LOG.get().defaultBlockState().setValue(AXIS, pState.getValue(AXIS)), 11);
                    ItemEntity itementity = new ItemEntity(pLevel, (double) pPos.getX() + 0.5D + (double) direction1.getStepX() * 0.65D, (double) pPos.getY() + 0.1D, (double) pPos.getZ() + 0.5D + (double) direction1.getStepZ() * 0.65D, new ItemStack(ModItems.CINNAMON.get(), 4));
                    itementity.setDeltaMovement(0.05D * (double) direction1.getStepX() + pLevel.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double) direction1.getStepZ() + pLevel.random.nextDouble() * 0.02D);
                    pLevel.addFreshEntity(itementity);
                    itemstack.hurtAndBreak(1, pPlayer, pHand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
                }
            }

            return ItemInteractionResult.sidedSuccess(pLevel.isClientSide);
        } else {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
    }
}

