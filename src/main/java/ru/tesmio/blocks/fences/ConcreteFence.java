package ru.tesmio.blocks.fences;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;

public class ConcreteFence extends BlockSideCustomModel {
    final VoxelShape BOXS[] = new VoxelShape[] {Block.makeCuboidShape(0D, 0D, 4D, 16D, 16D, 12D),
            Block.makeCuboidShape(0D, 0D, 4D, 16D, 16D, 12D),
            Block.makeCuboidShape(4D, 0D, 0D, 12D, 16D, 16D),
            Block.makeCuboidShape(4D, 0D, 0D, 12D, 16D, 16D)};

    public static final EnumProperty<EnumPart> PART = EnumProperty.create("part", EnumPart.class);
    public ConcreteFence(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)).with(PART, EnumPart.DOWN));
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (state.get(FACING)) {
            case NORTH:
                return BOXS[0];
            case SOUTH:
                return BOXS[1];
            case WEST:
                return BOXS[2];
            case EAST:
                return BOXS[3];
        }
        return VoxelShapes.fullCube();
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        World w = context.getWorld(); BlockState s = context.getWorld().getBlockState(context.getPos()); BlockPos p = context.getPos();
        boolean isAirWest = w.isAirBlock(p.west());
        boolean isAirEast = w.isAirBlock(p.east());
        boolean isAirNorth = w.isAirBlock(p.north());
        boolean isAirSouth = w.isAirBlock(p.south());
        boolean isAirUp = w.isAirBlock(p.up());
        boolean isAirWestUp = w.isAirBlock(p.west().up());
        boolean isAirEastUp = w.isAirBlock(p.east().up());
        boolean isAirNorthUp = w.isAirBlock(p.north().up());
        boolean isAirSouthUp = w.isAirBlock(p.south().up());
        BlockPos west = p.west();
        BlockPos east = p.east();
        BlockPos south = p.south();
        BlockPos north = p.north();
        BlockPos up = p.up();
        BlockPos eastUp = p.east().up();
        BlockPos westUp = p.west().up();
        BlockPos southUp = p.south().up();
        BlockPos northUp = p.north().up();

        BlockState defaultstate = this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));

        switch (defaultstate.get(FACING)) {
            case NORTH:
                if(isAirEastUp && isAirEast && isAirWest && isAirUp && isAirWestUp) {
                    w.setBlockState(p, defaultstate);
                    w.setBlockState(east, defaultstate.with(PART, EnumPart.CORNER_DOWN_LEFT));
                    w.setBlockState(west, defaultstate.with(PART, EnumPart.CORNER_DOWN_RIGHT));
                    w.setBlockState(up, defaultstate.with(PART, EnumPart.UP));
                    w.setBlockState(westUp, defaultstate.with(PART, EnumPart.CORNER_UP_RIGHT));
                    w.setBlockState(eastUp, defaultstate.with(PART, EnumPart.CORNER_UP_LEFT));
                    return defaultstate;
                }
            case EAST:
                if(isAirNorthUp && isAirNorth && isAirSouth && isAirUp && isAirSouthUp) {
                    w.setBlockState(p, defaultstate);
                    w.setBlockState(south, defaultstate.with(PART, EnumPart.CORNER_DOWN_LEFT));
                    w.setBlockState(north, defaultstate.with(PART, EnumPart.CORNER_DOWN_RIGHT));
                    w.setBlockState(up, defaultstate.with(PART, EnumPart.UP));
                    w.setBlockState(southUp, defaultstate.with(PART, EnumPart.CORNER_UP_LEFT));
                    w.setBlockState(northUp, defaultstate.with(PART, EnumPart.CORNER_UP_RIGHT));
                    return defaultstate;
                }
            case WEST:
                if(isAirNorthUp && isAirNorth && isAirSouth && isAirUp && isAirSouthUp) {
                    w.setBlockState(p, defaultstate);
                    w.setBlockState(south, defaultstate.with(PART, EnumPart.CORNER_DOWN_RIGHT));
                    w.setBlockState(north, defaultstate.with(PART, EnumPart.CORNER_DOWN_LEFT));
                    w.setBlockState(up, defaultstate.with(PART, EnumPart.UP));
                    w.setBlockState(southUp, defaultstate.with(PART, EnumPart.CORNER_UP_RIGHT));
                    w.setBlockState(northUp, defaultstate.with(PART, EnumPart.CORNER_UP_LEFT));
                    return defaultstate;
                }
            case SOUTH:
                if(isAirEastUp && isAirEast && isAirWest && isAirUp && isAirWestUp) {
                    w.setBlockState(p, defaultstate);
                    w.setBlockState(east, defaultstate.with(PART, EnumPart.CORNER_DOWN_RIGHT));
                    w.setBlockState(west, defaultstate.with(PART, EnumPart.CORNER_DOWN_LEFT));
                    w.setBlockState(up, defaultstate.with(PART, EnumPart.UP));
                    w.setBlockState(westUp, defaultstate.with(PART, EnumPart.CORNER_UP_LEFT));
                    w.setBlockState(eastUp, defaultstate.with(PART, EnumPart.CORNER_UP_RIGHT));
                    return defaultstate;
                }


        }

        defaultstate = Blocks.AIR.getDefaultState();
        return defaultstate;
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART, WATERLOGGED);
    }
    public enum EnumPart implements IStringSerializable {
        UP("u"),
        DOWN("d"),
        CORNER_UP_LEFT("cul"),
        CORNER_UP_RIGHT("cur"),
        CORNER_DOWN_RIGHT("cdr"),
        CORNER_DOWN_LEFT("cdl");
        private final String name;

        EnumPart(String name) {
            this.name = name;
        }

        @Override
        public String getString() {
            return this.name;
        }
    }
}
