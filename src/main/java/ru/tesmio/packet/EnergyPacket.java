package ru.tesmio.packet;

import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;
import ru.tesmio.Core;
import ru.tesmio.blocks.diesel_generator.TileDieselGenerator;

import java.util.function.Supplier;

public class EnergyPacket {

    private BlockPos pos;
    private int currentEnergy;
    private int currentProduction;

    public EnergyPacket(PacketBuffer buf)
    {
        pos = buf.readBlockPos();
        currentEnergy = buf.readInt();
        currentProduction = buf.readInt();
    }

    public EnergyPacket(BlockPos pos, int currentEnergy, int currentProduction)
    {
        this.pos = pos;
        this.currentEnergy = currentEnergy;
        this.currentProduction = currentProduction;
    }

    public void toBytes(PacketBuffer buf)
    {
        buf.writeBlockPos(pos);
        buf.writeInt(currentEnergy);
        buf.writeInt(currentProduction);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            World world = Core.proxy.getClientWorld();
            if(world.isAreaLoaded(this.pos, this.pos))
            {
                TileEntity te = world.getTileEntity(pos);
                if(te instanceof TileDieselGenerator)
                {
                    TileDieselGenerator solar = (TileDieselGenerator) te;
                    solar.energyClient = currentEnergy;
                    solar.energyProductionClient = currentProduction;
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
