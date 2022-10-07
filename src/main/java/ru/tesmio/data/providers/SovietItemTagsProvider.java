package ru.tesmio.data.providers;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.io.IOException;

public class SovietItemTagsProvider implements IDataProvider {
    public SovietItemTagsProvider(DataGenerator gen, SovietBlockTagsProvider blockTags, ExistingFileHelper existingFileHelper) {
    }

    @Override
    public void act(DirectoryCache cache) throws IOException {

    }

    @Override
    public String getName() {
        return null;
    }
}
