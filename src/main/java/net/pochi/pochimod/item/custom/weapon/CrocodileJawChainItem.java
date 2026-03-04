package net.pochi.pochimod.item.custom.weapon;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.pochi.pochimod.entity.projectile.JawChainProjectileEntity;

public class CrocodileJawChainItem extends SwordItem {

    private static final int COOLDOWN_TICKS = 100; // 10秒
    private static final int DURABILITY_COST = 15;

    private static final String COOLDOWN_TAG = "crocodile_jaw_chain_cooldown";

    public CrocodileJawChainItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        // クールダウン中
        if (this.isOnCooldown(player)) {
            if (level.isClientSide) {
                level.playSound(player, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.DISPENSER_FAIL, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
            return InteractionResultHolder.pass(itemStack);
        }

        // 耐久値チェック
        if (itemStack.getDamageValue() + DURABILITY_COST > itemStack.getMaxDamage()) {
            if (level.isClientSide) {
                level.playSound(player, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
            return InteractionResultHolder.fail(itemStack);
        }

        // サーバー側でのみ処理
        if (!level.isClientSide) {
            // 顎鎖を射出
            JawChainProjectileEntity jawChain = new JawChainProjectileEntity(level, player);
            jawChain.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(jawChain);

            // 耐久値消費
            itemStack.hurtAndBreak(DURABILITY_COST, player, hand == InteractionHand.MAIN_HAND ? net.minecraft.world.entity.EquipmentSlot.MAINHAND : net.minecraft.world.entity.EquipmentSlot.OFFHAND);

            // クールダウン設定
            this.setCooldown(player);

            // 統計
            player.awardStat(Stats.ITEM_USED.get(this));
        }

        // サウンド再生
        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.IRON_GOLEM_ATTACK, SoundSource.PLAYERS,
                1.0F, 0.8F + level.random.nextFloat() * 0.4F);

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    /**
     * クールダウン中かチェック
     */
    private boolean isOnCooldown(Player player) {
        return player.getPersistentData().contains(COOLDOWN_TAG) &&
                player.getPersistentData().getLong(COOLDOWN_TAG) > player.level().getGameTime();
    }

    /**
     * クールダウンを設定
     */
    private void setCooldown(Player player) {
        long cooldownEnd = player.level().getGameTime() + COOLDOWN_TICKS;
        player.getPersistentData().putLong(COOLDOWN_TAG, cooldownEnd);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
