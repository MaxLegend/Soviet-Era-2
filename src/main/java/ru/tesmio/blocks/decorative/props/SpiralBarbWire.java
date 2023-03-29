package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.reg.RegItems;
import ru.tesmio.utils.VoxelShapeUtil;

public class SpiralBarbWire extends BlockSideCustomModel {
    public SpiralBarbWire(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }

    public void onEntityCollision(BlockState s, World w, BlockPos p, Entity e) {
        e.attackEntityFrom(DamageSource.CACTUS, 3);
        e.setMotionMultiplier(s, new Vector3d(0.25D, (double) 0.05F, 0.25D));
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {
                new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(1)),
        };
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape BOX = Block.makeCuboidShape(2, 0, 0, 14, 11, 16);
        switch (state.get(FACING)) {
            case SOUTH:
            case NORTH:
                return BOX;
            case WEST:
            case EAST:
                return VoxelShapeUtil.shapeRotCW90(BOX);
        }
    return BOX;
}
}
