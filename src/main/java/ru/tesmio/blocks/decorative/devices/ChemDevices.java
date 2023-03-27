package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import ru.tesmio.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;

import java.util.concurrent.ThreadLocalRandom;

public class ChemDevices extends BlockSideDevice {
    public ChemDevices(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        if(this == RegBlocks.MIXER.get()) {
            return new ItemStack[] {
                    new ItemStack(RegBlocks.COPPER_CIRCUIT.get() , tr.nextInt(1,4)),
                    new ItemStack(RegBlocks.SILVER_CIRCUIT.get() , tr.nextInt(1,3)),
                    new ItemStack(RegBlocks.GOLD_CIRCUIT.get() , tr.nextInt(2))
            };
        }
        if(this == RegBlocks.MAGNET_MIXER.get() || this == RegBlocks.LAB_STOVE.get()) {
            return new ItemStack[] {
                    new ItemStack(RegBlocks.COPPER_CIRCUIT.get() , tr.nextInt(1,2)),
            };
        } else
        return new ItemStack[] {
                new ItemStack(RegItems.WOOD_SCRAP.get(), 1)
        };
    }
    @Override
    public VoxelShape getFacingShape(BlockState s) {
        VoxelShape SHP[] = new VoxelShape[]{
                Block.makeCuboidShape(2.5, 0, 4, 13.5, 3, 12),
                Block.makeCuboidShape(1, 0, 3.5, 15, 7, 12.5),
                Block.makeCuboidShape(2.5, 0, 2.5, 13.5, 3, 13.5),
        };
        if(s.getBlock() == RegBlocks.MAGNET_MIXER.get()) {
            return SHP[0];
        }
        if(s.getBlock() == RegBlocks.MIXER.get()) {
            return SHP[1];
        }
        if(s.getBlock() == RegBlocks.LAB_STOVE.get()) {
            return SHP[2];
        }
        return VoxelShapes.fullCube();
    }
}
