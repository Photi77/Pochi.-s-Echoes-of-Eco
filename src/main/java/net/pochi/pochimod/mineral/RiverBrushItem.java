package net.pochi.pochimod.mineral;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BrushItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.item.ModItems;

import java.util.*;

/**
 * 川採掘ブラシ（RiverBrushItem）
 *
 * ===== ブラシ演出の仕組み =====
 *
 * 1. 右クリック長押し中、10tick毎にパーティクル+音を再生（演出のみ）
 * 2. REQUIRED_BRUSHES 回（デフォルト8回 = 約80tick）ブラシを当て続けると
 *    1回だけアイテムドロップ + ブロック破壊が発生する
 * 3. 途中で手を離すとカウントがリセットされる（やり直しになる）
 *
 * ===== 進捗管理 =====
 * サーバーサイドで UUID → brushCount のMapを保持。
 * クライアントには影響なし。
 */
public class RiverBrushItem extends BrushItem {

    // ---- 定数 ----
    /** 採掘完了に必要なブラシ回数（10tick毎に1カウント） */
    private static final int REQUIRED_BRUSHES = 8; // 約80tick ≈ 4秒

    private static final Set<net.minecraft.world.level.block.Block> BRUSHABLE_BLOCKS = Set.of(
            Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY
    );
    private static final int BIOME_SCAN_RADIUS = 192;
    private static final int BIOME_SCAN_STEP   = 16;
    private static final Set<String> RIVER_BIOMES = Set.of(
            "minecraft:river", "minecraft:frozen_river"
    );

    // ---- ブラシ進捗管理（サーバーサイド専用） ----
    // PlayerUUID → 現在ブラシを当てているブロック位置 + カウント
    private static final Map<UUID, BrushProgress> BRUSH_PROGRESS = new HashMap<>();

    private record BrushProgress(BlockPos pos, int count) {}

    public RiverBrushItem(Properties properties) {
        super(properties);
    }

    // ==============================
    //  使用中 Tick 処理
    // ==============================

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingTicks) {
        // バニラ挙動（SuspiciousSandへのパーティクル）はそのまま通す
        super.onUseTick(level, entity, stack, remainingTicks);

        if (level.isClientSide) return;
        if (!(entity instanceof Player player)) return;

        // 10tick刻みのタイミングのみ処理
        int elapsed = this.getUseDuration(stack, entity) - remainingTicks + 1;
        if (elapsed % 10 != 5) return;

        // レイキャストで対象ブロック取得
        BlockPos pos = getTargetPos(entity);
        if (pos == null) return;

        BlockState state = level.getBlockState(pos);
        if (!isBrushableBlock(state)) {
            // 対象外ブロックに向けている → 進捗リセット
            BRUSH_PROGRESS.remove(player.getUUID());
            return;
        }

        // 進捗更新
        UUID uuid = player.getUUID();
        BrushProgress prev = BRUSH_PROGRESS.get(uuid);

        // 前回と異なるブロックを向いていたらカウントリセット
        int newCount = (prev != null && prev.pos().equals(pos)) ? prev.count() + 1 : 1;
        BRUSH_PROGRESS.put(uuid, new BrushProgress(pos, newCount));

        // 演出（パーティクル + 音）
        playBrushEffects(level, pos, state, newCount);

        // 完了判定
        if (newCount >= REQUIRED_BRUSHES) {
            BRUSH_PROGRESS.remove(uuid);
            performMineralBrush(level, player, pos, stack);
        }
    }

    // ==============================
    //  使用中止 → 進捗リセット
    // ==============================

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeCharged) {
        super.releaseUsing(stack, level, entity, timeCharged);
        if (entity instanceof Player player) {
            BRUSH_PROGRESS.remove(player.getUUID());
        }
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        if (entity instanceof Player player) {
            BRUSH_PROGRESS.remove(player.getUUID());
        }
    }

    // ==============================
    //  採掘完了処理（1回のみ呼ばれる）
    // ==============================

    private void performMineralBrush(Level level, Player player, BlockPos pos, ItemStack brushStack) {
        if (!(level instanceof ServerLevel serverLevel)) return;

        Random random = new Random();

        // 1. バイオームスキャン
        BiomeScanResult scanResult = BiomeScanResult.scan(
                serverLevel, pos, BIOME_SCAN_RADIUS, BIOME_SCAN_STEP);

        if (!scanResult.isRiverBiome(RIVER_BIOMES)) return;

        // 2. depth_level算出
        int depthLevel = calcDepthLevel(pos.getY());

        // 3. 地質→不純物決定
        List<MineralImpurity> impurities =
                BiomeGeologyCalculator.determineImpuritiesFromScan(scanResult, depthLevel, random);

        // 4. ベース宝石ランダム選択
        BaseGem baseGem = BaseGem.random(random);

        // 5. color_hex計算
        String colorHex = MineralColorCalculator.calculate(impurities);

        // 6. comboBiomes（上位3バイオーム）
        List<ResourceLocation> comboBiomes = new ArrayList<>();
        List<BiomeScanResult.WeightedBiome> allBiomes = scanResult.getAllBiomes();
        for (int i = 0; i < Math.min(3, allBiomes.size()); i++) {
            comboBiomes.add(allBiomes.get(i).id());
        }

        // 7. MineralData生成
        MineralData data = new MineralData(baseGem, impurities, colorHex, comboBiomes, depthLevel);

        // 8. ItemStack生成 & ドロップ
        ItemStack mineralStack = MineralChunkItem.createStack(
                ModItems.MINERAL_CHUNK.get(), data);

        if (!player.getInventory().add(mineralStack)) {
            player.drop(mineralStack, false);
        }

        // 9. ブロック破壊（ドロップなし、完了音あり）
        level.removeBlock(pos, false);
        level.playSound(null, pos, SoundEvents.BRUSH_SAND_COMPLETED,
                SoundSource.BLOCKS, 1.0f, 1.0f);
    }

    // ==============================
    //  演出
    // ==============================

    /**
     * ブラシ中の演出（パーティクル + 音）
     * count が多いほど音が若干高くなり、進行感を演出する
     */
    private static void playBrushEffects(Level level, BlockPos pos, BlockState state, int count) {
        // ブロック破片パーティクル
        if (level instanceof ServerLevel serverLevel) {
            Vec3 center = Vec3.atCenterOf(pos).add(0, 0.5, 0);
            serverLevel.sendParticles(
                    new BlockParticleOption(ParticleTypes.BLOCK, state),
                    center.x, center.y, center.z,
                    6,           // パーティクル数
                    0.2, 0.1, 0.2, // spread
                    0.05         // speed
            );
        }

        // ブラシ音（進行に合わせてピッチを上げる）
        float pitch = 0.8f + (count / (float) REQUIRED_BRUSHES) * 0.4f;
        level.playSound(null, pos, SoundEvents.BRUSH_SAND,
                SoundSource.BLOCKS, 0.7f, pitch);
    }

    // ==============================
    //  ユーティリティ
    // ==============================

    /** レイキャストで照準ブロックのposを取得。対象外ならnull */
    private static BlockPos getTargetPos(LivingEntity entity) {
        Vec3 eyePos  = entity.getEyePosition();
        Vec3 lookVec = entity.getLookAngle();
        Vec3 endPos  = eyePos.add(lookVec.scale(4.5));
        net.minecraft.world.level.ClipContext clip = new net.minecraft.world.level.ClipContext(
                eyePos, endPos,
                net.minecraft.world.level.ClipContext.Block.OUTLINE,
                net.minecraft.world.level.ClipContext.Fluid.NONE,
                entity
        );
        BlockHitResult hit = entity.level().clip(clip);
        if (hit.getType() != HitResult.Type.BLOCK) return null;
        return hit.getBlockPos();
    }

    private static boolean isBrushableBlock(BlockState state) {
        return BRUSHABLE_BLOCKS.contains(state.getBlock());
    }

    private static int calcDepthLevel(int y) {
        if (y > 60) return 0;
        if (y > 30) return 1;
        if (y > 0)  return 2;
        return 3;
    }
}









