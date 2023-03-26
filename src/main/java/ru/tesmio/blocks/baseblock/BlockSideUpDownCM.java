package ru.tesmio.blocks.baseblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.WorldGenRegion;

public class BlockSideUpDownCM extends BlockSideCustomModel {
    public static final EnumProperty<EnumPart> PART = EnumProperty.create("part", EnumPart.class);
    public BlockSideUpDownCM(Properties properties, float shadingInside) {
        super(properties, shadingInside);
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
        builder.add(FACING, PART, WATERLOGGED);
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
            if(down.getBlock() instanceof BlockSideUpDownCM) {
                if(up.getBlock() instanceof BlockSideUpDownCM) {
                    return ts.with(PART, EnumPart.MIDDLE);
                }
                if(ts.get(PART) == EnumPart.UP) {
                    return ts.with(PART, EnumPart.UP);
                }
                return ts.with(PART, EnumPart.UP);

            }
        }
        return s;
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
