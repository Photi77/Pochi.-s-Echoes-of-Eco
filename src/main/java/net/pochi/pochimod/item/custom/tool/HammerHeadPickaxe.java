package net.pochi.pochimod.item.custom.tool;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.pochi.pochimod.entity.ModEntityTypes;
import net.pochi.pochimod.entity.projectile.OreSlime;

public class HammerHeadPickaxe extends PickaxeItem {
    public HammerHeadPickaxe(Tier p_42961_, Properties p_42964_) {
        super(p_42961_, p_42964_);
    }

    //||p_41432_.getBlockState(pos).is(Blocks.DEEPSLATE_IRON_ORE)|| p_41432_.getBlockState(pos).is(Blocks.IRON_ORE)
    //                        ||p_41432_.getBlockState(pos).is(Blocks.DEEPSLATE_GOLD_ORE)|| p_41432_.getBlockState(pos).is(Blocks.GOLD_ORE)
    //                        ||p_41432_.getBlockState(pos).is(Blocks.DEEPSLATE_EMERALD_ORE)|| p_41432_.getBlockState(pos).is(Blocks.EMERALD_ORE)

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        ItemStack itemStack = p_41433_.getItemInHand(p_41434_);
        for(int x = -50;x <= 50;x++){
            for(int y = -50;y <= 50;y++){
                for(int z = -50;z <= 50;z++){
                    BlockPos pos = BlockPos.containing(p_41433_.getX()+x,p_41433_.getY()+y,p_41433_.getZ()+z);
                    if(p_41432_.getBlockState(pos).is(Blocks.DIAMOND_ORE) || p_41432_.getBlockState(pos).is(Blocks.DEEPSLATE_DIAMOND_ORE)){
                        OreSlime dirtGolem = ModEntityTypes.ORE_SEARCH.get().create(p_41432_);
                        dirtGolem.moveTo(pos.getX()+0.5, pos.getY(), pos.getZ()+0.5);
                        p_41432_.addFreshEntity(dirtGolem);
                    }
                }
            }
        }
        return InteractionResultHolder.fail(itemStack);
    }
}
