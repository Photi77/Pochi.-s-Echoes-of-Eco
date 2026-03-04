package net.pochi.pochimod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Albatross extends Animal implements FlyingAnimal {
    private static final int INVENTORY_SIZE = 9; // 3x3のインベントリ
    private SimpleContainer inventory;

    // 配達ミッション関連
    private UUID ownerUUID;
    private BlockPos targetRespawnPoint;
    private BlockPos playerLastPos;
    private DeliveryState deliveryState = DeliveryState.IDLE;
    private int deliveryTimer = 0;

    // チャンクロード対策
    private boolean isOnDeliveryMission = false;
    private static final int MAX_DELIVERY_TIME = 12000; // 10分でタイムアウト

    @Override
    public boolean isFlying() {
        return false;
    }

    public enum DeliveryState {
        IDLE,           // 待機中
        TO_RESPAWN,     // リスポーン地点へ移動中
        STORING_ITEMS,  // チェストにアイテム格納中
        RETURNING       // プレイヤーの元へ帰還中
    }

    public Albatross(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 10, false);
        this.setPathfindingMalus(PathType.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(PathType.DAMAGE_FIRE, -1.0F);
        this.setPathfindingMalus(PathType.COCOA, -1.0F);
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


    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    @Override
    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        // インベントリの保存
        ListTag itemsList = new ListTag();
        for (int i = 0; i < this.inventory.getContainerSize(); i++) {
            ItemStack stack = this.inventory.getItem(i);
            if (!stack.isEmpty()) {
                net.minecraft.nbt.Tag savedTag = stack.save(this.registryAccess());
                if (savedTag instanceof CompoundTag savedCompound) {
                    savedCompound.putByte("Slot", (byte) i);
                    itemsList.add(savedCompound);
                }
            }
        }
        tag.put("Inventory", itemsList);

        // 配達ミッション状態の保存
        tag.putBoolean("OnDeliveryMission", this.isOnDeliveryMission);
        tag.putString("DeliveryState", this.deliveryState.name());
        tag.putInt("DeliveryTimer", this.deliveryTimer);

        if (this.targetRespawnPoint != null) {
            tag.putLong("TargetRespawnPoint", this.targetRespawnPoint.asLong());
        }
        if (this.playerLastPos != null) {
            tag.putLong("PlayerLastPos", this.playerLastPos.asLong());
        }
        if (this.ownerUUID != null) {
            tag.putUUID("OwnerUUID", this.ownerUUID);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        // インベントリの読み込み
        ListTag itemsList = tag.getList("Inventory", 10);
        for (int i = 0; i < itemsList.size(); i++) {
            CompoundTag itemTag = itemsList.getCompound(i);
            int slot = itemTag.getByte("Slot") & 255;
            if (slot < this.inventory.getContainerSize()) {
                this.inventory.setItem(slot, ItemStack.parseOptional(this.registryAccess(), itemTag));
            }
        }

        // 配達ミッション状態の復元
        this.isOnDeliveryMission = tag.getBoolean("OnDeliveryMission");
        //this.deliveryState = DeliveryState.valueOf(tag.getString("DeliveryState"));
        this.deliveryTimer = tag.getInt("DeliveryTimer");

        if (tag.contains("TargetRespawnPoint")) {
            this.targetRespawnPoint = BlockPos.of(tag.getLong("TargetRespawnPoint"));
        }
        if (tag.contains("PlayerLastPos")) {
            this.playerLastPos = BlockPos.of(tag.getLong("PlayerLastPos"));
        }
        if (tag.contains("OwnerUUID")) {
            this.ownerUUID = tag.getUUID("OwnerUUID");
        }
    }

    //@Override
    //public void containerChanged(Container container) {
    //    // インベントリ変更時の処理
    //}

    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        //if (this.isTame() && this.isOwnedBy(player)) {
            if (player.isShiftKeyDown()) {
                // Shift+右クリックで配達ミッション開始
                if (!this.level().isClientSide && this.deliveryState == DeliveryState.IDLE) {
                    if (hasItemsInInventory()) {
                        startDeliveryMission(player);
                        player.displayClientMessage(
                                Component.translatable("entity.albatross.delivery_start"),
                                true
                        );
                        return InteractionResult.SUCCESS;
                    } else {
                        player.displayClientMessage(
                                Component.translatable("entity.albatross.no_items"),
                                true
                        );
                        return InteractionResult.FAIL;
                    }
                }
            } else {
                // 通常の右クリックでインベントリを開く
                if (!this.level().isClientSide) {
                    openInventory(player);
                }
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
        //} else if (itemstack.is(Items.COD) && !this.isTame()) {
        //    // 餌付け処理
        //    if (!player.getAbilities().instabuild) {
        //        itemstack.shrink(1);
        //    }
//
        //    if (!this.level().isClientSide) {
        //        if (this.random.nextInt(3) == 0) {
        //            this.tame(player);
        //            this.navigation.stop();
        //            this.setTarget(null);
        //            this.level().broadcastEntityEvent(this, (byte) 7);
        //        } else {
        //            this.level().broadcastEntityEvent(this, (byte) 6);
        //        }
        //    }
//
        //    return InteractionResult.sidedSuccess(this.level().isClientSide);
        //}

        return super.mobInteract(player, hand);
    }

    private void openInventory(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            //serverPlayer.openMenu(
            //        new SimpleMenuProvider(
            //                (containerId, playerInv, p) ->
            //                        new AlbatrossInventoryMenu(containerId, playerInv, this.inventory, this),
            //                this.getDisplayName()
            //        ),
            //        buf -> buf.writeInt(this.getId())
            //);
        }
    }

    private boolean hasItemsInInventory() {
        for (int i = 0; i < this.inventory.getContainerSize(); i++) {
            if (!this.inventory.getItem(i).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private void startDeliveryMission(Player player) {
        this.ownerUUID = player.getUUID();
        this.playerLastPos = player.blockPosition();

        // プレイヤーのリスポーン地点を取得
        ServerPlayer serverPlayer = (ServerPlayer) player;
        this.targetRespawnPoint = serverPlayer.getRespawnPosition();

        if (this.targetRespawnPoint == null) {
            // リスポーン地点が未設定の場合はワールドスポーン
            this.targetRespawnPoint = serverPlayer.serverLevel().getSharedSpawnPos();
        }

        this.deliveryState = DeliveryState.TO_RESPAWN;
        this.isOnDeliveryMission = true;
        this.deliveryTimer = 0;

        // チャンクをロードし続けるために永続化
        this.setPersistenceRequired();

        // 飛行速度を上げる
        this.getAttribute(Attributes.FLYING_SPEED).setBaseValue(0.6);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide && this.isOnDeliveryMission) {
            handleDeliveryMission();
        }
    }

    private void handleDeliveryMission() {
        this.deliveryTimer++;

        // タイムアウト処理
        if (this.deliveryTimer > MAX_DELIVERY_TIME) {
            cancelDeliveryMission();
            return;
        }

        // 目的地のチャンクを強制ロード
        switch (this.deliveryState) {
            case TO_RESPAWN:
                handleToRespawnState();
                break;
            case STORING_ITEMS:
                handleStoringItemsState();
                break;
            case RETURNING:
                handleReturningState();
                break;
        }
    }

    private void handleToRespawnState() {
        if (this.targetRespawnPoint == null) {
            cancelDeliveryMission();
            return;
        }

        // チャンク強制ロード
        ServerLevel serverLevel = (ServerLevel) this.level();
        ChunkPos chunkPos = new ChunkPos(this.targetRespawnPoint);
        serverLevel.setChunkForced(chunkPos.x, chunkPos.z, true);

        // 目的地へのナビゲーション
        if (this.navigation.isDone()) {
            this.navigation.moveTo(
                    this.targetRespawnPoint.getX(),
                    this.targetRespawnPoint.getY(),
                    this.targetRespawnPoint.getZ(),
                    1.2D
            );
        }

        // 目的地到達判定
        if (this.blockPosition().closerThan(this.targetRespawnPoint, 3.0)) {
            this.deliveryState = DeliveryState.STORING_ITEMS;
            this.deliveryTimer = 0;
        }
    }

    private void handleStoringItemsState() {
        // 近くのチェストを探索
        BlockPos nearestChest = findNearestChest(this.targetRespawnPoint, 16);

        if (nearestChest != null) {
            // チェストにアイテムを格納
            if (storeItemsInChest(nearestChest)) {
                // 格納成功、帰還開始
                this.deliveryState = DeliveryState.RETURNING;
                this.deliveryTimer = 0;

                // リスポーン地点のチャンク強制ロード解除
                ServerLevel serverLevel = (ServerLevel) this.level();
                ChunkPos chunkPos = new ChunkPos(this.targetRespawnPoint);
                serverLevel.setChunkForced(chunkPos.x, chunkPos.z, false);
            }
        } else {
            // チェストが見つからない場合、地面にドロップ
            dropAllItems();
            this.deliveryState = DeliveryState.RETURNING;
            this.deliveryTimer = 0;

            ServerLevel serverLevel = (ServerLevel) this.level();
            ChunkPos chunkPos = new ChunkPos(this.targetRespawnPoint);
            serverLevel.setChunkForced(chunkPos.x, chunkPos.z, false);
        }
    }

    private void handleReturningState() {
        Player owner = this.level().getPlayerByUUID(this.ownerUUID);
        BlockPos returnPos;

        if (owner != null && owner.isAlive()) {
            // プレイヤーが存在する場合は現在位置へ
            returnPos = owner.blockPosition();
        } else {
            // プレイヤーが不在の場合は最後の位置へ
            returnPos = this.playerLastPos;
        }

        if (returnPos == null) {
            cancelDeliveryMission();
            return;
        }

        // プレイヤー位置のチャンク強制ロード
        ServerLevel serverLevel = (ServerLevel) this.level();
        ChunkPos chunkPos = new ChunkPos(returnPos);
        serverLevel.setChunkForced(chunkPos.x, chunkPos.z, true);

        // プレイヤーの元へナビゲーション
        if (this.navigation.isDone()) {
            this.navigation.moveTo(returnPos.getX(), returnPos.getY(), returnPos.getZ(), 1.2D);
        }

        // 帰還完了判定
        if (this.blockPosition().closerThan(returnPos, 5.0)) {
            completeDeliveryMission();
        }
    }

    private BlockPos findNearestChest(BlockPos center, int radius) {
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        double closestDistSq = Double.MAX_VALUE;
        BlockPos closestChest = null;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    mutablePos.set(center.getX() + x, center.getY() + y, center.getZ() + z);

                    BlockEntity blockEntity = this.level().getBlockEntity(mutablePos);
                    if (blockEntity instanceof ChestBlockEntity ||
                            blockEntity instanceof BarrelBlockEntity ||
                            blockEntity instanceof ShulkerBoxBlockEntity) {

                        double distSq = center.distSqr(mutablePos);
                        if (distSq < closestDistSq) {
                            closestDistSq = distSq;
                            closestChest = mutablePos.immutable();
                        }
                    }
                }
            }
        }

        return closestChest;
    }

    private boolean storeItemsInChest(BlockPos chestPos) {
        BlockEntity blockEntity = this.level().getBlockEntity(chestPos);

        if (blockEntity instanceof Container container) {
            for (int i = 0; i < this.inventory.getContainerSize(); i++) {
                ItemStack stack = this.inventory.getItem(i);
                if (!stack.isEmpty()) {
                    // チェストに空きスロットを探して格納
                    ItemStack remaining = addItemToContainer(container, stack);
                    this.inventory.setItem(i, remaining);
                }
            }

            // 全て格納できたか確認
            return !hasItemsInInventory();
        }

        return false;
    }

    private ItemStack addItemToContainer(Container container, ItemStack stack) {
        ItemStack remaining = stack.copy();

        // 既存のスタックと統合を試みる
        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack existingStack = container.getItem(i);
            if (!existingStack.isEmpty() && ItemStack.isSameItemSameComponents(existingStack, remaining)) {
                int maxStackSize = Math.min(existingStack.getMaxStackSize(), container.getMaxStackSize());
                int transferAmount = Math.min(remaining.getCount(), maxStackSize - existingStack.getCount());

                if (transferAmount > 0) {
                    existingStack.grow(transferAmount);
                    remaining.shrink(transferAmount);
                    container.setChanged();

                    if (remaining.isEmpty()) {
                        return ItemStack.EMPTY;
                    }
                }
            }
        }

        // 空きスロットに配置
        for (int i = 0; i < container.getContainerSize(); i++) {
            if (container.getItem(i).isEmpty()) {
                container.setItem(i, remaining);
                container.setChanged();
                return ItemStack.EMPTY;
            }
        }

        return remaining;
    }

    private void dropAllItems() {
        for (int i = 0; i < this.inventory.getContainerSize(); i++) {
            ItemStack stack = this.inventory.getItem(i);
            if (!stack.isEmpty()) {
                this.spawnAtLocation(stack);
                this.inventory.setItem(i, ItemStack.EMPTY);
            }
        }
    }

    private void completeDeliveryMission() {
        this.isOnDeliveryMission = false;
        this.deliveryState = DeliveryState.IDLE;
        this.deliveryTimer = 0;

        // チャンク強制ロード解除
        ServerLevel serverLevel = (ServerLevel) this.level();
        if (this.playerLastPos != null) {
            ChunkPos chunkPos = new ChunkPos(this.playerLastPos);
            serverLevel.setChunkForced(chunkPos.x, chunkPos.z, false);
        }

        // 飛行速度を元に戻す
        this.getAttribute(Attributes.FLYING_SPEED).setBaseValue(0.4);

        // プレイヤーに通知
        Player owner = this.level().getPlayerByUUID(this.ownerUUID);
        if (owner != null) {
            owner.displayClientMessage(
                    Component.translatable("entity.albatross.delivery_complete"),
                    true
            );
        }

        // 永続化解除
        this.getPersistentData().putBoolean("PersistenceRequired", false);
    }

    private void cancelDeliveryMission() {
        // アイテムをその場にドロップ
        dropAllItems();

        // 全てのチャンク強制ロード解除
        ServerLevel serverLevel = (ServerLevel) this.level();
        if (this.targetRespawnPoint != null) {
            ChunkPos chunkPos = new ChunkPos(this.targetRespawnPoint);
            serverLevel.setChunkForced(chunkPos.x, chunkPos.z, false);
        }
        if (this.playerLastPos != null) {
            ChunkPos chunkPos = new ChunkPos(this.playerLastPos);
            serverLevel.setChunkForced(chunkPos.x, chunkPos.z, false);
        }

        this.isOnDeliveryMission = false;
        this.deliveryState = DeliveryState.IDLE;
        this.getAttribute(Attributes.FLYING_SPEED).setBaseValue(0.4);
        this.getPersistentData().putBoolean("PersistenceRequired", false);
    }

    @Override
    protected void dropCustomDeathLoot(net.minecraft.server.level.ServerLevel serverLevel, DamageSource source, boolean recentlyHit) {
        super.dropCustomDeathLoot(serverLevel, source, recentlyHit);

        // インベントリ内のアイテムをドロップ
        for (int i = 0; i < this.inventory.getContainerSize(); i++) {
            ItemStack stack = this.inventory.getItem(i);
            if (!stack.isEmpty()) {
                this.spawnAtLocation(stack);
            }
        }
    }

    @Override
    public void remove(RemovalReason reason) {
        // エンティティ削除時にチャンク強制ロードを解除
        if (!this.level().isClientSide && this.isOnDeliveryMission) {
            cancelDeliveryMission();
        }
        super.remove(reason);
    }


    @Override
    public boolean isFood(net.minecraft.world.item.ItemStack pStack) {
        return false;
    }
}

