package ru.tesmio.soviet.blocks.storage.safe;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import ru.tesmio.soviet.blocks.storage.base.ContainerStorage;
import ru.tesmio.soviet.blocks.storage.base.TileEntityStorage;
import ru.tesmio.soviet.reg.RegContainers;

public class ContainerSafe extends ContainerStorage {
    public ContainerSafe(int w, PlayerInventory p, TileEntityStorage te) {
        super(w, p, te, RegContainers.SAFE_CONT.get());
    }

    public ContainerSafe(int windowId, PlayerInventory playerInv, PacketBuffer data) {
        this(windowId, playerInv, getTileEntity(playerInv, data));
    }

    @Override
    public void addSlots(TileEntityStorage te) {
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 4; col++) {
                this.addSlot(new Slot(te, index++, 52 + 18 * col, 22 + 18 * row));
            }
        }
    }

}
