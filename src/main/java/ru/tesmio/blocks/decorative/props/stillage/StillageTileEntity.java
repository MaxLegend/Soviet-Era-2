package ru.tesmio.blocks.decorative.props.stillage;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import ru.tesmio.core.Core;
import ru.tesmio.reg.RegTileEntitys;

import javax.annotation.Nullable;

public class StillageTileEntity extends LockableLootTileEntity {

    public static int slots = 1;


    protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);


    protected StillageTileEntity(TileEntityType<?> typeIn) {
        super(typeIn);
    }

    public StillageTileEntity() {
        this(RegTileEntitys.STILLAGE_TE.get());
    }

    @Override
    public int getSizeInventory() {
        return slots;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }


    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        write(nbtTagCompound);
        int tileEntityType = 42;  // arbitrary number; only used for vanilla TileEntities.  You can use it, or not, as you want.
        return new SUpdateTileEntityPacket(this.pos, tileEntityType, nbtTagCompound);
    }
    @Override
    public CompoundNBT getUpdateTag() {
        return write(new CompoundNBT());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        //  super.handleUpdateTag(state, tag);
        read(state, tag);
    }

    @Override
    public void onDataPacket(NetworkManager networkManager, SUpdateTileEntityPacket updatePacket) {
        CompoundNBT compound = updatePacket.getNbtCompound();
        BlockState state = this.world.getBlockState(this.pos);
        this.handleUpdateTag(state, updatePacket.getNbtCompound());
        this.world.notifyBlockUpdate(this.pos, state, state, 3);

        ItemStackHelper.loadAllItems(compound, this.items);
    }
    public ItemStack getItem() {
        return this.items.get(0);
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.items = itemsIn;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + Core.MODID + ".stillage_name");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new StillageContainer(id, player, this);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if(!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.items);
        }
        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.items = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);

        if (!this.checkLootAndRead(nbt)) {
            ItemStackHelper.loadAllItems(nbt, this.items);
            getUpdatePacket();
            //   handleUpdateTag(state, nbt);
        }
    }
}
