package ru.tesmio.blocks.decorative.lamp;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import ru.tesmio.blocks.baseblock.BlockRotatedAxisCustomModel;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;

import java.util.Random;

public class FluoLamp3 extends BlockRotatedAxisCustomModel {

    public static final IntegerProperty LIT_VALUE = IntegerProperty.create("lit_power", 0, 4);

    final VoxelShape BOXS[] = new VoxelShape[] {Block.makeCuboidShape(16D, 0D, 10.5D, 0D, 3.2D, 5.5D),
            Block.makeCuboidShape(10.5D, 0D, 16D, 5.5D, 3.2D, 0D),
            Block.makeCuboidShape(10.5D, 16D, 16D, 5.5D, 12.8D, 0D),
            Block.makeCuboidShape(16D, 16D, 10.5D, 0D, 12.8D, 5.5D),
            Block.makeCuboidShape(0D, 5.5D, 0D, 16D, 10.5D, 3.2D),
            Block.makeCuboidShape(0D, 5D, 12.8D, 16D, 10.5D, 16D),
            Block.makeCuboidShape(12.8D, 5.5D, 0D, 16D, 10.5D, 16D),
            Block.makeCuboidShape(0D, 5.5D, 0D, 3.2D, 10.5D, 16D)};
    public FluoLamp3(Properties builder) {
        super(builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, EnumOrientation.NORTH).with(LIT_VALUE, Integer.valueOf(0)).with(WATERLOGGED, false));
    }
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerEntity, Hand handIn, BlockRayTraceResult hit) {
        ItemStack activeItemRight = playerEntity.getHeldItemMainhand();
        ItemStack activeItemLeft = playerEntity.getHeldItemOffhand();

        if(activeItemRight.getItem() == RegItems.PULLER.get()) {
            worldIn.setBlockState(pos, RegBlocks.BROKEN_FLUORESCENT_LAMP3.get().getDefaultState().with(BrokenFluoLamp3.FACING, state.get(FACING)));
            activeItemRight.damageItem(1, playerEntity, (player) -> player.sendBreakAnimation(handIn));
            state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.FLUOLAMP.get(), 1));
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }
    public BlockState getStateForPlacement(BlockItemUseContext c) {
        FluidState fluidstate = c.getWorld().getFluidState(c.getPos());
        int powerLight = c.getWorld().getRedstonePower(c.getPos(), c.getFace());
        for(Direction direction : c.getNearestLookingDirections()) {

            if (direction.getAxis() == Direction.Axis.Y) {

                if (powerLight == 0)
                    return this.getDefaultState().with(LIT_VALUE, 0).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER)).with(FACING, EnumOrientation.forFacing(direction, c.getPlacementHorizontalFacing()));
                if (powerLight <= 4 && powerLight > 0) {
                    return this.getDefaultState().with(LIT_VALUE, 1).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER)).with(FACING, EnumOrientation.forFacing(direction, c.getPlacementHorizontalFacing()));
                }
                if (powerLight <= 8 && powerLight > 4) {
                    return this.getDefaultState().with(LIT_VALUE, 2).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER)).with(FACING, EnumOrientation.forFacing(direction, c.getPlacementHorizontalFacing()));
                }
                if (powerLight <= 12 && powerLight > 8) {
                    return this.getDefaultState().with(LIT_VALUE, 3).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER)).with(FACING, EnumOrientation.forFacing(direction, c.getPlacementHorizontalFacing()));
                }
                if (powerLight > 12) {
                    return this.getDefaultState().with(LIT_VALUE, 4).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER)).with(FACING, EnumOrientation.forFacing(direction, c.getPlacementHorizontalFacing()));


                }
            } else {

                if (powerLight == 0)
                    return this.getDefaultState().with(LIT_VALUE, 0).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER)).with(FACING, EnumOrientation.forFacing(direction, direction));
                if (powerLight <= 4 && powerLight > 0) {
                    return this.getDefaultState().with(LIT_VALUE, 1).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER)).with(FACING, EnumOrientation.forFacing(direction, direction));
                }
                if (powerLight <= 8 && powerLight > 4) {
                    return this.getDefaultState().with(LIT_VALUE, 2).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER)).with(FACING, EnumOrientation.forFacing(direction, direction));
                }
                if (powerLight <= 12 && powerLight > 8) {
                    return this.getDefaultState().with(LIT_VALUE, 3).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER)).with(FACING, EnumOrientation.forFacing(direction, direction));
                }
                if (powerLight > 12) {
                    return this.getDefaultState().with(LIT_VALUE, 4).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER)).with(FACING, EnumOrientation.forFacing(direction, direction));

                }

            }
        }
        return this.getDefaultState();
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (state.get(FACING)) {
            case UP_X:
                return BOXS[3];
            case UP_Z:
                return BOXS[2];
            case DOWN_X:
                return BOXS[0];
            case DOWN_Z:
                return BOXS[1];
            case NORTH:
                return BOXS[4];
            case SOUTH:
                return BOXS[5];
            case WEST:
                return BOXS[7];
            case EAST:
                return BOXS[6];


        }
        return VoxelShapes.fullCube();
    }

    @Override
    public int  getLightValue(BlockState s, IBlockReader br, BlockPos pos) {
        switch (s.get(LIT_VALUE)) {
            case 0:
            default:
                return 0;
            case 1:
                return 4;
            case 2:
                return 9;
            case 3:
                return 12;
            case 4:
                return 15;

        }
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT_VALUE, WATERLOGGED);
    }
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!worldIn.isRemote) {

            for(Direction facing : Direction.values()) {
                int powerLight = worldIn.getRedstonePower(pos.offset(facing), facing);

                if (powerLight <= 4 && powerLight > 0) {
                    worldIn.getPendingBlockTicks().scheduleTick(pos, this, 4);
                    worldIn.setBlockState(pos, state.with(LIT_VALUE, 1), 2);
                }
                if (powerLight <= 8 && powerLight > 4) {
                    worldIn.getPendingBlockTicks().scheduleTick(pos, this, 4);
                    worldIn.setBlockState(pos, state.with(LIT_VALUE, 2), 2);
                }
                if (powerLight <= 12 && powerLight > 8) {
                    worldIn.getPendingBlockTicks().scheduleTick(pos, this, 4);
                    worldIn.setBlockState(pos, state.with(LIT_VALUE, 3), 2);
                }
                if(powerLight > 12) {
                    worldIn.getPendingBlockTicks().scheduleTick(pos, this, 4);
                    worldIn.setBlockState(pos, state.with(LIT_VALUE, 4), 2);
                }
            }
        }

    }
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (worldIn.isBlockPowered(pos)) {
            for (Direction facing : Direction.values()) {
                int powerLight = worldIn.getRedstonePower(pos.offset(facing), facing);
                worldIn.getPendingBlockTicks().scheduleTick(pos, this, 4);
                if (powerLight <= 4 && powerLight > 0) {

                    worldIn.setBlockState(pos, state.with(LIT_VALUE, 1), 2);
                }
                if (powerLight <= 8 && powerLight > 4) {
                    worldIn.setBlockState(pos, state.with(LIT_VALUE, 2), 2);
                }
                if (powerLight <= 12 && powerLight > 8) {
                    worldIn.setBlockState(pos, state.with(LIT_VALUE, 3), 2);
                }
                if (powerLight > 12) {
                    worldIn.setBlockState(pos, state.with(LIT_VALUE, 4), 2);
                }

            }

        } else {   worldIn.setBlockState(pos, state.with(LIT_VALUE, 0), 2); }
    }
}