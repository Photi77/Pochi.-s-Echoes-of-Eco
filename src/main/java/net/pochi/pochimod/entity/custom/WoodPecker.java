package net.pochi.pochimod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.block.ModBlocks;
import net.pochi.pochimod.entity.ModEntityTypes;
import net.pochi.pochimod.item.ModItems;

import javax.annotation.Nullable;

public class WoodPecker extends Animal implements FlyingAnimal {

    public final AnimationState idleAnimationState = new AnimationState();

    private int idleAnimationTimeout = 0;
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    private float flapping = 1.0F;
    private float nextFlap = 1.0F;

    public WoodPecker(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 10, false);
        this.setPathfindingMalus(PathType.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(PathType.DAMAGE_FIRE, -1.0F);
        this.setPathfindingMalus(PathType.COCOA, -1.0F);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.FLYING_SPEED, (double) 4.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.3f).build();
    }

    protected PathNavigation createNavigation(Level pLevel) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, pLevel);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(3, new Peacock.SparrowWanderGoal(this, 1.2D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new DeerStrippedGoal(1.0F,12,4));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.of(Items.WHEAT), false));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        //this.goalSelector.addGoal(3, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
    }

    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return pSize.height() * 0.6F;
    }

    public void aiStep() {
        super.aiStep();
        this.calculateFlapping();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    private void calculateFlapping() {
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed += (float) (!this.onGround() && !this.isPassenger() ? 4 : -1) * 0.3F;
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

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return ModEntityTypes.WOOD_PECKER.get().create(p_146743_);
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

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    public Vec3 getLeashOffset() {
        return new Vec3(0.0D, (double) (0.5F * this.getEyeHeight()), (double) (this.getBbWidth() * 0.4F));
    }

    @Override
    public boolean isFood(ItemStack p_27600_) {
        return p_27600_.is(Blocks.OAK_LEAVES.asItem());
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    //static class SparrowWanderGoal extends WaterAvoidingRandomFlyingGoal {
    //    public SparrowWanderGoal(PathfinderMob p_186224_, double p_186225_) {
    //        super(p_186224_, p_186225_);
    //    }
//
    //    @Nullable
    //    protected Vec3 getPosition() {
    //        Vec3 vec3 = null;
    //        if (this.mob.isInWater()) {
    //            vec3 = LandRandomPos.getPos(this.mob, 15, 15);
    //        }
//
    //        if (this.mob.getRandom().nextFloat() >= this.probability) {
    //            vec3 = this.getTreePos();
    //        }
//
    //        return vec3 == null ? super.getPosition() : vec3;
    //    }
//
    //    @Nullable
    //    private Vec3 getTreePos() {
    //        BlockPos blockpos = this.mob.blockPosition();
    //        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
    //        BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();
//
    //        for (BlockPos blockpos1 : BlockPos.betweenClosed(Mth.floor(this.mob.getX() - 3.0D), Mth.floor(this.mob.getY() - 6.0D), Mth.floor(this.mob.getZ() - 3.0D), Mth.floor(this.mob.getX() + 3.0D), Mth.floor(this.mob.getY() + 6.0D), Mth.floor(this.mob.getZ() + 3.0D))) {
    //            if (!blockpos.equals(blockpos1)) {
    //                BlockState blockstate = this.mob.level().getBlockState(blockpos$mutableblockpos1.setWithOffset(blockpos1, Direction.DOWN));
    //                boolean flag = blockstate.getBlock() instanceof LeavesBlock || blockstate.is(BlockTags.LOGS);
    //                if (flag && this.mob.level().isEmptyBlock(blockpos1) && this.mob.level().isEmptyBlock(blockpos$mutableblockpos.setWithOffset(blockpos1, Direction.UP))) {
    //                    return Vec3.atBottomCenterOf(blockpos1);
    //                }
    //            }
    //        }
//
    //        return null;
    //    }
    //}


    public class DeerStrippedGoal extends MoveToBlockGoal {
        private static final int WAIT_TICKS = 40;
        protected int ticksWaited;

        public DeerStrippedGoal(double pSpeedModifier, int pSearchRange, int pVerticalSearchRange) {
            super(WoodPecker.this, pSpeedModifier, pSearchRange, pVerticalSearchRange);
        }

        public double acceptedDistance() {
            return 5.0D;
        }

        public boolean shouldRecalculatePath() {
            return this.tryTicks % 100 == 0;
        }

        /**
         * Return true to set given position as destination
         */
        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            BlockState blockstate = pLevel.getBlockState(pPos);
            return blockstate.is(ModBlocks.MAPLE_LOG.get());
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (this.isReachedTarget()) {
                if (this.ticksWaited >= 20) {
                    this.onReachedTarget();
                } else {
                    ++this.ticksWaited;
                }
            } else if (!this.isReachedTarget() && WoodPecker.this.random.nextFloat() < 0.05F) {
                WoodPecker.this.playSound(SoundEvents.PHANTOM_FLAP, 1.0F, 1.0F);
            }

            super.tick();
        }

        protected void onReachedTarget() {
            if (net.neoforged.neoforge.common.NeoForge.EVENT_BUS.post(new net.neoforged.neoforge.event.entity.EntityMobGriefingEvent(WoodPecker.this.level(), WoodPecker.this)).canGrief()) {
                BlockState blockstate = WoodPecker.this.level().getBlockState(this.blockPos);
                if (blockstate.is(ModBlocks.MAPLE_LOG.get())) {
                    this.stripped(blockstate, blockPos);
                    ticksWaited = 0;
                }
            }
        }

        private void stripped(BlockState pState , BlockPos pos) {
            int j = 2 + WoodPecker.this.level().random.nextInt(8);
            ItemStack itemstack = WoodPecker.this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (itemstack.isEmpty()) {
                WoodPecker.this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.MAPLE_SYRUP.get()));
                --j;
            }

            if (j > 0) {
                Block.popResource(WoodPecker.this.level(), this.blockPos, new ItemStack(ModItems.MAPLE_SYRUP.get(), j));
            }

            WoodPecker.this.playSound(SoundEvents.AXE_STRIP, 1.0F, 1.0F);
            WoodPecker.this.level().setBlock(pos, ModBlocks.STRIPPED_MAPLE_LOG.get().defaultBlockState(),11);
        }

        public boolean canUse() {
            return !WoodPecker.this.isSleeping() && super.canUse();
        }

        public void start() {
            this.ticksWaited = 0;
            super.start();
        }
    }
}