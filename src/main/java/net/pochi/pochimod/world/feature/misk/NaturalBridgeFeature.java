package net.pochi.pochimod.world.feature.misk;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class NaturalBridgeFeature extends Feature<NoneFeatureConfiguration> {

    private static final BlockState SANDSTONE = Blocks.SANDSTONE.defaultBlockState();
    private static final BlockState SMOOTH_SANDSTONE = Blocks.SMOOTH_SANDSTONE.defaultBlockState();
    private static final BlockState COBBLESTONE = Blocks.COBBLESTONE.defaultBlockState();

    public NaturalBridgeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        // より確実な石柱検出
        List<PillarInfo> nearbyPillars = findAllNearbyPillars(level, pos, 50);

        if (nearbyPillars.size() < 2) {
            return false; // 石柱が2つ未満の場合は橋を生成しない
        }

        // 最適な石柱ペアを選択
        PillarPair bestPair = findBestPillarPair(nearbyPillars);
        if (bestPair == null) {
            return false;
        }

        // 橋を生成
        return generateImprovedBridge(level, bestPair.pillar1, bestPair.pillar2, random);
    }

    /**
     * 石柱情報を保持するクラス
     */
    private static class PillarInfo {
        public final BlockPos basePos;
        public final BlockPos topPos;
        public final int height;
        public final int radius;

        public PillarInfo(BlockPos basePos, BlockPos topPos, int height, int radius) {
            this.basePos = basePos;
            this.topPos = topPos;
            this.height = height;
            this.radius = radius;
        }
    }

    /**
     * 石柱ペア情報
     */
    private static class PillarPair {
        public final PillarInfo pillar1;
        public final PillarInfo pillar2;
        public final double distance;

        public PillarPair(PillarInfo pillar1, PillarInfo pillar2) {
            this.pillar1 = pillar1;
            this.pillar2 = pillar2;
            this.distance = pillar1.topPos.distSqr(pillar2.topPos);
        }
    }

    /**
     * 周囲の石柱を全て検出
     */
    private List<PillarInfo> findAllNearbyPillars(WorldGenLevel level, BlockPos center, int searchRadius) {
        List<PillarInfo> pillars = new ArrayList<>();

        // グリッド検索で効率化
        int gridSize = 10; // 10ブロックごとにサンプリング

        for (int x = -searchRadius; x <= searchRadius; x += gridSize) {
            for (int z = -searchRadius; z <= searchRadius; z += gridSize) {
                BlockPos searchPos = center.offset(x, 0, z);
                PillarInfo pillar = analyzePillarAt(level, searchPos);

                if (pillar != null && pillar.height > 30) { // 最小高度30ブロック
                    pillars.add(pillar);
                }
            }
        }

        return pillars;
    }

    /**
     * 指定位置の石柱を詳細分析
     */
    private PillarInfo analyzePillarAt(WorldGenLevel level, BlockPos searchCenter) {
        // 地表から上に向かって石柱を検索
        int surfaceY = findSurfaceLevel(level, searchCenter);
        BlockPos surfacePos = new BlockPos(searchCenter.getX(), surfaceY, searchCenter.getZ());

        // 石柱の基部を特定
        BlockPos pillarBase = findPillarBase(level, surfacePos);
        if (pillarBase == null) {
            return null;
        }

        // 石柱の高さと半径を計算
        int pillarHeight = calculatePillarHeight(level, pillarBase);
        int pillarRadius = calculatePillarRadius(level, pillarBase);

        if (pillarHeight < 30 || pillarRadius < 3) {
            return null; // 小さすぎる構造は除外
        }

        BlockPos pillarTop = pillarBase.above(pillarHeight);
        return new PillarInfo(pillarBase, pillarTop, pillarHeight, pillarRadius);
    }

    /**
     * 地表レベルを見つける
     */
    private int findSurfaceLevel(WorldGenLevel level, BlockPos pos) {
        for (int y = level.getMaxBuildHeight() - 1; y >= level.getMinBuildHeight(); y--) {
            BlockPos checkPos = new BlockPos(pos.getX(), y, pos.getZ());
            if (!level.getBlockState(checkPos).isAir()) {
                return y;
            }
        }
        return level.getSeaLevel();
    }

    /**
     * 石柱の基部を特定
     */
    private BlockPos findPillarBase(WorldGenLevel level, BlockPos surfacePos) {
        // 3x3範囲で石柱ブロックの密度をチェック
        int pillarBlockCount = 0;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                for (int dy = 0; dy <= 5; dy++) {
                    BlockPos checkPos = surfacePos.offset(dx, dy, dz);
                    if (isPillarBlock(level, checkPos)) {
                        pillarBlockCount++;
                    }
                }
            }
        }

        // 十分な密度があれば石柱の基部と判定
        return pillarBlockCount >= 15 ? surfacePos : null;
    }

    /**
     * 石柱の高さを計算
     */
    private int calculatePillarHeight(WorldGenLevel level, BlockPos base) {
        int height = 0;

        for (int y = 0; y < 200; y++) { // 最大200ブロック
            BlockPos checkPos = base.above(y);

            // 中心とその周辺をチェック
            boolean hasPillarBlocks = false;
            for (int dx = -2; dx <= 2; dx++) {
                for (int dz = -2; dz <= 2; dz++) {
                    if (isPillarBlock(level, checkPos.offset(dx, 0, dz))) {
                        hasPillarBlocks = true;
                        break;
                    }
                }
                if (hasPillarBlocks) break;
            }

            if (hasPillarBlocks) {
                height = y;
            } else if (y - height > 10) {
                // 10ブロック以上連続で石柱ブロックがない場合は終了
                break;
            }
        }

        return height;
    }

    /**
     * 石柱の半径を計算
     */
    private int calculatePillarRadius(WorldGenLevel level, BlockPos base) {
        int maxRadius = 0;

        // 高さの中点で半径を測定
        int middleHeight = calculatePillarHeight(level, base) / 2;
        BlockPos middlePos = base.above(middleHeight);

        for (int radius = 1; radius <= 15; radius++) {
            boolean foundPillarBlock = false;

            // 円周上をチェック
            for (int angle = 0; angle < 360; angle += 45) {
                double radians = Math.toRadians(angle);
                int x = (int)(Math.cos(radians) * radius);
                int z = (int)(Math.sin(radians) * radius);

                if (isPillarBlock(level, middlePos.offset(x, 0, z))) {
                    foundPillarBlock = true;
                    break;
                }
            }

            if (foundPillarBlock) {
                maxRadius = radius;
            }
        }

        return maxRadius;
    }

    /**
     * 石柱ブロックかどうかを判定
     */
    private boolean isPillarBlock(WorldGenLevel level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        return state.is(Blocks.SANDSTONE) ||
                state.is(Blocks.SMOOTH_SANDSTONE) ||
                state.is(Blocks.RED_SANDSTONE) ||
                state.is(Blocks.COBBLESTONE) ||
                state.is(Blocks.MOSSY_COBBLESTONE);
    }

    /**
     * 最適な石柱ペアを選択
     */
    private PillarPair findBestPillarPair(List<PillarInfo> pillars) {
        PillarPair bestPair = null;
        double bestScore = 0.0;

        for (int i = 0; i < pillars.size(); i++) {
            for (int j = i + 1; j < pillars.size(); j++) {
                PillarInfo pillar1 = pillars.get(i);
                PillarInfo pillar2 = pillars.get(j);

                PillarPair pair = new PillarPair(pillar1, pillar2);
                double score = evaluatePillarPair(pair);

                if (score > bestScore) {
                    bestScore = score;
                    bestPair = pair;
                }
            }
        }

        return bestScore > 0.5 ? bestPair : null; // スコアが0.5以上の場合のみ選択
    }

    /**
     * 石柱ペアの適合性を評価
     */
    private double evaluatePillarPair(PillarPair pair) {
        double distance = Math.sqrt(pair.distance);

        // 距離の評価（15-40ブロックが理想）
        double distanceScore;
        if (distance < 15 || distance > 40) {
            distanceScore = 0.0; // 範囲外は不適合
        } else if (distance >= 20 && distance <= 30) {
            distanceScore = 1.0; // 理想的な距離
        } else {
            // 理想範囲外は線形に減少
            distanceScore = distance < 20 ? (distance - 15) / 5.0 : (40 - distance) / 10.0;
        }

        // 高さの差の評価（高さが近いほど良い）
        int heightDiff = Math.abs(pair.pillar1.height - pair.pillar2.height);
        double heightScore = Math.max(0.0, 1.0 - heightDiff / 30.0);

        // 高度の評価（高い場所ほど良い）
        int avgHeight = (pair.pillar1.topPos.getY() + pair.pillar2.topPos.getY()) / 2;
        double altitudeScore = Math.min(1.0, (avgHeight - 80) / 50.0);

        // 総合スコア
        return distanceScore * 0.5 + heightScore * 0.3 + altitudeScore * 0.2;
    }

    /**
     * 改良された橋の生成
     */
    private boolean generateImprovedBridge(WorldGenLevel level, PillarInfo pillar1, PillarInfo pillar2, RandomSource random) {
        // 橋の接続ポイントを決定
        BlockPos start = findBridgeConnectionPoint(level, pillar1, pillar2.topPos);
        BlockPos end = findBridgeConnectionPoint(level, pillar2, pillar1.topPos);

        if (start == null || end == null) {
            return false;
        }

        // 橋のパラメータ
        int bridgeWidth = 4 + random.nextInt(3); // 2-3ブロック幅
        double bridgeSag = Math.sqrt(start.distSqr(end)) * 0.10; // 距離の15%垂れ下がる

        // 橋の軌道を計算
        List<BlockPos> bridgePath = calculateBridgePath(start, end, bridgeSag, 64);

        // 橋を生成
        generateBridgeStructure(level, bridgePath, bridgeWidth, random);

        // 橋の装飾
        addBridgeDecorations(level, bridgePath, bridgeWidth, random);

        return true;
    }

    /**
     * 石柱の橋接続ポイントを見つける
     */
    private BlockPos findBridgeConnectionPoint(WorldGenLevel level, PillarInfo pillar, BlockPos targetPos) {
        BlockPos pillarTop = pillar.topPos;
        Vec3 direction = Vec3.atCenterOf(targetPos).subtract(Vec3.atCenterOf(pillarTop)).normalize();

        // 石柱の表面で、目標方向に最も近い点を探す
        for (int radius = 1; radius <= pillar.radius; radius++) {
            for (int angle = 0; angle < 360; angle += 15) {
                double radians = Math.toRadians(angle);
                int x = (int)(Math.cos(radians) * radius);
                int z = (int)(Math.sin(radians) * radius);

                BlockPos candidatePos = pillarTop.offset(x, 0, z);

                // この位置が石柱の表面で、かつ目標方向に向いているかチェック
                if (isPillarBlock(level, candidatePos) && !isPillarBlock(level, candidatePos.relative(getDirectionToTarget(candidatePos, targetPos)))) {
                    return candidatePos.above(); // 1ブロック上を接続点とする
                }
            }
        }

        return pillarTop.above(); // デフォルトは石柱の頂上
    }

    /**
     * 目標への方向を取得
     */
    private Direction getDirectionToTarget(BlockPos from, BlockPos to) {
        int dx = to.getX() - from.getX();
        int dz = to.getZ() - from.getZ();

        if (Math.abs(dx) > Math.abs(dz)) {
            return dx > 0 ? Direction.EAST : Direction.WEST;
        } else {
            return dz > 0 ? Direction.SOUTH : Direction.NORTH;
        }
    }

    /**
     * 橋の軌道を計算（カテナリー曲線風）
     */
    private List<BlockPos> calculateBridgePath(BlockPos start, BlockPos end, double sag, int segments) {
        List<BlockPos> path = new ArrayList<>();

        for (int i = 0; i <= segments; i++) {
            double t = (double) i / segments;

            // 線形補間で基本位置を計算
            double x = start.getX() + (end.getX() - start.getX()) * t;
            double z = start.getZ() + (end.getZ() - start.getZ()) * t;
            double y = start.getY() + (end.getY() - start.getY()) * t;

            // カテナリー曲線で垂れ下がりを追加
            double sagAmount = sag * Math.sin(Math.PI * t);
            y -= sagAmount;

            path.add(new BlockPos((int)x, (int)y, (int)z));
        }

        return path;
    }

    /**
     * 橋の構造を生成
     */
    private void generateBridgeStructure(WorldGenLevel level, List<BlockPos> path, int width, RandomSource random) {
        for (int i = 0; i < path.size(); i++) {
            BlockPos centerPos = path.get(i);

            // 橋の方向を計算
            Vec3 direction = i < path.size() - 1 ?
                    Vec3.atCenterOf(path.get(i + 1)).subtract(Vec3.atCenterOf(centerPos)).normalize() :
                    Vec3.atCenterOf(centerPos).subtract(Vec3.atCenterOf(path.get(i - 1))).normalize();

            Vec3 perpendicular = new Vec3(-direction.z, 0, direction.x).normalize();

            // 橋の幅を生成
            for (int w = -width/2; w <= width/2; w++) {
                BlockPos bridgePos = centerPos.offset(
                        (int)(perpendicular.x * w),
                        0,
                        (int)(perpendicular.z * w)
                );

                // 橋のブロックを配置
                BlockState bridgeBlock = getBridgeBlockType(w, width, i, path.size(), random);

                if (level.getBlockState(bridgePos).isAir()) {
                    level.setBlock(bridgePos, bridgeBlock, 2);
                }

                // 橋の下部サポート
                if (Math.abs(w) == width/2 && random.nextFloat() < 0.3) {
                    addBridgeSupport(level, bridgePos, random);
                }
            }
        }
    }

    /**
     * 橋のブロック種類を決定
     */
    private BlockState getBridgeBlockType(int widthPos, int maxWidth, int pathPos, int pathLength, RandomSource random) {
        // 橋の端は頑丈なブロック
        if (Math.abs(widthPos) == maxWidth/2) {
            return random.nextFloat() < 0.8 ? SANDSTONE : COBBLESTONE;
        }

        // 橋の中央部
        if (pathPos == 0 || pathPos == pathLength - 1) {
            // 両端は特に頑丈に
            return SMOOTH_SANDSTONE;
        }

        // 通常部分
        return random.nextFloat() < 0.9 ? SMOOTH_SANDSTONE : SANDSTONE;
    }

    /**
     * 橋のサポート構造を追加
     */
    private void addBridgeSupport(WorldGenLevel level, BlockPos bridgePos, RandomSource random) {
        // 下向きに短いサポートを生成
        int supportLength = 1 + random.nextInt(3);

        for (int i = 1; i <= supportLength; i++) {
            BlockPos supportPos = bridgePos.below(i);
            if (level.getBlockState(supportPos).isAir()) {
                level.setBlock(supportPos, SANDSTONE, 2);
            } else {
                break; // 何かにぶつかったら停止
            }
        }
    }

    /**
     * 橋の装飾を追加
     */
    private void addBridgeDecorations(WorldGenLevel level, List<BlockPos> path, int width, RandomSource random) {
        // 橋の両端に装飾
        if (path.size() >= 2) {
            addBridgeEndDecoration(level, path.get(0), random);
            addBridgeEndDecoration(level, path.get(path.size() - 1), random);
        }

        // 橋の中間点に装飾
        if (path.size() > 10) {
            BlockPos midPoint = path.get(path.size() / 2);
            addBridgeMidDecoration(level, midPoint, width, random);
        }

        // ランダムに蔦を追加
        for (BlockPos pos : path) {
            if (random.nextFloat() < 0.1) {
                addHangingVines(level, pos, random);
            }
        }
    }

    /**
     * 橋の端の装飾
     */
    private void addBridgeEndDecoration(WorldGenLevel level, BlockPos pos, RandomSource random) {
        // 小さな石柱や装飾ブロック
        if (random.nextFloat() < 0.6) {
            level.setBlock(pos.above(), SANDSTONE, 2);
            if (random.nextFloat() < 0.4) {
                level.setBlock(pos.above(2), SANDSTONE, 2);
            }
        }
    }

    /**
     * 橋の中央装飾
     */
    private void addBridgeMidDecoration(WorldGenLevel level, BlockPos center, int width, RandomSource random) {
        // 中央に小さなアーチや装飾要素
        if (random.nextFloat() < 0.5) {
            level.setBlock(center.above(), SMOOTH_SANDSTONE, 2);
        }
    }

    /**
     * 垂れ下がる蔦を追加
     */
    private void addHangingVines(WorldGenLevel level, BlockPos bridgePos, RandomSource random) {
        int vineLength = 2 + random.nextInt(6);

        for (int i = 1; i <= vineLength; i++) {
            BlockPos vinePos = bridgePos.below(i);
            if (level.getBlockState(vinePos).isAir()) {
                level.setBlock(vinePos, Blocks.VINE.defaultBlockState(), 2);
            } else {
                break;
            }
        }
    }
}