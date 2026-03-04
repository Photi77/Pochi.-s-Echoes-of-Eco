package net.pochi.pochimod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.entity.ModEntityTypes;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SootyShearwater extends Animal implements FlyingAnimal {

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    private float flapping = 1.0F;
    private float nextFlap = 1.0F;

    // アイテムドロップ確率
    // 攻撃関連
    private static final double ATTACK_DAMAGE = 3.0;
    private static final double ATTACK_RANGE = 2.0;
    private static final int ATTACK_COOLDOWN = 40; // 2秒
    private int attackCooldownTimer = 0;

    // アイテムドロップ確率
    private static final float BASE_DROP_CHANCE = 0.3F; // 30%
    private static final float ARMOR_DROP_CHANCE = 0.15F; // 15%
    private static final float WEAPON_DROP_CHANCE = 0.25F; // 25%

    // 最後に攻撃したエンティティ
    @Nullable
    private LivingEntity lastAttackedEntity;
    private int lastAttackTime = 0;

    public SootyShearwater(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 10, false);
        this.setPathfindingMalus(PathType.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(PathType.DAMAGE_FIRE, -1.0F);
        this.setPathfindingMalus(PathType.COCOA, -1.0F);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 16.0D)
                .add(Attributes.FLYING_SPEED, 0.5D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE)
                .add(Attributes.FOLLOW_RANGE, 32.0D);
    }

    @Override
    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("AttackCooldown", this.attackCooldownTimer);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.attackCooldownTimer = tag.getInt("AttackCooldown");
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        return target instanceof Monster && super.canAttack(target);
    }

    protected PathNavigation createNavigation(Level pLevel) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, pLevel);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    protected void registerGoals() {
        //this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new Peacock.SparrowWanderGoal(this, 1.2D));
        this.goalSelector.addGoal(4, new BreedGoal(this, 1.0D));
        //this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.of(Items.WHEAT), false));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        //this.goalSelector.addGoal(3, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Monster.class, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AbstractIllager.class, false));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
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

        if (!this.level().isClientSide) {
            if (this.attackCooldownTimer > 0) {
                this.attackCooldownTimer--;
            }
        }
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
        return ModEntityTypes.SOOTY_SHEARWATER.get().create(p_146743_);
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
        return p_27600_.is(Items.CHICKEN);
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

    @Override
    public boolean doHurtTarget(Entity target) {
        if (!(target instanceof LivingEntity livingTarget)) {
            return false;
        }

        // 敵対Mobのみ攻撃
        if (!(livingTarget instanceof Monster)) {
            return false;
        }

        boolean hitSuccessful = super.doHurtTarget(target);

        if (hitSuccessful && !this.level().isClientSide) {
            // アイテム強制ドロップ処理
            performItemTheft(livingTarget);

            // 最後の攻撃記録
            this.lastAttackedEntity = livingTarget;
            this.lastAttackTime = this.tickCount;

            // 攻撃エフェクト
            this.level().broadcastEntityEvent(this, (byte) 50);
            this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP, 1.0F, 1.0F);
        }

        return hitSuccessful;
    }

    private void performItemTheft(LivingEntity target) {
        ServerLevel serverLevel = (ServerLevel) this.level();
        List<ItemStack> stolenItems = new ArrayList<>();

        // 1. 手持ちアイテムを盗む
        stolenItems.addAll(tryStealHandItems(target));

        // 2. 防具を盗む
        stolenItems.addAll(tryStealArmor(target));

        // 3. インベントリアイテムを盗む（Mobがインベントリを持つ場合）
        if (target instanceof Mob mob) {
            stolenItems.addAll(tryStealInventoryItems(mob));
        }

        // アイテムをドロップ
        if (!stolenItems.isEmpty()) {
            for (ItemStack item : stolenItems) {
                dropStolenItem(item, target.position());
            }

            // 盗難成功エフェクト
            spawnTheftSuccessEffects(target);
        }
    }

    private List<ItemStack> tryStealHandItems(LivingEntity target) {
        List<ItemStack> stolen = new ArrayList<>();

        // メインハンド
        if (this.random.nextFloat() < WEAPON_DROP_CHANCE) {
            ItemStack mainHand = target.getMainHandItem();
            if (!mainHand.isEmpty()) {
                stolen.add(mainHand.copy());
                target.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
            }
        }

        // オフハンド
        if (this.random.nextFloat() < WEAPON_DROP_CHANCE) {
            ItemStack offHand = target.getOffhandItem();
            if (!offHand.isEmpty()) {
                stolen.add(offHand.copy());
                target.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
            }
        }

        return stolen;
    }

    private List<ItemStack> tryStealArmor(LivingEntity target) {
        List<ItemStack> stolen = new ArrayList<>();

        // 各防具スロットをチェック
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.isArmor()) {
                if (this.random.nextFloat() < ARMOR_DROP_CHANCE) {
                    ItemStack armor = target.getItemBySlot(slot);
                    if (!armor.isEmpty()) {
                        stolen.add(armor.copy());
                        target.setItemSlot(slot, ItemStack.EMPTY);
                    }
                }
            }
        }

        return stolen;
    }

    private List<ItemStack> tryStealInventoryItems(Mob mob) {
        List<ItemStack> stolen = new ArrayList<>();

        // Mobが持っているアイテムから1-3個ランダムに盗む
        if (mob instanceof AbstractSkeleton || mob instanceof Zombie || mob instanceof Pillager) {
            // 特定のMobは追加アイテムを持っている可能性がある
            int stealAttempts = 1 + this.random.nextInt(3);

            for (int i = 0; i < stealAttempts; i++) {
                if (this.random.nextFloat() < BASE_DROP_CHANCE) {
                    // ランダムなドロップアイテムを生成
                    ItemStack randomDrop = generateRandomMobDrop(mob);
                    if (!randomDrop.isEmpty()) {
                        stolen.add(randomDrop);
                    }
                }
            }
        }

        return stolen;
    }

    private ItemStack generateRandomMobDrop(Mob mob) {
        // Mobのルートテーブルから通常のドロップを取得
        net.minecraft.resources.ResourceKey<LootTable> lootTable = mob.getLootTable();

        if (lootTable != BuiltInLootTables.EMPTY) {
            ServerLevel serverLevel = (ServerLevel) this.level();
            LootTable table = serverLevel.getServer().reloadableRegistries().getLootTable(lootTable);

            LootParams lootParams = new LootParams.Builder(serverLevel)
                    .withParameter(LootContextParams.THIS_ENTITY, mob)
                    .withParameter(LootContextParams.ORIGIN, mob.position())
                    .withParameter(LootContextParams.DAMAGE_SOURCE, this.damageSources().mobAttack(this))
                    .withOptionalParameter(LootContextParams.ATTACKING_ENTITY, this)
                    .withOptionalParameter(LootContextParams.DIRECT_ATTACKING_ENTITY, this)
                    .create(LootContextParamSets.ENTITY);

            List<ItemStack> loot = table.getRandomItems(lootParams);

            if (!loot.isEmpty()) {
                return loot.get(this.random.nextInt(loot.size()));
            }
        }

        return ItemStack.EMPTY;
    }

    private void dropStolenItem(ItemStack item, Vec3 position) {
        // アイテムを少し上空から落とす（グンカンドリが空中で盗んだイメージ）
        Vec3 dropPos = position.add(0, 1.5, 0);

        ItemEntity itemEntity = new ItemEntity(
                this.level(),
                dropPos.x,
                dropPos.y,
                dropPos.z,
                item
        );

        // ランダムな方向に少し投げる
        double angle = this.random.nextDouble() * Math.PI * 2;
        double speed = 0.2;
        itemEntity.setDeltaMovement(
                Math.cos(angle) * speed,
                0.3,
                Math.sin(angle) * speed
        );

        this.level().addFreshEntity(itemEntity);
    }

    private void spawnTheftSuccessEffects(LivingEntity target) {
        ServerLevel serverLevel = (ServerLevel) this.level();

        // キラキラパーティクル
        for (int i = 0; i < 15; i++) {
            double offsetX = (this.random.nextDouble() - 0.5) * 1.0;
            double offsetY = this.random.nextDouble() * 1.5;
            double offsetZ = (this.random.nextDouble() - 0.5) * 1.0;

            serverLevel.sendParticles(
                    ParticleTypes.HAPPY_VILLAGER,
                    target.getX() + offsetX,
                    target.getY() + offsetY,
                    target.getZ() + offsetZ,
                    1,
                    0, 0.1, 0,
                    0.1
            );
        }

        // 金色のパーティクル
        for (int i = 0; i < 10; i++) {
            serverLevel.sendParticles(
                    ParticleTypes.WAX_OFF,
                    target.getX(),
                    target.getY() + 1.0,
                    target.getZ(),
                    1,
                    0.3, 0.3, 0.3,
                    0.1
            );
        }

        // 音
        this.playSound(SoundEvents.PLAYER_LEVELUP, 0.5F, 2.0F);
    }

    // カスタム攻撃ゴール
    public static class FrigateBirdAttackGoal extends MeleeAttackGoal {
        private final SootyShearwater frigateBird;
        private int attackTime = 0;

        public FrigateBirdAttackGoal(SootyShearwater frigateBird, double speedModifier, boolean followingTargetEvenIfNotSeen) {
            super(frigateBird, speedModifier, followingTargetEvenIfNotSeen);
            this.frigateBird = frigateBird;
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.frigateBird.getTarget();
            if (target == null || !target.isAlive()) {
                return false;
            }

            // 敵対Mobのみ攻撃
            if (!(target instanceof Monster)) {
                this.frigateBird.setTarget(null);
                return false;
            }

            return super.canUse();
        }

        @Override
        public void start() {
            super.start();
            this.attackTime = 0;
        }

        @Override
        public void tick() {
            super.tick();

            LivingEntity target = this.frigateBird.getTarget();
            if (target != null) {
                // ターゲットを見る
                this.frigateBird.getLookControl().setLookAt(target, 30.0F, 30.0F);

                double distanceSqr = this.frigateBird.distanceToSqr(
                        target.getX(),
                        target.getY(),
                        target.getZ()
                );

                // 攻撃範囲内かチェック
                if (distanceSqr <= ATTACK_RANGE * ATTACK_RANGE) {
                    this.attackTime++;

                    if (this.attackTime >= 20 && this.frigateBird.attackCooldownTimer <= 0) {
                        this.frigateBird.doHurtTarget(target);
                        this.frigateBird.attackCooldownTimer = ATTACK_COOLDOWN;
                        this.attackTime = 0;
                    }
                } else {
                    this.attackTime = 0;
                }
            }
        }
    }
}
