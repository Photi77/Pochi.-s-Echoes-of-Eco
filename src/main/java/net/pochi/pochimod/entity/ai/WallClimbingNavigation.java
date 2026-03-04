package net.pochi.pochimod.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.pochi.pochimod.entity.custom.LeopardGecko;

public class WallClimbingNavigation extends GroundPathNavigation {
    private final LeopardGecko gecko;

    public WallClimbingNavigation(LeopardGecko entity, Level level) {
        super(entity, level);
        this.gecko = entity;
    }

    @Override
    protected PathFinder createPathFinder(int maxVisitedNodes) {
        this.nodeEvaluator = new WallClimbingNodeEvaluator();
        this.nodeEvaluator.setCanPassDoors(true);
        return new PathFinder(this.nodeEvaluator, maxVisitedNodes);
    }

    @Override
    public boolean isStableDestination(BlockPos pos) {
        // 6方向全てが有効な目的地
        for (Direction dir : Direction.values()) {
            BlockPos adjacentPos = pos.relative(dir);
            if (!this.level.getBlockState(adjacentPos).isAir()) {
                return true;
            }
        }
        return false;
    }
}
