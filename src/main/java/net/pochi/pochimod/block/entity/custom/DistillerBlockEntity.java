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
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.HolderLookup;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.pochi.pochimod.block.entity.ModBlockEntities;
import net.pochi.pochimod.recipe.DistillerRecipe;
import net.pochi.pochimod.recipe.SimpleContainerRecipeInput;
import net.pochi.pochimod.screen.DistillerMenu;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DistillerBlockEntity extends BlockEntity implements MenuProvider {

    final ItemStackHandler itemHandler = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };


    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 500;
    private int fuelTime = 0;
    private int maxFuelTime = 0;

    public DistillerBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.DISTILLER_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0: return DistillerBlockEntity.this.progress;
                    case 1: return DistillerBlockEntity.this.maxProgress;
                    case 2: return DistillerBlockEntity.this.fuelTime;
                    case 3: return DistillerBlockEntity.this.maxFuelTime;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: DistillerBlockEntity.this.progress = value; break;
                    case 1: DistillerBlockEntity.this.maxProgress = value; break;
                    case 2: DistillerBlockEntity.this.fuelTime = value; break;
                    case 3: DistillerBlockEntity.this.maxFuelTime = value; break;
                }
            }

            public int getCount() {return 4;}
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("distiller");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new DistillerMenu(pContainerId, pInventory, this, this.data);
    }

    public IItemHandler getItemHandler() {
        return itemHandler;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemHandler.serializeNBT(registries));
        tag.putInt("distiller.progress", progress);
        tag.putInt("distiller.fuelTime", fuelTime);
        tag.putInt("distiller.maxFuelTime", maxFuelTime);
        super.saveAdditional(tag, registries);
    }

    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        super.loadAdditional(nbt, registries);
        itemHandler.deserializeNBT(registries, nbt.getCompound("inventory"));
        progress = nbt.getInt("distiller.progress");
        fuelTime = nbt.getInt("distiller.fuelTime");
        maxFuelTime = nbt.getInt("distiller.maxFuelTime");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    private void consumeFuel() {
        if(!itemHandler.getStackInSlot(0).isEmpty()) {
            ItemStack fuel = this.itemHandler.extractItem(0, 1, false);
            int burnTime = fuel.getItem().getBurnTime(fuel, RecipeType.SMELTING);
            this.fuelTime = burnTime < 0 ? 0 : burnTime;
            this.maxFuelTime = this.fuelTime;
        }
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, DistillerBlockEntity pBlockEntity) {
        if(isConsumingFuel(pBlockEntity)) {
            pBlockEntity.fuelTime--;
        }

        if(hasRecipe(pBlockEntity)) {
            if(hasFuelInFuelSlot(pBlockEntity) && !isConsumingFuel(pBlockEntity)) {
                pBlockEntity.consumeFuel();
                setChanged(pLevel, pPos, pState);
            }
            if(isConsumingFuel(pBlockEntity)) {
                pBlockEntity.progress++;
                setChanged(pLevel, pPos, pState);
                if(pBlockEntity.progress > pBlockEntity.maxProgress) {
                    craftItem(pBlockEntity);
                }
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }
    }

    private static boolean hasFuelInFuelSlot(DistillerBlockEntity entity) {
        return !entity.itemHandler.getStackInSlot(0).isEmpty();
    }

    private static boolean isConsumingFuel(DistillerBlockEntity entity) {
        return entity.fuelTime > 0;
    }


    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(DistillerBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<RecipeHolder<DistillerRecipe>> match = level.getRecipeManager()
                .getRecipeFor(DistillerRecipe.Type.INSTANCE, new SimpleContainerRecipeInput(inventory), level);

        if (match.isPresent()) {
            entity.itemHandler.extractItem(1, 1, false);
            entity.itemHandler.extractItem(2, 1, false);
            entity.itemHandler.extractItem(3, 1, false);

            entity.itemHandler.setStackInSlot(4, new ItemStack(match.get().value().getResultItem(level.registryAccess()).getItem(),
                    entity.itemHandler.getStackInSlot(4).getCount() + 1));

            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(DistillerBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<RecipeHolder<DistillerRecipe>> match = level.getRecipeManager()
                .getRecipeFor(DistillerRecipe.Type.INSTANCE, new SimpleContainerRecipeInput(inventory), level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().value().getResultItem(level.registryAccess()));
    }


    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(5).getItem() == output.getItem() || inventory.getItem(5).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(5).getMaxStackSize() > inventory.getItem(5).getCount();
    }
}
