package net.pochi.pochimod.block.custom.crops;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.BeetrootBlock;

public class CropLapis extends BeetrootBlock {
    public CropLapis(Properties p_49661_) {
        super(p_49661_);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return Items.LAPIS_LAZULI;
    }
}
