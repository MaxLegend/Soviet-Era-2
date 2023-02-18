package ru.tesmio.blocks.doors;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
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
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AirlockDoorBlock extends DoorBlock implements IWaterLoggable {
    public static final BooleanProperty LOCKED = BooleanProperty.create("locked");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public final VoxelShape lowerFrameEast = VoxelShapes.or(Block.makeCuboidShape(0D, 0D, 0D, 3D, 1.0D, 16D),
            Block.makeCuboidShape(0D, 0D, 0D, 3D, 2.0D, 6D),
            Block.makeCuboidShape(0D, 0D, 0D, 3D, 3.0D, 4D),
            Block.makeCuboidShape(0D, 0D, 0D, 3D, 4.0D, 3D),
            Block.makeCuboidShape(0D, 0D, 0D, 3D, 16.0D, 2D),
            Block.makeCuboidShape(0D, 0D, 10D, 3D, 2.0D, 16D),
            Block.makeCuboidShape(0D, 0D, 12D, 3D, 3.0D, 16D),
            Block.makeCuboidShape(0D, 0D, 13D, 3D, 4.0D, 16D),
            Block.makeCuboidShape(0D, 0D, 14D, 3D, 16.0D, 16D));
    public final VoxelShape upperFrameEast = VoxelShapes.or(Block.makeCuboidShape(0D, 16D, 0D, 3D, 15.0D, 16D),
            Block.makeCuboidShape(0D, 16D, 0D, 3D, 14.0D, 6D),
            Block.makeCuboidShape(0D, 16D, 0D, 3D, 13.0D, 4D),
            Block.makeCuboidShape(0D, 16D, 0D, 3D, 12.0D, 3D),
            Block.makeCuboidShape(0D, 16D, 0D, 3D, 0.0D, 2D),
            Block.makeCuboidShape(0D, 16D, 10D, 3D, 14.0D, 16D),
            Block.makeCuboidShape(0D, 16D, 12D, 3D, 13.0D, 16D),
            Block.makeCuboidShape(0D, 16D, 13D, 3D, 12.0D, 16D),
            Block.makeCuboidShape(0D, 16D, 14D, 3D, 0.0D, 16D));
    public final VoxelShape lowerFrameWest = VoxelShapes.or(Block.makeCuboidShape(13D, 0D, 0D, 16D, 1.0D, 16D),
            Block.makeCuboidShape(13D, 0D, 0D, 16D, 2.0D, 6D),
            Block.makeCuboidShape(13D, 0D, 0D, 16D, 3.0D, 4D),
            Block.makeCuboidShape(13D, 0D, 0D, 16D, 4.0D, 3D),
            Block.makeCuboidShape(13D, 0D, 0D, 16D, 16.0D, 2D),
            Block.makeCuboidShape(13D, 0D, 10D, 16D, 2.0D, 16D),
            Block.makeCuboidShape(13D, 0D, 12D, 16D, 3.0D, 16D),
            Block.makeCuboidShape(13D, 0D, 13D, 16D, 4.0D, 16D),
            Block.makeCuboidShape(13D, 0D, 14D, 16D, 16.0D, 16D));
    public final VoxelShape upperFrameWest = VoxelShapes.or(Block.makeCuboidShape(13D, 16D, 0D, 16D, 15.0D, 16D),
            Block.makeCuboidShape(13D, 16D, 0D, 16D, 14.0D, 6D),
            Block.makeCuboidShape(13D, 16D, 0D, 16D, 13.0D, 4D),
            Block.makeCuboidShape(13D, 16D, 0D, 16D, 12.0D, 3D),
            Block.makeCuboidShape(13D, 16D, 0D, 16D, 0.0D, 2D),
            Block.makeCuboidShape(13D, 16D, 10D, 16D, 14.0D, 16D),
            Block.makeCuboidShape(13D, 16D, 12D, 16D, 13.0D, 16D),
            Block.makeCuboidShape(13D, 16D, 13D, 16D, 12.0D, 16D),
            Block.makeCuboidShape(13D, 16D, 14D, 16D, 0.0D, 16D));
    public final VoxelShape lowerFrameSouth = VoxelShapes.or(Block.makeCuboidShape(0D, 0D, 3D, 16D, 1.0D, 0D),
            Block.makeCuboidShape(0D, 0D, 3D, 6D, 2.0D, 0D),
            Block.makeCuboidShape(10D, 0D, 3D, 16D, 2.0D, 0D),
            Block.makeCuboidShape(0D, 0D, 3D, 4D, 3.0D, 0D),
            Block.makeCuboidShape(12D, 0D, 3D, 16D, 3.0D, 0D),
            Block.makeCuboidShape(0D, 0D, 3D, 3D, 4.0D, 0D),
            Block.makeCuboidShape(13D, 0D, 3D, 16D, 4.0D, 0D),
            Block.makeCuboidShape(0D, 0D, 3D, 2D, 16.0D, 0D),
            Block.makeCuboidShape(14D, 0D, 3D, 16D, 16.0D, 0D));

    public final VoxelShape upperFrameSouth = VoxelShapes.or(Block.makeCuboidShape(0D, 16D, 3D, 16D, 15.0D, 0D),
            Block.makeCuboidShape(0D, 16D, 3D, 6D, 14D, 0D),
            Block.makeCuboidShape(10D, 16D, 3D, 16D, 14D, 0D),
            Block.makeCuboidShape(0D, 16D, 3D, 4D, 13D, 0D),
            Block.makeCuboidShape(12D, 16D, 3D, 16D, 13D, 0D),
            Block.makeCuboidShape(0D, 16D, 3D, 3D, 12D, 0D),
            Block.makeCuboidShape(13D, 16D, 3D, 16D, 12D, 0D),
            Block.makeCuboidShape(0D, 16D, 3D, 2D, 0D, 0D),
            Block.makeCuboidShape(14D, 16D, 3D, 16D, 0D, 0D));
    public final VoxelShape lowerFrameNorth = VoxelShapes.or(Block.makeCuboidShape(0D, 0D, 16D, 16D, 1.0D, 13D),
            Block.makeCuboidShape(0D, 0D, 16D, 6D, 2.0D, 13D),
            Block.makeCuboidShape(10D, 0D, 16D, 16D, 2.0D, 13D),
            Block.makeCuboidShape(0D, 0D, 16D, 4D, 3.0D, 13D),
            Block.makeCuboidShape(12D, 0D, 16D, 16D, 3.0D, 13D),
            Block.makeCuboidShape(0D, 0D, 16D, 3D, 4.0D, 13D),
            Block.makeCuboidShape(13D, 0D, 16D, 16D, 4.0D, 13D),
            Block.makeCuboidShape(0D, 0D, 16D, 2D, 16.0D, 13D),
            Block.makeCuboidShape(14D, 0D, 16D, 16D, 16.0D, 13D));

    public final VoxelShape upperFrameNorth = VoxelShapes.or(Block.makeCuboidShape(0D, 16D, 16D, 16D, 15.0D, 13D),
            Block.makeCuboidShape(0D, 16D, 16D, 6D, 14D, 13D),
            Block.makeCuboidShape(10D, 16D, 16D, 16D, 14D, 13D),
            Block.makeCuboidShape(0D, 16D, 16D, 4D, 13D, 13D),
            Block.makeCuboidShape(12D, 16D, 16D, 16D, 13D, 13D),
            Block.makeCuboidShape(0D, 16D, 16D, 3D, 12D, 13D),
            Block.makeCuboidShape(13D, 16D, 16D, 16D, 12D, 13D),
            Block.makeCuboidShape(0D, 16D, 16D, 2D, 0D, 13D),
            Block.makeCuboidShape(14D, 16D, 16D, 16D, 0D, 13D));
    public VoxelShape lowerDoorEast, upperDoorEast, both, lowerDoorWest, upperDoorWest, lowerDoorSouth, upperDoorSouth, lowerDoorNorth, upperDoorNorth;
    public AirlockDoorBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(OPEN, Boolean.valueOf(false)).with(HINGE, DoorHingeSide.LEFT).with(POWERED, Boolean.valueOf(false)).with(HALF, DoubleBlockHalf.LOWER).with(WATERLOGGED, Boolean.valueOf(false)).with(LOCKED, true));
    }
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return true;
    }
    @OnlyIn(Dist.CLIENT)
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1F;
    }
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {{
        boolean isLocked = state.get(LOCKED);
        if(!isLocked) {
            state = state.cycleValue(OPEN);
            worldIn.setBlockState(pos, state, 10);
            //   worldIn.playEvent(player, state.get(OPEN) ? this.getOpenSound() : this.getCloseSound(), pos, 0);
            return ActionResultType.func_233537_a_(worldIn.isRemote);
        }
        }
        return ActionResultType.FAIL;
    }
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {

    }
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        DoubleBlockHalf doubleblockhalf = stateIn.get(HALF);
        if (facing.getAxis() == Direction.Axis.Y && doubleblockhalf == DoubleBlockHalf.LOWER == (facing == Direction.UP)) {
            return facingState.matchesBlock(this) && facingState.get(HALF) != doubleblockhalf ? stateIn.with(FACING, facingState.get(FACING)).with(OPEN, facingState.get(OPEN)).with(HINGE, facingState.get(HINGE)).with(POWERED, facingState.get(POWERED)).with(LOCKED, facingState.get(LOCKED)) : Blocks.AIR.getDefaultState();
        } else {
            return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
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
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Direction direction = state.get(FACING);
        VoxelShape def = Block.makeCuboidShape(0.0D, 0.0D, 0D, 16.0D, 16D, 16.0D);
        boolean isOpen = state.get(OPEN);
        boolean isHingeRight = state.get(HINGE) == DoorHingeSide.RIGHT;
        boolean isHingeLeft = state.get(HINGE) == DoorHingeSide.LEFT;
            switch (direction) {
                case EAST:
                default:
                    if(isHingeRight) {
                        if (!isOpen) {
                            if (state.get(HALF) == DoubleBlockHalf.LOWER) {
                                lowerDoorEast = VoxelShapes.or(Block.makeCuboidShape(1D, 0D, 2D, 2D, 16.0D, 14D));
                                both = VoxelShapes.or(lowerDoorEast, lowerFrameEast);
                                return both;
                            }
                            if (state.get(HALF) == DoubleBlockHalf.UPPER) {
                                upperDoorEast = VoxelShapes.or(Block.makeCuboidShape(1D, 0D, 2D, 2D, 16.0D, 14D));
                                both = VoxelShapes.or(upperDoorEast, upperFrameEast);
                                return both;
                            }
                        }
                        if (isOpen) {
                            if (state.get(HALF) == DoubleBlockHalf.LOWER) {
                                lowerDoorEast = VoxelShapes.or(Block.makeCuboidShape(3D, 1D, 13D, 15D, 16.0D, 14D));
                                both = VoxelShapes.or(lowerDoorEast, lowerFrameEast);
                                return both;
                            } else {
                                upperDoorEast = VoxelShapes.or(Block.makeCuboidShape(3D, 0D, 13D, 15D, 14.0D, 14D));
                                both = VoxelShapes.or(upperDoorEast, upperFrameEast);
                                return both;
                            }
                        }
                    }
                    if(isHingeLeft) {
                        if (!isOpen) {
                            if (state.get(HALF) == DoubleBlockHalf.LOWER) {
                                lowerDoorEast = VoxelShapes.or(Block.makeCuboidShape(1D, 0D, 2D, 2D, 16.0D, 14D));
                               both = VoxelShapes.or(lowerDoorEast, lowerFrameEast);
                                return both;
                            }
                            if (state.get(HALF) == DoubleBlockHalf.UPPER) {
                                upperDoorEast = VoxelShapes.or(Block.makeCuboidShape(1D, 0D, 2D, 2D, 16.0D, 14D));
                                both = VoxelShapes.or(upperDoorEast, upperFrameEast);
                                return both;
                            }
                        }
                        if (isOpen) {
                            if (state.get(HALF) == DoubleBlockHalf.LOWER) {
                                lowerDoorEast = VoxelShapes.or(Block.makeCuboidShape(3D, 1D, 2D, 15D, 16.0D, 3D));
                                both = VoxelShapes.or(lowerDoorEast, lowerFrameEast);
                                return both;
                            } else {
                                upperDoorEast = VoxelShapes.or(Block.makeCuboidShape(3D, 0D, 2D, 15D, 14.0D, 3D));
                                both = VoxelShapes.or(upperDoorEast, upperFrameEast);
                                return both;
                            }
                        }
                    }
                    return lowerFrameEast;

                case SOUTH:
                    if(isHingeRight) {
                        if (!isOpen) {
                            if (state.get(HALF) == DoubleBlockHalf.LOWER) {
                                lowerDoorSouth = VoxelShapes.or(Block.makeCuboidShape(2D, 1D, 1D, 14D, 16.0D, 2D));
                                both = VoxelShapes.or(lowerDoorSouth, lowerFrameSouth);
                                return both;
                            }
                            if (state.get(HALF) == DoubleBlockHalf.UPPER) {
                                upperDoorSouth = VoxelShapes.or(Block.makeCuboidShape(2D, 0D, 1D, 14D, 16.0D, 2D));
                                both = VoxelShapes.or(upperDoorSouth, upperFrameSouth);
                                return both;
                            }
                        }
                        if (isOpen) {
                            if (state.get(HALF) == DoubleBlockHalf.LOWER) {
                                lowerDoorSouth = VoxelShapes.or(Block.makeCuboidShape(2D, 1D, 3D, 3D, 16.0D, 15D));
                                both = VoxelShapes.or(lowerDoorSouth, lowerFrameSouth);
                                return both;
                            } else {

                                upperDoorSouth = VoxelShapes.or(Block.makeCuboidShape(2D, 0D, 3D, 3D, 14.0D, 15D));
                                both = VoxelShapes.or(upperDoorSouth, upperFrameSouth);
                                return both;
                            }
                        }
                    }
                    if(isHingeLeft) {
                        if (!isOpen) {
                            if (state.get(HALF) == DoubleBlockHalf.LOWER) {
                                lowerDoorSouth = VoxelShapes.or(Block.makeCuboidShape(2D, 1D, 1D, 14D, 16.0D, 2D));
                                both = VoxelShapes.or(lowerDoorSouth, lowerFrameSouth);
                                return both;
                            }
                            if (state.get(HALF) == DoubleBlockHalf.UPPER) {
                                upperDoorSouth = VoxelShapes.or(Block.makeCuboidShape(2D, 0D, 1D, 14D, 16.0D, 2D));
                                both = VoxelShapes.or(upperDoorSouth, upperFrameSouth);
                                return both;
                            }
                        }
                        if (isOpen) {
                            if (state.get(HALF) == DoubleBlockHalf.LOWER) {
                                lowerDoorSouth = VoxelShapes.or(Block.makeCuboidShape(14D, 1D, 3D, 13D, 16.0D, 15D));
                                both = VoxelShapes.or(lowerDoorSouth, lowerFrameSouth);
                                return both;
                            } else {

                                upperDoorSouth = VoxelShapes.or(Block.makeCuboidShape(14D, 0D, 3D, 13D, 14.0D, 15D));
                                both = VoxelShapes.or(upperDoorSouth, upperFrameSouth);
                                return both;
                            }
                        }
                    }
                    return lowerFrameSouth;
                case WEST:
                    if(isHingeLeft) {
                        if (!isOpen) {
                            if (state.get(HALF) == DoubleBlockHalf.LOWER) {
                                lowerDoorWest = VoxelShapes.or(Block.makeCuboidShape(15D, 0D, 2D, 14D, 16.0D, 14D));
                                both = VoxelShapes.or(lowerDoorWest, lowerFrameWest);
                                return both;
                            }
                            if (state.get(HALF) == DoubleBlockHalf.UPPER) {
                                upperDoorWest = VoxelShapes.or(Block.makeCuboidShape(15D, 0D, 2D, 14D, 16.0D, 14D));
                                both = VoxelShapes.or(upperDoorWest, upperFrameWest);
                                return both;
                            }
                        }
                        if (isOpen) {
                            if (state.get(HALF) == DoubleBlockHalf.LOWER) {
                               lowerDoorWest = VoxelShapes.or(Block.makeCuboidShape(1D, 1D, 13D, 14D, 16.0D, 14D));
                                both = VoxelShapes.or(lowerDoorWest, lowerFrameWest);
                                return both;
                            } else {
                               upperDoorWest = VoxelShapes.or(Block.makeCuboidShape(1D, 0D, 13D, 13D, 14.0D, 14D));
                               both = VoxelShapes.or(upperDoorWest, upperFrameWest);
                                return both;
                            }
                        }
                    }
                    if(isHingeRight) {
                        if (!isOpen) {
                            if (state.get(HALF) == DoubleBlockHalf.LOWER) {
                                lowerDoorWest = VoxelShapes.or(Block.makeCuboidShape(15D, 0D, 2D, 14D, 16.0D, 14D));
                                 both = VoxelShapes.or(lowerDoorWest, lowerFrameWest);
                                return both;
                            }
                            if (state.get(HALF) == DoubleBlockHalf.UPPER) {
                                upperDoorWest = VoxelShapes.or(Block.makeCuboidShape(15D, 0D, 2D, 14D, 16.0D, 14D));
                                both = VoxelShapes.or(upperDoorWest, upperFrameWest);
                                return both;
                            }
                        }
                        if (isOpen) {
                            if (state.get(HALF) == DoubleBlockHalf.LOWER) {
                                lowerDoorWest = VoxelShapes.or(Block.makeCuboidShape(1D, 1D, 2D, 13D, 16.0D, 3D));
                                both = VoxelShapes.or(lowerDoorWest, lowerFrameWest);
                                return both;
                            } else {
                                upperDoorWest = VoxelShapes.or(Block.makeCuboidShape(1D, 0D, 2D, 13D, 14.0D, 3D));
                                both = VoxelShapes.or(upperDoorWest, upperFrameWest);
                                return both;
                            }
                        }
                    }
                    return lowerFrameWest;

                case NORTH:
                    if(isHingeRight) {
                        if (!isOpen) {
                            if (state.get(HALF) == DoubleBlockHalf.LOWER) {
                                lowerDoorNorth = VoxelShapes.or(Block.makeCuboidShape(2D, 1D, 14D, 14D, 16.0D, 15D));
                                both = VoxelShapes.or(lowerDoorNorth, lowerFrameNorth);
                                return both;
                            }
                            if (state.get(HALF) == DoubleBlockHalf.UPPER) {
                                upperDoorNorth = VoxelShapes.or(Block.makeCuboidShape(2D, 0D, 14D, 14D, 16.0D, 15D));
                                both = VoxelShapes.or(upperDoorNorth, upperFrameNorth);
                                return both;
                            }
                        }
                        if (isOpen) {
                            if (state.get(HALF) == DoubleBlockHalf.LOWER) {
                                lowerDoorNorth = VoxelShapes.or(Block.makeCuboidShape(14D, 1D, 1D, 13D, 16.0D, 13D));
                                both = VoxelShapes.or(lowerDoorNorth, lowerFrameNorth);
                                return both;
                            } else {

                                upperDoorNorth = VoxelShapes.or(Block.makeCuboidShape(14D, 0D, 1D, 13D, 14.0D, 13D));
                                both = VoxelShapes.or(upperDoorNorth, upperFrameNorth);
                                return both;
                            }
                        }
                    }
                    if(isHingeLeft) {
                        if (!isOpen) {
                            if (state.get(HALF) == DoubleBlockHalf.LOWER) {
                                lowerDoorNorth = VoxelShapes.or(Block.makeCuboidShape(2D, 1D, 14D, 14D, 16.0D, 15D));
                                both = VoxelShapes.or(lowerDoorNorth, lowerFrameNorth);
                                return both;
                            }
                            if (state.get(HALF) == DoubleBlockHalf.UPPER) {
                                upperDoorNorth = VoxelShapes.or(Block.makeCuboidShape(2D, 0D, 14D, 14D, 16.0D, 15D));
                                both = VoxelShapes.or(upperDoorNorth, upperFrameNorth);
                                return both;
                            }
                        }
                        if (isOpen) {
                            if (state.get(HALF) == DoubleBlockHalf.LOWER) {
                                lowerDoorNorth = VoxelShapes.or(Block.makeCuboidShape(3D, 1D, 1D, 2D, 16.0D, 13D));
                                both = VoxelShapes.or(lowerDoorNorth, lowerFrameNorth);
                                return both;
                            } else {
                                upperDoorNorth = VoxelShapes.or(Block.makeCuboidShape(3D, 0D, 1D, 2D, 14.0D, 13D));
                                both = VoxelShapes.or(upperDoorNorth, upperFrameNorth);
                                return both;
                            }
                        }
                    }
                    return lowerFrameNorth;
            }


    }
}
