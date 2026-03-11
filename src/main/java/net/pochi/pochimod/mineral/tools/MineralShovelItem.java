package net.pochi.pochimod.mineral.tools;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.state.BlockState;
import net.pochi.pochimod.mineral.MineralData;
import net.pochi.pochimod.mineral.MineralImpurity;

import java.util.function.Consumer;

/**
 * 鉱物シャベル
 */
public class MineralShovelItem extends AbstractMineralItem {

    public MineralShovelItem(Properties properties) {
        super(properties);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (!net.minecraft.tags.BlockTags.MINEABLE_WITH_SHOVEL.equals(state.getBlock())) {
            return 1.0f;
        }
        MineralData data = AbstractMineralItem.getMineralData(stack);
        MineralImpurity primary = MineralStatCalculator.getPrimaryOrDefault(data);
        return MineralStatCalculator.calcMiningSpeed(primary);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        MineralData data = AbstractMineralItem.getMineralData(stack);
        MineralImpurity primary = MineralStatCalculator.getPrimaryOrDefault(data);
        return MineralStatCalculator.calcToolDurability(primary);
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        if (!net.minecraft.tags.BlockTags.MINEABLE_WITH_SHOVEL.equals(state.getBlock())) {
            return false;
        }
        MineralData data = AbstractMineralItem.getMineralData(stack);
        MineralImpurity primary = MineralStatCalculator.getPrimaryOrDefault(data);
        int harvestLevel = MineralStatCalculator.calcHarvestLevel(primary);

        if (state.is(net.minecraft.tags.BlockTags.NEEDS_DIAMOND_TOOL)) return harvestLevel >= 3;
        if (state.is(net.minecraft.tags.BlockTags.NEEDS_IRON_TOOL))    return harvestLevel >= 2;
        if (state.is(net.minecraft.tags.BlockTags.NEEDS_STONE_TOOL))   return harvestLevel >= 1;
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context,
                                TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
        MineralData data = AbstractMineralItem.getMineralData(stack);
        MineralImpurity primary = MineralStatCalculator.getPrimaryOrDefault(data);

        tooltip.accept(Component.literal(MineralStatCalculator.formatStat(
                "採掘速度", MineralStatCalculator.calcMiningSpeed(primary))));
        tooltip.accept(Component.literal(MineralStatCalculator.formatStatInt(
                "耐久", MineralStatCalculator.calcToolDurability(primary))));

        if (data != null) tooltip.accept(Component.literal("§6素材: §f" + data.getBaseGem().displayName));
    }
}
