package net.pochi.pochimod.item.custom.spell;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import java.util.List;

public class SoulSandItem extends Item {

    int killTime = 60;

    public SoulSandItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        Player player = p_41433_;
        ItemStack itemstack = p_41433_.getItemInHand(p_41434_);
        if (player != null) {
            player.startUsingItem(p_41434_);
        }
        player.getCooldowns().addCooldown(this,1000);
        return InteractionResultHolder.pass(itemstack);
    }

    @Override
    public int getUseDuration(ItemStack p_41454_, LivingEntity entity) {
        return 100;
    }

    @Override
    public void releaseUsing(ItemStack p_41412_, Level p_41413_, LivingEntity p_41414_, int p_41415_) {
        super.releaseUsing(p_41412_, p_41413_, p_41414_, p_41415_);
    }

    @Override
    public void onUseTick(Level p_41428_, LivingEntity p_41429_, ItemStack p_41430_, int p_41431_) {
        List<LivingEntity> list = p_41429_.level().getEntitiesOfClass(LivingEntity.class, p_41429_.getBoundingBox().inflate(4.0D, 2.0D, 4.0D));
        if (p_41431_ >= 0 && p_41429_ instanceof Player player) {
            if (!p_41428_.isClientSide) {
                if (!list.isEmpty()) {
                    for (LivingEntity livingentity : list) {
                        double d0 = player.distanceToSqr(livingentity);
                        float f = (float)(p_41430_.getUseDuration(p_41429_) - p_41431_) / (float)getChargeDuration(p_41430_, p_41429_);
                        if (d0 < 30.0D) {
                            if(player.level() instanceof ServerLevel serverLevel) {
                                if (!(livingentity == player)) {
                                    if (f <= 4F) {
                                        livingentity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 10, true, false));
                                        for (double l = 0; l < 20; l++) {
                                            serverLevel.sendParticles(ParticleTypes.SOUL,
                                                    livingentity.getX() + (player.getX() - livingentity.getX()) / l,
                                                    livingentity.getY() + 0.5 + (player.getY() - livingentity.getY()) / l,
                                                    livingentity.getZ() + (player.getZ() - livingentity.getZ()) / l, 1,
                                                    0, 0, 0, 0);

                                            serverLevel.sendParticles(ParticleTypes.SOUL,
                                                    player.getX() + (livingentity.getX() - player.getX()) / l,
                                                    player.getY() + 0.5 + (livingentity.getY() - player.getY()) / l,
                                                    player.getZ() + (livingentity.getZ() - player.getZ()) / l, 1,
                                                    0, 0, 0, 0);
                                        }
                                    } else {
                                        for (int l = 0; l < 20; l++) {
                                            serverLevel.sendParticles(ParticleTypes.SCULK_SOUL,
                                                    livingentity.getX(), livingentity.getY() + 0.5, livingentity.getZ(), 1,
                                                    0, 0, 0, 0.1);
                                        }
                                        livingentity.checkDespawn();
                                        livingentity.discard();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static int getChargeDuration(ItemStack p_40940_, LivingEntity entity) {
        Holder<Enchantment> quickCharge = entity.level().registryAccess()
                .lookup(Registries.ENCHANTMENT)
                .flatMap(r -> r.get(Enchantments.QUICK_CHARGE))
                .orElse(null);
        if (quickCharge == null) return 20;
        int i = EnchantmentHelper.getItemEnchantmentLevel(quickCharge, p_40940_);
        return i == 0 ? 20 : 20 - 5 * i;
    }
}
