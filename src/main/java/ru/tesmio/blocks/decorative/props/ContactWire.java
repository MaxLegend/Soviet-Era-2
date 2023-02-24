package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
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
import net.minecraft.world.server.ServerWorld;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.utils.VoxelShapeUtil;

import java.util.ArrayList;
import java.util.Random;

//выяснить почему цепный сигнал не запитывает редстоун
public class ContactWire extends BlockSideCustomModel {
    public static final BooleanProperty SIGNAL = BooleanProperty.create("signal");
    public static final BooleanProperty FULCRUM = BooleanProperty.create("fulcrum");
    public static final BooleanProperty LEFT = BooleanProperty.create("left");
    public static final BooleanProperty RIGHT = BooleanProperty.create("right");

    public ContactWire(Properties properties, float shadingInside) {
        super(properties, shadingInside);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)).with(SIGNAL, false)
                .with(FULCRUM, false).with(LEFT, false).with(RIGHT, false));
    }

    public BlockState getStateForPlacement(BlockItemUseContext c) {
        FluidState fluidstate = c.getWorld().getFluidState(c.getPos());
        return this.getDefaultState().with(FACING, c.getPlacementHorizontalFacing().getOpposite())
                .with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }

    public boolean canProvidePower(BlockState state) {
        return true;
    }

    public int getWeakPower(BlockState s, IBlockReader br, BlockPos p, Direction side) {
        if(s.get(SIGNAL)){

            return 15;
        }

        return 0;
    }

    public void onEntityCollision(BlockState s, World w, BlockPos p, Entity e) {
        if(!w.isRemote()) {
            if (e instanceof LivingEntity) {
                w.setBlockState(p, s.with(SIGNAL, true));
                w.getPendingBlockTicks().scheduleTick(p, this, 4);
                this.poweredBlocks(s, w, p, this,RegBlocks.CONTACT_WIRE_INNER.get(),RegBlocks.CONTACT_WIRE_OUTER.get());
            }
        }
    }

    @Override
    public void onBlockAdded(BlockState s, World w, BlockPos p, BlockState oldState, boolean isMoving) {
        this.updateState(w,p,s);
    }
    VoxelShape SHP[] = new VoxelShape[] {
            Block.makeCuboidShape(2,11,7,12,12,9),
            Block.makeCuboidShape(2,11,7,12,12,16),
            Block.makeCuboidShape(2,11,0,12,12,9),
    };
    public VoxelShape getShape(BlockState s, IBlockReader w, BlockPos p, ISelectionContext context) {
        switch (s.get(FACING)) {
            case EAST:
                if(s.get(RIGHT) && s.get(LEFT)) {
                    return VoxelShapes.or(SHP[0],SHP[2],SHP[1]);
                }
                if(s.get(RIGHT)) {
                    return VoxelShapes.or(SHP[0],SHP[1]);
                }
                if(s.get(LEFT)) {
                    return VoxelShapes.or(SHP[0],SHP[2]);
                }
                return SHP[0];
            case WEST:
                if(s.get(RIGHT) && s.get(LEFT)) {
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRot180(SHP[0]),VoxelShapeUtil.shapeRot180(SHP[2]),VoxelShapeUtil.shapeRot180(SHP[1]));
                }
                if(s.get(RIGHT)) {
                    return VoxelShapes.or(VoxelShapeUtil.shapeRot180(SHP[0]),VoxelShapeUtil.shapeRot180(SHP[1]));
                }
                if(s.get(LEFT)) {
                    return VoxelShapes.or(VoxelShapeUtil.shapeRot180(SHP[0]),VoxelShapeUtil.shapeRot180(SHP[2]));
                }
                return VoxelShapeUtil.shapeRot180(SHP[0]);
            case SOUTH:
                if(s.get(RIGHT) && s.get(LEFT)) {
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCW90(SHP[0]),
                            VoxelShapeUtil.shapeRotCW90(SHP[2]),
                            VoxelShapeUtil.shapeRotCW90(SHP[1]));
                }
                if(s.get(LEFT)) {
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCW90(SHP[0]),
                            VoxelShapeUtil.shapeRotCW90(SHP[1]));
                }
                if(s.get(RIGHT)) {
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCW90(SHP[0]),
                            VoxelShapeUtil.shapeRotCW90(SHP[2]));
                }
                return VoxelShapeUtil.shapeRotCW90(SHP[0]);
            case NORTH:
                if(s.get(RIGHT) && s.get(LEFT)) {
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(SHP[0])),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(SHP[2])),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(SHP[1])));
                }
                if(s.get(LEFT)) {
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(SHP[0])),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(SHP[1])));
                }
                if(s.get(RIGHT)) {
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(SHP[0])),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(SHP[2])));
                }
                return VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(SHP[0]));
        }
        return SHP[0];
    }

    public void tick(BlockState s, ServerWorld w, BlockPos p, Random rand) {
        if(s.get(SIGNAL)) {
           w.getPendingBlockTicks().scheduleTick(p, this, 4);
            w.setBlockState(p, s.with(SIGNAL, false));
        }

    }
    public void neighborChanged(BlockState s, World w, BlockPos p, Block b, BlockPos fromPos, boolean isMoving) {
        if(s.get(SIGNAL)) {
            w.getPendingBlockTicks().scheduleTick(p, this, 4);
        }
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, LEFT, RIGHT, FULCRUM, SIGNAL, WATERLOGGED);
    }

    @Override
    public BlockState updatePostPlacement(BlockState s, Direction f, BlockState bs, IWorld w, BlockPos p, BlockPos facingPos) {
        if(s.get(SIGNAL)) {
            w.updateBlock(p.offset(f), w.getBlockState(p.offset(f)).getBlock());
        }
        return updateState((World) w,p,s);
    }
    public Block validBlock() {
        Block b1 = RegBlocks.CONTACT_WIRE_INNER.get();
        Block b2 = RegBlocks.CONTACT_WIRE_OUTER.get();
        Block b3 = RegBlocks.CONTACT_WIRE.get();

        return b3;
    }
//    public Supplier<Block[]> validBlocks() {
//        return () -> new Block[]{ RegBlocks.CONTACT_WIRE.get(), RegBlocks.CONTACT_WIRE_OUTER.get(), RegBlocks.CONTACT_WIRE_INNER.get()};
//    }
    public boolean validBlocks(World w, BlockPos p) {
        for(Direction d : Direction.Plane.HORIZONTAL) {
            if(w.getBlockState(p.offset(d)).isAir()) return false;
            if(w.getBlockState(p.offset(d)).getBlock() == RegBlocks.CONTACT_WIRE.get()) return true;
            if(w.getBlockState(p.offset(d)).getBlock() == RegBlocks.CONTACT_WIRE_INNER.get()) return true;
            if(w.getBlockState(p.offset(d)).getBlock() == RegBlocks.CONTACT_WIRE_OUTER.get()) return true;
        }
        return false;

    }
    public BlockState updateState(World w, BlockPos p, BlockState s) {
        if (!w.isRemote()) {
            BlockState ts = w.getBlockState(p);
            BlockState westS = w.getBlockState(p.west());
            BlockState eastS = w.getBlockState(p.east());
            BlockState northS = w.getBlockState(p.north());
            BlockState southS = w.getBlockState(p.south());

                switch (s.get(FACING)) {
                    case NORTH:
                        if(eastS.getBlock() instanceof ContactWireAngle) {
                             s = s.with(RIGHT, true);
                        } else
                        if (eastS.getBlock() instanceof ContactWire) {
                            if (eastS.get(FACING) == ts.get(FACING)) {
                                s = s.with(RIGHT, true);
                            }
                        } else {
                            s = s.with(RIGHT, false);
                        }
                        if(westS.getBlock() instanceof ContactWireAngle) {
                            s = s.with(RIGHT, true);
                        } else
                        if (westS.getBlock() instanceof ContactWire) {
                            if (westS.get(FACING) == ts.get(FACING)) {
                                s = s.with(LEFT, true);
                            }
                        }else {
                            s = s.with(LEFT, false);
                        }
                        if (southS.isSolid()) {
                            s = s.with(FULCRUM, true);
                        } else {
                            s = s.with(FULCRUM, false);
                        }

                        return s;
                    case SOUTH:
                        if(eastS.getBlock() instanceof ContactWireAngle) {
                            s = s.with(LEFT, true);
                        } else
                        if (eastS.getBlock() instanceof ContactWire) {
                            if (eastS.get(FACING) == ts.get(FACING)) {
                                s = s.with(LEFT, true);
                            } }
                        else {
                                s = s.with(LEFT, false);
                            }
                        if(westS.getBlock() instanceof ContactWireAngle) {
                            s = s.with(RIGHT, true);
                        } else
                        if (westS.getBlock() instanceof ContactWire) {
                            if (westS.get(FACING) == ts.get(FACING)) {
                                s = s.with(RIGHT, true);
                            } }else {
                                s = s.with(RIGHT, false);
                            }

                        if (northS.isSolid()) {
                            s = s.with(FULCRUM, true);
                        } else {
                            s = s.with(FULCRUM, false);
                        }
                        return s;
                    case EAST:
                        if(northS.getBlock() instanceof ContactWireAngle) {
                            s = s.with(LEFT, true);
                        } else
                        if (northS.getBlock() instanceof ContactWire) {
                            if (northS.get(FACING) == ts.get(FACING)) {
                                s = s.with(LEFT, true);
                            } } else {
                                s = s.with(LEFT, false);
                            }
                        if(southS.getBlock() instanceof ContactWireAngle) {
                            s = s.with(RIGHT, true);
                        } else
                        if (southS.getBlock() instanceof ContactWire) {
                            if (southS.get(FACING) == ts.get(FACING)) {
                                s = s.with(RIGHT, true);
                            } } else {
                                s = s.with(RIGHT, false);
                            }

                        if (westS.isSolid()) {
                            s = s.with(FULCRUM, true);
                        } else {
                            s = s.with(FULCRUM, false);
                        }
                        return s;
                    case WEST:
                        if(northS.getBlock() instanceof ContactWireAngle) {
                            s = s.with(RIGHT, true);
                        } else
                        if (northS.getBlock() instanceof ContactWire) {
                            if (northS.get(FACING) == ts.get(FACING)) {
                                s = s.with(RIGHT, true);
                            } }else {
                                s = s.with(RIGHT, false);
                            }
                        if(southS.getBlock() instanceof ContactWireAngle) {
                            s = s.with(LEFT, true);
                        } else
                        if (southS.getBlock() instanceof ContactWire) {
                            if (southS.get(FACING) == ts.get(FACING)) {
                                s = s.with(LEFT, true);
                            } } else {
                                s = s.with(LEFT, false);
                            }

                        if (eastS.isSolid()) {
                            s = s.with(FULCRUM, true);
                        } else {
                            s = s.with(FULCRUM, false);
                        }
                        return s;

                }

        }
        return s;
    }

    public void poweredBlocks (BlockState s, World w, BlockPos p, Block targetBlock, Block targetBlock2, Block targetBlock3) {
        if ((s.getBlock() == targetBlock || s.getBlock() == targetBlock2 || s.getBlock() == targetBlock3) && !s.get(SIGNAL)) {
            ArrayList<BlockPos> poss = new ArrayList();
            ArrayList<BlockPos> unposs = new ArrayList();
            poss.add(p);
            for(Direction dir : Direction.Plane.HORIZONTAL) {
                unposs.add(p.add(dir.getDirectionVec()));
            }
            while (unposs.size() > 0) {
                ArrayList<BlockPos> newSet = new ArrayList();
                for (BlockPos upp : unposs) {
                    if (w.getBlockState(upp).getBlock() == targetBlock || w.getBlockState(upp).getBlock() == targetBlock2 || w.getBlockState(upp).getBlock() == targetBlock3) {
                        poss.add(upp);
                        for(Direction dir : Direction.Plane.HORIZONTAL) {
                            BlockPos pp = upp.add(dir.getDirectionVec());
                            if (!poss.contains(pp)) {
                                w.setBlockState(p, s.with(SIGNAL, true), 6);
                                w.setBlockState(upp, w.getBlockState(upp).with(SIGNAL, true),6);
                                newSet.add(pp);
                            }
                        }
                    }
                    newSet.remove(upp);
                }
                unposs = newSet;
            }
        }
    }
}
