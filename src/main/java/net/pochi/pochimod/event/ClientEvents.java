package net.pochi.pochimod.event;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.ShoulderRidingEntity;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.Rhino;
import net.pochi.pochimod.item.ModItems;
import net.pochi.pochimod.client.renderer.MineralItemRenderer;
import net.pochi.pochimod.mineral.MineralChunkItem;
import net.pochi.pochimod.mineral.tools.AbstractMineralItem;
import net.pochi.pochimod.networking.ModMessages;
import net.pochi.pochimod.networking.packet.RhinoChargePacket;
import net.pochi.pochimod.nutrition.VitalHudOverlay;
import net.pochi.pochimod.screen.*;
import net.pochi.pochimod.util.KeyBinding;
import org.lwjgl.glfw.GLFW;

import static net.pochi.pochimod.entity.ModEntityTypes.*;
import static net.pochi.pochimod.entity.ModEntityTypes.ANCIENT_LIZARD;
import static net.pochi.pochimod.entity.ModEntityTypes.ANT;
import static net.pochi.pochimod.entity.ModEntityTypes.ASIAN_BEAR;
import static net.pochi.pochimod.entity.ModEntityTypes.BEAVER;
import static net.pochi.pochimod.entity.ModEntityTypes.BURROWING_OWL;
import static net.pochi.pochimod.entity.ModEntityTypes.BUTTERFLY;
import static net.pochi.pochimod.entity.ModEntityTypes.CAPYBARA;
import static net.pochi.pochimod.entity.ModEntityTypes.CICADA;
import static net.pochi.pochimod.entity.ModEntityTypes.CLIONE;
import static net.pochi.pochimod.entity.ModEntityTypes.CROCODILE;
import static net.pochi.pochimod.entity.ModEntityTypes.DRAGONFLY;
import static net.pochi.pochimod.entity.ModEntityTypes.EMU;
import static net.pochi.pochimod.entity.ModEntityTypes.FLOWER_MANTIS;
import static net.pochi.pochimod.entity.ModEntityTypes.FOLIVORE;
import static net.pochi.pochimod.entity.ModEntityTypes.GIANT_OTTER;
import static net.pochi.pochimod.entity.ModEntityTypes.GIRAFFE;
import static net.pochi.pochimod.entity.ModEntityTypes.GUYANA_RUPICOLA;
import static net.pochi.pochimod.entity.ModEntityTypes.HARPY_EAGLE;
import static net.pochi.pochimod.entity.ModEntityTypes.HERMIT_CRAB;
import static net.pochi.pochimod.entity.ModEntityTypes.IBERIAN_PIG;
import static net.pochi.pochimod.entity.ModEntityTypes.KIWI;
import static net.pochi.pochimod.entity.ModEntityTypes.LEOPARD_GECKO;
import static net.pochi.pochimod.entity.ModEntityTypes.LONG_TIT;
import static net.pochi.pochimod.entity.ModEntityTypes.MINI_HIPO;
import static net.pochi.pochimod.entity.ModEntityTypes.MONGOOSE;
import static net.pochi.pochimod.entity.ModEntityTypes.MUSK_CAT;
import static net.pochi.pochimod.entity.ModEntityTypes.PEACOCK;
import static net.pochi.pochimod.entity.ModEntityTypes.PERISSO;
import static net.pochi.pochimod.entity.ModEntityTypes.QUOKKA;
import static net.pochi.pochimod.entity.ModEntityTypes.RATEL;
import static net.pochi.pochimod.entity.ModEntityTypes.RHINO;
import static net.pochi.pochimod.entity.ModEntityTypes.ROCK_PENGUIN;
import static net.pochi.pochimod.entity.ModEntityTypes.SKUNK;
import static net.pochi.pochimod.entity.ModEntityTypes.SNAKE;
import static net.pochi.pochimod.entity.ModEntityTypes.SOOTY_SHEARWATER;
import static net.pochi.pochimod.entity.ModEntityTypes.SQUIRREL;
import static net.pochi.pochimod.entity.ModEntityTypes.URAL_OWL;
import static net.pochi.pochimod.entity.ModEntityTypes.WOMBAT;
import static net.pochi.pochimod.entity.ModEntityTypes.WOOD_PECKER;

public class ClientEvents {
    @EventBusSubscriber(modid = PochiMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        private static boolean wasControlPressed = false;

        @SubscribeEvent
        public static void onClientTickRhino(ClientTickEvent.Post event) {
            handleRhinoInput();
        }

        private static void handleRhinoInput() {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null) {
                return;
            }

            LocalPlayer player = mc.player;

            if (player.getVehicle() instanceof Rhino rhino) {
                // Ctrl繧ｭ繝ｼ縺ｮ迥ｶ諷九ｒ蜿門ｾ・
                boolean controlPressed = InputConstants.isKeyDown(
                        mc.getWindow().getWindow(),
                        GLFW.GLFW_KEY_LEFT_CONTROL
                ) || InputConstants.isKeyDown(
                        mc.getWindow().getWindow(),
                        GLFW.GLFW_KEY_RIGHT_CONTROL
                );

                // Ctrl繧ｭ繝ｼ縺梧款縺輔ｌ縺溽椪髢薙ｒ讀懃衍
                if (controlPressed && !wasControlPressed) {
                    // 繧ｵ繝ｼ繝舌・縺ｫ遯・ｲ髢句ｧ九ヱ繧ｱ繝・ヨ繧帝∽ｿ｡
                    ModMessages.sendToServer(new RhinoChargePacket());
                }

                wasControlPressed = controlPressed;
            } else {
                wasControlPressed = false;
            }
        }
    }

    @EventBusSubscriber(modid = PochiMod.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {

        @SubscribeEvent
        public static void onRegisterSpawnPlacements(RegisterSpawnPlacementsEvent event) {
            event.register(
                    SPARROW.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    ShoulderRidingEntity::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(DEER.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    AbstractHorse::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(DOE.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    AbstractHorse::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(CICADA.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(DRAGONFLY.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(BUTTERFLY.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(LONG_TIT.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Goat::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            //event.register(SEAL.get(),
            //        SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            //        Seal::checkSealSpawnRules,
            //        RegisterSpawnPlacementsEvent.Operation.REPLACE
            //);

            event.register(HERMIT_CRAB.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(MINI_HIPO.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(MONGOOSE.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(ANT.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            //event.register(ETUPIRKA.get(),
            //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            //        Animal::checkAnimalSpawnRules,
            //        RegisterSpawnPlacementsEvent.Operation.REPLACE
            //);

            event.register(SNAKE.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(PEACOCK.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(BURROWING_OWL.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(FOLIVORE.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(GUYANA_RUPICOLA.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(GIANT_OTTER.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(HARPY_EAGLE.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(PERISSO.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(RATEL.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(MUSK_CAT.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(WOMBAT.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(BEAVER.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(KIWI.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(SKUNK.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(ROCK_PENGUIN.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Goat::checkGoatSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(SOOTY_SHEARWATER.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Goat::checkGoatSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(QUOKKA.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(WOOD_PECKER.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(IBERIAN_PIG.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(EMU.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(CAPYBARA.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(GIRAFFE.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(CLIONE.get(),
                    SpawnPlacementTypes.IN_WATER, Heightmap.Types.OCEAN_FLOOR,
                    Squid::checkMobSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );



            event.register(ASIAN_BEAR.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            //event.register(ELECTRIC_CATFISH.get(),
            //        SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            //        Animal::checkAnimalSpawnRules,
            //        RegisterSpawnPlacementsEvent.Operation.REPLACE
            //);

            event.register(FLOWER_MANTIS.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(ANCIENT_LIZARD.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(URAL_OWL.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(LEOPARD_GECKO.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(CROCODILE.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(RHINO.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            event.register(SQUIRREL.get(),
                    SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
            );

            //event.register(HAMMER_HEAD.get(),
            //        SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            //        WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
//
            //event.register(LEAFY_SEA.get(),
            //        SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            //        WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
//
            //event.register(STURGEON.get(),
            //        SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            //        WaterAnimal::checkSurfaceWaterAnimalSpawnRules);

        }

        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(ModMenuTypes.BFURNACE_MENU.get(), BFurnaceScreen::new);
            event.register(ModMenuTypes.DISTILLER_MENU.get(), DistillerScreen::new);
            event.register(ModMenuTypes.SAKEDARU_MENU.get(), SakeDaruScreen::new);
            event.register(ModMenuTypes.FRYPAN_MENU.get(), FrypanScreen::new);
            event.register(ModMenuTypes.MIXER_MENU.get(), MixerScreen::new);

        }
        /**
         * 繧｢繧､繝・Β繧ｫ繝ｩ繝ｼ繝上Φ繝峨Λ繝ｼ逋ｻ骭ｲ
         * mineral_chunk縺ｮ蜍慕噪逹濶ｲ繧偵％縺薙〒逋ｻ骭ｲ
         */
        @SubscribeEvent
        public static void registerMineralColors(RegisterColorHandlersEvent.Item event) {
            // mineral_chunk: MineralData の color_hex を tintIndex=0 に適用
            event.register(
                    MineralChunkItem::getItemColor,
                    ModItems.MINERAL_CHUNK.get()
            );

            // mineral ツール類: ToolData の color_hex を tintIndex=0 に適用
            // item/generated / item/handheld は layer0 → tintIndex=0 を自動付与するため
            // 各 model JSON に tintindex を書く必要はない
            event.register(
                    AbstractMineralItem::getItemColor,
                    ModItems.MINERAL_SWORD.get(),
                    ModItems.MINERAL_AXE.get(),
                    ModItems.MINERAL_PICKAXE.get(),
                    ModItems.MINERAL_SHOVEL.get(),
                    ModItems.MINERAL_HELMET.get(),
                    ModItems.MINERAL_CHESTPLATE.get(),
                    ModItems.MINERAL_LEGGINGS.get(),
                    ModItems.MINERAL_BOOTS.get()
            );
        }


        @SubscribeEvent
        public static void registerGuiLayers(RegisterGuiLayersEvent event) {
            event.registerAboveAll(
                    net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "vital_hud"),
                    new VitalHudOverlay()
            );
        }

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.JUMP_KEY);
            event.register(KeyBinding.RIGHT);
        }

        @SubscribeEvent
        public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
            // 髯ｶ蝎ｨ繧｢繧､繝・Β縺ｮ濶ｲ繧・NBT 縺九ｉ蜿門ｾ・
            event.register((stack, tintIndex) -> {
                if (tintIndex == 0) {
                    net.minecraft.world.item.component.CustomData customData = stack.get(net.minecraft.core.component.DataComponents.CUSTOM_DATA);
                    if (customData != null) {
                        net.minecraft.nbt.CompoundTag tag = customData.copyTag();
                        if (tag.contains("GlazeColor")) {
                            return tag.getInt("GlazeColor");
                        }
                    }
                }
                return 0xFFFFFF;
            }, ModItems.FIRED_POTTERY.get(), ModItems.UNFIRED_POTTERY.get());
        }

        //@SubscribeEvent
        //public static void onCommandRegister(RegisterCommandsEvent event) {
        //    ExportPotteryCommand.register(event.getDispatcher());
        //}

        //@SubscribeEvent
        //public static void registerChunk(RegisterEvent event){
        //    event.register(Registries.CHUNK_GENERATOR,codecRegisterHelper ->
        //            codecRegisterHelper.register(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "guyane"), GuyaneMountain.CODEC));
        //}

        /** mineral 系アイテムの BEWLR 用ベースモデルを登録（MOD バスのみで動作） */
        @SubscribeEvent
        public static void onRegisterAdditionalModels(net.neoforged.neoforge.client.event.ModelEvent.RegisterAdditional event) {
            MineralItemRenderer.registerModels(event);
        }
    }
}

