package ru.tesmio.blocks.redstone_wire;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FourWayBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.*;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;
//WiP - необходимо добавить дефаулт, условия для размещения, перенести и сделать кастомные шейпы.
public class RedstoneWire extends FourWayBlock {
    public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final IntegerProperty POWER = IntegerProperty.create("power", 0, 15);
    public static final EnumProperty<AttachFace> PLACEMENT =  BlockStateProperties.FACE;

    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public RedstoneWire(Properties properties) {
        super(2.0F, 2.0F, 2.0F, 2.0F, 2.0F, properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(POWER, 0).with(PLACEMENT, AttachFace.FLOOR).with(HORIZONTAL_FACING, Direction.NORTH)
                .with(NORTH, false)
                .with(SOUTH, false)
                .with(WEST, false)
                .with(EAST, false)

                .with(WATERLOGGED, Boolean.valueOf(false)));

    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING, POWER, PLACEMENT, NORTH, SOUTH, WEST, EAST, WATERLOGGED);
    }
    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }

    public boolean canConnect(BlockState state) {
        Block block = state.getBlock();
        boolean flag1 = block instanceof RedstoneWire;

        return flag1 ;
    }

    public BlockState getStateForPlacement(BlockItemUseContext c) {
        BlockState s = c.getWorld().getBlockState(c.getPos().north());
        BlockState s1 = c.getWorld().getBlockState(c.getPos().east());
        BlockState s2 = c.getWorld().getBlockState(c.getPos().south());
        BlockState s3 = c.getWorld().getBlockState(c.getPos().west());

        BlockState finalState = this.getDefaultState().with(NORTH, Boolean.valueOf(this.canConnect(s)))
                .with(EAST, Boolean.valueOf(this.canConnect(s1)))
                .with(SOUTH, Boolean.valueOf(this.canConnect(s2)))
                .with(WEST, Boolean.valueOf(this.canConnect(s3)))
                .with(WATERLOGGED, Boolean.valueOf(c.getWorld().getFluidState(c.getPos()).getFluid() == Fluids.WATER));

        for(Direction direction : c.getNearestLookingDirections()) {
                if (c.getFace() == Direction.UP) {
                    return finalState.with(PLACEMENT, AttachFace.FLOOR);
                }
                    if (c.getFace() == Direction.DOWN) {
                        return finalState.with(PLACEMENT, AttachFace.CEILING);
                } else {
                        switch (direction) {
                            case SOUTH:
                                return finalState.with(HORIZONTAL_FACING, Direction.SOUTH).with(PLACEMENT, AttachFace.WALL);
                            case WEST:
                                return finalState.with(HORIZONTAL_FACING, Direction.WEST).with(PLACEMENT, AttachFace.WALL);
                            case EAST:
                                return finalState.with(HORIZONTAL_FACING, Direction.EAST).with(PLACEMENT, AttachFace.WALL);
                            case NORTH:
                                return finalState.with(HORIZONTAL_FACING, Direction.NORTH).with(PLACEMENT, AttachFace.WALL);
                        }
                    }
            }
        return super.getStateForPlacement(c);
    }

    public void neighborChanged(BlockState s, World w, BlockPos p, Block b, BlockPos p2, boolean isMoving) {
        if (!w.isRemote) {
            BlockState stateN = w.getBlockState(p.north());
            BlockState stateE = w.getBlockState(p.east());
            BlockState stateS = w.getBlockState(p.south());
            BlockState stateW = w.getBlockState(p.west());
            w.getPendingBlockTicks().scheduleTick(p, this, 4);
            BlockState finalState = this.getDefaultState().with(NORTH, Boolean.valueOf(this.canConnect(stateN)))
                    .with(EAST, Boolean.valueOf(this.canConnect(stateE)))
                    .with(SOUTH, Boolean.valueOf(this.canConnect(stateS)))
                    .with(WEST, Boolean.valueOf(this.canConnect(stateW)))
                    .with(WATERLOGGED, Boolean.valueOf(w.getFluidState(p).getFluid() == Fluids.WATER));
            w.setBlockState(p, finalState);
        }
    }
    public void tick(BlockState s, ServerWorld w, BlockPos p, Random r) {
        BlockState stateN = w.getBlockState(p.north());
        BlockState stateE = w.getBlockState(p.east());
        BlockState stateS = w.getBlockState(p.south());
        BlockState stateW = w.getBlockState(p.west());
        w.getPendingBlockTicks().scheduleTick(p, this, 4);
        BlockState finalState = this.getDefaultState().with(NORTH, Boolean.valueOf(this.canConnect(stateN)))
                .with(EAST, Boolean.valueOf(this.canConnect(stateE)))
                .with(SOUTH, Boolean.valueOf(this.canConnect(stateS)))
                .with(WEST, Boolean.valueOf(this.canConnect(stateW)))
                .with(WATERLOGGED, Boolean.valueOf(w.getFluidState(p).getFluid() == Fluids.WATER));
        w.setBlockState(p, finalState);

    }
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(HORIZONTAL_FACING, rot.rotate(state.get(HORIZONTAL_FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(HORIZONTAL_FACING)));
    }
    protected static Direction getFacing(BlockState state) {
        switch(state.get(PLACEMENT)) {
            case CEILING:
                return Direction.DOWN;
            case FLOOR:
                return Direction.UP;
            default:
                return state.get(HORIZONTAL_FACING);
        }
    }


}
