package ru.tesmio;

import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Core.MODID)
public class Core {

    public static final String MODID = "soviet";
    public Core() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        RegItems.register(eventBus);
        RegBlocks.register(eventBus);

    }
    public static class ItemGroups {

        public static final ItemGroup TAB_MAIN = new ItemGroup("soviet_main") {
            @Override
            public ItemStack createIcon() {
                return new ItemStack(RegBlocks.CONCRETE_BLUE.get());
            }
        };
        public static final ItemGroup TAB_ITEMS = new ItemGroup("soviet_items") {
            @Override
            public ItemStack createIcon() {
                return new ItemStack(RegItems.ARMATURE.get());
            }
        };
    }
    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

      //  InterModComms.sendTo("sovera", "helloworld", () -> {});
    }

    private void processIMC(final InterModProcessEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {

        }
    }
}
