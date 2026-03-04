package net.pochi.pochimod.ferm;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.neoforged.neoforge.common.util.INBTSerializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// SoilData.java（Attachmentで保持するデータ本体）
public class SoilData implements INBTSerializable<CompoundTag> {
    private final Map<Long, float[]> nutrientMap = new HashMap<>();
    // float[0]=N, float[1]=P, float[2]=K
    private boolean dirty = false;

    public float getValue(BlockPos pos, String key) {
        float[] nutrients = nutrientMap.getOrDefault(pos.asLong(), null);
        if (nutrients == null) return SoilNutrientHelper.DEFAULT;
        return switch (key) {
            case SoilNutrientHelper.KEY_NITROGEN   -> nutrients[0];
            case SoilNutrientHelper.KEY_PHOSPHORUS -> nutrients[1];
            case SoilNutrientHelper.KEY_POTASSIUM  -> nutrients[2];
            default -> SoilNutrientHelper.DEFAULT;
        };
    }

    public void setValue(BlockPos pos, String key, float value) {
        float[] nutrients = nutrientMap.computeIfAbsent(
                pos.asLong(), k -> new float[]{
                        SoilNutrientHelper.DEFAULT,
                        SoilNutrientHelper.DEFAULT,
                        SoilNutrientHelper.DEFAULT
                });
        switch (key) {
            case SoilNutrientHelper.KEY_NITROGEN   -> nutrients[0] = value;
            case SoilNutrientHelper.KEY_PHOSPHORUS -> nutrients[1] = value;
            case SoilNutrientHelper.KEY_POTASSIUM  -> nutrients[2] = value;
        }
        dirty = true;
    }

    public boolean isDirty() { return dirty; }
    public void setDirty()   { this.dirty = true; }
    public void clearDirty() { this.dirty = false; }

    // --- INBTSerializable (Attachment API) ---
    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        return serializeNBT();
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        deserializeNBT(tag);
    }

    // --- NBTシリアライズ ---
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        ListTag list = new ListTag();
        nutrientMap.forEach((posLong, npk) -> {
            CompoundTag entry = new CompoundTag();
            entry.putLong("pos", posLong);
            entry.putFloat("n", npk[0]);
            entry.putFloat("p", npk[1]);
            entry.putFloat("k", npk[2]);
            list.add(entry);
        });
        tag.put("soils", list);
        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        nutrientMap.clear();
        ListTag list = tag.getList("soils", Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag entry = list.getCompound(i);
            long posLong = entry.getLong("pos");
            float[] npk = {
                    entry.getFloat("n"),
                    entry.getFloat("p"),
                    entry.getFloat("k")
            };
            nutrientMap.put(posLong, npk);
        }
    }

    // SoilData.java に追加
    public Set<Long> getFarmlandPositions() {
        return nutrientMap.keySet();
    }
}
