package net.pochi.pochimod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.pochi.pochimod.entity.ModEntityTypes;
import net.pochi.pochimod.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Capybara extends Animal {

    // 温泉化範囲
    private static final int HOT_SPRING_RADIUS = 5;
    private static final int UPDATE_INTERVAL = 20; // 1秒ごとに更新
    private int updateTimer = 0;

    // 温泉効果を受けているエンティティの追跡
    private Map<UUID, Integer> entitiesInHotSpring = new HashMap<>();
    private static final int EFFECT_GRACE_PERIOD = 40; // 2秒の猶予期間

    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.PUMPKIN, Items.MELON, Items.WHEAT, ModItems.CABBAGE_LEAF.get());
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, Ingredient.of(Items.CARROT_ON_A_STICK), false));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(5, new StayNearWaterGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    public Capybara(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }

    @Override
    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        // 必要に応じてentitiesInHotSpringも保存可能
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
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
        return ModEntityTypes.CAPYBARA.get().create(p_146743_);
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
        return FOOD_ITEMS.test(p_27600_);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
            // クライアント側でパーティクル
            if (this.isInWater() || isNearWater()) {
                spawnHotSpringParticles();
            }
        } else {
            // サーバー側で温泉効果管理
            this.updateTimer++;
            if (this.updateTimer >= UPDATE_INTERVAL) {
                this.updateTimer = 0;
                updateHotSprings();
            }
        }
    }

    // ========================================
    // 温泉システムのメイン処理
    // ========================================

    private void updateHotSprings() {
        // 1. 範囲内の水中エンティティに効果付与
        applyEffectsToWaterEntities();

        // 2. 範囲外に出たエンティティの効果除去
        removeEffectsFromDistantEntities();

        // 3. オプション: 周辺環境への影響
        meltNearbyIce();
    }

    private void applyEffectsToWaterEntities() {
        BlockPos center = this.blockPosition();
        AABB searchArea = new AABB(center).inflate(HOT_SPRING_RADIUS);

        List<LivingEntity> waterEntities = this.level().getEntitiesOfClass(
                LivingEntity.class,
                searchArea,
                entity -> entity.isAlive() && isInHotSpringWater(entity)
        );

        Set<UUID> currentEntities = new HashSet<>();

        for (LivingEntity entity : waterEntities) {
            UUID uuid = entity.getUUID();
            currentEntities.add(uuid);

            // 温泉効果を付与
            applyHotSpringEffects(entity);

            // 追跡リストに追加/更新
            entitiesInHotSpring.put(uuid, 0); // カウンターをリセット
        }

        // 現在範囲内にいるエンティティのカウンターをリセット
        for (UUID uuid : currentEntities) {
            entitiesInHotSpring.put(uuid, 0);
        }
    }

    private void removeEffectsFromDistantEntities() {
        Iterator<Map.Entry<UUID, Integer>> iterator = entitiesInHotSpring.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<UUID, Integer> entry = iterator.next();
            UUID uuid = entry.getKey();
            int ticksOutside = entry.getValue();

            // カウンターを増加
            entry.setValue(ticksOutside + UPDATE_INTERVAL);

            // 猶予期間を超えたら削除
            if (ticksOutside >= EFFECT_GRACE_PERIOD) {
                // エンティティを取得して効果を除去
                LivingEntity entity = findEntityByUUID(uuid);
                if (entity != null) {
                    removeHotSpringEffects(entity);
                    spawnExitParticles(entity);
                }

                iterator.remove();
            }
        }
    }

    private boolean isInHotSpringWater(LivingEntity entity) {
        if (!entity.isInWater()) return false;

        // カピバラから一定距離内の水
        double distanceSq = entity.distanceToSqr(this);
        return distanceSq <= HOT_SPRING_RADIUS * HOT_SPRING_RADIUS;
    }

    private void applyHotSpringEffects(LivingEntity entity) {
        // 基本温泉効果
        entity.addEffect(new MobEffectInstance(
                MobEffects.REGENERATION,
                60, // 3秒
                0,  // レベルI
                true,  // アンビエント
                false  // パーティクル非表示
        ));

        entity.addEffect(new MobEffectInstance(
                MobEffects.FIRE_RESISTANCE,
                60,
                0,
                true,
                false
        ));

        // プレイヤー限定の追加効果
        if (entity instanceof Player player) {
            // 満腹度回復
            entity.addEffect(new MobEffectInstance(
                    MobEffects.SATURATION,
                    40, // 2秒
                    0,
                    true,
                    false
            ));

            // リラックス効果（低確率でデバフ除去）
            if (this.random.nextInt(20) == 0) {
                player.removeEffect(MobEffects.WEAKNESS);
                player.removeEffect(MobEffects.POISON);
                player.removeEffect(MobEffects.WITHER);

                // デバフ除去時にエフェクト
                if (this.level() instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(
                            ParticleTypes.HEART,
                            player.getX(),
                            player.getY() + 1.5,
                            player.getZ(),
                            3,
                            0.5, 0.5, 0.5,
                            0
                    );
                }
            }
        }

        // 温泉パーティクル（サーバー側）
        if (this.random.nextInt(5) == 0 && this.level() instanceof ServerLevel serverLevel) {
            spawnEntityWarmParticles(entity, serverLevel);
        }
    }

    private void removeHotSpringEffects(LivingEntity entity) {
        // 温泉由来の効果のみ除去（既存の長時間効果は残す）
        MobEffectInstance regen = entity.getEffect(MobEffects.REGENERATION);
        if (regen != null && regen.getDuration() <= 60) {
            entity.removeEffect(MobEffects.REGENERATION);
        }

        MobEffectInstance fireRes = entity.getEffect(MobEffects.FIRE_RESISTANCE);
        if (fireRes != null && fireRes.getDuration() <= 60) {
            entity.removeEffect(MobEffects.FIRE_RESISTANCE);
        }

        if (entity instanceof Player) {
            entity.removeEffect(MobEffects.SATURATION);
        }
    }

    private LivingEntity findEntityByUUID(UUID uuid) {
        // 周辺のエンティティから検索
        AABB searchArea = this.getBoundingBox().inflate(HOT_SPRING_RADIUS + 5);
        List<LivingEntity> entities = this.level().getEntitiesOfClass(
                LivingEntity.class,
                searchArea
        );

        for (LivingEntity entity : entities) {
            if (entity.getUUID().equals(uuid)) {
                return entity;
            }
        }

        return null;
    }

    // ========================================
    // パーティクルエフェクト
    // ========================================

    private void spawnHotSpringParticles() {
        if (this.random.nextInt(2) != 0) return;

        Level level = this.level();

        // カピバラ周辺の水面に湯気を発生
        for (int i = 0; i < 3; i++) {
            double angle = this.random.nextDouble() * Math.PI * 2;
            double distance = this.random.nextDouble() * HOT_SPRING_RADIUS;

            double x = this.getX() + Math.cos(angle) * distance;
            double z = this.getZ() + Math.sin(angle) * distance;
            double y = this.getY();

            BlockPos checkPos = new BlockPos((int)x, (int)y, (int)z);

            // 水面を探す
            for (int j = 0; j < 3; j++) {
                if (level.getBlockState(checkPos).is(Blocks.WATER)) {
                    BlockPos above = checkPos.above();
                    if (!level.getBlockState(above).is(Blocks.WATER)) {
                        // 水面発見 - 湯気を発生
                        level.addParticle(
                                ParticleTypes.CLOUD,
                                x, above.getY() + 0.1, z,
                                0, 0.05, 0
                        );
                        break;
                    }
                }
                checkPos = checkPos.above();
            }
        }

        // カピバラ自身からも湯気
        if (this.isInWater() && this.random.nextInt(3) == 0) {
            level.addParticle(
                    ParticleTypes.CLOUD,
                    this.getX() + (this.random.nextDouble() - 0.5) * this.getBbWidth(),
                    this.getY() + this.getBbHeight(),
                    this.getZ() + (this.random.nextDouble() - 0.5) * this.getBbWidth(),
                    0, 0.05, 0
            );
        }

        // たまに泡柱
        if (this.isInWater() && this.random.nextInt(10) == 0) {
            level.addParticle(
                    ParticleTypes.BUBBLE_COLUMN_UP,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    0, 0.08, 0
            );
        }
    }

    private void spawnEntityWarmParticles(LivingEntity entity, ServerLevel serverLevel) {
        // エンティティ周囲の湯気
        for (int i = 0; i < 2; i++) {
            double offsetX = (this.random.nextDouble() - 0.5) * 0.5;
            double offsetZ = (this.random.nextDouble() - 0.5) * 0.5;

            serverLevel.sendParticles(
                    ParticleTypes.CLOUD,
                    entity.getX() + offsetX,
                    entity.getY() + entity.getBbHeight(),
                    entity.getZ() + offsetZ,
                    1,
                    0, 0.1, 0,
                    0.02
            );
        }

        // たまにハッピーパーティクル
        if (this.random.nextInt(3) == 0) {
            serverLevel.sendParticles(
                    ParticleTypes.HAPPY_VILLAGER,
                    entity.getX(),
                    entity.getY() + entity.getBbHeight() / 2,
                    entity.getZ(),
                    1,
                    0.3, 0.3, 0.3,
                    0
            );
        }
    }

    private void spawnExitParticles(LivingEntity entity) {
        if (this.level() instanceof ServerLevel serverLevel) {
            // 温泉から出た時の水しぶき
            serverLevel.sendParticles(
                    ParticleTypes.SPLASH,
                    entity.getX(),
                    entity.getY() + entity.getBbHeight() / 2,
                    entity.getZ(),
                    5,
                    0.3, 0.3, 0.3,
                    0.1
            );
        }
    }

    // ========================================
    // 環境への影響
    // ========================================

    private void meltNearbyIce() {
        if (this.random.nextInt(10) != 0) return;

        BlockPos center = this.blockPosition();
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        // 小範囲で氷を溶かす
        int meltRadius = 3;
        for (int x = -meltRadius; x <= meltRadius; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -meltRadius; z <= meltRadius; z++) {
                    mutablePos.set(center.getX() + x, center.getY() + y, center.getZ() + z);
                    BlockState state = this.level().getBlockState(mutablePos);

                    // 氷を水に変換
                    if (state.is(Blocks.ICE) || state.is(Blocks.PACKED_ICE)) {
                        this.level().setBlock(mutablePos, Blocks.WATER.defaultBlockState(), 3);

                        // 溶解パーティクル
                        if (this.level() instanceof ServerLevel serverLevel) {
                            serverLevel.sendParticles(
                                    ParticleTypes.SPLASH,
                                    mutablePos.getX() + 0.5,
                                    mutablePos.getY() + 0.5,
                                    mutablePos.getZ() + 0.5,
                                    3,
                                    0.3, 0.1, 0.3,
                                    0.1
                            );
                        }
                    }

                    // 雪を溶かす
                    if (state.is(Blocks.SNOW)) {
                        this.level().removeBlock(mutablePos, false);
                    }
                }
            }
        }
    }

    private boolean isNearWater() {
        BlockPos pos = this.blockPosition();
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        for (int x = -4; x <= 4; x++) {
            for (int z = -4; z <= 4; z++) {
                for (int y = -4; y <= 4; y++) {
                    mutablePos.set(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
                    if (this.level().getBlockState(mutablePos).is(Blocks.WATER)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void remove(RemovalReason reason) {
        // エンティティ削除時に全ての効果を除去
        if (!this.level().isClientSide) {
            for (UUID uuid : entitiesInHotSpring.keySet()) {
                LivingEntity entity = findEntityByUUID(uuid);
                if (entity != null) {
                    removeHotSpringEffects(entity);
                }
            }
            entitiesInHotSpring.clear();
        }

        super.remove(reason);
    }

    @Override
    protected void tickDeath() {
        super.tickDeath();

        // 死亡時も効果を除去
        if (!this.level().isClientSide && this.deathTime == 1) {
            for (UUID uuid : entitiesInHotSpring.keySet()) {
                LivingEntity entity = findEntityByUUID(uuid);
                if (entity != null) {
                    removeHotSpringEffects(entity);
                }
            }
            entitiesInHotSpring.clear();
        }
    }

    // ========================================
    // 水辺に留まるカスタムゴール
    // ========================================

    public static class StayNearWaterGoal extends Goal {
        private final PathfinderMob mob;
        private final double speedModifier;
        private final int searchRange;
        private BlockPos targetPos;

        public StayNearWaterGoal(PathfinderMob mob, double speedModifier, int searchRange) {
            this.mob = mob;
            this.speedModifier = speedModifier;
            this.searchRange = searchRange;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            if (isNearWater(this.mob.blockPosition())) {
                return false;
            }
            this.targetPos = findNearestWater();
            return this.targetPos != null;
        }

        @Override
        public boolean canContinueToUse() {
            return this.targetPos != null && !isNearWater(this.mob.blockPosition());
        }

        @Override
        public void start() {
            if (this.targetPos != null) {
                this.mob.getNavigation().moveTo(
                        this.targetPos.getX(),
                        this.targetPos.getY(),
                        this.targetPos.getZ(),
                        this.speedModifier
                );
            }
        }

        private boolean isNearWater(BlockPos pos) {
            BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
            for (int x = -2; x <= 2; x++) {
                for (int z = -2; z <= 2; z++) {
                    for (int y = -1; y <= 1; y++) {
                        mutablePos.set(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
                        if (this.mob.level().getBlockState(mutablePos).is(Blocks.WATER)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        private BlockPos findNearestWater() {
            BlockPos center = this.mob.blockPosition();
            BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

            for (int range = 1; range <= this.searchRange; range++) {
                for (int x = -range; x <= range; x++) {
                    for (int z = -range; z <= range; z++) {
                        mutablePos.set(center.getX() + x, center.getY(), center.getZ() + z);
                        BlockPos surfacePos = this.mob.level().getHeightmapPos(
                                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                                mutablePos
                        );

                        if (this.mob.level().getBlockState(surfacePos).is(Blocks.WATER) ||
                                this.mob.level().getBlockState(surfacePos.below()).is(Blocks.WATER)) {
                            return surfacePos;
                        }
                    }
                }
            }
            return null;
        }
    }

}