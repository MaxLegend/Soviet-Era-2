package ru.tesmio.blocks.decorative.devices;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockCustomModel;
import ru.tesmio.blocks.decorative.props.VentPipe;
import ru.tesmio.reg.RegItems;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class RedstoneCable extends BlockCustomModel {

    public static final BooleanProperty DEFAULT = BooleanProperty.create("def");
    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    public static final IntegerProperty POWER = BlockStateProperties.POWER_0_15;
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
    private boolean canProvidePower = true;
    public RedstoneCable(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(DEFAULT, Boolean.valueOf(true)).with(NORTH, Boolean.valueOf(false))
                .with(EAST, Boolean.valueOf(false)) .with(DOWN, Boolean.valueOf(false)).with(UP, Boolean.valueOf(false)).with(SOUTH, Boolean.valueOf(false))
                .with(WEST, Boolean.valueOf(false)).with(POWER, Integer.valueOf(0)).with(WATERLOGGED, Boolean.valueOf(false)));
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
                if (blockstate.isNormalCube(world, blockpos)) {
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
        }
    }

    public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return !this.canProvidePower ? 0 : blockState.getWeakPower(blockAccess, pos, side);
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader reader, List<ITextComponent> list, ITooltipFlag flags) {
        list.add(new TranslationTextComponent("info.amplifier_redstone_wire", Integer.toString(1000)));
    }
    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        if (this.canProvidePower) {
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
    public boolean canProvidePower(BlockState state) {
        return this.canProvidePower;
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {
                new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(2,4)),
        };
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
    public boolean canConnect(IWorld w, BlockPos p, Direction d) {
        Block connector = w.getBlockState(p.offset(d)).getBlock();
        boolean flag1 = connector instanceof RedstoneCable;
        return flag1;
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(DEFAULT, NORTH, EAST, WEST, SOUTH, UP, DOWN, POWER, WATERLOGGED);
    }
    public VoxelShape getShape(BlockState s, IBlockReader w, BlockPos p, ISelectionContext c) {
        return SHAPES[getShapeIndex(w.getBlockState(p))];
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
    public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, reader, pos, context);
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

}
