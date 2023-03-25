package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.baseblock.BlockRotatedAllSideCM;
import ru.tesmio.reg.RegBlocks;

public class BlockMoss extends BlockRotatedAllSideCM {
    public BlockMoss() {
        super(AbstractBlock.Properties.create(Material.TALL_PLANTS)
                .hardnessAndResistance(0,0)
                .notSolid().sound(SoundType.WET_GRASS), 1f);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, EnumOrientation.NORTH).with(WATERLOGGED, Boolean.valueOf(false)));

    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if(state.getBlock() == RegBlocks.BLOCK_MOSS_FULL.get()) {
            switch (state.get(FACING)) {
                case EAST:
                    return Block.makeCuboidShape(15.25, 0, 0, 16, 16, 16);
                case WEST:
                    return Block.makeCuboidShape(0, 0, 0, 0.75, 16, 16);
                case SOUTH:
                    return Block.makeCuboidShape(0, 0, 15.25, 16, 16, 16);
                case NORTH:
                    return Block.makeCuboidShape(0, 0, 0, 16, 16, 0.75);
                case UP_EAST:
                case UP_WEST:
                case UP_SOUTH:
                case UP_NORTH:
                    return Block.makeCuboidShape(0, 15.25, 0, 16, 16, 16);
                case DOWN_EAST:
                case DOWN_WEST:
                case DOWN_SOUTH:
                case DOWN_NORTH:
                    return Block.makeCuboidShape(0, 0, 0, 16, 0.75, 16);
            }
        }
        if(state.getBlock() == RegBlocks.BLOCK_MOSS.get()) {
            switch (state.get(FACING)) {
                case EAST:
                    return Block.makeCuboidShape(15.99, 0, 0, 16, 16, 16);
                case WEST:
                    return Block.makeCuboidShape(0, 0, 0, 0.01, 16, 16);
                case SOUTH:
                    return Block.makeCuboidShape(0, 0, 15.99, 16, 16, 16);
                case NORTH:
                    return Block.makeCuboidShape(0, 0, 0, 16, 16, 0.01);
                case UP_EAST:
                case UP_WEST:
                case UP_SOUTH:
                case UP_NORTH:
                    return Block.makeCuboidShape(0, 15.99, 0, 16, 16, 16);
                case DOWN_EAST:
                case DOWN_WEST:
                case DOWN_SOUTH:
                case DOWN_NORTH:
                    return Block.makeCuboidShape(0, 0, 0, 16, 0.01, 16);
            }
        }
        if(state.getBlock() == RegBlocks.BLOCK_MOULD.get()) {
            switch (state.get(FACING)) {
                case EAST:
                    return Block.makeCuboidShape(15.5, 0, 0, 16, 16, 16);
                case WEST:
                    return Block.makeCuboidShape(0, 0, 0, 0.5, 16, 16);
                case SOUTH:
                    return Block.makeCuboidShape(0, 0, 15.5, 16, 16, 16);
                case NORTH:
                    return Block.makeCuboidShape(0, 0, 0, 16, 16, 0.5);
                case UP_EAST:
                case UP_WEST:
                case UP_SOUTH:
                case UP_NORTH:
                    return Block.makeCuboidShape(0, 15.5, 0, 16, 16, 16);
                case DOWN_EAST:
                case DOWN_WEST:
                case DOWN_SOUTH:
                case DOWN_NORTH:
                    return Block.makeCuboidShape(0, 0, 0, 16, 0.5, 16);
            }
        }
        return VoxelShapes.fullCube();
    }
}
