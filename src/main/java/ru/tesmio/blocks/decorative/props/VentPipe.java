package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockCustomModel;
import ru.tesmio.reg.RegBlocks;

public class VentPipe extends BlockCustomModel {
    public VentPipe(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(DEFAULT, Boolean.valueOf(true)).with(NORTH, Boolean.valueOf(false))
                .with(EAST, Boolean.valueOf(false)) .with(DOWN, Boolean.valueOf(false)).with(UP, Boolean.valueOf(false)).with(SOUTH, Boolean.valueOf(false))
                .with(WEST, Boolean.valueOf(false)).with(WATERLOGGED, Boolean.valueOf(false)));
    }
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if(!worldIn.isRemote() && worldIn.getBlockState(pos).isAir()) {

        }
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        World w = context.getWorld();
        BlockPos p = context.getPos();
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return super.getStateForPlacement(context)
                .with(NORTH, Boolean.valueOf(this.canConnect(w,p, Direction.NORTH)))
                .with(EAST, Boolean.valueOf(this.canConnect(w,p, Direction.EAST)))
                .with(SOUTH, Boolean.valueOf(this.canConnect(w,p, Direction.SOUTH)))
                .with(WEST, Boolean.valueOf(this.canConnect(w,p, Direction.WEST)))
                .with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }
    public static final BooleanProperty DEFAULT = BooleanProperty.create("default");
    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    protected static final VoxelShape[] SHAPES = new VoxelShape[] {
            /*1*/	VoxelShapes.create(0.375D, 0.375D, 0.375D, 0.625D, 0.625D, 0.625D),//одиночный кубик, + если (снизу\сверху, дефолт)
            /*2*/	VoxelShapes.create(0.375D, 0.375D, 0.375D, 0.625D, 0.625D, 1D),//когда что то есть на +Z
            /*3*/	VoxelShapes.create(0D, 0.375D, 0.375D, 0.625D, 0.625D, 0.625D),//когда что то есть на -X
            /*4*/	VoxelShapes.create(0D, 0.375D, 0.375D, 0.625D, 0.625D, 1D),//когда есть что то на -X и +Z
            /*5*/	VoxelShapes.create(0.375D, 0.375D, 0.625D, 0.625D, 0.625D, 0D),//когда что то есть на -Z
            /*6*/	VoxelShapes.create(0.375D, 0.375D, 0D, 0.625D, 0.625D, 1D), //когда что то есть на -Z и +Z
            /*7*/	VoxelShapes.create(0D, 0.375D, 0.625D, 0.625D, 0.625D, 0D),//когда что то есть на -X и -Z
            /*8*/	VoxelShapes.create(0.625D, 0.375D, 0D, 0D, 0.625D, 1D),//когда что то есть на +Z, -Z и -X
            /*9*/	VoxelShapes.create(0.375D, 0.375D, 0.375D, 1D, 0.625D, 0.625D),//когда что то есть на +X
            /*10*/	VoxelShapes.create(0.375D, 0.375D, 0.375D, 1D, 0.625D, 1D),//когда что то есть на +X и +Z
            /*11*/	VoxelShapes.create(0D, 0.375D, 0.375D, 1D, 0.625D, 0.625D),//когда что то есть на -X и +X
            /*12*/	VoxelShapes.create(0D, 0.375D, 0.375D, 1D, 0.625D, 1D),//когда что то есть на -X и +X и +Z
            /*13*/	VoxelShapes.create(0.375D, 0.375D, 0.625D, 1D, 0.625D, 0D),//когда что то есть на +X, -Z
            /*14*/	VoxelShapes.create(0.375D, 0.375D, 0D, 1D, 0.625D, 1D),//когда что то есть на -Z и +Z и +X
            /*15*/	VoxelShapes.create(0D, 0.375D, 0.375D, 1D, 0.625D, 0D),//когда что то есть на -X и +X и +Z
            /*16*/	VoxelShapes.create(0D, 0.375D, 0D, 1D, 0.625D, 1D),//все четыре стороны
    };
    public VoxelShape getShape(BlockState s, IBlockReader w, BlockPos p, ISelectionContext c) {
        return SHAPES[getShapeIndex(w.getBlockState(p))];
    }

    public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, reader, pos, context);
    }
    public boolean canConnect(IWorld w,BlockPos p, Direction d) {
        Block connector = w.getBlockState(p.offset(d)).getBlock();
        boolean flag1 = connector instanceof VentPipe;
        boolean flag2 = connector == RegBlocks.BIOLAB_TABLE_CASE.get();
        boolean flag3 = connector == RegBlocks.CHEMLAB_TABLE_CASE.get();
        boolean flag4 = connector == RegBlocks.VENT_PIPE_BASE.get();
        boolean flag5 = connector == RegBlocks.CIRCLE_FILTER.get();
        boolean flag6 = connector == RegBlocks.REST_FILTER.get();
        return flag1 || flag2 || flag3|| flag4 || flag5|| flag6;
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(DEFAULT, NORTH, EAST, WEST, SOUTH, UP, DOWN, WATERLOGGED);
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos p, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) worldIn.getPendingFluidTicks().scheduleTick(p, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        boolean flag0 =  canConnect(worldIn, p, Direction.DOWN);
        boolean flag4 =  canConnect(worldIn, p, Direction.UP);
        boolean flag =  canConnect(worldIn, p, Direction.NORTH);
        boolean flag1 = canConnect(worldIn, p, Direction.EAST);
        boolean flag2 = canConnect(worldIn, p, Direction.SOUTH);
        boolean flag3 = canConnect(worldIn, p, Direction.WEST);
        return stateIn
                .with(DEFAULT, Boolean.valueOf(true))
                .with(UP, Boolean.valueOf(flag4))
                .with(NORTH, Boolean.valueOf(flag))
                .with(EAST, Boolean.valueOf(flag1))
                .with(SOUTH, Boolean.valueOf(flag2))
                .with(WEST, Boolean.valueOf(flag3))
                .with(DOWN, Boolean.valueOf(flag0));
    }
    private static int getShapeIndex(BlockState state)
    {
        int i = 0;
        if(state.getBlock() instanceof VentPipe) {
            if ((state.get(NORTH)).booleanValue()) {
                i |= 1 << Direction.NORTH.getHorizontalIndex();
            }

            if ((state.get(EAST)).booleanValue()) {
                i |= 1 << Direction.EAST.getHorizontalIndex();
            }

            if ((state.get(SOUTH)).booleanValue()) {
                i |= 1 << Direction.SOUTH.getHorizontalIndex();
            }
            if ((state.get(WEST)).booleanValue()) {
                i |= 1 << Direction.WEST.getHorizontalIndex();
            }
        }

        return i;
    }
}
