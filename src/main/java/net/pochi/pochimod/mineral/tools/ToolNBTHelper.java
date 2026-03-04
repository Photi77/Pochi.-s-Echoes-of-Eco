package net.pochi.pochimod.mineral.tools;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.pochi.pochimod.mineral.BaseGem;
import net.pochi.pochimod.mineral.ImpurityType;
import net.pochi.pochimod.mineral.MineralData;
import net.pochi.pochimod.mineral.MineralImpurity;

import java.util.ArrayList;
import java.util.List;

/**
 * ツール用NBT読み書きユーティリティ
 *
 * mineral_chunk と同じ impurities 構造を流用し、
 * tool_type フィールドを追加する。
 *
 * NBT構造:
 * {
 *   "tool_data": {
 *     "base_gem":   "crystal",
 *     "tool_type":  "sword",
 *     "color_hex":  "#C0392B",
 *     "impurities": [
 *       { "type": "chromium", "ratio": 0.87 },
 *       { "type": "titanium", "ratio": 0.85 }
 *     ]
 *   }
 * }
 */
public final class ToolNBTHelper {

    private ToolNBTHelper() {}

    // ---- NBTキー ----
    public static final String KEY_ROOT       = "tool_data";
    private static final String KEY_BASE_GEM  = "base_gem";
    private static final String KEY_TOOL_TYPE = "tool_type";
    private static final String KEY_COLOR_HEX = "color_hex";
    private static final String KEY_IMPURITIES = "impurities";
    private static final String KEY_IMP_TYPE  = "type";
    private static final String KEY_IMP_RATIO = "ratio";

    /**
     * ツール種別
     */
    public enum ToolType {
        SWORD, AXE, PICKAXE, SHOVEL,
        HELMET, CHESTPLATE, LEGGINGS, BOOTS,
        RING;

        public String id() { return name().toLowerCase(); }

        public static ToolType fromId(String id) {
            for (ToolType t : values()) {
                if (t.id().equals(id)) return t;
            }
            return SWORD;
        }
    }

    /**
     * ツールデータ（NBTから読み込んだ状態）
     */
    public record ToolData(
            BaseGem baseGem,
            ToolType toolType,
            String colorHex,
            List<MineralImpurity> impurities) {

        public MineralImpurity primary() {
            return impurities.isEmpty() ? null : impurities.get(0);
        }

        public MineralImpurity secondary() {
            return impurities.size() < 2 ? null : impurities.get(1);
        }

        /** 主不純物を返す。nullの場合はIRON_2 ratio=0.3のデフォルト値 */
        public MineralImpurity primaryOrDefault() {
            MineralImpurity p = primary();
            return p != null ? p : new MineralImpurity(ImpurityType.IRON_2, 0.3f);
        }

        /** 副不純物を返す。nullの場合はMANGANESE ratio=0.1のデフォルト値 */
        public MineralImpurity secondaryOrDefault() {
            MineralImpurity s = secondary();
            return s != null ? s : new MineralImpurity(ImpurityType.MANGANESE, 0.1f);
        }
    }

    // ==============================
    //  書き込み
    // ==============================

    /**
     * mineral_chunk の MineralData → ToolData として書き込む
     * クラフト完了時に呼ぶ
     */
    public static void writeFromMineralData(CompoundTag itemTag,
                                            MineralData mineralData,
                                            ToolType toolType) {
        CompoundTag root = new CompoundTag();

        root.putString(KEY_BASE_GEM,  mineralData.getBaseGem().id);
        root.putString(KEY_TOOL_TYPE, toolType.id());
        root.putString(KEY_COLOR_HEX, mineralData.getColorHex());

        ListTag impList = new ListTag();
        for (MineralImpurity imp : mineralData.getImpurities()) {
            CompoundTag impTag = new CompoundTag();
            impTag.putString(KEY_IMP_TYPE,  imp.getType().id);
            impTag.putFloat (KEY_IMP_RATIO, imp.getRatio());
            impList.add(impTag);
        }
        root.put(KEY_IMPURITIES, impList);

        itemTag.put(KEY_ROOT, root);
    }

    // ==============================
    //  読み込み
    // ==============================

    public static boolean hasToolData(ItemStack stack) {
        if (!stack.has(DataComponents.CUSTOM_DATA)) return false;
        return stack.get(DataComponents.CUSTOM_DATA).copyTag().contains(KEY_ROOT, Tag.TAG_COMPOUND);
    }

    public static boolean hasToolData(CompoundTag tag) {
        return tag.contains(KEY_ROOT, Tag.TAG_COMPOUND);
    }

    public static ToolData read(ItemStack stack) {
        if (!hasToolData(stack)) return null;
        return read(stack.get(DataComponents.CUSTOM_DATA).copyTag());
    }

    public static ToolData read(CompoundTag itemTag) {
        if (!hasToolData(itemTag)) return null;
        CompoundTag root = itemTag.getCompound(KEY_ROOT);

        BaseGem  baseGem  = BaseGem.fromId(root.getString(KEY_BASE_GEM));
        ToolType toolType = ToolType.fromId(root.getString(KEY_TOOL_TYPE));
        String   colorHex = root.getString(KEY_COLOR_HEX);

        List<MineralImpurity> impurities = new ArrayList<>();
        ListTag impList = root.getList(KEY_IMPURITIES, Tag.TAG_COMPOUND);
        for (int i = 0; i < impList.size(); i++) {
            CompoundTag impTag = impList.getCompound(i);
            ImpurityType type  = ImpurityType.fromId(impTag.getString(KEY_IMP_TYPE));
            float ratio        = impTag.getFloat(KEY_IMP_RATIO);
            impurities.add(new MineralImpurity(type, ratio));
        }

        return new ToolData(baseGem, toolType, colorHex, impurities);
    }
}
