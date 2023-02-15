package ru.tesmio.core;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import ru.tesmio.blocks.affinage_factory.AffinageScreen;
import ru.tesmio.blocks.crusher.CrusherScreen;
import ru.tesmio.blocks.diesel_generator.DieselGeneratorScreen;
import ru.tesmio.blocks.storage.desc_drawers.LinearTableDrawersScreen;
import ru.tesmio.blocks.storage.safe.ScreenSafe;
import ru.tesmio.entity.renderer.EntitySittableBlockRender;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegContainers;
import ru.tesmio.reg.RegEntitys;

public class ClientProxy {
    public static void init() {
        registerScreenFactories();
        registerEntityRenderers();
        registerTileEntityRenderers();
        registerRenderLayers();
    }
    private static void registerRenderLayers() {
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
        RenderTypeLookup.setRenderLayer(RegBlocks.WOOD_DOOR_2.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(RegBlocks.INC_LAMP.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(RegBlocks.RED_LAMP.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(RegBlocks.FACTORY_WINDOW.get(), RenderType.getTranslucent());
    }
    private static void registerScreenFactories() {
        ScreenManager.registerFactory(RegContainers.DIESEL_CONTAINER.get(), DieselGeneratorScreen::new);
        ScreenManager.registerFactory(RegContainers.AFFINAGE_CONT.get(), AffinageScreen::new);
        ScreenManager.registerFactory(RegContainers.CRUSHER_CONT.get(), CrusherScreen::new);
        ScreenManager.registerFactory(RegContainers.STORAGE_CONT.get(), LinearTableDrawersScreen::new);
        ScreenManager.registerFactory(RegContainers.SAFE_CONT.get(), ScreenSafe::new);
    }
    private static void registerTileEntityRenderers() {

    }
    private static void registerEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(RegEntitys.SEAT.get(), EntitySittableBlockRender::new);
    }
}
