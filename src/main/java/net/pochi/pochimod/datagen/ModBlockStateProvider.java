package net.pochi.pochimod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PochiMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        blockWithItem(ModBlocks.CHROMITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_CHROMITE_ORE);
        blockWithItem(ModBlocks.NETHERRACK_CHROMITE_ORE);
        blockWithItem(ModBlocks.FLUORITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_FLUORITE_ORE);
        blockWithItem(ModBlocks.NETHERRACK_FLUORITE_ORE);
        blockWithItem(ModBlocks.ALUNITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ALUNITE_ORE);
        blockWithItem(ModBlocks.NETHERRACK_ALUNITE_ORE);
        blockWithItem(ModBlocks.BAUXITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_BAUXITE_ORE);
        blockWithItem(ModBlocks.NETHERRACK_BAUXITE_ORE);
        blockWithItem(ModBlocks.TITANIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_TITANIUM_ORE);
        blockWithItem(ModBlocks.NETHERRACK_TITANIUM_ORE);
        blockWithItem(ModBlocks.MAGUNESIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_MAGUNESIUM_ORE);
        blockWithItem(ModBlocks.NETHERRACK_MAGUNESIUM_ORE);
        blockWithItem(ModBlocks.VANADIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_VANADIUM_ORE);
        blockWithItem(ModBlocks.NETHERRACK_VANADIUM_ORE);


        blockWithItem(ModBlocks.CHROMITE_BLOCK);
        blockWithItem(ModBlocks.STAINLESS_BLOCK);
        blockWithItem(ModBlocks.FLUORITE_BLOCK);
        blockWithItem(ModBlocks.ALUNITE_BLOCK);
        blockWithItem(ModBlocks.ALUMINUM_BLOCK);
        blockWithItem(ModBlocks.TITANIUM_BLOCK);
        blockWithItem(ModBlocks.MAGUNESIUM_BLOCK);
        blockWithItem(ModBlocks.VANADIUM_BLOCK);


        blockWithItem(ModBlocks.SANITARY_BLOCK);


        //makeCrop((AsparagusBlock)ModBlocks.ASPARAGUS.get(),"asparagus_stage","asparagus_stage");
        //makeCrop((CabbageBlock)ModBlocks.CABBAGE.get(),"cabbage_stage","cabbage_stage");
        //makeCrop((RiceMaltBlock)ModBlocks.RICE_BLOCK.get(),"rice_stage","rice_stage");
        //makeCrop((MintBlock)ModBlocks.MINT_BLOCK.get(),"mint_stage","mint_stage");
        //makeCrop((TomatoBlock)ModBlocks.TOMATO_BLOCK.get(),"tomato_stage","tomato_stage");
        //makeCrop((CornBlock)ModBlocks.CORN_BLOCK.get(),"corn_stage","corn_stage");
        //makeCrop((OnionBlock)ModBlocks.ONION_BLOCK.get(),"onion_stage","onion_stage");
        //makeCrop((GingerBlock)ModBlocks.GINGER_BLOCK.get(),"ginger_stage","ginger_stage");
        //makeCrop((GreenPepperBlock)ModBlocks.GREEN_PEPPER_BLOCK.get(),"green_pepper_stage","green_pepper_stage");
        //makeCrop((PaprikaBlock)ModBlocks.PAPRIKA_BLOCK.get(),"paprika_stage","paprika_stage");
        //makeCrop((EggplantBlock)ModBlocks.EGGPLANT_BLOCK.get(),"eggplant_stage","eggplant_stage");
        //makeCrop((WhiteRadishBlock)ModBlocks.WHITE_RADISH_BLOCK.get(),"white_radish_stage","white_radish_stage");
        //makeCrop((ChiliPepperBlock)ModBlocks.CHILI_PEPPER_BLOCK.get(),"chili_pepper_stage","chili_pepper_stage");
        //makeCrop((BasilBlock)ModBlocks.BASIL_BLOCK.get(),"basil_stage","basil_stage");
        //makeCrop((LotusRootBlock)ModBlocks.LOTUS_ROOT_BLOCK.get(),"lotus_root_stage","lotus_root_stage");

        logBlock(((RotatedPillarBlock) ModBlocks.CABERNET_SAUVIGNON_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.CABERNET_SAUVIGNON_WOOD.get(), blockTexture(ModBlocks.CABERNET_SAUVIGNON_LOG.get()), blockTexture(ModBlocks.CABERNET_SAUVIGNON_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_CABERNET_SAUVIGNON_LOG.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_cabernet_sauvignon_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_cabernet_sauvignon_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_CABERNET_SAUVIGNON_WOOD.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_cabernet_sauvignon_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_cabernet_sauvignon_log"));

        blockWithItem(ModBlocks.CABERNET_SAUVIGNON_PLANKS);
        blockWithItem(ModBlocks.CABERNET_SAUVIGNON_LEAVES);
        saplingBlock(ModBlocks.CABERNET_SAUVIGNON_SAPLING);

        simpleBlockItem(ModBlocks.CABERNET_SAUVIGNON_LOG.get(), models().withExistingParent("pochimod:cabernet_sauvignon_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.CABERNET_SAUVIGNON_WOOD.get(), models().withExistingParent("pochimod:cabernet_sauvignon_wood", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_CABERNET_SAUVIGNON_LOG.get(), models().withExistingParent("pochimod:stripped_cabernet_sauvignon_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_CABERNET_SAUVIGNON_WOOD.get(), models().withExistingParent("pochimod:stripped_cabernet_sauvignon_wood", "minecraft:block/cube_column"));



        logBlock(((RotatedPillarBlock) ModBlocks.MAPLE_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.MAPLE_WOOD.get(), blockTexture(ModBlocks.MAPLE_LOG.get()), blockTexture(ModBlocks.MAPLE_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_MAPLE_LOG.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_maple_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_maple_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_MAPLE_WOOD.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_maple_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_maple_log"));

        blockWithItem(ModBlocks.MAPLE_PLANKS);
        blockWithItem(ModBlocks.MAPLE_LEAVES);
        saplingBlock(ModBlocks.MAPLE_SAPLING);

        simpleBlockItem(ModBlocks.MAPLE_LOG.get(), models().withExistingParent("pochimod:maple_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.MAPLE_WOOD.get(), models().withExistingParent("pochimod:maple_wood", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_MAPLE_LOG.get(), models().withExistingParent("pochimod:stripped_maple_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_MAPLE_WOOD.get(), models().withExistingParent("pochimod:stripped_maple_wood", "minecraft:block/cube_column"));



        logBlock(((RotatedPillarBlock) ModBlocks.CINNAMON_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.CINNAMON_WOOD.get(), blockTexture(ModBlocks.CINNAMON_LOG.get()), blockTexture(ModBlocks.CINNAMON_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_CINNAMON_LOG.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_cinnamon_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_cinnamon_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_CINNAMON_WOOD.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_cinnamon_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_cinnamon_log"));

        blockWithItem(ModBlocks.CINNAMON_PLANKS);
        blockWithItem(ModBlocks.CINNAMON_LEAVES);
        saplingBlock(ModBlocks.CINNAMON_SAPLING);

        simpleBlockItem(ModBlocks.CINNAMON_LOG.get(), models().withExistingParent("pochimod:cinnamon_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.CINNAMON_WOOD.get(), models().withExistingParent("pochimod:cinnamon_wood", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_CINNAMON_LOG.get(), models().withExistingParent("pochimod:stripped_cinnamon_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_CINNAMON_WOOD.get(), models().withExistingParent("pochimod:stripped_cinnamon_wood", "minecraft:block/cube_column"));



        logBlock(((RotatedPillarBlock) ModBlocks.COLA_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.COLA_WOOD.get(), blockTexture(ModBlocks.COLA_LOG.get()), blockTexture(ModBlocks.COLA_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_COLA_LOG.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_cola_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_cola_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_COLA_WOOD.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_cola_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_cola_log"));

        blockWithItem(ModBlocks.COLA_PLANKS);
        blockWithItem(ModBlocks.COLA_LEAVES);
        saplingBlock(ModBlocks.COLA_SAPLING);

        simpleBlockItem(ModBlocks.COLA_LOG.get(), models().withExistingParent("pochimod:cola_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.COLA_WOOD.get(), models().withExistingParent("pochimod:cola_wood", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_COLA_LOG.get(), models().withExistingParent("pochimod:stripped_cola_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_COLA_WOOD.get(), models().withExistingParent("pochimod:stripped_cola_wood", "minecraft:block/cube_column"));



        logBlock(((RotatedPillarBlock) ModBlocks.LEMON_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.LEMON_WOOD.get(), blockTexture(ModBlocks.LEMON_LOG.get()), blockTexture(ModBlocks.LEMON_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_LEMON_LOG.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_lemon_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_lemon_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_LEMON_WOOD.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_lemon_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_lemon_log"));

        blockWithItem(ModBlocks.LEMON_PLANKS);
        blockWithItem(ModBlocks.LEMON_LEAVES);
        saplingBlock(ModBlocks.LEMON_SAPLING);

        simpleBlockItem(ModBlocks.LEMON_LOG.get(), models().withExistingParent("pochimod:lemon_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.LEMON_WOOD.get(), models().withExistingParent("pochimod:lemon_wood", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_LEMON_LOG.get(), models().withExistingParent("pochimod:stripped_lemon_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_LEMON_WOOD.get(), models().withExistingParent("pochimod:stripped_lemon_wood", "minecraft:block/cube_column"));


        logBlock(((RotatedPillarBlock) ModBlocks.PLUM_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.PLUM_WOOD.get(), blockTexture(ModBlocks.PLUM_LOG.get()), blockTexture(ModBlocks.PLUM_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_PLUM_LOG.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_plum_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_plum_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_PLUM_WOOD.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_plum_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_plum_log"));

        blockWithItem(ModBlocks.PLUM_PLANKS);
        blockWithItem(ModBlocks.PLUM_LEAVES);
        saplingBlock(ModBlocks.PLUM_SAPLING);

        simpleBlockItem(ModBlocks.PLUM_LOG.get(), models().withExistingParent("pochimod:plum_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.PLUM_WOOD.get(), models().withExistingParent("pochimod:plum_wood", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_PLUM_LOG.get(), models().withExistingParent("pochimod:stripped_plum_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_PLUM_WOOD.get(), models().withExistingParent("pochimod:stripped_plum_wood", "minecraft:block/cube_column"));


        logBlock(((RotatedPillarBlock) ModBlocks.CHERRY_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.CHERRY_WOOD.get(), blockTexture(ModBlocks.CHERRY_LOG.get()), blockTexture(ModBlocks.CHERRY_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_CHERRY_LOG.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_cherry_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_cherry_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_CHERRY_WOOD.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_cherry_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_cherry_log"));

        blockWithItem(ModBlocks.CHERRY_PLANKS);
        blockWithItem(ModBlocks.CHERRY_LEAVES);
        saplingBlock(ModBlocks.CHERRY_SAPLING);

        simpleBlockItem(ModBlocks.CHERRY_LOG.get(), models().withExistingParent("pochimod:cherry_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.CHERRY_WOOD.get(), models().withExistingParent("pochimod:cherry_wood", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_CHERRY_LOG.get(), models().withExistingParent("pochimod:stripped_cherry_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_CHERRY_WOOD.get(), models().withExistingParent("pochimod:stripped_cherry_wood", "minecraft:block/cube_column"));


        logBlock(((RotatedPillarBlock) ModBlocks.BANANA_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.BANANA_WOOD.get(), blockTexture(ModBlocks.BANANA_LOG.get()), blockTexture(ModBlocks.BANANA_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_BANANA_LOG.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_banana_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_banana_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_BANANA_WOOD.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_banana_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_banana_log"));

        blockWithItem(ModBlocks.BANANA_PLANKS);
        blockWithItem(ModBlocks.BANANA_LEAVES);
        saplingBlock(ModBlocks.BANANA_SAPLING);

        simpleBlockItem(ModBlocks.BANANA_LOG.get(), models().withExistingParent("pochimod:banana_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.BANANA_WOOD.get(), models().withExistingParent("pochimod:banana_wood", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_BANANA_LOG.get(), models().withExistingParent("pochimod:stripped_banana_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_BANANA_WOOD.get(), models().withExistingParent("pochimod:stripped_banana_wood", "minecraft:block/cube_column"));


        logBlock(((RotatedPillarBlock) ModBlocks.COCONUT_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.COCONUT_WOOD.get(), blockTexture(ModBlocks.COCONUT_LOG.get()), blockTexture(ModBlocks.COCONUT_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_COCONUT_LOG.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_coconut_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_coconut_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_COCONUT_WOOD.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_coconut_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_coconut_log"));

        blockWithItem(ModBlocks.COCONUT_PLANKS);
        blockWithItem(ModBlocks.COCONUT_LEAVES);
        saplingBlock(ModBlocks.COCONUT_SAPLING);

        simpleBlockItem(ModBlocks.COCONUT_LOG.get(), models().withExistingParent("pochimod:coconut_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.COCONUT_WOOD.get(), models().withExistingParent("pochimod:coconut_wood", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_COCONUT_LOG.get(), models().withExistingParent("pochimod:stripped_coconut_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_COCONUT_WOOD.get(), models().withExistingParent("pochimod:stripped_coconut_wood", "minecraft:block/cube_column"));


        logBlock(((RotatedPillarBlock) ModBlocks.PEACH_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.PEACH_WOOD.get(), blockTexture(ModBlocks.PEACH_LOG.get()), blockTexture(ModBlocks.PEACH_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_PEACH_LOG.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_peach_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_peach_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_PEACH_WOOD.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_peach_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_peach_log"));

        blockWithItem(ModBlocks.PEACH_PLANKS);
        blockWithItem(ModBlocks.PEACH_LEAVES);
        saplingBlock(ModBlocks.PEACH_SAPLING);

        simpleBlockItem(ModBlocks.PEACH_LOG.get(), models().withExistingParent("pochimod:peach_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.PEACH_WOOD.get(), models().withExistingParent("pochimod:peach_wood", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_PEACH_LOG.get(), models().withExistingParent("pochimod:stripped_peach_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_PEACH_WOOD.get(), models().withExistingParent("pochimod:stripped_peach_wood", "minecraft:block/cube_column"));

        logBlock(((RotatedPillarBlock) ModBlocks.ALMOND_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.ALMOND_WOOD.get(), blockTexture(ModBlocks.ALMOND_LOG.get()), blockTexture(ModBlocks.ALMOND_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_ALMOND_LOG.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_almond_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_almond_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_ALMOND_WOOD.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_almond_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_almond_log"));

        blockWithItem(ModBlocks.ALMOND_PLANKS);
        blockWithItem(ModBlocks.ALMOND_LEAVES);
        saplingBlock(ModBlocks.ALMOND_SAPLING);

        simpleBlockItem(ModBlocks.ALMOND_LOG.get(), models().withExistingParent("pochimod:almond_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.ALMOND_WOOD.get(), models().withExistingParent("pochimod:almond_wood", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_ALMOND_LOG.get(), models().withExistingParent("pochimod:stripped_almond_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_ALMOND_WOOD.get(), models().withExistingParent("pochimod:stripped_almond_wood", "minecraft:block/cube_column"));

        logBlock(((RotatedPillarBlock) ModBlocks.DURIAN_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.DURIAN_WOOD.get(), blockTexture(ModBlocks.DURIAN_LOG.get()), blockTexture(ModBlocks.DURIAN_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_DURIAN_LOG.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_durian_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_durian_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_DURIAN_WOOD.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_durian_log"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/stripped_durian_log"));

        blockWithItem(ModBlocks.DURIAN_PLANKS);
        blockWithItem(ModBlocks.DURIAN_LEAVES);
        saplingBlock(ModBlocks.DURIAN_SAPLING);

        simpleBlockItem(ModBlocks.DURIAN_LOG.get(), models().withExistingParent("pochimod:durian_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.DURIAN_WOOD.get(), models().withExistingParent("pochimod:durian_wood", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_DURIAN_LOG.get(), models().withExistingParent("pochimod:stripped_durian_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_DURIAN_WOOD.get(), models().withExistingParent("pochimod:stripped_durian_wood", "minecraft:block/cube_column"));




        //buttonBlock((ButtonBlock)ModBlocks.CABERNET_SAUVIGNON_BUTTON.get(), blockTexture(ModBlocks.CABERNET_SAUVIGNON_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock)ModBlocks.CABERNET_SAUVIGNON_PRESSURE_PLATE.get(), blockTexture(ModBlocks.CABERNET_SAUVIGNON_PLANKS.get()));
        fenceBlock((FenceBlock) ModBlocks.CABERNET_SAUVIGNON_FENCE.get(), blockTexture(ModBlocks.CABERNET_SAUVIGNON_PLANKS.get()));

        fenceGateBlock((FenceGateBlock) ModBlocks.CABERNET_SAUVIGNON_FENCE_GATE.get(), blockTexture(ModBlocks.CABERNET_SAUVIGNON_PLANKS.get()));
        slabBlock((SlabBlock) ModBlocks.CABERNET_SAUVIGNON_SLAB.get(), blockTexture(ModBlocks.CABERNET_SAUVIGNON_PLANKS.get()),
                blockTexture(ModBlocks.CABERNET_SAUVIGNON_PLANKS.get()));
        stairsBlock((StairBlock) ModBlocks.CABERNET_SAUVIGNON_STAIRS.get(), blockTexture(ModBlocks.CABERNET_SAUVIGNON_PLANKS.get()));

        doorBlock((DoorBlock) ModBlocks.CABERNET_SAUVIGNON_DOOR.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/cabernet_sauvignon_door_bottom"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/cabernet_sauvignon_door_top"));
        trapdoorBlock((TrapDoorBlock) ModBlocks.CABERNET_SAUVIGNON_TRAPDOOR.get(), blockTexture(ModBlocks.CABERNET_SAUVIGNON_TRAPDOOR.get()), true);


        //buttonBlock((ButtonBlock)ModBlocks.MAPLE_BUTTON.get(), blockTexture(ModBlocks.MAPLE_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock)ModBlocks.MAPLE_PRESSURE_PLATE.get(), blockTexture(ModBlocks.MAPLE_PLANKS.get()));
        fenceBlock((FenceBlock) ModBlocks.MAPLE_FENCE.get(), blockTexture(ModBlocks.MAPLE_PLANKS.get()));

        fenceGateBlock((FenceGateBlock) ModBlocks.MAPLE_FENCE_GATE.get(), blockTexture(ModBlocks.MAPLE_PLANKS.get()));
        slabBlock((SlabBlock) ModBlocks.MAPLE_SLAB.get(), blockTexture(ModBlocks.MAPLE_PLANKS.get()),
                blockTexture(ModBlocks.MAPLE_PLANKS.get()));
        stairsBlock((StairBlock) ModBlocks.MAPLE_STAIRS.get(), blockTexture(ModBlocks.MAPLE_PLANKS.get()));

        doorBlock((DoorBlock) ModBlocks.MAPLE_DOOR.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/maple_door_bottom"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/maple_door_top"));
        trapdoorBlock((TrapDoorBlock) ModBlocks.MAPLE_TRAPDOOR.get(), blockTexture(ModBlocks.MAPLE_TRAPDOOR.get()), true);


        //buttonBlock((ButtonBlock)ModBlocks.CINNAMON_BUTTON.get(), blockTexture(ModBlocks.CINNAMON_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock)ModBlocks.CINNAMON_PRESSURE_PLATE.get(), blockTexture(ModBlocks.CINNAMON_PLANKS.get()));
        fenceBlock((FenceBlock) ModBlocks.CINNAMON_FENCE.get(), blockTexture(ModBlocks.CINNAMON_PLANKS.get()));

        fenceGateBlock((FenceGateBlock) ModBlocks.CINNAMON_FENCE_GATE.get(), blockTexture(ModBlocks.CINNAMON_PLANKS.get()));
        slabBlock((SlabBlock) ModBlocks.CINNAMON_SLAB.get(), blockTexture(ModBlocks.CINNAMON_PLANKS.get()),
                blockTexture(ModBlocks.CINNAMON_PLANKS.get()));
        stairsBlock((StairBlock) ModBlocks.CINNAMON_STAIRS.get(), blockTexture(ModBlocks.CINNAMON_PLANKS.get()));

        doorBlock((DoorBlock) ModBlocks.CINNAMON_DOOR.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/cinnamon_door_bottom"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/cinnamon_door_top"));
        trapdoorBlock((TrapDoorBlock) ModBlocks.CINNAMON_TRAPDOOR.get(), blockTexture(ModBlocks.CINNAMON_TRAPDOOR.get()), true);


        //buttonBlock((ButtonBlock)ModBlocks.COLA_BUTTON.get(), blockTexture(ModBlocks.COLA_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock)ModBlocks.COLA_PRESSURE_PLATE.get(), blockTexture(ModBlocks.COLA_PLANKS.get()));
        fenceBlock((FenceBlock) ModBlocks.COLA_FENCE.get(), blockTexture(ModBlocks.COLA_PLANKS.get()));

        fenceGateBlock((FenceGateBlock) ModBlocks.COLA_FENCE_GATE.get(), blockTexture(ModBlocks.COLA_PLANKS.get()));
        slabBlock((SlabBlock) ModBlocks.COLA_SLAB.get(), blockTexture(ModBlocks.COLA_PLANKS.get()),
                blockTexture(ModBlocks.COLA_PLANKS.get()));
        stairsBlock((StairBlock) ModBlocks.COLA_STAIRS.get(), blockTexture(ModBlocks.COLA_PLANKS.get()));

        doorBlock((DoorBlock) ModBlocks.COLA_DOOR.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/cola_door_bottom"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/cola_door_top"));
        trapdoorBlock((TrapDoorBlock) ModBlocks.COLA_TRAPDOOR.get(), blockTexture(ModBlocks.COLA_TRAPDOOR.get()), true);


        //buttonBlock((ButtonBlock)ModBlocks.LEMON_BUTTON.get(), blockTexture(ModBlocks.LEMON_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock)ModBlocks.LEMON_PRESSURE_PLATE.get(), blockTexture(ModBlocks.LEMON_PLANKS.get()));
        fenceBlock((FenceBlock) ModBlocks.LEMON_FENCE.get(), blockTexture(ModBlocks.LEMON_PLANKS.get()));

        fenceGateBlock((FenceGateBlock) ModBlocks.LEMON_FENCE_GATE.get(), blockTexture(ModBlocks.LEMON_PLANKS.get()));
        slabBlock((SlabBlock) ModBlocks.LEMON_SLAB.get(), blockTexture(ModBlocks.LEMON_PLANKS.get()),
                blockTexture(ModBlocks.LEMON_PLANKS.get()));
        stairsBlock((StairBlock) ModBlocks.LEMON_STAIRS.get(), blockTexture(ModBlocks.LEMON_PLANKS.get()));

        doorBlock((DoorBlock) ModBlocks.LEMON_DOOR.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/lemon_door_bottom"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/lemon_door_top"));
        trapdoorBlock((TrapDoorBlock) ModBlocks.LEMON_TRAPDOOR.get(), blockTexture(ModBlocks.LEMON_TRAPDOOR.get()), true);


        //buttonBlock((ButtonBlock)ModBlocks.PLUM_BUTTON.get(), blockTexture(ModBlocks.PLUM_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock)ModBlocks.PLUM_PRESSURE_PLATE.get(), blockTexture(ModBlocks.PLUM_PLANKS.get()));
        fenceBlock((FenceBlock) ModBlocks.PLUM_FENCE.get(), blockTexture(ModBlocks.PLUM_PLANKS.get()));

        fenceGateBlock((FenceGateBlock) ModBlocks.PLUM_FENCE_GATE.get(), blockTexture(ModBlocks.PLUM_PLANKS.get()));
        slabBlock((SlabBlock) ModBlocks.PLUM_SLAB.get(), blockTexture(ModBlocks.PLUM_PLANKS.get()),
                blockTexture(ModBlocks.PLUM_PLANKS.get()));
        stairsBlock((StairBlock) ModBlocks.PLUM_STAIRS.get(), blockTexture(ModBlocks.PLUM_PLANKS.get()));

        doorBlock((DoorBlock) ModBlocks.PLUM_DOOR.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/plum_door_bottom"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/plum_door_top"));
        trapdoorBlock((TrapDoorBlock) ModBlocks.PLUM_TRAPDOOR.get(), blockTexture(ModBlocks.PLUM_TRAPDOOR.get()), true);


        //buttonBlock((ButtonBlock)ModBlocks.CHERRY_BUTTON.get(), blockTexture(ModBlocks.CHERRY_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock)ModBlocks.CHERRY_PRESSURE_PLATE.get(), blockTexture(ModBlocks.CHERRY_PLANKS.get()));
        fenceBlock((FenceBlock) ModBlocks.CHERRY_FENCE.get(), blockTexture(ModBlocks.CHERRY_PLANKS.get()));

        fenceGateBlock((FenceGateBlock) ModBlocks.CHERRY_FENCE_GATE.get(), blockTexture(ModBlocks.CHERRY_PLANKS.get()));
        slabBlock((SlabBlock) ModBlocks.CHERRY_SLAB.get(), blockTexture(ModBlocks.CHERRY_PLANKS.get()),
                blockTexture(ModBlocks.CHERRY_PLANKS.get()));
        stairsBlock((StairBlock) ModBlocks.CHERRY_STAIRS.get(), blockTexture(ModBlocks.CHERRY_PLANKS.get()));

        doorBlock((DoorBlock) ModBlocks.CHERRY_DOOR.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/cherry_door_bottom"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/cherry_door_top"));
        trapdoorBlock((TrapDoorBlock) ModBlocks.CHERRY_TRAPDOOR.get(), blockTexture(ModBlocks.CHERRY_TRAPDOOR.get()), true);


        //buttonBlock((ButtonBlock)ModBlocks.BANANA_BUTTON.get(), blockTexture(ModBlocks.BANANA_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock)ModBlocks.BANANA_PRESSURE_PLATE.get(), blockTexture(ModBlocks.BANANA_PLANKS.get()));
        fenceBlock((FenceBlock) ModBlocks.BANANA_FENCE.get(), blockTexture(ModBlocks.BANANA_PLANKS.get()));

        fenceGateBlock((FenceGateBlock) ModBlocks.BANANA_FENCE_GATE.get(), blockTexture(ModBlocks.BANANA_PLANKS.get()));
        slabBlock((SlabBlock) ModBlocks.BANANA_SLAB.get(), blockTexture(ModBlocks.BANANA_PLANKS.get()),
                blockTexture(ModBlocks.BANANA_PLANKS.get()));
        stairsBlock((StairBlock) ModBlocks.BANANA_STAIRS.get(), blockTexture(ModBlocks.BANANA_PLANKS.get()));

        doorBlock((DoorBlock) ModBlocks.BANANA_DOOR.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/banana_door_bottom"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/banana_door_top"));
        trapdoorBlock((TrapDoorBlock) ModBlocks.BANANA_TRAPDOOR.get(), blockTexture(ModBlocks.BANANA_TRAPDOOR.get()), true);


        //buttonBlock((ButtonBlock)ModBlocks.COCONUT_BUTTON.get(), blockTexture(ModBlocks.COCONUT_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock)ModBlocks.COCONUT_PRESSURE_PLATE.get(), blockTexture(ModBlocks.COCONUT_PLANKS.get()));
        fenceBlock((FenceBlock) ModBlocks.COCONUT_FENCE.get(), blockTexture(ModBlocks.COCONUT_PLANKS.get()));

        fenceGateBlock((FenceGateBlock) ModBlocks.COCONUT_FENCE_GATE.get(), blockTexture(ModBlocks.COCONUT_PLANKS.get()));
        slabBlock((SlabBlock) ModBlocks.COCONUT_SLAB.get(), blockTexture(ModBlocks.COCONUT_PLANKS.get()),
                blockTexture(ModBlocks.COCONUT_PLANKS.get()));
        stairsBlock((StairBlock) ModBlocks.COCONUT_STAIRS.get(), blockTexture(ModBlocks.COCONUT_PLANKS.get()));

        doorBlock((DoorBlock) ModBlocks.COCONUT_DOOR.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/coconut_door_bottom"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/coconut_door_top"));
        trapdoorBlock((TrapDoorBlock) ModBlocks.COCONUT_TRAPDOOR.get(), blockTexture(ModBlocks.COCONUT_TRAPDOOR.get()), true);


        //buttonBlock((ButtonBlock)ModBlocks.PEACH_BUTTON.get(), blockTexture(ModBlocks.PEACH_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock)ModBlocks.PEACH_PRESSURE_PLATE.get(), blockTexture(ModBlocks.PEACH_PLANKS.get()));
        fenceBlock((FenceBlock) ModBlocks.PEACH_FENCE.get(), blockTexture(ModBlocks.PEACH_PLANKS.get()));

        fenceGateBlock((FenceGateBlock) ModBlocks.PEACH_FENCE_GATE.get(), blockTexture(ModBlocks.PEACH_PLANKS.get()));
        slabBlock((SlabBlock) ModBlocks.PEACH_SLAB.get(), blockTexture(ModBlocks.PEACH_PLANKS.get()),
                blockTexture(ModBlocks.PEACH_PLANKS.get()));
        stairsBlock((StairBlock) ModBlocks.PEACH_STAIRS.get(), blockTexture(ModBlocks.PEACH_PLANKS.get()));

        doorBlock((DoorBlock) ModBlocks.PEACH_DOOR.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/peach_door_bottom"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/peach_door_top"));
        trapdoorBlock((TrapDoorBlock) ModBlocks.PEACH_TRAPDOOR.get(), blockTexture(ModBlocks.PEACH_TRAPDOOR.get()), true);


        //buttonBlock((ButtonBlock)ModBlocks.ALMOND_BUTTON.get(), blockTexture(ModBlocks.ALMOND_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock)ModBlocks.ALMOND_PRESSURE_PLATE.get(), blockTexture(ModBlocks.ALMOND_PLANKS.get()));
        fenceBlock((FenceBlock) ModBlocks.ALMOND_FENCE.get(), blockTexture(ModBlocks.ALMOND_PLANKS.get()));

        fenceGateBlock((FenceGateBlock) ModBlocks.ALMOND_FENCE_GATE.get(), blockTexture(ModBlocks.ALMOND_PLANKS.get()));
        slabBlock((SlabBlock) ModBlocks.ALMOND_SLAB.get(), blockTexture(ModBlocks.ALMOND_PLANKS.get()),
                blockTexture(ModBlocks.ALMOND_PLANKS.get()));
        stairsBlock((StairBlock) ModBlocks.ALMOND_STAIRS.get(), blockTexture(ModBlocks.ALMOND_PLANKS.get()));

        doorBlock((DoorBlock) ModBlocks.ALMOND_DOOR.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/almond_door_bottom"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/almond_door_top"));
        trapdoorBlock((TrapDoorBlock) ModBlocks.ALMOND_TRAPDOOR.get(), blockTexture(ModBlocks.ALMOND_TRAPDOOR.get()), true);


        //buttonBlock((ButtonBlock)ModBlocks.DURIAN_BUTTON.get(), blockTexture(ModBlocks.DURIAN_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock)ModBlocks.DURIAN_PRESSURE_PLATE.get(), blockTexture(ModBlocks.DURIAN_PLANKS.get()));
        fenceBlock((FenceBlock) ModBlocks.DURIAN_FENCE.get(), blockTexture(ModBlocks.DURIAN_PLANKS.get()));

        fenceGateBlock((FenceGateBlock) ModBlocks.DURIAN_FENCE_GATE.get(), blockTexture(ModBlocks.DURIAN_PLANKS.get()));
        slabBlock((SlabBlock) ModBlocks.DURIAN_SLAB.get(), blockTexture(ModBlocks.DURIAN_PLANKS.get()),
                blockTexture(ModBlocks.DURIAN_PLANKS.get()));
        stairsBlock((StairBlock) ModBlocks.DURIAN_STAIRS.get(), blockTexture(ModBlocks.DURIAN_PLANKS.get()));

        doorBlock((DoorBlock) ModBlocks.DURIAN_DOOR.get(), ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/durian_door_bottom"),
                ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/durian_door_top"));
        trapdoorBlock((TrapDoorBlock) ModBlocks.DURIAN_TRAPDOOR.get(), blockTexture(ModBlocks.DURIAN_TRAPDOOR.get()), true);

    }


    private void blockWithItem(DeferredHolder<Block, Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void saplingBlock(DeferredHolder<Block, Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(blockRegistryObject.getId().getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    //public void makeCrop(CropBlock block, String modelName, String textureName) {
    //    Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);
    //
    //    getVariantBuilder(block).forAllStates(function);
    //}

    //private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
    //    ConfiguredModel[] models = new ConfiguredModel[1];
    //    models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(block.getAge()),
    //            ResourceLocation.fromNamespaceAndPath(PochiMod.MOD_ID, "block/" + textureName + state.getValue(block.getMaxAge()))));
    //
    //    return models;}
}

