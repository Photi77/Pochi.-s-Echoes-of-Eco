package net.pochi.pochimod.mineral.tools;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.pochi.pochimod.item.ModItems;

/**
 * 鉱物ツール・防具・アクセサリーのアイテム登録
 * 既存 ModItems.java にマージしてください
 */
public class MineralToolItems {

    public static final DeferredRegister<Item> ITEMS = ModItems.ITEMS; // 既存のITEMSを共有

    // ==============================
    //  武器
    // ==============================
    public static final DeferredHolder<Item, Item>MINERAL_SWORD = ITEMS.register(
            "mineral_sword",
            () -> new MineralSwordItem(new Item.Properties())
    );

    public static final DeferredHolder<Item, Item>MINERAL_AXE = ITEMS.register(
            "mineral_axe",
            () -> new MineralAxeItem(new Item.Properties())
    );

    // ==============================
    //  採掘ツール
    // ==============================
    public static final DeferredHolder<Item, Item>MINERAL_PICKAXE = ITEMS.register(
            "mineral_pickaxe",
            () -> new MineralPickaxeItem(new Item.Properties())
    );

    public static final DeferredHolder<Item, Item>MINERAL_SHOVEL = ITEMS.register(
            "mineral_shovel",
            () -> new MineralShovelItem(new Item.Properties())
    );

    // ==============================
    //  防具（4部位）
    // ==============================
    public static final DeferredHolder<Item, Item>MINERAL_HELMET = ITEMS.register(
            "mineral_helmet",
            () -> new MineralArmorItem(ArmorItem.Type.HELMET, new Item.Properties())
    );

    public static final DeferredHolder<Item, Item>MINERAL_CHESTPLATE = ITEMS.register(
            "mineral_chestplate",
            () -> new MineralArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Properties())
    );

    public static final DeferredHolder<Item, Item>MINERAL_LEGGINGS = ITEMS.register(
            "mineral_leggings",
            () -> new MineralArmorItem(ArmorItem.Type.LEGGINGS, new Item.Properties())
    );

    public static final DeferredHolder<Item, Item>MINERAL_BOOTS = ITEMS.register(
            "mineral_boots",
            () -> new MineralArmorItem(ArmorItem.Type.BOOTS, new Item.Properties())
    );

    // ==============================
    //  アクセサリー
    // ==============================
    public static final DeferredHolder<Item, Item>MINERAL_RING = ITEMS.register(
            "mineral_ring",
            () -> new MineralRingItem(new Item.Properties())
    );
}
