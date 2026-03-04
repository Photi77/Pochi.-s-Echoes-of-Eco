package net.pochi.pochimod.networking;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.networking.packet.EnergySyncS2CPacket;
import net.pochi.pochimod.networking.packet.FluidSyncS2CPacket;
import net.pochi.pochimod.networking.packet.RhinoChargePacket;
import net.pochi.pochimod.networking.packet.SoilDataPacket;
import net.pochi.pochimod.nutrition.SyncVitalDataPacket;

@EventBusSubscriber(modid = PochiMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModMessages {

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");

        registrar.playToClient(
                EnergySyncS2CPacket.TYPE,
                EnergySyncS2CPacket.STREAM_CODEC,
                EnergySyncS2CPacket::handle);

        registrar.playToClient(
                FluidSyncS2CPacket.TYPE,
                FluidSyncS2CPacket.STREAM_CODEC,
                FluidSyncS2CPacket::handle);

        registrar.playToServer(
                RhinoChargePacket.TYPE,
                RhinoChargePacket.STREAM_CODEC,
                RhinoChargePacket::handle);

        registrar.playToClient(
                SyncVitalDataPacket.TYPE,
                SyncVitalDataPacket.STREAM_CODEC,
                SyncVitalDataPacket::handle);

        registrar.playToClient(
                SoilDataPacket.TYPE,
                SoilDataPacket.STREAM_CODEC,
                SoilDataPacket::handle);
    }

    /** サーバーへ送信（クライアント→サーバー） */
    public static <MSG> void sendToServer(MSG message) {
        PacketDistributor.sendToServer((net.minecraft.network.protocol.common.custom.CustomPacketPayload) message);
    }

    /** 特定プレイヤーへ送信（サーバー→クライアント） */
    public static <MSG extends net.minecraft.network.protocol.common.custom.CustomPacketPayload> void sendToPlayer(MSG message, ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, message);
    }

    /** 旧 regiser() 呼び出し互換 — 登録は @SubscribeEvent で行うため何もしない */
    public static void regiser() {
        // 登録は RegisterPayloadHandlersEvent で自動的に行われる
    }
}
