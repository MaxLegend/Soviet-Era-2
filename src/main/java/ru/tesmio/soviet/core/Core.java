package ru.tesmio.soviet.core;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import ru.tesmio.soviet.event.EventAdvancement;
import ru.tesmio.soviet.network.NetworkHandler;
import ru.tesmio.soviet.reg.*;
import ru.tesmio.soviet.utils.BlockSavingHelper;

@Mod(Core.MODID)
public class Core {

    public static final String MODID = "soviet";
  //  IReloadableResourceManager resMgr = (IReloadableResourceManager) Minecraft.getInstance().getResourceManager();
    // public static final DiscoveryBlockTrigger DISCOVERY_BLOCK_TRIGGER = CriteriaTriggers.register(new DiscoveryBlockTrigger());
    public Core() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        //   resMgr.addReloadListener(AssetPreLoader.INSTANCE);
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
   //     ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> "", (x, y) -> true));
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new EventAdvancement());
        RegItems.register(eventBus);
        RegRecipeSerializers.RECIPE_SERIALIZERS.register(eventBus);
        RegBlocks.register(eventBus);
        RegFluids.register(eventBus);

        RegEntitys.ENTITY_TYPES.register(eventBus);
        RegContainers.CONTAINER_TYPES.register(eventBus);
        RegTileEntitys.TILE_ENTITY_TYPES.register(eventBus);
        SovietSounds.SOUNDS.register(eventBus);
        RegStructures.register(eventBus);
 //       MinecraftForge.EVENT_BUS.register(new RegModifiers());
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC, "soviet_era2_config.toml");
     //   CriteriaTriggers.register(DiscoveryBlockTrigger.INSTANCE);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup); //возможны проблемы переместить выше!
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
        NetworkHandler.init();
        RegStructures.setupStructures();
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        ClientProxy.init();
    }
    private void enqueueIMC(final InterModEnqueueEvent event) {}

    private void processIMC(final InterModProcessEvent event) {
        BlockSavingHelper.createValidBlockList();
    }
    private void onDataSetup(GatherDataEvent event) {}
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }

//    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
//    public static class RegistryEvents {
//        @SubscribeEvent
//        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {}
//    }
}
