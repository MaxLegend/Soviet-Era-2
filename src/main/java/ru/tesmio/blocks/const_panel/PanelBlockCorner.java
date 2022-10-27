package ru.tesmio.blocks.const_panel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;

import javax.annotation.Nullable;

public class PanelBlockCorner extends Block {

    public static final EnumProperty<EnumHalf> HALF = EnumProperty.create("half", EnumHalf.class);

    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST, Direction.UP, Direction.DOWN );
    public PanelBlockCorner(Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(HALF, EnumHalf.LOWER));
    }
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        for(Direction direction : context.getNearestLookingDirections()) {
            if(direction == Direction.UP) {
                return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(HALF, EnumHalf.LOWER);
            } else if(direction == Direction.DOWN) {
                return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(HALF, EnumHalf.UPPER);
            } else {
                switch (direction) {
                    case NORTH:
                        if(context.getHitVec().y - (double)context.getPos().getY() > 0.5D) return this.getDefaultState().with(FACING, Direction.NORTH).with(HALF, EnumHalf.UPPER);
                        return this.getDefaultState().with(FACING, Direction.NORTH).with(HALF, EnumHalf.LOWER);
                    case SOUTH:
                        if(context.getHitVec().y - (double)context.getPos().getY() > 0.5D) return this.getDefaultState().with(FACING, Direction.SOUTH).with(HALF, EnumHalf.UPPER);
                        return this.getDefaultState().with(FACING, Direction.SOUTH).with(HALF, EnumHalf.LOWER);
                    case EAST:
                        if(context.getHitVec().y - (double)context.getPos().getY() > 0.5D) return this.getDefaultState().with(FACING, Direction.EAST).with(HALF, EnumHalf.UPPER);
                        return this.getDefaultState().with(FACING, Direction.EAST).with(HALF, EnumHalf.LOWER);
                    case WEST:
                        if(context.getHitVec().y - (double)context.getPos().getY() > 0.5D) return this.getDefaultState().with(FACING, Direction.WEST).with(HALF, EnumHalf.UPPER);
                        return this.getDefaultState().with(FACING, Direction.WEST).with(HALF, EnumHalf.LOWER);
                }
            }
        }
        return null;
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF);
    }
    enum EnumHalf implements IStringSerializable {
        UPPER("upper"),
        LOWER("lower");

        public final String name;

        EnumHalf(String name) {
            this.name = name;
        }

        @Override
        public String getString() {
            return this.name;
        }
    }
}