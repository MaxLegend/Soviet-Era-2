package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.utils.VoxelShapeUtil;

public class KitchenBlock extends BlockSideCustomModel {
    public KitchenBlock(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape BOX0 = Block.makeCuboidShape(0,0,0,16,16,13);
        VoxelShape BOX = Block.makeCuboidShape(0,15,0,16,16,13);
        VoxelShape BOX2 = Block.makeCuboidShape(0,0,0,16,15,12);
        if(state.getBlock() == RegBlocks.KITCHEN_TABLE.get()) {
            switch (state.get(FACING)) {
                case EAST:
                    return VoxelShapes.or(VoxelShapeUtil.shapeRotCW90(BOX),VoxelShapeUtil.shapeRotCW90(BOX2));
                case WEST:
                    return VoxelShapes.or(VoxelShapeUtil.shapeRotCCW90(BOX),VoxelShapeUtil.shapeRotCCW90(BOX2));
                case NORTH:
                    return VoxelShapes.or(VoxelShapeUtil.shapeRot180(BOX),VoxelShapeUtil.shapeRot180(BOX2));
                case SOUTH:
                    return VoxelShapes.or(BOX, BOX2);
            }
        }
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
