package ru.tesmio.soviet.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import ru.tesmio.soviet.core.Core;

import java.util.function.Predicate;

public class NetworkHandler {
    public static final String NETWORK_VERSION = "0.1.0";

    private static final ResourceLocation CHANNEL_NAME = new ResourceLocation(Core.MODID, "network");
    private static final Predicate<String> ACCEPT_VERSION = NetworkRegistry.acceptMissingOr(NETWORK_VERSION);

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(CHANNEL_NAME, () -> NETWORK_VERSION, ACCEPT_VERSION, ACCEPT_VERSION);

    public static void init() {
        // CHANNEL.registerMessage(0, PacketTabletUpdate.class, PacketTabletUpdate::encode, PacketTabletUpdate::decode, PacketTabletUpdate::handle);
    }
}
