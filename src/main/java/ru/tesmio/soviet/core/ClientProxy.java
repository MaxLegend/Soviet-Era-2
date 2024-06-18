package ru.tesmio.soviet.core;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import ru.tesmio.soviet.blocks.affinage_factory.AffinageScreen;
import ru.tesmio.soviet.blocks.crusher.CrusherScreen;
import ru.tesmio.soviet.blocks.decorative.props.stillage.StillageScreen;
import ru.tesmio.soviet.blocks.decorative.props.stillage.StillageTER;
import ru.tesmio.soviet.blocks.diesel_generator.DieselGeneratorScreen;
import ru.tesmio.soviet.blocks.storage.desc_drawers.LinearTableDrawersScreen;
import ru.tesmio.soviet.blocks.storage.dsp_tump.DspTumbScreen;
import ru.tesmio.soviet.blocks.storage.kitchen_table.KitchenTableScreen;
import ru.tesmio.soviet.blocks.storage.safe.ScreenSafe;
import ru.tesmio.soviet.entity.renderer.EntitySittableBlockRender;
import ru.tesmio.soviet.reg.*;

public class ClientProxy {
    public static void init() {
        MinecraftForge.EVENT_BUS.register(new GasMaskOverlay());
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
        RenderTypeLookup.setRenderLayer(RegBlocks.SIREN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(RegBlocks.INFO_TABLE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(RegBlocks.BLOCK_MOSS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(RegBlocks.BLOCK_MOULD.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(RegBlocks.ELECTRICAL_PANEL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(RegBlocks.BIO_STILLAGE.get(), RenderType.getTranslucent());

        RenderTypeLookup.setRenderLayer(RegFluids.TOXIC_WATER_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(RegFluids.TOXIC_WATER_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(RegFluids.TOXIC_WATER.get(), RenderType.getTranslucent());
    }

    private static void registerScreenFactories() {
        ScreenManager.registerFactory(RegContainers.DIESEL_CONTAINER.get(), DieselGeneratorScreen::new);
        ScreenManager.registerFactory(RegContainers.AFFINAGE_CONT.get(), AffinageScreen::new);
        ScreenManager.registerFactory(RegContainers.CRUSHER_CONT.get(), CrusherScreen::new);
        ScreenManager.registerFactory(RegContainers.STORAGE_CONT.get(), LinearTableDrawersScreen::new);
        ScreenManager.registerFactory(RegContainers.SAFE_CONT.get(), ScreenSafe::new);
        ScreenManager.registerFactory(RegContainers.DSP_TUMB_CONT.get(), DspTumbScreen::new);
        ScreenManager.registerFactory(RegContainers.KITCHEN_TABLE_CONT.get(), KitchenTableScreen::new);
        ScreenManager.registerFactory(RegContainers.STILLAGE_CONT.get(), StillageScreen::new);

    }

    private static void registerTileEntityRenderers() {
        ClientRegistry.bindTileEntityRenderer(RegTileEntitys.STILLAGE_TE.get(), StillageTER::new);
       // ClientRegistry.bindTileEntityRenderer(RegTileEntitys.TABLET_TE.get(), TabletTER::new);
    }

    private static void registerEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(RegEntitys.SEAT.get(), EntitySittableBlockRender::new);
    }
}