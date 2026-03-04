package net.pochi.pochimod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.pochi.pochimod.entity.ModEntityTypes;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Squirrel extends Animal {
    // インベントリ
    private ItemStack saplingStack = ItemStack.EMPTY;

    // 苗木回収関連
    private static final double ITEM_PICKUP_RANGE = 8.0;

    // 植樹関連
    private static final int PLANTING_INTERVAL = 200; // 10秒ごと
    private int plantingTimer = 0;
    private static final int MIN_TREE_DISTANCE = 5; // 木との最小距離

    // 植樹アニメーション
    @Nullable
    private BlockPos currentPlantingPos = null;
    private int plantingAnimationTimer = 0;
    private static final int PLANTING_ANIMATION_DURATION = 40; // 2秒

    public Squirrel(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(2, new PickupSaplingGoal(this, 1.2D));
        this.goalSelector.addGoal(3, new PlantSaplingGoal(this, 1.0D)); // シンプル化
        this.goalSelector.addGoal(4, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return ModEntityTypes.SQUIRREL.get().create(p_146743_);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide) {
            this.plantingTimer++;

            // 植樹アニメーション中
            if (this.currentPlantingPos != null) {
                this.plantingAnimationTimer++;

                if (this.plantingAnimationTimer >= PLANTING_ANIMATION_DURATION) {
                    this.currentPlantingPos = null;
                    this.plantingAnimationTimer = 0;
                }
            }
        }

        // クライアント側のパーティクル
        if (this.level().isClientSide && this.currentPlantingPos != null) {
            spawnPlantingParticles();
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.JUMP_STRENGTH, 0.8D);
    }

    @Override
    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        if (!this.saplingStack.isEmpty()) {
            tag.put("SaplingStack", this.saplingStack.save(this.registryAccess()));
        }

        tag.putInt("PlantingTimer", this.plantingTimer);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        if (tag.contains("SaplingStack")) {
            this.saplingStack = ItemStack.parseOptional(this.registryAccess(), tag.getCompound("SaplingStack"));
        }

        this.plantingTimer = tag.getInt("PlantingTimer");
    }

    public ItemStack getSaplingStack() {
        return this.saplingStack;
    }

    public void setSaplingStack(ItemStack stack) {
        this.saplingStack = stack;
    }

    public boolean hasSapling() {
        return !this.saplingStack.isEmpty();
    }

    public int getSaplingCount() {
        return this.saplingStack.getCount();
    }

    // 苗木を拾うゴール（変更なし）
    public class PickupSaplingGoal extends Goal {
        private final Squirrel squirrel;
        private final double speedModifier;
        private ItemEntity targetItem;
        private int searchCooldown = 0;
        private static final int SEARCH_INTERVAL = 20;

        public PickupSaplingGoal(Squirrel squirrel, double speedModifier) {
            this.squirrel = squirrel;
            this.speedModifier = speedModifier;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            ItemStack currentStack = this.squirrel.getSaplingStack();

            if (!currentStack.isEmpty() && currentStack.getCount() >= currentStack.getMaxStackSize()) {
                return false;
            }

            if (this.searchCooldown > 0) {
                this.searchCooldown--;
                return false;
            }

            this.searchCooldown = SEARCH_INTERVAL;
            this.targetItem = findNearestSaplingItem();
            return this.targetItem != null;
        }

        @Override
        public boolean canContinueToUse() {
            if (this.targetItem == null || !this.targetItem.isAlive()) {
                return false;
            }

            ItemStack currentStack = this.squirrel.getSaplingStack();
            if (!currentStack.isEmpty() && currentStack.getCount() >= currentStack.getMaxStackSize()) {
                return false;
            }

            return this.squirrel.distanceToSqr(this.targetItem) > 2.25;
        }

        @Override
        public void start() {
            if (this.targetItem != null) {
                this.squirrel.getNavigation().moveTo(this.targetItem, this.speedModifier);
            }
        }

        @Override
        public void stop() {
            this.targetItem = null;
            this.squirrel.getNavigation().stop();
        }

        @Override
        public void tick() {
            if (this.targetItem != null && this.targetItem.isAlive()) {
                this.squirrel.getLookControl().setLookAt(this.targetItem, 10.0F, this.squirrel.getMaxHeadXRot());

                if (this.squirrel.distanceToSqr(this.targetItem) <= 2.25) {
                    pickupItem();
                }
            }
        }

        private ItemEntity findNearestSaplingItem() {
            List<ItemEntity> items = this.squirrel.level().getEntitiesOfClass(
                    ItemEntity.class,
                    this.squirrel.getBoundingBox().inflate(ITEM_PICKUP_RANGE),
                    item -> item.isAlive() && isSaplingItem(item.getItem())
            );

            ItemEntity nearest = null;
            double nearestDistSq = Double.MAX_VALUE;

            for (ItemEntity item : items) {
                double distSq = this.squirrel.distanceToSqr(item);
                if (distSq < nearestDistSq) {
                    nearestDistSq = distSq;
                    nearest = item;
                }
            }

            return nearest;
        }

        private boolean isSaplingItem(ItemStack stack) {
            return stack.getItem() instanceof BlockItem blockItem
                    && blockItem.getBlock() instanceof SaplingBlock;
        }

        private void pickupItem() {
            if (this.targetItem == null || !this.targetItem.isAlive()) {
                return;
            }

            ItemStack itemStack = this.targetItem.getItem();
            ItemStack currentStack = this.squirrel.getSaplingStack();

            if (currentStack.isEmpty()) {
                this.squirrel.setSaplingStack(itemStack.copy());
                this.targetItem.discard();
            } else if (ItemStack.isSameItemSameComponents(currentStack, itemStack)) {
                int addAmount = Math.min(
                        itemStack.getCount(),
                        currentStack.getMaxStackSize() - currentStack.getCount()
                );

                if (addAmount > 0) {
                    currentStack.grow(addAmount);
                    itemStack.shrink(addAmount);

                    if (itemStack.isEmpty()) {
                        this.targetItem.discard();
                    }
                }
            }

            this.squirrel.level().broadcastEntityEvent(this.squirrel, (byte) 45);
        }
    }

    // 新しいシンプルな植樹ゴール
    public class PlantSaplingGoal extends Goal {
        private final Squirrel squirrel;
        private final double speedModifier;
        private BlockPos targetPlantPos;
        private int plantingTimer = 0;
        private static final int PLANTING_TIME = 40; // 2秒
        private static final int SEARCH_RADIUS = 12; // 探索範囲

        public PlantSaplingGoal(Squirrel squirrel, double speedModifier) {
            this.squirrel = squirrel;
            this.speedModifier = speedModifier;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            // 苗木を持っているか
            if (!this.squirrel.hasSapling()) {
                return false;
            }

            // 植樹インターバルチェック
            if (this.squirrel.plantingTimer < PLANTING_INTERVAL) {
                return false;
            }

            // 植樹可能な場所を探す
            this.targetPlantPos = findPlantingSpot();
            return this.targetPlantPos != null;
        }

        @Override
        public boolean canContinueToUse() {
            return this.targetPlantPos != null
                    && this.squirrel.hasSapling()
                    && this.plantingTimer < PLANTING_TIME;
        }

        @Override
        public void start() {
            this.plantingTimer = 0;
            if (this.targetPlantPos != null) {
                this.squirrel.getNavigation().moveTo(
                        this.targetPlantPos.getX() + 0.5,
                        this.targetPlantPos.getY(),
                        this.targetPlantPos.getZ() + 0.5,
                        this.speedModifier
                );
            }
        }

        @Override
        public void stop() {
            this.targetPlantPos = null;
            this.plantingTimer = 0;
            this.squirrel.getNavigation().stop();
        }

        @Override
        public void tick() {
            if (this.targetPlantPos == null) {
                return;
            }

            this.squirrel.getLookControl().setLookAt(
                    this.targetPlantPos.getX() + 0.5,
                    this.targetPlantPos.getY() + 0.5,
                    this.targetPlantPos.getZ() + 0.5
            );

            // 目標地点の近くに到達したら植樹開始
            if (this.squirrel.blockPosition().closerThan(this.targetPlantPos, 3.0)) {
                this.plantingTimer++;

                // パーティクルを定期的に表示
                if (this.plantingTimer % 10 == 0) {
                    this.squirrel.level().broadcastEntityEvent(this.squirrel, (byte) 46);
                }

                // 植樹実行
                if (this.plantingTimer >= PLANTING_TIME) {
                    plantSapling();
                }
            }
        }

        private BlockPos findPlantingSpot() {
            BlockPos center = this.squirrel.blockPosition();
            Level level = this.squirrel.level();
            List<BlockPos> candidates = new ArrayList<>();

            // 周囲をランダムサーチ
            for (int attempt = 0; attempt < 20; attempt++) {
                int offsetX = this.squirrel.getRandom().nextInt(SEARCH_RADIUS * 2) - SEARCH_RADIUS;
                int offsetZ = this.squirrel.getRandom().nextInt(SEARCH_RADIUS * 2) - SEARCH_RADIUS;

                BlockPos searchPos = center.offset(offsetX, 0, offsetZ);

                // Y座標を地面に合わせる
                BlockPos groundPos = findGround(searchPos, level);

                if (groundPos != null && canPlantAt(groundPos, level)) {
                    candidates.add(groundPos);
                }
            }

            if (candidates.isEmpty()) {
                return null;
            }

            // ランダムに1つ選択
            return candidates.get(this.squirrel.getRandom().nextInt(candidates.size()));
        }

        private BlockPos findGround(BlockPos startPos, Level level) {
            // 上下3ブロック範囲で地面を探す
            for (int y = 3; y >= -3; y--) {
                BlockPos checkPos = startPos.offset(0, y, 0);
                BlockPos groundPos = checkPos.below();

                if (level.getBlockState(checkPos).isAir()
                        && !level.getBlockState(groundPos).isAir()) {
                    return checkPos;
                }
            }
            return null;
        }

        private boolean canPlantAt(BlockPos pos, Level level) {
            // 地面のブロックをチェック
            BlockPos groundPos = pos.below();
            BlockState groundState = level.getBlockState(groundPos);



            // 土系ブロックまたは草ブロックか
            if (!groundState.is(BlockTags.DIRT) && !groundState.is(Blocks.GRASS_BLOCK)) {
                return false;
            }

            //// 設置位置が空気か
            //if (!level.getBlockState(pos).isAir() || !level.getBlockState(pos).is(Blocks.GLASS)) {
            //    return false;
            //}
//
            //// 上に十分な空間があるか
            //for (int y = 1; y <= 3; y++) {
            //    if (!level.getBlockState(pos.above(y)).isAir()) {
            //        return false;
            //    }
            //}

            // 近くに木がないかチェック（簡易版）
            if (hasNearbyTree(pos, level)) {
                return false;
            }

            return true;
        }

        private boolean hasNearbyTree(BlockPos center, Level level) {
            // 周囲5ブロック以内に木や苗木がないかチェック
            BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

            for (int x = -MIN_TREE_DISTANCE; x <= MIN_TREE_DISTANCE; x++) {
                for (int z = -MIN_TREE_DISTANCE; z <= MIN_TREE_DISTANCE; z++) {
                    for (int y = -2; y <= 5; y++) {
                        mutablePos.set(center.getX() + x, center.getY() + y, center.getZ() + z);
                        BlockState state = level.getBlockState(mutablePos);

                        if (state.is(BlockTags.LOGS)
                                || state.is(BlockTags.LEAVES)
                                || state.getBlock() instanceof SaplingBlock) {
                            return true;
                        }
                    }
                }
            }

            return false;
        }

        private void plantSapling() {
            if (this.targetPlantPos == null) {
                return;
            }

            ItemStack saplingStack = this.squirrel.getSaplingStack();
            if (saplingStack.isEmpty() || !(saplingStack.getItem() instanceof BlockItem blockItem)) {
                return;
            }

            Block saplingBlock = blockItem.getBlock();
            if (!(saplingBlock instanceof SaplingBlock)) {
                return;
            }

            Level level = this.squirrel.level();

            // 苗木を設置
            level.setBlock(this.targetPlantPos, saplingBlock.defaultBlockState(), 3);

            // パーティクルエフェクト
            level.levelEvent(2001, this.targetPlantPos, Block.getId(saplingBlock.defaultBlockState()));

            // 苗木を消費
            saplingStack.shrink(1);
            if (saplingStack.isEmpty()) {
                this.squirrel.setSaplingStack(ItemStack.EMPTY);
            }

            // タイマーリセット
            this.squirrel.plantingTimer = 0;

            // アニメーション開始
            this.squirrel.currentPlantingPos = this.targetPlantPos;
            this.squirrel.plantingAnimationTimer = 0;
        }
    }

    @OnlyIn(Dist.CLIENT)
    private void spawnPlantingParticles() {
        if (this.currentPlantingPos == null) {
            return;
        }

        BlockPos pos = this.currentPlantingPos;

        if (this.plantingAnimationTimer % 5 == 0) {
            for (int i = 0; i < 3; i++) {
                double offsetX = (this.random.nextDouble() - 0.5) * 0.8;
                double offsetZ = (this.random.nextDouble() - 0.5) * 0.8;

                this.level().addParticle(
                        ParticleTypes.HAPPY_VILLAGER,
                        pos.getX() + 0.5 + offsetX,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5 + offsetZ,
                        0, 0.1, 0
                );
            }
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        // 苗木を与える
        if (itemstack.getItem() instanceof BlockItem blockItem
                && blockItem.getBlock() instanceof SaplingBlock) {

            ItemStack currentStack = this.getSaplingStack();

            if (currentStack.isEmpty()) {
                ItemStack toGive = itemstack.split(Math.min(itemstack.getCount(), itemstack.getMaxStackSize()));
                this.setSaplingStack(toGive);
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            } else if (ItemStack.isSameItemSameComponents(currentStack, itemstack)) {
                int addAmount = Math.min(
                        itemstack.getCount(),
                        currentStack.getMaxStackSize() - currentStack.getCount()
                );

                if (addAmount > 0) {
                    currentStack.grow(addAmount);
                    itemstack.shrink(addAmount);
                    return InteractionResult.sidedSuccess(this.level().isClientSide);
                }
            }
        }

        // Shift+右クリックで苗木を回収
        if (player.isShiftKeyDown() && this.hasSapling()) {
            if (!this.level().isClientSide) {
                ItemStack saplingStack = this.getSaplingStack();
                player.addItem(saplingStack.copy());
                this.setSaplingStack(ItemStack.EMPTY);
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        // 種で餌付け
        if (itemstack.is(Items.BEETROOT_SEEDS) || itemstack.is(Items.WHEAT_SEEDS)) {
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            if (!this.level().isClientSide) {
                this.heal(2.0F);
                this.level().broadcastEntityEvent(this, (byte) 7);
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        return super.mobInteract(player, hand);
    }

    @Override
    protected void dropCustomDeathLoot(net.minecraft.server.level.ServerLevel serverLevel, DamageSource source, boolean recentlyHit) {
        super.dropCustomDeathLoot(serverLevel, source, recentlyHit);

        if (!this.saplingStack.isEmpty()) {
            this.spawnAtLocation(this.saplingStack);
        }
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Items.BEETROOT_SEEDS) || stack.is(Items.WHEAT_SEEDS);
    }

}