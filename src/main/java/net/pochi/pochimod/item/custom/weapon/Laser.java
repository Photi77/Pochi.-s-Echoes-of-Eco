package net.pochi.pochimod.item.custom.weapon;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class Laser extends Item {
    public Laser(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        ItemStack itemStack = p_41433_.getItemInHand(p_41434_);
        if (!p_41432_.isClientSide) {
            HitResult lookingAt = p_41433_.pick(100, 0.0F, true);
            Entity result = Minecraft.getInstance().crosshairPickEntity;
            BlockPos blockPos = new BlockPos((int) lookingAt.getLocation().x, (int) lookingAt.getLocation().y, (int) lookingAt.getLocation().z);
            for(int i = 0; i <=36 ; i++){
                for(int j = 0; j <=36 ; j++) {
                    for (int p = 0; p <= 5; p++) {
                        BlockPos pos1 = BlockPos.containing(blockPos.getX() + p * Math.sin(i*10) * Math.cos(j*10),
                                blockPos.getY() + p * Math.sin(i*10) * Math.sin(j*10), blockPos.getZ() - p * Math.cos(i*10));
                        p_41432_.setBlock(pos1,Blocks.AIR.defaultBlockState(),11);

                        List<LivingEntity> list = p_41432_.getEntitiesOfClass(LivingEntity.class, p_41433_.getBoundingBox().inflate(lookingAt.getLocation().x,lookingAt.getLocation().y,lookingAt.getLocation().z));
                        if (!list.isEmpty()) {
                            for (LivingEntity livingentity : list) {
                                double d0 = lookingAt.getLocation().distanceToSqr(livingentity.getX(),livingentity.getY(),livingentity.getZ());
                                if(!(livingentity == p_41433_)) {
                                    if (d0 < 20.0D) {
                                        livingentity.kill();
                                        if(result.isAlive()) {
                                            livingentity.kill();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if(p_41432_ instanceof ServerLevel level){
                for (int l = 0; l < 1000; l++) {
                    level.sendParticles(new DustParticleOptions(Vec3.fromRGB24(16711680).toVector3f(),3),
                            p_41433_.getX() + p_41433_.getLookAngle().x * 2 * l, p_41433_.getY() + p_41433_.getLookAngle().y * 2 * l, p_41433_.getZ() + p_41433_.getLookAngle().z * 2 * l, 5,
                            0, 0, 0, 2);
                }
            }
        }

        return InteractionResultHolder.fail(itemStack);
    }
}
