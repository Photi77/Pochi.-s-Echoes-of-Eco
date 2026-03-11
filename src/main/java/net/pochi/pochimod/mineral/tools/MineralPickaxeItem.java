package net.pochi.pochimod.mineral.tools;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.state.BlockState;
import net.pochi.pochimod.mineral.ImpurityType;
import net.pochi.pochimod.mineral.MineralImpurity;

import java.util.function.Consumer;

/**
 * 鉱物ツルハシ
 *
 * getDestroySpeed() を NBT から動的に返す
 * ハーベストレベルは MineralToolTier 経由で動的に決定
 */
/**
 * 鉱物ツルハシ
 *
 * getDestroySpeed() を NBT から動的に返す
 * ハーベストレベルは MineralToolTier 経由で動的に決定
 */
public class MineralPickaxeItem extends AbstractMineralItem {

    public MineralPickaxeItem(Properties properties) {
        super(properties);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        // ツルハシ対象ブロックかチェック
        if (!net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE.equals(state.getBlock())) {
            return 1.0f;
        }
        ToolNBTHelper.ToolData data = AbstractMineralItem.getToolData(stack);
        MineralImpurity primary = data != null ? data.primaryOrDefault() : new MineralImpurity(ImpurityType.IRON_2, 0.3f);
        return MineralStatCalculator.calcMiningSpeed(primary);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        ToolNBTHelper.ToolData data = AbstractMineralItem.getToolData(stack);
        MineralImpurity primary = data != null ? data.primaryOrDefault() : new MineralImpurity(ImpurityType.IRON_2, 0.3f);
        return MineralStatCalculator.calcToolDurability(primary);
    }

    /**
     * ハーベストレベルを動的に返す
     * Forge の IForgeItem.getHarvestLevel() をオーバーライド
     */
    /**
     * 採掘可能レベルをNBTから判定
     * 1.20.1ではgetHarvestLevelが廃止されたため、
     * isCorrectToolForDrops() でブロックタグと照合する
     */
    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        if (!net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE.equals(state.getBlock())) {
            return false;
        }
        ToolNBTHelper.ToolData data = AbstractMineralItem.getToolData(stack);
        MineralImpurity primary = data != null ? data.primaryOrDefault() : new MineralImpurity(ImpurityType.IRON_2, 0.3f);
        int harvestLevel = MineralStatCalculator.calcHarvestLevel(primary);

        // harvestLevel に応じたブロックタグを確認
        if (state.is(net.minecraft.tags.BlockTags.NEEDS_DIAMOND_TOOL)) return harvestLevel >= 3;
        if (state.is(net.minecraft.tags.BlockTags.NEEDS_IRON_TOOL))    return harvestLevel >= 2;
        if (state.is(net.minecraft.tags.BlockTags.NEEDS_STONE_TOOL))   return harvestLevel >= 1;
        return true; // 制限なし（木・砂等）
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context,
                                TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
        ToolNBTHelper.ToolData data = AbstractMineralItem.getToolData(stack);
        MineralImpurity primary = data != null ? data.primaryOrDefault() : new MineralImpurity(ImpurityType.IRON_2, 0.3f);

        tooltip.accept(Component.literal(MineralStatCalculator.formatStat(
                "採掘速度", MineralStatCalculator.calcMiningSpeed(primary))));
        tooltip.accept(Component.literal(MineralStatCalculator.formatStatInt(
                "採掘レベル", MineralStatCalculator.calcHarvestLevel(primary))));
        tooltip.accept(Component.literal(MineralStatCalculator.formatStatInt(
                "耐久", MineralStatCalculator.calcToolDurability(primary))));

        if (data != null) tooltip.accept(Component.literal("§6素材: §f" + data.baseGem().displayName));
    }
}
