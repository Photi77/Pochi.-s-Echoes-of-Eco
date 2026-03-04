package net.pochi.pochimod.item.custom.armor;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
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

public class ElectronArmorItem extends ArmorItem{
    public ElectronArmorItem(Holder<ArmorMaterial> p_40386_, Type p_266831_, Properties p_40388_) {
        super(p_40386_, p_266831_, p_40388_);
    }
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if(!level.isClientSide() && hasFullSuitOfArmorOn(player)) {
            if (Minecraft.getInstance().options.keyJump.isDown()) {
                if(level instanceof ServerLevel serverLevel){
                    for (int l = 0; l < 20; l++) {
                        serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                                player.getX(), player.getY(), player.getZ(), 1,
                                0, 0, 0, 0.5);
                    }
                }

                player.hurtMarked = true;
                Vec3 vec3 = player.getLookAngle();
                player.setDeltaMovement(vec3.x, vec3.y, vec3.z);

                List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(10, 10, 10));
                if (!list.isEmpty()) {
                    for (LivingEntity entity : list) {
                        double d0 = player.distanceToSqr(entity.getX(), entity.getY(), entity.getZ());
                        if (d0 < 3.0D) {
                            if (!(entity instanceof Player)) {
                                entity.hurt(player.damageSources().playerAttack(player),10);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        boolean boots = player.getInventory().getArmor(0).is(ModItems.ELECTRON_BOOTS.get());
        boolean leggings = player.getInventory().getArmor(1).is(ModItems.ELECTRON_LEGGINGS.get());
        boolean chestplate = player.getInventory().getArmor(2).is(ModItems.ELECTRON_CHESTPLATE.get());
        boolean helmet = player.getInventory().getArmor(3).is(ModItems.ELECTRON_HELMET.get());

        return helmet && leggings && chestplate && boots;
    }


}
