package net.pochi.pochimod.mineral;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.mineral.tools.AbstractMineralItem;
import net.pochi.pochimod.mineral.tools.MineralArmorItem;
import net.pochi.pochimod.mineral.tools.MineralStatCalculator;
import net.pochi.pochimod.mineral.tools.ToolNBTHelper;

/**
 * MineralChunk繧剃ｽｿ逕ｨ縺励◆繧｢繧､繝・Β縺ｮ繧ｨ繝輔ぉ繧ｯ繝井ｻ倅ｸ弱ワ繝ｳ繝峨Λ繝ｼ
 *
 * 莉倅ｸ取擅莉ｶ:
 *   - EQUIPPED: 髦ｲ蜈ｷ繧ｹ繝ｭ繝・ヨ縺ｫ陬・ｙ荳ｭ 竊・Tick豈弱↓邯咏ｶ壻ｻ倅ｸ・
 *   - HELD:     繝｡繧､繝ｳ繝上Φ繝峨∪縺溘・繧ｪ繝輔ワ繝ｳ繝峨〒謇区戟縺｡ 竊・Tick豈弱↓邯咏ｶ壻ｻ倅ｸ・
 *   - ON_HIT:   謾ｻ謦・ヲ繝・ヨ譎・竊・謨ｵ繧ｨ繝ｳ繝・ぅ繝・ぅ縺ｫ莉倅ｸ・
 *
 * 繧ｨ繝輔ぉ繧ｯ繝医Ξ繝吶Ν = (int)(ratio ﾃ・2)
 * 窶ｻ ratio < 0.5 縺縺ｨlevel=0 竊・莉倅ｸ弱＆繧後↑縺・
 *
 * 菴ｿ逕ｨ譁ｹ豕・
 *   Forge縺ｮ繧､繝吶Φ繝医ヰ繧ｹ縺ｫ逋ｻ骭ｲ:
 *   NeoForge.EVENT_BUS.register(new MineralEffectHandler());
 */
@EventBusSubscriber
public class MineralEffectHandler {

    // 繧ｨ繝輔ぉ繧ｯ繝医・邯咏ｶ壽凾髢難ｼ・ick・・- 豈撒ick莉倅ｸ弱↑縺ｮ縺ｧ遏ｭ繧√〒OK
    private static final int EFFECT_DURATION_TICKS = 40; // 2遘・

    // ==============================
    //  陬・ｙ譎ゅお繝輔ぉ繧ｯ繝茨ｼ・ivingEquipmentChangeEvent・・
    // ==============================

    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event) {
        // 陬・ｙ螟画峩譎ゅ・繧ｨ繝輔ぉ繧ｯ繝医ｒ蜊ｳ譎ゆｻ倅ｸ趣ｼ育ｶ咏ｶ壹・onLivingTick縺ｧ・・
        // 縺薙％縺ｧ縺ｯ荳ｻ縺ｫ莉倅ｸ・隗｣髯､縺ｮ繝医Μ繧ｬ繝ｼ縺ｨ縺励※菴ｿ逕ｨ
    }

    // ==============================
    //  Tick豈弱・繧ｨ繝輔ぉ繧ｯ繝育ｶｭ謖・ｼ・QUIPPED / HELD・・
    //  Forge縺ｮLivingTick繧､繝吶Φ繝医〒豈撒ick蜻ｼ縺ｳ蜃ｺ縺呎Φ螳・
    //  PlayerTickEvent 縺ｧ繧ゆｻ｣譖ｿ蜿ｯ閭ｽ
    // ==============================

    /**
     * Tick豈弱↓蜻ｼ縺ｳ蜃ｺ縺呻ｼ・layerTickEvent.Post 遲峨°繧牙他縺ｶ縺薙→・・
     */
    public static void onLivingTick(LivingEntity entity) {
        // ---- EQUIPPED: 髦ｲ蜈ｷ繧ｹ繝ｭ繝・ヨ ----
        for (net.minecraft.world.entity.EquipmentSlot slot : new net.minecraft.world.entity.EquipmentSlot[]{
                net.minecraft.world.entity.EquipmentSlot.HEAD,
                net.minecraft.world.entity.EquipmentSlot.CHEST,
                net.minecraft.world.entity.EquipmentSlot.LEGS,
                net.minecraft.world.entity.EquipmentSlot.FEET}) {
            applyEquippedEffect(entity, entity.getItemBySlot(slot));
        }

        // ---- HELD: 繝｡繧､繝ｳ繝上Φ繝・/ 繧ｪ繝輔ワ繝ｳ繝・----
        applyHeldEffect(entity, entity.getMainHandItem());
        applyHeldEffect(entity, entity.getOffhandItem());
    }

    private static void applyEquippedEffect(LivingEntity entity, ItemStack stack) {
        MineralData data = getMineralData(stack);
        if (data == null) return;

        MineralImpurity secondary = data.getSecondaryImpurity();
        if (secondary == null || !secondary.canApplyEffect()) return;
        if (secondary.getType().trigger != ImpurityType.EffectTrigger.EQUIPPED) return;

        entity.addEffect(new MobEffectInstance(
                secondary.getType().getMobEffect(),
                EFFECT_DURATION_TICKS,
                secondary.getEffectAmplifier(),
                false,  // ambient・医ン繝ｼ繧ｳ繝ｳ逕ｱ譚･縺九←縺・°・・
                true,   // visible particles
                true    // show icon
        ));
    }

    private static void applyHeldEffect(LivingEntity entity, ItemStack stack) {
        MineralData data = getMineralData(stack);
        if (data == null) return;

        MineralImpurity secondary = data.getSecondaryImpurity();
        if (secondary == null || !secondary.canApplyEffect()) return;
        if (secondary.getType().trigger != ImpurityType.EffectTrigger.HELD) return;

        entity.addEffect(new MobEffectInstance(
                secondary.getType().getMobEffect(),
                EFFECT_DURATION_TICKS,
                secondary.getEffectAmplifier(),
                false,
                true,
                true
        ));
    }

    // ==============================
    //  謾ｻ謦・凾繧ｨ繝輔ぉ繧ｯ繝茨ｼ・N_HIT 竊・謨ｵ縺ｫ莉倅ｸ趣ｼ・
    // ==============================

    @SubscribeEvent
    public static void onLivingAttack(LivingIncomingDamageEvent event) {
        if (!(event.getSource().getEntity() instanceof LivingEntity attacker)) return;

        ItemStack mainHand = attacker.getMainHandItem();
        applyOnHitEffect(event.getEntity(), mainHand);
    }

    private static void applyOnHitEffect(LivingEntity target, ItemStack attackerStack) {
        MineralData data = getMineralData(attackerStack);
        if (data == null) return;

        MineralImpurity secondary = data.getSecondaryImpurity();
        if (secondary == null || !secondary.canApplyEffect()) return;
        if (secondary.getType().trigger != ImpurityType.EffectTrigger.ON_HIT) return;

        // 謨ｵ縺ｫ蟇ｾ縺励※繧ｨ繝輔ぉ繧ｯ繝井ｻ倅ｸ・
        target.addEffect(new MobEffectInstance(
                secondary.getType().getMobEffect(),
                60 + secondary.getEffectAmplifier() * 20, // duration縺ｯ蜉ｹ譫懊Ξ繝吶Ν縺ｫ蠢懊§縺ｦ螟牙喧
                secondary.getEffectAmplifier(),
                false,
                true,
                true
        ));
    }

    // ==============================
    //  MineralArmorItem 動的属性
    // ==============================

    @SubscribeEvent
    public static void onItemAttributeModifiers(ItemAttributeModifierEvent event) {
        ItemStack stack = event.getItemStack();
        if (!(stack.getItem() instanceof MineralArmorItem armorItem)) return;

        int slotIndex = armorItem.getSlotIndex();
        ToolNBTHelper.ToolData data = AbstractMineralItem.getToolData(stack);
        MineralImpurity primary   = MineralStatCalculator.getPrimaryOrDefault(data);
        MineralImpurity secondary = data != null ? data.secondary() : null;

        int armorValue   = MineralStatCalculator.BASE_ARMOR_VALUES[slotIndex]
                + MineralStatCalculator.calcArmorBonus(primary, secondary);
        float toughness  = MineralStatCalculator.calcToughnessBonus(primary, secondary);
        float knockback  = MineralStatCalculator.calcKnockbackResistance(primary, secondary);

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(armorItem.getEquipmentSlot(stack));

        event.addModifier(Attributes.ARMOR,
                new AttributeModifier(
                        Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "mineral_armor_" + slotIndex),
                        armorValue, AttributeModifier.Operation.ADD_VALUE), slotGroup);
        event.addModifier(Attributes.ARMOR_TOUGHNESS,
                new AttributeModifier(
                        Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "mineral_toughness_" + slotIndex),
                        toughness, AttributeModifier.Operation.ADD_VALUE), slotGroup);
        if (knockback > 0f) {
            event.addModifier(Attributes.KNOCKBACK_RESISTANCE,
                    new AttributeModifier(
                            Identifier.fromNamespaceAndPath(PochiMod.MOD_ID, "mineral_kb_" + slotIndex),
                            knockback, AttributeModifier.Operation.ADD_VALUE), slotGroup);
        }
    }

    // ==============================
    //  ユーティリティ
    // ==============================

    private static MineralData getMineralData(ItemStack stack) {
        if (stack.isEmpty()) return null;
        if (!stack.has(DataComponents.CUSTOM_DATA)) return null;
        CompoundTag tag = stack.get(DataComponents.CUSTOM_DATA).copyTag();
        if (!MineralNBTHelper.hasMineralData(tag)) return null;
        return MineralNBTHelper.read(tag);
    }
}

