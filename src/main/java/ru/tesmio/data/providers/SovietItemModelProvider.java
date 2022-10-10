package ru.tesmio.data.providers;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import ru.tesmio.Core;


public class SovietItemModelProvider extends ItemModelProvider {
    public SovietItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Core.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

       ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));


        builderItem(itemGenerated, "big_tile");

        //rest tiles
        builderItemBlock("concrete/concrete_orange");
        builderItemBlock( "concrete/concrete_orange_br");
        builderItemBlock( "resttile/tile_rest_blue");
        builderItemBlock( "resttile/tile_rest_blue_br");
        builderItemBlock("resttile/tile_rest_brown");
        builderItemBlock("resttile/tile_rest_brown_br");
        builderItemBlock( "resttile/tile_rest_white");
        builderItemBlock("resttile/tile_rest_white_br");
        builderItemBlock("resttile/tile_rest_black");
        builderItemBlock("resttile/tile_rest_black_br");
        builderItemBlock("resttile/tile_rest_dark_blue");
        builderItemBlock("resttile/tile_rest_dark_blue_br");

        //horiz tiles
        builderItemBlock( "horiztile/horiz_tile_blue");
        builderItemBlock("horiztile/horiz_tile_blue_br");
        builderItemBlock( "horiztile/horiz_tile_white");
        builderItemBlock("horiztile/horiz_tile_white_br");
        builderItemBlock("horiztile/horiz_tile_dark_blue");
        builderItemBlock("horiztile/horiz_tile_dark_blue_br");

        //small tiles
        builderItemBlock("smalltile/small_tile_blue");
        builderItemBlock("smalltile/small_tile_blue_br");
        builderItemBlock("smalltile/small_tile_white");
        builderItemBlock("smalltile/small_tile_white_br");
        builderItemBlock("smalltile/small_tile_red");
        builderItemBlock("smalltile/small_tile_red_br");
        builderItemBlock("smalltile/small_tile_yellow");
        builderItemBlock("smalltile/small_tile_yellow_br");

        //reg tiles
        builderItemBlock("regtile/brown_tilled_block");

    }

    private ItemModelBuilder builderItem(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
    private ItemModelBuilder builderItemBlock(String name) {
        return getBuilder("item/" + name).parent(getExistingFile(modLoc("block/" + name)));
    }

}
