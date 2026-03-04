package net.pochi.pochimod.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.client.*;
import net.pochi.pochimod.entity.layer.ModModelLayers;

@EventBusSubscriber(modid = PochiMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {





    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.SEAL_LAYER, SealModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.LONGTIT_LAYER, LongTailTitModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.HERMIT_CRAB_LAYER, HermitCrabModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.MINI_HIPO_LAYER, MiniHipoModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.MONGOOSE_LAYER, MongooseModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.ANT_LAYER, AntModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.ETUPIRKA_LAYER, EtupirkaModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.PEACOCK_LAYER, PeacockModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.DEER_LAYER, DeerModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.DOE_LAYER, DoeModel::createBodyLayer);

        event.registerLayerDefinition(ModModelLayers.BURROWING_LAYER, BurrowingOwlModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.FPLIVORE_LAYER, FolivoreModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.GIANT_OTTER_LAYER, GiantOtterModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.RUPICO_LAYER, GuyanaRupicolaModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.HARPY_LAYER, HarpyEagleModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.MUSK_LAYER, MuskCatModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.RATEL_LAYER, RatelModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.WOMBAT_LAYER, WombatModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.PERISSO_LAYER, PerissoModel::createBodyLayer);

        event.registerLayerDefinition(ModModelLayers.BEAVER_LAYER, BeaverModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.HAMMER_HEAD_LAYER, HammerHeadSharkModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.LEAFY_LAYER, LeafySeaDragonModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.KIWI_LAYER, KiwiModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SKUNK_LAYER, SkunkModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.STURGEON_LAYER, SturgeonModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.ROCK_PENGUIN_LAYER, RockPenguinModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.QUOKKA_LAYER, QuokkaModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.WOODPECKER_LAYER, WoodPeckerModel::createBodyLayer);

        event.registerLayerDefinition(ModModelLayers.FELIS_LAYER, FelisModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.FRUIT_FLY_LAYER, FruitFlyModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.INDICATOR_LAYER, IndicatoridaeModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.MANTIS_SHRIMP_LAYER, MantisShrimpModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.MEERKAT_LAYER, MeerkatModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.PALLAS_CAT_LAYER, PallasCatModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.PANGOLIN_LAYER, PangolinModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.PORCUPINE_LAYER, PorcupineModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.TAPIR_LAYER, TapirModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.CHAMELEON_LAYER, ChameleonModel::createBodyLayer);

        event.registerLayerDefinition(ModModelLayers.BETTA_LAYER, BettaModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.ALBATROSS_LAYER, AlbatrossModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.IBERIAN_PIG_LAYER, IberianPigModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.EMU_LAYER, EmuModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.CAPYBARA_LAYER, CapybaraModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.PLATYPUS_LAYER, PlatypusModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.GIRAFFE_LAYER, GiraffeModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.CLIONE_LAYER, ClioneModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SOOTY_SHEARWATER_LAYER, SootyShearwaterModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.ASIAN_BEAR_LAYER, AsianBearModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.ELECTRIC_CATFISH_LAYER, ElectricCatfishModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.FLOWER_MANTIS_LAYER, FlowerMantisModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.CASSOWARY_LAYER, CassowaryModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.ANCIENT_LIZARD_LAYER, AncientLizardModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.MOLE_LAYER, MoleModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.URAL_OWL_LAYER, UralOwlModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.LEOPARD_GECKO_LAYER, LeopardGeckoModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.CROCODILE_LAYER, CrocodileModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.RHINO_LAYER, RhinoModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SQUIRREL_LAYER, SquirrelModel::createBodyLayer);
    }
}
