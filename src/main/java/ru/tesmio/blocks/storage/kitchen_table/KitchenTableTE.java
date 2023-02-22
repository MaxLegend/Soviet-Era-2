package ru.tesmio.blocks.storage.kitchen_table;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import ru.tesmio.blocks.storage.base.TileEntityStorage;
import ru.tesmio.reg.RegTileEntitys;

public class KitchenTableTE extends TileEntityStorage {
    public KitchenTableTE() {
        super(RegTileEntitys.KITCHEN_TABLE_TE.get());

    }
    //нарисовать инвентарь, расположить слоты
    @Override
    public int getSizeInventory() {
        return 12;
    }
    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new KitchenTableContainer(id, player, this);
    }
}
