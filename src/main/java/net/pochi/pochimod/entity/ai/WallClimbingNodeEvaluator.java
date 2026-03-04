package net.pochi.pochimod.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.PathfindingContext;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;

public class WallClimbingNodeEvaluator extends WalkNodeEvaluator {
    @Override
    public PathType getPathTypeOfMob(PathfindingContext context, int x, int y, int z, Mob mob) {
        BlockPos pos = new BlockPos(x, y, z);

        // 壁に接触している場合は歩行可能と判定
        for (Direction dir : Direction.values()) {
            BlockPos adjacentPos = pos.relative(dir);
            if (!context.level().getBlockState(adjacentPos).isAir()) {
                return PathType.WALKABLE;
            }
        }

        return super.getPathTypeOfMob(context, x, y, z, mob);
    }
}
