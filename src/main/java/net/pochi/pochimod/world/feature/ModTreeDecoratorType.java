package net.pochi.pochimod.world.feature;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.world.feature.treedecorators.AlmondDecorator;
import net.pochi.pochimod.world.feature.treedecorators.CoffeeDecorator;
import net.pochi.pochimod.world.feature.treedecorators.ColaDecorator;
import net.pochi.pochimod.world.feature.treedecorators.DurianDecorator;

public class ModTreeDecoratorType {

    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECO =
            DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, PochiMod.MOD_ID);

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<ColaDecorator>> COLA = TREE_DECO.register("cola",
            () -> new TreeDecoratorType<>(ColaDecorator.CODEC));

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<AlmondDecorator>> ALMOND = TREE_DECO.register("almond",
            () -> new TreeDecoratorType<>(AlmondDecorator.CODEC));

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<DurianDecorator>> DURIAN = TREE_DECO.register("durian",
            () -> new TreeDecoratorType<>(DurianDecorator.CODEC));

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<CoffeeDecorator>> COFFEE = TREE_DECO.register("coffee",
            () -> new TreeDecoratorType<>(CoffeeDecorator.CODEC));


    public static void register(IEventBus eventBus) {
        TREE_DECO.register(eventBus);
    }
}
