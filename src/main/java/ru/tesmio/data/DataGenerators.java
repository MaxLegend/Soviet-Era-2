package ru.tesmio.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import ru.tesmio.Core;
import ru.tesmio.data.providers.SovietBlockStateProvider;
import ru.tesmio.data.providers.SovietItemModelProvider;
import ru.tesmio.data.providers.SovietRecipeProvider;

@Mod.EventBusSubscriber(modid = Core.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    private DataGenerators() {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        gen.addProvider(new SovietBlockStateProvider(gen, existingFileHelper));
       // gen.addProvider(new SovietBlockModelProvider(gen, existingFileHelper));
          gen.addProvider(new SovietItemModelProvider(gen, existingFileHelper));

//        SovietBlockTagsProvider blockTags = new SovietBlockTagsProvider(gen, existingFileHelper);
//        gen.addProvider(blockTags);
//        gen.addProvider(new SovietItemTagsProvider(gen, blockTags, existingFileHelper));

       // gen.addProvider(new SovietLootTableProvider(gen));
        gen.addProvider(new SovietRecipeProvider(gen));
    }
}
