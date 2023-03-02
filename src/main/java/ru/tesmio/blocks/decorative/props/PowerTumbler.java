package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import ru.tesmio.blocks.baseblock.BlockSideCustomModelLeveler;
import ru.tesmio.utils.VoxelShapeUtil;

public class PowerTumbler extends BlockSideCustomModelLeveler {
    public PowerTumbler(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }
    @Override
    public VoxelShape getFacingShape(BlockState s) {
        return VoxelShapeUtil.shapeRot180(VoxelShapes.create(0.377D, 0.377D, 1D, 0.623D, 0.623D, 0.956D));
    }
}
