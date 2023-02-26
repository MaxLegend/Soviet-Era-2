package ru.tesmio.blocks.storage.safe;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.storage.base.BlockStorage;
import ru.tesmio.reg.RegTileEntitys;
import ru.tesmio.utils.VoxelShapeUtil;

public class BlockSafe extends BlockStorage {
    VoxelShape BOX = Block.makeCuboidShape(0D, 0D, 4D, 16D, 16D, 16D);
    public BlockSafe(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return RegTileEntitys.SAFE_TE.get().create();
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                return BOX;
            case WEST:
                return VoxelShapeUtil.shapeRotCW90(BOX);
            case SOUTH:
                return VoxelShapeUtil.shapeRot180(BOX);
            case EAST:
                return VoxelShapeUtil.shapeRotCCW90(BOX);
        }
        return VoxelShapes.fullCube();
    }
}
