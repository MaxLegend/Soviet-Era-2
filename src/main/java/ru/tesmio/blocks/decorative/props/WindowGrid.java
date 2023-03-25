package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.baseblock.BlockRotatedAllSideCM;
import ru.tesmio.utils.VoxelShapeUtil;

public class WindowGrid extends BlockRotatedAllSideCM {
    public WindowGrid(Properties builder, float shadingInside) {

        super(builder, shadingInside);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape SHAPE = Block.makeCuboidShape(15.5D, 0D, 0D, 16D, 16D, 16D);
        VoxelShape SHAPE_DOWN = Block.makeCuboidShape(0D, 0D, 0D, 16D, 0.5D, 16D);
        VoxelShape SHAPE_UP = Block.makeCuboidShape(0D, 15.5D, 0D, 16D, 16D, 16D);
        switch (state.get(FACING)) {
            case EAST:
                return SHAPE;
            case WEST:
                return VoxelShapeUtil.shapeRot180(SHAPE);
            case NORTH:
                return VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(SHAPE));
            case SOUTH:
                return VoxelShapeUtil.shapeRotCCW90(SHAPE);
            case DOWN_EAST:
            case DOWN_WEST:
            case DOWN_SOUTH:
            case DOWN_NORTH:
                return SHAPE_DOWN;
            case UP_EAST:
            case UP_WEST:
            case UP_SOUTH:
            case UP_NORTH:
                return SHAPE_UP;

        }
        return VoxelShapes.fullCube();
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        for(Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {

                return this.getDefaultState().with(FACING, EnumOrientation.forFacing(direction, context.getPlacementHorizontalFacing())).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
            } else {

                return this.getDefaultState().with(FACING, EnumOrientation.forFacing(direction, direction)).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
            }
        }
        return this.getDefaultState();
    }
}
