package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
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
import ru.tesmio.reg.RegItems;
import ru.tesmio.utils.VoxelShapeUtil;

import java.util.concurrent.ThreadLocalRandom;

public class HeatPipes extends BlockCustomModel {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    public static final BooleanProperty FRONT = BooleanProperty.create("front");
    public static final BooleanProperty BACK = BooleanProperty.create("back");
    public static final BooleanProperty FRONT_RIGHT = BooleanProperty.create("front_r");
    public static final BooleanProperty BACK_RIGHT = BooleanProperty.create("back_r");
    public static final BooleanProperty FRONT_LEFT = BooleanProperty.create("front_l");
    public static final BooleanProperty BACK_LEFT = BooleanProperty.create("back_l");

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public HeatPipes(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.X).with(WATERLOGGED, Boolean.valueOf(false))
                .with(FRONT, Boolean.FALSE).with(BACK, Boolean.FALSE));
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        return new ItemStack[] {
                new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(6)),
                new ItemStack(RegItems.ARMATURES.get(), tr.nextInt(2)),
        };
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape vs;
        switch (state.get(AXIS)) {
            case X:
                if(state.get(FRONT) && state.get(BACK)){
                    return VoxelShapes.or(
                            Block.makeCuboidShape(7D, 5D, 1D, 9D, 12D, 15D),
                            Block.makeCuboidShape(0D, 6D, 2D, 16D, 11D, 14D)
                    );
                }
                if(state.get(FRONT)){
                    return VoxelShapes.or(
                            Block.makeCuboidShape(7D, 5D, 1D, 9D, 12D, 15D),
                            Block.makeCuboidShape(0D, 6D, 2D, 9D, 11D, 14D)

                    );
                }
                if(state.get(BACK)){
                    return VoxelShapes.or(
                            Block.makeCuboidShape(7D, 5D, 1D, 9D, 12D, 15D),
                            Block.makeCuboidShape(7D, 6D, 2D, 16D, 11D, 14D)
                    );
                }
                return Block.makeCuboidShape(7D, 5D, 1D, 9D, 12D, 15D);
            case Z:
                if(state.get(FRONT) && state.get(BACK)){
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(7D, 5D, 1D, 9D, 12D, 15D)),
                            VoxelShapeUtil.shapeRotCW90( Block.makeCuboidShape(0D, 6D, 2D, 16D, 11D, 14D))
                    );
                }
                if(state.get(FRONT)){
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRot180( VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(7D, 5D, 1D, 9D, 12D, 15D))),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(0D, 6D, 2D, 9D, 11D, 14D)))

                    );
                }
                if(state.get(BACK)){
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRot180( VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(7D, 5D, 1D, 9D, 12D, 15D))),
                                    VoxelShapeUtil.shapeRot180( VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(7D, 6D, 2D, 16D, 11D, 14D)))
                    );
                }
                return VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(7D, 5D, 1D, 9D, 12D, 15D));
        }
        return VoxelShapes.fullCube();
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return getShape(state, worldIn, pos, context);
    }
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if(!worldIn.isRemote() && worldIn.getBlockState(pos).isAir()) {
        //    state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(this, 1));
        }
    }

    public BlockState getStateForPlacement(BlockItemUseContext c) {
        FluidState fluidstate = c.getWorld().getFluidState(c.getPos());
        World w = c.getWorld();
        PlayerEntity pl = c.getPlayer();
        BlockPos p = c.getPos();
        BlockState s = this.getDefaultState();
        if(s != null && s.getBlock() instanceof HeatPipes) {
                s = s.with(AXIS, c.getPlacementHorizontalFacing().getAxis());

            if(s.get(AXIS) == Direction.Axis.X) {
                if (w.getBlockState(p.west()).getBlock() == validBlock()) {
                    s = s.with(FRONT, true);
                }
                if (w.getBlockState(p.east()).getBlock() == validBlock()) {
                    s = s.with(BACK, true);
                }
            }
            return s;
                }
        return s;
    }

    @Override
    public BlockState updatePostPlacement(BlockState s, Direction f, BlockState bs, IWorld w, BlockPos p, BlockPos facingPos) {
        return updateState(w,p,s);
    }
    public Block validBlock() {

        return this;
    }
    public BlockState validState(BlockState s) {
        return s;
    }
    public BlockState updateState(IWorld w, BlockPos p, BlockState s) {
        if (!w.isRemote()) {
            if(s.get(AXIS) == Direction.Axis.X) {
                if (w.getBlockState(p.west()).getBlock() == validBlock()) {
                    if(w.getBlockState(p.west()).get(AXIS) == Direction.Axis.X) s = s.with(FRONT, true);
                } else if(w.getBlockState(p.west()).getBlock() == RegBlocks.HEAT_PIPES_CORNER.get()) {
                    s = s.with(FRONT, true);
                }else {  s = s.with(FRONT, false); }

                if (w.getBlockState(p.east()).getBlock() == validBlock()) {
                    if(w.getBlockState(p.east()).get(AXIS) == Direction.Axis.X) s = s.with(BACK, true);
                } else if(w.getBlockState(p.east()).getBlock() == RegBlocks.HEAT_PIPES_CORNER.get()) {
                    s = s.with(BACK, true);
                }
                else {  s = s.with(BACK, false); }
            }
            if(s.get(AXIS) == Direction.Axis.Z) {
                if (w.getBlockState(p.south()).getBlock() == validBlock()) {
                    if(w.getBlockState(p.south()).get(AXIS) == Direction.Axis.Z) s = s.with(FRONT, true);
                }else if(w.getBlockState(p.south()).getBlock() == RegBlocks.HEAT_PIPES_CORNER.get()) {
                    s = s.with(FRONT, true);
                } else {  s = s.with(FRONT, false); }

                if (w.getBlockState(p.north()).getBlock() == validBlock() ) {
                    if(w.getBlockState(p.north()).get(AXIS) == Direction.Axis.Z) s = s.with(BACK, true);
                }else if(w.getBlockState(p.north()).getBlock() == RegBlocks.HEAT_PIPES_CORNER.get()) {
                    s = s.with(BACK, true);
                } else {  s = s.with(BACK, false); }

            }
        }
        return s;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AXIS,FRONT,BACK, WATERLOGGED);
    }


}
