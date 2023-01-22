package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.baseblock.BlockCornerCustomModel;

public class RustyHandhold extends BlockCornerCustomModel {
      final VoxelShape BOXS[] = new VoxelShape[] {
              Block.makeCuboidShape(0D, 0D, 0D, 3D, 16D, 16D),
              Block.makeCuboidShape(13D, 0D, 0D, 16D, 16D, 16D),
              Block.makeCuboidShape(0D, 0D, 0D, 16D, 16D, 3D),
              Block.makeCuboidShape(0D, 0D, 13D, 16D, 16D, 16D)};
    public RustyHandhold(Properties properties) {
        super(properties);
   }


    public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return this.getShape(state, worldIn, pos, null);
    }
    public VoxelShape getRayTraceShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, reader, pos, context);
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
