package ru.tesmio.blocks.doors;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import ru.tesmio.reg.RegItems;

public class RailingDoorBlock extends DoorBlock implements IWaterLoggable{
    public static final BooleanProperty LOCKED = BooleanProperty.create("locked");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public RailingDoorBlock(Properties builder) {
        super(builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(OPEN, Boolean.valueOf(false)).with(HINGE, DoorHingeSide.LEFT).with(POWERED, Boolean.valueOf(false)).with(HALF, DoubleBlockHalf.LOWER).with(LOCKED, Boolean.valueOf(false)).with(WATERLOGGED, Boolean.valueOf(false)));
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HALF, FACING, OPEN, HINGE, POWERED, LOCKED, WATERLOGGED);
    }
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return !state.get(WATERLOGGED);
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        {
            boolean isLocked = state.get(LOCKED);
            Item activeItemRight = player.getHeldItemMainhand().getItem();
            Item activeItemLeft = player.getHeldItemOffhand().getItem();
            if(isLocked) {
                if(activeItemRight == RegItems.KEY_DOOR.get() || activeItemLeft == RegItems.KEY_DOOR.get()) {
                    state = state.cycleValue(LOCKED);
                    worldIn.setBlockState(pos, state, 10);
                    return ActionResultType.SUCCESS;
                } else return ActionResultType.FAIL;
            } else {
                if(activeItemRight == RegItems.KEY_DOOR.get() || activeItemLeft == RegItems.KEY_DOOR.get()) {
                    state = state.cycleValue(LOCKED);

                } else {
                    state = state.cycleValue(OPEN);
                }
                worldIn.setBlockState(pos, state, 10);
                return ActionResultType.SUCCESS;
            }
        }

    }
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        DoubleBlockHalf doubleblockhalf = stateIn.get(HALF);
        if (facing.getAxis() == Direction.Axis.Y && doubleblockhalf == DoubleBlockHalf.LOWER == (facing == Direction.UP)) {
            return facingState.matchesBlock(this) && facingState.get(HALF) != doubleblockhalf ? stateIn.with(FACING, facingState.get(FACING)).with(OPEN, facingState.get(OPEN)).with(HINGE, facingState.get(HINGE)).with(POWERED, facingState.get(POWERED)).with(LOCKED, facingState.get(LOCKED)) : Blocks.AIR.getDefaultState();
        } else {
            return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
    }

   final VoxelShape upperFrame = VoxelShapes.or(Block.makeCuboidShape(7D, 16D, 0D, 9D, 15.5D, 16D),
            Block.makeCuboidShape(7D, 0D, 15.5D, 9D, 16D, 16D),
            Block.makeCuboidShape(7D, 0D, 0D, 9D, 16D, 0.5D));
    final VoxelShape lowerFrame = VoxelShapes.or(Block.makeCuboidShape(7D, 0D, 0D, 9D, 0.5D, 16D),
            Block.makeCuboidShape(7D, 0D, 15.5D, 9D, 16D, 16D),
            Block.makeCuboidShape(7D, 0D, 0D, 9D, 16D, 0.5D));

    final  VoxelShape lowerFrame2 = VoxelShapes.or(Block.makeCuboidShape(0D, 0D, 9D, 16D, 0.5D, 7D),
            Block.makeCuboidShape(15.5D, 0D, 9D, 16D, 16D, 7D),
            Block.makeCuboidShape(0D, 0D, 9D, 0.5D, 16D, 7D));

    final VoxelShape upperFrame2 = VoxelShapes.or(Block.makeCuboidShape(0D, 16D, 9D, 16D, 15.5D, 7D),
            Block.makeCuboidShape(15.5D, 0D, 9D, 16D, 16D, 7D),
            Block.makeCuboidShape(0D, 0D, 9D, 0.5D, 16D, 7D));
    VoxelShape door;
    final VoxelShape def = VoxelShapes.or(Block.makeCuboidShape(7D, 0D, 0D, 9D, 16.0D, 16D));
    final VoxelShape def2 = VoxelShapes.or(Block.makeCuboidShape(0D, 0D, 7D, 16D, 16.0D, 9D));
        @Override
        public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
            return this.getShape(state, worldIn, pos, context);
        }
        @Override
        public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
            Direction direction = state.get(FACING);
            boolean isOpen = state.get(OPEN);
            boolean isHingeRight = state.get(HINGE) == DoorHingeSide.RIGHT;
           boolean isLower = state.get(HALF) == DoubleBlockHalf.LOWER;
            switch (direction) {
                case EAST:
                    if (isOpen) {
                        if (isHingeRight) {
                            door = Block.makeCuboidShape(7D, 0D, 11.5D, 9D, 16.0D, 16D);
                        } else {
                            door = Block.makeCuboidShape(7D, 0D, 0D, 9D, 16.0D, 4.5D);
                        }
                        if (isLower) {
                            return VoxelShapes.or(lowerFrame, door);
                        } else {
                            return VoxelShapes.or(upperFrame, door);
                        }
                    } else {
                        return def;
                    }
                case WEST:
                default:
                    if (isOpen) {
                        if (!isHingeRight) {
                            door = Block.makeCuboidShape(7D, 0D, 11.5D, 9D, 16.0D, 16D);
                        } else {
                            door = Block.makeCuboidShape(7D, 0D, 0D, 9D, 16.0D, 4.5D);
                        }
                        if (isLower) {
                            return VoxelShapes.or(lowerFrame, door);
                        } else {
                            return VoxelShapes.or(upperFrame, door);
                        }
                    } else {
                        return def;
                    }
                case SOUTH:
                    if (isOpen) {
                        if (!isHingeRight) {
                            door = Block.makeCuboidShape(11.5D, 0D, 7D, 16D, 16.0D, 9D);
                        } else {
                            door = Block.makeCuboidShape(0D, 0D, 7D, 4.5D, 16.0D, 9D);
                        }
                        if (isLower) {
                            return VoxelShapes.or(lowerFrame2, door);
                        } else {
                            return VoxelShapes.or(upperFrame2, door);
                        }
                    } else {
                        return def2;
                    }
                case NORTH:
                    if (isOpen) {
                        if (isHingeRight) {
                            door = Block.makeCuboidShape(11.5D, 0D, 7D, 16D, 16.0D, 9D);
                        } else {
                            door = Block.makeCuboidShape(0D, 0D, 7D, 4.5D, 16.0D, 9D);
                        }
                        if (isLower) {
                            return VoxelShapes.or(lowerFrame2, door);
                        } else {
                            return VoxelShapes.or(upperFrame2, door);
                        }
                    } else {
                        return def2;
                    }
            }
        }
}
