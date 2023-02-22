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

public class Sink extends BlockSideCustomModel {
    public Sink(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case EAST:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(1.5,12,0,14.5,15,11)),
                        VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(2.5,12,10,13.5,15,12)),
                        VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(3.5,12,10,12.5,15,13)),
                        VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(2.5,11,0,13.5,12,12))
                );
            case WEST:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(1.5,12,0,14.5,15,11)),
                        VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(2.5,12,10,13.5,15,12)),
                        VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(3.5,12,10,12.5,15,13)),
                        VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(2.5,11,0,13.5,12,12))
                );
            case NORTH:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(1.5,12,0,14.5,15,11)),
                        VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(2.5,12,10,13.5,15,12)),
                        VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(3.5,12,10,12.5,15,13)),
                        VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(2.5,11,0,13.5,12,12))
                );
            case SOUTH:
                return VoxelShapes.or(
                        Block.makeCuboidShape(1.5,12,0,14.5,15,11),
                        Block.makeCuboidShape(2.5,12,10,13.5,15,12),
                        Block.makeCuboidShape(3.5,12,10,12.5,15,13),
                        Block.makeCuboidShape(2.5,11,0,13.5,12,12)
                );
        }
        return VoxelShapes.fullCube();
    }
}
