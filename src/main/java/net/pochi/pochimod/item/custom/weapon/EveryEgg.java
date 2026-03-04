package net.pochi.pochimod.item.custom.weapon;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class EveryEgg extends Item{

    public EveryEgg(Properties p_41383_) {
        super(p_41383_);
    }
    public static final String CAPTURED_ENTITY_TAG = "CapturedEntity";
    public static final String ENTITY_TYPE_TAG = "EntityType";
    public static final List<String> TAGS_TO_REMOVE = List.of(
            "SleepingX", "SleepingY", "SleepingZ" // We need to remove sleeping tags because they case issues
    );


    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!release(context.getClickedPos(), context.getClickedFace(), context.getLevel(), context.getItemInHand()))
            return InteractionResult.FAIL;

        context.getItemInHand().hurtAndBreak(1, context.getPlayer(),
                context.getHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity interactionTarget, InteractionHand usedHand) {
        if (!capture(stack, interactionTarget, player))
            return InteractionResult.FAIL;
        player.swing(usedHand);
        player.setItemInHand(usedHand, stack);
        return InteractionResult.SUCCESS;
    }

    public static boolean capture(ItemStack stack, LivingEntity target) {
        return capture(stack, target, null);
    }

    public static boolean capture(ItemStack stack, LivingEntity target, @Nullable Player player) {
        if (target.level().isClientSide || getEntityType(stack) != null)
            return false;
        if (target instanceof Player || !target.isAlive())
            return false;
        final var nbt = new CompoundTag();
        nbt.putString(ENTITY_TYPE_TAG, EntityType.getKey(target.getType()).toString());
        target.saveWithoutId(nbt);
        TAGS_TO_REMOVE.forEach(nbt::remove);
        CompoundTag captureTag = stack.has(DataComponents.CUSTOM_DATA)
                ? stack.get(DataComponents.CUSTOM_DATA).copyTag()
                : new CompoundTag();
        captureTag.put(CAPTURED_ENTITY_TAG, nbt);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(captureTag));
        target.remove(Entity.RemovalReason.KILLED);
        return true;
    }

    public static boolean release(BlockPos pos, Direction facing, Level level, ItemStack stack) {
        if (level.isClientSide)
            return false;
        final var entityType = getEntityType(stack);
        if (entityType == null)
            return false;
        final var entity = entityType.create(level);
        if (entity != null) {
            if (stack.has(DataComponents.CUSTOM_DATA)) {
                entity.load(stack.get(DataComponents.CUSTOM_DATA).copyTag().getCompound(CAPTURED_ENTITY_TAG));
            }
            BlockPos blockPos = pos.relative(facing);
            entity.absMoveTo(blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5, 0, 0);
            if (stack.has(DataComponents.CUSTOM_DATA)) {
                CompoundTag releaseTag = stack.get(DataComponents.CUSTOM_DATA).copyTag();
                releaseTag.remove(CAPTURED_ENTITY_TAG);
                stack.set(DataComponents.CUSTOM_DATA, CustomData.of(releaseTag));
            }
            level.addFreshEntity(entity);
            return true;
        }
        return false;
    }

    @Override
    public Component getName(ItemStack stack) {
        final var entity = getEntityType(stack);
        if (entity != null) {
            final var eName = Component.translatable(entity.getDescriptionId());
            return Component.translatable(super.getDescriptionId(stack))
                    .append(" (")
                    .append(eName)
                    .append(")");
        }
        return super.getName(stack);
    }

    @Nullable
    public static EntityType<?> getEntityType(ItemStack stack) {
        if (!stack.has(DataComponents.CUSTOM_DATA)) return null;
        CompoundTag checkTag = stack.get(DataComponents.CUSTOM_DATA).copyTag();
        if (checkTag.contains(CAPTURED_ENTITY_TAG, Tag.TAG_COMPOUND)) {
            final var typeStr = checkTag.getCompound(CAPTURED_ENTITY_TAG).getString(ENTITY_TYPE_TAG);
            final var rl = ResourceLocation.parse(typeStr);
            return BuiltInRegistries.ENTITY_TYPE.getOptional(rl).orElse(null);
        }
        return null;
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, Item.TooltipContext context, List<Component> p_41423_, TooltipFlag p_41424_) {
        final var entity = getEntityType(p_41421_);
        if (entity != null) {
            p_41423_.add(Component.translatable("explanation_" + entity.getDescriptionId()));
        }
    }
}
