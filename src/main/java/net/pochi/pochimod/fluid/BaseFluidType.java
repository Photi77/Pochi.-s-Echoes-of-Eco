package net.pochi.pochimod.fluid;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidType;
import org.joml.Vector3f;
import org.joml.Vector4f;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class BaseFluidType extends FluidType {
    private final Identifier stillTexture;
    private final Identifier flowingTexture;
    private final Identifier overlayTexture;
    private final int tintColor;
    private final Vector3f fogColor;

    public BaseFluidType(final Identifier stillTexture, final Identifier flowingTexture, final Identifier overlayTexture,
                         final int tintColor, final  Vector3f fogColor, Properties properties) {
        super(properties);
        this.stillTexture = stillTexture;
        this.flowingTexture = flowingTexture;
        this.overlayTexture = overlayTexture;
        this.tintColor = tintColor;
        this.fogColor = fogColor;
    }

    public Identifier getStillTexture() {
        return stillTexture;
    }

    public Identifier getFlowingTexture() {
        return flowingTexture;
    }

    public int getTintColor() {
        return tintColor;
    }

    public Identifier getOverlayTexture() {
        return overlayTexture;
    }

    public Vector3f getFogColor() {
        return fogColor;
    }

    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {

            @Override
            public Identifier getStillTexture(){
                return stillTexture;
            }

            @Override
            public Identifier getFlowingTexture() {
                return flowingTexture;
            }

            @Override
            public @Nullable Identifier getOverlayTexture() {
                return overlayTexture;
            }
            @Override
            public int getTintColor() {
                return tintColor;
            }

            @Override
            public @Nullable Vector4f modifyFogColor(Camera camera, float partilaTick, ClientLevel level,
                                                     int renderDistance, float darkenWorldAmount, Vector4f fluidFogColor){
                return new Vector4f(fogColor.x(), fogColor.y(), fogColor.z(), 1.0f);
            }

            @Override
            public void modifyFogRender(Camera camera, FogEnvironment environment, float renderDistance, float partialTick,
                                        FogData fogData) {
                fogData.environmentalStart = 1f;
                fogData.environmentalEnd = 6f;
            }
        });
    }
}
