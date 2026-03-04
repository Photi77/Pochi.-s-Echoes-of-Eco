package net.pochi.pochimod.potion;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.effect.ModEffects;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(Registries.POTION, PochiMod.MOD_ID);

    public static final DeferredHolder<Potion, Potion> BIO_POTION = POTIONS.register("bio_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.SHIELD, 600, 0)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
