package net.pochi.pochimod.entity.projectile;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.PlayState;

import java.util.List;
import java.util.Random;

public class PickaxeHead extends TamableAnimal implements GeoEntity {

    int life = 0;

    static boolean use = false;

    Random random = new Random();
    private AnimatableInstanceCache factory = new SingletonAnimatableInstanceCache(this);
    public PickaxeHead(EntityType<? extends PickaxeHead> p_21803_, Level p_21804_) {
        super(p_21803_, p_21804_);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }


    @Override
    public void tick() {
        super.tick();

        Player palyer1 = this.level().getNearestPlayer(this, 1000);


        if(life++ >= 600) {
            this.discard();
        }

        List<LivingEntity> list = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(100, 100, 100));
        if (!list.isEmpty()) {
            for (LivingEntity entity : list) {
                if (!(entity == this)) {
                    if (!(entity instanceof Player)) {
                        double d0 = entity.distanceToSqr(this.getX(), this.getY(), this.getZ());
                        if (d0 < 500) {
                            this.getNavigation().moveTo(entity.getX(),entity.getY(), entity.getZ(), 1);
                            //this.lookAt(entity,360,360);
                        }
                        if (d0 < 2) {
                            entity.kill();
                        }
                    }
                }
            }
        }
    }

    private PlayState predicate(AnimationState animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("animation.iron_head.new", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController(this, "controller",
                0, this::predicate));
    }

    public static boolean isCast() {
        return use;
    }

    @Override
    public boolean isFood(net.minecraft.world.item.ItemStack pStack) {
        return false;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }
}
