package ru.tesmio.data.providers;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import ru.tesmio.Core;
import ru.tesmio.reg.RegBlocks;

public class SovietBlockStateProvider extends BlockStateProvider {
    public SovietBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Core.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        variantBuilder(RegBlocks.CONCRETE_ORANGE.get(),"concrete/concrete_orange");
        variantBuilder(RegBlocks.CONCRETE_ORANGE_BR.get(),"concrete/concrete_orange_br");
        variantBuilder(RegBlocks.TILE_REST_BLACK.get(), "resttile/tile_rest_black");
        variantBuilder(RegBlocks.TILE_REST_BLACK_BR.get(), "resttile/tile_rest_black_br");
        variantBuilder(RegBlocks.TILE_REST_BROWN.get(), "resttile/tile_rest_brown");
        variantBuilder(RegBlocks.TILE_REST_BROWN_BR.get(), "resttile/tile_rest_brown_br");
        variantBuilder(RegBlocks.TILE_REST_BLUE.get(), "resttile/tile_rest_blue");
        variantBuilder(RegBlocks.TILE_REST_BLUE_BR.get(), "resttile/tile_rest_blue_br");
        variantBuilder(RegBlocks.TILE_REST_WHITE.get(), "resttile/tile_rest_white");
        variantBuilder(RegBlocks.TILE_REST_WHITE_BR.get(), "resttile/tile_rest_white_br");
        variantBuilder(RegBlocks.TILE_REST_DARK_BLUE.get(), "resttile/tile_rest_dark_blue");
        variantBuilder(RegBlocks.TILE_REST_DARK_BLUE_BR.get(), "resttile/tile_rest_dark_blue_br");
    }
    public ModelBuilder<BlockModelBuilder> builder(String name, ResourceLocation rs) {
        return models().cubeAll(name, rs);
    }
    public VariantBlockStateBuilder variantBuilder(Block b, String nameP) {
        return getVariantBuilder(b).forAllStates(state -> ConfiguredModel.builder().modelFile(builder("block/" + nameP, modLoc("block/" + nameP))).build());

    }
}