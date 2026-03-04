package net.pochi.pochimod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.entity.ModEntityTypes;
import net.pochi.pochimod.entity.custom.base.AgeableaterAnimal;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class ElectricCatfish extends AgeableaterAnimal {

    // アイテム引き寄せ関連
    private static final double ITEM_ATTRACT_RANGE = 12.0; // 引き寄せ範囲
    private static final double ITEM_ORBIT_RADIUS = 1.5; // アイテムが周回する半径
    private static final double ITEM_ATTRACT_SPEED = 0.15; // 引き寄せ速度
    private static final int ATTRACT_INTERVAL = 5; // 引き寄せ処理の頻度
    private int attractTimer = 0;

    // アイテム投げ出し関連
    private static final int THROW_INTERVAL_MIN = 100; // 5秒
    private static final int THROW_INTERVAL_MAX = 200; // 10秒
    private int throwTimer = 0;
    private int nextThrowTime;

    // エフェクト関連
    private static final int PARTICLE_INTERVAL = 3;
    private int particleTimer = 0;


    private static final EntityDataAccessor<Integer> MOISTNESS_LEVEL = SynchedEntityData.defineId(ElectricCatfish.class, EntityDataSerializers.INT);

    public ElectricCatfish(EntityType<? extends AgeableaterAnimal> p_30341_, Level p_30342_) {
        super(p_30341_, p_30342_);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
        this.nextThrowTime = THROW_INTERVAL_MIN + this.random.nextInt(THROW_INTERVAL_MAX - THROW_INTERVAL_MIN);
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_28332_, DifficultyInstance p_28333_, MobSpawnType p_28334_, @Nullable SpawnGroupData p_28335_) {
        this.setAirSupply(this.getMaxAirSupply());
        this.setXRot(0.0F);
        return super.finalizeSpawn(p_28332_, p_28333_, p_28334_, p_28335_);
    }


    protected void handleAirSupply(int p_28326_) {
    }

    public int getMoistnessLevel() {
        return this.entityData.get(MOISTNESS_LEVEL);
    }

    public void setMoisntessLevel(int p_28344_) {
        this.entityData.set(MOISTNESS_LEVEL, p_28344_);
    }

    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(MOISTNESS_LEVEL, 2400);
    }

    public void addAdditionalSaveData(CompoundTag p_28364_) {
        super.addAdditionalSaveData(p_28364_);
        p_28364_.putInt("Moistness", this.getMoistnessLevel());
        p_28364_.putInt("ThrowTimer", this.throwTimer);
        p_28364_.putInt("NextThrowTime", this.nextThrowTime);
    }

    public void readAdditionalSaveData(CompoundTag p_28340_) {
        super.readAdditionalSaveData(p_28340_);
        this.setMoisntessLevel(p_28340_.getInt("Moistness"));
        this.throwTimer = p_28340_.getInt("ThrowTimer");
        this.nextThrowTime = p_28340_.getInt("NextThrowTime");
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreathAirGoal(this));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(3, new MoveToItemGoal(this, 1.2D)); // アイテムに近づく
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new MeleeAttackGoal(this, (double)1.2F, true));
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
        this.goalSelector.addGoal(9, new AvoidEntityGoal<>(this, Guardian.class, 8.0F, 1.0D, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Guardian.class)).setAlertOthers());
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return ModEntityTypes.ELECTRIC_CATFISH.get().create(p_146743_);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, (double)1.2F).add(Attributes.ATTACK_DAMAGE, 3.0D);
    }

    protected PathNavigation createNavigation(Level p_28362_) {
        return new WaterBoundPathNavigation(this, p_28362_);
    }

    public boolean doHurtTarget(Entity p_28319_) {
        boolean flag = p_28319_.hurt(this.damageSources().mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            this.playSound(SoundEvents.DOLPHIN_ATTACK, 1.0F, 1.0F);
        }

        return flag;
    }

    public int getMaxAirSupply() {
        return 4800;
    }

    protected int increaseAirSupply(int p_28389_) {
        return this.getMaxAirSupply();
    }

    protected float getStandingEyeHeight(Pose p_28352_, EntityDimensions p_28353_) {
        return 0.3F;
    }

    public int getMaxHeadXRot() {
        return 1;
    }

    public int getMaxHeadYRot() {
        return 1;
    }

    protected boolean canRide(Entity p_28391_) {
        return true;
    }

    public void tick() {
        super.tick();
        if (this.isNoAi()) {
            this.setAirSupply(this.getMaxAirSupply());
        } else {
            if (this.isInWaterRainOrBubble()) {
                this.setMoisntessLevel(2400);
            } else {
                this.setMoisntessLevel(this.getMoistnessLevel() - 1);
                if (this.getMoistnessLevel() <= 0) {
                    this.hurt(this.damageSources().dryOut(), 1.0F);
                }

                if (this.onGround()) {
                    this.setDeltaMovement(this.getDeltaMovement().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F), 0.5D, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F)));
                    this.setYRot(this.random.nextFloat() * 360.0F);
                    this.setOnGround(false);
                    this.hasImpulse = true;
                }
            }

            if (this.level().isClientSide && this.isInWater() && this.getDeltaMovement().lengthSqr() > 0.03D) {
                Vec3 vec3 = this.getViewVector(0.0F);
                float f = Mth.cos(this.getYRot() * ((float)Math.PI / 180F)) * 0.3F;
                float f1 = Mth.sin(this.getYRot() * ((float)Math.PI / 180F)) * 0.3F;
                float f2 = 1.2F - this.random.nextFloat() * 0.7F;

                for(int i = 0; i < 2; ++i) {
                    this.level().addParticle(ParticleTypes.DOLPHIN, this.getX() - vec3.x * (double)f2 + (double)f, this.getY() - vec3.y, this.getZ() - vec3.z * (double)f2 + (double)f1, 0.0D, 0.0D, 0.0D);
                    this.level().addParticle(ParticleTypes.DOLPHIN, this.getX() - vec3.x * (double)f2 - (double)f, this.getY() - vec3.y, this.getZ() - vec3.z * (double)f2 - (double)f1, 0.0D, 0.0D, 0.0D);
                }
            }

        }

        if (!this.level().isClientSide) {
            // アイテム引き寄せ処理
            this.attractTimer++;
            if (this.attractTimer >= ATTRACT_INTERVAL) {
                this.attractTimer = 0;
                attractAndOrbitItems();
            }

            // アイテム投げ出し処理
            this.throwTimer++;
            if (this.throwTimer >= this.nextThrowTime) {
                throwItemsToLand();
                this.throwTimer = 0;
                this.nextThrowTime = THROW_INTERVAL_MIN + this.random.nextInt(THROW_INTERVAL_MAX - THROW_INTERVAL_MIN);
            }
        }

        // パーティクルエフェクト
        if (this.level().isClientSide) {
            this.particleTimer++;
            if (this.particleTimer >= PARTICLE_INTERVAL) {
                this.particleTimer = 0;
                //spawnElectricParticles();
            }
        }
    }

    private void attractAndOrbitItems() {
        // 範囲内のアイテムエンティティを取得
        List<ItemEntity> nearbyItems = this.level().getEntitiesOfClass(
                ItemEntity.class,
                this.getBoundingBox().inflate(ITEM_ATTRACT_RANGE),
                item -> item.isAlive() && !item.hasPickUpDelay()
        );

        for (ItemEntity itemEntity : nearbyItems) {
            double distance = itemEntity.distanceTo(this);

            if (distance > ITEM_ORBIT_RADIUS + 0.5) {
                // まだ遠い場合は引き寄せる
                attractItem(itemEntity, distance);
            } else {
                // 十分近い場合は周回させる
                orbitItem(itemEntity);
            }
        }
    }

    private void attractItem(ItemEntity itemEntity, double distance) {
        Vec3 itemPos = itemEntity.position();
        Vec3 fishPos = this.position().add(0, this.getBbHeight() / 2, 0);

        // ナマズの方向へのベクトルを計算
        Vec3 direction = fishPos.subtract(itemPos);

        // Y軸方向の調整（ナマズの中心高さに合わせる）
        if (Math.abs(direction.y) > 0.1) {
            direction = direction.normalize();
        } else {
            direction = new Vec3(direction.x, 0, direction.z).normalize();
        }

        // 距離に応じて引き寄せ速度を調整
        double speedMultiplier = Math.max(0.3, 1.0 - (distance / ITEM_ATTRACT_RANGE));
        Vec3 attractVelocity = direction.scale(ITEM_ATTRACT_SPEED * speedMultiplier);

        // 水の抵抗を考慮した速度設定
        Vec3 currentVelocity = itemEntity.getDeltaMovement();
        Vec3 newVelocity = currentVelocity.scale(0.8).add(attractVelocity);

        // 速度制限
        double maxSpeed = 0.4;
        if (newVelocity.length() > maxSpeed) {
            newVelocity = newVelocity.normalize().scale(maxSpeed);
        }

        if(itemEntity.isInWater()) {
            itemEntity.setDeltaMovement(newVelocity);
        }

        // アイテムの寿命を延長（消えないように）
        itemEntity.setUnlimitedLifetime();
    }

    private void orbitItem(ItemEntity itemEntity) {
        Vec3 fishPos = this.position().add(0, this.getBbHeight() / 2, 0);
        Vec3 itemPos = itemEntity.position();

        // ナマズからの方向ベクトル
        Vec3 fromCenter = itemPos.subtract(fishPos);
        double currentDistance = fromCenter.length();

        // 理想的な軌道半径を維持
        if (currentDistance < 0.1) {
            currentDistance = 0.1;
        }

        Vec3 normalizedDirection = fromCenter.normalize();

        // 回転ベクトルを計算（Y軸周りに回転）
        double angle = 0.05; // 回転速度
        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);

        Vec3 tangent = new Vec3(
                normalizedDirection.x * cosAngle - normalizedDirection.z * sinAngle,
                0,
                normalizedDirection.x * sinAngle + normalizedDirection.z * cosAngle
        );

        // 求心力と接線速度の合成
        Vec3 centripetal = normalizedDirection.scale(-0.1); // 中心に向かう力
        Vec3 orbital = tangent.scale(0.15); // 接線方向の速度

        // 軌道半径を維持する力
        double radiusDiff = currentDistance - ITEM_ORBIT_RADIUS;
        Vec3 radiusCorrection = normalizedDirection.scale(-radiusDiff * 0.2);

        Vec3 targetVelocity = orbital.add(centripetal).add(radiusCorrection);

        // 上下の揺らぎを追加（より自然な動き）
        double verticalWave = Math.sin(this.tickCount * 0.1 + itemEntity.getId()) * 0.02;
        targetVelocity = targetVelocity.add(0, verticalWave, 0);

        // スムーズな速度変更
        Vec3 currentVelocity = itemEntity.getDeltaMovement();
        Vec3 newVelocity = currentVelocity.scale(0.7).add(targetVelocity.scale(0.3));

        itemEntity.setDeltaMovement(newVelocity);
        itemEntity.setUnlimitedLifetime();
    }

    public void handleEntityEvent(byte p_28324_) {
        if (p_28324_ == 38) {
            this.addParticlesAroundSelf(ParticleTypes.HAPPY_VILLAGER);
        } else {
            super.handleEntityEvent(p_28324_);
        }

    }

    private void addParticlesAroundSelf(ParticleOptions p_28338_) {
        for(int i = 0; i < 7; ++i) {
            double d0 = this.random.nextGaussian() * 0.01D;
            double d1 = this.random.nextGaussian() * 0.01D;
            double d2 = this.random.nextGaussian() * 0.01D;
            this.level().addParticle(p_28338_, this.getRandomX(1.0D), this.getRandomY() + 0.2D, this.getRandomZ(1.0D), d0, d1, d2);
        }

    }

    public InteractionResult mobInteract(Player p_28359_, InteractionHand p_28360_) {
        ItemStack itemstack = p_28359_.getItemInHand(p_28360_);
        if (!itemstack.isEmpty() && itemstack.is(ItemTags.FISHES)) {
            if (!this.level().isClientSide) {
                this.playSound(SoundEvents.DOLPHIN_EAT, 1.0F, 1.0F);
            }

            if (!p_28359_.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(p_28359_, p_28360_);
        }
    }

    protected SoundEvent getHurtSound(DamageSource p_28374_) {
        return SoundEvents.DOLPHIN_HURT;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.DOLPHIN_DEATH;
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return this.isInWater() ? SoundEvents.DOLPHIN_AMBIENT_WATER : SoundEvents.DOLPHIN_AMBIENT;
    }

    protected SoundEvent getSwimSplashSound() {
        return SoundEvents.DOLPHIN_SPLASH;
    }

    protected SoundEvent getSwimSound() {
        return SoundEvents.DOLPHIN_SWIM;
    }

    public void travel(Vec3 p_28383_) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), p_28383_);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(p_28383_);
        }
    }

    public boolean canBeLeashed(Player p_28330_) {
        return true;
    }

    private void throwItemsToLand() {
        // 周囲のアイテムを取得
        List<ItemEntity> orbitingItems = this.level().getEntitiesOfClass(
                ItemEntity.class,
                this.getBoundingBox().inflate(ITEM_ORBIT_RADIUS + 1.0),
                item -> item.isAlive()
        );

        if (orbitingItems.isEmpty()) {
            return;
        }

        // 最も近い陸地を探す
        BlockPos landPos = findNearestLand(24);

        if (landPos != null) {
            // 陸地が見つかった場合、そこに向けて投げる
            for (ItemEntity item : orbitingItems) {
                throwItemToPosition(item, landPos);
            }

            // 投げ出しエフェクト
            this.level().broadcastEntityEvent(this, (byte) 48);
            playThrowSound();
        }
    }

    private BlockPos findNearestLand(int radius) {
        BlockPos center = this.blockPosition();
        Level level = this.level();

        List<BlockPos> landCandidates = new ArrayList<>();

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                // 円形範囲に絞る
                if (x * x + z * z > radius * radius) continue;

                BlockPos columnBase = new BlockPos(center.getX() + x, 0, center.getZ() + z);

                // ハイトマップで地表を取得（葉・水面を除く）
                BlockPos surfacePos = level.getHeightmapPos(
                        Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, columnBase
                );
                // surfacePosは「最初に遮るブロックの1つ上の空気」なので、
                // surfacePos.below() が地表ブロック

                if (isLandSurface(surfacePos, level)) {
                    landCandidates.add(surfacePos);
                }
            }
        }

        if (landCandidates.isEmpty()) return null;

        return landCandidates.stream()
                .min(Comparator.comparingDouble(pos -> pos.distSqr(center)))
                .orElse(null);
    }

    private boolean isLandSurface(BlockPos pos, Level level) {
        BlockState posState   = level.getBlockState(pos);          // 着地点（空気であるべき）
        BlockState belowState = level.getBlockState(pos.below());  // 足場ブロック

        return (posState.isAir() || posState.getFluidState().isEmpty())
                && belowState.isSolidRender(level, pos.below())
                && !belowState.liquid()
                && !level.isWaterAt(pos)
                && !level.isWaterAt(pos.below());
    }


    private void throwItemToPosition(ItemEntity item, BlockPos targetPos) {
        Vec3 itemPos = item.position();
        Vec3 target = Vec3.atBottomCenterOf(targetPos);

        // 放物線軌道を計算
        Vec3 direction = target.subtract(itemPos);
        double horizontalDist = Math.sqrt(direction.x * direction.x + direction.z * direction.z);
        double verticalDist = direction.y;

        // 投射角度と初速を計算
        double gravity = 0.08; // Minecraftの重力加速度
        double angle = Math.PI / 4; // 45度

        double velocity = Math.sqrt(horizontalDist * gravity / Math.sin(2 * angle));
        velocity = Math.min(velocity, 1.5); // 最大速度制限

        Vec3 horizontalDirection = new Vec3(direction.x, 0, direction.z).normalize();
        double vx = horizontalDirection.x * velocity * Math.cos(angle);
        double vy = velocity * Math.sin(angle);
        double vz = horizontalDirection.z * velocity * Math.cos(angle);

        // ランダムな誤差を追加
        vx += (this.random.nextDouble() - 0.5) * 0.2;
        vy += (this.random.nextDouble() - 0.5) * 0.1;
        vz += (this.random.nextDouble() - 0.5) * 0.2;

        item.setDeltaMovement(vx, vy, vz);

        // 通常のアイテムの寿命に戻す
        item.setDefaultPickUpDelay();
    }

    private void throwItemRandomly(ItemEntity item) {
        // ランダムな方向に投げる
        double angle = this.random.nextDouble() * Math.PI * 2;
        double speed = 0.3 + this.random.nextDouble() * 0.4;

        double vx = Math.cos(angle) * speed;
        double vy = 0.3 + this.random.nextDouble() * 0.3;
        double vz = Math.sin(angle) * speed;

        item.setDeltaMovement(vx, vy, vz);
        item.setDefaultPickUpDelay();
    }

    private void playThrowSound() {
        this.level().playSound(
                null,
                this.getX(),
                this.getY(),
                this.getZ(),
                SoundEvents.DOLPHIN_SPLASH,
                this.getSoundSource(),
                1.0F,
                0.8F + this.random.nextFloat() * 0.4F
        );
    }

    public class MoveToItemGoal extends Goal {
        private final ElectricCatfish catfish;
        private final double speedModifier;
        private ItemEntity targetItem;
        private int searchCooldown = 0;

        public MoveToItemGoal(ElectricCatfish catfish, double speedModifier) {
            this.catfish = catfish;
            this.speedModifier = speedModifier;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {

            if (this.searchCooldown > 0) {
                this.searchCooldown--;
                return false;
            }

            this.searchCooldown = 40; // 2秒ごと
            this.targetItem = findNearestItem();
            return this.targetItem != null;
        }

        @Override
        public boolean canContinueToUse() {
            return this.targetItem != null
                    && this.targetItem.isAlive()
                    && this.catfish.distanceToSqr(this.targetItem) > 4.0;
        }

        @Override
        public void start() {
            if (this.targetItem != null) {
                this.catfish.getNavigation().moveTo(this.targetItem, this.speedModifier);
            }
        }

        @Override
        public void stop() {
            this.targetItem = null;
            this.catfish.getNavigation().stop();
        }

        private ItemEntity findNearestItem() {
            List<ItemEntity> items = this.catfish.level().getEntitiesOfClass(
                    ItemEntity.class,
                    this.catfish.getBoundingBox().inflate(ITEM_ATTRACT_RANGE),
                    item -> item.isAlive() && !item.hasPickUpDelay()
            );

            return items.stream()
                    .min(Comparator.comparingDouble(item -> this.catfish.distanceToSqr(item)))
                    .orElse(null);
        }
    }
}
