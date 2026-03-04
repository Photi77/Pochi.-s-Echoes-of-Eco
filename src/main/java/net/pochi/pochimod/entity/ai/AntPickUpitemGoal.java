package net.pochi.pochimod.entity.ai;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.pochi.pochimod.entity.custom.Ant;

import java.util.List;

public class AntPickUpitemGoal extends Goal {

    private final Ant entity;

    public AntPickUpitemGoal(Ant entity) {
        this.entity = entity;
    }


    @Override
    public boolean canUse() {
        AABB boundingBox = entity.getBoundingBox().inflate(10.0D, 10.0D, 10.0D); // 半径5ブロックの範囲を探索
        List<ItemEntity> nearbyItems = entity.level().getEntitiesOfClass(ItemEntity.class, boundingBox);

        // 見つけたアイテムの中から拾えるものがあるか確認
        for (ItemEntity itemEntity : nearbyItems) {
            if (entity.getInventory().isEmpty() || entity.getInventory().getItem(0).is(itemEntity.getItem().copy().getItem())) {
                // そのアイテムエンティティに向かって移動
                return true;
            }
            break;
        }

        return false;
    }

    @Override
    public void tick() {
        // アイテムを拾う
        List<ItemEntity> items = this.entity.level().getEntitiesOfClass(ItemEntity.class, this.entity.getBoundingBox().inflate(10.0D));
        if (!items.isEmpty()) {
            ItemEntity item = items.get(0);
            this.entity.getNavigation().moveTo(item, 1.0D);  // アイテムに向かって移動
        }
    }

    public boolean canPickUpItem(ItemStack itemStack) {
        // インベントリ内に空のスロットがあるか、同じアイテムがあるか確認
        for (int i = 0; i < entity.getInventory().getContainerSize(); i++) {
            ItemStack slotStack = entity.getInventory().getItem(i);

            // 空のスロットがある場合、または同じアイテムでスタック可能な場合
            if (slotStack.isEmpty() || (slotStack.is(itemStack.getItem()) && slotStack.getCount() < slotStack.getMaxStackSize())) {
                return true;
            }
        }

        return false; // インベントリが満杯でアイテムを拾えない
    }
}
