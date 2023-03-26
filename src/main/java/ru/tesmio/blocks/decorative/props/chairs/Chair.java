package ru.tesmio.blocks.decorative.props.chairs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.baseblock.SittableBlock;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.utils.VoxelShapeUtil;

public class Chair extends SittableBlock {
    public Chair(Properties properties, float shadingInside) {
        super(properties, 1F);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape SHAPES[] = new VoxelShape[]{
                Block.makeCuboidShape(0, 0, 2, 1.5, 8, 15),
                Block.makeCuboidShape(14.5, 0, 2, 16, 8, 15),
                Block.makeCuboidShape(1.5, 6, 1, 14.5, 7, 9),
                Block.makeCuboidShape(0, 0, 12, 16, 16, 15)
        };
        if(state.getBlock() == RegBlocks.PINK_CHAIR.get()) {
            switch (state.get(FACING)) {
                case EAST:
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCW90(SHAPES[0]),
                            VoxelShapeUtil.shapeRotCW90(SHAPES[1]),
                            VoxelShapeUtil.shapeRotCW90( SHAPES[2]),
                            VoxelShapeUtil.shapeRotCW90(SHAPES[3]));
                case WEST:
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCCW90(SHAPES[0]),
                            VoxelShapeUtil.shapeRotCCW90(SHAPES[1]),
                            VoxelShapeUtil.shapeRotCCW90( SHAPES[2]),
                            VoxelShapeUtil.shapeRotCCW90(SHAPES[3]));
                case NORTH:
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRot180(SHAPES[0]),
                            VoxelShapeUtil.shapeRot180(SHAPES[1]),
                            VoxelShapeUtil.shapeRot180( SHAPES[2]),
                            VoxelShapeUtil.shapeRot180(SHAPES[3]));
                case SOUTH:
                    return VoxelShapes.or(SHAPES[0],SHAPES[1],SHAPES[2],SHAPES[3]);
            }
        }
        return VoxelShapes.fullCube();
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }

}

