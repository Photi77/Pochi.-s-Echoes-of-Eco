package net.pochi.pochimod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.pochi.pochimod.effect.ModEffects;

public class ModFoods {

    //農作物
    public static final FoodProperties VEGETABLE1 = (new FoodProperties.Builder()).fast().nutrition(1).saturationModifier(0.2F).build();

    public static final FoodProperties VEGETABLE2 = (new FoodProperties.Builder()).fast().nutrition(2).saturationModifier(0.2F).build();

    public static final FoodProperties VEGETABLE3 = (new FoodProperties.Builder()).fast().nutrition(3).saturationModifier(0.2F).build();

    public static final FoodProperties VEGETABLE4 = (new FoodProperties.Builder()).fast().nutrition(4).saturationModifier(0.2F).build();

    //frypanで作る食べ物
    public static final FoodProperties FRYPAN5 = (new FoodProperties.Builder()).fast().nutrition(5).saturationModifier(1.2F).build();

    public static final FoodProperties FRYPAN6 = (new FoodProperties.Builder()).fast().nutrition(6).saturationModifier(1.2F).build();

    public static final FoodProperties FRYPAN7 = (new FoodProperties.Builder()).fast().nutrition(7).saturationModifier(1.2F).build();

    public static final FoodProperties FRYPAN8 = (new FoodProperties.Builder()).fast().nutrition(8).saturationModifier(1.2F).build();

    //飲み物
    public static final FoodProperties NON = (new FoodProperties.Builder()).fast().build();
    public static final FoodProperties ARROW = (new FoodProperties.Builder()).fast().nutrition(1).effect(new MobEffectInstance(ModEffects.DROWNED, 6000, 0), 1.0F)
            .effect(new MobEffectInstance(ModEffects.ARROW, 6000, 0), 1.0F).build();
    public static final FoodProperties INV = (new FoodProperties.Builder()).fast().nutrition(1).effect(new MobEffectInstance(ModEffects.DROWNED, 6000, 0), 1.0F)
            .effect(new MobEffectInstance(ModEffects.INV, 6000, 0), 1.0F).build();
    public static final FoodProperties CRIME = (new FoodProperties.Builder()).fast().nutrition(1).effect(new MobEffectInstance(ModEffects.DROWNED, 6000, 0), 1.0F)
            .effect(new MobEffectInstance(ModEffects.CRIME, 6000, 0), 1.0F).build();
    public static final FoodProperties MAGNET = (new FoodProperties.Builder()).fast().nutrition(1).effect(new MobEffectInstance(ModEffects.DROWNED, 6000, 0), 1.0F)
            .effect(new MobEffectInstance(ModEffects.MAGNETIC, 6000, 0), 1.0F).build();
    public static final FoodProperties MOON = (new FoodProperties.Builder()).fast().nutrition(1).effect(new MobEffectInstance(ModEffects.DROWNED, 6000, 0), 1.0F)
            .effect(new MobEffectInstance(ModEffects.MOON_WALK, 6000, 0), 1.0F).build();
    public static final FoodProperties SHIELD = (new FoodProperties.Builder()).fast().nutrition(1).effect(new MobEffectInstance(ModEffects.DROWNED, 6000, 0), 1.0F)
            .effect(new MobEffectInstance(ModEffects.SHIELD, 6000, 0), 1.0F).build();
    public static final FoodProperties FIRE_RES = (new FoodProperties.Builder()).fast().nutrition(1).effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 2), 1.0F).build();
    public static final FoodProperties REGEN = (new FoodProperties.Builder()).fast().nutrition(1).effect(new MobEffectInstance(MobEffects.REGENERATION, 6000, 2), 1.0F).build();
    public static final FoodProperties DAMAGE_BOOST = (new FoodProperties.Builder()).fast().nutrition(1).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 6000, 2), 1.0F).build();
    public static final FoodProperties MOVE = (new FoodProperties.Builder()).fast().nutrition(1).effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 6000, 2), 1.0F).build();
    public static final FoodProperties DAMAGE_RES = (new FoodProperties.Builder()).fast().nutrition(1).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 2), 1.0F).build();
    public static final FoodProperties WATER_BREATH = (new FoodProperties.Builder()).fast().nutrition(1).effect(new MobEffectInstance(MobEffects.WATER_BREATHING, 6000, 2), 1.0F).build();
    public static final FoodProperties DIG = (new FoodProperties.Builder()).fast().nutrition(1).effect(new MobEffectInstance(MobEffects.DIG_SPEED, 6000, 2), 1.0F).build();
    public static final FoodProperties LUCK = (new FoodProperties.Builder()).fast().nutrition(1).effect(new MobEffectInstance(MobEffects.LUCK, 6000, 2), 1.0F).build();
    public static final FoodProperties HABU = (new FoodProperties.Builder()).fast().nutrition(1).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 6000, 2), 1.0F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 2), 1.0F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 6000, 2), 1.0F)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 6000, 2), 1.0F)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 6000, 2), 1.0F)
            .effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 6000, 2), 1.0F)
            .effect(new MobEffectInstance(ModEffects.DROWNED, 6000, 0), 1.0F).build();

}
