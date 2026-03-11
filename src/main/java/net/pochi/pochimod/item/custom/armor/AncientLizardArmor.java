package net.pochi.pochimod.item.custom.armor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animation.state.AnimationTest;
import software.bernie.geckolib.animation.object.PlayState;

public class AncientLizardArmor extends Item {

    //private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public static final String COOLDOWN_TAG = "tuatara_head_cooldown";
    public static final int COOLDOWN_TICKS = 100; // 5遘・
    public static final int DURABILITY_COST = 10;

    public AncientLizardArmor(Holder<ArmorMaterial> p_40386_, ArmorType p_266831_, Properties p_40388_) {
        super(p_40388_);
    }
    private PlayState predicate(AnimationTest animationState) {
        return PlayState.STOP;
    }

    //@Override
    //public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
    //    controllerRegistrar.add(new AnimationController("controller", 0, this::predicate));
    //}
//
    //@Override
    //public AnimatableInstanceCache getAnimatableInstanceCache() {
    //    return cache;
    //}

    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (level.isClientSide()) {
            if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == this) {
                // TODO: Phase 3縺ｧ繝代・繝・ぅ繧ｯ繝ｫ繧ｨ繝輔ぉ繧ｯ繝郁ｿｽ蜉
            }
        }
    }


    //@Override
    //public void initializeClient(Consumer<IClientItemExtensions> consumer) {
    //    consumer.accept(new IClientItemExtensions() {
    //        private PerissoArmorRenderer renderer;
//
    //        @Override
    //        public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack,
    //                                                               EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
    //            if (this.renderer == null)
    //                this.renderer = new PerissoArmorRenderer();
//
    //            this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
    //            return this.renderer;
    //        }
    //    });
    //}
}
