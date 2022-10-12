package ru.tesmio.data.providers;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import ru.tesmio.Core;
import ru.tesmio.reg.RegBlocks;

public class SovietBlockStateProvider extends BlockStateProvider {
    public SovietBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Core.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //concrete
        variantBuilder(RegBlocks.CONCRETE_ORANGE.get(),"concrete/concrete_orange");
        variantBuilder(RegBlocks.CONCRETE_ORANGE_BR.get(),"concrete/concrete_orange_br");
        variantBuilder(RegBlocks.CONCRETE_CORNER_PANEL.get(),"concrete/beton_panel_ang_lu");

        //rest tiles
        variantBuilder(RegBlocks.TILE_REST_BLACK.get(), "resttile/tile_rest_black");
        variantBuilder(RegBlocks.TILE_REST_BLACK_BR.get(), "resttile/tile_rest_black_br");
        variantBuilder(RegBlocks.TILE_REST_BROWN.get(), "resttile/tile_rest_brown");
        variantBuilder(RegBlocks.TILE_REST_BROWN_BR.get(), "resttile/tile_rest_brown_br");
        variantBuilder(RegBlocks.TILE_REST_BLUE.get(), "resttile/tile_rest_blue");
        variantBuilder(RegBlocks.TILE_REST_BLUE_BR.get(), "resttile/tile_rest_blue_br");
        variantBuilder(RegBlocks.TILE_REST_WHITE.get(), "resttile/tile_rest_white");
        variantBuilder(RegBlocks.TILE_REST_WHITE_BR.get(), "resttile/tile_rest_white_br");
        variantBuilder(RegBlocks.TILE_REST_DARK_BLUE.get(), "resttile/tile_rest_dark_blue");
        variantBuilder(RegBlocks.TILE_REST_DARK_BLUE_BR.get(), "resttile/tile_rest_dark_blue_br");

        //horiz tiles
        variantBuilder(RegBlocks.HORIZ_TILE_WHITE.get(), "horiztile/horiz_tile_white");
        variantBuilder(RegBlocks.HORIZ_TILE_WHITE_BR.get(), "horiztile/horiz_tile_white_br");
        variantBuilder(RegBlocks.HORIZ_TILE_BLUE.get(), "horiztile/horiz_tile_blue");
        variantBuilder(RegBlocks.HORIZ_TILE_BLUE_BR.get(), "horiztile/horiz_tile_blue_br");
        variantBuilder(RegBlocks.HORIZ_TILE_DARK_BLUE.get(), "horiztile/horiz_tile_dark_blue");
        variantBuilder(RegBlocks.HORIZ_TILE_DARK_BLUE_BR.get(), "horiztile/horiz_tile_dark_blue_br");

        //small tiles
        variantBuilder(RegBlocks.SMALL_TILE_BLUE.get(), "smalltile/small_tile_blue");
        variantBuilder(RegBlocks.SMALL_TILE_BLUE_BR.get(), "smalltile/small_tile_blue_br");
        variantBuilder(RegBlocks.SMALL_TILE_WHITE.get(), "smalltile/small_tile_white");
        variantBuilder(RegBlocks.SMALL_TILE_WHITE_BR.get(), "smalltile/small_tile_white_br");
        variantBuilder(RegBlocks.SMALL_TILE_RED.get(), "smalltile/small_tile_red");
        variantBuilder(RegBlocks.SMALL_TILE_RED_BR.get(), "smalltile/small_tile_red_br");
        variantBuilder(RegBlocks.SMALL_TILE_YELLOW.get(), "smalltile/small_tile_yellow");
        variantBuilder(RegBlocks.SMALL_TILE_YELLOW_BR.get(), "smalltile/small_tile_yellow_br");

        //reg tiles
        variantBuilder(RegBlocks.REGULAR_BROWN_TILE.get(), "regtile/tile_reg_brown");
        variantBuilder(RegBlocks.REGULAR_BROWN_TILE_BR.get(), "regtile/tile_reg_brown_br");
        variantBuilder(RegBlocks.REGULAR_AM_TILE.get(), "regtile/tile_reg_am");
        variantBuilder(RegBlocks.REGULAR_AM_TILE_BR.get(), "regtile/tile_reg_am_br");
        variantBuilder(RegBlocks.REGULAR_LIL_TILE.get(), "regtile/tile_reg_lil");
        variantBuilder(RegBlocks.REGULAR_LIL_TILE_BR.get(), "regtile/tile_reg_lil_br");

        //bricks
        variantBuilder(RegBlocks.SOVIET_BRICKS_WITH_WHITE.get(), "brick/soviet_bricks_with_white");
        variantBuilder(RegBlocks.WALL_BRICKS.get(), "brick/light_bricks2");
        variantBuilder(RegBlocks.LIGHT_BRICKS.get(), "brick/light_bricks");
    }
    public ModelBuilder<BlockModelBuilder> builder(String name, ResourceLocation rs) {
        return models().cubeAll(name, rs);
    }
    public VariantBlockStateBuilder variantBuilder(Block b, String nameP) {
        return getVariantBuilder(b).forAllStates(state -> ConfiguredModel.builder().modelFile(builder("block/" + nameP, modLoc("block/" + nameP))).build());

    }
}