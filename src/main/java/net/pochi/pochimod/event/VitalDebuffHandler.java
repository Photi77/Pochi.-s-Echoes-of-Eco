package net.pochi.pochimod.event;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.attachment.ModAttachments;
import net.pochi.pochimod.config.VitalConfig;
import net.pochi.pochimod.nutrition.NutritionType;
import net.pochi.pochimod.nutrition.PlayerVitalData;
import net.pochi.pochimod.vital.VitalDeficiencyEffects;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * バイタル不足時のデバフ効果を適用
 */
@EventBusSubscriber(modid = PochiMod.MOD_ID)
public class VitalDebuffHandler {

    private static final Map<UUID, Integer> EFFECT_CHECK_COUNTERS = new HashMap<>();
    private static final int EFFECT_CHECK_INTERVAL = 100; // 5秒ごと
    private static final int EFFECT_DURATION = 120; // 6秒間

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (player.level().isClientSide) {
            return;
        }

        if (player.isCreative() || player.isSpectator()) {
            return;
        }

        UUID playerId = player.getUUID();
        int counter = EFFECT_CHECK_COUNTERS.getOrDefault(playerId, 0);
        counter++;

        if (counter >= EFFECT_CHECK_INTERVAL) {
            counter = 0;
            applyDeficiencyEffects(player);
        }

        EFFECT_CHECK_COUNTERS.put(playerId, counter);
    }

    private static void applyDeficiencyEffects(Player player) {
        if (!VitalConfig.ENABLE_DEFICIENCY_EFFECTS.get()) {
            return;
        }

        PlayerVitalData vitalData = player.getData(ModAttachments.PLAYER_VITAL);

        if (VitalConfig.ENABLE_HYDRATION_DEBUFFS.get()) {
            applyHydrationEffects(player, vitalData);
        }

        if (VitalConfig.ENABLE_NUTRITION_DEBUFFS.get()) {
            applyNutritionEffects(player, vitalData);
        }

        if (VitalConfig.ENABLE_EXCESS_EFFECTS.get()) {
            applyExcessEffects(player, vitalData);
        }
    }

    private static void applyHydrationEffects(Player player, PlayerVitalData vitalData) {
        int hydration = vitalData.getHydration();
        VitalDeficiencyEffects.DeficiencyLevel level = VitalDeficiencyEffects.DeficiencyLevel.fromValue(hydration);

        switch (level) {
            case MILD:
                addEffect(player, VitalDeficiencyEffects.HydrationEffects.getMildEffect(),
                        VitalDeficiencyEffects.HydrationEffects.getMildAmplifier());
                break;
            case MODERATE:
                addEffect(player, VitalDeficiencyEffects.HydrationEffects.getMildEffect(),
                        VitalDeficiencyEffects.HydrationEffects.getMildAmplifier());
                addEffect(player, VitalDeficiencyEffects.HydrationEffects.getModerateEffect(),
                        VitalDeficiencyEffects.HydrationEffects.getModerateAmplifier());
                break;
            case SEVERE:
                addEffect(player, VitalDeficiencyEffects.HydrationEffects.getMildEffect(),
                        VitalDeficiencyEffects.HydrationEffects.getMildAmplifier());
                addEffect(player, VitalDeficiencyEffects.HydrationEffects.getModerateEffect(),
                        VitalDeficiencyEffects.HydrationEffects.getModerateAmplifier());
                addEffect(player, VitalDeficiencyEffects.HydrationEffects.getSevereEffect(),
                        VitalDeficiencyEffects.HydrationEffects.getSevereAmplifier());
                break;
            default:
                break;
        }
    }

    private static void applyNutritionEffects(Player player, PlayerVitalData vitalData) {
        for (NutritionType type : NutritionType.getAllTypes()) {
            int nutritionValue = vitalData.getNutrition(type);
            VitalDeficiencyEffects.DeficiencyLevel level = VitalDeficiencyEffects.DeficiencyLevel.fromValue(nutritionValue);

            if (level != VitalDeficiencyEffects.DeficiencyLevel.NONE) {
                VitalDeficiencyEffects.NutritionDeficiencyEffect deficiency =
                        VitalDeficiencyEffects.getNutritionEffect(type);

                if (deficiency != null) {
                    Holder<MobEffect> effect = deficiency.getEffect(level);
                    int amplifier = deficiency.getAmplifier(level);

                    if (effect != null) {
                        addEffect(player, effect, amplifier);
                    }
                }
            }
        }
    }

    private static void applyExcessEffects(Player player, PlayerVitalData vitalData) {
        for (NutritionType type : NutritionType.getAllTypes()) {
            int nutritionValue = vitalData.getNutrition(type);
            VitalDeficiencyEffects.ExcessLevel level =
                    VitalDeficiencyEffects.ExcessLevel.fromValue(nutritionValue);

            if (level != VitalDeficiencyEffects.ExcessLevel.NONE) {
                VitalDeficiencyEffects.NutritionExcessEffect excess =
                        VitalDeficiencyEffects.getNutritionExcessEffect(type);

                if (excess != null) {
                    for (VitalDeficiencyEffects.EffectEntry entry : excess.getEffects(level)) {
                        addEffect(player, entry.effect(), entry.amplifier());
                    }
                }
            }
        }
    }

    private static void addEffect(Player player, net.minecraft.core.Holder<MobEffect> effect, int amplifier) {
        MobEffectInstance existingEffect = player.getEffect(effect);
        if (existingEffect == null || existingEffect.getAmplifier() < amplifier) {
            player.addEffect(new MobEffectInstance(effect, EFFECT_DURATION, amplifier, false, true, true));
        }
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        EFFECT_CHECK_COUNTERS.remove(event.getEntity().getUUID());
    }
}
