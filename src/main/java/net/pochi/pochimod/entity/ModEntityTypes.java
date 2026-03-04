package net.pochi.pochimod.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.minecraft.core.registries.Registries;
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
                    () -> EntityType.Builder.of(SparrowEntity::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "sparrow").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<DeerEntity>> DEER =
            ENTITY_TYPES.register("deer",
                    () -> EntityType.Builder.of(DeerEntity::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.5f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "deer").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<DoeEntity>> DOE =
            ENTITY_TYPES.register("doe",
                    () -> EntityType.Builder.of(DoeEntity::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.2f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "doe").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Sakaban>> SAKABAN =
            ENTITY_TYPES.register("sakaban",
                    () -> EntityType.Builder.of(Sakaban::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.5f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "sakaban").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Cicada>> CICADA =
            ENTITY_TYPES.register("cicada",
                    () -> EntityType.Builder.of(Cicada::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "cicada").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Dragonfly>> DRAGONFLY =
            ENTITY_TYPES.register("dragonfly",
                    () -> EntityType.Builder.of(Dragonfly::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "dragonfly").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Butterfly>> BUTTERFLY =
            ENTITY_TYPES.register("butterfly",
                    () -> EntityType.Builder.of(Butterfly::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "butterfly").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<LongTailTit>> LONG_TIT =
            ENTITY_TYPES.register("long_tit",
                    () -> EntityType.Builder.of(LongTailTit::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "long_tit").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Seal>> SEAL =
            ENTITY_TYPES.register("seal",
                    () -> EntityType.Builder.of(Seal::new, MobCategory.CREATURE)
                            .sized(0.9F, 0.6F)
                            .build("seal"));

    public static final DeferredHolder<EntityType<?>, EntityType<HermitCrab>> HERMIT_CRAB =
            ENTITY_TYPES.register("hermit_crab",
                    () -> EntityType.Builder.of(HermitCrab::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "hermit_crab").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<MiniHipo>> MINI_HIPO =
            ENTITY_TYPES.register("mini_hipo",
                    () -> EntityType.Builder.of(MiniHipo::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.5f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "mini_hipo").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Mongoose>> MONGOOSE =
            ENTITY_TYPES.register("mongoose",
                    () -> EntityType.Builder.of(Mongoose::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.5f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "mongoose").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Ant>> ANT =
            ENTITY_TYPES.register("ant",
                    () -> EntityType.Builder.of(Ant::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "ant").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Etupirka>> ETUPIRKA =
            ENTITY_TYPES.register("etupirka",
                    () -> EntityType.Builder.of(Etupirka::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "etupirka").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Snake>> SNAKE =
            ENTITY_TYPES.register("snake",
                    () -> EntityType.Builder.of(Snake::new, MobCategory.CREATURE)
                            .sized(0.5F, 0.5F)
                            .build("snake"));

    public static final DeferredHolder<EntityType<?>, EntityType<Peacock>> PEACOCK =
            ENTITY_TYPES.register("peacock",
                    () -> EntityType.Builder.of(Peacock::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "peacock").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<BurrowingOwl>> BURROWING_OWL =
            ENTITY_TYPES.register("burrowing_owl",
                    () -> EntityType.Builder.of(BurrowingOwl::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "burrowing_owl").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Folivore>> FOLIVORE =
            ENTITY_TYPES.register("folivore",
                    () -> EntityType.Builder.of(Folivore::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "folivore").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<GiantOtter>> GIANT_OTTER =
            ENTITY_TYPES.register("giant_otter",
                    () -> EntityType.Builder.of(GiantOtter::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "giant_otter").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<GuyanaRupicola>> GUYANA_RUPICOLA =
            ENTITY_TYPES.register("guyana_rupicola",
                    () -> EntityType.Builder.of(GuyanaRupicola::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "guyana_rupicola").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<HarpyEagle>> HARPY_EAGLE =
            ENTITY_TYPES.register("harpy_eagle",
                    () -> EntityType.Builder.of(HarpyEagle::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "harpy_eagle").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<MuskCat>> MUSK_CAT =
            ENTITY_TYPES.register("musk_cat",
                    () -> EntityType.Builder.of(MuskCat::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "musk_cat").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Perisso>> PERISSO =
            ENTITY_TYPES.register("perisso",
                    () -> EntityType.Builder.of(Perisso::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "perisso").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Ratel>> RATEL =
            ENTITY_TYPES.register("ratel",
                    () -> EntityType.Builder.of(Ratel::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "ratel").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Wombat>> WOMBAT =
            ENTITY_TYPES.register("wombat",
                    () -> EntityType.Builder.of(Wombat::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "wombat").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Beaver>> BEAVER =
            ENTITY_TYPES.register("beaver",
                    () -> EntityType.Builder.of(Beaver::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "beaver").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<HammerHeadShark>> HAMMER_HEAD =
            ENTITY_TYPES.register("hammer_head",
                    () -> EntityType.Builder.of(HammerHeadShark::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "hammer_head").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<LeafySeaDragon>> LEAFY_SEA =
            ENTITY_TYPES.register("leafy_sea",
                    () -> EntityType.Builder.of(LeafySeaDragon::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "leafy_sea").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Kiwi>> KIWI =
            ENTITY_TYPES.register("kiwi",
                    () -> EntityType.Builder.of(Kiwi::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "kiwi").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<RockPenguin>> ROCK_PENGUIN =
            ENTITY_TYPES.register("rock_penguin",
                    () -> EntityType.Builder.of(RockPenguin::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "rock_penguin").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Skunk>> SKUNK =
            ENTITY_TYPES.register("skunk",
                    () -> EntityType.Builder.of(Skunk::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "skunk").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Sturgeon>> STURGEON =
            ENTITY_TYPES.register("sturgeon",
                    () -> EntityType.Builder.of(Sturgeon::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "sturgeon").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Quokka>> QUOKKA =
            ENTITY_TYPES.register("quokka",
                    () -> EntityType.Builder.of(Quokka::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "quokka").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<WoodPecker>> WOOD_PECKER =
            ENTITY_TYPES.register("wood_pecker",
                    () -> EntityType.Builder.of(WoodPecker::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "wood_pecker").toString()));


    public static final DeferredHolder<EntityType<?>, EntityType<Felis>> FELIS =
            ENTITY_TYPES.register("felis",
                    () -> EntityType.Builder.of(Felis::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "felis").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<FruitFly>> FRUIT_FLY =
            ENTITY_TYPES.register("fruit_fly",
                    () -> EntityType.Builder.of(FruitFly::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "fruit_fly").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Indicatoridae>> INDICATOR_IDAE =
            ENTITY_TYPES.register("indicator_idae",
                    () -> EntityType.Builder.of(Indicatoridae::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "indicator_idae").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<MantisShrimp>> MANTIS_SHRIMP =
            ENTITY_TYPES.register("mantis_shrimp",
                    () -> EntityType.Builder.of(MantisShrimp::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "mantis_shrimp").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Meerkat>> MEERKAT =
            ENTITY_TYPES.register("meerkat",
                    () -> EntityType.Builder.of(Meerkat::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "meerkat").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<PallasCat>> PALLAS_CAT =
            ENTITY_TYPES.register("pallas_cat",
                    () -> EntityType.Builder.of(PallasCat::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "pallas_cat").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Pangolin>> PANGOLIN =
            ENTITY_TYPES.register("pangolin",
                    () -> EntityType.Builder.of(Pangolin::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "pangolin").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Porcupine>> PORCUPINE =
            ENTITY_TYPES.register("porcupine",
                    () -> EntityType.Builder.of(Porcupine::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "porcupine").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Tapir>> TAPIR =
            ENTITY_TYPES.register("tapir",
                    () -> EntityType.Builder.of(Tapir::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "tapir").toString()));




    public static final DeferredHolder<EntityType<?>, EntityType<Betta>> BETTA =
            ENTITY_TYPES.register("betta",
                    () -> EntityType.Builder.of(Betta::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.8f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "betta").toString()));

    //public static final DeferredHolder<EntityType<?>, EntityType<Albatross>> ALBATROSS =
    //        ENTITY_TYPES.register("albatross",
    //                () -> EntityType.Builder.of(Albatross::new, MobCategory.CREATURE)
    //                        .sized(0.4f, 0.4f)
    //                        .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "albatross").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<IberianPig>> IBERIAN_PIG =
            ENTITY_TYPES.register("iberian_pig",
                    () -> EntityType.Builder.of(IberianPig::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.5f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "iberian_pig").toString()));

    //public static final DeferredHolder<EntityType<?>, EntityType<Uguisu>> UGUISU =
    //        ENTITY_TYPES.register("uguisu",
    //                () -> EntityType.Builder.of(Uguisu::new, MobCategory.CREATURE)
    //                        .sized(0.8f, 1.2f)
    //                        .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "uguisu").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Emu>> EMU =
            ENTITY_TYPES.register("emu",
                    () -> EntityType.Builder.of(Emu::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.5f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "emu").toString()));

    //public static final DeferredHolder<EntityType<?>, EntityType<GiantSalamander>> GIANT_SALAMANDER =
    //        ENTITY_TYPES.register("giant_salamander",
    //                () -> EntityType.Builder.of(GiantSalamander::new, MobCategory.CREATURE)
    //                        .sized(0.4f, 0.4f)
    //                        .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "giant_salamander").toString()));
//
    //public static final DeferredHolder<EntityType<?>, EntityType<TocoToucan>> TOCO_TOUCAN =
    //        ENTITY_TYPES.register("toco_toucan",
    //                () -> EntityType.Builder.of(TocoToucan::new, MobCategory.CREATURE)
    //                        .sized(0.4f, 0.4f)
    //                        .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "toco_toucan").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Capybara>> CAPYBARA =
            ENTITY_TYPES.register("capybara",
                    () -> EntityType.Builder.of(Capybara::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.5f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "toco_toucan").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Chameleon>> CHAMELEON =
            ENTITY_TYPES.register("chameleon",
                    () -> EntityType.Builder.of(Chameleon::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.2f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "chameleon").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Platypus>> PLATYPUS =
            ENTITY_TYPES.register("platypus",
                    () -> EntityType.Builder.of(Platypus::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.5f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "platypus").toString()));

    //public static final DeferredHolder<EntityType<?>, EntityType<Crow>> CROW =
    //        ENTITY_TYPES.register("crow",
    //                () -> EntityType.Builder.of(Crow::new, MobCategory.CREATURE)
    //                        .sized(0.4f, 0.4f)
    //                        .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "crow").toString()));
//
    //public static final DeferredHolder<EntityType<?>, EntityType<Kingfisher>> KINGFISHER =
    //        ENTITY_TYPES.register("kingfisher",
    //                () -> EntityType.Builder.of(Kingfisher::new, MobCategory.CREATURE)
    //                        .sized(0.4f, 0.4f)
    //                        .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "kingfisher").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Giraffe>> GIRAFFE =
            ENTITY_TYPES.register("giraffe",
                    () -> EntityType.Builder.of(Giraffe::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.5f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "giraffe").toString()));

    //public static final DeferredHolder<EntityType<?>, EntityType<KiwahiruStadium>> KIWAHIRU_STADIUM =
    //        ENTITY_TYPES.register("kiwahiru_stadium",
    //                () -> EntityType.Builder.of(KiwahiruStadium::new, MobCategory.CREATURE)
    //                        .sized(0.8f, 1.2f)
    //                        .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "kiwahiru_stadium").toString()));
//
    //public static final DeferredHolder<EntityType<?>, EntityType<Jackal>> JACKAL =
    //        ENTITY_TYPES.register("jackal",
    //                () -> EntityType.Builder.of(Jackal::new, MobCategory.CREATURE)
    //                        .sized(0.8f, 1.5f)
    //                        .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "jackal").toString()));
//
    //public static final DeferredHolder<EntityType<?>, EntityType<GoblinShark>> GOBLIN_SHARK =
    //        ENTITY_TYPES.register("goblin_shark",
    //                () -> EntityType.Builder.of(GoblinShark::new, MobCategory.CREATURE)
    //                        .sized(0.4f, 0.4f)
    //                        .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "goblin_shark").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Clione>> CLIONE =
            ENTITY_TYPES.register("clione",
                    () -> EntityType.Builder.of(Clione::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "clione").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<SootyShearwater>> SOOTY_SHEARWATER =
            ENTITY_TYPES.register("sooty_shearwater",
                    () -> EntityType.Builder.of(SootyShearwater::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.5f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "sooty_shearwater").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<AsianBear>> ASIAN_BEAR =
            ENTITY_TYPES.register("asian_bear",
                    () -> EntityType.Builder.of(AsianBear::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.2f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "asian_bear").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<ElectricCatfish>> ELECTRIC_CATFISH =
            ENTITY_TYPES.register("electric_catfish",
                    () -> EntityType.Builder.of(ElectricCatfish::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.5f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "electric_catfish").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<FlowerMantis>> FLOWER_MANTIS =
            ENTITY_TYPES.register("flower_mantis",
                    () -> EntityType.Builder.of(FlowerMantis::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "flower_mantis").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Cassowary>> CASSOWARY =
            ENTITY_TYPES.register("cassowary",
                    () -> EntityType.Builder.of(Cassowary::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "cassowary").toString()));

    //public static final DeferredHolder<EntityType<?>, EntityType<ScarletMacaw>> SCARLET_MACAW =
    //        ENTITY_TYPES.register("scarlet_macaw",
    //                () -> EntityType.Builder.of(ScarletMacaw::new, MobCategory.CREATURE)
    //                        .sized(0.8f, 1.2f)
    //                        .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "scarlet_macaw").toString()));
//
    //public static final DeferredHolder<EntityType<?>, EntityType<SecretaryBird>> SECRETARY_BIRD =
    //        ENTITY_TYPES.register("secretary_bird",
    //                () -> EntityType.Builder.of(SecretaryBird::new, MobCategory.CREATURE)
    //                        .sized(0.8f, 1.5f)
    //                        .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "secretary_bird").toString()));
//
    //public static final DeferredHolder<EntityType<?>, EntityType<Possum>> POSSUM =
    //        ENTITY_TYPES.register("possum",
    //                () -> EntityType.Builder.of(Possum::new, MobCategory.CREATURE)
    //                        .sized(0.4f, 0.4f)
    //                        .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "possum").toString()));
//
    //public static final DeferredHolder<EntityType<?>, EntityType<HoneyPotAnt>> HONEY_POT_ANT =
    //        ENTITY_TYPES.register("honey_pot_ant",
    //                () -> EntityType.Builder.of(HoneyPotAnt::new, MobCategory.CREATURE)
    //                        .sized(0.4f, 0.4f)
    //                        .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "honey_pot_ant").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<AncientLizard>> ANCIENT_LIZARD =
            ENTITY_TYPES.register("ancient_lizard",
                    () -> EntityType.Builder.of(AncientLizard::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.5f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "ancient_lizard").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Mole>> MOLE =
            ENTITY_TYPES.register("mole",
                    () -> EntityType.Builder.of(Mole::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.2f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "mole").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<UralOwl>> URAL_OWL =
            ENTITY_TYPES.register("ural_owl",
                    () -> EntityType.Builder.of(UralOwl::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.5f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "ural_owl").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<LeopardGecko>> LEOPARD_GECKO =
            ENTITY_TYPES.register("leopard_gecko",
                    () -> EntityType.Builder.of(LeopardGecko::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "leopard_gecko").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Crocodile>> CROCODILE =
            ENTITY_TYPES.register("crocodile",
                    () -> EntityType.Builder.of(Crocodile::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "crocodile").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Rhino>> RHINO =
            ENTITY_TYPES.register("rhino",
                    () -> EntityType.Builder.of(Rhino::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "rhino").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Squirrel>> SQUIRREL =
            ENTITY_TYPES.register("squirrel",
                    () -> EntityType.Builder.of(Squirrel::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.4f)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "squirrel").toString()));



    //動物じゃない
    public static final DeferredHolder<EntityType<?>, EntityType<OreSlime>> ORE_SEARCH =
            ENTITY_TYPES.register("ore_search",
                    () -> EntityType.Builder.of(OreSlime::new, MobCategory.CREATURE)
                            .sized(0.1f, 0.1f).fireImmune()
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "ore_search").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<HookArrow>> HOOK_ARROW =
            ENTITY_TYPES.register("hook_arrow",
                    () -> EntityType.Builder.<HookArrow>of(HookArrow::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            .setTrackingRange(100)
                            .setShouldReceiveVelocityUpdates(true)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "hook_arrow").toString()));

    //public static final DeferredHolder<EntityType<?>, EntityType<CreateTntEntity>> CREATE_TNT =
    //        ENTITY_TYPES.register("create_tnt",
    //                () -> EntityType.Builder.<CreateTntEntity>of(CreateTntEntity::new, MobCategory.MISC)
    //                        .fireImmune().sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(10)
    //                        .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "create_tnt").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<DirtGolem>> DIRT_GOLEM =
            ENTITY_TYPES.register("dirt_golem",
                    () -> EntityType.Builder.of(DirtGolem::new, MobCategory.CREATURE)
                            .sized(1.4F, 2.7F).clientTrackingRange(10)
                            .build("dirt_golem"));

    public static final DeferredHolder<EntityType<?>, EntityType<PickaxeHead>> PICKAXE_HEAD =
            ENTITY_TYPES.register("pickaxe_head",
                    () -> EntityType.Builder.of(PickaxeHead::new, MobCategory.CREATURE)
                            .sized(0.25F, 0.25F)
                            .clientTrackingRange(4)
                            .updateInterval(4)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "pickaxe_head").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<DeltaMovementBlockEntity>> DELTA_BLOCK_O =
            ENTITY_TYPES.register("delta_block_o",
                    () -> EntityType.Builder.<DeltaMovementBlockEntity>of(DeltaMovementBlockEntity::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            .setTrackingRange(100)
                            .setShouldReceiveVelocityUpdates(true)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "delta_block_o").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<BlockArrowEntity>> BLOCK_ARROW =
            ENTITY_TYPES.register("block_arrow",
                    () -> EntityType.Builder.<BlockArrowEntity>of(BlockArrowEntity::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            .setTrackingRange(100)
                            .setShouldReceiveVelocityUpdates(true)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block_arrow").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<PickaxeHook>> PICKAXE_HOOK =
            ENTITY_TYPES.register("pickaxe_hook",
                    () -> EntityType.Builder.<PickaxeHook>of(PickaxeHook::new, MobCategory.MISC)
                            .noSave().noSummon().sized(0.25F, 0.25F)
                            .clientTrackingRange(4)
                            .updateInterval(5)
                            .build(ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "pickaxe_hook").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<IceProjectileEntity>> ICE_PROJECTILE =
            ENTITY_TYPES.register("ice_projectile",
                    () -> EntityType.Builder.<IceProjectileEntity>of(IceProjectileEntity::new, MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .clientTrackingRange(4)
                            .updateInterval(10)
                            .build("ice_projectile")
            );

    public static final DeferredHolder<EntityType<?>, EntityType<ScytheProjectileEntity>> SCYTHE_PROJECTILE =
            ENTITY_TYPES.register("scythe_projectile",
                    () -> EntityType.Builder.<ScytheProjectileEntity>of(ScytheProjectileEntity::new, MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .clientTrackingRange(8)
                            .updateInterval(5)
                            .build("scythe_projectile")
            );

    public static final DeferredHolder<EntityType<?>, EntityType<SummonedClioneEntity>> SUMMONED_CLIONE =
            ENTITY_TYPES.register("summoned_clione",
                    () -> EntityType.Builder.<SummonedClioneEntity>of(SummonedClioneEntity::new, MobCategory.MISC)
                            .sized(0.6F, 0.6F)
                            .clientTrackingRange(8)
                            .updateInterval(3)
                            .build("summoned_clione")
            );

    public static final DeferredHolder<EntityType<?>, EntityType<FlowerBombEntity>> FLOWER_BOMB =
            ENTITY_TYPES.register("flower_bomb",
                    () -> EntityType.Builder.<FlowerBombEntity>of(FlowerBombEntity::new, MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .clientTrackingRange(10)
                            .updateInterval(1)
                            .build("flower_bomb")
            );

    public static final DeferredHolder<EntityType<?>, EntityType<JawChainProjectileEntity>> JAW_CHAIN_PROJECTILE =
            ENTITY_TYPES.register("jaw_chain_projectile",
                    () -> EntityType.Builder.<JawChainProjectileEntity>of(JawChainProjectileEntity::new, MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .clientTrackingRange(8)
                            .updateInterval(5)
                            .build("jaw_chain_projectile")
            );

    public static final DeferredHolder<EntityType<?>, EntityType<DecoyTailEntity>> DECOY_TAIL =
            ENTITY_TYPES.register("decoy_tail",
                    () -> EntityType.Builder.<DecoyTailEntity>of(DecoyTailEntity::new, MobCategory.MISC)
                            .sized(0.4F, 0.3F)
                            .clientTrackingRange(8)
                            .updateInterval(3)
                            .build("decoy_tail")
            );

    public static final DeferredHolder<EntityType<?>, EntityType<FlyingBoatEntity>> FLY_BOAT =
            ENTITY_TYPES.register("fly_boat", () -> EntityType.Builder.<FlyingBoatEntity>of(FlyingBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("fly_boat"));

    public static final DeferredHolder<EntityType<?>, EntityType<FlyingChestBoatEntity>> FLY_CHEST_BOAT =
            ENTITY_TYPES.register("fly_chest_boat", () -> EntityType.Builder.<FlyingChestBoatEntity>of(FlyingChestBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("fly_chest_boat"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
