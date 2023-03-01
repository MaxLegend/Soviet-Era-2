package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.baseblock.BlockRotatedAxisCustomModel;

public class VentFilterRest extends BlockRotatedAxisCustomModel {
    VoxelShape[] BOXS = new VoxelShape[] {
            VoxelShapes.create(1D, 0D, 0.87D, 0D, 0.265D, 0.13D), //upx
            VoxelShapes.create(0.87D, 0D, 1D, 0.13D, 0.265D, 0D),//upz
            VoxelShapes.create(0.87D, 1D, 1D, 0.13D, 0.735D, 0D),//downz
            VoxelShapes.create(1D, 1D, 0.87D, 0D, 0.735D, 0.13D),//downx
            VoxelShapes.create(0D, 0.13D, 0D, 1D, 0.87D,  0.265D),//south
            VoxelShapes.create(0D, 0.13D, 0.735D, 1D, 0.87D, 1D), //north
            VoxelShapes.create(0.735D, 0.13D, 0D, 1D, 0.87D, 1D),//west
            VoxelShapes.create(0D, 0.13D, 0D, 0.265D, 0.87D, 1D),//east
    };
    public VentFilterRest(Properties properties) {
        super(properties, 1F);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (state.get(FACING)) {
            case UP_X:
                return BOXS[3];
            case UP_Z:
                return BOXS[2];
            case DOWN_X:
                return BOXS[0];
            case DOWN_Z:
                return BOXS[1];
            case NORTH:
                return BOXS[4];
            case SOUTH:
                return BOXS[5];
            case WEST:
                return BOXS[7];
            case EAST:
                return BOXS[6];
        }
        return VoxelShapes.fullCube();
    }
}
