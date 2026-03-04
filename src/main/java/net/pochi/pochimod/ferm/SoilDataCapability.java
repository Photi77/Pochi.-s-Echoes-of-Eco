package net.pochi.pochimod.ferm;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunk;
import net.pochi.pochimod.attachment.ModAttachments;

import java.util.Optional;

/**
 * チャンクの土壌データへのアクセスヘルパー
 * NeoForge 1.21.1 Attachment API を使用
 */
public class SoilDataCapability {

    /**
     * 指定座標の土壌データを取得
     */
    public static Optional<SoilData> get(Level level, BlockPos pos) {
        if (level.isClientSide()) return Optional.empty();

        ChunkAccess chunkAccess = level.getChunkAt(pos);
        if (!(chunkAccess instanceof LevelChunk chunk)) return Optional.empty();

        return Optional.of(chunk.getData(ModAttachments.SOIL_DATA));
    }
}
