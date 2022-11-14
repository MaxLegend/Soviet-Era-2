package ru.tesmio.blocks.fences;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;

import java.util.Random;

public class ElectroFence extends BlockSideCustomModel {
    final VoxelShape BOXS[] = new VoxelShape[] {Block.makeCuboidShape(7D, 0D, 7D, 9D, 16D, 9D)};
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
    public static final BooleanProperty LEFT = BooleanProperty.create("left");
    public static final BooleanProperty LEFT_BACK = BooleanProperty.create("left_back");
    public static final BooleanProperty RIGHT = BooleanProperty.create("right");
    public static final BooleanProperty RIGHT_BACK = BooleanProperty.create("right_back");
    public ElectroFence(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)).with(LEFT_BACK, false).with(RIGHT_BACK, false).with(LEFT, false).with(RIGHT, false).with(POWERED, false));

    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return BOXS[0];
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, LEFT, RIGHT,LEFT_BACK, RIGHT_BACK, POWERED, WATERLOGGED);
    }
    public void neighborChanged(BlockState s, World w, BlockPos p, Block b, BlockPos fromPos, boolean isMoving) {
        BlockState newState = this.getDefaultState();
        BlockState newStateSouth = w.getBlockState(p.south());
        BlockState newStateNorth = w.getBlockState(p.north());
        BlockState newStateEast = w.getBlockState(p.east());
        BlockState newStateWest = w.getBlockState(p.west());
        //разобраться с ошибкой одновременного стейта на соседней позиции, дописать west
        if (!w.isRemote) {
            switch (s.get(FACING)) {
                case NORTH:
                    newState = newState.with(RIGHT, canConnect(w, p, Direction.EAST)).with(LEFT, canConnect(w,p,Direction.WEST));
                    if(w.getBlockState(p.south()).getBlock() instanceof ElectroFence) {
                        if (w.getBlockState(p.south()).get(FACING) == Direction.WEST && !newState.get(LEFT)) {
                            newState = newState.with(LEFT_BACK, true);
                            w.setBlockState(p.south(), newStateSouth.with(RIGHT, true));
                        }
                        if (w.getBlockState(p.south()).get(FACING) == Direction.EAST && !newState.get(RIGHT)) {
                            newState = newState.with(RIGHT_BACK, true);
                            w.setBlockState(p.south(), newStateSouth.with(LEFT, true));
                        }
                    }
                    w.setBlockState(p, newState);
                    return;
                case SOUTH:
                    newState = newState.with(RIGHT, canConnect(w, p, Direction.WEST)).with(LEFT, canConnect(w,p,Direction.EAST));
                    if(w.getBlockState(p.north()).getBlock() instanceof ElectroFence) {
                        if (w.getBlockState(p.north()).get(FACING) == Direction.EAST && !newState.get(LEFT)) {
                            newState = newState.with(LEFT_BACK, true);
                            w.setBlockState(p.north(), newStateNorth.with(RIGHT, true));
                        }
                        if (w.getBlockState(p.north()).get(FACING) == Direction.WEST && !newState.get(RIGHT)) {
                            newState = newState.with(RIGHT_BACK, true);
                            w.setBlockState(p.north(), newStateNorth.with(LEFT, true));
                        }
                    }
                    w.setBlockState(p, newState.with(FACING, Direction.SOUTH));
                    return;
                case WEST:
                    newState = newState.with(RIGHT, canConnect(w, p, Direction.NORTH)).with(LEFT, canConnect(w,p,Direction.SOUTH));
                    if(w.getBlockState(p.east()).getBlock() instanceof ElectroFence) {
                        if (w.getBlockState(p.east()).get(FACING) == Direction.SOUTH && !newState.get(LEFT)) {
                            newState = newState.with(LEFT_BACK, true);
                            w.setBlockState(p.east(), newStateEast.with(RIGHT, true));
                        }
                        if (w.getBlockState(p.east()).get(FACING) == Direction.NORTH && !newState.get(RIGHT)) {
                            newState = newState.with(RIGHT_BACK, true);
                            w.setBlockState(p.east(), newStateEast.with(LEFT, true));
                        }
                    }
                    w.setBlockState(p, newState.with(FACING, Direction.WEST));
                    return;
                case EAST:
                    w.setBlockState(p, s.with(RIGHT, canConnect(w, p, Direction.SOUTH)).with(LEFT, canConnect(w,p,Direction.NORTH)));
            }
        }
    }
    public boolean canConnect(World w, BlockPos p, Direction facing) {
        Block block = w.getBlockState(p.offset(facing)).getBlock();
        if(block instanceof ElectroFence) {

            if(w.getBlockState(p.offset(facing)).get(FACING) == w.getBlockState(p).get(FACING)) return true;

        }
        return false;
    }
    public void tick(BlockState s, ServerWorld w, BlockPos p, Random r) {
        this.neighborChanged(s, w, p, w.getBlockState(p).getBlock(), p, false);
    }
}
