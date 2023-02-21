package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.SittableBlock;
import ru.tesmio.entity.EntitySittableBlock;
import ru.tesmio.utils.VoxelShapeUtil;

public class Toilet extends SittableBlock {
    public Toilet(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult result)
    {
        return EntitySittableBlock.create(world, pos, 0.1, playerEntity);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case WEST:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(4,5,6,12,7,12)),
                        VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(3.5,5,7,12.5,7,12)),
                        VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(3,5,8,13,7,12)),
                        VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(4,5,8,12,7,14)),
                        VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(5,5,8,11,7,15)),
                        VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(6,5,8,10,7,16)),
                        VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(5,3.25,3,11,5,14)),
                        VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(6,0,3,10,5,13)),
                        VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(3,5,1,13,14,5)),
                        VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(4,5,0,12,14,6))
                );
            case EAST:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(4,5,6,12,7,12)),
                        VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(3.5,5,7,12.5,7,12)),
                        VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(3,5,8,13,7,12)),
                        VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(4,5,8,12,7,14)),
                        VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(5,5,8,11,7,15)),
                        VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(6,5,8,10,7,16)),
                        VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(5,3.25,3,11,5,14)),
                        VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(6,0,3,10,5,13)),
                        VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(3,5,1,13,14,5)),
                        VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(4,5,0,12,14,6))
                );
            case SOUTH:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(4,5,6,12,7,12)),
                        VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(3.5,5,7,12.5,7,12)),
                        VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(3,5,8,13,7,12)),
                        VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(4,5,8,12,7,14)),
                        VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(5,5,8,11,7,15)),
                        VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(6,5,8,10,7,16)),
                        VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(5,3.25,3,11,5,14)),
                        VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(6,0,3,10,5,13)),
                        VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(3,5,1,13,14,5)),
                        VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(4,5,0,12,14,6))
                );
            case NORTH:
                return VoxelShapes.or(
                        Block.makeCuboidShape(4,5,6,12,7,12),
                        Block.makeCuboidShape(3.5,5,7,12.5,7,12),
                        Block.makeCuboidShape(3,5,8,13,7,12),
                        Block.makeCuboidShape(4,5,8,12,7,14),
                        Block.makeCuboidShape(5,5,8,11,7,15),
                        Block.makeCuboidShape(6,5,8,10,7,16),
                        Block.makeCuboidShape(5,3.25,3,11,5,14),
                        Block.makeCuboidShape(6,0,3,10,5,13),
                        Block.makeCuboidShape(3,5,1,13,14,5),
                        Block.makeCuboidShape(4,5,0,12,14,6)
                );
        }
        return VoxelShapes.fullCube();
    }
}
