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

        builderItemBlock("item/concrete/concrete_orange", "block/concrete/concrete_orange");
        builderItemBlock("item/concrete/concrete_orange_br", "block/concrete/concrete_orange_br");

    }

    private ItemModelBuilder builderItem(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
    private ItemModelBuilder builderItemBlock(String path, String name) {
        return getBuilder(path).parent(getExistingFile(modLoc(name)));
    }

}
