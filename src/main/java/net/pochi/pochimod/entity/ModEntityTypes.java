package net.pochi.pochimod.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.entity.custom.*;
import net.pochi.pochimod.entity.projectile.*;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, PochiMod.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<SparrowEntity>> SPARROW =
            ENTITY_TYPES.register("sparrow",
                    key -> EntityType.Builder.of(SparrowEntity::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<DeerEntity>> DEER =
            ENTITY_TYPES.register("deer",
                    key -> EntityType.Builder.of(DeerEntity::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.5f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<DoeEntity>> DOE =
            ENTITY_TYPES.register("doe",
                    key -> EntityType.Builder.of(DoeEntity::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.2f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Sakaban>> SAKABAN =
            ENTITY_TYPES.register("sakaban",
                    key -> EntityType.Builder.of(Sakaban::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.5f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Cicada>> CICADA =
            ENTITY_TYPES.register("cicada",
                    key -> EntityType.Builder.of(Cicada::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Dragonfly>> DRAGONFLY =
            ENTITY_TYPES.register("dragonfly",
                    key -> EntityType.Builder.of(Dragonfly::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Butterfly>> BUTTERFLY =
            ENTITY_TYPES.register("butterfly",
                    key -> EntityType.Builder.of(Butterfly::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<LongTailTit>> LONG_TIT =
            ENTITY_TYPES.register("long_tit",
                    key -> EntityType.Builder.of(LongTailTit::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Seal>> SEAL =
            ENTITY_TYPES.register("seal",
                    key -> EntityType.Builder.of(Seal::new, MobCategory.CREATURE)
                            .sized(0.9F, 0.6F)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<HermitCrab>> HERMIT_CRAB =
            ENTITY_TYPES.register("hermit_crab",
                    key -> EntityType.Builder.of(HermitCrab::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<MiniHipo>> MINI_HIPO =
            ENTITY_TYPES.register("mini_hipo",
                    key -> EntityType.Builder.of(MiniHipo::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.5f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Mongoose>> MONGOOSE =
            ENTITY_TYPES.register("mongoose",
                    key -> EntityType.Builder.of(Mongoose::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.5f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Ant>> ANT =
            ENTITY_TYPES.register("ant",
                    key -> EntityType.Builder.of(Ant::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Etupirka>> ETUPIRKA =
            ENTITY_TYPES.register("etupirka",
                    key -> EntityType.Builder.of(Etupirka::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Snake>> SNAKE =
            ENTITY_TYPES.register("snake",
                    key -> EntityType.Builder.of(Snake::new, MobCategory.CREATURE)
                            .sized(0.5F, 0.5F)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Peacock>> PEACOCK =
            ENTITY_TYPES.register("peacock",
                    key -> EntityType.Builder.of(Peacock::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<BurrowingOwl>> BURROWING_OWL =
            ENTITY_TYPES.register("burrowing_owl",
                    key -> EntityType.Builder.of(BurrowingOwl::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Folivore>> FOLIVORE =
            ENTITY_TYPES.register("folivore",
                    key -> EntityType.Builder.of(Folivore::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<GiantOtter>> GIANT_OTTER =
            ENTITY_TYPES.register("giant_otter",
                    key -> EntityType.Builder.of(GiantOtter::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<GuyanaRupicola>> GUYANA_RUPICOLA =
            ENTITY_TYPES.register("guyana_rupicola",
                    key -> EntityType.Builder.of(GuyanaRupicola::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<HarpyEagle>> HARPY_EAGLE =
            ENTITY_TYPES.register("harpy_eagle",
                    key -> EntityType.Builder.of(HarpyEagle::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<MuskCat>> MUSK_CAT =
            ENTITY_TYPES.register("musk_cat",
                    key -> EntityType.Builder.of(MuskCat::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Perisso>> PERISSO =
            ENTITY_TYPES.register("perisso",
                    key -> EntityType.Builder.of(Perisso::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Ratel>> RATEL =
            ENTITY_TYPES.register("ratel",
                    key -> EntityType.Builder.of(Ratel::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Wombat>> WOMBAT =
            ENTITY_TYPES.register("wombat",
                    key -> EntityType.Builder.of(Wombat::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Beaver>> BEAVER =
            ENTITY_TYPES.register("beaver",
                    key -> EntityType.Builder.of(Beaver::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<HammerHeadShark>> HAMMER_HEAD =
            ENTITY_TYPES.register("hammer_head",
                    key -> EntityType.Builder.of(HammerHeadShark::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<LeafySeaDragon>> LEAFY_SEA =
            ENTITY_TYPES.register("leafy_sea",
                    key -> EntityType.Builder.of(LeafySeaDragon::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Kiwi>> KIWI =
            ENTITY_TYPES.register("kiwi",
                    key -> EntityType.Builder.of(Kiwi::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<RockPenguin>> ROCK_PENGUIN =
            ENTITY_TYPES.register("rock_penguin",
                    key -> EntityType.Builder.of(RockPenguin::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Skunk>> SKUNK =
            ENTITY_TYPES.register("skunk",
                    key -> EntityType.Builder.of(Skunk::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Sturgeon>> STURGEON =
            ENTITY_TYPES.register("sturgeon",
                    key -> EntityType.Builder.of(Sturgeon::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Quokka>> QUOKKA =
            ENTITY_TYPES.register("quokka",
                    key -> EntityType.Builder.of(Quokka::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<WoodPecker>> WOOD_PECKER =
            ENTITY_TYPES.register("wood_pecker",
                    key -> EntityType.Builder.of(WoodPecker::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));


    public static final DeferredHolder<EntityType<?>, EntityType<Felis>> FELIS =
            ENTITY_TYPES.register("felis",
                    key -> EntityType.Builder.of(Felis::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<FruitFly>> FRUIT_FLY =
            ENTITY_TYPES.register("fruit_fly",
                    key -> EntityType.Builder.of(FruitFly::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Indicatoridae>> INDICATOR_IDAE =
            ENTITY_TYPES.register("indicator_idae",
                    key -> EntityType.Builder.of(Indicatoridae::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<MantisShrimp>> MANTIS_SHRIMP =
            ENTITY_TYPES.register("mantis_shrimp",
                    key -> EntityType.Builder.of(MantisShrimp::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Meerkat>> MEERKAT =
            ENTITY_TYPES.register("meerkat",
                    key -> EntityType.Builder.of(Meerkat::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<PallasCat>> PALLAS_CAT =
            ENTITY_TYPES.register("pallas_cat",
                    key -> EntityType.Builder.of(PallasCat::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Pangolin>> PANGOLIN =
            ENTITY_TYPES.register("pangolin",
                    key -> EntityType.Builder.of(Pangolin::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Porcupine>> PORCUPINE =
            ENTITY_TYPES.register("porcupine",
                    key -> EntityType.Builder.of(Porcupine::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Tapir>> TAPIR =
            ENTITY_TYPES.register("tapir",
                    key -> EntityType.Builder.of(Tapir::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));




    public static final DeferredHolder<EntityType<?>, EntityType<Betta>> BETTA =
            ENTITY_TYPES.register("betta",
                    key -> EntityType.Builder.of(Betta::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.8f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    //public static final DeferredHolder<EntityType<?>, EntityType<Albatross>> ALBATROSS =
    //        ENTITY_TYPES.register("albatross",
    //                key -> EntityType.Builder.of(Albatross::new, MobCategory.CREATURE)
    //                        .sized(0.4f, 0.4f)
    //                        .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<IberianPig>> IBERIAN_PIG =
            ENTITY_TYPES.register("iberian_pig",
                    key -> EntityType.Builder.of(IberianPig::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.5f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    //public static final DeferredHolder<EntityType<?>, EntityType<Uguisu>> UGUISU =
    //        ENTITY_TYPES.register("uguisu",
    //                key -> EntityType.Builder.of(Uguisu::new, MobCategory.CREATURE)
    //                        .sized(0.8f, 1.2f)
    //                        .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Emu>> EMU =
            ENTITY_TYPES.register("emu",
                    key -> EntityType.Builder.of(Emu::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.5f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    //public static final DeferredHolder<EntityType<?>, EntityType<GiantSalamander>> GIANT_SALAMANDER =
    //        ENTITY_TYPES.register("giant_salamander",
    //                key -> EntityType.Builder.of(GiantSalamander::new, MobCategory.CREATURE)
    //                        .sized(0.4f, 0.4f)
    //                        .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));
//
    //public static final DeferredHolder<EntityType<?>, EntityType<TocoToucan>> TOCO_TOUCAN =
    //        ENTITY_TYPES.register("toco_toucan",
    //                key -> EntityType.Builder.of(TocoToucan::new, MobCategory.CREATURE)
    //                        .sized(0.4f, 0.4f)
    //                        .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Capybara>> CAPYBARA =
            ENTITY_TYPES.register("capybara",
                    key -> EntityType.Builder.of(Capybara::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.5f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Chameleon>> CHAMELEON =
            ENTITY_TYPES.register("chameleon",
                    key -> EntityType.Builder.of(Chameleon::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.2f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Platypus>> PLATYPUS =
            ENTITY_TYPES.register("platypus",
                    key -> EntityType.Builder.of(Platypus::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.5f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    //public static final DeferredHolder<EntityType<?>, EntityType<Crow>> CROW =
    //        ENTITY_TYPES.register("crow",
    //                key -> EntityType.Builder.of(Crow::new, MobCategory.CREATURE)
    //                        .sized(0.4f, 0.4f)
    //                        .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));
//
    //public static final DeferredHolder<EntityType<?>, EntityType<Kingfisher>> KINGFISHER =
    //        ENTITY_TYPES.register("kingfisher",
    //                key -> EntityType.Builder.of(Kingfisher::new, MobCategory.CREATURE)
    //                        .sized(0.4f, 0.4f)
    //                        .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Giraffe>> GIRAFFE =
            ENTITY_TYPES.register("giraffe",
                    key -> EntityType.Builder.of(Giraffe::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.5f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    //public static final DeferredHolder<EntityType<?>, EntityType<KiwahiruStadium>> KIWAHIRU_STADIUM =
    //        ENTITY_TYPES.register("kiwahiru_stadium",
    //                key -> EntityType.Builder.of(KiwahiruStadium::new, MobCategory.CREATURE)
    //                        .sized(0.8f, 1.2f)
    //                        .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));
//
    //public static final DeferredHolder<EntityType<?>, EntityType<Jackal>> JACKAL =
    //        ENTITY_TYPES.register("jackal",
    //                key -> EntityType.Builder.of(Jackal::new, MobCategory.CREATURE)
    //                        .sized(0.8f, 1.5f)
    //                        .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));
//
    //public static final DeferredHolder<EntityType<?>, EntityType<GoblinShark>> GOBLIN_SHARK =
    //        ENTITY_TYPES.register("goblin_shark",
    //                key -> EntityType.Builder.of(GoblinShark::new, MobCategory.CREATURE)
    //                        .sized(0.4f, 0.4f)
    //                        .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Clione>> CLIONE =
            ENTITY_TYPES.register("clione",
                    key -> EntityType.Builder.of(Clione::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<SootyShearwater>> SOOTY_SHEARWATER =
            ENTITY_TYPES.register("sooty_shearwater",
                    key -> EntityType.Builder.of(SootyShearwater::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.5f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<AsianBear>> ASIAN_BEAR =
            ENTITY_TYPES.register("asian_bear",
                    key -> EntityType.Builder.of(AsianBear::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.2f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<ElectricCatfish>> ELECTRIC_CATFISH =
            ENTITY_TYPES.register("electric_catfish",
                    key -> EntityType.Builder.of(ElectricCatfish::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.5f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<FlowerMantis>> FLOWER_MANTIS =
            ENTITY_TYPES.register("flower_mantis",
                    key -> EntityType.Builder.of(FlowerMantis::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Cassowary>> CASSOWARY =
            ENTITY_TYPES.register("cassowary",
                    key -> EntityType.Builder.of(Cassowary::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    //public static final DeferredHolder<EntityType<?>, EntityType<ScarletMacaw>> SCARLET_MACAW =
    //        ENTITY_TYPES.register("scarlet_macaw",
    //                key -> EntityType.Builder.of(ScarletMacaw::new, MobCategory.CREATURE)
    //                        .sized(0.8f, 1.2f)
    //                        .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));
//
    //public static final DeferredHolder<EntityType<?>, EntityType<SecretaryBird>> SECRETARY_BIRD =
    //        ENTITY_TYPES.register("secretary_bird",
    //                key -> EntityType.Builder.of(SecretaryBird::new, MobCategory.CREATURE)
    //                        .sized(0.8f, 1.5f)
    //                        .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));
//
    //public static final DeferredHolder<EntityType<?>, EntityType<Possum>> POSSUM =
    //        ENTITY_TYPES.register("possum",
    //                key -> EntityType.Builder.of(Possum::new, MobCategory.CREATURE)
    //                        .sized(0.4f, 0.4f)
    //                        .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));
//
    //public static final DeferredHolder<EntityType<?>, EntityType<HoneyPotAnt>> HONEY_POT_ANT =
    //        ENTITY_TYPES.register("honey_pot_ant",
    //                key -> EntityType.Builder.of(HoneyPotAnt::new, MobCategory.CREATURE)
    //                        .sized(0.4f, 0.4f)
    //                        .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<AncientLizard>> ANCIENT_LIZARD =
            ENTITY_TYPES.register("ancient_lizard",
                    key -> EntityType.Builder.of(AncientLizard::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.5f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Mole>> MOLE =
            ENTITY_TYPES.register("mole",
                    key -> EntityType.Builder.of(Mole::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.2f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<UralOwl>> URAL_OWL =
            ENTITY_TYPES.register("ural_owl",
                    key -> EntityType.Builder.of(UralOwl::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.5f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<LeopardGecko>> LEOPARD_GECKO =
            ENTITY_TYPES.register("leopard_gecko",
                    key -> EntityType.Builder.of(LeopardGecko::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Crocodile>> CROCODILE =
            ENTITY_TYPES.register("crocodile",
                    key -> EntityType.Builder.of(Crocodile::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Rhino>> RHINO =
            ENTITY_TYPES.register("rhino",
                    key -> EntityType.Builder.of(Rhino::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<Squirrel>> SQUIRREL =
            ENTITY_TYPES.register("squirrel",
                    key -> EntityType.Builder.of(Squirrel::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));



    //動物じゃない
    public static final DeferredHolder<EntityType<?>, EntityType<OreSlime>> ORE_SEARCH =
            ENTITY_TYPES.register("ore_search",
                    key -> EntityType.Builder.of(OreSlime::new, MobCategory.CREATURE)
                            .sized(0.1f, 0.1f).fireImmune()
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<HookArrow>> HOOK_ARROW =
            ENTITY_TYPES.register("hook_arrow",
                    key -> EntityType.Builder.<HookArrow>of(HookArrow::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            .setTrackingRange(100)
                            .setShouldReceiveVelocityUpdates(true)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    //public static final DeferredHolder<EntityType<?>, EntityType<CreateTntEntity>> CREATE_TNT =
    //        ENTITY_TYPES.register("create_tnt",
    //                key -> EntityType.Builder.<CreateTntEntity>of(CreateTntEntity::new, MobCategory.MISC)
    //                        .fireImmune().sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(10)
    //                        .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<DirtGolem>> DIRT_GOLEM =
            ENTITY_TYPES.register("dirt_golem",
                    key -> EntityType.Builder.of(DirtGolem::new, MobCategory.CREATURE)
                            .sized(1.4F, 2.7F).clientTrackingRange(10)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<PickaxeHead>> PICKAXE_HEAD =
            ENTITY_TYPES.register("pickaxe_head",
                    key -> EntityType.Builder.of(PickaxeHead::new, MobCategory.CREATURE)
                            .sized(0.25F, 0.25F)
                            .clientTrackingRange(4)
                            .updateInterval(4)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<DeltaMovementBlockEntity>> DELTA_BLOCK_O =
            ENTITY_TYPES.register("delta_block_o",
                    key -> EntityType.Builder.<DeltaMovementBlockEntity>of(DeltaMovementBlockEntity::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            .setTrackingRange(100)
                            .setShouldReceiveVelocityUpdates(true)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<BlockArrowEntity>> BLOCK_ARROW =
            ENTITY_TYPES.register("block_arrow",
                    key -> EntityType.Builder.<BlockArrowEntity>of(BlockArrowEntity::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            .setTrackingRange(100)
                            .setShouldReceiveVelocityUpdates(true)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<PickaxeHook>> PICKAXE_HOOK =
            ENTITY_TYPES.register("pickaxe_hook",
                    key -> EntityType.Builder.<PickaxeHook>of(PickaxeHook::new, MobCategory.MISC)
                            .noSave().noSummon().sized(0.25F, 0.25F)
                            .clientTrackingRange(4)
                            .updateInterval(5)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<IceProjectileEntity>> ICE_PROJECTILE =
            ENTITY_TYPES.register("ice_projectile",
                    key -> EntityType.Builder.<IceProjectileEntity>of(IceProjectileEntity::new, MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .clientTrackingRange(4)
                            .updateInterval(10)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key))
            );

    public static final DeferredHolder<EntityType<?>, EntityType<ScytheProjectileEntity>> SCYTHE_PROJECTILE =
            ENTITY_TYPES.register("scythe_projectile",
                    key -> EntityType.Builder.<ScytheProjectileEntity>of(ScytheProjectileEntity::new, MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .clientTrackingRange(8)
                            .updateInterval(5)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key))
            );

    public static final DeferredHolder<EntityType<?>, EntityType<SummonedClioneEntity>> SUMMONED_CLIONE =
            ENTITY_TYPES.register("summoned_clione",
                    key -> EntityType.Builder.<SummonedClioneEntity>of(SummonedClioneEntity::new, MobCategory.MISC)
                            .sized(0.6F, 0.6F)
                            .clientTrackingRange(8)
                            .updateInterval(3)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key))
            );

    public static final DeferredHolder<EntityType<?>, EntityType<FlowerBombEntity>> FLOWER_BOMB =
            ENTITY_TYPES.register("flower_bomb",
                    key -> EntityType.Builder.<FlowerBombEntity>of(FlowerBombEntity::new, MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .clientTrackingRange(10)
                            .updateInterval(1)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key))
            );

    public static final DeferredHolder<EntityType<?>, EntityType<JawChainProjectileEntity>> JAW_CHAIN_PROJECTILE =
            ENTITY_TYPES.register("jaw_chain_projectile",
                    key -> EntityType.Builder.<JawChainProjectileEntity>of(JawChainProjectileEntity::new, MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .clientTrackingRange(8)
                            .updateInterval(5)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key))
            );

    public static final DeferredHolder<EntityType<?>, EntityType<DecoyTailEntity>> DECOY_TAIL =
            ENTITY_TYPES.register("decoy_tail",
                    key -> EntityType.Builder.<DecoyTailEntity>of(DecoyTailEntity::new, MobCategory.MISC)
                            .sized(0.4F, 0.3F)
                            .clientTrackingRange(8)
                            .updateInterval(3)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, key))
            );

    public static final DeferredHolder<EntityType<?>, EntityType<FlyingBoatEntity>> FLY_BOAT =
            ENTITY_TYPES.register("fly_boat", key -> EntityType.Builder.<FlyingBoatEntity>of(FlyingBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static final DeferredHolder<EntityType<?>, EntityType<FlyingChestBoatEntity>> FLY_CHEST_BOAT =
            ENTITY_TYPES.register("fly_chest_boat", key -> EntityType.Builder.<FlyingChestBoatEntity>of(FlyingChestBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build(ResourceKey.create(Registries.ENTITY_TYPE, key)));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
