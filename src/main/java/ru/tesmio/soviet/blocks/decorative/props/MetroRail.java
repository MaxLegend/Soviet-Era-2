package ru.tesmio.soviet.blocks.decorative.props;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.soviet.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.soviet.utils.VoxelShapeUtil;

public class MetroRail extends BlockSideDevice {

    public MetroRail(float shadingInside) {
        super(shadingInside);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }

    final VoxelShape SHP = VoxelShapes.or(VoxelShapes.create(0, 0, 0.15625, 1, 0.046875, 0.34375),
            VoxelShapes.create(0, 0, 0.65625, 1, 0.046875, 0.84375),
            VoxelShapes.create(0.125, 0.046875, 0, 0.28125, 0.0625, 1),
            VoxelShapes.create(0.1796875, 0.0625, 0, 0.2265625, 0.109375, 1),
            VoxelShapes.create(0.15625, 0.109375, 0, 0.25, 0.171875, 1),
            VoxelShapes.create(0.75, 0.109375, 0, 0.84375, 0.171875, 1),
            VoxelShapes.create(0.7734375, 0.0625, 0, 0.8203125, 0.109375, 1),
            VoxelShapes.create(0.71875, 0.046875, 0, 0.875, 0.0625, 1));
    final VoxelShape SHP2 = VoxelShapes.or(VoxelShapeUtil.shapeRotCW90(VoxelShapes.create(0, 0, 0.15625, 1, 0.046875, 0.34375)),
            VoxelShapeUtil.shapeRotCW90(VoxelShapes.create(0, 0, 0.65625, 1, 0.046875, 0.84375)),
            VoxelShapeUtil.shapeRotCW90(VoxelShapes.create(0.125, 0.046875, 0, 0.28125, 0.0625, 1)),
            VoxelShapeUtil.shapeRotCW90(VoxelShapes.create(0.1796875, 0.0625, 0, 0.2265625, 0.109375, 1)),
            VoxelShapeUtil.shapeRotCW90(VoxelShapes.create(0.15625, 0.109375, 0, 0.25, 0.171875, 1)),
            VoxelShapeUtil.shapeRotCW90(VoxelShapes.create(0.75, 0.109375, 0, 0.84375, 0.171875, 1)),
            VoxelShapeUtil.shapeRotCW90(VoxelShapes.create(0.7734375, 0.0625, 0, 0.8203125, 0.109375, 1)),
            VoxelShapeUtil.shapeRotCW90(VoxelShapes.create(0.71875, 0.046875, 0, 0.875, 0.0625, 1)));

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case NORTH:
            case SOUTH:
                return SHP;
            case WEST:
            case EAST:
                return SHP2;
        }
        return VoxelShapes.fullCube();
    }
}
