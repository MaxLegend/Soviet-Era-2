package ru.tesmio.soviet.reg;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.tesmio.soviet.blocks.rasty_frame.texturable_block.TexturableModelLoader;
import ru.tesmio.soviet.core.Core;

@Mod.EventBusSubscriber(modid = Core.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegModels {
    @SubscribeEvent
    public static void init(final ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(new ResourceLocation(Core.MODID, "rasty_frame_loader"), new TexturableModelLoader());
    }
}
