package net.pochi.pochimod.fluid;

import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.pochi.pochimod.PochiMod;
import org.joml.Vector3f;

public class ModFluidTypes {
    public static final Identifier BROWN_STILL_RL = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "block/whiskey_still");
    public static final Identifier BROWN_FLOWING_RL = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "block/whiskey_flow");
    public static final Identifier BROWN_OVERLAY_RL = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "misc/in_whiskey");

    public static final Identifier SKYBLUE_STILL_RL = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "block/sake_still");
    public static final Identifier SKYBLUE_FLOWING_RL = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "block/sake_flow");
    public static final Identifier SKYBLUE_OVERLAY_RL = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "misc/in_whiskey");

    public static final Identifier PURPLE_STILL_RL = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "block/grape_still");
    public static final Identifier PURPLE_FLOWING_RL = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "block/grape_flow");
    public static final Identifier PURPLE_OVERLAY_RL = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "misc/in_whiskey");

    public static final Identifier GREEN_STILL_RL = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "block/green_still");
    public static final Identifier GREEN_FLOWING_RL = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "block/green_flow");
    public static final Identifier GREEN_OVERLAY_RL = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "misc/in_whiskey");

    public static final Identifier WHITE_STILL_RL = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "block/coconut_still");
    public static final Identifier WHITE_FLOWING_RL = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "block/coconut_flow");
    public static final Identifier WHITE_OVERLAY_RL = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "misc/in_whiskey");

    public static final Identifier MASH_STILL_RL = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "block/mash");
    public static final Identifier MASH_FLOWING_RL = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "block/mash");
    public static final Identifier MASH_OVERLAY_RL = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "misc/in_whiskey");

    public static final Identifier YEAST_STILL_RL = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "block/yeast");
    public static final Identifier YEAST_FLOWING_RL = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "block/yeast");
    public static final Identifier YEAST_OVERLAY_RL = Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "misc/in_whiskey");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, PochiMod.MOD_ID);


    public static final DeferredHolder<FluidType, FluidType> CLEAN_FLUID_TYPE = registerFluidType("clean_fluid",
            new BaseFluidType(SKYBLUE_STILL_RL, SKYBLUE_FLOWING_RL, SKYBLUE_OVERLAY_RL, 0xAAFEEEEA,
                    new Vector3f(175f / 255f, 238f / 255f, 238f / 255f),
                    FluidType.Properties.create().viscosity(5).density(15)));

    public static final DeferredHolder<FluidType, FluidType> WHISKEY_FLUID_TYPE = registerFluidType("whiskey_fluid",
            new BaseFluidType(BROWN_STILL_RL, BROWN_FLOWING_RL, BROWN_OVERLAY_RL, 0xA8B0000A,
                    new Vector3f(139f / 255f, 0f / 255f, 0f / 255f),
                    FluidType.Properties.create().viscosity(5).density(15)));

    public static final DeferredHolder<FluidType, FluidType> MAPLE_FLUID_TYPE = registerFluidType("maple_fluid",
            new BaseFluidType(BROWN_STILL_RL, BROWN_FLOWING_RL, BROWN_OVERLAY_RL, 0xA185512A,
                    new Vector3f(224f / 255f, 56f / 255f, 208f / 255f),
                    FluidType.Properties.create().viscosity(5).density(15)));

    public static final DeferredHolder<FluidType, FluidType> SAKE_FLUID_TYPE = registerFluidType("sake_fluid",
            new BaseFluidType(SKYBLUE_STILL_RL, SKYBLUE_FLOWING_RL, SKYBLUE_OVERLAY_RL, 0xAAFEEEEA,
                    new Vector3f(175f / 255f, 238f / 255f, 238f / 255f),
                    FluidType.Properties.create().viscosity(5).density(15)));

    public static final DeferredHolder<FluidType, FluidType> WINE_FLUID_TYPE = registerFluidType("wine_fluid",
            new BaseFluidType(PURPLE_STILL_RL, PURPLE_FLOWING_RL, PURPLE_OVERLAY_RL, 0xAC71585A,
                    new Vector3f(199f / 255f, 21f / 255f, 133f / 255f),
                    FluidType.Properties.create().viscosity(5).density(15)));

    public static final DeferredHolder<FluidType, FluidType> WHITE_WINE_FLUID_TYPE = registerFluidType("white_wine_fluid",
            new BaseFluidType(GREEN_STILL_RL, GREEN_FLOWING_RL, GREEN_OVERLAY_RL, 0xA90EE90A,
                    new Vector3f(144f / 255f, 238f / 255f, 144f / 255f),
                    FluidType.Properties.create().viscosity(5).density(15)));

    public static final DeferredHolder<FluidType, FluidType> MASH_FLUID_TYPE = registerFluidType("mash_fluid",
            new BaseFluidType(MASH_STILL_RL, MASH_FLOWING_RL, MASH_OVERLAY_RL, 0xAAFEEEEA,
                    new Vector3f(175f / 255f, 238f / 255f, 238f / 255f),
                    FluidType.Properties.create().viscosity(5).density(15)));

    public static final DeferredHolder<FluidType, FluidType> YEAST_FLUID_TYPE = registerFluidType("yeast_fluid",
            new BaseFluidType(YEAST_STILL_RL, YEAST_FLOWING_RL, YEAST_OVERLAY_RL, 0xAAFEEEEA,
                    new Vector3f(175f / 255f, 238f / 255f, 238f / 255f),
                    FluidType.Properties.create().viscosity(5).density(15)));



    private static DeferredHolder<FluidType, FluidType> registerFluidType(String name, FluidType fluidType) {
        return FLUID_TYPES.register(name, () -> fluidType);
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
