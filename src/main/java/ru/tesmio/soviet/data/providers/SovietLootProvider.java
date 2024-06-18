package ru.tesmio.soviet.data.providers;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import ru.tesmio.soviet.reg.RegBlocks;
import ru.tesmio.soviet.reg.RegItems;

import java.util.concurrent.ThreadLocalRandom;

public class SovietLootProvider extends BaseLootProvider {

    public SovietLootProvider(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }


    ThreadLocalRandom rand = ThreadLocalRandom.current();
    Item[] loot = new Item[] {RegItems.SUIT_GAS_MASK.get()};



    @Override
    protected void addTables() {


//        forEachStandartBlock(RegBlocks.TURNSTILE.get(), "devices");
//        forEachStandartBlock(RegBlocks.MOTION_SENSOR.get(), "devices");
//        forEachStandartBlock(RegBlocks.STILLAGE.get(), "stuff");
//        lootTables.put(RegBlocks.PURPLE_TABLE.get(), drop1x1Rand("stuff", RegItems.WOOD_SCRAP.get(),2));
//        lootTables.put(RegBlocks.PURPLE_CHAIR.get(), drop1x1Rand("stuff", RegItems.WOOD_SCRAP.get(),1));
//        forEachStandartBlock(RegBlocks.BIOLAB_TABLE2.get(), "stuff");
//        forEachStandartBlock(RegBlocks.BIOLAB_TABLE3.get(), "stuff");
//        forEachStandartBlock(RegBlocks.BIOLAB_TABLE4.get(), "stuff");
//        forEachStandartBlock(RegBlocks.BIOLAB_TABLE_CASE.get(), "stuff");
//        forEachStandartBlock(RegBlocks.BIOLAB_TABLE.get(), "stuff");
//        forEachStandartBlock(RegBlocks.CHEMLAB_TABLE_CASE.get(), "stuff");
//        forEachStandartBlock(RegBlocks.CHEMLAB_TABLE.get(), "stuff");
//        forEachStandartBlock(RegBlocks.BIO_STILLAGE.get(), "stuff");
//        lootTables.put(RegBlocks.REDSTONE_WIRE.get(), drop1x1Rand("copperscrap", RegItems.COPPER_SCRAP.get(),1));
      //  lootChest.put(new ResourceLocation(Core.MODID, "chests/underground_lab"), genChestLoot("gas_mask_lt", RegItems.SUIT_GAS_MASK.get(),15,2,4,1,3));
        //windows
        lootTables.put(RegBlocks.ALUM_FRAMES_EMPTY.get(), drop1x1Rand("windows", RegItems.ALUMINUM_SCRAP.get(),2));
        lootTables.put(RegBlocks.ALUM_FRAMES.get(), drop1x1Rand("windows", RegItems.ALUMINUM_SCRAP.get(),2));
        lootTables.put(RegBlocks.ALUM_WINDOW_EMPTY.get(), drop1x1Rand("windows", RegItems.ALUMINUM_SCRAP.get(),2));
        lootTables.put(RegBlocks.ALUM_WINDOW.get(), drop1x1Rand("windows", RegItems.ALUMINUM_SCRAP.get(),2));
        lootTables.put(RegBlocks.MODERN_WINDOW_LEAF.get(), drop1x1Rand("windows", RegItems.ARMATURES.get(),2));
        lootTables.put(RegBlocks.MODERN_WINDOW_LEAF_EMPTY.get(), drop1x1Rand("windows", RegItems.ARMATURES.get(),2));
        lootTables.put(RegBlocks.MODERN_WINDOW_EMPTY.get(), drop1x1Rand("windows", RegItems.ARMATURES.get(),2));
        lootTables.put(RegBlocks.MODERN_WINDOW.get(), drop1x1Rand("windows", RegItems.ARMATURES.get(),2));
        lootTables.put(RegBlocks.WOOD_WINDOW_LEAF.get(), drop1x1Rand("windows", RegItems.WOOD_SCRAP.get(),2));
        lootTables.put(RegBlocks.WOOD_WINDOW_LEAF_EMPTY.get(), drop1x1Rand("windows", RegItems.WOOD_SCRAP.get(),2));
        lootTables.put(RegBlocks.WOOD_WINDOW_EMPTY.get(), drop1x1Rand("windows", RegItems.WOOD_SCRAP.get(),2));
        lootTables.put(RegBlocks.WOOD_WINDOW.get(), drop1x1Rand("windows", RegItems.WOOD_SCRAP.get(),2));
        //slabs
        lootTables.put(RegBlocks.CONCRETE_SLAB_GRAY.get(), drop1x1Rand("slab", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_SLAB_GREEN.get(), drop1x1Rand("slab", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_SLAB_RED.get(), drop1x1Rand("slab", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_SLAB_BLUE.get(), drop1x1Rand("slab", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_SLAB_BEIGE.get(), drop1x1Rand("slab", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_SLAB_BEIGE2.get(), drop1x1Rand("slab", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_SLAB_YELLOW.get(), drop1x1Rand("slab", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_SLAB_WHITE.get(), drop1x1Rand("slab", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_SLAB_ORANGE.get(), drop1x1Rand("slab", RegItems.ARMATURES.get(),4));
        //stairs
        lootTables.put(RegBlocks.CONCRETE_STAIRS_GRAY.get(), drop1x1Rand("stairs", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_STAIRS_GREEN.get(), drop1x1Rand("stairs", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_STAIRS_RED.get(), drop1x1Rand("stairs", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_STAIRS_BLUE.get(), drop1x1Rand("stairs", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_STAIRS_BEIGE.get(), drop1x1Rand("stairs", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_STAIRS_BEIGE2.get(), drop1x1Rand("stairs", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_STAIRS_YELLOW.get(), drop1x1Rand("stairs", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_STAIRS_WHITE.get(), drop1x1Rand("stairs", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_STAIRS_ORANGE.get(), drop1x1Rand("stairs", RegItems.ARMATURES.get(),4));
        //concrete railing
        lootTables.put(RegBlocks.CONCRETE_RAILING_GRAY.get(), drop1x1Rand("structural/concrete_railing_gray", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_RAILING_WHITE.get(), drop1x1Rand("structural/concrete_railing_white", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_RAILING_BEIGE.get(), drop1x1Rand("structural/concrete_railing_beige", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_RAILING_BEIGE2.get(), drop1x1Rand("structural/concrete_railing_beige2", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_RAILING_RED.get(), drop1x1Rand("structural/concrete_railing_red", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_RAILING_BLUE.get(), drop1x1Rand("structural/concrete_railing_blue", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_RAILING_YELLOW.get(), drop1x1Rand("structural/concrete_railing_yellow", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_RAILING_ORANGE.get(), drop1x1Rand("structural/concrete_railing_orange", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_RAILING_GREEN.get(), drop1x1Rand("structural/concrete_railing_green", RegItems.ARMATURES.get(),4));
        //lino
        lootTables.put(RegBlocks.LINO_1.get(), drop1x1Rand("lino/lino1", RegItems.LINO.get(),3));
        lootTables.put(RegBlocks.LINO_2.get(), drop1x1Rand("lino/lino2", RegItems.LINO.get(),3));
        lootTables.put(RegBlocks.LINO_3.get(), drop1x1Rand("lino/lino3", RegItems.LINO.get(),3));
        lootTables.put(RegBlocks.LINO_4.get(), drop1x1Rand("lino/lino4", RegItems.LINO.get(),3));
        lootTables.put(RegBlocks.LINO_5.get(), drop1x1Rand("lino/lino5", RegItems.LINO.get(),3));
        lootTables.put(RegBlocks.LINO_6.get(), drop1x1Rand("lino/lino6", RegItems.LINO.get(),3));
        lootTables.put(RegBlocks.LINO_7.get(), drop1x1Rand("lino/lino7", RegItems.LINO.get(),3));
        lootTables.put(RegBlocks.LINO_8.get(), drop1x1Rand("lino/lino8", RegItems.LINO.get(),3));
        //concrete
            lootTables.put(RegBlocks.CONCRETE_ORANGE.get(), drop1x1Rand("concrete/concrete_orange", RegItems.ARMATURES.get(),4));
            lootTables.put(RegBlocks.CONCRETE_ORANGE_BR.get(), drop1x1Rand("concrete/concrete_orange_br", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_BLUE.get(), drop1x1Rand("concrete/concrete_blue", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_BLUE_BR.get(), drop1x1Rand("concrete/concrete_blue_br", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_RED.get(), drop1x1Rand("concrete/concrete_red", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_RED_BR.get(), drop1x1Rand("concrete/concrete_red_br", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_WHITE.get(), drop1x1Rand("concrete/concrete_white", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_WHITE_BR.get(), drop1x1Rand("concrete/concrete_white_br", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_GREEN.get(), drop1x1Rand("concrete/concrete_green", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_GREEN_BR.get(), drop1x1Rand("concrete/concrete_green_br", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_YELLOW.get(), drop1x1Rand("concrete/concrete_yellow", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_YELLOW_BR.get(), drop1x1Rand("concrete/concrete_yellow_br", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_BEIGE.get(), drop1x1Rand("concrete/concrete_beige", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_BEIGE_BR.get(), drop1x1Rand("concrete/concrete_beige_br", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_BEIGE2.get(), drop1x1Rand("concrete/concrete_beige2", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_BEIGE2_BR.get(), drop1x1Rand("concrete/concrete_beige2_br", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_GRAY.get(), drop1x1Rand("concrete/concrete_gray", RegItems.ARMATURES.get(),4));
        lootTables.put(RegBlocks.CONCRETE_GRAY_BR.get(), drop1x1Rand("concrete/concrete_gray_br", RegItems.ARMATURES.get(),4 ));
        //panels
        lootTables.put(RegBlocks.PANEL_CONCRETE_CORNER.get(), drop1x1Rand("structural/panel_concrete_corner", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.PANEL_CONCRETE_SIDE.get(), drop1x1Rand("structural/panel_concrete_side", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.PANEL_CONCRETE.get(), drop1x1Rand("structural/panel_concrete", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.PANEL_CONCRETE_YELLOW_CORNER.get(), drop1x1Rand("structural/panel_concrete_yellow_corner", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.PANEL_CONCRETE_YELLOW_SIDE.get(), drop1x1Rand("structural/panel_concrete_yellow_side", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.PANEL_CONCRETE_YELLOW.get(), drop1x1Rand("structural/panel_yellow_concrete", RegItems.ARMATURES.get(),1));
        lootTables.put(RegBlocks.PANEL_TILE_CORNER.get(), drop2x2Const("structural/panel_tile_corner", RegItems.ARMATURES.get(),RegItems.SMALL_TILE.get()));
        lootTables.put(RegBlocks.PANEL_TILE_SIDE.get(), drop2x2Const("structural/panel_tile_side", RegItems.ARMATURES.get(),RegItems.SMALL_TILE.get()));
        lootTables.put(RegBlocks.PANEL_TILE.get(), drop2x2Const("structural/panel_tile", RegItems.ARMATURES.get(),RegItems.SMALL_TILE.get()));


        //tile quad
        lootTables.put(RegBlocks.TILE_QUAD_1.get(), drop2x2Const("quadtile/tile_quad_1", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_1_BR.get(), drop2x2Const("quadtile/tile_quad_1_br", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_2.get(), drop2x2Const("quadtile/tile_quad_2", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_2_BR.get(), drop2x2Const("quadtile/tile_quad_2_br", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_3.get(), drop2x2Const("quadtile/tile_quad_3", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_3_BR.get(), drop2x2Const("quadtile/tile_quad_3_br", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_4.get(), drop2x2Const("quadtile/tile_quad_4", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_5_BR.get(), drop2x2Const("quadtile/tile_quad_5_br", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_5_BRf.get(), drop1x1Rand("quadtile/tile_quad_5_brf", RegItems.ARMATURES.get(), 1));
        lootTables.put(RegBlocks.TILE_QUAD_5.get(), drop2x2Const("quadtile/tile_quad_5", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_BLUE.get(), drop2x2Const("quadtile/tile_quad_blue", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_BLUE_BR.get(), drop2x2Const("quadtile/tile_quad_blue_br", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_WHITE.get(), drop2x2Const("quadtile/tile_quad_white", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_WHITE_BR.get(), drop2x2Const("quadtile/tile_quad_white_br", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_GRAY.get(), drop2x2Const("quadtile/tile_quad_white", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_CONCRETE.get(), drop1x1Rand("quadtile/tile_quad_concrete", RegItems.ARMATURES.get(),1));

        //tile rest
        lootTables.put(RegBlocks.TILE_REST_DARK_BLUE.get(), drop2x2Const("resttile/tile_rest_dark_blue", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.TILE_REST_DARK_BLUE_BR.get(), drop2x2Const("resttile/tile_rest_dark_blue_br", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.TILE_REST_BLUE.get(), drop2x2Const("resttile/tile_rest_blue", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.TILE_REST_BLUE_BR.get(), drop2x2Const("resttile/tile_rest_blue_br", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.TILE_REST_WHITE.get(), drop2x2Const("resttile/tile_rest_white", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.TILE_REST_WHITE_BR.get(), drop2x2Const("resttile/tile_rest_white_br", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.TILE_REST_BLACK.get(), drop2x2Const("resttile/tile_rest_black", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.TILE_REST_BLACK_BR.get(), drop2x2Const("resttile/tile_rest_black_br", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.TILE_REST_BROWN.get(), drop2x2Const("resttile/tile_rest_brown", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.TILE_REST_BROWN_BR.get(), drop2x2Const("resttile/tile_rest_brown_br", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));

        //reg tiles
        lootTables.put(RegBlocks.REGULAR_AM_TILE.get(), drop2x2Const("regtile/tile_reg_am", RegItems.ARMATURES.get(), RegItems.BIG_TILE.get()));
        lootTables.put(RegBlocks.REGULAR_AM_TILE_BR.get(), drop2x2Const("regtile/tile_reg_am_br", RegItems.ARMATURES.get(), RegItems.BIG_TILE.get()));
        lootTables.put(RegBlocks.REGULAR_BROWN_TILE.get(), drop2x2Const("regtile/tile_reg_brown", RegItems.ARMATURES.get(), RegItems.BIG_TILE.get()));
        lootTables.put(RegBlocks.REGULAR_BROWN_TILE_BR.get(), drop2x2Const("regtile/tile_reg_brown_br", RegItems.ARMATURES.get(), RegItems.BIG_TILE.get()));
        lootTables.put(RegBlocks.REGULAR_LIL_TILE.get(), drop2x2Const("regtile/tile_reg_lil", RegItems.ARMATURES.get(), RegItems.BIG_TILE.get()));
        lootTables.put(RegBlocks.REGULAR_LIL_TILE_BR.get(), drop2x2Const("regtile/tile_reg_lil_br", RegItems.ARMATURES.get(), RegItems.BIG_TILE.get()));

        //small tiles
        lootTables.put(RegBlocks.TILE_MOSAIC_1.get(), drop2x2Const("mosaictile/tile_mosaic_1", RegItems.ARMATURES.get(), RegItems.SMALL_TILE.get()));
        lootTables.put(RegBlocks.TILE_MOSAIC_2.get(), drop2x2Const("mosaictile/tile_mosaic_2", RegItems.ARMATURES.get(), RegItems.SMALL_TILE.get()));

        lootTables.put(RegBlocks.SMALL_TILE_BLUE.get(), drop2x2Const("regtile/small_tile_blue", RegItems.ARMATURES.get(), RegItems.SMALL_TILE.get()));
        lootTables.put(RegBlocks.SMALL_TILE_BLUE_BR.get(), drop2x2Const("regtile/small_tile_blue_br", RegItems.ARMATURES.get(), RegItems.SMALL_TILE.get()));
        lootTables.put(RegBlocks.SMALL_TILE_YELLOW.get(), drop2x2Const("regtile/small_tile_yellow", RegItems.ARMATURES.get(), RegItems.SMALL_TILE.get()));
        lootTables.put(RegBlocks.SMALL_TILE_YELLOW_BR.get(), drop2x2Const("regtile/small_tile_yellow_br", RegItems.ARMATURES.get(), RegItems.SMALL_TILE.get()));
        lootTables.put(RegBlocks.SMALL_TILE_RED.get(), drop2x2Const("regtile/small_tile_red", RegItems.ARMATURES.get(), RegItems.SMALL_TILE.get()));
        lootTables.put(RegBlocks.SMALL_TILE_RED_BR.get(), drop2x2Const("regtile/small_tile_red_br", RegItems.ARMATURES.get(), RegItems.SMALL_TILE.get()));
        lootTables.put(RegBlocks.SMALL_TILE_WHITE.get(), drop2x2Const("regtile/small_tile_white", RegItems.ARMATURES.get(), RegItems.SMALL_TILE.get()));
        lootTables.put(RegBlocks.SMALL_TILE_WHITE_BR.get(), drop2x2Const("regtile/small_tile_white_br", RegItems.ARMATURES.get(), RegItems.SMALL_TILE.get()));

        //horiz tile
        lootTables.put(RegBlocks.HORIZ_TILE_BLUE.get(), drop2x2Const("horiztile/horiz_tile_blue", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.HORIZ_TILE_BLUE_BR.get(), drop2x2Const("horiztile/horiz_tile_blue_br", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.HORIZ_TILE_WHITE.get(), drop2x2Const("horiztile/horiz_tile_white", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.HORIZ_TILE_WHITE_BR.get(), drop2x2Const("horiztile/horiz_tile_white_br", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.HORIZ_TILE_DARK_BLUE.get(), drop2x2Const("horiztile/horiz_tile_dark_blue", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));
        lootTables.put(RegBlocks.HORIZ_TILE_DARK_BLUE_BR.get(), drop2x2Const("horiztile/horiz_tile_dark_blue_br", RegItems.ARMATURES.get(), RegItems.REST_TILE.get()));


        //windproof panel
        lootTables.put(RegBlocks.WINDPROOF_BETON_BEIGE.get(), drop1x1Rand("windproof", RegItems.ARMATURES.get(),3));
        lootTables.put(RegBlocks.WINDPROOF_BETON_GRAY.get(), drop1x1Rand("windproof", RegItems.ARMATURES.get(),3));
        lootTables.put(RegBlocks.WINDPROOF_BETON_BEIGE2.get(), drop1x1Rand("windproof", RegItems.ARMATURES.get(),3));
        lootTables.put(RegBlocks.WINDPROOF_BETON_BLUE.get(), drop1x1Rand("windproof", RegItems.ARMATURES.get(),3));
        lootTables.put(RegBlocks.WINDPROOF_BETON_GREEN.get(), drop1x1Rand("windproof", RegItems.ARMATURES.get(),3));
        lootTables.put(RegBlocks.WINDPROOF_BETON_ORANGE.get(), drop1x1Rand("windproof", RegItems.ARMATURES.get(),3));
        lootTables.put(RegBlocks.WINDPROOF_BETON_RED.get(), drop1x1Rand("windproof", RegItems.ARMATURES.get(),3));
        lootTables.put(RegBlocks.WINDPROOF_BETON_WHITE.get(), drop1x1Rand("windproof", RegItems.ARMATURES.get(),3));
        lootTables.put(RegBlocks.WINDPROOF_BETON_YELLOW.get(), drop1x1Rand("windproof", RegItems.ARMATURES.get(),3));

        //red bricks
        lootTables.put(RegBlocks.RED_BRICKS.get(), drop2x2Rand("redbricks", RegItems.CRACKED_RED_BRICK.get(), RegItems.BROKEN_RED_BRICK.get(),0,2,2,4));
        lootTables.put(RegBlocks.RED_BRICKS_BR.get(), drop2x2Rand("redbricks", RegItems.CRACKED_RED_BRICK.get(), RegItems.BROKEN_RED_BRICK.get(),0,2,2,4));
        lootTables.put(RegBlocks.WALL_BRICKS.get(), drop2x2Rand("redbricks", RegItems.CRACKED_RED_BRICK.get(), RegItems.BROKEN_RED_BRICK.get(),0,2,2,4));
        lootTables.put(RegBlocks.WALL_BRICKS_BR.get(), drop2x2Rand("redbricks", RegItems.CRACKED_RED_BRICK.get(), RegItems.BROKEN_RED_BRICK.get(),0,2,2,4));
        lootTables.put(RegBlocks.BRICKS.get(), drop2x2Rand("redbricks", RegItems.CRACKED_RED_BRICK.get(), RegItems.BROKEN_RED_BRICK.get(),0,2,2,4));
        lootTables.put(RegBlocks.BRICKS_WITH_WHITE.get(), drop2x2Rand("redbricks", RegItems.CRACKED_RED_BRICK.get(), RegItems.BROKEN_RED_BRICK.get(),0,2,2,4));
        lootTables.put(RegBlocks.BRICKS_BR.get(), drop2x2Rand("redbricks", RegItems.CRACKED_RED_BRICK.get(), RegItems.BROKEN_RED_BRICK.get(),0,2,2,4));
        lootTables.put(RegBlocks.LIGHT_BRICKS.get(), drop2x2Rand("redbricks", RegItems.CRACKED_RED_BRICK.get(), RegItems.BROKEN_RED_BRICK.get(),0,2,2,4));
        lootTables.put(RegBlocks.LIGHT_BRICKS_BR.get(), drop2x2Rand("redbricks", RegItems.CRACKED_RED_BRICK.get(), RegItems.BROKEN_RED_BRICK.get(),0,2,2,4));
        lootTables.put(RegBlocks.SHORT_BRICKS.get(), drop2x2Rand("redbricks", RegItems.CRACKED_RED_BRICK.get(), RegItems.BROKEN_RED_BRICK.get(),0,2,2,4));
        //silica bricks
        lootTables.put(RegBlocks.YELLOW_BRICKS_1.get(), drop2x2Rand("silicabricks", RegItems.CRACKED_YELLOW_BRICK.get(), RegItems.BROKEN_YELLOW_BRICK.get(),0,2,2,4));
        lootTables.put(RegBlocks.YELLOW_BRICKS_2.get(), drop2x2Rand("silicabricks", RegItems.CRACKED_YELLOW_BRICK.get(), RegItems.BROKEN_YELLOW_BRICK.get(),0,2,2,4));
        lootTables.put(RegBlocks.YELLOW_BRICKS_3.get(), drop2x2Rand("silicabricks", RegItems.CRACKED_YELLOW_BRICK.get(), RegItems.BROKEN_YELLOW_BRICK.get(),0,2,2,4));
        lootTables.put(RegBlocks.YELLOW_BRICKS_1_BR.get(), drop2x2Rand("silicabricks", RegItems.CRACKED_YELLOW_BRICK.get(), RegItems.BROKEN_YELLOW_BRICK.get(),0,2,2,4));
        lootTables.put(RegBlocks.YELLOW_BRICKS_2_BR.get(), drop2x2Rand("silicabricks", RegItems.CRACKED_YELLOW_BRICK.get(), RegItems.BROKEN_YELLOW_BRICK.get(),0,2,2,4));
        lootTables.put(RegBlocks.YELLOW_BRICKS_3_BR.get(), drop2x2Rand("silicabricks", RegItems.CRACKED_YELLOW_BRICK.get(), RegItems.BROKEN_YELLOW_BRICK.get(),0,2,2,4));
        lootTables.put(RegBlocks.SMALL_BRICKS.get(), drop2x2Rand("silicabricks", RegItems.CRACKED_YELLOW_BRICK.get(), RegItems.BROKEN_YELLOW_BRICK.get(),0,1,2,4));
        //white bricks
        lootTables.put(RegBlocks.WHITE_BRICKS.get(), drop2x2Rand("whitebricks", RegItems.WHITE_BRICK.get(), RegItems.BROKEN_WHITE_BRICK.get(),0,2,2,4));
        lootTables.put(RegBlocks.WHITE_BRICKS_BR.get(), drop2x2Rand("whitebricks", RegItems.WHITE_BRICK.get(), RegItems.BROKEN_WHITE_BRICK.get(),0,2,2,4));

        //trim blocks
        lootTables.put(RegBlocks.TRIM_TILE_1.get(), drop2x2Rand("trim", RegItems.ARMATURES.get(), RegItems.REST_TILE.get(),0,2,0,5));
        lootTables.put(RegBlocks.TRIM_TILE_1_BR.get(), drop2x2Rand("trim", RegItems.ARMATURES.get(), RegItems.REST_TILE.get(),0,2,0,3));
        lootTables.put(RegBlocks.TRIM_TILE_RED.get(), drop2x2Rand("trim", RegItems.ARMATURES.get(), RegItems.SMALL_TILE.get(),0,2,0,7));
        lootTables.put(RegBlocks.TRIM_TILE_BLUE.get(), drop2x2Rand("trim", RegItems.ARMATURES.get(), RegItems.SMALL_TILE.get(),0,2,0,7));

        lootTables.put(RegBlocks.PARQUET_BLOCK.get(), drop1x1Rand("parquet", RegItems.WOOD_SCRAP.get(),2,7));
        lootTables.put(RegBlocks.PARQUET_BLOCK_DIAG.get(), drop1x1Rand("parquet", RegItems.WOOD_SCRAP.get(),2,7));
        lootTables.put(RegBlocks.TRIM_STONE_1.get(), drop1x1Rand("trim2", RegItems.CONCRETE_SCRAP.get(),1,4));
        lootTables.put(RegBlocks.TRIM_STONE_2.get(), drop1x1Rand("trim2", RegItems.CONCRETE_SCRAP.get(),1,4));
        lootTables.put(RegBlocks.TRIM_STONE_3.get(), drop1x1Rand("trim2", RegItems.CONCRETE_SCRAP.get(),1,4));
        lootTables.put(RegBlocks.TRIM_STONE_4.get(), drop1x1Rand("trim2", RegItems.CONCRETE_SCRAP.get(),1,4));
        lootTables.put(RegBlocks.TRIM_METAL_1.get(), drop1x1Rand("trim2", RegItems.ALUMINUM_SCRAP.get(),1,4));
        lootTables.put(RegBlocks.TRIM_METAL_2.get(), drop1x1Rand("trim2", RegItems.ALUMINUM_SCRAP.get(),1,4));
        lootTables.put(RegBlocks.RUSTYMETAL_BLOCK.get(), drop1x1Rand("trim2", RegItems.RUSTY_SCRAP.get(),2,9));
        lootTables.put(RegBlocks.CONCRETE_PLATE.get(), drop1x1Rand("trim2", RegItems.ARMATURES.get(),1,3));
        lootTables.put(RegBlocks.CONTAINMENT_BLOCK.get(), drop1x1Rand("trim2", RegItems.RUSTY_SCRAP.get(),4,9));

        lootTables.put(RegBlocks.LEADCERAMIC_TILE.get(), drop2x2Rand("leadceramic", RegItems.ARMATURES.get(),RegItems.LEADCERAMIC_TILE.get(),0,3, 6,14));
    }

}