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

public class HomePipesBattery extends BlockSideCustomModel {
    public HomePipesBattery(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }

    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[]{
                new ItemStack(RegItems.ARMATURES.get(), tr.nextInt(2, 5))
        };
    }

    final VoxelShape BOX = Block.makeCuboidShape(0, 0, 12, 16, 16, 16);

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (state.get(FACING)) {
            case NORTH:
                return BOX;
            case SOUTH:
                return VoxelShapeUtil.shapeRot180(BOX);
            case WEST:
                return VoxelShapeUtil.shapeRotCW90(BOX);
            case EAST:
                return VoxelShapeUtil.shapeRotCCW90(BOX);
        }
        return VoxelShapes.fullCube();
    }
}
