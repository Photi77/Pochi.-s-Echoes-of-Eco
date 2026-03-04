package net.pochi.pochimod.world.biome.chunkgen;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.ProtoChunk;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import org.apache.commons.lang3.mutable.MutableObject;

import java.text.DecimalFormat;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class GuyaneMountain extends NoiseBasedChunkGenerator {

    public static final MapCodec<GuyaneMountain> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BiomeSource.CODEC.fieldOf("biome_source").forGetter(generator -> generator.biomeSource),
            NoiseGeneratorSettings.CODEC.fieldOf("settings").forGetter(generator -> generator.settings)
    ).apply(instance, instance.stable(GuyaneMountain::new)));

    private final Holder<NoiseGeneratorSettings> settings;
    private final Supplier<Aquifer.FluidPicker> globalFluidPicker;

    public GuyaneMountain(BiomeSource p_256415_, Holder<NoiseGeneratorSettings> p_256182_) {
        super(p_256415_, p_256182_);
        this.settings = p_256182_;
        this.globalFluidPicker = Suppliers.memoize(() -> {
            return createFluidPicker(p_256182_.value());
        });
    }

    private static Aquifer.FluidPicker createFluidPicker(NoiseGeneratorSettings p_249264_) {
        Aquifer.FluidStatus aquifer$fluidstatus = new Aquifer.FluidStatus(-54, Blocks.LAVA.defaultBlockState());
        int i = p_249264_.seaLevel();
        Aquifer.FluidStatus aquifer$fluidstatus1 = new Aquifer.FluidStatus(i, p_249264_.defaultFluid());
        Aquifer.FluidStatus aquifer$fluidstatus2 = new Aquifer.FluidStatus(DimensionType.MIN_Y * 2, Blocks.AIR.defaultBlockState());
        return (p_224274_, p_224275_, p_224276_) -> {
            return p_224275_ < Math.min(-54, i) ? aquifer$fluidstatus : aquifer$fluidstatus1;
        };
    }

    private NoiseChunk createNoiseChunk(ChunkAccess p_224257_, StructureManager p_224258_, Blender p_224259_, RandomState p_224260_) {
        return NoiseChunk.forChunk(p_224257_, p_224260_, Beardifier.forStructuresInChunk(p_224258_, p_224257_.getPos()), this.settings.value(), this.globalFluidPicker.get(), p_224259_);
    }

    @Override
    protected MapCodec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public void applyCarvers(WorldGenRegion p_224224_, long p_224225_, RandomState p_224226_, BiomeManager p_224227_, StructureManager p_224228_, ChunkAccess p_224229_, GenerationStep.Carving p_224230_) {
        BiomeManager biomemanager = p_224227_.withDifferentSource((p_255581_, p_255582_, p_255583_) -> {
            return this.biomeSource.getNoiseBiome(p_255581_, p_255582_, p_255583_, p_224226_.sampler());
        });
        WorldgenRandom worldgenrandom = new WorldgenRandom(new LegacyRandomSource(RandomSupport.generateUniqueSeed()));
        int i = 8;
        ChunkPos chunkpos = p_224229_.getPos();
        NoiseChunk noisechunk = p_224229_.getOrCreateNoiseChunk((p_224250_) -> {
            return this.createNoiseChunk(p_224250_, p_224228_, Blender.of(p_224224_), p_224226_);
        });
        Aquifer aquifer = noisechunk.aquifer();
        CarvingContext carvingcontext = new CarvingContext(this, p_224224_.registryAccess(), p_224229_.getHeightAccessorForGeneration(), noisechunk, p_224226_, this.settings.value().surfaceRule());
        CarvingMask carvingmask = ((ProtoChunk)p_224229_).getOrCreateCarvingMask(p_224230_);

        for(int j = -8; j <= 8; ++j) {
            for(int k = -8; k <= 8; ++k) {
                ChunkPos chunkpos1 = new ChunkPos(chunkpos.x + j, chunkpos.z + k);
                ChunkAccess chunkaccess = p_224224_.getChunk(chunkpos1.x, chunkpos1.z);
                BiomeGenerationSettings biomegenerationsettings = chunkaccess.carverBiome(() -> {
                    return this.getBiomeGenerationSettings(this.biomeSource.getNoiseBiome(QuartPos.fromBlock(chunkpos1.getMinBlockX()), 0, QuartPos.fromBlock(chunkpos1.getMinBlockZ()), p_224226_.sampler()));
                });
                Iterable<Holder<ConfiguredWorldCarver<?>>> iterable = biomegenerationsettings.getCarvers(p_224230_);
                int l = 0;

                for(Holder<ConfiguredWorldCarver<?>> holder : iterable) {
                    ConfiguredWorldCarver<?> configuredworldcarver = holder.value();
                    worldgenrandom.setLargeFeatureSeed(p_224225_ + (long)l, chunkpos1.x, chunkpos1.z);
                    if (configuredworldcarver.isStartChunk(worldgenrandom)) {
                        configuredworldcarver.carve(carvingcontext, p_224229_, biomemanager::getBiome, worldgenrandom, aquifer, chunkpos1, carvingmask);
                    }

                    ++l;
                }
            }
        }

    }

    @Override
    public void buildSurface(WorldGenRegion p_223050_, StructureManager p_223051_, RandomState p_223052_, ChunkAccess p_223053_) {
        super.buildSurface(p_223050_, p_223051_, p_223052_, p_223053_);
    }

    public void spawnOriginalMobs(WorldGenRegion p_64379_) {
        if (!this.settings.value().disableMobGeneration()) {
            ChunkPos chunkpos = p_64379_.getCenter();
            Holder<Biome> holder = p_64379_.getBiome(chunkpos.getWorldPosition().atY(p_64379_.getMaxBuildHeight() - 1));
            WorldgenRandom worldgenrandom = new WorldgenRandom(new LegacyRandomSource(RandomSupport.generateUniqueSeed()));
            worldgenrandom.setDecorationSeed(p_64379_.getSeed(), chunkpos.getMinBlockX(), chunkpos.getMinBlockZ());
            NaturalSpawner.spawnMobsForChunkGeneration(p_64379_, holder, chunkpos, worldgenrandom);
        }
    }

    @Override
    public int getGenDepth() {
        return this.settings.value().noiseSettings().height();
    }

    //@Override
    //public CompletableFuture<ChunkAccess> fillFromNoise(Executor p_223209_, Blender p_223210_, RandomState p_223211_, StructureManager p_223212_, ChunkAccess p_223213_) {
    //    return super.fillFromNoise(p_223209_, p_223210_, p_223211_, p_223212_, p_223213_);
    //}

    @Override
    public int getSeaLevel() {
        return this.settings.value().seaLevel();
    }

    @Override
    public int getMinY() {
        return this.settings.value().noiseSettings().minY();
    }

    @Override
    public int getBaseHeight(int p_224216_, int p_224217_, Heightmap.Types p_224218_, LevelHeightAccessor p_224219_, RandomState p_224220_) {
        return this.iterateNoiseColumn(p_224219_, p_224220_, p_224216_, p_224217_, (MutableObject<NoiseColumn>)null, p_224218_.isOpaque()).orElse(p_224219_.getMinBuildHeight());
    }

    @Override
    public NoiseColumn getBaseColumn(int p_224211_, int p_224212_, LevelHeightAccessor p_224213_, RandomState p_224214_) {
        MutableObject<NoiseColumn> mutableobject = new MutableObject<>();
        this.iterateNoiseColumn(p_224213_, p_224214_, p_224211_, p_224212_, mutableobject, (Predicate<BlockState>)null);
        return mutableobject.getValue();
    }

    @Override
    public void addDebugScreenInfo(List<String> p_224304_, RandomState p_224305_, BlockPos p_224306_) {
        DecimalFormat decimalformat = new DecimalFormat("0.000");
        NoiseRouter noiserouter = p_224305_.router();
        DensityFunction.SinglePointContext densityfunction$singlepointcontext = new DensityFunction.SinglePointContext(p_224306_.getX(), p_224306_.getY(), p_224306_.getZ());
        double d0 = noiserouter.ridges().compute(densityfunction$singlepointcontext);
        p_224304_.add("NoiseRouter T: " + decimalformat.format(noiserouter.temperature().compute(densityfunction$singlepointcontext)) + " V: " + decimalformat.format(noiserouter.vegetation().compute(densityfunction$singlepointcontext)) + " C: " + decimalformat.format(noiserouter.continents().compute(densityfunction$singlepointcontext)) + " E: " + decimalformat.format(noiserouter.erosion().compute(densityfunction$singlepointcontext)) + " D: " + decimalformat.format(noiserouter.depth().compute(densityfunction$singlepointcontext)) + " W: " + decimalformat.format(d0) + " PV: " + decimalformat.format((double)NoiseRouterData.peaksAndValleys((float)d0)) + " AS: " + decimalformat.format(noiserouter.initialDensityWithoutJaggedness().compute(densityfunction$singlepointcontext)) + " N: " + decimalformat.format(noiserouter.finalDensity().compute(densityfunction$singlepointcontext)));
    }
}
