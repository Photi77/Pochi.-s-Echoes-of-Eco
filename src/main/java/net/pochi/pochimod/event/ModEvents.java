package net.pochi.pochimod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.level.ChunkDataEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.effect.Dreamer;
import net.pochi.pochimod.effect.ModEffects;
import net.pochi.pochimod.entity.ModEntityTypes;
import net.pochi.pochimod.entity.custom.*;
import net.pochi.pochimod.entity.projectile.DecoyTailEntity;
import net.pochi.pochimod.entity.projectile.SummonedClioneEntity;
import net.pochi.pochimod.item.ModItems;
import net.pochi.pochimod.world.biome.ModBiomes;
import net.pochi.pochimod.world.biome.chunkgen.CalderaSurfaceBuilder;

import java.util.List;
import java.util.Random;

public class ModEvents {

    @EventBusSubscriber(modid = PochiMod.MOD_ID)
    public static class ForgeEvents {

        private static final CalderaSurfaceBuilder surfaceBuilder = new CalderaSurfaceBuilder();

        @SubscribeEvent
        public static void onChunkSave(ChunkDataEvent.Save event) {
            ChunkAccess chunk = event.getChunk();
            ChunkPos chunkPos = chunk.getPos();

            // 蛻晏屓逕滓・譎ゅ・縺ｿ蜃ｦ逅・ｼ医ち繧ｰ縺ｧ邂｡逅・ｼ・
            if (!event.getLevel().getChunkSource().hasChunk(chunkPos.x, chunkPos.z)) {
                return;
            }

            // 繧ｫ繝ｫ繝・Λ繝舌う繧ｪ繝ｼ繝繝√ぉ繝・け
            BlockPos centerPos = chunkPos.getWorldPosition().offset(8, 64, 8);
            if (event.getLevel().getBiome(centerPos).is(ModBiomes.CALDERA_BIOME)) {

                // 繧ｫ繧ｹ繧ｿ繝繧ｿ繧ｰ縺ｧ蜃ｦ逅・ｸ医∩縺九メ繧ｧ繝・け
                if (!event.getLevel().getChunkSource().hasChunk(chunkPos.x, chunkPos.z)) {

                    // 蝨ｰ蠖｢逕滓・
                    surfaceBuilder.buildSurfaceForChunk(chunk, chunkPos.x, chunkPos.z);

                    // 蜃ｦ逅・ｸ医∩繝槭・繧ｯ
                    event.getData().putBoolean("caldera_generated", true);

                    System.out.println("Generated Caldera terrain for chunk: " + chunkPos);
                }
            }
        }


        @SubscribeEvent
        public static void tonbo(LivingDeathEvent event){
            Random random = new Random();
            if(event.getEntity() instanceof Dragonfly dragonfly){
                dragonfly.spawnAtLocation(ModItems.DRAGONFLY_WINGS.get(),random.nextInt(3));
            }
        }

        @SubscribeEvent
        public static void addCustomTrades(VillagerTradesEvent event) {
            if(event.getType() == VillagerProfession.TOOLSMITH) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(ModItems.STAINLESS.get(),4);
                int villagerLevel = 1;

                trades.get(villagerLevel).add((trader,rand) -> new MerchantOffer(
                        new net.minecraft.world.item.trading.ItemCost(Items.EMERALD,2),
                        stack,10,8,0.02F));
            }

        }

        @SubscribeEvent
        public static void dreamer(LivingDamageEvent.Pre event){
            if(event.getEntity().hasEffect(ModEffects.DREAMER)){
                if(event.getEntity().getHealth() <= event.getNewDamage()) {
                    if (event.getEntity() instanceof Player player) {
                        event.setNewDamage(0);
                        if (Dreamer.getPos() != null) {
                            if(player.level() instanceof ServerLevel serverLevel0) {
                                ServerLevel destLevel = player.getServer().getLevel(Dreamer.getDimensionKey());
                                if(destLevel != null && !(player.level().dimension() == Dreamer.getDimensionKey())) {
                                    if(player.canChangeDimensions(serverLevel0, destLevel)) {
                                        player.changeDimension(new net.minecraft.world.level.portal.DimensionTransition(destLevel, player, net.minecraft.world.level.portal.DimensionTransition.DO_NOTHING));
                                    }
                                }
                            }
                            player.teleportTo(Dreamer.getPos().getX(), Dreamer.getPos().getY(), Dreamer.getPos().getZ());
                            player.removeEffect(ModEffects.DREAMER);
                        } else {
                            if(player.level() instanceof ServerLevel serverLevel0) {
                                ServerLevel overworld = player.getServer().getLevel(Level.OVERWORLD);
                                if(overworld != null && !(player.level().dimension() == Level.OVERWORLD)) {
                                    if(player.canChangeDimensions(serverLevel0, overworld)) {
                                        player.changeDimension(new net.minecraft.world.level.portal.DimensionTransition(overworld, player, net.minecraft.world.level.portal.DimensionTransition.DO_NOTHING));
                                    }
                                }
                            }
                            player.teleportTo(player.level().getSharedSpawnPos().getX(),player.level().getSharedSpawnPos().getY(),player.level().getSharedSpawnPos().getZ());
                            player.removeEffect(ModEffects.DREAMER);
                        }
                    }
                }
            }
        }

        //@SubscribeEvent
        //public static void cropOre(PlayerInteractEvent.RightClickBlock event) {
        //    Level level = event.getLevel();
        //    Player player = event.getEntity();
        //    Map<Item, Block> blockMap = new HashMap<>();
        //    blockMap.put(Items.DIAMOND, ModBlocks.CROP_DIAMOND.get());
        //    blockMap.put(Items.RAW_COPPER, ModBlocks.CROP_COPPER.get());
        //    blockMap.put(Items.RAW_GOLD, ModBlocks.CROP_GOLD.get());
        //    blockMap.put(Items.RAW_IRON, ModBlocks.CROP_IRON.get());
        //    blockMap.put(Items.LAPIS_LAZULI, ModBlocks.CROP_LAPIS.get());
        //    blockMap.put(Items.REDSTONE, ModBlocks.CROP_REDSTONE.get());
        //    blockMap.put(Items.COAL, ModBlocks.CROP_COAL.get());
        //    blockMap.put(Items.EMERALD, ModBlocks.CROP_EMERALD.get());
//
        //    if (!level.isClientSide) {
        //        if (level.getBlockState(event.getPos()).is(Blocks.FARMLAND)) {
        //            if (blockMap.containsKey(player.getMainHandItem().getItem())) {
        //                BlockPos pos = BlockPos.containing(event.getPos().getX(), event.getPos().getY() + 1,
        //                        event.getPos().getZ());
        //                event.getLevel().setBlock(pos,
        //                        blockMap.get(event.getEntity().getMainHandItem().getItem()).defaultBlockState(), 11);
        //                player.getMainHandItem().shrink(1);
//
        //            }
        //        }
        //    }
        //}

        @SubscribeEvent
        public static void axo(EntityTickEvent.Post event) {
            if (!(event.getEntity() instanceof Axolotl axolotl)) return;
           Level level = axolotl.level();
           {
               if(level.getBiome(axolotl.getOnPos()).is(ModBiomes.PAMUKKALE_BIOME)){
                   axolotl.setAirSupply(6000);
               }
           }
        }

        //@SubscribeEvent
        //public static void fall(LivingDamageEvent event) {
        //    if(!event.getEntity().level().isClientSide) {
        //        if (event.getEntity() instanceof Player player1) {
        //            if(event.getSource().is(DamageTypes.FALL)){
        //                if (player1.getInventory().getArmor(0).is(ModItems.PERISO_HELMET.get())) {
        //                    event.setCanceled(true);
        //                }
        //            }
        //        }
        //    }
        //}


        //@SubscribeEvent
        //public static void elyt(TickEvent.PlayerTickEvent event){
        //    if(event.player.getInventory().getArmor(3) == Items.ELYTRA.getDefaultInstance()){
        //        if(event.player.isFallFlying()) {
        //            List<LivingEntity> list = event.player.level().getEntitiesOfClass(LivingEntity.class, event.player.getBoundingBox().inflate(10, 10, 10));
        //            if (!list.isEmpty()) {
        //                for (LivingEntity entity : list) {
        //                    double d0 = event.player.distanceToSqr(entity.getX(), entity.getY(), entity.getZ());
        //                    if (d0 < 10.0D) {
        //                        if (!(entity instanceof Player)) {
        //                            entity.kill();
        //                        }
        //                    }
        //                }
        //            }
        //        }
        //    }
        //}

    }

    @EventBusSubscriber(modid = PochiMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(ModEntityTypes.SPARROW.get(), SparrowEntity.setAttributes());
            event.put(ModEntityTypes.DEER.get(), DeerEntity.setAttributes());
            event.put(ModEntityTypes.DOE.get(), DoeEntity.setAttributes());
            event.put(ModEntityTypes.SAKABAN.get(), Sakaban.setAttributes());
            event.put(ModEntityTypes.CICADA.get(), Cicada.setAttributes());
            event.put(ModEntityTypes.DRAGONFLY.get(), Dragonfly.setAttributes());
            event.put(ModEntityTypes.BUTTERFLY.get(), Butterfly.setAttributes());
            event.put(ModEntityTypes.LONG_TIT.get(), LongTailTit.setAttributes());
            event.put(ModEntityTypes.SEAL.get(), GiantOtter.createAttributes().build());
            event.put(ModEntityTypes.HERMIT_CRAB.get(), Chicken.createAttributes().build());
            event.put(ModEntityTypes.MINI_HIPO.get(), MiniHipo.createAttributes().build());
            event.put(ModEntityTypes.MONGOOSE.get(), Wolf.createAttributes().build());
            event.put(ModEntityTypes.ANT.get(), Wolf.createAttributes().build());
            event.put(ModEntityTypes.ETUPIRKA.get(), Etupirka.setAttributes());
            event.put(ModEntityTypes.SNAKE.get(), Silverfish.createAttributes().build());
            event.put(ModEntityTypes.PEACOCK.get(), Peacock.setAttributes());
            event.put(ModEntityTypes.BURROWING_OWL.get(), SparrowEntity.setAttributes());
            event.put(ModEntityTypes.FOLIVORE.get(), SparrowEntity.setAttributes());
            event.put(ModEntityTypes.GUYANA_RUPICOLA.get(), SparrowEntity.setAttributes());
            event.put(ModEntityTypes.GIANT_OTTER.get(), GiantOtter.setAttributes());
            event.put(ModEntityTypes.HARPY_EAGLE.get(), HarpyEagle.setAttributes());
            event.put(ModEntityTypes.PERISSO.get(), SparrowEntity.setAttributes());
            event.put(ModEntityTypes.RATEL.get(), Wolf.createAttributes().build());
            event.put(ModEntityTypes.MUSK_CAT.get(), Wolf.createAttributes().build());
            event.put(ModEntityTypes.WOMBAT.get(), Wolf.createAttributes().build());
            event.put(ModEntityTypes.BEAVER.get(), GiantOtter.setAttributes());
            event.put(ModEntityTypes.HAMMER_HEAD.get(), Dolphin.createAttributes().build());
            event.put(ModEntityTypes.LEAFY_SEA.get(), Dolphin.createAttributes().build());
            event.put(ModEntityTypes.KIWI.get(), Chicken.createAttributes().build());
            event.put(ModEntityTypes.ROCK_PENGUIN.get(), GiantOtter.setAttributes());
            event.put(ModEntityTypes.SKUNK.get(), Wolf.createAttributes().build());
            event.put(ModEntityTypes.STURGEON.get(), Dolphin.createAttributes().build());
            event.put(ModEntityTypes.QUOKKA.get(), Wolf.createAttributes().build());
            event.put(ModEntityTypes.WOOD_PECKER.get(), SparrowEntity.setAttributes());

            event.put(ModEntityTypes.FELIS.get(), Wolf.createAttributes().build());
            event.put(ModEntityTypes.FRUIT_FLY.get(), Bee.createAttributes().build());
            event.put(ModEntityTypes.INDICATOR_IDAE.get(), SparrowEntity.setAttributes());
            event.put(ModEntityTypes.MANTIS_SHRIMP.get(), MantisShrimp.createAttributes().build());
            event.put(ModEntityTypes.MEERKAT.get(), Wolf.createAttributes().build());
            event.put(ModEntityTypes.PALLAS_CAT.get(), Wolf.createAttributes().build());
            event.put(ModEntityTypes.PANGOLIN.get(), Wolf.createAttributes().build());
            event.put(ModEntityTypes.PORCUPINE.get(), Wolf.createAttributes().build());
            event.put(ModEntityTypes.TAPIR.get(), Wolf.createAttributes().build());

            event.put(ModEntityTypes.BETTA.get(), Betta.createLivingAttributes().build());
            //event.put(ModEntityTypes.ALBATROSS.get(), HarpyEagle.setAttributes());
            event.put(ModEntityTypes.IBERIAN_PIG.get(), IberianPig.createAttributes().build());
            event.put(ModEntityTypes.EMU.get(), Emu.createAttributes().build());
            event.put(ModEntityTypes.CAPYBARA.get(), Capybara.createAttributes().build());
            event.put(ModEntityTypes.CHAMELEON.get(), Chameleon.createAttributes().build());
            event.put(ModEntityTypes.PLATYPUS.get(), Wolf.createAttributes().build());
            event.put(ModEntityTypes.GIRAFFE.get(), Giraffe.createAttributes().build());
            event.put(ModEntityTypes.CLIONE.get(), Clione.createAttributes().build());
            event.put(ModEntityTypes.SOOTY_SHEARWATER.get(), SootyShearwater.createAttributes().build());
            event.put(ModEntityTypes.ASIAN_BEAR.get(), AsianBear.createAttributes().build());
            event.put(ModEntityTypes.ELECTRIC_CATFISH.get(), ElectricCatfish.createAttributes().build());
            event.put(ModEntityTypes.FLOWER_MANTIS.get(), Wolf.createAttributes().build());
            event.put(ModEntityTypes.CASSOWARY.get(), Cassowary.createAttributes().build());
            event.put(ModEntityTypes.ANCIENT_LIZARD.get(), AncientLizard.createAttributes().build());
            event.put(ModEntityTypes.MOLE.get(), Mole.createAttributes().build());
            event.put(ModEntityTypes.URAL_OWL.get(), HarpyEagle.setAttributes());
            event.put(ModEntityTypes.LEOPARD_GECKO.get(), LeopardGecko.createAttributes().build());
            event.put(ModEntityTypes.CROCODILE.get(), Wolf.createAttributes().build());
            event.put(ModEntityTypes.RHINO.get(), Rhino.createAttributes().build());
            event.put(ModEntityTypes.SQUIRREL.get(), Squirrel.createAttributes().build());


            event.put(ModEntityTypes.ORE_SEARCH.get(), Zombie.createAttributes().build());
            event.put(ModEntityTypes.DIRT_GOLEM.get(), LongTailTit.setAttributes());
            event.put(ModEntityTypes.PICKAXE_HEAD.get(), IronGolem.createAttributes().build());
            event.put(ModEntityTypes.SUMMONED_CLIONE.get(), SummonedClioneEntity.createAttributes().build());
            event.put(ModEntityTypes.DECOY_TAIL.get(), DecoyTailEntity.createAttributes().build());
        }
    }
}

