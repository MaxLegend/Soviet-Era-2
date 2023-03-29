package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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
import ru.tesmio.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;
import ru.tesmio.utils.VoxelShapeUtil;

import java.util.concurrent.ThreadLocalRandom;

public class DryCab extends BlockSideDevice {
    public DryCab(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        return new ItemStack[] {
                new ItemStack(RegItems.ALUMINUM_SCRAP.get(), tr.nextInt(3)),
                new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(5)),
                new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(2)),
                new ItemStack(RegBlocks.GOLD_CIRCUIT.get(), tr.nextInt(1))
        };
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        VoxelShape SHPS[] = new VoxelShape[] {
                Block.makeCuboidShape(5, 2, 1, 11, 16, 15),
                Block.makeCuboidShape(4, 3, 1, 12, 15, 15),
                Block.makeCuboidShape(3, 4, 1, 13, 14, 15),
                Block.makeCuboidShape(2, 5, 1, 14, 13, 15),
                Block.makeCuboidShape(1, 6, 1, 15, 12, 15),
                Block.makeCuboidShape(1, 0, 2, 15, 2, 14),
                Block.makeCuboidShape(2, 0, 2, 14, 3, 14),
                Block.makeCuboidShape(3, 0, 2, 13, 5, 14),

                Block.makeCuboidShape(5, 3, 0, 11, 15, 16),
                Block.makeCuboidShape(4, 4, 0, 12, 14, 16),
                Block.makeCuboidShape(3, 5, 0, 13, 13, 16),
                Block.makeCuboidShape(2, 6, 0, 14, 12, 16),
        };
        switch (state.get(FACING)) {
            case NORTH:
            case SOUTH:
                return VoxelShapes.or(
                        SHPS[0],
                        SHPS[1],
                        SHPS[2],
                        SHPS[3],
                        SHPS[4],
                        SHPS[5],
                        SHPS[6],
                        SHPS[7],
                        SHPS[8],
                        SHPS[9],
                        SHPS[10],
                        SHPS[11]);
            case WEST:
            case EAST:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRotCW90(SHPS[0]),
                        VoxelShapeUtil.shapeRotCW90(SHPS[1]),
                        VoxelShapeUtil.shapeRotCW90(SHPS[2]),
                        VoxelShapeUtil.shapeRotCW90(SHPS[3]),
                        VoxelShapeUtil.shapeRotCW90(SHPS[4]),
                        VoxelShapeUtil.shapeRotCW90(SHPS[5]),
                        VoxelShapeUtil.shapeRotCW90(SHPS[6]),
                        VoxelShapeUtil.shapeRotCW90(SHPS[7]),
                        VoxelShapeUtil.shapeRotCW90( SHPS[8]),
                        VoxelShapeUtil.shapeRotCW90(SHPS[9]),
                        VoxelShapeUtil.shapeRotCW90( SHPS[10]),
                        VoxelShapeUtil.shapeRotCW90( SHPS[11]));

        }
    return VoxelShapes.fullCube();
    }
}
