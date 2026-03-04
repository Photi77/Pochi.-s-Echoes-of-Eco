package net.pochi.pochimod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.item.ModItems;

import java.util.function.BiConsumer;

public class ModEntityLootTables implements net.minecraft.data.loot.LootTableSubProvider {
    private final HolderLookup.Provider registries;

    public ModEntityLootTables(HolderLookup.Provider registries) {
        this.registries = registries;
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> exporter) {
        exporter.accept(entityKey("snake"), dropItem(ModItems.HABU.get()));
        exporter.accept(entityKey("ancient_lizard"), dropItem(ModItems.ANCIENT_LIZARD_HELMET.get()));
        exporter.accept(entityKey("asian_bear"), dropItem(ModItems.LUNAR_CLAW_BLADE.get()));
        exporter.accept(entityKey("clione"), dropItem(ModItems.CLIONE_STAFF.get()));
        exporter.accept(entityKey("crocodile"), dropItem(ModItems.CROCODILE_JAW_CHAIN.get()));
        exporter.accept(entityKey("emu"), dropItem(ModItems.HABU.get()));
        exporter.accept(entityKey("flower_mantis"), dropItem(ModItems.FLOWER_MANTIS_STAFF.get()));
        exporter.accept(entityKey("hammer_head"), dropItem(ModItems.HAMMER_HEAD.get()));
        exporter.accept(entityKey("leopard_gecko"), dropItem(ModItems.LEOPARD_GECKO_TAIL_BELT.get()));
        exporter.accept(entityKey("perisso"), dropItem(ModItems.PERISO_HELMET.get()));
        exporter.accept(entityKey("rock_penguin"), dropItem(ModItems.ROCK_PENGUIN_BOOTS.get()));
    }

    private static ResourceKey<LootTable> entityKey(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE,
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "entities/" + name));
    }

    private static LootTable.Builder dropItem(net.minecraft.world.item.Item item) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .setBonusRolls(ConstantValue.exactly(0))
                        .add(LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1)))));
    }
}
