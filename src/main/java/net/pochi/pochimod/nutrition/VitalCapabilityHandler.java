package net.pochi.pochimod.nutrition;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.attachment.ModAttachments;
import net.pochi.pochimod.networking.ModMessages;

/**
 * バイタルデータの同期イベント処理
 * データ保持はAttachment APIで行うため、AttachCapabilitiesEvent等は不要
 */
@EventBusSubscriber(modid = PochiMod.MOD_ID)
public class VitalCapabilityHandler {

    /**
     * プレイヤーログイン時にクライアントへ同期
     */
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            syncToClient(serverPlayer);
        }
    }

    /**
     * ディメンション変更時にクライアントへ同期
     */
    @SubscribeEvent
    public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            syncToClient(serverPlayer);
        }
    }

    /**
     * リスポーン時にクライアントへ同期
     */
    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            syncToClient(serverPlayer);
        }
    }

    /**
     * クライアントへバイタルデータを同期
     */
    private static void syncToClient(ServerPlayer player) {
        PlayerVitalData vitalData = player.getData(ModAttachments.PLAYER_VITAL);
        ModMessages.sendToPlayer(new SyncVitalDataPacket(vitalData), player);
    }

    /**
     * プレイヤーからバイタルデータを取得（ヘルパーメソッド）
     */
    public static PlayerVitalData getVitalData(Player player) {
        return player.getData(ModAttachments.PLAYER_VITAL);
    }
}
