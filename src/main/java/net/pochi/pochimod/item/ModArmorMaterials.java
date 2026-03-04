package net.pochi.pochimod.item;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.pochi.pochimod.PochiMod;

import java.util.EnumMap;
import java.util.List;

public class ModArmorMaterials {

    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> defense,
            int enchantmentValue, net.minecraft.core.Holder<net.minecraft.sounds.SoundEvent> equipSound,
            float toughness, float knockbackResistance,
            java.util.function.Supplier<Ingredient> repairIngredient) {
        return Registry.registerForHolder(
                BuiltInRegistries.ARMOR_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(net.pochi.pochimod.PochiMod.MOD_ID, name),
                new ArmorMaterial(defense, enchantmentValue, equipSound, repairIngredient,
                        List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(net.pochi.pochimod.PochiMod.MOD_ID, name))),
                        toughness, knockbackResistance));
    }

    public static final Holder<ArmorMaterial> LEOPA = register("leopa",
            Util.make(new EnumMap<>(ArmorItem.Type.class), m -> {
                m.put(ArmorItem.Type.BOOTS, 2); m.put(ArmorItem.Type.LEGGINGS, 5);
                m.put(ArmorItem.Type.CHESTPLATE, 6); m.put(ArmorItem.Type.HELMET, 2);
            }), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.FEATHER));

    public static final Holder<ArmorMaterial> ANCIENT = register("ancient",
            Util.make(new EnumMap<>(ArmorItem.Type.class), m -> {
                m.put(ArmorItem.Type.BOOTS, 2); m.put(ArmorItem.Type.LEGGINGS, 5);
                m.put(ArmorItem.Type.CHESTPLATE, 6); m.put(ArmorItem.Type.HELMET, 2);
            }), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.FEATHER));

    public static final Holder<ArmorMaterial> CASSOWARY = register("cassowary",
            Util.make(new EnumMap<>(ArmorItem.Type.class), m -> {
                m.put(ArmorItem.Type.BOOTS, 2); m.put(ArmorItem.Type.LEGGINGS, 5);
                m.put(ArmorItem.Type.CHESTPLATE, 6); m.put(ArmorItem.Type.HELMET, 2);
            }), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.FEATHER));

    public static final Holder<ArmorMaterial> EMU = register("emu",
            Util.make(new EnumMap<>(ArmorItem.Type.class), m -> {
                m.put(ArmorItem.Type.BOOTS, 2); m.put(ArmorItem.Type.LEGGINGS, 5);
                m.put(ArmorItem.Type.CHESTPLATE, 6); m.put(ArmorItem.Type.HELMET, 2);
            }), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.FEATHER));

    public static final Holder<ArmorMaterial> PERISO = register("periso",
            Util.make(new EnumMap<>(ArmorItem.Type.class), m -> {
                m.put(ArmorItem.Type.BOOTS, 2); m.put(ArmorItem.Type.LEGGINGS, 5);
                m.put(ArmorItem.Type.CHESTPLATE, 6); m.put(ArmorItem.Type.HELMET, 2);
            }), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.FEATHER));

    public static final Holder<ArmorMaterial> ROCK_PENGUIN = register("rock_penguin",
            Util.make(new EnumMap<>(ArmorItem.Type.class), m -> {
                m.put(ArmorItem.Type.BOOTS, 2); m.put(ArmorItem.Type.LEGGINGS, 5);
                m.put(ArmorItem.Type.CHESTPLATE, 6); m.put(ArmorItem.Type.HELMET, 2);
            }), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.FEATHER));

    public static final Holder<ArmorMaterial> CHROMITE = register("chromite",
            Util.make(new EnumMap<>(ArmorItem.Type.class), m -> {
                m.put(ArmorItem.Type.BOOTS, 2); m.put(ArmorItem.Type.LEGGINGS, 5);
                m.put(ArmorItem.Type.CHESTPLATE, 6); m.put(ArmorItem.Type.HELMET, 2);
            }), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(ModItems.CHROMITE_INGOT.get()));

    public static final Holder<ArmorMaterial> FLUORITE = register("fluorite",
            Util.make(new EnumMap<>(ArmorItem.Type.class), m -> {
                m.put(ArmorItem.Type.BOOTS, 1); m.put(ArmorItem.Type.LEGGINGS, 4);
                m.put(ArmorItem.Type.CHESTPLATE, 5); m.put(ArmorItem.Type.HELMET, 2);
            }), 35, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(ModItems.ROW_FLUORITE.get()));

    public static final Holder<ArmorMaterial> STAINLESS = register("stainless",
            Util.make(new EnumMap<>(ArmorItem.Type.class), m -> {
                m.put(ArmorItem.Type.BOOTS, 3); m.put(ArmorItem.Type.LEGGINGS, 6);
                m.put(ArmorItem.Type.CHESTPLATE, 8); m.put(ArmorItem.Type.HELMET, 3);
            }), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(ModItems.STAINLESS.get()));

    public static final Holder<ArmorMaterial> ALUMINIUM = register("aluminium",
            Util.make(new EnumMap<>(ArmorItem.Type.class), m -> {
                m.put(ArmorItem.Type.BOOTS, 1); m.put(ArmorItem.Type.LEGGINGS, 4);
                m.put(ArmorItem.Type.CHESTPLATE, 5); m.put(ArmorItem.Type.HELMET, 2);
            }), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(ModItems.ALUMINIUM_INGOT.get()));

    public static final Holder<ArmorMaterial> TITANIUM = register("titanium",
            Util.make(new EnumMap<>(ArmorItem.Type.class), m -> {
                m.put(ArmorItem.Type.BOOTS, 2); m.put(ArmorItem.Type.LEGGINGS, 5);
                m.put(ArmorItem.Type.CHESTPLATE, 6); m.put(ArmorItem.Type.HELMET, 2);
            }), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(ModItems.TITANIUM_INGOT.get()));

    public static final Holder<ArmorMaterial> MAGUNESIUM = register("magunesium",
            Util.make(new EnumMap<>(ArmorItem.Type.class), m -> {
                m.put(ArmorItem.Type.BOOTS, 1); m.put(ArmorItem.Type.LEGGINGS, 4);
                m.put(ArmorItem.Type.CHESTPLATE, 5); m.put(ArmorItem.Type.HELMET, 2);
            }), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(ModItems.MAGUNESIUM_INGOT.get()));

    public static final Holder<ArmorMaterial> VANADIUM = register("vanadium",
            Util.make(new EnumMap<>(ArmorItem.Type.class), m -> {
                m.put(ArmorItem.Type.BOOTS, 1); m.put(ArmorItem.Type.LEGGINGS, 4);
                m.put(ArmorItem.Type.CHESTPLATE, 5); m.put(ArmorItem.Type.HELMET, 2);
            }), 12, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(ModItems.VANADIUM_INGOT.get()));

    public static final Holder<ArmorMaterial> DURALUMIN = register("duralumin",
            Util.make(new EnumMap<>(ArmorItem.Type.class), m -> {
                m.put(ArmorItem.Type.BOOTS, 4); m.put(ArmorItem.Type.LEGGINGS, 7);
                m.put(ArmorItem.Type.CHESTPLATE, 9); m.put(ArmorItem.Type.HELMET, 4);
            }), 5, SoundEvents.ARMOR_EQUIP_IRON, 3.0F, 0.1F, () -> Ingredient.of(ModItems.DURALUMIN.get()));

    public static final Holder<ArmorMaterial> TITAN_ALLOY = register("titan_alloy",
            Util.make(new EnumMap<>(ArmorItem.Type.class), m -> {
                m.put(ArmorItem.Type.BOOTS, 3); m.put(ArmorItem.Type.LEGGINGS, 6);
                m.put(ArmorItem.Type.CHESTPLATE, 8); m.put(ArmorItem.Type.HELMET, 3);
            }), 9, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.0F, () -> Ingredient.of(ModItems.TITANIUM_INGOT.get()));

    public static final Holder<ArmorMaterial> ELECTRON = register("electron",
            Util.make(new EnumMap<>(ArmorItem.Type.class), m -> {
                m.put(ArmorItem.Type.BOOTS, 2); m.put(ArmorItem.Type.LEGGINGS, 5);
                m.put(ArmorItem.Type.CHESTPLATE, 6); m.put(ArmorItem.Type.HELMET, 2);
            }), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(ModItems.ELECTRON.get()));

    public static final Holder<ArmorMaterial> VANADIUM_ALLOY = register("vanadium_alloy",
            Util.make(new EnumMap<>(ArmorItem.Type.class), m -> {
                m.put(ArmorItem.Type.BOOTS, 2); m.put(ArmorItem.Type.LEGGINGS, 5);
                m.put(ArmorItem.Type.CHESTPLATE, 6); m.put(ArmorItem.Type.HELMET, 2);
            }), 15, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(ModItems.VANADIUM_ALLOY.get()));

    // ダミーマテリアル: MineralArmorItemは動的属性を使うので防御値0
    // dyeable=true にすることで HumanoidArmorLayer が DyeableLeatherItem.getColor() を呼び出し、
    // NBT から計算した鉱物カラーを装着時テクスチャに乗算する
    public static final Holder<ArmorMaterial> MINERAL = Registry.registerForHolder(
            BuiltInRegistries.ARMOR_MATERIAL,
            ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "mineral"),
            new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), m -> {
                        m.put(ArmorItem.Type.BOOTS, 0); m.put(ArmorItem.Type.LEGGINGS, 0);
                        m.put(ArmorItem.Type.CHESTPLATE, 0); m.put(ArmorItem.Type.HELMET, 0);
                    }),
                    12, SoundEvents.ARMOR_EQUIP_GENERIC, () -> Ingredient.EMPTY,
                    List.of(new ArmorMaterial.Layer(
                            ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "mineral"),
                            "",
                            true
                    )),
                    0.0F, 0.0F
            ));
}
