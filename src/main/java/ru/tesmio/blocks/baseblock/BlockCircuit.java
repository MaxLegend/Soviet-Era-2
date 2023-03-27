package ru.tesmio.blocks.baseblock;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;
import ru.tesmio.reg.RegBlocks;

import javax.annotation.Nullable;

public class BlockCircuit extends BlockCustomModel {


    public BlockCircuit(Properties p) {
        super(p);
    }
    public BlockCircuit(Properties p, VoxelShape vs, float shadingInside) {
        super(p,vs,shadingInside);

    }
    public IItemProvider getDrop() {
        if(this == RegBlocks.COPPER_CIRCUIT_EMPTY.get()) {
            return RegBlocks.COPPER_CIRCUIT_EMPTY.get();
        }
        if(this == RegBlocks.SILVER_CIRCUIT_EMPTY.get()) {
            return RegBlocks.SILVER_CIRCUIT_EMPTY.get();
        }
        if(this == RegBlocks.GOLD_CIRCUIT_EMPTY.get()) {
            return RegBlocks.GOLD_CIRCUIT_EMPTY.get();
        }
        if(this == RegBlocks.DIAMOND_CIRCUIT_EMPTY.get()) {
            return RegBlocks.DIAMOND_CIRCUIT_EMPTY.get();
        }
        if(this == RegBlocks.NETHERITE_CIRCUIT_EMPTY.get()) {
            return RegBlocks.NETHERITE_CIRCUIT_EMPTY.get();
        }
        if(this == RegBlocks.PLATINUM_CIRCUIT_EMPTY.get()) {
            return RegBlocks.PLATINUM_CIRCUIT_EMPTY.get();
        }
        if(this == RegBlocks.PLATE_GOLDEN_JACKS_EMPTY.get()) {
            return RegBlocks.PLATE_GOLDEN_JACKS_EMPTY.get();
        }
        if(this == RegBlocks.PLATE_PLATINUM_JACKS_EMPTY.get()) {
            return RegBlocks.PLATE_PLATINUM_JACKS_EMPTY.get();
        }
        return this;
    }
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        player.addStat(Stats.BLOCK_MINED.get(this));
        player.addExhaustion(0.005F);
        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(getDrop(), 1));
    }
}
