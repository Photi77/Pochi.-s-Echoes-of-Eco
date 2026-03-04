package net.pochi.pochimod.entity.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.pochi.pochimod.entity.ModEntityTypes;

public class PickaxeHook extends FishingHook {

    public PickaxeHook(EntityType<? extends PickaxeHook> p_150141_, Level p_150142_, int p_150143_, int p_150144_) {
        super(p_150141_, p_150142_);
        this.noCulling = true;
    }

    public PickaxeHook(EntityType<? extends PickaxeHook> p_150138_, Level p_150139_) {
        this(p_150138_, p_150139_, 0, 0);
    }

    public PickaxeHook(Player p_37106_, Level p_37107_, int p_37108_, int p_37109_) {
        this(ModEntityTypes.PICKAXE_HOOK.get(), p_37107_, p_37108_, p_37109_);
        this.setOwner(p_37106_);
        float f = p_37106_.getXRot();
        float f1 = p_37106_.getYRot();
        float f2 = Mth.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = Mth.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -Mth.cos(-f * ((float)Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float)Math.PI / 180F));
        double d0 = p_37106_.getX() - (double)f3 * 0.3D;
        double d1 = p_37106_.getEyeY();
        double d2 = p_37106_.getZ() - (double)f2 * 0.3D;
        this.moveTo(d0, d1, d2, f1, f);
        Vec3 vec3 = new Vec3((double)(-f3), (double)Mth.clamp(-(f5 / f4), -5.0F, 5.0F), (double)(-f2));
        double d3 = vec3.length();
        vec3 = vec3.multiply(0.6D / d3 + this.random.triangle(0.5D, 0.0103365D), 0.6D / d3 + this.random.triangle(0.5D, 0.0103365D), 0.6D / d3 + this.random.triangle(0.5D, 0.0103365D));
        this.setDeltaMovement(vec3);
        this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * (double)(180F / (float)Math.PI)));
        this.setXRot((float)(Mth.atan2(vec3.y, vec3.horizontalDistance()) * (double)(180F / (float)Math.PI)));
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
    }

    @Override
    protected void pullEntity(Entity p_150156_) {
        Entity entity = this.getOwner();
        if (entity != null) {
            Vec3 vec3 = (new Vec3(entity.getX() - this.getX(), 30 , entity.getZ() - this.getZ())).scale(0.1D);
            p_150156_.setDeltaMovement(p_150156_.getDeltaMovement().add(vec3));
        }
    }

    @Override
    public int retrieve(ItemStack p_37157_) {
        Player player = this.getPlayerOwner();
        if (!this.level().isClientSide && player != null && !this.shouldStopFishing(player)) {
            for (int x = -3; x <= 6; x++) {
                for (int y = -3; y <= 6; y++) {
                    for (int z = -3; z <= 6; z++) {
                        BlockPos pos2 = BlockPos.containing(this.getX() + x, this.getY() - y, this.getZ() + z);
                        DeltaMovementBlockEntity redShell = new DeltaMovementBlockEntity(this.level(), player.getLookAngle().x, player.getLookAngle().y, player.getLookAngle().z,player, level().getBlockState(pos2));
                        redShell.setBlockState(level().getBlockState(pos2));
                        redShell.setDeltaMovement(0, 0.5, 0);
                        redShell.setPos(pos2.getCenter().x,pos2.getCenter().y,pos2.getCenter().z);
                        this.level().addFreshEntity(redShell);

                        this.level().setBlock(pos2, Blocks.AIR.defaultBlockState(), 11);
                    }
                }
            }
        }
        return super.retrieve(p_37157_);
    }

    private boolean shouldStopFishing(Player p_37137_) {
        ItemStack itemstack = p_37137_.getMainHandItem();
        ItemStack itemstack1 = p_37137_.getOffhandItem();
        boolean flag = itemstack.canPerformAction(net.neoforged.neoforge.common.ItemAbilities.FISHING_ROD_CAST);
        boolean flag1 = itemstack1.canPerformAction(net.neoforged.neoforge.common.ItemAbilities.FISHING_ROD_CAST);
        if (!p_37137_.isRemoved() && p_37137_.isAlive() && (flag || flag1) && !(this.distanceToSqr(p_37137_) > 1024.0D)) {
            return false;
        } else {
            this.discard();
            return true;
        }
    }
}