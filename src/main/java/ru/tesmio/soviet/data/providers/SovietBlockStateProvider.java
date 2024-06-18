package ru.tesmio.soviet.data.providers;

import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.Half;
import net.minecraft.state.properties.StairsShape;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import ru.tesmio.soviet.blocks.baseblock.BlockForFacing;
import ru.tesmio.soviet.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.soviet.blocks.decorative.props.ToxicAir;
import ru.tesmio.soviet.blocks.decorative.props.WindProofPanel;
import ru.tesmio.soviet.blocks.decorative.slabs.BaseSlab;
import ru.tesmio.soviet.blocks.decorative.stairs.BaseStairs;
import ru.tesmio.soviet.core.Core;
import ru.tesmio.soviet.reg.RegBlocks;
import ru.tesmio.soviet.reg.RegFluids;

import java.util.concurrent.atomic.AtomicInteger;

public class SovietBlockStateProvider extends BlockStateProvider {
    public SovietBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Core.MODID, exFileHelper);
    }


    @Override
    protected void registerStatesAndModels() {

        variantBuilderAll();
        //  builderSlabs();
        builderStairs();
        blockWithCustomModelBuilder();
    }

    public void blockRotAxisCustomModelBuilder() {
        AtomicInteger down = new AtomicInteger();
        AtomicInteger up = new AtomicInteger();
        for (RegistryObject<Block> block : RegBlocks.BLOCKS_CUSTOM_MODELS_COLORED.getEntries()) {
            if (block.get() instanceof BaseSlab) {
                String name = block.get().getRegistryName().toString();
                String loc2 = name.replaceAll(name, "block/concrete/concrete" + name.substring(26));
                getVariantBuilder(block.get())
                        .forAllStatesExcept(state -> {
                            BlockForFacing.EnumOrientation enumOrient = state.get(BlockForFacing.FACING);
                            Direction orient = enumOrient.getDirection();
                            Direction.Axis ax = orient.getAxis();
                            down.set(orient.getDirectionVec().getY() * 90);
                            up.set(orient.getDirectionVec().getY() * (-270));
                            return ConfiguredModel.builder()
                                    .modelFile(builderForParent("block/" + name.substring(7), "soviet:block/structural/slab", modLoc(loc2), "0"))
                                    .rotationY(orient.getAxis().isVertical() ? 0 : (((int) orient.getHorizontalAngle())) % 360)
                                    .rotationX(orient.getDirectionVec().getY() == 1 ? down.get() : up.get())
                                    .build();
                        }, BlockSideCustomModel.WATERLOGGED);
            }
        }
    }

    public void blockWithCustomModelBuilder() {
        AtomicInteger down = new AtomicInteger();
        AtomicInteger up = new AtomicInteger();
        for (RegistryObject<Block> block : RegBlocks.BLOCKS_CUSTOM_MODELS_COLORED.getEntries()) {

            if (block.get() instanceof WindProofPanel) {
                String name = block.get().getRegistryName().toString();
                String loc2 = name.replaceAll(name, "block/concrete/concrete" + name.substring(43));
                getVariantBuilder(block.get())
                        .forAllStatesExcept(state -> {
                            Direction dir = state.get(BlockSideCustomModel.FACING);
                            return ConfiguredModel.builder()
                                    .modelFile(builderForParent("block/" + name.substring(7), "soviet:block/outerdeco/streetdeco/windproof_beton", modLoc(loc2), "0"))
                                    .rotationY(dir.getAxis().isVertical() ? 0 : (((int) dir.getHorizontalAngle())) % 360)
                                    .build();
                        }, BlockSideCustomModel.WATERLOGGED);
            }

            if (block.get() instanceof BaseSlab) {
                String name = block.get().getRegistryName().toString();
                String loc2 = name.replaceAll(name, "block/concrete/concrete" + name.substring(26));
                getVariantBuilder(block.get())
                        .forAllStatesExcept(state -> {
                            BlockForFacing.EnumOrientation enumOrient = state.get(BlockForFacing.FACING);
                            Direction orient = enumOrient.getDirection();
                            down.set(orient.getDirectionVec().getY() * 90);
                            up.set(orient.getDirectionVec().getY() * (-270));
                            return ConfiguredModel.builder()
                                    .modelFile(builderForParent("block/" + name.substring(7), "soviet:block/structural/slab", modLoc(loc2), "0"))
                                    .rotationY(orient.getAxis().isVertical() ? 0 : (((int) orient.getHorizontalAngle())) % 360)
                                    .rotationX(orient.getDirectionVec().getY() == 1 ? down.get() : up.get())
                                    .build();
                        }, BlockSideCustomModel.WATERLOGGED);
            }
        }
    }

    public ModelBuilder<BlockModelBuilder> builder(String name, ResourceLocation rs) {
        return models().cubeAll(name, rs);
    }

    public void variantBuilderAll() {
        for (RegistryObject<Block> b2 : RegBlocks.BLOCKS.getEntries()) {
            String name = b2.get().getRegistryName().toString();
            if (!(b2.get() instanceof ToxicAir) && !(b2.get() instanceof BaseStairs) && !(b2.get() instanceof BaseSlab) && !(b2.get() == RegFluids.TOXIC_WATER_BLOCK.get()))
                getVariantBuilder(b2.get()).forAllStates(state -> ConfiguredModel.builder().modelFile(builder("block/" + name.substring(7), modLoc("block/" + name.substring(7)))).build());

        }
    }

    public ModelBuilder<BlockModelBuilder> builderForParent(String name, String parent, ResourceLocation color, String textureKey) {
        return models().withExistingParent(name, parent)
                .texture(textureKey, color);
    }

    public ModelBuilder<BlockModelBuilder> builder3TexturesModel(String name, String parent, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        return models().withExistingParent(name, parent)
                .texture("side", side)
                .texture("bottom", bottom)
                .texture("top", top);
    }


    //    public void builderSlabs() {
//        for (RegistryObject<Block> b2 : RegBlocks.BLOCKS.getEntries()) {
//            if (b2.get() instanceof BaseSlab) {
//                String name = b2.get().getRegistryName().toString();
//                String loc = "block/" + name.substring(6, name.length() - 5);
//                String loc2 = loc.replaceAll(loc, "block/concrete/" + loc.substring(13));
//                getVariantBuilder(b2.get()).forAllStatesExcept(state -> {
//                    SlabType type = state.get(SlabBlock.TYPE);
//                    switch (type) {
//                        case TOP:
//                            return ConfiguredModel.builder()
//                                    .modelFile(builder3TexturesModel(
//                                            "block/" + name.substring(7) + "_top", "block/slab",
//                                            modLoc(loc2),
//                                            modLoc(loc2),
//                                            modLoc(loc2))).rotationX(180).build();
//
//                        case DOUBLE:
//                            return ConfiguredModel.builder()
//                                    .modelFile(builder(
//                                            "block/" + name.substring(7) + "_double",
//                                            modLoc(loc2))).build();
//                        case BOTTOM:
//                        default:
//                            return ConfiguredModel.builder()
//                                    .modelFile(builder3TexturesModel(
//                                            "block/" + name.substring(7), "block/slab",
//                                            modLoc(loc2),
//                                            modLoc(loc2),
//                                            modLoc(loc2))).build();
//
//
//                    }
//
//                }, StairsBlock.WATERLOGGED);
//            }
//        }
//    }
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
                                                    "block/" + name.substring(7) + "_outer", "block/outer_stairs", modLoc(loc2), modLoc(loc2), modLoc("block/concrete/concrete_gray")))
                                            .rotationX(half == Half.BOTTOM ? 0 : 180)
                                            .rotationY(yRot)
                                            .uvLock(uvlock)
                                            .build();
                            }
                        }, StairsBlock.WATERLOGGED);
            }
        }
    }

    public void builderVault() {
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
                                                    "block/" + name.substring(7), "block/structural/vault_block", modLoc(loc2), modLoc(loc2), modLoc("block/concrete/concrete_gray")))
                                            .rotationX(half == Half.BOTTOM ? 0 : 180)
                                            .rotationY(yRot)
                                            .uvLock(uvlock)
                                            .build();
                                case INNER_LEFT:
                                case INNER_RIGHT:
                                    return ConfiguredModel.builder()
                                            .modelFile(builder3TexturesModel(
                                                    "block/" + name.substring(7) + "_inner", "block/structural/inner_vault_block", modLoc(loc2), modLoc(loc2), modLoc("block/concrete/concrete_gray")))
                                            .rotationX(half == Half.BOTTOM ? 0 : 180)
                                            .rotationY(yRot)
                                            .uvLock(uvlock)
                                            .build();
                                case OUTER_RIGHT:
                                case OUTER_LEFT:
                                    return ConfiguredModel.builder()
                                            .modelFile(builder3TexturesModel(
                                                    "block/" + name.substring(7) + "_outer", "block/structural/outer_vault_block", modLoc(loc2), modLoc(loc2), modLoc("block/concrete/concrete_gray")))
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