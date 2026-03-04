package net.pochi.pochimod.block.custom.cauldrons;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.pochi.pochimod.block.ModBlocks;
import net.pochi.pochimod.item.ModItems;

import java.util.Random;

public class ModCauldronBlock extends AbstractCauldronBlock {
    @Override
    protected MapCodec<? extends ModCauldronBlock> codec() { throw new UnsupportedOperationException("ModCauldronBlock.codec"); }

    public static final IntegerProperty LEVEL_CAULDRON = IntegerProperty.create("level", 1, 12);
    public static final int MIN_FILL_LEVEL = 1;
    public static final int MAX_FILL_LEVEL = 3;
    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_CAULDRON;
    private static final int BASE_CONTENT_HEIGHT = 6;
    private static final double HEIGHT_PER_LEVEL = 3.0D;
    int fireTime = 0;
    int fermentation = 0;

    int press = 0;

    private final CauldronInteraction.InteractionMap interactions;
    Random random = new Random();

    public ModCauldronBlock(Properties p_151946_, CauldronInteraction.InteractionMap p_151947_) {
        super(p_151946_, p_151947_);
        this.interactions = p_151947_;
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL_CAULDRON, Integer.valueOf(12)));
    }

    @Override
    public boolean isFull(BlockState p_151984_) {
        return p_151984_.getValue(LEVEL_CAULDRON) == 12;
    }

    @Override
    protected double getContentHeight(BlockState p_151948_) {
        return (6.0D + (double)p_151948_.getValue(LEVEL_CAULDRON).intValue() * 3.0D) / 16.0D;
    }

    public void handlePrecipitation(BlockState p_153539_, Level p_153540_, BlockPos p_153541_, Biome.Precipitation p_153542_) {
        if (p_153539_.getValue(LEVEL_CAULDRON) != 12) {
            BlockState blockstate = p_153539_.cycle(LEVEL_CAULDRON);
            p_153540_.setBlockAndUpdate(p_153541_, blockstate);
            p_153540_.gameEvent(GameEvent.BLOCK_CHANGE, p_153541_, GameEvent.Context.of(blockstate));
        }


    }

    public void entityInside(BlockState p_153506_, Level p_153507_, BlockPos p_153508_, Entity p_153509_) {


        if (this.isEntityInsideContent(p_153506_, p_153508_, p_153509_)) {
            if(p_153506_.is(ModBlocks.GRAPE_CAULDRON.get())){
                p_153507_.setBlockAndUpdate(p_153508_, ModBlocks.GRAPE_JUICE_CAULDRON.get()
                        .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_153506_.getValue(LEVEL_CAULDRON))));
            }

            if(p_153506_.is(ModBlocks.APPLE_CAULDRON.get())){
                p_153507_.setBlockAndUpdate(p_153508_, ModBlocks.APPLE_JUICE_CAULDRON.get()
                        .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_153506_.getValue(LEVEL_CAULDRON))));
            }

            if(p_153506_.is(ModBlocks.LEMON_CAULDRON.get())){
                p_153507_.setBlockAndUpdate(p_153508_, ModBlocks.LEMON_JUICE_CAULDRON.get()
                        .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_153506_.getValue(LEVEL_CAULDRON))));
            }

            if(p_153506_.is(ModBlocks.PEACH_CAULDRON.get())){
                p_153507_.setBlockAndUpdate(p_153508_, ModBlocks.PEACH_JUICE_CAULDRON.get()
                        .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_153506_.getValue(LEVEL_CAULDRON))));
            }

            if(p_153506_.is(ModBlocks.PLUM_CAULDRON.get())){
                p_153507_.setBlockAndUpdate(p_153508_, ModBlocks.PLUM_JUICE_CAULDRON.get()
                        .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_153506_.getValue(LEVEL_CAULDRON))));
            }

            if(p_153506_.is(ModBlocks.BANANA_CAULDRON.get())){
                p_153507_.setBlockAndUpdate(p_153508_, ModBlocks.BANANA_JUICE_CAULDRON.get()
                        .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_153506_.getValue(LEVEL_CAULDRON))));
            }

            if(p_153506_.is(ModBlocks.ALMON_CAULDRON.get())){
                p_153507_.setBlockAndUpdate(p_153508_, ModBlocks.ALMON_JUICE_CAULDRON.get()
                        .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_153506_.getValue(LEVEL_CAULDRON))));
            }

            if(p_153506_.is(ModBlocks.COCONUT_CAULDRON.get())){
                p_153507_.setBlockAndUpdate(p_153508_, ModBlocks.COCONUT_JUICE_CAULDRON.get()
                        .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_153506_.getValue(LEVEL_CAULDRON))));
            }


            if(p_153506_.is(ModBlocks.CLEAN_WATER_CAULDRON.get())){
                if(p_153509_ instanceof ItemEntity entity) {
                    if(entity.getItem().is(ModItems.RICE.get())) {
                        p_153507_.setBlockAndUpdate(p_153508_, ModBlocks.MASH_RICE_CAULDRON_PROCESSED.get()
                                .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_153506_.getValue(LEVEL_CAULDRON))));
                        entity.discard();
                    }
                }
            }

            if(p_153506_.is(ModBlocks.MASH_RICE_CAULDRON_PROCESSED.get())){
                if(p_153509_ instanceof ItemEntity entity) {
                    if(entity.getItem().is(ModItems.JAPANESE_YEAST.get())) {
                        p_153507_.setBlockAndUpdate(p_153508_, ModBlocks.MASH_CAULDRON_PROCESSED.get()
                                .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_153506_.getValue(LEVEL_CAULDRON))));
                        entity.discard();
                    }
                }
            }

            if(p_153506_.is(ModBlocks.CLEAN_WATER_CAULDRON.get())){
                if(p_153509_ instanceof ItemEntity entity) {
                    if(entity.getItem().is(Items.WHEAT)) {
                        p_153507_.setBlockAndUpdate(p_153508_, ModBlocks.ROW_WHISKEY_CAULDRON.get()
                                .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_153506_.getValue(LEVEL_CAULDRON))));
                        entity.discard();
                    }
                }
            }

            if(p_153506_.is(ModBlocks.WHITE_LIQUEUR_CAULDRON.get())){
                if(p_153509_ instanceof ItemEntity entity) {
                    if(entity.getItem().is(ModItems.LEMON.get())) {
                        p_153507_.setBlockAndUpdate(p_153508_, ModBlocks.LEMON_LIQUEUR_CAULDRON.get()
                                .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_153506_.getValue(LEVEL_CAULDRON))));
                        entity.discard();
                    }
                }
            }

            if(p_153506_.is(ModBlocks.WHITE_LIQUEUR_CAULDRON.get())){
                if(p_153509_ instanceof ItemEntity entity) {
                    if(entity.getItem().is(ModItems.PEACH.get())) {
                        p_153507_.setBlockAndUpdate(p_153508_, ModBlocks.PEACH_LIQUEUR_CAULDRON.get()
                                .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_153506_.getValue(LEVEL_CAULDRON))));
                        entity.discard();
                    }
                }
            }

            if(p_153506_.is(ModBlocks.WHITE_LIQUEUR_CAULDRON.get())){
                if(p_153509_ instanceof ItemEntity entity) {
                    if(entity.getItem().is(ModItems.PLUM.get())) {
                        p_153507_.setBlockAndUpdate(p_153508_, ModBlocks.PLUM_LIQUEUR_CAULDRON.get()
                                .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_153506_.getValue(LEVEL_CAULDRON))));
                        entity.discard();
                    }
                }
            }

            if(p_153506_.is(ModBlocks.WHITE_LIQUEUR_CAULDRON.get())){
                if(p_153509_ instanceof ItemEntity entity) {
                    if(entity.getItem().is(Items.APPLE)) {
                        p_153507_.setBlockAndUpdate(p_153508_, ModBlocks.APPLE_LIQUEUR_CAULDRON.get()
                                .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_153506_.getValue(LEVEL_CAULDRON))));
                        entity.discard();
                    }
                }
            }
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemstack, BlockState p_151969_, Level p_151970_, BlockPos p_151971_, Player p_151972_, InteractionHand p_151973_, BlockHitResult p_151974_) {
        CauldronInteraction cauldroninteraction = this.interactions.map().get(itemstack.getItem());
        if(p_151969_.is(ModBlocks.CLEAN_WATER_CAULDRON.get())){
            if(itemstack.is(Items.WHEAT)){
                p_151970_.setBlockAndUpdate(p_151971_, ModBlocks.MASH_CAULDRON_PROCESSED.get()
                        .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_151969_.getValue(LEVEL_CAULDRON))));
            }

            if(itemstack.is(ModItems.COFFEE_BEANS.get())){
                p_151970_.setBlockAndUpdate(p_151971_, ModBlocks.COFFEE_CAULDRON.get()
                        .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_151969_.getValue(LEVEL_CAULDRON))));
            }

            if(itemstack.is(ModItems.MUSK_COFFEE_BEANS.get())){
                p_151970_.setBlockAndUpdate(p_151971_, ModBlocks.MUSK_COFFEE_CAULDRON.get()
                        .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_151969_.getValue(LEVEL_CAULDRON))));
            }
        }

        if(p_151969_.is(ModBlocks.WHITE_LIQUEUR_CAULDRON.get())){
            if(itemstack.is(ModItems.HABU.get())){
                p_151970_.setBlockAndUpdate(p_151971_, ModBlocks.HABU_LIQUEUR_CAULDRON.get()
                        .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_151969_.getValue(LEVEL_CAULDRON))));
            }
        }
        return cauldroninteraction.interact(p_151969_, p_151970_, p_151971_, p_151972_, p_151973_, itemstack);
    }

    public static void lowerFillLevel(BlockState p_153560_, Level p_153561_, BlockPos p_153562_) {
        int i = p_153560_.getValue(LEVEL_CAULDRON) - 4;
        BlockState blockstate = i == 0 ? Blocks.CAULDRON.defaultBlockState() : p_153560_.setValue(LEVEL_CAULDRON, Integer.valueOf(i));
        p_153561_.setBlockAndUpdate(p_153562_, blockstate);
        p_153561_.gameEvent(GameEvent.BLOCK_CHANGE, p_153562_, GameEvent.Context.of(blockstate));
    }

    public static void addFillLevel(BlockState p_153560_, Level p_153561_, BlockPos p_153562_) {
        int i = p_153560_.getValue(LEVEL_CAULDRON) + 4;
        BlockState blockstate = i == 0 ? Blocks.CAULDRON.defaultBlockState() : p_153560_.setValue(LEVEL_CAULDRON, Integer.valueOf(i));
        p_153561_.setBlockAndUpdate(p_153562_, blockstate);
        p_153561_.gameEvent(GameEvent.BLOCK_CHANGE, p_153562_, GameEvent.Context.of(blockstate));
    }

    public static void lowerFillLevelBottle(BlockState p_153560_, Level p_153561_, BlockPos p_153562_) {
        int i = p_153560_.getValue(LEVEL_CAULDRON) - 1;
        BlockState blockstate = i == 0 ? Blocks.CAULDRON.defaultBlockState() : p_153560_.setValue(LEVEL_CAULDRON, Integer.valueOf(i));
        p_153561_.setBlockAndUpdate(p_153562_, blockstate);
        p_153561_.gameEvent(GameEvent.BLOCK_CHANGE, p_153562_, GameEvent.Context.of(blockstate));
    }

    public static void addFillLevelBottle(BlockState p_153560_, Level p_153561_, BlockPos p_153562_) {
        int i = p_153560_.getValue(LEVEL_CAULDRON) + 1;
        BlockState blockstate = i == 0 ? Blocks.CAULDRON.defaultBlockState() : p_153560_.setValue(LEVEL_CAULDRON, Integer.valueOf(i));
        p_153561_.setBlockAndUpdate(p_153562_, blockstate);
        p_153561_.gameEvent(GameEvent.BLOCK_CHANGE, p_153562_, GameEvent.Context.of(blockstate));
    }

    public int getAnalogOutputSignal(BlockState p_153530_, Level p_153531_, BlockPos p_153532_) {
        return p_153530_.getValue(LEVEL_CAULDRON);
    }


    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_153549_) {
        p_153549_.add(LEVEL_CAULDRON);
    }

    @Override
    public boolean isRandomlyTicking(BlockState p_49921_) {
        return true;
    }

    @Override
    public void tick(BlockState p_220702_, ServerLevel p_220703_, BlockPos p_220704_, RandomSource p_220705_) {

        if (p_220703_.getBlockState(p_220704_.above()).is(Blocks.TINTED_GLASS)) {
            press++;
        }

        if (p_220703_.getBlockState(p_220704_.below()).is(Blocks.CAMPFIRE)) {
            fireTime++;
        }

        if (!p_220703_.getBlockState(p_220704_.below()).is(Blocks.CAMPFIRE)) {
            fermentation++;
        }

        if (p_220702_ == ModBlocks.RICE_CAULDRON_ROW.get().defaultBlockState()) {
            if (fireTime >= 1) {
                p_220703_.setBlockAndUpdate(p_220704_, ModBlocks.RICE_CAULDRON_PROCESSED.get()
                        .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_220702_.getValue(LEVEL_CAULDRON))));
            }
        }

        if (p_220702_ == ModBlocks.ROW_WHISKEY_CAULDRON.get().defaultBlockState()) {
            if (fireTime >= 1) {
                p_220703_.setBlockAndUpdate(p_220704_, ModBlocks.WHISKEY_CAULDRON.get()
                        .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_220702_.getValue(LEVEL_CAULDRON))));
            }
        }

        if (p_220702_ == ModBlocks.YEAST_STARTER_CAULDRON_ROW.get().defaultBlockState()) {
            if (fermentation >= 1) {
                p_220703_.setBlockAndUpdate(p_220704_, ModBlocks.YEAST_STARTER_CAULDRON_PROCESSED.get()
                        .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_220702_.getValue(LEVEL_CAULDRON))));
            }
        }

        if (p_220702_ == ModBlocks.MASH_CAULDRON_ROW.get().defaultBlockState()) {
            if (fermentation >= 1) {
                p_220703_.setBlockAndUpdate(p_220704_, ModBlocks.MASH_CAULDRON_PROCESSED.get()
                        .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_220702_.getValue(LEVEL_CAULDRON))));
            }
        }

        if (p_220702_ == ModBlocks.MASH_CAULDRON_PROCESSED.get().defaultBlockState()) {
            if (fermentation >= 1) {
                p_220703_.setBlockAndUpdate(p_220704_, ModBlocks.SAKE_CAULDRON.get()
                        .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_220702_.getValue(LEVEL_CAULDRON))));
            }

            if (fireTime >= 1) {
                p_220703_.setBlockAndUpdate(p_220704_, ModBlocks.WHITE_LIQUEUR_CAULDRON.get()
                        .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_220702_.getValue(LEVEL_CAULDRON))));
            }
        }

        if (p_220702_ == ModBlocks.GRAPE_JUICE_CAULDRON.get().defaultBlockState()) {
            if (fermentation >= 1) {
                p_220703_.setBlockAndUpdate(p_220704_, ModBlocks.WINE_CAULDRON.get()
                        .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_220702_.getValue(LEVEL_CAULDRON))));
            }
        }

        if (p_220702_ == ModBlocks.WHITE_GRAPE_JUICE_CAULDRON.get().defaultBlockState()) {
            if (fermentation >= 1) {
                p_220703_.setBlockAndUpdate(p_220704_, ModBlocks.WINE_CAULDRON.get()
                        .defaultBlockState().setValue(LEVEL_CAULDRON, Integer.valueOf(p_220702_.getValue(LEVEL_CAULDRON))));
            }
        }

    }
}
