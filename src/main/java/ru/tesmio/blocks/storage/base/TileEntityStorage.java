package ru.tesmio.blocks.storage.base;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import ru.tesmio.core.Core;
import ru.tesmio.reg.RegContainers;

public class TileEntityStorage extends LockableLootTileEntity {

    public TileEntityType<?> type;
    public int slots;

    protected NonNullList<ItemStack> inventory;

    protected TileEntityStorage(TileEntityType<?> typeIn) {
        super(typeIn);
        this.inventory = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);

    }

    @Override
    public int getSizeInventory() {
        return slots;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.inventory = itemsIn;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + Core.MODID + ".table");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new ContainerStorage(id, player, this, RegContainers.STORAGE_CONT.get());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if(!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.inventory);
        }
        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.inventory = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
        if (!this.checkLootAndRead(nbt)) {
            ItemStackHelper.loadAllItems(nbt, this.inventory);
        }
    }
}