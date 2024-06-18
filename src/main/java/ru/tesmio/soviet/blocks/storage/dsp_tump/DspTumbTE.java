package ru.tesmio.soviet.blocks.storage.dsp_tump;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import ru.tesmio.soviet.blocks.storage.base.TileEntityStorage;
import ru.tesmio.soviet.reg.RegTileEntitys;

public class DspTumbTE extends TileEntityStorage {
    //настроить положение слотов
    public DspTumbTE() {
        super(RegTileEntitys.DSP_TUMB_TE.get());
    }

    @Override
    public int getSizeInventory() {
        return 8;
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new DspTumbContainer(id, player, this);
    }
}
