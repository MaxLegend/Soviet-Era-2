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
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlockCornerCustomModel extends BlockSideCustomModel {
    public static final EnumProperty<EnumConnent> ENUM_CONNECT = EnumProperty.create("corner", EnumConnent.class);
    public BlockCornerCustomModel(Properties properties, float shadingInside) {
        super(properties, shadingInside);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)).with(ENUM_CONNECT, EnumConnent.NOT_CONNECT));
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING,ENUM_CONNECT,WATERLOGGED);
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        World w = context.getWorld();
        BlockPos p = context.getPos();
      //  w.getPendingBlockTicks().scheduleTick(p, this, 1);
        BlockState thisBS = this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite())
                .with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
        return thisBS.with(ENUM_CONNECT, EnumConnent.NOT_CONNECT);
    }
    @Override
    public BlockState updatePostPlacement(BlockState s, Direction f, BlockState bs, IWorld w, BlockPos p, BlockPos facingPos) {
        return updateState(w,p,s);
    }
    public Block validBlock() {
        return this;
    }
    public BlockState updateState(IWorld w, BlockPos p, BlockState s) {
        if (!w.isRemote()) {
            BlockState state = w.getBlockState(p);
            BlockState southPos = w.getBlockState(p.south());
            BlockState northPos = w.getBlockState(p.north());
            BlockState eastPos = w.getBlockState(p.east());
            BlockState westPos = w.getBlockState(p.west());
        //    w.getPendingBlockTicks().scheduleTick(p, this, 4);
            switch(s.get(FACING)) {
                case SOUTH:
                    if (southPos.getBlock() == validBlock()) {
                        if (southPos.get(FACING) == Direction.EAST)  return state.with(ENUM_CONNECT, EnumConnent.CORNER_LEFT);
                        if (southPos.get(FACING) == Direction.WEST) return state.with(ENUM_CONNECT, EnumConnent.CORNER_RIGHT);
                    } else {
                        return state.with(ENUM_CONNECT, EnumConnent.NOT_CONNECT).with(FACING, s.get(FACING));
                    }
                    break;
                case WEST:
                    if (westPos.getBlock() == validBlock()) {
                        if (westPos.get(ENUM_CONNECT) == EnumConnent.NOT_CONNECT) {
                            if (westPos.get(FACING) == Direction.NORTH) return state.with(ENUM_CONNECT, EnumConnent.CORNER_RIGHT);
                            if (westPos.get(FACING) == Direction.SOUTH) return state.with(ENUM_CONNECT, EnumConnent.CORNER_LEFT);
                        }
                    } else {
                        return state.with(ENUM_CONNECT, EnumConnent.NOT_CONNECT).with(FACING, s.get(FACING));
                    }
                    break;
                case EAST:
                    if (eastPos.getBlock() == validBlock()) {
                        if (eastPos.get(FACING) == Direction.NORTH)  return state.with(ENUM_CONNECT, EnumConnent.CORNER_LEFT);
                        if (eastPos.get(FACING) == Direction.SOUTH) return state.with(ENUM_CONNECT, EnumConnent.CORNER_RIGHT);
                    } else {
                        return state.with(ENUM_CONNECT, EnumConnent.NOT_CONNECT).with(FACING, s.get(FACING));
                    }
                    break;
                case NORTH:
                    if (northPos.getBlock() == validBlock()) {
                        if (northPos.get(FACING) == Direction.WEST)  return state.with(ENUM_CONNECT, EnumConnent.CORNER_LEFT);
                        if (northPos.get(FACING) == Direction.EAST) return state.with(ENUM_CONNECT, EnumConnent.CORNER_RIGHT);
                    } else {
                        return state.with(ENUM_CONNECT, EnumConnent.NOT_CONNECT).with(FACING, s.get(FACING));
                    }
                    break;
            }
        }
        return s;
    }
    public enum EnumConnent implements IStringSerializable {
        NOT_CONNECT("not"),
        CORNER_RIGHT("right"),
        CORNER_LEFT("left");

        @Override
        public String getString() {
            return this.name;
        }
        private final String name;
        EnumConnent(String name) {
            this.name = name;
        }
    }
}
