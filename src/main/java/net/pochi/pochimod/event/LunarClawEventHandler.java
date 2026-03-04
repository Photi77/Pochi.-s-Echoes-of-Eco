package net.pochi.pochimod.event;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.item.custom.weapon.LunarClawBladeItem;
import net.pochi.pochimod.util.MoonPhaseUtil;

import net.minecraft.resources.ResourceLocation;

@EventBusSubscriber(modid = PochiMod.MOD_ID)
public class LunarClawEventHandler {

    private static final ResourceLocation ATTACK_DAMAGE_ID = ResourceLocation.fromNamespaceAndPath("pochimod", "lunar_claw_attack_damage");
    private static final ResourceLocation ATTACK_SPEED_ID = ResourceLocation.fromNamespaceAndPath("pochimod", "lunar_claw_attack_speed");
    private static final ResourceLocation MOVEMENT_SPEED_ID = ResourceLocation.fromNamespaceAndPath("pochimod", "lunar_claw_movement_speed");
    private static final ResourceLocation KNOCKBACK_RESISTANCE_ID = ResourceLocation.fromNamespaceAndPath("pochimod", "lunar_claw_knockback_resistance");

    /**
     * 繝励Ξ繧､繝､繝ｼ繝・ぅ繝・け譎ゅ・蜃ｦ逅・
     */
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (player.level().isClientSide) {
            return;
        }

        // 譛郁ｼｪ縺ｮ辷ｪ蛻・ｒ謖√▲縺ｦ縺・ｋ縺九メ繧ｧ繝・け
        ItemStack mainHand = player.getItemBySlot(EquipmentSlot.MAINHAND);
        if (!(mainHand.getItem() instanceof LunarClawBladeItem)) {
            // 謖√▲縺ｦ縺・↑縺・ｴ蜷医∝・縺ｦ縺ｮ菫ｮ豁｣繧貞炎髯､
            removeAllModifiers(player);
            return;
        }

        // 譛磯ｽ｢縺ｨ繝｢繝ｼ繝牙叙蠕・
        int moonPhase = MoonPhaseUtil.getMoonPhase(player.level());
        int mode = MoonPhaseUtil.getMode(moonPhase);
        boolean isNight = MoonPhaseUtil.isNightTime(player.level());

        // 繝｢繝ｼ繝峨↓蠢懊§縺溷柑譫懊ｒ驕ｩ逕ｨ
        applyModeEffects(player, mode, moonPhase, isNight);
    }

    /**
     * 繝｢繝ｼ繝峨↓蠢懊§縺溷柑譫懊ｒ驕ｩ逕ｨ
     */
    private static void applyModeEffects(Player player, int mode, int moonPhase, boolean isNight) {
        // 譌｢蟄倥・菫ｮ豁｣繧貞炎髯､
        removeAllModifiers(player);

        if (mode == 0) {
            // 貅譛域悄・壹ヱ繝ｯ繝ｼ繝｢繝ｼ繝・
            applyFullMoonEffects(player, moonPhase, isNight);
        } else if (mode == 1) {
            // 譁ｰ譛域悄・壹せ繝斐・繝峨Δ繝ｼ繝・
            applyNewMoonEffects(player, moonPhase, isNight);
        } else {
            // 遘ｻ陦梧悄・壼渕譛ｬ迥ｶ諷・
            applyTransitionEffects(player);
        }
    }

    /**
     * 貅譛域悄縺ｮ蜉ｹ譫・
     */
    private static void applyFullMoonEffects(Player player, int moonPhase, boolean isNight) {
        AttributeInstance attackDamage = player.getAttribute(Attributes.ATTACK_DAMAGE);
        AttributeInstance knockbackResistance = player.getAttribute(Attributes.KNOCKBACK_RESISTANCE);

        // 蝓ｺ譛ｬ謾ｻ謦・鴨: 9.0・域ｭｦ蝎ｨ縺ｮ蝓ｺ譛ｬ蛟､・・
        // 螟憺俣繝懊・繝翫せ
        if (isNight && attackDamage != null) {
            float nightBonus = moonPhase == 0 ? 3.0F : 2.0F;
            AttributeModifier modifier = new AttributeModifier(
                    ATTACK_DAMAGE_ID,
                    nightBonus,
                    AttributeModifier.Operation.ADD_VALUE
            );
            attackDamage.addTransientModifier(modifier);
        }

        // 繝弱ャ繧ｯ繝舌ャ繧ｯ閠先ｧ+30%
        if (knockbackResistance != null) {
            AttributeModifier modifier = new AttributeModifier(
                    KNOCKBACK_RESISTANCE_ID,
                    0.3,
                    AttributeModifier.Operation.ADD_VALUE
            );
            knockbackResistance.addTransientModifier(modifier);
        }
    }

    /**
     * 譁ｰ譛域悄縺ｮ蜉ｹ譫・
     */
    private static void applyNewMoonEffects(Player player, int moonPhase, boolean isNight) {
        AttributeInstance attackSpeed = player.getAttribute(Attributes.ATTACK_SPEED);
        AttributeInstance movementSpeed = player.getAttribute(Attributes.MOVEMENT_SPEED);

        // 謾ｻ謦・溷ｺｦ+25%
        if (attackSpeed != null) {
            AttributeModifier modifier = new AttributeModifier(
                    ATTACK_SPEED_ID,
                    0.25,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
            );
            attackSpeed.addTransientModifier(modifier);
        }

        // 螟憺俣繝懊・繝翫せ
        if (isNight) {
            // 遘ｻ蜍暮溷ｺｦ+20%
            if (movementSpeed != null) {
                AttributeModifier modifier = new AttributeModifier(
                        MOVEMENT_SPEED_ID,
                        0.20,
                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                );
                movementSpeed.addTransientModifier(modifier);
            }

            // 譁ｰ譛医・螟懶ｼ壼ｮ悟・騾乗・蛹厄ｼ域姶髣伜､厄ｼ・
            if (moonPhase == 4 && !isInCombat(player)) {
                player.addEffect(new MobEffectInstance(
                        MobEffects.INVISIBILITY,
                        40, // 2遘貞・・域ｯ師ick譖ｴ譁ｰ縺輔ｌ繧九・縺ｧ螳溯ｳｪ蟶ｸ譎ゑｼ・
                        0,
                        false,
                        false,
                        false
                ));
            }
        }
    }

    /**
     * 遘ｻ陦梧悄縺ｮ蜉ｹ譫懶ｼ亥渕譛ｬ迥ｶ諷具ｼ・
     */
    private static void applyTransitionEffects(Player player) {
        // 遘ｻ陦梧悄縺ｯ迚ｹ蛻･縺ｪ蜉ｹ譫懊↑縺・
        // 蝓ｺ譛ｬ謾ｻ謦・鴨7.5縺ｯ豁ｦ蝎ｨ縺ｮ蝓ｺ譛ｬ蛟､縺ｧ蟇ｾ蠢・
    }

    /**
     * 蜈ｨ縺ｦ縺ｮ菫ｮ豁｣繧貞炎髯､
     */
    private static void removeAllModifiers(Player player) {
        AttributeInstance attackDamage = player.getAttribute(Attributes.ATTACK_DAMAGE);
        AttributeInstance attackSpeed = player.getAttribute(Attributes.ATTACK_SPEED);
        AttributeInstance movementSpeed = player.getAttribute(Attributes.MOVEMENT_SPEED);
        AttributeInstance knockbackResistance = player.getAttribute(Attributes.KNOCKBACK_RESISTANCE);

        if (attackDamage != null) {
            AttributeModifier existing = attackDamage.getModifier(ATTACK_DAMAGE_ID);
            if (existing != null) {
                attackDamage.removeModifier(ATTACK_DAMAGE_ID);
            }
        }

        if (attackSpeed != null) {
            AttributeModifier existing = attackSpeed.getModifier(ATTACK_SPEED_ID);
            if (existing != null) {
                attackSpeed.removeModifier(ATTACK_SPEED_ID);
            }
        }

        if (movementSpeed != null) {
            AttributeModifier existing = movementSpeed.getModifier(MOVEMENT_SPEED_ID);
            if (existing != null) {
                movementSpeed.removeModifier(MOVEMENT_SPEED_ID);
            }
        }

        if (knockbackResistance != null) {
            AttributeModifier existing = knockbackResistance.getModifier(KNOCKBACK_RESISTANCE_ID);
            if (existing != null) {
                knockbackResistance.removeModifier(KNOCKBACK_RESISTANCE_ID);
            }
        }
    }

    /**
     * 謌ｦ髣倅ｸｭ縺九メ繧ｧ繝・け
     */
    private static boolean isInCombat(Player player) {
        // 譛霑代ム繝｡繝ｼ繧ｸ繧貞女縺代◆ or 荳弱∴縺・
        return player.getLastHurtMob() != null &&
                player.tickCount - player.getLastHurtMobTimestamp() < 100; // 5遘剃ｻ･蜀・
    }
}

