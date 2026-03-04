package net.pochi.pochimod.ferm;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.item.food.FoodQualityHelper;
import net.pochi.pochimod.nutrition.FoodNutritionData;
import net.pochi.pochimod.nutrition.FoodNutritionHelper;
import net.pochi.pochimod.nutrition.FoodNutritionRegistry;
import net.pochi.pochimod.nutrition.NutritionType;

import java.util.List;

@EventBusSubscriber(modid = PochiMod.MOD_ID)
public class CropHarvestEventHandler {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Level level = (Level) event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);

        // 作物ブロックか判定（バニラ + 自作作物に対応）
        if (!(state.getBlock() instanceof CropBlock crop)) return;
        if (!crop.isMaxAge(state)) return; // 完熟のみ対象

        // 土壌（1ブロック下）のN/P/K取得
        BlockPos farmlandPos = pos.below();
        float n = SoilNutrientHelper.get(level, farmlandPos, SoilNutrientHelper.KEY_NITROGEN);
        float p = SoilNutrientHelper.get(level, farmlandPos, SoilNutrientHelper.KEY_PHOSPHORUS);
        float k = SoilNutrientHelper.get(level, farmlandPos, SoilNutrientHelper.KEY_POTASSIUM);

        // 作物ごとの重みを取得（レジストリから、なければ均等）
        CropNutrientWeight weight = CropNutrientRegistry.getWeight(state.getBlock());
        float quality = SoilNutrientHelper.calcQuality(n, p, k,
                weight.wN(), weight.wP(), weight.wK());

        // ドロップアイテムに品質NBTを付与
        event.setCanceled(true); // デフォルトドロップをキャンセル
        List<ItemStack> drops = Block.getDrops(state, (ServerLevel) level, pos,
                null, event.getPlayer(), event.getPlayer().getMainHandItem());

        PochiMod.LOGGER.info("[Harvest] ドロップ数={}", drops.size());
        for (ItemStack drop : drops) {
            // isEdible・FoodNutritionRegistry・ホワイトリストの3段階で判定
            boolean shouldApply =
                    drop.has(net.minecraft.core.component.DataComponents.FOOD) ||
                            FoodNutritionRegistry.getNutritionData(drop.getItem()).isPresent() ||
                            CropNutrientRegistry.shouldApplyQuality(drop.getItem());

            if (shouldApply) {
                // 品質係数をNBTに付与した後、栄養値も付与
                if (shouldApply) {
                    // 品質係数付与の直後
                    FoodQualityHelper.setQuality(drop, quality);  // ← これはある

                    // 収穫時に追加
                    FoodNutritionRegistry.getNutritionData(drop.getItem()).ifPresent(baseData -> {
                        PochiMod.LOGGER.info("[Registry] C={} P={} L={} V={} M={} H={}",
                                baseData.getNutrition(NutritionType.CARBOHYDRATE),
                                baseData.getNutrition(NutritionType.PROTEIN),
                                baseData.getNutrition(NutritionType.LIPID),
                                baseData.getNutrition(NutritionType.VITAMIN),
                                baseData.getNutrition(NutritionType.MINERAL),
                                baseData.getHydration());

                        FoodNutritionData scaledData = baseData.scaled(quality);

                        PochiMod.LOGGER.info("[Scaled] C={} P={} L={} V={} M={} H={}",
                                scaledData.getNutrition(NutritionType.CARBOHYDRATE),
                                scaledData.getNutrition(NutritionType.PROTEIN),
                                scaledData.getNutrition(NutritionType.LIPID),
                                scaledData.getNutrition(NutritionType.VITAMIN),
                                scaledData.getNutrition(NutritionType.MINERAL),
                                scaledData.getHydration());

                        FoodNutritionHelper.setNutrition(drop, scaledData);
                    });
                }
            }

            level.addFreshEntity(new ItemEntity(level,
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, drop));
        }

        // 収穫後の土壌劣化
        depleteSoil(level, farmlandPos, weight);

        level.destroyBlock(pos, false);
    }

    private static void depleteSoil(Level level, BlockPos pos, CropNutrientWeight weight) {
        // 作物が消費した栄養素を減少（重みに比例）
        float deplete = 0.1f;
        adjustNutrient(level, pos, SoilNutrientHelper.KEY_NITROGEN,   -deplete * weight.wN());
        adjustNutrient(level, pos, SoilNutrientHelper.KEY_PHOSPHORUS, -deplete * weight.wP());
        adjustNutrient(level, pos, SoilNutrientHelper.KEY_POTASSIUM,  -deplete * weight.wK());
    }

    private static void adjustNutrient(Level level, BlockPos pos, String key, float delta) {
        float current = SoilNutrientHelper.get(level, pos, key);
        SoilNutrientHelper.set(level, pos, key, current + delta);
    }
}
