package net.pochi.pochimod.item.custom.tool;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.pochi.pochimod.attachment.ModAttachments;
import net.pochi.pochimod.nutrition.PlayerVitalData;
import net.pochi.pochimod.nutrition.VitalCheckScreen;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * バイタルチェックマシン
 * 右クリックで現在のバイタルデータ（水分・栄養）を表示
 */
public class VitalCheckItem extends Item {

    public VitalCheckItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (level.isClientSide) {
            openVitalCheckScreen(player);
        }

        return InteractionResultHolder.success(itemStack);
    }

    @OnlyIn(Dist.CLIENT)
    private void openVitalCheckScreen(Player player) {
        PlayerVitalData vitalData = player.getData(ModAttachments.PLAYER_VITAL);
        Minecraft.getInstance().setScreen(new VitalCheckScreen(vitalData));
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable("item.yourmod.vital_check.tooltip.1")
                .withStyle(style -> style.withColor(0xAAAAAA)));
        tooltip.add(Component.translatable("item.yourmod.vital_check.tooltip.2")
                .withStyle(style -> style.withColor(0xAAAAAA)));
    }
}
