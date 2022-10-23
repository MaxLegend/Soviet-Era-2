package ru.tesmio.data.providers;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import ru.tesmio.Core;
import ru.tesmio.reg.RegBlocks;

public class SovietBlockStateProvider extends BlockStateProvider {
    public SovietBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Core.MODID, exFileHelper);
    }

    @Override
   protected void registerStatesAndModels() {
        variantBuilderAll();
    }
    public ModelBuilder<BlockModelBuilder> builder(String name, ResourceLocation rs) {
        return models().cubeAll(name, rs);
    }
    public VariantBlockStateBuilder variantBuilder(Block b, String nameP) {

        return getVariantBuilder(b).forAllStates(state -> ConfiguredModel.builder().modelFile(builder("block/" + nameP, modLoc("block/" + nameP))).build());

    }
    public void variantBuilderAll() {
        for(RegistryObject<Block> b2 : RegBlocks.BLOCKS.getEntries()) {
            getVariantBuilder(b2.get()).forAllStates(state -> ConfiguredModel.builder().modelFile(builder("block/" + b2.get().getRegistryName().toString().substring(7), modLoc("block/" + b2.get().getRegistryName().toString().substring(7)))).build());
        }


    }
}