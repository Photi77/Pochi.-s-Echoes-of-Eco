package net.pochi.pochimod.client.renderer;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.pochi.pochimod.mineral.MineralChunkItem;
import net.pochi.pochimod.mineral.tools.AbstractMineralItem;

/**
 * mineral系アイテムの動的カラーをtintIndex=0に適用するItemTintSource。
 * ツール/防具はToolNBTHelper、mineralChunkはMineralNBTHelperからカラーを読み込む。
 */
public class MineralTintSource implements ItemTintSource {

    public static final MineralTintSource INSTANCE = new MineralTintSource();
    public static final MapCodec<MineralTintSource> CODEC = MapCodec.unit(INSTANCE);

    private MineralTintSource() {}

    @Override
    public int calculate(ItemStack stack, ClientLevel level, LivingEntity entity) {
        // ツール/防具データ優先（ToolNBTHelper）
        int color = AbstractMineralItem.getItemColor(stack, 0);
        if (color != 0xFFFFFF) return color;
        // フォールバック: mineral_chunk データ（MineralNBTHelper）
        return MineralChunkItem.getItemColor(stack, 0);
    }

    @Override
    public MapCodec<? extends ItemTintSource> type() {
        return CODEC;
    }
}
