package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.reg.RegItems;
import ru.tesmio.utils.VoxelShapeUtil;

public class BlockLadder extends BlockSideCustomModel {
    final VoxelShape BOX = Block.makeCuboidShape(3D, 0D, 13D, 13D, 16D, 16D);
    public BlockLadder(Properties builder) {
        super(builder, 1F);
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {
                new ItemStack(RegItems.ARMATURES.get(), tr.nextInt(1,2)),
        };
    }
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return true;
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    @Override
    public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity) {
        return true;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (state.get(FACING)) {
            case NORTH:
                return VoxelShapeUtil.shapeRot180(BOX);
            case SOUTH:
                return BOX;
            case WEST:
                return VoxelShapeUtil.shapeRotCCW90(BOX);
            case EAST:
                return VoxelShapeUtil.shapeRotCW90(BOX);
        }
        return VoxelShapes.fullCube();
    }
}
