package ru.tesmio.blocks.decorative.devices;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import ru.tesmio.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;

public class Accelerator extends BlockSideDevice {
    public Accelerator(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    public Accelerator(float shadingInside) {
        super(shadingInside);
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        if (this == RegBlocks.ACCELERATOR_RINGS.get() || this == RegBlocks.ACCELERATOR_RINGS_CORNER_LEFT.get()
                || this == RegBlocks.ACCELERATOR_RINGS_CORNER_RIGHT.get() || this == RegBlocks.ACCELERATOR_RINGS_END.get()) {
            return new ItemStack[]{
                    new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(10)),
                    new ItemStack(RegItems.ALUMINUM_SCRAP.get(), tr.nextInt(10)),
                    new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(5)),
                    new ItemStack(RegItems.LEAD_SCRAP.get(), tr.nextInt(7)),
            };
        }
        if (this == RegBlocks.ACCELERATOR.get()) {
            return new ItemStack[]{
                    new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(5)),
                    new ItemStack(RegItems.ALUMINUM_SCRAP.get(), tr.nextInt(5)),
                    new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(3)),
                    new ItemStack(RegItems.LEAD_SCRAP.get(), tr.nextInt(3)),
                    new ItemStack(RegBlocks.GOLD_CIRCUIT.get(), tr.nextInt(2)),
                    new ItemStack(RegBlocks.DIAMOND_CIRCUIT.get(), tr.nextInt(2)),
            };
        }
        if (this == RegBlocks.ACCELERATOR_STAND.get()) {
            return new ItemStack[]{
                    new ItemStack(RegItems.ARMATURES.get(), tr.nextInt(3)),
                    new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(2)),
                    new ItemStack(RegItems.LEAD_SCRAP.get(), tr.nextInt(4)),
            };
        }
        if (this == RegBlocks.ACCELERATOR_CALC_BLOCK.get()) {
            return new ItemStack[]{
                    new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(2)),
                    new ItemStack(RegItems.LEAD_SCRAP.get(), tr.nextInt(4)),
                    new ItemStack(RegBlocks.PLATINUM_CIRCUIT.get(), tr.nextInt(12)),
                    new ItemStack(RegBlocks.NETHERITE_CIRCUIT.get(), tr.nextInt(12)),
                    new ItemStack(RegBlocks.DIAMOND_CIRCUIT.get(), tr.nextInt(12)),
            };
        }
        return new ItemStack[]{
               ItemStack.EMPTY
        };
    }
}
