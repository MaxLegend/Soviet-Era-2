package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.utils.VoxelShapeUtil;

public class Televisor extends BlockSideCustomModel {
    final VoxelShape BOX = Block.makeCuboidShape(3,0.5,0,13,13.5,16);
    public Televisor(Properties properties, float shadingInside) {
        super(properties, shadingInside);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)));
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (state.get(FACING)) {
            case EAST:
                return BOX;
            case WEST:
                return VoxelShapeUtil.shapeRot180(BOX);
            case NORTH:
                return VoxelShapeUtil.shapeRotCW90(BOX);
            case SOUTH:
                return VoxelShapeUtil.shapeRotCCW90(BOX);
        }
    return VoxelShapes.fullCube();
    }
}
