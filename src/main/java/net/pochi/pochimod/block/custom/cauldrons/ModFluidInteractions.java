package net.pochi.pochimod.block.custom.cauldrons;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;

public class ModFluidInteractions extends LiquidBlock {
    public ModFluidInteractions(FlowingFluid p_54694_, Properties p_54695_) {
        super(p_54694_, p_54695_);
    }

    @Override
    public void neighborChanged(BlockState p_54709_, Level p_54710_, BlockPos p_54711_, Block p_54712_, BlockPos p_54713_, boolean p_54714_) {
        FluidInteractionRegistry.addInteraction(Fluids.LAVA.getFluidType(),new FluidInteractionRegistry.InteractionInformation(
                Fluids.WATER.getFluidType(),
                fluidState -> fluidState.isSource() ? Blocks.PUMPKIN.defaultBlockState() : Blocks.PUMPKIN.defaultBlockState()
        ));
        super.neighborChanged(p_54709_, p_54710_, p_54711_, p_54712_, p_54713_, p_54714_);
    }
}
