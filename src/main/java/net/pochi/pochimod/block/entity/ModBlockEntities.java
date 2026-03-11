package net.pochi.pochimod.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.block.ModBlocks;
import net.pochi.pochimod.block.entity.custom.*;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, PochiMod.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BFurnaceBlockEntity>> BFURNACE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("bfurnace_block", () ->
                    new BlockEntityType<>(BFurnaceBlockEntity::new, ModBlocks.BFURNACE_BLOCK.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SakeDaruBlockEntity>> SAKE_DARU_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("sakedaru_block", () ->
                    new BlockEntityType<>(SakeDaruBlockEntity::new, ModBlocks.SAKE_DARU_BLOCK.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DistillerBlockEntity>> DISTILLER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("distiller_block", () ->
                    new BlockEntityType<>(DistillerBlockEntity::new, ModBlocks.DISTILLER_BLOCK.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MixerBlockEntity>> MIXER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("mixer_block", () ->
                    new BlockEntityType<>(MixerBlockEntity::new, ModBlocks.MIXER_BLOCK.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FrypanBlockEntity>> FRYPAN_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("frypan_block", () ->
                    new BlockEntityType<>(FrypanBlockEntity::new, ModBlocks.FRYPAN_BLOCK.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BubbleEntity>> BUBBLE_ENTITY =
            BLOCK_ENTITIES.register("bubble", () ->
                    new BlockEntityType<>(BubbleEntity::new, ModBlocks.BUBBLE.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PotteryOnWheelBlockEntity>> POTTERY_ON_WHEEL =
            BLOCK_ENTITIES.register("pottery_on_wheel", () ->
                    new BlockEntityType<>(PotteryOnWheelBlockEntity::new, ModBlocks.POTTERY_ON_WHEEL.get()));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
