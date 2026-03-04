package net.pochi.pochimod.networking.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.pochi.pochimod.PochiMod;

public record EnergySyncS2CPacket(int energy, BlockPos pos) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<EnergySyncS2CPacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "energy_sync"));

    public static final StreamCodec<FriendlyByteBuf, EnergySyncS2CPacket> STREAM_CODEC =
            StreamCodec.composite(
                    net.minecraft.network.codec.ByteBufCodecs.INT, EnergySyncS2CPacket::energy,
                    BlockPos.STREAM_CODEC, EnergySyncS2CPacket::pos,
                    EnergySyncS2CPacket::new
            );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(EnergySyncS2CPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            // クライアント側での処理（必要に応じて追加）
        });
    }
}
