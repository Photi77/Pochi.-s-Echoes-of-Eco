package net.pochi.pochimod.mineral.tools;

import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.pochi.pochimod.mineral.MineralData;
import net.pochi.pochimod.mineral.MineralImpurity;

/**
 * NBT縺九ｉ蜍慕噪縺ｫTier繧呈ｧ狗ｯ峨☆繧九ヵ繧｡繧ｯ繝医Μ
 *
 * Minecraft 縺ｮ Tier 縺ｯ static final 縺ｧ螳夂ｾｩ縺輔ｌ繧九％縺ｨ繧貞燕謠舌→縺励◆險ｭ險医・縺溘ａ縲・ * 縲君BT縺九ｉ豈主屓逕滓・縺吶ｋ縲阪い繝励Ο繝ｼ繝√ｒ蜿悶ｋ縲・ *
 * getDestroySpeed() 縺ｨ getLevel() 繧貞虚逧・､縺ｧ霑斐☆ SimpleTier 繧堤函謌舌☆繧九・ */
public final class MineralToolTier {

    private MineralToolTier() {}

    /**
     * MineralData縺九ｉSimpleTier繧堤函謌・     *
     * @param data MineralData・・ull縺ｮ蝣ｴ蜷医・繝・ヵ繧ｩ繝ｫ繝亥､・・     * @return 蜍慕噪SimpleTier
     */
    public static SimpleTier from(MineralData data) {
        MineralImpurity primary = MineralStatCalculator.getPrimaryOrDefault(data);

        int   durability    = MineralStatCalculator.calcToolDurability(primary);
        float miningSpeed   = MineralStatCalculator.calcMiningSpeed(primary);
        float attackDamage  = MineralStatCalculator.calcAttackDamageBonus(primary); // 繝・・繝ｫ縺ｮ謾ｻ謦・｣懈ｭ｣
        int   harvestLevel  = MineralStatCalculator.calcHarvestLevel(primary);
        int   enchantability = (int)(primary.getRatio() * 15); // ratio鬮倥＞縺ｻ縺ｩ鬲疲ｳ穂ｻ倅ｸ弱＠繧・☆縺・
        return new SimpleTier(
                // isCorrectToolForDrops()縺ｧ蜍慕噪蛻､螳壹☆繧九◆繧√％縺薙・DIAMOND蝓ｺ貅悶ｒ繝・ヵ繧ｩ繝ｫ繝医→縺吶ｋ
                net.minecraft.tags.BlockTags.NEEDS_DIAMOND_TOOL,
                durability,
                miningSpeed,
                attackDamage,
                enchantability,
                () -> Ingredient.EMPTY
        );
    }
}

