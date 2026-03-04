package net.pochi.pochimod.world.feature.treedecorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.pochi.pochimod.block.ModBlocks;
import net.pochi.pochimod.block.custom.fruits.ColaBlock;
import net.pochi.pochimod.world.feature.ModTreeDecoratorType;

import java.util.List;

public class ColaDecorator extends TreeDecorator {
    public static final MapCodec<ColaDecorator> CODEC =
            RecordCodecBuilder.mapCodec(instance ->
                    instance.group(
                            Codec.floatRange(0.0F, 1.0F)
                                    .fieldOf("probability")
                                    .forGetter(d -> d.probability)
                    ).apply(instance, ColaDecorator::new)
            );


    public final float probability;

    public ColaDecorator(float p_69976_) {
        this.probability = p_69976_;
    }

    protected TreeDecoratorType<?> type() {
        return ModTreeDecoratorType.COLA.get();
    }

    public void place(Context pContext) {
        RandomSource randomsource = pContext.random();
        if (!(randomsource.nextFloat() >= this.probability)) {
            List<BlockPos> list = pContext.logs();
            int i = list.get(0).getY();
            list.stream().filter((p_69980_) -> {
                return p_69980_.getY() - i <= 2;
            }).forEach((p_226026_) -> {
                for(Direction direction : Direction.Plane.HORIZONTAL) {
                    if (randomsource.nextFloat() <= 0.25F) {
                        Direction direction1 = direction.getOpposite();
                        BlockPos blockpos = p_226026_.offset(direction1.getStepX(), 0, direction1.getStepZ());
                        if (pContext.isAir(blockpos)) {
                            pContext.setBlock(blockpos, ModBlocks.COLA_FRUIT.get().defaultBlockState().setValue(ColaBlock.AGE, Integer.valueOf(randomsource.nextInt(3))).setValue(ColaBlock.FACING, direction));
                        }
                    }
                }

            });
        }
    }
}
