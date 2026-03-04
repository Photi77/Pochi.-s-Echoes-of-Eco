package net.pochi.pochimod.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.attachment.ModAttachments;
import net.pochi.pochimod.item.food.FoodQualityHelper;
import net.pochi.pochimod.networking.ModMessages;
import net.pochi.pochimod.nutrition.*;

@EventBusSubscriber(modid = PochiMod.MOD_ID)
public class FoodEatEventHandler {

    @SubscribeEvent
    public static void onItemUseFinish(LivingEntityUseItemEvent.Finish event) {
        if (!(event.getEntity() instanceof Player player)) return;

        ItemStack stack = event.getItem();
        Item item = stack.getItem();

        if (!stack.has(net.minecraft.core.component.DataComponents.FOOD)) return;

        // NBT栄養値を優先、なければRegistry品質係数にフォールバック
        FoodNutritionData finalData = FoodNutritionHelper.getNutrition(stack)
                .orElseGet(() ->
                        FoodNutritionRegistry.getNutritionData(item)
                                .map(base -> base.scaled(FoodQualityHelper.getQuality(stack)))
                                .orElse(null)
                );

        if (finalData == null) return;

        PlayerVitalData vitalData = player.getData(ModAttachments.PLAYER_VITAL);
        for (NutritionType type : NutritionType.getAllTypes()) {
            int value = finalData.getNutrition(type);
            if (value > 0) vitalData.addNutrition(type, value);
        }

        int hydration = finalData.getHydration();
        if (hydration > 0) vitalData.addHydration(hydration);

        if (!player.level().isClientSide && player instanceof ServerPlayer serverPlayer) {
            ModMessages.sendToPlayer(new SyncVitalDataPacket(vitalData), serverPlayer);
        }
    }
}
