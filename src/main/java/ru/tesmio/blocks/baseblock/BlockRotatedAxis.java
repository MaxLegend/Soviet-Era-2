package ru.tesmio.blocks.baseblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Rotation;

public class BlockRotatedAxis extends Block {
    public static final EnumProperty<EnumOrientation> FACING = EnumProperty.create("facing", EnumOrientation.class);

    public BlockRotatedAxis(Properties builder) {
        super(builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, EnumOrientation.NORTH));
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
                        return state.with(FACING, EnumOrientation.NORTH);
                    case WEST:
                        return state.with(FACING, EnumOrientation.SOUTH);
                    case SOUTH:
                        return state.with(FACING, EnumOrientation.EAST);
                    case NORTH:
                        return state.with(FACING, EnumOrientation.WEST);
                    case UP_Z:
                        return state.with(FACING, EnumOrientation.UP_X);
                    case UP_X:
                        return state.with(FACING, EnumOrientation.UP_Z);
                    case DOWN_X:
                        return state.with(FACING, EnumOrientation.DOWN_Z);
                    case DOWN_Z:
                        return state.with(FACING, EnumOrientation.DOWN_X);
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
                    case UP_Z:
                        return state.with(FACING, EnumOrientation.UP_X);
                    case UP_X:
                        return state.with(FACING, EnumOrientation.UP_Z);
                    case DOWN_X:
                        return state.with(FACING, EnumOrientation.DOWN_Z);
                    case DOWN_Z:
                        return state.with(FACING, EnumOrientation.DOWN_X);
                }

            default:
                return state;
        }
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
    public static enum EnumOrientation implements IStringSerializable
    {
        DOWN_X( "down_x", Direction.DOWN),
        EAST("east", Direction.EAST),
        WEST("west", Direction.WEST),
        SOUTH("south", Direction.SOUTH),
        NORTH("north", Direction.NORTH),
        UP_Z("up_z", Direction.UP),
        UP_X("up_x", Direction.UP),
        DOWN_Z("down_z", Direction.DOWN);
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

            switch (entityFacing.getAxis())
            {
                case X:
                    return DOWN_X;
                case Z:
                    return DOWN_Z;
                default:
                    return DOWN_Z;

            }

            case UP:

            switch (entityFacing.getAxis())
            {
                case X:
                    return UP_X;
                case Z:
                    return UP_Z;
                default:
                    return UP_Z;
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
