package ru.tesmio.soviet.blocks.tumbler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.shapes.VoxelShape;
import ru.tesmio.soviet.blocks.baseblock.BlockSideCustomModelLeveler;
import ru.tesmio.soviet.reg.RegBlocks;
import ru.tesmio.soviet.reg.RegItems;
import ru.tesmio.soviet.reg.SovietSounds;

public class RustyTumbler extends BlockSideCustomModelLeveler {
    public RustyTumbler(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }

    @Override
    public SoundEvent getSoundChangeState() {

        return SovietSounds.CONTAINMENT_DOOR.get();
    }

    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[]{
                new ItemStack(RegItems.ARMATURES.get(), tr.nextInt(1, 4)),
                new ItemStack(RegBlocks.COPPER_CIRCUIT.get(), 1),
                new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(2, 5))
        };

    }

    @Override
    public VoxelShape getFacingShape(BlockState s) {
        return Block.makeCuboidShape(0, 0, 0, 16, 16, 15.5);
    }
}
