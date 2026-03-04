package net.pochi.pochimod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.pochi.pochimod.effect.ModEffects;
import net.pochi.pochimod.entity.ModEntityTypes;
import net.pochi.pochimod.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Skunk extends Animal {

    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.PUMPKIN, Items.MELON,Items.WHEAT, ModItems.CABBAGE_LEAF.get());
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, Ingredient.of(Items.CARROT_ON_A_STICK), false));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(3, new DeerStrippedGoal(1.0F,12,4));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    public Skunk(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }


    public boolean hurt(DamageSource p_32820_, float p_32821_) {
        AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
        areaeffectcloud.setOwner(this);
        areaeffectcloud.setParticle(ParticleTypes.WITCH);
        areaeffectcloud.setRadius(3.0F);
        areaeffectcloud.addEffect(new MobEffectInstance(MobEffects.WITHER, 40, 2));
        //areaeffectcloud.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 300, 6));
        areaeffectcloud.setDuration(200);
        areaeffectcloud.setRadiusPerTick((7.0F - areaeffectcloud.getRadius()) / (float) 200);
        areaeffectcloud.setPos(this.getX(), this.getY(), this.getZ());
        this.level().addFreshEntity(areaeffectcloud);
        return super.hurt(p_32820_, p_32821_);
    }


    protected SoundEvent getAmbientSound() {
        return SoundEvents.PIG_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_29502_) {
        return SoundEvents.PIG_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.PIG_DEATH;
    }

    protected void playStepSound(BlockPos p_29492_, BlockState p_29493_) {
        this.playSound(SoundEvents.PIG_STEP, 0.15F, 1.0F);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return ModEntityTypes.SKUNK.get().create(p_146743_);
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
    public boolean addEffect(MobEffectInstance p_182394_, @javax.annotation.Nullable Entity p_182395_) {
        return p_182394_.getEffect().value().getCategory() != MobEffectCategory.HARMFUL;
    }

    @Override
    public boolean isFood(ItemStack p_27600_) {
        return p_27600_.is(Items.HONEYCOMB);
    }

    public class DeerStrippedGoal extends MoveToBlockGoal {
        private static final int WAIT_TICKS = 40;
        protected int ticksWaited;

        public DeerStrippedGoal(double pSpeedModifier, int pSearchRange, int pVerticalSearchRange) {
            super(Skunk.this, pSpeedModifier, pSearchRange, pVerticalSearchRange);
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
            return (blockstate.is(Blocks.BEEHIVE) || blockstate.is(Blocks.BEE_NEST))
                    && blockstate.getValue(BlockStateProperties.LEVEL_HONEY) >= 5;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (this.isReachedTarget()) {
                if (this.ticksWaited >= 40) {
                    this.onReachedTarget();
                } else {
                    ++this.ticksWaited;
                }
            } else if (!this.isReachedTarget() && Skunk.this.random.nextFloat() < 0.05F) {
                Skunk.this.playSound(SoundEvents.HORSE_STEP, 1.0F, 1.0F);
            }

            super.tick();
        }

        protected void onReachedTarget() {
            if (net.neoforged.neoforge.common.NeoForge.EVENT_BUS.post(new net.neoforged.neoforge.event.entity.EntityMobGriefingEvent(Skunk.this.level(), Skunk.this)).canGrief()) {
                BlockState blockstate = Skunk.this.level().getBlockState(this.blockPos);
                if (blockstate.is(Blocks.BEEHIVE) || blockstate.is(Blocks.BEE_NEST)) {
                    this.stripped(blockstate, blockPos);
                }
            }
        }

        private void stripped(BlockState pState , BlockPos pos) {
            int i = pState.getValue(BlockStateProperties.LEVEL_HONEY);
            pState.setValue(BlockStateProperties.LEVEL_HONEY, Integer.valueOf(0));
            List<LivingEntity> list = Skunk.this.level().getEntitiesOfClass(LivingEntity.class, Skunk.this.getBoundingBox().inflate(10,10,10));
            if (!list.isEmpty()) {
                for (LivingEntity entity : list) {
                    double d0 = Skunk.this.distanceToSqr(entity.getX(), entity.getY(), entity.getZ());
                    if (d0 < 10.0D) {
                        entity.addEffect(new MobEffectInstance(ModEffects.HONEY,5 + random.nextInt(20)));
                    }
                }
            }
            Skunk.this.playSound(SoundEvents.AXE_STRIP, 1.0F, 1.0F);
            Skunk.this.level().setBlock(pos, pState.setValue(BlockStateProperties.LEVEL_HONEY, Integer.valueOf(0)),11);
        }

        public boolean canUse() {
            return !Skunk.this.isSleeping() && super.canUse();
        }

        public void start() {
            this.ticksWaited = 0;
            super.start();
        }
    }
}