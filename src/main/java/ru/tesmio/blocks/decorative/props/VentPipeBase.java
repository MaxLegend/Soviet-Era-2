package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.baseblock.BlockCustomModel;

public class VentPipeBase extends BlockCustomModel {
    public VentPipeBase(Properties properties) {
        super(properties);
    }
    public VoxelShape getShape(BlockState s, IBlockReader w, BlockPos p, ISelectionContext c) {
        return Block.makeCuboidShape(0,0,0,16,16,16);
    }
}
