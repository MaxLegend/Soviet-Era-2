package ru.tesmio.blocks.storage.dsp_tump;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.storage.base.BlockStorage;
import ru.tesmio.reg.RegTileEntitys;
import ru.tesmio.utils.VoxelShapeUtil;

public class DspTumbBlock extends BlockStorage {
    VoxelShape BOX = Block.makeCuboidShape(0D, 6D, 3.5D, 16D, 16D, 12.5D);
    VoxelShape BOX2 = Block.makeCuboidShape(0D, 15D, 3.21D, 16D, 16D, 12.5D);
    public DspTumbBlock(AbstractBlock.Properties properties) {
        super(properties,1F);
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return RegTileEntitys.DSP_TUMB_TE.get().create();
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case SOUTH:
                return VoxelShapes.or(
                        BOX,
                        BOX2);
            case EAST:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRotCW90(BOX),
                        VoxelShapeUtil.shapeRotCW90(BOX2));
            case NORTH:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRot180(BOX),
                        VoxelShapeUtil.shapeRot180(BOX2));
            case WEST:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRotCCW90(BOX),
                        VoxelShapeUtil.shapeRotCCW90(BOX2));
        }
        return VoxelShapes.fullCube();
    }
}
