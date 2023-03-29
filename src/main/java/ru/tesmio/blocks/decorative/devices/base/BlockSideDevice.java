package ru.tesmio.blocks.decorative.devices.base;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;

public class BlockSideDevice extends BlockSideCustomModel {

    public BlockSideDevice(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    public BlockSideDevice(float shadingInside) {
        super(shadingInside);
    }

    public boolean isCustomDrop() {
        return false;
    }
    @Override
    public ItemStack getStackAddDrop(PlayerEntity pl) {
        return new ItemStack(this, 1);
    }

}
