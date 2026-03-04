package net.pochi.pochimod.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.item.custom.armor.AncientLizardArmor;

@EventBusSubscriber(modid = PochiMod.MOD_ID)
public class TuataraArmorEventHandler {

    private static final int MIN_TELEPORT_DISTANCE = 3;
    private static final int MAX_TELEPORT_DISTANCE = 8;
    private static final int TELEPORT_ATTEMPTS = 16; // 隧ｦ陦悟屓謨ｰ

    @SubscribeEvent
    public static void onPlayerAttacked(LivingIncomingDamageEvent event) {
        // 繝励Ξ繧､繝､繝ｼ縺ｮ縺ｿ
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        // 繧ｵ繝ｼ繝舌・蛛ｴ縺ｮ縺ｿ
        if (player.level().isClientSide) {
            return;
        }

        // 繝繧ｫ繧ｷ繝医き繧ｲ縺ｮ鬆ｭ繧定｣・ｙ縺励※縺・ｋ縺九メ繧ｧ繝・け
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        if (!(helmet.getItem() instanceof AncientLizardArmor)) {
            return;
        }

        // 繧ｯ繝ｼ繝ｫ繝繧ｦ繝ｳ荳ｭ縺九メ繧ｧ繝・け
        if (isOnCooldown(player)) {
            return;
        }

        // 閠蝉ｹ・､繝√ぉ繝・け
        if (helmet.getDamageValue() + AncientLizardArmor.DURABILITY_COST > helmet.getMaxDamage()) {
            return;
        }

        // 迚ｹ螳壹・繝繝｡繝ｼ繧ｸ繧ｿ繧､繝励・辟｡隕厄ｼ井ｾ具ｼ壼･郁誠縲・kill繧ｳ繝槭Φ繝会ｼ・
        if (event.getSource().is(net.minecraft.tags.DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return;
        }

        // 繝・Ξ繝昴・繝郁ｩｦ陦・
        Vec3 originalPos = player.position();
        boolean teleported = attemptTeleport(player);

        if (teleported) {
            // 繝繝｡繝ｼ繧ｸ繧偵く繝｣繝ｳ繧ｻ繝ｫ
            event.setCanceled(true);

            // 繧ｨ繝輔ぉ繧ｯ繝・
            spawnTeleportEffects((ServerLevel) player.level(), originalPos, player.position());

            // 閠蝉ｹ・､豸郁ｲｻ
            helmet.hurtAndBreak(
                    AncientLizardArmor.DURABILITY_COST,
                    player,
                    EquipmentSlot.HEAD
            );

            // 繧ｯ繝ｼ繝ｫ繝繧ｦ繝ｳ險ｭ螳・
            setCooldown(player);
        }
    }

    /**
     * 繧ｯ繝ｼ繝ｫ繝繧ｦ繝ｳ荳ｭ縺九メ繧ｧ繝・け
     */
    private static boolean isOnCooldown(Player player) {
        return player.getPersistentData().contains(AncientLizardArmor.COOLDOWN_TAG) &&
                player.getPersistentData().getLong(AncientLizardArmor.COOLDOWN_TAG) > player.level().getGameTime();
    }

    /**
     * 繧ｯ繝ｼ繝ｫ繝繧ｦ繝ｳ險ｭ螳・
     */
    private static void setCooldown(Player player) {
        long cooldownEnd = player.level().getGameTime() + AncientLizardArmor.COOLDOWN_TICKS;
        player.getPersistentData().putLong(AncientLizardArmor.COOLDOWN_TAG, cooldownEnd);
    }

    /**
     * 繝・Ξ繝昴・繝郁ｩｦ陦鯉ｼ医お繝ｳ繝繝ｼ繝槭Φ譁ｹ蠑擾ｼ・
     */
    private static boolean attemptTeleport(Player player) {
        Level level = player.level();

        // 隍・焚蝗櫁ｩｦ陦後＠縺ｦ螳牙・縺ｪ蝣ｴ謇繧定ｦ九▽縺代ｋ
        for (int i = 0; i < TELEPORT_ATTEMPTS; i++) {
            // 繝ｩ繝ｳ繝繝縺ｪ霍晞屬縺ｨ隗貞ｺｦ
            double distance = MIN_TELEPORT_DISTANCE +
                    player.getRandom().nextDouble() * (MAX_TELEPORT_DISTANCE - MIN_TELEPORT_DISTANCE);
            double angle = player.getRandom().nextDouble() * Math.PI * 2;

            // 豌ｴ蟷ｳ譁ｹ蜷代・遘ｻ蜍包ｼ・霆ｸ縺ｯ蠕後〒隱ｿ謨ｴ・・
            double offsetX = Math.cos(angle) * distance;
            double offsetZ = Math.sin(angle) * distance;

            // 蛟呵｣應ｽ咲ｽｮ
            Vec3 targetPos = new Vec3(
                    player.getX() + offsetX,
                    player.getY(),
                    player.getZ() + offsetZ
            );

            // Y霆ｸ繧剃ｸ贋ｸ九↓謗｢邏｢縺励※螳牙・縺ｪ蝨ｰ髱｢繧定ｦ九▽縺代ｋ
            for (int yOffset = 2; yOffset >= -2; yOffset--) {
                Vec3 testPos = targetPos.add(0, yOffset, 0);

                if (isSafeTeleportDestination(level, testPos, player)) {
                    // 繝・Ξ繝昴・繝亥ｮ溯｡・
                    player.teleportTo(testPos.x, testPos.y, testPos.z);
                    player.fallDistance = 0.0F;
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 繝・Ξ繝昴・繝亥・縺悟ｮ牙・縺九メ繧ｧ繝・け
     */
    private static boolean isSafeTeleportDestination(Level level, Vec3 pos, Player player) {
        BlockPos blockPos = BlockPos.containing(pos);

        // 雜ｳ蜈・・繝悶Ο繝・け
        BlockPos groundPos = blockPos.below();
        BlockState groundState = level.getBlockState(groundPos);

        // 蝨ｰ髱｢縺悟ｭ伜惠縺励↑縺・
        if (groundState.isAir()) {
            return false;
        }

        // 貅ｶ蟯ｩ繝ｻ轣ｫ
        if (groundState.is(Blocks.LAVA)) {
            return false;
        }

        // 繝励Ξ繧､繝､繝ｼ縺ｮ蠖薙◆繧雁愛螳夲ｼ・繝悶Ο繝・け鬮假ｼ・
        BlockPos headPos = blockPos.above();

        // 遯呈・繝√ぉ繝・け
        if (!level.getBlockState(blockPos).isAir() || !level.getBlockState(headPos).isAir()) {
            return false;
        }

        // 繝励Ξ繧､繝､繝ｼ縺ｮ蠖薙◆繧雁愛螳壹′莉悶・繧ｨ繝ｳ繝・ぅ繝・ぅ縺ｨ陲ｫ繧峨↑縺・°繝√ぉ繝・け
        AABB playerBox = player.getBoundingBox().move(
                pos.x - player.getX(),
                pos.y - player.getY(),
                pos.z - player.getZ()
        );

        if (!level.noCollision(player, playerBox)) {
            return false;
        }

        // 螂郁誠繝√ぉ繝・け・・ < -64・・
        if (pos.y < level.getMinBuildHeight()) {
            return false;
        }

        return true;
    }

    /**
     * 繝・Ξ繝昴・繝医お繝輔ぉ繧ｯ繝・
     */
    private static void spawnTeleportEffects(ServerLevel level, Vec3 oldPos, Vec3 newPos) {
        // 蜈・・菴咲ｽｮ縺ｮ繝代・繝・ぅ繧ｯ繝ｫ
        level.sendParticles(
                ParticleTypes.PORTAL,
                oldPos.x, oldPos.y + 1.0, oldPos.z,
                30,
                0.5, 1.0, 0.5,
                0.5
        );

        // 譁ｰ縺励＞菴咲ｽｮ縺ｮ繝代・繝・ぅ繧ｯ繝ｫ
        level.sendParticles(
                ParticleTypes.PORTAL,
                newPos.x, newPos.y + 1.0, newPos.z,
                30,
                0.5, 1.0, 0.5,
                0.5
        );

        // 繧ｨ繝ｳ繝繝ｼ繝槭Φ縺ｮ繝・Ξ繝昴・繝磯浹
        level.playSound(
                null,
                oldPos.x, oldPos.y, oldPos.z,
                SoundEvents.ENDERMAN_TELEPORT,
                SoundSource.PLAYERS,
                1.0F,
                1.0F
        );

        level.playSound(
                null,
                newPos.x, newPos.y, newPos.z,
                SoundEvents.ENDERMAN_TELEPORT,
                SoundSource.PLAYERS,
                1.0F,
                1.0F
        );
    }
}
