package net.pochi.pochimod.item.custom.armor;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;

import java.util.List;

public class LeopardGeckoArmor extends ArmorItem {

    //private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    // NBTタグ
    public static final String NUTRITION_TAG = "TailNutrition";
    public static final String REGENERATING_TAG = "IsRegenerating";
    public static final String REGEN_TICKS_TAG = "RegenTicks";

    // 定数
    public static final float MAX_NUTRITION = 20.0F;
    public static final int REGENERATION_DURATION = 1200; // 60秒
    public static final int AUTOTOMY_DURABILITY_COST = 50;
    public static final float AUTOTOMY_HP_THRESHOLD = 0.3F; // 30%

    // 移動速度ボーナス
    public static final float SPEED_BONUS_TIER1 = 0.05F;
    public static final float SPEED_BONUS_TIER2 = 0.10F;
    public static final float SPEED_BONUS_TIER3 = 0.20F;

    // 再生中のデバフ
    public static final float REGEN_SPEED_PENALTY = -0.20F;

    public LeopardGeckoArmor(Holder<ArmorMaterial> p_40386_, Type p_266831_, Properties p_40388_) {
        super(p_40386_, p_266831_, p_40388_);
    }
    private PlayState predicate(AnimationState animationState) {
        return PlayState.STOP;
    }

    //@Override
    //public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
    //    controllerRegistrar.add(new AnimationController(this, "controller", 0, this::predicate));
    //}
//
    //@Override
    //public AnimatableInstanceCache getAnimatableInstanceCache() {
    //    return cache;
    //}

    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (level.isClientSide) {
            return;
        }

        if (isRegenerating(stack)) {
            CompoundTag tag = getOrCreateCustomTag(stack);
            int regenTicks = tag.getInt(REGEN_TICKS_TAG) - 1;

            if (regenTicks <= 0) {
                tag.putBoolean(REGENERATING_TAG, false);
                tag.remove(REGEN_TICKS_TAG);
            } else {
                tag.putInt(REGEN_TICKS_TAG, regenTicks);
            }
            saveCustomTag(stack, tag);
        }
    }

    private static CompoundTag getOrCreateCustomTag(ItemStack stack) {
        CustomData cd = stack.get(DataComponents.CUSTOM_DATA);
        return cd != null ? cd.copyTag() : new CompoundTag();
    }

    private static void saveCustomTag(ItemStack stack, CompoundTag tag) {
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }

    /**
     * 栄養値を取得
     */
    public static float getNutrition(ItemStack stack) {
        CustomData cd = stack.get(DataComponents.CUSTOM_DATA);
        if (cd == null) return 0.0F;
        return cd.copyTag().getFloat(NUTRITION_TAG);
    }

    /**
     * 栄養値を設定
     */
    public static void setNutrition(ItemStack stack, float nutrition) {
        nutrition = Math.max(0.0F, Math.min(MAX_NUTRITION, nutrition));
        CompoundTag tag = getOrCreateCustomTag(stack);
        tag.putFloat(NUTRITION_TAG, nutrition);
        saveCustomTag(stack, tag);
    }

    /**
     * 栄養値を追加
     */
    public static void addNutrition(ItemStack stack, float amount) {
        float current = getNutrition(stack);
        setNutrition(stack, current + amount);
    }

    /**
     * 栄養値を消費
     */
    public static float consumeNutrition(ItemStack stack, float amount) {
        float current = getNutrition(stack);
        float consumed = Math.min(current, amount);
        setNutrition(stack, current - consumed);
        return consumed;
    }

    /**
     * 再生中かチェック
     */
    public static boolean isRegenerating(ItemStack stack) {
        CustomData cd = stack.get(DataComponents.CUSTOM_DATA);
        if (cd == null) return false;
        return cd.copyTag().getBoolean(REGENERATING_TAG);
    }

    /**
     * 再生開始
     */
    public static void startRegeneration(ItemStack stack) {
        CompoundTag tag = getOrCreateCustomTag(stack);
        tag.putBoolean(REGENERATING_TAG, true);
        tag.putInt(REGEN_TICKS_TAG, REGENERATION_DURATION);
        tag.putFloat(NUTRITION_TAG, 0.0F);
        saveCustomTag(stack, tag);
    }

    /**
     * 再生残り時間を取得（秒）
     */
    public static int getRemainingRegenSeconds(ItemStack stack) {
        if (!isRegenerating(stack)) {
            return 0;
        }
        CustomData cd = stack.get(DataComponents.CUSTOM_DATA);
        if (cd == null) return 0;
        return cd.copyTag().getInt(REGEN_TICKS_TAG) / 20;
    }

    /**
     * 移動速度ボーナスを取得
     */
    public static float getSpeedBonus(ItemStack stack) {
        if (isRegenerating(stack)) {
            return REGEN_SPEED_PENALTY;
        }

        float nutrition = getNutrition(stack);

        if (nutrition >= 15.0F) {
            return SPEED_BONUS_TIER3;
        } else if (nutrition >= 10.0F) {
            return SPEED_BONUS_TIER2;
        } else if (nutrition >= 5.0F) {
            return SPEED_BONUS_TIER1;
        }

        return 0.0F;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);

        float nutrition = getNutrition(stack);
        float percentage = (nutrition / MAX_NUTRITION) * 100;

        if (isRegenerating(stack)) {
            int seconds = getRemainingRegenSeconds(stack);
            tooltip.add(Component.literal("状態: 再生中 (" + seconds + "秒)")
                    .withStyle(ChatFormatting.RED));
            tooltip.add(Component.literal("栄養摂取: 不可").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("移動速度: -20%").withStyle(ChatFormatting.RED));
        } else {
            tooltip.add(Component.literal(String.format("尻尾栄養: %.1f/%.1f (%.0f%%)",
                            nutrition, MAX_NUTRITION, percentage))
                    .withStyle(ChatFormatting.YELLOW));

            float speedBonus = getSpeedBonus(stack);
            if (speedBonus > 0) {
                tooltip.add(Component.literal(String.format("移動速度: +%.0f%%", speedBonus * 100))
                        .withStyle(ChatFormatting.GREEN));
            }
        }

        tooltip.add(Component.literal(""));
        tooltip.add(Component.literal("特性: HP30%以下で自動発動")
                .withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.literal("またはShift長押しで手動発動")
                .withStyle(ChatFormatting.GOLD));
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return getNutrition(stack) >= MAX_NUTRITION;
    }


    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }


    //@Override
    //public void initializeClient(Consumer<IClientItemExtensions> consumer) {
    //    consumer.accept(new IClientItemExtensions() {
    //        private PerissoArmorRenderer renderer;
//
    //        @Override
    //        public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack,
    //                                                               EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
    //            if (this.renderer == null)
    //                this.renderer = new PerissoArmorRenderer();
//
    //            this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
    //            return this.renderer;
    //        }
    //    });
    //}
}
