package net.pochi.pochimod.entity.projectile;

import com.mojang.logging.LogUtils;
import net.minecraft.CrashReportCategory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.DirectionalPlaceContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.entity.ModEntityTypes;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class BlockArrowEntity extends AbstractArrow {

    private static final Logger LOGGER = LogUtils.getLogger();
    private BlockState blockState = Blocks.SAND.defaultBlockState();
    public int time;
    public boolean dropItem = true;
    private boolean cancelDrop;
    private boolean hurtEntities;
    private int fallDamageMax = 40;
    private float fallDamagePerDistance;
    @Nullable
    public CompoundTag blockData;

    public int killtime = 0;
    protected static final EntityDataAccessor<BlockPos> DATA_START_POS = SynchedEntityData.defineId(BlockArrowEntity.class, EntityDataSerializers.BLOCK_POS);


    @Nullable
    private LivingEntity owner;


    public BlockArrowEntity(EntityType<? extends BlockArrowEntity> p_31950_, Level p_31951_) {
        super(p_31950_, p_31951_);
    }

    public BlockArrowEntity(Level p_31953_, double p_31954_, double p_31955_, double p_31956_, @Nullable LivingEntity p_32083_, BlockState p_31957_) {
        this(ModEntityTypes.BLOCK_ARROW.get(), p_31953_);
        this.blockState = p_31957_;
        this.blocksBuilding = true;
        this.setPos(p_31954_, p_31955_, p_31956_);
        this.xo = p_31954_;
        this.yo = p_31955_;
        this.zo = p_31956_;
        this.owner = p_32083_;
        this.setStartPos(this.blockPosition());
        noPhysics = true;
    }

    public static BlockArrowEntity fall(Level p_201972_, BlockPos p_201973_, BlockState p_201974_) {
        BlockArrowEntity fallingblockentity = new BlockArrowEntity(p_201972_, (double)p_201973_.getX() + 0.5D, (double)p_201973_.getY(), (double)p_201973_.getZ() + 0.5D, null,p_201974_.hasProperty(BlockStateProperties.WATERLOGGED) ? p_201974_.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(false)) : p_201974_);
        p_201972_.setBlock(p_201973_, p_201974_.getFluidState().createLegacyBlock(), 3);
        p_201972_.addFreshEntity(fallingblockentity);
        return fallingblockentity;
    }

    public boolean isAttackable() {
        return false;
    }

    public void setStartPos(BlockPos p_31960_) {
        this.entityData.set(DATA_START_POS, p_31960_);
    }

    public BlockPos getStartPos() {
        return this.entityData.get(DATA_START_POS);
    }

    protected MovementEmission getMovementEmission() {
        return MovementEmission.NONE;
    }

    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
        builder.define(DATA_START_POS, BlockPos.ZERO);
    }

    public boolean isPickable() {
        return !this.isRemoved();
    }
    @Nullable
    public LivingEntity getOwner() {
        return this.owner;
    }

    public BlockState setBlockState(BlockState blockState) {
        return this.blockState = blockState;
    }

    @Override
    public void tick() {

        if(killtime++ >= 60){
            this.discard();
        }

        if (this.blockState.isAir()) {
            this.discard();
        } else {
            Block block = this.blockState.getBlock();
            ++this.time;

            this.move(MoverType.SELF, this.getDeltaMovement());
            if (!this.level().isClientSide) {
                BlockPos blockpos = this.blockPosition();
                boolean flag1 = this.blockState.canBeHydrated(this.level(), blockpos, this.level().getFluidState(blockpos), blockpos);


                BlockHitResult blockhitresult = this.level().clip(new ClipContext(new Vec3(this.xo, this.yo, this.zo), this.position(), ClipContext.Block.COLLIDER, ClipContext.Fluid.SOURCE_ONLY, this));
                if (blockhitresult.getType() != HitResult.Type.MISS && this.blockState.canBeHydrated(this.level(), blockpos, this.level().getFluidState(blockhitresult.getBlockPos()), blockhitresult.getBlockPos())) {
                    blockpos = blockhitresult.getBlockPos();
                    flag1 = true;
                }


                if (!this.onGround() && !flag1) {
                    if (!this.level().isClientSide && (this.time > 100 && (blockpos.getY() <= this.level().getMinBuildHeight() || blockpos.getY() > this.level().getMaxBuildHeight()) || this.time > 600)) {
                        if (this.dropItem && this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                            this.spawnAtLocation(block);
                        }

                        this.discard();
                    }
                } else {
                    BlockState blockstate = this.level().getBlockState(blockpos);
                    if (!blockstate.is(Blocks.MOVING_PISTON)) {
                        if (!this.cancelDrop) {
                            boolean flag2 = blockstate.canBeReplaced(new DirectionalPlaceContext(this.level(), blockpos, Direction.DOWN, ItemStack.EMPTY, Direction.UP));
                            boolean flag4 = this.blockState.canSurvive(this.level(), blockpos);
                            if (flag2 && flag4) {
                                if (this.blockState.hasProperty(BlockStateProperties.WATERLOGGED) && this.level().getFluidState(blockpos).getType() == Fluids.WATER) {
                                    this.blockState = this.blockState.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(true));
                                }

                                if (this.level().setBlock(blockpos, this.blockState, 3)) {
                                    ((ServerLevel)this.level()).getChunkSource().chunkMap.broadcast(this, new ClientboundBlockUpdatePacket(blockpos, this.level().getBlockState(blockpos)));
                                    this.discard();


                                    if (this.blockData != null && this.blockState.hasBlockEntity()) {
                                        BlockEntity blockentity = this.level().getBlockEntity(blockpos);
                                        if (blockentity != null) {
                                            CompoundTag compoundtag = blockentity.saveWithoutMetadata(this.level().registryAccess());

                                            for(String s : this.blockData.getAllKeys()) {
                                                compoundtag.put(s, this.blockData.get(s).copy());
                                            }

                                            blockentity.setChanged();
                                        }
                                    }
                                }
                            }
                        } else {
                            this.discard();
                        }
                    }
                }
            }
        }
    }

    public boolean causeFallDamage(float p_149643_, float p_149644_, DamageSource p_149645_) {
        if (!this.hurtEntities) {
            return false;
        } else {
            int i = Mth.ceil(p_149643_ - 1.0F);
            if (i < 0) {
                return false;
            } else {
                Predicate<Entity> predicate = EntitySelector.NO_CREATIVE_OR_SPECTATOR.and(EntitySelector.LIVING_ENTITY_STILL_ALIVE);
                Block $$8 = this.blockState.getBlock();
                DamageSource damagesource1;
                if ($$8 instanceof Fallable) {
                    Fallable fallable = (Fallable)$$8;
                    damagesource1 = fallable.getFallDamageSource(this);
                } else {
                    damagesource1 = this.damageSources().fallingBlock(this);
                }

                DamageSource damagesource = damagesource1;
                float f = (float)Math.min(Mth.floor((float)i * this.fallDamagePerDistance), this.fallDamageMax);
                this.level().getEntities(this, this.getBoundingBox(), predicate).forEach((p_149649_) -> {
                    p_149649_.hurt(damagesource, f);
                });
                boolean flag = this.blockState.is(BlockTags.ANVIL);
                if (flag && f > 0.0F && this.random.nextFloat() < 0.05F + (float)i * 0.05F) {
                    BlockState blockstate = AnvilBlock.damage(this.blockState);
                    if (blockstate == null) {
                        this.cancelDrop = true;
                    } else {
                        this.blockState = blockstate;
                    }
                }

                return false;
            }
        }
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return this.getBlockState().getBlock().asItem().getDefaultInstance();
    }

    @Override
    protected ItemStack getPickupItem() {
        return this.getDefaultPickupItem();
    }


    public boolean displayFireAnimation() {
        return false;
    }

    public void fillCrashReportCategory(CrashReportCategory p_31962_) {
        super.fillCrashReportCategory(p_31962_);
        p_31962_.setDetail("Immitating BlockState", this.blockState.toString());
    }

    public BlockState getBlockState() {
        return this.blockState;
    }

    protected Component getTypeName() {
        return Component.translatable("entity.minecraft.falling_block_type", this.blockState.getBlock().getName());
    }

    public boolean onlyOpCanSetNbt() {
        return true;
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket(net.minecraft.server.level.ServerEntity p_352287_) {
        return new ClientboundAddEntityPacket(this, p_352287_, Block.getId(this.getBlockState()));
    }

    public void recreateFromPacket(ClientboundAddEntityPacket p_149654_) {
        super.recreateFromPacket(p_149654_);
        this.blockState = Block.stateById(p_149654_.getData());
        this.blocksBuilding = true;
        double d0 = p_149654_.getX();
        double d1 = p_149654_.getY();
        double d2 = p_149654_.getZ();
        this.setPos(d0, d1, d2);
        this.setStartPos(this.blockPosition());
    }
}
