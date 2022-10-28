package ru.tesmio.packet;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import ru.tesmio.Core;

import java.util.Optional;

public class PacketHandler {

    private static final String PROTOCOL_VERSION = "1";
    public static SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Core.MODID, "ru"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals);

    public static void init()
    {
        int id = 0;
        INSTANCE.registerMessage(id++, EnergyPacket.class, EnergyPacket::toBytes, EnergyPacket::new, EnergyPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
    };
}
