package net.pochi.pochimod.ferm;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.item.custom.CompostItem;

@EventBusSubscriber(modid = PochiMod.MOD_ID)
public class CompostUseEventHandler {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        // クライアントスレッドは無視
        if (event.getLevel().isClientSide()) return;

        // メインハンドのみ処理
        if (event.getHand() != InteractionHand.MAIN_HAND) return;

        Player player = event.getEntity();
        ItemStack stack = player.getMainHandItem();

        // COMPOST_VALUESに登録されているか確認
        float[] values = CompostItem.COMPOST_VALUES.get(stack.getItem());
        if (values == null) return;

        // 自作CompostItemは自前のuse()で処理
        if (stack.getItem() instanceof CompostItem) return;

        // クリックしたブロックがFarmlandか確認
        BlockPos pos = event.getPos();
        BlockState state = event.getLevel().getBlockState(pos);

        if (!(state.getBlock() instanceof FarmBlock)) {
            pos = pos.below();
            state = event.getLevel().getBlockState(pos);
            if (!(state.getBlock() instanceof FarmBlock)) return;
        }

        ServerLevel serverLevel = (ServerLevel) event.getLevel();
        final BlockPos farmlandPos = pos;

        applyCompost(serverLevel, farmlandPos, values);

        serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER,
                farmlandPos.getX() + 0.5,
                farmlandPos.getY() + 1.0,
                farmlandPos.getZ() + 0.5,
                8, 0.3, 0.2, 0.3, 0.0);

        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        event.setCanceled(true);
    }

    private static void applyCompost(Level level, BlockPos pos, float[] values) {
        float beforeN = SoilNutrientHelper.get(level, pos, SoilNutrientHelper.KEY_NITROGEN);

        adjustNutrient(level, pos, SoilNutrientHelper.KEY_NITROGEN,   values[0]);
        adjustNutrient(level, pos, SoilNutrientHelper.KEY_PHOSPHORUS, values[1]);
        adjustNutrient(level, pos, SoilNutrientHelper.KEY_POTASSIUM,  values[2]);

        float afterN = SoilNutrientHelper.get(level, pos, SoilNutrientHelper.KEY_NITROGEN);
        PochiMod.LOGGER.info("[Compost] 施肥完了 pos={} N: {} -> {}", pos, beforeN, afterN);
    }

    private static void adjustNutrient(Level level, BlockPos pos, String key, float delta) {
        float current = SoilNutrientHelper.get(level, pos, key);
        SoilNutrientHelper.set(level, pos, key, current + delta);
    }
}