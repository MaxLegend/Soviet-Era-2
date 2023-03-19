package ru.tesmio.world.structure.labs;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
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

/**
 * @author Tesmio
 */
public class UndergroundLabsPieces {
    private static final ResourceLocation tripleFragment = new ResourceLocation("soviet:uglabs/fragment_triple");
    private static final ResourceLocation quadFragment = new ResourceLocation("soviet:uglabs/fragment_quad");
    private static final ResourceLocation cornerFragment = new ResourceLocation("soviet:uglabs/fragment_corner");
    private static final ResourceLocation linearFragment = new ResourceLocation("soviet:uglabs/fragment_linear");
    private static final ResourceLocation linearFragmentWithBattery = new ResourceLocation("soviet:uglabs/fragment_linear_bat");
    private static final ResourceLocation linearFragmentWithLamp1 = new ResourceLocation("soviet:uglabs/fragment_linear_lamp1");
    private static final ResourceLocation linearFragmentWithLamp2 = new ResourceLocation("soviet:uglabs/fragment_linear_lamp2");

    static IStructurePieceType UG_LABS_PIECES = IStructurePieceType.register(UndergroundLabsPieces.Piece::new, "ug_labs");
//    private static final Map<ResourceLocation, BlockPos> POOL = ImmutableMap.of(
//            linearFragment, BlockPos.ZERO,
//            linearFragmentWithBattery, BlockPos.ZERO,
//            linearFragmentWithLamp1, BlockPos.ZERO,
//            linearFragmentWithLamp2, BlockPos.ZERO
//            );
    private static final Map<ResourceLocation, BlockPos> POOL = new HashMap<>();

    public static void putValueInMap() {
        POOL.put(linearFragment, BlockPos.ZERO);
        POOL.put(tripleFragment, BlockPos.ZERO);
        POOL.put(quadFragment, BlockPos.ZERO);
        POOL.put(cornerFragment, BlockPos.ZERO);
        POOL.put(linearFragmentWithBattery, BlockPos.ZERO);
        POOL.put(linearFragmentWithLamp2, BlockPos.ZERO);
        POOL.put(linearFragmentWithLamp1, BlockPos.ZERO);
    }
    static int lenthFragment = 5;

    /**
     * Method of generating straight corridors.
     * @param tm Template Manager
     * @param p Position Template
     * @param l List of pieces
     * @param ax Corridor generation axis
     * @param dirCorrection Sets the direction of the corridor. May be 1 or -1
     */
    public static int genCorridor(TemplateManager tm, BlockPos p, List<StructurePiece> l, Direction.Axis ax, int dirCorrection) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        int randLin = tr.nextInt(4,10);
        int randRare = tr.nextInt(50);
        int finishPZ = 0;
        int finishPX = 0;
        if(ax == Direction.Axis.Z) {
            for (int pZ = 4; pZ < randLin * lenthFragment; pZ += lenthFragment) {
                l.add(new UndergroundLabsPieces.Piece(tm, linearFragment, p, Rotation.NONE, Mirror.NONE, 0, 0, pZ * dirCorrection));
                if (pZ == randRare) {
                    l.add(new UndergroundLabsPieces.Piece(tm, linearFragmentWithBattery, p, Rotation.NONE, Mirror.NONE, 0, 0,  pZ * dirCorrection));
                }
                if (pZ == randRare) {
                    l.add(new UndergroundLabsPieces.Piece(tm, linearFragmentWithLamp2, p, Rotation.NONE, Mirror.NONE, 0, 0,  pZ * dirCorrection));
                }
                if (pZ == randRare) {
                    l.add(new UndergroundLabsPieces.Piece(tm, linearFragmentWithLamp1, p, Rotation.NONE, Mirror.NONE, 0, 0,  pZ * dirCorrection));
                }
                finishPZ = pZ;
            }
            return 5 + finishPZ;
        }
        if(ax == Direction.Axis.X) {
            for (int pX = 4; pX < randLin * lenthFragment; pX += lenthFragment) {
                l.add(new UndergroundLabsPieces.Piece(tm, linearFragment, p, Rotation.CLOCKWISE_90, Mirror.NONE, 4 + pX * dirCorrection, 0, 0));
                if (pX == randRare) {
                    l.add(new UndergroundLabsPieces.Piece(tm, linearFragmentWithBattery, p, Rotation.CLOCKWISE_90, Mirror.NONE, 4 + pX * dirCorrection, 0, 0));
                }
                if (pX == randRare) {
                    l.add(new UndergroundLabsPieces.Piece(tm, linearFragmentWithLamp2, p, Rotation.CLOCKWISE_90, Mirror.NONE, 4 + pX * dirCorrection, 0, 0));
                }
                if (pX == randRare) {
                    l.add(new UndergroundLabsPieces.Piece(tm, linearFragmentWithLamp1, p, Rotation.CLOCKWISE_90, Mirror.NONE, 4 + pX * dirCorrection, 0, 0));
                }
                finishPX = pX;
            }
            return 5 + finishPX;
        }
        return 0;
    }
    //Решить ошибку с двойной генерацией конечных нод.
    //Попытаться исправить проскакивающую ноду (после узла может сгенериться еще один линейный фрагмент
    public static int genNodeFragment(TemplateManager tm, BlockPos p, List<StructurePiece> l, Direction d, int finishValue) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        int randNode = tr.nextInt(0,3);
        if(d == Direction.SOUTH) {
                if (randNode == 0) {
                    l.add(new UndergroundLabsPieces.Piece(tm, quadFragment, p, Rotation.NONE, Mirror.NONE, 0, 0,  finishValue));
                }
                if (randNode == 1) {
                    Rotation isRot = tr.nextBoolean() ? Rotation.CLOCKWISE_90 : Rotation.CLOCKWISE_180;
                    l.add(new UndergroundLabsPieces.Piece(tm, cornerFragment, p, isRot, Mirror.NONE, 4, 0,  finishValue));
                }
                if (randNode == 2) {
                    l.add(new UndergroundLabsPieces.Piece(tm, tripleFragment, p, Rotation.CLOCKWISE_180, Mirror.NONE, 4, 0, finishValue));
                }
                return randNode;
        }
        return 0;
    }

    public enum TypeNode {
        QUAD_NODE(0),
        CORNER_NODE(1),
        TRIPLE_NODe(2);
        int id;
        TypeNode(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }
    }


    public static StructurePiece getNewNode(TemplateManager tm, ResourceLocation l, BlockPos p, Rotation rot, Mirror mir, int xCor, int yCor, int zCor) {
        return new UndergroundLabsPieces.Piece(tm, l, p, rot, mir, xCor, yCor, zCor);
    }
    public static void finalBuild(TemplateManager tm, BlockPos bPos, List<StructurePiece> listPieces) {
        //init node
        listPieces.add(new UndergroundLabsPieces.Piece(tm, quadFragment, bPos, Rotation.NONE,Mirror.NONE, 0,0,0));
        //first four corridor
        {
            //south corridor
            genCorridor(tm, bPos, listPieces, Direction.Axis.Z, 1);
            int finishV = genCorridor(tm, bPos, listPieces, Direction.Axis.Z, 1);
            genNodeFragment(tm, bPos, listPieces, Direction.SOUTH, finishV);
            //число от 0 до 2, символизирующее тип узла. 0- quad, 1 - corner, 2 - triple
            int nodeType = genNodeFragment(tm, bPos, listPieces, Direction.SOUTH, finishV);
            UndergroundLabsPieces.Piece lf = (UndergroundLabsPieces.Piece)listPieces.get(listPieces.size()-1);
            if(nodeType == 0) {
                genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, 1);
            }
        }
    }
    public static void addPieces(TemplateManager tm, BlockPos bPos, List<StructurePiece> listPieces) {

      //  listPieces.add(new UndergroundLabsPieces.Piece(tm, locationLinearFragmentWithLamp2, bPos, rot, 0,0,0));
      //  listPieces.add(new UndergroundLabsPieces.Piece(tm, locationLinearFragmentWithBattery, bPos, rot, 0,0,0));
      //  listPieces.add(new UndergroundLabsPieces.Piece(tm, locationLinearFragmentWithLamp1, bPos, rot, 0,0,0));

        finalBuild(tm, bPos, listPieces);


        //lastFragment - get new node fragment
      //  UndergroundLabsPieces.Piece lf = (UndergroundLabsPieces.Piece)listPieces.get(listPieces.size()-1);
     //   listPieces.add(getNewNode(tm, linearFragment, bPos, lf.getRotate(), lf.getMirror(), lf.xCorrection, lf.yCorrection, lf.zCorrection));

      //  listPieces.add(getNewNode(tm, lf.getPosition(lf.getLocation()), lf.getRotate(), lf.getMirror()));
//        genCorridor(tm,bPos,listPieces, Direction.Axis.Z, -1);
//        genCorridor(tm,bPos,listPieces, Direction.Axis.X, 1);
//        genCorridor(tm,bPos,listPieces, Direction.Axis.X, -1);
    }

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation location;
        private final Rotation rot;
        private final Mirror mir;
        private int xCorrection,yCorrection,zCorrection;
        public Piece(TemplateManager tm, ResourceLocation rs, BlockPos bp, Rotation rot, Mirror mir, int xCorrection, int yCorrection, int zCorrection) {
            super(UG_LABS_PIECES, 0);
            putValueInMap();
            this.location = rs;
            this.rot = rot;
            this.mir = mir;
            BlockPos blockpos = POOL.get(rs);
            this.templatePosition = bp.add(blockpos.getX() + xCorrection, blockpos.getY() + yCorrection, blockpos.getZ() + zCorrection);
            this.zCorrection = zCorrection;
            this.xCorrection = xCorrection;
            this.yCorrection = yCorrection;
            this.tempMngSetup(tm);
        }

        public Piece(TemplateManager tm, CompoundNBT nbt) {
            super(UG_LABS_PIECES, nbt);
            this.location = new ResourceLocation(nbt.getString("Template"));
            this.rot = Rotation.NONE;
            this.mir = Mirror.NONE;
            this.tempMngSetup(tm);
        }
        public Mirror getMirror() {
            return mir;
        }
        public Rotation getRotate() {
            return rot;
        }
        public BlockPos getPosition(ResourceLocation rs) {
            return POOL.get(rs);
        }
        public ResourceLocation getLocation() {
            return location;
        }
        public BlockPos getTemplatePosition() {
            return templatePosition;
        }
        private void tempMngSetup(TemplateManager p_207614_1_) {
            Template template = p_207614_1_.getTemplateDefaulted(this.location);
            PlacementSettings placementsettings = (
                    new PlacementSettings())
                    .setRotation(this.rot)
                    .setMirror(this.mir);
            this.setup(template, this.templatePosition, placementsettings);
        }


        protected void handleDataMarker(String function, BlockPos pos, IServerWorld worldIn, Random rand, MutableBoundingBox sbb) {

        }

        //генератор заработал, настраивать, компоновать лабы.
        public boolean func_230383_a_(ISeedReader sr, StructureManager sm, ChunkGenerator cg, Random r, MutableBoundingBox mbb, ChunkPos cp, BlockPos bp) {
            PlacementSettings ps = (
                    new PlacementSettings())
                    .setRotation(Rotation.NONE)
                    .setMirror(Mirror.NONE)
                    .setCenterOffset(UndergroundLabsPieces.POOL.get(this.location))
                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);

            BlockPos blockpos = UndergroundLabsPieces.POOL.get(this.location);
            if(blockpos == null) return false;
            BlockPos blockpos1 = this.templatePosition.add(
            Template.transformedBlockPos(ps, new BlockPos(blockpos.getX(), 0, blockpos.getZ())));
            int i = sr.getHeight(Heightmap.Type.MOTION_BLOCKING, blockpos1.getX(), blockpos1.getZ());
            BlockPos blockpos2 = this.templatePosition;
            this.templatePosition = this.templatePosition.add(0, 0, 0);
            boolean flag = super.func_230383_a_(sr, sm, cg, r, mbb, cp, bp);
            this.templatePosition = blockpos2;
            return flag;
        }
    }
}
