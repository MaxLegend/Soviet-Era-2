package ru.tesmio.blocks.decorative.devices;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockCornerCustomModel;

import java.util.Set;

public class BlockRedstoneWire extends BlockCornerCustomModel {
    final VoxelShape BOXS[] = new VoxelShape[] {
            Block.makeCuboidShape(0D, 0D, 0D, 3D, 16D, 16D),
            Block.makeCuboidShape(13D, 0D, 0D, 16D, 16D, 16D),
            Block.makeCuboidShape(0D, 0D, 0D, 16D, 16D, 3D),
            Block.makeCuboidShape(0D, 0D, 13D, 16D, 16D, 16D)};
    public static final IntegerProperty POWER = BlockStateProperties.POWER_0_15;
    private boolean canProvidePower = true;
    public BlockRedstoneWire(Properties properties, float shadingInside) {
        super(properties, shadingInside);
        this.setDefaultState(this.stateContainer.getBaseState().with(POWER, 0).with(FACING, Direction.NORTH).with(ENUM_CONNECT, EnumConnent.NOT_CONNECT));
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWER, ENUM_CONNECT, WATERLOGGED);
    }
    public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return this.getShape(state, worldIn, pos, null);
    }
    public VoxelShape getRayTraceShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, reader, pos, context);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (state.get(FACING)) {
            case EAST:
                if(state.get(ENUM_CONNECT) == EnumConnent.CORNER_LEFT) return VoxelShapes.or(BOXS[0],BOXS[3]);
                if(state.get(ENUM_CONNECT) == EnumConnent.CORNER_RIGHT) return VoxelShapes.or(BOXS[0],BOXS[2]);
                return BOXS[0];
            case WEST:
                if(state.get(ENUM_CONNECT) == EnumConnent.CORNER_LEFT) return VoxelShapes.or(BOXS[1],BOXS[2]);
                if(state.get(ENUM_CONNECT) == EnumConnent.CORNER_RIGHT) return VoxelShapes.or(BOXS[1],BOXS[3]);
                return BOXS[1];
            case SOUTH:
                if(state.get(ENUM_CONNECT) == EnumConnent.CORNER_LEFT) return VoxelShapes.or(BOXS[2],BOXS[0]);
                if(state.get(ENUM_CONNECT) == EnumConnent.CORNER_RIGHT) return VoxelShapes.or(BOXS[2],BOXS[1]);
                return BOXS[2];
            case NORTH:
                if(state.get(ENUM_CONNECT) == EnumConnent.CORNER_LEFT) return VoxelShapes.or(BOXS[3],BOXS[1]);
                if(state.get(ENUM_CONNECT) == EnumConnent.CORNER_RIGHT) return VoxelShapes.or(BOXS[3],BOXS[0]);
                return BOXS[3];
            default:
                return VoxelShapes.fullCube();
        }
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        World w = context.getWorld();
        BlockPos p = context.getPos();
        w.getPendingBlockTicks().scheduleTick(p, this, 1);
        BlockState thisBS = this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite())
                .with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
        return thisBS.with(ENUM_CONNECT, EnumConnent.NOT_CONNECT);

    }

    private int getStrongestSignal(World world, BlockPos pos) {
        this.canProvidePower = false;
        int i = world.getRedstonePowerFromNeighbors(pos);
        this.canProvidePower = true;
        int j = 0;
        if (i < 15) {
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                BlockPos blockpos = pos.offset(direction);
                BlockState blockstate = world.getBlockState(blockpos);
                j = Math.max(j, this.getPower(blockstate));
                BlockPos blockpos1 = pos.up();
                if (blockstate.isNormalCube(world, blockpos) && !world.getBlockState(blockpos1).isNormalCube(world, blockpos1)) {
                    j = Math.max(j, this.getPower(world.getBlockState(blockpos.up())));
                } else if (!blockstate.isNormalCube(world, blockpos)) {
                    j = Math.max(j, this.getPower(world.getBlockState(blockpos.down())));
                }
            }
        }

        return Math.max(i, j - 1);
    }

    public void neighborChanged(BlockState s, World w, BlockPos p, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!w.isRemote) {
            this.updatePower(w, p, s);
            BlockState state = w.getBlockState(p);
            BlockState southPos = w.getBlockState(p.south());
            BlockState northPos = w.getBlockState(p.north());
            BlockState eastPos = w.getBlockState(p.east());
            BlockState westPos = w.getBlockState(p.west());
            w.getPendingBlockTicks().scheduleTick(p, this, 4);
            switch(s.get(FACING)) {
                case SOUTH:
                    if (southPos.getBlock() instanceof BlockCornerCustomModel) {
                        if (southPos.get(FACING) == Direction.EAST) w.setBlockState(p,state.with(ENUM_CONNECT, EnumConnent.CORNER_LEFT));
                        if (southPos.get(FACING) == Direction.WEST) w.setBlockState(p,state.with(ENUM_CONNECT, EnumConnent.CORNER_RIGHT));
                    } else {
                        w.setBlockState(p, state.with(ENUM_CONNECT, EnumConnent.NOT_CONNECT).with(FACING, s.get(FACING)));
                    }
                    break;
                case WEST:
                    if (westPos.getBlock() instanceof BlockCornerCustomModel) {
                        if (westPos.get(ENUM_CONNECT) == EnumConnent.NOT_CONNECT) {
                            if (westPos.get(FACING) == Direction.NORTH) w.setBlockState(p, state.with(ENUM_CONNECT, EnumConnent.CORNER_RIGHT));
                            if (westPos.get(FACING) == Direction.SOUTH) w.setBlockState(p, state.with(ENUM_CONNECT, EnumConnent.CORNER_LEFT));
                        }
                    } else {
                        w.setBlockState(p, state.with(ENUM_CONNECT, EnumConnent.NOT_CONNECT).with(FACING, s.get(FACING)));
                    }
                    break;
                case EAST:
                    if (eastPos.getBlock() instanceof BlockCornerCustomModel) {
                        if (eastPos.get(FACING) == Direction.NORTH) w.setBlockState(p,state.with(ENUM_CONNECT, EnumConnent.CORNER_LEFT));
                        if (eastPos.get(FACING) == Direction.SOUTH) w.setBlockState(p,state.with(ENUM_CONNECT, EnumConnent.CORNER_RIGHT));
                    } else {
                        w.setBlockState(p, state.with(ENUM_CONNECT, EnumConnent.NOT_CONNECT).with(FACING, s.get(FACING)));
                    }
                    break;
                case NORTH:
                    if (northPos.getBlock() instanceof BlockCornerCustomModel) {
                        if (northPos.get(FACING) == Direction.WEST) w.setBlockState(p,state.with(ENUM_CONNECT, EnumConnent.CORNER_LEFT));
                        if (northPos.get(FACING) == Direction.EAST) w.setBlockState(p,state.with(ENUM_CONNECT, EnumConnent.CORNER_RIGHT));
                    } else {
                        w.setBlockState(p, state.with(ENUM_CONNECT, EnumConnent.NOT_CONNECT).with(FACING, s.get(FACING)));
                    }
                    break;
            }
        }
    }

    public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return !this.canProvidePower ? 0 : blockState.getWeakPower(blockAccess, pos, side);
    }

    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        if (this.canProvidePower && side != Direction.DOWN) {
            int i = blockState.get(POWER);
            if (i == 0) {
                return 0;
            } else {

                return i - 1;
            }
        } else {
            return 0;
        }
    }
    public boolean canProvidePower(BlockState state) {
        return this.canProvidePower;
    }


    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
//        if (!oldState.matchesBlock(state.getBlock()) && !worldIn.isRemote) {
//            this.updatePower(worldIn, pos, state);
//
//            for(Direction direction : Direction.Plane.VERTICAL) {
//                worldIn.notifyNeighborsOfStateChange(pos.offset(direction), this);
//            }
//
//            this.updateNeighboursStateChange(worldIn, pos);
//        }
    }
    private int getPower(BlockState state) {
        return state.matchesBlock(this) ? state.get(POWER) : 0;
    }
    private void updatePower(World world, BlockPos pos, BlockState state) {
        int i = this.getStrongestSignal(world, pos);
        if (state.get(POWER) != i) {
            if (world.getBlockState(pos) == state) {
                world.setBlockState(pos, state.with(POWER, Integer.valueOf(i)), 2);
            }
            Set<BlockPos> set = Sets.newHashSet();
            set.add(pos);

            for(Direction direction : Direction.values()) {
                set.add(pos.offset(direction));
            }

            for(BlockPos blockpos : set) {
                world.notifyNeighborsOfStateChange(blockpos, this);
            }
        }
    }
}
