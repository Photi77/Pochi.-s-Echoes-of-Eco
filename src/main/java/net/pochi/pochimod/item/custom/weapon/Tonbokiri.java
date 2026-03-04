package net.pochi.pochimod.item.custom.weapon;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class Tonbokiri extends Item {

    public double radius = 10;

    public double time = 10;

    public double jump = 10;

    public double crash = 10;
    public double area = 0;
    public boolean tataki = false;

    public int fall = 0;

    public Tonbokiri(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        ItemStack itemStack = p_41433_.getItemInHand(p_41434_);
        if(!p_41432_.isClientSide) {
            if (!p_41433_.isShiftKeyDown()) {
                radius = 0;
                time = 0;
                fall = 80;
                p_41433_.playNotifySound(SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundSource.PLAYERS, 1, 1);
                p_41433_.hurtMarked = true;
                p_41433_.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 10, 0, true, false));
                p_41433_.setDeltaMovement(p_41433_.getLookAngle().x * 5, p_41433_.getLookAngle().y * 5, p_41433_.getLookAngle().z * 5);
                p_41432_.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL,
                        p_41433_.getLookAngle(), Vec2.ZERO, (ServerLevel) p_41432_, 4, "", Component.literal(""), p_41432_.getServer(), null).withSuppressedOutput(), "/gamerule fallDamage false");
            } else {
                tataki = true;
                jump = 0;
                fall = 80;
                p_41433_.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 10, 50, true, false));
                p_41432_.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL,
                        p_41433_.getLookAngle(), Vec2.ZERO, (ServerLevel) p_41432_, 4, "", Component.literal(""), p_41432_.getServer(), null).withSuppressedOutput(), "/gamerule fallDamage false");
            }
        }
        return InteractionResultHolder.fail(itemStack);
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);

        if(!p_41405_.isClientSide) {
            if (fall-- <= 0) {
                p_41405_.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL,
                        p_41406_.getLookAngle(), Vec2.ZERO, (ServerLevel) p_41405_, 4, "", Component.literal(""), p_41405_.getServer(), null).withSuppressedOutput(), "/gamerule fallDamage true");
            }
        }

        //if(jump == 1) {
        //    if (p_41406_ instanceof AbstractClientPlayer player) {
        //        var animation = (ModifierLayer<IAnimation>) PlayerAnimationAccess.getPlayerAssociatedData(player).get(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "animation"));
        //        animation.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry.getAnimation(ResourceLocation.fromNamespaceAndPath("pochimod", "tataki"))));
        //    }
        //}

        if(tataki == true) {
            if (jump++ >= 60) {
                if (p_41406_ instanceof Player player) {
                    if(!player.onGround()) {
                        player.hurtMarked = true;
                        player.setDeltaMovement(player.getLookAngle().x * 0.5, -3, player.getLookAngle().z * 0.5);
                    } else {
                        player.playNotifySound(SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundSource.PLAYERS, 1, 1);
                        tataki = false;
                        crash = 0;
                    }
                }
            }
        }

        if (p_41406_ instanceof Player player) {
            if(player.onGround()) {
                if (crash++ <= 10) {
                    for (int l = 0; l < 360; l++) {
                        if (p_41405_ instanceof ServerLevel serverLevel) {
                            Vec3 vec3 = new Vec3(p_41406_.getX() + Math.sin(l) * crash/3, p_41406_.getY(), p_41406_.getZ() + Math.cos(l) * crash/3);
                            serverLevel.sendParticles(ParticleTypes.SWEEP_ATTACK, vec3.x, vec3.y, vec3.z, 2, 0, 0, 0, 0.1);
                            serverLevel.sendParticles(ParticleTypes.DRIPPING_WATER, vec3.x, vec3.y, vec3.z, 2, 0, 0, 0, 0.1);
                            serverLevel.sendParticles(ParticleTypes.CLOUD, vec3.x, vec3.y, vec3.z, 2, 0, 0, 0, 0);
                            serverLevel.sendParticles(new DustParticleOptions(Vec3.fromRGB24(4607).toVector3f(), 2), vec3.x, vec3.y, vec3.z, 1, 0, 0, 0, 0.2);
                            List<LivingEntity> list = serverLevel.getEntitiesOfClass(LivingEntity.class, p_41406_.getBoundingBox().inflate(100, 100, 100));
                            if (!list.isEmpty()) {
                                for (LivingEntity entity : list) {
                                       if (!(entity == p_41406_)) {
                                           double d0 = entity.distanceToSqr(vec3.x, vec3.y, vec3.z);
                                           if (d0 < 2) {
                                               entity.hurt(entity.damageSources().magic(), 5);
                                           }
                                       }
                                }
                            }
                        }
                    }
                }
            }
        }


        //if(radius == 1) {
        //    if (p_41406_ instanceof AbstractClientPlayer player) {
        //        var animation = (ModifierLayer<IAnimation>) PlayerAnimationAccess.getPlayerAssociatedData(player).get(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "animation"));
        //        animation.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry.getAnimation(ResourceLocation.fromNamespaceAndPath("pochimod", "tuki"))));
        //    }
        //}
        if(p_41405_ instanceof ServerLevel serverLevel) {
            //円錐広がる
            if (radius++ <= 20) {
                if(time++ <= 360) {
                    for (int i = 0; i <= 20; i++) {
                        Vec3 vec3 = new Vec3(p_41406_.getX() + p_41406_.getLookAngle().x * radius/2 + p_41406_.getLookAngle().z * radius/3 * Math.sin(time+time*i),
                                p_41406_.getY() + radius/3 * Math.cos(time+time*i),
                                p_41406_.getZ()+ p_41406_.getLookAngle().z * radius/2 - p_41406_.getLookAngle().x * radius/3 * Math.sin(time+time*i));
                        serverLevel.sendParticles(ParticleTypes.SWEEP_ATTACK, vec3.x, vec3.y, vec3.z, 2, 0, 0, 0, 0.1);
                        serverLevel.sendParticles(ParticleTypes.DRIPPING_WATER, vec3.x, vec3.y, vec3.z, 2, 0, 0, 0, 0.1);
                        serverLevel.sendParticles(ParticleTypes.CLOUD, vec3.x, vec3.y, vec3.z, 2, 0, 0, 0, 0);
                        serverLevel.sendParticles(new DustParticleOptions(Vec3.fromRGB24(4607).toVector3f(),2), vec3.x, vec3.y, vec3.z, 1, 0, 0, 0, 0.2);
                        List<LivingEntity> list = serverLevel.getEntitiesOfClass(LivingEntity.class, p_41406_.getBoundingBox().inflate(100, 100, 100));
                        if (!list.isEmpty()) {
                            for (LivingEntity entity : list) {
                                if (!(entity == p_41406_)) {
                                    double d0 = entity.distanceToSqr(vec3.x, vec3.y, vec3.z);
                                    if (d0 < 5) {
                                        entity.hurt(entity.damageSources().magic(), 5);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
