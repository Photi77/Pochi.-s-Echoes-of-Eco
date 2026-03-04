package net.pochi.pochimod.datagen;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.pochi.pochimod.block.ModBlocks;
import net.pochi.pochimod.block.custom.fruits.AlmondBlock;
import net.pochi.pochimod.block.custom.fruits.ColaBlock;
import net.pochi.pochimod.block.custom.fruits.DurianBlock;
import net.pochi.pochimod.block.custom.fruits.GrapeBlock;
import net.pochi.pochimod.block.custom.crops.*;
import net.pochi.pochimod.item.ModItems;

import java.util.Set;

import net.minecraft.core.HolderLookup;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {

        add(ModBlocks.RAINBOW_WOOD.get(),
                (block) -> createOreDrop(ModBlocks.CHROMITE_ORE.get(), ModItems.ROW_CHROMITE.get()));


        //宝石の国
        dropSelf(ModBlocks.CINNABAR_BLOCK.get());
        add(ModBlocks.CINNABAR_CLUSTER.get(), (p_252201_) -> {
            return createSilkTouchDispatchTable(p_252201_, LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F))).apply(ApplyBonusCount.addOreBonusCount(this.registries.lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE))).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES))).otherwise(applyExplosionDecay(p_252201_, LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))))));
        });
        dropWhenSilkTouch(ModBlocks.LARGE_CINNABAR_BUD.get());
        dropWhenSilkTouch(ModBlocks.MEDIUM_CINNABAR_BUD.get());
        dropWhenSilkTouch(ModBlocks.SMALL_CINNABAR_BUD.get());
        add(ModBlocks.BUDDING_CINNABAR.get(), noDrop());

        dropSelf(ModBlocks.BLACK_DIAMOND_BLOCK.get());
        add(ModBlocks.BLACK_DIAMOND_CLUSTER.get(), (p_252201_) -> {
            return createSilkTouchDispatchTable(p_252201_, LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F))).apply(ApplyBonusCount.addOreBonusCount(this.registries.lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE))).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES))).otherwise(applyExplosionDecay(p_252201_, LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))))));
        });
        dropWhenSilkTouch(ModBlocks.LARGE_BLACK_DIAMOND_BUD.get());
        dropWhenSilkTouch(ModBlocks.MEDIUM_BLACK_DIAMOND_BUD.get());
        dropWhenSilkTouch(ModBlocks.SMALL_BLACK_DIAMOND_BUD.get());
        add(ModBlocks.BUDDING_BLACK_DIAMOND.get(), noDrop());

        dropSelf(ModBlocks.YELLOW_DIAMOND_BLOCK.get());
        add(ModBlocks.YELLOW_DIAMOND_CLUSTER.get(), (p_252201_) -> {
            return createSilkTouchDispatchTable(p_252201_, LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F))).apply(ApplyBonusCount.addOreBonusCount(this.registries.lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE))).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES))).otherwise(applyExplosionDecay(p_252201_, LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))))));
        });
        dropWhenSilkTouch(ModBlocks.LARGE_YELLOW_DIAMOND_BUD.get());
        dropWhenSilkTouch(ModBlocks.MEDIUM_YELLOW_DIAMOND_BUD.get());
        dropWhenSilkTouch(ModBlocks.SMALL_YELLOW_DIAMOND_BUD.get());
        add(ModBlocks.BUDDING_YELLOW_DIAMOND.get(), noDrop());

        dropSelf(ModBlocks.MORGANITE_BLOCK.get());
        add(ModBlocks.MORGANITE_CLUSTER.get(), (p_252201_) -> {
            return createSilkTouchDispatchTable(p_252201_, LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F))).apply(ApplyBonusCount.addOreBonusCount(this.registries.lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE))).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES))).otherwise(applyExplosionDecay(p_252201_, LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))))));
        });
        dropWhenSilkTouch(ModBlocks.LARGE_MORGANITE_BUD.get());
        dropWhenSilkTouch(ModBlocks.MEDIUM_MORGANITE_BUD.get());
        dropWhenSilkTouch(ModBlocks.SMALL_MORGANITE_BUD.get());
        add(ModBlocks.BUDDING_MORGANITE.get(), noDrop());

        dropSelf(ModBlocks.GOSHENITE_BLOCK.get());
        add(ModBlocks.GOSHENITE_CLUSTER.get(), (p_252201_) -> {
            return createSilkTouchDispatchTable(p_252201_, LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F))).apply(ApplyBonusCount.addOreBonusCount(this.registries.lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE))).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES))).otherwise(applyExplosionDecay(p_252201_, LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))))));
        });
        dropWhenSilkTouch(ModBlocks.LARGE_GOSHENITE_BUD.get());
        dropWhenSilkTouch(ModBlocks.MEDIUM_GOSHENITE_BUD.get());
        dropWhenSilkTouch(ModBlocks.SMALL_GOSHENITE_BUD.get());
        add(ModBlocks.BUDDING_GOSHENITE.get(), noDrop());

        dropSelf(ModBlocks.RUTILE_BLOCK.get());
        add(ModBlocks.RUTILE_CLUSTER.get(), (p_252201_) -> {
            return createSilkTouchDispatchTable(p_252201_, LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F))).apply(ApplyBonusCount.addOreBonusCount(this.registries.lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE))).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES))).otherwise(applyExplosionDecay(p_252201_, LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))))));
        });
        dropWhenSilkTouch(ModBlocks.LARGE_RUTILE_BUD.get());
        dropWhenSilkTouch(ModBlocks.MEDIUM_RUTILE_BUD.get());
        dropWhenSilkTouch(ModBlocks.SMALL_RUTILE_BUD.get());
        add(ModBlocks.BUDDING_RUTILE.get(), noDrop());

        dropSelf(ModBlocks.JADE_BLOCK.get());
        add(ModBlocks.JADE_CLUSTER.get(), (p_252201_) -> {
            return createSilkTouchDispatchTable(p_252201_, LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F))).apply(ApplyBonusCount.addOreBonusCount(this.registries.lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE))).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES))).otherwise(applyExplosionDecay(p_252201_, LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))))));
        });
        dropWhenSilkTouch(ModBlocks.LARGE_JADE_BUD.get());
        dropWhenSilkTouch(ModBlocks.MEDIUM_JADE_BUD.get());
        dropWhenSilkTouch(ModBlocks.SMALL_JADE_BUD.get());
        add(ModBlocks.BUDDING_JADE.get(), noDrop());

        dropSelf(ModBlocks.EUCLASE_BLOCK.get());
        add(ModBlocks.EUCLASE_CLUSTER.get(), (p_252201_) -> {
            return createSilkTouchDispatchTable(p_252201_, LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F))).apply(ApplyBonusCount.addOreBonusCount(this.registries.lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE))).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES))).otherwise(applyExplosionDecay(p_252201_, LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))))));
        });
        dropWhenSilkTouch(ModBlocks.LARGE_EUCLASE_BUD.get());
        dropWhenSilkTouch(ModBlocks.MEDIUM_EUCLASE_BUD.get());
        dropWhenSilkTouch(ModBlocks.SMALL_EUCLASE_BUD.get());
        add(ModBlocks.BUDDING_EUCLASE.get(), noDrop());

        dropSelf(ModBlocks.ALEXANDRITE_BLOCK.get());
        add(ModBlocks.ALEXANDRITE_CLUSTER.get(), (p_252201_) -> {
            return createSilkTouchDispatchTable(p_252201_, LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F))).apply(ApplyBonusCount.addOreBonusCount(this.registries.lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE))).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES))).otherwise(applyExplosionDecay(p_252201_, LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))))));
        });
        dropWhenSilkTouch(ModBlocks.LARGE_ALEXANDRITE_BUD.get());
        dropWhenSilkTouch(ModBlocks.MEDIUM_ALEXANDRITE_BUD.get());
        dropWhenSilkTouch(ModBlocks.SMALL_ALEXANDRITE_BUD.get());
        add(ModBlocks.BUDDING_ALEXANDRITE.get(), noDrop());

        dropSelf(ModBlocks.PHOSPHOPHYLITE_BLOCK.get());
        add(ModBlocks.PHOSPHOPHYLITE_CLUSTER.get(), (p_252201_) -> {
            return createSilkTouchDispatchTable(p_252201_, LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F))).apply(ApplyBonusCount.addOreBonusCount(this.registries.lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE))).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES))).otherwise(applyExplosionDecay(p_252201_, LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))))));
        });
        dropWhenSilkTouch(ModBlocks.LARGE_PHOSPHOPHYLITE_BUD.get());
        dropWhenSilkTouch(ModBlocks.MEDIUM_PHOSPHOPHYLITE_BUD.get());
        dropWhenSilkTouch(ModBlocks.SMALL_PHOSPHOPHYLITE_BUD.get());
        add(ModBlocks.BUDDING_PHOSPHOPHYLITE.get(), noDrop());



        dropOther(ModBlocks.BUBBLE.get(), Items.AIR);
        dropSelf(ModBlocks.SALT_BLOCK.get());

        add(ModBlocks.CHROMITE_ORE.get(),
                (block) -> createOreDrop(ModBlocks.CHROMITE_ORE.get(), ModItems.ROW_CHROMITE.get()));
        add(ModBlocks.DEEPSLATE_CHROMITE_ORE.get(),
                (block) -> createOreDrop(ModBlocks.DEEPSLATE_CHROMITE_ORE.get(), ModItems.ROW_CHROMITE.get()));
        add(ModBlocks.NETHERRACK_CHROMITE_ORE.get(),
                (block) -> createOreDrop(ModBlocks.NETHERRACK_CHROMITE_ORE.get(), ModItems.ROW_CHROMITE.get()));

        add(ModBlocks.FLUORITE_ORE.get(),
                (block) -> createOreDrop(ModBlocks.FLUORITE_ORE.get(), ModItems.ROW_FLUORITE.get()));
        add(ModBlocks.DEEPSLATE_FLUORITE_ORE.get(),
                (block) -> createOreDrop(ModBlocks.DEEPSLATE_FLUORITE_ORE.get(), ModItems.ROW_FLUORITE.get()));
        add(ModBlocks.NETHERRACK_FLUORITE_ORE.get(),
                (block) -> createOreDrop(ModBlocks.NETHERRACK_FLUORITE_ORE.get(), ModItems.ROW_FLUORITE.get()));

        add(ModBlocks.ALUNITE_ORE.get(),
                (block) -> createOreDrop(ModBlocks.ALUNITE_ORE.get(), ModItems.ROW_ALUNITE.get()));
        add(ModBlocks.DEEPSLATE_ALUNITE_ORE.get(),
                (block) -> createOreDrop(ModBlocks.DEEPSLATE_ALUNITE_ORE.get(), ModItems.ROW_ALUNITE.get()));
        add(ModBlocks.NETHERRACK_ALUNITE_ORE.get(),
                (block) -> createOreDrop(ModBlocks.NETHERRACK_ALUNITE_ORE.get(), ModItems.ROW_ALUNITE.get()));

        add(ModBlocks.BAUXITE_ORE.get(),
                (block) -> createOreDrop(ModBlocks.BAUXITE_ORE.get(), ModItems.ROW_BAUXITE.get()));
        add(ModBlocks.DEEPSLATE_BAUXITE_ORE.get(),
                (block) -> createOreDrop(ModBlocks.DEEPSLATE_BAUXITE_ORE.get(), ModItems.ROW_BAUXITE.get()));
        add(ModBlocks.NETHERRACK_BAUXITE_ORE.get(),
                (block) -> createOreDrop(ModBlocks.NETHERRACK_BAUXITE_ORE.get(), ModItems.ROW_BAUXITE.get()));

        add(ModBlocks.TITANIUM_ORE.get(),
                (block) -> createOreDrop(ModBlocks.TITANIUM_ORE.get(), ModItems.ROW_TITANIUM.get()));
        add(ModBlocks.DEEPSLATE_TITANIUM_ORE.get(),
                (block) -> createOreDrop(ModBlocks.DEEPSLATE_TITANIUM_ORE.get(), ModItems.ROW_TITANIUM.get()));
        add(ModBlocks.NETHERRACK_TITANIUM_ORE.get(),
                (block) -> createOreDrop(ModBlocks.NETHERRACK_TITANIUM_ORE.get(), ModItems.ROW_TITANIUM.get()));

        add(ModBlocks.MAGUNESIUM_ORE.get(),
                (block) -> createOreDrop(ModBlocks.MAGUNESIUM_ORE.get(), ModItems.ROW_MAGUNESIUM.get()));
        add(ModBlocks.DEEPSLATE_MAGUNESIUM_ORE.get(),
                (block) -> createOreDrop(ModBlocks.DEEPSLATE_MAGUNESIUM_ORE.get(), ModItems.ROW_MAGUNESIUM.get()));
        add(ModBlocks.NETHERRACK_MAGUNESIUM_ORE.get(),
                (block) -> createOreDrop(ModBlocks.NETHERRACK_MAGUNESIUM_ORE.get(), ModItems.ROW_MAGUNESIUM.get()));

        add(ModBlocks.VANADIUM_ORE.get(),
                (block) -> createOreDrop(ModBlocks.VANADIUM_ORE.get(), ModItems.ROW_VANADIUM.get()));
        add(ModBlocks.DEEPSLATE_VANADIUM_ORE.get(),
                (block) -> createOreDrop(ModBlocks.DEEPSLATE_VANADIUM_ORE.get(), ModItems.ROW_VANADIUM.get()));
        add(ModBlocks.NETHERRACK_VANADIUM_ORE.get(),
                (block) -> createOreDrop(ModBlocks.NETHERRACK_VANADIUM_ORE.get(), ModItems.ROW_VANADIUM.get()));

        dropSelf(ModBlocks.CHROMITE_BLOCK.get());
        dropSelf(ModBlocks.STAINLESS_BLOCK.get());
        dropSelf(ModBlocks.FLUORITE_BLOCK.get());
        dropSelf(ModBlocks.ALUNITE_BLOCK.get());
        dropSelf(ModBlocks.SANITARY_BLOCK.get());
        dropSelf(ModBlocks.ALUMINUM_BLOCK.get());
        dropSelf(ModBlocks.TITANIUM_BLOCK.get());
        dropSelf(ModBlocks.MAGUNESIUM_BLOCK.get());
        dropSelf(ModBlocks.VANADIUM_BLOCK.get());
        dropSelf(ModBlocks.PAMMUKALE_BLOCK.get());

        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.ASPARAGUS.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AsparagusBlock.AGE, 3));
        this.add(ModBlocks.ASPARAGUS.get(), this.createCropDrops(ModBlocks.ASPARAGUS.get(), ModItems.ASPARAGUS.get(), ModItems.ASPARAGUS_SEEDS.get(), lootitemcondition$builder));

        LootItemCondition.Builder lootitemcondition$builder1 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CABBAGE.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CabbageBlock.AGE, 3));
        this.add(ModBlocks.CABBAGE.get(), this.createCropDrops(ModBlocks.CABBAGE.get(), ModItems.CABBAGE_LEAF.get(), ModItems.CABBAGE_SEEDS.get(), lootitemcondition$builder1));

        LootItemCondition.Builder lootitemcondition$builder2 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.RICE_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RiceBlock.AGE, 3));
        this.add(ModBlocks.RICE_BLOCK.get(), this.createCropDrops(ModBlocks.RICE_BLOCK.get(), ModItems.ROW_RICE.get(), ModItems.RICE_SEEDS.get(), lootitemcondition$builder2));

        LootItemCondition.Builder lootitemcondition$builder3 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.MINT_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MintBlock.AGE, 3));
        this.add(ModBlocks.MINT_BLOCK.get(), this.createCropDrops(ModBlocks.MINT_BLOCK.get(), ModItems.MINT.get(), ModItems.MINT_SEEDS.get(), lootitemcondition$builder3));

        LootItemCondition.Builder lootitemcondition$builder4 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.TOMATO_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TomatoBlock.AGE, 3));
        this.add(ModBlocks.TOMATO_BLOCK.get(), this.createCropDrops(ModBlocks.TOMATO_BLOCK.get(), ModItems.TOMATO.get(), ModItems.TOMATO_SEEDS.get(), lootitemcondition$builder4));

        LootItemCondition.Builder lootitemcondition$builder5 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CORN_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CornBlock.AGE, 3));
        this.add(ModBlocks.CORN_BLOCK.get(), this.createCropDrops(ModBlocks.CORN_BLOCK.get(), ModItems.CORN.get(), ModItems.CORN_SEEDS.get(), lootitemcondition$builder5));

        LootItemCondition.Builder lootitemcondition$builder6 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.ONION_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OnionBlock.AGE, 3));
        this.add(ModBlocks.ONION_BLOCK.get(), this.createCropDrops(ModBlocks.ONION_BLOCK.get(), ModItems.ONION.get(), ModItems.ONION_SEEDS.get(), lootitemcondition$builder6));

        LootItemCondition.Builder lootitemcondition$builder7 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.GINGER_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GingerBlock.AGE, 3));
        this.add(ModBlocks.GINGER_BLOCK.get(), this.createCropDrops(ModBlocks.GINGER_BLOCK.get(), ModItems.GINGER.get(), ModItems.GINGER_SEEDS.get(), lootitemcondition$builder7));

        LootItemCondition.Builder lootitemcondition$builder8 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.GREEN_PEPPER_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GreenPepperBlock.AGE, 3));
        this.add(ModBlocks.GREEN_PEPPER_BLOCK.get(), this.createCropDrops(ModBlocks.GREEN_PEPPER_BLOCK.get(), ModItems.GREEN_PEPPER.get(), ModItems.GREEN_PEPPER_SEEDS.get(), lootitemcondition$builder8));

        LootItemCondition.Builder lootitemcondition$builder9 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.PAPRIKA_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PaprikaBlock.AGE, 3));
        this.add(ModBlocks.PAPRIKA_BLOCK.get(), this.createCropDrops(ModBlocks.PAPRIKA_BLOCK.get(), ModItems.PAPRIKA.get(), ModItems.PAPRIKA_SEEDS.get(), lootitemcondition$builder9));

        LootItemCondition.Builder lootitemcondition$builder10 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.EGGPLANT_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(EggplantBlock.AGE, 3));
        this.add(ModBlocks.EGGPLANT_BLOCK.get(), this.createCropDrops(ModBlocks.EGGPLANT_BLOCK.get(), ModItems.EGGPLANT.get(), ModItems.EGGPLANT_SEEDS.get(), lootitemcondition$builder10));

        LootItemCondition.Builder lootitemcondition$builder11 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.WHITE_RADISH_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WhiteRadishBlock.AGE, 3));
        this.add(ModBlocks.WHITE_RADISH_BLOCK.get(), this.createCropDrops(ModBlocks.WHITE_RADISH_BLOCK.get(), ModItems.WHITE_RADISH.get(), ModItems.WHITE_RADISH_SEEDS.get(), lootitemcondition$builder11));

        LootItemCondition.Builder lootitemcondition$builder12 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CHILI_PEPPER_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ChiliPepperBlock.AGE, 3));
        this.add(ModBlocks.CHILI_PEPPER_BLOCK.get(), this.createCropDrops(ModBlocks.CHILI_PEPPER_BLOCK.get(), ModItems.CHILI_PEPPER.get(), ModItems.CHILI_PEPPER_SEEDS.get(), lootitemcondition$builder12));

        LootItemCondition.Builder lootitemcondition$builder13 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BASIL_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BasilBlock.AGE, 3));
        this.add(ModBlocks.BASIL_BLOCK.get(), this.createCropDrops(ModBlocks.BASIL_BLOCK.get(), ModItems.BASIL.get(), ModItems.BASIL_SEEDS.get(), lootitemcondition$builder13));

        LootItemCondition.Builder lootitemcondition$builder14 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.LOTUS_ROOT_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LotusRootBlock.AGE, 3));
        this.add(ModBlocks.LOTUS_ROOT_BLOCK.get(), this.createCropDrops(ModBlocks.LOTUS_ROOT_BLOCK.get(), ModItems.LOTUS_ROOT.get(), ModItems.LOTUS_ROOT_SEEDS.get(), lootitemcondition$builder14));

        LootItemCondition.Builder lootitemcondition$builder15 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CROP_DIAMOND.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LotusRootBlock.AGE, 3));
        this.add(ModBlocks.CROP_DIAMOND.get(), this.createCropDrops(ModBlocks.CROP_DIAMOND.get(), Items.DIAMOND, Items.DIAMOND, lootitemcondition$builder15));

        LootItemCondition.Builder lootitemcondition$builder16 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CROP_COAL.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LotusRootBlock.AGE, 3));
        this.add(ModBlocks.CROP_COAL.get(), this.createCropDrops(ModBlocks.CROP_COAL.get(), Items.COAL, Items.COAL, lootitemcondition$builder16));

        LootItemCondition.Builder lootitemcondition$builder17 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CROP_REDSTONE.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LotusRootBlock.AGE, 3));
        this.add(ModBlocks.CROP_REDSTONE.get(), this.createCropDrops(ModBlocks.CROP_REDSTONE.get(), Items.REDSTONE, Items.REDSTONE, lootitemcondition$builder17));

        LootItemCondition.Builder lootitemcondition$builder18 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CROP_LAPIS.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LotusRootBlock.AGE, 3));
        this.add(ModBlocks.CROP_LAPIS.get(), this.createCropDrops(ModBlocks.CROP_LAPIS.get(), Items.LAPIS_LAZULI, Items.LAPIS_LAZULI, lootitemcondition$builder18));

        LootItemCondition.Builder lootitemcondition$builder19 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CROP_GOLD.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LotusRootBlock.AGE, 3));
        this.add(ModBlocks.CROP_GOLD.get(), this.createCropDrops(ModBlocks.CROP_GOLD.get(), Items.RAW_GOLD, Items.RAW_GOLD, lootitemcondition$builder19));

        LootItemCondition.Builder lootitemcondition$builder20 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CROP_IRON.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LotusRootBlock.AGE, 3));
        this.add(ModBlocks.CROP_IRON.get(), this.createCropDrops(ModBlocks.CROP_IRON.get(),Items.RAW_IRON, Items.RAW_IRON, lootitemcondition$builder20));

        LootItemCondition.Builder lootitemcondition$builder21 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CROP_COPPER.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LotusRootBlock.AGE, 3));
        this.add(ModBlocks.CROP_COPPER.get(), this.createCropDrops(ModBlocks.CROP_COPPER.get(), Items.RAW_COPPER, Items.RAW_COPPER, lootitemcondition$builder21));

        LootItemCondition.Builder lootitemcondition$builder22 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CROP_EMERALD.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LotusRootBlock.AGE, 3));
        this.add(ModBlocks.CROP_EMERALD.get(), this.createCropDrops(ModBlocks.CROP_EMERALD.get(), Items.EMERALD, Items.EMERALD, lootitemcondition$builder22));

        this.add(ModBlocks.SEED_PLAIN.get(), this.applyExplosionDecay(ModBlocks.SEED_PLAIN.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.ASPARAGUS_SEEDS.get())))
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.MINT_SEEDS.get())))
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.CORN_SEEDS.get())))
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.BASIL_SEEDS.get())))));

        this.add(ModBlocks.SEED_JUNGLE.get(), this.applyExplosionDecay(ModBlocks.SEED_JUNGLE.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.RICE_SEEDS.get())))
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.GREEN_PEPPER_SEEDS.get())))
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.PAPRIKA_SEEDS.get())))
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.LOTUS_ROOT_SEEDS.get())))));

        this.add(ModBlocks.SEED_TAIGA.get(), this.applyExplosionDecay(ModBlocks.SEED_TAIGA.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.CABBAGE_SEEDS.get())))
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.ONION_SEEDS.get())))
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.GINGER_SEEDS.get())))
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.WHITE_RADISH_SEEDS.get())))));

        this.add(ModBlocks.SEED_SAVANNA.get(), this.applyExplosionDecay(ModBlocks.SEED_SAVANNA.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.TOMATO_SEEDS.get())))
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.EGGPLANT_SEEDS.get())))
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.CHILI_PEPPER_SEEDS.get())))));

        dropSelf(ModBlocks.BFURNACE_BLOCK.get());
        dropSelf(ModBlocks.DISTILLER_BLOCK.get());
        dropSelf(ModBlocks.SAKE_DARU_BLOCK.get());
        dropSelf(ModBlocks.MIXER_BLOCK.get());
        dropSelf(ModBlocks.FRYPAN_BLOCK.get());

        dropSelf(ModBlocks.CABERNET_SAUVIGNON_LOG.get());
        dropSelf(ModBlocks.CABERNET_SAUVIGNON_WOOD.get());
        dropSelf(ModBlocks.CABERNET_SAUVIGNON_PLANKS.get());
        dropSelf(ModBlocks.STRIPPED_CABERNET_SAUVIGNON_LOG.get());
        dropSelf(ModBlocks.STRIPPED_CABERNET_SAUVIGNON_WOOD.get());
        dropSelf(ModBlocks.CABERNET_SAUVIGNON_SAPLING.get());

        add(ModBlocks.CABERNET_SAUVIGNON_LEAVES.get(), (block) ->
                createLeavesDrops(block, ModBlocks.CABERNET_SAUVIGNON_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        LootItemCondition.Builder lootitemcondition$builder100 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.GRAPE_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GrapeBlock.AGE, 4));
        this.add(ModBlocks.GRAPE_BLOCK.get(), this.applyExplosionDecay(ModBlocks.GRAPE_BLOCK.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().when(lootitemcondition$builder100).add(LootItem.lootTableItem(ModItems.GRAPE.get())))));

        dropSelf(ModBlocks.MAPLE_LOG.get());
        dropSelf(ModBlocks.MAPLE_WOOD.get());
        dropSelf(ModBlocks.MAPLE_PLANKS.get());
        dropSelf(ModBlocks.STRIPPED_MAPLE_LOG.get());
        dropSelf(ModBlocks.STRIPPED_MAPLE_WOOD.get());
        dropSelf(ModBlocks.MAPLE_SAPLING.get());

        add(ModBlocks.MAPLE_LEAVES.get(), (block) ->
                createLeavesDrops(block, ModBlocks.MAPLE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        dropSelf(ModBlocks.CINNAMON_LOG.get());
        dropSelf(ModBlocks.CINNAMON_WOOD.get());
        dropSelf(ModBlocks.CINNAMON_PLANKS.get());
        dropSelf(ModBlocks.STRIPPED_CINNAMON_LOG.get());
        dropSelf(ModBlocks.STRIPPED_CINNAMON_WOOD.get());
        dropSelf(ModBlocks.CINNAMON_SAPLING.get());

        add(ModBlocks.CINNAMON_LEAVES.get(), (block) ->
                createLeavesDrops(block, ModBlocks.CINNAMON_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        dropSelf(ModBlocks.COLA_LOG.get());
        dropSelf(ModBlocks.COLA_WOOD.get());
        dropSelf(ModBlocks.COLA_PLANKS.get());
        dropSelf(ModBlocks.STRIPPED_COLA_LOG.get());
        dropSelf(ModBlocks.STRIPPED_COLA_WOOD.get());
        dropSelf(ModBlocks.COLA_SAPLING.get());

        add(ModBlocks.COLA_LEAVES.get(), (block) ->
                createLeavesDrops(block, ModBlocks.COLA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        add(ModBlocks.COLA_FRUIT.get(), (p_250228_) -> {
            return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(p_250228_, LootItem.lootTableItem(ModItems.COLA.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(p_250228_).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ColaBlock.AGE, 2)))))));
        });

        dropSelf(ModBlocks.LEMON_LOG.get());
        dropSelf(ModBlocks.LEMON_WOOD.get());
        dropSelf(ModBlocks.LEMON_PLANKS.get());
        dropSelf(ModBlocks.STRIPPED_LEMON_LOG.get());
        dropSelf(ModBlocks.STRIPPED_LEMON_WOOD.get());
        dropSelf(ModBlocks.LEMON_SAPLING.get());

        add(ModBlocks.LEMON_LEAVES.get(), (block) ->
                createLeavesDrops(block, ModBlocks.LEMON_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        LootItemCondition.Builder lootitemcondition$builder101 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.LEMON_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GrapeBlock.AGE, 4));
        this.add(ModBlocks.LEMON_BLOCK.get(), this.applyExplosionDecay(ModBlocks.LEMON_BLOCK.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().when(lootitemcondition$builder101).add(LootItem.lootTableItem(ModItems.LEMON.get())))));

        dropSelf(ModBlocks.PLUM_LOG.get());
        dropSelf(ModBlocks.PLUM_WOOD.get());
        dropSelf(ModBlocks.PLUM_PLANKS.get());
        dropSelf(ModBlocks.STRIPPED_PLUM_LOG.get());
        dropSelf(ModBlocks.STRIPPED_PLUM_WOOD.get());
        dropSelf(ModBlocks.PLUM_SAPLING.get());

        add(ModBlocks.PLUM_LEAVES.get(), (block) ->
                createLeavesDrops(block, ModBlocks.PLUM_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        LootItemCondition.Builder lootitemcondition$builder102 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.PLUM_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GrapeBlock.AGE, 4));
        this.add(ModBlocks.PLUM_BLOCK.get(), this.applyExplosionDecay(ModBlocks.PLUM_BLOCK.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().when(lootitemcondition$builder102).add(LootItem.lootTableItem(ModItems.PLUM.get())))));

        dropSelf(ModBlocks.CHERRY_LOG.get());
        dropSelf(ModBlocks.CHERRY_WOOD.get());
        dropSelf(ModBlocks.CHERRY_PLANKS.get());
        dropSelf(ModBlocks.STRIPPED_CHERRY_LOG.get());
        dropSelf(ModBlocks.STRIPPED_CHERRY_WOOD.get());
        dropSelf(ModBlocks.CHERRY_SAPLING.get());

        add(ModBlocks.CHERRY_LEAVES.get(), (block) ->
                createLeavesDrops(block, ModBlocks.CHERRY_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        LootItemCondition.Builder lootitemcondition$builder103 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CHERRY_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GrapeBlock.AGE, 4));
        this.add(ModBlocks.CHERRY_BLOCK.get(), this.applyExplosionDecay(ModBlocks.CHERRY_BLOCK.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().when(lootitemcondition$builder103).add(LootItem.lootTableItem(ModItems.CHERRY.get())))));

        dropSelf(ModBlocks.BANANA_LOG.get());
        dropSelf(ModBlocks.BANANA_WOOD.get());
        dropSelf(ModBlocks.BANANA_PLANKS.get());
        dropSelf(ModBlocks.STRIPPED_BANANA_LOG.get());
        dropSelf(ModBlocks.STRIPPED_BANANA_WOOD.get());
        dropSelf(ModBlocks.BANANA_SAPLING.get());

        add(ModBlocks.BANANA_LEAVES.get(), (block) ->
                createLeavesDrops(block, ModBlocks.BANANA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        LootItemCondition.Builder lootitemcondition$builder104 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BANANA_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GrapeBlock.AGE, 4));
        this.add(ModBlocks.BANANA_BLOCK.get(), this.applyExplosionDecay(ModBlocks.BANANA_BLOCK.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().when(lootitemcondition$builder104).add(LootItem.lootTableItem(ModItems.BANANA.get())))));

        dropSelf(ModBlocks.COCONUT_LOG.get());
        dropSelf(ModBlocks.COCONUT_WOOD.get());
        dropSelf(ModBlocks.COCONUT_PLANKS.get());
        dropSelf(ModBlocks.STRIPPED_COCONUT_LOG.get());
        dropSelf(ModBlocks.STRIPPED_COCONUT_WOOD.get());
        dropSelf(ModBlocks.COCONUT_SAPLING.get());

        add(ModBlocks.COCONUT_LEAVES.get(), (block) ->
                createLeavesDrops(block, ModBlocks.COCONUT_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        LootItemCondition.Builder lootitemcondition$builder105 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.COCONUT_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GrapeBlock.AGE, 4));
        this.add(ModBlocks.COCONUT_BLOCK.get(), this.applyExplosionDecay(ModBlocks.COCONUT_BLOCK.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().when(lootitemcondition$builder105).add(LootItem.lootTableItem(ModItems.COCONUT.get())))));

        dropSelf(ModBlocks.PEACH_LOG.get());
        dropSelf(ModBlocks.PEACH_WOOD.get());
        dropSelf(ModBlocks.PEACH_PLANKS.get());
        dropSelf(ModBlocks.STRIPPED_PEACH_LOG.get());
        dropSelf(ModBlocks.STRIPPED_PEACH_WOOD.get());
        dropSelf(ModBlocks.PEACH_SAPLING.get());

        add(ModBlocks.PEACH_LEAVES.get(), (block) ->
                createLeavesDrops(block, ModBlocks.PEACH_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        LootItemCondition.Builder lootitemcondition$builder106 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.PEACH_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GrapeBlock.AGE, 4));
        this.add(ModBlocks.PEACH_BLOCK.get(), this.applyExplosionDecay(ModBlocks.PEACH_BLOCK.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().when(lootitemcondition$builder106).add(LootItem.lootTableItem(ModItems.PEACH.get())))));

        dropSelf(ModBlocks.ALMOND_LOG.get());
        dropSelf(ModBlocks.ALMOND_WOOD.get());
        dropSelf(ModBlocks.ALMOND_PLANKS.get());
        dropSelf(ModBlocks.STRIPPED_ALMOND_LOG.get());
        dropSelf(ModBlocks.STRIPPED_ALMOND_WOOD.get());
        dropSelf(ModBlocks.ALMOND_SAPLING.get());

        add(ModBlocks.ALMOND_LEAVES.get(), (block) ->
                createLeavesDrops(block, ModBlocks.ALMOND_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        add(ModBlocks.ALMOND_FRUIT.get(), (p_250228_) -> {
            return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(p_250228_, LootItem.lootTableItem(ModItems.ALMOND.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(p_250228_).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AlmondBlock.AGE, 2)))))));
        });

        dropSelf(ModBlocks.DURIAN_LOG.get());
        dropSelf(ModBlocks.DURIAN_WOOD.get());
        dropSelf(ModBlocks.DURIAN_PLANKS.get());
        dropSelf(ModBlocks.STRIPPED_DURIAN_LOG.get());
        dropSelf(ModBlocks.STRIPPED_DURIAN_WOOD.get());
        dropSelf(ModBlocks.DURIAN_SAPLING.get());

        add(ModBlocks.DURIAN_LEAVES.get(), (block) ->
                createLeavesDrops(block, ModBlocks.DURIAN_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        add(ModBlocks.DURIAN_FRUIT.get(), (p_250228_) -> {
            return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(p_250228_, LootItem.lootTableItem(ModItems.DURIAN.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(p_250228_).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DurianBlock.AGE, 2)))))));
        });

        dropSelf(ModBlocks.COFFEE_LOG.get());
        dropSelf(ModBlocks.COFFEE_WOOD.get());
        dropSelf(ModBlocks.COFFEE_PLANKS.get());
        dropSelf(ModBlocks.STRIPPED_COFFEE_LOG.get());
        dropSelf(ModBlocks.STRIPPED_COFFEE_WOOD.get());
        dropSelf(ModBlocks.COFFEE_SAPLING.get());

        add(ModBlocks.COFFEE_LEAVES.get(), (block) ->
                createLeavesDrops(block, ModBlocks.COFFEE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        add(ModBlocks.COFFEE_FRUIT.get(), (p_250228_) -> {
            return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(p_250228_, LootItem.lootTableItem(ModItems.ROW_COFFEE_BEANS.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(p_250228_).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DurianBlock.AGE, 2)))))));
        });

        dropSelf(ModBlocks.KIWI_LOG.get());
        dropSelf(ModBlocks.KIWI_WOOD.get());
        dropSelf(ModBlocks.KIWI_PLANKS.get());
        dropSelf(ModBlocks.STRIPPED_KIWI_LOG.get());
        dropSelf(ModBlocks.STRIPPED_KIWI_WOOD.get());
        dropSelf(ModBlocks.KIWI_SAPLING.get());

        add(ModBlocks.KIWI_LEAVES.get(), (block) ->
                createLeavesDrops(block, ModBlocks.KIWI_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        LootItemCondition.Builder lootitemcondition$builder107 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.KIWI_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GrapeBlock.AGE, 4));
        this.add(ModBlocks.KIWI_BLOCK.get(), this.applyExplosionDecay(ModBlocks.KIWI_BLOCK.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().when(lootitemcondition$builder107).add(LootItem.lootTableItem(ModItems.KIWI.get())))));

        dropSelf(ModBlocks.TEA_BUSH.get());
        dropSelf(ModBlocks.OLIVE_BUSH.get());
        dropSelf(ModBlocks.BLUE_BERRY_BUSH.get());
        dropSelf(ModBlocks.HOP_BUSH.get());
        dropSelf(ModBlocks.PEPPER_BUSH.get());

        dropSelf(ModBlocks.CABERNET_SAUVIGNON_STAIRS.get());
        dropSelf(ModBlocks.CABERNET_SAUVIGNON_SLAB.get());
        dropSelf(ModBlocks.CABERNET_SAUVIGNON_FENCE.get());
        dropSelf(ModBlocks.CABERNET_SAUVIGNON_FENCE_GATE.get());
        //dropSelf(ModBlocks.CABERNET_SAUVIGNON_BUTTON.get());
        dropSelf(ModBlocks.CABERNET_SAUVIGNON_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.CABERNET_SAUVIGNON_DOOR.get());
        dropSelf(ModBlocks.CABERNET_SAUVIGNON_TRAPDOOR.get());

        dropSelf(ModBlocks.MAPLE_STAIRS.get());
        dropSelf(ModBlocks.MAPLE_SLAB.get());
        dropSelf(ModBlocks.MAPLE_FENCE.get());
        dropSelf(ModBlocks.MAPLE_FENCE_GATE.get());
        //dropSelf(ModBlocks.MAPLE_BUTTON.get());
        dropSelf(ModBlocks.MAPLE_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.MAPLE_DOOR.get());
        dropSelf(ModBlocks.MAPLE_TRAPDOOR.get());

        dropSelf(ModBlocks.CINNAMON_STAIRS.get());
        dropSelf(ModBlocks.CINNAMON_SLAB.get());
        dropSelf(ModBlocks.CINNAMON_FENCE.get());
        dropSelf(ModBlocks.CINNAMON_FENCE_GATE.get());
        //dropSelf(ModBlocks.CINNAMON_BUTTON.get());
        dropSelf(ModBlocks.CINNAMON_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.CINNAMON_DOOR.get());
        dropSelf(ModBlocks.CINNAMON_TRAPDOOR.get());

        dropSelf(ModBlocks.COLA_STAIRS.get());
        dropSelf(ModBlocks.COLA_SLAB.get());
        dropSelf(ModBlocks.COLA_FENCE.get());
        dropSelf(ModBlocks.COLA_FENCE_GATE.get());
        //dropSelf(ModBlocks.COLA_BUTTON.get());
        dropSelf(ModBlocks.COLA_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.COLA_DOOR.get());
        dropSelf(ModBlocks.COLA_TRAPDOOR.get());

        dropSelf(ModBlocks.LEMON_STAIRS.get());
        dropSelf(ModBlocks.LEMON_SLAB.get());
        dropSelf(ModBlocks.LEMON_FENCE.get());
        dropSelf(ModBlocks.LEMON_FENCE_GATE.get());
        //dropSelf(ModBlocks.LEMON_BUTTON.get());
        dropSelf(ModBlocks.LEMON_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.LEMON_DOOR.get());
        dropSelf(ModBlocks.LEMON_TRAPDOOR.get());

        dropSelf(ModBlocks.PLUM_STAIRS.get());
        dropSelf(ModBlocks.PLUM_SLAB.get());
        dropSelf(ModBlocks.PLUM_FENCE.get());
        dropSelf(ModBlocks.PLUM_FENCE_GATE.get());
        //dropSelf(ModBlocks.PLUM_BUTTON.get());
        dropSelf(ModBlocks.PLUM_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.PLUM_DOOR.get());
        dropSelf(ModBlocks.PLUM_TRAPDOOR.get());

        dropSelf(ModBlocks.CHERRY_STAIRS.get());
        dropSelf(ModBlocks.CHERRY_SLAB.get());
        dropSelf(ModBlocks.CHERRY_FENCE.get());
        dropSelf(ModBlocks.CHERRY_FENCE_GATE.get());
        //dropSelf(ModBlocks.CHERRY_BUTTON.get());
        dropSelf(ModBlocks.CHERRY_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.CHERRY_DOOR.get());
        dropSelf(ModBlocks.CHERRY_TRAPDOOR.get());

        dropSelf(ModBlocks.BANANA_STAIRS.get());
        dropSelf(ModBlocks.BANANA_SLAB.get());
        dropSelf(ModBlocks.BANANA_FENCE.get());
        dropSelf(ModBlocks.BANANA_FENCE_GATE.get());
        //dropSelf(ModBlocks.BANANA_BUTTON.get());
        dropSelf(ModBlocks.BANANA_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.BANANA_DOOR.get());
        dropSelf(ModBlocks.BANANA_TRAPDOOR.get());

        dropSelf(ModBlocks.COCONUT_STAIRS.get());
        dropSelf(ModBlocks.COCONUT_SLAB.get());
        dropSelf(ModBlocks.COCONUT_FENCE.get());
        dropSelf(ModBlocks.COCONUT_FENCE_GATE.get());
        //dropSelf(ModBlocks.COCONUT_BUTTON.get());
        dropSelf(ModBlocks.COCONUT_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.COCONUT_DOOR.get());
        dropSelf(ModBlocks.COCONUT_TRAPDOOR.get());

        dropSelf(ModBlocks.PEACH_STAIRS.get());
        dropSelf(ModBlocks.PEACH_SLAB.get());
        dropSelf(ModBlocks.PEACH_FENCE.get());
        dropSelf(ModBlocks.PEACH_FENCE_GATE.get());
        //dropSelf(ModBlocks.PEACH_BUTTON.get());
        dropSelf(ModBlocks.PEACH_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.PEACH_DOOR.get());
        dropSelf(ModBlocks.PEACH_TRAPDOOR.get());

        dropSelf(ModBlocks.ALMOND_STAIRS.get());
        dropSelf(ModBlocks.ALMOND_SLAB.get());
        dropSelf(ModBlocks.ALMOND_FENCE.get());
        dropSelf(ModBlocks.ALMOND_FENCE_GATE.get());
        //dropSelf(ModBlocks.ALMOND_BUTTON.get());
        dropSelf(ModBlocks.ALMOND_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.ALMOND_DOOR.get());
        dropSelf(ModBlocks.ALMOND_TRAPDOOR.get());

        dropSelf(ModBlocks.DURIAN_STAIRS.get());
        dropSelf(ModBlocks.DURIAN_SLAB.get());
        dropSelf(ModBlocks.DURIAN_FENCE.get());
        dropSelf(ModBlocks.DURIAN_FENCE_GATE.get());
        //dropSelf(ModBlocks.DURIAN_BUTTON.get());
        dropSelf(ModBlocks.DURIAN_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.DURIAN_DOOR.get());
        dropSelf(ModBlocks.DURIAN_TRAPDOOR.get());

        dropSelf(ModBlocks.COFFEE_STAIRS.get());
        dropSelf(ModBlocks.COFFEE_SLAB.get());
        dropSelf(ModBlocks.COFFEE_FENCE.get());
        dropSelf(ModBlocks.COFFEE_FENCE_GATE.get());
        //dropSelf(ModBlocks.DURIAN_BUTTON.get());
        dropSelf(ModBlocks.COFFEE_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.COFFEE_DOOR.get());
        dropSelf(ModBlocks.COFFEE_TRAPDOOR.get());
        dropSelf(ModBlocks.RAW_COFFEE_BLOCK.get());
        dropSelf(ModBlocks.MUSK_COFFEE_BLOCK.get());
        dropSelf(ModBlocks.COFFEE_BLOCK.get());

        dropSelf(ModBlocks.KIWI_STAIRS.get());
        dropSelf(ModBlocks.KIWI_SLAB.get());
        dropSelf(ModBlocks.KIWI_FENCE.get());
        dropSelf(ModBlocks.KIWI_FENCE_GATE.get());
        //dropSelf(ModBlocks.CABERNET_SAUVIGNON_BUTTON.get());
        dropSelf(ModBlocks.KIWI_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.KIWI_DOOR.get());
        dropSelf(ModBlocks.KIWI_TRAPDOOR.get());

        dropSelf(ModBlocks.QUEEN_OF_NIGHT.get());
        dropSelf(ModBlocks.CAMOMILE.get());
        dropSelf(ModBlocks.ROSE.get());
        dropSelf(ModBlocks.THYME.get());
        dropSelf(ModBlocks.OREGANO.get());
        //dropSelf(ModBlocks.CREATE_TNT.get());


    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(h -> (Block) h.value())::iterator;
    }
}
