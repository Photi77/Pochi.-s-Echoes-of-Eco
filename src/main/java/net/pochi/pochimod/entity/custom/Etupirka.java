package net.pochi.pochimod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.entity.ModEntityTypes;
import net.pochi.pochimod.entity.ai.EtupirkaJump;
import net.pochi.pochimod.entity.ai.SwimmingFlyingControl;

import javax.annotation.Nullable;


public class Etupirka extends Turtle implements FlyingAnimal {

    public final net.minecraft.world.entity.AnimationState idleAnimationState = new AnimationState();

    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    private float flapping = 1.0F;
    private float nextFlap = 1.0F;

    public Etupirka(EntityType<? extends Turtle> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SwimmingFlyingControl(this, 85, 10, 0.5F,true,false);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    protected PathNavigation createNavigation(Level pLevel) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, pLevel);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new FloatGoal(this));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.of(Items.WHEAT), false));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomFlyingGoal(this, 1.2D));
        this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 1.0D,20));
        this.goalSelector.addGoal(3,new MeleeAttackGoal(this,1.D,false));
        this.goalSelector.addGoal(5, new EtupirkaJump(this, 30));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractFish.class, false));
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0D)
                .add(Attributes.ATTACK_DAMAGE, 8.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.FLYING_SPEED, (double)4.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.3f).build();
    }

    public void aiStep() {
        super.aiStep();
        this.calculateFlapping();
    }

    private void calculateFlapping() {
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed += (float)(!this.onGround() && !this.isPassenger() ? 4 : -1) * 0.3F;
        this.flapSpeed = Mth.clamp(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround() && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping *= 0.9F;
        Vec3 vec3 = this.getDeltaMovement();
        if (!this.onGround() && vec3.y < 0.0D) {
            this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));
        }

        this.flap += this.flapping * 2.0F;
    }

    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
    }

    protected boolean isFlapping() {
        return this.flyDist > this.nextFlap;
    }

    protected void onFlap() {
        this.playSound(SoundEvents.PARROT_FLY, 0.15F, 1.0F);
        this.nextFlap = this.flyDist + this.flapSpeed / 2.0F;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.PARROT_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.PARROT_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.PARROT_DEATH;
    }

    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(SoundEvents.PARROT_STEP, 0.15F, 1.0F);
    }

    protected float getSoundVolume() {
        return 0.2F;
    }

    public Vec3 getLeashOffset() {
        return new Vec3(0.0D, (double)(0.5F * this.getEyeHeight()), (double)(this.getBbWidth() * 0.4F));
    }


    // エンティティが飛行中かどうかを判定する
    public boolean isFlying() {
        return !this.isInWater() && this.getDeltaMovement().y > 0; // 地上にいない、かつ水中にいないとき飛行中
    }


    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return ModEntityTypes.ETUPIRKA.get().create(p_146743_);
    }

    @Override
    public boolean isFood(ItemStack p_27600_) {
        return p_27600_.is(Items.SALMON);
    }

    static class EtupirkaWanderGoal extends WaterAvoidingRandomFlyingGoal {
        public EtupirkaWanderGoal(PathfinderMob p_186224_, double p_186225_) {
            super(p_186224_, p_186225_);
        }

        @javax.annotation.Nullable
        protected Vec3 getPosition() {
            Vec3 vec3 = null;
            if (this.mob.isInWater()) {
                vec3 = LandRandomPos.getPos(this.mob, 25, 25);
            }

            if (this.mob.getRandom().nextFloat() >= this.probability) {
                vec3 = this.getTreePos();
            }

            return vec3 == null ? super.getPosition() : vec3;
        }

        @javax.annotation.Nullable
        private Vec3 getTreePos() {
            BlockPos blockpos = this.mob.blockPosition();
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
            BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();

            for(BlockPos blockpos1 : BlockPos.betweenClosed(Mth.floor(this.mob.getX() - 3.0D), Mth.floor(this.mob.getY() - 6.0D), Mth.floor(this.mob.getZ() - 3.0D), Mth.floor(this.mob.getX() + 3.0D), Mth.floor(this.mob.getY() + 6.0D), Mth.floor(this.mob.getZ() + 3.0D))) {
                if (!blockpos.equals(blockpos1)) {
                    BlockState blockstate = this.mob.level().getBlockState(blockpos$mutableblockpos1.setWithOffset(blockpos1, Direction.DOWN));
                    boolean flag = blockstate.getBlock() instanceof LeavesBlock || blockstate.is(BlockTags.LOGS);
                    if (flag && this.mob.level().isEmptyBlock(blockpos1) && this.mob.level().isEmptyBlock(blockpos$mutableblockpos.setWithOffset(blockpos1, Direction.UP))) {
                        return Vec3.atBottomCenterOf(blockpos1);
                    }
                }
            }

            return null;
        }
    }

    //class SwimmingFlyingControl extends MoveControl {
    //    private static final float FULL_SPEED_TURN_THRESHOLD = 10.0F;
    //    private static final float STOP_TURN_THRESHOLD = 60.0F;
    //    private final int maxTurnX;
    //    private final int maxTurnY;
    //    private final float inWaterSpeedModifier;
    //    private final boolean applyGravity;
//
    //    private float speed = 0.1F;
    //    private final boolean hoversInPlace;
//
    //    public SwimmingFlyingControl(Etupirka p_148070_, int p_148071_, int p_148072_, float p_148073_, boolean water, boolean fly) {
    //        super(p_148070_);
    //        this.maxTurnX = p_148071_;
    //        this.maxTurnY = p_148072_;
    //        this.inWaterSpeedModifier = p_148073_;
    //        this.applyGravity = water;
    //        this.hoversInPlace = fly;
    //    }
//
    //    public void tick() {
    //        if (this.applyGravity && Etupirka.this.isInWater()) {
    //            Etupirka.this.setDeltaMovement(Etupirka.this.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
    //        }
//
    //        if (this.operation == MoveControl.Operation.MOVE_TO) {
    //            if (Etupirka.this.isInWater()  && !Etupirka.this.getNavigation().isDone()) {
    //                double d0 = this.wantedX - Etupirka.this.getX();
    //                double d1 = this.wantedY - Etupirka.this.getY();
    //                double d2 = this.wantedZ - Etupirka.this.getZ();
    //                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
    //                if (d3 < (double) 2.5000003E-7F) {
    //                    Etupirka.this.setZza(0.0F);
    //                } else {
    //                    float f = (float) (Mth.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
    //                    Etupirka.this.setYRot(this.rotlerp(Etupirka.this.getYRot(), f, (float) this.maxTurnY));
    //                    Etupirka.this.yBodyRot = Etupirka.this.getYRot();
    //                    Etupirka.this.yHeadRot = Etupirka.this.getYRot();
    //                    float f1 = (float) (this.speedModifier * Etupirka.this.getAttributeValue(Attributes.MOVEMENT_SPEED));
//
    //                    Etupirka.this.setSpeed(f1 * this.inWaterSpeedModifier);
    //                    double d4 = Math.sqrt(d0 * d0 + d2 * d2);
    //                    if (Math.abs(d1) > (double) 1.0E-5F || Math.abs(d4) > (double) 1.0E-5F) {
    //                        float f3 = -((float) (Mth.atan2(d1, d4) * (double) (180F / (float) Math.PI)));
    //                        f3 = Mth.clamp(Mth.wrapDegrees(f3), (float) (-this.maxTurnX), (float) this.maxTurnX);
    //                        Etupirka.this.setXRot(this.rotlerp(Etupirka.this.getXRot(), f3, 5.0F));
    //                    }
//
    //                    float f6 = Mth.cos(Etupirka.this.getXRot() * ((float) Math.PI / 180F));
    //                    float f4 = Mth.sin(Etupirka.this.getXRot() * ((float) Math.PI / 180F));
    //                    Etupirka.this.zza = f6 * f1;
    //                    Etupirka.this.yya = -f4 * f1;
//
//
    //                }
    //            } else if(!Etupirka.this.isInWater() && !Etupirka.this.onGround()){
    //                if (Etupirka.this.horizontalCollision) {
    //                    Etupirka.this.setYRot(Etupirka.this.getYRot() + 180.0F);
    //                    this.speed = 0.1F;
    //                }
//
    //                double d0 = Etupirka.this.moveTargetPoint.x - Etupirka.this.getX();
    //                double d1 = Etupirka.this.moveTargetPoint.y - Etupirka.this.getY();
    //                double d2 = Etupirka.this.moveTargetPoint.z - Etupirka.this.getZ();
    //                double d3 = Math.sqrt(d0 * d0 + d2 * d2);
    //                if (Math.abs(d3) > (double)1.0E-5F) {
    //                    double d4 = 1.0D - Math.abs(d1 * (double)0.7F) / d3;
    //                    d0 *= d4;
    //                    d2 *= d4;
    //                    d3 = Math.sqrt(d0 * d0 + d2 * d2);
    //                    double d5 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
    //                    float f = Etupirka.this.getYRot();
    //                    float f1 = (float)Mth.atan2(d2, d0);
    //                    float f2 = Mth.wrapDegrees(Etupirka.this.getYRot() + 90.0F);
    //                    float f3 = Mth.wrapDegrees(f1 * (180F / (float)Math.PI));
    //                    Etupirka.this.setYRot(Mth.approachDegrees(f2, f3, 4.0F) - 90.0F);
    //                    Etupirka.this.yBodyRot = Etupirka.this.getYRot();
    //                    if (Mth.degreesDifferenceAbs(f, Etupirka.this.getYRot()) < 3.0F) {
    //                        this.speed = Mth.approach(this.speed, 1.8F, 0.005F * (1.8F / this.speed));
    //                    } else {
    //                        this.speed = Mth.approach(this.speed, 0.2F, 0.025F);
    //                    }
//
    //                    float f4 = (float)(-(Mth.atan2(-d1, d3) * (double)(180F / (float)Math.PI)));
    //                    Etupirka.this.setXRot(f4);
    //                    float f5 = Etupirka.this.getYRot() + 90.0F;
    //                    double d6 = (double)(this.speed * Mth.cos(f5 * ((float)Math.PI / 180F))) * Math.abs(d0 / d5);
    //                    double d7 = (double)(this.speed * Mth.sin(f5 * ((float)Math.PI / 180F))) * Math.abs(d2 / d5);
    //                    double d8 = (double)(this.speed * Mth.sin(f4 * ((float)Math.PI / 180F))) * Math.abs(d1 / d5);
    //                    Vec3 vec3 = Etupirka.this.getDeltaMovement();
    //                    Etupirka.this.setDeltaMovement(vec3.add((new Vec3(d6, d8, d7)).subtract(vec3).scale(0.2D)));
    //                }
    //            }
    //        } else {
    //            if (!this.hoversInPlace) {
    //                Etupirka.this.setNoGravity(false);
    //            }
//
    //            Etupirka.this.setSpeed(0.0F);
    //            Etupirka.this.setXxa(0.0F);
    //            Etupirka.this.setYya(0.0F);
    //            Etupirka.this.setZza(0.0F);
    //        }
    //    }
//
    //    private static float getTurningSpeedFactor(float p_249853_) {
    //        return 1.0F - Mth.clamp((p_249853_ - 10.0F) / 50.0F, 0.0F, 1.0F);
    //    }
    //}
}