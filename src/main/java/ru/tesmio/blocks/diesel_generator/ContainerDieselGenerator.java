package ru.tesmio.blocks.diesel_generator;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import ru.tesmio.reg.RegContainers;

import java.util.Objects;

public class ContainerDieselGenerator extends Container {

    public final TileDieselGenerator tile;
    private final PlayerEntity player;

    public ContainerDieselGenerator(int windowId, final PlayerInventory playerInv, TileDieselGenerator tile)
    {
        super(RegContainers.DIESEL_CONT.get(), windowId);
        this.tile = tile;
        this.player = playerInv.player;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn)
    {
        return isWithinUsableDistance(IWorldPosCallable.of(tile.getWorld(), tile.getPos()), playerIn, tile.getBlockState().getBlock());
    }
    public ContainerDieselGenerator(final int windowID, final PlayerInventory playerInv, final PacketBuffer data) {
        this(windowID, playerInv, getTileEntity(playerInv, data));
    }
    private static TileDieselGenerator getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
        Objects.requireNonNull(playerInv, "playerInv cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final TileEntity tileAtPos = playerInv.player.world.getTileEntity(data.readBlockPos());
        if (tileAtPos instanceof TileDieselGenerator) {
            return (TileDieselGenerator) tileAtPos;
        }
        throw new IllegalStateException("TileEntity is not correct " + tileAtPos);
    }
}