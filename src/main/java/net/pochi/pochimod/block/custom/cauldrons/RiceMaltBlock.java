package net.pochi.pochimod.block.custom.cauldrons;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.pochi.pochimod.block.ModBlocks;
import net.pochi.pochimod.item.ModItems;

import java.util.Optional;

public class RiceMaltBlock extends Block implements BucketPickup {

    int dryTime = 0;
    public RiceMaltBlock(Properties p_56359_) {
        super(p_56359_);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack p_itemstack_, BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_, InteractionHand p_60507_, BlockHitResult p_60508_) {
        if(p_60503_.is(ModBlocks.BOILED_RICE_BLOCK.get()) && p_itemstack_.is(Items.CHARCOAL)){
            p_60504_.setBlock(p_60505_,ModBlocks.JAPANESE_MALT_R.get().defaultBlockState(),11);
            p_itemstack_.shrink(1);
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    public void fallOn(Level p_196695_, BlockState p_196696_, BlockPos p_196697_, Entity p_196698_, float p_196699_) {
        if (!((double)p_196699_ < 4.0D) && p_196698_ instanceof LivingEntity livingentity) {
            LivingEntity.Fallsounds $$7 = livingentity.getFallSounds();
            SoundEvent soundevent = (double)p_196699_ < 7.0D ? $$7.small() : $$7.big();
            p_196698_.playSound(soundevent, 1.0F, 1.0F);
        }
    }


    @Override
    public ItemStack pickupBlock(@javax.annotation.Nullable Player p_player_, LevelAccessor p_154281_, BlockPos p_154282_, BlockState p_154283_) {
        p_154281_.setBlock(p_154282_, Blocks.AIR.defaultBlockState(), 11);
        if(p_154283_ == ModBlocks.JAPANESE_MALT_R.get().defaultBlockState()) {
            p_154281_.setBlock(p_154282_, Blocks.AIR.defaultBlockState(), 11);
            if (!p_154281_.isClientSide()) {
                p_154281_.levelEvent(2001, p_154282_, Block.getId(p_154283_));
            }
            return new ItemStack(ModItems.RICE_BUCKET.get());
        } else if(p_154283_ == ModBlocks.JAPANESE_MALT_P.get().defaultBlockState()) {
            p_154281_.setBlock(p_154282_, Blocks.AIR.defaultBlockState(), 11);
            if (!p_154281_.isClientSide()) {
                p_154281_.levelEvent(2001, p_154282_, Block.getId(p_154283_));
            }
            return new ItemStack(ModItems.J_MALT_BUCKET.get());
        } else {
            return new ItemStack(ModItems.RICE_BUCKET.get());
        }
    }

    public Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.BUCKET_FILL_POWDER_SNOW);
    }

    @Override
    public boolean isRandomlyTicking(BlockState p_49921_) {
        return true;
    }

    @Override
    public void tick(BlockState p_220702_, ServerLevel p_220703_, BlockPos p_220704_, RandomSource p_220705_) {
        dryTime++;
        if (p_220702_ == ModBlocks.JAPANESE_MALT_R.get().defaultBlockState()) {
            if (dryTime >= 1) {
                p_220703_.setBlockAndUpdate(p_220704_, ModBlocks.JAPANESE_MALT_P.get().defaultBlockState());
            }
        }

    }
}
