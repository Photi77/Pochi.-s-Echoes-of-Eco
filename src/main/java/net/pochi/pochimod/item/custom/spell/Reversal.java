package net.pochi.pochimod.item.custom.spell;

import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.effect.ModEffects;

import java.util.List;

public class Reversal extends Item {

    int time = 0;
    public Reversal(Properties p_41383_) {
        super(p_41383_);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        ItemStack itemStack = p_41433_.getItemInHand(p_41434_);
        if(!p_41432_.isClientSide){
            time = 10;
            releaseUsing(itemStack,p_41432_,p_41433_,3);
            List<LivingEntity> list = p_41432_.getEntitiesOfClass(LivingEntity.class, p_41433_.getBoundingBox().inflate(50, 50, 50));
            if (!list.isEmpty()) {
                for (LivingEntity entity : list) {
                    double d0 = p_41433_.distanceToSqr(entity.getX(), entity.getY(), entity.getZ());
                    if (d0 <50.0D) {
                        if (!(entity instanceof Player)) {
                            entity.addEffect(new MobEffectInstance(ModEffects.ARROW, 10000 ,1));
                        }
                    }
                }
            }
        }
        p_41433_.getCooldowns().addCooldown(this,1000);
        return InteractionResultHolder.fail(itemStack);
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
        if(p_41405_ instanceof ServerLevel serverLevel) {
            if (time-- > 0) {
                for (int l = 0; l < 360; l++) {
                    for (int k = 0; k < 10; k++) {
                        serverLevel.sendParticles(new DustColorTransitionOptions(Vec3.fromRGB24(12116536).toVector3f(), Vec3.fromRGB24(12116536).toVector3f(), 1f),
                                p_41406_.getX() + Math.sin(l / 2 % 40) * Math.cos(l / 2) * k/time, p_41406_.getY(), p_41406_.getZ() + Math.cos(l / 2 % 40) * Math.cos(l / 2) * k/time, 5,
                                0, 0, 0, 2);

                    }
                }
            }
        }
    }
}
