package ru.tesmio.soviet.blocks.decorative.props;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.soviet.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.soviet.reg.RegItems;
import ru.tesmio.soviet.utils.VoxelShapeUtil;

public class DSPPanel extends BlockSideDevice {
    final VoxelShape[] SHPS = new VoxelShape[]{
            VoxelShapes.or(
                    VoxelShapes.create(0, 0, 0.96875, 1, 1, 1),
                    VoxelShapes.create(0.0625, 0.0625, 0.9375, 0.9375, 0.9375, 0.96875)),
            VoxelShapes.or(
                    VoxelShapeUtil.shapeRot180(VoxelShapes.create(0, 0, 0.96875, 1, 1, 1)),
                    VoxelShapeUtil.shapeRot180(VoxelShapes.create(0.0625, 0.0625, 0.9375, 0.9375, 0.9375, 0.96875))),
            VoxelShapes.or(
                    VoxelShapeUtil.shapeRotCW90(VoxelShapes.create(0, 0, 0.96875, 1, 1, 1)),
                    VoxelShapeUtil.shapeRotCW90(VoxelShapes.create(0.0625, 0.0625, 0.9375, 0.9375, 0.9375, 0.96875))),
            VoxelShapes.or(
                    VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0, 0, 0.96875, 1, 1, 1)),
                    VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0.0625, 0.0625, 0.9375, 0.9375, 0.9375, 0.96875)))

    };

    public DSPPanel(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }

    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[]{
                new ItemStack(RegItems.WOOD_SCRAP.get(), 2)
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                return SHPS[0];

            case SOUTH:
                return SHPS[1];

            case WEST:
                return SHPS[2];
            case EAST:
                return SHPS[3];
        }
        return VoxelShapes.fullCube();
    }
}


