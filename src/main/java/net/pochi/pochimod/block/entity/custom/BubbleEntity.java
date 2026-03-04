package net.pochi.pochimod.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.HolderLookup;
import net.pochi.pochimod.block.entity.ModBlockEntities;

public class BubbleEntity extends BlockEntity {

    protected final ContainerData data;
    private int lava = 0;
    private int progress = 0;

    public BubbleEntity( BlockPos p_155229_, BlockState p_155230_) {
        super(ModBlockEntities.BUBBLE_ENTITY.get(), p_155229_, p_155230_);
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0: return BubbleEntity.this.progress;
                    case 1: return BubbleEntity.this.lava;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: BubbleEntity.this.progress = value; break;
                    case 1: BubbleEntity.this.lava = value; break;
                }
            }

            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putInt("blaster.progress", progress);
        tag.putInt("blaster.fuelTime", lava);
        super.saveAdditional(tag, registries);
    }

    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        super.loadAdditional(nbt, registries);
        progress = nbt.getInt("blaster.progress");
        lava = nbt.getInt("blaster.fuelTime");
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, BubbleEntity pBlockEntity) {
        if (!pLevel.isClientSide) {
            if (pBlockEntity.lava++ >= 160) {
                pLevel.setBlockAndUpdate(pPos, Blocks.LAVA.defaultBlockState());
            }
        }
    }
}
