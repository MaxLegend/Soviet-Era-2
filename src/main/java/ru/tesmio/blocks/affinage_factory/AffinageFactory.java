package ru.tesmio.blocks.affinage_factory;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import ru.tesmio.reg.RegTileEntitys;

import javax.annotation.Nullable;

public class AffinageFactory extends Block {
    private static final VoxelShape NORTH_AABB = VoxelShapes.or(Block.makeCuboidShape(0.0D, 0.0D, 5.0D, 9.0D, 10.0D, 14.0D),
            Block.makeCuboidShape(1.0D, 10.0D, 5.0D, 8.0D, 16.0D, 14.0D),
            Block.makeCuboidShape(0.0D, 1.5D, 7.5D, 11.0D, 9.5D, 10.5D),
            Block.makeCuboidShape(0.0D, 2D, 8D, 14.0D, 9D, 10D),
            Block.makeCuboidShape(12.0D, 2D, 8D, 14.0D, 9D, 2D),
            Block.makeCuboidShape(16.0D, 0D, 5D, 11.0D, 5D, 0D));

    private static final  VoxelShape EAST_AABB = VoxelShapes.or(Block.makeCuboidShape(2.0D, 0.0D, 9.0D, 11.0D, 10.0D, 0.0D),
            Block.makeCuboidShape(2.0D, 10.0D, 8.0D, 11.0D, 16.0D, 1.0D),
            Block.makeCuboidShape(5.5D, 1.5D, 10.75D, 8.5D, 9.5D, 8.0D),
            Block.makeCuboidShape(6D, 2D, 14D, 8D, 9D, 8.0D),
            Block.makeCuboidShape(14D, 2D, 14D, 8D, 9D, 12.0D),
            Block.makeCuboidShape(16D, 0D, 11D, 11D, 5D, 16.0D));

    private static final VoxelShape WEST_AABB = VoxelShapes.or(Block.makeCuboidShape(14.0D, 0.0D, 16.0D, 5.0D, 10.0D, 7.0D),
            Block.makeCuboidShape(14D, 10.0D, 8.0D, 5D, 16.0D, 15.0D),
            Block.makeCuboidShape(7.5D, 1.5D, 5.25D, 10.5D, 9.5D, 8D),
            Block.makeCuboidShape(8D, 2D, 2D, 10D, 9D, 8D),
            Block.makeCuboidShape(8D, 2D, 2D, 2D, 9D, 4D),
            Block.makeCuboidShape(5D, 0D, 5D, 0D, 5D, 0D));

    private static final VoxelShape SOUTH_AABB = VoxelShapes.or(Block.makeCuboidShape(7.0D, 0.0D, 2.0D, 16.0D, 10.0D, 11.0D),
            Block.makeCuboidShape(8D, 6.0D, 2.0D, 15D, 16.0D, 11.0D),
            Block.makeCuboidShape(5.25D, 1.5D, 5.5D, 10.25D, 9.5D, 8.5D),
            Block.makeCuboidShape(2D, 2D, 6D, 14D, 9D, 8.25D),
            Block.makeCuboidShape(2D, 2D, 8D, 4D, 9D, 14D),
            Block.makeCuboidShape(5D, 0D, 11D, 0D, 5D, 16D));

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public AffinageFactory(Properties properties) {

        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(LIT, false).with(WATERLOGGED, false));
    }
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return RegTileEntitys.AFFINAGE_TE.get().create();
    }
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        player.addStat(Stats.BLOCK_MINED.get(this));
        player.addExhaustion(0.005F);
        spawnDrops(state, worldIn, pos, te, player, stack);
    }
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING, LIT, WATERLOGGED);
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Direction direction = state.get(FACING);

        switch (direction) {
            case EAST:return EAST_AABB;
            case WEST:return WEST_AABB;
            case SOUTH:return SOUTH_AABB;
            case NORTH:  return NORTH_AABB;
        }
        return NORTH_AABB;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (stack.hasDisplayName()) {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof AffinageTileEntity) {
                ((AffinageTileEntity) tile).setCustomName(stack.getDisplayName());
            }
        }
    }
    @Override
    public boolean hasComparatorInputOverride(BlockState state) {
        return true;
    }
    @Override
    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }



    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
                                             Hand handIn, BlockRayTraceResult hit) {
        if (worldIn != null && !worldIn.isRemote) {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof AffinageTileEntity) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tile, pos);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile instanceof AffinageTileEntity && state.getBlock() != newState.getBlock()) {
            AffinageTileEntity furnace = (AffinageTileEntity) tile;
            ((AffinageItemHandler) furnace.getInventory()).toNonNullList().forEach(item -> {
                ItemEntity itemEntity = new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), item);
                worldIn.addEntity(itemEntity);
            });
        }

        if (state.hasTileEntity() && state.getBlock() != newState.getBlock()) {
            worldIn.removeTileEntity(pos);
        }
    }
}