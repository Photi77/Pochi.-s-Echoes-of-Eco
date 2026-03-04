package net.pochi.pochimod.ferm;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.event.RenderHighlightEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.networking.ModMessages;
import net.pochi.pochimod.networking.packet.SoilDataClientCache;
import net.pochi.pochimod.networking.packet.SoilDataPacket;

@EventBusSubscriber(modid = PochiMod.MOD_ID)
public class SoilTooltipHandler {

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();

        // 閠募慍繧｢繧､繝・Β・・armland 縺ｯ逶ｴ謗･蜈･謇九〒縺阪↑縺・・縺ｧ Hoe 邉ｻ縺ｫ陦ｨ遉ｺ縺吶ｋ譁ｹ豕輔ｂ縺ゅｋ縺・
        // 縺薙％縺ｧ縺ｯ荳譌ｦ縺ｩ縺ｮ繧｢繧､繝・Β縺ｧ繧ゅせ繝九・繧ｯ荳ｭ縺ｫ雜ｳ蜈・愛螳壹☆繧区婿蠑上・髮｣縺励＞縺溘ａ
        // FarmlandBlock 繧｢繧､繝・Β繧呈戟縺｣縺ｦ縺・ｋ縺ｨ縺・縺ｫ陦ｨ遉ｺ・・
        if (stack.getItem() != Items.WOODEN_HOE) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) return;
        if (!mc.player.isShiftKeyDown()) return;

        // 繧ｯ繝ｩ繧､繧｢繝ｳ繝亥・縺ｪ縺ｮ縺ｧ陦ｨ遉ｺ縺ｮ縺ｿ・亥､縺ｯ繧ｵ繝ｼ繝舌・縺九ｉ蜷梧悄縺悟ｿ・ｦ√□縺・
        // 繝・せ繝域ｮｵ髫弱〒縺ｯ繧ｳ繝槭Φ繝峨〒遒ｺ隱阪☆繧区婿蠑上〒蜊∝・・・
        event.getToolTip().add(
                Component.literal("ﾂｧ7[繧ｹ繝九・繧ｯ荳ｭ] /soil get 縺ｧ蝨溷｣悟､繧堤｢ｺ隱阪〒縺阪∪縺・"));
    }

    // SoilTooltipHandler.java・域里蟄倥け繝ｩ繧ｹ縺ｫ霑ｽ險假ｼ・
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        if (level.isClientSide()) return;
        if (event.getHand() != InteractionHand.MAIN_HAND) return;

        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);

        // Farmland縺ｮ縺ｿ蟇ｾ雎｡
        if (!(state.getBlock() instanceof FarmBlock)) return;

        // 謇九′遨ｺ縺ｮ譎ゅ・縺ｿ騾∽ｿ｡
        Player player = event.getEntity();
        if (!player.getMainHandItem().isEmpty()) return;

        // SoilTooltipHandler.java 縺ｮ騾∽ｿ｡驛ｨ蛻・
        SoilDataCapability.get(level, pos).ifPresent(data -> {
            float n = SoilNutrientHelper.get(level, pos, SoilNutrientHelper.KEY_NITROGEN);
            float p = SoilNutrientHelper.get(level, pos, SoilNutrientHelper.KEY_PHOSPHORUS);
            float k = SoilNutrientHelper.get(level, pos, SoilNutrientHelper.KEY_POTASSIUM);

            PochiMod.LOGGER.info("[SoilPacket] n={} p={} k={} at pos={}", n, p, k, pos);

            ModMessages.sendToPlayer(new SoilDataPacket(pos, n, p, k),
                    (ServerPlayer) player);
        });
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onBlockHighlight(RenderHighlightEvent.Block event) {
        // 繝悶Ο繝・け繝輔か繝ｼ繧ｫ繧ｹ譎ゅ↓繝代こ繝・ヨ隕∵ｱ・竊・繝・・繝ｫ繝√ャ繝励・HUD縺ｧ陦ｨ遉ｺ
    }

    // 縺ｾ縺溘・ ItemTooltipEvent 繧剃ｽｿ繧上★縺ｫHUD謠冗判
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGuiLayerEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Level level = mc.level;
        if (level == null) return;

        // 繝励Ξ繧､繝､繝ｼ縺瑚ｦ九※縺・ｋ繝悶Ο繝・け繧貞叙蠕・
        HitResult hit = mc.hitResult;
        if (!(hit instanceof BlockHitResult blockHit)) return;

        BlockPos pos = blockHit.getBlockPos();
        BlockState state = mc.level.getBlockState(pos);
        if (!(level.getBlockState(pos).getBlock() instanceof FarmBlock)) return;

        if (!SoilDataClientCache.hasData(pos)) return;

        // 逕ｻ髱｢縺ｫ謠冗判
        Font font = mc.font;
        PoseStack pose = event.getGuiGraphics().pose();

        int x = 4, y = 4;
        float n = SoilDataClientCache.getN();
        float p = SoilDataClientCache.getP();
        float k = SoilDataClientCache.getK();

        event.getGuiGraphics().drawString(font,
                String.format("N: %.0f%%  P: %.0f%%  K: %.0f%%",
                        n * 100, p * 100, k * 100),
                x, y, 0xAAFFAA);

        // 騾∽ｿ｡蛛ｴ・医し繝ｼ繝舌・・・
        PochiMod.LOGGER.info("[SoilPacket] sending pos={}", pos);

// 蜿嶺ｿ｡蛛ｴ・医け繝ｩ繧､繧｢繝ｳ繝医く繝｣繝・す繝･譖ｴ譁ｰ譎ゑｼ・
        PochiMod.LOGGER.info("[SoilCache] cached pos={}", pos);

// HUD蛛ｴ
        PochiMod.LOGGER.info("[SoilHUD] looking at pos={}", pos);
    }
}


