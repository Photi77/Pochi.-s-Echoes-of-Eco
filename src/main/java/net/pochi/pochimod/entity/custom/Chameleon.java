package net.pochi.pochimod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;

public class Chameleon extends PathfinderMob implements GeoEntity {
    private static final EntityDataAccessor<String> CURRENT_BLOCK_TEXTURE = 
        SynchedEntityData.defineId(Chameleon.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Boolean> IS_CAMOUFLAGED = 
        SynchedEntityData.defineId(Chameleon.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Float> CAMOUFLAGE_ALPHA = 
        SynchedEntityData.defineId(Chameleon.class, EntityDataSerializers.FLOAT);
    
    private String currentBlockTexture = "";
    private BlockState lastGroundBlock = Blocks.STONE.defaultBlockState();
    private int textureCooldown = 0;
    private int camouflageTimer = 0;
    private boolean isTransitioning = false;
    
    // アニメーション用
    public float eyeRotationY = 0.0f;
    public float prevEyeRotationY = 0.0f;
    public float eyeRotationX = 0.0f; 
    public float prevEyeRotationX = 0.0f;
    public float tongueExtension = 0.0f;
    public float prevTongueExtension = 0.0f;
    
    public Chameleon(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }
    
    @Override
    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CURRENT_BLOCK_TEXTURE, "");
        builder.define(IS_CAMOUFLAGED, false);
        builder.define(CAMOUFLAGE_ALPHA, 1.0f);
    }
    
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Player.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(4, new ChameleonStalkGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }
    
    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
            .add(Attributes.MAX_HEALTH, 8.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.25D)
            .add(Attributes.ARMOR, 2.0D);
    }
    
    @Override
    public void tick() {
        super.tick();
        
        // アニメーション更新
        updateAnimations();
        
        // テクスチャ更新（サーバー側のみ）
        if (!this.level().isClientSide) {
            updateCamouflageTexture();
            updateCamouflageState();
        }
    }
    
    private void updateAnimations() {
        this.prevEyeRotationY = this.eyeRotationY;
        this.prevEyeRotationX = this.eyeRotationX;
        this.prevTongueExtension = this.tongueExtension;
        
        // 目の動き（独立して動く）
        if (this.getTarget() != null) {
            LivingEntity target = this.getTarget();
            Vec3 eyePos = this.getEyePosition();
            Vec3 targetPos = target.getEyePosition();
            Vec3 diff = targetPos.subtract(eyePos).normalize();
            
            this.eyeRotationY = (float) Math.atan2(diff.x, diff.z);
            this.eyeRotationX = (float) Math.asin(-diff.y);
        } else {
            // ランダムな目の動き
            if (this.random.nextFloat() < 0.02f) {
                this.eyeRotationY += (this.random.nextFloat() - 0.5f) * 0.5f;
                this.eyeRotationX += (this.random.nextFloat() - 0.5f) * 0.3f;
            }
        }
        
        // 舌の動き（獲物を狙う時）
        if (this.getTarget() != null && this.distanceToSqr(this.getTarget()) < 4.0) {
            this.tongueExtension = Math.min(this.tongueExtension + 0.1f, 1.0f);
        } else {
            this.tongueExtension = Math.max(this.tongueExtension - 0.05f, 0.0f);
        }
    }
    
    private void updateCamouflageTexture() {
        if (textureCooldown > 0) {
            textureCooldown--;
            return;
        }
        
        // 真下のブロックを取得
        BlockPos belowPos = this.blockPosition().below();
        BlockState belowBlock = this.level().getBlockState(belowPos);
        
        // 前回と異なるブロックの場合のみ更新
        if (!belowBlock.equals(lastGroundBlock)) {
            this.lastGroundBlock = belowBlock;
            updateTextureFromBlock(belowBlock);
            textureCooldown = 10; // 0.5秒のクールダウン
        }
    }
    
    private void updateTextureFromBlock(BlockState blockState) {
        Block block = blockState.getBlock();
        ResourceLocation blockRegistryName = net.minecraft.core.registries.BuiltInRegistries.BLOCK.getKey(block);
        
        if (blockRegistryName != null) {
            // ブロックのテクスチャパスを構築
            String texturePath = blockRegistryName.getNamespace() + ":block/" + blockRegistryName.getPath();
            
            // 特定のブロックに対する特別な処理
            texturePath = getSpecialTextureForBlock(block, blockState, texturePath);
            
            if (!texturePath.equals(this.currentBlockTexture)) {
                this.currentBlockTexture = texturePath;
                this.entityData.set(CURRENT_BLOCK_TEXTURE, texturePath);
                this.isTransitioning = true;
                
                System.out.println("Chameleon adapted to: " + texturePath);
            }
        }
    }
    
    private String getSpecialTextureForBlock(Block block, BlockState blockState, String defaultPath) {
        // 特定のブロックに対する特別なテクスチャ処理
        if (block == Blocks.GRASS_BLOCK) {
            return "minecraft:block/grass_block_top";
        } else if (block == Blocks.MYCELIUM) {
            return "minecraft:block/mycelium_top";
        } else if (block == Blocks.PODZOL) {
            return "minecraft:block/podzol_top";
        } else if (block == Blocks.FARMLAND) {
            return "minecraft:block/farmland";
        } else if (block == Blocks.DIRT_PATH) {
            return "minecraft:block/dirt_path_top";
        } else if (block.toString().contains("log")) {
            // 原木の場合は上面テクスチャを使用
            return defaultPath.replace("_log", "_log_top");
        } else if (block.toString().contains("leaves")) {
            // 葉ブロックの場合
            return defaultPath;
        }
        
        return defaultPath;
    }
    
    private void updateCamouflageState() {
        boolean shouldCamouflage = shouldActivateCamouflage();
        boolean isCamouflaged = this.entityData.get(IS_CAMOUFLAGED);
        
        if (shouldCamouflage && !isCamouflaged) {
            // カモフラージュ開始
            this.entityData.set(IS_CAMOUFLAGED, true);
            this.camouflageTimer = 0;
        } else if (!shouldCamouflage && isCamouflaged) {
            // カモフラージュ終了
            this.entityData.set(IS_CAMOUFLAGED, false);
            this.camouflageTimer = 0;
        }
        
        // カモフラージュのアルファ値を更新
        if (isCamouflaged) {
            this.camouflageTimer++;
            float alpha = Math.max(0.3f, 1.0f - (this.camouflageTimer / 60.0f)); // 3秒でほぼ透明
            this.entityData.set(CAMOUFLAGE_ALPHA, alpha);
        } else {
            this.camouflageTimer++;
            float alpha = Math.min(1.0f, 0.3f + (this.camouflageTimer / 40.0f)); // 2秒で完全表示
            this.entityData.set(CAMOUFLAGE_ALPHA, alpha);
        }
    }
    
    private boolean shouldActivateCamouflage() {
        // プレイヤーが近くにいない、かつ動いていない場合にカモフラージュ
        Player nearestPlayer = this.level().getNearestPlayer(this, 8.0);
        boolean playerNearby = nearestPlayer != null;
        boolean isMoving = this.getDeltaMovement().horizontalDistanceSqr() > 0.001;
        
        return !playerNearby && !isMoving && this.onGround();
    }
    
    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("CurrentBlockTexture", this.currentBlockTexture);
        compound.putBoolean("IsCamouflaged", this.entityData.get(IS_CAMOUFLAGED));
        compound.putFloat("CamouflageAlpha", this.entityData.get(CAMOUFLAGE_ALPHA));
        compound.putInt("CamouflageTimer", this.camouflageTimer);
    }
    
    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.currentBlockTexture = compound.getString("CurrentBlockTexture");
        this.entityData.set(CURRENT_BLOCK_TEXTURE, this.currentBlockTexture);
        this.entityData.set(IS_CAMOUFLAGED, compound.getBoolean("IsCamouflaged"));
        this.entityData.set(CAMOUFLAGE_ALPHA, compound.getFloat("CamouflageAlpha"));
        this.camouflageTimer = compound.getInt("CamouflageTimer");
    }
    
    // 公開メソッド
    public String getCurrentBlockTexture() {
        return this.entityData.get(CURRENT_BLOCK_TEXTURE);
    }
    
    public boolean isCamouflaged() {
        return this.entityData.get(IS_CAMOUFLAGED);
    }
    
    public float getCamouflageAlpha() {
        return this.entityData.get(CAMOUFLAGE_ALPHA);
    }
    
    public float getEyeRotationY(float partialTick) {
        return Mth.lerp(partialTick, this.prevEyeRotationY, this.eyeRotationY);
    }
    
    public float getEyeRotationX(float partialTick) {
        return Mth.lerp(partialTick, this.prevEyeRotationX, this.eyeRotationX);
    }
    
    public float getTongueExtension(float partialTick) {
        return Mth.lerp(partialTick, this.prevTongueExtension, this.tongueExtension);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return null;
    }

    // カスタムAIゴール
    static class ChameleonStalkGoal extends Goal {
        private final Chameleon chameleon;
        private final double speedModifier;
        private LivingEntity target;
        private int stalkTime;
        
        public ChameleonStalkGoal(Chameleon chameleon, double speedModifier) {
            this.chameleon = chameleon;
            this.speedModifier = speedModifier;
        }
        
        @Override
        public boolean canUse() {
            this.target = this.chameleon.level().getNearestPlayer(this.chameleon, 10.0);
            return this.target != null && !this.chameleon.isCamouflaged();
        }
        
        @Override
        public void start() {
            this.stalkTime = 0;
        }
        
        @Override
        public void tick() {
            this.stalkTime++;
            
            if (this.target != null) {
                double distance = this.chameleon.distanceToSqr(this.target);
                
                if (distance > 16.0) {
                    // 遠すぎる場合は近づく
                    this.chameleon.getNavigation().moveTo(this.target, this.speedModifier);
                } else if (distance < 4.0) {
                    // 近すぎる場合は離れる
                    this.chameleon.getNavigation().moveTo(
                        this.chameleon.getX() - (this.target.getX() - this.chameleon.getX()),
                        this.chameleon.getY(),
                        this.chameleon.getZ() - (this.target.getZ() - this.chameleon.getZ()),
                        this.speedModifier
                    );
                } else {
                    // 適度な距離でじっと見つめる
                    this.chameleon.getLookControl().setLookAt(this.target);
                    this.chameleon.getNavigation().stop();
                }
                
                // 一定時間後にカモフラージュモードに入る
                if (this.stalkTime > 100 && this.chameleon.random.nextFloat() < 0.02f) {
                    this.chameleon.getNavigation().stop();
                }
            }
        }
        
        @Override
        public boolean canContinueToUse() {
            return this.target != null && this.target.isAlive() && 
                   this.chameleon.distanceToSqr(this.target) < 144.0; // 12ブロック以内
        }
    }
}