package ru.tesmio.data.providers;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import ru.tesmio.Core;

public class SovietBlockTagsProvider extends BlockTagsProvider {
    public SovietBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, Core.MODID, existingFileHelper);
    }


    protected void addTags() {
    //    tag(ModTags.Blocks.ORES_SILVER).add(ModBlocks.SILVER_ORE.get());

    }
}
