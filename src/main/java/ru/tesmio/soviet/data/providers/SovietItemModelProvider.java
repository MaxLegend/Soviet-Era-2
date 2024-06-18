package ru.tesmio.soviet.data.providers;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import ru.tesmio.soviet.blocks.decorative.props.ToxicAir;
import ru.tesmio.soviet.core.Core;
import ru.tesmio.soviet.reg.RegBlocks;
import ru.tesmio.soviet.reg.RegFluids;
import ru.tesmio.soviet.reg.RegItems;


public class SovietItemModelProvider extends ItemModelProvider {
    public SovietItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Core.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        builderItem(itemGenerated);
        builderItemBlock();
    }

    private void builderItem(ModelFile itemGenerated) {
        for(RegistryObject<Item> item : RegItems.ITEMS.getEntries()) {
            getBuilder("item/" + item.get().getRegistryName().toString().substring(7)).parent(itemGenerated).texture("layer0", "item/" + item.get().getRegistryName().toString().substring(7));
        }
    }
    private void builderItemBlock() {
        for(RegistryObject<Block> b2 : RegBlocks.BLOCKS.getEntries()) {
            if( !(b2.get() instanceof ToxicAir) && !(b2.get() == RegFluids.TOXIC_WATER_BLOCK.get()))
            getBuilder("item/" + b2.get().getRegistryName().toString().substring(7)).parent(getExistingFile(modLoc("block/" + b2.get().getRegistryName().toString().substring(7))));
        }
        for(RegistryObject<Block> b2 : RegBlocks.BLOCKS_CUSTOM_MODELS.getEntries()) {
            if(b2.get() == RegBlocks.RASTY_FRAME.get()) {
                continue;
            }
            getBuilder("item/" + b2.get().getRegistryName().toString().substring(7)).parent(getExistingFile(modLoc("block/" + b2.get().getRegistryName().toString().substring(7))));
        }
        for(RegistryObject<Block> b2 : RegBlocks.BLOCKS_CUSTOM_MODELS_COLORED.getEntries()) {

                getBuilder("item/" + b2.get().getRegistryName().toString().substring(7)).parent(getExistingFile(modLoc("block/" + b2.get().getRegistryName().toString().substring(7))));


        }
        for(RegistryObject<Block> b2 : RegBlocks.NOT_DEFAULT_BLOCKS.getEntries()) {
            getBuilder("item/" + b2.get().getRegistryName().toString().substring(7)).parent(getExistingFile(modLoc("block/" + b2.get().getRegistryName().toString().substring(7))));
        }
    }

}
