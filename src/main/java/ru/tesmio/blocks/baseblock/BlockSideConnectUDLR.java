package ru.tesmio.blocks.baseblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class BlockSideConnectUDLR extends BlockSideCustomModel {
    public static final BooleanProperty PANE_UP = BooleanProperty.create("up");
    public static final BooleanProperty PANE_DOWN = BooleanProperty.create("down");
    public static final BooleanProperty PANE_LEFT = BooleanProperty.create("left");
    public static final BooleanProperty PANE_RIGHT = BooleanProperty.create("right");

    public BlockSideConnectUDLR(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(PANE_UP, false)
                .with(PANE_DOWN, false)
                .with(PANE_RIGHT, false)
                .with(PANE_LEFT, false).with(WATERLOGGED, Boolean.valueOf(false)));
    }
    private boolean isBlockConnect(IWorld w, BlockPos pos) {
        Block block = w.getBlockState(pos).getBlock();
        return block instanceof BlockSideConnectUDLR;
    }
    public BlockState updateState(IWorld w, BlockPos p, BlockState s) {
        if (!w.isRemote()) {
            s = s.with(PANE_UP, this.isBlockConnect(w, p.up())).with(FACING, s.get(FACING)).with(PANE_LEFT, s.get(PANE_LEFT)).with(PANE_RIGHT, s.get(PANE_RIGHT));
            s = s.with(PANE_DOWN, this.isBlockConnect(w, p.down())).with(FACING, s.get(FACING)).with(PANE_LEFT, s.get(PANE_LEFT)).with(PANE_RIGHT, s.get(PANE_RIGHT));
            if(s.get(FACING) == Direction.EAST || s.get(FACING) == Direction.WEST) {
                s = s.with(PANE_RIGHT, this.isBlockConnect(w, p.north())).with(FACING, s.get(FACING)).with(PANE_LEFT, s.get(PANE_LEFT)).with(PANE_DOWN, s.get(PANE_DOWN));
                s = s.with(PANE_LEFT, this.isBlockConnect(w, p.south())).with(FACING, s.get(FACING)).with(PANE_RIGHT, s.get(PANE_RIGHT)).with(PANE_DOWN, s.get(PANE_DOWN));
            }

            if(s.get(FACING) == Direction.NORTH || s.get(FACING) == Direction.SOUTH) {
                s = s.with(PANE_RIGHT, this.isBlockConnect(w, p.west())).with(FACING, s.get(FACING)).with(PANE_LEFT, s.get(PANE_LEFT)).with(PANE_DOWN, s.get(PANE_DOWN));
                s = s.with(PANE_RIGHT, this.isBlockConnect(w, p.east())).with(FACING, s.get(FACING)).with(PANE_LEFT, s.get(PANE_LEFT)).with(PANE_DOWN, s.get(PANE_DOWN));
            }

        }
        return s;
    }
    @Override
    public BlockState updatePostPlacement(BlockState s, Direction f, BlockState bs, IWorld w, BlockPos p, BlockPos facingPos) {
        return updateState(w,p,s);
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING,PANE_DOWN, PANE_UP, PANE_LEFT, PANE_RIGHT, WATERLOGGED);
    }
}
