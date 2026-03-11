package net.pochi.pochimod.mineral;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.pochi.pochimod.item.ModItems;

import java.util.function.Consumer;
import java.util.List;

/**
 * mineral_chunk アイテム
 *
 * - NBTからMineralDataを読み込み、color_hexでアイテムを着色
 * - ツールチップに不純物情報を表示
 * - IItemColorインターフェースと連携して動的着色
 */
public class MineralChunkItem extends Item {

    public MineralChunkItem(Properties properties) {
        super(properties);
    }

    // ==============================
    //  ツールチップ
    // ==============================

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context,
                                net.minecraft.world.item.component.TooltipDisplay display,
                                Consumer<Component> tooltip,
                                TooltipFlag isAdvanced) {

        if (!stack.has(DataComponents.CUSTOM_DATA)) {
            tooltip.accept(Component.literal("§7Unknown mineral"));
            return;
        }
        CompoundTag tag = stack.get(DataComponents.CUSTOM_DATA).copyTag();
        if (!MineralNBTHelper.hasMineralData(tag)) {
            tooltip.accept(Component.literal("§7Unknown mineral"));
            return;
        }

        MineralData data = MineralNBTHelper.read(tag);
        if (data == null) return;

        // ベース宝石
        tooltip.accept(Component.literal(
                "§6Base: §f" + data.getBaseGem().displayName + " §8(" + data.getBaseGem().id + ")"
        ));

        // コンボバイオーム（最大3個）
        List<Identifier> biomes = data.getComboBiomes();
        if (!biomes.isEmpty()) {
            String label = biomes.size() >= 3 ? "§b[3 Biome Combo]" : "§b[2 Biome Combo]";
            tooltip.accept(Component.literal(label));
            for (int i = 0; i < biomes.size(); i++) {
                String prefix = switch (i) {
                    case 0 -> "§7 River : §f";
                    case 1 -> "§7 Biome1 : §f";
                    case 2 -> "§7 Biome2 : §f";
                    default -> "§7  + §f";
                };
                tooltip.accept(Component.literal(prefix + biomes.get(i).getPath()));
            }
        }

        // 深度
        tooltip.accept(Component.literal(
                "§eDepth: §f" + data.getDepthLevel()
        ));

        // 不純物
        List<MineralImpurity> imps = data.getImpurities();
        if (!imps.isEmpty()) {
            tooltip.accept(Component.literal("§dImpurities:"));
            for (int i = 0; i < imps.size(); i++) {
                MineralImpurity imp = imps.get(i);
                String role = (i == 0) ? "[Main]" : "[Sub]";
                tooltip.accept(Component.literal(
                        String.format("  §7%s §f%s §8(%.0f%%)", role, imp.getType().id, imp.getRatio() * 100)
                ));
            }
        }

        // カラーコード
        tooltip.accept(Component.literal(
                "§7Color: " + data.getColorHex()
        ));
    }

    // ==============================
    //  アイテムカラー（IItemColor連携）
    // ==============================

    public static int getItemColor(ItemStack stack, int tintIndex) {
        if (tintIndex != 0) return 0xFFFFFF;

        if (!stack.has(DataComponents.CUSTOM_DATA)) return 0xFFFFFF;
        CompoundTag tag = stack.get(DataComponents.CUSTOM_DATA).copyTag();
        if (!MineralNBTHelper.hasMineralData(tag)) return 0xFFFFFF;

        MineralData data = MineralNBTHelper.read(tag);
        if (data == null) return 0xFFFFFF;

        return MineralColorCalculator.hexToInt(data.getColorHex());
    }

    // ==============================
    //  NBT付きItemStack生成ヘルパー
    // ==============================

    public static ItemStack createStack(Item mineralChunkItem, MineralData data) {
        ItemStack stack = new ItemStack(mineralChunkItem, 1);
        CompoundTag tag = new CompoundTag();
        MineralNBTHelper.write(tag, data);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
        return stack;
    }
}
