package ru.tesmio.world.structure.labs;

import com.google.common.collect.ImmutableMap;
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
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class UndergroundLabsPieces {

    private static final ResourceLocation locationLinearFragment = new ResourceLocation("soviet:uglabs/fragment_linear");
    private static final ResourceLocation locationLinearFragmentWithBattery = new ResourceLocation("soviet:uglabs/fragment_linear_bat");
    private static final ResourceLocation locationLinearFragmentWithLamp1 = new ResourceLocation("soviet:uglabs/fragment_linear_lamp1");
    private static final ResourceLocation locationLinearFragmentWithLamp2 = new ResourceLocation("soviet:uglabs/fragment_linear_lamp2");

    static IStructurePieceType UG_LABS_PIECES = IStructurePieceType.register(UndergroundLabsPieces.Piece::new, "ug_labs");
    private static final Map<ResourceLocation, BlockPos> POOL1 = ImmutableMap.of(
            locationLinearFragment, new BlockPos(0, 5, 0),
            locationLinearFragmentWithBattery, new BlockPos(0, 5, 5),
            locationLinearFragmentWithLamp1, new BlockPos(0, 5, 10),
            locationLinearFragmentWithLamp2, new BlockPos(0, 5, 15));


    public static void addPieces(TemplateManager tm, BlockPos bPos, Rotation rot, List<StructurePiece> listPieces, Random r) {
        listPieces.add(new UndergroundLabsPieces.Piece(tm, locationLinearFragmentWithLamp2, bPos, rot, 0));
        listPieces.add(new UndergroundLabsPieces.Piece(tm, locationLinearFragmentWithBattery, bPos, rot, 0));
        listPieces.add(new UndergroundLabsPieces.Piece(tm, locationLinearFragmentWithLamp1, bPos, rot, 0));
        listPieces.add(new UndergroundLabsPieces.Piece(tm, locationLinearFragment, bPos, rot, 0));
    }

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation location;
        private final Rotation rot;

        public Piece(TemplateManager tm, ResourceLocation rs, BlockPos bp, Rotation rot, int yCorrection) {
            super(UG_LABS_PIECES, 0);
            this.location = rs;
            BlockPos blockpos = POOL1.get(rs);
            this.templatePosition = bp.add(blockpos.getX(), blockpos.getY() - yCorrection, blockpos.getZ());
            this.rot = rot;
            this.tempMngSetup(tm);
        }

        public Piece(TemplateManager tm, CompoundNBT nbt) {
            super(UG_LABS_PIECES, nbt);
            this.location = new ResourceLocation(nbt.getString("Template"));
            this.rot = Rotation.valueOf(nbt.getString("Rot"));
            this.tempMngSetup(tm);
        }

        private void tempMngSetup(TemplateManager p_207614_1_) {
            Template template = p_207614_1_.getTemplateDefaulted(this.location);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rot)
                    .setMirror(Mirror.NONE);
            this.setup(template, this.templatePosition, placementsettings);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
//        protected void readAdditional(CompoundNBT tagCompound) {
//            super.readAdditional(tagCompound);
//            tagCompound.putString("Template", this.location.toString());
//            tagCompound.putString("Rot", this.rot.name());
//        }

        protected void handleDataMarker(String function, BlockPos pos, IServerWorld worldIn, Random rand, MutableBoundingBox sbb) {
//            if ("chest".equals(function)) {
//                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
//                TileEntity tileentity = worldIn.getTileEntity(pos.down());
//                if (tileentity instanceof ChestTileEntity) {
//                    ((ChestTileEntity)tileentity).setLootTable(LootTables.CHESTS_IGLOO_CHEST, rand.nextLong());
//                }
//
//            }
        }
//
        //генератор заработал, настраивать, компоновать лабы.
        public boolean func_230383_a_(ISeedReader sr, StructureManager sm, ChunkGenerator cg, Random r, MutableBoundingBox mbb, ChunkPos cp, BlockPos bp) {
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rot).setMirror(Mirror.NONE).setCenterOffset(UndergroundLabsPieces.POOL1.get(this.location)).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            BlockPos blockpos = UndergroundLabsPieces.POOL1.get(this.location);
            BlockPos blockpos1 = this.templatePosition.add(Template.transformedBlockPos(placementsettings, new BlockPos(3 - blockpos.getX(), 0, 0 - blockpos.getZ())));
            int i = sr.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
            BlockPos blockpos2 = this.templatePosition;
            this.templatePosition = this.templatePosition.add(0, i - 90 - 1, 0);
            boolean flag = super.func_230383_a_(sr, sm, cg, r, mbb, cp, bp);
            this.templatePosition = blockpos2;
            return flag;
        }
    }
}
