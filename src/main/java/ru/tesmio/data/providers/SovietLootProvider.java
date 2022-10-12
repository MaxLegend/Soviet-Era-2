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

            lootTables.put(RegBlocks.CONCRETE_ORANGE.get(), standartItemBlockBuilder("concrete/concrete_orange", RegItems.ARMATURES.get()));
            lootTables.put(RegBlocks.CONCRETE_ORANGE_BR.get(), standartItemBlockBuilder("concrete/concrete_orange_br", RegItems.ARMATURES.get()));
        lootTables.put(RegBlocks.CONCRETE_BLUE.get(), standartItemBlockBuilder("concrete/concrete_blue", RegItems.ARMATURES.get()));
        lootTables.put(RegBlocks.CONCRETE_BLUE_BR.get(), standartItemBlockBuilder("concrete/concrete_blue_br", RegItems.ARMATURES.get()));
        lootTables.put(RegBlocks.CONCRETE_RED.get(), standartItemBlockBuilder("concrete/concrete_red", RegItems.ARMATURES.get()));
        lootTables.put(RegBlocks.CONCRETE_RED_BR.get(), standartItemBlockBuilder("concrete/concrete_red_br", RegItems.ARMATURES.get()));
        lootTables.put(RegBlocks.CONCRETE_WHITE.get(), standartItemBlockBuilder("concrete/concrete_white", RegItems.ARMATURES.get()));
        lootTables.put(RegBlocks.CONCRETE_WHITE_BR.get(), standartItemBlockBuilder("concrete/concrete_white_br", RegItems.ARMATURES.get()));
        lootTables.put(RegBlocks.CONCRETE_GREEN.get(), standartItemBlockBuilder("concrete/concrete_green", RegItems.ARMATURES.get()));
        lootTables.put(RegBlocks.CONCRETE_GREEN_BR.get(), standartItemBlockBuilder("concrete/concrete_green_br", RegItems.ARMATURES.get()));
        lootTables.put(RegBlocks.CONCRETE_YELLOW.get(), standartItemBlockBuilder("concrete/concrete_yellow", RegItems.ARMATURES.get()));
        lootTables.put(RegBlocks.CONCRETE_YELLOW_BR.get(), standartItemBlockBuilder("concrete/concrete_yellow_br", RegItems.ARMATURES.get()));
        lootTables.put(RegBlocks.CONCRETE_BEIGE.get(), standartItemBlockBuilder("concrete/concrete_beige", RegItems.ARMATURES.get()));
        lootTables.put(RegBlocks.CONCRETE_BEIGE_BR.get(), standartItemBlockBuilder("concrete/concrete_beige_br", RegItems.ARMATURES.get()));
        lootTables.put(RegBlocks.CONCRETE_BEIGE2.get(), standartItemBlockBuilder("concrete/concrete_beige2", RegItems.ARMATURES.get()));
        lootTables.put(RegBlocks.CONCRETE_BEIGE2_BR.get(), standartItemBlockBuilder("concrete/concrete_beige2_br", RegItems.ARMATURES.get()));
        lootTables.put(RegBlocks.CONCRETE_GRAY.get(), standartItemBlockBuilder("concrete/concrete_gray", RegItems.ARMATURES.get()));
        lootTables.put(RegBlocks.CONCRETE_GRAY_BR.get(), standartItemBlockBuilder("concrete/concrete_gray_br", RegItems.ARMATURES.get()));


        lootTables.put(RegBlocks.TILE_QUAD_1.get(), standart2ItemBlockBuilder("quadtile/tile_quad_1", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_1_BR.get(), standart2ItemBlockBuilder("quadtile/tile_quad_1_br", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_2.get(), standart2ItemBlockBuilder("quadtile/tile_quad_2", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_2_BR.get(), standart2ItemBlockBuilder("quadtile/tile_quad_2_br", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_3.get(), standart2ItemBlockBuilder("quadtile/tile_quad_3", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_3_BR.get(), standart2ItemBlockBuilder("quadtile/tile_quad_3_br", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_4.get(), standart2ItemBlockBuilder("quadtile/tile_quad_4", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_5_BR.get(), standart2ItemBlockBuilder("quadtile/tile_quad_5_br", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_5_BRf.get(), standart2ItemBlockBuilder("quadtile/tile_quad_5_brf", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_5.get(), standart2ItemBlockBuilder("quadtile/tile_quad_5", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_BLUE.get(), standart2ItemBlockBuilder("quadtile/tile_quad_blue", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_BLUE_BR.get(), standart2ItemBlockBuilder("quadtile/tile_quad_blue_br", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_WHITE.get(), standart2ItemBlockBuilder("quadtile/tile_quad_white", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_WHITE_BR.get(), standart2ItemBlockBuilder("quadtile/tile_quad_white_br", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_GRAY.get(), standart2ItemBlockBuilder("quadtile/tile_quad_white", RegItems.ARMATURES.get(), RegItems.QUAD_TILE.get()));
        lootTables.put(RegBlocks.TILE_QUAD_CONCRETE.get(), standartItemBlockBuilder("quadtile/tile_quad_concrete", RegItems.ARMATURES.get()));
    }
}