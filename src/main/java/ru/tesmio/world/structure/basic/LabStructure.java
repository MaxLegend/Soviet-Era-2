package ru.tesmio.world.structure.basic;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;
import ru.tesmio.core.Core;

public class LabStructure extends Structure<NoFeatureConfig> {
    public LabStructure() {
        super(NoFeatureConfig.CODEC);
    }
    @Override
    public GenerationStage.Decoration getDecorationStage() {
        return GenerationStage.Decoration.UNDERGROUND_STRUCTURES;
    }

    @Override //isFeatureChunk
    protected boolean func_230363_a_(ChunkGenerator cg, BiomeProvider bp, long seed, SharedSeedRandom ssr, int chunkX, int chunkZ, Biome b, ChunkPos cPos, NoFeatureConfig cfg) {
        return true;
    }


    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return LabStructure.Start::new;
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
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;
            BlockPos pos = new BlockPos(x, 0, z);
            JigsawManager.func_242837_a(drm,
                    new VillageConfig(() -> drm.getRegistry(Registry.JIGSAW_POOL_KEY)
                            .getOrDefault(new ResourceLocation(Core.MODID, "lab/start_pool")),
                            10), AbstractVillagePiece::new, cg, tm, pos, this.components, this.rand,false,true);

            this.components.forEach(piece -> piece.offset(0, 1, 0));
            this.components.forEach(piece -> piece.getBoundingBox().minY -= 1);

            this.recalculateStructureSize();
        }
    }
}
