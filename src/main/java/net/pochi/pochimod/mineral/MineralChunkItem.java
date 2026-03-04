package net.pochi.pochimod.mineral;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.pochi.pochimod.client.renderer.MineralItemRenderer;
import net.pochi.pochimod.item.ModItems;

import java.util.List;
import java.util.function.Consumer;

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
    //  ツールチップ
    // ==============================

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context,
                                List<Component> tooltipComponents,
                                TooltipFlag isAdvanced) {

        if (!stack.has(DataComponents.CUSTOM_DATA)) {
            tooltipComponents.add(Component.literal("§7Unknown mineral"));
            return;
        }
        CompoundTag tag = stack.get(DataComponents.CUSTOM_DATA).copyTag();
        if (!MineralNBTHelper.hasMineralData(tag)) {
            tooltipComponents.add(Component.literal("§7Unknown mineral"));
            return;
        }

        MineralData data = MineralNBTHelper.read(tag);
        if (data == null) return;

        // ベース宝石
        tooltipComponents.add(Component.literal(
                "§6Base: §f" + data.getBaseGem().displayName + " §8(" + data.getBaseGem().id + ")"
        ));

        // コンボバイオーム（最大3個）
        List<ResourceLocation> biomes = data.getComboBiomes();
        if (!biomes.isEmpty()) {
            // バイオーム数に応じてラベルを変える
            String label = biomes.size() >= 3 ? "§b[3 Biome Combo]" : "§b[2 Biome Combo]";
            tooltipComponents.add(Component.literal(label));
            for (int i = 0; i < biomes.size(); i++) {
                String prefix = switch (i) {
                    case 0 -> "§7 River : §f";
                    case 1 -> "§7 Biome1 : §f";
                    case 2 -> "§7 Biome2 : §f";
                    default -> "§7  + §f";
                };
                tooltipComponents.add(Component.literal(prefix + biomes.get(i).getPath()));
            }
        }

        // 深度
        tooltipComponents.add(Component.literal(
                "§eDepth: §f" + data.getDepthLevel()
        ));

        // 不純物
        List<MineralImpurity> imps = data.getImpurities();
        if (!imps.isEmpty()) {
            tooltipComponents.add(Component.literal("§dImpurities:"));
            for (int i = 0; i < imps.size(); i++) {
                MineralImpurity imp = imps.get(i);
                String role = (i == 0) ? "[Main]" : "[Sub]";
                tooltipComponents.add(Component.literal(
                        String.format("  §7%s §f%s §8(%.0f%%)", role, imp.getType().id, imp.getRatio() * 100)
                ));
            }
        }

        // カラーコード
        tooltipComponents.add(Component.literal(
                "§7Color: " + data.getColorHex()
        ));
    }

    // ==============================
    //  アイテムカラー（IItemColor連携）
    //  登録はClientSetup側でColorHandlerEvent.ItemにてIItemColorを登録する
    // ==============================

    /**
     * ItemStackのNBTからRGBカラー値を取得
     * IItemColorのgetColor()から呼び出す
     *
     * @param stack  ItemStack
     * @param tintIndex 0: チャンク本体の着色
     * @return 0xRRGGBB 形式のカラー値
     */
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

    /**
     * MineralDataからNBT付きItemStackを生成
     */
    public static ItemStack createStack(Item mineralChunkItem, MineralData data) {
        ItemStack stack = new ItemStack(mineralChunkItem, 1);
        CompoundTag tag = new CompoundTag();
        MineralNBTHelper.write(tag, data);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
        return stack;
    }
}
