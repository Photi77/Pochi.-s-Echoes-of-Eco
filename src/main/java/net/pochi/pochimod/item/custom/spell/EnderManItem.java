package net.pochi.pochimod.item.custom.spell;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class EnderManItem extends Item {
    public EnderManItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResult use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        ItemStack itemStack = p_41433_.getItemInHand(p_41434_);
        if(!p_41433_.isShiftKeyDown()) {
            if (!p_41432_.isClientSide()) {
                HitResult lookingAt = p_41433_.pick(100, 0.0F, true);
                p_41433_.teleportTo(lookingAt.getLocation().x, lookingAt.getLocation().y, lookingAt.getLocation().z);
                p_41433_.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
            }
        }
        p_41433_.getCooldowns().addCooldown(itemStack,200);
        return InteractionResult.FAIL;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack p_41398_, Player p_41399_, LivingEntity p_41400_, InteractionHand p_41401_) {
        ItemStack itemStack = p_41399_.getItemInHand(p_41401_);
        if(p_41399_.isShiftKeyDown()) {
            if (!p_41399_.level().isClientSide()) {
                p_41400_.discard();
                p_41400_.checkDespawn();
                if(p_41399_.level() instanceof ServerLevel level){
                    for (int l = 0; l < 20; l++) {
                        level.sendParticles(ParticleTypes.PORTAL,
                                p_41400_.getX(), p_41400_.getY(), p_41400_.getZ(), 5,
                                0, 0, 0, 2);
                    }
                }
            }
        }
        p_41399_.getCooldowns().addCooldown(p_41399_.getItemInHand(p_41401_),2000);
        return InteractionResult.FAIL;
    }
}
