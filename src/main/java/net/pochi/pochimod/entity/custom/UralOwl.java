package net.pochi.pochimod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.pochi.pochimod.entity.ModEntityTypes;

import javax.annotation.Nullable;
import java.util.*;

public class UralOwl extends Animal implements FlyingAnimal {

    public final AnimationState idleAnimationState = new AnimationState();

    private int idleAnimationTimeout = 0;
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    private float flapping = 1.0F;
    private float nextFlap = 1.0F;

    // 成長促進範囲
    private static final int GROWTH_RANGE = 8; // 8ブロック範囲
    private static final int GROWTH_CHECK_INTERVAL = 100; // 5秒ごと
    private int growthTimer = 0;

    // 成長させた苗木の記録（同じ苗木を連続で成長させないため）
    private final Set<BlockPos> recentlyGrownSaplings = new HashSet<>();
    private static final int GROWN_MEMORY_TIME = 1200; // 60秒間は同じ苗木を成長させない
    private final Map<BlockPos, Integer> grownSaplingTimers = new HashMap<>();

    // パーティクル用
    @Nullable
    private BlockPos currentGrowthTarget = null;
    private int growthAnimationTimer = 0;
    private static final int GROWTH_ANIMATION_DURATION = 40; // 2秒

    public UralOwl(EntityType<? extends Animal> entityType, Level level) {
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
                .add(Attributes.FLYING_SPEED, (double)4.0F)
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
        this.goalSelector.addGoal(2, new Peacock.SparrowWanderGoal(this, 1.2D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new OwlPatrolGoal(this, 1.0D)); // 周辺を巡回
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.of(Items.WHEAT), false));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        //this.goalSelector.addGoal(3, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
    }



    @Override
    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("GrowthTimer", this.growthTimer);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.growthTimer = tag.getInt("GrowthTimer");
    }

    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return pSize.height() * 0.6F;
    }

    public void aiStep() {
        super.aiStep();
        this.calculateFlapping();

        // 通常時もたまに魔法的なパーティクルを出す
        if (this.level().isClientSide && this.random.nextInt(20) == 0) {
            this.level().addParticle(
                    ParticleTypes.ENCHANT,
                    this.getX() + (this.random.nextDouble() - 0.5) * 0.5,
                    this.getY() + this.random.nextDouble() * this.getBbHeight(),
                    this.getZ() + (this.random.nextDouble() - 0.5) * 0.5,
                    0, 0.02, 0
            );
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }

        if (!this.level().isClientSide) {
            // タイマーの更新
            this.growthTimer++;

            // 記録された苗木のタイマーを更新
            updateGrownSaplingsMemory();

            // 定期的に苗木を成長させる
            if (this.growthTimer >= GROWTH_CHECK_INTERVAL) {
                this.growthTimer = 0;
                tryGrowNearbyPlants();
            }
        }

        // 成長アニメーション中
        if (this.currentGrowthTarget != null) {
            this.growthAnimationTimer++;

            if (this.level().isClientSide) {
                spawnGrowthParticles();
            }

            if (this.growthAnimationTimer >= GROWTH_ANIMATION_DURATION) {
                this.currentGrowthTarget = null;
                this.growthAnimationTimer = 0;
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    private void spawnGrowthParticles() {
        if (this.currentGrowthTarget == null) {
            return;
        }

        BlockPos target = this.currentGrowthTarget;

        // フクロウから苗木へのエネルギービーム
        if (this.growthAnimationTimer % 2 == 0) {
            Vec3 start = this.position().add(0, this.getBbHeight() / 2, 0);
            Vec3 end = Vec3.atCenterOf(target);

            Vec3 direction = end.subtract(start);
            int steps = (int)(direction.length() * 4);

            for (int i = 0; i < steps; i++) {
                double progress = i / (double)steps;
                Vec3 pos = start.add(direction.scale(progress));

                this.level().addParticle(
                        ParticleTypes.ENCHANT,
                        pos.x + (this.random.nextDouble() - 0.5) * 0.1,
                        pos.y + (this.random.nextDouble() - 0.5) * 0.1,
                        pos.z + (this.random.nextDouble() - 0.5) * 0.1,
                        0, 0.05, 0
                );
            }
        }

        // 苗木周辺のパーティクル
        if (this.growthAnimationTimer % 5 == 0) {
            for (int i = 0; i < 3; i++) {
                double offsetX = (this.random.nextDouble() - 0.5) * 0.8;
                double offsetZ = (this.random.nextDouble() - 0.5) * 0.8;

                this.level().addParticle(
                        ParticleTypes.HAPPY_VILLAGER,
                        target.getX() + 0.5 + offsetX,
                        target.getY() + 0.5,
                        target.getZ() + 0.5 + offsetZ,
                        0, 0.1, 0
                );
            }
        }

        // フクロウ周辺の魔法陣のようなエフェクト
        if (this.growthAnimationTimer % 3 == 0) {
            double angle = (this.growthAnimationTimer * 0.2) % (Math.PI * 2);
            double radius = 1.0;

            double x = this.getX() + Math.cos(angle) * radius;
            double z = this.getZ() + Math.sin(angle) * radius;

            this.level().addParticle(
                    ParticleTypes.ENCHANT,
                    x,
                    this.getY() + 0.5,
                    z,
                    0, 0.05, 0
            );
        }
    }



    private void updateGrownSaplingsMemory() {
        // 時間経過で記憶を削除
        Iterator<Map.Entry<BlockPos, Integer>> iterator = this.grownSaplingTimers.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<BlockPos, Integer> entry = iterator.next();
            int timeLeft = entry.getValue() - 1;

            if (timeLeft <= 0) {
                this.recentlyGrownSaplings.remove(entry.getKey());
                iterator.remove();
            } else {
                entry.setValue(timeLeft);
            }
        }
    }

    private void tryGrowNearbyPlants() {
        BlockPos owlPos = this.blockPosition();
        ServerLevel serverLevel = (ServerLevel) this.level();

        // 範囲内の苗木を探索
        List<BlockPos> saplingPositions = findNearbyGrowablePlants(owlPos, GROWTH_RANGE);

        if (saplingPositions.isEmpty()) {
            return;
        }

        // ランダムに1つ選択して成長させる
        BlockPos targetPos = saplingPositions.get(this.random.nextInt(saplingPositions.size()));
        growPlant(targetPos, serverLevel);
    }

    private List<BlockPos> findNearbyGrowablePlants(BlockPos center, int radius) {
        List<BlockPos> growablePlants = new ArrayList<>();
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    mutablePos.set(center.getX() + x, center.getY() + y, center.getZ() + z);

                    // 最近成長させた苗木はスキップ
                    if (this.recentlyGrownSaplings.contains(mutablePos.immutable())) {
                        continue;
                    }

                    BlockState state = this.level().getBlockState(mutablePos);

                    // 成長可能な植物かチェック
                    if (isGrowablePlant(state, mutablePos)) {
                        growablePlants.add(mutablePos.immutable());
                    }
                }
            }
        }

        return growablePlants;
    }

    private boolean isGrowablePlant(BlockState state, BlockPos pos) {
        Block block = state.getBlock();

        // 苗木
        if (block instanceof SaplingBlock) {
            return canSaplingGrow(state, pos);
        }

        // 作物（オプション：コメントアウトで苗木のみに制限可能）
        //if (block instanceof CropBlock cropBlock) {
        //    return !cropBlock.isMaxAge(state);
        //}

        // 竹
        if (block == Blocks.BAMBOO_SAPLING || block == Blocks.BAMBOO) {
            return true;
        }

        // キノコ
        if (block instanceof MushroomBlock) {
            return true;
        }

        // スイートベリー
        if (block == Blocks.SWEET_BERRY_BUSH) {
            return state.getValue(SweetBerryBushBlock.AGE) < 3;
        }

        return false;
    }

    private boolean canSaplingGrow(BlockState state, BlockPos pos) {
        Block block = state.getBlock();

        // ダークオークは2x2で生える必要があるため特殊処理
        if (block == Blocks.DARK_OAK_SAPLING) {
            return canDarkOakGrow(pos);
        }

        // 通常の苗木は上に十分な空間があればOK
        return hasEnoughSpaceToGrow(pos);
    }

    private boolean canDarkOakGrow(BlockPos pos) {
        // 2x2パターンをチェック
        int[][] patterns = {
                {0, 0}, {0, 1}, {1, 0}, {1, 1}  // 基準位置
        };

        for (int[] offset : patterns) {
            boolean canGrowHere = true;

            for (int x = 0; x < 2; x++) {
                for (int z = 0; z < 2; z++) {
                    BlockPos checkPos = pos.offset(-offset[0] + x, 0, -offset[1] + z);
                    BlockState checkState = this.level().getBlockState(checkPos);

                    if (checkState.getBlock() != Blocks.DARK_OAK_SAPLING) {
                        canGrowHere = false;
                        break;
                    }
                }
                if (!canGrowHere) break;
            }

            if (canGrowHere && hasEnoughSpaceToGrow(pos, 7)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasEnoughSpaceToGrow(BlockPos pos) {
        return hasEnoughSpaceToGrow(pos, 5);
    }

    private boolean hasEnoughSpaceToGrow(BlockPos pos, int heightNeeded) {
        // 上方向の空間チェック
        for (int y = 1; y <= heightNeeded; y++) {
            BlockPos checkPos = pos.above(y);
            BlockState state = this.level().getBlockState(checkPos);

            if (!state.isAir() && !state.is(BlockTags.LEAVES)) {
                return false;
            }
        }

        return true;
    }

    private void growPlant(BlockPos pos, ServerLevel serverLevel) {
        BlockState state = serverLevel.getBlockState(pos);
        Block block = state.getBlock();

        boolean growthSuccessful = false;

        // 苗木の成長
        if (block instanceof SaplingBlock saplingBlock) {
            growthSuccessful = growSapling(pos, state, saplingBlock, serverLevel);
        }
        // 作物の成長
        else if (block instanceof CropBlock cropBlock) {
            growthSuccessful = growCrop(pos, state, cropBlock, serverLevel);
        }
        // 竹の成長
        else if (block == Blocks.BAMBOO_SAPLING) {
            growthSuccessful = growBamboo(pos, serverLevel);
        }
        // その他のBonemeal対応植物
        else if (block instanceof BonemealableBlock bonemealable) {
            growthSuccessful = growBonemealablePlant(pos, state, bonemealable, serverLevel);
        }

        if (growthSuccessful) {
            // 成長させた苗木を記録
            this.recentlyGrownSaplings.add(pos);
            this.grownSaplingTimers.put(pos, GROWN_MEMORY_TIME);

            // アニメーション開始
            this.currentGrowthTarget = pos;
            this.growthAnimationTimer = 0;

            // エフェクト
            spawnGrowthSuccessEffects(pos, serverLevel);
        }
    }

    private boolean growSapling(BlockPos pos, BlockState state, SaplingBlock saplingBlock, ServerLevel serverLevel) {
        // 骨粉と同じ効果で成長
        if (saplingBlock.isValidBonemealTarget(serverLevel, pos, state)) {
            saplingBlock.performBonemeal(serverLevel, serverLevel.random, pos, state);
            return true;
        }

        return false;
    }

    private boolean growCrop(BlockPos pos, BlockState state, CropBlock cropBlock, ServerLevel serverLevel) {
        if (!cropBlock.isMaxAge(state)) {
            // 1段階成長させる
            int currentAge = cropBlock.getAge(state);
            int newAge = Math.min(currentAge + 1, cropBlock.getMaxAge());
            serverLevel.setBlock(pos, cropBlock.getStateForAge(newAge), 3);
            return true;
        }

        return false;
    }

    private boolean growBamboo(BlockPos pos, ServerLevel serverLevel) {
        BlockState bambooState = Blocks.BAMBOO.defaultBlockState();
        serverLevel.setBlock(pos, bambooState, 3);

        // 竹を数ブロック成長させる
        for (int i = 1; i <= 3; i++) {
            BlockPos abovePos = pos.above(i);
            if (serverLevel.getBlockState(abovePos).isAir()) {
                serverLevel.setBlock(abovePos, bambooState, 3);
            }
        }

        return true;
    }

    private boolean growBonemealablePlant(BlockPos pos, BlockState state, BonemealableBlock bonemealable, ServerLevel serverLevel) {
        if (bonemealable.isValidBonemealTarget(serverLevel, pos, state)) {
            bonemealable.performBonemeal(serverLevel, serverLevel.random, pos, state);
            return true;
        }

        return false;
    }

    private void spawnGrowthSuccessEffects(BlockPos pos, ServerLevel serverLevel) {
        // 骨粉のようなパーティクル
        serverLevel.sendParticles(
                ParticleTypes.HAPPY_VILLAGER,
                pos.getX() + 0.5,
                pos.getY() + 0.5,
                pos.getZ() + 0.5,
                15,
                0.5, 0.5, 0.5,
                0.1
        );

        // 成長音
        serverLevel.playSound(
                null,
                pos,
                SoundEvents.BONE_MEAL_USE,
                SoundSource.BLOCKS,
                1.0F,
                1.0F
        );

        // 追加の魔法的なエフェクト
        serverLevel.sendParticles(
                ParticleTypes.ENCHANT,
                pos.getX() + 0.5,
                pos.getY() + 0.5,
                pos.getZ() + 0.5,
                10,
                0.3, 0.5, 0.3,
                0.5
        );
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

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return ModEntityTypes.URAL_OWL.get().create(p_146743_);
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
        return new Vec3(0.0D, (double)(0.5F * this.getEyeHeight()), (double)(this.getBbWidth() * 0.4F));
    }

    @Override
    public boolean isFood(ItemStack p_27600_) {
        return p_27600_.is(Items.SPIDER_EYE);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    static class SparrowWanderGoal extends WaterAvoidingRandomFlyingGoal {
        public SparrowWanderGoal(PathfinderMob p_186224_, double p_186225_) {
            super(p_186224_, p_186225_);
        }

        @Nullable
        protected Vec3 getPosition() {
            Vec3 vec3 = null;
            if (this.mob.isInWater()) {
                vec3 = LandRandomPos.getPos(this.mob, 15, 15);
            }

            if (this.mob.getRandom().nextFloat() >= this.probability) {
                vec3 = this.getTreePos();
            }

            return vec3 == null ? super.getPosition() : vec3;
        }

        @Nullable
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


    // 苗木の近くを巡回するAI
    public static class OwlPatrolGoal extends Goal {
        private final UralOwl owl;
        private final double speedModifier;
        private BlockPos targetPos;
        private int searchCooldown = 0;

        public OwlPatrolGoal(UralOwl owl, double speedModifier) {
            this.owl = owl;
            this.speedModifier = speedModifier;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            if (this.searchCooldown > 0) {
                this.searchCooldown--;
                return false;
            }

            this.searchCooldown = 100; // 5秒ごとに検索
            this.targetPos = findNearbySapling();
            return this.targetPos != null;
        }

        @Override
        public boolean canContinueToUse() {
            return this.targetPos != null &&
                    !this.owl.getNavigation().isDone() &&
                    this.owl.blockPosition().distSqr(this.targetPos) > 4.0;
        }

        @Override
        public void start() {
            if (this.targetPos != null) {
                this.owl.getNavigation().moveTo(
                        this.targetPos.getX(),
                        this.targetPos.getY(),
                        this.targetPos.getZ(),
                        this.speedModifier
                );
            }
        }

        @Override
        public void stop() {
            this.targetPos = null;
            this.owl.getNavigation().stop();
        }

        private BlockPos findNearbySapling() {
            BlockPos owlPos = this.owl.blockPosition();
            BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

            for (int attempt = 0; attempt < 10; attempt++) {
                int x = owlPos.getX() + this.owl.getRandom().nextInt(17) - 8;
                int y = owlPos.getY() + this.owl.getRandom().nextInt(5) - 2;
                int z = owlPos.getZ() + this.owl.getRandom().nextInt(17) - 8;

                mutablePos.set(x, y, z);
                BlockState state = this.owl.level().getBlockState(mutablePos);

                if (state.getBlock() instanceof SaplingBlock) {
                    return mutablePos.immutable();
                }
            }

            return null;
        }
    }

}
