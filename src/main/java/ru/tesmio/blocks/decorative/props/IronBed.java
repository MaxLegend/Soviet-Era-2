package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.properties.BedPart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.baseblock.BlockBed;
import ru.tesmio.utils.VoxelShapeUtil;

public class IronBed extends BlockBed {

    public IronBed(Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape[] SHAPES = new VoxelShape[]{
                Block.makeCuboidShape(0.0D, 5.0D, 0.0D, 16.0D, 6.0D, 16.0D),
                Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D),
                Block.makeCuboidShape(0.0D, 6.0D, 0.5D, 16.0D, 9.0D, 15.5D)
        };
        switch (state.get(HORIZONTAL_FACING)) {
            case EAST:
                if(state.get(PART) == BedPart.HEAD) {
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRot180(SHAPES[0]),
                            VoxelShapeUtil.shapeRot180(SHAPES[1]),
                            VoxelShapeUtil.shapeRot180(SHAPES[2]));
                }
                return VoxelShapes.or(SHAPES[0],SHAPES[1],SHAPES[2]);
            case NORTH:
                if(state.get(PART) == BedPart.HEAD) {
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCCW90(SHAPES[0]),
                            VoxelShapeUtil.shapeRotCCW90(SHAPES[1]),
                            VoxelShapeUtil.shapeRotCCW90(SHAPES[2]));
                }
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRotCW90( VoxelShapeUtil.shapeRot180(SHAPES[0])),
                        VoxelShapeUtil.shapeRotCW90( VoxelShapeUtil.shapeRot180(SHAPES[1])),
                        VoxelShapeUtil.shapeRotCW90(VoxelShapeUtil.shapeRot180(SHAPES[2])));
            case SOUTH:
                if(state.get(PART) == BedPart.FOOT) {
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCW90(SHAPES[0]),
                            VoxelShapeUtil.shapeRotCW90(SHAPES[1]),
                            VoxelShapeUtil.shapeRotCW90(SHAPES[2]));

                }
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRotCCW90(VoxelShapeUtil.shapeRot180(SHAPES[0])),
                        VoxelShapeUtil.shapeRotCCW90(VoxelShapeUtil.shapeRot180(SHAPES[1])),
                        VoxelShapeUtil.shapeRotCCW90(VoxelShapeUtil.shapeRot180(SHAPES[2])));
            case WEST:
                if(state.get(PART) == BedPart.FOOT) {
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRot180(SHAPES[0]),
                            VoxelShapeUtil.shapeRot180(SHAPES[1]),
                            VoxelShapeUtil.shapeRot180(SHAPES[2]));
                }
                return VoxelShapes.or(SHAPES[0],SHAPES[1],SHAPES[2]);
        }
        return VoxelShapes.fullCube();
    }

}
