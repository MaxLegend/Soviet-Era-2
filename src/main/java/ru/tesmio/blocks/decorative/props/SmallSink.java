package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.utils.VoxelShapeUtil;

public class SmallSink extends BlockSideCustomModel {
    public SmallSink(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case EAST:
                return VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(3,10,0,13,15,10));
            case WEST:
                return VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(3,10,0,13,15,10));
            case NORTH:
                return VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(3,10,0,13,15,10));
            case SOUTH:
                return VoxelShapes.or(
                        Block.makeCuboidShape(3,10,0,13,15,10)
                );
        }
        return VoxelShapes.fullCube();
    }
}
