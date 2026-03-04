package net.pochi.pochimod.item.custom.armor;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.item.ModItems;

public class EmuArmorItem extends ArmorItem{

    int effectTime = 0;

    private int count = 0;
    public EmuArmorItem(Holder<ArmorMaterial> p_40386_, Type p_266831_, Properties p_40388_) {
        super(p_40386_, p_266831_, p_40388_);
    }
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if(!level.isClientSide() && hasFullSuitOfArmorOn(player)) {

            player.addEffect(new MobEffectInstance(MobEffects.JUMP));
            if (Minecraft.getInstance().options.keyJump.consumeClick()) {
                if(!player.onGround() && count == 0) {
                    Vec3 vec3 = player.getLookAngle();
                    player.hurtMarked = true;
                    player.setDeltaMovement(vec3.x, 1.1, vec3.z);
                    effectTime = 5;
                    if (level instanceof ServerLevel serverLevel) {
                        serverLevel.playSound(null, player.blockPosition(), SoundEvents.ENDER_DRAGON_FLAP, SoundSource.BLOCKS);
                    }
                    count = 1;
                }
            }

            if(player.onGround()){
                count = 0;
            }


            if(effectTime-- > 0) {
                if(level instanceof ServerLevel serverLevel){
                    for (int l = 0; l < 20; l++) {
                        serverLevel.sendParticles(ParticleTypes.SNOWFLAKE,
                                player.getX(), player.getY(), player.getZ(), 1,
                                0, 0, 0, 1);
                    }
                }
            }
        }
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        boolean boots = player.getInventory().getArmor(0).is(ModItems.EMU_BOOTS.get());

        return boots;
    }

}
