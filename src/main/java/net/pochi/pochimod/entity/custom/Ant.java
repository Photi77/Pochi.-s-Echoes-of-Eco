package net.pochi.pochimod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.pochi.pochimod.entity.ModEntityTypes;
import net.pochi.pochimod.entity.ai.AntPickUpitemGoal;
import net.pochi.pochimod.entity.ai.WantToChestGoal;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Ant extends Animal implements InventoryCarrier {

    private static final Vec3i ITEM_PICKUP_REACH = new Vec3i(1, 1, 1);
    private final SimpleContainer inventory = new SimpleContainer(1);
    public final net.minecraft.world.entity.AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    //private static final EntityDataAccessor<Optional<BlockPos>> DATA_START_POS = SynchedEntityData.defineId(Ant.class, EntityDataSerializers.OPTIONAL_BLOCK_POS);
    private static final EntityDataAccessor<Optional<BlockPos>> DATA_GOAL_POS = SynchedEntityData.defineId(Ant.class, EntityDataSerializers.OPTIONAL_BLOCK_POS);

    private static final List<Item> WANT = net.minecraft.core.registries.BuiltInRegistries.ITEM.stream().toList();
    private static final Set<Item> WANTED_ITEMS = new HashSet<Item>(WANT);

    public Ant(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(4, new AntPickUpitemGoal(this));
        this.goalSelector.addGoal(5, new WantToChestGoal(this));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.2D, Ingredient.of(Items.SUGAR), false));
        //this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_GOAL_POS, Optional.empty());
    }

    public void addAdditionalSaveData(CompoundTag p_32520_) {
        super.addAdditionalSaveData(p_32520_);
        BlockPos pos = this.getChestPos();
        if (pos != null) {
            p_32520_.put("pos", NbtUtils.writeBlockPos(pos));
        }
    }

    public void readAdditionalSaveData(CompoundTag p_32511_) {
        super.readAdditionalSaveData(p_32511_);
        NbtUtils.readBlockPos(p_32511_, "pos").ifPresent(this::setChestPos);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SILVERFISH_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_29502_) {
        return SoundEvents.SILVERFISH_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SILVERFISH_DEATH;
    }

    protected void playStepSound(BlockPos p_29492_, BlockState p_29493_) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return ModEntityTypes.ANT.get().create(p_146743_);
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
        return p_27600_.is(Items.SUGAR);
    }

    public void setChestPos(@javax.annotation.Nullable BlockPos p_32522_) {
        this.entityData.set(DATA_GOAL_POS, Optional.ofNullable(p_32522_));
    }

    @javax.annotation.Nullable
    public BlockPos getChestPos() {
        return this.entityData.get(DATA_GOAL_POS).orElse((BlockPos) null);
    }


    @Override
    public SimpleContainer getInventory() {
        return this.inventory;
    }

    @Override
    public void pickUpItem(ItemEntity itemEntity) {
        SimpleContainer inventory = this.getInventory();
        ItemStack itemStack = itemEntity.getItem();

        // インベントリにアイテムを追加
        boolean added = false;
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack slotStack = inventory.getItem(i);

            // 空きスロットか、同じアイテムでスタックできる場合
            if (slotStack.isEmpty() || (slotStack.is(itemStack.getItem()) && slotStack.getCount() < slotStack.getMaxStackSize())) {
                inventory.addItem(itemStack);
                itemEntity.discard(); // アイテムエンティティを消去
                added = true;
            }
        }

        if (!added) {
            System.out.println("No room in inventory for item: " + itemStack);
        } else {
            System.out.println("Picked up item: " + itemStack);
        }
    }

    public boolean wantsToPickUp(ItemStack p_35543_) {
        Item item = p_35543_.getItem();
        return WANTED_ITEMS.contains(item) && this.getInventory().canAddItem(p_35543_);
    }

    public void moveItemsToChest(BlockEntity blockEntity) {
        if (blockEntity instanceof ChestBlockEntity chest) {
            Container chestInventory = chest;

            // エンティティのインベントリを取得
            SimpleContainer entityInventory = this.getInventory();

            // エンティティのインベントリ内のアイテムをチェストに移動
            for (int i = 0; i < entityInventory.getContainerSize(); i++) {
                ItemStack itemStack = entityInventory.getItem(i);

                if (!itemStack.isEmpty()) {
                    // チェストにアイテムを移動するロジック
                    for (int j = 0; j < chestInventory.getContainerSize(); j++) {
                        ItemStack chestStack = chestInventory.getItem(j);
                        if (chestStack.isEmpty()) {
                            chestInventory.setItem(j, itemStack);
                            entityInventory.setItem(i, ItemStack.EMPTY); // エンティティのスロットを空に
                            break;
                        } else if(chestStack.is(itemStack.getItem()) && chestStack.isStackable() && !(chestStack.getCount() == itemStack.getMaxStackSize())){
                            ItemStack newStack = new ItemStack(chestStack.getItem(), chestStack.getCount()+itemStack.getCount());
                            chestInventory.setItem(j, newStack);
                            entityInventory.setItem(i, ItemStack.EMPTY); // エンティティのスロットを空に
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
        this.pickupNearbyItems();
        // チェストが近くにあるかどうかチェック
        BlockPos entityPos = this.blockPosition();
        BlockPos chestPos = findNearestChest(entityPos);
        if (chestPos != null && this.distanceToSqr(chestPos.getX(), chestPos.getY(), chestPos.getZ()) < 4.0D) {
            // チェストにアイテムを移動する処理
            BlockEntity blockEntity = level().getBlockEntity(chestPos);
            if (blockEntity != null) {
                moveItemsToChest(blockEntity);
            }
        }
    }

    // チェストを探すメソッド
    public BlockPos findNearestChest(BlockPos entityPos) {
        int searchRadius = 30; // 探索範囲
        for (BlockPos pos : BlockPos.betweenClosed(entityPos.offset(-searchRadius, -searchRadius, -searchRadius), entityPos.offset(searchRadius, searchRadius, searchRadius))) {
            BlockState state = level().getBlockState(pos);
            if (state.getBlock() instanceof ChestBlock) {
                return pos;
            }
        }
        return null;
    }

    public void pickupNearbyItems() {
        // エンティティの位置を取得
        AABB boundingBox = this.getBoundingBox().inflate(2.0D, 2.0D, 2.0D); // 半径2ブロックの範囲を探索
        List<ItemEntity> itemsNearby = this.level().getEntitiesOfClass(ItemEntity.class, boundingBox);

        // 見つけたItemEntityを拾う
        for (ItemEntity itemEntity : itemsNearby) {
            if (!itemEntity.isRemoved() && !itemEntity.getItem().isEmpty()) {
                // エンティティがアイテムを拾う処理
                pickUpItem(itemEntity);
            }
        }
    }

    @Override
    public InteractionResult mobInteract(Player p_27584_, InteractionHand p_27585_) {
        if(p_27584_.getMainHandItem().is(Items.STICK)) {
            BlockPos pos = findNearestChest(this.getOnPos());
            if(pos != null){
                setChestPos(pos);
                return InteractionResult.SUCCESS;
            } else {
                if(!this.level().isClientSide) {
                    p_27584_.sendSystemMessage(Component.literal("近くにチェストがない！"));
                }
            }
        }
        return super.mobInteract(p_27584_, p_27585_);
    }
}
