package net.pochi.pochimod.block.custom.crops;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.BeetrootBlock;
import net.pochi.pochimod.item.ModItems;

public class OnionBlock extends BeetrootBlock {
    public OnionBlock(Properties p_49661_) {
        super(p_49661_);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.ONION_SEEDS.get();
    }
}
