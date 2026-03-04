package net.pochi.pochimod.mineral.tools;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.client.renderer.MineralItemRenderer;
import net.pochi.pochimod.mineral.ImpurityType;
import net.pochi.pochimod.mineral.MineralImpurity;

import java.util.function.Consumer;

import java.util.List;
import java.util.UUID;

/**
 * 鉱物斧
 * AxeItem を継承すると固定 Attribute が干渉するため DiggerItem を直接継承
 */
public class MineralAxeItem extends DiggerItem {

    private static final UUID ATTACK_DAMAGE_UUID = UUID.fromString("92A35D26-3875-4C3F-A5D0-8DFF6B7E3A71");
    private static final UUID ATTACK_SPEED_UUID  = UUID.fromString("B5F8A3D1-9C4E-4B2A-8F7D-1E6C2A4B8D9F");

    public MineralAxeItem(Properties properties) {
        super(MineralToolTier.from(null),
                net.minecraft.tags.BlockTags.MINEABLE_WITH_AXE, properties);
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
                "[MineralAxe] data={}, primary={} ratio={}",
                data != null,
                primary.getType().id,
                primary.getRatio()
        );

        float damage = (MineralStatCalculator.BASE_AXE_DAMAGE
                + MineralStatCalculator.calcAttackDamageBonus(primary)) * 1.2f;

        float speed = MineralStatCalculator.BASE_AXE_SPEED
                + MineralStatCalculator.calcAttackSpeedBonus(primary) * 0.7f;

        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "mineral_axe_damage"),
                                damage,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(
                                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "mineral_axe_speed"),
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
    //            "[MineralAxe] getAttributeModifiers: data={}, primary={} ratio={}",
    //            data != null, primary.getType().id, primary.getRatio());
//
    //    float damage = (MineralStatCalculator.BASE_AXE_DAMAGE
    //            + MineralStatCalculator.calcAttackDamageBonus(primary)) * 1.2f;
    //    float speed  = MineralStatCalculator.BASE_AXE_SPEED
    //            + MineralStatCalculator.calcAttackSpeedBonus(primary) * 0.7f;
//
    //    return ImmutableMultimap.of(
    //            Attributes.ATTACK_DAMAGE,
    //            new AttributeModifier(ATTACK_DAMAGE_UUID, "Mineral axe damage",
    //                    damage, AttributeModifier.Operation.ADD_VALUE),
    //            Attributes.ATTACK_SPEED,
    //            new AttributeModifier(ATTACK_SPEED_UUID, "Mineral axe speed",
    //                    speed, AttributeModifier.Operation.ADD_VALUE)
    //    );
    //}

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (!net.minecraft.tags.BlockTags.MINEABLE_WITH_AXE.equals(state.getBlock())) return 1.0f;
        ToolNBTHelper.ToolData data = AbstractMineralItem.getToolData(stack);
        MineralImpurity primary = data != null ? data.primaryOrDefault() : new MineralImpurity(ImpurityType.IRON_2, 0.3f);
        return MineralStatCalculator.calcMiningSpeed(primary);
    }

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
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        if (!net.minecraft.tags.BlockTags.MINEABLE_WITH_AXE.equals(state.getBlock())) return false;
        ToolNBTHelper.ToolData data = AbstractMineralItem.getToolData(stack);
        MineralImpurity primary = data != null ? data.primaryOrDefault() : new MineralImpurity(ImpurityType.IRON_2, 0.3f);
        int level = MineralStatCalculator.calcHarvestLevel(primary);
        if (state.is(net.minecraft.tags.BlockTags.NEEDS_DIAMOND_TOOL)) return level >= 3;
        if (state.is(net.minecraft.tags.BlockTags.NEEDS_IRON_TOOL))    return level >= 2;
        if (state.is(net.minecraft.tags.BlockTags.NEEDS_STONE_TOOL))   return level >= 1;
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context,
                                List<Component> tooltip, TooltipFlag flag) {
        ToolNBTHelper.ToolData data = AbstractMineralItem.getToolData(stack);
        MineralImpurity primary = data != null ? data.primaryOrDefault() : new MineralImpurity(ImpurityType.IRON_2, 0.3f);

        tooltip.add(Component.literal(MineralStatCalculator.formatStat(
                "攻撃力", (MineralStatCalculator.BASE_AXE_DAMAGE
                        + MineralStatCalculator.calcAttackDamageBonus(primary)) * 1.2f)));
        tooltip.add(Component.literal(MineralStatCalculator.formatStat(
                "採掘速度", MineralStatCalculator.calcMiningSpeed(primary))));
        tooltip.add(Component.literal(MineralStatCalculator.formatStatInt(
                "耐久", MineralStatCalculator.calcWeaponDurability(primary))));
        if (data != null) tooltip.add(Component.literal("§6素材: §f" + data.baseGem().displayName));
    }
}