package ru.tesmio;

import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
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
import ru.tesmio.blocks.affinage_factory.AffinageScreen;
import ru.tesmio.blocks.crusher.CrusherScreen;
import ru.tesmio.blocks.diesel_generator.DieselGeneratorScreen;
import ru.tesmio.reg.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Core.MODID)
public class Core {

    public static final String MODID = "soviet";
  //  IReloadableResourceManager resMgr = (IReloadableResourceManager) Minecraft.getInstance().getResourceManager();
    public Core() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        //   resMgr.addReloadListener(AssetPreLoader.INSTANCE);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        RegItems.register(eventBus);
        RegRecipeSerializers.RECIPE_SERIALIZERS.register(eventBus);
        RegBlocks.register(eventBus);
        RegContainers.CONTAINER_TYPES.register(eventBus);
        RegTileEntitys.TILE_ENTITY_TYPES.register(eventBus);
        RegSounds.SOUNDS.register(eventBus);

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
        public static final ItemGroup TAB_INNER_DECO = new ItemGroup("soviet_inner_deco") {
            @Override
            public ItemStack createIcon() {
                return new ItemStack(RegBlocks.CRUSHER.get());
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
                return new ItemStack(RegBlocks.DIESEL_TANK.get());
            }
        };

    }
    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {

        event.enqueueWork(() -> {

            ScreenManager.registerFactory(RegContainers.DIESEL_CONTAINER.get(), DieselGeneratorScreen::new);
            ScreenManager.registerFactory(RegContainers.AFFINAGE_CONT.get(), AffinageScreen::new);
            ScreenManager.registerFactory(RegContainers.CRUSHER_CONT.get(), CrusherScreen::new);
            RenderTypeLookup.setRenderLayer(RegBlocks.AIRLOCK_DOOR.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegBlocks.ALUMINIUM_DOOR.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegBlocks.IRON_BEAM_THIN.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(RegBlocks.COPPER_CIRCUIT.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(RegBlocks.COPPER_CIRCUIT_EMPTY.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(RegBlocks.IRON_BEAM.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(RegBlocks.IRON_BEAM_CONCRETE.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(RegBlocks.CERAMIC_GLASS_BLUE.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegBlocks.CERAMIC_GLASS_GREEN.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegBlocks.CERAMIC_GLASS_BROWN.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegBlocks.MOTION_SENSOR.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegBlocks.BIOLAB_TABLE_CASE.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegBlocks.CHEMLAB_TABLE_CASE.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegBlocks.ALUM_FRAMES.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegBlocks.ALUM_WINDOW.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(RegBlocks.ALUM_WINDOW_EMPTY.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(RegBlocks.MODERN_WINDOW_EMPTY.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(RegBlocks.MODERN_WINDOW.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(RegBlocks.MODERN_WINDOW_LEAF_EMPTY.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(RegBlocks.MODERN_WINDOW_LEAF.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(RegBlocks.WOOD_WINDOW_EMPTY.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(RegBlocks.WOOD_WINDOW.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(RegBlocks.WOOD_WINDOW_LEAF_EMPTY.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(RegBlocks.WOOD_WINDOW_LEAF.get(), RenderType.getCutout());
        });
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
