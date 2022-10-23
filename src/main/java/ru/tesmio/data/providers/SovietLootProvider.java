package ru.tesmio.data.providers;

import net.minecraft.data.DataGenerator;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;

public class SovietLootProvider extends BaseLootProvider {

    public SovietLootProvider(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        //concrete railing
        lootTables.put(RegBlocks.CONCRETE_RAILING_GRAY.get(), standartItemBlockBuilder("structural/concrete_railing_gray", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_RAILING_WHITE.get(), standartItemBlockBuilder("structural/concrete_railing_white", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_RAILING_BEIGE.get(), standartItemBlockBuilder("structural/concrete_railing_beige", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_RAILING_BEIGE2.get(), standartItemBlockBuilder("structural/concrete_railing_beige2", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_RAILING_RED.get(), standartItemBlockBuilder("structural/concrete_railing_red", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_RAILING_BLUE.get(), standartItemBlockBuilder("structural/concrete_railing_blue", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_RAILING_YELLOW.get(), standartItemBlockBuilder("structural/concrete_railing_yellow", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_RAILING_ORANGE.get(), standartItemBlockBuilder("structural/concrete_railing_orange", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_RAILING_GREEN.get(), standartItemBlockBuilder("structural/concrete_railing_green", RegItems.ARMATURES.get(),1));
        //lino
        lootTables.put(RegBlocks.LINO_1.get(), standartItemBlockBuilder("lino/lino1", RegItems.LINO.get(),3));
        lootTables.put(RegBlocks.LINO_2.get(), standartItemBlockBuilder("lino/lino2", RegItems.LINO.get(),3));
        lootTables.put(RegBlocks.LINO_3.get(), standartItemBlockBuilder("lino/lino3", RegItems.LINO.get(),3));
        lootTables.put(RegBlocks.LINO_4.get(), standartItemBlockBuilder("lino/lino4", RegItems.LINO.get(),3));
        lootTables.put(RegBlocks.LINO_5.get(), standartItemBlockBuilder("lino/lino5", RegItems.LINO.get(),3));
        lootTables.put(RegBlocks.LINO_6.get(), standartItemBlockBuilder("lino/lino6", RegItems.LINO.get(),3));
        lootTables.put(RegBlocks.LINO_7.get(), standartItemBlockBuilder("lino/lino7", RegItems.LINO.get(),3));
        lootTables.put(RegBlocks.LINO_8.get(), standartItemBlockBuilder("lino/lino8", RegItems.LINO.get(),3));
        //concrete
            lootTables.put(RegBlocks.CONCRETE_ORANGE.get(), standartItemBlockBuilder("concrete/concrete_orange", RegItems.ARMATURES.get(),1));
            lootTables.put(RegBlocks.CONCRETE_ORANGE_BR.get(), standartItemBlockBuilder("concrete/concrete_orange_br", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_BLUE.get(), standartItemBlockBuilder("concrete/concrete_blue", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_BLUE_BR.get(), standartItemBlockBuilder("concrete/concrete_blue_br", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_RED.get(), standartItemBlockBuilder("concrete/concrete_red", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_RED_BR.get(), standartItemBlockBuilder("concrete/concrete_red_br", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_WHITE.get(), standartItemBlockBuilder("concrete/concrete_white", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_WHITE_BR.get(), standartItemBlockBuilder("concrete/concrete_white_br", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_GREEN.get(), standartItemBlockBuilder("concrete/concrete_green", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_GREEN_BR.get(), standartItemBlockBuilder("concrete/concrete_green_br", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_YELLOW.get(), standartItemBlockBuilder("concrete/concrete_yellow", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_YELLOW_BR.get(), standartItemBlockBuilder("concrete/concrete_yellow_br", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_BEIGE.get(), standartItemBlockBuilder("concrete/concrete_beige", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_BEIGE_BR.get(), standartItemBlockBuilder("concrete/concrete_beige_br", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_BEIGE2.get(), standartItemBlockBuilder("concrete/concrete_beige2", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_BEIGE2_BR.get(), standartItemBlockBuilder("concrete/concrete_beige2_br", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_GRAY.get(), standartItemBlockBuilder("concrete/concrete_gray", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.CONCRETE_GRAY_BR.get(), standartItemBlockBuilder("concrete/concrete_gray_br", RegItems.ARMATURES.get(),1 ));
        //panels
        lootTables.put(RegBlocks.PANEL_CONCRETE_CORNER.get(), standartItemBlockBuilder("structural/panel_concrete_corner", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.PANEL_CONCRETE_SIDE.get(), standartItemBlockBuilder("structural/panel_concrete_side", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.PANEL_CONCRETE.get(), standartItemBlockBuilder("structural/panel_concrete", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.PANEL_CONCRETE_YELLOW_CORNER.get(), standartItemBlockBuilder("structural/panel_concrete_yellow_corner", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.PANEL_CONCRETE_YELLOW_SIDE.get(), standartItemBlockBuilder("structural/panel_concrete_yellow_side", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.PANEL_CONCRETE_YELLOW.get(), standartItemBlockBuilder("structural/panel_yellow_concrete", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.PANEL_TILE_CORNER.get(), standart2ItemBlockBuilder("structural/panel_tile_corner", RegItems.ARMATURES.get(),RegItems.SMALL_TILE.get()));
        lootTables.put(RegBlocks.PANEL_TILE_SIDE.get(), standart2ItemBlockBuilder("structural/panel_tile_side", RegItems.ARMATURES.get(),RegItems.SMALL_TILE.get()));
        lootTables.put(RegBlocks.PANEL_TILE.get(), standart2ItemBlockBuilder("structural/panel_tile", RegItems.ARMATURES.get(),RegItems.SMALL_TILE.get()));


        //tile quad
        lootTables.put(RegBlocks.TILE_QUAD_1.get(), standart2ItemBlockBuilder("quadtile/tile_quad_1", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_1_BR.get(), standart2ItemBlockBuilder("quadtile/tile_quad_1_br", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_2.get(), standart2ItemBlockBuilder("quadtile/tile_quad_2", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_2_BR.get(), standart2ItemBlockBuilder("quadtile/tile_quad_2_br", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_3.get(), standart2ItemBlockBuilder("quadtile/tile_quad_3", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_3_BR.get(), standart2ItemBlockBuilder("quadtile/tile_quad_3_br", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_4.get(), standart2ItemBlockBuilder("quadtile/tile_quad_4", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_5_BR.get(), standart2ItemBlockBuilder("quadtile/tile_quad_5_br", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_5_BRf.get(), standartItemBlockBuilder("quadtile/tile_quad_5_brf", RegItems.ARMATURES.get(), 1));
        lootTables.put(RegBlocks.TILE_QUAD_5.get(), standart2ItemBlockBuilder("quadtile/tile_quad_5", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_BLUE.get(), standart2ItemBlockBuilder("quadtile/tile_quad_blue", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_BLUE_BR.get(), standart2ItemBlockBuilder("quadtile/tile_quad_blue_br", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_WHITE.get(), standart2ItemBlockBuilder("quadtile/tile_quad_white", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_WHITE_BR.get(), standart2ItemBlockBuilder("quadtile/tile_quad_white_br", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_GRAY.get(), standart2ItemBlockBuilder("quadtile/tile_quad_white", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_CONCRETE.get(), standartItemBlockBuilder("quadtile/tile_quad_concrete", RegItems.ARMATURES.get(),1));

        //tile rest
        lootTables.put(RegBlocks.TILE_REST_DARK_BLUE.get(), standart2ItemBlockBuilder("resttile/tile_rest_dark_blue", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.TILE_REST_DARK_BLUE_BR.get(), standart2ItemBlockBuilder("resttile/tile_rest_dark_blue_br", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.TILE_REST_BLUE.get(), standart2ItemBlockBuilder("resttile/tile_rest_blue", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.TILE_REST_BLUE_BR.get(), standart2ItemBlockBuilder("resttile/tile_rest_blue_br", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.TILE_REST_WHITE.get(), standart2ItemBlockBuilder("resttile/tile_rest_white", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.TILE_REST_WHITE_BR.get(), standart2ItemBlockBuilder("resttile/tile_rest_white_br", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.TILE_REST_BLACK.get(), standart2ItemBlockBuilder("resttile/tile_rest_black", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.TILE_REST_BLACK_BR.get(), standart2ItemBlockBuilder("resttile/tile_rest_black_br", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.TILE_REST_BROWN.get(), standart2ItemBlockBuilder("resttile/tile_rest_brown", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.TILE_REST_BROWN_BR.get(), standart2ItemBlockBuilder("resttile/tile_rest_brown_br", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));

        //reg tiles
        lootTables.put(RegBlocks.REGULAR_AM_TILE.get(), standart2ItemBlockBuilder("regtile/tile_reg_am", RegItems.ARMATURES.get(), RegItems.BIG_TILE.get()));
        lootTables.put(RegBlocks.REGULAR_AM_TILE_BR.get(), standart2ItemBlockBuilder("regtile/tile_reg_am_br", RegItems.ARMATURES.get(), RegItems.BIG_TILE.get()));
        lootTables.put(RegBlocks.REGULAR_BROWN_TILE.get(), standart2ItemBlockBuilder("regtile/tile_reg_brown", RegItems.ARMATURES.get(), RegItems.BIG_TILE.get()));
        lootTables.put(RegBlocks.REGULAR_BROWN_TILE_BR.get(), standart2ItemBlockBuilder("regtile/tile_reg_brown_br", RegItems.ARMATURES.get(), RegItems.BIG_TILE.get()));
        lootTables.put(RegBlocks.REGULAR_LIL_TILE.get(), standart2ItemBlockBuilder("regtile/tile_reg_lil", RegItems.ARMATURES.get(), RegItems.BIG_TILE.get()));
        lootTables.put(RegBlocks.REGULAR_LIL_TILE_BR.get(), standart2ItemBlockBuilder("regtile/tile_reg_lil_br", RegItems.ARMATURES.get(), RegItems.BIG_TILE.get()));

        //small tiles
        lootTables.put(RegBlocks.TILE_MOSAIC_1.get(), standart2ItemBlockBuilder("mosaictile/tile_mosaic_1", RegItems.ARMATURES.get(), RegItems.SMALL_TILE.get()));
        lootTables.put(RegBlocks.TILE_MOSAIC_2.get(), standart2ItemBlockBuilder("mosaictile/tile_mosaic_2", RegItems.ARMATURES.get(), RegItems.SMALL_TILE.get()));

        lootTables.put(RegBlocks.SMALL_TILE_BLUE.get(), standart2ItemBlockBuilder("regtile/small_tile_blue", RegItems.ARMATURES.get(), RegItems.SMALL_TILE.get()));
        lootTables.put(RegBlocks.SMALL_TILE_BLUE_BR.get(), standart2ItemBlockBuilder("regtile/small_tile_blue_br", RegItems.ARMATURES.get(), RegItems.SMALL_TILE.get()));
        lootTables.put(RegBlocks.SMALL_TILE_YELLOW.get(), standart2ItemBlockBuilder("regtile/small_tile_yellow", RegItems.ARMATURES.get(), RegItems.SMALL_TILE.get()));
        lootTables.put(RegBlocks.SMALL_TILE_YELLOW_BR.get(), standart2ItemBlockBuilder("regtile/small_tile_yellow_br", RegItems.ARMATURES.get(), RegItems.SMALL_TILE.get()));
        lootTables.put(RegBlocks.SMALL_TILE_RED.get(), standart2ItemBlockBuilder("regtile/small_tile_red", RegItems.ARMATURES.get(), RegItems.SMALL_TILE.get()));
        lootTables.put(RegBlocks.SMALL_TILE_RED_BR.get(), standart2ItemBlockBuilder("regtile/small_tile_red_br", RegItems.ARMATURES.get(), RegItems.SMALL_TILE.get()));
        lootTables.put(RegBlocks.SMALL_TILE_WHITE.get(), standart2ItemBlockBuilder("regtile/small_tile_white", RegItems.ARMATURES.get(), RegItems.SMALL_TILE.get()));
        lootTables.put(RegBlocks.SMALL_TILE_WHITE_BR.get(), standart2ItemBlockBuilder("regtile/small_tile_white_br", RegItems.ARMATURES.get(), RegItems.SMALL_TILE.get()));

        //horiz tile
        lootTables.put(RegBlocks.HORIZ_TILE_BLUE.get(), standart2ItemBlockBuilder("horiztile/horiz_tile_blue", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.HORIZ_TILE_BLUE_BR.get(), standart2ItemBlockBuilder("horiztile/horiz_tile_blue_br", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.HORIZ_TILE_WHITE.get(), standart2ItemBlockBuilder("horiztile/horiz_tile_white", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.HORIZ_TILE_WHITE_BR.get(), standart2ItemBlockBuilder("horiztile/horiz_tile_white_br", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.HORIZ_TILE_DARK_BLUE.get(), standart2ItemBlockBuilder("horiztile/horiz_tile_dark_blue", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.HORIZ_TILE_DARK_BLUE_BR.get(), standart2ItemBlockBuilder("horiztile/horiz_tile_dark_blue_br", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));

    }
}