package net.pochi.pochimod.enchantment;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.IEventBus;
import net.pochi.pochimod.PochiMod;

// MC 1.21.1: Enchantments are data-driven. Define them in JSON data files.
public class ModEnchantments {
    public static final ResourceKey<Enchantment> BLOCK_ARROW = ResourceKey.create(
            Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block_arrow"));

    public static void register(IEventBus eventBus) {
        // No-op: enchantments are data-driven in MC 1.21.1
    }
}
