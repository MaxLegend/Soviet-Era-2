package ru.tesmio.blocks.tumbler;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.baseblock.BlockSideCustomModelLeveler;

public class ElectroFenceTumbler extends BlockSideCustomModelLeveler {
    //добавить коннекты по бокам в модели, сделать чтобы коннектился только по ним. Или сделать контактные пластины, в которые типа заходят проводки
    public ElectroFenceTumbler(Properties properties) {
        super(properties);
    }
    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return blockState.get(POWERED) ? 15 : 0;
    }

    public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return blockState.get(POWERED) ? 15 : 0;
    }
}
