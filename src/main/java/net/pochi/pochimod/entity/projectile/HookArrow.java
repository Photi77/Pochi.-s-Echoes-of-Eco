package net.pochi.pochimod.entity.projectile;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.entity.ModEntityTypes;
import net.pochi.pochimod.item.custom.weapon.HookShot;
import net.pochi.pochimod.util.MathHelper;

import javax.annotation.Nullable;

public class HookArrow extends AbstractArrow {
    private double x_distance = 0.0f;
    private double y_distance = 0.0f;
    private double z_distance = 0.0f;
    private float travelDistance = 0.25f;

    private final float TICK_RATE = 20;
    private final float speed = 1.0f * TICK_RATE; // the higher the float, the slower the travel

    private boolean isStuckInBlock = false;

    private HookShot parentItem;

    private final int lifeSpan = 20; // 3 second life span
    private int currentLife = 0;


    private boolean playerIsNear = false;


    private ItemStack shootStack = new ItemStack(Items.AIR);



    public HookArrow(EntityType<? extends HookArrow> entity, Level level) {
        super(entity, level);
        this.setNoGravity(true);
    }

    @Nullable
    public Player getPlayerOwner() {
        Entity entity = this.getOwner();
        return entity instanceof Player ? (Player)entity : null;
    }

    protected void killEntity(){
        this.discard();

        if (parentItem != null) {
            ((Player) this.getOwner()).getCooldowns().removeCooldown(parentItem);
        }
    }


    public HookArrow(Level level, LivingEntity entity, ItemStack stack) {
        super(ModEntityTypes.HOOK_ARROW.get(), entity, level, stack, null);
        shootStack = stack;
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return shootStack;
    }



    @Override
    protected void onHitBlock(BlockHitResult blockHitResult){
        super.onHitBlock(blockHitResult);
        Entity owner = this.getOwner();
        if (owner instanceof ServerPlayer player) {
            Vec3 vec3 = owner.getLookAngle();
            player.addEffect(new MobEffectInstance(MobEffects.LEVITATION,3,50,true,false));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,100,5,true,false));
            owner.hurtMarked = true;
            player.setDeltaMovement(vec3.x * 10,vec3.y * 10,vec3.z * 10);
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return shootStack;
    }


    @Override
    public void tick() {
        super.tick();
        if(this.getOwner() != null) {

            if (!isStuckInBlock){
                currentLife++;
            }

            if (!isStuckInBlock && currentLife >= lifeSpan){
                killEntity();
            }

            Entity owner = this.getOwner();

            Vec3 hookPos = this.position();
            Vec3 playerPos = owner.position();
            float distance = (float) MathHelper.getDistance(hookPos, playerPos);
            if (isStuckInBlock) {
                if (owner instanceof ServerPlayer) {
                    ServerPlayer serverplayer = (ServerPlayer) owner;

                }
            }

            if(distance >= 80.0f){
                killEntity();
            }

            if (distance <= 1.0f){
                playerIsNear = true;
            }

            if(isStuckInBlock && playerIsNear) {
                killEntity();
            }
        } else {
            killEntity();
        }
    }
}
