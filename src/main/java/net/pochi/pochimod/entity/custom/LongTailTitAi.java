package net.pochi.pochimod.entity.custom;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public class LongTailTitAi {

    protected static Brain<?> makeBrain(Brain<LongTailTit> p_218420_) {
        initCoreActivity(p_218420_);
        initIdleActivity(p_218420_);
        p_218420_.setCoreActivities(ImmutableSet.of(Activity.CORE));
        p_218420_.setDefaultActivity(Activity.IDLE);
        p_218420_.useDefaultActivity();
        return p_218420_;
    }

    private static void initCoreActivity(Brain<LongTailTit> p_218426_) {
        p_218426_.addActivity(Activity.CORE, 0, ImmutableList.<net.minecraft.world.entity.ai.behavior.BehaviorControl<? super LongTailTit>>of(new Swim(0.8F), new AnimalPanic(2.5F), new LookAtTargetSink(45, 90), new MoveToTargetSink(), new CountDownCooldownTicks(MemoryModuleType.LIKED_NOTEBLOCK_COOLDOWN_TICKS), new CountDownCooldownTicks(MemoryModuleType.ITEM_PICKUP_COOLDOWN_TICKS)));
    }

    private static void initIdleActivity(Brain<LongTailTit> p_218432_) {
        p_218432_.addActivityWithConditions(Activity.IDLE, ImmutableList.of(Pair.of(0, GoToWantedItem.create((p_218428_) -> {
            return true;
        }, 1.75F, true, 32)), Pair.of(1, new GoAndGiveItemsToTarget<>(LongTailTitAi::getItemDepositPosition, 2.25F, 20)), Pair.of(2, StayCloseToTarget.create(LongTailTitAi::getItemDepositPosition, Predicate.not(LongTailTitAi::hasWantedItem), 4, 16, 2.25F)), Pair.of(3, SetEntityLookTargetSometimes.create(6.0F, UniformInt.of(30, 60))), Pair.of(4, new RunOne<>(ImmutableList.of(Pair.of(RandomStroll.fly(1.0F), 2), Pair.of(SetWalkTargetFromLookTarget.create(1.0F, 3), 2), Pair.of(new DoNothing(30, 60), 1))))), ImmutableSet.of());
    }

    public static void updateActivity(LongTailTit p_218422_) {
        p_218422_.getBrain().setActiveActivityToFirstValid(ImmutableList.of(Activity.IDLE));
    }

    public static void hearNoteblock(LivingEntity p_218417_, BlockPos p_218418_) {
        Brain<?> brain = p_218417_.getBrain();
        GlobalPos globalpos = GlobalPos.of(p_218417_.level().dimension(), p_218418_);
        Optional<GlobalPos> optional = brain.getMemory(MemoryModuleType.LIKED_NOTEBLOCK_POSITION);
        if (optional.isEmpty()) {
            brain.setMemory(MemoryModuleType.LIKED_NOTEBLOCK_POSITION, globalpos);
            brain.setMemory(MemoryModuleType.LIKED_NOTEBLOCK_COOLDOWN_TICKS, 600);
        } else if (optional.get().equals(globalpos)) {
            brain.setMemory(MemoryModuleType.LIKED_NOTEBLOCK_COOLDOWN_TICKS, 600);
        }

    }

    private static Optional<PositionTracker> getItemDepositPosition(LivingEntity p_218424_) {
        Brain<?> brain = p_218424_.getBrain();
        Optional<GlobalPos> optional = brain.getMemory(MemoryModuleType.LIKED_NOTEBLOCK_POSITION);
        if (optional.isPresent()) {
            GlobalPos globalpos = optional.get();
            if (shouldDepositItemsAtLikedNoteblock(p_218424_, brain, globalpos)) {
                return Optional.of(new BlockPosTracker(globalpos.pos().above()));
            }

            brain.eraseMemory(MemoryModuleType.LIKED_NOTEBLOCK_POSITION);
        }

        return getLikedPlayerPositionTracker(p_218424_);
    }

    private static boolean hasWantedItem(LivingEntity p_273346_) {
        Brain<?> brain = p_273346_.getBrain();
        return brain.hasMemoryValue(MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM);
    }

    private static boolean shouldDepositItemsAtLikedNoteblock(LivingEntity p_218413_, Brain<?> p_218414_, GlobalPos p_218415_) {
        Optional<Integer> optional = p_218414_.getMemory(MemoryModuleType.LIKED_NOTEBLOCK_COOLDOWN_TICKS);
        Level level = p_218413_.level();
        return level.dimension() == p_218415_.dimension() && level.getBlockState(p_218415_.pos()).is(Blocks.NOTE_BLOCK) && optional.isPresent();
    }

    private static Optional<PositionTracker> getLikedPlayerPositionTracker(LivingEntity p_218430_) {
        return getLikedPlayer(p_218430_).map((p_218409_) -> {
            return new EntityTracker(p_218409_, true);
        });
    }

    public static Optional<ServerPlayer> getLikedPlayer(LivingEntity p_218411_) {
        Level level = p_218411_.level();
        if (!level.isClientSide() && level instanceof ServerLevel serverlevel) {
            Optional<UUID> optional = p_218411_.getBrain().getMemory(MemoryModuleType.LIKED_PLAYER);
            if (optional.isPresent()) {
                Entity entity = serverlevel.getEntity(optional.get());
                if (entity instanceof ServerPlayer) {
                    ServerPlayer serverplayer = (ServerPlayer)entity;
                    if ((serverplayer.gameMode.isSurvival() || serverplayer.gameMode.isCreative()) && serverplayer.closerThan(p_218411_, 64.0D)) {
                        return Optional.of(serverplayer);
                    }
                }

                return Optional.empty();
            }
        }

        return Optional.empty();
    }
}
