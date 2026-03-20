package net.pochi.pochimod.item.custom.tool;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.pochi.pochimod.attachment.ModAttachments;
import net.pochi.pochimod.nutrition.PlayerVitalData;
import net.pochi.pochimod.nutrition.VitalCheckScreen;

import java.util.function.Consumer;

/**
 * バイタルチェックマシン
 * 右クリックで現在のバイタルデータ（水分・栄養）を表示
 */
public class VitalCheckItem extends Item {

    public VitalCheckItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (level.isClientSide()) {
            openVitalCheckScreen(player);
        }

        return InteractionResult.SUCCESS;
    }


    private void openVitalCheckScreen(Player player) {
        PlayerVitalData vitalData = player.getData(ModAttachments.PLAYER_VITAL);
        Minecraft.getInstance().setScreen(new VitalCheckScreen(vitalData));
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, net.minecraft.world.item.component.TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, display, tooltip, flag);
        tooltip.accept(Component.translatable("item.yourmod.vital_check.tooltip.1")
                .withStyle(style -> style.withColor(0xAAAAAA)));
        tooltip.accept(Component.translatable("item.yourmod.vital_check.tooltip.2")
                .withStyle(style -> style.withColor(0xAAAAAA)));
    }
}
