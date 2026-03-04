package net.pochi.pochimod.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.pochi.pochimod.attachment.ModAttachments;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.attachment.ModAttachments;
import net.pochi.pochimod.config.ActionConsumptionConfig;
import net.pochi.pochimod.attachment.ModAttachments;
import net.pochi.pochimod.config.VitalConfig;
import net.pochi.pochimod.attachment.ModAttachments;
import net.pochi.pochimod.networking.ModMessages;
import net.pochi.pochimod.attachment.ModAttachments;
import net.pochi.pochimod.nutrition.NutritionType;
import net.pochi.pochimod.attachment.ModAttachments;
import net.pochi.pochimod.nutrition.PlayerVitalData;
import net.pochi.pochimod.nutrition.SyncVitalDataPacket;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * プレイヤーの行動に応じた栁E��消費�E�デバッグ版！E
 */
@EventBusSubscriber(modid = PochiMod.MOD_ID)
public class ActionBasedConsumptionHandler {

    private static final Map<UUID, ActionCounters> PLAYER_COUNTERS = new HashMap<>();

    // チE��チE��ログの頻度制御
    private static final int DEBUG_LOG_INTERVAL = 200; // 10秒ごとにログ出劁E

    private static class ActionCounters {
        int sprintNutritionTicks = 0;
        int sprintHydrationTicks = 0;
        int swimNutritionTicks = 0;
        int swimHydrationTicks = 0;
        int climbNutritionTicks = 0;
        int jumpCount = 0;
        int blockBreakCount = 0;
        int attackCount = 0;

        // チE��チE��用
        int debugLogCounter = 0;
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (player.level().isClientSide || player.isCreative() || player.isSpectator()) {
            return;
        }

        if (!VitalConfig.ENABLE_ACTION_CONSUMPTION.get()) {
            return;
        }

        UUID playerId = player.getUUID();
        ActionCounters counters = PLAYER_COUNTERS.computeIfAbsent(playerId, k -> new ActionCounters());

        boolean needsSync = false;

        // ===== ダチE��ュ =====
        if (player.isSprinting()) {
            // 栁E��消費カウンター�E�独立！E
            counters.sprintNutritionTicks++;
            int nutritionInterval = VitalConfig.SPRINT_NUTRITION_INTERVAL.get();

            if (counters.sprintNutritionTicks >= nutritionInterval) {
                PochiMod.LOGGER.debug("Sprint nutrition consumed at tick: {}", counters.sprintNutritionTicks);
                counters.sprintNutritionTicks = 0; // リセチE��
                needsSync = consumeNutrition(player, ActionConsumptionConfig.getSprintNutritionConsumption());
            }

            // 水刁E��費カウンター�E�独立！E
            counters.sprintHydrationTicks++;
            int hydrationInterval = VitalConfig.SPRINT_HYDRATION_INTERVAL.get();

            if (counters.sprintHydrationTicks >= hydrationInterval) {
                PochiMod.LOGGER.info("Sprint hydration consumed at tick: {} (interval: {})",
                        counters.sprintHydrationTicks, hydrationInterval);
                counters.sprintHydrationTicks = 0; // リセチE��
                int cost = VitalConfig.SPRINT_HYDRATION_COST.get();
                needsSync |= consumeHydration(player, cost);
            }

            // チE��チE��ログ�E�E0秒ごと�E�E
            counters.debugLogCounter++;
            if (counters.debugLogCounter >= DEBUG_LOG_INTERVAL) {
                PochiMod.LOGGER.info("Sprint Status - Nutrition Ticks: {}/{}, Hydration Ticks: {}/{}",
                        counters.sprintNutritionTicks, nutritionInterval,
                        counters.sprintHydrationTicks, hydrationInterval);
                counters.debugLogCounter = 0;
            }
        }
        //else {
        //    // ダチE��ュしてぁE��ぁE��は両方のカウンターをリセチE��
        //    if (counters.sprintNutritionTicks > 0 || counters.sprintHydrationTicks > 0) {
        //        PochiMod.LOGGER.debug("Sprint stopped - Resetting counters");
        //        counters.sprintNutritionTicks = 0;
        //        counters.sprintHydrationTicks = 0;
        //        counters.debugLogCounter = 0;
        //    }
        //}

        // ===== 水泳 =====
        if (player.isSwimming()) {
            // 栁E��消費カウンター�E�独立！E
            counters.swimNutritionTicks++;
            int nutritionInterval = VitalConfig.SWIM_NUTRITION_INTERVAL.get();

            if (counters.swimNutritionTicks >= nutritionInterval) {
                PochiMod.LOGGER.debug("Swim nutrition consumed at tick: {}", counters.swimNutritionTicks);
                counters.swimNutritionTicks = 0; // リセチE��
                needsSync = consumeNutrition(player, ActionConsumptionConfig.getSwimNutritionConsumption());
            }

            // 水刁E��費カウンター�E�独立！E
            counters.swimHydrationTicks++;
            int hydrationInterval = VitalConfig.SWIM_HYDRATION_INTERVAL.get();

            if (counters.swimHydrationTicks >= hydrationInterval) {
                PochiMod.LOGGER.info("Swim hydration consumed at tick: {} (interval: {})",
                        counters.swimHydrationTicks, hydrationInterval);
                counters.swimHydrationTicks = 0; // リセチE��
                int cost = VitalConfig.SWIM_HYDRATION_COST.get();
                needsSync |= consumeHydration(player, cost);
            }
        }
        //else {
        //    // 水泳してぁE��ぁE��は両方のカウンターをリセチE��
        //    if (counters.swimNutritionTicks > 0 || counters.swimHydrationTicks > 0) {
        //        counters.swimNutritionTicks = 0;
        //        counters.swimHydrationTicks = 0;
        //    }
        //}

        // ===== クライミング =====
        if (player.onClimbable()) {
            counters.climbNutritionTicks++;
            int interval = VitalConfig.CLIMB_NUTRITION_INTERVAL.get();

            if (counters.climbNutritionTicks >= interval) {
                PochiMod.LOGGER.debug("Climb nutrition consumed at tick: {}", counters.climbNutritionTicks);
                counters.climbNutritionTicks = 0; // リセチE��
                needsSync = consumeNutrition(player, ActionConsumptionConfig.getClimbNutritionConsumption());
            }
        } else {
            if (counters.climbNutritionTicks > 0) {
                counters.climbNutritionTicks = 0;
            }
        }

        if (needsSync && player instanceof ServerPlayer serverPlayer) {
            syncVitalData(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onLivingJump(LivingEvent.LivingJumpEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        if (player.level().isClientSide || player.isCreative() || player.isSpectator()) {
            return;
        }

        if (!VitalConfig.ENABLE_ACTION_CONSUMPTION.get()) {
            return;
        }

        UUID playerId = player.getUUID();
        ActionCounters counters = PLAYER_COUNTERS.computeIfAbsent(playerId, k -> new ActionCounters());

        counters.jumpCount++;
        int threshold = VitalConfig.JUMP_COUNTER_THRESHOLD.get();

        if (counters.jumpCount >= threshold) {
            PochiMod.LOGGER.debug("Jump nutrition consumed after {} jumps (threshold: {})",
                    counters.jumpCount, threshold);
            counters.jumpCount = 0; // リセチE��

            if (consumeNutrition(player, ActionConsumptionConfig.getJumpNutritionConsumption())) {
                if (player instanceof ServerPlayer serverPlayer) {
                    syncVitalData(serverPlayer);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();

        if (player.level().isClientSide || player.isCreative() || player.isSpectator()) {
            return;
        }

        if (!VitalConfig.ENABLE_ACTION_CONSUMPTION.get()) {
            return;
        }

        UUID playerId = player.getUUID();
        ActionCounters counters = PLAYER_COUNTERS.computeIfAbsent(playerId, k -> new ActionCounters());

        counters.blockBreakCount++;
        int threshold = VitalConfig.BLOCK_BREAK_COUNTER_THRESHOLD.get();

        if (counters.blockBreakCount >= threshold) {
            PochiMod.LOGGER.debug("Block break nutrition consumed after {} block (threshold: {})",
                    counters.blockBreakCount, threshold);
            counters.blockBreakCount = 0; // リセチE��

            if (consumeNutrition(player, ActionConsumptionConfig.getBlockBreakNutritionConsumption())) {
                if (player instanceof ServerPlayer serverPlayer) {
                    syncVitalData(serverPlayer);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingAttack(LivingIncomingDamageEvent event) {
        // 攻撁E��た�E
        if (event.getSource().getEntity() instanceof Player player) {
            if (player.level().isClientSide || player.isCreative() || player.isSpectator()) {
                return;
            }

            if (!VitalConfig.ENABLE_ACTION_CONSUMPTION.get()) {
                return;
            }

            UUID playerId = player.getUUID();
            ActionCounters counters = PLAYER_COUNTERS.computeIfAbsent(playerId, k -> new ActionCounters());

            counters.attackCount++;
            int threshold = VitalConfig.ATTACK_COUNTER_THRESHOLD.get();

            if (counters.attackCount >= threshold) {
                PochiMod.LOGGER.debug("Attack nutrition consumed after {} attacks (threshold: {})",
                        counters.attackCount, threshold);
                counters.attackCount = 0; // リセチE��

                if (consumeNutrition(player, ActionConsumptionConfig.getAttackNutritionConsumption())) {
                    if (player instanceof ServerPlayer serverPlayer) {
                        syncVitalData(serverPlayer);
                    }
                }
            }
        }

        // ダメージを受けた側
        if (event.getEntity() instanceof Player player) {
            if (player.level().isClientSide || player.isCreative() || player.isSpectator()) {
                return;
            }

            if (!VitalConfig.ENABLE_ACTION_CONSUMPTION.get()) {
                return;
            }

            PochiMod.LOGGER.debug("Damage nutrition consumed");

            if (consumeNutrition(player, ActionConsumptionConfig.getDamageNutritionConsumption())) {
                if (player instanceof ServerPlayer serverPlayer) {
                    syncVitalData(serverPlayer);
                }
            }
        }
    }

    private static boolean consumeNutrition(Player player, Map<NutritionType, Integer> consumption) {
        if (consumption.isEmpty()) {
            return false;
        }

        PlayerVitalData vitalData = player.getData(ModAttachments.PLAYER_VITAL);
        boolean consumed = false;
        for (Map.Entry<NutritionType, Integer> entry : consumption.entrySet()) {
            NutritionType type = entry.getKey();
            int amount = entry.getValue();

            if (amount > 0) {
                int before = vitalData.getNutrition(type);
                vitalData.consumeNutrition(type, amount);
                int after = vitalData.getNutrition(type);
                PochiMod.LOGGER.debug("Consumed {} {}: {} -> {}", amount, type.getName(), before, after);
                consumed = true;
            }
        }
        return consumed;
    }

    private static boolean consumeHydration(Player player, int amount) {
        if (amount <= 0) {
            return false;
        }

        PlayerVitalData vitalData = player.getData(ModAttachments.PLAYER_VITAL);
        int before = vitalData.getHydration();
        vitalData.consumeHydration(amount);
        int after = vitalData.getHydration();
        PochiMod.LOGGER.info("Consumed {} hydration: {} -> {}", amount, before, after);
        return true;
    }

    private static void syncVitalData(ServerPlayer player) {
        PlayerVitalData vitalData = player.getData(ModAttachments.PLAYER_VITAL);
        ModMessages.sendToPlayer(new SyncVitalDataPacket(vitalData), player);
    }

    @SubscribeEvent
    public static void onPlayerLogout(net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent event) {
        PLAYER_COUNTERS.remove(event.getEntity().getUUID());
    }
}


