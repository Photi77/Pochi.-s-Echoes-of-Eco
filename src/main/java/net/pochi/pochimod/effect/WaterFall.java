package net.pochi.pochimod.effect;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.pochi.pochimod.attachment.ModAttachments;
import net.pochi.pochimod.config.VitalConfig;
import net.pochi.pochimod.networking.ModMessages;
import net.pochi.pochimod.nutrition.PlayerVitalData;
import net.pochi.pochimod.nutrition.SyncVitalDataPacket;

public class WaterFall extends MobEffect {

    int effectTime = 0;
    protected WaterFall(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        Level level = pLivingEntity.level();
        if (!level.isClientSide()) {
            if (pLivingEntity instanceof Player player) {
                PlayerVitalData vitalData = player.getData(ModAttachments.PLAYER_VITAL);

                // 水分減少
                int hydrationDepletion = (int) (VitalConfig.HYDRATION_DEPLETION_RATE.get());
                vitalData.consumeHydration(hydrationDepletion);

                // クライアントに同期
                if (player instanceof ServerPlayer serverPlayer) {
                    ModMessages.sendToPlayer(new SyncVitalDataPacket(vitalData), serverPlayer);
                }
            }
        }
        return super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }
}
