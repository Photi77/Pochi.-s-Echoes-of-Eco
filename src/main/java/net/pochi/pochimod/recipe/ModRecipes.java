package net.pochi.pochimod.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.mineral.tools.MineralCraftingRecipe;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, PochiMod.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<BFurnaceRecipe>> BFURNACE_SERIALIZER =
            SERIALIZERS.register("bfurnace", () -> BFurnaceRecipe.Serializer.INSTANCE);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<DistillerRecipe>> DISTILLER_SERIALIZER =
            SERIALIZERS.register("distiller", () -> DistillerRecipe.Serializer.INSTANCE);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<SakeDaruRecipe>> SAKEADARU_SERIALIZER =
            SERIALIZERS.register("sakedaru", () -> SakeDaruRecipe.Serializer.INSTANCE);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<FrypanRecipe>> FRYPAN_SERIALIZER =
            SERIALIZERS.register("frypan", () -> FrypanRecipe.Serializer.INSTANCE);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<MixerRecipe>> MIXER_SERIALIZER =
            SERIALIZERS.register("mixer", () -> MixerRecipe.Serializer.INSTANCE);

    public static final DeferredHolder<RecipeSerializer<?>, QualityShapelessRecipe.Serializer> QUALITY_SHAPELESS =
            SERIALIZERS.register("quality_shapeless",
                    QualityShapelessRecipe.Serializer::new);



    public static final DeferredHolder<RecipeSerializer<?>, MineralCraftingRecipe.Serializer> MINERAL_SHAPED =
            SERIALIZERS.register("mineral_shaped",
                    MineralCraftingRecipe.Serializer::new);



    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }


}
