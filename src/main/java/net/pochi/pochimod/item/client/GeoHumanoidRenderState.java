package net.pochi.pochimod.item.client;

import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import software.bernie.geckolib.constant.dataticket.DataTicket;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.HashMap;
import java.util.Map;

/**
 * Compile-time type that satisfies GeoArmorRenderer's
 * {@code R extends HumanoidRenderState & GeoRenderState} bound.
 *
 * GeckoLib injects GeoRenderState into EntityRenderState via mixin at runtime,
 * so all HumanoidRenderState instances already implement GeoRenderState.
 * This class makes that explicit for the Java compiler.
 */
public class GeoHumanoidRenderState extends HumanoidRenderState implements GeoRenderState {

    private final Map<DataTicket<?>, Object> data = new HashMap<>();

    @Override
    public Map<DataTicket<?>, Object> getDataMap() {
        return data;
    }
}
