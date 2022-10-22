package ru.tesmio.reg;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.tesmio.Core;
import ru.tesmio.blocks.BlockInfo;

import java.util.function.Supplier;

public class RegBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Core.MODID);
    public static final DeferredRegister<Item> ITEM_BLOCKS = DeferredRegister.create(ForgeRegistries.ITEMS, Core.MODID);


    public static RegistryObject<Block> CONCRETE_ORANGE,CONCRETE_ORANGE_BR,CONCRETE_BLUE,CONCRETE_RED,CONCRETE_GREEN,CONCRETE_GRAY,CONCRETE_BEIGE,CONCRETE_BEIGE2,CONCRETE_YELLOW,CONCRETE_WHITE,CONCRETE_BLUE_BR,CONCRETE_RED_BR,CONCRETE_GREEN_BR,CONCRETE_GRAY_BR,CONCRETE_BEIGE_BR,CONCRETE_BEIGE2_BR,CONCRETE_YELLOW_BR,CONCRETE_WHITE_BR, CONCRETE_CORNER_PANEL;
    public static RegistryObject<Block> TILE_QUAD_WHITE,TILE_QUAD_GRAY, TILE_QUAD_BLUE, TILE_QUAD_CONCRETE, TILE_QUAD_WHITE_BR, TILE_QUAD_BLUE_BR;
    public static RegistryObject<Block>  TILE_QUAD_3, TILE_QUAD_3_BR, TILE_QUAD_4, TILE_QUAD_5,TILE_QUAD_5_BR,TILE_QUAD_5_BRf;
    public static RegistryObject<Block> REGULAR_BROWN_TILE, REGULAR_BROWN_TILE_BR, REGULAR_AM_TILE, REGULAR_AM_TILE_BR, REGULAR_LIL_TILE, REGULAR_LIL_TILE_BR;

    public static RegistryObject<Block> TILE_REST_DARK_BLUE, TILE_REST_DARK_BLUE_BR,TILE_REST_BLUE, TILE_REST_BLUE_BR, TILE_REST_BROWN, TILE_REST_BROWN_BR, TILE_REST_WHITE, TILE_REST_WHITE_BR, TILE_REST_BLACK, TILE_REST_BLACK_BR;

    public static RegistryObject<Block> HORIZ_TILE_BLUE, HORIZ_TILE_BLUE_BR, HORIZ_TILE_WHITE, HORIZ_TILE_WHITE_BR, HORIZ_TILE_DARK_BLUE, HORIZ_TILE_DARK_BLUE_BR;
    public static RegistryObject<Block> SMALL_TILE_BLUE, SMALL_TILE_BLUE_BR, SMALL_TILE_WHITE, SMALL_TILE_WHITE_BR, SMALL_TILE_RED, SMALL_TILE_RED_BR, SMALL_TILE_YELLOW, SMALL_TILE_YELLOW_BR;
    public static RegistryObject<Block> TILE_QUAD_1, TILE_QUAD_1_BR, TILE_QUAD_2, TILE_QUAD_2_BR;
    public static RegistryObject<Block> SMALL_BRICKS, YELLOW_BRICKS_3_BR, YELLOW_BRICKS_3, YELLOW_BRICKS_2_BR, YELLOW_BRICKS_2,YELLOW_BRICKS_1_BR, YELLOW_BRICKS_1, WHITE_BRICKS_BR, WHITE_BRICKS,RED_BRICKS_BR, RED_BRICKS, BRICKS_WITH_WHITE, BRICKS, BRICKS_BR, WALL_BRICKS,WALL_BRICKS_BR, LIGHT_BRICKS, LIGHT_BRICKS_BR, SHORT_BRICKS;
    public static void init() {
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
        CONCRETE_CORNER_PANEL = registerBlock("concrete/beton_panel_ang_lu", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.beton_panel_ang_lu"));

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
        ITEM_BLOCKS.register(eventBus);
        init();
    }
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ITEM_BLOCKS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(Core.ItemGroups.TAB_MAIN)));
    }
}
