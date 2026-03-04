package net.pochi.pochimod.block.custom;


import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.pochi.pochimod.block.entity.custom.PotteryOnWheelBlockEntity;
import net.pochi.pochimod.pottery.PotteryState;
import org.jetbrains.annotations.Nullable;

public class PotteryOnWheelBlock extends Block implements EntityBlock {

    public static final IntegerProperty VISUAL_HEIGHT = IntegerProperty.create("visual_height", 1, 10);

    public PotteryOnWheelBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(VISUAL_HEIGHT, 5));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(VISUAL_HEIGHT);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int height = state.getValue(VISUAL_HEIGHT);
        return Block.box(2, 0, 2, 14, height * 1.6, 14);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack heldItem, BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return ItemInteractionResult.SUCCESS;

        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof PotteryOnWheelBlockEntity potteryBE)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        PotteryState currentState = potteryBE.getState();

        // 焼成中の場合：素手で右クリックすると取得
        if (currentState == PotteryState.FIRING && heldItem.isEmpty()) {
            ItemStack firedPottery = potteryBE.retrieveFiredPottery();

            if (!firedPottery.isEmpty()) {
                // プレイヤーに渡す
                player.addItem(firedPottery);

                // 焼成時間を表示
                CustomData cd = firedPottery.get(DataComponents.CUSTOM_DATA);
                int firingTime = cd != null ? cd.copyTag().getInt("FiringTime") : 0;
                float seconds = firingTime / 20f;

                player.displayClientMessage(
                        Component.translatable("message.yourmod.pottery.retrieved",
                                        String.format("%.1f", seconds))
                                .withStyle(ChatFormatting.GOLD),
                        true
                );

                // ブロックを削除
                level.removeBlock(pos, false);
                level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 1.0F, 1.0F);

                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        // スニーク時：詳細情報を表示
        if (player.isCrouching() && heldItem.isEmpty()) {
            displayDetailedInfo(player, potteryBE);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        // 成形中のみツールで形状変更可能
        if (currentState == PotteryState.SHAPING) {
            if (heldItem.getItem() instanceof SwordItem) {
                potteryBE.adjustHeight(player.isCrouching() ? -1 : 1);
                playShapingSound(level, pos);
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            } else if (heldItem.getItem() instanceof ShovelItem) {
                potteryBE.adjustDiameter(player.isCrouching() ? -1 : 1);
                playShapingSound(level, pos);
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            } else if (heldItem.getItem() instanceof PickaxeItem) {
                potteryBE.adjustThickness(player.isCrouching() ? -1 : 1);
                playShapingSound(level, pos);
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            } else if (heldItem.getItem() instanceof AxeItem) {
                potteryBE.adjustMouth(player.isCrouching() ? -1 : 1);
                playShapingSound(level, pos);
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            } else if (heldItem.getItem() instanceof HoeItem) {
                potteryBE.cycleShape();
                playShapingSound(level, pos);
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            } else if (heldItem.isEmpty()) {
                potteryBE.finishShaping();
                level.playSound(null, pos, SoundEvents.MUD_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                player.displayClientMessage(
                        Component.translatable("message.yourmod.pottery.drying_started"),
                        true
                );
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        // 釉薬塗布可能状態で染料を使用
        if (currentState == PotteryState.GLAZEABLE) {
            if (heldItem.getItem() instanceof DyeItem dyeItem) {
                int color = dyeItem.getDyeColor().getFireworkColor();
                potteryBE.setGlazeColor(color);
                level.playSound(null, pos, SoundEvents.HONEY_BLOCK_PLACE, SoundSource.BLOCKS, 1.0F, 1.2F);
                if (!player.getAbilities().instabuild) {
                    heldItem.shrink(1);
                }
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            } else if (heldItem.isEmpty()) {
                potteryBE.startFiring();
                player.displayClientMessage(
                        Component.translatable("message.yourmod.pottery.firing_started"),
                        true
                );
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    private void playShapingSound(Level level, BlockPos pos) {
        level.playSound(null, pos, SoundEvents.GRAVEL_HIT, SoundSource.BLOCKS, 0.5F, 1.5F);
    }

    private void displayDetailedInfo(Player player, PotteryOnWheelBlockEntity be) {
        player.displayClientMessage(
                Component.literal("=== Pottery Details ===").withStyle(ChatFormatting.GOLD),
                false
        );
        player.displayClientMessage(
                Component.literal("State: " + be.getState().getName()),
                false
        );
        player.displayClientMessage(
                Component.literal(String.format("Height: %d, Diameter: %d, Thickness: %d, Mouth: %d",
                        be.getHeight(), be.getDiameter(), be.getWallThickness(), be.getMouthWidth())),
                false
        );
        player.displayClientMessage(
                Component.literal("Shape: " + be.getShape().getName()),
                false
        );

        if (be.getState() == PotteryState.FIRING) {
            float seconds = be.getFiringTime() / 20f;
            player.displayClientMessage(
                    Component.literal(String.format("Firing Time: %.1fs (%d ticks)", seconds, be.getFiringTime()))
                            .withStyle(ChatFormatting.AQUA),
                    false
            );
        }

        if (be.getGlazeColor() != 0xFFFFFF) {
            player.displayClientMessage(
                    Component.literal(String.format("Glaze Color: #%06X", be.getGlazeColor())),
                    false
            );
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PotteryOnWheelBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
                                                                  BlockEntityType<T> type) {
        return level.isClientSide ? null :
                (lvl, pos, st, be) -> {
                    if (be instanceof PotteryOnWheelBlockEntity potteryBE) {
                        PotteryOnWheelBlockEntity.tick(lvl, pos, st, potteryBE);
                    }
                };
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof PotteryOnWheelBlockEntity) {
                // ドロップ処理は手動回収のみ
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }
}