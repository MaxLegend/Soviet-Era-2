package ru.tesmio.blocks.baseblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;

public class BlockAxisCustomModel extends BlockCustomModel {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    public BlockAxisCustomModel(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.X));
    }
    public BlockState getStateForPlacement(BlockItemUseContext c) {
        Direction facing = Direction.fromAngle(c.getPlayer().getRotationYawHead());
        Direction.Axis axis = facing.getAxis();
        FluidState fluidstate = c.getWorld().getFluidState(c.getPos());
        return this.getDefaultState().with(AXIS, axis).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }


    public enum Axis implements IStringSerializable {
        X("x"),
        Z("z");
        private final String name;

        Axis(String name) {
            this.name = name;
        }
        @Override
        public String getString() {
            return this.name;
        }
        public static Axis fromFacingAxis(Direction.Axis axis)
        {
            switch (axis)
            {
                case Z:
                    return Z;
                default:
                    return X;
            }
        }

    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AXIS, WATERLOGGED);
    }
}
