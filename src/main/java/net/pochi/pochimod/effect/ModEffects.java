package net.pochi.pochimod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.pochi.pochimod.PochiMod;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(Registries.MOB_EFFECT, PochiMod.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> SHIELD = MOB_EFFECTS.register("shield",
            () -> new Shield(MobEffectCategory.NEUTRAL, 3800240));

    public static final DeferredHolder<MobEffect, MobEffect> ARROW = MOB_EFFECTS.register("arrow",
            () -> new ArroeEf(MobEffectCategory.NEUTRAL, 16777215));

    public static final DeferredHolder<MobEffect, MobEffect> INV = MOB_EFFECTS.register("inv",
            () -> new Inv(MobEffectCategory.NEUTRAL, 10600946));

    public static final DeferredHolder<MobEffect, MobEffect> CRIME = MOB_EFFECTS.register("crime",
            () -> new Crime(MobEffectCategory.NEUTRAL, 10600828));

    public static final DeferredHolder<MobEffect, MobEffect> BUBBLE_WALK = MOB_EFFECTS.register("bubble_walk",
            () -> new BubbleWalk(MobEffectCategory.NEUTRAL, 3538943));

    public static final DeferredHolder<MobEffect, MobEffect> MAGNETIC = MOB_EFFECTS.register("magnetic",
            () -> new Magnetic(MobEffectCategory.NEUTRAL, 16742492));

    public static final DeferredHolder<MobEffect, MobEffect> MOON_WALK = MOB_EFFECTS.register("moon_walk",
            () -> new MoonWalk(MobEffectCategory.NEUTRAL, 4151977));

    public static final DeferredHolder<MobEffect, MobEffect> HONEY = MOB_EFFECTS.register("honey",
            () -> new Honey(MobEffectCategory.NEUTRAL, 4151977));

    public static final DeferredHolder<MobEffect, MobEffect> INDICATE_HONEY = MOB_EFFECTS.register("indicate_honey",
            () -> new IndicateHoney(MobEffectCategory.NEUTRAL, 4151977));

    public static final DeferredHolder<MobEffect, MobEffect> WATER_FLOW = MOB_EFFECTS.register("water_flow",
            () -> new WaterFlow(MobEffectCategory.NEUTRAL, 4151977));

    public static final DeferredHolder<MobEffect, MobEffect> WATER_FALL = MOB_EFFECTS.register("water_fall",
            () -> new WaterFall(MobEffectCategory.NEUTRAL, 4151977));

    public static final DeferredHolder<MobEffect, MobEffect> SNOW_WALK = MOB_EFFECTS.register("snow_walk",
            () -> new SnowWalk(MobEffectCategory.NEUTRAL, 4151977));

    public static final DeferredHolder<MobEffect, MobEffect> DROWNED = MOB_EFFECTS.register("drowned",
            () -> new Drowned(MobEffectCategory.NEUTRAL, 4151977));

    public static final DeferredHolder<MobEffect, MobEffect> DREAMER = MOB_EFFECTS.register("dreamer",
            () -> new Dreamer(MobEffectCategory.NEUTRAL, 4151977));


    public static void register(IEventBus modEventBus) {
        MOB_EFFECTS.register(modEventBus);
    }
}
