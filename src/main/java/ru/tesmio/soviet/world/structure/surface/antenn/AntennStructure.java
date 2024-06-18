package ru.tesmio.soviet.world.structure.surface.antenn;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class AntennStructure extends Structure<NoFeatureConfig> {
    public AntennStructure() {
        super(NoFeatureConfig.CODEC);
    }
    @Override
    public GenerationStage.Decoration getDecorationStage() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }
    @Override //isFeatureChunk
    protected boolean func_230363_a_(ChunkGenerator cg, BiomeProvider bp, long seed, SharedSeedRandom ssr, int chunkX, int chunkZ, Biome b, ChunkPos cPos, NoFeatureConfig cfg) {
        return true;
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return AntennStructure.Start::new;
    }

    public static class Start extends StructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }
        @Override //generatePieces
        public void func_230364_a_(DynamicRegistries drm, ChunkGenerator cg, TemplateManager tm, int chunkX, int chunkZ, Biome b, NoFeatureConfig cfg) {
            this.generatePieces(drm,cg,tm,chunkX,chunkZ,b,cfg);
        }

        public void generatePieces(DynamicRegistries drm, ChunkGenerator cg, TemplateManager tm, int chunkX, int chunkZ, Biome b, NoFeatureConfig cfg) {

           if(cg.getBiomeProvider().getNoiseBiome(chunkX, 0, chunkZ).getRegistryName().equals(Biomes.MOUNTAINS.getLocation()) ||
                   cg.getBiomeProvider().getNoiseBiome(chunkX, 0, chunkZ).getRegistryName().equals(Biomes.FOREST.getLocation())) {
                int x = chunkX * 16;
                int z = chunkZ * 16;
                BlockPos blockpos = new BlockPos(x, 0, z);
                AntennPieces.addPieces(tm, blockpos, this.components);
                this.recalculateStructureSize();
            }

        }


    }

}
