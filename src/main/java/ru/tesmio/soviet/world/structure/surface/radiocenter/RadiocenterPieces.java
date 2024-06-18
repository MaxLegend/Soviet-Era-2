package ru.tesmio.soviet.world.structure.surface.radiocenter;

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

public class RadiocenterPieces {

    private static final ResourceLocation PART_CONTROL = new ResourceLocation("soviet:radiocenter/part_control");
    private static final ResourceLocation PART_CHECKPOINT = new ResourceLocation("soviet:radiocenter/part_cp");
    private static final ResourceLocation PART_ANTENN = new ResourceLocation("soviet:radiocenter/part_antenn");
    private static final ResourceLocation PART_TUNNEL = new ResourceLocation("soviet:proc_lab/ventilation_mid");

    static IStructurePieceType RADIOCENTER_PIECES = IStructurePieceType.register(RadiocenterPieces.Piece::new, "soviet:radiocenter");
    public static final Map<ResourceLocation, BlockPos> POOL = new HashMap<>();

    public static void putValueInMap() {
        POOL.put(PART_CONTROL, BlockPos.ZERO);
        POOL.put(PART_CHECKPOINT, BlockPos.ZERO);
        POOL.put(PART_ANTENN, BlockPos.ZERO);
        POOL.put(PART_TUNNEL, BlockPos.ZERO);
    }
    public static void addPieces(TemplateManager tm, BlockPos bPos, List<StructurePiece> l) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        generateCheckpoint(tm, bPos, l);
        l.add(new RadiocenterPieces.Piece(tm, PART_TUNNEL, bPos.east(2), Rotation.NONE, Mirror.NONE));

        generateControl(tm, bPos, l);
        generateAntenn(tm, bPos.south(6).up(), l);
        generateAntenn(tm, bPos.east(7).south(6).up(), l);
    }
    public static void generateCheckpoint(TemplateManager tm, BlockPos bPos, List<StructurePiece> l) {
        l.add(new RadiocenterPieces.Piece(tm, PART_CHECKPOINT, bPos, Rotation.NONE, Mirror.NONE));
    }
    public static void generateControl(TemplateManager tm, BlockPos bPos, List<StructurePiece> l) {
        l.add(new RadiocenterPieces.Piece(tm, PART_CONTROL, bPos, Rotation.NONE, Mirror.NONE));
    }
    public static void generateAntenn(TemplateManager tm, BlockPos bPos, List<StructurePiece> l) {
        l.add(new RadiocenterPieces.Piece(tm, PART_ANTENN, bPos, Rotation.NONE, Mirror.NONE));
    }

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation location;
        private final Rotation rot;
        private final Mirror mir;

        public Piece(TemplateManager tm, ResourceLocation rs, BlockPos bp, Rotation rot, Mirror mir) {
            super(RADIOCENTER_PIECES, 0);
            putValueInMap();
            this.location = rs;
            this.rot = rot;
            this.mir = mir;
            this.templatePosition = bp;
            this.tempMngSetup(tm);

        }

        public Piece(TemplateManager tm, CompoundNBT nbt) {
            super(RADIOCENTER_PIECES, nbt);
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
                    .setCenterOffset(RadiocenterPieces.POOL.get(this.location))
                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);


            BlockPos blockpos = RadiocenterPieces.POOL.get(this.location);
            if(blockpos == null) return false;
            BlockPos blockpos1 = this.templatePosition.add(
                    Template.transformedBlockPos(ps, new BlockPos(blockpos.getX(), 0, blockpos.getZ())));
            int i = sr.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
            BlockPos blockpos2;
            blockpos2 = this.templatePosition;
            boolean flag = false;
            if(this.location == PART_TUNNEL) {
                i = i -5;
            }
            if(this.location == PART_CONTROL) {
                i = i -10;
            }
                this.templatePosition = this.templatePosition.add(0, i-1, 0); // y = -170 / -40 test
                flag = super.func_230383_a_(sr, sm, cg, r, mbb, cp, bp);

            return flag;
        }

    }

}
