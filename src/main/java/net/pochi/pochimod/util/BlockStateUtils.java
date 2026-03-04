package net.pochi.pochimod.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;

public class BlockStateUtils {

    public BlockStateUtils() {
    }

    public static boolean checkChair(Level level, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        if (level.getBlockState(new BlockPos(x + 1, y, z)).getBlock() instanceof CampfireBlock) {
            return true;
        } else if (level.getBlockState(new BlockPos(x - 1, y, z)).getBlock() instanceof CampfireBlock) {
            return true;
        } else {
            return level.getBlockState(new BlockPos(x, y, z + 1)).getBlock() instanceof CampfireBlock ? true : level.getBlockState(new BlockPos(x, y, z - 1)).getBlock() instanceof CampfireBlock;
        }
    }
}
