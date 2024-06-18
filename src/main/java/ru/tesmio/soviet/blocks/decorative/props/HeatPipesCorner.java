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

import java.util.concurrent.ThreadLocalRandom;

public class HeatPipesCorner extends BlockSideCustomModel {
    public HeatPipesCorner(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }

    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        return new ItemStack[]{
                new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(6)),
                new ItemStack(RegItems.ARMATURES.get(), tr.nextInt(2)),
        };
    }

    final VoxelShape[] SHPS = new VoxelShape[]{
            VoxelShapes.or(
                    VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(0D, 5D, 1D, 2D, 12D, 15D)),
                    VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0D, 5D, 1D, 2D, 12D, 15D)),
                    Block.makeCuboidShape(2D, 6D, 2D, 14D, 11D, 14D)
            ),
            VoxelShapes.or(
                    Block.makeCuboidShape(0D, 5D, 1D, 2D, 12D, 15D),
                    VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0D, 5D, 1D, 2D, 12D, 15D))),
                    Block.makeCuboidShape(2D, 6D, 2D, 14D, 11D, 14D)
            ),
            VoxelShapes.or(
                    Block.makeCuboidShape(0D, 5D, 1D, 2D, 12D, 15D),
                    VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0D, 5D, 1D, 2D, 12D, 15D)),
                    Block.makeCuboidShape(2D, 6D, 2D, 14D, 11D, 14D)
            ),
            VoxelShapes.or(
                    VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(0D, 5D, 1D, 2D, 12D, 15D)),
                    VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(0D, 5D, 1D, 2D, 12D, 15D))),
                    Block.makeCuboidShape(2D, 6D, 2D, 14D, 11D, 14D)
            )
    };

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
