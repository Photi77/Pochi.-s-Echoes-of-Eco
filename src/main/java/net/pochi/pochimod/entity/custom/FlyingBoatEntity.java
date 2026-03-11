package net.pochi.pochimod.entity.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.pochi.pochimod.entity.ModEntityTypes;
import net.pochi.pochimod.item.ModItems;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;

public class FlyingBoatEntity extends Boat implements GeoEntity {

    private AnimatableInstanceCache factory = new SingletonAnimatableInstanceCache(this);
    public FlyingBoatEntity(EntityType<? extends Boat> p_38290_, Level p_38291_) {
        super(p_38290_, p_38291_, () -> ModItems.FLY_BOAT.get());
    }

    public FlyingBoatEntity(Level level, double pX, double pY, double pZ) {
        this(ModEntityTypes.FLY_BOAT.get(), level);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public void tick() {
        super.tick();
        if(this.level().isClientSide()) {
            if (this.getControllingPassenger() instanceof Player player) {
                if (Minecraft.getInstance().options.keyJump.isDown()) {
                    this.setDeltaMovement(getDeltaMovement().x, player.getLookAngle().y * 0.3, getDeltaMovement().z);
                    //this.move(MoverType.SELF, this.getDeltaMovement());
                } else {
                    this.setDeltaMovement(getDeltaMovement().x,0.02,getDeltaMovement().z);
                }
            } else {
                this.setDeltaMovement(0,0.02,0);
            }
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }

    // getDropItem() replaced by Supplier<Item> in constructor in 1.21.11

}
