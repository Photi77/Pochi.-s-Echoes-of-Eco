package net.pochi.pochimod.item.food;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.nutrition.FoodNutritionHelper;
import net.pochi.pochimod.nutrition.FoodNutritionRegistry;
import net.pochi.pochimod.nutrition.NutritionType;

// アイテムクラス or ItemEventHandler に追加
@EventBusSubscriber(modid = PochiMod.MOD_ID)
public class TooltipEventHandler {

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();

        // NBTが存在するか先にチェック
        if (!stack.has(net.minecraft.core.component.DataComponents.CUSTOM_DATA)) return;
        net.minecraft.nbt.CompoundTag _tag = stack.get(net.minecraft.core.component.DataComponents.CUSTOM_DATA).copyTag();
        if (!_tag.contains(FoodQualityHelper.NBT_KEY)) return;

        float quality = FoodQualityHelper.getQuality(stack);
        FoodQualityHelper.QualityRank rank = FoodQualityHelper.getRank(quality);

        event.getToolTip().add(
                Component.literal("品質: " + rank.label +
                                String.format(" (%.2f)", quality))
                        .withStyle(style -> style.withColor(rank.color))
        );
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();

        // NBT栄養値があれば表示
        FoodNutritionHelper.getNutrition(stack).ifPresent(data -> {
            event.getToolTip().add(Component.literal("─── 栄養素 ───")
                    .withStyle(ChatFormatting.DARK_GRAY));

            for (NutritionType type : NutritionType.values()) {
                int value = data.getNutrition(type);
                if (value > 0) {
                    event.getToolTip().add(
                            Component.literal("  ")
                                    .append(type.getDisplayName())
                                    .append(Component.literal(": " + value)
                                            .withStyle(ChatFormatting.WHITE))
                    );
                }
            }

            int hydration = data.getHydration();
            if (hydration > 0) {
                event.getToolTip().add(
                        Component.literal("  水分: " + hydration)
                                .withStyle(ChatFormatting.AQUA)
                );
            }
        });

        // NBTがない場合はRegistryの固定値を表示（未収穫アイテムなど）
        if (!FoodNutritionHelper.hasNutrition(stack)) {
            FoodNutritionRegistry.getNutritionData(stack.getItem()).ifPresent(data -> {
                // 同様の表示（グレーアウトなど差別化してもよい）
            });
        }
    }
}
