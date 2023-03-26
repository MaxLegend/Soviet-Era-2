package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.WorldGenRegion;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.utils.VoxelShapeUtil;

public class StreetLightpost extends BlockSideCustomModel {
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final EnumProperty<EnumPart> PART = EnumProperty.create("part", EnumPart.class);
    public StreetLightpost(Properties properties, float shadingInside) {
        super(properties, shadingInside);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)).with(PART, EnumPart.MIDDLE).with(LIT, false));
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        World w = context.getWorld();
        BlockPos p = context.getPos();
        BlockState thisBS = this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite())
                .with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
        if(w.getBlockState(p.down()).isSolid()) {
            return thisBS.with(PART, EnumPart.DOWN);
        }
        if (w.getBlockState(p.up()).getBlock() != thisBS.getBlock()) {
            return thisBS.with(PART, EnumPart.UP);
        }

        return thisBS.with(PART, EnumPart.MIDDLE);
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART, LIT, WATERLOGGED);
    }
    @Override
    public BlockState updatePostPlacement(BlockState s, Direction f, BlockState bs, IWorld w, BlockPos p, BlockPos facingPos) {
        if(w instanceof WorldGenRegion) return s;
        return updateState((World) w,p,s);
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public BlockState updateState(World w, BlockPos p, BlockState s) {
        if (!w.isRemote()) {
            BlockState ts = w.getBlockState(p);
            BlockState up = w.getBlockState(p.up());
            BlockState down = w.getBlockState(p.down());
            if(w.getBlockState(p.down()).isSolid()) {

                return ts.with(PART, EnumPart.DOWN);
            }
            if(down.getBlock() instanceof StreetLightpost) {
                if(up.getBlock() instanceof StreetLightpost) {
                    return ts.with(PART, EnumPart.MIDDLE);
                }
                if(ts.get(PART) == EnumPart.UP && w.isBlockPowered(p)) {
                    ts = ts.with(LIT, true);
                } else if(!w.isBlockPowered(p)) {
                    ts = ts.with(LIT, false);
                }
                return ts.with(PART, EnumPart.UP);

            }
        }
        return s;
    }
    @Override
    public int getLightValue(BlockState s, IBlockReader br, BlockPos p) {
        if(s.get(LIT)) {
            return 15;
        }
        return 0;
    }

    public VoxelShape getShape(BlockState s, IBlockReader w, BlockPos p, ISelectionContext c) {
        switch (s.get(FACING)) {
            case WEST:
                if(s.get(PART) == EnumPart.DOWN) {
                    if (s.getBlock() == RegBlocks.STREET_LIGHTPOST2.get()) {
                        return VoxelShapes.or(Block.makeCuboidShape(5D, 0D, 5D, 11D, 16D, 11D),
                                VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(5D, 0D, 5D, 11D, 13D, 14D)));
                    }
                }
            case EAST:
                if(s.get(PART) == EnumPart.DOWN) {
                    if (s.getBlock() == RegBlocks.STREET_LIGHTPOST2.get()) {
                        return VoxelShapes.or(Block.makeCuboidShape(5D, 0D, 5D, 11D, 16D, 11D),
                                VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(5D, 0D, 5D, 11D, 13D, 14D)));
                    }
                }
            case NORTH:
                if(s.get(PART) == EnumPart.DOWN) {
                    if (s.getBlock() == RegBlocks.STREET_LIGHTPOST2.get()) {
                        return VoxelShapes.or(Block.makeCuboidShape(5D, 0D, 5D, 11D, 16D, 11D),
                                VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(5D, 0D, 5D, 11D, 13D, 14D)));
                    }
                }
            case SOUTH:
                if(s.get(PART) == EnumPart.DOWN) {
                    if(s.getBlock() == RegBlocks.STREET_LIGHTPOST2.get()) {
                        return VoxelShapes.or(Block.makeCuboidShape(5D, 0D, 5D, 11D, 16D, 11D),
                                Block.makeCuboidShape(5D, 0D, 5D, 11D, 13D, 14D));
                    }
                    return VoxelShapes.or(Block.makeCuboidShape(4D, 0D, 4D, 12D, 16D, 12D),
                            Block.makeCuboidShape(3D, 0D, 3D, 13D, 10D, 13D));
                }
            return Block.makeCuboidShape(5D, 0D, 5D, 11D, 16D, 11D);
        }
        return VoxelShapes.fullCube();
    }

        public enum EnumPart implements IStringSerializable {
        DOWN("down"),
        MIDDLE("mid"),
        UP("up");

        @Override
        public String getString() {
            return this.name;
        }
        private final String name;
        EnumPart(String name) {
            this.name = name;
        }
    }
}
