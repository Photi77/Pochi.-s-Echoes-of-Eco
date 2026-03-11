package net.pochi.pochimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.pochi.pochimod.entity.projectile.DeltaMovementBlockEntity;

public class DeltaMovementBlockEntityRenderer extends EntityRenderer<DeltaMovementBlockEntity, DeltaMovementBlockEntityRenderer.DeltaBlockRenderState> {
    private final BlockRenderDispatcher dispatcher;

    public DeltaMovementBlockEntityRenderer(EntityRendererProvider.Context p_174112_) {
        super(p_174112_);
        this.shadowRadius = 0.5F;
        this.dispatcher = p_174112_.getBlockRenderDispatcher();
    }

    public static class DeltaBlockRenderState extends EntityRenderState {
        public BlockState blockState = Blocks.AIR.defaultBlockState();
        public BlockPos startPos = BlockPos.ZERO;
        public Level level = null;
    }

    @Override
    public DeltaBlockRenderState createRenderState() {
        return new DeltaBlockRenderState();
    }

    @Override
    public void extractRenderState(DeltaMovementBlockEntity entity, DeltaBlockRenderState reusedState, float partialTick) {
        super.extractRenderState(entity, reusedState, partialTick);
        reusedState.blockState = entity.getBlockState();
        reusedState.startPos = entity.getStartPos();
        reusedState.level = entity.level();
    }

    public void render(DeltaBlockRenderState state, PoseStack p_114637_, MultiBufferSource p_114638_, int p_114639_) {
        BlockState blockstate = state.blockState;
        if (blockstate.getRenderShape() == RenderShape.MODEL) {
            Level level = state.level;
            if (level != null && blockstate != level.getBlockState(BlockPos.containing(state.x, state.y, state.z)) && blockstate.getRenderShape() != RenderShape.INVISIBLE) {
                p_114637_.pushPose();
                BlockPos blockpos = BlockPos.containing(state.x, state.y, state.z);
                p_114637_.translate(-0.5D, 0.0D, -0.5D);
                var model = this.dispatcher.getBlockModel(blockstate);
                net.minecraft.client.renderer.block.ModelBlockRenderer.renderModel(p_114637_.last(), p_114638_, model, 1.0F, 1.0F, 1.0F, p_114639_, OverlayTexture.NO_OVERLAY, level, blockpos, blockstate);
                p_114637_.popPose();
            }
        }
    }

    public Identifier getTextureLocation(DeltaBlockRenderState state) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
