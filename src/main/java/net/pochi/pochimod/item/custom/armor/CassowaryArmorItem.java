package net.pochi.pochimod.item.custom.armor;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.item.ModItems;

import java.util.List;

public class CassowaryArmorItem extends Item {
    public CassowaryArmorItem(Holder<ArmorMaterial> p_40386_, ArmorType p_266831_, Properties p_40388_) {
        super(p_40388_);
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
                                entity.hurtOrSimulate(player.damageSources().playerAttack(player),10);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        boolean boots = player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.FEET).is(ModItems.ELECTRON_BOOTS.get());

        return boots;
    }


}
