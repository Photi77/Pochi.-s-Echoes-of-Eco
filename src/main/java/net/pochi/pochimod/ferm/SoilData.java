package net.pochi.pochimod.ferm;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.util.ValueIOSerializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// SoilData.java（Attachmentで保持するデータ本体）
public class SoilData implements ValueIOSerializable {
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

    // --- ValueIOSerializable (Attachment API) ---
    @Override
    public void serialize(ValueOutput output) {
        ValueOutput.ValueOutputList list = output.childrenList("soils");
        nutrientMap.forEach((posLong, npk) -> {
            ValueOutput entry = list.addChild();
            entry.putLong("pos", posLong);
            entry.putFloat("n", npk[0]);
            entry.putFloat("p", npk[1]);
            entry.putFloat("k", npk[2]);
        });
    }

    @Override
    public void deserialize(ValueInput input) {
        nutrientMap.clear();
        input.childrenListOrEmpty("soils").forEach(entry -> {
            long posLong = entry.getLongOr("pos", 0L);
            float[] npk = {
                    entry.getFloatOr("n", 0.0f),
                    entry.getFloatOr("p", 0.0f),
                    entry.getFloatOr("k", 0.0f)
            };
            nutrientMap.put(posLong, npk);
        });
    }

    // SoilData.java に追加
    public Set<Long> getFarmlandPositions() {
        return nutrientMap.keySet();
    }
}
