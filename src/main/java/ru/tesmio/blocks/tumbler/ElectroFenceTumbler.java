package ru.tesmio.blocks.tumbler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.baseblock.BlockSideCustomModelLeveler;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;
import ru.tesmio.reg.RegSounds;

import java.util.concurrent.ThreadLocalRandom;

public class ElectroFenceTumbler extends BlockSideCustomModelLeveler {
    //добавить коннекты по бокам в модели, сделать чтобы коннектился только по ним. Или сделать контактные пластины, в которые типа заходят проводки
    final VoxelShape defShape = Block.makeCuboidShape(0D, 0D, 0D, 16D, 16D, 14D);
    final VoxelShape defShape2 = Block.makeCuboidShape(0D, 0D, 2D, 16D, 16D, 16D);
    final VoxelShape defShape3 = Block.makeCuboidShape(2D, 0D, 0D, 16D, 16D, 16D);
    final VoxelShape defShape4 = Block.makeCuboidShape(0D, 0D, 0D, 14D, 16D, 16D);
    public ElectroFenceTumbler(Properties properties) {
        super(properties, 1F);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
     switch (state.get(FACING)) {
            case NORTH:
                return defShape;
            case WEST:
                return defShape4;
            case EAST:
                return defShape3;
            case SOUTH:
                return defShape2;

        }
        return defShape;
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        return new ItemStack[] {
                new ItemStack(RegItems.ALUMINUM_SCRAP.get(), tr.nextInt(2,4)),
                new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(3,5)),
                new ItemStack(RegBlocks.COPPER_CIRCUIT.get(), tr.nextInt(1,2)),
                new ItemStack(RegBlocks.SILVER_CIRCUIT.get(), tr.nextInt(1)),
        };
    }
    public SoundEvent getSoundChangeState() {
        return RegSounds.SOUND_RUSTY_LEVER.get();
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        if(side == blockState.get(FACING)) {
            return blockState.get(POWERED) ? 15 : 0;
        }
    return 0;
    }

    public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        if(side == blockState.get(FACING)) {
            return blockState.get(POWERED) ? 15 : 0;
        }
        return 0;
    }
}
