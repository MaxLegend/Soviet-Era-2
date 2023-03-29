package ru.tesmio.blocks.baseblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

import java.util.concurrent.ThreadLocalRandom;

public class BaseBlock extends Block {
    public final ThreadLocalRandom tr = ThreadLocalRandom.current();
    public BaseBlock(Properties properties) {
        super(properties);
    }
    public boolean canProvidePower(BlockState state) {
        return true;
    }
}
