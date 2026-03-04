package net.pochi.pochimod;

import com.mojang.logging.LogUtils;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationFactory;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.pochi.pochimod.attachment.ModAttachments;
import net.pochi.pochimod.block.ModBlocks;
import net.pochi.pochimod.block.entity.ModBlockEntities;
import net.pochi.pochimod.block.entity.client.PotteryOnWheelRenderer;
import net.pochi.pochimod.config.VitalConfig;
import net.pochi.pochimod.effect.ModEffects;
import net.pochi.pochimod.enchantment.ModEnchantments;
import net.pochi.pochimod.entity.ModEntityTypes;
import net.pochi.pochimod.entity.client.*;
import net.pochi.pochimod.ferm.CropNutrientRegistry;
import net.pochi.pochimod.fluid.ModFluidTypes;
import net.pochi.pochimod.fluid.ModFluids;
import net.pochi.pochimod.item.ModCreativeModeTabs;
import net.pochi.pochimod.item.ModItemProperties;
import net.pochi.pochimod.item.ModItems;
import net.pochi.pochimod.item.custom.CompostItem;
import net.pochi.pochimod.loot.ModLootModifiers;
import net.pochi.pochimod.mineral.MineralEffectHandler;
import net.pochi.pochimod.networking.ModMessages;
import net.pochi.pochimod.nutrition.FoodNutritionRegistry;
import net.pochi.pochimod.potion.ModPotions;
import net.pochi.pochimod.recipe.ModRecipes;
import net.pochi.pochimod.screen.ModMenuTypes;
import net.pochi.pochimod.sound.ModSounds;
import net.pochi.pochimod.util.ModCauldronInteraction;
import net.pochi.pochimod.world.biome.ModTerraBlenderAPI;
import net.pochi.pochimod.world.biome.surface.ModSurfaceRules;
import net.pochi.pochimod.world.feature.ModFeature;
import net.pochi.pochimod.world.feature.ModTreeDecoratorType;
import org.slf4j.Logger;
import terrablender.api.SurfaceRuleManager;

import static net.pochi.pochimod.entity.ModEntityTypes.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(PochiMod.MOD_ID)
public class PochiMod {
    public static final String MOD_ID = "pochimod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public PochiMod(IEventBus modEventBus) {
        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModEntityTypes.register(modEventBus);
        ModEffects.register(modEventBus);
        ModSounds.register(modEventBus);
        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModTreeDecoratorType.register(modEventBus);
        ModTerraBlenderAPI.registerRegions();
        ModEnchantments.register(modEventBus);
        ModFeature.register(modEventBus);
        ModPotions.register(modEventBus);
        ModLoadingContext.get().getActiveContainer().registerConfig(ModConfig.Type.COMMON, VitalConfig.SPEC, "EchoesOfEco/vital.toml");
        // GeckoLib auto-initializes in 4.x
        ModAttachments.register(modEventBus);

        NeoForge.EVENT_BUS.register(MineralEffectHandler.class);

        modEventBus.addListener(this::commonSetup);
        //modEventBus.addListener(PochiMod::registerSpawnPlacements);

        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private static void run() {
        //SpawnPlacements.register(SPARROW.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        ShoulderRidingEntity::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(DEER.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        AbstractHorse::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(DOE.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        AbstractHorse::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(CICADA.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(DRAGONFLY.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(BUTTERFLY.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(LONG_TIT.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Goat::checkAnimalSpawnRules);
//
        ////SpawnPlacements.register(SEAL.get(),
        ////        SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        ////        Seal::checkSealSpawnRules);
//
        //SpawnPlacements.register(HERMIT_CRAB.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(MINI_HIPO.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(MONGOOSE.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(ANT.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        ////SpawnPlacements.register(ETUPIRKA.get(),
        ////        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        ////        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(SNAKE.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(PEACOCK.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(BURROWING_OWL.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(FOLIVORE.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(GUYANA_RUPICOLA.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(GIANT_OTTER.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(HARPY_EAGLE.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(PERISSO.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(RATEL.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(MUSK_CAT.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(WOMBAT.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(BEAVER.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(KIWI.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(SKUNK.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(ROCK_PENGUIN.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Goat::checkGoatSpawnRules);
//
        //SpawnPlacements.register(SOOTY_SHEARWATER.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Goat::checkGoatSpawnRules);
//
        //SpawnPlacements.register(QUOKKA.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(WOOD_PECKER.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(IBERIAN_PIG.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(EMU.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(CAPYBARA.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(GIRAFFE.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(CLIONE.get(),
        //        SpawnPlacementTypes.IN_WATER, Heightmap.Types.OCEAN_FLOOR,
        //        Squid::checkMobSpawnRules);
//
//
//
        //SpawnPlacements.register(ASIAN_BEAR.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        ////SpawnPlacements.register(ELECTRIC_CATFISH.get(),
        ////        SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        ////        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(FLOWER_MANTIS.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(ANCIENT_LIZARD.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(URAL_OWL.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(LEOPARD_GECKO.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(CROCODILE.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(RHINO.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);
//
        //SpawnPlacements.register(SQUIRREL.get(),
        //        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        Animal::checkAnimalSpawnRules);

        //SpawnPlacements.register(HAMMER_HEAD.get(),
        //        SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
//
        //SpawnPlacements.register(LEAFY_SEA.get(),
        //        SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
//
        //SpawnPlacements.register(STURGEON.get(),
        //        SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        //        WaterAnimal::checkSurfaceWaterAnimalSpawnRules);


        ModCauldronInteraction.bootstrap();
        ModMessages.regiser();
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeRules());
        FoodNutritionRegistry.init();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(PochiMod::run);
        CompostItem.registerDefaults();
        CropNutrientRegistry.registerDefaults();
        // CommonSetupイベント内
        //PochiMod.LOGGER.info("[CropRegistry] WHEAT登録確認={}",
        //        CropNutrientRegistry.shouldApplyQuality(Items.WHEAT));
    }


    private void addCreative(BuildCreativeModeTabContentsEvent event) {

        if(event.getTab() == ModCreativeModeTabs.POCHI_PLANT.get()){
            event.accept(ModBlocks.CABERNET_SAUVIGNON_LOG.get());
            event.accept(ModBlocks.CABERNET_SAUVIGNON_WOOD.get());
            event.accept(ModBlocks.STRIPPED_CABERNET_SAUVIGNON_LOG.get());
            event.accept(ModBlocks.STRIPPED_CABERNET_SAUVIGNON_WOOD.get());
            event.accept(ModBlocks.CABERNET_SAUVIGNON_PLANKS.get());
            event.accept(ModBlocks.CABERNET_SAUVIGNON_LEAVES.get());
            event.accept(ModBlocks.CABERNET_SAUVIGNON_SAPLING.get());
            event.accept(ModBlocks.MAPLE_LOG.get());
            event.accept(ModBlocks.MAPLE_WOOD.get());
            event.accept(ModBlocks.STRIPPED_MAPLE_LOG.get());
            event.accept(ModBlocks.STRIPPED_MAPLE_WOOD.get());
            event.accept(ModBlocks.MAPLE_PLANKS.get());
            event.accept(ModBlocks.MAPLE_LEAVES.get());
            event.accept(ModBlocks.MAPLE_SAPLING.get());
            event.accept(ModBlocks.CINNAMON_LOG.get());
            event.accept(ModBlocks.CINNAMON_WOOD.get());
            event.accept(ModBlocks.STRIPPED_CINNAMON_LOG.get());
            event.accept(ModBlocks.STRIPPED_CINNAMON_WOOD.get());
            event.accept(ModBlocks.CINNAMON_PLANKS.get());
            event.accept(ModBlocks.CINNAMON_LEAVES.get());
            event.accept(ModBlocks.CINNAMON_SAPLING.get());
            event.accept(ModBlocks.COLA_LOG.get());
            event.accept(ModBlocks.COLA_WOOD.get());
            event.accept(ModBlocks.STRIPPED_COLA_LOG.get());
            event.accept(ModBlocks.STRIPPED_COLA_WOOD.get());
            event.accept(ModBlocks.COLA_PLANKS.get());
            event.accept(ModBlocks.COLA_LEAVES.get());
            event.accept(ModBlocks.COLA_SAPLING.get());
            event.accept(ModBlocks.LEMON_LOG.get());
            event.accept(ModBlocks.LEMON_WOOD.get());
            event.accept(ModBlocks.STRIPPED_LEMON_LOG.get());
            event.accept(ModBlocks.STRIPPED_LEMON_WOOD.get());
            event.accept(ModBlocks.LEMON_PLANKS.get());
            event.accept(ModBlocks.LEMON_LEAVES.get());
            event.accept(ModBlocks.LEMON_SAPLING.get());
            event.accept(ModBlocks.PLUM_LOG.get());
            event.accept(ModBlocks.PLUM_WOOD.get());
            event.accept(ModBlocks.STRIPPED_PLUM_LOG.get());
            event.accept(ModBlocks.STRIPPED_PLUM_WOOD.get());
            event.accept(ModBlocks.PLUM_PLANKS.get());
            event.accept(ModBlocks.PLUM_LEAVES.get());
            event.accept(ModBlocks.PLUM_SAPLING.get());
            event.accept(ModBlocks.CHERRY_LOG.get());
            event.accept(ModBlocks.CHERRY_WOOD.get());
            event.accept(ModBlocks.STRIPPED_CHERRY_LOG.get());
            event.accept(ModBlocks.STRIPPED_CHERRY_WOOD.get());
            event.accept(ModBlocks.CHERRY_PLANKS.get());
            event.accept(ModBlocks.CHERRY_LEAVES.get());
            event.accept(ModBlocks.CHERRY_SAPLING.get());
            event.accept(ModBlocks.BANANA_LOG.get());
            event.accept(ModBlocks.BANANA_WOOD.get());
            event.accept(ModBlocks.STRIPPED_BANANA_LOG.get());
            event.accept(ModBlocks.STRIPPED_BANANA_WOOD.get());
            event.accept(ModBlocks.BANANA_PLANKS.get());
            event.accept(ModBlocks.BANANA_LEAVES.get());
            event.accept(ModBlocks.BANANA_SAPLING.get());
            event.accept(ModBlocks.PEACH_LOG.get());
            event.accept(ModBlocks.PEACH_WOOD.get());
            event.accept(ModBlocks.STRIPPED_PEACH_LOG.get());
            event.accept(ModBlocks.STRIPPED_PEACH_WOOD.get());
            event.accept(ModBlocks.PEACH_PLANKS.get());
            event.accept(ModBlocks.PEACH_LEAVES.get());
            event.accept(ModBlocks.PEACH_SAPLING.get());
            event.accept(ModBlocks.COCONUT_LOG.get());
            event.accept(ModBlocks.COCONUT_WOOD.get());
            event.accept(ModBlocks.STRIPPED_COCONUT_LOG.get());
            event.accept(ModBlocks.STRIPPED_COCONUT_WOOD.get());
            event.accept(ModBlocks.COCONUT_PLANKS.get());
            event.accept(ModBlocks.COCONUT_LEAVES.get());
            event.accept(ModBlocks.COCONUT_SAPLING.get());
            event.accept(ModBlocks.ALMOND_LOG.get());
            event.accept(ModBlocks.ALMOND_WOOD.get());
            event.accept(ModBlocks.STRIPPED_ALMOND_LOG.get());
            event.accept(ModBlocks.STRIPPED_ALMOND_WOOD.get());
            event.accept(ModBlocks.ALMOND_PLANKS.get());
            event.accept(ModBlocks.ALMOND_LEAVES.get());
            event.accept(ModBlocks.ALMOND_SAPLING.get());
            event.accept(ModBlocks.DURIAN_LOG.get());
            event.accept(ModBlocks.DURIAN_WOOD.get());
            event.accept(ModBlocks.STRIPPED_DURIAN_LOG.get());
            event.accept(ModBlocks.STRIPPED_DURIAN_WOOD.get());
            event.accept(ModBlocks.DURIAN_PLANKS.get());
            event.accept(ModBlocks.DURIAN_LEAVES.get());
            event.accept(ModBlocks.DURIAN_SAPLING.get());
            event.accept(ModBlocks.COFFEE_LOG.get());
            event.accept(ModBlocks.COFFEE_WOOD.get());
            event.accept(ModBlocks.STRIPPED_COFFEE_LOG.get());
            event.accept(ModBlocks.STRIPPED_COFFEE_WOOD.get());
            event.accept(ModBlocks.COFFEE_PLANKS.get());
            event.accept(ModBlocks.COFFEE_LEAVES.get());
            event.accept(ModBlocks.COFFEE_SAPLING.get());
            event.accept(ModBlocks.KIWI_LOG.get());
            event.accept(ModBlocks.KIWI_WOOD.get());
            event.accept(ModBlocks.STRIPPED_KIWI_LOG.get());
            event.accept(ModBlocks.STRIPPED_KIWI_WOOD.get());
            event.accept(ModBlocks.KIWI_PLANKS.get());
            event.accept(ModBlocks.KIWI_LEAVES.get());
            event.accept(ModBlocks.KIWI_SAPLING.get());
            event.accept(ModBlocks.TEA_BUSH.get());
            event.accept(ModBlocks.OLIVE_BUSH.get());
            event.accept(ModBlocks.BLUE_BERRY_BUSH.get());
            event.accept(ModBlocks.HOP_BUSH.get());
            event.accept(ModBlocks.PEPPER_BUSH.get());
            event.accept(ModBlocks.CABERNET_SAUVIGNON_STAIRS.get());
            event.accept(ModBlocks.CABERNET_SAUVIGNON_SLAB.get());
            event.accept(ModBlocks.CABERNET_SAUVIGNON_FENCE.get());
            event.accept(ModBlocks.CABERNET_SAUVIGNON_FENCE_GATE.get());
            //event.accept(ModBlocks.CABERNET_SAUVIGNON_BUTTON);
            event.accept(ModBlocks.CABERNET_SAUVIGNON_PRESSURE_PLATE.get());
            event.accept(ModBlocks.CABERNET_SAUVIGNON_DOOR.get());
            event.accept(ModBlocks.CABERNET_SAUVIGNON_TRAPDOOR.get());
            event.accept(ModBlocks.MAPLE_STAIRS.get());
            event.accept(ModBlocks.MAPLE_SLAB.get());
            event.accept(ModBlocks.MAPLE_FENCE.get());
            event.accept(ModBlocks.MAPLE_FENCE_GATE.get());
            //event.accept(ModBlocks.MAPLE_BUTTON);
            event.accept(ModBlocks.MAPLE_PRESSURE_PLATE.get());
            event.accept(ModBlocks.MAPLE_DOOR.get());
            event.accept(ModBlocks.MAPLE_TRAPDOOR.get());
            event.accept(ModBlocks.CINNAMON_STAIRS.get());
            event.accept(ModBlocks.CINNAMON_SLAB.get());
            event.accept(ModBlocks.CINNAMON_FENCE.get());
            event.accept(ModBlocks.CINNAMON_FENCE_GATE.get());
            //event.accept(ModBlocks.CINNAMON_BUTTON);
            event.accept(ModBlocks.CINNAMON_PRESSURE_PLATE.get());
            event.accept(ModBlocks.CINNAMON_DOOR.get());
            event.accept(ModBlocks.CINNAMON_TRAPDOOR.get());
            event.accept(ModBlocks.COLA_STAIRS.get());
            event.accept(ModBlocks.COLA_SLAB.get());
            event.accept(ModBlocks.COLA_FENCE.get());
            event.accept(ModBlocks.COLA_FENCE_GATE.get());
            //event.accept(ModBlocks.COLA_BUTTON);
            event.accept(ModBlocks.COLA_PRESSURE_PLATE.get());
            event.accept(ModBlocks.COLA_DOOR.get());
            event.accept(ModBlocks.COLA_TRAPDOOR.get());
            event.accept(ModBlocks.LEMON_STAIRS.get());
            event.accept(ModBlocks.LEMON_SLAB.get());
            event.accept(ModBlocks.LEMON_FENCE.get());
            event.accept(ModBlocks.LEMON_FENCE_GATE.get());
            //event.accept(ModBlocks.LEMON_BUTTON);
            event.accept(ModBlocks.LEMON_PRESSURE_PLATE.get());
            event.accept(ModBlocks.LEMON_DOOR.get());
            event.accept(ModBlocks.LEMON_TRAPDOOR.get());
            event.accept(ModBlocks.PLUM_STAIRS.get());
            event.accept(ModBlocks.PLUM_SLAB.get());
            event.accept(ModBlocks.PLUM_FENCE.get());
            event.accept(ModBlocks.PLUM_FENCE_GATE.get());
            //event.accept(ModBlocks.PLUM_BUTTON);
            event.accept(ModBlocks.PLUM_PRESSURE_PLATE.get());
            event.accept(ModBlocks.PLUM_DOOR.get());
            event.accept(ModBlocks.PLUM_TRAPDOOR.get());
            event.accept(ModBlocks.CHERRY_STAIRS.get());
            event.accept(ModBlocks.CHERRY_SLAB.get());
            event.accept(ModBlocks.CHERRY_FENCE.get());
            event.accept(ModBlocks.CHERRY_FENCE_GATE.get());
            //event.accept(ModBlocks.CHERRY_BUTTON.get());
            event.accept(ModBlocks.CHERRY_PRESSURE_PLATE.get());
            event.accept(ModBlocks.CHERRY_DOOR.get());
            event.accept(ModBlocks.CHERRY_TRAPDOOR.get());
            event.accept(ModBlocks.BANANA_STAIRS.get());
            event.accept(ModBlocks.BANANA_SLAB.get());
            event.accept(ModBlocks.BANANA_FENCE.get());
            event.accept(ModBlocks.BANANA_FENCE_GATE.get());
            //event.accept(ModBlocks.BANANA_BUTTON.get());
            event.accept(ModBlocks.BANANA_PRESSURE_PLATE.get());
            event.accept(ModBlocks.BANANA_DOOR.get());
            event.accept(ModBlocks.BANANA_TRAPDOOR.get());
            event.accept(ModBlocks.COCONUT_STAIRS.get());
            event.accept(ModBlocks.COCONUT_SLAB.get());
            event.accept(ModBlocks.COCONUT_FENCE.get());
            event.accept(ModBlocks.COCONUT_FENCE_GATE.get());
            //event.accept(ModBlocks.COCONUT_BUTTON.get());
            event.accept(ModBlocks.COCONUT_PRESSURE_PLATE.get());
            event.accept(ModBlocks.COCONUT_DOOR.get());
            event.accept(ModBlocks.COCONUT_TRAPDOOR.get());
            event.accept(ModBlocks.PEACH_STAIRS.get());
            event.accept(ModBlocks.PEACH_SLAB.get());
            event.accept(ModBlocks.PEACH_FENCE.get());
            event.accept(ModBlocks.PEACH_FENCE_GATE.get());
            //event.accept(ModBlocks.PEACH_BUTTON.get());
            event.accept(ModBlocks.PEACH_PRESSURE_PLATE.get());
            event.accept(ModBlocks.PEACH_DOOR.get());
            event.accept(ModBlocks.PEACH_TRAPDOOR.get());
            event.accept(ModBlocks.ALMOND_STAIRS.get());
            event.accept(ModBlocks.ALMOND_SLAB.get());
            event.accept(ModBlocks.ALMOND_FENCE.get());
            event.accept(ModBlocks.ALMOND_FENCE_GATE.get());
            //event.accept(ModBlocks.ALMOND_BUTTON.get());
            event.accept(ModBlocks.ALMOND_PRESSURE_PLATE.get());
            event.accept(ModBlocks.ALMOND_DOOR.get());
            event.accept(ModBlocks.ALMOND_TRAPDOOR.get());
            event.accept(ModBlocks.DURIAN_STAIRS.get());
            event.accept(ModBlocks.DURIAN_SLAB.get());
            event.accept(ModBlocks.DURIAN_FENCE.get());
            event.accept(ModBlocks.DURIAN_FENCE_GATE.get());
            //event.accept(ModBlocks.DURIAN_BUTTON.get());
            event.accept(ModBlocks.DURIAN_PRESSURE_PLATE.get());
            event.accept(ModBlocks.DURIAN_DOOR.get());
            event.accept(ModBlocks.DURIAN_TRAPDOOR.get());
            event.accept(ModBlocks.COFFEE_STAIRS.get());
            event.accept(ModBlocks.COFFEE_SLAB.get());
            event.accept(ModBlocks.COFFEE_FENCE.get());
            event.accept(ModBlocks.COFFEE_FENCE_GATE.get());
            //event.accept(ModBlocks.DURIAN_BUTTON.get());
            event.accept(ModBlocks.COFFEE_PRESSURE_PLATE.get());
            event.accept(ModBlocks.COFFEE_DOOR.get());
            event.accept(ModBlocks.COFFEE_TRAPDOOR.get());
            event.accept(ModBlocks.QUEEN_OF_NIGHT.get());
            event.accept(ModBlocks.ROSE.get());
            event.accept(ModBlocks.CAMOMILE.get());
            event.accept(ModBlocks.THYME.get());
            event.accept(ModBlocks.OREGANO.get());
            event.accept(ModBlocks.KIWI_STAIRS.get());
            event.accept(ModBlocks.KIWI_SLAB.get());
            event.accept(ModBlocks.KIWI_FENCE.get());
            event.accept(ModBlocks.KIWI_FENCE_GATE.get());
            //event.accept(ModBlocks.CABERNET_SAUVIGNON_BUTTON.get());
            event.accept(ModBlocks.KIWI_PRESSURE_PLATE.get());
            event.accept(ModBlocks.KIWI_DOOR.get());
            event.accept(ModBlocks.KIWI_TRAPDOOR.get());
            //event.accept(ModBlocks.CHAIR.get());
        }

        if(event.getTab() == ModCreativeModeTabs.POCHI_ORE.get()){
            event.accept(ModBlocks.CHROMITE_ORE.get());
            event.accept(ModBlocks.DEEPSLATE_CHROMITE_ORE.get());
            event.accept(ModBlocks.NETHERRACK_CHROMITE_ORE.get());
            event.accept(ModBlocks.FLUORITE_ORE.get());
            event.accept(ModBlocks.DEEPSLATE_FLUORITE_ORE.get());
            event.accept(ModBlocks.NETHERRACK_FLUORITE_ORE.get());
            event.accept(ModBlocks.ALUNITE_ORE.get());
            event.accept(ModBlocks.DEEPSLATE_ALUNITE_ORE.get());
            event.accept(ModBlocks.NETHERRACK_ALUNITE_ORE.get());
            event.accept(ModBlocks.BAUXITE_ORE.get());
            event.accept(ModBlocks.DEEPSLATE_BAUXITE_ORE.get());
            event.accept(ModBlocks.NETHERRACK_BAUXITE_ORE.get());
            event.accept(ModBlocks.TITANIUM_ORE.get());
            event.accept(ModBlocks.DEEPSLATE_TITANIUM_ORE.get());
            event.accept(ModBlocks.NETHERRACK_TITANIUM_ORE.get());
            event.accept(ModBlocks.MAGUNESIUM_ORE.get());
            event.accept(ModBlocks.DEEPSLATE_MAGUNESIUM_ORE.get());
            event.accept(ModBlocks.NETHERRACK_MAGUNESIUM_ORE.get());
            event.accept(ModBlocks.VANADIUM_ORE.get());
            event.accept(ModBlocks.DEEPSLATE_VANADIUM_ORE.get());
            event.accept(ModBlocks.NETHERRACK_VANADIUM_ORE.get());
            event.accept(ModBlocks.CHROMITE_BLOCK.get());
            event.accept(ModBlocks.STAINLESS_BLOCK.get());
            event.accept(ModBlocks.FLUORITE_BLOCK.get());
            event.accept(ModBlocks.ALUNITE_BLOCK.get());
            event.accept(ModBlocks.SANITARY_BLOCK.get());

            event.accept(ModItems.BAKED_ALUM.get());
            event.accept(ModItems.STAINLESS.get());
            event.accept(ModItems.DURALUMIN.get());
            event.accept(ModItems.TITAN_ALLOY.get());
            event.accept(ModItems.ELECTRON.get());
            event.accept(ModItems.VANADIUM_ALLOY.get());
            event.accept(ModItems.CHROMITE_INGOT.get());
            event.accept(ModItems.ALUMINIUM_INGOT.get());
            event.accept(ModItems.TITANIUM_INGOT.get());
            event.accept(ModItems.MAGUNESIUM_INGOT.get());
            event.accept(ModItems.VANADIUM_INGOT.get());
            event.accept(ModItems.ROW_CHROMITE.get());
            event.accept(ModItems.ROW_FLUORITE.get());
            event.accept(ModItems.ROW_ALUNITE.get());
            event.accept(ModItems.ROW_BAUXITE.get());
            event.accept(ModItems.ROW_TITANIUM.get());
            event.accept(ModItems.ROW_MAGUNESIUM.get());
            event.accept(ModItems.ROW_VANADIUM.get());
            event.accept(ModBlocks.PAMMUKALE_BLOCK.get());

            event.accept(ModBlocks.ALEXANDRITE_BLOCK.get());
            event.accept(ModBlocks.BLACK_DIAMOND_BLOCK.get());
            event.accept(ModBlocks.YELLOW_DIAMOND_BLOCK.get());
            event.accept(ModBlocks.CINNABAR_BLOCK.get());
            event.accept(ModBlocks.MORGANITE_BLOCK.get());
            event.accept(ModBlocks.EUCLASE_BLOCK.get());
            event.accept(ModBlocks.JADE_BLOCK.get());
            event.accept(ModBlocks.GOSHENITE_BLOCK.get());
            event.accept(ModBlocks.RUTILE_BLOCK.get());
            event.accept(ModBlocks.PHOSPHOPHYLITE_BLOCK.get());

            event.accept(ModItems.HAMMER_HEAD.get());
        }
        if(event.getTab() == ModCreativeModeTabs.POCHI_FOOD.get()){
            event.accept(ModItems.CABBAGE_LEAF.get());
            event.accept(ModItems.ASPARAGUS.get());
            event.accept(ModItems.GRAPE.get());
            event.accept(ModItems.LEMON.get());
            event.accept(ModItems.PLUM.get());
            event.accept(ModItems.CHERRY.get());
            event.accept(ModItems.BANANA.get());
            event.accept(ModItems.COCONUT.get());
            event.accept(ModItems.PEACH.get());
            event.accept(ModItems.ALMOND.get());
            event.accept(ModItems.DURIAN.get());
            event.accept(ModItems.CINNAMON.get());
            event.accept(ModItems.ROW_RICE.get());
            event.accept(ModItems.RICE.get());
            event.accept(ModItems.MINT.get());
            event.accept(ModItems.COLA.get());
            event.accept(ModItems.TOMATO.get());
            event.accept(ModItems.CORN.get());
            event.accept(ModItems.ONION.get());
            event.accept(ModItems.GINGER.get());
            event.accept(ModItems.GREEN_PEPPER.get());
            event.accept(ModItems.PAPRIKA.get());
            event.accept(ModItems.EGGPLANT.get());
            event.accept(ModItems.WHITE_RADISH.get());
            event.accept(ModItems.CHILI_PEPPER.get());
            event.accept(ModItems.BASIL.get());
            event.accept(ModItems.LOTUS_ROOT.get());
            event.accept(ModItems.ASPARAGUS_BACON.get());
            event.accept(ModItems.GINGER_PORK.get());
            event.accept(ModItems.FRIED_EGGPLANT.get());
            event.accept(ModItems.CHINJAOLOSE.get());
            event.accept(ModItems.POPCORN.get());
            event.accept(ModItems.PIZZA_BRED.get());
            event.accept(ModItems.BOILED_FISH.get());
            event.accept(ModItems.CORN_SOUP.get());
            event.accept(ModItems.HAMBURGER.get());
            event.accept(ModItems.PEPERONCINO.get());
            event.accept(ModItems.MABO_NASU.get());
            event.accept(ModItems.BAKED_CORN.get());
            event.accept(ModItems.RADISH_MINCED_MEAT.get());
            event.accept(ModItems.CHICKEN_EGG.get());
            event.accept(ModItems.GENOVESE.get());
            event.accept(ModItems.FRIED_ALMOND.get());
            event.accept(ModItems.GREEN_CARRY.get());
            event.accept(ModItems.GREEN_PEPPER_MINCED_MEAT.get());
            event.accept(ModItems.PEPE_CABBAGE.get());
            event.accept(ModItems.FRIED_LOTUS_ROOT.get());
            event.accept(ModItems.LOTUS_ROOT_MINCED_MEAT.get());
            event.accept(ModItems.GAPRAO.get());
            event.accept(ModItems.TACOS.get());
            event.accept(ModItems.TOMATO_SAND.get());
            event.accept(ModItems.BANANA_SAND.get());
            event.accept(ModItems.PEACH_SAND.get());
            event.accept(ModItems.APPLE_SAND.get());
            event.accept(ModItems.GRAPE_SAND.get());
            event.accept(ModItems.TEA.get());
            event.accept(ModItems.OLIVE.get());
            event.accept(ModItems.BLUE_BERRY.get());
            event.accept(ModItems.HOP.get());
            event.accept(ModItems.PEPPER.get());
            event.accept(ModItems.ASPARAGUS_SEEDS.get());
            event.accept(ModItems.CABBAGE_SEEDS.get());
            event.accept(ModItems.RICE_SEEDS.get());
            event.accept(ModItems.MINT_SEEDS.get());
            event.accept(ModItems.COLA_SEEDS.get());
            event.accept(ModItems.TOMATO_SEEDS.get());
            event.accept(ModItems.CORN_SEEDS.get());
            event.accept(ModItems.ONION_SEEDS.get());
            event.accept(ModItems.GINGER_SEEDS.get());
            event.accept(ModItems.GREEN_PEPPER_SEEDS.get());
            event.accept(ModItems.PAPRIKA_SEEDS.get());
            event.accept(ModItems.EGGPLANT_SEEDS.get());
            event.accept(ModItems.WHITE_RADISH_SEEDS.get());
            event.accept(ModItems.CHILI_PEPPER_SEEDS.get());
            event.accept(ModItems.BASIL_SEEDS.get());
            event.accept(ModItems.LOTUS_ROOT_SEEDS.get());
            event.accept(ModItems.COFFEE_BEANS.get());
            event.accept(ModItems.ROW_COFFEE_BEANS.get());
            event.accept(ModItems.MUSK_COFFEE_BEANS.get());
        }

        if(event.getTab() == ModCreativeModeTabs.POCHI_DRINK.get()){
            event.accept(ModItems.FILTERED_WATER.get());
            event.accept(ModItems.BOTTLE_OF_MILK.get());
            event.accept(ModItems.BOTTLE_OF_WHISKEY.get());
            event.accept(ModItems.BOTTLE_OF_RED_WINE.get());
            event.accept(ModItems.BOTTLE_OF_WHITE_WINE.get());
            event.accept(ModItems.BOTTLE_OF_WHITE_LIQUOR.get());
            event.accept(ModItems.PEACH_LIQUEUR.get());
            event.accept(ModItems.PLUM_LIQUEUR.get());
            event.accept(ModItems.LEMON_LIQUEUR.get());
            event.accept(ModItems.MINT_LIQUEUR.get());
            event.accept(ModItems.APPLE_LIQUEUR.get());
            event.accept(ModItems.MAPLE_WATER.get());
            event.accept(ModItems.MAPLE_SYRUP.get());
            event.accept(ModItems.BOTTLE_OF_COLA.get());
            event.accept(ModItems.GRAPE_JUICE.get());
            event.accept(ModItems.APPLE_JUICE.get());
            event.accept(ModItems.LEMON_JUICE.get());
            event.accept(ModItems.PEACH_JUICE.get());
            event.accept(ModItems.PLUM_JUICE.get());
            event.accept(ModItems.BANANA_JUICE.get());
            event.accept(ModItems.ALMOND_MILK.get());
            event.accept(ModItems.COCONUT_MILK.get());
            event.accept(ModItems.SMOOTHIE.get());
            event.accept(ModItems.MIX_JUICE.get());
            event.accept(ModItems.MIX_AU_LAIT.get());
            event.accept(ModItems.CHOCOLATE.get());
            event.accept(ModItems.CHOCO_MINT.get());
            event.accept(ModItems.COFFEE.get());
            event.accept(ModItems.MUSK_COFFEE.get());
        }

        if(event.getTab() == ModCreativeModeTabs.POCHI_BUCKET.get()){
            event.accept(ModItems.WHISKEY_BUCKET.get());
            event.accept(ModItems.MAPLE_BUCKET.get());
            event.accept(ModItems.SAKE_BUCKET.get());
            event.accept(ModItems.WINE_BUCKET.get());
            event.accept(ModItems.WHITE_WINE_BUCKET.get());
            event.accept(ModItems.J_MALT_BUCKET.get());
            event.accept(ModItems.RICE_BUCKET.get());
            event.accept(ModItems.MASH_BUCKET.get());
            event.accept(ModItems.YEAST_BUCKET.get());
            event.accept(ModItems.FILTERED_WATER_BUCKET.get());
            event.accept(ModItems.JAPANESE_YEAST.get());
        }

        if(event.getTab() == ModCreativeModeTabs.POCHI_TOOL.get()){
            event.accept(ModItems.CHROMITE_AXE.get());
            event.accept(ModItems.CHROMITE_HOE.get());
            event.accept(ModItems.CHROMITE_PICKAXE.get());
            event.accept(ModItems.CHROMITE_SHOVEL.get());
            event.accept(ModItems.CHROMITE_SWORD.get());
            event.accept(ModItems.FLUORITE_AXE.get());
            event.accept(ModItems.FLUORITE_HOE.get());
            event.accept(ModItems.FLUORITE_PICKAXE.get());
            event.accept(ModItems.FLUORITE_SHOVEL.get());
            event.accept(ModItems.FLUORITE_SWORD.get());
            event.accept(ModItems.ALUNITE_AXE.get());
            event.accept(ModItems.ALUNITE_HOE.get());
            event.accept(ModItems.ALUNITE_PICKAXE.get());
            event.accept(ModItems.ALUNITE_SHOVEL.get());
            event.accept(ModItems.ALUNITE_SWORD.get());
            event.accept(ModItems.STAINLESS_AXE.get());
            event.accept(ModItems.STAINLESS_HOE.get());
            event.accept(ModItems.STAINLESS_PICKAXE.get());
            event.accept(ModItems.STAINLESS_SHOVEL.get());
            event.accept(ModItems.STAINLESS_SWORD.get());
            event.accept(ModItems.ALUMINIUM_AXE.get());
            event.accept(ModItems.ALUMINIUM_HOE.get());
            event.accept(ModItems.ALUMINIUM_PICKAXE.get());
            event.accept(ModItems.ALUMINIUM_SHOVEL.get());
            event.accept(ModItems.ALUMINIUM_SWORD.get());
            event.accept(ModItems.TITANIUM_AXE.get());
            event.accept(ModItems.TITANIUM_HOE.get());
            event.accept(ModItems.TITANIUM_PICKAXE.get());
            event.accept(ModItems.TITANIUM_SHOVEL.get());
            event.accept(ModItems.TITANIUM_SWORD.get());
            event.accept(ModItems.MAGUNESIUM_AXE.get());
            event.accept(ModItems.MAGUNESIUM_HOE.get());
            event.accept(ModItems.MAGUNESIUM_PICKAXE.get());
            event.accept(ModItems.MAGUNESIUM_SHOVEL.get());
            event.accept(ModItems.MAGUNESIUM_SWORD.get());
            event.accept(ModItems.VANADIUM_AXE.get());
            event.accept(ModItems.VANADIUM_HOE.get());
            event.accept(ModItems.VANADIUM_PICKAXE.get());
            event.accept(ModItems.VANADIUM_SHOVEL.get());
            event.accept(ModItems.VANADIUM_SWORD.get());
            event.accept(ModItems.DURALUMIN_AXE.get());
            event.accept(ModItems.DURALUMIN_HOE.get());
            event.accept(ModItems.DURALUMIN_PICKAXE.get());
            event.accept(ModItems.DURALUMIN_SHOVEL.get());
            event.accept(ModItems.DURALUMIN_SWORD.get());
            event.accept(ModItems.TITAN_ALLOY_AXE.get());
            event.accept(ModItems.TITAN_ALLOY_HOE.get());
            event.accept(ModItems.TITAN_ALLOY_PICKAXE.get());
            event.accept(ModItems.TITAN_ALLOY_SHOVEL.get());
            event.accept(ModItems.TITAN_ALLOY_SWORD.get());
            event.accept(ModItems.ELECTRON_AXE.get());
            event.accept(ModItems.ELECTRON_HOE.get());
            event.accept(ModItems.ELECTRON_PICKAXE.get());
            event.accept(ModItems.ELECTRON_SHOVEL.get());
            event.accept(ModItems.ELECTRON_SWORD.get());
            event.accept(ModItems.VANADIUM_ALLOY_AXE.get());
            event.accept(ModItems.VANADIUM_ALLOY_HOE.get());
            event.accept(ModItems.VANADIUM_ALLOY_PICKAXE.get());
            event.accept(ModItems.VANADIUM_ALLOY_SHOVEL.get());
            event.accept(ModItems.VANADIUM_ALLOY_SWORD.get());
            event.accept(ModItems.HOOK_SHOT.get());
            event.accept(ModItems.GRAPPLING.get());
            //event.accept(ModBlocks.CREATE_TNT.get());
            event.accept(ModItems.TONBOKIRI.get());
            event.accept(ModItems.PICKAXE_ROD.get());
            event.accept(ModItems.PICKAXE_STICK.get());
            event.accept(ModItems.FLY_BOAT.get());
            event.accept(ModItems.FLY_CHEST_BOAT.get());
            event.accept(ModItems.WOODEN_NET.get());
            event.accept(ModItems.IRON_NET.get());
            event.accept(ModItems.DIAMOND_NET.get());
            event.accept(ModItems.HAMMER_HEAD_PICKAXE.get());
            event.accept(ModItems.MANTIS_GRAB.get());
        }
        if(event.getTab() == ModCreativeModeTabs.POCHI_ARMOR.get()){
            event.accept(ModItems.CHROMITE_HELMET.get());
            event.accept(ModItems.CHROMITE_CHESTPLATE.get());
            event.accept(ModItems.CHROMITE_LEGGINGS.get());
            event.accept(ModItems.CHROMITE_BOOTS.get());
            event.accept(ModItems.FLUORITE_HELMET.get());
            event.accept(ModItems.FLUORITE_CHESTPLATE.get());
            event.accept(ModItems.FLUORITE_LEGGINGS.get());
            event.accept(ModItems.FLUORITE_BOOTS.get());
            event.accept(ModItems.STAINLESS_HELMET.get());
            event.accept(ModItems.STAINLESS_CHESTPLATE.get());
            event.accept(ModItems.STAINLESS_LEGGINGS.get());
            event.accept(ModItems.STAINLESS_BOOTS.get());
            event.accept(ModItems.ALUMINIUM_HELMET.get());
            event.accept(ModItems.ALUMINIUM_CHESTPLATE.get());
            event.accept(ModItems.ALUMINIUM_LEGGINGS.get());
            event.accept(ModItems.ALUMINIUM_BOOTS.get());
            event.accept(ModItems.TITANIUM_HELMET.get());
            event.accept(ModItems.TITANIUM_CHESTPLATE.get());
            event.accept(ModItems.TITANIUM_LEGGINGS.get());
            event.accept(ModItems.TITANIUM_BOOTS.get());
            event.accept(ModItems.MAGUNESIUM_HELMET.get());
            event.accept(ModItems.MAGUNESIUM_CHESTPLATE.get());
            event.accept(ModItems.MAGUNESIUM_LEGGINGS.get());
            event.accept(ModItems.MAGUNESIUM_BOOTS.get());
            event.accept(ModItems.VANADIUM_HELMET.get());
            event.accept(ModItems.VANADIUM_CHESTPLATE.get());
            event.accept(ModItems.VANADIUM_LEGGINGS.get());
            event.accept(ModItems.VANADIUM_BOOTS.get());
            event.accept(ModItems.DURALUMIN_HELMET.get());
            event.accept(ModItems.DURALUMIN_CHESTPLATE.get());
            event.accept(ModItems.DURALUMIN_LEGGINGS.get());
            event.accept(ModItems.DURALUMIN_BOOTS.get());
            event.accept(ModItems.TITAN_ALLOY_HELMET.get());
            event.accept(ModItems.TITAN_ALLOY_CHESTPLATE.get());
            event.accept(ModItems.TITAN_ALLOY_LEGGINGS.get());
            event.accept(ModItems.TITAN_ALLOY_BOOTS.get());
            event.accept(ModItems.ELECTRON_HELMET.get());
            event.accept(ModItems.ELECTRON_CHESTPLATE.get());
            event.accept(ModItems.ELECTRON_LEGGINGS.get());
            event.accept(ModItems.ELECTRON_BOOTS.get());
            event.accept(ModItems.VANADIUM_ALLOY_HELMET.get());
            event.accept(ModItems.VANADIUM_ALLOY_CHESTPLATE.get());
            event.accept(ModItems.VANADIUM_ALLOY_LEGGINGS.get());
            event.accept(ModItems.VANADIUM_ALLOY_BOOTS.get());
            event.accept(ModItems.PERISO_HELMET.get());
            event.accept(ModItems.ROCK_PENGUIN_BOOTS.get());
            event.accept(ModItems.ANCIENT_LIZARD_HELMET.get());
            event.accept(ModItems.LEOPARD_GECKO_TAIL_BELT.get());
            event.accept(ModItems.EMU_BOOTS.get());
        }
        if(event.getTab() == ModCreativeModeTabs.POCHI_CRAFT.get()){
            event.accept(ModBlocks.BFURNACE_BLOCK.get());
            event.accept(ModBlocks.DISTILLER_BLOCK.get());
            event.accept(ModBlocks.SAKE_DARU_BLOCK.get());
            event.accept(ModBlocks.MIXER_BLOCK.get());
            event.accept(ModBlocks.FRYPAN_BLOCK.get());
        }
        if(event.getTab() == ModCreativeModeTabs.POCHI_ENTITY.get()){
            event.accept(ModItems.SPARROW_SPAWN_EGG.get());
            event.accept(ModItems.DEER_SPAWN_EGG.get());
            event.accept(ModItems.DOE_SPAWN_EGG.get());
            event.accept(ModItems.SAKABAN_SPAWN_EGG.get());
            event.accept(ModItems.CICADA_SPAWN_EGG.get());
            event.accept(ModItems.DRAGONFLY_SPAWN_EGG.get());
            event.accept(ModItems.BUTTERFLY_SPAWN_EGG.get());
            event.accept(ModItems.LONG_TIT_SPAWN_EGG.get());
            event.accept(ModItems.SEAL_SPAWN_EGG.get());
            event.accept(ModItems.HERMIT_CRAB_SPAWN_EGG.get());
            event.accept(ModItems.MINI_HIPO_SPAWN_EGG.get());
            event.accept(ModItems.MONGOOSE_SPAWN_EGG.get());
            event.accept(ModItems.ANT_SPAWN_EGG.get());
            event.accept(ModItems.ETUPIRKA_SPAWN_EGG.get());
            event.accept(ModItems.SNAKE_SPAWN_EGG.get());
            event.accept(ModItems.PEACOCK_SPAWN_EGG.get());
            event.accept(ModItems.BURROWING_OWL_SPAWN_EGG.get());
            event.accept(ModItems.FOLIVORE_SPAWN_EGG.get());
            event.accept(ModItems.GIANT_OTTER_SPAWN_EGG.get());
            event.accept(ModItems.GUYANA_RUPICOLA_SPAWN_EGG.get());
            event.accept(ModItems.HARPY_EAGLE_SPAWN_EGG.get());
            event.accept(ModItems.MUSK_CAT_SPAWN_EGG.get());
            event.accept(ModItems.PERISSO_SPAWN_EGG.get());
            event.accept(ModItems.RATEL_SPAWN_EGG.get());
            event.accept(ModItems.WOMBAT_SPAWN_EGG.get());
            event.accept(ModItems.BEAVER_SPAWN_EGG.get());
            event.accept(ModItems.HAMMER_HEAD_SPAWN_EGG.get());
            event.accept(ModItems.LEAFY_SEA_SPAWN_EGG.get());
            event.accept(ModItems.KIWI_SPAWN_EGG.get());
            event.accept(ModItems.ROCK_PENGUIN_SPAWN_EGG.get());
            event.accept(ModItems.SKUNK_SPAWN_EGG.get());
            event.accept(ModItems.STURGEON_SPAWN_EGG.get());
            event.accept(ModItems.QUOKKA_SPAWN_EGG.get());
            event.accept(ModItems.WOOD_PECKER_SPAWN_EGG.get());
            event.accept(ModItems.FELIS_SPAWN_EGG.get());
            event.accept(ModItems.FRUIT_FLY_SPAWN_EGG.get());
            event.accept(ModItems.INDICATOR_IDAE_SPAWN_EGG.get());
            event.accept(ModItems.MANTIS_SHRIMP_SPAWN_EGG.get());
            event.accept(ModItems.MEERKAT_SPAWN_EGG.get());
            event.accept(ModItems.PALLAS_CAT_SPAWN_EGG.get());
            event.accept(ModItems.PANGOLIN_SPAWN_EGG.get());
            event.accept(ModItems.PORCUPINE_SPAWN_EGG.get());
            event.accept(ModItems.TAPIR_SPAWN_EGG.get());
        }

        if(event.getTab() == ModCreativeModeTabs.POCHI_SPELL.get()){
            event.accept(ModItems.REVERSAL.get());
            event.accept(ModItems.SHIELD_CAST.get());
            event.accept(ModItems.GOLEM_SUMMON.get());
            event.accept(ModItems.ENDER_SPELL.get());
            event.accept(ModItems.SOUL_SPELL.get());
            event.accept(ModItems.CLIONE_STAFF.get());
            event.accept(ModItems.FLOWER_MANTIS_STAFF.get());
            event.accept(ModItems.CROCODILE_JAW_CHAIN.get());
            event.accept(ModItems.LUNAR_CLAW_BLADE.get());
            event.accept(ModItems.VITAL_CHECK.get());
        }

    }

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent.Post event) {
        if (event.getEntity().level().isClientSide()) return;

        MineralEffectHandler.onLivingTick(event.getEntity());
    }



    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_WHISKEY.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_WHISKEY.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_MAPLE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_MAPLE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_SAKE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_SAKE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_WINE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_WINE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_WHITE_WINE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_WHITE_WINE.get(), RenderType.translucent());

            //MenuScreens.register(ModMenuTypes.BFURNACE_MENU.get(), BFurnaceScreen::new);
            //MenuScreens.register(ModMenuTypes.DISTILLER_MENU.get(), DistillerScreen::new);
            //MenuScreens.register(ModMenuTypes.SAKEDARU_MENU.get(), SakeDaruScreen::new);
            //MenuScreens.register(ModMenuTypes.FRYPAN_MENU.get(), FrypanScreen::new);
            //MenuScreens.register(ModMenuTypes.MIXER_MENU.get(), MixerScreen::new);

            EntityRenderers.register(ModEntityTypes.SPARROW.get(), SparrowRenderer::new);
            EntityRenderers.register(ModEntityTypes.DEER.get(), DeerRenderer::new);
            EntityRenderers.register(ModEntityTypes.DOE.get(), DoeRenderer::new);
            EntityRenderers.register(ModEntityTypes.SAKABAN.get(), SakabanRenderer::new);
            EntityRenderers.register(ModEntityTypes.CICADA.get(), CicadaRenderer::new);
            EntityRenderers.register(ModEntityTypes.DRAGONFLY.get(), DragonflyRenderer::new);
            EntityRenderers.register(ModEntityTypes.BUTTERFLY.get(), ButterflyRenderer::new);
            EntityRenderers.register(ModEntityTypes.LONG_TIT.get(), LongTailTitRenderer::new);
            EntityRenderers.register(ModEntityTypes.SEAL.get(), SealRenderer::new);
            EntityRenderers.register(ModEntityTypes.HERMIT_CRAB.get(), HermitCrabRenderer::new);
            EntityRenderers.register(MINI_HIPO.get(), MiniHipoRenderer::new);
            EntityRenderers.register(MONGOOSE.get(), MongooseRenderer::new);
            EntityRenderers.register(ANT.get(), AntRenderer::new);
            EntityRenderers.register(ETUPIRKA.get(), EtupirkaRenderer::new);
            EntityRenderers.register(SNAKE.get(), SnakeRenderer::new);
            EntityRenderers.register(PEACOCK.get(), PeacockRenderer::new);
            EntityRenderers.register(ModEntityTypes.BURROWING_OWL.get(), BurrowingOwlRenderer::new);
            EntityRenderers.register(ModEntityTypes.FOLIVORE.get(), FolivoreRenderer::new);
            EntityRenderers.register(ModEntityTypes.GUYANA_RUPICOLA.get(), GuyanaRupicolaRenderer::new);
            EntityRenderers.register(GIANT_OTTER.get(), GiantOtterRenderer::new);
            EntityRenderers.register(HARPY_EAGLE.get(), HarpyEagleRenderer::new);
            EntityRenderers.register(PERISSO.get(), PerissoRenderer::new);
            EntityRenderers.register(RATEL.get(), RatelRenderer::new);
            EntityRenderers.register(MUSK_CAT.get(), MuskCatRenderer::new);
            EntityRenderers.register(WOMBAT.get(), WombatRenderer::new);
            EntityRenderers.register(BEAVER.get(), BeaverRenderer::new);
            EntityRenderers.register(HAMMER_HEAD.get(), HammerHeadSharkRenderer::new);
            EntityRenderers.register(LEAFY_SEA.get(), LeafySeaDragonRenderer::new);
            EntityRenderers.register(KIWI.get(), KiwiRenderer::new);
            EntityRenderers.register(SKUNK.get(), SkunkRenderer::new);
            EntityRenderers.register(STURGEON.get(), SturgeonRenderer::new);
            EntityRenderers.register(ROCK_PENGUIN.get(), RockPenguinRenderer::new);
            EntityRenderers.register(QUOKKA.get(), QuokkaRenderer::new);
            EntityRenderers.register(WOOD_PECKER.get(), WoodPeckerRenderer::new);

            EntityRenderers.register(FELIS.get(), FelisRenderer::new);
            EntityRenderers.register(FRUIT_FLY.get(), FruitFlyRenderer::new);
            EntityRenderers.register(INDICATOR_IDAE.get(), IndicatoridaeRenderer::new);
            EntityRenderers.register(MANTIS_SHRIMP.get(), MantisShrimpRenderer::new);
            EntityRenderers.register(MEERKAT.get(), MeerkatRenderer::new);
            EntityRenderers.register(PALLAS_CAT.get(), PallasCatRenderer::new);
            EntityRenderers.register(PANGOLIN.get(), PangolinRenderer::new);
            EntityRenderers.register(PORCUPINE.get(), PorcupineRenderer::new);
            EntityRenderers.register(TAPIR.get(), TapirRenderer::new);
            EntityRenderers.register(CHAMELEON.get(), ChameleonRenderer::new);

            EntityRenderers.register(BETTA.get(), BettaRenderer::new);
            //EntityRenderers.register(ALBATROSS.get(), AlbatrossRenderer::new);
            EntityRenderers.register(IBERIAN_PIG.get(), IberianPigRenderer::new);
            EntityRenderers.register(EMU.get(), EmuRenderer::new);
            EntityRenderers.register(CAPYBARA.get(), CapybaraRenderer::new);
            EntityRenderers.register(PLATYPUS.get(), PlatypusRenderer::new);
            EntityRenderers.register(GIRAFFE.get(), GiraffeRenderer::new);
            EntityRenderers.register(CLIONE.get(), ClioneRenderer::new);
            EntityRenderers.register(SOOTY_SHEARWATER.get(), SootyShearwaterRenderer::new);
            EntityRenderers.register(ASIAN_BEAR.get(), AsianBearRenderer::new);
            EntityRenderers.register(ELECTRIC_CATFISH.get(), ElectricCatfishRenderer::new);
            EntityRenderers.register(FLOWER_MANTIS.get(), FlowerMantisRenderer::new);
            EntityRenderers.register(CASSOWARY.get(), CassowaryRenderer::new);
            EntityRenderers.register(ANCIENT_LIZARD.get(), AncientLizardRenderer::new);
            EntityRenderers.register(MOLE.get(), MoleRenderer::new);
            EntityRenderers.register(URAL_OWL.get(), UralOwlRenderer::new);
            EntityRenderers.register(LEOPARD_GECKO.get(), LeopardGeckoRenderer::new);
            EntityRenderers.register(CROCODILE.get(), CrocodileRenderer::new);
            EntityRenderers.register(RHINO.get(), RhinoRenderer::new);
            EntityRenderers.register(SQUIRREL.get(), SquirrelRenderer::new);


            EntityRenderers.register(ModEntityTypes.DIRT_GOLEM.get(), DirtGolemRenderer::new);
            EntityRenderers.register(ORE_SEARCH.get(), OreSlimeRenderer::new);
            EntityRenderers.register(DELTA_BLOCK_O.get(), DeltaMovementBlockEntityRenderer::new);
            EntityRenderers.register(BLOCK_ARROW.get(), BlockArrowEntityRenderer::new);
            EntityRenderers.register(ModEntityTypes.PICKAXE_HOOK.get(), PickaxeHookRenderer::new);
            EntityRenderers.register(ModEntityTypes.PICKAXE_HEAD.get(), PickaxeHeadRenderer::new);

            EntityRenderers.register(HOOK_ARROW.get(), HookArrowRenderer::new);
            EntityRenderers.register(ICE_PROJECTILE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(SCYTHE_PROJECTILE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(SUMMONED_CLIONE.get(), SummonedClioneEntityRenderer::new);
            EntityRenderers.register(ModEntityTypes.FLOWER_BOMB.get(), NoopRenderer::new);
            EntityRenderers.register(JAW_CHAIN_PROJECTILE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(ModEntityTypes.DECOY_TAIL.get(), NoopRenderer::new);
            //EntityRenderers.register(CREATE_TNT.get(), CreateTntRenderer::new);

            EntityRenderers.register(FLY_BOAT.get(), FlyBoatRenderer::new);
            EntityRenderers.register(FLY_CHEST_BOAT.get(), FlyChestBoatRenderer::new);

            PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "animation"),
                    42,
                    ClientModEvents::registerPlayerAnimation);

            event.enqueueWork(() -> {
                ModItemProperties.addCustomItemProperties();

                BlockEntityRenderers.register(
                        ModBlockEntities.POTTERY_ON_WHEEL.get(),
                        PotteryOnWheelRenderer::new
                );
            });
        }

        private static IAnimation registerPlayerAnimation(AbstractClientPlayer player) {
            //This will be invoked for every new player
            return new ModifierLayer<>();
        }
    }

}

