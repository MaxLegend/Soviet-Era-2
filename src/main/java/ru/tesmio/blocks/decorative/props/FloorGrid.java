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
import ru.tesmio.blocks.baseblock.BlockRotatedAxisCustomModel;
import ru.tesmio.utils.VoxelShapeUtil;

public class FloorGrid extends BlockRotatedAxisCustomModel {
    VoxelShape SHP = Block.makeCuboidShape(0,0,0,16,2,16);
    VoxelShape SHP2 = Block.makeCuboidShape(0,14,0,16,16,16);
    VoxelShape SHP3 = Block.makeCuboidShape(0,0,0,2,16,16);
    public FloorGrid(Properties builder, float shadingInside) {
        super(builder, shadingInside);
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        for(Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {
                return this.getDefaultState().with(FACING, EnumOrientation.forFacing(direction.getOpposite(), context.getPlacementHorizontalFacing().getOpposite())).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
            } else {
                return this.getDefaultState().with(FACING, EnumOrientation.forFacing(direction.getOpposite(), direction.getOpposite())).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
            }
        }
        return this.getDefaultState();
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (state.get(FACING)) {
            case NORTH:
                return VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(SHP3));
            case SOUTH:
                return VoxelShapeUtil.shapeRotCCW90(SHP3);
            case EAST:
                return SHP3;
            case WEST:
                return VoxelShapeUtil.shapeRot180(SHP3);
            case UP_X:
            case UP_Z:
                return SHP;
            case DOWN_X:
            case DOWN_Z:
                return SHP2;
        }
        return VoxelShapes.fullCube();
    }
}
