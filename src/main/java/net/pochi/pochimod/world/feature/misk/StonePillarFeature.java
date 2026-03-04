package net.pochi.pochimod.world.feature.misk;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.phys.Vec3;

public class StonePillarFeature extends Feature<NoneFeatureConfiguration> {

    //private final PerlinSimplexNoise noiseGenerator;

    // 使用するブロック
    private static final BlockState SANDSTONE = Blocks.SANDSTONE.defaultBlockState();
    private static final BlockState SMOOTH_SANDSTONE = Blocks.SMOOTH_SANDSTONE.defaultBlockState();
    private static final BlockState MOSSY_COBBLESTONE = Blocks.MOSSY_COBBLESTONE.defaultBlockState();
    private static final BlockState MOSS_BLOCK = Blocks.MOSS_BLOCK.defaultBlockState();
    private static final BlockState MUD = Blocks.MUD.defaultBlockState();
    private static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.defaultBlockState();

    // 植生ブロック
    private static final BlockState MOSS_CARPET = Blocks.MOSS_CARPET.defaultBlockState();
    private static final BlockState AZALEA = Blocks.AZALEA.defaultBlockState();
    private static final BlockState FLOWERING_AZALEA = Blocks.FLOWERING_AZALEA.defaultBlockState();
    private static final BlockState GRASS = Blocks.GRASS_BLOCK.defaultBlockState();
    private static final BlockState TALL_GRASS = Blocks.TALL_GRASS.defaultBlockState();
    private static final BlockState FERN = Blocks.FERN.defaultBlockState();
    private static final BlockState LARGE_FERN = Blocks.LARGE_FERN.defaultBlockState();

    public StonePillarFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
        //this.noiseGenerator = new PerlinSimplexNoise(new WorldgenRandom(new LegacyRandomSource(0L)), ImmutableList.of(0));
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        // 地面の高さを取得
        int groundHeight = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, pos.getX(), pos.getZ());
        BlockPos groundPos = new BlockPos(pos.getX(), groundHeight-15, pos.getZ());

        // 円錐形石柱のパラメータ（太さ2倍 + 角度追加）
        int pillarHeight = 60 + random.nextInt(120); // 60-180ブロック
        double baseRadius = 12 + random.nextDouble() * 16; // 12-28ブロック（2倍）
        double topRadius = 2 + random.nextDouble() * 6; // 2-8ブロック（2倍）

        // 石柱の傾斜角度を追加
        double tiltAngle = (random.nextDouble() - 0.3) * Math.PI * 0.2; // -27度～+27度
        double tiltDirection = random.nextDouble() * 2 * Math.PI; // 傾斜方向

        // 石柱の傾斜オフセットを計算
        Vec3 tiltOffset = new Vec3(
                Math.cos(tiltDirection) * Math.sin(tiltAngle),
                0,
                Math.sin(tiltDirection) * Math.sin(tiltAngle)
        );

        // 円錐の形状パラメータ
        double taper = random.nextDouble() * 0.3 + 0.7; // 0.7-1.0（テーパー度）
        double roughness = random.nextDouble() * 0.5 + 0.2; // 0.2-0.7（表面の粗さ）

        // 円錐形石柱を生成（角度付き）
        generateAngledConicalPillar(level, groundPos, pillarHeight, baseRadius, topRadius,
                taper, roughness, tiltOffset, random);

        // 石柱の側面に木を生成
        addPillarSideTrees(level, groundPos, pillarHeight, baseRadius, topRadius, taper, tiltOffset, random);

        // 石柱頂上を草ブロック + 植生で処理（角度を考慮）
        BlockPos tiltedTop = calculateTiltedPosition(groundPos.above(pillarHeight), pillarHeight, tiltOffset);
        addPillarTopVegetation(level, tiltedTop, topRadius, random);

        return true;
    }

    /**
     * 角度付き円錐形石柱を生成
     */
    private void generateAngledConicalPillar(WorldGenLevel level, BlockPos basePos, int height,
                                             double baseRadius, double topRadius, double taper,
                                             double roughness, Vec3 tiltOffset, RandomSource random) {

        for (int y = 0; y < height; y++) {
            // 円錐の半径を計算（非線形テーパー）
            double progress = (double) y / height;
            double taperedProgress = Math.pow(progress, taper); // テーパー関数
            double currentRadius = baseRadius * (1 - taperedProgress) + topRadius * taperedProgress;

            // 高度による自然な変動
            double heightVariation = Math.sin(y * 0.1) * roughness * 0.5;
            currentRadius += heightVariation;

            // 傾斜による中心位置のオフセット
            BlockPos layerCenter = calculateTiltedPosition(basePos, y, tiltOffset);

            // より自然なノイズパターン（3オクターブ）
            double noiseScale1 = 0.08;
            double noiseScale2 = 0.15;
            double noiseScale3 = 0.25;

            for (int x = -(int) currentRadius - 8; x <= (int) currentRadius + 8; x++) {
                for (int z = -(int) currentRadius - 8; z <= (int) currentRadius + 8; z++) {
                    double distance = Math.sqrt(x * x + z * z);

                    // 多層ノイズで自然な表面
                    double noise1 = generatePerlinNoise(x, y, z, noiseScale1) * 2.0;
                    double noise2 = generatePerlinNoise(x, y, z, noiseScale2) * 1.0;
                    double noise3 = generatePerlinNoise(x, y, z, noiseScale3) * 0.5;
                    double totalNoise = noise1 + noise2 + noise3;

                    // 表面の凹凸を追加
                    double surfaceVariation = totalNoise * roughness;
                    double effectiveRadius = currentRadius + surfaceVariation;

                    if (distance <= effectiveRadius) {
                        BlockPos currentPos = layerCenter.offset(x, y, z);

                        // 円錐形石柱のブロック種類を決定
                        BlockState blockToPlace = getConicalPillarBlock(
                                distance, effectiveRadius, y, height, progress, random);

                        //if (shouldReplaceBlock(level, currentPos)) {
                            level.setBlock(currentPos, blockToPlace, 2);
                        //}
                    }
                }
            }
        }

        // 石柱表面の詳細加工（角度付き）
        addAngledConicalPillarDetails(level, basePos, height, baseRadius, topRadius, taper, tiltOffset, random);
    }

    /**
     * 傾斜による位置計算
     */
    private BlockPos calculateTiltedPosition(BlockPos basePos, int height, Vec3 tiltOffset) {
        double offsetX = tiltOffset.x * height;
        double offsetZ = tiltOffset.z * height;

        return basePos.offset((int) offsetX, 0, (int) offsetZ);
    }

    /**
     * カスタムオークの木
     */
    private void generateCustomOakTree(WorldGenLevel level, BlockPos pos, RandomSource random) {
        int height = 4 + random.nextInt(4); // 4-8ブロック

        // 幹を生成
        for (int y = 0; y < height; y++) {
            level.setBlock(pos.above(y), Blocks.OAK_LOG.defaultBlockState(), 2);
        }

        // 葉を生成
        BlockPos leafCenter = pos.above(height - 1);
        for (int x = -2; x <= 2; x++) {
            for (int y = -1; y <= 2; y++) {
                for (int z = -2; z <= 2; z++) {
                    if (Math.abs(x) + Math.abs(y) + Math.abs(z) <= 3 && random.nextFloat() < 0.8) {
                        BlockPos leafPos = leafCenter.offset(x, y, z);
                        if (level.getBlockState(leafPos).isAir()) {
                            level.setBlock(leafPos, Blocks.OAK_LEAVES.defaultBlockState(), 2);
                        }
                    }
                }
            }
        }
    }

    /**
     * 改良されたパーリンノイズ生成
     */
    private double generatePerlinNoise(int x, int y, int z, double scale) {
        double nx = x * scale;
        double ny = y * scale * 0.3; // Y軸は圧縮
        double nz = z * scale;

        // より複雑なノイズ関数
        double noise1 = Math.sin(nx) * Math.cos(nz);
        double noise2 = Math.sin(ny + nx * 0.5) * Math.cos(nz + ny * 0.5);
        double noise3 = Math.sin(nx + nz) * Math.cos(ny);

        return (noise1 + noise2 * 0.7 + noise3 * 0.5) / 2.2;
    }

    /**
     * 円錐形石柱のブロック選択（砂岩・苔石ベース + コケ・泥ミックス）
     */
    private BlockState getConicalPillarBlock(double distance, double maxRadius,
                                             int currentY, int totalHeight, double heightProgress,
                                             RandomSource random) {
        double relativeDistance = distance / maxRadius;

        // 高度による湿度と風化の計算
        double humidity = Math.max(0, 1.0 - heightProgress * 0.8); // 下部ほど湿潤
        double weathering = heightProgress * 0.4 + random.nextDouble() * 0.3; // 上部ほど風化

        // 表面近くは植生やコケが生えやすい
        if (relativeDistance > 0.85) {
            // 最外層：砂岩・苔石 + コケ・泥ミックス
            if (humidity > 0.6) {
                // 高湿度：コケブロックと泥を多く混ぜる
                float mixChance = random.nextFloat();
                if (mixChance < 0.25) return MOSS_BLOCK;
                else if (mixChance < 0.45) return MUD;
                else if (mixChance < 0.65) return MOSSY_COBBLESTONE;
                else return SANDSTONE;
            } else if (weathering > 0.5) {
                // 中湿度：苔石中心
                float mixChance = random.nextFloat();
                if (mixChance < 0.15) return MOSS_BLOCK;
                else if (mixChance < 0.25) return MUD;
                else if (mixChance < 0.7) return MOSSY_COBBLESTONE;
                else return SANDSTONE;
            } else {
                // 低湿度：砂岩中心
                float mixChance = random.nextFloat();
                if (mixChance < 0.1) return MOSS_BLOCK;
                else if (mixChance < 0.15) return MUD;
                else if (mixChance < 0.3) return MOSSY_COBBLESTONE;
                else return SANDSTONE;
            }
        } else if (relativeDistance > 0.6) {
            // 中間層：風化した岩石にコケ・泥を少量混入
            if (humidity > 0.4 && weathering > 0.3) {
                float mixChance = random.nextFloat();
                if (mixChance < 0.1) return MOSS_BLOCK;
                else if (mixChance < 0.15) return MUD;
                else if (mixChance < 0.4) return MOSSY_COBBLESTONE;
                else return SANDSTONE;
            } else {
                return SANDSTONE;
            }
        } else {
            // 内層：新鮮な岩石
            return random.nextFloat() < 0.9 ? SMOOTH_SANDSTONE : SANDSTONE;
        }
    }

    /**
     * 円錐形石柱の詳細加工
     */
    private void addConicalPillarDetails(WorldGenLevel level, BlockPos basePos, int height,
                                         double baseRadius, double topRadius, double taper,
                                         RandomSource random) {

        // 縦の風化線を追加
        int numWeatheringLines = 3 + random.nextInt(5);
        for (int i = 0; i < numWeatheringLines; i++) {
            double angle = (2 * Math.PI * i) / numWeatheringLines + random.nextDouble() * 0.8;
            addVerticalWeatheringLine(level, basePos, height, baseRadius, topRadius, taper, angle, random);
        }

        // 水平の侵食帯を追加
        int numErosionBands = height / 30; // 30ブロックごと
        for (int i = 1; i <= numErosionBands; i++) {
            int bandY = i * 30 + random.nextInt(20) - 10;
            if (bandY < height) {
                addHorizontalErosionBand(level, basePos.above(bandY),
                        calculateRadiusAtHeight(baseRadius, topRadius, bandY, height, taper),
                        random);
            }
        }

        // 大きな凹部（洞窟の入り口など）
        if (random.nextFloat() < 0.3 && height > 80) {
            addLargeCavity(level, basePos, height, baseRadius, random);
        }
    }

    /**
     * 指定高度での半径を計算
     */
    private double calculateRadiusAtHeight(double baseRadius, double topRadius, int currentY, int totalHeight, double taper) {
        double progress = (double) currentY / totalHeight;
        double taperedProgress = Math.pow(progress, taper);
        return baseRadius * (1 - taperedProgress) + topRadius * taperedProgress;
    }

    /**
     * 縦の風化線を追加（砂岩・苔石 + コケ・泥ミックス）
     */
    private void addVerticalWeatheringLine(WorldGenLevel level, BlockPos basePos, int height,
                                           double baseRadius, double topRadius, double taper,
                                           double angle, RandomSource random) {

        for (int y = 0; y < height; y += 2 + random.nextInt(4)) {
            double currentRadius = calculateRadiusAtHeight(baseRadius, topRadius, y, height, taper);

            // 風化線の位置
            int lineX = (int) (Math.cos(angle) * (currentRadius * 0.9));
            int lineZ = (int) (Math.sin(angle) * (currentRadius * 0.9));

            BlockPos linePos = basePos.offset(lineX, y, lineZ);

            // 風化線の深さ（1-2ブロック）
            for (int depth = 0; depth < 1 + random.nextInt(2); depth++) {
                BlockPos innerPos = linePos.offset(
                        (int) (Math.cos(angle) * depth), 0, (int) (Math.sin(angle) * depth));

                BlockState currentState = level.getBlockState(innerPos);
                if (currentState.is(Blocks.SANDSTONE)|| currentState.is(Blocks.SMOOTH_SANDSTONE) ||
                        currentState.is(Blocks.MOSSY_COBBLESTONE)) {
                    if (random.nextFloat() < 0.6) {
                        // 風化線にはコケブロックと泥を配置
                        float mixChance = random.nextFloat();
                        BlockState weatheringBlock;
                        if (mixChance < 0.3) weatheringBlock = MOSS_BLOCK;
                        else if (mixChance < 0.5) weatheringBlock = MUD;
                        else weatheringBlock = MOSSY_COBBLESTONE;

                        level.setBlock(innerPos, weatheringBlock, 2);
                    }
                }
            }
        }
    }

    /**
     * 水平の侵食帯を追加（砂岩・苔石 + コケ・泥ミックス）
     */
    private void addHorizontalErosionBand(WorldGenLevel level, BlockPos center, double radius, RandomSource random) {
        for (int x = -(int) radius - 2; x <= (int) radius + 2; x++) {
            for (int z = -(int) radius - 2; z <= (int) radius + 2; z++) {
                double distance = Math.sqrt(x * x + z * z);

                if (distance <= radius + 1 && distance >= radius - 1) {
                    BlockPos bandPos = center.offset(x, 0, z);
                    BlockState current = level.getBlockState(bandPos);

                    // 表面部分にコケと泥をミックス
                    if ((current.is(Blocks.SANDSTONE) || current.is(Blocks.MOSSY_COBBLESTONE))
                            && random.nextFloat() < 0.4) {
                        // 侵食帯はコケブロック、泥、苔石のミックス
                        float mixChance = random.nextFloat();
                        BlockState erosionBlock;
                        if (mixChance < 0.25) erosionBlock = MOSS_BLOCK;
                        else if (mixChance < 0.4) erosionBlock = MUD;
                        else erosionBlock = MOSSY_COBBLESTONE;

                        level.setBlock(bandPos, erosionBlock, 2);
                    }
                }
            }
        }
    }

    /**
     * 大きな洞窟を追加（太い石柱に対応）
     */
    private void addLargeCavity(WorldGenLevel level, BlockPos basePos, int height, double baseRadius, RandomSource random) {
        int caveY = 20 + random.nextInt(height - 40); // 高さの中間部
        double caveRadius = 6 + random.nextDouble() * 8; // 6-14ブロック（2倍）
        int caveDepth = 10 + random.nextInt(16); // 10-26ブロック（2倍）

        // 洞窟の方向
        double caveAngle = random.nextDouble() * 2 * Math.PI;
        double pillarRadius = calculateRadiusAtHeight(baseRadius, 0, caveY, height, 1.0);

        int caveX = (int) (Math.cos(caveAngle) * pillarRadius * 0.8);
        int caveZ = (int) (Math.sin(caveAngle) * pillarRadius * 0.8);

        BlockPos caveCenter = basePos.offset(caveX, caveY, caveZ);

        // 楕円形の洞窟を作成（太い石柱に対応）
        for (int x = -(int) caveRadius; x <= (int) caveRadius; x++) {
            for (int y = -(int) caveRadius; y <= (int) caveRadius; y++) { // 高さも調整
                for (int z = 0; z < caveDepth; z++) {
                    double ellipseValue = (x * x) / (caveRadius * caveRadius) +
                            (y * y) / (caveRadius * caveRadius); // 円形に変更

                    if (ellipseValue <= 1.0) {
                        BlockPos cavePos = caveCenter.offset(
                                x + (int) (Math.cos(caveAngle) * z),
                                y,
                                (int) (Math.sin(caveAngle) * z)
                        );

                        if (level.getBlockState(cavePos).is(Blocks.SANDSTONE)|| level.getBlockState(cavePos).is(Blocks.SMOOTH_SANDSTONE)||
                                level.getBlockState(cavePos).is(Blocks.MOSSY_COBBLESTONE)||
                                        level.getBlockState(cavePos).is(Blocks.MOSS_BLOCK)|| level.getBlockState(cavePos).is(Blocks.MUD)) {
                            level.setBlock(cavePos, Blocks.AIR.defaultBlockState(), 2);
                        }
                    }
                }
            }
        }

        // 洞窟の入り口に植生を追加
        //addCaveEntranceVegetation(level, caveCenter, caveRadius, random);
    }
    /**
     * 表面位置かどうか判定（砂岩・苔石・コケ・泥を考慮）
     */
    private boolean isSurfacePosition(WorldGenLevel level, BlockPos pos) {
        BlockState current = level.getBlockState(pos);

        // 固体ブロック（砂岩、苔石、コケ、泥含む）で、少なくとも1面が空気に接している
        if (!current.isAir() && (current.is(Blocks.SANDSTONE)|| current.is(Blocks.SMOOTH_SANDSTONE)||
                current.is(Blocks.MOSSY_COBBLESTONE)|| current.is(Blocks.MOSS_BLOCK)|| current.is(Blocks.MUD))) {
            for (Direction dir : Direction.values()) {
                if (level.getBlockState(pos.relative(dir)).isAir()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 表面植生の配置
     */
    private void placeSurfaceVegetation(WorldGenLevel level, BlockPos pos, double humidity, RandomSource random) {
        // 隣接する空気ブロックに植生を配置
        for (Direction dir : Direction.values()) {
            BlockPos vegPos = pos.relative(dir);

            if (level.getBlockState(vegPos).isAir() && random.nextFloat() < 0.3) {
                BlockState vegetation = selectVegetationByHumidity(humidity, random);

                if (vegetation != null) {
                    level.setBlock(vegPos, vegetation, 2);

                    // 蔦の場合は下方向に延長
                    if (vegetation.is(Blocks.VINE) && dir == Direction.DOWN) {
                        extendVineDownward(level, vegPos, 2 + random.nextInt(6), random);
                    }
                }
                break; // 1つの植生のみ配置
            }
        }
    }

    /**
     * 湿度に応じた植生選択（砂岩・苔石・コケ・泥表面用）
     */
    private BlockState selectVegetationByHumidity(double humidity, RandomSource random) {
        float chance = random.nextFloat();

        if (humidity > 0.7) {
            // 高湿度：コケ系植物とシダ類
            if (chance < 0.2) return MOSS_CARPET;
            else if (chance < 0.35) return FERN;
            else if (chance < 0.5) return AZALEA;
            else if (chance < 0.65) return Blocks.VINE.defaultBlockState();
            else if (chance < 0.8) return FLOWERING_AZALEA;
            else return LARGE_FERN;
        } else if (humidity > 0.4) {
            // 中湿度：バランスの取れた植生
            if (chance < 0.25) return GRASS;
            else if (chance < 0.45) return TALL_GRASS;
            else if (chance < 0.6) return FERN;
            else if (chance < 0.75) return MOSS_CARPET;
            else return AZALEA;
        } else {
            // 低湿度：乾燥に強い植物
            if (chance < 0.4) return GRASS;
            else if (chance < 0.7) return TALL_GRASS;
            else if (chance < 0.9) return Blocks.DEAD_BUSH.defaultBlockState();
            else return null; // 植生なし
        }
    }

    /**
     * 蔦を下方向に延長
     */
    private void extendVineDownward(WorldGenLevel level, BlockPos startPos, int maxLength, RandomSource random) {
        for (int i = 1; i <= maxLength; i++) {
            BlockPos vinePos = startPos.below(i);
            if (level.getBlockState(vinePos).isAir()) {
                level.setBlock(vinePos, Blocks.VINE.defaultBlockState(), 2);
            } else {
                break;
            }
        }
    }

    /**
     * 石柱頂上の特別な植生（頂上は草ブロック）
     */
    private void addPillarTopVegetation(WorldGenLevel level, BlockPos topPos, double topRadius, RandomSource random) {
        // 頂上の平坦部分を草ブロックで覆い、その上に植生を配置
        for (int x = -(int) topRadius; x <= (int) topRadius; x++) {
            for (int z = -(int) topRadius; z <= (int) topRadius; z++) {
                if (x * x + z * z <= topRadius * topRadius) {
                    BlockPos grassPos = topPos.offset(x, 0, z);
                    BlockPos vegPos = topPos.offset(x, 1, z); // 草ブロックの上

                    // まず草ブロックを配置
                    if (level.getBlockState(grassPos).isAir() ||
                            level.getBlockState(grassPos).is(Blocks.SANDSTONE)|| level.getBlockState(grassPos).is(Blocks.SMOOTH_SANDSTONE)||
                                    level.getBlockState(grassPos).is(Blocks.MOSSY_COBBLESTONE)|| level.getBlockState(grassPos).is(Blocks.MOSS_BLOCK)||
                                            level.getBlockState(grassPos).is(Blocks.MUD)) {
                        level.setBlock(grassPos, GRASS_BLOCK, 2);
                    }

                    // その上に植生を配置（60%の確率）
                    if (level.getBlockState(vegPos).isAir() && random.nextFloat() < 0.6) {
                        BlockState topVegetation = selectTopVegetation(random);
                        level.setBlock(vegPos, topVegetation, 2);

                        // 高い植物の場合は上部も配置
                        if (topVegetation.is(Blocks.LARGE_FERN) || topVegetation.is(Blocks.TALL_GRASS)) {
                            BlockPos upperVegPos = vegPos.above();
                            if (level.getBlockState(upperVegPos).isAir()) {
                                level.setBlock(upperVegPos, topVegetation, 2);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 頂上植生の選択（草ブロック上の植物用）
     */
    private BlockState selectTopVegetation(RandomSource random) {
        float chance = random.nextFloat();

        // 草ブロック上に適した植物を選択
        if (chance < 0.15) return AZALEA;
        else if (chance < 0.25) return FLOWERING_AZALEA;
        else if (chance < 0.4) return LARGE_FERN;
        else if (chance < 0.55) return TALL_GRASS;
        else if (chance < 0.7) return FERN;
        else if (chance < 0.85) return GRASS;
        else if (chance < 0.95) return Blocks.DANDELION.defaultBlockState(); // 花を追加
        else return Blocks.POPPY.defaultBlockState(); // 花を追加
    }

    private boolean shouldReplaceBlock(WorldGenLevel level, BlockPos pos) {
        BlockState existing = level.getBlockState(pos);
        return existing.canBeReplaced() ||
                existing.is(Blocks.GRASS_BLOCK) ||
                existing.is(Blocks.DIRT) ||
                existing.is(Blocks.STONE) ||
                existing.is(Blocks.MUD) ||
                existing.is(Blocks.WATER) ||
                // 木の葉や原木は石柱生成時に置き換える
                existing.is(Blocks.OAK_LEAVES)|| existing.is(Blocks.BIRCH_LEAVES)|| existing.is(Blocks.JUNGLE_LEAVES)||
                        existing.is(Blocks.ACACIA_LEAVES)|| existing.is(Blocks.MANGROVE_LEAVES) ||
                existing.is(Blocks.OAK_LOG)|| existing.is(Blocks.BIRCH_LOG)|| existing.is(Blocks.JUNGLE_LOG)||
                        existing.is(Blocks.ACACIA_LOG)|| existing.is(Blocks.MANGROVE_LOG);
    }

    /**
     * 石柱側面に木を生成
     */
    private void addPillarSideTrees(WorldGenLevel level, BlockPos basePos, int height,
                                    double baseRadius, double topRadius, double taper,
                                    Vec3 tiltOffset, RandomSource random) {

        // 石柱の中間から上部にかけて木を配置
        for (int y = height / 3; y < height - 10; y += 15 + random.nextInt(20)) {
            double currentRadius = calculateAngledRadiusAtHeight(baseRadius, topRadius, y, height, taper);
            BlockPos layerCenter = calculateTiltedPosition(basePos, y, tiltOffset);

            // 円周上に3-6本の木を配置
            int numTrees = 3 + random.nextInt(4);
            for (int i = 0; i < numTrees; i++) {
                double angle = (2 * Math.PI * i) / numTrees + random.nextDouble() * 0.8;

                // 石柱表面より少し外側に配置
                int treeX = (int)(Math.cos(angle) * (currentRadius + 2 + random.nextInt(3)));
                int treeZ = (int)(Math.sin(angle) * (currentRadius + 2 + random.nextInt(3)));

                BlockPos treePos = layerCenter.offset(treeX, y, treeZ);

                // 石柱表面に根を張れる場所かチェック
                if (canPlaceTreeOnPillar(level, treePos)) {
                    generatePillarSideTree(level, treePos, angle, random);
                }
            }
        }
    }

    /**
     * 角度付き石柱の高度別半径計算
     */
    private double calculateAngledRadiusAtHeight(double baseRadius, double topRadius, int currentY, int totalHeight, double taper) {
        double progress = (double) currentY / totalHeight;
        double taperedProgress = Math.pow(progress, taper);
        return baseRadius * (1 - taperedProgress) + topRadius * taperedProgress;
    }

    /**
     * 石柱に木を配置できるかチェック
     */
    private boolean canPlaceTreeOnPillar(WorldGenLevel level, BlockPos pos) {
        // 少し下の位置に石柱ブロックがあるかチェック
        for (int dy = -3; dy <= 0; dy++) {
            BlockPos checkPos = pos.offset(0, dy, 0);
            BlockState state = level.getBlockState(checkPos);
            if (state.is(Blocks.SANDSTONE)|| state.is(Blocks.SMOOTH_SANDSTONE)|| state.is(Blocks.MOSSY_COBBLESTONE)||
                    state.is(Blocks.MOSS_BLOCK)|| state.is(Blocks.MUD)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 石柱側面用の木を生成
     */
    private void generatePillarSideTree(WorldGenLevel level, BlockPos pos, double growthAngle, RandomSource random) {
        float treeType = random.nextFloat();

        if (treeType < 0.4) {
            // 小さなシラカバ（40%）- 石柱に適している
            generateSmallBirchTree(level, pos, growthAngle, random);
        } else if (treeType < 0.7) {
            // 小さなオーク（30%）
            generateSmallOakTree(level, pos, growthAngle, random);
        } else if (treeType < 0.85) {
            // 小さなアカシア（15%）- 斜面に適している
            generateSmallAcaciaTree(level, pos, growthAngle, random);
        } else {
            // 竹（15%）- 縦に成長
            generateBambooCluster(level, pos, random);
        }
    }

    /**
     * 小さなシラカバ（石柱用）
     */
    private void generateSmallBirchTree(WorldGenLevel level, BlockPos pos, double angle, RandomSource random) {
        int height = 3 + random.nextInt(3); // 3-6ブロック

        // 石柱から外向きに傾斜して成長
        for (int y = 0; y < height; y++) {
            int offsetX = (int)(Math.cos(angle) * y * 0.3);
            int offsetZ = (int)(Math.sin(angle) * y * 0.3);

            BlockPos trunkPos = pos.offset(offsetX, y, offsetZ);
            if (level.getBlockState(trunkPos).isAir()) {
                level.setBlock(trunkPos, Blocks.BIRCH_LOG.defaultBlockState(), 2);
            }
        }

        // 小さな葉
        BlockPos leafCenter = pos.offset(
                (int)(Math.cos(angle) * height * 0.3),
                height - 1,
                (int)(Math.sin(angle) * height * 0.3)
        );

        for (int x = -1; x <= 1; x++) {
            for (int y = 0; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (random.nextFloat() < 0.7) {
                        BlockPos leafPos = leafCenter.offset(x, y, z);
                        if (level.getBlockState(leafPos).isAir()) {
                            level.setBlock(leafPos, Blocks.BIRCH_LEAVES.defaultBlockState(), 2);
                        }
                    }
                }
            }
        }
    }

    /**
     * 小さなオーク（石柱用）
     */
    private void generateSmallOakTree(WorldGenLevel level, BlockPos pos, double angle, RandomSource random) {
        int height = 3 + random.nextInt(3); // 3-6ブロック

        // やや外向きに成長
        for (int y = 0; y < height; y++) {
            int offsetX = (int)(Math.cos(angle) * y * 0.2);
            int offsetZ = (int)(Math.sin(angle) * y * 0.2);

            BlockPos trunkPos = pos.offset(offsetX, y, offsetZ);
            if (level.getBlockState(trunkPos).isAir()) {
                level.setBlock(trunkPos, Blocks.OAK_LOG.defaultBlockState(), 2);
            }
        }

        // 葉を配置
        BlockPos leafCenter = pos.offset(
                (int)(Math.cos(angle) * height * 0.2),
                height - 1,
                (int)(Math.sin(angle) * height * 0.2)
        );

        for (int x = -2; x <= 2; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -2; z <= 2; z++) {
                    if (Math.abs(x) + Math.abs(z) <= 2 && random.nextFloat() < 0.6) {
                        BlockPos leafPos = leafCenter.offset(x, y, z);
                        if (level.getBlockState(leafPos).isAir()) {
                            level.setBlock(leafPos, Blocks.OAK_LEAVES.defaultBlockState(), 2);
                        }
                    }
                }
            }
        }
    }

    /**
     * 小さなアカシア（石柱用）
     */
    private void generateSmallAcaciaTree(WorldGenLevel level, BlockPos pos, double angle, RandomSource random) {
        int height = 4 + random.nextInt(2); // 4-6ブロック

        // 強く外向きに傾斜
        for (int y = 0; y < height; y++) {
            int offsetX = (int)(Math.cos(angle) * y * 0.5);
            int offsetZ = (int)(Math.sin(angle) * y * 0.5);

            BlockPos trunkPos = pos.offset(offsetX, y, offsetZ);
            if (level.getBlockState(trunkPos).isAir()) {
                level.setBlock(trunkPos, Blocks.ACACIA_LOG.defaultBlockState(), 2);
            }
        }

        // 傘状の葉
        BlockPos leafCenter = pos.offset(
                (int)(Math.cos(angle) * height * 0.5),
                height - 1,
                (int)(Math.sin(angle) * height * 0.5)
        );

        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                if (x * x + z * z <= 4 && random.nextFloat() < 0.5) {
                    BlockPos leafPos = leafCenter.offset(x, 0, z);
                    if (level.getBlockState(leafPos).isAir()) {
                        level.setBlock(leafPos, Blocks.ACACIA_LEAVES.defaultBlockState(), 2);
                    }
                }
            }
        }
    }

    /**
     * 竹の群生（石柱用）
     */
    private void generateBambooCluster(WorldGenLevel level, BlockPos pos, RandomSource random) {
        int clusterSize = 2 + random.nextInt(4); // 2-6本の竹

        for (int i = 0; i < clusterSize; i++) {
            int offsetX = random.nextInt(3) - 1;
            int offsetZ = random.nextInt(3) - 1;
            BlockPos bambooPos = pos.offset(offsetX, 0, offsetZ);

            int bambooHeight = 4 + random.nextInt(8); // 4-12ブロック

            for (int y = 0; y < bambooHeight; y++) {
                if (level.getBlockState(bambooPos.above(y)).isAir()) {
                    level.setBlock(bambooPos.above(y), Blocks.BAMBOO.defaultBlockState(), 2);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 角度付き石柱の詳細加工
     */
    private void addAngledConicalPillarDetails(WorldGenLevel level, BlockPos basePos, int height,
                                               double baseRadius, double topRadius, double taper,
                                               Vec3 tiltOffset, RandomSource random) {

        // 縦の風化線を追加（角度考慮）
        int numWeatheringLines = 3 + random.nextInt(5);
        for (int i = 0; i < numWeatheringLines; i++) {
            double angle = (2 * Math.PI * i) / numWeatheringLines + random.nextDouble() * 0.8;
            addAngledVerticalWeatheringLine(level, basePos, height, baseRadius, topRadius, taper,
                    tiltOffset, angle, random);
        }

        // 水平の侵食帯を追加（角度考慮）
        int numErosionBands = height / 30; // 30ブロックごと
        for (int i = 1; i <= numErosionBands; i++) {
            int bandY = i * 30 + random.nextInt(20) - 10;
            if (bandY < height) {
                BlockPos layerCenter = calculateTiltedPosition(basePos.above(bandY), bandY, tiltOffset);
                addHorizontalErosionBand(level, layerCenter,
                        calculateAngledRadiusAtHeight(baseRadius, topRadius, bandY, height, taper),
                        random);
            }
        }

        // 大きな凹部（洞窟の入り口など）角度考慮
        if (random.nextFloat() < 0.3 && height > 80) {
            addAngledLargeCavity(level, basePos, height, baseRadius, tiltOffset, random);
        }
    }

    /**
     * 角度付き縦の風化線
     */
    private void addAngledVerticalWeatheringLine(WorldGenLevel level, BlockPos basePos, int height,
                                                 double baseRadius, double topRadius, double taper,
                                                 Vec3 tiltOffset, double angle, RandomSource random) {

        for (int y = 0; y < height; y += 2 + random.nextInt(4)) {
            double currentRadius = calculateAngledRadiusAtHeight(baseRadius, topRadius, y, height, taper);
            BlockPos layerCenter = calculateTiltedPosition(basePos, y, tiltOffset);

            // 風化線の位置
            int lineX = (int)(Math.cos(angle) * (currentRadius * 0.9));
            int lineZ = (int)(Math.sin(angle) * (currentRadius * 0.9));

            BlockPos linePos = layerCenter.offset(lineX, y, lineZ);

            // 風化線の深さ（1-2ブロック）
            for (int depth = 0; depth < 1 + random.nextInt(2); depth++) {
                BlockPos innerPos = linePos.offset(
                        (int)(Math.cos(angle) * depth), 0, (int)(Math.sin(angle) * depth));

                BlockState currentState = level.getBlockState(innerPos);
                if (currentState.is(Blocks.SANDSTONE)|| currentState.is(Blocks.SMOOTH_SANDSTONE) ||
                        currentState.is(Blocks.MOSSY_COBBLESTONE)) {
                    if (random.nextFloat() < 0.6) {
                        // 風化線にはコケブロックと泥を配置
                        float mixChance = random.nextFloat();
                        BlockState weatheringBlock;
                        if (mixChance < 0.3) weatheringBlock = MOSS_BLOCK;
                        else if (mixChance < 0.5) weatheringBlock = MUD;
                        else weatheringBlock = MOSSY_COBBLESTONE;

                        level.setBlock(innerPos, weatheringBlock, 2);
                    }
                }
            }
        }
    }

    /**
     * 角度付き大きな洞窟
     */
    private void addAngledLargeCavity(WorldGenLevel level, BlockPos basePos, int height,
                                      double baseRadius, Vec3 tiltOffset, RandomSource random) {
        int caveY = 20 + random.nextInt(height - 40); // 高さの中間部
        double caveRadius = 6 + random.nextDouble() * 8; // 6-14ブロック（2倍）
        int caveDepth = 10 + random.nextInt(16); // 10-26ブロック（2倍）

        // 洞窟の方向
        double caveAngle = random.nextDouble() * 2 * Math.PI;
        BlockPos layerCenter = calculateTiltedPosition(basePos, caveY, tiltOffset);
        double pillarRadius = calculateAngledRadiusAtHeight(baseRadius, 0, caveY, height, 1.0);

        int caveX = (int)(Math.cos(caveAngle) * pillarRadius * 0.8);
        int caveZ = (int)(Math.sin(caveAngle) * pillarRadius * 0.8);

        BlockPos caveCenter = layerCenter.offset(caveX, caveY, caveZ);

        // 円形の洞窟を作成
        for (int x = -(int)caveRadius; x <= (int)caveRadius; x++) {
            for (int y = -(int)caveRadius; y <= (int)caveRadius; y++) {
                for (int z = 0; z < caveDepth; z++) {
                    double circleValue = (x * x) / (caveRadius * caveRadius) +
                            (y * y) / (caveRadius * caveRadius);

                    if (circleValue <= 1.0) {
                        BlockPos cavePos = caveCenter.offset(
                                x + (int)(Math.cos(caveAngle) * z),
                                y,
                                (int)(Math.sin(caveAngle) * z)
                        );

                        if (level.getBlockState(cavePos).is(Blocks.SANDSTONE)|| level.getBlockState(cavePos).is(Blocks.SMOOTH_SANDSTONE)||
                                level.getBlockState(cavePos).is(Blocks.MOSSY_COBBLESTONE)|| level.getBlockState(cavePos).is(Blocks.MOSS_BLOCK)||
                                level.getBlockState(cavePos).is(Blocks.MUD)) {
                            level.setBlock(cavePos, Blocks.AIR.defaultBlockState(), 2);
                        }
                    }
                }
            }
        }

        // 洞窟の入り口に植生を追加
        //addCaveEntranceVegetation(level, caveCenter, caveRadius, random);
    }

}
