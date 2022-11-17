package ru.tesmio.utils;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IBlockPowered {
    default BlockState isBlockPowered(World w, BlockPos p) {
        return w.getBlockState(p);
    }
}
