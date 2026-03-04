package net.pochi.pochimod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.pochi.pochimod.entity.ModEntityTypes;
import net.pochi.pochimod.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Wombat extends Animal {

    //public int eggTime = 600;
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.PUMPKIN, Items.MELON,Items.WHEAT, ModItems.CABBAGE_LEAF.get());
    public final net.minecraft.world.entity.AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new WombatGrowCropGoal(this, 15)); // ← 追加
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, Ingredient.of(Items.CARROT_ON_A_STICK), false));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    public Wombat(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
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
        return ModEntityTypes.WOMBAT.get().create(p_146743_);
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
    public boolean isFood(ItemStack p_27600_) {
        return p_27600_.is(Items.CARROT);
    }

    class WombatGrowCropGoal extends Goal {

        private final Wombat wombat;
        private final int searchRadius;
        private BlockPos targetCrop = null;

        // クールダウン（tick単位、600 = 30秒）
        private int lastGrowTick = -600; // 初回はすぐ発動できるよう負値にしておく
        private static final int COOLDOWN = 200;

        public WombatGrowCropGoal(Wombat wombat, int searchRadius) {
            this.wombat = wombat;
            this.searchRadius = searchRadius;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            if (this.wombat.isBaby()) return false;
            // tickCountとの差分で判定するので毎tick呼ばれなくても正確
            if (this.wombat.tickCount - this.lastGrowTick < COOLDOWN) return false;

            this.targetCrop = findNearbyCrop();
            return this.targetCrop != null;
        }


        // 継続条件：ターゲットがまだ未成熟か
        @Override
        public boolean canContinueToUse() {
            if (this.targetCrop == null) return false;
            BlockState state = this.wombat.level().getBlockState(this.targetCrop);
            return isImmatureCrop(state);
        }

        @Override
        public void start() {
            // ターゲットに向かって歩く
            this.wombat.getNavigation().moveTo(
                    this.targetCrop.getX() + 0.5,
                    this.targetCrop.getY(),
                    this.targetCrop.getZ() + 0.5,
                    1.0D
            );
        }

        @Override
        public void tick() {
            if (this.targetCrop == null) return;

            // 十分近づいたら成長処理
            if (this.wombat.distanceToSqr(
                    this.targetCrop.getX() + 0.5,
                    this.targetCrop.getY(),
                    this.targetCrop.getZ() + 0.5) < 4.0D) { // 2ブロック以内

                growCrop(this.targetCrop);
                this.targetCrop = null;
            }
        }

        @Override
        public void stop() {
            this.targetCrop = null;
            this.lastGrowTick = this.wombat.tickCount; // 停止した瞬間を記録
            this.wombat.getNavigation().stop();
        }

        // 範囲内の未成熟作物をランダムに1つ返す
        private BlockPos findNearbyCrop() {
            BlockPos center = this.wombat.blockPosition();
            List<BlockPos> candidates = new ArrayList<>();

            for (BlockPos pos : BlockPos.betweenClosed(
                    center.offset(-searchRadius, -2, -searchRadius),
                    center.offset( searchRadius,  2,  searchRadius)
            )) {
                BlockState state = this.wombat.level().getBlockState(pos);
                if (isImmatureCrop(state)) {
                    candidates.add(pos.immutable());
                }
            }

            if (candidates.isEmpty()) return null;
            return candidates.get(this.wombat.getRandom().nextInt(candidates.size()));
        }

        // 未成熟作物かどうかの判定
        private boolean isImmatureCrop(BlockState state) {
            Block block = state.getBlock();
            if (block instanceof CropBlock cropBlock) {
                return !cropBlock.isMaxAge(state);
            }
            if (block instanceof StemBlock) {
                return state.getValue(StemBlock.AGE) < 7;
            }
            return false;
        }

        // 1段階成長させる
        private void growCrop(BlockPos pos) {
            Level level = this.wombat.level();
            BlockState state = level.getBlockState(pos);
            Block block = state.getBlock();

            if (block instanceof CropBlock cropBlock) {
                int next = Math.min(cropBlock.getAge(state) + 1, cropBlock.getMaxAge());
                level.setBlock(pos, cropBlock.getStateForAge(next), 3);

            } else if (block instanceof StemBlock) {
                int next = Math.min(state.getValue(StemBlock.AGE) + 1, 7);
                level.setBlock(pos, state.setValue(StemBlock.AGE, next), 3);
            }

            // エフェクトと音
            level.levelEvent(1505, pos, 0);
            this.wombat.playSound(SoundEvents.BONE_MEAL_USE, 1.0F,
                    1.0F + (this.wombat.getRandom().nextFloat() - this.wombat.getRandom().nextFloat()) * 0.2F);
        }
    }

}