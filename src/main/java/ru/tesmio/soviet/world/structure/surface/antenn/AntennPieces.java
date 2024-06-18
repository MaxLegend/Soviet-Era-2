package ru.tesmio.soviet.world.structure.surface.antenn;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class AntennPieces {

    private static final ResourceLocation ANTENN = new ResourceLocation("soviet:antenn");
    static IStructurePieceType ANTENN_PIECES = IStructurePieceType.register(AntennPieces.Piece::new, "soviet:antenn");
    public static final Map<ResourceLocation, BlockPos> POOL = new HashMap<>();

    public static void putValueInMap() {
        POOL.put(ANTENN, BlockPos.ZERO);
    }
    public static void addPieces(TemplateManager tm, BlockPos bPos, List<StructurePiece> l) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        generateAntenn(tm, bPos, l);
    }
    public static void generateAntenn(TemplateManager tm, BlockPos bPos, List<StructurePiece> l) {
        l.add(new AntennPieces.Piece(tm, ANTENN, bPos, Rotation.NONE, Mirror.NONE));
    }


    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation location;
        private final Rotation rot;
        private final Mirror mir;

        public Piece(TemplateManager tm, ResourceLocation rs, BlockPos bp, Rotation rot, Mirror mir) {
            super(ANTENN_PIECES, 0);
            putValueInMap();
            this.location = rs;
            this.rot = rot;
            this.mir = mir;
            this.templatePosition = bp;
            this.tempMngSetup(tm);

        }

        public Piece(TemplateManager tm, CompoundNBT nbt) {
            super(ANTENN_PIECES, nbt);
            this.location = new ResourceLocation(nbt.getString("Template"));
            this.rot = Rotation.NONE;
            this.mir = Mirror.NONE;
            this.tempMngSetup(tm);
        }

        public BlockPos getPosition(ResourceLocation rs) {
            return POOL.get(rs);
        }
        public Mirror getMirror() {
            return mir;
        }

        public Rotation getRotate() {
            return rot;
        }

        public ResourceLocation getLocation() {
            return location;
        }

        public BlockPos getTemplatePosition() {
            return templatePosition;
        }

        private void tempMngSetup(TemplateManager tm) {
            Template template = tm.getTemplateDefaulted(this.location);
            PlacementSettings placementsettings = (
                    new PlacementSettings())
                    .setRotation(this.rot)
                    .setMirror(this.mir);
            this.setup(template, this.templatePosition, placementsettings);
        }

        protected void handleDataMarker(String function, BlockPos pos, IServerWorld worldIn, Random rand, MutableBoundingBox sbb) {}
        public boolean func_230383_a_(ISeedReader sr, StructureManager sm, ChunkGenerator cg, Random r, MutableBoundingBox mbb, ChunkPos cp, BlockPos bp) {
            PlacementSettings ps = (
                    new PlacementSettings())
                    .setRotation(Rotation.NONE)
                    .setMirror(Mirror.NONE)
                    .setCenterOffset(AntennPieces.POOL.get(this.location))
                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);

            BlockPos blockpos = AntennPieces.POOL.get(this.location);
            if(blockpos == null) return false;
            BlockPos blockpos1 = this.templatePosition.add(
                    Template.transformedBlockPos(ps, new BlockPos(blockpos.getX(), 0, blockpos.getZ())));
            int i = sr.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
            BlockPos blockpos2;
            blockpos2 = this.templatePosition;
            boolean flag = false;

            this.templatePosition = this.templatePosition.add(0, i - 10, 0); // y = -170 / -40 test
          //  System.out.println("templatePosition " + this.templatePosition.add(0, i, 0));
            flag = super.func_230383_a_(sr, sm, cg, r, mbb, cp, bp);
          return flag;
        }

    }

}
