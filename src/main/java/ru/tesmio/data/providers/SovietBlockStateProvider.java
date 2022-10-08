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
      //  getVariantBuilder(RegBlocks.CONCRETE_ORANGE.get()).forAllStates(state -> ConfiguredModel.builder().modelFile(singleTex("concrete_orange", modLoc("textures/block/concrete/concrete_orange"))).build());
        getVariantBuilder(RegBlocks.CONCRETE_ORANGE.get()).partialState().setModels(ConfiguredModel.builder().modelFile(builder("concrete_orange", modLoc("textures/block/concrete/concrete_orange.png"))).build());
        //  simpleBlock(RegBlocks.CONCRETE_ORANGE.get());
     //   simpleBlock(RegBlocks.CONCRETE_ORANGE_BR.get());

    }
    public ModelBuilder<BlockModelBuilder> builder(String name, ResourceLocation rs) {
        return models().cubeAll(name, rs);
    }

}
