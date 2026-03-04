package net.pochi.pochimod.ferm;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.*;

// CropNutrientRegistry.java
// CropNutrientRegistry.java に収穫物→品質付与の対象を追加
public class CropNutrientRegistry {
    private static final Map<Block, CropNutrientWeight> WEIGHT_MAP = new HashMap<>();

    // 収穫物として品質NBTを付与するアイテムのセット
    private static final Set<Item> QUALITY_DROP_ITEMS = new HashSet<>();

    public static void register(Block crop, float wN, float wP, float wK,
                                Item... qualityDrops) {
        WEIGHT_MAP.put(crop, new CropNutrientWeight(wN, wP, wK));
        QUALITY_DROP_ITEMS.addAll(Arrays.asList(qualityDrops));
    }

    public static boolean shouldApplyQuality(Item item) {
        return QUALITY_DROP_ITEMS.contains(item);
    }

    public static CropNutrientWeight getWeight(Block crop) {
        return WEIGHT_MAP.getOrDefault(crop, CropNutrientWeight.DEFAULT);
    }

    public static void registerDefaults() {
        register(Blocks.WHEAT,    2f, 1f, 1f, Items.WHEAT);
        register(Blocks.CARROTS,  1f, 2f, 1f, Items.CARROT);
        register(Blocks.POTATOES, 1f, 2f, 1f, Items.POTATO, Items.POISONOUS_POTATO);
        register(Blocks.BEETROOTS,1f, 1f, 2f, Items.BEETROOT);
        // 自作作物はここに追加
    }
}


//---
//
//## 全体データフロー
//```
//土壌(Farmland) ChunkCapability
//N / P / K（0.0〜1.0）
//↓ 収穫イベント
//calcQuality(N,P,K, 重み)
//↓
//品質係数（0.5〜1.5）→ 作物ItemStackにNBT付与
//↓ 食べる時
//FoodNutritionData.scaled(quality) → 栄養ステータス反映
//↓ 収穫後
//土壌N/P/K を劣化（−0.1×重み）