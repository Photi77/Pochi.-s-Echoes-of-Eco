package net.pochi.pochimod.item.custom.armor;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.state.AnimationTest;
import software.bernie.geckolib.animation.object.PlayState;

public class PangolinArmor extends Item implements GeoItem {

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    int effectTime = 0;

    private int count = 0;

    public PangolinArmor(Holder<ArmorMaterial> p_40386_, ArmorType p_266831_, Properties p_40388_) {
        super(p_40388_);
    }
    private PlayState predicate(AnimationTest animationState) {
        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController("controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if(!level.isClientSide()) {
            for (MobEffectInstance effectInstance : player.getActiveEffects()) {
                // MobEffectCategoryがHARMFULに該当する場吁E
                if (effectInstance.getEffect().value().getCategory() == MobEffectCategory.HARMFUL) {
                    player.removeEffect(effectInstance.getEffect());
                }
            }
        }
    }


    // initializeClient removed in NeoForge 1.21.11; register via RegisterClientExtensionsEvent instead
}
