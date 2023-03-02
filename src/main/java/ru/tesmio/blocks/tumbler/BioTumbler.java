package ru.tesmio.blocks.tumbler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.baseblock.BlockSideCustomModelLeveler;

public class BioTumbler extends BlockSideCustomModelLeveler {
    public BioTumbler(Properties properties, float shadingInside) {
        super(properties, 1f);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape defShape = Block.makeCuboidShape(0D, 0D, 0D, 16D, 16D, 14D);
        switch (state.get(FACING)) {
            case NORTH:
                return defShape;
            case WEST:
                return defShape;
            case EAST:
                return defShape;
            case SOUTH:
                return defShape;

        }
        return defShape;
    }
}
