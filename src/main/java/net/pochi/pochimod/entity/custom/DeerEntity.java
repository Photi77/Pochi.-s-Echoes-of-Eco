package net.pochi.pochimod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.pochi.pochimod.block.ModBlocks;
import net.pochi.pochimod.entity.ModEntityTypes;
import net.pochi.pochimod.item.ModItems;

import javax.annotation.Nullable;

public class DeerEntity extends AbstractHorse{

    public final net.minecraft.world.entity.AnimationState idleAnimationState = new net.minecraft.world.entity.AnimationState();
    private int idleAnimationTimeout = 0;
    private BlockPos blockPos = BlockPos.ZERO;

    public DeerEntity(EntityType<? extends DeerEntity> entityType, Level level) {
        super(entityType, level);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.2D));
        this.goalSelector.addGoal(1, new RunAroundLikeCrazyGoal(this, 1.2D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D, DoeEntity.class));
        this.goalSelector.addGoal(3, new DeerStrippedGoal(1.0F,12,4));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.7D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        if (this.canPerformRearing()) {
            this.goalSelector.addGoal(9, new RandomStandGoal(this));
        }
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15D)
                .add(Attributes.JUMP_STRENGTH, 0.9F)
                .add(Attributes.MOVEMENT_SPEED, 0.30F).build();
    }


    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return pSize.height() * 1.0F;
    }


    protected SoundEvent getAmbientSound() {
        return SoundEvents.HORSE_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.HORSE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.HORSE_DEATH;
    }

    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(SoundEvents.HORSE_STEP, 0.15F, 1.0F);
    }

    protected float getSoundVolume() {
        return 0.2F;
    }

    public InteractionResult mobInteract(Player p_30713_, InteractionHand p_30714_) {
        boolean flag = !this.isBaby() && this.isTamed() && p_30713_.isSecondaryUseActive();
        if (!this.isVehicle() && !flag) {
            ItemStack itemstack = p_30713_.getItemInHand(p_30714_);
            if (!itemstack.isEmpty()) {
                if (this.isFood(itemstack)) {
                    return this.fedFood(p_30713_, itemstack);
                }

                if (!this.isTamed()) {
                    this.makeMad();
                    return InteractionResult.sidedSuccess(this.level().isClientSide);
                }
            }

            return super.mobInteract(p_30713_, p_30714_);
        } else {
            return super.mobInteract(p_30713_, p_30714_);
        }
    }

    public boolean canMate(Animal p_30698_) {
        if (p_30698_ == this) {
            return false;
        } else if (!(p_30698_ instanceof DoeEntity)) {
            return false;
        } else {
            return this.canParent();
        }
    }

    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel p_149533_, AgeableMob p_149534_) {
        if (p_149534_ instanceof DoeEntity) {
            if (this.random.nextInt(1) == 0) {
                return ModEntityTypes.DOE.get().create(p_149533_);
            }
            return ModEntityTypes.DEER.get().create(p_149533_);
        } else {
            return null;
        }
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

    //シナモンのstrippedmethod
    public class DeerStrippedGoal extends MoveToBlockGoal {
        private static final int WAIT_TICKS = 40;
        protected int ticksWaited;

        public DeerStrippedGoal(double pSpeedModifier, int pSearchRange, int pVerticalSearchRange) {
            super(DeerEntity.this, pSpeedModifier, pSearchRange, pVerticalSearchRange);
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
            return blockstate.is(ModBlocks.CINNAMON_LOG.get());
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (this.isReachedTarget()) {
                if (this.ticksWaited >= 1200) {
                    this.onReachedTarget();
                } else {
                    ++this.ticksWaited;
                }
            } else if (!this.isReachedTarget() && DeerEntity.this.random.nextFloat() < 0.05F) {
                DeerEntity.this.playSound(SoundEvents.HORSE_STEP, 1.0F, 1.0F);
            }

            super.tick();
        }

        protected void onReachedTarget() {
            if (net.neoforged.neoforge.common.NeoForge.EVENT_BUS.post(new net.neoforged.neoforge.event.entity.EntityMobGriefingEvent(DeerEntity.this.level(), DeerEntity.this)).canGrief()) {
                BlockState blockstate = DeerEntity.this.level().getBlockState(this.blockPos);
                if (blockstate.is(ModBlocks.CINNAMON_LOG.get())) {
                    this.stripped(blockstate, blockPos);
                    ticksWaited = 0;
                }
            }
        }

        private void stripped(BlockState pState , BlockPos pos) {
            int j = 1 + DeerEntity.this.level().random.nextInt(2);
            ItemStack itemstack = DeerEntity.this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (itemstack.isEmpty()) {
                DeerEntity.this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.CINNAMON.get()));
                --j;
            }

            if (j > 0) {
                Block.popResource(DeerEntity.this.level(), this.blockPos, new ItemStack(ModItems.CINNAMON.get(), j));
            }

            DeerEntity.this.playSound(SoundEvents.AXE_STRIP, 1.0F, 1.0F);
            DeerEntity.this.level().setBlock(pos, ModBlocks.STRIPPED_CINNAMON_LOG.get().defaultBlockState(),11);
        }

        public boolean canUse() {
            return !DeerEntity.this.isSleeping() && super.canUse();
        }

        public void start() {
            this.ticksWaited = 0;
            super.start();
        }
    }
}

