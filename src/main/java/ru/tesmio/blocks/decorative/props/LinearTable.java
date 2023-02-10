package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.utils.VoxelShapeUtil;

public class LinearTable extends BlockSideCustomModel {

    public static final EnumProperty<EnumState> STATES = EnumProperty.create("states", EnumState.class);

    public LinearTable(Properties properties) {
        super(properties, 0.8F);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(STATES, EnumState.DEF));
    }
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if(state.getBlock() == RegBlocks.BIOLAB_TABLE.get() || state.getBlock() == RegBlocks.BIOLAB_TABLE_CASE.get() || state.getBlock() == RegBlocks.CHEMLAB_TABLE_CASE.get()) {
            switch (state.get(FACING)) {
                case EAST:
                case WEST:
                    return VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0,0,1,16,16,15));
                case NORTH:
                case SOUTH:
                    return Block.makeCuboidShape(0,0,1,16,16,15);
            }
            return VoxelShapes.fullCube();
        }
        if(state.getBlock() == RegBlocks.BIOLAB_TABLE2.get() || state.getBlock() == RegBlocks.BIOLAB_TABLE3.get()) {
            switch (state.get(FACING)) {
                case EAST:
                    if(state.get(STATES) == EnumState.RIGHT) {
                        return VoxelShapes.or(VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0.5D,12D,0.5D,15.5D,15,15.5D)),
                                VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0D,15,0D,16D,16,16D)),
                                VoxelShapeUtil.shapeRotCW90(   Block.makeCuboidShape(0.5D,0D,0.5D,1.5D,15,15.5D)));
                    }
                    if(state.get(STATES) == EnumState.LEFT) {
                        return VoxelShapes.or(VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0.5D,12D,0.5D,15.5D,15,15.5D)),
                                VoxelShapeUtil.shapeRotCW90( Block.makeCuboidShape(0D,15,0D,16D,16,16D)),
                                VoxelShapeUtil.shapeRotCW90( Block.makeCuboidShape(14.5D,0D,0.5D,15.5D,15,15.5D)));
                    }
                case WEST:
                    if(state.get(STATES) == EnumState.LEFT) {
                        return VoxelShapes.or(VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0.5D,12D,0.5D,15.5D,15,15.5D)),
                                VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0D,15,0D,16D,16,16D)),
                                        VoxelShapeUtil.shapeRotCW90(   Block.makeCuboidShape(0.5D,0D,0.5D,1.5D,15,15.5D)));
                    }
                    if(state.get(STATES) == EnumState.RIGHT) {
                        return VoxelShapes.or(VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0.5D,12D,0.5D,15.5D,15,15.5D)),
                                VoxelShapeUtil.shapeRotCW90( Block.makeCuboidShape(0D,15,0D,16D,16,16D)),
                                        VoxelShapeUtil.shapeRotCW90( Block.makeCuboidShape(14.5D,0D,0.5D,15.5D,15,15.5D)));
                    }
                    if(state.get(STATES) == EnumState.CENTER) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0.5D,12D,0.5D,15.5D,15,15.5D)),
                                        VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0D,15,0D,16D,16,16D)));
                    }
                     return VoxelShapes.or(
                             VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0.5D,12D,0.5D,15.5D,15,15.5D)),
                             VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0D,15,0D,16D,16,16D)),
                             VoxelShapeUtil.shapeRotCW90( Block.makeCuboidShape(0.5D,0D,0.5D,1.5D,15,15.5D)),
                             VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(14.5D,0D,0.5D,15.5D,15,15.5D)));
                case NORTH:
                    if(state.get(STATES) == EnumState.RIGHT) {
                        return VoxelShapes.or(Block.makeCuboidShape(0.5D,12D,0.5D,15.5D,15,15.5D),
                                Block.makeCuboidShape(0D,15,0D,16D,16,16D),
                                Block.makeCuboidShape(0.5D,0D,0.5D,1.5D,15,15.5D));
                    }
                    if(state.get(STATES) == EnumState.LEFT) {
                        return VoxelShapes.or(Block.makeCuboidShape(0.5D,12D,0.5D,15.5D,15,15.5D),
                                Block.makeCuboidShape(0D,15,0D,16D,16,16D),
                                Block.makeCuboidShape(14.5D,0D,0.5D,15.5D,15,15.5D));
                    }
                case SOUTH:
                    if(state.get(STATES) == EnumState.LEFT) {
                        return VoxelShapes.or(Block.makeCuboidShape(0.5D,12D,0.5D,15.5D,15,15.5D),
                                Block.makeCuboidShape(0D,15,0D,16D,16,16D),
                                Block.makeCuboidShape(0.5D,0D,0.5D,1.5D,15,15.5D));
                    }
                    if(state.get(STATES) == EnumState.RIGHT) {
                        return VoxelShapes.or(Block.makeCuboidShape(0.5D,12D,0.5D,15.5D,15,15.5D),
                                Block.makeCuboidShape(0D,15,0D,16D,16,16D),
                                Block.makeCuboidShape(14.5D,0D,0.5D,15.5D,15,15.5D));
                    }
                    if(state.get(STATES) == EnumState.CENTER) {
                        return VoxelShapes.or(Block.makeCuboidShape(0.5D,12D,0.5D,15.5D,15,15.5D),
                                Block.makeCuboidShape(0D,15,0D,16D,16,16D));
                    }
                    return VoxelShapes.or(Block.makeCuboidShape(0.5D,12D,0.5D,15.5D,15,15.5D),
                            Block.makeCuboidShape(0D,15,0D,16D,16,16D),
                            Block.makeCuboidShape(0.5D,0D,0.5D,1.5D,15,15.5D),
                            Block.makeCuboidShape(14.5D,0D,0.5D,15.5D,15,15.5D));
            }
            return VoxelShapes.fullCube();
        }
        if(state.getBlock() == RegBlocks.BIOLAB_TABLE4.get()) {
            switch (state.get(FACING)) {
                case EAST:
                    if(state.get(STATES) == EnumState.RIGHT) {
                        return VoxelShapes.or(VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0.5D,4D,0.5D,15.5D,15,15.5D)),
                                VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0D,15,0D,16D,16,16D)),
                                VoxelShapeUtil.shapeRotCW90(   Block.makeCuboidShape(0.5D,0D,0.5D,1.5D,15,15.5D)));
                    }
                    if(state.get(STATES) == EnumState.LEFT) {
                        return VoxelShapes.or(VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0.5D,4D,0.5D,15.5D,15,15.5D)),
                                VoxelShapeUtil.shapeRotCW90( Block.makeCuboidShape(0D,15,0D,16D,16,16D)),
                                VoxelShapeUtil.shapeRotCW90( Block.makeCuboidShape(14.5D,0D,0.5D,15.5D,15,15.5D)));
                    }
                case WEST:
                    if(state.get(STATES) == EnumState.LEFT) {
                        return VoxelShapes.or(VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0.5D,4D,0.5D,15.5D,15,15.5D)),
                                VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0D,15,0D,16D,16,16D)),
                                VoxelShapeUtil.shapeRotCW90(   Block.makeCuboidShape(0.5D,0D,0.5D,1.5D,15,15.5D)));
                    }
                    if(state.get(STATES) == EnumState.RIGHT) {
                        return VoxelShapes.or(VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0.5D,4D,0.5D,15.5D,15,15.5D)),
                                VoxelShapeUtil.shapeRotCW90( Block.makeCuboidShape(0D,15,0D,16D,16,16D)),
                                VoxelShapeUtil.shapeRotCW90( Block.makeCuboidShape(14.5D,0D,0.5D,15.5D,15,15.5D)));
                    }
                    if(state.get(STATES) == EnumState.CENTER) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0.5D,4D,0.5D,15.5D,15,15.5D)),
                                VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0D,15,0D,16D,16,16D)));
                    }
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0.5D,4D,0.5D,15.5D,15,15.5D)),
                            VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0D,15,0D,16D,16,16D)),
                            VoxelShapeUtil.shapeRotCW90( Block.makeCuboidShape(0.5D,0D,0.5D,1.5D,15,15.5D)),
                            VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(14.5D,0D,0.5D,15.5D,15,15.5D)));
                case NORTH:
                    if(state.get(STATES) == EnumState.RIGHT) {
                        return VoxelShapes.or(Block.makeCuboidShape(0.5D,4D,0.5D,15.5D,15,15.5D),
                                Block.makeCuboidShape(0D,15,0D,16D,16,16D),
                                Block.makeCuboidShape(0.5D,0D,0.5D,1.5D,15,15.5D));
                    }
                    if(state.get(STATES) == EnumState.LEFT) {
                        return VoxelShapes.or(Block.makeCuboidShape(0.5D,4D,0.5D,15.5D,15,15.5D),
                                Block.makeCuboidShape(0D,15,0D,16D,16,16D),
                                Block.makeCuboidShape(14.5D,0D,0.5D,15.5D,15,15.5D));
                    }
                case SOUTH:
                    if(state.get(STATES) == EnumState.LEFT) {
                        return VoxelShapes.or(Block.makeCuboidShape(0.5D,4D,0.5D,15.5D,15,15.5D),
                                Block.makeCuboidShape(0D,15,0D,16D,16,16D),
                                Block.makeCuboidShape(0.5D,0D,0.5D,1.5D,15,15.5D));
                    }
                    if(state.get(STATES) == EnumState.RIGHT) {
                        return VoxelShapes.or(Block.makeCuboidShape(0.5D,4D,0.5D,15.5D,15,15.5D),
                                Block.makeCuboidShape(0D,15,0D,16D,16,16D),
                                Block.makeCuboidShape(14.5D,0D,0.5D,15.5D,15,15.5D));
                    }
                    if(state.get(STATES) == EnumState.CENTER) {
                        return VoxelShapes.or(Block.makeCuboidShape(0.5D,4D,0.5D,15.5D,15,15.5D),
                                Block.makeCuboidShape(0D,15,0D,16D,16,16D));
                    }
                    return VoxelShapes.or(Block.makeCuboidShape(0.5D,4D,0.5D,15.5D,15,15.5D),
                            Block.makeCuboidShape(0D,15,0D,16D,16,16D),
                            Block.makeCuboidShape(0.5D,0D,0.5D,1.5D,15,15.5D),
                            Block.makeCuboidShape(14.5D,0D,0.5D,15.5D,15,15.5D));
            }
            return VoxelShapes.fullCube();
        }
        return VoxelShapes.fullCube();
    }
    public BlockState getActualState(BlockState s, IWorld w, BlockPos p, Block attachBlock) {
        Block blockWest = w.getBlockState(p.west()).getBlock();
        Block blockEast = w.getBlockState(p.east()).getBlock();
        Block blockSouth = w.getBlockState(p.south()).getBlock();
        Block blockNorth = w.getBlockState(p.north()).getBlock();

        switch (s.get(FACING)) {
            case NORTH:
                if (blockWest != attachBlock && blockEast != attachBlock) {
                    return s.with(FACING, s.get(FACING)).with(STATES, EnumState.DEF);
                } else if (blockWest == attachBlock && blockEast == attachBlock) {
                    return s.with(FACING, s.get(FACING)).with(STATES, EnumState.CENTER);
                } else if (blockWest == attachBlock) {
                    return s.with(FACING, s.get(FACING)).with(STATES, EnumState.LEFT);
                } else if (blockEast == attachBlock) {
                    return s.with(FACING, s.get(FACING)).with(STATES, EnumState.RIGHT);
                }
            case EAST:
                if (blockNorth != attachBlock && blockSouth != attachBlock) {
                    return s.with(FACING, s.get(FACING)).with(STATES, EnumState.DEF);
                } else if (blockNorth == attachBlock && blockSouth == attachBlock) {
                    return s.with(FACING, s.get(FACING)).with(STATES, EnumState.CENTER);
                } else if (blockNorth == attachBlock) {
                    return s.with(FACING, s.get(FACING)).with(STATES, EnumState.LEFT);
                } else if (blockSouth == attachBlock) {
                    return s.with(FACING, s.get(FACING)).with(STATES, EnumState.RIGHT);
                }
            case SOUTH:
                if (blockEast != attachBlock && blockWest != attachBlock) {
                    return s.with(FACING, s.get(FACING)).with(STATES, EnumState.DEF);
                } else if (blockEast == attachBlock && blockWest == attachBlock) {
                    return s.with(FACING, s.get(FACING)).with(STATES, EnumState.CENTER);
                } else if (blockEast == attachBlock) {
                    return s.with(FACING, s.get(FACING)).with(STATES, EnumState.LEFT);
                } else if (blockWest == attachBlock) {
                    return s.with(FACING, s.get(FACING)).with(STATES, EnumState.RIGHT);
                }
            case WEST:
                if (blockSouth != attachBlock && blockNorth != attachBlock) {
                    return s.with(FACING, s.get(FACING)).with(STATES, EnumState.DEF);
                } else if (blockSouth == attachBlock && blockNorth == attachBlock) {
                    return s.with(FACING, s.get(FACING)).with(STATES, EnumState.CENTER);
                } else if (blockSouth == attachBlock) {
                    return s.with(FACING, s.get(FACING)).with(STATES, EnumState.LEFT);
                } else if (blockNorth == attachBlock) {
                    return s.with(FACING, s.get(FACING)).with(STATES, EnumState.RIGHT);
                }
        }
        return s;
    }
        //настроить тени, сделать красивые шейпы
    public BlockState updatePostPlacement(BlockState s, Direction f, BlockState facingState, IWorld w, BlockPos p, BlockPos facingPos) {
        Block block = s.getBlock();
        if(block instanceof LinearTable) {
            return this.getActualState(s, w, p, block);
        }
        return s;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, STATES, WATERLOGGED);
    }
    public static enum EnumState implements IStringSerializable
    {
        DEF("def"),
        LEFT("left"),
        RIGHT("right"),
        CENTER("center");

        private final String name;

        private EnumState(String name)
        {
            this.name = name;
        }

        public String getString()
        {
            return this.name;
        }



    }
}
