package net.pochi.pochimod.entity.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.pochi.pochimod.entity.ModEntityTypes;

import javax.annotation.Nullable;
import java.util.List;

public class Rhino extends Animal implements PlayerRideable, Saddleable {
    private static final EntityDataAccessor<Boolean> DATA_SADDLED =
            SynchedEntityData.defineId(Rhino.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_CHARGING =
            SynchedEntityData.defineId(Rhino.class, EntityDataSerializers.BOOLEAN);

    // 突進関連
    private static final double CHARGE_SPEED = 0.6; // 突進速度
    private static final double NORMAL_SPEED = 0.25; // 通常速度
    private static final int CHARGE_DURATION = 40; // 3秒間突進
    private static final int CHARGE_COOLDOWN = 100; // 5秒のクールダウン

    private int chargeTimer = 0;
    public int chargeCooldownTimer = 0;
    private Vec3 chargeDirection = Vec3.ZERO;

    // 破壊可能ブロックの硬度制限
    private static final float MAX_DESTROYABLE_HARDNESS = 3.0F; // 石まで破壊可能

    private int gallopSoundCounter = 0;

    public Rhino(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.3D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        //this.goalSelector.addGoal(3, new TemptGoal(this, 1.2D, Ingredient.of(Items.WHEAT), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Items.WHEAT);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return ModEntityTypes.RHINO.get().create(p_146743_);
    }

    @Override
    protected void dropEquipment() {
        super.dropEquipment();

        if (this.isSaddled()) {
            this.spawnAtLocation(Items.SADDLE);
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.COW_AMBIENT; // サイ用の音に置き換え推奨
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.COW_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.COW_DEATH;
    }

    @Override
    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_SADDLED, false);
        builder.define(DATA_CHARGING, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("Saddled", this.isSaddled());
        tag.putBoolean("Charging", this.isCharging());
        tag.putInt("ChargeTimer", this.chargeTimer);
        tag.putInt("ChargeCooldown", this.chargeCooldownTimer);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setSaddled(tag.getBoolean("Saddled"));
        this.setCharging(tag.getBoolean("Charging"));
        this.chargeTimer = tag.getInt("ChargeTimer");
        this.chargeCooldownTimer = tag.getInt("ChargeCooldown");
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D) // 頑丈
                .add(Attributes.MOVEMENT_SPEED, NORMAL_SPEED)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8D) // ノックバック耐性
                .add(Attributes.ATTACK_DAMAGE, 8.0D)
                .add(Attributes.ARMOR, 4.0D); // 防御力
    }

    public boolean isCharging() {
        return this.entityData.get(DATA_CHARGING);
    }

    public void setCharging(boolean charging) {
        this.entityData.set(DATA_CHARGING, charging);
    }

    @Override
    public boolean isSaddleable() {
        return this.isAlive() && !this.isBaby();
    }

    @Override
    public void equipSaddle(net.minecraft.world.item.ItemStack itemStack, @Nullable SoundSource soundSource) {
        this.setSaddled(true);
        if (soundSource != null) {
            this.level().playSound(null, this, SoundEvents.HORSE_SADDLE, soundSource, 0.5F, 1.0F);
        }
    }

    @Override
    public boolean isSaddled() {
        return this.entityData.get(DATA_SADDLED);
    }

    public void setSaddled(boolean saddled) {
        this.entityData.set(DATA_SADDLED, saddled);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide) {
            // クールダウンタイマー
            if (this.chargeCooldownTimer > 0) {
                this.chargeCooldownTimer--;
            }

            // 突進中の処理
            if (this.isCharging()) {
                handleCharge();
            }
        }

        // クライアント側のエフェクト
        if (this.level().isClientSide && this.isCharging()) {
            spawnChargeParticles();
        }
    }

    public void startCharge() {
        if (this.chargeCooldownTimer > 0 || this.isCharging()) {
            return;
        }

        // 現在の向きを突進方向として保存
        this.chargeDirection = this.getLookAngle().normalize();
        this.chargeTimer = CHARGE_DURATION;
        this.setCharging(true);

        // 突進開始音
        this.playSound(SoundEvents.RAVAGER_ROAR, 1.0F, 1.0F);

        // エフェクト
        this.level().broadcastEntityEvent(this, (byte) 51);
    }

    private void handleCharge() {
        this.chargeTimer--;

        if (this.chargeTimer <= 0) {
            stopCharge();
            return;
        }

        // 突進移動
        //Vec3 motion = this.chargeDirection.scale(CHARGE_SPEED);
        //this.setDeltaMovement(this.getLookAngle().x * 5, this.getLookAngle().y * 5, motion.z);

        // 前方のブロックを破壊
        destroyBlocksInPath();

        // 衝突したエンティティにダメージ
        damageEntitiesInPath();

        // 突進音
        if (this.chargeTimer % 5 == 0) {
            this.playSound(SoundEvents.RAVAGER_STEP, 0.5F, 0.8F);
        }
    }

    private void stopCharge() {
        this.setCharging(false);
        this.chargeTimer = 0;
        this.chargeCooldownTimer = CHARGE_COOLDOWN;
        //this.chargeDirection = Vec3.ZERO;

        // 停止音
        this.playSound(SoundEvents.RAVAGER_STUNNED, 1.0F, 1.0F);
    }

    private void destroyBlocksInPath() {
        // サイの頭部分の当たり判定
        Vec3 headPos = this.position().add(
                this.chargeDirection.x * 1.5,
                this.getBbHeight() * 0.5,
                this.chargeDirection.z * 1.5
        );

        BlockPos centerPos = BlockPos.containing(headPos);

        // 3x3x3の範囲でブロックを破壊
        for (int x = -1; x <= 1; x++) {
            for (int y = 0; y <= 2; y++) {
                for (int z = -1; z <= 1; z++) {
                    BlockPos targetPos = centerPos.offset(x, y, z);
                    tryDestroyBlock(targetPos);
                }
            }
        }
    }

    private void tryDestroyBlock(BlockPos pos) {
        BlockState state = this.level().getBlockState(pos);

        if (state.isAir()) {
            return;
        }

        // 破壊不可能なブロックをチェック
        if (!canDestroyBlock(state, pos)) {
            return;
        }

        // ブロックを破壊
        ServerLevel serverLevel = (ServerLevel) this.level();

        // ドロップを生成
        List<ItemStack> drops = getBlockDrops(state, serverLevel, pos);

        // ブロックを破壊
        serverLevel.destroyBlock(pos, false);

        // ドロップをスポーン
        for (ItemStack drop : drops) {
            Block.popResource(serverLevel, pos, drop);
        }

        // 破壊エフェクト
        serverLevel.levelEvent(2001, pos, Block.getId(state));

        // 破壊音
        SoundType soundType = state.getSoundType();
        this.playSound(soundType.getBreakSound(), 0.8F, 0.8F);
    }

    private boolean canDestroyBlock(BlockState state, BlockPos pos) {
        Block block = state.getBlock();

        // 破壊不可能なブロック
        if (state.is(BlockTags.WITHER_IMMUNE)) {
            return false; // 岩盤、バリア等
        }

        if (block instanceof LiquidBlock) {
            return false; // 液体
        }

        // 硬度チェック
        float hardness = state.getDestroySpeed(this.level(), pos);

        if (hardness < 0) {
            return false; // 破壊不可能
        }

        if (hardness > MAX_DESTROYABLE_HARDNESS) {
            return false; // 硬すぎる
        }

        // 特殊ブロックの保護
        if (block instanceof SpawnerBlock ||
                block instanceof EnderChestBlock ||
                block instanceof CommandBlock) {
            return false;
        }

        // ブロックエンティティがある場合は保護（チェスト等）
        if (this.level().getBlockEntity(pos) != null) {
            return false;
        }

        return true;
    }

    private List<ItemStack> getBlockDrops(BlockState state, ServerLevel level, BlockPos pos) {
        // 素手で破壊した場合のドロップを取得
        LootParams.Builder builder = new LootParams.Builder(level)
                .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                .withParameter(LootContextParams.TOOL, ItemStack.EMPTY)
                .withOptionalParameter(LootContextParams.THIS_ENTITY, this)
                .withOptionalParameter(LootContextParams.BLOCK_ENTITY, level.getBlockEntity(pos));

        return state.getDrops(builder);
    }

    private void damageEntitiesInPath() {
        // 前方の当たり判定
        AABB collisionBox = this.getBoundingBox().inflate(1.0, 0.5, 1.0)
                .move(this.chargeDirection.scale(1.5));

        List<LivingEntity> entities = this.level().getEntitiesOfClass(
                LivingEntity.class,
                collisionBox,
                entity -> entity != this && entity != this.getControllingPassenger()
        );

        for (LivingEntity entity : entities) {
            // ダメージを与える
            entity.hurt(this.damageSources().mobAttack(this),
                    (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE));

            // 強力なノックバック
            Vec3 knockback = this.chargeDirection.scale(2.0);
            entity.push(knockback.x, 0.5, knockback.z);

            // ヒット音
            this.playSound(SoundEvents.PLAYER_ATTACK_STRONG, 1.0F, 1.0F);
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        // サドルを装着
        if (itemstack.is(Items.SADDLE) && this.isSaddleable() && !this.isSaddled()) {
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            this.equipSaddle(new ItemStack(Items.SADDLE), SoundSource.NEUTRAL);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        // 騎乗（突進中は乗れない）
        if (this.isSaddled() && !this.isBaby() && !this.isCharging()) {
            if (!this.level().isClientSide) {
                player.startRiding(this);
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        return super.mobInteract(player, hand);
    }

    @Override
    @Nullable
    public LivingEntity getControllingPassenger() {
        Entity entity = this.getFirstPassenger();
        if (entity instanceof Player) {
            return (Player) entity;
        }
        return null;
    }

    public boolean canBeControlledByRider() {
        return this.getControllingPassenger() instanceof Player;
    }

    @Override
    protected net.minecraft.world.phys.Vec3 getPassengerAttachmentPoint(net.minecraft.world.entity.Entity p_294756_, EntityDimensions p_295396_, float p_296362_) {
        return super.getPassengerAttachmentPoint(p_294756_, p_295396_, p_296362_).add(0.0, this.getBbHeight() * 0.8D - (this.getBbHeight() / 2.0), 0.0);
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isAlive()) {
            LivingEntity rider = this.getControllingPassenger();

            if (this.isVehicle() && rider instanceof Player player && this.canBeControlledByRider()) {
                LivingEntity livingentity = this.getControllingPassenger();
                this.setYRot(livingentity.getYRot());
                this.yRotO = this.getYRot();
                this.setXRot(livingentity.getXRot() * 0.5F);
                this.setRot(this.getYRot(), this.getXRot());
                this.yBodyRot = this.getYRot();
                this.yHeadRot = this.yBodyRot;
                float f = livingentity.xxa * 0.5F;
                float f1 = livingentity.zza;

                // Inside this if statement, we are on the client!
                if (this.isControlledByLocalInstance()) {
                    float newSpeed = (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED);
                    // increasing speed by 100% if the spring key is held down (number for testing purposes)
                    if (Minecraft.getInstance().options.keySprint.isDown()) {
                        newSpeed *= 2f;
                    }

                    this.setSpeed(newSpeed);
                    super.travel(new Vec3(f, travelVector.y, f1));
                }

            } else {
                super.travel(Vec3.ZERO);
            }

            this.calculateEntityAnimation(false);
            this.tryCheckInsideBlocks();
        } else {
            super.travel(travelVector);
        }
    }



    @Override
    public void handleEntityEvent(byte id) {
        if (id == 51) {
            // 突進開始エフェクト
            spawnChargeStartParticles();
        } else {
            super.handleEntityEvent(id);
        }
    }

    @OnlyIn(Dist.CLIENT)
    private void spawnChargeStartParticles() {
        // 爆発的なエフェクト
        for (int i = 0; i < 20; i++) {
            double angle = (i / 20.0) * Math.PI * 2;
            double radius = 1.0;

            double vx = Math.cos(angle) * radius;
            double vz = Math.sin(angle) * radius;

            this.level().addParticle(
                    ParticleTypes.CLOUD,
                    this.getX(),
                    this.getY() + 0.5,
                    this.getZ(),
                    vx, 0, vz
            );
        }
    }

    @OnlyIn(Dist.CLIENT)
    private void spawnChargeParticles() {
        if (this.random.nextInt(2) != 0) {
            return;
        }

        // 足元の土煙
        for (int i = 0; i < 3; i++) {
            double offsetX = (this.random.nextDouble() - 0.5) * this.getBbWidth();
            double offsetZ = (this.random.nextDouble() - 0.5) * this.getBbWidth();

            this.level().addParticle(
                    ParticleTypes.POOF,
                    this.getX() + offsetX,
                    this.getY() + 0.1,
                    this.getZ() + offsetZ,
                    -this.getDeltaMovement().x * 0.5,
                    0.05,
                    -this.getDeltaMovement().z * 0.5
            );
        }

        // 鼻息のような煙
        Vec3 headPos = this.position().add(
                this.getLookAngle().x * 1.5,
                this.getBbHeight() * 0.5,
                this.getLookAngle().z * 1.5
        );

        this.level().addParticle(
                ParticleTypes.LARGE_SMOKE,
                headPos.x,
                headPos.y,
                headPos.z,
                this.getLookAngle().x * 0.3,
                0,
                this.getLookAngle().z * 0.3
        );
    }
}
