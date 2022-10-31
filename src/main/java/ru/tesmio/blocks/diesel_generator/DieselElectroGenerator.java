package ru.tesmio.blocks.diesel_generator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.BlockSideCustomModel;

public class DieselElectroGenerator extends BlockSideCustomModel {
    private static final VoxelShape NORTH_AABB = VoxelShapes.or(Block.makeCuboidShape(2D, 1D, 3D, 13D, 5.0D, 13D),
            Block.makeCuboidShape(2D, 0D, 4D, 13D, 7.0D, 12D),
            Block.makeCuboidShape(2D, 0D, 6D, 13D, 8.0D, 10D),
            Block.makeCuboidShape(2D, 0D, 5D, 14D, 6.0D, 11D),
            Block.makeCuboidShape(0D, 0D, 6D, 16D, 6.0D, 10D),
            Block.makeCuboidShape(0D, 1D, 5D, 16D, 5.0D, 11D));
    private static final VoxelShape SOUTH_AABB =VoxelShapes.or(Block.makeCuboidShape(3D, 1D, 3D, 14D, 5.0D, 13D),
            Block.makeCuboidShape(3D, 0D, 4D, 14D, 7.0D, 12D),
            Block.makeCuboidShape(3D, 0D, 6D, 14D, 8.0D, 10D),
            Block.makeCuboidShape(2D, 0D, 5D, 14D, 6.0D, 11D),
            Block.makeCuboidShape(0D, 0D, 6D, 16D, 6.0D, 10D),
            Block.makeCuboidShape(0D, 1D, 5D, 16D, 5.0D, 11D));
    private static final VoxelShape WEST_AABB = VoxelShapes.or(Block.makeCuboidShape(13D, 1D, 14D, 3D, 5.0D, 3D),
            Block.makeCuboidShape(12D, 0D, 14D, 4D, 7.0D, 3D),
            Block.makeCuboidShape(10D, 0D, 14D, 6D, 8.0D, 3D),
            Block.makeCuboidShape(10D, 0D, 16D, 6D, 6.0D, 0D),
            Block.makeCuboidShape(11D, 0D, 2D, 5D, 6.0D, 10D),
            Block.makeCuboidShape(11D, 1D, 0D, 5D, 5.0D, 16D));
    private static final VoxelShape EAST_AABB = VoxelShapes.or(Block.makeCuboidShape(13D, 1D, 13D, 3D, 5.0D, 2D),
            Block.makeCuboidShape(12D, 0D, 13D, 4D, 7.0D, 2D),
            Block.makeCuboidShape(10D, 0D, 13D, 6D, 8.0D, 2D),
            Block.makeCuboidShape(10D, 0D, 16D, 6D, 6.0D, 0D),
            Block.makeCuboidShape(11D, 0D, 2D, 5D, 6.0D, 10D),
            Block.makeCuboidShape(11D, 1D, 0D, 5D, 5.0D, 16D));
    public DieselElectroGenerator(Properties properties) {
        super(properties);
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
