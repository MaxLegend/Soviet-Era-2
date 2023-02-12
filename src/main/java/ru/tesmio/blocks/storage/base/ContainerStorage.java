package ru.tesmio.blocks.storage.base;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;

public class ContainerStorage extends Container {
    public int index = 0;
    public TileEntityStorage te;

    public ContainerStorage(int w, PlayerInventory p, TileEntityStorage te, ContainerType<?> type) {
        super(type, w);
        this.te = te;

        addSlots(te);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(p, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
            }
        }
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(p, col, 8 + col * 18, 142));
        }
    }

    public void addSlots(TileEntityStorage te) {}

    public static TileEntityStorage getTileEntity(PlayerInventory playerInv, PacketBuffer data) {
        TileEntity te = playerInv.player.world.getTileEntity(data.readBlockPos());
        if (te instanceof TileEntityStorage) {
            return (TileEntityStorage) te;
        }
        throw new IllegalStateException("Tile Entity Is Not Correct");
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return  this.te.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();
            if (index < te.getSizeInventory()
                    && !this.mergeItemStack(stack1, te.getSizeInventory(), this.inventorySlots.size(), true)) {
                return ItemStack.EMPTY;
            }
            if (!this.mergeItemStack(stack1, 0, te.getSizeInventory(), false)) {
                return ItemStack.EMPTY;
            }

            if (stack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return stack;
    }

}
