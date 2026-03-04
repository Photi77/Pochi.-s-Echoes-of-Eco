package net.pochi.pochimod.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.pochi.pochimod.block.ModBlocks;
import net.pochi.pochimod.block.custom.cauldrons.ModCauldronBlock;
import net.pochi.pochimod.item.ModItems;

import java.util.Map;

public interface ModCauldronInteraction extends CauldronInteraction {

    CauldronInteraction.InteractionMap CLEAN_WATER = CauldronInteraction.newInteractionMap("pochimod_clean_water");
    CauldronInteraction.InteractionMap WHISKEY = CauldronInteraction.newInteractionMap("pochimod_whiskey");
    CauldronInteraction.InteractionMap ROW_WHISKEY = CauldronInteraction.newInteractionMap("pochimod_row_whiskey");
    CauldronInteraction.InteractionMap MAPLE = CauldronInteraction.newInteractionMap("pochimod_maple");
    CauldronInteraction.InteractionMap SAKE = CauldronInteraction.newInteractionMap("pochimod_sake");
    CauldronInteraction.InteractionMap WINE = CauldronInteraction.newInteractionMap("pochimod_wine");
    CauldronInteraction.InteractionMap WHITE_WINE = CauldronInteraction.newInteractionMap("pochimod_white_wine");

    CauldronInteraction.InteractionMap WHITE_LIQUEUR = CauldronInteraction.newInteractionMap("pochimod_white_liqueur");

    CauldronInteraction.InteractionMap HABU_LIQUEUR = CauldronInteraction.newInteractionMap("pochimod_habu_liqueur");
    CauldronInteraction.InteractionMap R_MASH = CauldronInteraction.newInteractionMap("pochimod_r_mash");
    CauldronInteraction.InteractionMap R_RICE = CauldronInteraction.newInteractionMap("pochimod_r_rice");
    CauldronInteraction.InteractionMap R_YEAST = CauldronInteraction.newInteractionMap("pochimod_r_yeast");
    CauldronInteraction.InteractionMap P_MASH = CauldronInteraction.newInteractionMap("pochimod_p_mash");
    CauldronInteraction.InteractionMap J_MALT = CauldronInteraction.newInteractionMap("pochimod_j_malt");
    CauldronInteraction.InteractionMap P_RICE = CauldronInteraction.newInteractionMap("pochimod_p_rice");
    CauldronInteraction.InteractionMap P_YEAST = CauldronInteraction.newInteractionMap("pochimod_p_yeast");
    CauldronInteraction.InteractionMap P_MASH_RICE = CauldronInteraction.newInteractionMap("pochimod_p_mash_rice");

    CauldronInteraction.InteractionMap GRAPE = CauldronInteraction.newInteractionMap("pochimod_grape");
    CauldronInteraction.InteractionMap GRAPE_JUICE = CauldronInteraction.newInteractionMap("pochimod_grape_juice");
    CauldronInteraction.InteractionMap WHITE_GRAPE_JUICE = CauldronInteraction.newInteractionMap("pochimod_white_grape_juice");
    CauldronInteraction.InteractionMap APPLE = CauldronInteraction.newInteractionMap("pochimod_apple");
    CauldronInteraction.InteractionMap APPLE_JUICE = CauldronInteraction.newInteractionMap("pochimod_apple_juice");
    CauldronInteraction.InteractionMap APPLE_LIQUEUR = CauldronInteraction.newInteractionMap("pochimod_apple_liqueur");
    CauldronInteraction.InteractionMap LEMON = CauldronInteraction.newInteractionMap("pochimod_lemon");
    CauldronInteraction.InteractionMap LEMON_JUICE = CauldronInteraction.newInteractionMap("pochimod_lemon_juice");
    CauldronInteraction.InteractionMap LEMON_LIQUEUR = CauldronInteraction.newInteractionMap("pochimod_lemon_liqueur");
    CauldronInteraction.InteractionMap PEACH = CauldronInteraction.newInteractionMap("pochimod_peach");
    CauldronInteraction.InteractionMap PEACH_JUICE = CauldronInteraction.newInteractionMap("pochimod_peach_juice");
    CauldronInteraction.InteractionMap PEACH_LIQUEUR = CauldronInteraction.newInteractionMap("pochimod_peach_liqueur");
    CauldronInteraction.InteractionMap PLUM = CauldronInteraction.newInteractionMap("pochimod_plum");
    CauldronInteraction.InteractionMap PLUM_JUICE = CauldronInteraction.newInteractionMap("pochimod_plum_juice");
    CauldronInteraction.InteractionMap PLUM_LIQUEUR = CauldronInteraction.newInteractionMap("pochimod_plum_liqueur");
    CauldronInteraction.InteractionMap BANANA = CauldronInteraction.newInteractionMap("pochimod_banana");
    CauldronInteraction.InteractionMap BANANA_JUICE = CauldronInteraction.newInteractionMap("pochimod_banana_juice");
    CauldronInteraction.InteractionMap ALMOND = CauldronInteraction.newInteractionMap("pochimod_almond");
    CauldronInteraction.InteractionMap ALMOND_JUICE = CauldronInteraction.newInteractionMap("pochimod_almond_juice");
    CauldronInteraction.InteractionMap COCONUT = CauldronInteraction.newInteractionMap("pochimod_coconut");
    CauldronInteraction.InteractionMap COCONUT_JUICE = CauldronInteraction.newInteractionMap("pochimod_coconut_juice");
    CauldronInteraction.InteractionMap COFFEE = CauldronInteraction.newInteractionMap("pochimod_coffee");

    CauldronInteraction.InteractionMap MUSK_COFFEE = CauldronInteraction.newInteractionMap("pochimod_musk_coffee");

    CauldronInteraction FILL_CLEAN = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return addFruits(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_,
                ModBlocks.CLEAN_WATER_CAULDRON.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(4)), SoundEvents.BUCKET_EMPTY);
    };

    CauldronInteraction FILL_WISKEY = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return addFruits(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_,
                ModBlocks.WHISKEY_CAULDRON.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(4)), SoundEvents.BUCKET_EMPTY);
    };

    CauldronInteraction FILL_MAPLE = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return addFruits(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_,
                ModBlocks.MAPLE_CAULDRON.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(4)), SoundEvents.BUCKET_EMPTY);
    };

    CauldronInteraction FILL_SAKE = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return addFruits(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_,
                ModBlocks.SAKE_CAULDRON.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(4)), SoundEvents.BUCKET_EMPTY);
    };

    CauldronInteraction FILL_WINE = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return addFruits(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_,
                ModBlocks.WINE_CAULDRON.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(4)), SoundEvents.BUCKET_EMPTY);
    };

    CauldronInteraction FILL_WHITE_WINE = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return addFruits(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_,
                ModBlocks.WHITE_WINE_CAULDRON.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(4)), SoundEvents.BUCKET_EMPTY);
    };

    CauldronInteraction FILL_R_RICE = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return addFruits(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_,
                ModBlocks.RICE_CAULDRON_ROW.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(4)), SoundEvents.BUCKET_EMPTY);
    };

    CauldronInteraction FILL_R_MASH = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return addFruits(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_,
                ModBlocks.RICE_CAULDRON_PROCESSED.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(4)), SoundEvents.BUCKET_EMPTY);
    };

    CauldronInteraction FILL_R_YEAST = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return addFruits(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_,
                ModBlocks.YEAST_STARTER_CAULDRON_ROW.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(4)), SoundEvents.BUCKET_EMPTY);
    };

    CauldronInteraction FILL_P_RICE = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return addFruits(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_,
                ModBlocks.RICE_CAULDRON_PROCESSED.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(4)), SoundEvents.BUCKET_EMPTY);
    };
    CauldronInteraction FILL_J_MALT = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return addFruits(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_,
                ModBlocks.J_MALT_CAULDRON.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(4)), SoundEvents.BUCKET_EMPTY);
    };

    CauldronInteraction FILL_P_MASH = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return addFruits(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_,
                ModBlocks.MASH_CAULDRON_PROCESSED.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(4)), SoundEvents.BUCKET_EMPTY);
    };

    CauldronInteraction FILL_P_YEAST = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return addFruits(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_,
                ModBlocks.YEAST_STARTER_CAULDRON_PROCESSED.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(4)), SoundEvents.BUCKET_EMPTY);
    };

    CauldronInteraction FILL_MASH_RICE = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return addFruits(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_,
                ModBlocks.MASH_RICE_CAULDRON_PROCESSED.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(4)), SoundEvents.BUCKET_EMPTY);
    };

    CauldronInteraction FILL_GRAPE = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return addFruits(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_,
                ModBlocks.GRAPE_CAULDRON.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(1)), SoundEvents.BUCKET_EMPTY);
    };

    CauldronInteraction FILL_APPLE = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return addFruits(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_,
                ModBlocks.APPLE_CAULDRON.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(1)), SoundEvents.BUCKET_EMPTY);
    };

    CauldronInteraction FILL_LEMON = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return addFruits(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_,
                ModBlocks.LEMON_CAULDRON.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(1)), SoundEvents.BUCKET_EMPTY);
    };

    CauldronInteraction FILL_PEACH = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return addFruits(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_,
                ModBlocks.PEACH_CAULDRON.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(1)), SoundEvents.BUCKET_EMPTY);
    };

    CauldronInteraction FILL_PLUM = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return addFruits(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_,
                ModBlocks.PLUM_CAULDRON.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(1)), SoundEvents.BUCKET_EMPTY);
    };

    CauldronInteraction FILL_BANANA = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return addFruits(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_,
                ModBlocks.BANANA_CAULDRON.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(1)), SoundEvents.BUCKET_EMPTY);
    };

    CauldronInteraction FILL_ALMOND = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return addFruits(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_,
                ModBlocks.ALMON_CAULDRON.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(1)), SoundEvents.BUCKET_EMPTY);
    };

    CauldronInteraction FILL_COCONUT = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return addFruits(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_,
                ModBlocks.COCONUT_CAULDRON.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(1)), SoundEvents.BUCKET_EMPTY);
    };


    @Override
    ItemInteractionResult interact(BlockState p_175711_, Level p_175712_, BlockPos p_175713_, Player p_175714_, InteractionHand p_175715_, ItemStack p_175716_);

    static void bootstrap() {
        addDefaultInteractions(EMPTY.map());
        //水を濾過する
        WATER.map().put(ModItems.BAKED_ALUM.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(LayeredCauldronBlock.LEVEL) == 3) {
                if (!p_175705_.isClientSide) {
                    p_175709_.shrink(1);
                    p_175705_.setBlock(p_175706_,ModBlocks.CLEAN_WATER_CAULDRON.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(12)),11);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

        //濾過水
        CLEAN_WATER.map().put(Items.BUCKET, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.FILTERED_WATER_BUCKET.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevel(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });
        CLEAN_WATER.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.FILTERED_WATER.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });
        CLEAN_WATER.map().put(ModItems.FILTERED_WATER_BUCKET.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) != 12) {
                if (!p_175705_.isClientSide) {
                    p_175707_.setItemInHand(p_175708_, ItemUtils.createFilledResult(p_175709_, p_175707_, new ItemStack(Items.BUCKET)));
                    p_175707_.awardStat(Stats.USE_CAULDRON);
                    p_175707_.awardStat(Stats.ITEM_USED.get(p_175709_.getItem()));
                    ModCauldronBlock.addFillLevel(p_175704_, p_175705_, p_175706_);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

        //ウィスキー
        WHISKEY.map().put(Items.BUCKET, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.WHISKEY_BUCKET.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevel(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });
        WHISKEY.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.BOTTLE_OF_WHISKEY.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });
        WHISKEY.map().put(ModItems.WHISKEY_BUCKET.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) != 12) {
                if (!p_175705_.isClientSide) {
                    p_175707_.setItemInHand(p_175708_, ItemUtils.createFilledResult(p_175709_, p_175707_, new ItemStack(Items.BUCKET)));
                    p_175707_.awardStat(Stats.USE_CAULDRON);
                    p_175707_.awardStat(Stats.ITEM_USED.get(p_175709_.getItem()));
                    ModCauldronBlock.addFillLevel(p_175704_, p_175705_, p_175706_);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

        //メープル
        MAPLE.map().put(Items.BUCKET, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.MAPLE_BUCKET.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevel(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });
        MAPLE.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.MAPLE_SYRUP.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });
        MAPLE.map().put(ModItems.MAPLE_BUCKET.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) != 12) {
                if (!p_175705_.isClientSide) {
                    p_175707_.setItemInHand(p_175708_, ItemUtils.createFilledResult(p_175709_, p_175707_, new ItemStack(Items.BUCKET)));
                    p_175707_.awardStat(Stats.USE_CAULDRON);
                    p_175707_.awardStat(Stats.ITEM_USED.get(p_175709_.getItem()));
                    ModCauldronBlock.addFillLevel(p_175704_, p_175705_, p_175706_);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

        //酒作り過程
        //濾過水に米を入れます、濾過水12に対して米1で大釜を米蒸す前の1に変換
        CLEAN_WATER.map().put(ModItems.RICE.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) == 12) {
                if (!p_175705_.isClientSide) {
                    p_175705_.setBlock(p_175706_,ModBlocks.RICE_CAULDRON_ROW.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(1)),11);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });
        //米1の大釜に米でプラスして12まで
        R_RICE.map().put(ModItems.RICE.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) != 12) {
                if (!p_175705_.isClientSide) {
                    ModCauldronBlock.addFillLevelBottle(p_175704_, p_175705_, p_175706_);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });
        //蒸した後の窯にバケツで蒸米バケツ
        P_RICE.map().put(Items.BUCKET, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.RICE_BUCKET.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevel(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }
            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });

        P_RICE.map().put(ModItems.RICE_BUCKET.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) != 12) {
                if (!p_175705_.isClientSide) {
                    ModCauldronBlock.addFillLevel(p_175704_, p_175705_, p_175706_);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

        J_MALT.map().put(ModItems.J_MALT_BUCKET.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) != 12) {
                if (!p_175705_.isClientSide) {
                    ModCauldronBlock.addFillLevel(p_175704_, p_175705_, p_175706_);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

        //おにぎり用
        P_RICE.map().put(Items.BOWL, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.RICE.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }
            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });
        //麹か蒸米の大釜にもう一つを追加すると麹と蒸米大釜に変化、その後、水を追加
        J_MALT.map().put(ModItems.RICE_BUCKET.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) == 4) {
                if (!p_175705_.isClientSide) {
                    p_175707_.setItemInHand(p_175708_, ItemUtils.createFilledResult(p_175709_, p_175707_, new ItemStack(Items.BUCKET)));
                    p_175705_.setBlock(p_175706_,ModBlocks.MASH_RICE_CAULDRON_PROCESSED.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(8)),11);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

        P_RICE.map().put(ModItems.J_MALT_BUCKET.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) == 4) {
                if (!p_175705_.isClientSide) {
                    p_175707_.setItemInHand(p_175708_, ItemUtils.createFilledResult(p_175709_, p_175707_, new ItemStack(Items.BUCKET)));
                    p_175705_.setBlock(p_175706_,ModBlocks.MASH_RICE_CAULDRON_PROCESSED.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(8)),11);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

        P_MASH_RICE.map().put(ModItems.FILTERED_WATER_BUCKET.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) == 8) {
                if (!p_175705_.isClientSide) {
                    p_175707_.setItemInHand(p_175708_, ItemUtils.createFilledResult(p_175709_, p_175707_, new ItemStack(Items.BUCKET)));
                    p_175705_.setBlock(p_175706_,ModBlocks.MASH_RICE_CAULDRON_PROCESSED.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(12)),11);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

        P_YEAST.map().put(Items.BUCKET, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.YEAST_BUCKET.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevel(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });

        P_MASH.map().put(Items.BUCKET, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.MASH_BUCKET.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevel(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });

        P_MASH.map().put(ModItems.MASH_BUCKET.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) != 12) {
                if (!p_175705_.isClientSide) {
                    ModCauldronBlock.addFillLevel(p_175704_, p_175705_, p_175706_);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

        //酵母を入れて酒母
        P_MASH_RICE.map().put(ModItems.JAPANESE_YEAST.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) >= 8) {
                if (!p_175705_.isClientSide) {
                    p_175707_.setItemInHand(p_175708_, ItemUtils.createFilledResult(p_175709_, p_175707_, new ItemStack(Items.BUCKET)));
                    p_175705_.setBlock(p_175706_,ModBlocks.YEAST_STARTER_CAULDRON_ROW.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(12)),11);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });
        //酒母を入れてもろみ
        P_MASH_RICE.map().put(ModItems.YEAST_BUCKET.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) >= 8) {
                if (!p_175705_.isClientSide) {
                    p_175707_.setItemInHand(p_175708_, ItemUtils.createFilledResult(p_175709_, p_175707_, new ItemStack(Items.BUCKET)));
                    p_175705_.setBlock(p_175706_,ModBlocks.MASH_CAULDRON_ROW.get().defaultBlockState().setValue(ModCauldronBlock.LEVEL_CAULDRON, Integer.valueOf(12)),11);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

        //酒
        SAKE.map().put(Items.BUCKET, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.SAKE_BUCKET.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevel(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });
        SAKE.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.BOTTLE_OF_SAKE.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });
        SAKE.map().put(ModItems.SAKE_BUCKET.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) != 12) {
                if (!p_175705_.isClientSide) {
                    p_175707_.setItemInHand(p_175708_, ItemUtils.createFilledResult(p_175709_, p_175707_, new ItemStack(Items.BUCKET)));
                    p_175707_.awardStat(Stats.USE_CAULDRON);
                    p_175707_.awardStat(Stats.ITEM_USED.get(p_175709_.getItem()));
                    ModCauldronBlock.addFillLevel(p_175704_, p_175705_, p_175706_);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

        //ワイン
        WINE.map().put(Items.BUCKET, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.WINE_BUCKET.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevel(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });
        WINE.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.BOTTLE_OF_RED_WINE.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });
        WINE.map().put(ModItems.WINE_BUCKET.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) != 12) {
                if (!p_175705_.isClientSide) {
                    p_175707_.setItemInHand(p_175708_, ItemUtils.createFilledResult(p_175709_, p_175707_, new ItemStack(Items.BUCKET)));
                    p_175707_.awardStat(Stats.USE_CAULDRON);
                    p_175707_.awardStat(Stats.ITEM_USED.get(p_175709_.getItem()));
                    ModCauldronBlock.addFillLevel(p_175704_, p_175705_, p_175706_);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

        //白ワイン
        WHITE_WINE.map().put(Items.BUCKET, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.WHITE_WINE_BUCKET.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevel(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });
        WHITE_WINE.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.BOTTLE_OF_WHITE_WINE.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });
        WHITE_WINE.map().put(ModItems.WHITE_WINE_BUCKET.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) != 12) {
                if (!p_175705_.isClientSide) {
                    p_175707_.setItemInHand(p_175708_, ItemUtils.createFilledResult(p_175709_, p_175707_, new ItemStack(Items.BUCKET)));
                    p_175707_.awardStat(Stats.USE_CAULDRON);
                    p_175707_.awardStat(Stats.ITEM_USED.get(p_175709_.getItem()));
                    ModCauldronBlock.addFillLevel(p_175704_, p_175705_, p_175706_);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

        GRAPE.map().put(ModItems.GRAPE.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) != 12) {
                if (!p_175705_.isClientSide) {
                    p_175707_.awardStat(Stats.USE_CAULDRON);
                    p_175707_.awardStat(Stats.ITEM_USED.get(p_175709_.getItem()));
                    p_175709_.shrink(1);
                    ModCauldronBlock.addFillLevelBottle(p_175704_, p_175705_, p_175706_);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

        GRAPE_JUICE.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.GRAPE_JUICE.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });

        WHITE_GRAPE_JUICE.map().put(ModItems.GRAPE_JUICE.get(), (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.GRAPE_JUICE.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });


        WHITE_GRAPE_JUICE.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.GRAPE_JUICE.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });

        APPLE.map().put(Items.APPLE, (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) != 12) {
                if (!p_175705_.isClientSide) {
                    p_175707_.awardStat(Stats.USE_CAULDRON);
                    p_175707_.awardStat(Stats.ITEM_USED.get(p_175709_.getItem()));
                    p_175709_.shrink(1);
                    ModCauldronBlock.addFillLevelBottle(p_175704_, p_175705_, p_175706_);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

        APPLE_JUICE.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.APPLE_JUICE.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });

        LEMON.map().put(ModItems.LEMON.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) != 12) {
                if (!p_175705_.isClientSide) {
                    p_175707_.awardStat(Stats.USE_CAULDRON);
                    p_175707_.awardStat(Stats.ITEM_USED.get(p_175709_.getItem()));
                    p_175709_.shrink(1);
                    ModCauldronBlock.addFillLevelBottle(p_175704_, p_175705_, p_175706_);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

        LEMON_JUICE.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.LEMON_JUICE.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });

        PEACH.map().put(ModItems.PEACH.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) != 12) {
                if (!p_175705_.isClientSide) {
                    p_175707_.awardStat(Stats.USE_CAULDRON);
                    p_175707_.awardStat(Stats.ITEM_USED.get(p_175709_.getItem()));
                    p_175709_.shrink(1);
                    ModCauldronBlock.addFillLevelBottle(p_175704_, p_175705_, p_175706_);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

        PEACH_JUICE.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.PEACH_JUICE.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });

        PLUM.map().put(ModItems.PLUM.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) != 12) {
                if (!p_175705_.isClientSide) {
                    p_175707_.awardStat(Stats.USE_CAULDRON);
                    p_175707_.awardStat(Stats.ITEM_USED.get(p_175709_.getItem()));
                    p_175709_.shrink(1);
                    ModCauldronBlock.addFillLevelBottle(p_175704_, p_175705_, p_175706_);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

        PLUM_JUICE.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.PLUM_JUICE.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });

        BANANA.map().put(ModItems.BANANA.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) != 12) {
                if (!p_175705_.isClientSide) {
                    p_175707_.awardStat(Stats.USE_CAULDRON);
                    p_175707_.awardStat(Stats.ITEM_USED.get(p_175709_.getItem()));
                    p_175709_.shrink(1);
                    ModCauldronBlock.addFillLevelBottle(p_175704_, p_175705_, p_175706_);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

        BANANA_JUICE.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.BANANA_JUICE.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });

        ALMOND.map().put(ModItems.ALMOND.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) != 12) {
                if (!p_175705_.isClientSide) {
                    p_175707_.awardStat(Stats.USE_CAULDRON);
                    p_175707_.awardStat(Stats.ITEM_USED.get(p_175709_.getItem()));
                    p_175709_.shrink(1);
                    ModCauldronBlock.addFillLevelBottle(p_175704_, p_175705_, p_175706_);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

        ALMOND_JUICE.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.APPLE_JUICE.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });

        COCONUT.map().put(ModItems.COCONUT.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
            if (p_175704_.getValue(ModCauldronBlock.LEVEL_CAULDRON) != 12) {
                if (!p_175705_.isClientSide) {
                    p_175707_.awardStat(Stats.USE_CAULDRON);
                    p_175707_.awardStat(Stats.ITEM_USED.get(p_175709_.getItem()));
                    p_175709_.shrink(1);
                    ModCauldronBlock.addFillLevelBottle(p_175704_, p_175705_, p_175706_);
                    p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
                }
                return ItemInteractionResult.sidedSuccess(p_175705_.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

        COCONUT_JUICE.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.COCONUT_MILK.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });

        WHITE_LIQUEUR.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.BOTTLE_OF_WHITE_LIQUOR.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });

        HABU_LIQUEUR.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.HABU_LIQUEUR.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });

        COFFEE.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.COFFEE.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });

        MUSK_COFFEE.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.MUSK_COFFEE.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });

        APPLE_LIQUEUR.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.APPLE_LIQUEUR.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });

        PEACH_LIQUEUR.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.PEACH_LIQUEUR.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });

        PLUM_LIQUEUR.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.PLUM_LIQUEUR.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });

        LEMON_LIQUEUR.map().put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
            if (!p_175719_.isClientSide) {
                Item item = p_175723_.getItem();
                p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.LEMON_LIQUEUR.get())));
                p_175721_.awardStat(Stats.USE_CAULDRON);
                p_175721_.awardStat(Stats.ITEM_USED.get(item));
                ModCauldronBlock.lowerFillLevelBottle(p_175718_, p_175719_, p_175720_);
                p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
            }

            return ItemInteractionResult.sidedSuccess(p_175719_.isClientSide);
        });
    }

    static void addDefaultInteractions(Map<Item, CauldronInteraction> p_175648_) {
        p_175648_.put(ModItems.FILTERED_WATER_BUCKET.get(), FILL_CLEAN);
        p_175648_.put(ModItems.WHISKEY_BUCKET.get(), FILL_WISKEY);
        p_175648_.put(ModItems.MAPLE_BUCKET.get(), FILL_MAPLE);
        p_175648_.put(ModItems.J_MALT_BUCKET.get(), FILL_J_MALT);
        p_175648_.put(ModItems.RICE_BUCKET.get(), FILL_P_RICE);
        p_175648_.put(ModItems.SAKE_BUCKET.get(), FILL_SAKE);
        p_175648_.put(ModItems.WINE_BUCKET.get(), FILL_WINE);
        p_175648_.put(ModItems.WHITE_WINE_BUCKET.get(), FILL_WHITE_WINE);
        p_175648_.put(ModItems.MASH_BUCKET.get(), FILL_P_MASH);
        p_175648_.put(ModItems.GRAPE.get(), FILL_GRAPE);
        p_175648_.put(Items.APPLE, FILL_APPLE);
        p_175648_.put(ModItems.LEMON.get(), FILL_LEMON);
        p_175648_.put(ModItems.PEACH.get(), FILL_PEACH);
        p_175648_.put(ModItems.PLUM.get(), FILL_PLUM);
        p_175648_.put(ModItems.BANANA.get(), FILL_BANANA);
        p_175648_.put(ModItems.ALMOND.get(), FILL_ALMOND);
        p_175648_.put(ModItems.COCONUT.get(), FILL_COCONUT);
    }

    static ItemInteractionResult addFruits(Level p_175619_, BlockPos p_175620_, Player p_175621_, InteractionHand p_175622_, ItemStack p_175623_, BlockState p_175624_, SoundEvent p_175625_) {
        if (!p_175619_.isClientSide) {
            Item item = p_175623_.getItem();
            p_175621_.awardStat(Stats.FILL_CAULDRON);
            p_175621_.awardStat(Stats.ITEM_USED.get(item));
            p_175619_.setBlockAndUpdate(p_175620_, p_175624_);
            p_175619_.playSound((Player)null, p_175620_, p_175625_, SoundSource.BLOCKS, 1.0F, 1.0F);
            p_175619_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175620_);
        }

        return ItemInteractionResult.sidedSuccess(p_175619_.isClientSide);
    }

}
