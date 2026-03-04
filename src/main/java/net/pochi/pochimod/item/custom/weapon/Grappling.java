package net.pochi.pochimod.item.custom.weapon;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class Grappling extends Item {

    Vec3 viewPos;

    int length = 0;


    public Grappling(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        ItemStack itemStack = p_41433_.getItemInHand(p_41434_);
        if(!p_41433_.isShiftKeyDown()) {
            if (!p_41432_.isClientSide) {
                HitResult lookingAt = p_41433_.pick(100, 0.0F, true);
                if(length == 0) {
                    for(int i = 0; i <=3 ; i++) {
                        for (int j = 0; j <= 3; j++) {
                            for (int p = 0; p <= 3; p++) {
                                BlockPos blockPos = new BlockPos((int) (lookingAt.getLocation().x - 1 * i),
                                        (int) (lookingAt.getLocation().y - 1 * j), (int) (lookingAt.getLocation().z - 1 * p));
                                BlockState blockState = p_41432_.getBlockState(blockPos);
                                if(!(blockState == Blocks.AIR.defaultBlockState())) {
                                    setView(lookingAt);
                                    length = 1;
                                }
                            }
                        }
                    }
                } else if(length == 1 && !(viewPos == Vec3.ZERO)){
                    length = 0;
                    viewPos = Vec3.ZERO;
                }
            }
        }
        return InteractionResultHolder.fail(itemStack);
    }

    public void setView(HitResult result){
        viewPos = result.getLocation();
    }

    public Vec3 getView(){
        return viewPos;
    }


    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
        Player player = (Player) p_41406_;
        if(length > 0) {
            if (!p_41405_.isClientSide) {
                if(player.getMainHandItem() == p_41404_) {
                    if(p_41405_ instanceof ServerLevel level){
                        for (double l = 0; l < 100; l++) {
                            level.sendParticles(ParticleTypes.CRIT,
                                    player.getX() + (getView().x - player.getX()) * 1/l,
                                    player.getY() + 0.5 + (getView().y - player.getY()) * 1/l,
                                    player.getZ() + (getView().z - player.getZ()) * 1/l, 1,
                                    0, 0, 0, 0);
                        }
                    }

                    if (p_41406_.isShiftKeyDown()) {
                        player.addEffect(new MobEffectInstance(MobEffects.LEVITATION,3,1,true,false));
                        p_41406_.hurtMarked = true;
                        p_41406_.setDeltaMovement((getView().x - player.position().x) * (player.position().x + getView().x) / getView().x/20,
                                (getView().y - player.position().y) * (player.position().y + getView().y) / getView().y /20,
                                (getView().z - player.position().z) * (player.position().z + getView().z) / getView().z/20);
                    }
                }
            }
        }
    }
}
