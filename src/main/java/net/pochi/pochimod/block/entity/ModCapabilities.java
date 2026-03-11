package net.pochi.pochimod.block.entity;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.pochi.pochimod.PochiMod;

// IItemHandler / Capabilities.ItemHandler was removed in NeoForge 21.11.x
// Capability registration disabled until API replacement is identified
@EventBusSubscriber(modid = PochiMod.MOD_ID)
public class ModCapabilities {

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        // Capabilities.ItemHandler.BLOCK was removed in NeoForge 21.11.x
        // (IItemHandler deprecated and scheduled for removal)
    }
}
