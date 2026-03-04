package net.pochi.pochimod.networking.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.pochi.pochimod.PochiMod;

public record FluidSyncS2CPacket(FluidStack fluidStack, BlockPos pos) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<FluidSyncS2CPacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "fluid_sync"));


    public static final StreamCodec<RegistryFriendlyByteBuf, FluidSyncS2CPacket> STREAM_CODEC =
            StreamCodec.composite(
                    FluidStack.STREAM_CODEC,
                    FluidSyncS2CPacket::fluidStack,
                    BlockPos.STREAM_CODEC,
                    FluidSyncS2CPacket::pos,
                    FluidSyncS2CPacket::new
            );
    //public static final StreamCodec<FriendlyByteBuf, FluidSyncS2CPacket> STREAM_CODEC =
    //        StreamCodec.composite(
    //                FluidStack.STREAM_CODEC, FluidSyncS2CPacket::fluidStack,
    //                BlockPos.STREAM_CODEC, FluidSyncS2CPacket::pos,
    //                FluidSyncS2CPacket::new
    //        );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(FluidSyncS2CPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            // クライアント側での処理（必要に応じて追加）
        });
    }
}
