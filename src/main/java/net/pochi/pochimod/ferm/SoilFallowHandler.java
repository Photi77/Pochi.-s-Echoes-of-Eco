package net.pochi.pochimod.ferm;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.level.ChunkEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.attachment.ModAttachments;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@EventBusSubscriber(modid = PochiMod.MOD_ID)
public class SoilFallowHandler {

    private static final float FALLOW_RECOVERY = 0.005f;
    private static final int CHECK_INTERVAL = 200;

    // 繝ｭ繝ｼ繝我ｸｭ縺ｮ繝√Ε繝ｳ繧ｯ蠎ｧ讓吶ｒ繝・ぅ繝｡繝ｳ繧ｷ繝ｧ繝ｳ縺斐→縺ｫ邂｡逅・
    private static final Map<ResourceKey<Level>, Set<ChunkPos>> loadedChunks
            = new HashMap<>();

    private static int tickCounter = 0;

    // --- 繝√Ε繝ｳ繧ｯ繝ｭ繝ｼ繝峨・繧｢繝ｳ繝ｭ繝ｼ繝峨・霑ｽ霍｡ ---
    @SubscribeEvent
    public static void onChunkLoad(ChunkEvent.Load event) {
        if (!(event.getLevel() instanceof ServerLevel serverLevel)) return;
        loadedChunks
                .computeIfAbsent(serverLevel.dimension(), k -> new HashSet<>())
                .add(event.getChunk().getPos());
    }

    @SubscribeEvent
    public static void onChunkUnload(ChunkEvent.Unload event) {
        if (!(event.getLevel() instanceof ServerLevel serverLevel)) return;
        Set<ChunkPos> set = loadedChunks.get(serverLevel.dimension());
        if (set != null) set.remove(event.getChunk().getPos());
    }

    // --- 莨題募屓蠕ｩ繝・ぅ繝・け ---
    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel serverLevel)) return;

        tickCounter++;
        if (tickCounter < CHECK_INTERVAL) return;
        tickCounter = 0;

        Set<ChunkPos> chunks = loadedChunks.get(serverLevel.dimension());
        if (chunks == null || chunks.isEmpty()) return;

        for (ChunkPos chunkPos : chunks) {
            LevelChunk chunk = serverLevel.getChunkSource()
                    .getChunkNow(chunkPos.x, chunkPos.z);
            if (chunk == null) continue;

            {
                SoilData soilData = chunk.getData(ModAttachments.SOIL_DATA);
                for (long posLong : soilData.getFarmlandPositions()) {
                    BlockPos pos = BlockPos.of(posLong);
                    BlockState state = serverLevel.getBlockState(pos);

                    if (!(state.getBlock() instanceof FarmBlock)) continue;

                    BlockState cropState = serverLevel.getBlockState(pos.above());
                    if (cropState.isAir() || !(cropState.getBlock() instanceof CropBlock)) {
                        recoverSoil(serverLevel, pos);
                    }
                }
            }
        }
    }

    private static void recoverSoil(Level level, BlockPos pos) {
        adjustNutrient(level, pos, SoilNutrientHelper.KEY_NITROGEN,   FALLOW_RECOVERY);
        adjustNutrient(level, pos, SoilNutrientHelper.KEY_PHOSPHORUS, FALLOW_RECOVERY);
        adjustNutrient(level, pos, SoilNutrientHelper.KEY_POTASSIUM,  FALLOW_RECOVERY);
    }

    private static void adjustNutrient(Level level, BlockPos pos, String key, float delta) {
        float current = SoilNutrientHelper.get(level, pos, key);
        SoilNutrientHelper.set(level, pos, key, current + delta);
    }
}
