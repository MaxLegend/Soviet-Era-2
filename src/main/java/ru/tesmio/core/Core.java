package ru.tesmio.core;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import ru.tesmio.reg.*;

@Mod(Core.MODID)
public class Core {

    public static final String MODID = "soviet";
  //  IReloadableResourceManager resMgr = (IReloadableResourceManager) Minecraft.getInstance().getResourceManager();
    public Core() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        //   resMgr.addReloadListener(AssetPreLoader.INSTANCE);
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);
        RegItems.register(eventBus);
        RegRecipeSerializers.RECIPE_SERIALIZERS.register(eventBus);
        RegBlocks.register(eventBus);
        RegEntitys.ENTITY_TYPES.register(eventBus);
        RegContainers.CONTAINER_TYPES.register(eventBus);
        RegTileEntitys.TILE_ENTITY_TYPES.register(eventBus);
        RegSounds.SOUNDS.register(eventBus);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
    }


    public static class ItemGroups {
        public static final ItemGroup TAB_MAIN = new ItemGroup("soviet_main") {
            @Override
            public ItemStack createIcon() {
                return new ItemStack(RegBlocks.CONCRETE_GRAY.get());
            }
        };
        public static final ItemGroup TAB_ITEMS = new ItemGroup("soviet_items") {
            @Override
            public ItemStack createIcon() {
                return new ItemStack(RegItems.ARMATURE.get());
            }
        };
        public static final ItemGroup TAB_INNER_DECO = new ItemGroup("soviet_inner_deco") {
            @Override
            public ItemStack createIcon() {
                return new ItemStack(RegBlocks.SAFE.get());
            }
        };
        public static final ItemGroup TAB_SYMBOLS = new ItemGroup("soviet_symbols") {
            @Override
            public ItemStack createIcon() {
                return new ItemStack(RegBlocks.ex_po.get());
            }
        };
        public static final ItemGroup TAB_OUTER_DECO = new ItemGroup("soviet_outer_deco") {
            @Override
            public ItemStack createIcon() {
                //temporary icon
                return new ItemStack(RegBlocks.CONCRETE_FENCE_BASE.get());
            }
        };

    }

    private void onCommonSetup(FMLCommonSetupEvent event) {

    }

    private void onClientSetup(FMLClientSetupEvent event) {
        ClientProxy.init();
    }
    private void enqueueIMC(final InterModEnqueueEvent event) {}

    private void processIMC(final InterModProcessEvent event) {}
    private void onDataSetup(GatherDataEvent event) {}
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {}
//    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
//    public static class RegistryEvents {
//        @SubscribeEvent
//        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {}
//    }
}
