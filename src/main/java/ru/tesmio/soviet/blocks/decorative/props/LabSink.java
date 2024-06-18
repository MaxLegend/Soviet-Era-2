package ru.tesmio.soviet.blocks.decorative.props;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import ru.tesmio.soviet.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.soviet.reg.RegItems;

import java.util.concurrent.ThreadLocalRandom;

public class LabSink extends BlockSideDevice {
    public LabSink(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }

    ThreadLocalRandom r = ThreadLocalRandom.current();

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }

    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        ItemStack is = new ItemStack(RegItems.ALUMINUM_SCRAP.get(), r.nextInt(2, 4));
        return new ItemStack[]{
                is
        };
    }
}
