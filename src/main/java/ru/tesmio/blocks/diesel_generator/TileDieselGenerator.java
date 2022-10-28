package ru.tesmio.blocks.diesel_generator;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.network.PacketDistributor;
import ru.tesmio.Core;
import ru.tesmio.blocks.api.SEEnergyStorage;
import ru.tesmio.packet.EnergyPacket;
import ru.tesmio.packet.PacketHandler;
import ru.tesmio.reg.RegTileEntitys;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;

public class TileDieselGenerator extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergy);
    private int energyGeneration, maxEnergyOutput;
    public int maxEnergy;
    public int energyClient, energyProductionClient;
    public TileDieselGenerator() {
        this(RegTileEntitys.DIESEL_TE.get());
    }
    public TileDieselGenerator(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        energyGeneration = (int) Math.pow(8, 4);
        maxEnergyOutput = energyGeneration * 2;
        maxEnergy = energyGeneration * 1000;
        energyClient = energyProductionClient = -1;
    }

    private IEnergyStorage createEnergy() {
        return new SEEnergyStorage(maxEnergyOutput, maxEnergy);
    }
    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container." + Core.MODID + ".diesel");
    }

    @Nullable
    @Override
    public Container createMenu(final int windowID, final PlayerInventory playerInv, final PlayerEntity playerIn) {
        return new ContainerDieselGenerator(windowID, playerInv, this);
    }

    @Override
    public void tick() {
        if(world.isRemote) {
            energy.ifPresent(e -> ((SEEnergyStorage) e).generatePower(currentAmountEnergyProduced()));
            sendEnergy();
            if(energyClient != getEnergy() || energyProductionClient != currentAmountEnergyProduced()) {
                int energyProduced = (getEnergy() != getMaxEnergy()) ? currentAmountEnergyProduced() : 0;
                PacketHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new EnergyPacket(getPos(), getEnergy(), energyProduced));
            }
        }
    }
    private int getMaxEnergy() {
        return getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getMaxEnergyStored).orElse(0);
    }

    private int getEnergy() {
        return getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }

    private int currentAmountEnergyProduced() {
        return energyGeneration * 10;
    }
    private void sendEnergy() {
        energy.ifPresent(energy -> {
            AtomicInteger capacity = new AtomicInteger(energy.getEnergyStored());

            for(int i = 0; (i < Direction.values().length) && (capacity.get() > 0); i++)
            {
                Direction facing = Direction.values()[i];

                    TileEntity tileEntity = world.getTileEntity(getPos());

                    if(tileEntity != null)
                    {
                        tileEntity.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite()).ifPresent(handler -> {
                            if(handler.canReceive())
                            {
                                int received = handler.receiveEnergy(Math.min(capacity.get(), maxEnergyOutput), false);
                                capacity.addAndGet(-received);
                                ((SEEnergyStorage) energy).consumePower(received);
                            }
                        });
                    }

            }
        });
    }

    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, Direction facing)
    {
        if(capability == CapabilityEnergy.ENERGY)
        {
            return energy.cast();
        }
        return super.getCapability(capability, facing);
    }



    @SuppressWarnings("unchecked")
    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        CompoundNBT energyTag = compound.getCompound("diesel");
        energy.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(energyTag));
        return super.write(compound);
    }
    @SuppressWarnings("unchecked")
    @Override
    public void read(BlockState state, CompoundNBT compound)
    {
        CompoundNBT energyTag = compound.getCompound("diesel");

        this.energy.ifPresent(h -> {
            ((INBTSerializable<CompoundNBT>)h).deserializeNBT(energyTag);

        });
        super.read(state, compound);
    }
}
