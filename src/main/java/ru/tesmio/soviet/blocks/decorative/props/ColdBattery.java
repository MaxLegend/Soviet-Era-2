package ru.tesmio.soviet.blocks.decorative.props;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.soviet.reg.RegItems;
import ru.tesmio.soviet.utils.VoxelShapeUtil;

import java.util.concurrent.ThreadLocalRandom;

public class ColdBattery extends LinearTable {
    final VoxelShape vs = VoxelShapes.create(0D, 0D, 0D, 1D, 1D, 0.3D);

    public ColdBattery(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        return new ItemStack[]{
                new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(1, 3)),
                new ItemStack(RegItems.ALUMINUM_SCRAP.get(), tr.nextInt(4, 10)),
                new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(6, 12)),
                new ItemStack(RegItems.ARMATURES.get(), tr.nextInt(4))
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState s, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(s, worldIn, pos, context);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (state.get(FACING)) {
            case EAST:
                return VoxelShapeUtil.shapeRotCCW90(vs);
            case WEST:
                return VoxelShapeUtil.shapeRotCW90(vs);
            case NORTH:
                return vs;
            case SOUTH:
                return VoxelShapeUtil.shapeRot180(vs);
        }
        return VoxelShapes.fullCube();
    }
}
