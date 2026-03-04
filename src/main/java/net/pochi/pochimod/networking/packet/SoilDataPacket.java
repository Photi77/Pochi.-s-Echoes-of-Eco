package net.pochi.pochimod.networking.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.pochi.pochimod.PochiMod;

public record SoilDataPacket(BlockPos pos, float n, float p, float k) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<SoilDataPacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "soil_data"));

    public static final StreamCodec<FriendlyByteBuf, SoilDataPacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC, SoilDataPacket::pos,
                    ByteBufCodecs.FLOAT, SoilDataPacket::n,
                    ByteBufCodecs.FLOAT, SoilDataPacket::p,
                    ByteBufCodecs.FLOAT, SoilDataPacket::k,
                    SoilDataPacket::new
            );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(SoilDataPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            SoilDataClientCache.update(packet.pos(), packet.n(), packet.p(), packet.k());
        });
    }
}
