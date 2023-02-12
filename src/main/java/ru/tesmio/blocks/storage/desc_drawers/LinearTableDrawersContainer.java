package ru.tesmio.blocks.storage.desc_drawers;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import ru.tesmio.blocks.storage.base.ContainerStorage;
import ru.tesmio.blocks.storage.base.TileEntityStorage;
import ru.tesmio.reg.RegContainers;

public class LinearTableDrawersContainer extends ContainerStorage {
    public LinearTableDrawersContainer(int w, PlayerInventory p, TileEntityStorage te) {
        super(w, p, te, RegContainers.STORAGE_CONT.get());
    }
    public LinearTableDrawersContainer(int windowId, PlayerInventory playerInv, PacketBuffer data) {
        this(windowId, playerInv, getTileEntity(playerInv, data));
    }
    public void addSlots(TileEntityStorage te) {
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 4; col++) {
                this.addSlot(new Slot(te, index++, 14 + 18* col, 18 + 18*row));
            }
        }
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 4; col++) {
                this.addSlot(new Slot(te, index++, 93 + 18* col, 18 + 18*row));
            }
        }
    }
}
