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

public class BiolabChair extends SittableBlock {
    public BiolabChair(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult result)
    {
        return EntitySittableBlock.create(world, pos, 0.2, playerEntity);
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    VoxelShape[] BOXS = new VoxelShape[]{
            Block.makeCuboidShape(3,7.75,3,13,8.5,13),
            Block.makeCuboidShape(4,0,4,12,8,12)
    };
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

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

