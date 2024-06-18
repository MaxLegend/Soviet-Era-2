package ru.tesmio.soviet.blocks.decorative.devices.base;

import ru.tesmio.soviet.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.soviet.blocks.decorative.devices.IRedstoneDevice;

public class BlockSideDevice extends BlockSideCustomModel implements IRedstoneDevice {

    public BlockSideDevice(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }

    public BlockSideDevice(float shadingInside) {
        super(shadingInside);
    }

    public boolean isCustomDrop() {
        return true;
    }

}
