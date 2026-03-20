package net.pochi.pochimod.item;

import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.pochi.pochimod.PochiMod;

import java.util.EnumMap;
import java.util.Map;

public class ModArmorMaterials {

    private static Holder<ArmorMaterial> register(String name, Map<ArmorType, Integer> defense,
            int enchantmentValue, net.minecraft.core.Holder<net.minecraft.sounds.SoundEvent> equipSound,
            float toughness, float knockbackResistance) {
        return Holder.direct(new ArmorMaterial(
                1,
                defense,
                enchantmentValue,
                equipSound,
                toughness,
                knockbackResistance,
                ItemTags.REPAIRS_IRON_ARMOR,
                ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, name))));
    }

    public static final Holder<ArmorMaterial> LEOPA = register("leopa",
            makeDefense(2, 5, 6, 2), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F);

    public static final Holder<ArmorMaterial> ANCIENT = register("ancient",
            makeDefense(2, 5, 6, 2), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F);

    public static final Holder<ArmorMaterial> CASSOWARY = register("cassowary",
            makeDefense(2, 5, 6, 2), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F);

    public static final Holder<ArmorMaterial> EMU = register("emu",
            makeDefense(2, 5, 6, 2), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F);

    public static final Holder<ArmorMaterial> PERISO = register("periso",
            makeDefense(2, 5, 6, 2), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F);

    public static final Holder<ArmorMaterial> ROCK_PENGUIN = register("rock_penguin",
            makeDefense(2, 5, 6, 2), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F);

    public static final Holder<ArmorMaterial> CHROMITE = register("chromite",
            makeDefense(2, 5, 6, 2), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F);

    public static final Holder<ArmorMaterial> FLUORITE = register("fluorite",
            makeDefense(2, 4, 5, 2), 35, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F);

    public static final Holder<ArmorMaterial> STAINLESS = register("stainless",
            makeDefense(3, 6, 8, 3), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F);

    public static final Holder<ArmorMaterial> ALUMINIUM = register("aluminium",
            makeDefense(1, 4, 5, 2), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F);

    public static final Holder<ArmorMaterial> TITANIUM = register("titanium",
            makeDefense(2, 5, 6, 2), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F);

    public static final Holder<ArmorMaterial> MAGUNESIUM = register("magunesium",
            makeDefense(1, 4, 5, 2), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F);

    public static final Holder<ArmorMaterial> VANADIUM = register("vanadium",
            makeDefense(1, 4, 5, 2), 12, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F);

    public static final Holder<ArmorMaterial> DURALUMIN = register("duralumin",
            makeDefense(4, 7, 9, 4), 5, SoundEvents.ARMOR_EQUIP_IRON, 3.0F, 0.1F);

    public static final Holder<ArmorMaterial> TITAN_ALLOY = register("titan_alloy",
            makeDefense(3, 6, 8, 3), 9, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.0F);

    public static final Holder<ArmorMaterial> ELECTRON = register("electron",
            makeDefense(2, 5, 6, 2), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F);

    public static final Holder<ArmorMaterial> VANADIUM_ALLOY = register("vanadium_alloy",
            makeDefense(2, 5, 6, 2), 15, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F);

    // ダミーマテリアル: MineralArmorItemは動的属性を使うので防御値0
    public static final Holder<ArmorMaterial> MINERAL = Holder.direct(new ArmorMaterial(
            1,
            makeDefense(0, 0, 0, 0),
            12,
            SoundEvents.ARMOR_EQUIP_GENERIC,
            0.0F, 0.0F,
            ItemTags.REPAIRS_IRON_ARMOR,
            ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "mineral"))));

    private static Map<ArmorType, Integer> makeDefense(int boots, int leggings, int chestplate, int helmet) {
        EnumMap<ArmorType, Integer> map = new EnumMap<>(ArmorType.class);
        map.put(ArmorType.BOOTS, boots);
        map.put(ArmorType.LEGGINGS, leggings);
        map.put(ArmorType.CHESTPLATE, chestplate);
        map.put(ArmorType.HELMET, helmet);
        return map;
    }
}
