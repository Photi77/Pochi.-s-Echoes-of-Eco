package net.pochi.pochimod.entity.custom;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Dynamic;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.GameEventTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.animal.ShoulderRidingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.DynamicGameEventListener;
import net.minecraft.world.level.gameevent.EntityPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.entity.ModEntityTypes;
import net.pochi.pochimod.item.ModItems;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;

public class LongTailTit extends ShoulderRidingEntity implements FlyingAnimal, InventoryCarrier, VibrationSystem {
    private static final Vec3i ITEM_PICKUP_REACH = new Vec3i(1, 1, 1);

    private static final Logger LOGGER = LogUtils.getLogger();

    public final net.minecraft.world.entity.AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    private final SimpleContainer inventory = new SimpleContainer(1);
    private static final Item POISONOUS_FOOD = Items.COOKIE;

    private final DynamicGameEventListener<VibrationSystem.Listener> dynamicVibrationListener;
    private VibrationSystem.Data vibrationData;
    private final VibrationSystem.User vibrationUser;

    private static final Set<Item> TAME_FOOD = Sets.newHashSet(
            Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS,
            ModItems.ASPARAGUS_SEEDS.get(), ModItems.COLA_SEEDS.get(), ModItems.CABBAGE_SEEDS.get(), ModItems.MINT_SEEDS.get(),
            ModItems.RICE_SEEDS.get(), ModItems.TOMATO_SEEDS.get(),ModItems.CORN_SEEDS.get(),ModItems.ONION_SEEDS.get(),ModItems.GINGER_SEEDS.get(),
            ModItems.GREEN_PEPPER_SEEDS.get(),ModItems.PAPRIKA_SEEDS.get(),ModItems.EGGPLANT_SEEDS.get(),ModItems.WHITE_RADISH_SEEDS.get());

    private static final int VARIANTS = 5;

    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    private float flapping = 1.0F;
    private float nextFlap = 1.0F;
    protected BlockPos blockPos = BlockPos.ZERO;

    protected static final ImmutableList<SensorType<? extends Sensor<? super LongTailTit>>> SENSOR_TYPES = ImmutableList.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.NEAREST_PLAYERS, SensorType.HURT_BY, SensorType.NEAREST_ITEMS);
    protected static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(MemoryModuleType.PATH, MemoryModuleType.LOOK_TARGET, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES, MemoryModuleType.WALK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.HURT_BY, MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM, MemoryModuleType.LIKED_PLAYER, MemoryModuleType.LIKED_NOTEBLOCK_POSITION, MemoryModuleType.LIKED_NOTEBLOCK_COOLDOWN_TICKS, MemoryModuleType.ITEM_PICKUP_COOLDOWN_TICKS, MemoryModuleType.IS_PANICKING);

    public LongTailTit(EntityType<? extends Animal> entityType, Level level) {
        super((EntityType<? extends ShoulderRidingEntity>) entityType, level);
        this.moveControl = new FlyingMoveControl(this, 10, false);
        this.setCanPickUpLoot(this.canPickUpLoot());
        this.vibrationUser = new LongTailTit.VibrationUser();
        this.vibrationData = new VibrationSystem.Data();
        this.dynamicVibrationListener = new DynamicGameEventListener<>(new VibrationSystem.Listener(this));
        this.setPathfindingMalus(PathType.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(PathType.DAMAGE_FIRE, -1.0F);
        this.setPathfindingMalus(PathType.COCOA, -1.0F);
    }

    protected Brain.Provider<LongTailTit> brainProvider() {
        return Brain.provider(MEMORY_TYPES, SENSOR_TYPES);
    }

    protected Brain<?> makeBrain(Dynamic<?> p_218344_) {
        return LongTailTitAi.makeBrain(this.brainProvider().makeBrain(p_218344_));
    }

    public Brain<LongTailTit> getBrain() {
        return (Brain<LongTailTit>)super.getBrain();
    }


    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.FLYING_SPEED, (double)4.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.3f).build();
    }

    protected PathNavigation createNavigation(@NotNull Level pLevel) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, pLevel);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.of(Items.SPIDER_EYE), false));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 5.0F, 1.0F));
        this.goalSelector.addGoal(2, new LongTailTit.LongTailTitWanderGoal(this, 1.0D));
        //this.goalSelector.addGoal(3, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Spider.class, false));
    }


    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return pSize.height() * 0.6F;
    }


    protected void customServerAiStep() {
        this.level().getProfiler().push("allayBrain");
        this.getBrain().tick((ServerLevel)this.level(), this);
        this.level().getProfiler().pop();
        this.level().getProfiler().push("allayActivityUpdate");
        LongTailTitAi.updateActivity(this);
        this.level().getProfiler().pop();
        super.customServerAiStep();
    }

    public boolean doHurtTarget(Entity p_32257_) {
        if (super.doHurtTarget(p_32257_)) {
            if (p_32257_ instanceof Spider spider) {
                spider.hurt(this.damageSources().mobAttack(this), random.nextInt(6,10));
            }

            return true;
        } else {
            return false;
        }
    }

    public void aiStep() {
        super.aiStep();
        this.calculateFlapping();
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    public void updateDynamicGameEventListener(BiConsumer<DynamicGameEventListener<?>, ServerLevel> p_218348_) {
        Level level = this.level();
        if (level instanceof ServerLevel serverlevel) {
            p_218348_.accept(this.dynamicVibrationListener, serverlevel);
        }

    }

    public void addAdditionalSaveData(CompoundTag p_218367_) {
        super.addAdditionalSaveData(p_218367_);
        this.writeInventoryToTag(p_218367_, this.registryAccess());
        VibrationSystem.Data.CODEC.encodeStart(NbtOps.INSTANCE, this.vibrationData).resultOrPartial(LOGGER::error).ifPresent((p_218353_) -> {
            p_218367_.put("listener", p_218353_);
        });
    }

    public void readAdditionalSaveData(CompoundTag p_218350_) {
        super.readAdditionalSaveData(p_218350_);
        this.readInventoryFromTag(p_218350_, this.registryAccess());
        if (p_218350_.contains("listener", 10)) {
            VibrationSystem.Data.CODEC.parse(new Dynamic<>(NbtOps.INSTANCE, p_218350_.getCompound("listener"))).resultOrPartial(LOGGER::error).ifPresent((p_281082_) -> {
                this.vibrationData = p_281082_;
            });
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        } else {
            VibrationSystem.Ticker.tick(this.level(), this.vibrationData, this.vibrationUser);
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

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return ModEntityTypes.LONG_TIT.get().create(p_146743_);
    }


    protected boolean isFlapping() {
        return this.flyDist > this.nextFlap;
    }

    protected void onFlap() {
        this.playSound(SoundEvents.PARROT_FLY, 0.15F, 1.0F);
        this.nextFlap = this.flyDist + this.flapSpeed / 2.0F;
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
    public Data getVibrationData() {
        return this.vibrationData;
    }

    @Override
    public User getVibrationUser() {
        return this.vibrationUser;
    }


    static class LongTailTitWanderGoal extends WaterAvoidingRandomFlyingGoal {
        public LongTailTitWanderGoal(PathfinderMob p_186224_, double p_186225_) {
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

    public void setSitting(boolean pSitting) {
        this.setFlag(1, pSitting);
    }

    private void setFlag(int pFlagId, boolean pValue) {
        if (pValue) {
            this.entityData.set(DATA_FLAGS_ID, (byte)(this.entityData.get(DATA_FLAGS_ID) | pFlagId));
        } else {
            this.entityData.set(DATA_FLAGS_ID, (byte)(this.entityData.get(DATA_FLAGS_ID) & ~pFlagId));
        }

    }

    public InteractionResult mobInteract(Player p_218361_, InteractionHand p_218362_) {
        ItemStack itemstack = p_218361_.getItemInHand(p_218362_);
        ItemStack itemstack1 = this.getItemInHand(InteractionHand.MAIN_HAND);
         if (itemstack1.isEmpty() && !itemstack.isEmpty()) {
            ItemStack itemstack3 = itemstack.copyWithCount(1);
            this.setItemInHand(InteractionHand.MAIN_HAND, itemstack3);
            this.removeInteractionItem(p_218361_, itemstack);
            this.level().playSound(p_218361_, this, SoundEvents.ALLAY_ITEM_GIVEN, SoundSource.NEUTRAL, 2.0F, 1.0F);
            this.getBrain().setMemory(MemoryModuleType.LIKED_PLAYER, p_218361_.getUUID());
            return InteractionResult.SUCCESS;
        } else if (!itemstack1.isEmpty() && p_218362_ == InteractionHand.MAIN_HAND && itemstack.isEmpty()) {
            this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            this.level().playSound(p_218361_, this, SoundEvents.ALLAY_ITEM_TAKEN, SoundSource.NEUTRAL, 2.0F, 1.0F);
            this.swing(InteractionHand.MAIN_HAND);

            for(ItemStack itemstack2 : this.getInventory().removeAllItems()) {
                BehaviorUtils.throwItem(this, itemstack2, this.position());
            }

            this.getBrain().eraseMemory(MemoryModuleType.LIKED_PLAYER);
            p_218361_.addItem(itemstack1);
            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(p_218361_, p_218362_);
        }
    }


    public boolean canPickUpLoot() {
        return !this.isOnPickupCooldown() && this.hasItemInHand();
    }

    public boolean hasItemInHand() {
        return !this.getItemInHand(InteractionHand.MAIN_HAND).isEmpty();
    }

    public boolean canTakeItem(ItemStack p_218380_) {
        return false;
    }

    private boolean isOnPickupCooldown() {
        return this.getBrain().checkMemory(MemoryModuleType.ITEM_PICKUP_COOLDOWN_TICKS, MemoryStatus.VALUE_PRESENT);
    }

    public SimpleContainer getInventory() {
        return this.inventory;
    }

    protected Vec3i getPickupReach() {
        return ITEM_PICKUP_REACH;
    }

    public boolean wantsToPickUp(ItemStack p_218387_) {
        ItemStack itemstack = this.getItemInHand(InteractionHand.MAIN_HAND);
        return !itemstack.isEmpty() && this.inventory.canAddItem(p_218387_) && this.allayConsidersItemEqual(itemstack, p_218387_) && net.neoforged.neoforge.common.NeoForge.EVENT_BUS.post(new net.neoforged.neoforge.event.entity.EntityMobGriefingEvent(this.level(), this)).canGrief();
    }

    private boolean allayConsidersItemEqual(ItemStack p_252278_, ItemStack p_250405_) {
        return ItemStack.isSameItem(p_252278_, p_250405_) && !this.hasNonMatchingPotion(p_252278_, p_250405_);
    }

    private boolean hasNonMatchingPotion(ItemStack p_248762_, ItemStack p_250839_) {
        net.minecraft.world.item.alchemy.PotionContents potioncontents = p_248762_.get(net.minecraft.core.component.DataComponents.POTION_CONTENTS);
        net.minecraft.world.item.alchemy.PotionContents potioncontents1 = p_250839_.get(net.minecraft.core.component.DataComponents.POTION_CONTENTS);
        return !java.util.Objects.equals(potioncontents, potioncontents1);
    }

    protected void pickUpItem(ItemEntity p_218359_) {
        InventoryCarrier.pickUpItem(this, this, p_218359_);
    }

    protected void sendDebugPackets() {
        super.sendDebugPackets();
        DebugPackets.sendEntityBrain(this);
    }

    private void removeInteractionItem(Player p_239359_, ItemStack p_239360_) {
        if (!p_239359_.getAbilities().instabuild) {
            p_239360_.shrink(1);
        }

    }

    public boolean equipmentHasChanged(ItemStack p_249825_, ItemStack p_251595_) {
        return !this.allayConsidersItemEqual(p_249825_, p_251595_);
    }

    protected void dropEquipment() {
        super.dropEquipment();
        this.inventory.removeAllItems().forEach(this::spawnAtLocation);
        ItemStack itemstack = this.getItemBySlot(EquipmentSlot.MAINHAND);
        if (!itemstack.isEmpty()) {
            this.spawnAtLocation(itemstack);
            this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        }

    }

    public boolean removeWhenFarAway(double p_218384_) {
        return false;
    }


    class VibrationUser implements VibrationSystem.User {
        private static final int VIBRATION_EVENT_LISTENER_RANGE = 16;
        private final PositionSource positionSource = new EntityPositionSource(LongTailTit.this, LongTailTit.this.getEyeHeight());

        public int getListenerRadius() {
            return 16;
        }

        public PositionSource getPositionSource() {
            return this.positionSource;
        }

        public boolean canReceiveVibration(ServerLevel p_282038_, BlockPos p_283385_, net.minecraft.core.Holder<GameEvent> p_283669_, GameEvent.Context p_282208_) {
            if (LongTailTit.this.isNoAi()) {
                return false;
            } else {
                Optional<GlobalPos> optional = LongTailTit.this.getBrain().getMemory(MemoryModuleType.LIKED_NOTEBLOCK_POSITION);
                if (optional.isEmpty()) {
                    return true;
                } else {
                    GlobalPos globalpos = optional.get();
                    return globalpos.dimension().equals(p_282038_.dimension()) && globalpos.pos().equals(p_283385_);
                }
            }
        }

        public void onReceiveVibration(ServerLevel p_281422_, BlockPos p_281449_, net.minecraft.core.Holder<GameEvent> p_281488_, @org.jetbrains.annotations.Nullable Entity p_281794_, @org.jetbrains.annotations.Nullable Entity p_281864_, float p_281642_) {
            if (p_281488_.is(GameEvent.NOTE_BLOCK_PLAY)) {
                LongTailTitAi.hearNoteblock(LongTailTit.this, new BlockPos(p_281449_));
            }

        }

        public TagKey<GameEvent> getListenableEvents() {
            return GameEventTags.ALLAY_CAN_LISTEN;
        }
    }

    @Override
    public boolean isFood(ItemStack p_27600_) {
        return p_27600_.is(Items.SPIDER_EYE);
    }
}

