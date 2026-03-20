package net.pochi.pochimod.mineral.tools;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.pochi.pochimod.mineral.MineralColorCalculator;
import net.pochi.pochimod.mineral.MineralImpurity;

import java.util.function.Consumer;

/**
 * 鉱物防具（4部位共通クラス）
 *
 * 属性値はItemAttributeModifierEventで動的に付与 (MineralEffectHandler参照)
 */
public class MineralArmorItem extends Item {

    private final int slotIndex;

    public MineralArmorItem(ArmorType slot, Properties properties) {
        super(properties);
        this.slotIndex = switch (slot.getSlot()) {
            case HEAD  -> 0;
            case CHEST -> 1;
            case LEGS  -> 2;
            case FEET  -> 3;
            default    -> 0;
        };
    }


    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public int getDefaultDyeColor(ItemStack stack) {
                ToolNBTHelper.ToolData data = AbstractMineralItem.getToolData(stack);
                if (data != null && data.colorHex() != null && !data.colorHex().isEmpty()) {
                    return 0xFF000000 | MineralColorCalculator.hexToInt(data.colorHex());
                }
                return 0xFFFFFFFF;
            }
        });
    }

    /** 動的属性はItemAttributeModifierEventで付与するため空を返す */
    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
        return ItemAttributeModifiers.EMPTY;
    }

    public int getSlotIndex() {
        return slotIndex;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        ToolNBTHelper.ToolData data = AbstractMineralItem.getToolData(stack);
        MineralImpurity primary   = MineralStatCalculator.getPrimaryOrDefault(data);
        MineralImpurity secondary = data != null ? data.secondary() : null;
        return MineralStatCalculator.calcArmorDurability(primary, secondary, slotIndex);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context,
                                TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
        ToolNBTHelper.ToolData data = AbstractMineralItem.getToolData(stack);
        MineralImpurity primary   = MineralStatCalculator.getPrimaryOrDefault(data);
        MineralImpurity secondary = data != null ? data.secondary() : null;

        int armorVal    = MineralStatCalculator.BASE_ARMOR_VALUES[slotIndex]
                + MineralStatCalculator.calcArmorBonus(primary, secondary);
        float toughness = MineralStatCalculator.calcToughnessBonus(primary, secondary);
        float kbRes     = MineralStatCalculator.calcKnockbackResistance(primary, secondary);

        tooltip.accept(Component.literal(MineralStatCalculator.formatStatInt("防御値", armorVal)));
        if (toughness > 0f)
            tooltip.accept(Component.literal(MineralStatCalculator.formatStat("タフネス", toughness)));
        if (kbRes > 0f)
            tooltip.accept(Component.literal(MineralStatCalculator.formatStat("KB耐性", kbRes)));
        tooltip.accept(Component.literal(MineralStatCalculator.formatStatInt(
                "耐久", MineralStatCalculator.calcArmorDurability(primary, secondary, slotIndex))));

        if (data != null) {
            tooltip.accept(Component.literal(String.format("§c主成分: §f%s §8(%.0f%%)",
                    primary.getType().id, primary.getRatio() * 100)));
            if (secondary != null && secondary.canApplyEffect()) {
                tooltip.accept(Component.literal(String.format("§d副成分: §f%s §8(Lv%d効果)",
                        secondary.getType().id, secondary.getEffectLevel())));
            }
            tooltip.accept(Component.literal("§6素材: §f" + data.baseGem().displayName));
        }
    }
}
