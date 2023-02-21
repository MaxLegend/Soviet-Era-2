package ru.tesmio.blocks.storage.dsp_tump;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import ru.tesmio.blocks.storage.base.ContainerStorage;
import ru.tesmio.blocks.storage.base.TileEntityStorage;
import ru.tesmio.reg.RegContainers;

public class DspTumbContainer extends ContainerStorage {
    public DspTumbContainer(int w, PlayerInventory p, TileEntityStorage te) {
        super(w, p, te, RegContainers.DSP_TUMB_CONT.get());
    }
    public DspTumbContainer(int windowId, PlayerInventory playerInv, PacketBuffer data) {
        this(windowId, playerInv, getTileEntity(playerInv, data));
    }
    @Override
    public void addSlots(TileEntityStorage te) {
        for (int col = 0; col < 4; col++) {
                this.addSlot(new Slot(te, index++, 52 + 18* col, 18));
        }

        for (int col = 0; col < 4; col++) {
            this.addSlot(new Slot(te, index++, 52 + 18* col, 49));
        }
    }

}