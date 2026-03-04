package net.pochi.pochimod.event;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.projectile.DecoyTailEntity;
import net.pochi.pochimod.item.custom.armor.LeopardGeckoArmor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EventBusSubscriber(modid = PochiMod.MOD_ID)
public class LeopardGeckoArmorEventHandler {

    private static final net.minecraft.resources.ResourceLocation SPEED_MODIFIER_ID = net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("pochimod", "leopard_gecko_speed");

    // 謇句虚逋ｺ蜍慕畑・售hift髟ｷ謚ｼ縺玲凾髢薙ｒ險倬鹸
    private static final Map<UUID, Integer> SHIFT_HOLD_TICKS = new HashMap<>();
    private static final int MANUAL_ACTIVATION_TICKS = 40; // 2遘・

    /**
     * 鬟滉ｺ句ｮ御ｺ・凾縺ｮ蜃ｦ逅・
     */
    @SubscribeEvent
    public static void onItemUseFinish(LivingEntityUseItemEvent.Finish event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        if (player.level().isClientSide) {
            return;
        }

        ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
        if (!(leggings.getItem() instanceof LeopardGeckoArmor)) {
            return;
        }

        if (LeopardGeckoArmor.isRegenerating(leggings)) {
            return;
        }

        ItemStack foodItem = event.getItem();
        if (!foodItem.has(net.minecraft.core.component.DataComponents.FOOD)) {
            return;
        }

        FoodData foodData = player.getFoodData();
        if (foodData.getFoodLevel() >= 20) {
            net.minecraft.world.food.FoodProperties foodProps = foodItem.getFoodProperties(player);
            float foodValue = foodProps != null ? (float) foodProps.nutrition() : 0;
            LeopardGeckoArmor.addNutrition(leggings, foodValue);

            player.displayClientMessage(
                    net.minecraft.network.chat.Component.literal(
                            String.format("蟆ｻ蟆ｾ縺ｫ譬・､願塘遨・ +%.1f (蜷郁ｨ・ %.1f/%.1f)",
                                    foodValue,
                                    LeopardGeckoArmor.getNutrition(leggings),
                                    LeopardGeckoArmor.MAX_NUTRITION)
                    ).withStyle(net.minecraft.ChatFormatting.YELLOW),
                    true
            );
        }
    }

    /**
     * 繝繝｡繝ｼ繧ｸ繧貞女縺代◆譎ゅ・閾ｪ蜍慕匱蜍輔メ繧ｧ繝・け
     */
    @SubscribeEvent
    public static void onPlayerAttacked(LivingIncomingDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        if (player.level().isClientSide) {
            return;
        }

        ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
        if (!(leggings.getItem() instanceof LeopardGeckoArmor)) {
            return;
        }

        // HP30%莉･荳九°繝√ぉ繝・け
        float healthPercentage = player.getHealth() / player.getMaxHealth();
        if (healthPercentage > LeopardGeckoArmor.AUTOTOMY_HP_THRESHOLD) {
            return;
        }

        // 閾ｪ蛻・匱蜍・
        if (performAutotomy(player, leggings, false)) {
            // 繝繝｡繝ｼ繧ｸ繧偵く繝｣繝ｳ繧ｻ繝ｫ・育┌謨ｵ蜉ｹ譫懊〒・・
            event.setCanceled(true);
        }
    }

    /**
     * 繝励Ξ繧､繝､繝ｼ繝・ぅ繝・け譎ゅ・蜃ｦ逅・
     */
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (player.level().isClientSide) {
            return;
        }

        ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
        if (!(leggings.getItem() instanceof LeopardGeckoArmor)) {
            removeSpeedModifier(player);
            SHIFT_HOLD_TICKS.remove(player.getUUID());
            return;
        }

        // 貅閻ｹ蠎ｦ閾ｪ蜍戊｣懷・
        FoodData foodData = player.getFoodData();
        if (foodData.getFoodLevel() < 20) {
            float nutrition = LeopardGeckoArmor.getNutrition(leggings);
            if (nutrition > 0) {
                float toRestore = Math.min(0.05F, nutrition);
                float consumed = LeopardGeckoArmor.consumeNutrition(leggings, toRestore);

                if (consumed > 0) {
                    foodData.setFoodLevel(Math.min(20, foodData.getFoodLevel() + 1));
                }
            }
        }

        // 遘ｻ蜍暮溷ｺｦ菫ｮ豁｣繧呈峩譁ｰ
        updateSpeedModifier(player, leggings);

        // 謇句虚逋ｺ蜍輔メ繧ｧ繝・け・・hift髟ｷ謚ｼ縺暦ｼ・
        if (!LeopardGeckoArmor.isRegenerating(leggings)) {
            checkManualActivation(player, leggings);
        }
    }

    /**
     * 謇句虚逋ｺ蜍輔メ繧ｧ繝・け・・hift髟ｷ謚ｼ縺暦ｼ・
     */
    private static void checkManualActivation(Player player, ItemStack belt) {
        UUID playerUUID = player.getUUID();

        if (player.isShiftKeyDown()) {
            // Shift謚ｼ縺励※縺・ｋ譎る俣繧偵き繧ｦ繝ｳ繝・
            int ticks = SHIFT_HOLD_TICKS.getOrDefault(playerUUID, 0);
            ticks++;
            SHIFT_HOLD_TICKS.put(playerUUID, ticks);

            // 騾ｲ謐苓｡ｨ遉ｺ・・.5遘偵＃縺ｨ・・
            if (ticks % 10 == 0 && ticks < MANUAL_ACTIVATION_TICKS) {
                player.displayClientMessage(
                        net.minecraft.network.chat.Component.literal(
                                String.format("閾ｪ蛻・ｺ門ｙ荳ｭ... %.1f遘・", (MANUAL_ACTIVATION_TICKS - ticks) / 20.0)
                        ).withStyle(net.minecraft.ChatFormatting.GOLD),
                        true
                );
            }

            // 2遘堤ｵ碁℃縺ｧ逋ｺ蜍・
            if (ticks >= MANUAL_ACTIVATION_TICKS) {
                performAutotomy(player, belt, true);
                SHIFT_HOLD_TICKS.remove(playerUUID);
            }
        } else {
            // Shift繧帝屬縺励◆繧峨Μ繧ｻ繝・ヨ
            SHIFT_HOLD_TICKS.remove(playerUUID);
        }
    }

    /**
     * 蟆ｻ蟆ｾ閾ｪ蛻・ｒ螳溯｡・
     */
    private static boolean performAutotomy(Player player, ItemStack belt, boolean isManual) {
        // 蜀咲函荳ｭ縺ｯ逋ｺ蜍穂ｸ榊庄
        if (LeopardGeckoArmor.isRegenerating(belt)) {
            if (isManual) {
                player.displayClientMessage(
                        net.minecraft.network.chat.Component.literal("蟆ｻ蟆ｾ蜀咲函荳ｭ縺ｯ菴ｿ逕ｨ縺ｧ縺阪∪縺帙ｓ")
                                .withStyle(net.minecraft.ChatFormatting.RED),
                        true
                );
            }
            return false;
        }

        // 閠蝉ｹ・､繝√ぉ繝・け
        if (belt.getDamageValue() + LeopardGeckoArmor.AUTOTOMY_DURABILITY_COST > belt.getMaxDamage()) {
            if (isManual) {
                player.displayClientMessage(
                        net.minecraft.network.chat.Component.literal("閠蝉ｹ・､縺御ｸ崎ｶｳ縺励※縺・∪縺・")
                                .withStyle(net.minecraft.ChatFormatting.RED),
                        true
                );
            }
            return false;
        }

        ServerLevel level = (ServerLevel) player.level();

        // 蝗ｮ縺ｮ蟆ｻ蟆ｾ繧堤函謌・
        Vec3 tailPos = player.position();
        DecoyTailEntity decoyTail = new DecoyTailEntity(level, player, tailPos);
        level.addFreshEntity(decoyTail);

        // 闢・ｩ肴・､企㍼繧貞叙蠕暦ｼ医Γ繝・そ繝ｼ繧ｸ逕ｨ・・
        float nutrition = LeopardGeckoArmor.getNutrition(belt);

        // 蜉ｹ譫憺←逕ｨ
        // 1. 菴灘鴨蜈ｨ蝗槫ｾｩ
        player.setHealth(player.getMaxHealth());

        // 2. 辟｡謨ｵ蜉ｹ譫懶ｼ・遘抵ｼ・
        player.addEffect(new MobEffectInstance(
                MobEffects.DAMAGE_RESISTANCE,
                60, // 3遘・
                255, // 繝ｬ繝吶Ν255縺ｧ螳溯ｳｪ辟｡謨ｵ
                false,
                false,
                true
        ));

        // 3. 遘ｻ蜍暮溷ｺｦ繝悶・繧ｹ繝茨ｼ・遘偵・50%・・
        player.addEffect(new MobEffectInstance(
                MobEffects.MOVEMENT_SPEED,
                100, // 5遘・
                2, // 繝ｬ繝吶Ν3 = +60%・郁ｿ台ｼｼ・・
                false,
                true,
                true
        ));

        // 4. 蜀咲函髢句ｧ・
        LeopardGeckoArmor.startRegeneration(belt);

        // 5. 閠蝉ｹ・､豸郁ｲｻ
        belt.hurtAndBreak(
                LeopardGeckoArmor.AUTOTOMY_DURABILITY_COST,
                player,
                EquipmentSlot.LEGS
        );

        // 繧ｨ繝輔ぉ繧ｯ繝・
        spawnAutotomyEffects(level, player);

        // 繝｡繝・そ繝ｼ繧ｸ
        player.displayClientMessage(
                net.minecraft.network.chat.Component.literal(
                        String.format("蟆ｻ蟆ｾ閾ｪ蛻・匱蜍包ｼ・譬・､・.1f繧呈ｶ郁ｲｻ縺励※菴灘鴨蜈ｨ蝗槫ｾｩ", nutrition)
                ).withStyle(net.minecraft.ChatFormatting.GREEN),
                true
        );

        return true;
    }

    /**
     * 閾ｪ蛻・凾縺ｮ繧ｨ繝輔ぉ繧ｯ繝・
     */
    private static void spawnAutotomyEffects(ServerLevel level, Player player) {
        // 蟆ｻ蟆ｾ縺悟・繧後ｋ繝代・繝・ぅ繧ｯ繝ｫ
        level.sendParticles(
                ParticleTypes.CLOUD,
                player.getX(), player.getY() + 0.5, player.getZ(),
                20,
                0.3, 0.3, 0.3,
                0.1
        );

        // 蝗槫ｾｩ繝代・繝・ぅ繧ｯ繝ｫ
        level.sendParticles(
                ParticleTypes.HAPPY_VILLAGER,
                player.getX(), player.getY() + 1.0, player.getZ(),
                30,
                0.5, 1.0, 0.5,
                0.1
        );

        // 辟｡謨ｵ繧ｨ繝輔ぉ繧ｯ繝・
        level.sendParticles(
                ParticleTypes.TOTEM_OF_UNDYING,
                player.getX(), player.getY() + 1.0, player.getZ(),
                50,
                0.5, 1.0, 0.5,
                0.2
        );

        // 髻ｳ
        level.playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.TOTEM_USE,
                SoundSource.PLAYERS,
                1.0F,
                1.0F
        );

        level.playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.ENDER_DRAGON_FLAP,
                SoundSource.PLAYERS,
                0.5F,
                2.0F
        );
    }

    /**
     * 遘ｻ蜍暮溷ｺｦ菫ｮ豁｣繧呈峩譁ｰ
     */
    private static void updateSpeedModifier(Player player, ItemStack belt) {
        AttributeInstance movementSpeed = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if (movementSpeed == null) {
            return;
        }

        AttributeModifier existing = movementSpeed.getModifier(SPEED_MODIFIER_ID);
        if (existing != null) {
            movementSpeed.removeModifier(SPEED_MODIFIER_ID);
        }

        float speedBonus = LeopardGeckoArmor.getSpeedBonus(belt);
        if (speedBonus != 0) {
            AttributeModifier modifier = new AttributeModifier(
                    SPEED_MODIFIER_ID,
                    speedBonus,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
            );
            movementSpeed.addTransientModifier(modifier);
        }
    }

    /**
     * 遘ｻ蜍暮溷ｺｦ菫ｮ豁｣繧貞炎髯､
     */
    private static void removeSpeedModifier(Player player) {
        AttributeInstance movementSpeed = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if (movementSpeed != null) {
            AttributeModifier existing = movementSpeed.getModifier(SPEED_MODIFIER_ID);
            if (existing != null) {
                movementSpeed.removeModifier(SPEED_MODIFIER_ID);
            }
        }
    }
}

