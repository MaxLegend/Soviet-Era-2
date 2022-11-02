package ru.tesmio.blocks.doors;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.PathType;
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

public class AluminiumDoorBlock extends DoorBlock  implements IWaterLoggable{
    public static final BooleanProperty LOCKED = BooleanProperty.create("locked");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public final VoxelShape closeNorth = Block.makeCuboidShape(0D, 0D, 14D, 16D, 16D, 16D);
    public final VoxelShape closeSouth = Block.makeCuboidShape(0D, 0D, 0D, 16D, 16D, 2D);
    public final VoxelShape closeEast = Block.makeCuboidShape(0D, 0D, 0D, 2D, 16D, 16D);
    public final VoxelShape lowerFrameWest = VoxelShapes.or(Block.makeCuboidShape(16D, 0D, 0D, 14D, 0.5D, 16D),
            Block.makeCuboidShape(14D, 0D, 0D, 16D, 16D, 0.5D),
            Block.makeCuboidShape(14D, 0D, 16D, 16D, 16D, 15.5D));

    public final VoxelShape closeWest = Block.makeCuboidShape(14D, 0D, 0D, 16D, 16D, 16D);

    public final VoxelShape upperFrameWest = VoxelShapes.or(Block.makeCuboidShape(16D, 16, 0D, 14D, 15.5D, 16D),
            Block.makeCuboidShape(14D, 16D, 0D, 16D, 0D, 0.5D),
            Block.makeCuboidShape(14D, 16D, 16D, 16D, 0D, 15.5D));
    public final VoxelShape lowerFrameNorth = VoxelShapes.or(Block.makeCuboidShape(0D, 0D, 14D, 16D, 0.5D, 16D),
            Block.makeCuboidShape(15.5D, 0D, 14D, 16D, 16D, 16D),
            Block.makeCuboidShape(0D, 0D, 14D, 0.5D, 16D, 16D));
    public final VoxelShape upperFrameNorth = VoxelShapes.or(Block.makeCuboidShape(0D, 16D, 14D, 16D, 15.5D, 16D),
            Block.makeCuboidShape(15.5D, 0D, 14D, 16D, 16D, 16D),
            Block.makeCuboidShape(0D, 0D, 14D, 0.5D, 16D, 16D));
    public final VoxelShape lowerFrameSouth = VoxelShapes.or(Block.makeCuboidShape(0D, 0D, 2D, 16D, 0.5D, 0D),
            Block.makeCuboidShape(15.5D, 0D, 2D, 16D, 16D, 0D),
            Block.makeCuboidShape(0D, 0D, 2D, 0.5D, 16D, 0D));
    public final VoxelShape upperFrameSouth = VoxelShapes.or(Block.makeCuboidShape(0D, 16D, 2D, 16D, 15.5D, 0D),
            Block.makeCuboidShape(15.5D, 0D, 2D, 16D, 16D, 0D),
            Block.makeCuboidShape(0D, 0D, 2D, 0.5D, 16D, 0D));
    public final VoxelShape lowerFrameEast = VoxelShapes.or(Block.makeCuboidShape(0D, 0D, 0D, 2D, 0.5D, 16D),
            Block.makeCuboidShape(0D, 0D, 15.5D, 2D, 16D, 16D),
            Block.makeCuboidShape(0D, 0D, 0D, 2D, 16D, 0.5D));

    public final VoxelShape upperFrameEast = VoxelShapes.or(Block.makeCuboidShape(0D, 16D, 0D, 2D, 15.5D, 16D),
            Block.makeCuboidShape(0D, 0D, 15.5D, 2D, 16D, 16D),
            Block.makeCuboidShape(0D, 0D, 0D, 2D, 16D, 0.5D));
    public VoxelShape lowerDoorNorth, upperDoorNorth, lowerDoorSouth, upperDoorSouth,lowerDoorWest, upperDoorWest,lowerDoorEast, upperDoorEast;
    public AluminiumDoorBlock(Properties builder) {
        super(builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(OPEN, Boolean.valueOf(false)).with(HINGE, DoorHingeSide.LEFT).with(POWERED, Boolean.valueOf(false)).with(HALF, DoubleBlockHalf.LOWER).with(LOCKED, Boolean.valueOf(false)).with(WATERLOGGED, Boolean.valueOf(false)));

    }
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return !state.get(WATERLOGGED);
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }
    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
                return true;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HALF, FACING, OPEN, HINGE, POWERED, LOCKED, WATERLOGGED);
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
            default:
                if (isOpen) {
                    if(isHingeRight) {
                        if (isLower) {
                            lowerDoorEast = VoxelShapes.or(Block.makeCuboidShape(1.5D, 0.5D, 15.5D, 16D, 16.0D, 14.5D));
                             return VoxelShapes.or(lowerDoorEast, lowerFrameEast);
                        } else {
                            upperDoorEast = VoxelShapes.or(Block.makeCuboidShape(1.5D, 0D, 15.5D, 16D, 15.5D, 14.5D));
                            return VoxelShapes.or(upperDoorEast, upperFrameEast);
                        }
                    } else {
                        if (isLower) {
                            lowerDoorEast = VoxelShapes.or(Block.makeCuboidShape(16D, 0.5D, 0.5D, 1.5D, 16.0D, 1.5D));
                            return VoxelShapes.or(lowerDoorEast, lowerFrameEast);
                        } else {
                            upperDoorEast = VoxelShapes.or(Block.makeCuboidShape(16D, 0D, 0.5D, 1.5D, 15.5D, 1.5D));
                            return VoxelShapes.or(upperDoorEast, upperFrameEast);
                        }
                    }
                } else {
                    return closeEast;
                }
            case WEST:
                if (isOpen) {
                    if(isHingeRight) {
                        if (isLower) {
                            lowerDoorWest = VoxelShapes.or(Block.makeCuboidShape(14.5D, 0.5D, 0.5D, 0D, 16.0D, 1.5D));
                            return VoxelShapes.or(lowerDoorWest, lowerFrameWest);
                        } else {
                            upperDoorWest = VoxelShapes.or(Block.makeCuboidShape(14.5D, 0D, 0.5D, 0D, 15.5D, 1.5D));
                            return VoxelShapes.or(upperDoorWest, upperFrameWest);
                        }
                    } else {
                        if (isLower) {
                            lowerDoorWest = VoxelShapes.or(Block.makeCuboidShape(0D, 0.5D, 15.5D, 14.5D, 16.0D, 14.5D));
                            return VoxelShapes.or(lowerDoorWest, lowerFrameWest);
                        } else {
                            upperDoorWest = VoxelShapes.or(Block.makeCuboidShape(0D, 0D, 15.5D, 14.5D, 15.5D, 14.5D));
                            return VoxelShapes.or(upperDoorWest, upperFrameWest);
                        }
                    }
                } else {
                    return closeWest;
                }
            case SOUTH:
                if (isOpen) {
                    if(isHingeRight) {
                        if (isLower) {
                            lowerDoorSouth = VoxelShapes.or(Block.makeCuboidShape(0.5D, 0.5D, 1.5D, 1.5D, 16.0D, 16D));
                            return VoxelShapes.or(lowerDoorSouth, lowerFrameSouth);
                        } else {
                            upperDoorSouth = VoxelShapes.or(Block.makeCuboidShape(0.5D, 0D, 1.5D, 1.5D, 15.5D, 16D));
                            return VoxelShapes.or(upperDoorSouth, upperFrameSouth);
                        }
                    } else {
                        if (isLower) {
                            lowerDoorSouth = VoxelShapes.or(Block.makeCuboidShape(14.5D, 0.5D, 1.5D, 15.5D, 16.0D, 16D));
                            return VoxelShapes.or(lowerDoorSouth, lowerFrameSouth);
                        } else {
                            upperDoorSouth = VoxelShapes.or(Block.makeCuboidShape(14.5D, 0D, 1.5D, 15.5D, 15.5D, 16D));
                            return VoxelShapes.or(upperDoorSouth, upperFrameSouth);
                        }
                    }
                } else {
                    return closeSouth;
                }
            case NORTH:
                    if (isOpen) {
                        if(isHingeRight) {
                            if (isLower) {
                                lowerDoorNorth = VoxelShapes.or(Block.makeCuboidShape(14.5D, 0.5D, 0D, 15.5D, 16.0D, 14.5D));
                                return VoxelShapes.or(lowerDoorNorth, lowerFrameNorth);
                            } else {
                                upperDoorNorth = VoxelShapes.or(Block.makeCuboidShape(14.5D, 0D, 0D, 15.5D, 15.5D, 14.5D));
                                return VoxelShapes.or(upperDoorNorth, upperFrameNorth);
                            }
                        } else {
                            if (isLower) {
                                lowerDoorNorth = VoxelShapes.or(Block.makeCuboidShape(0.5D, 0.5D, 0D, 1.5D, 16.0D, 14.5D));
                                return VoxelShapes.or(lowerDoorNorth, lowerFrameNorth);
                            } else {
                                upperDoorNorth = VoxelShapes.or(Block.makeCuboidShape(0.5D, 0D, 0D, 1.5D, 15.5D, 14.5D));
                                return VoxelShapes.or(upperDoorNorth, upperFrameNorth);
                            }
                        }
                    } else {
                        return closeNorth;
                    }
                }
    }


}
