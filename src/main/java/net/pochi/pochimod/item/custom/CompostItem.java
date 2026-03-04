package net.pochi.pochimod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.ferm.SoilData;
import net.pochi.pochimod.ferm.SoilDataCapability;
import net.pochi.pochimod.ferm.SoilNutrientHelper;
import net.pochi.pochimod.item.ModItems;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CompostItem extends Item {

    // アイテムごとの回復量定義
    public static final Map<Item, float[]> COMPOST_VALUES = new HashMap<>();

    static {
        // float[0]=N回復, float[1]=P回復, float[2]=K回復
        registerCompost(Items.BONE_MEAL,      0.15f, 0.20f, 0.05f); // P重視
        // 自作堆肥は全体的に均等回復
    }

    public static void registerCompost(Item item, float n, float p, float k) {
        COMPOST_VALUES.put(item, new float[]{n, p, k});
    }

    public CompostItem(Properties properties) {
        super(properties);
        // 自分自身を登録（遅延するためコンストラクタでは直接登録しない）
    }

    // 自作堆肥の回復値をModSetupで登録
    public static void registerDefaults() {
        // MODアイテムはここで登録（アイテムインスタンスが確定してから）
        registerCompost(ModItems.COMPOST.get(), 0.12f, 0.12f, 0.12f);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // プレイヤーの照準先ブロックを取得
        BlockHitResult hit = (BlockHitResult) player.pick(5.0, 1.0f, false);
        BlockPos targetPos = hit.getBlockPos();
        BlockState targetState = level.getBlockState(targetPos);

        // Farmlandブロックのみ対象
        if (!(targetState.getBlock() instanceof FarmBlock)) {
            return InteractionResultHolder.pass(stack);
        }

        if (!level.isClientSide()) {
            float[] values = COMPOST_VALUES.getOrDefault(stack.getItem(),
                    new float[]{0.1f, 0.1f, 0.1f});

            applyCompost(level, targetPos, values);

            // パーティクル演出
            ((ServerLevel) level).sendParticles(ParticleTypes.HAPPY_VILLAGER,
                    targetPos.getX() + 0.5, targetPos.getY() + 1.0, targetPos.getZ() + 0.5,
                    8, 0.3, 0.2, 0.3, 0.0);

            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    private static void applyCompost(Level level, BlockPos pos, float[] values) {
        // Capabilityが取得できているか確認
        Optional<SoilData> opt = SoilDataCapability.get(level, pos);
        if (opt.isEmpty()) {
            PochiMod.LOGGER.error("[Soil] Capability取得失敗 pos={}", pos);
            return;
        }
        PochiMod.LOGGER.info("[Soil] Capability取得成功 pos={}", pos);

        // 変更前の値
        float beforeN = SoilNutrientHelper.get(level, pos, SoilNutrientHelper.KEY_NITROGEN);
        PochiMod.LOGGER.info("[Soil] 変更前 N={}", beforeN);

        adjustNutrient(level, pos, SoilNutrientHelper.KEY_NITROGEN,   values[0]);
        adjustNutrient(level, pos, SoilNutrientHelper.KEY_PHOSPHORUS, values[1]);
        adjustNutrient(level, pos, SoilNutrientHelper.KEY_POTASSIUM,  values[2]);

        // 変更後の値
        float afterN = SoilNutrientHelper.get(level, pos, SoilNutrientHelper.KEY_NITROGEN);
        PochiMod.LOGGER.info("[Soil] 変更後 N={}", afterN);
    }

    private static void adjustNutrient(Level level, BlockPos pos, String key, float delta) {
        float current = SoilNutrientHelper.get(level, pos, key);
        SoilNutrientHelper.set(level, pos, key, current + delta);
    }
}