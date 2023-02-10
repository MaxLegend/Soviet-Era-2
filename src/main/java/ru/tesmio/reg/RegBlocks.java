package ru.tesmio.reg;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.tesmio.Core;
import ru.tesmio.blocks.affinage_factory.AffinageFactory;
import ru.tesmio.blocks.baseblock.*;
import ru.tesmio.blocks.circuits.*;
import ru.tesmio.blocks.const_panel.PanelBlockCorner;
import ru.tesmio.blocks.const_panel.PanelBlockSide;
import ru.tesmio.blocks.crusher.BlockCrusher;
import ru.tesmio.blocks.decorative.devices.BlockRedstoneWire;
import ru.tesmio.blocks.decorative.devices.ControlTable;
import ru.tesmio.blocks.decorative.devices.EntitySensor;
import ru.tesmio.blocks.decorative.devices.Turnstile;
import ru.tesmio.blocks.decorative.lamp.*;
import ru.tesmio.blocks.decorative.props.*;
import ru.tesmio.blocks.decorative.slabs.BaseSlab;
import ru.tesmio.blocks.decorative.stairs.BaseStairs;
import ru.tesmio.blocks.decorative.windows.AlumFrame;
import ru.tesmio.blocks.decorative.windows.AlumWindow;
import ru.tesmio.blocks.decorative.windows.ModernWindow;
import ru.tesmio.blocks.diesel_generator.DieselElectroGenerator;
import ru.tesmio.blocks.diesel_generator.DieselGenerator;
import ru.tesmio.blocks.diesel_generator.DieselTank;
import ru.tesmio.blocks.doors.AirlockDoorBlock;
import ru.tesmio.blocks.doors.AluminiumDoorBlock;
import ru.tesmio.blocks.doors.RailingDoorBlock;
import ru.tesmio.blocks.fences.ConcreteFence;
import ru.tesmio.blocks.fences.ConcreteFenceHigh;
import ru.tesmio.blocks.fences.ElectroFence;
import ru.tesmio.blocks.fences.ElectroFenceDouble;
import ru.tesmio.blocks.tumbler.AirlockDoorController;
import ru.tesmio.blocks.tumbler.ElectroFenceTumbler;

import java.util.function.Supplier;

public class RegBlocks {
    public static final DeferredRegister<Block> ONLY_CUSTOM_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Core.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Core.MODID);
    public static final DeferredRegister<Block> BLOCKS_SYMBOL = DeferredRegister.create(ForgeRegistries.BLOCKS, Core.MODID);
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
    public static RegistryObject<Block> CONTROL_PANEL_UP, CONTROL_PANEL_DOWN;
    public static RegistryObject<Block> FLUORESCENT_LAMP, BROKEN_FLUORESCENT_LAMP,FLUORESCENT_LAMP2, BROKEN_FLUORESCENT_LAMP2,FLUORESCENT_LAMP3, BROKEN_FLUORESCENT_LAMP3;
    public static RegistryObject<Block> AIRLOCK_DOOR,ALUMINIUM_DOOR,RAILING_DOOR,RUSTY_BARS, AIRLOCK_DOOR_CONTROLLER;
    public static RegistryObject<Block> ACCELERATOR_CALC_BLOCK, ACCELERATOR_STAND, ACCELERATOR, ACCELERATOR_RINGS_END, ACCELERATOR_RINGS,
            ACCELERATOR_RINGS_CORNER_LEFT, ACCELERATOR_RINGS_CORNER_RIGHT;
    public static RegistryObject<Block> RUSTY_HANDHOLD, STREET_FENCE, BALCONY_HANDHOLD;
    public static RegistryObject<Block> COPPER_CIRCUIT, COPPER_CIRCUIT_EMPTY, SILVER_CIRCUIT,SILVER_CIRCUIT_EMPTY, GOLD_CIRCUIT,GOLD_CIRCUIT_EMPTY, DIAMOND_CIRCUIT, DIAMOND_CIRCUIT_EMPTY,NETHERITE_CIRCUIT, NETHERITE_CIRCUIT_EMPTY,PLATINUM_CIRCUIT, PLATINUM_CIRCUIT_EMPTY , PLATE_GOLDEN_JACKS, PLATE_GOLDEN_JACKS_EMPTY, PLATE_PLATINUM_JACKS, PLATE_PLATINUM_JACKS_EMPTY;

    public static RegistryObject<Block> CONCRETE_STAIRS_GRAY, CONCRETE_STAIRS_GREEN, CONCRETE_STAIRS_BLUE, CONCRETE_STAIRS_BEIGE,
            CONCRETE_STAIRS_BEIGE2, CONCRETE_STAIRS_RED, CONCRETE_STAIRS_YELLOW, CONCRETE_STAIRS_WHITE, CONCRETE_STAIRS_ORANGE;
    public static RegistryObject<Block> CONCRETE_SLAB_GRAY, CONCRETE_SLAB_GREEN, CONCRETE_SLAB_BLUE, CONCRETE_SLAB_BEIGE,
            CONCRETE_SLAB_BEIGE2, CONCRETE_SLAB_RED, CONCRETE_SLAB_YELLOW, CONCRETE_SLAB_WHITE, CONCRETE_SLAB_ORANGE;

    public static RegistryObject<Block> CONCRETE_FENCE, CONCRETE_FENCE_HIGH, CONCRETE_FENCE_BASE, ELECTRO_FENCE, ELECTRO_FENCE_TUMBLER, ELECTRO_FENCE_DOUBLE;
    public static RegistryObject<Block> MOTION_SENSOR,TURNSTILE, REDSTONE_WIRE;
    public static RegistryObject<Block> BIOLAB_TABLE, BIOLAB_TABLE2, BIOLAB_TABLE3, BIOLAB_TABLE4,BIOLAB_TABLE_CASE, CHEMLAB_TABLE, CHEMLAB_TABLE_CASE;
    public static RegistryObject<Block> ALUM_FRAMES, ALUM_FRAMES_EMPTY, ALUM_WINDOW, ALUM_WINDOW_EMPTY, MODERN_WINDOW, MODERN_WINDOW_EMPTY, MODERN_WINDOW_LEAF, MODERN_WINDOW_LEAF_EMPTY
            , WOOD_WINDOW, WOOD_WINDOW_EMPTY, WOOD_WINDOW_LEAF, WOOD_WINDOW_LEAF_EMPTY;
    public static RegistryObject<Block> IRON_BED, ex_po;


   // public static Map<String, RegistryObject<Block>> SYMBOLS = new HashMap<>();


    protected static VoxelShape SHAPE_CIRCUIT = Block.makeCuboidShape(0.0D, 0.0D, 2.0D, 16.0D, 0.25D, 13.0D);
    protected static VoxelShape BOX = Block.makeCuboidShape(0.0D, 0.0D, 0D, 16.0D, 16D, 16.0D);
    protected static VoxelShape BOX_CONCRETE_FENCE_BASE[] = new VoxelShape[] {VoxelShapes.or(Block.makeCuboidShape(0.0D, 0.0D, 0D, 16.0D, 6D, 16.0D),
            Block.makeCuboidShape(1.0D, 6.0D, 1D, 15.0D, 12D, 15.0D),
            Block.makeCuboidShape(2.0D, 12.0D, 2D, 14.0D, 16D, 14.0D))};
    protected static VoxelShape SHAPE_CIRCUIT2 = Block.makeCuboidShape(0.0D, 0.0D, 3.0D, 16.0D, 0.25D, 13.0D);

   public static void init() {
       Symbols.addSymbols();
       Symbols.addSymbolsName();
        ex_po = registerBlockWithModel("outerdeco/symbols/ex_po", () -> new SymbolBlock(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f, 4f).notSolid(), 1F), Core.ItemGroups.TAB_SYMBOLS);

       for (int iter = 0; iter < Symbols.SYMBOLS_NAME.size(); iter++) {
           registerBlockWithModel("outerdeco/symbols/" + Symbols.SYMBOLS_NAME.get(iter), () -> new SymbolBlock(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f, 4f).notSolid(), 1F), Core.ItemGroups.TAB_SYMBOLS);
       }
        IRON_BED = registerOnlyCustomBlock("innerdeco/iron_bed", () -> new IronBed(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);

        WOOD_WINDOW = registerBlockWithModel("innerdeco/windows/wood_window", () -> new ModernWindow(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        WOOD_WINDOW_EMPTY = registerBlockWithModel("innerdeco/windows/wood_window_empty", () -> new ModernWindow(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        WOOD_WINDOW_LEAF = registerBlockWithModel("innerdeco/windows/wood_window_leaf", () -> new ModernWindow(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        WOOD_WINDOW_LEAF_EMPTY = registerBlockWithModel("innerdeco/windows/wood_window_leaf_empty", () -> new ModernWindow(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);

        MODERN_WINDOW = registerBlockWithModel("innerdeco/windows/modern_window", () -> new ModernWindow(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        MODERN_WINDOW_EMPTY = registerBlockWithModel("innerdeco/windows/modern_window_empty", () -> new ModernWindow(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        MODERN_WINDOW_LEAF = registerBlockWithModel("innerdeco/windows/modern_window_leaf", () -> new ModernWindow(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        MODERN_WINDOW_LEAF_EMPTY = registerBlockWithModel("innerdeco/windows/modern_window_leaf_empty", () -> new ModernWindow(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);

        ALUM_WINDOW = registerBlockWithModel("innerdeco/windows/alum_window", () -> new AlumWindow(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        ALUM_WINDOW_EMPTY = registerBlockWithModel("innerdeco/windows/alum_window_empty", () -> new AlumWindow(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);

        ALUM_FRAMES_EMPTY = registerBlockWithModel("innerdeco/windows/alum_frame_empty", () -> new AlumFrame(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        ALUM_FRAMES = registerBlockWithModel("innerdeco/windows/alum_frame", () -> new AlumFrame(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        //redstone devices
        REDSTONE_WIRE = registerBlockWithModel("innerdeco/devices/redstone_wire", () -> new BlockRedstoneWire(AbstractBlock.Properties.create(Material.WOOL).setRequiresTool().hardnessAndResistance(1f,4f).notSolid(), 1F), Core.ItemGroups.TAB_INNER_DECO);
        CHEMLAB_TABLE = registerBlockWithModel("innerdeco/chemlab_table", () -> new LinearTable(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f, 4f).notSolid()) {}, Core.ItemGroups.TAB_INNER_DECO);
        CHEMLAB_TABLE_CASE = registerBlockWithModel("innerdeco/chemlab_table_up", () -> new LinearTable(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f, 4f).notSolid()) {}, Core.ItemGroups.TAB_INNER_DECO);
        BIOLAB_TABLE = registerBlockWithModel("innerdeco/biolab_table", () -> new LinearTable(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f, 4f).notSolid()) {}, Core.ItemGroups.TAB_INNER_DECO);
        BIOLAB_TABLE_CASE = registerBlockWithModel("innerdeco/biolab_table_up", () -> new LinearTable(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f, 4f).notSolid()) {}, Core.ItemGroups.TAB_INNER_DECO);
        BIOLAB_TABLE2 = registerBlockWithModel("innerdeco/biolab_table2", () -> new LinearTable(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f, 4f).notSolid()) {}, Core.ItemGroups.TAB_INNER_DECO);
        BIOLAB_TABLE3 = registerBlockWithModel("innerdeco/biolab_table3", () -> new LinearTable(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f, 4f).notSolid()) {}, Core.ItemGroups.TAB_INNER_DECO);
        BIOLAB_TABLE4 = registerBlockWithModel("innerdeco/biolab_table4", () -> new LinearTable(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f, 4f).notSolid()) {}, Core.ItemGroups.TAB_INNER_DECO);
        //other devices
        MOTION_SENSOR = registerBlockWithModel("innerdeco/devices/motion_sensor", () -> new EntitySensor(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f, 4f).notSolid()) {}, Core.ItemGroups.TAB_OUTER_DECO);
        TURNSTILE = registerBlockWithModel("innerdeco/devices/turnstile_off", () -> new Turnstile(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f, 4f).notSolid()) {}, Core.ItemGroups.TAB_INNER_DECO);
        //fence
        CONCRETE_FENCE = registerOnlyCustomBlock("outerdeco/fences/concrete_fence", () -> new ConcreteFence(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_OUTER_DECO);
        CONCRETE_FENCE_HIGH = registerOnlyCustomBlock("outerdeco/fences/concrete_fence_high", () -> new ConcreteFenceHigh(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_OUTER_DECO);
        CONCRETE_FENCE_BASE = registerBlockWithModel("outerdeco/fences/concrete_fence_base", () -> new BlockSideWithModelCustomAABB(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid(), BOX_CONCRETE_FENCE_BASE, true, 0.8F), Core.ItemGroups.TAB_OUTER_DECO);
        ELECTRO_FENCE = registerBlockWithModel("outerdeco/fences/electro_fence", () -> new ElectroFence(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_OUTER_DECO);
        ELECTRO_FENCE_TUMBLER = registerBlockWithModel("outerdeco/fences/electro_fence_tumbler", () -> new ElectroFenceTumbler(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_OUTER_DECO);
        ELECTRO_FENCE_DOUBLE = registerBlockWithModel("outerdeco/fences/electro_fence_double", () -> new ElectroFenceDouble(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_OUTER_DECO);



        //lamp
        FLUORESCENT_LAMP = registerBlockWithModel("innerdeco/lamp/fluo_lamp", () -> new FluoLamp(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        BROKEN_FLUORESCENT_LAMP = registerBlockWithModel("innerdeco/lamp/broken_fluo_lamp", () -> new BrokenFluoLamp(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        FLUORESCENT_LAMP2 = registerBlockWithModel("innerdeco/lamp/fluo_lamp2", () -> new FluoLamp2(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        BROKEN_FLUORESCENT_LAMP2 = registerBlockWithModel("innerdeco/lamp/broken_fluo_lamp2", () -> new BrokenFluoLamp2(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        FLUORESCENT_LAMP3 = registerBlockWithModel("innerdeco/lamp/fluo_lamp3", () -> new FluoLamp3(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        BROKEN_FLUORESCENT_LAMP3 = registerBlockWithModel("innerdeco/lamp/broken_fluo_lamp3", () -> new BrokenFluoLamp3(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);

        //doors
        AIRLOCK_DOOR_CONTROLLER = registerBlockWithModel("innerdeco/airlock_door_controller", () -> new AirlockDoorController(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)), Core.ItemGroups.TAB_INNER_DECO);
        AIRLOCK_DOOR = registerOnlyCustomBlock("innerdeco/airlock_door", () -> new AirlockDoorBlock(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)), Core.ItemGroups.TAB_INNER_DECO);
        ALUMINIUM_DOOR = registerOnlyCustomBlock("innerdeco/aluminium_door", () -> new AluminiumDoorBlock(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)), Core.ItemGroups.TAB_INNER_DECO);
        RAILING_DOOR = registerOnlyCustomBlock("innerdeco/railing_door", () -> new RailingDoorBlock(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)), Core.ItemGroups.TAB_INNER_DECO);
        RUSTY_BARS = registerBlockWithModel("innerdeco/rusty_bars", () -> new RustyBars(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)), Core.ItemGroups.TAB_INNER_DECO);

        //circuits
        PLATE_GOLDEN_JACKS = registerOnlyCustomBlock("innerdeco/circuit/plate_golden_jack", () -> new PlateGoldenJack(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT2), Core.ItemGroups.TAB_INNER_DECO);
        PLATE_GOLDEN_JACKS_EMPTY = registerOnlyCustomBlock("innerdeco/circuit/plate_golden_jack_empty", () -> new BlockCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT2, 1F), Core.ItemGroups.TAB_INNER_DECO);
        PLATE_PLATINUM_JACKS = registerOnlyCustomBlock("innerdeco/circuit/plate_platinum_jack", () -> new PlatePlatinumJack(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT2), Core.ItemGroups.TAB_INNER_DECO);
        PLATE_PLATINUM_JACKS_EMPTY = registerOnlyCustomBlock("innerdeco/circuit/plate_platinum_jack_empty", () -> new BlockCopperCircuit(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT2), Core.ItemGroups.TAB_INNER_DECO);

        COPPER_CIRCUIT = registerOnlyCustomBlock("innerdeco/circuit/copper_plate", () -> new BlockCopperCircuit(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT), Core.ItemGroups.TAB_INNER_DECO);
        COPPER_CIRCUIT_EMPTY = registerOnlyCustomBlock("innerdeco/circuit/copper_plate_empty", () -> new BlockCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT, 1F), Core.ItemGroups.TAB_INNER_DECO);
        SILVER_CIRCUIT = registerOnlyCustomBlock("innerdeco/circuit/silver_plate", () -> new BlockSilverCircuit(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT), Core.ItemGroups.TAB_INNER_DECO);
        SILVER_CIRCUIT_EMPTY = registerOnlyCustomBlock("innerdeco/circuit/silver_plate_empty", () -> new BlockCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT, 1F), Core.ItemGroups.TAB_INNER_DECO);
        GOLD_CIRCUIT = registerOnlyCustomBlock("innerdeco/circuit/gold_plate", () -> new BlockGoldCircuit(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT), Core.ItemGroups.TAB_INNER_DECO);
        GOLD_CIRCUIT_EMPTY = registerOnlyCustomBlock("innerdeco/circuit/gold_plate_empty", () -> new BlockCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT, 1F), Core.ItemGroups.TAB_INNER_DECO);
        DIAMOND_CIRCUIT = registerOnlyCustomBlock("innerdeco/circuit/diamond_plate", () -> new BlockDiamondCircuit(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT), Core.ItemGroups.TAB_INNER_DECO);
        DIAMOND_CIRCUIT_EMPTY = registerOnlyCustomBlock("innerdeco/circuit/diamond_plate_empty", () -> new BlockCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT, 1F), Core.ItemGroups.TAB_INNER_DECO);
        NETHERITE_CIRCUIT = registerOnlyCustomBlock("innerdeco/circuit/netherite_plate", () -> new BlockNetheriteCircuit(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT), Core.ItemGroups.TAB_INNER_DECO);
        NETHERITE_CIRCUIT_EMPTY = registerOnlyCustomBlock("innerdeco/circuit/netherite_plate_empty", () -> new BlockCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT, 1F), Core.ItemGroups.TAB_INNER_DECO);
        PLATINUM_CIRCUIT = registerOnlyCustomBlock("innerdeco/circuit/platinum_plate", () -> new BlockPlatinumCircuit(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT), Core.ItemGroups.TAB_INNER_DECO);
        PLATINUM_CIRCUIT_EMPTY = registerOnlyCustomBlock("innerdeco/circuit/platinum_plate_empty", () -> new BlockCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(0.1f,0.1f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), SHAPE_CIRCUIT, 1F), Core.ItemGroups.TAB_INNER_DECO);

        //inner deco
        TUBING_HORIZONTAL = registerBlockWithModel("innerdeco/tubing_horizontal", () -> new BlockRotatedAxisCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), 0.6F), Core.ItemGroups.TAB_INNER_DECO);
        TUBING_VERTICAL = registerBlockWithModel("innerdeco/tubing_vertical", () -> new BlockRotatedAxisCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), 0.6F), Core.ItemGroups.TAB_INNER_DECO);
        ACCELERATOR_RINGS = registerBlockWithModel("innerdeco/accelerator/accl_rings", () -> new BlockSideCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), 1F), Core.ItemGroups.TAB_INNER_DECO);
        ACCELERATOR_RINGS_CORNER_LEFT = registerBlockWithModel("innerdeco/accelerator/accl_rings_corner_left", () -> new BlockSideCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), 1F), Core.ItemGroups.TAB_INNER_DECO);
        ACCELERATOR_RINGS_CORNER_RIGHT = registerBlockWithModel("innerdeco/accelerator/accl_rings_corner_right", () -> new BlockSideCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), 1F), Core.ItemGroups.TAB_INNER_DECO);
        ACCELERATOR_STAND = registerBlockWithModel("innerdeco/accelerator/accelerator_stand", () -> new BlockSideCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), 1F), Core.ItemGroups.TAB_INNER_DECO);
        ACCELERATOR = registerBlockWithModel("innerdeco/accelerator/accelerator", () -> new BlockSideCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), 0.6F), Core.ItemGroups.TAB_INNER_DECO);
        ACCELERATOR_CALC_BLOCK = registerBlockWithModel("innerdeco/accelerator/accl_calc_block", () -> new BlockSideCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), 0.6F), Core.ItemGroups.TAB_INNER_DECO);
        ACCELERATOR_RINGS_END = registerBlockWithModel("innerdeco/accelerator/accl_rings_end", () -> new BlockSideCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), 0.6F), Core.ItemGroups.TAB_INNER_DECO);
        CONTROL_PANEL_UP = registerBlockWithModel("innerdeco/control_panel/cp_up", () -> new ControlTable(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)), Core.ItemGroups.TAB_INNER_DECO);
        CONTROL_PANEL_DOWN = registerBlockWithModel("innerdeco/control_panel/cp_down", () -> new ControlTable(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)), Core.ItemGroups.TAB_INNER_DECO);
        RUSTY_HANDHOLD = registerBlockWithModel("innerdeco/handhold/rusty_handhold", () -> new RustyHandhold(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), 1F), Core.ItemGroups.TAB_INNER_DECO);
        BALCONY_HANDHOLD = registerBlockWithModel("innerdeco/handhold/balcony_handhold", () -> new ThinHandhold(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), 1F), Core.ItemGroups.TAB_INNER_DECO);
        STREET_FENCE = registerBlockWithModel("innerdeco/handhold/street_handhold", () -> new ThinHandhold(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), 1F), Core.ItemGroups.TAB_INNER_DECO);

        //mech
        CRUSHER = registerBlockWithModel("mech/crusher", () -> new BlockCrusher(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        AFFINAGE_FACTORY = registerBlockWithModel("mech/affinage", () -> new AffinageFactory(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        ENERGY_GENERATOR = registerBlockWithModel("mech/diesel_generator", () -> new DieselGenerator(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid(), BOX), Core.ItemGroups.TAB_INNER_DECO);
        DIESEL_TANK = registerBlockWithModel("mech/diesel_tank", () -> new DieselTank(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        DIESEL_E_GENERATOR = registerBlockWithModel("mech/diesel_electro_generator", () -> new DieselElectroGenerator(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);

        //trim_stone
        TRIM_TILE_1 = registerBlock("structural/trim_tile_1", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        TRIM_TILE_1_BR = registerBlock("structural/trim_tile_1_br", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        TRIM_STONE_1 = registerBlock("structural/trim_stone_1", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        TRIM_STONE_2 = registerBlock("structural/trim_stone_2", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        TRIM_STONE_3 = registerBlock("structural/trim_stone_3", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        TRIM_STONE_4 = registerBlock("structural/trim_stone_4", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        PARQUET_BLOCK = registerBlock("structural/parquet_block", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        TRIM_TILE_RED = registerBlock("structural/trim_tile_red", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        TRIM_TILE_BLUE = registerBlock("structural/trim_tile_blue", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        CONCRETE_PLATE = registerBlock("structural/concrete_plate", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        CONTAINMENT_BLOCK = registerBlock("structural/containment_block", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        TRIM_METAL_1 = registerBlock("structural/trim_metal_1", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        TRIM_METAL_2 = registerBlock("structural/trim_metal_2", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        LEADCERAMIC_TILE = registerBlock("structural/leadceramic_tile", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);

        //ceramic_glass
        CERAMIC_GLASS_BLUE = registerBlock("structural/ceramic_glass_blue", () -> new Block(AbstractBlock.Properties.create(Material.GLASS).setRequiresTool().hardnessAndResistance(1f,2f).harvestTool(ToolType.PICKAXE).sound(SoundType.GLASS).notSolid()),Core.ItemGroups.TAB_MAIN);
        CERAMIC_GLASS_GREEN = registerBlock("structural/ceramic_glass_green", () -> new Block(AbstractBlock.Properties.create(Material.GLASS).setRequiresTool().hardnessAndResistance(1f,2f).harvestTool(ToolType.PICKAXE).sound(SoundType.GLASS).notSolid()),Core.ItemGroups.TAB_MAIN);
        CERAMIC_GLASS_BROWN = registerBlock("structural/ceramic_glass_brown", () -> new Block(AbstractBlock.Properties.create(Material.GLASS).setRequiresTool().hardnessAndResistance(1f,2f).harvestTool(ToolType.PICKAXE).sound(SoundType.GLASS).notSolid()),Core.ItemGroups.TAB_MAIN);

        //linos
        LINO_1 = registerBlock("lino/lino1", () -> new Block(AbstractBlock.Properties.create(Material.WOOL).setRequiresTool().hardnessAndResistance(1f,2f).harvestTool(ToolType.AXE).sound(SoundType.CLOTH)),Core.ItemGroups.TAB_MAIN);
        LINO_2 = registerBlock("lino/lino2", () -> new Block(AbstractBlock.Properties.create(Material.WOOL).setRequiresTool().hardnessAndResistance(1f,2f).harvestTool(ToolType.AXE).sound(SoundType.CLOTH)),Core.ItemGroups.TAB_MAIN);
        LINO_3 = registerBlock("lino/lino3", () -> new Block(AbstractBlock.Properties.create(Material.WOOL).setRequiresTool().hardnessAndResistance(1f,2f).harvestTool(ToolType.AXE).sound(SoundType.CLOTH)),Core.ItemGroups.TAB_MAIN);
        LINO_4 = registerBlock("lino/lino4", () -> new Block(AbstractBlock.Properties.create(Material.WOOL).setRequiresTool().hardnessAndResistance(1f,2f).harvestTool(ToolType.AXE).sound(SoundType.CLOTH)),Core.ItemGroups.TAB_MAIN);
        LINO_5 = registerBlock("lino/lino5", () -> new Block(AbstractBlock.Properties.create(Material.WOOL).setRequiresTool().hardnessAndResistance(1f,2f).harvestTool(ToolType.AXE).sound(SoundType.CLOTH)),Core.ItemGroups.TAB_MAIN);
        LINO_6 = registerBlock("lino/lino6", () -> new Block(AbstractBlock.Properties.create(Material.WOOL).setRequiresTool().hardnessAndResistance(1f,2f).harvestTool(ToolType.AXE).sound(SoundType.CLOTH)),Core.ItemGroups.TAB_MAIN);
        LINO_7 = registerBlock("lino/lino7", () -> new Block(AbstractBlock.Properties.create(Material.WOOL).setRequiresTool().hardnessAndResistance(1f,2f).harvestTool(ToolType.AXE).sound(SoundType.CLOTH)),Core.ItemGroups.TAB_MAIN);
        LINO_8 = registerBlock("lino/lino8", () -> new Block(AbstractBlock.Properties.create(Material.WOOL).setRequiresTool().hardnessAndResistance(1f,2f).harvestTool(ToolType.AXE).sound(SoundType.CLOTH)),Core.ItemGroups.TAB_MAIN);
        //concrete_railing
        CONCRETE_RAILING_ORANGE = registerBlockWithModel("structural/concrete_railing_orange", () -> new BlockRotatedAxisCustomModelInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid(), "concrete_railing.orange", 0.5F), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_WHITE = registerBlockWithModel("structural/concrete_railing_white", () -> new BlockRotatedAxisCustomModelInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid(), "concrete_railing.white", 0.5F), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_GRAY = registerBlockWithModel("structural/concrete_railing_gray", () -> new BlockRotatedAxisCustomModelInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid(), "concrete_railing.gray", 0.5F), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_GREEN = registerBlockWithModel("structural/concrete_railing_green", () -> new BlockRotatedAxisCustomModelInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid(), "concrete_railing.greem", 0.5F), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_RED = registerBlockWithModel("structural/concrete_railing_red", () -> new BlockRotatedAxisCustomModelInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid(), "concrete_railing.red", 0.5F), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_BLUE = registerBlockWithModel("structural/concrete_railing_blue", () -> new BlockRotatedAxisCustomModelInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid(), "concrete_railing.blue", 0.5F), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_BEIGE = registerBlockWithModel("structural/concrete_railing_beige", () -> new BlockRotatedAxisCustomModelInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid(), "concrete_railing.beige", 0.5F), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_BEIGE2 = registerBlockWithModel("structural/concrete_railing_beige2", () -> new BlockRotatedAxisCustomModelInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid(), "concrete_railing.beige2", 0.5F), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_YELLOW = registerBlockWithModel("structural/concrete_railing_yellow", () -> new BlockRotatedAxisCustomModelInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid(), "concrete_railing.yellow", 0.5F), Core.ItemGroups.TAB_MAIN);

        //beams
        IRON_BEAM_CONCRETE = registerBlockWithModel("structural/iron_beam_concrete", () -> new BlockRotatedAxisCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid(), 0.5F), Core.ItemGroups.TAB_MAIN);
        IRON_BEAM = registerBlockWithModel("structural/iron_beam", () -> new BlockRotatedAxisCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid(), 0.5F), Core.ItemGroups.TAB_MAIN);
        IRON_BEAM_THIN = registerBlockWithModel("structural/iron_beam_thin", () -> new BlockRotatedAxisCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid(), 0.5F), Core.ItemGroups.TAB_MAIN);
        //concrete
        CONCRETE_ORANGE = registerBlock("concrete/concrete_orange", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_orange"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_BLUE = registerBlock("concrete/concrete_blue", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_blue"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_RED = registerBlock("concrete/concrete_red", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_red"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_YELLOW = registerBlock("concrete/concrete_yellow", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_yellow"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_WHITE = registerBlock("concrete/concrete_white", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_white"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_BEIGE = registerBlock("concrete/concrete_beige", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_beige"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_GREEN = registerBlock("concrete/concrete_green", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_green"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_GRAY = registerBlock("concrete/concrete_gray", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_gray"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_BEIGE2 = registerBlock("concrete/concrete_beige2", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_beige2"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_BLUE_BR = registerBlock("concrete/concrete_blue_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_blue_br"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_ORANGE_BR = registerBlock("concrete/concrete_orange_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_orange_br"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_RED_BR = registerBlock("concrete/concrete_red_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_red_br"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_YELLOW_BR = registerBlock("concrete/concrete_yellow_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_yellow_br"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_WHITE_BR = registerBlock("concrete/concrete_white_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_white_br"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_BEIGE_BR = registerBlock("concrete/concrete_beige_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_beige_br"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_GREEN_BR = registerBlock("concrete/concrete_green_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_green_br"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_GRAY_BR = registerBlock("concrete/concrete_gray_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_gray_br"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_BEIGE2_BR = registerBlock("concrete/concrete_beige2_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_beige2_br"),Core.ItemGroups.TAB_MAIN);
        //stairs
        CONCRETE_STAIRS_GRAY = registerBlock("stairs/concrete_gray_stairs", () -> new BaseStairs(CONCRETE_GRAY.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()),Core.ItemGroups.TAB_MAIN);
        CONCRETE_STAIRS_GREEN = registerBlock("stairs/concrete_green_stairs", () -> new BaseStairs(CONCRETE_GREEN.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()),Core.ItemGroups.TAB_MAIN);
        CONCRETE_STAIRS_BLUE = registerBlock("stairs/concrete_blue_stairs", () -> new BaseStairs(CONCRETE_BLUE.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()),Core.ItemGroups.TAB_MAIN);
        CONCRETE_STAIRS_BEIGE = registerBlock("stairs/concrete_beige_stairs", () -> new BaseStairs(CONCRETE_BEIGE.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()),Core.ItemGroups.TAB_MAIN);
        CONCRETE_STAIRS_BEIGE2 = registerBlock("stairs/concrete_beige2_stairs", () -> new BaseStairs(CONCRETE_BEIGE2.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()),Core.ItemGroups.TAB_MAIN);
        CONCRETE_STAIRS_RED = registerBlock("stairs/concrete_red_stairs", () -> new BaseStairs(CONCRETE_RED.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()),Core.ItemGroups.TAB_MAIN);
        CONCRETE_STAIRS_ORANGE = registerBlock("stairs/concrete_orange_stairs", () -> new BaseStairs(CONCRETE_ORANGE.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()),Core.ItemGroups.TAB_MAIN);
        CONCRETE_STAIRS_YELLOW = registerBlock("stairs/concrete_yellow_stairs", () -> new BaseStairs(CONCRETE_YELLOW.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()),Core.ItemGroups.TAB_MAIN);
        CONCRETE_STAIRS_WHITE = registerBlock("stairs/concrete_white_stairs", () -> new BaseStairs(CONCRETE_WHITE.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()),Core.ItemGroups.TAB_MAIN);
        //slabs
        CONCRETE_SLAB_GRAY = registerBlock("slabs/concrete_gray_slab", () -> new BaseSlab(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()),Core.ItemGroups.TAB_MAIN);
        CONCRETE_SLAB_GREEN = registerBlock("slabs/concrete_green_slab", () -> new BaseSlab(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()),Core.ItemGroups.TAB_MAIN);
        CONCRETE_SLAB_BLUE = registerBlock("slabs/concrete_blue_slab", () -> new BaseSlab(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()),Core.ItemGroups.TAB_MAIN);
        CONCRETE_SLAB_BEIGE = registerBlock("slabs/concrete_beige_slab", () -> new BaseSlab(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()),Core.ItemGroups.TAB_MAIN);
        CONCRETE_SLAB_BEIGE2 = registerBlock("slabs/concrete_beige2_slab", () -> new BaseSlab(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()),Core.ItemGroups.TAB_MAIN);
        CONCRETE_SLAB_RED = registerBlock("slabs/concrete_red_slab", () -> new BaseSlab(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()),Core.ItemGroups.TAB_MAIN);
        CONCRETE_SLAB_ORANGE = registerBlock("slabs/concrete_orange_slab", () -> new BaseSlab(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()),Core.ItemGroups.TAB_MAIN);
        CONCRETE_SLAB_YELLOW = registerBlock("slabs/concrete_yellow_slab", () -> new BaseSlab(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()),Core.ItemGroups.TAB_MAIN);
        CONCRETE_SLAB_WHITE = registerBlock("slabs/concrete_white_slab", () -> new BaseSlab(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1f,4f).notSolid()),Core.ItemGroups.TAB_MAIN);

        //panels
        PANEL_CONCRETE_CORNER = registerNDBlock("structural/panel_concrete_corner", () -> new PanelBlockCorner(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f)), Core.ItemGroups.TAB_MAIN);
        PANEL_CONCRETE_SIDE = registerNDBlock("structural/panel_concrete_side", () -> new PanelBlockSide(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f)), Core.ItemGroups.TAB_MAIN);
        PANEL_CONCRETE = registerBlock("structural/panel_concrete", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f)),Core.ItemGroups.TAB_MAIN);
        PANEL_CONCRETE_YELLOW_CORNER = registerNDBlock("structural/panel_concrete_yellow_corner", () -> new PanelBlockCorner(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f)), Core.ItemGroups.TAB_MAIN);
        PANEL_CONCRETE_YELLOW_SIDE = registerNDBlock("structural/panel_concrete_yellow_side", () -> new PanelBlockSide(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f)), Core.ItemGroups.TAB_MAIN);
        PANEL_CONCRETE_YELLOW = registerBlock("structural/panel_concrete_yellow", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f)),Core.ItemGroups.TAB_MAIN);
        PANEL_TILE_CORNER = registerNDBlock("structural/panel_tile_corner", () -> new PanelBlockCorner(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f)), Core.ItemGroups.TAB_MAIN);
        PANEL_TILE_SIDE = registerNDBlock("structural/panel_tile_side", () -> new PanelBlockSide(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f)), Core.ItemGroups.TAB_MAIN);
        PANEL_TILE = registerBlock("structural/panel_tile", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f)),Core.ItemGroups.TAB_MAIN);

        //horizontal_tiles
        HORIZ_TILE_BLUE = registerBlock("horiztile/horiz_tile_blue", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.horiz_tile_blue"),Core.ItemGroups.TAB_MAIN);
        HORIZ_TILE_BLUE_BR = registerBlock("horiztile/horiz_tile_blue_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.horiz_tile_blue_br"),Core.ItemGroups.TAB_MAIN);
        HORIZ_TILE_WHITE = registerBlock("horiztile/horiz_tile_white", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.horiz_tile_white"),Core.ItemGroups.TAB_MAIN);
        HORIZ_TILE_WHITE_BR = registerBlock("horiztile/horiz_tile_white_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.horiz_tile_white_br"),Core.ItemGroups.TAB_MAIN);
        HORIZ_TILE_DARK_BLUE = registerBlock("horiztile/horiz_tile_dark_blue", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.horiz_tile_dark_blue"),Core.ItemGroups.TAB_MAIN);
        HORIZ_TILE_DARK_BLUE_BR = registerBlock("horiztile/horiz_tile_dark_blue_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.horiz_tile_dark_blue_br"),Core.ItemGroups.TAB_MAIN);

        //small_tiles
        SMALL_TILE_BLUE = registerBlock("smalltile/small_tile_blue", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.small_tile_blue"),Core.ItemGroups.TAB_MAIN);
        SMALL_TILE_BLUE_BR = registerBlock("smalltile/small_tile_blue_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.small_tile_blue_br"),Core.ItemGroups.TAB_MAIN);
        SMALL_TILE_WHITE = registerBlock("smalltile/small_tile_white", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.small_tile_white"),Core.ItemGroups.TAB_MAIN);
        SMALL_TILE_WHITE_BR = registerBlock("smalltile/small_tile_white_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.small_tile_white_br"),Core.ItemGroups.TAB_MAIN);
        SMALL_TILE_RED = registerBlock("smalltile/small_tile_red", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.small_tile_red"),Core.ItemGroups.TAB_MAIN);
        SMALL_TILE_RED_BR = registerBlock("smalltile/small_tile_red_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.small_tile_red_br"),Core.ItemGroups.TAB_MAIN);
        SMALL_TILE_YELLOW = registerBlock("smalltile/small_tile_yellow", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.small_tile_yellow"),Core.ItemGroups.TAB_MAIN);
        SMALL_TILE_YELLOW_BR = registerBlock("smalltile/small_tile_yellow_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.small_tile_yellow_br"),Core.ItemGroups.TAB_MAIN);

        //mosaic_tile
        TILE_MOSAIC_1 = registerBlock("mosaictile/tile_mosaic_1", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        TILE_MOSAIC_2 = registerBlock("mosaictile/tile_mosaic_2", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);

        //quad_tiles
        TILE_QUAD_GRAY = registerBlock("quadtile/tile_quad_gray", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_gray"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_WHITE = registerBlock("quadtile/tile_quad_white", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_white"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_BLUE = registerBlock("quadtile/tile_quad_blue", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_blue"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_1_BR = registerBlock("quadtile/tile_quad_1_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_1_br"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_2_BR = registerBlock("quadtile/tile_quad_2_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_2_br"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_1 = registerBlock("quadtile/tile_quad_1", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_1"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_2 = registerBlock("quadtile/tile_quad_2", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_2"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_4 = registerBlock("quadtile/tile_quad_4", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_4"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_5 = registerBlock("quadtile/tile_quad_5", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_5"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_3 = registerBlock("quadtile/tile_quad_3", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_3"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_3_BR = registerBlock("quadtile/tile_quad_3_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_3_br"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_5_BR = registerBlock("quadtile/tile_quad_5_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_5_br"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_5_BRf = registerBlock("quadtile/tile_quad_5_brf", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_5_brf"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_CONCRETE = registerBlock("quadtile/tile_quad_concrete", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_concrete"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_BLUE_BR = registerBlock("quadtile/tile_quad_blue_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_blue_br"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_WHITE_BR = registerBlock("quadtile/tile_quad_white_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_white_br"),Core.ItemGroups.TAB_MAIN);

        //rest_tiles
        TILE_REST_DARK_BLUE = registerBlock("resttile/tile_rest_dark_blue", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_dark_blue"),Core.ItemGroups.TAB_MAIN);
        TILE_REST_DARK_BLUE_BR = registerBlock("resttile/tile_rest_dark_blue_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_dark_blue_br"),Core.ItemGroups.TAB_MAIN);
        TILE_REST_BLUE = registerBlock("resttile/tile_rest_blue", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_blue"),Core.ItemGroups.TAB_MAIN);
        TILE_REST_BLUE_BR = registerBlock("resttile/tile_rest_blue_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_blue_br"),Core.ItemGroups.TAB_MAIN);
        TILE_REST_BLACK = registerBlock("resttile/tile_rest_black", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_black"),Core.ItemGroups.TAB_MAIN);
        TILE_REST_BLACK_BR = registerBlock("resttile/tile_rest_black_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_black_br"),Core.ItemGroups.TAB_MAIN);
        TILE_REST_BROWN = registerBlock("resttile/tile_rest_brown", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_brown"),Core.ItemGroups.TAB_MAIN);
        TILE_REST_BROWN_BR = registerBlock("resttile/tile_rest_brown_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_brown_br"),Core.ItemGroups.TAB_MAIN);
        TILE_REST_WHITE = registerBlock("resttile/tile_rest_white", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_white"),Core.ItemGroups.TAB_MAIN);
        TILE_REST_WHITE_BR = registerBlock("resttile/tile_rest_white_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_white_br"),Core.ItemGroups.TAB_MAIN);

        //reg tiles
        REGULAR_BROWN_TILE = registerBlock("regtile/tile_reg_brown", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_reg_brown"),Core.ItemGroups.TAB_MAIN);
        REGULAR_BROWN_TILE_BR = registerBlock("regtile/tile_reg_brown_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_reg_brown_br"),Core.ItemGroups.TAB_MAIN);
        REGULAR_LIL_TILE = registerBlock("regtile/tile_reg_lil", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_reg_lil"),Core.ItemGroups.TAB_MAIN);
        REGULAR_LIL_TILE_BR = registerBlock("regtile/tile_reg_lil_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_reg_lil_br"),Core.ItemGroups.TAB_MAIN);
        REGULAR_AM_TILE = registerBlock("regtile/tile_reg_am", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_reg_am"),Core.ItemGroups.TAB_MAIN);
        REGULAR_AM_TILE_BR = registerBlock("regtile/tile_reg_am_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_reg_am_br"),Core.ItemGroups.TAB_MAIN);

        //bricks
        RED_BRICKS_BR = registerBlock("brick/red_bricks_br", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        RED_BRICKS = registerBlock("brick/red_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        SHORT_BRICKS = registerBlock("brick/short_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        BRICKS_BR = registerBlock("brick/soviet_bricks_br", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        BRICKS = registerBlock("brick/soviet_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        BRICKS_WITH_WHITE = registerBlock("brick/soviet_bricks_with_white", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        WALL_BRICKS = registerBlock("brick/light_bricks2", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        LIGHT_BRICKS = registerBlock("brick/light_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        WALL_BRICKS_BR = registerBlock("brick/light_bricks2_br", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        LIGHT_BRICKS_BR = registerBlock("brick/light_bricks_br", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        YELLOW_BRICKS_1_BR = registerBlock("brick/yellow_bricks_1_br", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        YELLOW_BRICKS_1 = registerBlock("brick/yellow_bricks_1", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        YELLOW_BRICKS_2_BR = registerBlock("brick/yellow_bricks_2_br", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        YELLOW_BRICKS_2 = registerBlock("brick/yellow_bricks_2", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        YELLOW_BRICKS_3_BR = registerBlock("brick/yellow_bricks_3_br", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        YELLOW_BRICKS_3 = registerBlock("brick/yellow_bricks_3", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        WHITE_BRICKS = registerBlock("brick/white_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        WHITE_BRICKS_BR = registerBlock("brick/white_bricks_br", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        SMALL_BRICKS = registerBlock("brick/small_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);

    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);

        BLOCKS_CUSTOM_MODELS.register(eventBus);
        NOT_DEFAULT_BLOCKS.register(eventBus);
        ONLY_CUSTOM_BLOCKS.register(eventBus);
        ITEM_BLOCKS.register(eventBus);
        init();
    }
    private static <T extends Block> RegistryObject<T> registerOnlyCustomBlock(String name, Supplier<T> block, ItemGroup group) {
        RegistryObject<T> toReturn = ONLY_CUSTOM_BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, group);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, ItemGroup group) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, group);
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
