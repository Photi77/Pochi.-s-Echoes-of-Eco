package net.pochi.pochimod.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.pochi.pochimod.PochiMod;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(Registries.SOUND_EVENT, PochiMod.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> CICADA_AMBIENT = registerSoundEvent("cicada_ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> CICADA_HURT = registerSoundEvent("cicada_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> CICADA_DEATH = registerSoundEvent("cicada_death");

    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, name);
        return SOUND_EVENTS.register(name,() -> SoundEvent.createVariableRangeEvent(id));
    }


    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
