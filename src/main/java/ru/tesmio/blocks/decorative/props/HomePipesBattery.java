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

public class HomePipesBattery extends BlockSideCustomModel {
    public HomePipesBattery(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape BOX = Block.makeCuboidShape(0,0,12,16,16,16);
        switch (state.get(FACING)) {
            case NORTH:
                return  BOX;
            case SOUTH:
                return VoxelShapeUtil.shapeRot180(BOX);
            case WEST:
                return VoxelShapeUtil.shapeRotCW90(BOX);
            case EAST:
                return VoxelShapeUtil.shapeRotCCW90(BOX);
        }
    return VoxelShapes.fullCube();
    }
}
