package net.pochi.pochimod.entity.client;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import software.bernie.geckolib.constant.dataticket.DataTicket;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.HashMap;
import java.util.Map;

/**
 * Compile-time stub satisfying GeoEntityRenderer's R extends EntityRenderState & GeoRenderState bound.
 * GeckoLib's mixin injects GeoRenderState into EntityRenderState at runtime, so this class
 * never needs to be actually instantiated (GeoEntityRenderer.createRenderState() returns null).
 */
public class GeckolibEntityRenderState extends EntityRenderState implements GeoRenderState {
    private final Map<DataTicket<?>, Object> dataMap = new HashMap<>();

    @Override
    public Map<DataTicket<?>, Object> getDataMap() {
        return dataMap;
    }
}
