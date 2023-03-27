package ru.tesmio.blocks.decorative.lamp.base;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import ru.tesmio.blocks.baseblock.BlockRotatedAxisCustomModel;
import ru.tesmio.reg.RegItems;

import java.util.concurrent.ThreadLocalRandom;

public class BlockRotLamp extends BlockRotatedAxisCustomModel {
    public BlockRotLamp(Properties p) {
        super(p, 1F);
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        return new ItemStack[] {
                new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(1)),
                new ItemStack(RegItems.ALUMINUM_SCRAP.get(), tr.nextInt(1)),
        };
    }
}
