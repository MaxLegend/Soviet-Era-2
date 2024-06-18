package ru.tesmio.soviet.blocks.decorative.props;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import ru.tesmio.soviet.blocks.baseblock.BlockRotatedAxisCustomModel;
import ru.tesmio.soviet.reg.RegBlocks;
import ru.tesmio.soviet.reg.RegItems;

import java.util.concurrent.ThreadLocalRandom;

public class TubingBlock extends BlockRotatedAxisCustomModel {
    ThreadLocalRandom tr = ThreadLocalRandom.current();

    public TubingBlock(Properties p, float shadingInside) {
        super(p, shadingInside);
    }

    public ItemStack[] getItemsDrop(PlayerEntity pl) {

        if (this == RegBlocks.TUBING_HORIZONTAL.get()) {
            return new ItemStack[]{
                    new ItemStack(RegItems.ARMATURES.get(), tr.nextInt(4, 6)),
                    new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(3, 8)),
            };
        } else if (this == RegBlocks.TUBING_VERTICAL.get()) {
            return new ItemStack[]{
                    new ItemStack(RegItems.ARMATURES.get(), tr.nextInt(4, 6)),
                    new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(3, 8)),
            };
        } else return new ItemStack[]{
                ItemStack.EMPTY
        };
    }
}
