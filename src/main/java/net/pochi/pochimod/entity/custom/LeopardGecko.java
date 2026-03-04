package net.pochi.pochimod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.entity.ModEntityTypes;
import net.pochi.pochimod.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class LeopardGecko extends Animal {

    private Vec3 currentGravityDirection = new Vec3(0, -1, 0);
    private Direction attachedFace = Direction.DOWN;
    private static final float RAYCAST_DISTANCE = 0.5f;

    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.PUMPKIN, Items.MELON,Items.WHEAT, ModItems.CABBAGE_LEAF.get());
    public final net.minecraft.world.entity.AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new WallClimbingPanicGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        //this.goalSelector.addGoal(4, new WallClimbingFollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    public LeopardGecko(EntityType<? extends Animal> p_27557_, Level p_27558_) {
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
        return ModEntityTypes.LEOPARD_GECKO.get().create(p_146743_);
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

        if (!this.level().isClientSide) {
            updateAttachedSurface();
            applyCustomGravity();
        }
    }

    private void updateAttachedSurface() {
        Direction closestFace = null;
        double closestDistance = RAYCAST_DISTANCE;
        Vec3 pos = this.position();

        // 6方向全てをレイキャスト
        for (Direction dir : Direction.values()) {
            Vec3 offset = Vec3.atLowerCornerOf(dir.getNormal()).scale(RAYCAST_DISTANCE);
            BlockHitResult hit = this.level().clip(new ClipContext(
                    pos,
                    pos.add(offset),
                    ClipContext.Block.COLLIDER,
                    ClipContext.Fluid.NONE,
                    this
            ));

            if (hit.getType() == HitResult.Type.BLOCK) {
                double distance = hit.getLocation().distanceTo(pos);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestFace = hit.getDirection().getOpposite();
                }
            }
        }

        if (closestFace != null) {
            attachedFace = closestFace;
            updateGravityDirection(closestFace);
        } else {
            // 空中にいる場合は通常の重力
            attachedFace = Direction.DOWN;
            currentGravityDirection = new Vec3(0, -1, 0);
        }
    }

    private void updateGravityDirection(Direction face) {
        Vec3 targetGravity = Vec3.atLowerCornerOf(face.getNormal()).scale(-1);

        // スムーズな補間(急激な方向転換を防ぐ)
        currentGravityDirection = currentGravityDirection.lerp(targetGravity, 0.2);

        // 正規化して単位ベクトルに
        if (currentGravityDirection.lengthSqr() > 0) {
            currentGravityDirection = currentGravityDirection.normalize();
        }
    }

    private void applyCustomGravity() {
        Vec3 motion = this.getDeltaMovement();

        // カスタム重力を適用(強度0.08はバニラと同等)
        Vec3 gravity = currentGravityDirection.scale(0.08);

        if (!this.onGround() && attachedFace == Direction.DOWN) {
            // 通常の落下
            this.setDeltaMovement(motion.add(gravity));
        } else {
            // 壁/天井歩行時の重力
            this.setDeltaMovement(motion.add(gravity.scale(0.5)));
        }
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        // 壁歩行中は落下ダメージを無効化
        if (attachedFace == Direction.DOWN) {
            super.checkFallDamage(y, onGround, state, pos);
        }
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (attachedFace != Direction.DOWN) {
            // 壁/天井歩行時の移動処理
            travelOnSurface(travelVector);
        } else {
            super.travel(travelVector);
        }
    }

    private void travelOnSurface(Vec3 travelVector) {
        if (this.isEffectiveAi() || this.isControlledByLocalInstance()) {
            double gravity = 0.08;

            FluidState fluidstate = this.level().getFluidState(this.blockPosition());
            if (this.isInWater() && this.isAffectedByFluids() && !this.canStandOnFluid(fluidstate)) {
                // 水中の処理
                this.moveRelative(0.02F, travelVector);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.8));
            } else {
                // 陸上の壁/天井歩行
                BlockPos groundPos = this.getBlockPosBelowThatAffectsMyMovement();
                float friction = this.level().getBlockState(groundPos).getFriction(this.level(), groundPos, this);
                float slipperiness = this.onGround() ? friction * 0.91F : 0.91F;

                // 接地面に応じた移動ベクトルの変換
                Vec3 adjustedTravel = adjustTravelVectorToSurface(travelVector);

                this.moveRelative(this.getSpeed(), adjustedTravel);
                this.move(MoverType.SELF, this.getDeltaMovement());

                Vec3 motion = this.getDeltaMovement();
                this.setDeltaMovement(motion.x * slipperiness, motion.y * 0.98, motion.z * slipperiness);
            }
        }

        this.calculateEntityAnimation(false);
    }

    private Vec3 adjustTravelVectorToSurface(Vec3 travel) {
        // 接地面の法線ベクトルに基づいて移動方向を調整
        Vec3 normal = Vec3.atLowerCornerOf(attachedFace.getNormal());

        // 接地面に平行な移動ベクトルを計算
        Vec3 right = normal.cross(new Vec3(0, 1, 0));
        if (right.lengthSqr() < 0.001) {
            right = normal.cross(new Vec3(1, 0, 0));
        }
        right = right.normalize();

        Vec3 forward = right.cross(normal).normalize();

        return forward.scale(travel.z).add(right.scale(travel.x));
    }

    @Override
    public boolean isFood(ItemStack p_27600_) {
        return p_27600_.is(Items.HONEYCOMB);
    }

    public class WallClimbingPanicGoal extends PanicGoal {
        private final LeopardGecko gecko;

        public WallClimbingPanicGoal(LeopardGecko gecko, double speedModifier) {
            super(gecko, speedModifier);
            this.gecko = gecko;
        }

        @Override
        protected boolean findRandomPosition() {
            // 壁を登って逃げる動作を優先
            Vec3 currentPos = gecko.position();

            // 上方向への逃走を試みる
            for (int i = 0; i < 10; i++) {
                BlockPos targetPos = gecko.blockPosition().above(2 + i);
                if (isValidEscapePosition(targetPos)) {
                    this.posX = targetPos.getX();
                    this.posY = targetPos.getY();
                    this.posZ = targetPos.getZ();
                    return true;
                }
            }

            return super.findRandomPosition();
        }

        private boolean isValidEscapePosition(BlockPos pos) {
            // 隣接に壁があるか確認
            for (Direction dir : Direction.values()) {
                if (!gecko.level().getBlockState(pos.relative(dir)).isAir()) {
                    return true;
                }
            }
            return false;
        }
    }

}


