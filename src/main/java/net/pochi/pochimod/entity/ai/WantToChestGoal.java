package net.pochi.pochimod.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.pochi.pochimod.entity.custom.Ant;

import java.util.EnumSet;

public class WantToChestGoal extends Goal {
    private final Ant entity;
    private BlockPos targetChestPos;

    public WantToChestGoal(Ant entity) {
        this.entity = entity;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        BlockPos chestPos = entity.getChestPos();
        if (chestPos != null && !entity.getInventory().isEmpty()) {
            this.targetChestPos = chestPos;
            return true;
        }
        return false;
    }

    @Override
    public void start() {
        this.entity.getNavigation().moveTo(targetChestPos.getX(), targetChestPos.getY(), targetChestPos.getZ(), 1.0D);
    }

    @Override
    public void tick() {
        if (this.entity.distanceToSqr(targetChestPos.getX(), targetChestPos.getY(), targetChestPos.getZ()) < 50.0D) {
            BlockEntity blockEntity = this.entity.level().getBlockEntity(targetChestPos);
            if (blockEntity != null) {
                this.entity.moveItemsToChest(blockEntity);
            }
        }
    }
}