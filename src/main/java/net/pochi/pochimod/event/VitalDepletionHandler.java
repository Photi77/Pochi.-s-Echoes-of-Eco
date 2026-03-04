package net.pochi.pochimod.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.attachment.ModAttachments;
import net.pochi.pochimod.config.VitalConfig;
import net.pochi.pochimod.networking.ModMessages;
import net.pochi.pochimod.nutrition.NutritionType;
import net.pochi.pochimod.nutrition.PlayerVitalData;
import net.pochi.pochimod.nutrition.SyncVitalDataPacket;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * バイタル値の時間経過減少処理
 */
@EventBusSubscriber(modid = PochiMod.MOD_ID)
public class VitalDepletionHandler {

    private static final Map<UUID, Integer> PLAYER_TICK_COUNTERS = new HashMap<>();

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
        int tickCounter = PLAYER_TICK_COUNTERS.getOrDefault(playerId, 0);
        tickCounter++;

        int depletionInterval = VitalConfig.DEPLETION_INTERVAL.get();

        if (tickCounter >= depletionInterval) {
            tickCounter = 0;
            depleteVitals(player);
        }

        PLAYER_TICK_COUNTERS.put(playerId, tickCounter);
    }

    private static void depleteVitals(Player player) {
        PlayerVitalData vitalData = player.getData(ModAttachments.PLAYER_VITAL);
        float difficultyMultiplier = getDifficultyMultiplier(player);

        // 水分減少
        int hydrationDepletion = (int) (VitalConfig.HYDRATION_DEPLETION_RATE.get() * difficultyMultiplier);
        vitalData.consumeHydration(hydrationDepletion);

        // 全栄養素を均等に減少
        int nutritionDepletion = (int) (VitalConfig.NUTRITION_DEPLETION_RATE.get() * difficultyMultiplier);
        for (NutritionType type : NutritionType.getAllTypes()) {
            vitalData.consumeNutrition(type, nutritionDepletion);
        }

        // クライアントに同期
        if (player instanceof ServerPlayer serverPlayer) {
            ModMessages.sendToPlayer(new SyncVitalDataPacket(vitalData), serverPlayer);
        }
    }

    private static float getDifficultyMultiplier(Player player) {
        if (!VitalConfig.SCALE_WITH_DIFFICULTY.get()) {
            return 1.0f;
        }

        Difficulty difficulty = player.level().getDifficulty();
        return switch (difficulty) {
            case PEACEFUL -> 0.5f;
            case EASY -> 1.0f;
            case NORMAL -> 1.5f;
            case HARD -> 2.0f;
        };
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        PLAYER_TICK_COUNTERS.remove(event.getEntity().getUUID());
    }
}
