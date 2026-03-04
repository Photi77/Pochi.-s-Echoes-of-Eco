package net.pochi.pochimod.nutrition;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.event.ClientPacketHandler;

/**
 * サーバーからクライアントへバイタルデータを同期するパケット
 */
public record SyncVitalDataPacket(int hydration, int[] nutritionValues) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<SyncVitalDataPacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "sync_vital_data"));

    public static final StreamCodec<FriendlyByteBuf, SyncVitalDataPacket> STREAM_CODEC =
            new StreamCodec<>() {
                @Override
                public SyncVitalDataPacket decode(FriendlyByteBuf buf) {
                    int hydration = buf.readInt();
                    int length = buf.readInt();
                    int[] values = new int[length];
                    for (int i = 0; i < length; i++) values[i] = buf.readInt();
                    return new SyncVitalDataPacket(hydration, values);
                }

                @Override
                public void encode(FriendlyByteBuf buf, SyncVitalDataPacket packet) {
                    buf.writeInt(packet.hydration());
                    buf.writeInt(packet.nutritionValues().length);
                    for (int v : packet.nutritionValues()) buf.writeInt(v);
                }
            };

    public SyncVitalDataPacket(PlayerVitalData vitalData) {
        this(vitalData.getHydration(), buildNutritionValues(vitalData));
    }

    private static int[] buildNutritionValues(PlayerVitalData vitalData) {
        NutritionType[] types = NutritionType.getAllTypes();
        int[] values = new int[types.length];
        for (int i = 0; i < types.length; i++) {
            values[i] = vitalData.getNutrition(types[i]);
        }
        return values;
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(SyncVitalDataPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (FMLEnvironment.dist == Dist.CLIENT) {
                ClientPacketHandler.handleSyncVitalData(packet);
            }
        });
    }
}
