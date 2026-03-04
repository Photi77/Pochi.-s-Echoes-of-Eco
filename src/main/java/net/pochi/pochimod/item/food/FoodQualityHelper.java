package net.pochi.pochimod.item.food;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

public class FoodQualityHelper {
    public static final String NBT_KEY = "nutrition_quality";
    public static final float DEFAULT_QUALITY = 1.0f;
    public static final float MIN_QUALITY = 0.5f;
    public static final float MAX_QUALITY = 1.5f;

    public static float getQuality(ItemStack stack) {
        CustomData cd = stack.get(DataComponents.CUSTOM_DATA);
        if (cd == null) return DEFAULT_QUALITY;
        CompoundTag tag = cd.copyTag();
        if (!tag.contains(NBT_KEY, Tag.TAG_FLOAT)) return DEFAULT_QUALITY;
        return Mth.clamp(tag.getFloat(NBT_KEY), MIN_QUALITY, MAX_QUALITY);
    }

    public static void setQuality(ItemStack stack, float quality) {
        CompoundTag tag = stack.has(DataComponents.CUSTOM_DATA)
                ? stack.get(DataComponents.CUSTOM_DATA).copyTag()
                : new CompoundTag();
        tag.putFloat(NBT_KEY, Mth.clamp(quality, MIN_QUALITY, MAX_QUALITY));
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }

    // ツールチップ用ランク取得
    public static QualityRank getRank(float quality) {
        if (quality >= 1.3f) return QualityRank.EXCELLENT;
        if (quality >= 1.1f) return QualityRank.GOOD;
        if (quality >= 0.9f) return QualityRank.NORMAL;
        return QualityRank.POOR;
    }

    public enum QualityRank {
        POOR     ("粗悪", 0xFF5555),
        NORMAL   ("普通", 0xAAAAAA),
        GOOD     ("良品", 0x55FF55),
        EXCELLENT("上質", 0xFFAA00);

        public final String label;
        public final int color;
        QualityRank(String label, int color) {
            this.label = label;
            this.color = color;
        }
    }
}
