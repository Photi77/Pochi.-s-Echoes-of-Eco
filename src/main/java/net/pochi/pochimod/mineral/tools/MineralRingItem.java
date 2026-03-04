package net.pochi.pochimod.mineral.tools;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.pochi.pochimod.mineral.MineralData;
import net.pochi.pochimod.mineral.MineralImpurity;

import java.util.List;

/**
 * 鉱物リング（アクセサリー）
 *
 * 主不純物のMobEffectを「着用中に常時付与」する。
 * 剣・防具の副不純物エフェクトとは独立して動作。
 *
 * ===== Curios API 対応 =====
 * Curios API が導入されている場合は ICurioItem を実装して
 * リングスロットに装備できるようにする。
 *
 * 導入していない場合はオフハンドやメインハンドに持つことで
 * MineralEffectHandler の HELD トリガーが発動する（簡易版）。
 *
 * ===== 付与エフェクト設計 =====
 * 主不純物がEQUIPPEDトリガーのもの → 装備中に常時付与（Curios装備時）
 * 主不純物がHELDトリガーのもの    → 手持ち時に付与（MineralEffectHandlerが管理）
 * 主不純物がON_HITトリガーのもの  → リングは特殊：攻撃力ボーナスに変換
 *
 * ===== 性能 =====
 * リングはエフェクト専用。攻撃力・防御値への直接補正はなし。
 * エフェクトレベル = (int)(primaryRatio × 3)（剣・防具の副不純物より高め）
 */
public class MineralRingItem extends AbstractMineralItem {

    public MineralRingItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    // ==============================
    //  Curios API インターフェース
    //  Curios が存在する場合は @Override して使用
    // ==============================

    /**
     * Curios の curioTick() 相当 — 毎Tick呼ばれる
     * Curios 導入時は ICurioItem を実装してこのメソッドを登録する
     *
     * @param identifier スロット識別子（"ring" など）
     * @param index      スロットインデックス
     * @param living     装備者
     * @param stack      このItemStack
     */
    public void onCurioTick(String identifier, int index, LivingEntity living, ItemStack stack) {
        if (living.level().isClientSide()) return;
        if (living.tickCount % 40 != 0) return; // 2秒に1回更新

        MineralData data = getMineralData(stack);
        if (data == null) return;

        MineralImpurity primary = data.getPrimaryImpurity();
        if (primary == null || !primary.canApplyEffect()) return;

        // リングはprimaryのエフェクトを付与（副不純物より強め）
        int level = Math.min((int)(primary.getRatio() * 3), 3); // 最大Lv3
        if (level < 1) return;

        living.addEffect(new MobEffectInstance(
                primary.getType().getMobEffect(),
                60,          // 3秒（毎2秒更新なのでほぼ常時）
                level - 1,   // amplifier（0-origin）
                false,
                true,
                true
        ));
    }

    // ==============================
    //  Curios なし版：PlayerTickEvent からの呼び出し用
    //  YourMod.onPlayerTick() で curio スロットを走査できない場合の代替
    // ==============================

    /**
     * MineralEffectHandler.onLivingTick() から呼ばれる想定の静的メソッド
     * オフハンド・メインハンドのMineralRingItemを検出して効果を付与
     */
    public static void applyRingEffect(LivingEntity entity) {
        applyIfRing(entity, entity.getMainHandItem());
        applyIfRing(entity, entity.getOffhandItem());
    }

    private static void applyIfRing(LivingEntity entity, ItemStack stack) {
        if (!(stack.getItem() instanceof MineralRingItem)) return;
        if (entity.level().isClientSide()) return;

        MineralData data = getMineralData(stack);
        if (data == null) return;

        MineralImpurity primary = data.getPrimaryImpurity();
        if (primary == null || !primary.canApplyEffect()) return;

        int level = Math.min((int)(primary.getRatio() * 3), 3);
        if (level < 1) return;

        entity.addEffect(new MobEffectInstance(
                primary.getType().getMobEffect(),
                60,
                level - 1,
                false, true, true
        ));
    }

    // ==============================
    //  ツールチップ
    // ==============================

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context,
                                List<Component> tooltip, TooltipFlag flag) {
        MineralData data = getMineralData(stack);
        if (data == null) {
            tooltip.add(Component.literal("§7[未加工]"));
            return;
        }

        MineralImpurity primary = data.getPrimaryImpurity();
        if (primary != null) {
            int effectLevel = Math.min((int)(primary.getRatio() * 3), 3);
            tooltip.add(Component.literal(
                    "§b常時効果: §f" + primary.getType().id + " Lv" + effectLevel
            ));
            tooltip.add(Component.literal(
                    "§7  (" + getTriggerDescription(primary) + ")"
            ));
        }

        tooltip.add(Component.literal("§6素材: §f" + data.getBaseGem().displayName));
        tooltip.add(Component.literal("§7カラー: " + data.getColorHex()));
    }

    private String getTriggerDescription(MineralImpurity imp) {
        return switch (imp.getType().trigger) {
            case EQUIPPED -> "Curios装備中に発動";
            case HELD     -> "手持ち中に発動";
            case ON_HIT   -> "攻撃時に敵へ発動";
        };
    }
}
