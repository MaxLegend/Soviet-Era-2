package ru.tesmio.soviet.blocks.storage.kitchen_table;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.soviet.blocks.storage.base.BlockStorage;
import ru.tesmio.soviet.reg.RegItems;
import ru.tesmio.soviet.reg.RegTileEntitys;
import ru.tesmio.soviet.utils.VoxelShapeUtil;

public class KitchenTable extends BlockStorage {
    public KitchenTable(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return RegTileEntitys.KITCHEN_TABLE_TE.get().create();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }

    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[]{
                new ItemStack(RegItems.WOOD_SCRAP.get(), 4)
        };
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {


        VoxelShape BOX = Block.makeCuboidShape(0, 15, 0, 16, 16, 13);
        VoxelShape BOX2 = Block.makeCuboidShape(0, 0, 0, 16, 15, 12);
        switch (state.get(FACING)) {
            case WEST:
                return VoxelShapes.or(VoxelShapeUtil.shapeRotCCW90(BOX), VoxelShapeUtil.shapeRotCCW90(BOX2));
            case EAST:
                return VoxelShapes.or(VoxelShapeUtil.shapeRotCW90(BOX), VoxelShapeUtil.shapeRotCW90(BOX2));
            case SOUTH:
                return VoxelShapes.or(BOX, BOX2);
            case NORTH:
                return VoxelShapes.or(VoxelShapeUtil.shapeRot180(BOX), VoxelShapeUtil.shapeRot180(BOX2));
        }
        return VoxelShapes.fullCube();
    }
}
