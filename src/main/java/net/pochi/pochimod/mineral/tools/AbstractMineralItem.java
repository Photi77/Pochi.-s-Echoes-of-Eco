package net.pochi.pochimod.mineral.tools;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.pochi.pochimod.client.renderer.MineralItemRenderer;
import net.pochi.pochimod.mineral.MineralColorCalculator;
import net.pochi.pochimod.mineral.MineralData;
import net.pochi.pochimod.mineral.MineralImpurity;
import net.pochi.pochimod.mineral.MineralNBTHelper;

import java.util.function.Consumer;

import java.util.List;

/**
 * 鉱物系アイテム共通基底クラス
 *
 * mineral_chunk と同じ NBT 構造を参照し、
 * MineralData → MineralStatCalculator で性能を決定する。
 *
 * クラフト時に mineral_chunk の NBT をそのままコピーすることで
 * 性能が確定する設計。
 */
public abstract class AbstractMineralItem extends Item {

    public AbstractMineralItem(Properties properties) {
        super(properties);
    }

    // ==============================
    //  NBTアクセス
    // ==============================

    /**
     * ツールアイテム用: ToolNBTHelper経由で tool_data を読み込む
     * クラフト後のツールはこちらを使う
     */
    public static ToolNBTHelper.ToolData getToolData(ItemStack stack) {
        return ToolNBTHelper.read(stack);
    }

    /**
     * mineral_chunk 用: MineralNBTHelper経由で mineral_data を読み込む
     */
    public static MineralData getMineralData(ItemStack stack) {
        if (!stack.has(DataComponents.CUSTOM_DATA)) return null;
        CompoundTag tag = stack.get(DataComponents.CUSTOM_DATA).copyTag();
        if (!MineralNBTHelper.hasMineralData(tag)) return null;
        return MineralNBTHelper.read(tag);
    }

    /**
     * mineral_chunk の NBT をこのアイテムにコピー
     * クラフト完成時に呼び出す
     */
    public static void copyMineralNBT(ItemStack source, ItemStack target) {
        if (!source.has(DataComponents.CUSTOM_DATA)) return;
        CompoundTag sourceTag = source.get(DataComponents.CUSTOM_DATA).copyTag();
        if (!MineralNBTHelper.hasMineralData(sourceTag)) return;
        MineralData data = MineralNBTHelper.read(sourceTag);
        if (data == null) return;
        CompoundTag targetTag = target.has(DataComponents.CUSTOM_DATA)
                ? target.get(DataComponents.CUSTOM_DATA).copyTag()
                : new CompoundTag();
        MineralNBTHelper.write(targetTag, data);
        target.set(DataComponents.CUSTOM_DATA, CustomData.of(targetTag));
    }

    // ==============================
    //  BEWLR（IClientItemExtensions）
    // ==============================

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return MineralItemRenderer.getInstance();
            }
        });
    }

    // ==============================
    //  アイテムカラー（IItemColor連携）
    //  登録はClientEvents側でRegisterColorHandlersEvent.Itemにて行う
    // ==============================

    /**
     * ツールアイテムのNBTからRGBカラー値を取得
     * tintIndex=0: ツール本体の着色
     *
     * @param stack      ItemStack
     * @param tintIndex  0のみ有効（item/generated/handheld はlayer0→tintIndex0 を自動付与）
     * @return 0xRRGGBB 形式のカラー値
     */
    public static int getItemColor(ItemStack stack, int tintIndex) {
        if (tintIndex != 0) return 0xFFFFFF;
        ToolNBTHelper.ToolData data = ToolNBTHelper.read(stack);
        if (data == null || data.colorHex() == null || data.colorHex().isEmpty()) return 0xFFFFFF;
        return MineralColorCalculator.hexToInt(data.colorHex());
    }

    // ==============================
    //  ツールチップ共通部分
    // ==============================

    /**
     * 鉱物情報のツールチップを追加
     * サブクラスで super.appendHoverText() を呼ぶこと
     */
    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context,
                                List<Component> tooltipComponents, TooltipFlag flag) {
        // ツールは tool_data、mineral_chunk は mineral_data を持つ
        // 両方を試みて、あった方を使う
        ToolNBTHelper.ToolData toolData = getToolData(stack);
        if (toolData != null) {
            appendToolDataTooltip(toolData, tooltipComponents);
            return;
        }

        MineralData mineralData = getMineralData(stack);
        if (mineralData != null) {
            appendMineralDataTooltip(mineralData, tooltipComponents);
            return;
        }

        tooltipComponents.add(Component.literal("§7[未加工 - mineral_chunkで素材なし]"));
    }

    private static void appendToolDataTooltip(ToolNBTHelper.ToolData data,
                                              List<Component> tooltip) {
        tooltip.add(Component.literal("§6素材: §f" + data.baseGem().displayName));

        MineralImpurity primary = data.primary();
        if (primary != null) {
            tooltip.add(Component.literal(
                    "§c主成分: §f" + primary.getType().id +
                            String.format(" §8(%.0f%%)", primary.getRatio() * 100)));
        }

        MineralImpurity secondary = data.secondary();
        if (secondary != null && secondary.canApplyEffect()) {
            tooltip.add(Component.literal(
                    "§d副成分: §f" + secondary.getType().id +
                            String.format(" §8(Lv%d効果)", secondary.getEffectLevel())));
        }

        tooltip.add(Component.literal("§7カラー: " + data.colorHex()));
    }

    private static void appendMineralDataTooltip(MineralData data,
                                                 List<Component> tooltip) {
        tooltip.add(Component.literal("§6素材: §f" + data.getBaseGem().displayName));

        MineralImpurity primary = data.getPrimaryImpurity();
        if (primary != null) {
            tooltip.add(Component.literal(
                    "§c主成分: §f" + primary.getType().id +
                            String.format(" §8(%.0f%%)", primary.getRatio() * 100)));
        }

        MineralImpurity secondary = data.getSecondaryImpurity();
        if (secondary != null && secondary.canApplyEffect()) {
            tooltip.add(Component.literal(
                    "§d副成分: §f" + secondary.getType().id +
                            String.format(" §8(Lv%d効果)", secondary.getEffectLevel())));
        }

        tooltip.add(Component.literal("§7カラー: " + data.getColorHex()));
    }

    // ==============================
    //  ItemStack生成ヘルパー
    // ==============================

    /**
     * mineral_chunkのNBTを引き継いだItemStackを生成
     */
    public static ItemStack createFromChunk(Item toolItem, ItemStack chunkStack) {
        ItemStack result = new ItemStack(toolItem, 1);
        copyMineralNBT(chunkStack, result);
        return result;
    }
}
