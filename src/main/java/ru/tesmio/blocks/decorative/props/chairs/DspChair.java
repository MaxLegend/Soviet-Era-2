package ru.tesmio.blocks.decorative.props.chairs;

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

public class DspChair extends SittableBlock {
    public DspChair(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult result)
    {
        return EntitySittableBlock.create(world, pos, 0.2, playerEntity);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape[] BOXS = new VoxelShape[]{
                Block.makeCuboidShape(2,8,2,14,9,14),
                Block.makeCuboidShape(3,0,3,13,8,13)
        };
        switch (state.get(FACING)) {
            case WEST:
                return VoxelShapes.or(BOXS[0],BOXS[1]);
            case SOUTH:
                return VoxelShapes.or(VoxelShapeUtil.shapeRotCW90(BOXS[0]),VoxelShapeUtil.shapeRotCW90(BOXS[1]));
            case EAST:
                return VoxelShapes.or(VoxelShapeUtil.shapeRot180(BOXS[0]),VoxelShapeUtil.shapeRot180(BOXS[1]));
            case NORTH:
                return VoxelShapes.or(VoxelShapeUtil.shapeRotCCW90(BOXS[0]),VoxelShapeUtil.shapeRotCCW90(BOXS[1]));
        }

    return VoxelShapes.fullCube();
    }

}
