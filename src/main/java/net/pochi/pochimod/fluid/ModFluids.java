package net.pochi.pochimod.fluid;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.block.ModBlocks;
import net.pochi.pochimod.item.ModItems;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(Registries.FLUID, PochiMod.MOD_ID);

    public static final DeferredHolder<Fluid, FlowingFluid> SOURCE_CLEAN = FLUIDS.register("clean_fluid",
            () -> new BaseFlowingFluid.Source(ModFluids.CLEAN_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_CLEAN = FLUIDS.register("flowing_clean",
            () -> new BaseFlowingFluid.Flowing(ModFluids.CLEAN_FLUID_PROPERTIES));

    public static final DeferredHolder<Fluid, FlowingFluid> SOURCE_WHISKEY = FLUIDS.register("whiskey_fluid",
            () -> new BaseFlowingFluid.Source(ModFluids.WHISKEY_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_WHISKEY = FLUIDS.register("flowing_whiskey",
            () -> new BaseFlowingFluid.Flowing(ModFluids.WHISKEY_FLUID_PROPERTIES));

    public static final DeferredHolder<Fluid, FlowingFluid> SOURCE_MAPLE = FLUIDS.register("maple_fluid",
            () -> new BaseFlowingFluid.Source(ModFluids.MAPLE_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MAPLE = FLUIDS.register("flowing_maple_water",
            () -> new BaseFlowingFluid.Flowing(ModFluids.MAPLE_FLUID_PROPERTIES));

    public static final DeferredHolder<Fluid, FlowingFluid> SOURCE_SAKE = FLUIDS.register("sake_fluid",
            () -> new BaseFlowingFluid.Source(ModFluids.SAKE_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_SAKE = FLUIDS.register("flowing_sake",
            () -> new BaseFlowingFluid.Flowing(ModFluids.SAKE_FLUID_PROPERTIES));

    public static final DeferredHolder<Fluid, FlowingFluid> SOURCE_WINE = FLUIDS.register("wine_fluid",
            () -> new BaseFlowingFluid.Source(ModFluids.WINE_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_WINE = FLUIDS.register("flowing_wine",
            () -> new BaseFlowingFluid.Flowing(ModFluids.WINE_FLUID_PROPERTIES));

    public static final DeferredHolder<Fluid, FlowingFluid> SOURCE_WHITE_WINE = FLUIDS.register("white_wine_fluid",
            () -> new BaseFlowingFluid.Source(ModFluids.WHITE_WINE_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_WHITE_WINE = FLUIDS.register("flowing_white_wine",
            () -> new BaseFlowingFluid.Flowing(ModFluids.WHITE_WINE_FLUID_PROPERTIES));

    public static final DeferredHolder<Fluid, FlowingFluid> SOURCE_MASH = FLUIDS.register("mash_fluid",
            () -> new BaseFlowingFluid.Source(ModFluids.MASH_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MASH = FLUIDS.register("flowing_mash",
            () -> new BaseFlowingFluid.Flowing(ModFluids.MASH_FLUID_PROPERTIES));

    public static final DeferredHolder<Fluid, FlowingFluid> SOURCE_YEAST = FLUIDS.register("yeast_fluid",
            () -> new BaseFlowingFluid.Source(ModFluids.YEAST_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_YEAST = FLUIDS.register("flowing_yeast",
            () -> new BaseFlowingFluid.Flowing(ModFluids.YEAST_FLUID_PROPERTIES));






    public static final BaseFlowingFluid.Properties CLEAN_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.CLEAN_FLUID_TYPE, SOURCE_CLEAN, FLOWING_CLEAN)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModBlocks.CLEAN_WATER_BLOCK)
            .bucket(ModItems.FILTERED_WATER_BUCKET);

    public static final BaseFlowingFluid.Properties WHISKEY_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.WHISKEY_FLUID_TYPE, SOURCE_WHISKEY, FLOWING_WHISKEY)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModBlocks.WHISKEY_BLOCK)
            .bucket(ModItems.WHISKEY_BUCKET);

    public static final BaseFlowingFluid.Properties MAPLE_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.MAPLE_FLUID_TYPE, SOURCE_MAPLE, FLOWING_MAPLE)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModBlocks.MAPLE_BLOCK)
            .bucket(ModItems.MAPLE_BUCKET);

    public static final BaseFlowingFluid.Properties SAKE_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.SAKE_FLUID_TYPE, SOURCE_SAKE, FLOWING_SAKE)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModBlocks.SAKE_BLOCK)
            .bucket(ModItems.SAKE_BUCKET);

    public static final BaseFlowingFluid.Properties WINE_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.WINE_FLUID_TYPE, SOURCE_WINE, FLOWING_WINE)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModBlocks.WINE_BLOCK)
            .bucket(ModItems.WINE_BUCKET);

    public static final BaseFlowingFluid.Properties WHITE_WINE_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.WHITE_WINE_FLUID_TYPE, SOURCE_WHITE_WINE, FLOWING_WHITE_WINE)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModBlocks.WHITE_WINE_BLOCK)
            .bucket(ModItems.WHITE_WINE_BUCKET);

    public static final BaseFlowingFluid.Properties MASH_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.MASH_FLUID_TYPE, SOURCE_MASH, FLOWING_MASH)
            .slopeFindDistance(2).levelDecreasePerBlock(5).block(ModBlocks.MASH_BLOCK)
            .bucket(ModItems.MASH_BUCKET);

    public static final BaseFlowingFluid.Properties YEAST_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.YEAST_FLUID_TYPE, SOURCE_YEAST, FLOWING_YEAST)
            .slopeFindDistance(2).levelDecreasePerBlock(5).block(ModBlocks.YEAST_BLOCK)
            .bucket(ModItems.YEAST_BUCKET);




    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
