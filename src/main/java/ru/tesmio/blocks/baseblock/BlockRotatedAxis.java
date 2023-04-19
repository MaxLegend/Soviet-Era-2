package ru.tesmio.blocks.baseblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import ru.tesmio.utils.VoxelShapeUtil;

import javax.annotation.Nullable;

public class BlockRotatedAxis extends BaseBlock {
    public static final EnumProperty<EnumOrientation> FACING = EnumProperty.create("facing", EnumOrientation.class);
    public static VoxelShape FACING_SHAPE = VoxelShapes.fullCube();
    public BlockRotatedAxis(Properties builder) {
        super(builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, EnumOrientation.NORTH));
    }
    @Override
    public void harvestBlock(World w, PlayerEntity pl, BlockPos p, BlockState s, @Nullable TileEntity te, ItemStack st) {
        if (!w.isRemote) {
            if (!pl.isCreative()) {
                getDropsWithBlock(w, p,pl);
                getAdditionDrops(w,p,getStackAddDrop(pl));
            }
        }
    }
    public VoxelShape getShape(BlockState s, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (s.get(FACING)) {
            case NORTH:
                return getFacingShape(s);
            case SOUTH:
                   return VoxelShapeUtil.shapeRot180(getFacingShape(s));
            case EAST:
                  return VoxelShapeUtil.shapeRotCCW90(getFacingShape(s));
            case WEST:
                  return VoxelShapeUtil.shapeRotCW90(getFacingShape(s));
            case UP_X:
            case UP_Z:
                return VoxelShapeUtil.shapeRotCWX90(getFacingShape(s));
            case DOWN_Z:
            case DOWN_X:
                return VoxelShapeUtil.shapeRotCCWX90(getFacingShape(s));
        }
        return VoxelShapes.fullCube();
    }

    public VoxelShape getFacingShape(BlockState s) {
        return this.FACING_SHAPE;
    }
    public ItemStack getStackAddDrop(PlayerEntity pl) {
        return ItemStack.EMPTY;
    }
    @Nullable
    public void getAdditionDrops(World w, BlockPos p, ItemStack is) {
        spawnAsEntity(w, p, is);
    }

    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {
                ItemStack.EMPTY
        };
    }

    protected void getDropsWithBlock(World w, BlockPos p,PlayerEntity pl) {
        for(ItemStack is : getItemsDrop(pl)) {
            spawnAsEntity(w, p, is);
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
    public enum EnumOrientation implements IStringSerializable
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
