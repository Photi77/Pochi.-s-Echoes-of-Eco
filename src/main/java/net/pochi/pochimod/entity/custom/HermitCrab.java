package net.pochi.pochimod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.pochi.pochimod.entity.ModEntityTypes;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class HermitCrab extends Animal {
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.COD_BUCKET, Items.SALMON_BUCKET);
    public final net.minecraft.world.entity.AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private static final EntityDataAccessor<Optional<BlockState>> DATA_CARRY_STATE = SynchedEntityData.defineId(HermitCrab.class, EntityDataSerializers.OPTIONAL_BLOCK_STATE);
    public int eggTime = this.random.nextInt(6000) + 600;
    public HermitCrab(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public InteractionResult mobInteract(Player p_28298_, InteractionHand p_28299_) {
        ItemStack itemstack = p_28298_.getItemInHand(p_28299_);
        if (itemstack.getItem() instanceof BlockItem blockItem) {
            p_28298_.playSound(SoundEvents.CHEST_OPEN, 1.0F, 1.0F);
            setCarriedBlock(blockItem.getBlock().defaultBlockState());
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(p_28298_, p_28299_);
        }
    }

    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_CARRY_STATE, Optional.empty());
    }

    public void addAdditionalSaveData(CompoundTag p_32520_) {
        super.addAdditionalSaveData(p_32520_);
        BlockState blockstate = this.getCarriedBlock();
        if (blockstate != null) {
            p_32520_.put("carried", NbtUtils.writeBlockState(blockstate));
        }
    }

    public void readAdditionalSaveData(CompoundTag p_32511_) {
        super.readAdditionalSaveData(p_32511_);
        BlockState blockstate = null;
        blockstate = NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), p_32511_.getCompound("carried"));
        if (blockstate != null) {
            setCarriedBlock(blockstate);
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return ModEntityTypes.HERMIT_CRAB.get().create(p_146743_);
    }

    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide && this.isAlive() && !this.isBaby() && --this.eggTime <= 0) {
            if(!(getCarriedBlock() == null)) {
                BlockState state = getCarriedBlock();
                ItemStack stack = state.getBlock().asItem().getDefaultInstance();
                this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                this.spawnAtLocation(stack);
                this.gameEvent(GameEvent.ENTITY_PLACE);
                this.eggTime = this.random.nextInt(600) + 600;
            }
        }
    }

    public void setCarriedBlock(@javax.annotation.Nullable BlockState p_32522_) {
        this.entityData.set(DATA_CARRY_STATE, Optional.ofNullable(p_32522_));
    }

    @javax.annotation.Nullable
    public BlockState getCarriedBlock() {
        return this.entityData.get(DATA_CARRY_STATE).orElse((BlockState)null);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    public int getIdleAnimationTimeout(){
        return this.idleAnimationTimeout;
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
        adaptTextureToBlockBelow();
    }

    private void adaptTextureToBlockBelow() {
        // エンティティの現在位置の下にあるブロックの位置を取得
        BlockPos belowPos = this.blockPosition().below();
        BlockState blockState = this.level().getBlockState(belowPos);

        // ブロックのテクスチャ情報を取得してエンティティに適用するロジックを実装
        // 具体的な実装はレンダリング部分で行います
    }

    @Override
    public boolean isFood(ItemStack p_27600_) {
        return p_27600_.is(Items.SALMON);
    }

}
