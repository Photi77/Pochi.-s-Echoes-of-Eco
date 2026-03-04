package net.pochi.pochimod.entity.projectile;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;

public class OreSlime extends PathfinderMob implements GeoEntity {
    int kill = 0;

    private AnimatableInstanceCache factory = new SingletonAnimatableInstanceCache(this);

    public OreSlime(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
        this.noPhysics = true;
        this.setNoGravity(true);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        // すべての攻撃を無効化
        if(source.is(DamageTypes.GENERIC_KILL)){
            return false;
        }
        return true;
    }

    @Override
    public void tick() {
        kill++;
        if(kill >= 300){
            this.kill();
        }
        this.moveTo(this.getX(),this.getY(),this.getZ());
        this.addEffect(new MobEffectInstance(MobEffects.GLOWING,600,2,true,false));
        this.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY,600,2,true,false));
        super.tick();
    }

    @Override
    public boolean isPushable() {
        return false; // 他のエンティティから押されないように
    }




    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }
}