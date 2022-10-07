package ru.tesmio.data.providers;


import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import ru.tesmio.Core;

public class SovietBlockStateProvider extends BlockStateProvider {
    public SovietBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Core.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
     //   simpleBlock(RegBlocks.SILVER_BLOCK.get());


    }

}
