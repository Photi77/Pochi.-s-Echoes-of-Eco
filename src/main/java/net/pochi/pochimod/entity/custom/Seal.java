package net.pochi.pochimod.entity.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.entity.ModEntityTypes;

import javax.annotation.Nullable;

public class Seal extends Turtle implements PlayerRideable{

    public final net.minecraft.world.entity.AnimationState idleAnimationState = new net.minecraft.world.entity.AnimationState();
    private int idleAnimationTimeout = 0;

    public Seal(EntityType<? extends Turtle> p_28316_, Level p_28317_) {
        super(p_28316_, p_28317_);
        this.moveControl = new GiantOtter.TurtleMoveControl(this);
    }

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        if(pHand == InteractionHand.MAIN_HAND) {

            if(pPlayer.isShiftKeyDown()){
                for(int i = 0; i<20; i++) {
                    this.level().addParticle(ParticleTypes.TOTEM_OF_UNDYING, this.getX() - 1 + this.random.triangle(1,1), this.getY() + 1 + this.random.triangle(1,1),
                            this.getZ() - 1 + this.random.triangle(1,1), 0, 0, 0);
                }
            }

            if(!pPlayer.isCrouching()) {
                setRiding(pPlayer);
            } else {
                // TOGGLES SITTING FOR OUR ENTITY

            }
            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(pPlayer, pHand);
    }

    private void setRiding(Player pPlayer) {
        pPlayer.setYRot(this.getYRot());
        pPlayer.setXRot(this.getXRot());
        pPlayer.startRiding(this);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getControllingPassenger() instanceof Player player){
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING,200,3));
        }
        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return ModEntityTypes.SEAL.get().create(p_146743_);
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        return ((LivingEntity) this.getFirstPassenger());
    }

    @Override
    public void travel(Vec3 pTravelVector) {
        if(this.isVehicle() && getControllingPassenger() instanceof Player player) {
            if(this.isInWater() ) {
                LivingEntity livingentity = this.getControllingPassenger();
                this.setYRot(livingentity.getYRot());
                this.yRotO = this.getYRot();
                this.setXRot(livingentity.getXRot() * 0.5F);
                this.setRot(this.getYRot(), this.getXRot());
                this.yBodyRot = this.getYRot();
                this.yHeadRot = this.yBodyRot;
                float f = livingentity.xxa * 2;
                float f1 = livingentity.zza * 2;

                // Inside this if statement, we are on the client!
                if (this.isControlledByLocalInstance()) {
                    float newSpeed = (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED);
                    // increasing speed by 100% if the spring key is held down (number for testing purposes)
                    if (Minecraft.getInstance().options.keySprint.isDown()) {
                        newSpeed *= 2f;
                    }
                    this.setSpeed(newSpeed);
                    super.travel(new Vec3(f * 2, pTravelVector.y, f1 * 2));
                    this.setDeltaMovement(getDeltaMovement().x * 1.2, player.getLookAngle().y * 0.2, getDeltaMovement().z * 1.2);
                    this.move(MoverType.SELF, this.getDeltaMovement());
                }
            } else {
                this.setDeltaMovement(this.getDeltaMovement().x,-0.2,this.getDeltaMovement().y);
                this.move(MoverType.SELF, this.getDeltaMovement());
            }
        }
        super.travel(pTravelVector);
    }
    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity pLivingEntity) {
        Direction direction = this.getMotionDirection();
        if (direction.getAxis() != Direction.Axis.Y) {
            int[][] offsets = DismountHelper.offsetsForDirection(direction);
            BlockPos blockpos = this.blockPosition();
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for (Pose pose : pLivingEntity.getDismountPoses()) {
                AABB aabb = pLivingEntity.getLocalBoundsForPose(pose);

                for (int[] offset : offsets) {
                    blockpos$mutableblockpos.set(blockpos.getX() + offset[0], blockpos.getY(), blockpos.getZ() + offset[1]);
                    double d0 = this.level().getBlockFloorHeight(blockpos$mutableblockpos);
                    if (DismountHelper.isBlockFloorValid(d0)) {
                        Vec3 vec3 = Vec3.upFromBottomCenterOf(blockpos$mutableblockpos, d0);
                        if (DismountHelper.canDismountTo(this.level(), pLivingEntity, aabb.move(vec3))) {
                            pLivingEntity.setPose(pose);
                            return vec3;
                        }
                    }
                }
            }
        }

        return super.getDismountLocationForPassenger(pLivingEntity);
    }

    public static boolean checkSealSpawnRules(EntityType<Seal> p_218277_, LevelAccessor p_218278_, MobSpawnType p_218279_, BlockPos p_218280_, RandomSource p_218281_) {
        return p_218278_.getFluidState(p_218280_.below()).is(FluidTags.WATER) && p_218278_.getBlockState(p_218280_.above()).is(Blocks.WATER);

    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    protected void updateWalkAnimation(float v) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(v * 6.0F, 1.0F);
        } else {
            f = 0.0F;
        }

        this.walkAnimation.update(f, 0.2F);
    }

}
