package ru.tesmio.blocks.decorative.props;

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
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;

public class Autoclave extends BlockSideCustomModel {
    public Autoclave(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }
    VoxelShape[] BOXS = new VoxelShape[]{
            Block.makeCuboidShape(0,0,0,16,1,16),
            Block.makeCuboidShape(0,3,5,16,13,11),
            Block.makeCuboidShape(1,3,3,15,13,13),
            Block.makeCuboidShape(2,3,2,14,13,14),
            Block.makeCuboidShape(3,3,1,13,13,15),
            Block.makeCuboidShape(5,3,0,11,13,16),
            //верхнее сужение
            Block.makeCuboidShape(1,13,6,15,15.25,10),
            Block.makeCuboidShape(2,13,4,14,15.25,12),
            Block.makeCuboidShape(3,13,3,13,15.25,13),
            Block.makeCuboidShape(4,13,2,12,15.25,14),
            Block.makeCuboidShape(5,13,1,11,15.25,15),
            //крышка
            Block.makeCuboidShape(6,15,3,10,16,13),
            Block.makeCuboidShape(4,15,4,12,16,12),
            Block.makeCuboidShape(3,15,6,13,16,10),
    };
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (state.get(FACING)) {
            case WEST:

            case SOUTH:

            case EAST:

            case NORTH:
                return VoxelShapes.or(BOXS[0],BOXS[1],BOXS[2],BOXS[3],BOXS[4],BOXS[5],BOXS[6],BOXS[7],BOXS[8],BOXS[9],BOXS[10],BOXS[11],BOXS[12],BOXS[13]);

        }

        return VoxelShapes.fullCube();
    }
}
