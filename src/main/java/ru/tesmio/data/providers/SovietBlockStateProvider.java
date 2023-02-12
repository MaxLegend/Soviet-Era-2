package ru.tesmio.data.providers;

import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.Half;
import net.minecraft.state.properties.SlabType;
import net.minecraft.state.properties.StairsShape;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import ru.tesmio.core.Core;
import ru.tesmio.blocks.decorative.slabs.BaseSlab;
import ru.tesmio.blocks.decorative.stairs.BaseStairs;
import ru.tesmio.reg.RegBlocks;

public class SovietBlockStateProvider extends BlockStateProvider {
    public SovietBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Core.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        variantBuilderAll();
        builderSlabs();
        builderStairs();
    }

    public ModelBuilder<BlockModelBuilder> builder(String name, ResourceLocation rs) {
        return models().cubeAll(name, rs);
    }

    public void variantBuilderAll() {
        for (RegistryObject<Block> b2 : RegBlocks.BLOCKS.getEntries()) {
            String name = b2.get().getRegistryName().toString();
            if (!(b2.get() instanceof BaseStairs) && !(b2.get() instanceof BaseSlab))
                getVariantBuilder(b2.get()).forAllStates(state -> ConfiguredModel.builder().modelFile(builder("block/" + name.substring(7), modLoc("block/" + name.substring(7)))).build());

        }
    }


    public ModelBuilder<BlockModelBuilder> builder3TexturesModel(String name, String parent, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        return models().withExistingParent(name, parent)
                .texture("side", side)
                .texture("bottom", bottom)
                .texture("top", top);
    }


    public void builderSlabs() {
        for (RegistryObject<Block> b2 : RegBlocks.BLOCKS.getEntries()) {
            if (b2.get() instanceof BaseSlab) {
                String name = b2.get().getRegistryName().toString();
                String loc = "block/" + name.substring(6, name.length() - 5);
                String loc2 = loc.replaceAll(loc, "block/concrete/" + loc.substring(13));
                getVariantBuilder(b2.get()).forAllStatesExcept(state -> {
                    SlabType type = state.get(SlabBlock.TYPE);
                    switch (type) {
                        case TOP:
                            return ConfiguredModel.builder()
                                    .modelFile(builder3TexturesModel(
                                            "block/" + name.substring(7) + "_top", "block/slab",
                                            modLoc(loc2),
                                            modLoc(loc2),
                                            modLoc(loc2))).rotationX(180).build();

                        case DOUBLE:
                            return ConfiguredModel.builder()
                                    .modelFile(builder(
                                            "block/" + name.substring(7) + "_double",
                                            modLoc(loc2))).build();
                        case BOTTOM:
                        default:
                            return ConfiguredModel.builder()
                                    .modelFile(builder3TexturesModel(
                                            "block/" + name.substring(7), "block/slab",
                                            modLoc(loc2),
                                            modLoc(loc2),
                                            modLoc(loc2))).build();


                    }

                }, StairsBlock.WATERLOGGED);
            }
        }
    }
    public void builderStairs() {
        for (RegistryObject<Block> b2 : RegBlocks.BLOCKS.getEntries()) {
            if (b2.get() instanceof BaseStairs) {
                getVariantBuilder(b2.get())
                        .forAllStatesExcept(state -> {
                            Direction facing = state.get(StairsBlock.FACING);
                            Half half = state.get(StairsBlock.HALF);
                            StairsShape shape = state.get(StairsBlock.SHAPE);
                            int yRot = (int) facing.rotateY().getHorizontalAngle();
                            if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT) {
                                yRot += 270;
                            }
                            if (shape != StairsShape.STRAIGHT && half == Half.TOP) {
                                yRot += 90;
                            }
                            yRot %= 360;
                            boolean uvlock = yRot != 0 || half == Half.TOP;
                            String name = b2.get().getRegistryName().toString();

                            String loc = "block/" + name.substring(7, name.length() - 7);
                            String loc2 = loc.replaceAll(loc, "block/concrete/" + loc.substring(13));
                            switch (shape) {
                                case STRAIGHT:
                                default:
                                    return ConfiguredModel.builder()
                                            .modelFile(builder3TexturesModel(
                                                    "block/" + name.substring(7), "block/stairs", modLoc(loc2), modLoc(loc2), modLoc("block/concrete/concrete_gray")))
                                            .rotationX(half == Half.BOTTOM ? 0 : 180)
                                            .rotationY(yRot)
                                            .uvLock(uvlock)
                                            .build();
                                case INNER_LEFT:
                                case INNER_RIGHT:
                                    return ConfiguredModel.builder()
                                            .modelFile(builder3TexturesModel(
                                                    "block/" + name.substring(7) + "_inner", "block/inner_stairs", modLoc(loc2), modLoc(loc2), modLoc("block/concrete/concrete_gray")))
                                            .rotationX(half == Half.BOTTOM ? 0 : 180)
                                            .rotationY(yRot)
                                            .uvLock(uvlock)
                                            .build();
                                case OUTER_RIGHT:
                                case OUTER_LEFT:
                                    return ConfiguredModel.builder()
                                            .modelFile(builder3TexturesModel(
                                                    "block/" + name.substring(7)+ "_outer", "block/outer_stairs", modLoc(loc2), modLoc(loc2), modLoc("block/concrete/concrete_gray")))
                                            .rotationX(half == Half.BOTTOM ? 0 : 180)
                                            .rotationY(yRot)
                                            .uvLock(uvlock)
                                            .build();
                            }
                        }, StairsBlock.WATERLOGGED);
            }
        }
    }
}



//                                    .modelFile(builderStairsModel(
//                                            "block/" + name.substring(7), "block/outer_stairs", modLoc(loc2), modLoc(loc2), modLoc("block/concrete/concrete_gray")))
//                                    .modelFile(builderStairsModel(
//                                            "block/" + name.substring(7), "block/inner_stairs", modLoc(loc2), modLoc(loc2), modLoc("block/concrete/concrete_gray")))