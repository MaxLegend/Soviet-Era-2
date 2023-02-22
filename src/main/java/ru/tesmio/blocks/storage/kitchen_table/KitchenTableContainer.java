package ru.tesmio.blocks.storage.kitchen_table;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import ru.tesmio.blocks.storage.base.ContainerStorage;
import ru.tesmio.blocks.storage.base.TileEntityStorage;
import ru.tesmio.reg.RegContainers;

public class KitchenTableContainer extends ContainerStorage {
    public KitchenTableContainer(int w, PlayerInventory p, TileEntityStorage te) {
        super(w, p, te, RegContainers.KITCHEN_TABLE_CONT.get());
    }
    public KitchenTableContainer(int windowId, PlayerInventory playerInv, PacketBuffer data) {
        this(windowId, playerInv, getTileEntity(playerInv, data));
    }
    @Override
    public void addSlots(TileEntityStorage te) {
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 2; col++) {
                this.addSlot(new Slot(te, index++, 44 + 18* col, 38 + 18*row));
            }
        }
        this.addSlot(new Slot(te, index++, 44, 12));
        this.addSlot(new Slot(te, index++, 62, 12 ));
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 2; col++) {
                this.addSlot(new Slot(te, index++, 99 + 18* col, 38 + 18*row));
            }
        }
        this.addSlot(new Slot(te, index++, 99, 12));
        this.addSlot(new Slot(te, index++, 117, 12 ));
    }

}

