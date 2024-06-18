package ru.tesmio.soviet.blocks.baseblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.soviet.utils.VoxelShapeUtil;

public class BlockRotatedAxis extends BaseBlock implements IWaterLoggable {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<BaseEnumOrientation> FACING = EnumProperty.create("facing", BaseEnumOrientation.class);
    public static VoxelShape FACING_SHAPE = VoxelShapes.fullCube();

    public BlockRotatedAxis(Properties builder) {
        super(builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, BaseEnumOrientation.NORTH).with(WATERLOGGED, Boolean.valueOf(false)));
    }

    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return !state.get(WATERLOGGED);
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
        return FACING_SHAPE;
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        for (Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {

                return this.getDefaultState().with(FACING, BaseEnumOrientation.forFacing(direction, context.getPlacementHorizontalFacing()));
            } else {

                return this.getDefaultState().with(FACING, BaseEnumOrientation.forFacing(direction, direction));
            }
        }
        return this.getDefaultState();
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        switch (rot) {
            case CLOCKWISE_180:

                switch (state.get(FACING)) {
                    case EAST:
                        return state.with(FACING, BaseEnumOrientation.WEST);
                    case WEST:
                        return state.with(FACING, BaseEnumOrientation.EAST);
                    case SOUTH:
                        return state.with(FACING, BaseEnumOrientation.NORTH);
                    case NORTH:
                        return state.with(FACING, BaseEnumOrientation.SOUTH);
                    default:
                        return state;
                }

            case COUNTERCLOCKWISE_90:

                switch (state.get(FACING)) {
                    case EAST:
                        return state.with(FACING, BaseEnumOrientation.NORTH);
                    case WEST:
                        return state.with(FACING, BaseEnumOrientation.SOUTH);
                    case SOUTH:
                        return state.with(FACING, BaseEnumOrientation.EAST);
                    case NORTH:
                        return state.with(FACING, BaseEnumOrientation.WEST);
                    case UP_Z:
                        return state.with(FACING, BaseEnumOrientation.UP_X);
                    case UP_X:
                        return state.with(FACING, BaseEnumOrientation.UP_Z);
                    case DOWN_X:
                        return state.with(FACING, BaseEnumOrientation.DOWN_Z);
                    case DOWN_Z:
                        return state.with(FACING, BaseEnumOrientation.DOWN_X);
                }

            case CLOCKWISE_90:

                switch (state.get(FACING)) {
                    case EAST:
                        return state.with(FACING, BaseEnumOrientation.SOUTH);
                    case WEST:
                        return state.with(FACING, BaseEnumOrientation.NORTH);
                    case SOUTH:
                        return state.with(FACING, BaseEnumOrientation.WEST);
                    case NORTH:
                        return state.with(FACING, BaseEnumOrientation.EAST);
                    case UP_Z:
                        return state.with(FACING, BaseEnumOrientation.UP_X);
                    case UP_X:
                        return state.with(FACING, BaseEnumOrientation.UP_Z);
                    case DOWN_X:
                        return state.with(FACING, BaseEnumOrientation.DOWN_Z);
                    case DOWN_Z:
                        return state.with(FACING, BaseEnumOrientation.DOWN_X);
                }

            default:
                return state;
        }
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }


}
