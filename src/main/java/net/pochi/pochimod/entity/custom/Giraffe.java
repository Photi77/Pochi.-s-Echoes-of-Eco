package net.pochi.pochimod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.entity.ModEntityTypes;
import net.pochi.pochimod.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Giraffe extends Animal {

    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.PUMPKIN, Items.MELON,Items.WHEAT, ModItems.CABBAGE_LEAF.get());
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, Ingredient.of(Items.CARROT_ON_A_STICK), false));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(3, new Giraffe.DoeStrippedGoal(1.0F,12,4));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    public Giraffe(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
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
        return ModEntityTypes.GIRAFFE.get().create(p_146743_);
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
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    @Override
    public boolean isFood(ItemStack p_27600_) {
        return p_27600_.is(ModItems.ROW_COFFEE_BEANS.get());
    }

    public class DoeStrippedGoal extends MoveToBlockGoal {
        private static final int WAIT_TICKS = 40;
        protected int ticksWaited;

        public List<ItemStack> getDropsFromBlock(BlockState state, ServerLevel level, BlockPos pos) {
            // ルートコンテキストを構築
            LootParams.Builder builder = new LootParams.Builder(level)
                    .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                    .withParameter(LootContextParams.TOOL, ItemStack.EMPTY)
                    .withOptionalParameter(LootContextParams.THIS_ENTITY,null)
                    .withOptionalParameter(LootContextParams.BLOCK_ENTITY, level.getBlockEntity(pos));

            // ブロックのルートテーブルを取得してドロップを生成
            return state.getDrops(builder);
        }



        public DoeStrippedGoal(double pSpeedModifier, int pSearchRange, int pVerticalSearchRange) {
            super(Giraffe.this, pSpeedModifier, pSearchRange, pVerticalSearchRange);
        }

        public double acceptedDistance() {
            return 5.0D;
        }

        public boolean shouldRecalculatePath() {
            return this.tryTicks % 100 == 0;
        }

        /**
         * Return true to set given position as destination
         */
        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            BlockState blockstate = pLevel.getBlockState(pPos);
            return blockstate.getBlock() instanceof LeavesBlock;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (this.isReachedTarget()) {
                if (this.ticksWaited >= 40) {
                    this.onReachedTarget();
                } else {
                    ++this.ticksWaited;
                }
            }
            super.tick();
        }

        protected void onReachedTarget() {
            if (net.neoforged.neoforge.common.NeoForge.EVENT_BUS.post(new net.neoforged.neoforge.event.entity.EntityMobGriefingEvent(Giraffe.this.level(), Giraffe.this)).canGrief()) {
                BlockState blockstate = Giraffe.this.level().getBlockState(this.blockPos);
                if (blockstate.getBlock() instanceof LeavesBlock ) {
                    this.stripped(blockstate, blockPos);
                    ticksWaited = 0;
                }
            }
        }

        private void stripped(BlockState pState, BlockPos pos) {
            ServerLevel serverLevel = (ServerLevel) Giraffe.this.level();

            // 苗木を確実に取得するまでルートを試行
            ItemStack saplingDrop = ItemStack.EMPTY;
            List<ItemStack> otherDrops = new ArrayList<>();

            // 最大20回試行して苗木を探す
            for (int attempt = 0; attempt < 20 && saplingDrop.isEmpty(); attempt++) {
                List<ItemStack> drops = getDropsFromBlock(pState, serverLevel, pos);

                for (ItemStack drop : drops) {
                    if (drop.is(ItemTags.SAPLINGS)) {
                        saplingDrop = drop.copy();
                        break;
                    } else if (attempt == 0) {
                        // 最初の試行のみ他のアイテムを保存
                        otherDrops.add(drop.copy());
                    }
                }
            }

            // それでも苗木が出なかった場合、ブロックから直接取得
            //if (saplingDrop.isEmpty()) {
            //    saplingDrop = getSaplingFromLeaves(pState);
            //}

            // ブロックを破壊
            serverLevel.destroyBlock(pos, false);

            // 苗木を確定ドロップ
            if (!saplingDrop.isEmpty()) {
                Block.popResource(serverLevel, pos, saplingDrop);
            }

            // その他のドロップ
            for (ItemStack drop : otherDrops) {
                Block.popResource(serverLevel, pos, drop);
            }

            Giraffe.this.playSound(SoundEvents.SHEEP_SHEAR, 1.0F, 1.0F);
        }

        // 葉っぱブロックから対応する苗木を取得
        //private ItemStack getSaplingFromLeaves(BlockState leafState) {
        //    Block leafBlock = leafState.getBlock();
//
        //    // バニラの葉っぱブロックに対応
        //    if (leafBlock == Blocks.OAK_LEAVES) {
        //        return new ItemStack(Items.OAK_SAPLING, 1);
        //    } else if (leafBlock == Blocks.SPRUCE_LEAVES) {
        //        return new ItemStack(Items.SPRUCE_SAPLING, 1);
        //    } else if (leafBlock == Blocks.BIRCH_LEAVES) {
        //        return new ItemStack(Items.BIRCH_SAPLING, 1);
        //    } else if (leafBlock == Blocks.JUNGLE_LEAVES) {
        //        return new ItemStack(Items.JUNGLE_SAPLING, 1);
        //    } else if (leafBlock == Blocks.ACACIA_LEAVES) {
        //        return new ItemStack(Items.ACACIA_SAPLING, 1);
        //    } else if (leafBlock == Blocks.DARK_OAK_LEAVES) {
        //        return new ItemStack(Items.DARK_OAK_SAPLING, 1);
        //    } else if (leafBlock == Blocks.MANGROVE_LEAVES) {
        //        return new ItemStack(Items.MANGROVE_PROPAGULE, 1);
        //    } else if (leafBlock == Blocks.CHERRY_LEAVES) {
        //        return new ItemStack(Items.CHERRY_SAPLING, 1);
        //    } else if (leafBlock == Blocks.AZALEA_LEAVES) {
        //        return new ItemStack(Items.AZALEA, 1);
        //    } else if (leafBlock == Blocks.FLOWERING_AZALEA_LEAVES) {
        //        return new ItemStack(Items.FLOWERING_AZALEA, 1);
        //    }
//
        //    // 対応する苗木が見つからない場合は空
        //    return ItemStack.EMPTY;
        //}

        public boolean canUse() {
            return !Giraffe.this.isSleeping() && super.canUse();
        }

        public void start() {
            this.ticksWaited = 0;
            super.start();
        }
    }
}