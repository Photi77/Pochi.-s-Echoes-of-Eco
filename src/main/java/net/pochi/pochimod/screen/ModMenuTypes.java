package net.pochi.pochimod.screen;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.pochi.pochimod.PochiMod;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, PochiMod.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<BFurnaceMenu>> BFURNACE_MENU =
            registerMenuType(BFurnaceMenu::new, "bfurnace_menu");

    public static final DeferredHolder<MenuType<?>, MenuType<DistillerMenu>> DISTILLER_MENU =
            registerMenuType(DistillerMenu::new, "distiller_menu");

    public static final DeferredHolder<MenuType<?>, MenuType<SakeDaruMenu>> SAKEDARU_MENU =
            registerMenuType(SakeDaruMenu::new, "sakedaru_menu");

    public static final DeferredHolder<MenuType<?>, MenuType<FrypanMenu>> FRYPAN_MENU =
            registerMenuType(FrypanMenu::new, "frypan_menu");

    public static final DeferredHolder<MenuType<?>, MenuType<MixerMenu>> MIXER_MENU =
            registerMenuType(MixerMenu::new, "mixer_menu");

   //public static final DeferredHolder<MenuType<?>, MenuType<AlbatrossInventoryMenu>> ALBATROSS_INVENTORY =
   //        registerMenuType(AlbatrossInventoryMenu::new, "albatross_menu");

    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                  String name) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
