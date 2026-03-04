package net.pochi.pochimod.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.HolderLookup;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.pochi.pochimod.block.entity.ModBlockEntities;
import net.pochi.pochimod.recipe.FrypanRecipe;
import net.pochi.pochimod.recipe.SimpleContainerRecipeInput;
import net.pochi.pochimod.screen.FrypanMenu;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FrypanBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(10) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };


    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 150;

    public FrypanBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.FRYPAN_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0: return FrypanBlockEntity.this.progress;
                    case 1: return FrypanBlockEntity.this.maxProgress;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: FrypanBlockEntity.this.progress = value; break;
                    case 1: FrypanBlockEntity.this.maxProgress = value; break;
                }
            }

            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("frypan");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new FrypanMenu(pContainerId, pInventory, this, this.data);
    }

    public IItemHandler getItemHandler() {
        return itemHandler;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemHandler.serializeNBT(registries));
        tag.putInt("frypan.progress", progress);
        super.saveAdditional(tag, registries);
    }

    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        super.loadAdditional(nbt, registries);
        itemHandler.deserializeNBT(registries, nbt.getCompound("inventory"));
        progress = nbt.getInt("frypan.progress");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, FrypanBlockEntity pEntity) {
        if(level.isClientSide()) {
            return;
        }

        if(hasRecipe(pEntity, level, pos)) {
            pEntity.progress++;
            setChanged(level, pos, state);

            if(pEntity.progress >= pEntity.maxProgress) {
                craftItem(pEntity, level, pos);
            }
        } else {
            pEntity.resetProgress();
            setChanged(level, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(FrypanBlockEntity entity, Level pLevel, BlockPos pPos) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        if(pLevel.getBlockState(pPos.below()).is(Blocks.CAMPFIRE)) {
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<RecipeHolder<FrypanRecipe>> match = level.getRecipeManager()
                .getRecipeFor(FrypanRecipe.Type.INSTANCE, new SimpleContainerRecipeInput(inventory), level);


            if (match.isPresent()) {
                entity.itemHandler.extractItem(0, 1, false);
                entity.itemHandler.extractItem(1, 1, false);
                entity.itemHandler.extractItem(2, 1, false);
                entity.itemHandler.extractItem(3, 1, false);
                entity.itemHandler.extractItem(4, 1, false);
                entity.itemHandler.extractItem(5, 1, false);
                entity.itemHandler.extractItem(6, 1, false);
                entity.itemHandler.extractItem(7, 1, false);
                entity.itemHandler.extractItem(8, 1, false);

                entity.itemHandler.setStackInSlot(9, new ItemStack(match.get().value().getResultItem(level.registryAccess()).getItem(),
                        entity.itemHandler.getStackInSlot(9).getCount() + 1));

                entity.resetProgress();
            }
        }
    }

    private static boolean hasRecipe(FrypanBlockEntity entity, Level pLevel, BlockPos pPos) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        if(pLevel.getBlockState(pPos.below()).is(Blocks.CAMPFIRE)) {
            for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
                inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
            }

            Optional<RecipeHolder<FrypanRecipe>> match = level.getRecipeManager()
                    .getRecipeFor(FrypanRecipe.Type.INSTANCE, new SimpleContainerRecipeInput(inventory), level);

            return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                    && canInsertItemIntoOutputSlot(inventory, match.get().value().getResultItem(level.registryAccess()));
        }
        return false;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(9).getItem() == output.getItem() || inventory.getItem(9).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(9).getMaxStackSize() > inventory.getItem(9).getCount();
    }


}
