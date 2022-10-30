package ru.tesmio.reg;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.tesmio.Core;
import ru.tesmio.blocks.*;
import ru.tesmio.blocks.affinage_factory.AffinageFactory;
import ru.tesmio.blocks.circuits.*;
import ru.tesmio.blocks.const_panel.PanelBlockCorner;
import ru.tesmio.blocks.const_panel.PanelBlockSide;
import ru.tesmio.blocks.crusher.BlockCrusher;
import ru.tesmio.blocks.diesel_generator.DieselGenerator;

import java.util.function.Supplier;

public class RegBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Core.MODID);
    public static final DeferredRegister<Block> NOT_DEFAULT_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Core.MODID);
    public static final DeferredRegister<Block> BLOCKS_CUSTOM_MODELS = DeferredRegister.create(ForgeRegistries.BLOCKS, Core.MODID);
    public static final DeferredRegister<Item> ITEM_BLOCKS = DeferredRegister.create(ForgeRegistries.ITEMS, Core.MODID);


    public static RegistryObject<Block> CONCRETE_ORANGE,CONCRETE_ORANGE_BR,CONCRETE_BLUE,CONCRETE_RED,CONCRETE_GREEN,CONCRETE_GRAY,CONCRETE_BEIGE,CONCRETE_BEIGE2,CONCRETE_YELLOW,CONCRETE_WHITE,CONCRETE_BLUE_BR,CONCRETE_RED_BR,CONCRETE_GREEN_BR,CONCRETE_GRAY_BR,CONCRETE_BEIGE_BR,CONCRETE_BEIGE2_BR,CONCRETE_YELLOW_BR,CONCRETE_WHITE_BR;
    public static RegistryObject<Block> TILE_QUAD_WHITE,TILE_QUAD_GRAY, TILE_QUAD_BLUE, TILE_QUAD_CONCRETE, TILE_QUAD_WHITE_BR, TILE_QUAD_BLUE_BR;
    public static RegistryObject<Block>  TILE_QUAD_3, TILE_QUAD_3_BR, TILE_QUAD_4, TILE_QUAD_5,TILE_QUAD_5_BR,TILE_QUAD_5_BRf;
    public static RegistryObject<Block> REGULAR_BROWN_TILE, REGULAR_BROWN_TILE_BR, REGULAR_AM_TILE, REGULAR_AM_TILE_BR, REGULAR_LIL_TILE, REGULAR_LIL_TILE_BR;

    public static RegistryObject<Block> TILE_REST_DARK_BLUE, TILE_REST_DARK_BLUE_BR,TILE_REST_BLUE, TILE_REST_BLUE_BR, TILE_REST_BROWN, TILE_REST_BROWN_BR, TILE_REST_WHITE, TILE_REST_WHITE_BR, TILE_REST_BLACK, TILE_REST_BLACK_BR;

    public static RegistryObject<Block> HORIZ_TILE_BLUE, HORIZ_TILE_BLUE_BR, HORIZ_TILE_WHITE, HORIZ_TILE_WHITE_BR, HORIZ_TILE_DARK_BLUE, HORIZ_TILE_DARK_BLUE_BR;
    public static RegistryObject<Block> SMALL_TILE_BLUE, SMALL_TILE_BLUE_BR, SMALL_TILE_WHITE, SMALL_TILE_WHITE_BR, SMALL_TILE_RED, SMALL_TILE_RED_BR, SMALL_TILE_YELLOW, SMALL_TILE_YELLOW_BR;
    public static RegistryObject<Block> TILE_QUAD_1, TILE_QUAD_1_BR, TILE_QUAD_2, TILE_QUAD_2_BR;
    public static RegistryObject<Block> TILE_MOSAIC_1, TILE_MOSAIC_2;
    public static RegistryObject<Block> LINO_1, LINO_2, LINO_3, LINO_4, LINO_5, LINO_6, LINO_7, LINO_8;
    public static RegistryObject<Block> SMALL_BRICKS, YELLOW_BRICKS_3_BR, YELLOW_BRICKS_3, YELLOW_BRICKS_2_BR, YELLOW_BRICKS_2,YELLOW_BRICKS_1_BR, YELLOW_BRICKS_1, WHITE_BRICKS_BR, WHITE_BRICKS,RED_BRICKS_BR, RED_BRICKS, BRICKS_WITH_WHITE, BRICKS, BRICKS_BR, WALL_BRICKS,WALL_BRICKS_BR, LIGHT_BRICKS, LIGHT_BRICKS_BR, SHORT_BRICKS;
    public static RegistryObject<Block> IRON_BEAM_THIN, IRON_BEAM_CONCRETE, IRON_BEAM;
    public static RegistryObject<Block> PANEL_CONCRETE_CORNER, PANEL_CONCRETE_SIDE, PANEL_CONCRETE, PANEL_TILE_CORNER, PANEL_TILE_SIDE, PANEL_TILE, PANEL_CONCRETE_YELLOW_CORNER, PANEL_CONCRETE_YELLOW_SIDE, PANEL_CONCRETE_YELLOW;
    public static RegistryObject<Block> CONCRETE_RAILING_GRAY, CONCRETE_RAILING_WHITE, CONCRETE_RAILING_RED, CONCRETE_RAILING_BEIGE, CONCRETE_RAILING_BEIGE2, CONCRETE_RAILING_YELLOW, CONCRETE_RAILING_BLUE, CONCRETE_RAILING_GREEN, CONCRETE_RAILING_ORANGE;
    public static RegistryObject<Block> CERAMIC_GLASS_BLUE, CERAMIC_GLASS_GREEN, CERAMIC_GLASS_BROWN;
    public static RegistryObject<Block> CRUSHER, AFFINAGE_FACTORY, ENERGY_GENERATOR, DIESEL_E_GENERATOR, DIESEL_TANK;
    public static RegistryObject<Block> TRIM_TILE_1,TRIM_TILE_1_BR, TRIM_STONE_1, TRIM_STONE_2, TRIM_STONE_3, TRIM_STONE_4, PARQUET_BLOCK, TRIM_TILE_RED, TRIM_TILE_BLUE, CONCRETE_PLATE, CONTAINMENT_BLOCK, TRIM_METAL_1, TRIM_METAL_2, LEADCERAMIC_TILE;
    public static RegistryObject<Block> TUBING_HORIZONTAL, TUBING_VERTICAL;
    public static RegistryObject<Block> ACCELERATOR_CALC_BLOCK, ACCELERATOR_STAND, ACCELERATOR, ACCELERATOR_RINGS_END, ACCELERATOR_RINGS, ACCELERATOR_RINGS_CORNER_LEFT, ACCELERATOR_RINGS_CORNER_RIGHT;
    public static RegistryObject<Block> COPPER_CIRCUIT, COPPER_CIRCUIT_EMPTY, SILVER_CIRCUIT,SILVER_CIRCUIT_EMPTY, GOLD_CIRCUIT,GOLD_CIRCUIT_EMPTY, DIAMOND_CIRCUIT, DIAMOND_CIRCUIT_EMPTY,NETHERITE_CIRCUIT, NETHERITE_CIRCUIT_EMPTY,PLATINUM_CIRCUIT, PLATINUM_CIRCUIT_EMPTY , PLATE_GOLDEN_JACKS, PLATE_GOLDEN_JACKS_EMPTY, PLATE_PLATINUM_JACKS, PLATE_PLATINUM_JACKS_EMPTY;
    protected static VoxelShape SHAPE_CIRCUIT = Block.makeCuboidShape(0.0D, 0.0D, 2.0D, 16.0D, 0.25D, 13.0D);
    protected static VoxelShape BOX = Block.makeCuboidShape(0.0D, 0.0D, 0D, 16.0D, 16D, 16.0D);
    protected static VoxelShape SHAPE_CIRCUIT2 = Block.makeCuboidShape(0.0D, 0.0D, 3.0D, 16.0D, 0.25D, 13.0D);
    public static void init() {
//circuits
        PLATE_GOLDEN_JACKS = registerBlockWithModel("innerdeco/circuit/plate_golden_jack", () -> new PlateGoldenJack(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT2), Core.ItemGroups.TAB_INNER_DECO);
        PLATE_GOLDEN_JACKS_EMPTY = registerBlockWithModel("innerdeco/circuit/plate_golden_jack_empty", () -> new BlockCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT2), Core.ItemGroups.TAB_INNER_DECO);
        PLATE_PLATINUM_JACKS = registerBlockWithModel("innerdeco/circuit/plate_platinum_jack", () -> new PlatePlatinumJack(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT2), Core.ItemGroups.TAB_INNER_DECO);
        PLATE_PLATINUM_JACKS_EMPTY = registerBlockWithModel("innerdeco/circuit/plate_platinum_jack_empty", () -> new BlockCopperCircuit(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT2), Core.ItemGroups.TAB_INNER_DECO);

        COPPER_CIRCUIT = registerBlockWithModel("innerdeco/circuit/copper_plate", () -> new BlockCopperCircuit(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT), Core.ItemGroups.TAB_INNER_DECO);
        COPPER_CIRCUIT_EMPTY = registerBlockWithModel("innerdeco/circuit/copper_plate_empty", () -> new BlockCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT), Core.ItemGroups.TAB_INNER_DECO);
        SILVER_CIRCUIT = registerBlockWithModel("innerdeco/circuit/silver_plate", () -> new BlockSilverCircuit(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT), Core.ItemGroups.TAB_INNER_DECO);
        SILVER_CIRCUIT_EMPTY = registerBlockWithModel("innerdeco/circuit/silver_plate_empty", () -> new BlockCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT), Core.ItemGroups.TAB_INNER_DECO);
        GOLD_CIRCUIT = registerBlockWithModel("innerdeco/circuit/gold_plate", () -> new BlockGoldCircuit(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT), Core.ItemGroups.TAB_INNER_DECO);
        GOLD_CIRCUIT_EMPTY = registerBlockWithModel("innerdeco/circuit/gold_plate_empty", () -> new BlockCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT), Core.ItemGroups.TAB_INNER_DECO);
        DIAMOND_CIRCUIT = registerBlockWithModel("innerdeco/circuit/diamond_plate", () -> new BlockDiamondCircuit(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT), Core.ItemGroups.TAB_INNER_DECO);
        DIAMOND_CIRCUIT_EMPTY = registerBlockWithModel("innerdeco/circuit/diamond_plate_empty", () -> new BlockCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT), Core.ItemGroups.TAB_INNER_DECO);
        NETHERITE_CIRCUIT = registerBlockWithModel("innerdeco/circuit/netherite_plate", () -> new BlockNetheriteCircuit(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT), Core.ItemGroups.TAB_INNER_DECO);
        NETHERITE_CIRCUIT_EMPTY = registerBlockWithModel("innerdeco/circuit/netherite_plate_empty", () -> new BlockCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT), Core.ItemGroups.TAB_INNER_DECO);
        PLATINUM_CIRCUIT = registerBlockWithModel("innerdeco/circuit/platinum_plate", () -> new BlockPlatinumCircuit(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT), Core.ItemGroups.TAB_INNER_DECO);
        PLATINUM_CIRCUIT_EMPTY = registerBlockWithModel("innerdeco/circuit/platinum_plate_empty", () -> new BlockCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT), Core.ItemGroups.TAB_INNER_DECO);

        //inner deco
        TUBING_HORIZONTAL = registerBlockWithModel("innerdeco/tubing_horizontal", () -> new BlockRotatedAxisCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)), Core.ItemGroups.TAB_INNER_DECO);
        TUBING_VERTICAL = registerBlockWithModel("innerdeco/tubing_vertical", () -> new BlockRotatedAxisCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)), Core.ItemGroups.TAB_INNER_DECO);
        ACCELERATOR_RINGS = registerBlockWithModel("innerdeco/accelerator/accl_rings", () -> new BlockSideCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)), Core.ItemGroups.TAB_INNER_DECO);
        ACCELERATOR_RINGS_CORNER_LEFT = registerBlockWithModel("innerdeco/accelerator/accl_rings_corner_left", () -> new BlockSideCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)), Core.ItemGroups.TAB_INNER_DECO);
        ACCELERATOR_RINGS_CORNER_RIGHT = registerBlockWithModel("innerdeco/accelerator/accl_rings_corner_right", () -> new BlockSideCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)), Core.ItemGroups.TAB_INNER_DECO);
        ACCELERATOR_STAND = registerBlockWithModel("innerdeco/accelerator/accelerator_stand", () -> new BlockSideCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)), Core.ItemGroups.TAB_INNER_DECO);
        ACCELERATOR = registerBlockWithModel("innerdeco/accelerator/accelerator", () -> new BlockSideCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)), Core.ItemGroups.TAB_INNER_DECO);
        ACCELERATOR_CALC_BLOCK = registerBlockWithModel("innerdeco/accelerator/accl_calc_block", () -> new BlockSideCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)), Core.ItemGroups.TAB_INNER_DECO);
        ACCELERATOR_RINGS_END = registerBlockWithModel("innerdeco/accelerator/accl_rings_end", () -> new BlockSideCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)), Core.ItemGroups.TAB_INNER_DECO);

        //mech
        CRUSHER = registerBlockWithModel("mech/crusher", () -> new BlockCrusher(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        AFFINAGE_FACTORY = registerBlockWithModel("mech/affinage", () -> new AffinageFactory(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        ENERGY_GENERATOR = registerBlockWithModel("mech/diesel_generator", () -> new DieselGenerator(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid(), BOX), Core.ItemGroups.TAB_INNER_DECO);
        DIESEL_TANK = registerBlockWithModel("mech/diesel_tank", () -> new BlockSideCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        DIESEL_E_GENERATOR = registerBlockWithModel("mech/diesel_electro_generator", () -> new BlockSideCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);

        //trim_stone
        TRIM_TILE_1 = registerBlock("structural/trim_tile_1", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        TRIM_TILE_1_BR = registerBlock("structural/trim_tile_1_br", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        TRIM_STONE_1 = registerBlock("structural/trim_stone_1", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        TRIM_STONE_2 = registerBlock("structural/trim_stone_2", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        TRIM_STONE_3 = registerBlock("structural/trim_stone_3", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        TRIM_STONE_4 = registerBlock("structural/trim_stone_4", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        PARQUET_BLOCK = registerBlock("structural/parquet_block", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        TRIM_TILE_RED = registerBlock("structural/trim_tile_red", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        TRIM_TILE_BLUE = registerBlock("structural/trim_tile_blue", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        CONCRETE_PLATE = registerBlock("structural/concrete_plate", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        CONTAINMENT_BLOCK = registerBlock("structural/containment_block", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        TRIM_METAL_1 = registerBlock("structural/trim_metal_1", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        TRIM_METAL_2 = registerBlock("structural/trim_metal_2", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        LEADCERAMIC_TILE = registerBlock("structural/leadceramic_tile", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));

        //ceramic_glass
        CERAMIC_GLASS_BLUE = registerBlock("structural/ceramic_glass_blue", () -> new Block(AbstractBlock.Properties.create(Material.GLASS).setRequiresTool().hardnessAndResistance(1f,2f).harvestTool(ToolType.PICKAXE).sound(SoundType.GLASS).notSolid()));
        CERAMIC_GLASS_GREEN = registerBlock("structural/ceramic_glass_green", () -> new Block(AbstractBlock.Properties.create(Material.GLASS).setRequiresTool().hardnessAndResistance(1f,2f).harvestTool(ToolType.PICKAXE).sound(SoundType.GLASS).notSolid()));
        CERAMIC_GLASS_BROWN = registerBlock("structural/ceramic_glass_brown", () -> new Block(AbstractBlock.Properties.create(Material.GLASS).setRequiresTool().hardnessAndResistance(1f,2f).harvestTool(ToolType.PICKAXE).sound(SoundType.GLASS).notSolid()));

        //linos
        LINO_1 = registerBlock("lino/lino1", () -> new Block(AbstractBlock.Properties.create(Material.WOOL).setRequiresTool().hardnessAndResistance(1f,2f).harvestTool(ToolType.AXE).sound(SoundType.CLOTH)));
        LINO_2 = registerBlock("lino/lino2", () -> new Block(AbstractBlock.Properties.create(Material.WOOL).setRequiresTool().hardnessAndResistance(1f,2f).harvestTool(ToolType.AXE).sound(SoundType.CLOTH)));
        LINO_3 = registerBlock("lino/lino3", () -> new Block(AbstractBlock.Properties.create(Material.WOOL).setRequiresTool().hardnessAndResistance(1f,2f).harvestTool(ToolType.AXE).sound(SoundType.CLOTH)));
        LINO_4 = registerBlock("lino/lino4", () -> new Block(AbstractBlock.Properties.create(Material.WOOL).setRequiresTool().hardnessAndResistance(1f,2f).harvestTool(ToolType.AXE).sound(SoundType.CLOTH)));
        LINO_5 = registerBlock("lino/lino5", () -> new Block(AbstractBlock.Properties.create(Material.WOOL).setRequiresTool().hardnessAndResistance(1f,2f).harvestTool(ToolType.AXE).sound(SoundType.CLOTH)));
        LINO_6 = registerBlock("lino/lino6", () -> new Block(AbstractBlock.Properties.create(Material.WOOL).setRequiresTool().hardnessAndResistance(1f,2f).harvestTool(ToolType.AXE).sound(SoundType.CLOTH)));
        LINO_7 = registerBlock("lino/lino7", () -> new Block(AbstractBlock.Properties.create(Material.WOOL).setRequiresTool().hardnessAndResistance(1f,2f).harvestTool(ToolType.AXE).sound(SoundType.CLOTH)));
        LINO_8 = registerBlock("lino/lino8", () -> new Block(AbstractBlock.Properties.create(Material.WOOL).setRequiresTool().hardnessAndResistance(1f,2f).harvestTool(ToolType.AXE).sound(SoundType.CLOTH)));
        //concrete_railing
        CONCRETE_RAILING_ORANGE = registerBlockWithModel("structural/concrete_railing_orange", () -> new BlockRotatedAxisCustomModelInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid(), "concrete_railing.orange"), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_WHITE = registerBlockWithModel("structural/concrete_railing_white", () -> new BlockRotatedAxisCustomModelInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid(), "concrete_railing.white"), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_GRAY = registerBlockWithModel("structural/concrete_railing_gray", () -> new BlockRotatedAxisCustomModelInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid(), "concrete_railing.gray"), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_GREEN = registerBlockWithModel("structural/concrete_railing_green", () -> new BlockRotatedAxisCustomModelInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid(), "concrete_railing.greem"), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_RED = registerBlockWithModel("structural/concrete_railing_red", () -> new BlockRotatedAxisCustomModelInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid(), "concrete_railing.red"), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_BLUE = registerBlockWithModel("structural/concrete_railing_blue", () -> new BlockRotatedAxisCustomModelInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid(), "concrete_railing.blue"), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_BEIGE = registerBlockWithModel("structural/concrete_railing_beige", () -> new BlockRotatedAxisCustomModelInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid(), "concrete_railing.beige"), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_BEIGE2 = registerBlockWithModel("structural/concrete_railing_beige2", () -> new BlockRotatedAxisCustomModelInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid(), "concrete_railing.beige2"), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_YELLOW = registerBlockWithModel("structural/concrete_railing_yellow", () -> new BlockRotatedAxisCustomModelInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid(), "concrete_railing.yellow"), Core.ItemGroups.TAB_MAIN);

        //beams
        IRON_BEAM_CONCRETE = registerBlockWithModel("structural/iron_beam_concrete", () -> new BlockRotatedAxisCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid()), Core.ItemGroups.TAB_MAIN);
        IRON_BEAM = registerBlockWithModel("structural/iron_beam", () -> new BlockRotatedAxisCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid()), Core.ItemGroups.TAB_MAIN);
        IRON_BEAM_THIN = registerBlockWithModel("structural/iron_beam_thin", () -> new BlockRotatedAxisCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid()), Core.ItemGroups.TAB_MAIN);
        //concrete
        CONCRETE_ORANGE = registerBlock("concrete/concrete_orange", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_orange"));
        CONCRETE_BLUE = registerBlock("concrete/concrete_blue", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_blue"));
        CONCRETE_RED = registerBlock("concrete/concrete_red", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_red"));
        CONCRETE_YELLOW = registerBlock("concrete/concrete_yellow", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_yellow"));
        CONCRETE_WHITE = registerBlock("concrete/concrete_white", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_white"));
        CONCRETE_BEIGE = registerBlock("concrete/concrete_beige", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_beige"));
        CONCRETE_GREEN = registerBlock("concrete/concrete_green", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_green"));
        CONCRETE_GRAY = registerBlock("concrete/concrete_gray", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_gray"));
        CONCRETE_BEIGE2 = registerBlock("concrete/concrete_beige2", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_beige2"));
        CONCRETE_BLUE_BR = registerBlock("concrete/concrete_blue_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_blue_br"));
        CONCRETE_ORANGE_BR = registerBlock("concrete/concrete_orange_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_orange_br"));
        CONCRETE_RED_BR = registerBlock("concrete/concrete_red_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_red_br"));
        CONCRETE_YELLOW_BR = registerBlock("concrete/concrete_yellow_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_yellow_br"));
        CONCRETE_WHITE_BR = registerBlock("concrete/concrete_white_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_white_br"));
        CONCRETE_BEIGE_BR = registerBlock("concrete/concrete_beige_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_beige_br"));
        CONCRETE_GREEN_BR = registerBlock("concrete/concrete_green_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_green_br"));
        CONCRETE_GRAY_BR = registerBlock("concrete/concrete_gray_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_gray_br"));
        CONCRETE_BEIGE2_BR = registerBlock("concrete/concrete_beige2_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_beige2_br"));

        //panels
        PANEL_CONCRETE_CORNER = registerNDBlock("structural/panel_concrete_corner", () -> new PanelBlockCorner(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f)), Core.ItemGroups.TAB_MAIN);
        PANEL_CONCRETE_SIDE = registerNDBlock("structural/panel_concrete_side", () -> new PanelBlockSide(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f)), Core.ItemGroups.TAB_MAIN);
        PANEL_CONCRETE = registerBlock("structural/panel_concrete", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f)));
        PANEL_CONCRETE_YELLOW_CORNER = registerNDBlock("structural/panel_concrete_yellow_corner", () -> new PanelBlockCorner(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f)), Core.ItemGroups.TAB_MAIN);
        PANEL_CONCRETE_YELLOW_SIDE = registerNDBlock("structural/panel_concrete_yellow_side", () -> new PanelBlockSide(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f)), Core.ItemGroups.TAB_MAIN);
        PANEL_CONCRETE_YELLOW = registerBlock("structural/panel_concrete_yellow", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f)));
        PANEL_TILE_CORNER = registerNDBlock("structural/panel_tile_corner", () -> new PanelBlockCorner(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f)), Core.ItemGroups.TAB_MAIN);
        PANEL_TILE_SIDE = registerNDBlock("structural/panel_tile_side", () -> new PanelBlockSide(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f)), Core.ItemGroups.TAB_MAIN);
        PANEL_TILE = registerBlock("structural/panel_tile", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f)));

        //horizontal_tiles
        HORIZ_TILE_BLUE = registerBlock("horiztile/horiz_tile_blue", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.horiz_tile_blue"));
        HORIZ_TILE_BLUE_BR = registerBlock("horiztile/horiz_tile_blue_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.horiz_tile_blue_br"));
        HORIZ_TILE_WHITE = registerBlock("horiztile/horiz_tile_white", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.horiz_tile_white"));
        HORIZ_TILE_WHITE_BR = registerBlock("horiztile/horiz_tile_white_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.horiz_tile_white_br"));
        HORIZ_TILE_DARK_BLUE = registerBlock("horiztile/horiz_tile_dark_blue", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.horiz_tile_dark_blue"));
        HORIZ_TILE_DARK_BLUE_BR = registerBlock("horiztile/horiz_tile_dark_blue_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.horiz_tile_dark_blue_br"));

        //small_tiles
        SMALL_TILE_BLUE = registerBlock("smalltile/small_tile_blue", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.small_tile_blue"));
        SMALL_TILE_BLUE_BR = registerBlock("smalltile/small_tile_blue_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.small_tile_blue_br"));
        SMALL_TILE_WHITE = registerBlock("smalltile/small_tile_white", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.small_tile_white"));
        SMALL_TILE_WHITE_BR = registerBlock("smalltile/small_tile_white_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.small_tile_white_br"));
        SMALL_TILE_RED = registerBlock("smalltile/small_tile_red", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.small_tile_red"));
        SMALL_TILE_RED_BR = registerBlock("smalltile/small_tile_red_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.small_tile_red_br"));
        SMALL_TILE_YELLOW = registerBlock("smalltile/small_tile_yellow", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.small_tile_yellow"));
        SMALL_TILE_YELLOW_BR = registerBlock("smalltile/small_tile_yellow_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.small_tile_yellow_br"));

        //mosaic_tile
        TILE_MOSAIC_1 = registerBlock("mosaictile/tile_mosaic_1", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        TILE_MOSAIC_2 = registerBlock("mosaictile/tile_mosaic_2", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));

        //quad_tiles
        TILE_QUAD_GRAY = registerBlock("quadtile/tile_quad_gray", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_gray"));
        TILE_QUAD_WHITE = registerBlock("quadtile/tile_quad_white", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_white"));
        TILE_QUAD_BLUE = registerBlock("quadtile/tile_quad_blue", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_blue"));
        TILE_QUAD_1_BR = registerBlock("quadtile/tile_quad_1_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_1_br"));
        TILE_QUAD_2_BR = registerBlock("quadtile/tile_quad_2_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_2_br"));
        TILE_QUAD_1 = registerBlock("quadtile/tile_quad_1", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_1"));
        TILE_QUAD_2 = registerBlock("quadtile/tile_quad_2", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_2"));
        TILE_QUAD_4 = registerBlock("quadtile/tile_quad_4", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_4"));
        TILE_QUAD_5 = registerBlock("quadtile/tile_quad_5", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_5"));
        TILE_QUAD_3 = registerBlock("quadtile/tile_quad_3", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_3"));
        TILE_QUAD_3_BR = registerBlock("quadtile/tile_quad_3_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_3_br"));
        TILE_QUAD_5_BR = registerBlock("quadtile/tile_quad_5_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_5_br"));
        TILE_QUAD_5_BRf = registerBlock("quadtile/tile_quad_5_brf", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_5_brf"));
        TILE_QUAD_CONCRETE = registerBlock("quadtile/tile_quad_concrete", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_concrete"));
        TILE_QUAD_BLUE_BR = registerBlock("quadtile/tile_quad_blue_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_blue_br"));
        TILE_QUAD_WHITE_BR = registerBlock("quadtile/tile_quad_white_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_white_br"));

        //rest_tiles
        TILE_REST_DARK_BLUE = registerBlock("resttile/tile_rest_dark_blue", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_dark_blue"));
        TILE_REST_DARK_BLUE_BR = registerBlock("resttile/tile_rest_dark_blue_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_dark_blue_br"));
        TILE_REST_BLUE = registerBlock("resttile/tile_rest_blue", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_blue"));
        TILE_REST_BLUE_BR = registerBlock("resttile/tile_rest_blue_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_blue_br"));
        TILE_REST_BLACK = registerBlock("resttile/tile_rest_black", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_black"));
        TILE_REST_BLACK_BR = registerBlock("resttile/tile_rest_black_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_black_br"));
        TILE_REST_BROWN = registerBlock("resttile/tile_rest_brown", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_brown"));
        TILE_REST_BROWN_BR = registerBlock("resttile/tile_rest_brown_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_brown_br"));
        TILE_REST_WHITE = registerBlock("resttile/tile_rest_white", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_white"));
        TILE_REST_WHITE_BR = registerBlock("resttile/tile_rest_white_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_white_br"));

        //reg tiles
        REGULAR_BROWN_TILE = registerBlock("regtile/tile_reg_brown", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_reg_brown"));
        REGULAR_BROWN_TILE_BR = registerBlock("regtile/tile_reg_brown_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_reg_brown_br"));
        REGULAR_LIL_TILE = registerBlock("regtile/tile_reg_lil", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_reg_lil"));
        REGULAR_LIL_TILE_BR = registerBlock("regtile/tile_reg_lil_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_reg_lil_br"));
        REGULAR_AM_TILE = registerBlock("regtile/tile_reg_am", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_reg_am"));
        REGULAR_AM_TILE_BR = registerBlock("regtile/tile_reg_am_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_reg_am_br"));

        //bricks
        RED_BRICKS_BR = registerBlock("brick/red_bricks_br", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        RED_BRICKS = registerBlock("brick/red_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        SHORT_BRICKS = registerBlock("brick/short_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        BRICKS_BR = registerBlock("brick/soviet_bricks_br", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        BRICKS = registerBlock("brick/soviet_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        BRICKS_WITH_WHITE = registerBlock("brick/soviet_bricks_with_white", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        WALL_BRICKS = registerBlock("brick/light_bricks2", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        LIGHT_BRICKS = registerBlock("brick/light_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        WALL_BRICKS_BR = registerBlock("brick/light_bricks2_br", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        LIGHT_BRICKS_BR = registerBlock("brick/light_bricks_br", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        YELLOW_BRICKS_1_BR = registerBlock("brick/yellow_bricks_1_br", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        YELLOW_BRICKS_1 = registerBlock("brick/yellow_bricks_1", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        YELLOW_BRICKS_2_BR = registerBlock("brick/yellow_bricks_2_br", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        YELLOW_BRICKS_2 = registerBlock("brick/yellow_bricks_2", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        YELLOW_BRICKS_3_BR = registerBlock("brick/yellow_bricks_3_br", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        YELLOW_BRICKS_3 = registerBlock("brick/yellow_bricks_3", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        WHITE_BRICKS = registerBlock("brick/white_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        WHITE_BRICKS_BR = registerBlock("brick/white_bricks_br", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));
        SMALL_BRICKS = registerBlock("brick/small_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)));

    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);

        BLOCKS_CUSTOM_MODELS.register(eventBus);
        NOT_DEFAULT_BLOCKS.register(eventBus);
        ITEM_BLOCKS.register(eventBus);
        init();
    }
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, Core.ItemGroups.TAB_MAIN);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<T> registerNDBlock(String name, Supplier<T> block, ItemGroup group) {
        RegistryObject<T> toReturn = NOT_DEFAULT_BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, group);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<T> registerBlockWithModel(String name, Supplier<T> block, ItemGroup group) {
        RegistryObject<T> toReturn = BLOCKS_CUSTOM_MODELS.register(name, block);
        registerBlockItem(name, toReturn, group);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, ItemGroup group) {
        ITEM_BLOCKS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(group)));
    }
}
