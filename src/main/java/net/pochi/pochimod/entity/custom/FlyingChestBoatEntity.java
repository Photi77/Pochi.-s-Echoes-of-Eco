package net.pochi.pochimod.entity.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.pochi.pochimod.entity.ModEntityTypes;
import net.pochi.pochimod.item.ModItems;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;

public class FlyingChestBoatEntity extends ChestBoat implements GeoEntity {

    private AnimatableInstanceCache factory = new SingletonAnimatableInstanceCache(this);
    //private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(Boat.class, EntityDataSerializers.INT);

    public FlyingChestBoatEntity(EntityType<? extends ChestBoat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public FlyingChestBoatEntity(Level pLevel, double pX, double pY, double pZ) {
        this(ModEntityTypes.FLY_CHEST_BOAT.get(), pLevel);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public double getTick(Object entity) {
        return GeoEntity.super.getTick(entity);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.level().isClientSide) {
            if (this.isControlledByLocalInstance()) {
                if (this.getControllingPassenger() instanceof Player player) {
                    if (Minecraft.getInstance().options.keyJump.isDown()) {
                        this.setDeltaMovement(getDeltaMovement().x, player.getLookAngle().y * 0.3, getDeltaMovement().z);
                        //this.move(MoverType.SELF, this.getDeltaMovement());
                    }  else {
                        this.setDeltaMovement(getDeltaMovement().x,0.02,getDeltaMovement().z);
                    }
                } else {
                    this.setDeltaMovement(0,0.02,0);
                }
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

    @Override
    public Item getDropItem() {
        return ModItems.FLY_CHEST_BOAT.get();
    }

}
