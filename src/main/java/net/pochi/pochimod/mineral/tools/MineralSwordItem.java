package net.pochi.pochimod.mineral.tools;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.client.renderer.MineralItemRenderer;
import net.pochi.pochimod.mineral.ImpurityType;
import net.pochi.pochimod.mineral.MineralImpurity;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * 鉱物剣
 *
 * 主不純物タイプ → 攻撃力 or 攻撃速度を重点強化
 * 副不純物 → 装備時エフェクト（MineralEffectHandlerで管理）
 */
public class MineralSwordItem extends TieredItem {

    private static final UUID ATTACK_DAMAGE_UUID = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    private static final UUID ATTACK_SPEED_UUID  = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");

    public MineralSwordItem(Properties properties) {
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
    public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {

        ToolNBTHelper.ToolData data = AbstractMineralItem.getToolData(stack);
        MineralImpurity primary = data != null
                ? data.primaryOrDefault()
                : new MineralImpurity(ImpurityType.IRON_2, 0.3f);

        // デバッグ
        org.apache.logging.log4j.LogManager.getLogger().info(
                "[MineralSword] data={}, primary={} ratio={}",
                data != null,
                primary.getType().id,
                primary.getRatio()
        );

        float damage = MineralStatCalculator.BASE_SWORD_DAMAGE
                + MineralStatCalculator.calcAttackDamageBonus(primary);

        float speed = MineralStatCalculator.BASE_SWORD_SPEED
                + MineralStatCalculator.calcAttackSpeedBonus(primary);

        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "mineral_sword_damage"),
                                damage,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(
                                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "mineral_sword_speed"),
                                speed,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }

    //@Override
    //public Multimap<Attribute, AttributeModifier> getAttributeModifiers(
    //        EquipmentSlot slot, ItemStack stack) {
//
    //    if (slot != EquipmentSlot.MAINHAND) return ImmutableMultimap.of();
//
    //    ToolNBTHelper.ToolData data = AbstractMineralItem.getToolData(stack);
    //    MineralImpurity primary = data != null ? data.primaryOrDefault() : new MineralImpurity(ImpurityType.IRON_2, 0.3f);
//
    //    // ★デバッグ（確認後削除）
    //    org.apache.logging.log4j.LogManager.getLogger().info(
    //            "[MineralSword] getAttributeModifiers: data={}, primary={} ratio={}",
    //            data != null, primary.getType().id, primary.getRatio());
//
    //    float damage = MineralStatCalculator.BASE_SWORD_DAMAGE
    //            + MineralStatCalculator.calcAttackDamageBonus(primary);
    //    float speed  = MineralStatCalculator.BASE_SWORD_SPEED
    //            + MineralStatCalculator.calcAttackSpeedBonus(primary);
//
    //    return ImmutableMultimap.of(
    //            Attributes.ATTACK_DAMAGE,
    //            new AttributeModifier(ATTACK_DAMAGE_UUID, "Mineral sword damage",
    //                    damage, AttributeModifier.Operation.ADD_VALUE),
    //            Attributes.ATTACK_SPEED,
    //            new AttributeModifier(ATTACK_SPEED_UUID, "Mineral sword speed",
    //                    speed, AttributeModifier.Operation.ADD_VALUE)
    //    );
    //}

    @Override
    public int getMaxDamage(ItemStack stack) {
        ToolNBTHelper.ToolData data = AbstractMineralItem.getToolData(stack);
        MineralImpurity primary = data != null ? data.primaryOrDefault() : new MineralImpurity(ImpurityType.IRON_2, 0.3f);
        return MineralStatCalculator.calcWeaponDurability(primary);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
        return true;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, net.minecraft.world.level.block.state.BlockState state) {
        if (state.is(net.minecraft.world.level.block.Blocks.COBWEB)) return 15.0f;
        return super.getDestroySpeed(stack, state);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context,
                                List<Component> tooltip, TooltipFlag flag) {
        ToolNBTHelper.ToolData data = AbstractMineralItem.getToolData(stack);
        MineralImpurity primary = data != null ? data.primaryOrDefault() : new MineralImpurity(ImpurityType.IRON_2, 0.3f);

        tooltip.add(Component.literal(MineralStatCalculator.formatStat(
                "攻撃力", MineralStatCalculator.BASE_SWORD_DAMAGE
                        + MineralStatCalculator.calcAttackDamageBonus(primary))));
        tooltip.add(Component.literal(MineralStatCalculator.formatStatInt(
                "耐久", MineralStatCalculator.calcWeaponDurability(primary))));

        if (data != null) {
            tooltip.add(Component.literal("§6素材: §f" + data.baseGem().displayName));
            tooltip.add(Component.literal(String.format("§c主成分: §f%s §8(%.0f%%)",
                    primary.getType().id, primary.getRatio() * 100)));
            MineralImpurity secondary = data.secondary();
            if (secondary != null && secondary.canApplyEffect()) {
                tooltip.add(Component.literal(String.format("§d副成分: §f%s §8(Lv%d効果)",
                        secondary.getType().id, secondary.getEffectLevel())));
            }
        }
    }
}



