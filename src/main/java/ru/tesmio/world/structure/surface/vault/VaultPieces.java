package ru.tesmio.world.structure.surface.vault;

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

public class VaultPieces {

    private static final ResourceLocation PART_STAIR = new ResourceLocation("soviet:vault/part_stair");
    private static final ResourceLocation PART_UG1 = new ResourceLocation("soviet:vault/part_ug1");
    private static final ResourceLocation PART_UG2 = new ResourceLocation("soviet:vault/part_ug2");

    static IStructurePieceType VAULT_PIECES = IStructurePieceType.register(VaultPieces.Piece::new, "soviet:vault");
    public static final Map<ResourceLocation, BlockPos> POOL = new HashMap<>();

    public static void putValueInMap() {
        POOL.put(PART_STAIR, BlockPos.ZERO);
        POOL.put(PART_UG1, BlockPos.ZERO);
        POOL.put(PART_UG2, BlockPos.ZERO);
    }
    BlockPos genPos;
    public static void addPieces(TemplateManager tm, BlockPos bPos, List<StructurePiece> l, GenerationContext context) {
        int height1 = bPos.getY();
   //     System.out.println("bPos " + bPos);
        l.add(new VaultPieces.Piece(tm, PART_STAIR, bPos.down(17), Rotation.NONE, Mirror.NONE, context));
        l.add(new VaultPieces.Piece(tm, PART_UG1, bPos, Rotation.NONE, Mirror.NONE, context));
        l.add(new VaultPieces.Piece(tm, PART_UG2, bPos.west(16), Rotation.NONE, Mirror.NONE, context));

    }
    public static class GenerationContext {
        private int savedHeight = -1;
        public static final GenerationContext INSTANCE = new GenerationContext();
        public int getSavedHeight() {
            return savedHeight;
        }

        public void setSavedHeight(int height) {
            this.savedHeight = height;
        }
    }

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation location;
        private final Rotation rot;
        private final Mirror mir;
        private GenerationContext context;

        public Piece(TemplateManager tm, ResourceLocation rs, BlockPos bp, Rotation rot, Mirror mir, GenerationContext context) {
            super(VAULT_PIECES, 0);
            putValueInMap();
            this.location = rs;
            this.rot = rot;
            this.mir = mir;
            this.templatePosition = bp;
            this.context = context;
            this.tempMngSetup(tm);

        }

        public Piece(TemplateManager tm, CompoundNBT nbt) {
            super(VAULT_PIECES, nbt);
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
                    .setCenterOffset(VaultPieces.POOL.get(this.location))
                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            BlockPos blockpos = VaultPieces.POOL.get(this.location);
            if(blockpos == null) return false;
            BlockPos blockpos1 = this.templatePosition.add(Template.transformedBlockPos(ps, new BlockPos(blockpos.getX(), 0, blockpos.getZ())));
            if (context.getSavedHeight() == -1) {
                int height = sr.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
                context.setSavedHeight(height);
            }
            if (this.location == PART_STAIR) {
                this.templatePosition = this.templatePosition.add(0, context.getSavedHeight(), 0);
            }
            // Используем сохраненную высоту
            if (this.location == PART_UG1 || this.location == PART_UG2) {
                this.templatePosition = this.templatePosition.add(0, context.getSavedHeight()-17, 0);
            }
            return super.func_230383_a_(sr, sm, cg, r, mbb, cp, bp);
        }

    }

}

