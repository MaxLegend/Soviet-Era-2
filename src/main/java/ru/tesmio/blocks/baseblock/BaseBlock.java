package ru.tesmio.blocks.baseblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

public class BaseBlock extends Block {
    public BaseBlock(Properties properties) {
        super(properties);
    }
    public boolean canProvidePower(BlockState state) {
        return true;
    }
}
