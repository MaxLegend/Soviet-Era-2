package ru.tesmio.blocks.diesel_generator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;

public class DieselTank extends BlockSideCustomModel {
    private static final VoxelShape NORTH_AABB = VoxelShapes.or(Block.makeCuboidShape(2D, 0D, 3D, 16D, 13.0D, 13D),
            Block.makeCuboidShape(1D, 0D, 4D, 16D, 12.0D, 12D),
            Block.makeCuboidShape(0D, 0D, 5D, 16D, 11.0D, 11D));
    private static final VoxelShape SOUTH_AABB =VoxelShapes.or(Block.makeCuboidShape(0D, 0D, 3D, 14D, 13.0D, 13D),
            Block.makeCuboidShape(1D, 0D, 4D, 15D, 12.0D, 12D),
            Block.makeCuboidShape(0D, 0D, 5D, 16D, 11.0D, 11D));
    private static final VoxelShape WEST_AABB = VoxelShapes.or(Block.makeCuboidShape(13D, 0D, 0D, 3D, 13.0D, 14D),
            Block.makeCuboidShape(12D, 0D, 0D, 4D, 12.0D, 15D),
            Block.makeCuboidShape(11D, 0D, 0D, 5D, 11.0D, 16D));
    private static final VoxelShape EAST_AABB = VoxelShapes.or(Block.makeCuboidShape(13D, 0D, 2D, 3D, 13.0D, 16D),
            Block.makeCuboidShape(12D, 0D, 1D, 4D, 12.0D, 15D),
            Block.makeCuboidShape(11D, 0D, 0D, 5D, 11.0D, 16D));
    public DieselTank(Properties properties) {
        super(properties, 1F);
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (state.get(FACING)) {
            case NORTH:
                return NORTH_AABB;
            case SOUTH:
                return SOUTH_AABB;
            case WEST:
                return WEST_AABB;
            case EAST:
                return EAST_AABB;
        }
        return NORTH_AABB;


    }
}
