package net.pochi.pochimod.event;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.pochi.pochimod.attachment.ModAttachments;
import net.pochi.pochimod.nutrition.NutritionType;
import net.pochi.pochimod.nutrition.PlayerVitalData;
import net.pochi.pochimod.nutrition.SyncVitalDataPacket;

public class ClientPacketHandler {

    public static void handleSyncVitalData(SyncVitalDataPacket packet) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            PlayerVitalData vitalData = player.getData(ModAttachments.PLAYER_VITAL);
            // 水分設定
            vitalData.setHydration(packet.hydration());

            // 全栄養素を設定
            NutritionType[] types = NutritionType.getAllTypes();
            int[] nutritionValues = packet.nutritionValues();
            for (int i = 0; i < types.length && i < nutritionValues.length; i++) {
                vitalData.setNutrition(types[i], nutritionValues[i]);
            }
        }
    }
}
