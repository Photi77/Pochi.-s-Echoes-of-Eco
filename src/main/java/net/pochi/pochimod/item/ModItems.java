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
import java.util.function.Function;
import net.pochi.pochimod.pottery.PotteryPattern;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(PochiMod.MOD_ID);


    public static final DeferredHolder<Item, Item> MINERAL_SWORD = ITEMS.registerItem("mineral_sword",
            MineralSwordItem::new);
    public static final DeferredHolder<Item, Item> MINERAL_PICKAXE = ITEMS.registerItem("mineral_pickaxe",
            MineralPickaxeItem::new);
    public static final DeferredHolder<Item, Item> MINERAL_SHOVEL = ITEMS.registerItem("mineral_shovel",
            MineralShovelItem::new);
    public static final DeferredHolder<Item, Item> MINERAL_AXE = ITEMS.registerItem("mineral_axe",
            MineralAxeItem::new);

    public static final DeferredHolder<Item, Item> MINERAL_HELMET = ITEMS.registerItem("mineral_helmet",
            props -> new MineralArmorItem(ArmorType.HELMET, props));
    public static final DeferredHolder<Item, Item> MINERAL_CHESTPLATE = ITEMS.registerItem("mineral_chestplate",
            props -> new MineralArmorItem(ArmorType.CHESTPLATE, props));
    public static final DeferredHolder<Item, Item> MINERAL_LEGGINGS = ITEMS.registerItem("mineral_leggings",
            props -> new MineralArmorItem(ArmorType.LEGGINGS, props));
    public static final DeferredHolder<Item, Item> MINERAL_BOOTS = ITEMS.registerItem("mineral_boots",
            props -> new MineralArmorItem(ArmorType.BOOTS, props));

    public static final DeferredHolder<Item, Item> MINERAL_RING = ITEMS.registerItem("mineral_ring",
            Item::new);

    //特殊アイテム
    public static final DeferredHolder<Item, Item> MINERAL_CHUNK = ITEMS.registerItem("mineral_chunk",
            MineralChunkItem::new,
            new Item.Properties()
                            .stacksTo(64));

    public static final DeferredHolder<Item, Item> RIVER_BRUSH = ITEMS.registerItem("river_brush",
            RiverBrushItem::new,
            new Item.Properties().durability(64)); // バニラブラシと同耐久


    public static final DeferredHolder<Item, Item> COMPOST = ITEMS.registerItem("compost",
            CompostItem::new,
            new Item.Properties().stacksTo(64));
    public static final DeferredHolder<Item, Item> BAKED_ALUM = ITEMS.registerItem("baked_alum",
            Item::new);

    public static final DeferredHolder<Item, Item> DRAGONFLY_WINGS = ITEMS.registerItem("dragonfly_wings",
            Item::new);

    public static final DeferredHolder<Item, Item> SALT = ITEMS.registerItem("salt",
            Item::new);

    public static final DeferredHolder<Item, Item> HABU = ITEMS.registerItem("habu",
            Item::new);

    public static final DeferredHolder<Item, Item> FLY_BOAT = ITEMS.registerItem("fly_boat",
            FlyBoatItem::new);

    public static final DeferredHolder<Item, Item> FLY_CHEST_BOAT = ITEMS.registerItem("fly_chest_boat",
            FlyChestBoatItem::new);

    //インゴット
    public static final DeferredHolder<Item, Item> CHROMITE_INGOT = ITEMS.registerItem("chromite_ingot",
            Item::new);
    public static final DeferredHolder<Item, Item> ALUMINIUM_INGOT = ITEMS.registerItem("aluminium_ingot",
            Item::new);
    public static final DeferredHolder<Item, Item> TITANIUM_INGOT = ITEMS.registerItem("titanium_ingot",
            Item::new);
    public static final DeferredHolder<Item, Item> MAGUNESIUM_INGOT = ITEMS.registerItem("magunesium_ingot",
            Item::new);
    public static final DeferredHolder<Item, Item> VANADIUM_INGOT = ITEMS.registerItem("vanadium_ingot",
            Item::new);

    //インゴットfrom BFURNACE
    public static final DeferredHolder<Item, Item> STAINLESS = ITEMS.registerItem("stainless",
            Item::new);
    public static final DeferredHolder<Item, Item> DURALUMIN = ITEMS.registerItem("duralumin",
            Item::new);
    public static final DeferredHolder<Item, Item> TITAN_ALLOY = ITEMS.registerItem("titan_alloy",
            Item::new);
    public static final DeferredHolder<Item, Item> ELECTRON = ITEMS.registerItem("electron",
            Item::new);
    public static final DeferredHolder<Item, Item> VANADIUM_ALLOY = ITEMS.registerItem("vanadium_alloy",
            Item::new);

    //原石
    public static final DeferredHolder<Item, Item> ROW_CHROMITE = ITEMS.registerItem("row_chromite",
            Item::new);
    public static final DeferredHolder<Item, Item> ROW_FLUORITE = ITEMS.registerItem("row_fluorite",
            Item::new);
    public static final DeferredHolder<Item, Item> ROW_ALUNITE = ITEMS.registerItem("row_alunite",
            Item::new);
    public static final DeferredHolder<Item, Item> ROW_BAUXITE = ITEMS.registerItem("row_bauxite",
            Item::new);
    public static final DeferredHolder<Item, Item> ROW_TITANIUM = ITEMS.registerItem("row_titanium",
            Item::new);
    public static final DeferredHolder<Item, Item> ROW_MAGUNESIUM = ITEMS.registerItem("row_magunesium",
            Item::new);
    public static final DeferredHolder<Item, Item> ROW_VANADIUM = ITEMS.registerItem("row_vanadium",
            Item::new);

    public static final DeferredHolder<Item, Item> HAMMER_HEAD = ITEMS.registerItem("hammer_head",
            Item::new);


    //ツール
    public static final DeferredHolder<Item, Item> HAMMER_HEAD_PICKAXE = ITEMS.registerItem("hammer_head_pickaxe",
            HammerHeadPickaxe::new);

    //public static final DeferredHolder<Item, Item> MANTIS_SHRIMP_GRAB = ITEMS.register("mantis_shrimp_grab",
    //        () -> new HammerHeadPickaxe(3, -2.4F,
    //                new Item.Properties()));
//
    //public static final DeferredHolder<Item, Item> PORCUPINE_SWORD = ITEMS.register("porcupine_sword",
    //        () -> new HammerHeadPickaxe(3, -2.4F,
    //                new Item.Properties()));

    public static final DeferredHolder<Item, Item> CHROMITE_SWORD = ITEMS.registerItem("chromite_sword",
            Item::new);
    public static final DeferredHolder<Item, Item> CHROMITE_PICKAXE = ITEMS.registerItem("chromite_pickaxe",
            Item::new);
    public static final DeferredHolder<Item, Item> CHROMITE_SHOVEL = ITEMS.registerItem("chromite_shovel",
            Item::new);
    public static final DeferredHolder<Item, Item> CHROMITE_AXE = ITEMS.registerItem("chromite_axe",
            Item::new);
    public static final DeferredHolder<Item, Item> CHROMITE_HOE = ITEMS.registerItem("chromite_hoe",
            Item::new);

    public static final DeferredHolder<Item, Item> FLUORITE_SWORD = ITEMS.registerItem("fluorite_sword",
            Item::new);
    public static final DeferredHolder<Item, Item> FLUORITE_PICKAXE = ITEMS.registerItem("fluorite_pickaxe",
            Item::new);
    public static final DeferredHolder<Item, Item> FLUORITE_SHOVEL = ITEMS.registerItem("fluorite_shovel",
            Item::new);
    public static final DeferredHolder<Item, Item> FLUORITE_AXE = ITEMS.registerItem("fluorite_axe",
            Item::new);
    public static final DeferredHolder<Item, Item> FLUORITE_HOE = ITEMS.registerItem("fluorite_hoe",
            Item::new);

    public static final DeferredHolder<Item, Item> ALUNITE_SWORD = ITEMS.registerItem("alunite_sword",
            Item::new);
    public static final DeferredHolder<Item, Item> ALUNITE_PICKAXE = ITEMS.registerItem("alunite_pickaxe",
            Item::new);
    public static final DeferredHolder<Item, Item> ALUNITE_SHOVEL = ITEMS.registerItem("alunite_shovel",
            Item::new);
    public static final DeferredHolder<Item, Item> ALUNITE_AXE = ITEMS.registerItem("alunite_axe",
            Item::new);
    public static final DeferredHolder<Item, Item> ALUNITE_HOE = ITEMS.registerItem("alunite_hoe",
            Item::new);

    public static final DeferredHolder<Item, Item> STAINLESS_SWORD = ITEMS.registerItem("stainless_sword",
            Item::new);
    public static final DeferredHolder<Item, Item> STAINLESS_PICKAXE = ITEMS.registerItem("stainless_pickaxe",
            Item::new);
    public static final DeferredHolder<Item, Item> STAINLESS_SHOVEL = ITEMS.registerItem("stainless_shovel",
            Item::new);
    public static final DeferredHolder<Item, Item> STAINLESS_AXE = ITEMS.registerItem("stainless_axe",
            Item::new);
    public static final DeferredHolder<Item, Item> STAINLESS_HOE = ITEMS.registerItem("stainless_hoe",
            Item::new);

    public static final DeferredHolder<Item, Item> ALUMINIUM_SWORD = ITEMS.registerItem("aluminium_sword",
            Item::new);
    public static final DeferredHolder<Item, Item> ALUMINIUM_PICKAXE = ITEMS.registerItem("aluminium_pickaxe",
            Item::new);
    public static final DeferredHolder<Item, Item> ALUMINIUM_SHOVEL = ITEMS.registerItem("aluminium_shovel",
            Item::new);
    public static final DeferredHolder<Item, Item> ALUMINIUM_AXE = ITEMS.registerItem("aluminium_axe",
            Item::new);
    public static final DeferredHolder<Item, Item> ALUMINIUM_HOE = ITEMS.registerItem("aluminium_hoe",
            Item::new);

    public static final DeferredHolder<Item, Item> TITANIUM_SWORD = ITEMS.registerItem("titanium_sword",
            Item::new);
    public static final DeferredHolder<Item, Item> TITANIUM_PICKAXE = ITEMS.registerItem("titanium_pickaxe",
            Item::new);
    public static final DeferredHolder<Item, Item> TITANIUM_SHOVEL = ITEMS.registerItem("titanium_shovel",
            Item::new);
    public static final DeferredHolder<Item, Item> TITANIUM_AXE = ITEMS.registerItem("titanium_axe",
            Item::new);
    public static final DeferredHolder<Item, Item> TITANIUM_HOE = ITEMS.registerItem("titanium_hoe",
            Item::new);

    public static final DeferredHolder<Item, Item> MAGUNESIUM_SWORD = ITEMS.registerItem("magunesium_sword",
            Item::new);
    public static final DeferredHolder<Item, Item> MAGUNESIUM_PICKAXE = ITEMS.registerItem("magunesium_pickaxe",
            Item::new);
    public static final DeferredHolder<Item, Item> MAGUNESIUM_SHOVEL = ITEMS.registerItem("magunesium_shovel",
            Item::new);
    public static final DeferredHolder<Item, Item> MAGUNESIUM_AXE = ITEMS.registerItem("magunesium_axe",
            Item::new);
    public static final DeferredHolder<Item, Item> MAGUNESIUM_HOE = ITEMS.registerItem("magunesium_hoe",
            Item::new);

    public static final DeferredHolder<Item, Item> VANADIUM_SWORD = ITEMS.registerItem("vanadium_sword",
            Item::new);
    public static final DeferredHolder<Item, Item> VANADIUM_PICKAXE = ITEMS.registerItem("vanadium_pickaxe",
            Item::new);
    public static final DeferredHolder<Item, Item> VANADIUM_SHOVEL = ITEMS.registerItem("vanadium_shovel",
            Item::new);
    public static final DeferredHolder<Item, Item> VANADIUM_AXE = ITEMS.registerItem("vanadium_axe",
            Item::new);
    public static final DeferredHolder<Item, Item> VANADIUM_HOE = ITEMS.registerItem("vanadium_hoe",
            Item::new);

    public static final DeferredHolder<Item, Item> DURALUMIN_SWORD = ITEMS.registerItem("duralumin_sword",
            Item::new);
    public static final DeferredHolder<Item, Item> DURALUMIN_PICKAXE = ITEMS.registerItem("duralumin_pickaxe",
            Item::new);
    public static final DeferredHolder<Item, Item> DURALUMIN_SHOVEL = ITEMS.registerItem("duralumin_shovel",
            Item::new);
    public static final DeferredHolder<Item, Item> DURALUMIN_AXE = ITEMS.registerItem("duralumin_axe",
            Item::new);
    public static final DeferredHolder<Item, Item> DURALUMIN_HOE = ITEMS.registerItem("duralumin_hoe",
            Item::new);

    public static final DeferredHolder<Item, Item> TITAN_ALLOY_SWORD = ITEMS.registerItem("titan_alloy_sword",
            Item::new);
    public static final DeferredHolder<Item, Item> TITAN_ALLOY_PICKAXE = ITEMS.registerItem("titan_alloy_pickaxe",
            Item::new);
    public static final DeferredHolder<Item, Item> TITAN_ALLOY_SHOVEL = ITEMS.registerItem("titan_alloy_shovel",
            Item::new);
    public static final DeferredHolder<Item, Item> TITAN_ALLOY_AXE = ITEMS.registerItem("titan_alloy_axe",
            Item::new);
    public static final DeferredHolder<Item, Item> TITAN_ALLOY_HOE = ITEMS.registerItem("titan_alloy_hoe",
            Item::new);

    public static final DeferredHolder<Item, Item> ELECTRON_SWORD = ITEMS.registerItem("electron_sword",
            Item::new);
    public static final DeferredHolder<Item, Item> ELECTRON_PICKAXE = ITEMS.registerItem("electron_pickaxe",
            Item::new);
    public static final DeferredHolder<Item, Item> ELECTRON_SHOVEL = ITEMS.registerItem("electron_shovel",
            Item::new);
    public static final DeferredHolder<Item, Item> ELECTRON_AXE = ITEMS.registerItem("electron_axe",
            Item::new);
    public static final DeferredHolder<Item, Item> ELECTRON_HOE = ITEMS.registerItem("electron_hoe",
            Item::new);

    public static final DeferredHolder<Item, Item> VANADIUM_ALLOY_SWORD = ITEMS.registerItem("vanadium_alloy_sword",
            Item::new);
    public static final DeferredHolder<Item, Item> VANADIUM_ALLOY_PICKAXE = ITEMS.registerItem("vanadium_alloy_pickaxe",
            Item::new);
    public static final DeferredHolder<Item, Item> VANADIUM_ALLOY_SHOVEL = ITEMS.registerItem("vanadium_alloy_shovel",
            Item::new);
    public static final DeferredHolder<Item, Item> VANADIUM_ALLOY_AXE = ITEMS.registerItem("vanadium_alloy_axe",
            Item::new);
    public static final DeferredHolder<Item, Item> VANADIUM_ALLOY_HOE = ITEMS.registerItem("vanadium_alloy_hoe",
            Item::new,
            new Item.Properties().stacksTo(1)
                            .rarity(Rarity.RARE));

    //呪文系
   public static final DeferredHolder<Item, Item> CROCODILE_JAW_CHAIN = ITEMS.registerItem("crocodile_jaw_chain",
            CrocodileJawChainItem::new,
            new Item.Properties()
                            .stacksTo(1)
                            .rarity(Rarity.RARE));

    public static final DeferredHolder<Item, Item> LUNAR_CLAW_BLADE = ITEMS.registerItem("lunar_claw_blade",
            LunarClawBladeItem::new,
            new Item.Properties()
                            .stacksTo(1)
                            .rarity(Rarity.RARE));

    public static final DeferredHolder<Item, Item> FLOWER_MANTIS_STAFF = ITEMS.registerItem("flower_staff",
            FlowerMantisStaffItem::new);

    public static final DeferredHolder<Item, Item> CLIONE_STAFF = ITEMS.registerItem("clione_staff",
            ClioneStaffItem::new);

    public static final DeferredHolder<Item, Item> SHIELD_CAST = ITEMS.registerItem("shield_cast",
            ShieldCast::new);

    public static final DeferredHolder<Item, Item> REVERSAL = ITEMS.registerItem("reversal",
            Reversal::new);

    public static final DeferredHolder<Item, Item> GOLEM_SUMMON = ITEMS.registerItem("golem_summon",
            GolemSummon::new);

    public static final DeferredHolder<Item, Item> ENDER_SPELL = ITEMS.registerItem("ender_spell",
            EnderManItem::new);

    public static final DeferredHolder<Item, Item> SOUL_SPELL = ITEMS.registerItem("soul_spell",
            SoulSandItem::new);

    public static final DeferredHolder<Item, Item> HOOK_SHOT = ITEMS.registerItem("hook_shot",
            HookShot::new);

    public static final DeferredHolder<Item, Item> GRAPPLING = ITEMS.registerItem("grappling",
            Grappling::new);

    public static final DeferredHolder<Item, Item> TONBOKIRI = ITEMS.registerItem("tonbokiri",
            Tonbokiri::new);

    public static final DeferredHolder<Item, Item> MANTIS_GRAB = ITEMS.registerItem("mantis_grab",
            MantisGrab::new,
            new Item.Properties().durability(125));

    public static final DeferredHolder<Item, Item> PICKAXE_ROD = ITEMS.registerItem("pickaxe_rod",
            PickaxeRod::new);

    public static final DeferredHolder<Item, Item> PICKAXE_STICK = ITEMS.registerItem("pickaxe_stick",
            PickaxeShoot::new);

    public static final DeferredHolder<Item, Item> WOODEN_NET = ITEMS.registerItem("wooden_net",
            CaptureNet::new,
            new Item.Properties().durability(10));

    public static final DeferredHolder<Item, Item> IRON_NET = ITEMS.registerItem("iron_net",
            CaptureNet::new,
            new Item.Properties().durability(20));

    public static final DeferredHolder<Item, Item> DIAMOND_NET = ITEMS.registerItem("diamond_net",
            CaptureNet::new,
            new Item.Properties().durability(30));

    //防具
    public static final DeferredHolder<Item, Item> CHROMITE_HELMET = ITEMS.registerItem("chromite_helmet",
            Item::new);
    public static final DeferredHolder<Item, Item> CHROMITE_CHESTPLATE = ITEMS.registerItem("chromite_chestplate",
            Item::new);
    public static final DeferredHolder<Item, Item> CHROMITE_LEGGINGS = ITEMS.registerItem("chromite_leggings",
            Item::new);
    public static final DeferredHolder<Item, Item> CHROMITE_BOOTS = ITEMS.registerItem("chromite_boots",
            Item::new);

    public static final DeferredHolder<Item, Item> FLUORITE_HELMET = ITEMS.registerItem("fluorite_helmet",
            Item::new);
    public static final DeferredHolder<Item, Item> FLUORITE_CHESTPLATE = ITEMS.registerItem("fluorite_chestplate",
            Item::new);
    public static final DeferredHolder<Item, Item> FLUORITE_LEGGINGS = ITEMS.registerItem("fluorite_leggings",
            Item::new);
    public static final DeferredHolder<Item, Item> FLUORITE_BOOTS = ITEMS.registerItem("fluorite_boots",
            Item::new);

    public static final DeferredHolder<Item, Item> STAINLESS_HELMET = ITEMS.registerItem("stainless_helmet",
            Item::new);
    public static final DeferredHolder<Item, Item> STAINLESS_CHESTPLATE = ITEMS.registerItem("stainless_chestplate",
            Item::new);
    public static final DeferredHolder<Item, Item> STAINLESS_LEGGINGS = ITEMS.registerItem("stainless_leggings",
            Item::new);
    public static final DeferredHolder<Item, Item> STAINLESS_BOOTS = ITEMS.registerItem("stainless_boots",
            Item::new);

    public static final DeferredHolder<Item, Item> ALUMINIUM_HELMET = ITEMS.registerItem("aluminium_helmet",
            Item::new);
    public static final DeferredHolder<Item, Item> ALUMINIUM_CHESTPLATE = ITEMS.registerItem("aluminium_chestplate",
            Item::new);
    public static final DeferredHolder<Item, Item> ALUMINIUM_LEGGINGS = ITEMS.registerItem("aluminium_leggings",
            Item::new);
    public static final DeferredHolder<Item, Item> ALUMINIUM_BOOTS = ITEMS.registerItem("aluminium_boots",
            Item::new);

    public static final DeferredHolder<Item, Item> TITANIUM_HELMET = ITEMS.registerItem("titanium_helmet",
            Item::new);
    public static final DeferredHolder<Item, Item> TITANIUM_CHESTPLATE = ITEMS.registerItem("titanium_chestplate",
            Item::new);
    public static final DeferredHolder<Item, Item> TITANIUM_LEGGINGS = ITEMS.registerItem("titanium_leggings",
            Item::new);
    public static final DeferredHolder<Item, Item> TITANIUM_BOOTS = ITEMS.registerItem("titanium_boots",
            Item::new);

    public static final DeferredHolder<Item, Item> MAGUNESIUM_HELMET = ITEMS.registerItem("magunesium_helmet",
            Item::new);
    public static final DeferredHolder<Item, Item> MAGUNESIUM_CHESTPLATE = ITEMS.registerItem("magunesium_chestplate",
            Item::new);
    public static final DeferredHolder<Item, Item> MAGUNESIUM_LEGGINGS = ITEMS.registerItem("magunesium_leggings",
            Item::new);
    public static final DeferredHolder<Item, Item> MAGUNESIUM_BOOTS = ITEMS.registerItem("magunesium_boots",
            Item::new);

    public static final DeferredHolder<Item, Item> VANADIUM_HELMET = ITEMS.registerItem("vanadium_helmet",
            Item::new);
    public static final DeferredHolder<Item, Item> VANADIUM_CHESTPLATE = ITEMS.registerItem("vanadium_chestplate",
            Item::new);
    public static final DeferredHolder<Item, Item> VANADIUM_LEGGINGS = ITEMS.registerItem("vanadium_leggings",
            Item::new);
    public static final DeferredHolder<Item, Item> VANADIUM_BOOTS = ITEMS.registerItem("vanadium_boots",
            Item::new);

    public static final DeferredHolder<Item, Item> DURALUMIN_HELMET = ITEMS.registerItem("duralumin_helmet",
            props -> new DuraluminArmorItem(ModArmorMaterials.DURALUMIN, ArmorType.HELMET, props));
    public static final DeferredHolder<Item, Item> DURALUMIN_CHESTPLATE = ITEMS.registerItem("duralumin_chestplate",
            props -> new DuraluminArmorItem(ModArmorMaterials.DURALUMIN, ArmorType.CHESTPLATE, props));
    public static final DeferredHolder<Item, Item> DURALUMIN_LEGGINGS = ITEMS.registerItem("duralumin_leggings",
            props -> new DuraluminArmorItem(ModArmorMaterials.DURALUMIN, ArmorType.LEGGINGS, props));
    public static final DeferredHolder<Item, Item> DURALUMIN_BOOTS = ITEMS.registerItem("duralumin_boots",
            props -> new DuraluminArmorItem(ModArmorMaterials.DURALUMIN, ArmorType.BOOTS, props));

    public static final DeferredHolder<Item, Item> TITAN_ALLOY_HELMET = ITEMS.registerItem("titan_alloy_helmet",
            Item::new);
    public static final DeferredHolder<Item, Item> TITAN_ALLOY_CHESTPLATE = ITEMS.registerItem("titan_alloy_chestplate",
            Item::new);
    public static final DeferredHolder<Item, Item> TITAN_ALLOY_LEGGINGS = ITEMS.registerItem("titan_alloy_leggings",
            Item::new);
    public static final DeferredHolder<Item, Item> TITAN_ALLOY_BOOTS = ITEMS.registerItem("titan_alloy_boots",
            Item::new);

    public static final DeferredHolder<Item, Item> ELECTRON_HELMET = ITEMS.registerItem("electron_helmet",
            props -> new ElectronArmorItem(ModArmorMaterials.ELECTRON, ArmorType.HELMET, props));
    public static final DeferredHolder<Item, Item> ELECTRON_CHESTPLATE = ITEMS.registerItem("electron_chestplate",
            props -> new ElectronArmorItem(ModArmorMaterials.ELECTRON, ArmorType.CHESTPLATE, props));
    public static final DeferredHolder<Item, Item> ELECTRON_LEGGINGS = ITEMS.registerItem("electron_leggings",
            props -> new ElectronArmorItem(ModArmorMaterials.ELECTRON, ArmorType.LEGGINGS, props));
    public static final DeferredHolder<Item, Item> ELECTRON_BOOTS = ITEMS.registerItem("electron_boots",
            props -> new ElectronArmorItem(ModArmorMaterials.ELECTRON, ArmorType.BOOTS, props));

    public static final DeferredHolder<Item, Item> VANADIUM_ALLOY_HELMET = ITEMS.registerItem("vanadium_alloy_helmet",
            Item::new);
    public static final DeferredHolder<Item, Item> VANADIUM_ALLOY_CHESTPLATE = ITEMS.registerItem("vanadium_alloy_chestplate",
            Item::new);
    public static final DeferredHolder<Item, Item> VANADIUM_ALLOY_LEGGINGS = ITEMS.registerItem("vanadium_alloy_leggings",
            Item::new);
    public static final DeferredHolder<Item, Item> VANADIUM_ALLOY_BOOTS = ITEMS.registerItem("vanadium_alloy_boots",
            Item::new);

    public static final DeferredHolder<Item, Item> PERISO_HELMET = ITEMS.registerItem("periso_helmet",
            props -> new PerissoArmor(ModArmorMaterials.PERISO, ArmorType.HELMET, props));

    public static final DeferredHolder<Item, Item> ROCK_PENGUIN_BOOTS = ITEMS.registerItem("rock_penguin_boots",
            props -> new RockPenguinArmor(ModArmorMaterials.ROCK_PENGUIN, ArmorType.BOOTS, props));

    public static final DeferredHolder<Item, Item> EMU_BOOTS = ITEMS.registerItem("emu_boots",
            props -> new EmuArmorItem(ModArmorMaterials.EMU, ArmorType.BOOTS, props));

    public static final DeferredHolder<Item, Item> CASSOWARY_BOOTS = ITEMS.registerItem("cassowary_boots",
            props -> new CassowaryArmorItem(ModArmorMaterials.CASSOWARY, ArmorType.BOOTS, props));

    public static final DeferredHolder<Item, Item> ANCIENT_LIZARD_HELMET = ITEMS.registerItem("ancient_lizard_boots",
            props -> new AncientLizardArmor(ModArmorMaterials.ANCIENT, ArmorType.HELMET, props));

    public static final DeferredHolder<Item, Item> LEOPARD_GECKO_TAIL_BELT = ITEMS.registerItem("leopard_gecko_tail_belt",
            props -> new LeopardGeckoArmor(ModArmorMaterials.LEOPA,
                    ArmorType.LEGGINGS, props));

    //public static final DeferredHolder<Item, Item> PANGOLIN_CHESTPLATE = ITEMS.register("pangolin_chestplate",
    //        () -> new PangolinArmor(ModArmorMaterials.ROCK_PENGUIN, ArmorType.BOOTS, new Item.Properties()));


    //食べ物
    public static final DeferredHolder<Item, Item> CAVIAR = ITEMS.registerItem("caviar",
            CustomFoodItems.CaviarItem::new);


    public static final DeferredHolder<Item, Item> CABBAGE_LEAF = ITEMS.registerItem("cabbage",
            CustomFoodItems.CabbageItem::new);

    public static final DeferredHolder<Item, Item> ASPARAGUS = ITEMS.registerItem("asparagus",
            CustomFoodItems.AsparagusItem::new);

    public static final DeferredHolder<Item, Item> GRAPE = ITEMS.registerItem("grape",
            CustomFoodItems.GrapeItem::new);

    public static final DeferredHolder<Item, Item> LEMON = ITEMS.registerItem("lemon",
            CustomFoodItems.LemonItem::new);

    public static final DeferredHolder<Item, Item> CINNAMON = ITEMS.registerItem("cinnamon",
            CustomFoodItems.CinnamonItem::new);

    public static final DeferredHolder<Item, Item> ROW_RICE = ITEMS.registerItem("row_rice",
            CustomFoodItems.RiceItem::new);

    public static final DeferredHolder<Item, Item> RICE = ITEMS.registerItem("rice",
            CustomFoodItems.RiceItem::new);

    public static final DeferredHolder<Item, Item> MINT = ITEMS.registerItem("mint",
            CustomFoodItems.RiceItem::new);

    public static final DeferredHolder<Item, Item> COLA = ITEMS.registerItem("cola",
            props -> new BlockItem(ModBlocks.COLA_FRUIT.get(), props),
            new Item.Properties().food(ModFoods.VEGETABLE1));

    public static final DeferredHolder<Item, Item> TOMATO = ITEMS.registerItem("tomato",
            CustomFoodItems.TomatoItem::new);

    public static final DeferredHolder<Item, Item> CORN = ITEMS.registerItem("corn",
            CustomFoodItems.CornItem::new);

    public static final DeferredHolder<Item, Item> ONION = ITEMS.registerItem("onion",
            CustomFoodItems.OnionItem::new);

    public static final DeferredHolder<Item, Item> GINGER = ITEMS.registerItem("ginger",
            CustomFoodItems.GingerItem::new);

    public static final DeferredHolder<Item, Item> GREEN_PEPPER = ITEMS.registerItem("green_pepper",
            CustomFoodItems.GreenPepperItem::new);

    public static final DeferredHolder<Item, Item> PAPRIKA = ITEMS.registerItem("paprika",
            CustomFoodItems.PaprikaItem::new);

    public static final DeferredHolder<Item, Item> EGGPLANT = ITEMS.registerItem("eggplant",
            CustomFoodItems.EggPlantItem::new);

    public static final DeferredHolder<Item, Item> WHITE_RADISH = ITEMS.registerItem("white_radish",
            CustomFoodItems.WhiteRadishItem::new);

    public static final DeferredHolder<Item, Item> PLUM = ITEMS.registerItem("plum",
            CustomFoodItems.PlumItem::new);

    public static final DeferredHolder<Item, Item> CHERRY = ITEMS.registerItem("cherry",
            CustomFoodItems.CherryItem::new);

    public static final DeferredHolder<Item, Item> BANANA = ITEMS.registerItem("banana",
            CustomFoodItems.BananaItem::new);

    public static final DeferredHolder<Item, Item> COCONUT = ITEMS.registerItem("coconut",
            CustomFoodItems.CoconutItem::new);

    public static final DeferredHolder<Item, Item> PEACH = ITEMS.registerItem("peach",
            CustomFoodItems.PeachItem::new);

    public static final DeferredHolder<Item, Item> KIWI = ITEMS.registerItem("kiwi",
            CustomFoodItems.KiwiItem::new);

    public static final DeferredHolder<Item, Item> ALMOND = ITEMS.registerItem("almond",
            props -> new BlockItem(ModBlocks.ALMOND_FRUIT.get(), props),
            new Item.Properties().food(ModFoods.VEGETABLE1));

    public static final DeferredHolder<Item, Item> DURIAN = ITEMS.registerItem("durian",
            props -> new BlockItem(ModBlocks.DURIAN_FRUIT.get(), props),
            new Item.Properties().food(ModFoods.VEGETABLE1));

    public static final DeferredHolder<Item, Item> OLIVE = ITEMS.registerItem("olive",
            CustomFoodItems.OliveItem::new);

    public static final DeferredHolder<Item, Item> BLUE_BERRY = ITEMS.registerItem("blue_berry",
            CustomFoodItems.BlueBerryItem::new);

    public static final DeferredHolder<Item, Item> CHILI_PEPPER = ITEMS.registerItem("chili_pepper",
            CustomFoodItems.ChiliPepperItem::new);

    public static final DeferredHolder<Item, Item> BASIL = ITEMS.registerItem("basil",
            CustomFoodItems.BasilItem::new);

    public static final DeferredHolder<Item, Item> LOTUS_ROOT = ITEMS.registerItem("lotus_root",
            CustomFoodItems.LotusRootItem::new);

    public static final DeferredHolder<Item, Item> TOMATO_SAND = ITEMS.registerItem("tomato_sand",
            CustomFoodItems.TomatoSandItem::new);

    public static final DeferredHolder<Item, Item> BANANA_SAND = ITEMS.registerItem("banana_sand",
            CustomFoodItems.BananaSandItem::new);

    public static final DeferredHolder<Item, Item> PEACH_SAND = ITEMS.registerItem("peach_sand",
            CustomFoodItems.PeachSandItem::new);

    public static final DeferredHolder<Item, Item> APPLE_SAND = ITEMS.registerItem("apple_sand",
            CustomFoodItems.AppleSandItem::new);

    public static final DeferredHolder<Item, Item> GRAPE_SAND = ITEMS.registerItem("grape_sand",
            CustomFoodItems.GrapeSandItem::new);


    //frypanレシピ
    public static final DeferredHolder<Item, Item> ASPARAGUS_BACON = ITEMS.registerItem("asparagus_bacon",
            CustomFoodItems.AsparagusBaconItem::new);

    public static final DeferredHolder<Item, Item> GINGER_PORK = ITEMS.registerItem("ginger_pork",
            CustomFoodItems.GingerPorkItem::new);

    public static final DeferredHolder<Item, Item> FRIED_EGGPLANT = ITEMS.registerItem("fried_eggplant",
            CustomFoodItems.FriedEggplantItem::new);

    public static final DeferredHolder<Item, Item> CHINJAOLOSE = ITEMS.registerItem("chinjaolose",
            CustomFoodItems.ChinjaoloseeItem::new);

    public static final DeferredHolder<Item, Item> POPCORN = ITEMS.registerItem("popcorn",
            CustomFoodItems.PopcornItem::new);

    public static final DeferredHolder<Item, Item> PIZZA_BRED = ITEMS.registerItem("pizza_bread",
            CustomFoodItems.PizzaBredItem::new);

    public static final DeferredHolder<Item, Item> BOILED_FISH = ITEMS.registerItem("boiled_fish",
            CustomFoodItems.BoiledFishItem::new);

    public static final DeferredHolder<Item, Item> CORN_SOUP = ITEMS.registerItem("corn_soup",
            CustomFoodItems.CornSoupItem::new);

    public static final DeferredHolder<Item, Item> HAMBURGER = ITEMS.registerItem("hamburger",
            CustomFoodItems.HambugerItem::new);

    public static final DeferredHolder<Item, Item> PEPERONCINO = ITEMS.registerItem("peperoncino",
            CustomFoodItems.PeperoncinoItem::new);

    public static final DeferredHolder<Item, Item> MABO_NASU = ITEMS.registerItem("mabo_nasu",
            CustomFoodItems.MaboNasuItem::new);

    public static final DeferredHolder<Item, Item> BAKED_CORN = ITEMS.registerItem("baked_corn",
            CustomFoodItems.BakedCornItem::new);

    public static final DeferredHolder<Item, Item> RADISH_MINCED_MEAT = ITEMS.registerItem("radish_minced_meat",
            CustomFoodItems.RadishMinciMeatItem::new);

    public static final DeferredHolder<Item, Item> CHICKEN_EGG = ITEMS.registerItem("chicken_egg",
            CustomFoodItems.ChickenEggItem::new);

    public static final DeferredHolder<Item, Item> GENOVESE = ITEMS.registerItem("genovese",
            CustomFoodItems.GenoveseItem::new);

    public static final DeferredHolder<Item, Item> FRIED_ALMOND = ITEMS.registerItem("fried_almond",
            CustomFoodItems.FriedAlmondItem::new);

    public static final DeferredHolder<Item, Item> GREEN_CARRY = ITEMS.registerItem("green_carry",
            CustomFoodItems.GreenCarryItem::new);

    public static final DeferredHolder<Item, Item> GREEN_PEPPER_MINCED_MEAT = ITEMS.registerItem("green_pepper_minced_meat",
            CustomFoodItems.GreenPepperMincedMeatItem::new);

    public static final DeferredHolder<Item, Item> PEPE_CABBAGE = ITEMS.registerItem("pepe_cabbage",
            CustomFoodItems.PepeCabbageItem::new);

    public static final DeferredHolder<Item, Item> FRIED_LOTUS_ROOT = ITEMS.registerItem("fried_lotus_root",
            CustomFoodItems.FriedLotusRootItem::new);

    public static final DeferredHolder<Item, Item> LOTUS_ROOT_MINCED_MEAT = ITEMS.registerItem("lotus_root_minced_meat",
            CustomFoodItems.LotusRootMincedMeatItem::new);

    public static final DeferredHolder<Item, Item> GAPRAO = ITEMS.registerItem("gaprao",
            CustomFoodItems.GapraoItem::new);

    public static final DeferredHolder<Item, Item> TACOS = ITEMS.registerItem("tacos",
            CustomFoodItems.TacosItem::new);

    public static final DeferredHolder<Item, Item> TEA = ITEMS.registerItem("tea",
            Item::new);

    public static final DeferredHolder<Item, Item> HOP = ITEMS.registerItem("hop",
            Item::new);

    public static final DeferredHolder<Item, Item> PEPPER = ITEMS.registerItem("pepper",
            Item::new);

    public static final DeferredHolder<Item, Item> ROW_COFFEE_BEANS = ITEMS.registerItem("row_coffee_beans",
            props -> new BlockItem(ModBlocks.COFFEE_FRUIT.get(), props));

    public static final DeferredHolder<Item, Item> COFFEE_BEANS = ITEMS.registerItem("coffee_beans",
            Item::new);

    public static final DeferredHolder<Item, Item> MUSK_COFFEE_BEANS = ITEMS.registerItem("musk_coffee_beans",
            Item::new);

    //燃料
    public static final DeferredHolder<Item, Item> FIREWOOD = ITEMS.registerItem("fire_wood",
            FireWood::new);

    //飲み物
    public static final DeferredHolder<Item, Item> FILTERED_WATER = ITEMS.registerItem("filtered_water",
            CustomFoodItems.FilteredWaterItem::new);

    public static final DeferredHolder<Item, Item> BOTTLE_OF_MILK = ITEMS.registerItem("bottle_of_milk",
            CustomFoodItems.BottleMilkItem::new);

    public static final DeferredHolder<Item, Item> MAPLE_WATER = ITEMS.registerItem("maple_water",
            CustomFoodItems.MapleWaterItem::new);

    public static final DeferredHolder<Item, Item> MAPLE_SYRUP = ITEMS.registerItem("maple_syrup",
            CustomFoodItems.MapleSyrupItem::new);

    public static final DeferredHolder<Item, Item> COFFEE = ITEMS.registerItem("coffe",
            CustomFoodItems.CoffeeItem::new);

    public static final DeferredHolder<Item, Item> MUSK_COFFEE = ITEMS.registerItem("musk_coffe",
            CustomFoodItems.KopiLuwakCoffeeItem::new);


    //distiller
    public static final DeferredHolder<Item, Item> BOTTLE_OF_WHISKEY = ITEMS.registerItem("bottle_of_whiskey",
            CustomFoodItems.WhiskeyItem::new);

    public static final DeferredHolder<Item, Item> BOTTLE_OF_WHITE_LIQUOR = ITEMS.registerItem("bottle_of_white_liquor",
            CustomFoodItems.WhiteLiquorItem::new);

    public static final DeferredHolder<Item, Item> BOTTLE_OF_COLA = ITEMS.registerItem("bottle_of_cola",
            CustomFoodItems.ColaItem::new);


    //酒樽
    public static final DeferredHolder<Item, Item> BOTTLE_OF_RED_WINE = ITEMS.registerItem("bottle_of_red_wine",
            CustomFoodItems.RedWineItem::new);

    public static final DeferredHolder<Item, Item> BOTTLE_OF_WHITE_WINE = ITEMS.registerItem("bottle_of_white_wine",
            CustomFoodItems.WhiteWineItem::new);

    public static final DeferredHolder<Item, Item> BOTTLE_OF_SAKE = ITEMS.registerItem("bottle_of_sake",
            CustomFoodItems.SakeItem::new);

    public static final DeferredHolder<Item, Item> PEACH_LIQUEUR = ITEMS.registerItem("peach_liqueur",
            CustomFoodItems.PeachLiquorItem::new);

    public static final DeferredHolder<Item, Item> PLUM_LIQUEUR = ITEMS.registerItem("plum_liqueur",
            CustomFoodItems.PlumLiquorItem::new);

    public static final DeferredHolder<Item, Item> LEMON_LIQUEUR = ITEMS.registerItem("lemon_liqueur",
            CustomFoodItems.LemonLiquorItem::new);

    public static final DeferredHolder<Item, Item> MINT_LIQUEUR = ITEMS.registerItem("mint_liqueur",
            CustomFoodItems.MintLiquorItem::new);

    public static final DeferredHolder<Item, Item> APPLE_LIQUEUR = ITEMS.registerItem("apple_liqueur",
            CustomFoodItems.AppleLiquorItem::new);

    public static final DeferredHolder<Item, Item> HABU_LIQUEUR = ITEMS.registerItem("habu_liqueur",
            CustomFoodItems.HabuLiquorItem::new);


    //大釜で酒作る
    public static final DeferredHolder<Item, Item> ASPERGILLUS = ITEMS.registerItem("aspergillus",
            CustomFoodItems.AspergillusItem::new);

    public static final DeferredHolder<Item, Item> JAPANESE_YEAST = ITEMS.registerItem("japanese_yeast",
            CustomFoodItems.JYeastItem::new);


    //mixerで作る飲み物
    public static final DeferredHolder<Item, Item> GRAPE_JUICE = ITEMS.registerItem("grape_juice",
            CustomFoodItems.GrapeJuiceItem::new);

    public static final DeferredHolder<Item, Item> APPLE_JUICE = ITEMS.registerItem("apple_juice",
            CustomFoodItems.AppleJuiceItem::new);

    public static final DeferredHolder<Item, Item> LEMON_JUICE = ITEMS.registerItem("lemon_juice",
            CustomFoodItems.LemonJuiceItem::new);

    public static final DeferredHolder<Item, Item> PEACH_JUICE = ITEMS.registerItem("peach_juice",
            CustomFoodItems.PeachJuiceItem::new);

    public static final DeferredHolder<Item, Item> PLUM_JUICE = ITEMS.registerItem("plum_juice",
            CustomFoodItems.PlumJuiceItem::new);

    public static final DeferredHolder<Item, Item> BANANA_JUICE = ITEMS.registerItem("banana_juice",
            CustomFoodItems.BananaJuiceItem::new);

    public static final DeferredHolder<Item, Item> ALMOND_MILK = ITEMS.registerItem("almond_juice",
            CustomFoodItems.AlmondJuiceItem::new);

    public static final DeferredHolder<Item, Item> COCONUT_MILK = ITEMS.registerItem("coconut_milk",
            CustomFoodItems.CoconutJuiceItem::new);

    public static final DeferredHolder<Item, Item> SMOOTHIE = ITEMS.registerItem("smoothie",
            CustomFoodItems.SmoothieJuiceItem::new);

    public static final DeferredHolder<Item, Item> MIX_JUICE = ITEMS.registerItem("mix_juice",
            CustomFoodItems.MixJuiceItem::new);

    public static final DeferredHolder<Item, Item> MIX_AU_LAIT = ITEMS.registerItem("mix_au_lait",
            CustomFoodItems.MixAuLaitJuiceItem::new);

    public static final DeferredHolder<Item, Item> CHOCOLATE = ITEMS.registerItem("chocolate",
            CustomFoodItems.ChocoJuiceItem::new);

    public static final DeferredHolder<Item, Item> CHOCO_MINT = ITEMS.registerItem("choco_mint",
            CustomFoodItems.ChocoMintJuiceItem::new);


    //種
    public static final DeferredHolder<Item, Item> ASPARAGUS_SEEDS = ITEMS.registerItem("asparagus_seeds",
            props -> new BlockItem(ModBlocks.ASPARAGUS.get(), props));
    public static final DeferredHolder<Item, Item> CABBAGE_SEEDS = ITEMS.registerItem("cabbage_seeds",
            props -> new BlockItem(ModBlocks.CABBAGE.get(), props));
    public static final DeferredHolder<Item, Item> RICE_SEEDS = ITEMS.registerItem("rice_seeds",
            props -> new BlockItem(ModBlocks.RICE_BLOCK.get(), props));
    public static final DeferredHolder<Item, Item> MINT_SEEDS = ITEMS.registerItem("mint_seeds",
            props -> new BlockItem(ModBlocks.MINT_BLOCK.get(), props));
    public static final DeferredHolder<Item, Item> COLA_SEEDS = ITEMS.registerItem("cola_seeds",
            props -> new BlockItem(ModBlocks.COLA_FRUIT.get(), props));
    public static final DeferredHolder<Item, Item> TOMATO_SEEDS = ITEMS.registerItem("tomato_seeds",
            props -> new BlockItem(ModBlocks.TOMATO_BLOCK.get(), props));
    public static final DeferredHolder<Item, Item> CORN_SEEDS = ITEMS.registerItem("corn_seeds",
            props -> new BlockItem(ModBlocks.CORN_BLOCK.get(), props));
    public static final DeferredHolder<Item, Item> ONION_SEEDS = ITEMS.registerItem("onion_seeds",
            props -> new BlockItem(ModBlocks.ONION_BLOCK.get(), props));
    public static final DeferredHolder<Item, Item> GINGER_SEEDS = ITEMS.registerItem("ginger_seeds",
            props -> new BlockItem(ModBlocks.GINGER_BLOCK.get(), props));
    public static final DeferredHolder<Item, Item> GREEN_PEPPER_SEEDS = ITEMS.registerItem("green_pepper_seeds",
            props -> new BlockItem(ModBlocks.GREEN_PEPPER_BLOCK.get(), props));
    public static final DeferredHolder<Item, Item> PAPRIKA_SEEDS = ITEMS.registerItem("paprika_seeds",
            props -> new BlockItem(ModBlocks.PAPRIKA_BLOCK.get(), props));
    public static final DeferredHolder<Item, Item> EGGPLANT_SEEDS = ITEMS.registerItem("eggplant_seeds",
            props -> new BlockItem(ModBlocks.EGGPLANT_BLOCK.get(), props));
    public static final DeferredHolder<Item, Item> WHITE_RADISH_SEEDS = ITEMS.registerItem("white_radish_seeds",
            props -> new BlockItem(ModBlocks.WHITE_RADISH_BLOCK.get(), props));

    public static final DeferredHolder<Item, Item> CHILI_PEPPER_SEEDS = ITEMS.registerItem("chili_pepper_seeds",
            props -> new BlockItem(ModBlocks.CHILI_PEPPER_BLOCK.get(), props));

    public static final DeferredHolder<Item, Item> BASIL_SEEDS = ITEMS.registerItem("basil_seeds",
            props -> new BlockItem(ModBlocks.BASIL_BLOCK.get(), props));

    public static final DeferredHolder<Item, Item> LOTUS_ROOT_SEEDS = ITEMS.registerItem("lotus_root_seeds",
            props -> new BlockItem(ModBlocks.LOTUS_ROOT_BLOCK.get(), props));

    //飲み物バケツ
    public static final DeferredHolder<Item, Item> FILTERED_WATER_BUCKET = ITEMS.registerItem("filtered_water_bucket",
            props -> new BucketItem(ModFluids.SOURCE_CLEAN.get(), props),
            new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));

    public static final DeferredHolder<Item, Item> WHISKEY_BUCKET = ITEMS.registerItem("whiskey_bucket",
            props -> new BucketItem(ModFluids.SOURCE_WHISKEY.get(), props),
            new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));

    public static final DeferredHolder<Item, Item> MAPLE_BUCKET = ITEMS.registerItem("maple_bucket",
            props -> new BucketItem(ModFluids.SOURCE_MAPLE.get(), props),
            new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));

    public static final DeferredHolder<Item, Item> SAKE_BUCKET = ITEMS.registerItem("sake_bucket",
            props -> new BucketItem(ModFluids.SOURCE_SAKE.get(), props),
            new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));

    public static final DeferredHolder<Item, Item> WINE_BUCKET = ITEMS.registerItem("wine_bucket",
            props -> new BucketItem(ModFluids.SOURCE_WINE.get(), props),
            new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));

    public static final DeferredHolder<Item, Item> WHITE_WINE_BUCKET = ITEMS.registerItem("white_wine_bucket",
            props -> new BucketItem(ModFluids.SOURCE_WHITE_WINE.get(), props),
            new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));

    public static final DeferredHolder<Item, Item> J_MALT_BUCKET = ITEMS.registerItem("j_malt_bucket",
            props -> new SolidBucketItem(ModBlocks.JAPANESE_MALT_P.get(), SoundEvents.BUCKET_EMPTY_POWDER_SNOW, props),
            new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));

    public static final DeferredHolder<Item, Item> RICE_BUCKET = ITEMS.registerItem("rice_bucket",
            props -> new SolidBucketItem(ModBlocks.BOILED_RICE_BLOCK.get(), SoundEvents.BUCKET_EMPTY_POWDER_SNOW, props),
            new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));

    public static final DeferredHolder<Item, Item> MASH_BUCKET = ITEMS.registerItem("mash_bucket",
            props -> new BucketItem(ModFluids.SOURCE_MASH.get(), props),
            new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));

    public static final DeferredHolder<Item, Item> YEAST_BUCKET = ITEMS.registerItem("yeast_bucket",
            props -> new BucketItem(ModFluids.SOURCE_YEAST.get(), props),
            new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));





    //卵
    public static final DeferredHolder<Item, Item> SPARROW_SPAWN_EGG = ITEMS.registerItem("sparrow_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> DEER_SPAWN_EGG = ITEMS.registerItem("deer_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> DOE_SPAWN_EGG = ITEMS.registerItem("doe_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> SAKABAN_SPAWN_EGG = ITEMS.registerItem("sakaban_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> CICADA_SPAWN_EGG = ITEMS.registerItem("cicada_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> DRAGONFLY_SPAWN_EGG = ITEMS.registerItem("dragonfly_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> BUTTERFLY_SPAWN_EGG = ITEMS.registerItem("butterfly_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> LONG_TIT_SPAWN_EGG = ITEMS.registerItem("long_tit_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> SEAL_SPAWN_EGG = ITEMS.registerItem("seal_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> HERMIT_CRAB_SPAWN_EGG = ITEMS.registerItem("hermit_crab_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> MINI_HIPO_SPAWN_EGG = ITEMS.registerItem("mini_hipo_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> MONGOOSE_SPAWN_EGG = ITEMS.registerItem("mongoose_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> ANT_SPAWN_EGG = ITEMS.registerItem("ant_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> ETUPIRKA_SPAWN_EGG = ITEMS.registerItem("etupirka_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> SNAKE_SPAWN_EGG = ITEMS.registerItem("snake_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> PEACOCK_SPAWN_EGG = ITEMS.registerItem("peacock_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> BURROWING_OWL_SPAWN_EGG = ITEMS.registerItem("burrowing_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> FOLIVORE_SPAWN_EGG = ITEMS.registerItem("folivore_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> GIANT_OTTER_SPAWN_EGG = ITEMS.registerItem("giant_otter_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> GUYANA_RUPICOLA_SPAWN_EGG = ITEMS.registerItem("guyana_rupicola_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> HARPY_EAGLE_SPAWN_EGG = ITEMS.registerItem("harpy_eagle_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> MUSK_CAT_SPAWN_EGG = ITEMS.registerItem("musk_cat_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> PERISSO_SPAWN_EGG = ITEMS.registerItem("perisso_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> RATEL_SPAWN_EGG = ITEMS.registerItem("ratel_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> WOMBAT_SPAWN_EGG = ITEMS.registerItem("wombat_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> BEAVER_SPAWN_EGG = ITEMS.registerItem("beaver_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> HAMMER_HEAD_SPAWN_EGG = ITEMS.registerItem("hammer_head_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> LEAFY_SEA_SPAWN_EGG = ITEMS.registerItem("leafy_sea_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> KIWI_SPAWN_EGG = ITEMS.registerItem("kiwi_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> ROCK_PENGUIN_SPAWN_EGG = ITEMS.registerItem("rock_penguin_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> SKUNK_SPAWN_EGG = ITEMS.registerItem("skunk_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> STURGEON_SPAWN_EGG = ITEMS.registerItem("sturgeon_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> QUOKKA_SPAWN_EGG = ITEMS.registerItem("quokka_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> WOOD_PECKER_SPAWN_EGG = ITEMS.registerItem("wood_pecker_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> FELIS_SPAWN_EGG = ITEMS.registerItem("felis_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> FRUIT_FLY_SPAWN_EGG = ITEMS.registerItem("fruit_fly_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> INDICATOR_IDAE_SPAWN_EGG = ITEMS.registerItem("indicate_idae_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> MANTIS_SHRIMP_SPAWN_EGG = ITEMS.registerItem("mantis_shrimp_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> MEERKAT_SPAWN_EGG = ITEMS.registerItem("meerkat_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> PALLAS_CAT_SPAWN_EGG = ITEMS.registerItem("pallas_cat_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> PANGOLIN_SPAWN_EGG = ITEMS.registerItem("pangolin_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> PORCUPINE_SPAWN_EGG = ITEMS.registerItem("porcupine_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> TAPIR_SPAWN_EGG = ITEMS.registerItem("tapir_spawn_egg",
            SpawnEggItem::new);

    public static final DeferredHolder<Item, Item> EVERY_EGG = ITEMS.registerItem("every_egg",
            EveryEgg::new);




    public static final DeferredHolder<Item, Item> POTTERS_WHEEL_ITEM = ITEMS.registerItem("potters_wheel",
            props -> new BlockItem(ModBlocks.POTTERS_WHEEL.get(), props));

    // Items
    public static final DeferredHolder<Item, Item> UNFIRED_POTTERY = ITEMS.registerItem("unfired_pottery",
            UnfiredPotteryItem::new);

    public static final DeferredHolder<Item, Item> FIRED_POTTERY = ITEMS.registerItem("fired_pottery",
            FiredPotteryItem::new);

    // Glazes
    public static final DeferredHolder<Item, Item> GLAZE_WHITE = ITEMS.registerItem("glaze_white",
            props -> new GlazeItem(props, 0xFFFFFF));
    public static final DeferredHolder<Item, Item> GLAZE_BLACK = ITEMS.registerItem("glaze_black",
            props -> new GlazeItem(props, 0x000000));
    public static final DeferredHolder<Item, Item> GLAZE_RED = ITEMS.registerItem("glaze_red",
            props -> new GlazeItem(props, 0xFF0000));
    public static final DeferredHolder<Item, Item> GLAZE_BLUE = ITEMS.registerItem("glaze_blue",
            props -> new GlazeItem(props, 0x0000FF));
    public static final DeferredHolder<Item, Item> GLAZE_GREEN = ITEMS.registerItem("glaze_green",
            props -> new GlazeItem(props, 0x00FF00));
    public static final DeferredHolder<Item, Item> GLAZE_YELLOW = ITEMS.registerItem("glaze_yellow",
            props -> new GlazeItem(props, 0xFFFF00));
    public static final DeferredHolder<Item, Item> GLAZE_ORANGE = ITEMS.registerItem("glaze_orange",
            props -> new GlazeItem(props, 0xFFA500));
    public static final DeferredHolder<Item, Item> GLAZE_PURPLE = ITEMS.registerItem("glaze_purple",
            props -> new GlazeItem(props, 0x800080));
    public static final DeferredHolder<Item, Item> GLAZE_PINK = ITEMS.registerItem("glaze_pink",
            props -> new GlazeItem(props, 0xFFC0CB));
    public static final DeferredHolder<Item, Item> GLAZE_BROWN = ITEMS.registerItem("glaze_brown",
            props -> new GlazeItem(props, 0x8B4513));
    public static final DeferredHolder<Item, Item> GLAZE_GRAY = ITEMS.registerItem("glaze_gray",
            props -> new GlazeItem(props, 0x808080));
    public static final DeferredHolder<Item, Item> GLAZE_CYAN = ITEMS.registerItem("glaze_cyan",
            props -> new GlazeItem(props, 0x00FFFF));

    // Pattern Stamps
    public static final DeferredHolder<Item, Item> STAMP_STRIPES = ITEMS.registerItem("stamp_stripes",
            props -> new PotteryStampItem(props, PotteryPattern.STRIPES));
    public static final DeferredHolder<Item, Item> STAMP_DOTS = ITEMS.registerItem("stamp_dots",
            props -> new PotteryStampItem(props, PotteryPattern.DOTS));
    public static final DeferredHolder<Item, Item> STAMP_WAVES = ITEMS.registerItem("stamp_waves",
            props -> new PotteryStampItem(props, PotteryPattern.WAVES));
    public static final DeferredHolder<Item, Item> STAMP_FLOWERS = ITEMS.registerItem("stamp_flowers",
            props -> new PotteryStampItem(props, PotteryPattern.FLOWERS));
    public static final DeferredHolder<Item, Item> STAMP_GEOMETRIC = ITEMS.registerItem("stamp_geometric",
            props -> new PotteryStampItem(props, PotteryPattern.GEOMETRIC));

    // バイタルチェックマシン
    public static final DeferredHolder<Item, Item> VITAL_CHECK = ITEMS.registerItem("vital_check",
            VitalCheckItem::new,
            new Item.Properties().stacksTo(1)); // スタック不可



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
