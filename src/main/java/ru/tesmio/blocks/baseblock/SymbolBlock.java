package ru.tesmio.blocks.baseblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.utils.VoxelShapeUtil;

//необходимо сделать коллизию, доделать все блокстейты, доделать все пути к текстурам в моделях, добавить восклицательный знак и тире
public class SymbolBlock extends BlockSideCustomModel{
    public SymbolBlock(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }
    public VoxelShape getShape(BlockState s, IBlockReader w, BlockPos p, ISelectionContext x) {
        VoxelShape[] SHAPES = new VoxelShape[]{
                Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 3.0D, 15.0D, 15.0D),
                Block.makeCuboidShape(1.0D, 0.0D, 2.0D, 11.0D, 8.0D, 14.0D)
        };

        switch (s.get(FACING)) {
            case EAST:
                return VoxelShapes.or(SHAPES[0],SHAPES[1]);
            case NORTH:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(SHAPES[0])),
                        VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(SHAPES[1])));
            case WEST:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRot180(SHAPES[0]),
                        VoxelShapeUtil.shapeRot180(SHAPES[1]));
            case SOUTH:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRotCW90(SHAPES[0]),
                        VoxelShapeUtil.shapeRotCW90(SHAPES[1]));

        }
        return VoxelShapes.fullCube();
    }
}
