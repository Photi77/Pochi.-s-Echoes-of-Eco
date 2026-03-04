package net.pochi.pochimod.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.Rhino;

public record RhinoChargePacket() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<RhinoChargePacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "rhino_charge"));

    public static final StreamCodec<FriendlyByteBuf, RhinoChargePacket> STREAM_CODEC =
            StreamCodec.unit(new RhinoChargePacket());

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(RhinoChargePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            if (player.getVehicle() instanceof Rhino rhino) {
                if (rhino.chargeCooldownTimer <= 0 && !rhino.isCharging()) {
                    rhino.startCharge();
                } else {
                    player.displayClientMessage(
                            Component.translatable("entity.rhino.charge_cooldown"),
                            true
                    );
                }
            }
        });
    }
}
