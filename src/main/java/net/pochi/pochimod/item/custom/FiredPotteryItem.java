package net.pochi.pochimod.item.custom;


import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.pochi.pochimod.util.PotteryEffectCalculator;

import java.util.Map;

public class FiredPotteryItem extends Item {

    public FiredPotteryItem(Properties properties) {
        super(properties.stacksTo(1)); // スタック不可に変更
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (player.isCrouching()) { // スニーク + 右クリックで破壊
            if (!level.isClientSide) {
                CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
                if (customData != null) {
                    CompoundTag tag = customData.copyTag();
                    // エフェクトの計算
                    Map<Holder<MobEffect>, PotteryEffectCalculator.EffectData> effects =
                            PotteryEffectCalculator.calculateEffects(tag);

                    // エフェクトを付与
                    for (Map.Entry<Holder<MobEffect>, PotteryEffectCalculator.EffectData> entry : effects.entrySet()) {
                        Holder<MobEffect> effect = entry.getKey();
                        PotteryEffectCalculator.EffectData data = entry.getValue();

                        player.addEffect(new MobEffectInstance(
                                effect,
                                data.duration() * 20, // 秒からティックに変換
                                data.amplifier(),
                                false,
                                true,
                                true
                        ));
                    }

                    // パーティクルエフェクト
                    spawnBreakParticles((ServerLevel) level, player, tag);

                    // サウンド
                    level.playSound(null, player.getX(), player.getY(), player.getZ(),
                            SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);

                    // メッセージ表示
                    player.displayClientMessage(
                            Component.translatable("item.yourmod.pottery.shattered")
                                    .withStyle(ChatFormatting.GOLD),
                            true
                    );

                    // アイテムを消費
                    stack.shrink(1);
                }
            }

            return InteractionResultHolder.success(stack);
        } else {
            // 通常の右クリック（形状に応じた効果）
            if (!level.isClientSide) {
                CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
                if (customData != null) {
                    CompoundTag tag = customData.copyTag();
                    String shape = tag.getString("Shape");

                    switch (shape) {
                        case "CUP":
                            player.displayClientMessage(
                                    Component.translatable("item.yourmod.pottery.use.cup"),
                                    true
                            );
                            break;
                        case "BOWL":
                            player.displayClientMessage(
                                    Component.translatable("item.yourmod.pottery.use.bowl"),
                                    true
                            );
                            break;
                    }
                }
            }

            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
        }
    }

    private void spawnBreakParticles(ServerLevel level, Player player, CompoundTag tag) {
        int color = tag.getInt("GlazeColor");

        // 色からRGB値を抽出
        float r = ((color >> 16) & 0xFF) / 255f;
        float g = ((color >> 8) & 0xFF) / 255f;
        float b = (color & 0xFF) / 255f;

        // 陶器の大きさに応じてパーティクル数を変更
        int height = tag.getInt("Height");
        int diameter = tag.getInt("Diameter");
        int particleCount = (height + diameter) * 3;

        for (int i = 0; i < particleCount; i++) {
            double xOffset = (level.random.nextDouble() - 0.5) * 1.5;
            double yOffset = level.random.nextDouble() * 1.5;
            double zOffset = (level.random.nextDouble() - 0.5) * 1.5;

            // 色付きパーティクル（dust）
            level.sendParticles(
                    ParticleTypes.EXPLOSION,
                    player.getX() + xOffset,
                    player.getY() + yOffset + 0.5,
                    player.getZ() + zOffset,
                    1,
                    0, 0, 0, 0
            );

            // 破片パーティクル
            level.sendParticles(
                    ParticleTypes.ITEM_SNOWBALL,
                    player.getX() + xOffset,
                    player.getY() + yOffset + 0.5,
                    player.getZ() + zOffset,
                    1,
                    xOffset * 0.1, yOffset * 0.2, zOffset * 0.1, 0.1
            );
        }
    }

    //@Override
    //public void appendHoverText(ItemStack stack, @Nullable Level level,
    //                            List<Component> tooltip, TooltipFlag flag) {
    //    CompoundTag tag = stack.getTag();
    //    if (tag != null) {
    //        tooltip.add(Component.translatable("item.yourmod.pottery.shape",
    //                tag.getString("Shape")).withStyle(ChatFormatting.GOLD));
    //        tooltip.add(Component.translatable("item.yourmod.pottery.dimensions",
    //                        tag.getInt("Height"), tag.getInt("Diameter"))
    //                .withStyle(ChatFormatting.GRAY));
//
    //        // 色情報
    //        int color = tag.getInt("GlazeColor");
    //        tooltip.add(Component.translatable("item.yourmod.pottery.glaze")
    //                .withStyle(ChatFormatting.AQUA));
//
    //        // 保温性などのステータス
    //        int thickness = tag.getInt("WallThickness");
    //        if (thickness >= 4) {
    //            tooltip.add(Component.translatable("item.yourmod.pottery.insulation.high")
    //                    .withStyle(ChatFormatting.GREEN));
    //        } else if (thickness >= 2) {
    //            tooltip.add(Component.translatable("item.yourmod.pottery.insulation.medium")
    //                    .withStyle(ChatFormatting.YELLOW));
    //        } else {
    //            tooltip.add(Component.translatable("item.yourmod.pottery.insulation.low")
    //                    .withStyle(ChatFormatting.RED));
    //        }
//
    //        // 破壊時のエフェクト予告
    //        tooltip.add(Component.empty());
    //        tooltip.add(Component.translatable("item.yourmod.pottery.shatter_hint")
    //                .withStyle(ChatFormatting.ITALIC, ChatFormatting.DARK_PURPLE));
//
    //        // エフェクト計算とプレビュー
    //        Map<MobEffect, PotteryEffectCalculator.EffectData> effects =
    //                PotteryEffectCalculator.calculateEffects(tag);
//
    //        for (Map.Entry<MobEffect, PotteryEffectCalculator.EffectData> entry : effects.entrySet()) {
    //            MobEffect effect = entry.getKey();
    //            PotteryEffectCalculator.EffectData data = entry.getValue();
//
    //            String effectName = Component.translatable(effect.getDescriptionId()).getString();
    //            String level_roman = getRomanNumeral(data.amplifier() + 1);
//
    //            tooltip.add(Component.literal("  • " + effectName + " " + level_roman +
    //                            " (" + data.duration() + "s)")
    //                    .withStyle(ChatFormatting.LIGHT_PURPLE));
    //        }
    //    }
    //    super.appendHoverText(stack, level, tooltip, flag);
    //}

    private String getRomanNumeral(int number) {
        return switch (number) {
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            default -> String.valueOf(number);
        };
    }

    @Override
    public Component getName(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            CompoundTag tag = customData.copyTag();
            if (tag.contains("Shape")) {
                String shape = tag.getString("Shape").toLowerCase();
                return Component.translatable("item.yourmod.fired_pottery." + shape);
            }
        }
        return super.getName(stack);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData == null) return false;
        CompoundTag tag = customData.copyTag();
        return tag.getInt("GlazeColor") != 0xFFFFFF;
    }
}
