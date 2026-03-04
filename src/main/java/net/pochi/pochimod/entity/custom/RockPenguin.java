package net.pochi.pochimod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.entity.ModEntityTypes;

import javax.annotation.Nullable;

public class RockPenguin extends Turtle{

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public RockPenguin(EntityType<? extends Turtle> p_28316_, Level p_28317_) {
        super(p_28316_, p_28317_);
        this.moveControl = new RockPenguin.TurtleMoveControl(this);
    }

    private static final EntityDataAccessor<BlockPos> TRAVEL_POS = SynchedEntityData.defineId(RockPenguin.class, EntityDataSerializers.BLOCK_POS);
    //private static final EntityDataAccessor<Boolean> GOING_HOME = SynchedEntityData.defineId(GiantOtter.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> TRAVELLING = SynchedEntityData.defineId(RockPenguin.class, EntityDataSerializers.BOOLEAN);

    void setTravelPos(BlockPos p_30224_) {
        this.entityData.set(TRAVEL_POS, p_30224_);
    }

    BlockPos getTravelPos() {
        return this.entityData.get(TRAVEL_POS);
    }

    boolean isTravelling() {
        return this.entityData.get(TRAVELLING);
    }

    void setTravelling(boolean p_30241_) {
        this.entityData.set(TRAVELLING, p_30241_);
    }

    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(TRAVEL_POS, BlockPos.ZERO);
        builder.define(TRAVELLING, false);
    }

    public void addAdditionalSaveData(CompoundTag p_30176_) {
        super.addAdditionalSaveData(p_30176_);
        p_30176_.putInt("TravelPosX", this.getTravelPos().getX());
        p_30176_.putInt("TravelPosY", this.getTravelPos().getY());
        p_30176_.putInt("TravelPosZ", this.getTravelPos().getZ());
    }

    public void readAdditionalSaveData(CompoundTag p_30162_) {
        super.readAdditionalSaveData(p_30162_);
        int l = p_30162_.getInt("TravelPosX");
        int i1 = p_30162_.getInt("TravelPosY");
        int j1 = p_30162_.getInt("TravelPosZ");
        this.setTravelPos(new BlockPos(l, i1, j1));
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(3,new MeleeAttackGoal(this,1.0,true));
        this.goalSelector.addGoal(4,new RandomStrollGoal(this,1.0,100));
        this.goalSelector.addGoal(7, new TurtleTravelGoal(this, 1.0D));
        this.targetSelector.addGoal(4, (new HurtByTargetGoal(this)).setAlertOthers());
        //this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractFish.class, false));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return ModEntityTypes.ROCK_PENGUIN.get().create(p_146743_);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.ATTACK_DAMAGE, 8.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.3f).build();
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

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    @Override
    public boolean isFood(ItemStack p_27600_) {
        return p_27600_.is(Items.COD);
    }

    static class TurtleMoveControl extends MoveControl {
        private final Turtle turtle;

        TurtleMoveControl(Turtle p_30286_) {
            super(p_30286_);
            this.turtle = p_30286_;
        }

        private void updateSpeed() {
            if (this.turtle.isInWater()) {
                this.turtle.setDeltaMovement(this.turtle.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

        }

        public void tick() {
            this.updateSpeed();
            if (this.operation == Operation.MOVE_TO && !this.turtle.getNavigation().isDone()) {
                double d0 = this.wantedX - this.turtle.getX();
                double d1 = this.wantedY - this.turtle.getY();
                double d2 = this.wantedZ - this.turtle.getZ();
                double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                if (d3 < (double)1.0E-5F) {
                    this.mob.setSpeed(0.0F);
                } else {
                    d1 /= d3;
                    float f = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    this.turtle.setYRot(this.rotlerp(this.turtle.getYRot(), f, 90.0F));
                    this.turtle.yBodyRot = this.turtle.getYRot();
                    float f1 = (float)(this.speedModifier * this.turtle.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    this.turtle.setSpeed(Mth.lerp(0.125F, this.turtle.getSpeed(), f1));
                    this.turtle.setDeltaMovement(this.turtle.getDeltaMovement().add(0.0D, (double)this.turtle.getSpeed() * d1 * 0.1D, 0.0D));
                }
            } else {
                this.turtle.setSpeed(0.0F);
            }
        }
    }

    static class TurtleTravelGoal extends Goal {
        private final RockPenguin turtle;
        private final double speedModifier;
        private boolean stuck;

        TurtleTravelGoal(RockPenguin p_30333_, double p_30334_) {
            this.turtle = p_30333_;
            this.speedModifier = p_30334_;
        }

        public boolean canUse() {
            return this.turtle.isInWater();
        }

        public void start() {
            int i = 512;
            int j = 4;
            RandomSource randomsource = this.turtle.random;
            int k = randomsource.nextInt(1025) - 512;
            int l = randomsource.nextInt(9) - 4;
            int i1 = randomsource.nextInt(1025) - 512;
            if ((double)l + this.turtle.getY() > (double)(this.turtle.level().getSeaLevel() - 1)) {
                l = 0;
            }

            BlockPos blockpos = BlockPos.containing((double)k + this.turtle.getX(), (double)l + this.turtle.getY(), (double)i1 + this.turtle.getZ());
            this.turtle.setTravelPos(blockpos);
            this.turtle.setTravelling(true);
            this.stuck = false;
        }

        public void tick() {
            if (this.turtle.getNavigation().isDone()) {
                Vec3 vec3 = Vec3.atBottomCenterOf(this.turtle.getTravelPos());
                Vec3 vec31 = DefaultRandomPos.getPosTowards(this.turtle, 16, 3, vec3, (double)((float)Math.PI / 10F));
                if (vec31 == null) {
                    vec31 = DefaultRandomPos.getPosTowards(this.turtle, 8, 7, vec3, (double)((float)Math.PI / 2F));
                }

                if (vec31 != null) {
                    int i = Mth.floor(vec31.x);
                    int j = Mth.floor(vec31.z);
                    int k = 34;
                    if (!this.turtle.level().hasChunksAt(i - 34, j - 34, i + 34, j + 34)) {
                        vec31 = null;
                    }
                }

                if (vec31 == null) {
                    this.stuck = true;
                    return;
                }

                this.turtle.getNavigation().moveTo(vec31.x, vec31.y, vec31.z, this.speedModifier);
            }

        }

        public boolean canContinueToUse() {
            return !this.turtle.getNavigation().isDone() && !this.stuck && !this.turtle.isInLove() && !this.turtle.hasEgg();
        }

        public void stop() {
            this.turtle.setTravelling(false);
            super.stop();
        }
    }

}