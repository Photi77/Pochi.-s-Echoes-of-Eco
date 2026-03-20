package net.pochi.pochimod.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.block.ModBlocks;
import net.pochi.pochimod.fluid.ModFluids;
import net.pochi.pochimod.item.custom.*;
import net.pochi.pochimod.item.custom.armor.*;
import net.pochi.pochimod.item.custom.spell.*;
import net.pochi.pochimod.item.custom.tool.HammerHeadPickaxe;
import net.pochi.pochimod.item.custom.tool.VitalCheckItem;
import net.pochi.pochimod.item.custom.weapon.*;
import net.pochi.pochimod.mineral.MineralChunkItem;
import net.pochi.pochimod.mineral.RiverBrushItem;
import net.pochi.pochimod.mineral.tools.*;
import net.pochi.pochimod.pottery.PotteryPattern;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(PochiMod.MOD_ID);


    public static final DeferredHolder<Item, Item> MINERAL_SWORD = ITEMS.register("mineral_sword",
            () -> new MineralSwordItem(
                    new Item.Properties()));
    public static final DeferredHolder<Item, Item> MINERAL_PICKAXE = ITEMS.register("mineral_pickaxe",
            () -> new MineralPickaxeItem(
                    new Item.Properties()));
    public static final DeferredHolder<Item, Item> MINERAL_SHOVEL = ITEMS.register("mineral_shovel",
            () -> new MineralShovelItem(
                    new Item.Properties()));
    public static final DeferredHolder<Item, Item> MINERAL_AXE = ITEMS.register("mineral_axe",
            () -> new MineralAxeItem(
                    new Item.Properties()));

    public static final DeferredHolder<Item, Item> MINERAL_HELMET = ITEMS.register("mineral_helmet",
            () -> new MineralArmorItem( ArmorType.HELMET, new Item.Properties()));
    public static final DeferredHolder<Item, Item> MINERAL_CHESTPLATE = ITEMS.register("mineral_chestplate",
            () -> new MineralArmorItem( ArmorType.CHESTPLATE, new Item.Properties()));
    public static final DeferredHolder<Item, Item> MINERAL_LEGGINGS = ITEMS.register("mineral_leggings",
            () -> new MineralArmorItem(ArmorType.LEGGINGS, new Item.Properties()));
    public static final DeferredHolder<Item, Item> MINERAL_BOOTS = ITEMS.register("mineral_boots",
            () -> new MineralArmorItem( ArmorType.BOOTS, new Item.Properties()));

    public static final DeferredHolder<Item, Item> MINERAL_RING = ITEMS.register("mineral_ring",
            () -> new Item(new Item.Properties()));

    //特殊アイテム
    public static final DeferredHolder<Item, Item> MINERAL_CHUNK = ITEMS.register(
            "mineral_chunk",
            () -> new MineralChunkItem(
                    new Item.Properties()
                            .stacksTo(64)
            )
    );

    public static final DeferredHolder<Item, Item> RIVER_BRUSH = ITEMS.register(
            "river_brush",
            () -> new RiverBrushItem(
                    new Item.Properties()
                            .durability(64)  // バニラブラシと同耐久
            )
    );


    public static final DeferredHolder<Item, Item> COMPOST = ITEMS.register("compost",
            () -> new CompostItem(
                    new Item.Properties().stacksTo(64)));
    public static final DeferredHolder<Item, Item> BAKED_ALUM = ITEMS.register("baked_alum",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> DRAGONFLY_WINGS = ITEMS.register("dragonfly_wings",
            () ->  new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> SALT = ITEMS.register("salt",
            () ->  new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> HABU = ITEMS.register("habu",
            () ->  new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> FLY_BOAT = ITEMS.register("fly_boat",
            () -> new FlyBoatItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> FLY_CHEST_BOAT = ITEMS.register("fly_chest_boat",
            () -> new FlyChestBoatItem(new Item.Properties()));

    //インゴット
    public static final DeferredHolder<Item, Item> CHROMITE_INGOT = ITEMS.register("chromite_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ALUMINIUM_INGOT = ITEMS.register("aluminium_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> MAGUNESIUM_INGOT = ITEMS.register("magunesium_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> VANADIUM_INGOT = ITEMS.register("vanadium_ingot",
            () -> new Item(new Item.Properties()));

    //インゴットfrom BFURNACE
    public static final DeferredHolder<Item, Item> STAINLESS = ITEMS.register("stainless",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> DURALUMIN = ITEMS.register("duralumin",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> TITAN_ALLOY = ITEMS.register("titan_alloy",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ELECTRON = ITEMS.register("electron",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> VANADIUM_ALLOY = ITEMS.register("vanadium_alloy",
            () -> new Item(new Item.Properties()));

    //原石
    public static final DeferredHolder<Item, Item> ROW_CHROMITE = ITEMS.register("row_chromite",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ROW_FLUORITE = ITEMS.register("row_fluorite",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ROW_ALUNITE = ITEMS.register("row_alunite",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ROW_BAUXITE = ITEMS.register("row_bauxite",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ROW_TITANIUM = ITEMS.register("row_titanium",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ROW_MAGUNESIUM = ITEMS.register("row_magunesium",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ROW_VANADIUM = ITEMS.register("row_vanadium",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> HAMMER_HEAD = ITEMS.register("hammer_head",
            () -> new Item(new Item.Properties()));


    //ツール
    public static final DeferredHolder<Item, Item> HAMMER_HEAD_PICKAXE = ITEMS.register("hammer_head_pickaxe",
            () -> new HammerHeadPickaxe(new Item.Properties()));

    //public static final DeferredHolder<Item, Item> MANTIS_SHRIMP_GRAB = ITEMS.register("mantis_shrimp_grab",
    //        () -> new HammerHeadPickaxe(3, -2.4F,
    //                new Item.Properties()));
//
    //public static final DeferredHolder<Item, Item> PORCUPINE_SWORD = ITEMS.register("porcupine_sword",
    //        () -> new HammerHeadPickaxe(3, -2.4F,
    //                new Item.Properties()));

    public static final DeferredHolder<Item, Item> CHROMITE_SWORD = ITEMS.register("chromite_sword",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> CHROMITE_PICKAXE = ITEMS.register("chromite_pickaxe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> CHROMITE_SHOVEL = ITEMS.register("chromite_shovel",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> CHROMITE_AXE = ITEMS.register("chromite_axe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> CHROMITE_HOE = ITEMS.register("chromite_hoe",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> FLUORITE_SWORD = ITEMS.register("fluorite_sword",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> FLUORITE_PICKAXE = ITEMS.register("fluorite_pickaxe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> FLUORITE_SHOVEL = ITEMS.register("fluorite_shovel",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> FLUORITE_AXE = ITEMS.register("fluorite_axe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> FLUORITE_HOE = ITEMS.register("fluorite_hoe",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> ALUNITE_SWORD = ITEMS.register("alunite_sword",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ALUNITE_PICKAXE = ITEMS.register("alunite_pickaxe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ALUNITE_SHOVEL = ITEMS.register("alunite_shovel",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ALUNITE_AXE = ITEMS.register("alunite_axe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ALUNITE_HOE = ITEMS.register("alunite_hoe",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> STAINLESS_SWORD = ITEMS.register("stainless_sword",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> STAINLESS_PICKAXE = ITEMS.register("stainless_pickaxe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> STAINLESS_SHOVEL = ITEMS.register("stainless_shovel",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> STAINLESS_AXE = ITEMS.register("stainless_axe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> STAINLESS_HOE = ITEMS.register("stainless_hoe",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> ALUMINIUM_SWORD = ITEMS.register("aluminium_sword",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ALUMINIUM_PICKAXE = ITEMS.register("aluminium_pickaxe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ALUMINIUM_SHOVEL = ITEMS.register("aluminium_shovel",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ALUMINIUM_AXE = ITEMS.register("aluminium_axe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ALUMINIUM_HOE = ITEMS.register("aluminium_hoe",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> TITANIUM_SWORD = ITEMS.register("titanium_sword",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> TITANIUM_PICKAXE = ITEMS.register("titanium_pickaxe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> TITANIUM_SHOVEL = ITEMS.register("titanium_shovel",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> TITANIUM_AXE = ITEMS.register("titanium_axe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> TITANIUM_HOE = ITEMS.register("titanium_hoe",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> MAGUNESIUM_SWORD = ITEMS.register("magunesium_sword",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> MAGUNESIUM_PICKAXE = ITEMS.register("magunesium_pickaxe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> MAGUNESIUM_SHOVEL = ITEMS.register("magunesium_shovel",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> MAGUNESIUM_AXE = ITEMS.register("magunesium_axe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> MAGUNESIUM_HOE = ITEMS.register("magunesium_hoe",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> VANADIUM_SWORD = ITEMS.register("vanadium_sword",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> VANADIUM_PICKAXE = ITEMS.register("vanadium_pickaxe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> VANADIUM_SHOVEL = ITEMS.register("vanadium_shovel",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> VANADIUM_AXE = ITEMS.register("vanadium_axe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> VANADIUM_HOE = ITEMS.register("vanadium_hoe",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> DURALUMIN_SWORD = ITEMS.register("duralumin_sword",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> DURALUMIN_PICKAXE = ITEMS.register("duralumin_pickaxe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> DURALUMIN_SHOVEL = ITEMS.register("duralumin_shovel",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> DURALUMIN_AXE = ITEMS.register("duralumin_axe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> DURALUMIN_HOE = ITEMS.register("duralumin_hoe",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> TITAN_ALLOY_SWORD = ITEMS.register("titan_alloy_sword",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> TITAN_ALLOY_PICKAXE = ITEMS.register("titan_alloy_pickaxe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> TITAN_ALLOY_SHOVEL = ITEMS.register("titan_alloy_shovel",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> TITAN_ALLOY_AXE = ITEMS.register("titan_alloy_axe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> TITAN_ALLOY_HOE = ITEMS.register("titan_alloy_hoe",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> ELECTRON_SWORD = ITEMS.register("electron_sword",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ELECTRON_PICKAXE = ITEMS.register("electron_pickaxe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ELECTRON_SHOVEL = ITEMS.register("electron_shovel",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ELECTRON_AXE = ITEMS.register("electron_axe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ELECTRON_HOE = ITEMS.register("electron_hoe",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> VANADIUM_ALLOY_SWORD = ITEMS.register("vanadium_alloy_sword",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> VANADIUM_ALLOY_PICKAXE = ITEMS.register("vanadium_alloy_pickaxe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> VANADIUM_ALLOY_SHOVEL = ITEMS.register("vanadium_alloy_shovel",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> VANADIUM_ALLOY_AXE = ITEMS.register("vanadium_alloy_axe",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> VANADIUM_ALLOY_HOE = ITEMS.register("vanadium_alloy_hoe",
            () -> new Item(new Item.Properties().stacksTo(1)
                            .rarity(Rarity.RARE)));

    //呪文系
   public static final DeferredHolder<Item, Item> CROCODILE_JAW_CHAIN = ITEMS.register("crocodile_jaw_chain",
            () -> new CrocodileJawChainItem(new Item.Properties()
                            .stacksTo(1)
                            .rarity(Rarity.RARE)
            )
    );

    public static final DeferredHolder<Item, Item> LUNAR_CLAW_BLADE = ITEMS.register("lunar_claw_blade",
            () -> new LunarClawBladeItem(new Item.Properties()
                            .stacksTo(1)
                            .rarity(Rarity.RARE)
            )
    );

    public static final DeferredHolder<Item, Item> FLOWER_MANTIS_STAFF = ITEMS.register("flower_staff",
            () -> new FlowerMantisStaffItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> CLIONE_STAFF = ITEMS.register("clione_staff",
            () -> new ClioneStaffItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> SHIELD_CAST = ITEMS.register("shield_cast",
            () -> new ShieldCast(new Item.Properties()));

    public static final DeferredHolder<Item, Item> REVERSAL = ITEMS.register("reversal",
            () -> new Reversal(new Item.Properties()));

    public static final DeferredHolder<Item, Item> GOLEM_SUMMON = ITEMS.register("golem_summon",
            () -> new GolemSummon(new Item.Properties()));

    public static final DeferredHolder<Item, Item> ENDER_SPELL = ITEMS.register("ender_spell",
            () -> new EnderManItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> SOUL_SPELL = ITEMS.register("soul_spell",
            () -> new SoulSandItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> HOOK_SHOT = ITEMS.register("hook_shot",
            () -> new HookShot(new Item.Properties()));

    public static final DeferredHolder<Item, Item> GRAPPLING = ITEMS.register("grappling",
            () -> new Grappling(new Item.Properties()));

    public static final DeferredHolder<Item, Item> TONBOKIRI = ITEMS.register("tonbokiri",
            () -> new Tonbokiri(new Item.Properties()));

    public static final DeferredHolder<Item, Item> MANTIS_GRAB = ITEMS.register("mantis_grab",
            () -> new MantisGrab(new Item.Properties().durability(125)));

    public static final DeferredHolder<Item, Item> PICKAXE_ROD = ITEMS.register("pickaxe_rod",
            () -> new PickaxeRod(new Item.Properties()));

    public static final DeferredHolder<Item, Item> PICKAXE_STICK = ITEMS.register("pickaxe_stick",
            () -> new PickaxeShoot(new Item.Properties()));

    public static final DeferredHolder<Item, Item> WOODEN_NET = ITEMS.register("wooden_net",
            () -> new CaptureNet(new Item.Properties().durability(10)));

    public static final DeferredHolder<Item, Item> IRON_NET = ITEMS.register("iron_net",
            () -> new CaptureNet(new Item.Properties().durability(20)));

    public static final DeferredHolder<Item, Item> DIAMOND_NET = ITEMS.register("diamond_net",
            () -> new CaptureNet(new Item.Properties().durability(30)));

    //防具
    public static final DeferredHolder<Item, Item> CHROMITE_HELMET = ITEMS.register("chromite_helmet",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> CHROMITE_CHESTPLATE = ITEMS.register("chromite_chestplate",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> CHROMITE_LEGGINGS = ITEMS.register("chromite_leggings",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> CHROMITE_BOOTS = ITEMS.register("chromite_boots",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> FLUORITE_HELMET = ITEMS.register("fluorite_helmet",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> FLUORITE_CHESTPLATE = ITEMS.register("fluorite_chestplate",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> FLUORITE_LEGGINGS = ITEMS.register("fluorite_leggings",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> FLUORITE_BOOTS = ITEMS.register("fluorite_boots",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> STAINLESS_HELMET = ITEMS.register("stainless_helmet",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> STAINLESS_CHESTPLATE = ITEMS.register("stainless_chestplate",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> STAINLESS_LEGGINGS = ITEMS.register("stainless_leggings",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> STAINLESS_BOOTS = ITEMS.register("stainless_boots",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> ALUMINIUM_HELMET = ITEMS.register("aluminium_helmet",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ALUMINIUM_CHESTPLATE = ITEMS.register("aluminium_chestplate",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ALUMINIUM_LEGGINGS = ITEMS.register("aluminium_leggings",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ALUMINIUM_BOOTS = ITEMS.register("aluminium_boots",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> TITANIUM_HELMET = ITEMS.register("titanium_helmet",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> TITANIUM_CHESTPLATE = ITEMS.register("titanium_chestplate",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> TITANIUM_LEGGINGS = ITEMS.register("titanium_leggings",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> TITANIUM_BOOTS = ITEMS.register("titanium_boots",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> MAGUNESIUM_HELMET = ITEMS.register("magunesium_helmet",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> MAGUNESIUM_CHESTPLATE = ITEMS.register("magunesium_chestplate",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> MAGUNESIUM_LEGGINGS = ITEMS.register("magunesium_leggings",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> MAGUNESIUM_BOOTS = ITEMS.register("magunesium_boots",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> VANADIUM_HELMET = ITEMS.register("vanadium_helmet",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> VANADIUM_CHESTPLATE = ITEMS.register("vanadium_chestplate",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> VANADIUM_LEGGINGS = ITEMS.register("vanadium_leggings",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> VANADIUM_BOOTS = ITEMS.register("vanadium_boots",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> DURALUMIN_HELMET = ITEMS.register("duralumin_helmet",
            () -> new DuraluminArmorItem(ModArmorMaterials.DURALUMIN, ArmorType.HELMET, new Item.Properties()));
    public static final DeferredHolder<Item, Item> DURALUMIN_CHESTPLATE = ITEMS.register("duralumin_chestplate",
            () -> new DuraluminArmorItem(ModArmorMaterials.DURALUMIN, ArmorType.CHESTPLATE, new Item.Properties()));
    public static final DeferredHolder<Item, Item> DURALUMIN_LEGGINGS = ITEMS.register("duralumin_leggings",
            () -> new DuraluminArmorItem(ModArmorMaterials.DURALUMIN, ArmorType.LEGGINGS, new Item.Properties()));
    public static final DeferredHolder<Item, Item> DURALUMIN_BOOTS = ITEMS.register("duralumin_boots",
            () -> new DuraluminArmorItem(ModArmorMaterials.DURALUMIN, ArmorType.BOOTS, new Item.Properties()));

    public static final DeferredHolder<Item, Item> TITAN_ALLOY_HELMET = ITEMS.register("titan_alloy_helmet",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> TITAN_ALLOY_CHESTPLATE = ITEMS.register("titan_alloy_chestplate",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> TITAN_ALLOY_LEGGINGS = ITEMS.register("titan_alloy_leggings",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> TITAN_ALLOY_BOOTS = ITEMS.register("titan_alloy_boots",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> ELECTRON_HELMET = ITEMS.register("electron_helmet",
            () -> new ElectronArmorItem(ModArmorMaterials.ELECTRON, ArmorType.HELMET, new Item.Properties()));
    public static final DeferredHolder<Item, Item> ELECTRON_CHESTPLATE = ITEMS.register("electron_chestplate",
            () -> new ElectronArmorItem(ModArmorMaterials.ELECTRON, ArmorType.CHESTPLATE, new Item.Properties()));
    public static final DeferredHolder<Item, Item> ELECTRON_LEGGINGS = ITEMS.register("electron_leggings",
            () -> new ElectronArmorItem(ModArmorMaterials.ELECTRON, ArmorType.LEGGINGS, new Item.Properties()));
    public static final DeferredHolder<Item, Item> ELECTRON_BOOTS = ITEMS.register("electron_boots",
            () -> new ElectronArmorItem(ModArmorMaterials.ELECTRON, ArmorType.BOOTS, new Item.Properties()));

    public static final DeferredHolder<Item, Item> VANADIUM_ALLOY_HELMET = ITEMS.register("vanadium_alloy_helmet",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> VANADIUM_ALLOY_CHESTPLATE = ITEMS.register("vanadium_alloy_chestplate",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> VANADIUM_ALLOY_LEGGINGS = ITEMS.register("vanadium_alloy_leggings",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> VANADIUM_ALLOY_BOOTS = ITEMS.register("vanadium_alloy_boots",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> PERISO_HELMET = ITEMS.register("periso_helmet",
            () -> new PerissoArmor(ModArmorMaterials.PERISO, ArmorType.HELMET, new Item.Properties()));

    public static final DeferredHolder<Item, Item> ROCK_PENGUIN_BOOTS = ITEMS.register("rock_penguin_boots",
            () -> new RockPenguinArmor(ModArmorMaterials.ROCK_PENGUIN, ArmorType.BOOTS, new Item.Properties()));

    public static final DeferredHolder<Item, Item> EMU_BOOTS = ITEMS.register("emu_boots",
            () -> new EmuArmorItem(ModArmorMaterials.EMU, ArmorType.BOOTS, new Item.Properties()));

    public static final DeferredHolder<Item, Item> CASSOWARY_BOOTS = ITEMS.register("cassowary_boots",
            () -> new CassowaryArmorItem(ModArmorMaterials.CASSOWARY, ArmorType.BOOTS, new Item.Properties()));

    public static final DeferredHolder<Item, Item> ANCIENT_LIZARD_HELMET = ITEMS.register("ancient_lizard_boots",
            () -> new AncientLizardArmor(ModArmorMaterials.ANCIENT, ArmorType.HELMET, new Item.Properties()));

    public static final DeferredHolder<Item, Item> LEOPARD_GECKO_TAIL_BELT = ITEMS.register("leopard_gecko_tail_belt",
            () -> new LeopardGeckoArmor(
                    ModArmorMaterials.LEOPA,
                    ArmorType.LEGGINGS,
                    new Item.Properties()));

    //public static final DeferredHolder<Item, Item> PANGOLIN_CHESTPLATE = ITEMS.register("pangolin_chestplate",
    //        () -> new PangolinArmor(ModArmorMaterials.ROCK_PENGUIN, ArmorType.BOOTS, new Item.Properties()));


    //食べ物
    public static final DeferredHolder<Item, Item> CAVIAR = ITEMS.register("caviar",
            CustomFoodItems.CaviarItem::new);


    public static final DeferredHolder<Item, Item> CABBAGE_LEAF = ITEMS.register("cabbage",
            CustomFoodItems.CabbageItem::new);

    public static final DeferredHolder<Item, Item> ASPARAGUS = ITEMS.register("asparagus",
            CustomFoodItems.AsparagusItem::new);

    public static final DeferredHolder<Item, Item> GRAPE = ITEMS.register("grape",
            CustomFoodItems.GrapeItem::new);

    public static final DeferredHolder<Item, Item> LEMON = ITEMS.register("lemon",
            CustomFoodItems.LemonItem::new);

    public static final DeferredHolder<Item, Item> CINNAMON = ITEMS.register("cinnamon",
            CustomFoodItems.CinnamonItem::new);

    public static final DeferredHolder<Item, Item> ROW_RICE = ITEMS.register("row_rice",
            CustomFoodItems.RiceItem::new);

    public static final DeferredHolder<Item, Item> RICE = ITEMS.register("rice",
            CustomFoodItems.RiceItem::new);

    public static final DeferredHolder<Item, Item> MINT = ITEMS.register("mint",
            CustomFoodItems.RiceItem::new);

    public static final DeferredHolder<Item, Item> COLA = ITEMS.register("cola",
            () -> new BlockItem(ModBlocks.COLA_FRUIT.get(),new Item.Properties().food(ModFoods.VEGETABLE1)));

    public static final DeferredHolder<Item, Item> TOMATO = ITEMS.register("tomato",
            CustomFoodItems.TomatoItem::new);

    public static final DeferredHolder<Item, Item> CORN = ITEMS.register("corn",
            CustomFoodItems.CornItem::new);

    public static final DeferredHolder<Item, Item> ONION = ITEMS.register("onion",
            CustomFoodItems.OnionItem::new);

    public static final DeferredHolder<Item, Item> GINGER = ITEMS.register("ginger",
            CustomFoodItems.GingerItem::new);

    public static final DeferredHolder<Item, Item> GREEN_PEPPER = ITEMS.register("green_pepper",
            CustomFoodItems.GreenPepperItem::new);

    public static final DeferredHolder<Item, Item> PAPRIKA = ITEMS.register("paprika",
            CustomFoodItems.PaprikaItem::new);

    public static final DeferredHolder<Item, Item> EGGPLANT = ITEMS.register("eggplant",
            CustomFoodItems.EggPlantItem::new);

    public static final DeferredHolder<Item, Item> WHITE_RADISH = ITEMS.register("white_radish",
            CustomFoodItems.WhiteRadishItem::new);

    public static final DeferredHolder<Item, Item> PLUM = ITEMS.register("plum",
            CustomFoodItems.PlumItem::new);

    public static final DeferredHolder<Item, Item> CHERRY = ITEMS.register("cherry",
            CustomFoodItems.CherryItem::new);

    public static final DeferredHolder<Item, Item> BANANA = ITEMS.register("banana",
            CustomFoodItems.BananaItem::new);

    public static final DeferredHolder<Item, Item> COCONUT = ITEMS.register("coconut",
            CustomFoodItems.CoconutItem::new);

    public static final DeferredHolder<Item, Item> PEACH = ITEMS.register("peach",
            CustomFoodItems.PeachItem::new);

    public static final DeferredHolder<Item, Item> KIWI = ITEMS.register("kiwi",
            CustomFoodItems.KiwiItem::new);

    public static final DeferredHolder<Item, Item> ALMOND = ITEMS.register("almond",
            () -> new BlockItem(ModBlocks.ALMOND_FRUIT.get(),new Item.Properties().food(ModFoods.VEGETABLE1)));

    public static final DeferredHolder<Item, Item> DURIAN = ITEMS.register("durian",
            () -> new BlockItem(ModBlocks.DURIAN_FRUIT.get(),new Item.Properties().food(ModFoods.VEGETABLE1)));

    public static final DeferredHolder<Item, Item> OLIVE = ITEMS.register("olive",
            CustomFoodItems.OliveItem::new);

    public static final DeferredHolder<Item, Item> BLUE_BERRY = ITEMS.register("blue_berry",
            CustomFoodItems.BlueBerryItem::new);

    public static final DeferredHolder<Item, Item> CHILI_PEPPER = ITEMS.register("chili_pepper",
            CustomFoodItems.ChiliPepperItem::new);

    public static final DeferredHolder<Item, Item> BASIL = ITEMS.register("basil",
            CustomFoodItems.BasilItem::new);

    public static final DeferredHolder<Item, Item> LOTUS_ROOT = ITEMS.register("lotus_root",
            CustomFoodItems.LotusRootItem::new);

    public static final DeferredHolder<Item, Item> TOMATO_SAND = ITEMS.register("tomato_sand",
            CustomFoodItems.TomatoSandItem::new);

    public static final DeferredHolder<Item, Item> BANANA_SAND = ITEMS.register("banana_sand",
            CustomFoodItems.BananaSandItem::new);

    public static final DeferredHolder<Item, Item> PEACH_SAND = ITEMS.register("peach_sand",
            CustomFoodItems.PeachSandItem::new);

    public static final DeferredHolder<Item, Item> APPLE_SAND = ITEMS.register("apple_sand",
            CustomFoodItems.AppleSandItem::new);

    public static final DeferredHolder<Item, Item> GRAPE_SAND = ITEMS.register("grape_sand",
            CustomFoodItems.GrapeSandItem::new);


    //frypanレシピ
    public static final DeferredHolder<Item, Item> ASPARAGUS_BACON = ITEMS.register("asparagus_bacon",
            CustomFoodItems.AsparagusBaconItem::new);

    public static final DeferredHolder<Item, Item> GINGER_PORK = ITEMS.register("ginger_pork",
            CustomFoodItems.GingerPorkItem::new);

    public static final DeferredHolder<Item, Item> FRIED_EGGPLANT = ITEMS.register("fried_eggplant",
            CustomFoodItems.FriedEggplantItem::new);

    public static final DeferredHolder<Item, Item> CHINJAOLOSE = ITEMS.register("chinjaolose",
            CustomFoodItems.ChinjaoloseeItem::new);

    public static final DeferredHolder<Item, Item> POPCORN = ITEMS.register("popcorn",
            CustomFoodItems.PopcornItem::new);

    public static final DeferredHolder<Item, Item> PIZZA_BRED = ITEMS.register("pizza_bread",
            CustomFoodItems.PizzaBredItem::new);

    public static final DeferredHolder<Item, Item> BOILED_FISH = ITEMS.register("boiled_fish",
            CustomFoodItems.BoiledFishItem::new);

    public static final DeferredHolder<Item, Item> CORN_SOUP = ITEMS.register("corn_soup",
            CustomFoodItems.CornSoupItem::new);

    public static final DeferredHolder<Item, Item> HAMBURGER = ITEMS.register("hamburger",
            CustomFoodItems.HambugerItem::new);

    public static final DeferredHolder<Item, Item> PEPERONCINO = ITEMS.register("peperoncino",
            CustomFoodItems.PeperoncinoItem::new);

    public static final DeferredHolder<Item, Item> MABO_NASU = ITEMS.register("mabo_nasu",
            CustomFoodItems.MaboNasuItem::new);

    public static final DeferredHolder<Item, Item> BAKED_CORN = ITEMS.register("baked_corn",
            CustomFoodItems.BakedCornItem::new);

    public static final DeferredHolder<Item, Item> RADISH_MINCED_MEAT = ITEMS.register("radish_minced_meat",
            CustomFoodItems.RadishMinciMeatItem::new);

    public static final DeferredHolder<Item, Item> CHICKEN_EGG = ITEMS.register("chicken_egg",
            CustomFoodItems.ChickenEggItem::new);

    public static final DeferredHolder<Item, Item> GENOVESE = ITEMS.register("genovese",
            CustomFoodItems.GenoveseItem::new);

    public static final DeferredHolder<Item, Item> FRIED_ALMOND = ITEMS.register("fried_almond",
            CustomFoodItems.FriedAlmondItem::new);

    public static final DeferredHolder<Item, Item> GREEN_CARRY = ITEMS.register("green_carry",
            CustomFoodItems.GreenCarryItem::new);

    public static final DeferredHolder<Item, Item> GREEN_PEPPER_MINCED_MEAT = ITEMS.register("green_pepper_minced_meat",
            CustomFoodItems.GreenPepperMincedMeatItem::new);

    public static final DeferredHolder<Item, Item> PEPE_CABBAGE = ITEMS.register("pepe_cabbage",
            CustomFoodItems.PepeCabbageItem::new);

    public static final DeferredHolder<Item, Item> FRIED_LOTUS_ROOT = ITEMS.register("fried_lotus_root",
            CustomFoodItems.FriedLotusRootItem::new);

    public static final DeferredHolder<Item, Item> LOTUS_ROOT_MINCED_MEAT = ITEMS.register("lotus_root_minced_meat",
            CustomFoodItems.LotusRootMincedMeatItem::new);

    public static final DeferredHolder<Item, Item> GAPRAO = ITEMS.register("gaprao",
            CustomFoodItems.GapraoItem::new);

    public static final DeferredHolder<Item, Item> TACOS = ITEMS.register("tacos",
            CustomFoodItems.TacosItem::new);

    public static final DeferredHolder<Item, Item> TEA = ITEMS.register("tea",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> HOP = ITEMS.register("hop",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> PEPPER = ITEMS.register("pepper",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> ROW_COFFEE_BEANS = ITEMS.register("row_coffee_beans",
            () -> new BlockItem(ModBlocks.COFFEE_FRUIT.get(),new Item.Properties()));

    public static final DeferredHolder<Item, Item> COFFEE_BEANS = ITEMS.register("coffee_beans",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> MUSK_COFFEE_BEANS = ITEMS.register("musk_coffee_beans",
            () -> new Item(new Item.Properties()));

    //燃料
    public static final DeferredHolder<Item, Item> FIREWOOD = ITEMS.register("fire_wood",
            () -> new FireWood(new Item.Properties()));

    //飲み物
    public static final DeferredHolder<Item, Item> FILTERED_WATER = ITEMS.register("filtered_water",
            CustomFoodItems.FilteredWaterItem::new);

    public static final DeferredHolder<Item, Item> BOTTLE_OF_MILK = ITEMS.register("bottle_of_milk",
            CustomFoodItems.BottleMilkItem::new);

    public static final DeferredHolder<Item, Item> MAPLE_WATER = ITEMS.register("maple_water",
            CustomFoodItems.MapleWaterItem::new);

    public static final DeferredHolder<Item, Item> MAPLE_SYRUP = ITEMS.register("maple_syrup",
            CustomFoodItems.MapleSyrupItem::new);

    public static final DeferredHolder<Item, Item> COFFEE = ITEMS.register("coffe",
            CustomFoodItems.CoffeeItem::new);

    public static final DeferredHolder<Item, Item> MUSK_COFFEE = ITEMS.register("musk_coffe",
            CustomFoodItems.KopiLuwakCoffeeItem::new);


    //distiller
    public static final DeferredHolder<Item, Item> BOTTLE_OF_WHISKEY = ITEMS.register("bottle_of_whiskey",
            CustomFoodItems.WhiskeyItem::new);

    public static final DeferredHolder<Item, Item> BOTTLE_OF_WHITE_LIQUOR = ITEMS.register("bottle_of_white_liquor",
            CustomFoodItems.WhiteLiquorItem::new);

    public static final DeferredHolder<Item, Item> BOTTLE_OF_COLA = ITEMS.register("bottle_of_cola",
            CustomFoodItems.ColaItem::new);


    //酒樽
    public static final DeferredHolder<Item, Item> BOTTLE_OF_RED_WINE = ITEMS.register("bottle_of_red_wine",
            CustomFoodItems.RedWineItem::new);

    public static final DeferredHolder<Item, Item> BOTTLE_OF_WHITE_WINE = ITEMS.register("bottle_of_white_wine",
            CustomFoodItems.WhiteWineItem::new);

    public static final DeferredHolder<Item, Item> BOTTLE_OF_SAKE = ITEMS.register("bottle_of_sake",
            CustomFoodItems.SakeItem::new);

    public static final DeferredHolder<Item, Item> PEACH_LIQUEUR = ITEMS.register("peach_liqueur",
            CustomFoodItems.PeachLiquorItem::new);

    public static final DeferredHolder<Item, Item> PLUM_LIQUEUR = ITEMS.register("plum_liqueur",
            CustomFoodItems.PlumLiquorItem::new);

    public static final DeferredHolder<Item, Item> LEMON_LIQUEUR = ITEMS.register("lemon_liqueur",
            CustomFoodItems.LemonLiquorItem::new);

    public static final DeferredHolder<Item, Item> MINT_LIQUEUR = ITEMS.register("mint_liqueur",
            CustomFoodItems.MintLiquorItem::new);

    public static final DeferredHolder<Item, Item> APPLE_LIQUEUR = ITEMS.register("apple_liqueur",
            CustomFoodItems.AppleLiquorItem::new);

    public static final DeferredHolder<Item, Item> HABU_LIQUEUR = ITEMS.register("habu_liqueur",
            CustomFoodItems.HabuLiquorItem::new);


    //大釜で酒作る
    public static final DeferredHolder<Item, Item> ASPERGILLUS = ITEMS.register("aspergillus",
            CustomFoodItems.AspergillusItem::new);

    public static final DeferredHolder<Item, Item> JAPANESE_YEAST = ITEMS.register("japanese_yeast",
            CustomFoodItems.JYeastItem::new);


    //mixerで作る飲み物
    public static final DeferredHolder<Item, Item> GRAPE_JUICE = ITEMS.register("grape_juice",
            CustomFoodItems.GrapeJuiceItem::new);

    public static final DeferredHolder<Item, Item> APPLE_JUICE = ITEMS.register("apple_juice",
            CustomFoodItems.AppleJuiceItem::new);

    public static final DeferredHolder<Item, Item> LEMON_JUICE = ITEMS.register("lemon_juice",
            CustomFoodItems.LemonJuiceItem::new);

    public static final DeferredHolder<Item, Item> PEACH_JUICE = ITEMS.register("peach_juice",
            CustomFoodItems.PeachJuiceItem::new);

    public static final DeferredHolder<Item, Item> PLUM_JUICE = ITEMS.register("plum_juice",
            CustomFoodItems.PlumJuiceItem::new);

    public static final DeferredHolder<Item, Item> BANANA_JUICE = ITEMS.register("banana_juice",
            CustomFoodItems.BananaJuiceItem::new);

    public static final DeferredHolder<Item, Item> ALMOND_MILK = ITEMS.register("almond_juice",
            CustomFoodItems.AlmondJuiceItem::new);

    public static final DeferredHolder<Item, Item> COCONUT_MILK = ITEMS.register("coconut_milk",
            CustomFoodItems.CoconutJuiceItem::new);

    public static final DeferredHolder<Item, Item> SMOOTHIE = ITEMS.register("smoothie",
            CustomFoodItems.SmoothieJuiceItem::new);

    public static final DeferredHolder<Item, Item> MIX_JUICE = ITEMS.register("mix_juice",
            CustomFoodItems.MixJuiceItem::new);

    public static final DeferredHolder<Item, Item> MIX_AU_LAIT = ITEMS.register("mix_au_lait",
            CustomFoodItems.MixAuLaitJuiceItem::new);

    public static final DeferredHolder<Item, Item> CHOCOLATE = ITEMS.register("chocolate",
            CustomFoodItems.ChocoJuiceItem::new);

    public static final DeferredHolder<Item, Item> CHOCO_MINT = ITEMS.register("choco_mint",
            CustomFoodItems.ChocoMintJuiceItem::new);


    //種
    public static final DeferredHolder<Item, Item> ASPARAGUS_SEEDS = ITEMS.register("asparagus_seeds",
            () -> new BlockItem(ModBlocks.ASPARAGUS.get(),
                    new Item.Properties()));
    public static final DeferredHolder<Item, Item> CABBAGE_SEEDS = ITEMS.register("cabbage_seeds",
            () -> new BlockItem(ModBlocks.CABBAGE.get(),
                    new Item.Properties()));
    public static final DeferredHolder<Item, Item> RICE_SEEDS = ITEMS.register("rice_seeds",
            () -> new BlockItem(ModBlocks.RICE_BLOCK.get(),
                    new Item.Properties()));
    public static final DeferredHolder<Item, Item> MINT_SEEDS = ITEMS.register("mint_seeds",
            () -> new BlockItem(ModBlocks.MINT_BLOCK.get(),
                    new Item.Properties()));
    public static final DeferredHolder<Item, Item> COLA_SEEDS = ITEMS.register("cola_seeds",
            () -> new BlockItem(ModBlocks.COLA_FRUIT.get(),
                    new Item.Properties()));
    public static final DeferredHolder<Item, Item> TOMATO_SEEDS = ITEMS.register("tomato_seeds",
            () -> new BlockItem(ModBlocks.TOMATO_BLOCK.get(),
                    new Item.Properties()));
    public static final DeferredHolder<Item, Item> CORN_SEEDS = ITEMS.register("corn_seeds",
            () -> new BlockItem(ModBlocks.CORN_BLOCK.get(),
                    new Item.Properties()));
    public static final DeferredHolder<Item, Item> ONION_SEEDS = ITEMS.register("onion_seeds",
            () -> new BlockItem(ModBlocks.ONION_BLOCK.get(),
                    new Item.Properties()));
    public static final DeferredHolder<Item, Item> GINGER_SEEDS = ITEMS.register("ginger_seeds",
            () -> new BlockItem(ModBlocks.GINGER_BLOCK.get(),
                    new Item.Properties()));
    public static final DeferredHolder<Item, Item> GREEN_PEPPER_SEEDS = ITEMS.register("green_pepper_seeds",
            () -> new BlockItem(ModBlocks.GREEN_PEPPER_BLOCK.get(),
                    new Item.Properties()));
    public static final DeferredHolder<Item, Item> PAPRIKA_SEEDS = ITEMS.register("paprika_seeds",
            () -> new BlockItem(ModBlocks.PAPRIKA_BLOCK.get(),
                    new Item.Properties()));
    public static final DeferredHolder<Item, Item> EGGPLANT_SEEDS = ITEMS.register("eggplant_seeds",
            () -> new BlockItem(ModBlocks.EGGPLANT_BLOCK.get(),
                    new Item.Properties()));
    public static final DeferredHolder<Item, Item> WHITE_RADISH_SEEDS = ITEMS.register("white_radish_seeds",
            () -> new BlockItem(ModBlocks.WHITE_RADISH_BLOCK.get(),
                    new Item.Properties()));

    public static final DeferredHolder<Item, Item> CHILI_PEPPER_SEEDS = ITEMS.register("chili_pepper_seeds",
            () -> new BlockItem(ModBlocks.CHILI_PEPPER_BLOCK.get(),
                    new Item.Properties()));

    public static final DeferredHolder<Item, Item> BASIL_SEEDS = ITEMS.register("basil_seeds",
            () -> new BlockItem(ModBlocks.BASIL_BLOCK.get(),
                    new Item.Properties()));

    public static final DeferredHolder<Item, Item> LOTUS_ROOT_SEEDS = ITEMS.register("lotus_root_seeds",
            () -> new BlockItem(ModBlocks.LOTUS_ROOT_BLOCK.get(),
                    new Item.Properties()));

    //飲み物バケツ
    public static final DeferredHolder<Item, Item> FILTERED_WATER_BUCKET = ITEMS.register("filtered_water_bucket",
            () -> new BucketItem(ModFluids.SOURCE_CLEAN.get(),
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final DeferredHolder<Item, Item> WHISKEY_BUCKET = ITEMS.register("whiskey_bucket",
            () -> new BucketItem(ModFluids.SOURCE_WHISKEY.get(),
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final DeferredHolder<Item, Item> MAPLE_BUCKET = ITEMS.register("maple_bucket",
            () -> new BucketItem(ModFluids.SOURCE_MAPLE.get(),
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final DeferredHolder<Item, Item> SAKE_BUCKET = ITEMS.register("sake_bucket",
            () -> new BucketItem(ModFluids.SOURCE_SAKE.get(),
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final DeferredHolder<Item, Item> WINE_BUCKET = ITEMS.register("wine_bucket",
            () -> new BucketItem(ModFluids.SOURCE_WINE.get(),
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final DeferredHolder<Item, Item> WHITE_WINE_BUCKET = ITEMS.register("white_wine_bucket",
            () -> new BucketItem(ModFluids.SOURCE_WHITE_WINE.get(),
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final DeferredHolder<Item, Item> J_MALT_BUCKET = ITEMS.register("j_malt_bucket",
            () -> new SolidBucketItem(ModBlocks.JAPANESE_MALT_P.get(), SoundEvents.BUCKET_EMPTY_POWDER_SNOW,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final DeferredHolder<Item, Item> RICE_BUCKET = ITEMS.register("rice_bucket",
            () -> new SolidBucketItem(ModBlocks.BOILED_RICE_BLOCK.get(), SoundEvents.BUCKET_EMPTY_POWDER_SNOW,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final DeferredHolder<Item, Item> MASH_BUCKET = ITEMS.register("mash_bucket",
            () -> new BucketItem(ModFluids.SOURCE_MASH.get(),
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final DeferredHolder<Item, Item> YEAST_BUCKET = ITEMS.register("yeast_bucket",
            () -> new BucketItem(ModFluids.SOURCE_YEAST.get(),
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));





    //卵
    public static final DeferredHolder<Item, Item> SPARROW_SPAWN_EGG = ITEMS.register("sparrow_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> DEER_SPAWN_EGG = ITEMS.register("deer_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> DOE_SPAWN_EGG = ITEMS.register("doe_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> SAKABAN_SPAWN_EGG = ITEMS.register("sakaban_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> CICADA_SPAWN_EGG = ITEMS.register("cicada_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> DRAGONFLY_SPAWN_EGG = ITEMS.register("dragonfly_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> BUTTERFLY_SPAWN_EGG = ITEMS.register("butterfly_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> LONG_TIT_SPAWN_EGG = ITEMS.register("long_tit_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> SEAL_SPAWN_EGG = ITEMS.register("seal_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> HERMIT_CRAB_SPAWN_EGG = ITEMS.register("hermit_crab_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> MINI_HIPO_SPAWN_EGG = ITEMS.register("mini_hipo_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> MONGOOSE_SPAWN_EGG = ITEMS.register("mongoose_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> ANT_SPAWN_EGG = ITEMS.register("ant_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> ETUPIRKA_SPAWN_EGG = ITEMS.register("etupirka_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> SNAKE_SPAWN_EGG = ITEMS.register("snake_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> PEACOCK_SPAWN_EGG = ITEMS.register("peacock_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> BURROWING_OWL_SPAWN_EGG = ITEMS.register("burrowing_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> FOLIVORE_SPAWN_EGG = ITEMS.register("folivore_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> GIANT_OTTER_SPAWN_EGG = ITEMS.register("giant_otter_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> GUYANA_RUPICOLA_SPAWN_EGG = ITEMS.register("guyana_rupicola_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> HARPY_EAGLE_SPAWN_EGG = ITEMS.register("harpy_eagle_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> MUSK_CAT_SPAWN_EGG = ITEMS.register("musk_cat_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> PERISSO_SPAWN_EGG = ITEMS.register("perisso_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> RATEL_SPAWN_EGG = ITEMS.register("ratel_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> WOMBAT_SPAWN_EGG = ITEMS.register("wombat_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> BEAVER_SPAWN_EGG = ITEMS.register("beaver_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> HAMMER_HEAD_SPAWN_EGG = ITEMS.register("hammer_head_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> LEAFY_SEA_SPAWN_EGG = ITEMS.register("leafy_sea_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> KIWI_SPAWN_EGG = ITEMS.register("kiwi_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> ROCK_PENGUIN_SPAWN_EGG = ITEMS.register("rock_penguin_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> SKUNK_SPAWN_EGG = ITEMS.register("skunk_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> STURGEON_SPAWN_EGG = ITEMS.register("sturgeon_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> QUOKKA_SPAWN_EGG = ITEMS.register("quokka_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> WOOD_PECKER_SPAWN_EGG = ITEMS.register("wood_pecker_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> FELIS_SPAWN_EGG = ITEMS.register("felis_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> FRUIT_FLY_SPAWN_EGG = ITEMS.register("fruit_fly_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> INDICATOR_IDAE_SPAWN_EGG = ITEMS.register("indicate_idae_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> MANTIS_SHRIMP_SPAWN_EGG = ITEMS.register("mantis_shrimp_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> MEERKAT_SPAWN_EGG = ITEMS.register("meerkat_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> PALLAS_CAT_SPAWN_EGG = ITEMS.register("pallas_cat_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> PANGOLIN_SPAWN_EGG = ITEMS.register("pangolin_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> PORCUPINE_SPAWN_EGG = ITEMS.register("porcupine_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> TAPIR_SPAWN_EGG = ITEMS.register("tapir_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> EVERY_EGG = ITEMS.register("every_egg",
            () -> new EveryEgg(new Item.Properties()));




    public static final DeferredHolder<Item, Item> POTTERS_WHEEL_ITEM = ITEMS.register("potters_wheel",
            () -> new BlockItem(ModBlocks.POTTERS_WHEEL.get(), new Item.Properties()));

    // Items
    public static final DeferredHolder<Item, Item> UNFIRED_POTTERY = ITEMS.register("unfired_pottery",
            () -> new UnfiredPotteryItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> FIRED_POTTERY = ITEMS.register("fired_pottery",
            () -> new FiredPotteryItem(new Item.Properties()));

    // Glazes
    public static final DeferredHolder<Item, Item> GLAZE_WHITE = ITEMS.register("glaze_white",
            () -> new GlazeItem(new Item.Properties(), 0xFFFFFF));
    public static final DeferredHolder<Item, Item> GLAZE_BLACK = ITEMS.register("glaze_black",
            () -> new GlazeItem(new Item.Properties(), 0x000000));
    public static final DeferredHolder<Item, Item> GLAZE_RED = ITEMS.register("glaze_red",
            () -> new GlazeItem(new Item.Properties(), 0xFF0000));
    public static final DeferredHolder<Item, Item> GLAZE_BLUE = ITEMS.register("glaze_blue",
            () -> new GlazeItem(new Item.Properties(), 0x0000FF));
    public static final DeferredHolder<Item, Item> GLAZE_GREEN = ITEMS.register("glaze_green",
            () -> new GlazeItem(new Item.Properties(), 0x00FF00));
    public static final DeferredHolder<Item, Item> GLAZE_YELLOW = ITEMS.register("glaze_yellow",
            () -> new GlazeItem(new Item.Properties(), 0xFFFF00));
    public static final DeferredHolder<Item, Item> GLAZE_ORANGE = ITEMS.register("glaze_orange",
            () -> new GlazeItem(new Item.Properties(), 0xFFA500));
    public static final DeferredHolder<Item, Item> GLAZE_PURPLE = ITEMS.register("glaze_purple",
            () -> new GlazeItem(new Item.Properties(), 0x800080));
    public static final DeferredHolder<Item, Item> GLAZE_PINK = ITEMS.register("glaze_pink",
            () -> new GlazeItem(new Item.Properties(), 0xFFC0CB));
    public static final DeferredHolder<Item, Item> GLAZE_BROWN = ITEMS.register("glaze_brown",
            () -> new GlazeItem(new Item.Properties(), 0x8B4513));
    public static final DeferredHolder<Item, Item> GLAZE_GRAY = ITEMS.register("glaze_gray",
            () -> new GlazeItem(new Item.Properties(), 0x808080));
    public static final DeferredHolder<Item, Item> GLAZE_CYAN = ITEMS.register("glaze_cyan",
            () -> new GlazeItem(new Item.Properties(), 0x00FFFF));

    // Pattern Stamps
    public static final DeferredHolder<Item, Item> STAMP_STRIPES = ITEMS.register("stamp_stripes",
            () -> new PotteryStampItem(new Item.Properties(), PotteryPattern.STRIPES));
    public static final DeferredHolder<Item, Item> STAMP_DOTS = ITEMS.register("stamp_dots",
            () -> new PotteryStampItem(new Item.Properties(), PotteryPattern.DOTS));
    public static final DeferredHolder<Item, Item> STAMP_WAVES = ITEMS.register("stamp_waves",
            () -> new PotteryStampItem(new Item.Properties(), PotteryPattern.WAVES));
    public static final DeferredHolder<Item, Item> STAMP_FLOWERS = ITEMS.register("stamp_flowers",
            () -> new PotteryStampItem(new Item.Properties(), PotteryPattern.FLOWERS));
    public static final DeferredHolder<Item, Item> STAMP_GEOMETRIC = ITEMS.register("stamp_geometric",
            () -> new PotteryStampItem(new Item.Properties(), PotteryPattern.GEOMETRIC));

    // バイタルチェックマシン
    public static final DeferredHolder<Item, Item> VITAL_CHECK = ITEMS.register("vital_check",
            () -> new VitalCheckItem(new Item.Properties()
                    .stacksTo(1) // スタック不可
            ));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
