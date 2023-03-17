package ru.tesmio.blocks.baseblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Rotation;

public class BlockRotatedAllSide extends Block {
    public static final EnumProperty<EnumOrientation> FACING = EnumProperty.create("facing", EnumOrientation.class);

    public BlockRotatedAllSide(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, EnumOrientation.NORTH));
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        switch (rot)
        {
            case CLOCKWISE_180:
                switch (state.get(FACING))
                {
                    case EAST:
                        return state.with(FACING, EnumOrientation.WEST);
                    case WEST:
                        return state.with(FACING, EnumOrientation.EAST);
                    case SOUTH:
                        return state.with(FACING, EnumOrientation.NORTH);
                    case NORTH:
                        return state.with(FACING, EnumOrientation.SOUTH);
                    default:
                        return state;
                }

            case COUNTERCLOCKWISE_90:
                switch (state.get(FACING))
                {
                    case EAST:
                        return state.with(FACING, EnumOrientation.SOUTH);
                    case WEST:
                        return state.with(FACING, EnumOrientation.NORTH);
                    case SOUTH:
                        return state.with(FACING, EnumOrientation.WEST);
                    case NORTH:
                        return state.with(FACING, EnumOrientation.EAST);
                    case UP_EAST:
                        return state.with(FACING, EnumOrientation.UP_EAST);
                    case UP_WEST:
                        return state.with(FACING, EnumOrientation.UP_WEST);
                    case UP_NORTH:
                        return state.with(FACING, EnumOrientation.UP_NORTH);
                    case UP_SOUTH:
                        return state.with(FACING, EnumOrientation.UP_SOUTH);
                    case DOWN_EAST:
                        return state.with(FACING, EnumOrientation.DOWN_EAST);
                    case DOWN_WEST:
                        return state.with(FACING, EnumOrientation.DOWN_WEST);
                    case DOWN_NORTH:
                        return state.with(FACING, EnumOrientation.DOWN_NORTH);
                    case DOWN_SOUTH:
                        return state.with(FACING, EnumOrientation.DOWN_SOUTH);
                }
            case CLOCKWISE_90:
                switch (state.get(FACING))
                {
                    case EAST:
                        return state.with(FACING, EnumOrientation.SOUTH);
                    case WEST:
                        return state.with(FACING, EnumOrientation.NORTH);
                    case SOUTH:
                        return state.with(FACING, EnumOrientation.WEST);
                    case NORTH:
                        return state.with(FACING, EnumOrientation.EAST);
                    case UP_EAST:
                        return state.with(FACING, EnumOrientation.UP_EAST);
                    case UP_WEST:
                        return state.with(FACING, EnumOrientation.UP_WEST);
                    case UP_NORTH:
                        return state.with(FACING, EnumOrientation.UP_NORTH);
                    case UP_SOUTH:
                        return state.with(FACING, EnumOrientation.UP_SOUTH);
                    case DOWN_EAST:
                        return state.with(FACING, EnumOrientation.DOWN_EAST);
                    case DOWN_WEST:
                        return state.with(FACING, EnumOrientation.DOWN_WEST);
                    case DOWN_NORTH:
                        return state.with(FACING, EnumOrientation.DOWN_NORTH);
                    case DOWN_SOUTH:
                        return state.with(FACING, EnumOrientation.DOWN_SOUTH);
                }

            default:
                return state;
        }
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        for(Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {
                return this.getDefaultState().with(FACING, EnumOrientation.forFacing(direction, context.getPlacementHorizontalFacing()));
            } else {

                return this.getDefaultState().with(FACING, EnumOrientation.forFacing(direction, direction));
            }
        }
        return this.getDefaultState();
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public enum EnumOrientation implements IStringSerializable
    {
        UP_EAST("up_e", Direction.UP),
        UP_WEST("up_w", Direction.UP),
        UP_SOUTH("up_s", Direction.UP),
        UP_NORTH("up_n", Direction.UP),
        DOWN_EAST("down_e", Direction.DOWN),
        DOWN_WEST( "down_w", Direction.DOWN),
        DOWN_SOUTH("down_s", Direction.DOWN),
        DOWN_NORTH( "down_n", Direction.DOWN),
        EAST("east", Direction.EAST),
        WEST("west", Direction.WEST),
        SOUTH("south", Direction.SOUTH),
        NORTH("north", Direction.NORTH);

        private final String name;
        private final Direction dir;
        EnumOrientation(String name, Direction dir) {
            this.name = name;
            this.dir = dir;
        }

        @Override
        public String getString() {
            return this.name;
        }
        public Direction getDirection()
        {
            return this.dir;
        }
        public static EnumOrientation forFacing(Direction clickedSide, Direction entityFacing) {
            switch (clickedSide)
            {
                case DOWN:
                    switch (entityFacing)
                    {
                        case EAST:
                            return DOWN_EAST;
                        case WEST:
                            return DOWN_WEST;
                        case SOUTH:
                            return DOWN_SOUTH;
                        case NORTH:
                        default:
                            return DOWN_NORTH;
                    }

                case UP:
                    switch (entityFacing)
                    {
                        case EAST:
                            return UP_EAST;
                        case WEST:
                            return UP_WEST;
                        case SOUTH:
                            return UP_SOUTH;
                        case NORTH:
                        default:
                            return UP_NORTH;
                    }

                case NORTH:
                    return NORTH;
                case SOUTH:
                    return SOUTH;
                case WEST:
                    return WEST;
                case EAST:
                    return EAST;
                default:
                    throw new IllegalArgumentException("Invalid facing: " + clickedSide);
            }
        }
    }
}
