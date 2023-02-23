package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SixWayBlock;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockCustomModel;
import ru.tesmio.utils.VoxelShapeUtil;

public class BarbWire extends BlockCustomModel {

    protected static final AxisAlignedBB[] AABB_BY_INDEX = new AxisAlignedBB[] {
            new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 0.5625D, 1.1D, 0.5625D)};

    public static final BooleanProperty NORTH = SixWayBlock.NORTH;
    public static final BooleanProperty EAST = SixWayBlock.EAST;
    public static final BooleanProperty SOUTH = SixWayBlock.SOUTH;
    public static final BooleanProperty WEST = SixWayBlock.WEST;

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public BarbWire(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(NORTH, false).with(SOUTH, false).with(WEST, false).with(EAST, false).with(WATERLOGGED, false));
    }
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if(!worldIn.isRemote() && worldIn.getBlockState(pos).isAir()) {

          //  state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(this, 1));
        }
    }
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return !state.get(WATERLOGGED);
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, WEST, SOUTH, WATERLOGGED);
    }
    public boolean canConnect(BlockState state) {
        Block block = state.getBlock();
        boolean flag1 = block instanceof BarbWire;
        return flag1;
    }
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
        return super.getStateForPlacement(context).with(NORTH, Boolean.valueOf(this.canConnect(blockstate)))
                .with(EAST, Boolean.valueOf(this.canConnect(blockstate1)))
                .with(SOUTH, Boolean.valueOf(this.canConnect(blockstate2)))
                .with(WEST, Boolean.valueOf(this.canConnect(blockstate3)))
                .with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }
    VoxelShape SHP_DEF[] = new VoxelShape[] {
            Block.makeCuboidShape(7,0,7,9,1,9), Block.makeCuboidShape(7.5,0,7.5,8.5,16,8.5)
    };
    VoxelShape SHP = Block.makeCuboidShape(7.5,2,0,8.5,14.5,8.5);
    public void onEntityCollision(BlockState s, World w, BlockPos p, Entity e) {
        e.attackEntityFrom(DamageSource.CACTUS, 3);
        e.setMotionMultiplier(s, new Vector3d(0.25D, (double)0.05F, 0.25D));
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        boolean isN, isS, isE, isW;
        isN = state.get(NORTH);
        isS = state.get(SOUTH);
        isW = state.get(WEST);
        isE = state.get(EAST);
        if(isS && isE && isW && isN) {
            return VoxelShapes.or(
                    SHP_DEF[0], SHP_DEF[1], SHP, VoxelShapeUtil.shapeRot180(SHP), VoxelShapeUtil.shapeRotCCW90(SHP), VoxelShapeUtil.shapeRotCW90(SHP));
        }
        if(isS && isE && isW) {
            return VoxelShapes.or(
                    SHP_DEF[0], SHP_DEF[1], VoxelShapeUtil.shapeRot180(SHP), VoxelShapeUtil.shapeRotCCW90(SHP), VoxelShapeUtil.shapeRotCW90(SHP));
        }
        if(isN && isE && isW) {
            return VoxelShapes.or(
                    SHP_DEF[0], SHP_DEF[1], SHP, VoxelShapeUtil.shapeRotCCW90(SHP), VoxelShapeUtil.shapeRotCW90(SHP));
        }
        if(isN && isE && isS) {
            return VoxelShapes.or(
                    SHP_DEF[0], SHP_DEF[1], SHP, VoxelShapeUtil.shapeRotCCW90(SHP), VoxelShapeUtil.shapeRot180(SHP));
        }
        if(isN && isW && isS) {
            return VoxelShapes.or(
                    SHP_DEF[0], SHP_DEF[1], SHP, VoxelShapeUtil.shapeRotCW90(SHP), VoxelShapeUtil.shapeRot180(SHP));
        }
        if(isN && isW) {
            return VoxelShapes.or(
                    SHP_DEF[0], SHP_DEF[1], SHP, VoxelShapeUtil.shapeRotCW90(SHP));
        }
        if(isN && isE) {
            return VoxelShapes.or(
                    SHP_DEF[0], SHP_DEF[1], SHP, VoxelShapeUtil.shapeRotCCW90(SHP));
        }
        if(isS && isE) {
            return VoxelShapes.or(
                    SHP_DEF[0], SHP_DEF[1], VoxelShapeUtil.shapeRot180(SHP), VoxelShapeUtil.shapeRotCCW90(SHP));
        }
        if(isS && isW) {
            return VoxelShapes.or(
                    SHP_DEF[0], SHP_DEF[1], VoxelShapeUtil.shapeRot180(SHP), VoxelShapeUtil.shapeRotCW90(SHP));
        }
        if(isE && isW) {
            return VoxelShapes.or(
                    SHP_DEF[0], SHP_DEF[1], VoxelShapeUtil.shapeRotCCW90(SHP), VoxelShapeUtil.shapeRotCW90(SHP));
        }
        if(isS && isN) {
            return VoxelShapes.or(
                    SHP_DEF[0], SHP_DEF[1], SHP, VoxelShapeUtil.shapeRot180(SHP));
        }
        if(isN) {
            return VoxelShapes.or(
                    SHP_DEF[0], SHP_DEF[1], SHP);
        }
        if(isS) {
            return VoxelShapes.or(
                    SHP_DEF[0], SHP_DEF[1], VoxelShapeUtil.shapeRot180(SHP));
        }
        if(isW) {
            return VoxelShapes.or(
                    SHP_DEF[0], SHP_DEF[1], VoxelShapeUtil.shapeRotCW90(SHP));
        }
        if(isE) {
            return VoxelShapes.or(
                    SHP_DEF[0], SHP_DEF[1], VoxelShapeUtil.shapeRotCCW90(SHP));
        }
        return VoxelShapes.or(
                SHP_DEF[0], SHP_DEF[1]);
    }

    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state,worldIn,pos,context);
    }
    public BlockState updatePostPlacement(BlockState s, Direction f, BlockState fs, IWorld w, BlockPos p, BlockPos facingPos) {
        if (s.get(WATERLOGGED)) {
            w.getPendingFluidTicks().scheduleTick(p, Fluids.WATER, Fluids.WATER.getTickRate(w));
        }
        return this.updateState(w, p, s);
    }
    public Block validBlock() {
        return this;
    }
    public BlockState updateState(IWorld w, BlockPos p, BlockState s) {
        if (!w.isRemote()) {
            BlockState s2 = w.getBlockState(p);
            BlockState southPos = w.getBlockState(p.south());
            BlockState northPos = w.getBlockState(p.north());
            BlockState eastPos = w.getBlockState(p.east());
            BlockState westPos = w.getBlockState(p.west());
            if(southPos.getBlock() == validBlock()) {
                s2 = s2.with(SOUTH, true);
            } else {
                s2 = s2.with(SOUTH, false);
            }
            if(northPos.getBlock() == validBlock()) {
                s2 = s2.with(NORTH, true);
            } else {
                s2 = s2.with(NORTH, false);
            }
            if(eastPos.getBlock() == validBlock()) {
                s2 = s2.with(EAST, true);
            } else {
                s2 = s2.with(EAST, false);
            }
            if(westPos.getBlock() == validBlock()) {
                s2 = s2.with(WEST, true);
            } else {
                s2 = s2.with(WEST, false);
            }
            return s2;
        }
        return s;
    }
}
