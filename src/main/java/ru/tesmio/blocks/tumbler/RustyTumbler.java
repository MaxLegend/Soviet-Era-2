package ru.tesmio.blocks.tumbler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.shapes.VoxelShape;
import ru.tesmio.blocks.baseblock.BlockSideCustomModelLeveler;
import ru.tesmio.reg.RegSounds;

public class RustyTumbler extends BlockSideCustomModelLeveler {
    public RustyTumbler(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }

    @Override
    public SoundEvent getSoundChangeState() {

        return RegSounds.SOUND_RUSTY_LEVER.get();
    }

    @Override
    public VoxelShape getFacingShape(BlockState s) {
        return Block.makeCuboidShape(0,0,0,16,16,15.5);
    }
}
