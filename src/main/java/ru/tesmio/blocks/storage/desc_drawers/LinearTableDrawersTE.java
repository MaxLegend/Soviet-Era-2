package ru.tesmio.blocks.storage.desc_drawers;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import ru.tesmio.blocks.storage.base.TileEntityStorage;
import ru.tesmio.reg.RegTileEntitys;

public class LinearTableDrawersTE extends TileEntityStorage {
    public LinearTableDrawersTE() {
        super(RegTileEntitys.DRAWERS_STORAGE_TE.get());

    }
    @Override
    public int getSizeInventory() {
        return 16;
    }
    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new LinearTableDrawersContainer(id, player, this);
    }

}
