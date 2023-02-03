package ru.tesmio.blocks.decorative.windows;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.baseblock.BlockSideConnectUDLR;
import ru.tesmio.utils.VoxelShapeUtil;

public class AlumWindow extends BlockSideConnectUDLR {
    final VoxelShape BOX = Block.makeCuboidShape(0D, 0D, 0D, 16D, 16D, 3.04D);
    public AlumWindow(Properties properties) {
        super(properties);
    }
    //настроить шейпы для разбитых окон
    public VoxelShape getShape(BlockState s, IBlockReader w, BlockPos p, ISelectionContext context) {
        switch (s.get(FACING)) {
            case NORTH:
                return VoxelShapeUtil.shapeRot180(BOX);
            case SOUTH:
                return BOX;
            case WEST:
                return VoxelShapeUtil.shapeRotCCW90(BOX);
            case EAST:
                return VoxelShapeUtil.shapeRotCW90(BOX);
        }
        return BOX;
    }

}
