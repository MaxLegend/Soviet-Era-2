package ru.tesmio.data.providers;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import ru.tesmio.Core;
import ru.tesmio.reg.RegBlocks;

public class SovietBlockStateProvider extends BlockStateProvider {
    public SovietBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Core.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        getVariantBuilder(RegBlocks.CONCRETE_ORANGE.get()).forAllStates(state -> ConfiguredModel.builder().modelFile(builder("block/concrete/concrete_orange", modLoc("block/concrete/concrete_orange"))).build());
//        simpleBlock(RegBlocks.CONCRETE_ORANGE.get(),
        getVariantBuilder(RegBlocks.CONCRETE_ORANGE_BR.get()).forAllStates(state -> ConfiguredModel.builder().modelFile(builder("block/concrete/concrete_orange_br", modLoc("block/concrete/concrete_orange_br"))).build());

    }
    public ModelBuilder<BlockModelBuilder> builder(String name, ResourceLocation rs) {
      //  return models().singleTexture(name, mcLoc(BLOCK_FOLDER + "/concrete"), "concrete_orange", rs);
        return models().cubeAll(name, rs);
    }
}