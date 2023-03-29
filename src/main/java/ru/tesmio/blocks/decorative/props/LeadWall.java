package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import ru.tesmio.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;

public class LeadWall extends BlockSideDevice {
    public LeadWall(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        if(this == RegBlocks.THIN_LEAD_WALL.get()) {
            return new ItemStack[]{
                    new ItemStack(RegItems.LEAD_SCRAP.get(), tr.nextInt(1, 2)),
                    new ItemStack(RegItems.ARMATURE.get(), tr.nextInt(4, 6)),
                    new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(3, 5)),
                    new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(1, 2)),
            };
        }
        if(this == RegBlocks.LEAD_WALL.get()) {
            return new ItemStack[]{
                    new ItemStack(RegItems.LEAD_SCRAP.get(), tr.nextInt(3, 6)),
                    new ItemStack(RegItems.ARMATURE.get(), tr.nextInt(4, 6)),
                    new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(3, 5)),
                    new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(1, 2)),
            };
        }
        return new ItemStack[]{
                ItemStack.EMPTY
        };
    }
    @Override
    public VoxelShape getFacingShape(BlockState s) {
        if(s.getBlock() == RegBlocks.LEAD_WALL.get()) return Block.makeCuboidShape(0,0,2,16,16,15);
        if(s.getBlock() == RegBlocks.THIN_LEAD_WALL.get())return Block.makeCuboidShape(0,0,11,16,16,16);
        return VoxelShapes.fullCube();
    }
}
