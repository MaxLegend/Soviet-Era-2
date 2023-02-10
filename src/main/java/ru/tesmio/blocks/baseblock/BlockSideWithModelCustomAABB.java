package ru.tesmio.blocks.baseblock;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class BlockSideWithModelCustomAABB extends BlockSideCustomModel {

    public final VoxelShape BOXES[];
    public final boolean isOne;
    public BlockSideWithModelCustomAABB(Properties properties, VoxelShape[] boxes, boolean isOne, float shadingInside) {
        super(properties,shadingInside);
        this.BOXES = boxes;
        this.isOne = isOne;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if(!isOne) {
            switch (state.get(FACING)) {
                case NORTH:
                default:
                    return BOXES[0];
                case SOUTH:
                    return BOXES[1];
                case WEST:
                    return BOXES[2];
                case EAST:
                    return BOXES[3];
            }
        }
        else
        return BOXES[0];
    }

    public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return this.getShape(state, worldIn, pos, null);
    }

    public VoxelShape getRayTraceShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, reader, pos, context);
    }
}
