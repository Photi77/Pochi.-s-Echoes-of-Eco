package net.pochi.pochimod.mineral;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * 鉱物NBTの読み書きユーティリティ
 *
 * NBT構造（確定仕様）:
 * {
 *   "base_mineral": "quartz",
 *   "impurities": [
 *     { "type": "iron_3",    "ratio": 0.73 },
 *     { "type": "manganese", "ratio": 0.21 }
 *   ],
 *   "color_hex": "#9B59B6",
 *   "origin_biome": "minecraft:mountains",
 *   "depth_level": 2
 * }
 */
public final class MineralNBTHelper {

    // ---- NBTキー定数 ----
    private static final String KEY_ROOT         = "mineral_data";
    private static final String KEY_BASE_MINERAL = "base_mineral";
    private static final String KEY_IMPURITIES   = "impurities";
    private static final String KEY_IMP_TYPE     = "type";
    private static final String KEY_IMP_RATIO    = "ratio";
    private static final String KEY_COLOR_HEX    = "color_hex";
    private static final String KEY_COMBO_BIOMES = "combo_biomes";  // 複数バイオーム（ListTag）
    private static final String KEY_DEPTH_LEVEL  = "depth_level";

    private MineralNBTHelper() {}

    // ==============================
    //  書き込み
    // ==============================

    /**
     * MineralDataをItemStackのNBTに書き込む
     */
    public static void write(CompoundTag tag, MineralData data) {
        CompoundTag root = new CompoundTag();

        root.putString(KEY_BASE_MINERAL, data.getBaseGem().id);
        root.putString(KEY_COLOR_HEX,    data.getColorHex());
        root.putInt   (KEY_DEPTH_LEVEL,  data.getDepthLevel());

        // コンボバイオームリスト（StringTag の ListTag）
        ListTag biomeList = new ListTag();
        for (ResourceLocation biome : data.getComboBiomes()) {
            biomeList.add(net.minecraft.nbt.StringTag.valueOf(biome.toString()));
        }
        root.put(KEY_COMBO_BIOMES, biomeList);

        // 不純物リスト
        ListTag impList = new ListTag();
        for (MineralImpurity imp : data.getImpurities()) {
            CompoundTag impTag = new CompoundTag();
            impTag.putString(KEY_IMP_TYPE,  imp.getType().id);
            impTag.putFloat (KEY_IMP_RATIO, imp.getRatio());
            impList.add(impTag);
        }
        root.put(KEY_IMPURITIES, impList);

        tag.put(KEY_ROOT, root);
    }

    // ==============================
    //  読み込み
    // ==============================

    /**
     * ItemStackのNBTからMineralDataを復元
     * NBTが存在しない場合はnullを返す
     */
    public static MineralData read(CompoundTag tag) {
        if (!tag.contains(KEY_ROOT, Tag.TAG_COMPOUND)) return null;

        CompoundTag root = tag.getCompound(KEY_ROOT);

        BaseGem baseGem    = BaseGem.fromId(root.getString(KEY_BASE_MINERAL));
        String colorHex    = root.getString(KEY_COLOR_HEX);
        int    depthLevel  = root.getInt(KEY_DEPTH_LEVEL);

        // コンボバイオームリスト
        List<ResourceLocation> comboBiomes = new ArrayList<>();
        ListTag biomeList = root.getList(KEY_COMBO_BIOMES, Tag.TAG_STRING);
        for (int i = 0; i < biomeList.size(); i++) {
            comboBiomes.add(ResourceLocation.parse(biomeList.getString(i)));
        }

        // 不純物リスト
        List<MineralImpurity> impurities = new ArrayList<>();
        ListTag impList = root.getList(KEY_IMPURITIES, Tag.TAG_COMPOUND);
        for (int i = 0; i < impList.size(); i++) {
            CompoundTag impTag = impList.getCompound(i);
            ImpurityType type  = ImpurityType.fromId(impTag.getString(KEY_IMP_TYPE));
            float ratio        = impTag.getFloat(KEY_IMP_RATIO);
            impurities.add(new MineralImpurity(type, ratio));
        }

        return new MineralData(baseGem, impurities, colorHex, comboBiomes, depthLevel);
    }

    /**
     * NBTにmineral_dataが含まれているか確認
     */
    public static boolean hasMineralData(CompoundTag tag) {
        return tag.contains(KEY_ROOT, Tag.TAG_COMPOUND);
    }

    // ==============================
    //  主不純物・副不純物アクセス
    // ==============================

    /**
     * 主不純物（ratio最大）を取得
     * impurities[0]が主不純物として定義されているため添字0を返す
     */
    public static MineralImpurity getPrimaryImpurity(MineralData data) {
        List<MineralImpurity> list = data.getImpurities();
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    /**
     * 副不純物（ratio低、エフェクト付与担当）を取得
     */
    public static MineralImpurity getSecondaryImpurity(MineralData data) {
        List<MineralImpurity> list = data.getImpurities();
        if (list.size() < 2) return null;
        return list.get(1);
    }
}
