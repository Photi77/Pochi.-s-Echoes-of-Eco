package net.pochi.pochimod.item;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.pochi.pochimod.item.custom.weapon.PickaxeRod;
import net.pochi.pochimod.item.custom.weapon.PickaxeShoot;

public class ModItemProperties {

    public static void addCustomItemProperties() {
        cast(ModItems.PICKAXE_ROD.get());
        use(ModItems.PICKAXE_STICK.get());
    }
    private static void cast(Item item) {
        ItemProperties.register(ModItems.PICKAXE_ROD.get(), ResourceLocation.withDefaultNamespace("cast"), (p_174585_, p_174586_, p_174587_, p_174588_) -> {
            if (p_174587_ == null) {
                return 0.0F;
            } else {
                boolean flag = p_174587_.getMainHandItem() == p_174585_;
                boolean flag1 = p_174587_.getOffhandItem() == p_174585_;
                if (p_174587_.getMainHandItem().getItem() instanceof PickaxeItem) {
                    flag1 = false;
                }

                return PickaxeRod.isCast() ? 1.0F : 0.0F;
            }
        });
    }

    private static void use(Item item) {
        ItemProperties.register(ModItems.PICKAXE_STICK.get(), ResourceLocation.withDefaultNamespace("use"), (p_174585_, p_174586_, p_174587_, p_174588_) -> {
            if (p_174587_ == null) {
                return 0.0F;
            } else {
                return PickaxeShoot.isCast() ? 1.0F : 0.0F;
            }
        });
    }
}
