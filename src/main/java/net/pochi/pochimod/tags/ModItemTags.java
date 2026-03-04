package net.pochi.pochimod.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.pochi.pochimod.PochiMod;

public class ModItemTags {
    public static class Items{
        public static final TagKey<Item> CABERNET_SAUVIGNON_LOGS = tag("cabernet_sauvignon_logs");
        public static final TagKey<Item> MAPLE_LOGS = tag("maple_logs");
        public static final TagKey<Item> CINNAMON_LOGS = tag("cinnamon_logs");
        public static final TagKey<Item> LEMON_LOGS = tag("lemon_logs");
        public static final TagKey<Item> COLA_LOGS = tag("cola_logs");
        public static final TagKey<Item> PLUM_LOGS = tag("plum_logs");
        public static final TagKey<Item> CHERRY_LOGS = tag("cherry_logs");
        public static final TagKey<Item> BANANA_LOGS = tag("banana_logs");
        public static final TagKey<Item> PEACH_LOGS = tag("peach_logs");
        public static final TagKey<Item> COCONUT_LOGS = tag("coconut_logs");
        public static final TagKey<Item> ALMOND_LOGS = tag("almond_logs");
        public static final TagKey<Item> DURIAN_LOGS = tag("durian_logs");
        public static final TagKey<Item> COFFEE_LOGS = tag("coffee_logs");
        public static final TagKey<Item> KIWI_LOGS = tag("kiwi_logs");
    }

    private static TagKey<Item> tag(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, name));
    }

    public static TagKey<Item> forgeTag(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", name));
    }

}
