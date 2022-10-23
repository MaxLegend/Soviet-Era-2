package ru.tesmio.blocks.const_panel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;

import javax.annotation.Nullable;

public class PanelBlockSide extends Block {
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST, Direction.UP, Direction.DOWN );
    public PanelBlockSide(Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
    }
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        for(Direction direction : context.getNearestLookingDirections()) {
                switch (direction) {
                    case DOWN:
                        return this.getDefaultState().with(FACING, Direction.DOWN);
                    case UP:
                        return this.getDefaultState().with(FACING, Direction.UP);
                    case NORTH:
                        return this.getDefaultState().with(FACING, Direction.NORTH);
                    case SOUTH:
                       return this.getDefaultState().with(FACING, Direction.SOUTH);
                    case EAST:
                       return this.getDefaultState().with(FACING, Direction.EAST);
                    case WEST:
                        return this.getDefaultState().with(FACING, Direction.WEST);
                }

        }
        return null;
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

}