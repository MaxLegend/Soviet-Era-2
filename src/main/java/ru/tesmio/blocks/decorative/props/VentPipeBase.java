package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.baseblock.BlockCustomModel;
import ru.tesmio.reg.RegItems;

public class VentPipeBase extends BlockCustomModel {
    public VentPipeBase(Properties properties) {
        super(properties);
    }
    public VoxelShape getShape(BlockState s, IBlockReader w, BlockPos p, ISelectionContext c) {
        return Block.makeCuboidShape(0,0,0,16,16,16);
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {
                new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(2,4)),
                new ItemStack(RegItems.ARMATURES.get(), tr.nextInt(1,3)),
        };
    }
}
