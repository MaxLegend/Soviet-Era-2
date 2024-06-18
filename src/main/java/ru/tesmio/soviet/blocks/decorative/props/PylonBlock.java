package ru.tesmio.soviet.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
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
import net.minecraft.world.gen.WorldGenRegion;
import ru.tesmio.soviet.blocks.baseblock.BlockAxisCustomModel;
import ru.tesmio.soviet.reg.RegItems;
import ru.tesmio.soviet.utils.VoxelShapeUtil;

public class PylonBlock extends BlockAxisCustomModel {

    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty WEST = BooleanProperty.create("west");

    public PylonBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.X).with(DOWN, Boolean.FALSE).with(UP, Boolean.FALSE).with(WEST, Boolean.FALSE).with(EAST, Boolean.FALSE).with(NORTH, Boolean.FALSE).with(SOUTH, Boolean.FALSE));
    }

    final VoxelShape[] SHAPEX = new VoxelShape[]{
            VoxelShapes.or(VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0.6875, 0, 0.3125, 0.8125, 1, 0.6875)), VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0.1875, 0, 0.3125, 0.3125, 1, 0.6875)), VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0.3125, 0, 0.375, 0.6875, 1, 0.625))),
            VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0.125, 0, 0.1875, 0.875, 0.0625, 0.8125)),
            VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0.125, 0.9375, 0.1875, 0.875, 1, 0.8125)),
            VoxelShapes.or(
                    VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0.3125, 0.1875, 0, 0.6875, 0.3125, 0.375)),
                    VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0.375, 0.3125, 0, 0.625, 0.6875, 0.3125)),
                    VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0.25, 0.3125, 0.25, 0.375, 0.6875, 0.3125)),
                    VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0.625, 0.3125, 0.25, 0.75, 0.6875, 0.3125)),
                    VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0.3125, 0.6875, 0, 0.6875, 0.8125, 0.375))
            ),
            VoxelShapes.or(
                    VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0.125, 0.0625, 0.53125, 0.1875, 0.125, 0.65625)),
                    VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0.125, 0.0625, 0.34375, 0.1875, 0.125, 0.46875)),
                    VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0.125, 0.875, 0.53125, 0.1875, 0.9375, 0.65625)),
                    VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0.125, 0.875, 0.34375, 0.1875, 0.9375, 0.46875)),
                    VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0, 0.125, 0.53125, 0.1875, 0.1875, 0.65625)),
                    VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0, 0.125, 0.34375, 0.1875, 0.1875, 0.46875)),
                    VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0, 0.8125, 0.34375, 0.1875, 0.875, 0.46875)),
                    VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0, 0.8125, 0.53125, 0.1875, 0.875, 0.65625)),
                    VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0, 0.1875, 0.3125, 0.1875, 0.3125, 0.6875)),
                    VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0, 0.6875, 0.3125, 0.1875, 0.8125, 0.6875)),
                    VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0, 0.3125, 0.375, 0.1875, 0.6875, 0.625))
            ),
            VoxelShapes.or(
                    VoxelShapeUtil.shapeRotCW90(VoxelShapes.create(0.3125, 0.1875, 0, 0.6875, 0.3125, 0.375)),
                    VoxelShapeUtil.shapeRotCW90(VoxelShapes.create(0.375, 0.3125, 0, 0.625, 0.6875, 0.3125)),
                    VoxelShapeUtil.shapeRotCW90(VoxelShapes.create(0.25, 0.3125, 0.25, 0.375, 0.6875, 0.3125)),
                    VoxelShapeUtil.shapeRotCW90(VoxelShapes.create(0.625, 0.3125, 0.25, 0.75, 0.6875, 0.3125)),
                    VoxelShapeUtil.shapeRotCW90(VoxelShapes.create(0.3125, 0.6875, 0, 0.6875, 0.8125, 0.375))
            ),
            VoxelShapes.or(
                    VoxelShapeUtil.shapeRotCW90(VoxelShapeUtil.shapeRot180(VoxelShapes.create(0.125, 0.0625, 0.53125, 0.1875, 0.125, 0.65625))),
                    VoxelShapeUtil.shapeRotCW90(VoxelShapeUtil.shapeRot180(VoxelShapes.create(0.125, 0.0625, 0.34375, 0.1875, 0.125, 0.46875))),
                    VoxelShapeUtil.shapeRotCW90(VoxelShapeUtil.shapeRot180(VoxelShapes.create(0.125, 0.875, 0.53125, 0.1875, 0.9375, 0.65625))),
                    VoxelShapeUtil.shapeRotCW90(VoxelShapeUtil.shapeRot180(VoxelShapes.create(0.125, 0.875, 0.34375, 0.1875, 0.9375, 0.46875))),
                    VoxelShapeUtil.shapeRotCW90(VoxelShapeUtil.shapeRot180(VoxelShapes.create(0, 0.125, 0.53125, 0.1875, 0.1875, 0.65625))),
                    VoxelShapeUtil.shapeRotCW90(VoxelShapeUtil.shapeRot180(VoxelShapes.create(0, 0.125, 0.34375, 0.1875, 0.1875, 0.46875))),
                    VoxelShapeUtil.shapeRotCW90(VoxelShapeUtil.shapeRot180(VoxelShapes.create(0, 0.8125, 0.34375, 0.1875, 0.875, 0.46875))),
                    VoxelShapeUtil.shapeRotCW90(VoxelShapeUtil.shapeRot180(VoxelShapes.create(0, 0.8125, 0.53125, 0.1875, 0.875, 0.65625))),
                    VoxelShapeUtil.shapeRotCW90(VoxelShapeUtil.shapeRot180(VoxelShapes.create(0, 0.1875, 0.3125, 0.1875, 0.3125, 0.6875))),
                    VoxelShapeUtil.shapeRotCW90(VoxelShapeUtil.shapeRot180(VoxelShapes.create(0, 0.6875, 0.3125, 0.1875, 0.8125, 0.6875))),
                    VoxelShapeUtil.shapeRotCW90(VoxelShapeUtil.shapeRot180(VoxelShapes.create(0, 0.3125, 0.375, 0.1875, 0.6875, 0.625)))
            ),
            VoxelShapes.or(VoxelShapes.create(0.6875, 0, 0.3125, 0.8125, 1, 0.6875),
                    VoxelShapes.create(0.1875, 0, 0.3125, 0.3125, 1, 0.6875),
                    VoxelShapes.create(0.3125, 0, 0.375, 0.6875, 1, 0.625)),
            VoxelShapes.create(0.125, 0, 0.1875, 0.875, 0.0625, 0.8125),

            VoxelShapes.or(VoxelShapes.create(0.6875, 0, 0.3125, 0.8125, 1, 0.6875),
                    VoxelShapes.create(0.1875, 0, 0.3125, 0.3125, 1, 0.6875),
                    VoxelShapes.create(0.3125, 0, 0.375, 0.6875, 1, 0.625),
                    VoxelShapes.create(0.125, 0.9375, 0.1875, 0.875, 1, 0.8125)),

            VoxelShapes.or(
                    VoxelShapes.create(0.3125, 0.1875, 0, 0.6875, 0.3125, 0.375),
                    VoxelShapes.create(0.375, 0.3125, 0, 0.625, 0.6875, 0.3125),
                    VoxelShapes.create(0.25, 0.3125, 0.25, 0.375, 0.6875, 0.3125),
                    VoxelShapes.create(0.625, 0.3125, 0.25, 0.75, 0.6875, 0.3125),
                    VoxelShapes.create(0.3125, 0.6875, 0, 0.6875, 0.8125, 0.375)
            ),
            VoxelShapes.or(
                    VoxelShapeUtil.shapeRot180(VoxelShapes.create(0.3125, 0.1875, 0, 0.6875, 0.3125, 0.375)),
                    VoxelShapeUtil.shapeRot180(VoxelShapes.create(0.375, 0.3125, 0, 0.625, 0.6875, 0.3125)),
                    VoxelShapeUtil.shapeRot180(VoxelShapes.create(0.25, 0.3125, 0.25, 0.375, 0.6875, 0.3125)),
                    VoxelShapeUtil.shapeRot180(VoxelShapes.create(0.625, 0.3125, 0.25, 0.75, 0.6875, 0.3125)),
                    VoxelShapeUtil.shapeRot180(VoxelShapes.create(0.3125, 0.6875, 0, 0.6875, 0.8125, 0.375))
            ),
            VoxelShapes.or(
                    VoxelShapes.create(0.125, 0.0625, 0.53125, 0.1875, 0.125, 0.65625),
                    VoxelShapes.create(0.125, 0.0625, 0.34375, 0.1875, 0.125, 0.46875),
                    VoxelShapes.create(0.125, 0.875, 0.53125, 0.1875, 0.9375, 0.65625),
                    VoxelShapes.create(0.125, 0.875, 0.34375, 0.1875, 0.9375, 0.46875),
                    VoxelShapes.create(0, 0.125, 0.53125, 0.1875, 0.1875, 0.65625),
                    VoxelShapes.create(0, 0.125, 0.34375, 0.1875, 0.1875, 0.46875),
                    VoxelShapes.create(0, 0.8125, 0.34375, 0.1875, 0.875, 0.46875),
                    VoxelShapes.create(0, 0.8125, 0.53125, 0.1875, 0.875, 0.65625),
                    VoxelShapes.create(0, 0.1875, 0.3125, 0.1875, 0.3125, 0.6875),
                    VoxelShapes.create(0, 0.6875, 0.3125, 0.1875, 0.8125, 0.6875),
                    VoxelShapes.create(0, 0.3125, 0.375, 0.1875, 0.6875, 0.625)
            ),
            VoxelShapes.or(
                    VoxelShapeUtil.shapeRot180(VoxelShapes.create(0.125, 0.0625, 0.53125, 0.1875, 0.125, 0.65625)),
                    VoxelShapeUtil.shapeRot180(VoxelShapes.create(0.125, 0.0625, 0.34375, 0.1875, 0.125, 0.46875)),
                    VoxelShapeUtil.shapeRot180(VoxelShapes.create(0.125, 0.875, 0.53125, 0.1875, 0.9375, 0.65625)),
                    VoxelShapeUtil.shapeRot180(VoxelShapes.create(0.125, 0.875, 0.34375, 0.1875, 0.9375, 0.46875)),
                    VoxelShapeUtil.shapeRot180(VoxelShapes.create(0, 0.125, 0.53125, 0.1875, 0.1875, 0.65625)),
                    VoxelShapeUtil.shapeRot180(VoxelShapes.create(0, 0.125, 0.34375, 0.1875, 0.1875, 0.46875)),
                    VoxelShapeUtil.shapeRot180(VoxelShapes.create(0, 0.8125, 0.34375, 0.1875, 0.875, 0.46875)),
                    VoxelShapeUtil.shapeRot180(VoxelShapes.create(0, 0.8125, 0.53125, 0.1875, 0.875, 0.65625)),
                    VoxelShapeUtil.shapeRot180(VoxelShapes.create(0, 0.1875, 0.3125, 0.1875, 0.3125, 0.6875)),
                    VoxelShapeUtil.shapeRot180(VoxelShapes.create(0, 0.6875, 0.3125, 0.1875, 0.8125, 0.6875)),
                    VoxelShapeUtil.shapeRot180(VoxelShapes.create(0, 0.3125, 0.375, 0.1875, 0.6875, 0.625))
            )
    };


    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (state.get(AXIS)) {
            case X:
                VoxelShape shape = SHAPEX[0];
                if (state.get(DOWN)) {
                    shape = VoxelShapes.or(shape, SHAPEX[1]);
                }
                if (state.get(UP)) {
                    shape = VoxelShapes.or(shape, SHAPEX[2]);
                }
                if (state.get(EAST)) {
                    shape = VoxelShapes.or(shape, SHAPEX[3]);
                }
                if (state.get(NORTH)) {
                    shape = VoxelShapes.or(shape, SHAPEX[4]);
                }
                if (state.get(WEST)) {
                    shape = VoxelShapes.or(shape, SHAPEX[5]);
                }
                if (state.get(SOUTH)) {
                    shape = VoxelShapes.or(shape, SHAPEX[6]);
                }
                return shape;
            case Z:
                VoxelShape shape2 = SHAPEX[7];
                if (state.get(DOWN)) {
                    shape2 = VoxelShapes.or(shape2, SHAPEX[8]);
                }
                if (state.get(UP)) {
                    shape2 = VoxelShapes.or(shape2, SHAPEX[9]);
                }
                if (state.get(NORTH)) {
                    shape2 = VoxelShapes.or(shape2, SHAPEX[10]);
                }
                if (state.get(SOUTH)) {
                    shape2 = VoxelShapes.or(shape2, SHAPEX[11]);
                }
                if (state.get(WEST)) {
                    shape2 = VoxelShapes.or(shape2, SHAPEX[12]);
                }
                if (state.get(EAST)) {
                    shape2 = VoxelShapes.or(shape2, SHAPEX[13]);
                }
                return shape2;

        }


        return VoxelShapes.fullCube();
    }

    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[]{
                new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(1, 3)),
        };
    }

    public BlockState getStateForPlacement(BlockItemUseContext c) {
        Direction facing = Direction.fromAngle(c.getPlayer().getRotationYawHead());
        Direction.Axis axis = facing.getAxis();
        World w = c.getWorld();
        BlockPos p = c.getPos();
        FluidState fluidstate = c.getWorld().getFluidState(c.getPos());
        BlockState s = this.getDefaultState().with(AXIS, axis).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));

        if (w.getBlockState(p.down()).isSolid() || w.getBlockState(p.down()).getBlock() instanceof PylonBlockH) {
            s = s.with(DOWN, Boolean.TRUE);
        }
        if (w.getBlockState(p.up()).isSolid() || w.getBlockState(p.down()).getBlock() instanceof PylonBlockH) {
            s = s.with(UP, Boolean.TRUE);
        }
        return s;
    }

    @Override
    public BlockState updatePostPlacement(BlockState s, Direction f, BlockState bs, IWorld w, BlockPos p, BlockPos facingPos) {
        if (w instanceof WorldGenRegion) return s;
        return updateState((World) w, p, s);
    }

    public void neighborChanged(BlockState s, World w, BlockPos p, Block b, BlockPos fromPos, boolean isMoving) {
        updateState(w, p, s);
    }

    @Override
    public void onBlockAdded(BlockState s, World w, BlockPos p, BlockState oldState, boolean isMoving) {

        this.updateState(w, p, s);
    }

    public BlockState updateState(World w, BlockPos p, BlockState ts) {
        BlockState up = w.getBlockState(p.up());
        BlockState down = w.getBlockState(p.down());
        BlockState south = w.getBlockState(p.south());
        BlockState north = w.getBlockState(p.north());
        BlockState west = w.getBlockState(p.west());
        BlockState east = w.getBlockState(p.east());
        if (!w.isRemote()) {
            if (down.isSolid() || down.getBlock() instanceof PylonBlockH) {
                ts = ts.with(DOWN, Boolean.TRUE);
            } else {
                ts = ts.with(DOWN, Boolean.FALSE);
            }
            if (up.isSolid() || up.getBlock() instanceof PylonBlockH) {
                ts = ts.with(UP, Boolean.TRUE);
            } else {
                ts = ts.with(UP, Boolean.FALSE);
            }
            if (east.getBlock() instanceof PylonBlockH) {
                ts = ts.with(EAST, Boolean.TRUE);
            } else {
                ts = ts.with(EAST, Boolean.FALSE);
            }
            if (west.getBlock() instanceof PylonBlockH) {
                ts = ts.with(WEST, Boolean.TRUE);
            } else {
                ts = ts.with(WEST, Boolean.FALSE);
            }
            if (north.getBlock() instanceof PylonBlockH) {
                ts = ts.with(NORTH, Boolean.TRUE);
            } else {
                ts = ts.with(NORTH, Boolean.FALSE);
            }
            if (south.getBlock() instanceof PylonBlockH) {
                ts = ts.with(SOUTH, Boolean.TRUE);
            } else {
                ts = ts.with(SOUTH, Boolean.FALSE);
            }

        }
        return ts;

    }


//            if(down.getBlock() instanceof BioStillage) {
//                if(up.getBlock() instanceof BioStillage) {
//                    return ts.with(PART, BioStillage.Part.MID);
//                }
//                return ts.with(PART, BioStillage.Part.UP);
//            }
//            if(up.getBlock() instanceof BioStillage) {
//                return ts.with(PART, BioStillage.Part.DOWN);
//            } else {
//                return ts.with(PART, BioStillage.Part.DEF);
//            }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(UP, DOWN, EAST, WEST, NORTH, SOUTH, AXIS, WATERLOGGED);
    }
}
