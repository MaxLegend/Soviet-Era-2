package ru.tesmio.soviet.world.structure;

import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.tesmio.soviet.core.Core;
import ru.tesmio.soviet.reg.RegStructures;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = Core.MODID)
public class WorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        StructureGeneration.generateStructures(event);
    }
    @SubscribeEvent
    public static void addDimensionalSpacing(final WorldEvent.Load event) {
        if(event.getWorld() instanceof ServerWorld) {

            ServerWorld serverWorld = (ServerWorld) event.getWorld();
            ChunkGenerator scp = serverWorld.getChunkProvider().generator;
            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(scp.func_235957_b_().func_236195_a_());
            tempMap.putIfAbsent(RegStructures.PROCEDURAL_LABORATORY.get(), DimensionStructuresSettings.field_236191_b_.get(RegStructures.PROCEDURAL_LABORATORY.get()));
            tempMap.putIfAbsent(RegStructures.ANTENN.get(), DimensionStructuresSettings.field_236191_b_.get(RegStructures.PROCEDURAL_LABORATORY.get()));
            tempMap.putIfAbsent(RegStructures.RADIOCENTER.get(), DimensionStructuresSettings.field_236191_b_.get(RegStructures.RADIOCENTER.get()));
            tempMap.putIfAbsent(RegStructures.VAULT.get(), DimensionStructuresSettings.field_236191_b_.get(RegStructures.VAULT.get()));
            scp.func_235957_b_().field_236193_d_ = tempMap;

        }
    }
}
