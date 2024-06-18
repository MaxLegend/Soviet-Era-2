package ru.tesmio.soviet.blocks.decorative.devices;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.soviet.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.soviet.reg.RegBlocks;
import ru.tesmio.soviet.reg.RegItems;
import ru.tesmio.soviet.utils.VoxelShapeUtil;

public class DigitalMicroscope extends BlockSideDevice {
    public final VoxelShape[] VS2 = new VoxelShape[]{
            Block.makeCuboidShape(0.25, 1.5, 0.5, 15.75, 5.25, 12.5),
            Block.makeCuboidShape(6, 1, 0, 16, 16, 16)
    };
    public final VoxelShape[] VS = new VoxelShape[]{
            Block.makeCuboidShape(0, 0, 0, 16, 16, 10.5),
            Block.makeCuboidShape(0, 11, 0, 16, 16, 12.5),
            Block.makeCuboidShape(0, 0, 0, 16, 2, 16),
            Block.makeCuboidShape(0, 0, 12.25, 16, 4, 16),

            Block.makeCuboidShape(0, 0, 0, 16, 4, 16)
    };

    public DigitalMicroscope(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }

    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        if (this == RegBlocks.DIG_MICROSCOPE.get()) {
            return new ItemStack[]{
                    new ItemStack(RegItems.ALUMINUM_SCRAP.get(), tr.nextInt(2, 7)),
                    new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(3, 9)),
                    new ItemStack(RegBlocks.PLATE_GOLDEN_JACKS.get(), tr.nextInt(3, 4)),
                    new ItemStack(RegBlocks.NETHERITE_CIRCUIT.get(), tr.nextInt(1, 2)),
            };
        }
        if (this == RegBlocks.DIG_MICROSCOPE_CONTROL.get()) {
            return new ItemStack[]{new ItemStack(RegItems.ALUMINUM_SCRAP.get(), tr.nextInt(2, 7)),
                    new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(3, 9)),
                    new ItemStack(RegBlocks.GOLD_CIRCUIT.get(), tr.nextInt(1, 3)),
                    new ItemStack(RegBlocks.DIAMOND_CIRCUIT.get(), tr.nextInt(0, 2))
            };
        }

        return new ItemStack[]{new ItemStack(RegItems.ALUMINUM_SCRAP.get(), tr.nextInt(2, 7)),
                new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(3, 9)),
                new ItemStack(RegBlocks.GOLD_CIRCUIT.get(), tr.nextInt(4, 6)),
                new ItemStack(RegBlocks.DIAMOND_CIRCUIT.get(), tr.nextInt(2, 4)),
                new ItemStack(RegBlocks.PLATE_PLATINUM_JACKS.get(), tr.nextInt(2, 4))
        };

    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {

        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
    }

    public VoxelShape getShape(BlockState s, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        if (s.getBlock() == RegBlocks.DIG_MICROSCOPE.get()) {
            switch (s.get(FACING)) {
                case NORTH:
                    return VoxelShapes.or(
                            VS[0],
                            VS[1],
                            VS[2],
                            VS[3]);
                case SOUTH:
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRot180(VS[0]),
                            VoxelShapeUtil.shapeRot180(VS[1]),
                            VoxelShapeUtil.shapeRot180(VS[2]),
                            VoxelShapeUtil.shapeRot180(VS[3]));
                //    return VoxelShapeUtil.shapeRot180(getFacingShape(s));
                case EAST:
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCCW90(VS[0]),
                            VoxelShapeUtil.shapeRotCCW90(VS[1]),
                            VoxelShapeUtil.shapeRotCCW90(VS[2]),
                            VoxelShapeUtil.shapeRotCCW90(VS[3]));
                case WEST:
                    return VoxelShapes.or(VoxelShapeUtil.shapeRotCW90(VS[0]),
                            VoxelShapeUtil.shapeRotCW90(VS[1]),
                            VoxelShapeUtil.shapeRotCW90(VS[2]),
                            VoxelShapeUtil.shapeRotCW90(VS[3]));
            }
        }

        switch (s.get(FACING)) {
            case NORTH:
                return VoxelShapes.or(getFacingShape(s));
            case SOUTH:
                return VoxelShapeUtil.shapeRot180(getFacingShape(s));
            case EAST:
                return VoxelShapeUtil.shapeRotCCW90(getFacingShape(s));
            case WEST:
                return VoxelShapeUtil.shapeRotCW90(VoxelShapeUtil.shapeRot180(getFacingShape(s)));
        }

        return VoxelShapes.fullCube();
    }

    @Override
    public VoxelShape getFacingShape(BlockState s) {
        if (s.getBlock() == RegBlocks.DIG_MICROSCOPE_CALC_BLOCK.get()) {
            return VS2[1];
        }
        if (s.getBlock() == RegBlocks.DIG_MICROSCOPE_CONTROL.get()) {
            return VS2[0];
        }
        return super.getFacingShape(s);
    }
}
