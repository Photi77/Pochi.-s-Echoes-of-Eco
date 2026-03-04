package net.pochi.pochimod.mineral.tools;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.pochi.pochimod.client.renderer.MineralItemRenderer;
import net.pochi.pochimod.item.ModArmorMaterials;
import net.pochi.pochimod.mineral.MineralColorCalculator;
import net.pochi.pochimod.mineral.MineralImpurity;

import java.util.List;
import java.util.function.Consumer;

/**
 * 鉱物防具（4部位共通クラス）
 *
 * 属性値はItemAttributeModifierEventで動的に付与 (MineralEffectHandler参照)
 */
public class MineralArmorItem extends ArmorItem {

    private final int slotIndex;

    public MineralArmorItem(ArmorItem.Type slot, Properties properties) {
        super(ModArmorMaterials.MINERAL, slot, properties);
        this.slotIndex = switch (slot.getSlot()) {
            case HEAD  -> 0;
            case CHEST -> 1;
            case LEGS  -> 2;
            case FEET  -> 3;
            default    -> 0;
        };
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return MineralItemRenderer.getInstance();
            }

            /**
             * HumanoidArmorLayer が dyeable=true のレイヤーを描画する際に呼び出す。
             * ToolData の color_hex を ARGB 整数として返す。
             */
            @Override
            public int getDefaultDyeColor(ItemStack stack) {
                ToolNBTHelper.ToolData data = AbstractMineralItem.getToolData(stack);
                if (data != null && data.colorHex() != null && !data.colorHex().isEmpty()) {
                    return 0xFF000000 | MineralColorCalculator.hexToInt(data.colorHex());
                }
                return 0xFFFFFFFF; // データなし → 白（乗算で元テクスチャのまま）
            }
        });
    }

    /** 動的属性はItemAttributeModifierEventで付与するため空を返す */
    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        return ItemAttributeModifiers.EMPTY;
    }

    public int getSlotIndex() {
        return slotIndex;
    }

    // ==============================
    //  耐久値
    // ==============================

    @Override
    public int getMaxDamage(ItemStack stack) {
        ToolNBTHelper.ToolData data = AbstractMineralItem.getToolData(stack);
        MineralImpurity primary   = MineralStatCalculator.getPrimaryOrDefault(data);
        MineralImpurity secondary = data != null ? data.secondary() : null;
        return MineralStatCalculator.calcArmorDurability(primary, secondary, slotIndex);
    }

    // ==============================
    //  ツールチップ
    // ==============================

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context,
                                List<Component> tooltip, TooltipFlag flag) {
        ToolNBTHelper.ToolData data = AbstractMineralItem.getToolData(stack);
        MineralImpurity primary   = MineralStatCalculator.getPrimaryOrDefault(data);
        MineralImpurity secondary = data != null ? data.secondary() : null;

        int armorVal    = MineralStatCalculator.BASE_ARMOR_VALUES[slotIndex]
                + MineralStatCalculator.calcArmorBonus(primary, secondary);
        float toughness = MineralStatCalculator.calcToughnessBonus(primary, secondary);
        float kbRes     = MineralStatCalculator.calcKnockbackResistance(primary, secondary);

        tooltip.add(Component.literal(MineralStatCalculator.formatStatInt("防御値", armorVal)));
        if (toughness > 0f)
            tooltip.add(Component.literal(MineralStatCalculator.formatStat("タフネス", toughness)));
        if (kbRes > 0f)
            tooltip.add(Component.literal(MineralStatCalculator.formatStat("KB耐性", kbRes)));
        tooltip.add(Component.literal(MineralStatCalculator.formatStatInt(
                "耐久", MineralStatCalculator.calcArmorDurability(primary, secondary, slotIndex))));

        if (data != null) {
            tooltip.add(Component.literal(String.format("§c主成分: §f%s §8(%.0f%%)",
                    primary.getType().id, primary.getRatio() * 100)));
            if (secondary != null && secondary.canApplyEffect()) {
                tooltip.add(Component.literal(String.format("§d副成分: §f%s §8(Lv%d効果)",
                        secondary.getType().id, secondary.getEffectLevel())));
            }
            tooltip.add(Component.literal("§6素材: §f" + data.baseGem().displayName));
        }
    }
}
