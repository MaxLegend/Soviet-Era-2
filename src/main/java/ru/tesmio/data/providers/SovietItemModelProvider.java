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

       builder(itemGenerated, "big_tile");
    //    withExistingParent("concrete_orange", modLoc("block/concrete/concrete_orange"));
   //     withExistingParent("concrete_orange_br", modLoc("block/concrete/concrete_orange_br"));

    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }

}
