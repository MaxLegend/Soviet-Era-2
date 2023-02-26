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

public class ConcreteFenceHigh extends BlockSideCustomModel {
    final         VoxelShape BOXS[] = new VoxelShape[] {Block.makeCuboidShape(0D, 0D, 4D, 16D, 16D, 12D),
            Block.makeCuboidShape(0D, 0D, 4D, 16D, 16D, 12D),
            Block.makeCuboidShape(4D, 0D, 0D, 12D, 16D, 16D),
            Block.makeCuboidShape(4D, 0D, 0D, 12D, 16D, 16D)};

    public static final EnumProperty<EnumPart> PART = EnumProperty.create("part", EnumPart.class);
    public ConcreteFenceHigh(Properties properties) {
        super(properties, 1F);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)).with(PART, EnumPart.DOWN));

    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
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

        boolean isAirUp2 = w.isAirBlock(p.up(2));
        boolean isAirWestUp2 = w.isAirBlock(p.west().up(2));
        boolean isAirEastUp2 = w.isAirBlock(p.east().up(2));
        boolean isAirNorthUp2 = w.isAirBlock(p.north().up(2));
        boolean isAirSouthUp2 = w.isAirBlock(p.south().up(2));

        BlockPos west = p.west();
        BlockPos east = p.east();
        BlockPos south = p.south();
        BlockPos north = p.north();
        BlockPos up = p.up();
        BlockPos eastUp = p.east().up();
        BlockPos westUp = p.west().up();
        BlockPos southUp = p.south().up();
        BlockPos northUp = p.north().up();

        BlockPos up2 = p.up(2);
        BlockPos eastUp2 = p.east().up(2);
        BlockPos westUp2 = p.west().up(2);
        BlockPos southUp2 = p.south().up(2);
        BlockPos northUp2 = p.north().up(2);

        BlockState defaultstate = this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));

        boolean axisZ = isAirNorthUp && isAirNorth && isAirSouth && isAirUp && isAirSouthUp && isAirUp2 && isAirSouthUp2 && isAirNorthUp2;
        boolean axisX =  isAirEastUp && isAirEast && isAirWest && isAirUp && isAirWestUp && isAirWestUp2 && isAirUp2 && isAirEastUp2;
        switch (defaultstate.get(FACING)) {
            case NORTH:
                if(axisX) {
                    w.setBlockState(p, defaultstate);
                    w.setBlockState(east, defaultstate.with(PART, EnumPart.CORNER_DOWN_LEFT));
                    w.setBlockState(west, defaultstate.with(PART, EnumPart.CORNER_DOWN_RIGHT));
                    w.setBlockState(up, defaultstate.with(PART, EnumPart.CENTER));
                    w.setBlockState(up2, defaultstate.with(PART, EnumPart.UP));
                    w.setBlockState(westUp, defaultstate.with(PART, EnumPart.SIDE_RIGHT));
                    w.setBlockState(eastUp, defaultstate.with(PART, EnumPart.SIDE_LEFT));
                    w.setBlockState(westUp2, defaultstate.with(PART, EnumPart.CORNER_UP_RIGHT));
                    w.setBlockState(eastUp2, defaultstate.with(PART, EnumPart.CORNER_UP_LEFT));
                    return defaultstate;
                }
            case EAST:
                if(axisZ) {
                    w.setBlockState(p, defaultstate);
                    w.setBlockState(south, defaultstate.with(PART, EnumPart.CORNER_DOWN_LEFT));
                    w.setBlockState(north, defaultstate.with(PART, EnumPart.CORNER_DOWN_RIGHT));
                    w.setBlockState(up, defaultstate.with(PART, EnumPart.CENTER));
                    w.setBlockState(up2, defaultstate.with(PART, EnumPart.UP));
                    w.setBlockState(southUp, defaultstate.with(PART, EnumPart.SIDE_LEFT));
                    w.setBlockState(northUp, defaultstate.with(PART, EnumPart.SIDE_RIGHT));
                    w.setBlockState(southUp2, defaultstate.with(PART, EnumPart.CORNER_UP_LEFT));
                    w.setBlockState(northUp2, defaultstate.with(PART, EnumPart.CORNER_UP_RIGHT));
                    return defaultstate;
                }
            case WEST:
                if(axisZ) {
                    w.setBlockState(p, defaultstate);
                    w.setBlockState(south, defaultstate.with(PART, EnumPart.CORNER_DOWN_RIGHT));
                    w.setBlockState(north, defaultstate.with(PART, EnumPart.CORNER_DOWN_LEFT));
                    w.setBlockState(up, defaultstate.with(PART, EnumPart.CENTER));
                    w.setBlockState(up2, defaultstate.with(PART, EnumPart.UP));
                    w.setBlockState(southUp, defaultstate.with(PART, EnumPart.SIDE_RIGHT));
                    w.setBlockState(northUp, defaultstate.with(PART, EnumPart.SIDE_LEFT));
                    w.setBlockState(southUp2, defaultstate.with(PART, EnumPart.CORNER_UP_RIGHT));
                    w.setBlockState(northUp2, defaultstate.with(PART, EnumPart.CORNER_UP_LEFT));
                    return defaultstate;
                }
            case SOUTH:
                if(axisX) {
                    w.setBlockState(p, defaultstate);
                    w.setBlockState(east, defaultstate.with(PART, EnumPart.CORNER_DOWN_RIGHT));
                    w.setBlockState(west, defaultstate.with(PART, EnumPart.CORNER_DOWN_LEFT));
                    w.setBlockState(up, defaultstate.with(PART, EnumPart.CENTER));
                    w.setBlockState(up2, defaultstate.with(PART, EnumPart.UP));
                    w.setBlockState(westUp, defaultstate.with(PART, EnumPart.SIDE_LEFT));
                    w.setBlockState(eastUp, defaultstate.with(PART, EnumPart.SIDE_RIGHT));
                    w.setBlockState(westUp2, defaultstate.with(PART, EnumPart.CORNER_UP_LEFT));
                    w.setBlockState(eastUp2, defaultstate.with(PART, EnumPart.CORNER_UP_RIGHT));
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
        SIDE_LEFT("sl"),
        SIDE_RIGHT("sr"),
        CENTER("c"),
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
