package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.utils.VoxelShapeUtil;

public class Turnstile extends BlockSideCustomModel {
    private static VoxelShape[] SHAPES = new VoxelShape[] {
            VoxelShapes.or(Block.makeCuboidShape(0D, 0D, 4D, 5D, 1D, 12D),
                    Block.makeCuboidShape(0D, 0D, 6D, 3D, 10D, 10D),
                    Block.makeCuboidShape(0D, 10D, 4D, 4D, 13D, 12D),
                    Block.makeCuboidShape(0D, 13D, 5D, 6D, 16D, 11D),
                    //opened part
                    Block.makeCuboidShape(4D, 1.8D, 7.65D, 5.8D, 12.8D, 8.37D)),
            VoxelShapes.or(Block.makeCuboidShape(0D, 0D, 4D, 5D, 1D, 12D),
                    Block.makeCuboidShape(0D, 0D, 6D, 3D, 10D, 10D),
                    Block.makeCuboidShape(0D, 10D, 4D, 4D, 13D, 12D),
                    Block.makeCuboidShape(0D, 13D, 5D, 6D, 16D, 11D),
                    //opened part
                    Block.makeCuboidShape(0D, 11D, 7.65D, 15.25D, 12.8D, 8.37D)),
            VoxelShapes.or(Block.makeCuboidShape(11D, 0D, 4D, 16D, 1D, 12D),
                    Block.makeCuboidShape(13D, 0D, 6D, 16D, 10D, 10D),
                    Block.makeCuboidShape(12D, 10D, 4D, 16D, 13D, 12D),
                    Block.makeCuboidShape(10D, 13D, 5D, 16D, 16D, 11D),
                    //opened part
                    Block.makeCuboidShape(10.2D, 1.8D, 7.65D, 12D, 12.8D, 8.37D)),
            VoxelShapes.or(Block.makeCuboidShape(11D, 0D, 4D, 16D, 1D, 12D),
                    Block.makeCuboidShape(13D, 0D, 6D, 16D, 10D, 10D),
                    Block.makeCuboidShape(12D, 10D, 4D, 16D, 13D, 12D),
                    Block.makeCuboidShape(10D, 13D, 5D, 16D, 16D, 11D),
                    //opened part
                    Block.makeCuboidShape(0.65D, 11D, 7.65D, 16D, 12.8D, 8.37D)),
            VoxelShapes.or(
                    VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(0D, 0D, 4D, 5D, 1D, 12D)),
                    VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(0D, 0D, 6D, 3D, 10D, 10D)),
                    VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(0D, 10D, 4D, 4D, 13D, 12D)),
                    VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(0D, 13D, 5D, 6D, 16D, 11D)),
                    //opened part
                    VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(4D, 1.8D, 7.65D, 5.8D, 12.8D, 8.37D))),
            VoxelShapes.or(
                    VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(0D, 0D, 4D, 5D, 1D, 12D)),
                    VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(0D, 0D, 6D, 3D, 10D, 10D)),
                    VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(0D, 10D, 4D, 4D, 13D, 12D)),
                    VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(0D, 13D, 5D, 6D, 16D, 11D)),
                    VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(0D, 11D, 7.65D, 15.25D, 12.8D, 8.37D))),
            VoxelShapes.or(Block.makeCuboidShape(4D, 0D, 0D, 12D, 1D, 5D),
                    Block.makeCuboidShape(6D, 0D, 0D, 10D, 10D, 3D),
                    Block.makeCuboidShape(4D, 10D, 0D, 12D, 13D, 4D),
                    Block.makeCuboidShape(5D, 13D, 0D, 11D, 16D, 6D),
                    //opened part
                    Block.makeCuboidShape(7.65D, 1.8D, 4D, 8.37D, 12.8D, 5.8D)),
            VoxelShapes.or(Block.makeCuboidShape(4D, 0D, 0D, 12D, 1D, 5D),
                    Block.makeCuboidShape(6D, 0D, 0D, 10D, 10D, 3D),
                    Block.makeCuboidShape(4D, 10D, 0D, 12D, 13D, 4D),
                    Block.makeCuboidShape(5D, 13D, 0D, 11D, 16D, 6D),
                    //opened part
                    Block.makeCuboidShape(7.65D, 11D, 0D, 8.37D, 12.8D, 15.25D))
    };

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                if(state.get(HINGE) == EnumHinge.LEFT) {
                    if (state.get(STATUS) == EnumStatus.OPEN) { return SHAPES[0]; }
                    return SHAPES[1];
                }
                if(state.get(HINGE) == EnumHinge.RIGHT) {
                    if (state.get(STATUS) == EnumStatus.OPEN) { return SHAPES[2]; }
                    return SHAPES[3];
                }
            case SOUTH:
                if(state.get(HINGE) == EnumHinge.LEFT) {
                if (state.get(STATUS) == EnumStatus.OPEN) { return SHAPES[2]; }
                    return SHAPES[3];
            }
                if(state.get(HINGE) == EnumHinge.RIGHT) {
                    if (state.get(STATUS) == EnumStatus.OPEN) { return SHAPES[0]; }
                    return SHAPES[1];
                }
            case WEST:
                if(state.get(HINGE) == EnumHinge.LEFT) {
                    if (state.get(STATUS) == EnumStatus.OPEN) { return SHAPES[4]; }
                    return SHAPES[5];
                }
                if(state.get(HINGE) == EnumHinge.RIGHT) {
                    if (state.get(STATUS) == EnumStatus.OPEN) { return SHAPES[6]; }
                   return SHAPES[7];
                }
            case EAST:
                if(state.get(HINGE) == EnumHinge.LEFT) {
                    if (state.get(STATUS) == EnumStatus.OPEN) { return SHAPES[6]; }
                    return SHAPES[7];
                }
                if(state.get(HINGE) == EnumHinge.RIGHT) {
                    if (state.get(STATUS) == EnumStatus.OPEN) { return SHAPES[4]; }
                    return SHAPES[5];
                }
        }

        return VoxelShapes.fullCube();
    }


    public BlockState getStateForPlacement(BlockItemUseContext c) {
        FluidState fluidstate = c.getWorld().getFluidState(c.getPos());
        return this.getDefaultState().with(FACING, c.getPlacementHorizontalFacing()).with(HINGE, this.getHinge(c))
                .with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }

    public static final EnumProperty<EnumStatus> STATUS = EnumProperty.create("status", EnumStatus.class);
    public static final EnumProperty<EnumHinge> HINGE = EnumProperty.create("hinge", EnumHinge.class);
    public Turnstile(Properties properties) {
        super(properties, 1F);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(STATUS,EnumStatus.OFF).with(WATERLOGGED, Boolean.valueOf(false)).with(HINGE, EnumHinge.LEFT));
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, STATUS, HINGE, WATERLOGGED);
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public EnumHinge getHinge(BlockItemUseContext c) {
        BlockPos p = c.getPos();
        Direction d = c.getPlacementHorizontalFacing();
        int j = d.getXOffset();
        int k = d.getZOffset();
        Vector3d vector3d = c.getHitVec();
        double d0 = vector3d.x - (double)p.getX();
        double d1 = vector3d.z - (double)p.getZ();
        return (j >= 0 || !(d1 < 0.5D)) && (j <= 0 || !(d1 > 0.5D)) && (k >= 0 || !(d0 > 0.5D)) && (k <= 0 || !(d0 < 0.5D)) ? EnumHinge.LEFT : EnumHinge.RIGHT;
    }
    public void neighborChanged(BlockState s, World w, BlockPos p, Block b, BlockPos fromPos, boolean isMoving) {
        if(w.isBlockPowered(p)) {
            if(s.get(STATUS) == EnumStatus.OFF) {
                w.setBlockState(p, s.with(STATUS, EnumStatus.CLOSE));
            }
        } else {
            w.setBlockState(p, s.with(STATUS, EnumStatus.OFF));
        }
    }
    @Override
    public ActionResultType onBlockActivated(BlockState s, World w, BlockPos p, PlayerEntity pl, Hand hand, BlockRayTraceResult hit) {
        if(s.get(STATUS) == EnumStatus.CLOSE) {
            w.setBlockState(p, s.with(STATUS, EnumStatus.OPEN));
            return ActionResultType.SUCCESS;
        } else if(s.get(STATUS) == EnumStatus.OPEN) {
            w.setBlockState(p, s.with(STATUS, EnumStatus.CLOSE));
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }

    public enum EnumStatus implements IStringSerializable {
        OFF("off"),
        CLOSE("close"),
        OPEN("open");
        private final String name;
        EnumStatus(String name) {this.name = name;}
        @Override
        public String getString() {
            return this.name;
        }
    }
    public enum EnumHinge implements IStringSerializable {
        LEFT,
        RIGHT;

        public String toString() {
            return this.getString();
        }

        public String getString() {
            return this == LEFT ? "left" : "right";
        }
    }
}
