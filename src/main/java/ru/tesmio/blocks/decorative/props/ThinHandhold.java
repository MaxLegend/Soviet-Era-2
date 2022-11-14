package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class ThinHandhold extends RustyHandhold {
    final VoxelShape BOXS[] = new VoxelShape[] {Block.makeCuboidShape(0D, 0D, 0D, 2D, 16D, 16D),
            Block.makeCuboidShape(14D, 0D, 0D, 16D, 16D, 16D),
            Block.makeCuboidShape(0D, 0D, 0D, 16D, 16D, 2D),
            Block.makeCuboidShape(0D, 0D, 14D, 16D, 16D, 16D)};
    public ThinHandhold(Properties properties) {
        super(properties);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (state.get(FACING)) {
            case EAST:
                if(state.get(ENUM_CONNECT) == EnumConnent.CORNER_LEFT) return VoxelShapes.or(BOXS[0],BOXS[3]);
                if(state.get(ENUM_CONNECT) == EnumConnent.CORNER_RIGHT) return VoxelShapes.or(BOXS[0],BOXS[2]);
                return BOXS[0];
            case WEST:
                if(state.get(ENUM_CONNECT) == EnumConnent.CORNER_LEFT) return VoxelShapes.or(BOXS[1],BOXS[2]);
                if(state.get(ENUM_CONNECT) == EnumConnent.CORNER_RIGHT) return VoxelShapes.or(BOXS[1],BOXS[3]);
                return BOXS[1];
            case SOUTH:
                if(state.get(ENUM_CONNECT) == EnumConnent.CORNER_LEFT) return VoxelShapes.or(BOXS[2],BOXS[0]);
                if(state.get(ENUM_CONNECT) == EnumConnent.CORNER_RIGHT) return VoxelShapes.or(BOXS[2],BOXS[1]);
                return BOXS[2];
            case NORTH:
                if(state.get(ENUM_CONNECT) == EnumConnent.CORNER_LEFT) return VoxelShapes.or(BOXS[3],BOXS[1]);
                if(state.get(ENUM_CONNECT) == EnumConnent.CORNER_RIGHT) return VoxelShapes.or(BOXS[3],BOXS[0]);
                return BOXS[3];
            default:
                return VoxelShapes.fullCube();
        }
    }
}
