package net.pochi.pochimod.block.entity;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.block.entity.custom.*;

@EventBusSubscriber(modid = PochiMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModCapabilities {

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.BFURNACE_BLOCK_ENTITY.get(),
                (be, side) -> be.getItemHandler());

        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.DISTILLER_BLOCK_ENTITY.get(),
                (be, side) -> be.getItemHandler());

        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.FRYPAN_BLOCK_ENTITY.get(),
                (be, side) -> be.getItemHandler());

        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.MIXER_BLOCK_ENTITY.get(),
                (be, side) -> be.getItemHandler());

        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.SAKE_DARU_BLOCK_ENTITY.get(),
                (be, side) -> be.getItemHandler());
    }
}
