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

        builderItemBlock("concrete/concrete_orange", "concrete/concrete_orange");
        builderItemBlock("concrete/concrete_orange_br", "concrete/concrete_orange_br");
        builderItemBlock("resttile/tile_rest_blue", "resttile/tile_rest_blue");
        builderItemBlock("resttile/tile_rest_blue_br", "resttile/tile_rest_blue_br");
        builderItemBlock("resttile/tile_rest_brown", "resttile/tile_rest_brown");
        builderItemBlock("resttile/tile_rest_brown_br", "resttile/tile_rest_brown_br");
        builderItemBlock("resttile/tile_rest_white", "resttile/tile_rest_white");
        builderItemBlock("resttile/tile_rest_white_br", "resttile/tile_rest_white_br");
        builderItemBlock("resttile/tile_rest_black", "resttile/tile_rest_black");
        builderItemBlock("resttile/tile_rest_black_br", "resttile/tile_rest_black_br");
        builderItemBlock("resttile/tile_rest_dark_blue", "resttile/tile_rest_dark_blue");
        builderItemBlock("resttile/tile_rest_dark_blue_br", "resttile/tile_rest_dark_blue_br");
    }

    private ItemModelBuilder builderItem(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
    private ItemModelBuilder builderItemBlock(String path, String name) {
        return getBuilder("item/" + path).parent(getExistingFile(modLoc("block/" + name)));
    }

}
