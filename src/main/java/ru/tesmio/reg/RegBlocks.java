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
import ru.tesmio.blocks.affinage_factory.AffinageFactory;
import ru.tesmio.blocks.baseblock.*;
import ru.tesmio.blocks.baseblock.subtype.*;
import ru.tesmio.blocks.circuits.*;
import ru.tesmio.blocks.const_panel.PanelBlockCorner;
import ru.tesmio.blocks.const_panel.PanelBlockSide;
import ru.tesmio.blocks.crusher.BlockCrusher;
import ru.tesmio.blocks.decorative.devices.*;
import ru.tesmio.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.blocks.decorative.lamp.*;
import ru.tesmio.blocks.decorative.props.*;
import ru.tesmio.blocks.decorative.props.chairs.BiolabChair;
import ru.tesmio.blocks.decorative.props.chairs.Chair;
import ru.tesmio.blocks.decorative.props.chairs.DspChair;
import ru.tesmio.blocks.decorative.props.chairs.PurpleChair;
import ru.tesmio.blocks.decorative.props.stillage.StillageBlock;
import ru.tesmio.blocks.decorative.props.tables.DspTable;
import ru.tesmio.blocks.decorative.props.tables.PurpleTable;
import ru.tesmio.blocks.decorative.slabs.BaseSlab;
import ru.tesmio.blocks.decorative.stairs.BaseStairs;
import ru.tesmio.blocks.decorative.windows.AlumFrame;
import ru.tesmio.blocks.decorative.windows.AlumWindow;
import ru.tesmio.blocks.decorative.windows.FactoryWindow;
import ru.tesmio.blocks.decorative.windows.ModernWindow;
import ru.tesmio.blocks.diesel_generator.DieselElectroGenerator;
import ru.tesmio.blocks.diesel_generator.DieselGenerator;
import ru.tesmio.blocks.diesel_generator.DieselTank;
import ru.tesmio.blocks.doors.*;
import ru.tesmio.blocks.fences.*;
import ru.tesmio.blocks.storage.desc_drawers.LinearTableDrawers;
import ru.tesmio.blocks.storage.dsp_tump.DspTumbBlock;
import ru.tesmio.blocks.storage.kitchen_table.KitchenTable;
import ru.tesmio.blocks.storage.safe.BlockSafe;
import ru.tesmio.blocks.tumbler.AirlockDoorController;
import ru.tesmio.blocks.tumbler.ElectroFenceTumbler;
import ru.tesmio.blocks.tumbler.RustyTumbler;
import ru.tesmio.core.Core;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class RegBlocks {
    public static final DeferredRegister<Block> ONLY_CUSTOM_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Core.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Core.MODID);
    public static final DeferredRegister<Block> NOT_DEFAULT_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Core.MODID);
    public static final DeferredRegister<Block> BLOCKS_CUSTOM_MODELS = DeferredRegister.create(ForgeRegistries.BLOCKS, Core.MODID);
    public static final DeferredRegister<Block> BLOCKS_CUSTOM_MODELS_COLORED = DeferredRegister.create(ForgeRegistries.BLOCKS, Core.MODID);
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
    public static RegistryObject<Block> FLUORESCENT_LAMP, BROKEN_FLUORESCENT_LAMP,FLUORESCENT_LAMP2, BROKEN_FLUORESCENT_LAMP2,FLUORESCENT_LAMP3,
            BROKEN_FLUORESCENT_LAMP3,INC_LAMP,RED_LAMP;
    public static RegistryObject<Block> STREET_LIGHTPOST, STREET_LIGHTPOST2, STREET_LAMP;
    public static RegistryObject<Block> AIRLOCK_DOOR,ALUMINIUM_DOOR,RAILING_DOOR,RUSTY_IRON_DOOR, RUSTY_BARS, CONTAINMENT_TRAPDOOR, LADDER_1, VENT_PIPE_BASE, VENT_PIPE, LADDER_2, AIRLOCK_DOOR_CONTROLLER, CONTAINMENT_DOOR, WOOD_DOOR_1, WOOD_DOOR_2;
    public static RegistryObject<Block> ACCELERATOR_CALC_BLOCK, ACCELERATOR_STAND, ACCELERATOR, ACCELERATOR_RINGS_END, ACCELERATOR_RINGS,
            ACCELERATOR_RINGS_CORNER_LEFT, ACCELERATOR_RINGS_CORNER_RIGHT, INFO_TABLE, SMALL_BUTTON, DRESS_CABIN,FRIDGE,DSP_TABLE2,DSP_TABLE,DSP_CHAIR,HOME_PIPES,HOME_PIPES_BATTERY;
    public static RegistryObject<Block> RUSTY_HANDHOLD, STREET_FENCE, BALCONY_HANDHOLD, HEAT_PIPES,HEAT_PIPES_CORNER, COLD_BATTERY, BIO_TUMBLER, BIO_POWER_SOCKET;
    public static RegistryObject<Block> COPPER_CIRCUIT, COPPER_CIRCUIT_EMPTY, SILVER_CIRCUIT,SILVER_CIRCUIT_EMPTY, GOLD_CIRCUIT,GOLD_CIRCUIT_EMPTY, DIAMOND_CIRCUIT, DIAMOND_CIRCUIT_EMPTY,NETHERITE_CIRCUIT, NETHERITE_CIRCUIT_EMPTY,PLATINUM_CIRCUIT, PLATINUM_CIRCUIT_EMPTY , PLATE_GOLDEN_JACKS, PLATE_GOLDEN_JACKS_EMPTY, PLATE_PLATINUM_JACKS, PLATE_PLATINUM_JACKS_EMPTY;

    public static RegistryObject<Block> CONCRETE_STAIRS_GRAY, CONCRETE_STAIRS_GREEN, CONCRETE_STAIRS_BLUE, CONCRETE_STAIRS_BEIGE,
            CONCRETE_STAIRS_BEIGE2, CONCRETE_STAIRS_RED, CONCRETE_STAIRS_YELLOW, CONCRETE_STAIRS_WHITE, CONCRETE_STAIRS_ORANGE;
    public static RegistryObject<Block> CONCRETE_SLAB_GRAY, CONCRETE_SLAB_GREEN, CONCRETE_SLAB_BLUE, CONCRETE_SLAB_BEIGE,
            CONCRETE_SLAB_BEIGE2, CONCRETE_SLAB_RED, CONCRETE_SLAB_YELLOW, CONCRETE_SLAB_WHITE, CONCRETE_SLAB_ORANGE;

    public static RegistryObject<Block> CONCRETE_FENCE, CONCRETE_FENCE_HIGH, CONCRETE_FENCE_BASE, FLOOR_GRID, CONCRETE_WALL, CONTACT_WIRE, ELECTRO_FENCE, ELECTRO_FENCE_TUMBLER, ELECTRO_FENCE_DOUBLE, SPIRAL_BARB_WIRE;
    public static RegistryObject<Block> TELEGRAPH, SOLDERING_STATION, AUDIORECORDER2, AUDIORECORDER, RADIOSTATION,TRANSFORMATOR_ISOLATORS, TRANSFORMATOR_BASE, TRANSFORMATOR_COIL, CLOCK_ELECTRONICA,MOTION_SENSOR,TURNSTILE, RUSTY_TUMBLER, RUSTY_TUMBLER2,REDSTONE_WIRE;

    public static RegistryObject<Block> A_METER,ASSIGNER_BLOCK, AUTOWRITER, CONTROLLER, EM_METER, FREQUE_ANALISATOR, FREQUE_METER, GEIGER_STATIC, M_METER, K_METER, OM_METER, OSCILLOSCOPE, P_METER, V_METER, W_METER;
    public static RegistryObject<Block> BIOLAB_TABLE, BIOLAB_TABLE2, BIOLAB_TABLE3, BIOLAB_TABLE4,BIOLAB_TABLE_CASE, CHEMLAB_TABLE, CHEMLAB_TABLE_CASE, STILLAGE, PINK_CHAIR, PURPLE_TABLE, PURPLE_CHAIR;
    public static RegistryObject<Block> ALUM_FRAMES, ALUM_FRAMES_EMPTY, ALUM_WINDOW, ALUM_WINDOW_EMPTY, MODERN_WINDOW, MODERN_WINDOW_EMPTY, MODERN_WINDOW_LEAF, MODERN_WINDOW_LEAF_EMPTY
            , WOOD_WINDOW, WOOD_WINDOW_EMPTY, WOOD_WINDOW_LEAF, WOOD_WINDOW_LEAF_EMPTY, FACTORY_WINDOW, FACTORY_WINDOW_EMPTY;
    public static RegistryObject<Block> IRON_BED, ex_po, SAFE, DSP_TUMB, BATH_TUBE, LAB_SINK, KITCHEN_SINK, ELECTRO_STOVE,KITCHEN_TABLE, SMALL_SINK, TOILET, SINK, BARB_WIRE,CONTACT_WIRE_OUTER,CONTACT_WIRE_INNER;

    public static RegistryObject<Block> DRY_CAB,MAGNET_MIXER, MIXER, LAB_STOVE ,SLATE, BIO_STILLAGE, CHAIN,LEAD_WALL, THIN_LEAD_WALL, CIRCLE_FILTER, HALF_CIRCLE_GRID, DIAGONAL_GRID, FULL_DIAGONAL_GRID, FULL_DIAGONAL_GRID_INVERT, REST_FILTER
    ,WINDPROOF_BETON_GRAY ,WINDPROOF_BETON_GREEN ,WINDPROOF_BETON_RED ,WINDPROOF_BETON_ORANGE ,WINDPROOF_BETON_YELLOW ,WINDPROOF_BETON_BLUE ,WINDPROOF_BETON_BEIGE ,WINDPROOF_BETON_BEIGE2 ,WINDPROOF_BETON_WHITE;
    protected static VoxelShape SHAPE_CIRCUIT = Block.makeCuboidShape(0.0D, 0.0D, 2.0D, 16.0D, 0.25D, 13.0D);
    protected static VoxelShape BOX = Block.makeCuboidShape(0.0D, 0.0D, 0D, 16.0D, 16D, 16.0D);
    protected static VoxelShape BOX_CONCRETE_FENCE_BASE[] = new VoxelShape[] {VoxelShapes.or(Block.makeCuboidShape(0.0D, 0.0D, 0D, 16.0D, 6D, 16.0D),
            Block.makeCuboidShape(1.0D, 6.0D, 1D, 15.0D, 12D, 15.0D),
            Block.makeCuboidShape(2.0D, 12.0D, 2D, 14.0D, 16D, 14.0D))};
    protected static VoxelShape SHAPE_CIRCUIT2 = Block.makeCuboidShape(0.0D, 0.0D, 3.0D, 16.0D, 0.25D, 13.0D);

    public static RegistryObject<Block> SYS_BLOCK, COMPUTER, SPEC_MONITOR, TELEVISOR, SIREN, SMALL_COMPUTER, ELECTRICAL_PANEL, CRYOCAPSULE, AUTOCLAVE, BIOLAB_CHAIR, GAS_STOVE;
    public static RegistryObject<Block> STANDART_SIGNAL_GEN, PULT, PULT1, PULT2, PULT3, E_CONVERTER, E_TESTER, SOUND_POWER_AMPLIFIER, COULOMETRIC_INTEGRATOR,WAVEMETER,COULOMETRIC_INTEGRATOR2, WELDING_MACHINE;

    public static RegistryObject<Block> BLOCK_MOULD,BLOCK_MOSS_FULL,BLOCK_MOSS;
    public static void init() {
        Symbols.addSymbols();
        Symbols.addSymbolsName();

        ex_po = registerBlockWithModel("outerdeco/symbols/ex_po", () -> new SymbolBlock(getP(Material.IRON, 0.4f,0.6f, null, 1, SoundType.METAL, false, true), 1F), Core.ItemGroups.TAB_SYMBOLS);
        for (int iter = 0; iter < Symbols.SYMBOLS_NAME.size(); iter++) {
           registerBlockWithModel("outerdeco/symbols/" + Symbols.SYMBOLS_NAME.get(iter), () -> new SymbolBlock(getP(Material.IRON, 0.4f,0.6f, null, 1, SoundType.METAL, false, true), 1F), Core.ItemGroups.TAB_SYMBOLS);
        }


        //windows
        AbstractBlock.Properties WINDOW_PROPERTIES = getP(Material.IRON, 0.6f,1.6f,ToolType.PICKAXE, 0, SoundType.GLASS, true, true);
        AbstractBlock.Properties WINDOW_PROPERTIES_WOOD = getP(Material.WOOD, 0.6f,1.6f,ToolType.AXE, 0, SoundType.GLASS, true, true);
        WOOD_WINDOW = registerBlockWithModel("innerdeco/windows/wood_window", () -> new ModernWindow(WINDOW_PROPERTIES_WOOD), Core.ItemGroups.TAB_OUTER_DECO);
        WOOD_WINDOW_EMPTY = registerBlockWithModel("innerdeco/windows/wood_window_empty", () -> new ModernWindow(WINDOW_PROPERTIES_WOOD), Core.ItemGroups.TAB_OUTER_DECO);
        WOOD_WINDOW_LEAF = registerBlockWithModel("innerdeco/windows/wood_window_leaf", () -> new ModernWindow(WINDOW_PROPERTIES_WOOD), Core.ItemGroups.TAB_OUTER_DECO);
        WOOD_WINDOW_LEAF_EMPTY = registerBlockWithModel("innerdeco/windows/wood_window_leaf_empty", () -> new ModernWindow(WINDOW_PROPERTIES_WOOD), Core.ItemGroups.TAB_OUTER_DECO);
        MODERN_WINDOW = registerBlockWithModel("innerdeco/windows/modern_window", () -> new ModernWindow(WINDOW_PROPERTIES), Core.ItemGroups.TAB_OUTER_DECO);
        MODERN_WINDOW_EMPTY = registerBlockWithModel("innerdeco/windows/modern_window_empty", () -> new ModernWindow(WINDOW_PROPERTIES), Core.ItemGroups.TAB_OUTER_DECO);
        MODERN_WINDOW_LEAF = registerBlockWithModel("innerdeco/windows/modern_window_leaf", () -> new ModernWindow(WINDOW_PROPERTIES), Core.ItemGroups.TAB_OUTER_DECO);
        MODERN_WINDOW_LEAF_EMPTY = registerBlockWithModel("innerdeco/windows/modern_window_leaf_empty", () -> new ModernWindow(WINDOW_PROPERTIES), Core.ItemGroups.TAB_OUTER_DECO);

        BLOCK_MOSS = registerBlockWithModel("outerdeco/block_moss", () -> new BlockMoss(), Core.ItemGroups.TAB_OUTER_DECO);
        BLOCK_MOSS_FULL = registerBlockWithModel("outerdeco/block_moss_full", () -> new BlockMoss(), Core.ItemGroups.TAB_OUTER_DECO);
        BLOCK_MOULD = registerBlockWithModel("outerdeco/block_mould", () -> new BlockMoss(), Core.ItemGroups.TAB_OUTER_DECO);


        ALUM_WINDOW = registerBlockWithModel("innerdeco/windows/alum_window", () -> new AlumWindow(WINDOW_PROPERTIES), Core.ItemGroups.TAB_OUTER_DECO);
        ALUM_WINDOW_EMPTY = registerBlockWithModel("innerdeco/windows/alum_window_empty", () -> new AlumWindow(WINDOW_PROPERTIES), Core.ItemGroups.TAB_OUTER_DECO);

        ALUM_FRAMES_EMPTY = registerBlockWithModel("innerdeco/windows/alum_frame_empty", () -> new AlumFrame(WINDOW_PROPERTIES), Core.ItemGroups.TAB_INNER_DECO);
        ALUM_FRAMES = registerBlockWithModel("innerdeco/windows/alum_frame", () -> new AlumFrame(WINDOW_PROPERTIES), Core.ItemGroups.TAB_INNER_DECO);
        //redstone devices
        REDSTONE_WIRE = registerBlockWithModel("innerdeco/devices/redstone_wire", () -> new BlockRedstoneWire(getP(Material.WOOL, 0.8f,1.1f,null, 0, SoundType.CLOTH, true, true), 1F), Core.ItemGroups.TAB_INNER_DECO);
        RUSTY_TUMBLER = registerBlockWithModel("innerdeco/rusty_tumbler", () -> new RustyTumbler(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), 1F), Core.ItemGroups.TAB_INNER_DECO);
        RUSTY_TUMBLER2 = registerBlockWithModel("innerdeco/rusty_tumbler2", () -> new RustyTumbler(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), 1F), Core.ItemGroups.TAB_INNER_DECO);

        //furniture
        AbstractBlock.Properties FURNITURE_PROPERTIES = getP(Material.WOOD, 1f,1.2f,ToolType.AXE, 0, SoundType.WOOD, true, true);
        AbstractBlock.Properties CLOTH_PROPERTIES = getP(Material.WOOL, 0.9f,0.8f,null, 0, SoundType.CLOTH, false, true);
        BIO_STILLAGE = registerBlockWithModel("innerdeco/bio_stillage", () -> new BioStillage(getP(Material.WOOD, 0.9f,1.1f,ToolType.AXE, 0, SoundType.GLASS, true, true)) {}, Core.ItemGroups.TAB_INNER_DECO);
        CHEMLAB_TABLE = registerBlockWithModel("innerdeco/chemlab_table", () -> new LinearTableDrawers(FURNITURE_PROPERTIES) {}, Core.ItemGroups.TAB_INNER_DECO);
        CHEMLAB_TABLE_CASE = registerBlockWithModel("innerdeco/chemlab_table_up", () -> new LinearTable(FURNITURE_PROPERTIES) {}, Core.ItemGroups.TAB_INNER_DECO);
        BIOLAB_TABLE = registerBlockWithModel("innerdeco/biolab_table", () -> new LinearTableDrawers(FURNITURE_PROPERTIES) {}, Core.ItemGroups.TAB_INNER_DECO);
        BIOLAB_TABLE_CASE = registerBlockWithModel("innerdeco/biolab_table_up", () -> new LinearTable(FURNITURE_PROPERTIES) {}, Core.ItemGroups.TAB_INNER_DECO);
        BIOLAB_TABLE2 = registerBlockWithModel("innerdeco/biolab_table2", () -> new LinearTable(FURNITURE_PROPERTIES) {}, Core.ItemGroups.TAB_INNER_DECO);
        BIOLAB_TABLE3 = registerBlockWithModel("innerdeco/biolab_table3", () -> new LinearTable(FURNITURE_PROPERTIES) {}, Core.ItemGroups.TAB_INNER_DECO);
        BIOLAB_TABLE4 = registerBlockWithModel("innerdeco/biolab_table4", () -> new LinearTableDrawers(FURNITURE_PROPERTIES) {}, Core.ItemGroups.TAB_INNER_DECO);
        PURPLE_TABLE = registerBlockWithModel("innerdeco/furniture/purple_table", () -> new PurpleTable(FURNITURE_PROPERTIES) {}, Core.ItemGroups.TAB_INNER_DECO);
        PURPLE_CHAIR = registerBlockWithModel("innerdeco/furniture/purple_chair", () -> new PurpleChair(CLOTH_PROPERTIES, 0.5F) {}, Core.ItemGroups.TAB_INNER_DECO);
        PINK_CHAIR = registerBlockWithModel("innerdeco/furniture/pink_chair", () -> new Chair(CLOTH_PROPERTIES, 0.5F) {}, Core.ItemGroups.TAB_INNER_DECO);
        STILLAGE = registerBlockWithModel("innerdeco/stillage", () -> new StillageBlock(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)), Core.ItemGroups.TAB_INNER_DECO);

        //other devices
        AbstractBlock.Properties DEVICES_PROPERTIES = getP(Material.IRON, 0.7f,1f,null, 0, SoundType.METAL, false, true);
        MOTION_SENSOR = registerBlockWithModel("innerdeco/devices/motion_sensor", () -> new EntitySensor(DEVICES_PROPERTIES) {}, Core.ItemGroups.TAB_OUTER_DECO);
        TURNSTILE = registerBlockWithModel("innerdeco/devices/turnstile_off", () -> new Turnstile(getP(Material.IRON, 1.0f,1.6f,null, 0, SoundType.METAL, false, true)) {}, Core.ItemGroups.TAB_INNER_DECO);
        COMPUTER = registerBlockWithModel("innerdeco/devices/computer", () -> new Computer(DEVICES_PROPERTIES, 1F), Core.ItemGroups.TAB_INNER_DECO);
        SPEC_MONITOR = registerBlockWithModel("innerdeco/devices/spec_monitor", () -> new SpecMonitor(DEVICES_PROPERTIES, 1F), Core.ItemGroups.TAB_INNER_DECO);
        SMALL_COMPUTER = registerBlockWithModel("innerdeco/devices/small_computer", () -> new SmallComputer(DEVICES_PROPERTIES, 1F), Core.ItemGroups.TAB_INNER_DECO);
        SYS_BLOCK = registerBlockWithModel("innerdeco/devices/sys_block", () -> new SystemBlock(DEVICES_PROPERTIES, 1F), Core.ItemGroups.TAB_INNER_DECO);
        SIREN = registerBlockWithModel("innerdeco/devices/siren", () -> new Siren(DEVICES_PROPERTIES), Core.ItemGroups.TAB_INNER_DECO);
        TELEVISOR = registerBlockWithModel("innerdeco/devices/televisor", () -> new Televisor(DEVICES_PROPERTIES, 1F), Core.ItemGroups.TAB_INNER_DECO);
        ELECTRICAL_PANEL = registerBlockWithModel("innerdeco/devices/electrical_panel", () -> new ElectricalPanel(getP(Material.IRON, 0.9f,1.1f,null, 0, SoundType.METAL, false, true), 1F), Core.ItemGroups.TAB_INNER_DECO);
        GAS_STOVE = registerBlockWithModel("innerdeco/furniture/gas_stove", () -> new GasStove(getP(Material.IRON, 1.0f,1.6f,ToolType.PICKAXE, 0, SoundType.METAL, true, true), 1F), Core.ItemGroups.TAB_INNER_DECO);
        CRYOCAPSULE = registerBlockWithModel("innerdeco/cryocapsule", () -> new Cryocapsule(getP(Material.IRON, 1.0f,1.6f,ToolType.PICKAXE, 0, SoundType.METAL, true, true), 1F), Core.ItemGroups.TAB_INNER_DECO);
        BIOLAB_CHAIR = registerBlockWithModel("innerdeco/biolab_chair", () -> new BiolabChair(getP(Material.WOOD, 0.6f,1.6f,ToolType.AXE, 0, SoundType.WOOD, true, true), 1F), Core.ItemGroups.TAB_INNER_DECO);
        AUTOCLAVE = registerBlockWithModel("innerdeco/autoclave", () -> new Autoclave(getP(Material.IRON, 1.0f,1.6f,ToolType.PICKAXE, 0, SoundType.METAL, true, true), 1F), Core.ItemGroups.TAB_INNER_DECO);
        COLD_BATTERY = registerBlockWithModel("innerdeco/radiator", () -> new ColdBattery(getP(Material.IRON, 0.5f,1.1f,ToolType.PICKAXE, 0, SoundType.METAL, true, true)), Core.ItemGroups.TAB_INNER_DECO);
        BIO_TUMBLER = registerBlockWithModel("innerdeco/furniture/bio_tumbler", () -> new PowerTumbler(DEVICES_PROPERTIES, 1F), Core.ItemGroups.TAB_INNER_DECO);
        BIO_POWER_SOCKET = registerBlockWithModel("innerdeco/furniture/bio_power_socket", () -> new PowerSocket(DEVICES_PROPERTIES, 1F), Core.ItemGroups.TAB_INNER_DECO);
        LAB_STOVE = registerBlockWithModel("innerdeco/devices/lab_stove", () -> new ChemDevices(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), 1F), Core.ItemGroups.TAB_INNER_DECO);
        DRY_CAB = registerBlockWithModel("innerdeco/devices/dry_cab", () -> new DryCab(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), 1F), Core.ItemGroups.TAB_INNER_DECO);
        MIXER = registerBlockWithModel("innerdeco/devices/mixer", () -> new ChemDevices(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), 1F), Core.ItemGroups.TAB_INNER_DECO);
        MAGNET_MIXER = registerBlockWithModel("innerdeco/devices/magnet_mixer", () -> new ChemDevices(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(1f,4f).notSolid().harvestTool(ToolType.PICKAXE).sound(SoundType.METAL), 1F), Core.ItemGroups.TAB_INNER_DECO);

        //fence
        CONCRETE_FENCE = registerOnlyCustomBlock("outerdeco/fences/concrete_fence", () -> new ConcreteFence(getP(Material.ROCK, 1.1f,1.3f, ToolType.PICKAXE, 1, SoundType.STONE, true, true)), Core.ItemGroups.TAB_OUTER_DECO);
        CONCRETE_FENCE_HIGH = registerOnlyCustomBlock("outerdeco/fences/concrete_fence_high", () -> new ConcreteFenceHigh(getP(Material.ROCK, 1.1f,1.3f, ToolType.PICKAXE, 1, SoundType.STONE, true, true)), Core.ItemGroups.TAB_OUTER_DECO);
        CONCRETE_FENCE_BASE = registerBlockWithModel("outerdeco/fences/concrete_fence_base", () -> new BlockSideWithModelCustomAABB(getP(Material.ROCK, 1.1f,2.5f,ToolType.PICKAXE, 1, SoundType.METAL, true, true), BOX_CONCRETE_FENCE_BASE, true, 0.8F), Core.ItemGroups.TAB_OUTER_DECO);
        ELECTRO_FENCE = registerBlockWithModel("outerdeco/fences/electro_fence", () -> new ElectroFence(getP(Material.IRON, 0.6f,0.7f,ToolType.PICKAXE, 1, SoundType.METAL, true, true)), Core.ItemGroups.TAB_OUTER_DECO);
        ELECTRO_FENCE_TUMBLER = registerBlockWithModel("outerdeco/fences/electro_fence_tumbler", () -> new ElectroFenceTumbler(getP(Material.IRON, 1f,1.2f,ToolType.PICKAXE, 1, SoundType.METAL, true, true)), Core.ItemGroups.TAB_OUTER_DECO);
        ELECTRO_FENCE_DOUBLE = registerBlockWithModel("outerdeco/fences/electro_fence_double", () -> new ElectroFenceDouble(getP(Material.IRON, 0.84f,1.2f,ToolType.PICKAXE, 1, SoundType.METAL, false, true)), Core.ItemGroups.TAB_OUTER_DECO);
        CONTACT_WIRE = registerOnlyCustomBlock("outerdeco/streetdeco/contact_wire", () -> new ContactWire(getP(Material.IRON, 0.5f,1f,ToolType.PICKAXE, 0, SoundType.METAL, false, true), 1F), Core.ItemGroups.TAB_OUTER_DECO);
        CONTACT_WIRE_INNER = registerBlockWithModel("outerdeco/streetdeco/contact_wire_inner", () -> new ContactWireAngle(getP(Material.IRON, 0.5f,1f,ToolType.PICKAXE, 0, SoundType.METAL, false, true),1F), Core.ItemGroups.TAB_OUTER_DECO);
        CONTACT_WIRE_OUTER = registerBlockWithModel("outerdeco/streetdeco/contact_wire_outer", () -> new ContactWireAngle(getP(Material.IRON, 0.5f,1f,ToolType.PICKAXE, 0, SoundType.METAL, false, true),1F), Core.ItemGroups.TAB_OUTER_DECO);
        CONCRETE_WALL = registerBlockWithModel("outerdeco/fences/concrete_wall", () -> new ConcreteWall(getP(Material.ROCK, 1.1f,1.3f, ToolType.PICKAXE, 1, SoundType.STONE, true, true), 1F), Core.ItemGroups.TAB_OUTER_DECO);

        //post
        STREET_LIGHTPOST = registerBlockWithModel("outerdeco/streetdeco/street_lightpost", () -> new StreetLightpost(getP(Material.IRON, 0.7f,0.9f, ToolType.PICKAXE, 1, SoundType.METAL, true, true), 1F), Core.ItemGroups.TAB_OUTER_DECO);
        STREET_LIGHTPOST2 = registerBlockWithModel("outerdeco/streetdeco/street_lightpost2", () -> new StreetLightpost(getP(Material.IRON, 0.7f,0.9f, ToolType.PICKAXE, 1, SoundType.METAL, true, true), 1F), Core.ItemGroups.TAB_OUTER_DECO);


        //lamp
        AbstractBlock.Properties LAMP_PROPERTIES = getP(Material.GLASS, 0.4f,0.5f, null, 1, SoundType.GLASS, true, true);
        RED_LAMP = registerBlockWithModel("innerdeco/lamp/red_lamp", () -> new IncLamp(LAMP_PROPERTIES), Core.ItemGroups.TAB_INNER_DECO);
        INC_LAMP = registerBlockWithModel("innerdeco/lamp/inc_lamp", () -> new IncLamp(LAMP_PROPERTIES), Core.ItemGroups.TAB_INNER_DECO);
        FLUORESCENT_LAMP = registerBlockWithModel("innerdeco/lamp/fluo_lamp", () -> new FluoLamp(LAMP_PROPERTIES), Core.ItemGroups.TAB_INNER_DECO);
        BROKEN_FLUORESCENT_LAMP = registerBlockWithModel("innerdeco/lamp/broken_fluo_lamp", () -> new BrokenFluoLamp(LAMP_PROPERTIES), Core.ItemGroups.TAB_INNER_DECO);
        FLUORESCENT_LAMP2 = registerBlockWithModel("innerdeco/lamp/fluo_lamp2", () -> new FluoLamp2(LAMP_PROPERTIES), Core.ItemGroups.TAB_INNER_DECO);
        BROKEN_FLUORESCENT_LAMP2 = registerBlockWithModel("innerdeco/lamp/broken_fluo_lamp2", () -> new BrokenFluoLamp2(LAMP_PROPERTIES), Core.ItemGroups.TAB_INNER_DECO);
        FLUORESCENT_LAMP3 = registerBlockWithModel("innerdeco/lamp/fluo_lamp3", () -> new FluoLamp3(LAMP_PROPERTIES), Core.ItemGroups.TAB_INNER_DECO);
        BROKEN_FLUORESCENT_LAMP3 = registerBlockWithModel("innerdeco/lamp/broken_fluo_lamp3", () -> new BrokenFluoLamp3(LAMP_PROPERTIES), Core.ItemGroups.TAB_INNER_DECO);
        STREET_LAMP = registerBlockWithModel("innerdeco/lamp/street_lamp", () -> new StreetLamp(LAMP_PROPERTIES, 1F), Core.ItemGroups.TAB_OUTER_DECO);

        //doors - настроить дроп дверей. Почему то не работает как везде
        WOOD_DOOR_2 = registerOnlyCustomBlock("innerdeco/wood_door_2", () -> new WoodDoor(getP(Material.WOOD, 0.7f,0.9f, ToolType.AXE, 1, SoundType.METAL, true, true)), Core.ItemGroups.TAB_INNER_DECO);
        WOOD_DOOR_1 = registerOnlyCustomBlock("innerdeco/wood_door_1", () -> new WoodDoor(getP(Material.WOOD, 0.7f,0.9f, ToolType.AXE, 1, SoundType.METAL, true, true)), Core.ItemGroups.TAB_INNER_DECO);
        AIRLOCK_DOOR_CONTROLLER = registerBlockWithModel("innerdeco/airlock_door_controller", () -> new AirlockDoorController(getP(Material.IRON, 0.9f,1.1f, ToolType.PICKAXE, 1, SoundType.METAL, true, true)), Core.ItemGroups.TAB_INNER_DECO);
        AIRLOCK_DOOR = registerOnlyCustomBlock("innerdeco/airlock_door", () -> new AirlockDoorBlock(getP(Material.IRON, 0.7f,1.1f, ToolType.PICKAXE, 1, SoundType.METAL, true, true)), Core.ItemGroups.TAB_INNER_DECO);
        CONTAINMENT_DOOR = registerOnlyCustomBlock("innerdeco/containment_door", () -> new ContainmentDoor(getP(Material.ANVIL, 4.8f,12.0f, ToolType.PICKAXE, 1, SoundType.METAL, true, true)), Core.ItemGroups.TAB_OUTER_DECO);
        RUSTY_IRON_DOOR = registerOnlyCustomBlock("innerdeco/rusty_iron_door", () -> new RustyIronDoor(getP(Material.IRON, 1.0f,1.2f, ToolType.PICKAXE, 1, SoundType.METAL, true, true)), Core.ItemGroups.TAB_OUTER_DECO);
        ALUMINIUM_DOOR = registerOnlyCustomBlock("innerdeco/aluminium_door", () -> new AluminiumDoorBlock(getP(Material.IRON, 0.8f,0.6f, ToolType.PICKAXE, 1, SoundType.METAL, true, true)), Core.ItemGroups.TAB_OUTER_DECO);
        RAILING_DOOR = registerOnlyCustomBlock("innerdeco/railing_door", () -> new RailingDoorBlock(getP(Material.IRON, 0.95f,1.1f, ToolType.PICKAXE, 1, SoundType.METAL, true, true)), Core.ItemGroups.TAB_INNER_DECO);
        CONTAINMENT_TRAPDOOR = registerOnlyCustomBlock("innerdeco/containment_trapdoor", () -> new ContainmentTrapdoor(getP(Material.ANVIL, 4.8f,12.0f, ToolType.PICKAXE, 1, SoundType.METAL, true, true)), Core.ItemGroups.TAB_INNER_DECO);


        //circuits
        PLATE_GOLDEN_JACKS = registerOnlyCustomBlock("innerdeco/circuit/plate_golden_jack", () -> new PlateGoldenJack(getP(Material.CORAL, 0.2f,0.1f, null, 1, SoundType.CLOTH, false, true), SHAPE_CIRCUIT2), Core.ItemGroups.TAB_ITEMS);
        PLATE_GOLDEN_JACKS_EMPTY = registerOnlyCustomBlock("innerdeco/circuit/plate_golden_jack_empty", () -> new BlockCustomModel(getP(Material.CORAL, 0.2f,0.1f, null, 1, SoundType.CLOTH, false, true), SHAPE_CIRCUIT2, 1F), Core.ItemGroups.TAB_ITEMS);
        PLATE_PLATINUM_JACKS = registerOnlyCustomBlock("innerdeco/circuit/plate_platinum_jack", () -> new PlatePlatinumJack(getP(Material.CORAL, 0.2f,0.1f, null, 1, SoundType.CLOTH, false, true), SHAPE_CIRCUIT2), Core.ItemGroups.TAB_ITEMS);
        PLATE_PLATINUM_JACKS_EMPTY = registerOnlyCustomBlock("innerdeco/circuit/plate_platinum_jack_empty", () -> new BlockCopperCircuit(getP(Material.CORAL, 0.2f,0.1f, null, 1, SoundType.CLOTH, false, true), SHAPE_CIRCUIT2), Core.ItemGroups.TAB_ITEMS);
        COPPER_CIRCUIT = registerOnlyCustomBlock("innerdeco/circuit/copper_plate", () -> new BlockCopperCircuit(getP(Material.CORAL, 0.2f,0.1f, null, 1, SoundType.CLOTH, false, true), SHAPE_CIRCUIT), Core.ItemGroups.TAB_ITEMS);
        COPPER_CIRCUIT_EMPTY = registerOnlyCustomBlock("innerdeco/circuit/copper_plate_empty", () -> new BlockCircuit(getP(Material.CORAL, 0.2f,0.1f, null, 1, SoundType.CLOTH, false, true), SHAPE_CIRCUIT, 1F), Core.ItemGroups.TAB_ITEMS);
        SILVER_CIRCUIT = registerOnlyCustomBlock("innerdeco/circuit/silver_plate", () -> new BlockSilverCircuit(getP(Material.CORAL, 0.2f,0.1f, null, 1, SoundType.CLOTH, false, true), SHAPE_CIRCUIT), Core.ItemGroups.TAB_ITEMS);
        SILVER_CIRCUIT_EMPTY = registerOnlyCustomBlock("innerdeco/circuit/silver_plate_empty", () -> new BlockCircuit(getP(Material.CORAL, 0.2f,0.1f, null, 1, SoundType.CLOTH, false, true), SHAPE_CIRCUIT, 1F), Core.ItemGroups.TAB_ITEMS);
        GOLD_CIRCUIT = registerOnlyCustomBlock("innerdeco/circuit/gold_plate", () -> new BlockGoldCircuit(getP(Material.CORAL, 0.2f,0.1f, null, 1, SoundType.CLOTH, false, true), SHAPE_CIRCUIT), Core.ItemGroups.TAB_ITEMS);
        GOLD_CIRCUIT_EMPTY = registerOnlyCustomBlock("innerdeco/circuit/gold_plate_empty", () -> new BlockCircuit(getP(Material.CORAL, 0.2f,0.1f, null, 1, SoundType.CLOTH, false, true), SHAPE_CIRCUIT, 1F), Core.ItemGroups.TAB_ITEMS);
        DIAMOND_CIRCUIT = registerOnlyCustomBlock("innerdeco/circuit/diamond_plate", () -> new BlockDiamondCircuit(getP(Material.CORAL, 0.2f,0.1f, null, 1, SoundType.CLOTH, false, true), SHAPE_CIRCUIT), Core.ItemGroups.TAB_ITEMS);
        DIAMOND_CIRCUIT_EMPTY = registerOnlyCustomBlock("innerdeco/circuit/diamond_plate_empty", () -> new BlockCircuit(getP(Material.CORAL, 0.2f,0.1f, null, 1, SoundType.CLOTH, false, true), SHAPE_CIRCUIT, 1F), Core.ItemGroups.TAB_ITEMS);
        NETHERITE_CIRCUIT = registerOnlyCustomBlock("innerdeco/circuit/netherite_plate", () -> new BlockNetheriteCircuit(getP(Material.CORAL, 0.2f,0.1f, null, 1, SoundType.CLOTH, false, true), SHAPE_CIRCUIT), Core.ItemGroups.TAB_ITEMS);
        NETHERITE_CIRCUIT_EMPTY = registerOnlyCustomBlock("innerdeco/circuit/netherite_plate_empty", () -> new BlockCircuit(getP(Material.CORAL, 0.2f,0.1f, null, 1, SoundType.CLOTH, false, true), SHAPE_CIRCUIT, 1F), Core.ItemGroups.TAB_ITEMS);
        PLATINUM_CIRCUIT = registerOnlyCustomBlock("innerdeco/circuit/platinum_plate", () -> new BlockPlatinumCircuit(getP(Material.CORAL, 0.2f,0.1f, null, 1, SoundType.CLOTH, false, true), SHAPE_CIRCUIT), Core.ItemGroups.TAB_ITEMS);
        PLATINUM_CIRCUIT_EMPTY = registerOnlyCustomBlock("innerdeco/circuit/platinum_plate_empty", () -> new BlockCircuit(getP(Material.CORAL, 0.2f,0.1f, null, 1, SoundType.CLOTH, false, true), SHAPE_CIRCUIT, 1F), Core.ItemGroups.TAB_ITEMS);

        //innerdeco
        TUBING_HORIZONTAL = registerBlockWithModel("innerdeco/tubing_horizontal", () -> new BlockRotatedAxisCustomModel(0.6F), Core.ItemGroups.TAB_INNER_DECO);
        TUBING_VERTICAL = registerBlockWithModel("innerdeco/tubing_vertical", () -> new BlockRotatedAxisCustomModel(0.6F), Core.ItemGroups.TAB_INNER_DECO);
        //-- остановился здесь - дропы
        RUSTY_HANDHOLD = registerBlockWithModel("innerdeco/handhold/rusty_handhold", () -> new RustyHandhold(getP(Material.IRON, 0.7f,1.8f, ToolType.PICKAXE, 1, SoundType.METAL, true, true), 1F), Core.ItemGroups.TAB_INNER_DECO);
        BALCONY_HANDHOLD = registerBlockWithModel("innerdeco/handhold/balcony_handhold", () -> new ThinHandhold(getP(Material.IRON, 0.7f,1.8f, ToolType.PICKAXE, 1, SoundType.METAL, true, true), 1F), Core.ItemGroups.TAB_OUTER_DECO);
        STREET_FENCE = registerBlockWithModel("innerdeco/handhold/street_handhold", () -> new ThinHandhold(getP(Material.IRON, 0.7f,1.8f, ToolType.PICKAXE, 1, SoundType.METAL, true, true), 1F), Core.ItemGroups.TAB_INNER_DECO);
        HEAT_PIPES = registerBlockWithModel("innerdeco/heat_pipes", () -> new HeatPipes(getP(Material.IRON, 1.2f,2.5f, ToolType.PICKAXE, 1, SoundType.METAL, true, true)), Core.ItemGroups.TAB_OUTER_DECO);
        HEAT_PIPES_CORNER = registerBlockWithModel("innerdeco/heat_pipes_corner", () -> new HeatPipesCorner(getP(Material.IRON, 1.2f,2.5f, ToolType.PICKAXE, 1, SoundType.METAL, true, true), 1F), Core.ItemGroups.TAB_OUTER_DECO);
        LAB_SINK = registerBlockWithModel("innerdeco/lab_sink", () -> new LabSink(getP(Material.IRON, 0.8f,1.5f, ToolType.PICKAXE, 1, SoundType.METAL, true, true), 1F), Core.ItemGroups.TAB_INNER_DECO);
        KITCHEN_SINK = registerBlockWithModel("innerdeco/furniture/kitchen_sink", () -> new KitchenBlock(getP(Material.IRON, 0.8f,1.5f, ToolType.PICKAXE, 1, SoundType.METAL, true, true), 1F), Core.ItemGroups.TAB_INNER_DECO);
        ELECTRO_STOVE = registerBlockWithModel("innerdeco/furniture/e_stove", () -> new GasStove(getP(Material.IRON, 1.1f,1.2f, ToolType.PICKAXE, 1, SoundType.METAL, true, true), 1F), Core.ItemGroups.TAB_INNER_DECO);
        KITCHEN_TABLE= registerBlockWithModel("innerdeco/furniture/kitchen_table", () -> new KitchenTable(getP(Material.WOOD, 1f,1.2f,ToolType.AXE, 1, SoundType.WOOD, true, true), 1F), Core.ItemGroups.TAB_INNER_DECO);
        SINK = registerBlockWithModel("innerdeco/furniture/sink", () -> new Sink(getP(Material.ROCK, 0.9f,0.5f, ToolType.PICKAXE, 1, SoundType.GLASS, false, true), 1F), Core.ItemGroups.TAB_INNER_DECO);
        SMALL_SINK = registerBlockWithModel("innerdeco/furniture/small_sink", () -> new SmallSink(getP(Material.ROCK, 0.8f,0.5f, ToolType.PICKAXE, 1, SoundType.GLASS, false, true), 1F), Core.ItemGroups.TAB_INNER_DECO);
        TOILET = registerBlockWithModel("innerdeco/furniture/toilet", () -> new Toilet(getP(Material.ROCK, 0.8f,0.5f, ToolType.PICKAXE, 1, SoundType.GLASS, false, true), 1F), Core.ItemGroups.TAB_INNER_DECO);
        DSP_CHAIR = registerBlockWithModel("innerdeco/furniture/dsp_chair", () -> new DspChair(getP(Material.WOOD, 0.7f,0.8f,ToolType.AXE, 1, SoundType.WOOD, false, true), 1F), Core.ItemGroups.TAB_INNER_DECO);
        DSP_TABLE = registerBlockWithModel("innerdeco/furniture/dsp_table", () -> new DspTable(getP(Material.WOOD, 0.7f,0.8f,ToolType.AXE, 1, SoundType.WOOD, false, true)), Core.ItemGroups.TAB_INNER_DECO);
        DSP_TABLE2 = registerBlockWithModel("innerdeco/furniture/dsp_table_non_cloth", () -> new DspTable(getP(Material.WOOD, 0.7f,0.8f,ToolType.AXE, 1, SoundType.WOOD, false, true)), Core.ItemGroups.TAB_INNER_DECO);
        SAFE = registerBlockWithModel("innerdeco/furniture/safe", () -> new BlockSafe(getP(Material.IRON, 9.65f,43f, ToolType.PICKAXE, 1, SoundType.METAL, true, true), 1F), Core.ItemGroups.TAB_INNER_DECO);
        DSP_TUMB = registerBlockWithModel("innerdeco/furniture/dsp_tumb", () -> new DspTumbBlock(getP(Material.WOOD, 1f,1.2f,ToolType.AXE, 1, SoundType.WOOD, false, true)), Core.ItemGroups.TAB_INNER_DECO);
        INFO_TABLE = registerBlockWithModel("innerdeco/devices/infotable", () -> new InfoTable(getP(Material.IRON, 0.6f,1f, ToolType.PICKAXE, 1, SoundType.METAL, true, true)), Core.ItemGroups.TAB_INNER_DECO);
        SMALL_BUTTON = registerBlockWithModel("innerdeco/devices/small_button", () -> new SmallButton(getP(Material.IRON, 0.2f,0.3f,ToolType.PICKAXE, 1, SoundType.METAL, false, true)), Core.ItemGroups.TAB_INNER_DECO);
        FRIDGE = registerOnlyCustomBlock("innerdeco/furniture/fridge", () -> new Fridge(getP(Material.IRON, 1.0f,2f,ToolType.PICKAXE, 1, SoundType.METAL, true, true), 1F), Core.ItemGroups.TAB_INNER_DECO);
        DRESS_CABIN = registerOnlyCustomBlock("innerdeco/dress_cabin", () -> new DressCabin(getP(Material.WOOD, 0.9f,1f,ToolType.AXE, 1, SoundType.CLOTH, false, true), 1F), Core.ItemGroups.TAB_INNER_DECO);
        HOME_PIPES = registerBlockWithModel("innerdeco/pipes/home_pipes", () -> new HomePipes(getP(Material.IRON, 1f,2f,ToolType.PICKAXE, 1, SoundType.METAL, true, true), 1F), Core.ItemGroups.TAB_INNER_DECO);
        HOME_PIPES_BATTERY = registerBlockWithModel("innerdeco/pipes/home_pipes_battery", () -> new HomePipesBattery(getP(Material.IRON, 2f,4f,ToolType.PICKAXE, 1, SoundType.METAL, true, true), 1F), Core.ItemGroups.TAB_INNER_DECO);
        CIRCLE_FILTER = registerBlockWithModel("innerdeco/circle_filter", () -> new VentFilter(getP(Material.IRON, 0.6f,1f,ToolType.PICKAXE, 0, SoundType.METAL, false, true)), Core.ItemGroups.TAB_INNER_DECO);
        REST_FILTER = registerBlockWithModel("innerdeco/rest_filter", () -> new VentFilterRest(getP(Material.IRON, 0.6f,1f,ToolType.PICKAXE, 0, SoundType.METAL, false, true)), Core.ItemGroups.TAB_INNER_DECO);
        IRON_BED = registerOnlyCustomBlock("innerdeco/furniture/iron_bed", () -> new IronBed(getP(Material.IRON, 1.1f,2.1f, ToolType.PICKAXE, 1, SoundType.METAL, true, true)), Core.ItemGroups.TAB_INNER_DECO);
        BATH_TUBE = registerOnlyCustomBlock("innerdeco/furniture/bath_tube", () -> new BathTube(getP(Material.ROCK, 0.8f,0.5f, ToolType.PICKAXE, 1, SoundType.GLASS, false, true)), Core.ItemGroups.TAB_INNER_DECO);

        //outerdeco
        HALF_CIRCLE_GRID = registerBlockWithModel("outerdeco/streetdeco/half_circle_grid", () -> new WindowGrid(getP(Material.IRON, 1.6f,3f,ToolType.PICKAXE, 1, SoundType.METAL, true, true), 1), Core.ItemGroups.TAB_OUTER_DECO);
        DIAGONAL_GRID = registerBlockWithModel("outerdeco/streetdeco/diagonal_grid", () -> new WindowGrid(getP(Material.IRON, 1.6f,3f,ToolType.PICKAXE, 1, SoundType.METAL, true, true), 1), Core.ItemGroups.TAB_OUTER_DECO);
        FULL_DIAGONAL_GRID = registerBlockWithModel("outerdeco/streetdeco/full_diagonal_grid", () -> new WindowGrid(getP(Material.IRON, 1.4f,3f,ToolType.PICKAXE, 1, SoundType.METAL, true, true), 1), Core.ItemGroups.TAB_OUTER_DECO);
        FULL_DIAGONAL_GRID_INVERT = registerBlockWithModel("outerdeco/streetdeco/full_diagonal_grid_invert", () -> new WindowGrid(getP(Material.IRON, 1.4f,3f,ToolType.PICKAXE, 1, SoundType.METAL, true, true), 1), Core.ItemGroups.TAB_OUTER_DECO);
        BARB_WIRE = registerBlockWithModel("outerdeco/streetdeco/barb_wire", () -> new BarbWire(getP(Material.IRON, 0.65f,1f, ToolType.PICKAXE, 1, SoundType.METAL, true, true)), Core.ItemGroups.TAB_OUTER_DECO);
        SPIRAL_BARB_WIRE = registerBlockWithModel("outerdeco/streetdeco/spiral_barb_wire", () -> new SpiralBarbWire(getP(Material.IRON, 0.9f,2f, ToolType.PICKAXE, 1, SoundType.METAL, true, true), 1F), Core.ItemGroups.TAB_OUTER_DECO);
        FLOOR_GRID = registerBlockWithModel("innerdeco/floor_grid", () -> new FloorGrid(getP(Material.IRON, 0.78f,1.2f, ToolType.PICKAXE, 1, SoundType.METAL, true, true), 1F), Core.ItemGroups.TAB_OUTER_DECO);
        FACTORY_WINDOW = registerBlockWithModel("outerdeco/factory_window", () -> new FactoryWindow( 1F),Core.ItemGroups.TAB_OUTER_DECO);
        FACTORY_WINDOW_EMPTY = registerBlockWithModel("outerdeco/factory_window_empty", () -> new FactoryWindow( 1F),Core.ItemGroups.TAB_OUTER_DECO);
        SLATE = registerBlockWithModel("innerdeco/slate", () -> new SlateBlock(getP(Material.ROCK, 0.8f,1.1f,ToolType.PICKAXE, 0, SoundType.STONE, true, true)) {}, Core.ItemGroups.TAB_OUTER_DECO);
        CHAIN = registerBlockWithModel("innerdeco/chain", () -> new Chain(getP(Material.IRON, 0.3f,0.4f,ToolType.PICKAXE, 0, SoundType.METAL, false, true)) {}, Core.ItemGroups.TAB_INNER_DECO);
        LEAD_WALL = registerBlockWithModel("innerdeco/lead_wall", () -> new LeadWall(getP(Material.IRON, 2.8f,8.6f,ToolType.PICKAXE, 2, SoundType.STONE, true, true), 1F) {}, Core.ItemGroups.TAB_INNER_DECO);
        THIN_LEAD_WALL = registerBlockWithModel("innerdeco/thin_lead_wall", () -> new LeadWall(getP(Material.IRON, 1.6f,6.6f,ToolType.PICKAXE, 2, SoundType.STONE, true, true), 1F) {}, Core.ItemGroups.TAB_INNER_DECO);
        RUSTY_BARS = registerBlockWithModel("innerdeco/rusty_bars", () -> new RustyBars(getP(Material.IRON, 0.95f,1.1f, ToolType.PICKAXE, 1, SoundType.METAL, true, true)), Core.ItemGroups.TAB_INNER_DECO);
        LADDER_1 = registerBlockWithModel("innerdeco/ladder_1", () -> new BlockLadder(getP(Material.IRON, 0.8f,1.2f, ToolType.PICKAXE, 1, SoundType.METAL, true, true)), Core.ItemGroups.TAB_INNER_DECO);
        LADDER_2 = registerBlockWithModel("innerdeco/ladder_2", () -> new BlockLadder(getP(Material.IRON, 0.8f,1.2f, ToolType.PICKAXE, 1, SoundType.METAL, true, true)), Core.ItemGroups.TAB_INNER_DECO);
        VENT_PIPE = registerBlockWithModel("innerdeco/pipes/ventpipe", () -> new VentPipe(getP(Material.IRON, 0.5f,0.8f, ToolType.PICKAXE, 1, SoundType.METAL, true, true)), Core.ItemGroups.TAB_INNER_DECO);
        VENT_PIPE_BASE = registerBlockWithModel("innerdeco/pipes/ventpipe_base", () -> new VentPipeBase(getP(Material.IRON, 1.5f,2.8f, ToolType.PICKAXE, 1, SoundType.METAL, true, true)), Core.ItemGroups.TAB_INNER_DECO);

        //phys devices
        EM_METER = registerBlockWithModel("innerdeco/devices/phys/em_meter", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        A_METER = registerBlockWithModel("innerdeco/devices/phys/a_meter", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        P_METER = registerBlockWithModel("innerdeco/devices/phys/p_meter", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        OM_METER = registerBlockWithModel("innerdeco/devices/phys/om_meter", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        V_METER = registerBlockWithModel("innerdeco/devices/phys/v_meter", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        W_METER = registerBlockWithModel("innerdeco/devices/phys/w_meter", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        K_METER = registerBlockWithModel("innerdeco/devices/phys/k_meter", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        M_METER = registerBlockWithModel("innerdeco/devices/phys/m_meter", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        FREQUE_METER = registerBlockWithModel("innerdeco/devices/phys/freque_meter", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        ASSIGNER_BLOCK = registerBlockWithModel("innerdeco/devices/phys/assigner_block", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        OSCILLOSCOPE = registerBlockWithModel("innerdeco/devices/phys/oscilloscope", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        CONTROLLER = registerBlockWithModel("innerdeco/devices/phys/controller", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        GEIGER_STATIC = registerBlockWithModel("innerdeco/devices/phys/geiger_static", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        FREQUE_ANALISATOR = registerBlockWithModel("innerdeco/devices/phys/freque_analisator", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        AUTOWRITER = registerBlockWithModel("innerdeco/devices/phys/autowriter", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        STANDART_SIGNAL_GEN = registerBlockWithModel("innerdeco/devices/phys/standart_signal_gen", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        PULT = registerBlockWithModel("innerdeco/devices/pult", () -> new ControlBlock(1F), Core.ItemGroups.TAB_INNER_DECO);
        E_CONVERTER = registerBlockWithModel("innerdeco/devices/phys/e_converter", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        E_TESTER = registerBlockWithModel("innerdeco/devices/phys/e_tester", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        SOUND_POWER_AMPLIFIER = registerBlockWithModel("innerdeco/devices/phys/sound_power_amplifier", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        COULOMETRIC_INTEGRATOR = registerBlockWithModel("innerdeco/devices/phys/coulometric_integrator", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        COULOMETRIC_INTEGRATOR2 = registerBlockWithModel("innerdeco/devices/phys/coulometric_integrator2", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        WELDING_MACHINE = registerBlockWithModel("innerdeco/devices/welding_machine", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        WAVEMETER = registerBlockWithModel("innerdeco/devices/phys/wavemeter", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        ACCELERATOR_RINGS = registerBlockWithModel("innerdeco/accelerator/accl_rings", () -> new BlockSideDevice(1F ), Core.ItemGroups.TAB_INNER_DECO);
        ACCELERATOR_RINGS_CORNER_LEFT = registerBlockWithModel("innerdeco/accelerator/accl_rings_corner_left", () -> new BlockSideDevice( 1F), Core.ItemGroups.TAB_INNER_DECO);
        ACCELERATOR_RINGS_CORNER_RIGHT = registerBlockWithModel("innerdeco/accelerator/accl_rings_corner_right", () -> new BlockSideDevice( 1F), Core.ItemGroups.TAB_INNER_DECO);
        ACCELERATOR_STAND = registerBlockWithModel("innerdeco/accelerator/accelerator_stand", () -> new BlockSideDevice( 1F), Core.ItemGroups.TAB_INNER_DECO);
        ACCELERATOR = registerBlockWithModel("innerdeco/accelerator/accelerator", () -> new BlockSideDevice( 0.6F), Core.ItemGroups.TAB_INNER_DECO);
        ACCELERATOR_CALC_BLOCK = registerBlockWithModel("innerdeco/accelerator/accl_calc_block", () -> new BlockSideDevice( 0.6F), Core.ItemGroups.TAB_INNER_DECO);
        ACCELERATOR_RINGS_END = registerBlockWithModel("innerdeco/accelerator/accl_rings_end", () -> new BlockSideDevice( 0.6F), Core.ItemGroups.TAB_INNER_DECO);
        CONTROL_PANEL_UP = registerBlockWithModel("innerdeco/control_panel/cp_up", () -> new ControlTable(getP(Material.IRON, 1.3f,2.8f, ToolType.PICKAXE, 1, SoundType.METAL, true, true)), Core.ItemGroups.TAB_INNER_DECO);
        CONTROL_PANEL_DOWN = registerBlockWithModel("innerdeco/control_panel/cp_down", () -> new ControlTable(getP(Material.IRON, 1.3f,2.8f, ToolType.PICKAXE, 1, SoundType.METAL, true, true)), Core.ItemGroups.TAB_INNER_DECO);
        CLOCK_ELECTRONICA = registerBlockWithModel("innerdeco/devices/clock_electronika", () -> new ElectronicaClock(getP(Material.WOOD, 0.4f,1.1f,ToolType.AXE, 0, SoundType.METAL, true, true)), Core.ItemGroups.TAB_INNER_DECO);
        TRANSFORMATOR_COIL = registerBlockWithModel("innerdeco/devices/phys/coil", () -> new Coil(getP(Material.WOOD, 0.4f,1.1f,ToolType.AXE, 0, SoundType.METAL, true, true)), Core.ItemGroups.TAB_INNER_DECO);
        TRANSFORMATOR_BASE = registerBlockWithModel("innerdeco/devices/phys/transformator_base", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        TRANSFORMATOR_ISOLATORS = registerBlockWithModel("innerdeco/devices/phys/transformator_isolators", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        RADIOSTATION = registerBlockWithModel("innerdeco/devices/phys/radiostation", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        AUDIORECORDER = registerBlockWithModel("innerdeco/devices/phys/audiorecorder", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        AUDIORECORDER2 = registerBlockWithModel("innerdeco/devices/phys/audiorecorder2", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);

        SOLDERING_STATION = registerBlockWithModel("innerdeco/devices/phys/soldering_station", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);
        TELEGRAPH = registerBlockWithModel("innerdeco/devices/telegraph", () -> new PhysDevices(1F), Core.ItemGroups.TAB_INNER_DECO);

        //mech
        CRUSHER = registerBlockWithModel("mech/crusher", () -> new BlockCrusher(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        AFFINAGE_FACTORY = registerBlockWithModel("mech/affinage", () -> new AffinageFactory(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        ENERGY_GENERATOR = registerBlockWithModel("mech/diesel_generator", () -> new DieselGenerator(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid(), BOX), Core.ItemGroups.TAB_INNER_DECO);
        DIESEL_TANK = registerBlockWithModel("mech/diesel_tank", () -> new DieselTank(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);
        DIESEL_E_GENERATOR = registerBlockWithModel("mech/diesel_electro_generator", () -> new DieselElectroGenerator(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid()), Core.ItemGroups.TAB_INNER_DECO);


        AbstractBlock.Properties TRIM_STONE_PROPERTIES = getP(Material.ROCK, 5.5f,15f,ToolType.PICKAXE, 2, SoundType.STONE, true, true);
        //trim_stone
        TRIM_TILE_1 = registerBlock("structural/trim_tile_1", () -> new TilledBlock(),Core.ItemGroups.TAB_MAIN);
        TRIM_TILE_1_BR = registerBlock("structural/trim_tile_1_br", () -> new TilledBlock(),Core.ItemGroups.TAB_MAIN);
        TRIM_STONE_1 = registerBlock("structural/trim_stone_1", () -> new BaseBlock(TRIM_STONE_PROPERTIES),Core.ItemGroups.TAB_MAIN);
        TRIM_STONE_2 = registerBlock("structural/trim_stone_2", () -> new BaseBlock(TRIM_STONE_PROPERTIES),Core.ItemGroups.TAB_MAIN);
        TRIM_STONE_3 = registerBlock("structural/trim_stone_3", () -> new BaseBlock(TRIM_STONE_PROPERTIES),Core.ItemGroups.TAB_MAIN);
        TRIM_STONE_4 = registerBlock("structural/trim_stone_4", () -> new BaseBlock(TRIM_STONE_PROPERTIES),Core.ItemGroups.TAB_MAIN);
        PARQUET_BLOCK = registerBlock("structural/parquet_block", () -> new WoodBlock(),Core.ItemGroups.TAB_MAIN);
        TRIM_TILE_RED = registerBlock("structural/trim_tile_red", () -> new TilledBlock(),Core.ItemGroups.TAB_MAIN);
        TRIM_TILE_BLUE = registerBlock("structural/trim_tile_blue", () -> new TilledBlock(),Core.ItemGroups.TAB_MAIN);
        CONCRETE_PLATE = registerBlock("structural/concrete_plate", () -> new BaseBlock(TRIM_STONE_PROPERTIES),Core.ItemGroups.TAB_MAIN);
        CONTAINMENT_BLOCK = registerBlock("structural/containment_block", () -> new MetalBlock(),Core.ItemGroups.TAB_MAIN);
        TRIM_METAL_1 = registerBlock("structural/trim_metal_1", () -> new MetalBlock(),Core.ItemGroups.TAB_MAIN);
        TRIM_METAL_2 = registerBlock("structural/trim_metal_2", () -> new MetalBlock(),Core.ItemGroups.TAB_MAIN);
        LEADCERAMIC_TILE = registerBlock("structural/leadceramic_tile", () -> new TilledBlock(),Core.ItemGroups.TAB_MAIN);


        AbstractBlock.Properties WINDPROOF_PROPERTIES = getP(Material.ROCK, 2,1,ToolType.PICKAXE, 2, SoundType.STONE, true, true);
        //windproof
        WINDPROOF_BETON_GRAY = registerBlockWithModelColored("outerdeco/streetdeco/windproof_beton_gray", () -> new WindProofPanel(WINDPROOF_PROPERTIES, 1, "info.gray"), Core.ItemGroups.TAB_MAIN);
        WINDPROOF_BETON_RED = registerBlockWithModelColored("outerdeco/streetdeco/windproof_beton_red", () -> new WindProofPanel(WINDPROOF_PROPERTIES, 1, "info.red"), Core.ItemGroups.TAB_MAIN);
        WINDPROOF_BETON_GREEN = registerBlockWithModelColored("outerdeco/streetdeco/windproof_beton_green", () -> new WindProofPanel(WINDPROOF_PROPERTIES, 1, "info.green"), Core.ItemGroups.TAB_MAIN);
        WINDPROOF_BETON_ORANGE = registerBlockWithModelColored("outerdeco/streetdeco/windproof_beton_orange", () -> new WindProofPanel(WINDPROOF_PROPERTIES, 1, "info.orange"), Core.ItemGroups.TAB_MAIN);
        WINDPROOF_BETON_BLUE = registerBlockWithModelColored("outerdeco/streetdeco/windproof_beton_blue", () -> new WindProofPanel(WINDPROOF_PROPERTIES, 1, "info.blue"), Core.ItemGroups.TAB_MAIN);
        WINDPROOF_BETON_YELLOW = registerBlockWithModelColored("outerdeco/streetdeco/windproof_beton_yellow", () -> new WindProofPanel(WINDPROOF_PROPERTIES, 1, "info.yellow"), Core.ItemGroups.TAB_MAIN);
        WINDPROOF_BETON_BEIGE = registerBlockWithModelColored("outerdeco/streetdeco/windproof_beton_beige", () -> new WindProofPanel(WINDPROOF_PROPERTIES, 1, "info.beige"), Core.ItemGroups.TAB_MAIN);
        WINDPROOF_BETON_BEIGE2 = registerBlockWithModelColored("outerdeco/streetdeco/windproof_beton_beige2", () -> new WindProofPanel(WINDPROOF_PROPERTIES, 1, "info.beige2"), Core.ItemGroups.TAB_MAIN);
        WINDPROOF_BETON_WHITE = registerBlockWithModelColored("outerdeco/streetdeco/windproof_beton_white", () -> new WindProofPanel(WINDPROOF_PROPERTIES, 1, "info.white"), Core.ItemGroups.TAB_MAIN);

       //ceramic_glass
        CERAMIC_GLASS_BLUE = registerBlock("structural/ceramic_glass_blue", () -> new GlassBlock(),Core.ItemGroups.TAB_MAIN);
        CERAMIC_GLASS_GREEN = registerBlock("structural/ceramic_glass_green", () -> new GlassBlock(),Core.ItemGroups.TAB_MAIN);
        CERAMIC_GLASS_BROWN = registerBlock("structural/ceramic_glass_brown", () -> new GlassBlock(),Core.ItemGroups.TAB_MAIN);

        //linos
        LINO_1 = registerBlock("lino/lino1", () -> new LinoBlock(), Core.ItemGroups.TAB_MAIN);
        LINO_2 = registerBlock("lino/lino2", () -> new LinoBlock(),Core.ItemGroups.TAB_MAIN);
        LINO_3 = registerBlock("lino/lino3", () -> new LinoBlock(),Core.ItemGroups.TAB_MAIN);
        LINO_4 = registerBlock("lino/lino4", () -> new LinoBlock(),Core.ItemGroups.TAB_MAIN);
        LINO_5 = registerBlock("lino/lino5", () -> new LinoBlock(),Core.ItemGroups.TAB_MAIN);
        LINO_6 = registerBlock("lino/lino6", () -> new LinoBlock(),Core.ItemGroups.TAB_MAIN);
        LINO_7 = registerBlock("lino/lino7", () -> new LinoBlock(),Core.ItemGroups.TAB_MAIN);
        LINO_8 = registerBlock("lino/lino8", () -> new LinoBlock(),Core.ItemGroups.TAB_MAIN);

        AbstractBlock.Properties CONCRETE_RAILING_PROPERTIES = getP(Material.ROCK, 2,1,ToolType.PICKAXE, 2, SoundType.STONE, true, true);
        //concrete_railing
        CONCRETE_RAILING_ORANGE = registerBlockWithModel("structural/concrete_railing_orange", () -> new BlockRotatedAxisCustomModelInfo(CONCRETE_RAILING_PROPERTIES, "info.orange", 0.5F), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_WHITE = registerBlockWithModel("structural/concrete_railing_white", () -> new BlockRotatedAxisCustomModelInfo(CONCRETE_RAILING_PROPERTIES, "info.white", 0.5F), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_GRAY = registerBlockWithModel("structural/concrete_railing_gray", () -> new BlockRotatedAxisCustomModelInfo(CONCRETE_RAILING_PROPERTIES, "info.gray", 0.5F), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_GREEN = registerBlockWithModel("structural/concrete_railing_green", () -> new BlockRotatedAxisCustomModelInfo(CONCRETE_RAILING_PROPERTIES, "info.green", 0.5F), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_RED = registerBlockWithModel("structural/concrete_railing_red", () -> new BlockRotatedAxisCustomModelInfo(CONCRETE_RAILING_PROPERTIES, "info.red", 0.5F), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_BLUE = registerBlockWithModel("structural/concrete_railing_blue", () -> new BlockRotatedAxisCustomModelInfo(CONCRETE_RAILING_PROPERTIES, "info.blue", 0.5F), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_BEIGE = registerBlockWithModel("structural/concrete_railing_beige", () -> new BlockRotatedAxisCustomModelInfo(CONCRETE_RAILING_PROPERTIES, "info.beige", 0.5F), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_BEIGE2 = registerBlockWithModel("structural/concrete_railing_beige2", () -> new BlockRotatedAxisCustomModelInfo(CONCRETE_RAILING_PROPERTIES, "info.beige2", 0.5F), Core.ItemGroups.TAB_MAIN);
        CONCRETE_RAILING_YELLOW = registerBlockWithModel("structural/concrete_railing_yellow", () -> new BlockRotatedAxisCustomModelInfo(CONCRETE_RAILING_PROPERTIES, "info.yellow", 0.5F), Core.ItemGroups.TAB_MAIN);

        //beams
        IRON_BEAM_CONCRETE = registerBlockWithModel("structural/iron_beam_concrete", () -> new BlockRotatedAxisCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid(), 0.5F), Core.ItemGroups.TAB_MAIN);
        IRON_BEAM = registerBlockWithModel("structural/iron_beam", () -> new BlockRotatedAxisCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid(), 0.5F), Core.ItemGroups.TAB_MAIN);
        IRON_BEAM_THIN = registerBlockWithModel("structural/iron_beam_thin", () -> new BlockRotatedAxisCustomModel(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3f,8f).notSolid(), 0.5F), Core.ItemGroups.TAB_MAIN);
        //concrete
        CONCRETE_ORANGE = registerBlock("concrete/concrete_orange", () -> new FerroconcreteBlock("info.orange"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_BLUE = registerBlock("concrete/concrete_blue", () -> new FerroconcreteBlock("info.blue"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_RED = registerBlock("concrete/concrete_red", () -> new FerroconcreteBlock( "info.red"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_YELLOW = registerBlock("concrete/concrete_yellow", () -> new FerroconcreteBlock( "info.yellow"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_WHITE = registerBlock("concrete/concrete_white", () -> new FerroconcreteBlock( "info.white"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_BEIGE = registerBlock("concrete/concrete_beige", () -> new FerroconcreteBlock( "info.beige"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_GREEN = registerBlock("concrete/concrete_green", () -> new FerroconcreteBlock( "info.green"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_GRAY = registerBlock("concrete/concrete_gray", () -> new FerroconcreteBlock( "info.gray"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_BEIGE2 = registerBlock("concrete/concrete_beige2", () -> new FerroconcreteBlock( "info.beige2"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_BLUE_BR = registerBlock("concrete/concrete_blue_br", () -> new FerroconcreteBlock("info.blue"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_ORANGE_BR = registerBlock("concrete/concrete_orange_br", () -> new FerroconcreteBlock("info.orange"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_RED_BR = registerBlock("concrete/concrete_red_br", () -> new FerroconcreteBlock("info.red"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_YELLOW_BR = registerBlock("concrete/concrete_yellow_br", () -> new FerroconcreteBlock( "info.yellow"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_WHITE_BR = registerBlock("concrete/concrete_white_br", () -> new FerroconcreteBlock( "info.white"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_BEIGE_BR = registerBlock("concrete/concrete_beige_br", () -> new FerroconcreteBlock( "info.beige"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_GREEN_BR = registerBlock("concrete/concrete_green_br", () -> new FerroconcreteBlock( "info.green"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_GRAY_BR = registerBlock("concrete/concrete_gray_br", () -> new FerroconcreteBlock( "info.gray"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_BEIGE2_BR = registerBlock("concrete/concrete_beige2_br", () -> new FerroconcreteBlock("info.beige2"),Core.ItemGroups.TAB_MAIN);
        //stairs
        CONCRETE_STAIRS_GRAY = registerBlock("stairs/concrete_gray_stairs", () -> new BaseStairs(CONCRETE_GRAY.get().getDefaultState(), "info.gray"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_STAIRS_GREEN = registerBlock("stairs/concrete_green_stairs", () -> new BaseStairs(CONCRETE_GREEN.get().getDefaultState(),  "info.green"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_STAIRS_BLUE = registerBlock("stairs/concrete_blue_stairs", () -> new BaseStairs(CONCRETE_BLUE.get().getDefaultState(), "info.blue"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_STAIRS_BEIGE = registerBlock("stairs/concrete_beige_stairs", () -> new BaseStairs(CONCRETE_BEIGE.get().getDefaultState(),  "info.beige"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_STAIRS_BEIGE2 = registerBlock("stairs/concrete_beige2_stairs", () -> new BaseStairs(CONCRETE_BEIGE2.get().getDefaultState(),  "info.beige2"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_STAIRS_RED = registerBlock("stairs/concrete_red_stairs", () -> new BaseStairs(CONCRETE_RED.get().getDefaultState(),  "info.red"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_STAIRS_ORANGE = registerBlock("stairs/concrete_orange_stairs", () -> new BaseStairs(CONCRETE_ORANGE.get().getDefaultState(),  "info.orange"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_STAIRS_YELLOW = registerBlock("stairs/concrete_yellow_stairs", () -> new BaseStairs(CONCRETE_YELLOW.get().getDefaultState(),  "info.yellow"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_STAIRS_WHITE = registerBlock("stairs/concrete_white_stairs", () -> new BaseStairs(CONCRETE_WHITE.get().getDefaultState(), "info.white"),Core.ItemGroups.TAB_MAIN);
        //slabs
        CONCRETE_SLAB_GRAY = registerBlock("slabs/concrete_gray_slab", () -> new BaseSlab( "info.gray"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_SLAB_GREEN = registerBlock("slabs/concrete_green_slab", () -> new BaseSlab( "info.green"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_SLAB_BLUE = registerBlock("slabs/concrete_blue_slab", () -> new BaseSlab("info.blue"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_SLAB_BEIGE = registerBlock("slabs/concrete_beige_slab", () -> new BaseSlab("info.beige"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_SLAB_BEIGE2 = registerBlock("slabs/concrete_beige2_slab", () -> new BaseSlab("info.beige2"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_SLAB_RED = registerBlock("slabs/concrete_red_slab", () -> new BaseSlab("info.red"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_SLAB_ORANGE = registerBlock("slabs/concrete_orange_slab", () -> new BaseSlab("info.orange"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_SLAB_YELLOW = registerBlock("slabs/concrete_yellow_slab", () -> new BaseSlab("info.yellow"),Core.ItemGroups.TAB_MAIN);
        CONCRETE_SLAB_WHITE = registerBlock("slabs/concrete_white_slab", () -> new BaseSlab("info.white"),Core.ItemGroups.TAB_MAIN);

        //panels
        PANEL_CONCRETE_CORNER = registerNDBlock("structural/panel_concrete_corner", () -> new PanelBlockCorner(), Core.ItemGroups.TAB_MAIN);
        PANEL_CONCRETE_SIDE = registerNDBlock("structural/panel_concrete_side", () -> new PanelBlockSide(), Core.ItemGroups.TAB_MAIN);
        PANEL_CONCRETE = registerBlock("structural/panel_concrete", () -> new BaseBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        PANEL_CONCRETE_YELLOW_CORNER = registerNDBlock("structural/panel_concrete_yellow_corner", () -> new PanelBlockCorner(), Core.ItemGroups.TAB_MAIN);
        PANEL_CONCRETE_YELLOW_SIDE = registerNDBlock("structural/panel_concrete_yellow_side", () -> new PanelBlockSide(), Core.ItemGroups.TAB_MAIN);
        PANEL_CONCRETE_YELLOW = registerBlock("structural/panel_concrete_yellow", () -> new BaseBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);
        PANEL_TILE_CORNER = registerNDBlock("structural/panel_tile_corner", () -> new PanelBlockCorner(), Core.ItemGroups.TAB_MAIN);
        PANEL_TILE_SIDE = registerNDBlock("structural/panel_tile_side", () -> new PanelBlockSide(), Core.ItemGroups.TAB_MAIN);
        PANEL_TILE = registerBlock("structural/panel_tile", () -> new BaseBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f)),Core.ItemGroups.TAB_MAIN);

        //horizontal_tiles
        HORIZ_TILE_BLUE = registerBlock("horiztile/horiz_tile_blue", () -> new TilledBlock( "info.horiz_tile_blue"),Core.ItemGroups.TAB_MAIN);
        HORIZ_TILE_BLUE_BR = registerBlock("horiztile/horiz_tile_blue_br", () -> new TilledBlock( "info.horiz_tile_blue_br"),Core.ItemGroups.TAB_MAIN);
        HORIZ_TILE_WHITE = registerBlock("horiztile/horiz_tile_white", () -> new TilledBlock( "info.horiz_tile_white"),Core.ItemGroups.TAB_MAIN);
        HORIZ_TILE_WHITE_BR = registerBlock("horiztile/horiz_tile_white_br", () -> new TilledBlock( "info.horiz_tile_white_br"),Core.ItemGroups.TAB_MAIN);
        HORIZ_TILE_DARK_BLUE = registerBlock("horiztile/horiz_tile_dark_blue", () -> new TilledBlock( "info.horiz_tile_dark_blue"),Core.ItemGroups.TAB_MAIN);
        HORIZ_TILE_DARK_BLUE_BR = registerBlock("horiztile/horiz_tile_dark_blue_br", () -> new TilledBlock( "info.horiz_tile_dark_blue_br"),Core.ItemGroups.TAB_MAIN);

        //small_tiles
        SMALL_TILE_BLUE = registerBlock("smalltile/small_tile_blue", () -> new TilledBlock( "info.small_tile_blue"),Core.ItemGroups.TAB_MAIN);
        SMALL_TILE_BLUE_BR = registerBlock("smalltile/small_tile_blue_br", () -> new TilledBlock( "info.small_tile_blue_br"),Core.ItemGroups.TAB_MAIN);
        SMALL_TILE_WHITE = registerBlock("smalltile/small_tile_white", () -> new TilledBlock( "info.small_tile_white"),Core.ItemGroups.TAB_MAIN);
        SMALL_TILE_WHITE_BR = registerBlock("smalltile/small_tile_white_br", () -> new TilledBlock( "info.small_tile_white_br"),Core.ItemGroups.TAB_MAIN);
        SMALL_TILE_RED = registerBlock("smalltile/small_tile_red", () -> new TilledBlock( "info.small_tile_red"),Core.ItemGroups.TAB_MAIN);
        SMALL_TILE_RED_BR = registerBlock("smalltile/small_tile_red_br", () -> new TilledBlock( "info.small_tile_red_br"),Core.ItemGroups.TAB_MAIN);
        SMALL_TILE_YELLOW = registerBlock("smalltile/small_tile_yellow", () -> new TilledBlock( "info.small_tile_yellow"),Core.ItemGroups.TAB_MAIN);
        SMALL_TILE_YELLOW_BR = registerBlock("smalltile/small_tile_yellow_br", () -> new TilledBlock( "info.small_tile_yellow_br"),Core.ItemGroups.TAB_MAIN);

        //mosaic_tile
        TILE_MOSAIC_1 = registerBlock("mosaictile/tile_mosaic_1", () -> new TilledBlock("info.mosaic_tile1"),Core.ItemGroups.TAB_MAIN);
        TILE_MOSAIC_2 = registerBlock("mosaictile/tile_mosaic_2", () -> new TilledBlock("info.mosaic_tile2"),Core.ItemGroups.TAB_MAIN);

        //quad_tiles
        TILE_QUAD_GRAY = registerBlock("quadtile/tile_quad_gray", () -> new TilledBlock( "info.quadtile_gray"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_WHITE = registerBlock("quadtile/tile_quad_white", () -> new TilledBlock( "info.quadtile_white"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_BLUE = registerBlock("quadtile/tile_quad_blue", () -> new TilledBlock( "info.quadtile_blue"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_1_BR = registerBlock("quadtile/tile_quad_1_br", () -> new TilledBlock( "info.quadtile_1_br"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_2_BR = registerBlock("quadtile/tile_quad_2_br", () -> new TilledBlock( "info.quadtile_2_br"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_1 = registerBlock("quadtile/tile_quad_1", () -> new TilledBlock( "info.quadtile_1"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_2 = registerBlock("quadtile/tile_quad_2", () -> new TilledBlock( "info.quadtile_2"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_4 = registerBlock("quadtile/tile_quad_4", () -> new TilledBlock( "info.quadtile_4"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_5 = registerBlock("quadtile/tile_quad_5", () -> new TilledBlock( "info.quadtile_5"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_3 = registerBlock("quadtile/tile_quad_3", () -> new TilledBlock( "info.quadtile_3"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_3_BR = registerBlock("quadtile/tile_quad_3_br", () -> new TilledBlock( "info.quadtile_3_br"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_5_BR = registerBlock("quadtile/tile_quad_5_br", () -> new TilledBlock( "info.quadtile_5_br"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_5_BRf = registerBlock("quadtile/tile_quad_5_brf", () -> new TilledBlock( "info.quadtile_5_brf"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_CONCRETE = registerBlock("quadtile/tile_quad_concrete", () -> new TilledBlock( "info.quadtile_concrete"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_BLUE_BR = registerBlock("quadtile/tile_quad_blue_br", () -> new TilledBlock( "info.quadtile_blue_br"),Core.ItemGroups.TAB_MAIN);
        TILE_QUAD_WHITE_BR = registerBlock("quadtile/tile_quad_white_br", () -> new TilledBlock( "info.quadtile_white_br"),Core.ItemGroups.TAB_MAIN);

        //rest_tiles
        TILE_REST_DARK_BLUE = registerBlock("resttile/tile_rest_dark_blue", () -> new TilledBlock( "info.tile_rest_dark_blue"),Core.ItemGroups.TAB_MAIN);
        TILE_REST_DARK_BLUE_BR = registerBlock("resttile/tile_rest_dark_blue_br", () -> new TilledBlock( "info.tile_rest_dark_blue_br"),Core.ItemGroups.TAB_MAIN);
        TILE_REST_BLUE = registerBlock("resttile/tile_rest_blue", () -> new TilledBlock( "info.tile_rest_blue"),Core.ItemGroups.TAB_MAIN);
        TILE_REST_BLUE_BR = registerBlock("resttile/tile_rest_blue_br", () -> new TilledBlock( "info.tile_rest_blue_br"),Core.ItemGroups.TAB_MAIN);
        TILE_REST_BLACK = registerBlock("resttile/tile_rest_black", () -> new TilledBlock( "info.tile_rest_black"),Core.ItemGroups.TAB_MAIN);
        TILE_REST_BLACK_BR = registerBlock("resttile/tile_rest_black_br", () -> new TilledBlock( "info.tile_rest_black_br"),Core.ItemGroups.TAB_MAIN);
        TILE_REST_BROWN = registerBlock("resttile/tile_rest_brown", () -> new TilledBlock( "info.tile_rest_brown"),Core.ItemGroups.TAB_MAIN);
        TILE_REST_BROWN_BR = registerBlock("resttile/tile_rest_brown_br", () -> new TilledBlock( "info.tile_rest_brown_br"),Core.ItemGroups.TAB_MAIN);
        TILE_REST_WHITE = registerBlock("resttile/tile_rest_white", () -> new TilledBlock( "info.tile_rest_white"),Core.ItemGroups.TAB_MAIN);
        TILE_REST_WHITE_BR = registerBlock("resttile/tile_rest_white_br", () -> new TilledBlock( "info.tile_rest_white_br"),Core.ItemGroups.TAB_MAIN);

        //reg tiles
        REGULAR_BROWN_TILE = registerBlock("regtile/tile_reg_brown", () -> new TilledBlock( "info.tile_reg_brown"),Core.ItemGroups.TAB_MAIN);
        REGULAR_BROWN_TILE_BR = registerBlock("regtile/tile_reg_brown_br", () -> new TilledBlock( "info.tile_reg_brown_br"),Core.ItemGroups.TAB_MAIN);
        REGULAR_LIL_TILE = registerBlock("regtile/tile_reg_lil", () -> new TilledBlock( "info.tile_reg_lil"),Core.ItemGroups.TAB_MAIN);
        REGULAR_LIL_TILE_BR = registerBlock("regtile/tile_reg_lil_br", () -> new TilledBlock( "info.tile_reg_lil_br"),Core.ItemGroups.TAB_MAIN);
        REGULAR_AM_TILE = registerBlock("regtile/tile_reg_am", () -> new TilledBlock( "info.tile_reg_am"),Core.ItemGroups.TAB_MAIN);
        REGULAR_AM_TILE_BR = registerBlock("regtile/tile_reg_am_br", () -> new TilledBlock( "info.tile_reg_am_br"),Core.ItemGroups.TAB_MAIN);

        //bricks
        RED_BRICKS_BR = registerBlock("brick/red_bricks_br", () -> new BrickBlock(),Core.ItemGroups.TAB_MAIN);
        RED_BRICKS = registerBlock("brick/red_bricks", () -> new BrickBlock(),Core.ItemGroups.TAB_MAIN);
        SHORT_BRICKS = registerBlock("brick/short_bricks", () -> new BrickBlock(),Core.ItemGroups.TAB_MAIN);
        BRICKS_BR = registerBlock("brick/soviet_bricks_br", () -> new BrickBlock(),Core.ItemGroups.TAB_MAIN);
        BRICKS = registerBlock("brick/soviet_bricks", () -> new BrickBlock(),Core.ItemGroups.TAB_MAIN);
        BRICKS_WITH_WHITE = registerBlock("brick/soviet_bricks_with_white", () -> new BrickBlock(),Core.ItemGroups.TAB_MAIN);
        WALL_BRICKS = registerBlock("brick/light_bricks2", () -> new BrickBlock(),Core.ItemGroups.TAB_MAIN);
        LIGHT_BRICKS = registerBlock("brick/light_bricks", () -> new BrickBlock(),Core.ItemGroups.TAB_MAIN);
        WALL_BRICKS_BR = registerBlock("brick/light_bricks2_br", () -> new BrickBlock(),Core.ItemGroups.TAB_MAIN);
        LIGHT_BRICKS_BR = registerBlock("brick/light_bricks_br", () -> new BrickBlock(),Core.ItemGroups.TAB_MAIN);
        YELLOW_BRICKS_1_BR = registerBlock("brick/yellow_bricks_1_br", () -> new BrickBlock(),Core.ItemGroups.TAB_MAIN);
        YELLOW_BRICKS_1 = registerBlock("brick/yellow_bricks_1", () -> new BrickBlock(),Core.ItemGroups.TAB_MAIN);
        YELLOW_BRICKS_2_BR = registerBlock("brick/yellow_bricks_2_br", () -> new BrickBlock(),Core.ItemGroups.TAB_MAIN);
        YELLOW_BRICKS_2 = registerBlock("brick/yellow_bricks_2", () -> new BrickBlock(),Core.ItemGroups.TAB_MAIN);
        YELLOW_BRICKS_3_BR = registerBlock("brick/yellow_bricks_3_br", () -> new BrickBlock(),Core.ItemGroups.TAB_MAIN);
        YELLOW_BRICKS_3 = registerBlock("brick/yellow_bricks_3", () -> new BrickBlock(),Core.ItemGroups.TAB_MAIN);
        WHITE_BRICKS = registerBlock("brick/white_bricks", () -> new BrickBlock(),Core.ItemGroups.TAB_MAIN);
        WHITE_BRICKS_BR = registerBlock("brick/white_bricks_br", () -> new BrickBlock(),Core.ItemGroups.TAB_MAIN);
        SMALL_BRICKS = registerBlock("brick/small_bricks", () -> new BrickBlock(),Core.ItemGroups.TAB_MAIN);

    }

    public static AbstractBlock.Properties getP(Material mat, float hd, float rs, @Nullable ToolType ht, int hl, SoundType st, boolean reqTool, boolean notSolid) {
        AbstractBlock.Properties BASE = AbstractBlock.Properties.create(mat).hardnessAndResistance(hd,rs).harvestTool(ht).harvestLevel(hl).sound(st);
       if(reqTool) {
           BASE = BASE.setRequiresTool();
       }
       if(notSolid) {
           BASE = BASE.notSolid();
       }
        return BASE;
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        BLOCKS_CUSTOM_MODELS_COLORED.register(eventBus);
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
    private static <T extends Block> RegistryObject<T> registerBlockWithModelColored(String name, Supplier<T> block, ItemGroup group) {
        RegistryObject<T> toReturn = BLOCKS_CUSTOM_MODELS_COLORED.register(name, block);
        registerBlockItem(name, toReturn, group);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, ItemGroup group) {
        ITEM_BLOCKS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(group)));
    }
}
