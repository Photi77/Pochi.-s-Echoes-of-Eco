package net.pochi.pochimod.block.entity.custom;


import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.HolderLookup;
import net.pochi.pochimod.block.custom.PotteryOnWheelBlock;
import net.pochi.pochimod.block.entity.ModBlockEntities;
import net.pochi.pochimod.item.ModItems;
import net.pochi.pochimod.pottery.PotteryShape;
import net.pochi.pochimod.pottery.PotteryState;
import org.jetbrains.annotations.Nullable;

public class PotteryOnWheelBlockEntity extends BlockEntity {

    private PotteryState state = PotteryState.SHAPING;
    private int progressTicks = 0;
    private int firingTime = 0; // 焼成時間（キャンプファイア上での経過時間）

    // 陶器のパラメータ
    private int height = 5;
    private int diameter = 5;
    private int wallThickness = 2;
    private int mouthWidth = 5;
    private PotteryShape shape = PotteryShape.BOWL;
    private int glazeColor = 0xFFFFFF;

    public PotteryOnWheelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.POTTERY_ON_WHEEL.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, PotteryOnWheelBlockEntity be) {
        boolean changed = false;

        // 乾燥中
        if (be.state == PotteryState.DRYING) {
            be.progressTicks++;

            if (level.isClientSide && level.random.nextFloat() < 0.1f) {
                level.addParticle(ParticleTypes.SMOKE,
                        pos.getX() + 0.5 + (level.random.nextDouble() - 0.5) * 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5 + (level.random.nextDouble() - 0.5) * 0.5,
                        0, 0.02, 0);
            }

            if (be.progressTicks >= be.state.getDuration()) {
                be.state = PotteryState.GLAZEABLE;
                be.progressTicks = 0;
                changed = true;

                level.playSound(null, pos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 1.5F);
            }
        }

        // 焼成中（キャンプファイア上）
        if (be.state == PotteryState.FIRING) {
            BlockPos below = pos.below();
            BlockState belowState = level.getBlockState(below);

            if (belowState.getBlock() instanceof CampfireBlock && belowState.getValue(CampfireBlock.LIT)) {
                be.firingTime++; // 焼成時間をカウント（無制限）
                changed = true;

                // パーティクルエフェクト
                if (level.isClientSide && level.random.nextFloat() < 0.3f) {
                    level.addParticle(ParticleTypes.FLAME,
                            pos.getX() + 0.5 + (level.random.nextDouble() - 0.5) * 0.6,
                            pos.getY() + 0.1,
                            pos.getZ() + 0.5 + (level.random.nextDouble() - 0.5) * 0.6,
                            0, 0.05, 0);
                }

                // 焼成時間に応じた視覚効果の変化
                if (be.firingTime % 100 == 0 && !level.isClientSide) {
                    // 100ティックごとに音を鳴らす（焼成進行の合図）
                    level.playSound(null, pos, SoundEvents.BLASTFURNACE_FIRE_CRACKLE,
                            SoundSource.BLOCKS, 0.3F, 1.0F + (be.firingTime / 1000f));
                }
            } else {
                // キャンプファイアが消えた場合、焼成中断
                if (!level.isClientSide) {
                    level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH,
                            SoundSource.BLOCKS, 0.5F, 0.8F);
                }
            }
        }

        if (changed) {
            be.setChanged();
            level.sendBlockUpdated(pos, state, state, 3);
        }
    }

    // パラメータ調整メソッド
    public void adjustHeight(int delta) {
        this.height = Math.max(1, Math.min(10, this.height + delta));
        updateVisualHeight();
        setChanged();
        syncToClient();
    }

    public void adjustDiameter(int delta) {
        this.diameter = Math.max(1, Math.min(10, this.diameter + delta));
        setChanged();
        syncToClient();
    }

    public void adjustThickness(int delta) {
        this.wallThickness = Math.max(1, Math.min(5, this.wallThickness + delta));
        setChanged();
        syncToClient();
    }

    public void adjustMouth(int delta) {
        this.mouthWidth = Math.max(1, Math.min(10, this.mouthWidth + delta));
        setChanged();
        syncToClient();
    }

    public void cycleShape() {
        PotteryShape[] shapes = PotteryShape.values();
        int currentIndex = 0;
        for (int i = 0; i < shapes.length; i++) {
            if (shapes[i] == this.shape) {
                currentIndex = i;
                break;
            }
        }
        this.shape = shapes[(currentIndex + 1) % shapes.length];
        setChanged();
        syncToClient();
    }

    public void setGlazeColor(int color) {
        this.glazeColor = color;
        setChanged();
        syncToClient();
    }

    // 状態遷移メソッド
    public void finishShaping() {
        if (state == PotteryState.SHAPING) {
            state = PotteryState.DRYING;
            progressTicks = 0;
            setChanged();
            syncToClient();
        }
    }

    public void startFiring() {
        if (state == PotteryState.GLAZEABLE) {
            state = PotteryState.FIRING;
            progressTicks = 0;
            firingTime = 0; // 焼成時間リセット
            setChanged();
            syncToClient();
        }
    }

    // ビジュアルの更新
    private void updateVisualHeight() {
        if (level != null && !level.isClientSide) {
            BlockState currentState = level.getBlockState(worldPosition);
            level.setBlock(worldPosition,
                    currentState.setValue(PotteryOnWheelBlock.VISUAL_HEIGHT, height), 3);
        }
    }

    /**
     * 焼成中の陶器を取り出す（右クリック時）
     * 現在の焼成時間をNBTに保存
     */
    public ItemStack retrieveFiredPottery() {
        if (state != PotteryState.FIRING) {
            return ItemStack.EMPTY;
        }

        ItemStack pottery = new ItemStack(ModItems.FIRED_POTTERY.get());
        CompoundTag tag = new CompoundTag();
        tag.putInt("Height", height);
        tag.putInt("Diameter", diameter);
        tag.putInt("WallThickness", wallThickness);
        tag.putInt("MouthWidth", mouthWidth);
        tag.putString("Shape", shape.name());
        tag.putInt("GlazeColor", glazeColor);
        tag.putInt("FiringTime", firingTime);
        pottery.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));

        return pottery;
    }

    /**
     * 陶器アイテムを作成（完成状態でない場合の予備）
     */
    public ItemStack createPotteryItem() {
        ItemStack pottery = new ItemStack(ModItems.UNFIRED_POTTERY.get());
        CompoundTag tag = new CompoundTag();
        tag.putInt("Height", height);
        tag.putInt("Diameter", diameter);
        tag.putInt("WallThickness", wallThickness);
        tag.putInt("MouthWidth", mouthWidth);
        tag.putString("Shape", shape.name());
        tag.putInt("GlazeColor", glazeColor);
        return pottery;
    }

    // Getters
    public PotteryState getState() { return state; }
    public int getProgressTicks() { return progressTicks; }
    public int getFiringTime() { return firingTime; }
    public int getHeight() { return height; }
    public int getDiameter() { return diameter; }
    public int getWallThickness() { return wallThickness; }
    public int getMouthWidth() { return mouthWidth; }
    public PotteryShape getShape() { return shape; }
    public int getGlazeColor() { return glazeColor; }

    public float getProgressPercent() {
        if (state.getDuration() == 0) return 0;
        return (float) progressTicks / state.getDuration();
    }

    // NBT保存/読込
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putString("State", state.name());
        tag.putInt("ProgressTicks", progressTicks);
        tag.putInt("FiringTime", firingTime);
        tag.putInt("Height", height);
        tag.putInt("Diameter", diameter);
        tag.putInt("WallThickness", wallThickness);
        tag.putInt("MouthWidth", mouthWidth);
        tag.putString("Shape", shape.name());
        tag.putInt("GlazeColor", glazeColor);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        state = PotteryState.valueOf(tag.getString("State"));
        progressTicks = tag.getInt("ProgressTicks");
        firingTime = tag.getInt("FiringTime");
        height = tag.getInt("Height");
        diameter = tag.getInt("Diameter");
        wallThickness = tag.getInt("WallThickness");
        mouthWidth = tag.getInt("MouthWidth");
        shape = PotteryShape.valueOf(tag.getString("Shape"));
        glazeColor = tag.getInt("GlazeColor");
    }

    // クライアント同期
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, registries);
        return tag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    private void syncToClient() {
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }
}
