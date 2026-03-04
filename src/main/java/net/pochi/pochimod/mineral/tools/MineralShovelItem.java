package net.pochi.pochimod.mineral.tools;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.pochi.pochimod.client.renderer.MineralItemRenderer;
import net.pochi.pochimod.mineral.MineralData;
import net.pochi.pochimod.mineral.MineralImpurity;

import java.util.List;
import java.util.function.Consumer;

/**
 * 鉱物シャベル
 */
public class MineralShovelItem extends ShovelItem {

    public MineralShovelItem(Properties properties) {
        super(MineralToolTier.from(null), properties);
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
                                List<Component> tooltip, TooltipFlag flag) {
        MineralData data = AbstractMineralItem.getMineralData(stack);
        MineralImpurity primary = MineralStatCalculator.getPrimaryOrDefault(data);

        tooltip.add(Component.literal(MineralStatCalculator.formatStat(
                "採掘速度", MineralStatCalculator.calcMiningSpeed(primary))));
        tooltip.add(Component.literal(MineralStatCalculator.formatStatInt(
                "耐久", MineralStatCalculator.calcToolDurability(primary))));

        if (data != null) tooltip.add(Component.literal("§6素材: §f" + data.getBaseGem().displayName));
    }
}
