package ru.tesmio.soviet.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.soviet.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.soviet.reg.RegItems;
import ru.tesmio.soviet.utils.VoxelShapeUtil;

public class Sink extends BlockSideCustomModel {
    public Sink(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }

    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[]{
                new ItemStack(RegItems.CERAMIC_SHARD.get(), tr.nextInt(1, 3))
        };
    }

    final VoxelShape[] SHPS = new VoxelShape[]{
            Block.makeCuboidShape(1.5, 12, 0, 14.5, 15, 11),
            Block.makeCuboidShape(2.5, 12, 10, 13.5, 15, 12),
            Block.makeCuboidShape(3.5, 12, 10, 12.5, 15, 13),
            Block.makeCuboidShape(2.5, 11, 0, 13.5, 12, 12)
    };

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case EAST:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRotCW90(SHPS[0]),
                        VoxelShapeUtil.shapeRotCW90(SHPS[1]),
                        VoxelShapeUtil.shapeRotCW90(SHPS[2]),
                        VoxelShapeUtil.shapeRotCW90(SHPS[3])
                );
            case WEST:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRotCCW90(SHPS[0]),
                        VoxelShapeUtil.shapeRotCCW90(SHPS[1]),
                        VoxelShapeUtil.shapeRotCCW90(SHPS[2]),
                        VoxelShapeUtil.shapeRotCCW90(SHPS[3])
                );
            case NORTH:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRot180(SHPS[0]),
                        VoxelShapeUtil.shapeRot180(SHPS[1]),
                        VoxelShapeUtil.shapeRot180(SHPS[2]),
                        VoxelShapeUtil.shapeRot180(SHPS[3])
                );
            case SOUTH:
                return VoxelShapes.or(
                        SHPS[0],
                        SHPS[1],
                        SHPS[2],
                        SHPS[3]
                );
        }
        return VoxelShapes.fullCube();
    }
}
