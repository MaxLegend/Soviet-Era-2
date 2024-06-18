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
import ru.tesmio.soviet.reg.RegBlocks;
import ru.tesmio.soviet.reg.RegItems;
import ru.tesmio.soviet.utils.VoxelShapeUtil;

public class KitchenBlock extends BlockSideCustomModel {
    public KitchenBlock(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }

    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        if (this == RegBlocks.KITCHEN_SINK.get()) {
            return new ItemStack[]{
                    new ItemStack(RegItems.ALUMINUM_SCRAP.get(), 2),
                    new ItemStack(RegItems.RUSTY_SCRAP.get(), 2)
            };
        }
        return new ItemStack[]{
                new ItemStack(RegItems.WOOD_SCRAP.get(), 4)
        };
    }

    final VoxelShape BOX0 = Block.makeCuboidShape(0, 0, 0, 16, 16, 13);
    final VoxelShape BOX = Block.makeCuboidShape(0, 15, 0, 16, 16, 13);
    final VoxelShape BOX2 = Block.makeCuboidShape(0, 0, 0, 16, 15, 12);

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (state.get(FACING)) {
            case EAST:
                return VoxelShapeUtil.shapeRotCW90(BOX0);
            case WEST:
                return VoxelShapeUtil.shapeRotCCW90(BOX0);
            case NORTH:
                return VoxelShapeUtil.shapeRot180(BOX0);
            case SOUTH:
                return BOX0;
        }

        return VoxelShapes.fullCube();
    }
}
