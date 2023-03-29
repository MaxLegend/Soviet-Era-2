package ru.tesmio.blocks.decorative.props.chairs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.baseblock.SittableBlock;
import ru.tesmio.reg.RegItems;
import ru.tesmio.utils.VoxelShapeUtil;

public class PurpleChair extends SittableBlock {
    public PurpleChair(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {
                new ItemStack(RegItems.WOOD_SCRAP.get(), tr.nextInt(1,3)),
        };
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case EAST:
                return VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(1,0,0,15,9,15.25));
            case WEST:
                return VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(1,0,0,15,9,15.25));
            case NORTH:
                return VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(1,0,0,15,9,15.25));
            case SOUTH:
                return Block.makeCuboidShape(1,0,0,15,9,15.25);
        }
        return VoxelShapes.fullCube();
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }

}
