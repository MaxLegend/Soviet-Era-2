package ru.tesmio.blocks.baseblock;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SixWayBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Map;

public class BlockSideConnect extends BaseBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    final VoxelShape SHAPE = Block.makeCuboidShape(4D, 0D, 4D, 12D, 16D, 12D);
    public static final BooleanProperty IS_NORTH = BooleanProperty.create("north");
    public static final BooleanProperty IS_SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty IS_WEST = BooleanProperty.create("west");
    public static final BooleanProperty IS_EAST = BooleanProperty.create("east");
    protected VoxelShape[] collisionShapes;
    protected VoxelShape[] shapes;
    protected static final Map<Direction, BooleanProperty> FACING_TO_PROPERTY = SixWayBlock.FACING_TO_PROPERTY_MAP.entrySet().stream().filter((facingProperty) -> facingProperty.getKey().getAxis().isHorizontal()).collect(Util.toMapCollector());
    private final Object2IntMap<BlockState> statePaletteMap = new Object2IntOpenHashMap<>();
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        IBlockReader iblockreader = context.getWorld();
        BlockPos blockpos = context.getPos();
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        BlockPos blockpos1 = blockpos.north();
        BlockPos blockpos2 = blockpos.east();
        BlockPos blockpos3 = blockpos.south();
        BlockPos blockpos4 = blockpos.west();
        BlockState blockstate = iblockreader.getBlockState(blockpos1);
        BlockState blockstate1 = iblockreader.getBlockState(blockpos2);
        BlockState blockstate2 = iblockreader.getBlockState(blockpos3);
        BlockState blockstate3 = iblockreader.getBlockState(blockpos4);
        return super.getStateForPlacement(context)
                .with(IS_NORTH, Boolean.valueOf(this.canConnect(blockstate, blockstate.isSolidSide(iblockreader, blockpos1, Direction.SOUTH), Direction.SOUTH)))
                .with(IS_EAST, Boolean.valueOf(this.canConnect(blockstate1, blockstate1.isSolidSide(iblockreader, blockpos2, Direction.WEST), Direction.WEST)))
                .with(IS_SOUTH, Boolean.valueOf(this.canConnect(blockstate2, blockstate2.isSolidSide(iblockreader, blockpos3, Direction.NORTH), Direction.NORTH)))
                .with(IS_WEST, Boolean.valueOf(this.canConnect(blockstate3, blockstate3.isSolidSide(iblockreader, blockpos4, Direction.EAST), Direction.EAST)))
                .with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
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
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return facing.getAxis().getPlane() == Direction.Plane.HORIZONTAL ? stateIn.with(FACING_TO_PROPERTY.get(facing), Boolean.valueOf(this.canConnect(facingState, facingState.isSolidSide(worldIn, facingPos, facing.getOpposite()), facing.getOpposite()))) : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }
    public boolean canConnect(BlockState state, boolean isSideSolid, Direction direction) {
        Block block = state.getBlock();
        boolean flag1 = block instanceof BlockSideConnect;
        return !cannotAttach(block) && isSideSolid || flag1 ;
    }
    public BlockSideConnect(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, Boolean.valueOf(false))
                .with(IS_NORTH, false)
                .with(IS_EAST, false)
                .with(IS_WEST, false)
                .with(IS_SOUTH, false));

        for(BlockState blockstate : this.stateContainer.getValidStates()) {
            this.getIndex(blockstate);
        }
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(IS_NORTH, IS_EAST, IS_WEST, IS_SOUTH, WATERLOGGED);
    }
    protected VoxelShape[] makeShapes(float nodeWidth, float extensionWidth, float nodeHeight, float extensionBottom, float extensionHeight) {
        float f = 8.0F - nodeWidth;
        float f1 = 8.0F + nodeWidth;
        float f2 = 8.0F - extensionWidth;
        float f3 = 8.0F + extensionWidth;
        VoxelShape voxelshape = Block.makeCuboidShape((double)f, 0.0D, (double)f, (double)f1, (double)nodeHeight, (double)f1);
        VoxelShape voxelshape1 = Block.makeCuboidShape((double)f2, (double)extensionBottom, 0.0D, (double)f3, (double)extensionHeight, (double)f3);
        VoxelShape voxelshape2 = Block.makeCuboidShape((double)f2, (double)extensionBottom, (double)f2, (double)f3, (double)extensionHeight, 16.0D);
        VoxelShape voxelshape3 = Block.makeCuboidShape(0.0D, (double)extensionBottom, (double)f2, (double)f3, (double)extensionHeight, (double)f3);
        VoxelShape voxelshape4 = Block.makeCuboidShape((double)f2, (double)extensionBottom, (double)f2, 16.0D, (double)extensionHeight, (double)f3);
        VoxelShape voxelshape5 = VoxelShapes.or(voxelshape1, voxelshape4);
        VoxelShape voxelshape6 = VoxelShapes.or(voxelshape2, voxelshape3);
        VoxelShape[] avoxelshape = new VoxelShape[]{VoxelShapes.empty(), voxelshape2, voxelshape3, voxelshape6, voxelshape1, VoxelShapes.or(voxelshape2, voxelshape1), VoxelShapes.or(voxelshape3, voxelshape1), VoxelShapes.or(voxelshape6, voxelshape1), voxelshape4, VoxelShapes.or(voxelshape2, voxelshape4), VoxelShapes.or(voxelshape3, voxelshape4), VoxelShapes.or(voxelshape6, voxelshape4), voxelshape5, VoxelShapes.or(voxelshape2, voxelshape5), VoxelShapes.or(voxelshape3, voxelshape5), VoxelShapes.or(voxelshape6, voxelshape5)};

        for(int i = 0; i < 16; ++i) {
            avoxelshape[i] = VoxelShapes.or(voxelshape, avoxelshape[i]);
        }

        return avoxelshape;
    }
    private static int getMask(Direction facing) {
        return 1 << facing.getHorizontalIndex();
    }

    protected int getIndex(BlockState state) {
        return this.statePaletteMap.computeIntIfAbsent(state, (stateIn) -> {
            int i = 0;
            if (stateIn.get(IS_NORTH)) {
                i |= getMask(Direction.NORTH);
            }

            if (stateIn.get(IS_EAST)) {
                i |= getMask(Direction.EAST);
            }

            if (stateIn.get(IS_SOUTH)) {
                i |= getMask(Direction.SOUTH);
            }

            if (stateIn.get(IS_WEST)) {
                i |= getMask(Direction.WEST);
            }

            return i;
        });
    }
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return !state.get(WATERLOGGED);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.shapes[this.getIndex(state)];
    }

    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.collisionShapes[this.getIndex(state)];
    }
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }
    public BlockState rotate(BlockState state, Rotation rot) {
        switch(rot) {
            case CLOCKWISE_180:
                return state.with(IS_NORTH, state.get(IS_SOUTH)).with(IS_EAST, state.get(IS_WEST)).with(IS_SOUTH, state.get(IS_NORTH)).with(IS_WEST, state.get(IS_EAST));
            case COUNTERCLOCKWISE_90:
                return state.with(IS_NORTH, state.get(IS_EAST)).with(IS_EAST, state.get(IS_SOUTH)).with(IS_SOUTH, state.get(IS_WEST)).with(IS_WEST, state.get(IS_NORTH));
            case CLOCKWISE_90:
                return state.with(IS_NORTH, state.get(IS_WEST)).with(IS_EAST, state.get(IS_NORTH)).with(IS_SOUTH, state.get(IS_EAST)).with(IS_WEST, state.get(IS_SOUTH));
            default:
                return state;
        }
    }
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        switch(mirrorIn) {
            case LEFT_RIGHT:
                return state.with(IS_NORTH, state.get(IS_SOUTH)).with(IS_SOUTH, state.get(IS_NORTH));
            case FRONT_BACK:
                return state.with(IS_EAST, state.get(IS_WEST)).with(IS_WEST, state.get(IS_EAST));
            default:
                return super.mirror(state, mirrorIn);
        }
    }
}
