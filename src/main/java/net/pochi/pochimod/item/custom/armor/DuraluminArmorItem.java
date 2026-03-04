package net.pochi.pochimod.item.custom.armor;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.item.ModItems;

import java.util.List;

public class DuraluminArmorItem extends ArmorItem{

    int effectTime = 0;

    private int count = 0;

    public DuraluminArmorItem(Holder<ArmorMaterial> p_40386_, Type p_266831_, Properties p_40388_) {
        super(p_40386_, p_266831_, p_40388_);
    }
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if(!level.isClientSide() && hasFullSuitOfArmorOn(player)) {

                if (Minecraft.getInstance().options.keyJump.consumeClick()) {
                    Vec3 vec3 = player.getLookAngle();
                    player.hurtMarked = true;
                    player.setDeltaMovement(vec3.x * 2, 0.7, vec3.z * 2);
                    effectTime = 20;
                    if (level instanceof ServerLevel serverLevel) {
                        serverLevel.playSound(null, player.blockPosition(), SoundEvents.GENERIC_EXPLODE.value(), SoundSource.BLOCKS);
                    }
                }


            if(effectTime-- > 0) {
                if(level instanceof ServerLevel serverLevel){
                    for (int l = 0; l < 20; l++) {
                        serverLevel.sendParticles(ParticleTypes.CLOUD,
                                player.getX(), player.getY(), player.getZ(), 1,
                                0, 0, 0, 1);
                    }
                }
                List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(10, 10, 10));
                if (!list.isEmpty()) {
                    for (LivingEntity entity : list) {
                        double d0 = player.distanceToSqr(entity.getX(), entity.getY(), entity.getZ());
                        if (d0 < 5.0D) {
                            if (!(entity instanceof Player)) {
                                if (count-- <= 0) {
                                    entity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 100));
                                    count=5;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private boolean hasFullSuitOfArmorOn(Player player) {
        boolean boots = player.getInventory().getArmor(0).is(ModItems.DURALUMIN_BOOTS.get());
        boolean leggings = player.getInventory().getArmor(1).is(ModItems.DURALUMIN_LEGGINGS.get());
        boolean chestplate = player.getInventory().getArmor(2).is(ModItems.DURALUMIN_CHESTPLATE.get());
        boolean helmet = player.getInventory().getArmor(3).is(ModItems.DURALUMIN_HELMET.get());

        return helmet && leggings && chestplate && boots;
    }
}
