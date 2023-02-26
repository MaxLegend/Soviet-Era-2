package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.math.shapes.VoxelShape;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.reg.RegBlocks;

public class ChemDevices extends BlockSideCustomModel {
    public ChemDevices(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }
    @Override
    public VoxelShape getFacingShape(BlockState s) {
        VoxelShape SHP[] = new VoxelShape[]{
                Block.makeCuboidShape(2.5, 0, 4, 13.5, 3, 12),
                Block.makeCuboidShape(1, 0, 3.5, 15, 7, 12.5),
                Block.makeCuboidShape(2.5, 0, 2.5, 13.5, 3, 13.5),
        };
        if(s.getBlock() == RegBlocks.MAGNET_MIXER.get()) {
            return SHP[0];
        }
        if(s.getBlock() == RegBlocks.MIXER.get()) {
            return SHP[1];
        }
        if(s.getBlock() == RegBlocks.LAB_STOVE.get()) {
            return SHP[2];
        }
        return super.getFacingShape(s);
    }
}
