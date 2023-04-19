package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.utils.VoxelShapeUtil;

public class DigitalMicroscope extends BlockSideDevice {
    public final VoxelShape[] VS2 = new VoxelShape[] {
            Block.makeCuboidShape(0.25,1.5,0.5,15.75,5.25,12.5),
            Block.makeCuboidShape(6,1,0,16,16,16)
    };
    public final VoxelShape[] VS = new VoxelShape[] {
            Block.makeCuboidShape(0,0,0,16,16,10.5),
            Block.makeCuboidShape(0,11,0,16,16,12.5),
            Block.makeCuboidShape(0,0,0,16,2,16),
            Block.makeCuboidShape(0,0,12.25,16,4,16),

            Block.makeCuboidShape(0,0,0,16,4,16)
    };
    public DigitalMicroscope(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {

        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
    }
    public VoxelShape getShape(BlockState s, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        if(s.getBlock() == RegBlocks.DIG_MICROSCOPE.get()) {
            switch (s.get(FACING)) {
                case NORTH:
                    return VoxelShapes.or(
                            VS[0],
                            VS[1],
                            VS[2],
                            VS[3]);
                case SOUTH:
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRot180(VS[0]),
                            VoxelShapeUtil.shapeRot180(VS[1]),
                            VoxelShapeUtil.shapeRot180(VS[2]),
                            VoxelShapeUtil.shapeRot180(VS[3]));
                    //    return VoxelShapeUtil.shapeRot180(getFacingShape(s));
                case EAST:
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCCW90(VS[0]),
                            VoxelShapeUtil.shapeRotCCW90(VS[1]),
                            VoxelShapeUtil.shapeRotCCW90(VS[2]),
                            VoxelShapeUtil.shapeRotCCW90(VS[3]));
                case WEST:
                    return VoxelShapes.or(VoxelShapeUtil.shapeRotCW90(VS[0]),
                            VoxelShapeUtil.shapeRotCW90(VS[1]),
                            VoxelShapeUtil.shapeRotCW90(VS[2]),
                            VoxelShapeUtil.shapeRotCW90(VS[3]));
            }
        }

            switch (s.get(FACING)) {
                case NORTH:
                    return VoxelShapes.or(getFacingShape(s));
                case SOUTH:
                        return VoxelShapeUtil.shapeRot180(getFacingShape(s));
                case EAST:
                      return VoxelShapeUtil.shapeRotCCW90(getFacingShape(s));
                case WEST:
                      return VoxelShapeUtil.shapeRotCW90(getFacingShape(s));
            }

        return VoxelShapes.fullCube();
    }

    @Override
    public VoxelShape getFacingShape(BlockState s) {
        if(s.getBlock() == RegBlocks.DIG_MICROSCOPE_CALC_BLOCK.get()) {
            return VS2[1];
        }
        if(s.getBlock() == RegBlocks.DIG_MICROSCOPE_CONTROL.get()) {
            return VS2[0];
        }
        return super.getFacingShape(s);
    }
}
