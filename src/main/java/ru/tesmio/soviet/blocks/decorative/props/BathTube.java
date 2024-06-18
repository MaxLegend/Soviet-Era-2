package ru.tesmio.soviet.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BedPart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.soviet.blocks.baseblock.BlockBed;
import ru.tesmio.soviet.reg.RegItems;
import ru.tesmio.soviet.utils.VoxelShapeUtil;

public class BathTube extends BlockBed {
    final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.makeCuboidShape(1.0D, 3.0D, 2.0D, 16.0D, 5.0D, 14.0D),
            Block.makeCuboidShape(0.0D, 5.0D, 2.0D, 1.0D, 12.0D, 14.0D),
            Block.makeCuboidShape(1.0D, 5.0D, 1.0D, 16.0D, 12.0D, 2.0D),
            Block.makeCuboidShape(1.0D, 5.0D, 14.0D, 16.0D, 12.0D, 15.0D)
    };

    public BathTube(Properties properties) {
        super(properties);
    }

    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[]{
                new ItemStack(RegItems.CERAMIC_SHARD.get(), 4),
                new ItemStack(RegItems.RUSTY_SCRAP.get(), 6)
        };
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (state.get(HORIZONTAL_FACING)) {
            case EAST:
                if (state.get(PART) == BedPart.HEAD) {
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRot180(SHAPES[0]),
                            VoxelShapeUtil.shapeRot180(SHAPES[1]),
                            VoxelShapeUtil.shapeRot180(SHAPES[2]),
                            VoxelShapeUtil.shapeRot180(SHAPES[3]));
                }
                return VoxelShapes.or(
                        SHAPES[0],
                        SHAPES[1],
                        SHAPES[2],
                        SHAPES[3]);
            case NORTH:
                if (state.get(PART) == BedPart.HEAD) {
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCCW90(SHAPES[0]),
                            VoxelShapeUtil.shapeRotCCW90(SHAPES[1]),
                            VoxelShapeUtil.shapeRotCCW90(SHAPES[2]),
                            VoxelShapeUtil.shapeRotCCW90(SHAPES[3]));
                }
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRotCW90(VoxelShapeUtil.shapeRot180(SHAPES[0])),
                        VoxelShapeUtil.shapeRotCW90(VoxelShapeUtil.shapeRot180(SHAPES[1])),
                        VoxelShapeUtil.shapeRotCW90(VoxelShapeUtil.shapeRot180(SHAPES[2])),
                        VoxelShapeUtil.shapeRotCW90(VoxelShapeUtil.shapeRot180(SHAPES[3])));
            case SOUTH:
                if (state.get(PART) == BedPart.FOOT) {
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCW90(SHAPES[0]),
                            VoxelShapeUtil.shapeRotCW90(SHAPES[1]),
                            VoxelShapeUtil.shapeRotCW90(SHAPES[2]),
                            VoxelShapeUtil.shapeRotCW90(SHAPES[3]));

                }
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRotCCW90(VoxelShapeUtil.shapeRot180(SHAPES[0])),
                        VoxelShapeUtil.shapeRotCCW90(VoxelShapeUtil.shapeRot180(SHAPES[1])),
                        VoxelShapeUtil.shapeRotCCW90(VoxelShapeUtil.shapeRot180(SHAPES[2])),
                        VoxelShapeUtil.shapeRotCCW90(VoxelShapeUtil.shapeRot180(SHAPES[3])));
            case WEST:
                if (state.get(PART) == BedPart.FOOT) {
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRot180(SHAPES[0]),
                            VoxelShapeUtil.shapeRot180(SHAPES[1]),
                            VoxelShapeUtil.shapeRot180(SHAPES[2]),
                            VoxelShapeUtil.shapeRot180(SHAPES[3]));
                }
                return VoxelShapes.or(SHAPES[0], SHAPES[1], SHAPES[2], SHAPES[3]);
        }
        return VoxelShapes.fullCube();
    }
}
