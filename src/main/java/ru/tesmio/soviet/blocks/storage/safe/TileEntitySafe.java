package ru.tesmio.soviet.blocks.storage.safe;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import ru.tesmio.soviet.blocks.storage.base.TileEntityStorage;
import ru.tesmio.soviet.reg.RegTileEntitys;

public class TileEntitySafe extends TileEntityStorage {
    public TileEntitySafe() {
        super(RegTileEntitys.SAFE_TE.get());

    }

    @Override
    public int getSizeInventory() {
        return 8;
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new ContainerSafe(id, player, this);
    }
}
