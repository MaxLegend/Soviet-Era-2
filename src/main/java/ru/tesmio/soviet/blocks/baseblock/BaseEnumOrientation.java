package ru.tesmio.soviet.blocks.baseblock;

import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;

public enum BaseEnumOrientation implements IStringSerializable {
    DOWN_X("down_x", Direction.DOWN),
    EAST("east", Direction.EAST),
    WEST("west", Direction.WEST),
    SOUTH("south", Direction.SOUTH),
    NORTH("north", Direction.NORTH),
    UP_Z("up_z", Direction.UP),
    UP_X("up_x", Direction.UP),
    DOWN_Z("down_z", Direction.DOWN);
    private final String name;
    private final Direction dir;

    BaseEnumOrientation(String name, Direction dir) {
        this.name = name;
        this.dir = dir;
    }

    @Override
    public String getString() {
        return this.name;
    }

    public Direction getDirection() {
        return this.dir;
    }

    public static BaseEnumOrientation forFacing(Direction clickedSide, Direction entityFacing) {
        switch (clickedSide) {
            case DOWN:

                switch (entityFacing.getAxis()) {
                    case X:
                        return DOWN_X;
                    case Z:
                        return DOWN_Z;
                    default:
                        return DOWN_Z;

                }

            case UP:

                switch (entityFacing.getAxis()) {
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
