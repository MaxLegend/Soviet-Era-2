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

    private static final ResourceLocation concertHall = new ResourceLocation("soviet:proc_lab/fragment_concert_hall");

    private static final ResourceLocation stairsTop = new ResourceLocation("soviet:proc_lab/fragment_stairs_top");
    private static final ResourceLocation stairsMid = new ResourceLocation("soviet:proc_lab/fragment_stairs_mid");
    private static final ResourceLocation stairsDown = new ResourceLocation("soviet:proc_lab/fragment_stairs_down");
    private static final ResourceLocation tripleFragment = new ResourceLocation("soviet:proc_lab/fragment_triple");
    private static final ResourceLocation tripleFragmentMirrored = new ResourceLocation("soviet:proc_lab/fragment_triple_mir");
    private static final ResourceLocation quadFragment = new ResourceLocation("soviet:proc_lab/fragment_quad");
    private static final ResourceLocation cornerFragment = new ResourceLocation("soviet:proc_lab/fragment_corner");
    private static final ResourceLocation cornerFragmentMirrored = new ResourceLocation("soviet:proc_lab/fragment_corner_mir");
    private static final ResourceLocation linearFragment1 = new ResourceLocation("soviet:proc_lab/fragment_linear1");
    private static final ResourceLocation linearFragment2 = new ResourceLocation("soviet:proc_lab/fragment_linear2");
    private static final ResourceLocation linearFragment3 = new ResourceLocation("soviet:proc_lab/fragment_linear3");
    private static final ResourceLocation linearFragment4 = new ResourceLocation("soviet:proc_lab/fragment_linear4");

    static IStructurePieceType UG_LABS_PIECES = IStructurePieceType.register(UndergroundLabsPieces.Piece::new, "soviet:proc_lab");

    private static final Map<ResourceLocation, BlockPos> POOL = new HashMap<>();

    public static void putValueInMap() {
        POOL.put(linearFragment1, BlockPos.ZERO);
        POOL.put(tripleFragment, BlockPos.ZERO);
        POOL.put(tripleFragmentMirrored, BlockPos.ZERO);
        POOL.put(quadFragment, BlockPos.ZERO);
        POOL.put(cornerFragment, BlockPos.ZERO);
        POOL.put(cornerFragmentMirrored, BlockPos.ZERO);
        POOL.put(linearFragment2, BlockPos.ZERO);
        POOL.put(linearFragment3, BlockPos.ZERO);
        POOL.put(linearFragment4, BlockPos.ZERO);
        POOL.put(stairsMid, BlockPos.ZERO);
        POOL.put(stairsTop, BlockPos.ZERO);
        POOL.put(stairsDown, BlockPos.ZERO);
        POOL.put(concertHall, BlockPos.ZERO);
    }
    static int lenthFragment = 5;
    public static void addPieces(TemplateManager tm, BlockPos bPos, List<StructurePiece> listPieces) {
        finalBuildCorridors(tm, bPos, listPieces);
        finalBuildStairs(tm, bPos, listPieces);
    }
    public static void finalBuildStairs(TemplateManager tm, BlockPos bPos, List<StructurePiece> listPieces) {
        listPieces.add(new UndergroundLabsPieces.Piece(tm, stairsDown, bPos, Rotation.NONE,Mirror.NONE, 8,0,4));
        UndergroundLabsPieces.Piece lf = (UndergroundLabsPieces.Piece) listPieces.get(listPieces.size() - 1);
        listPieces.add(new UndergroundLabsPieces.Piece(tm, stairsMid, bPos, Rotation.NONE,Mirror.NONE, 8,0,4));
        listPieces.add(new UndergroundLabsPieces.Piece(tm, stairsTop, bPos, Rotation.NONE,Mirror.NONE, 8,0,-1));

        listPieces.add(new UndergroundLabsPieces.Piece(tm, concertHall, bPos, Rotation.NONE,Mirror.NONE, -20,0,-9));
    }


    public static void finalBuildCorridors(TemplateManager tm, BlockPos bPos, List<StructurePiece> listPieces) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        //init node
        listPieces.add(new UndergroundLabsPieces.Piece(tm, quadFragment, bPos, Rotation.NONE,Mirror.NONE, 0,0,0));
        //gen south wing labs
        {
            genCorridor(tm, bPos, listPieces, Direction.Axis.Z, 1,0,0,0);
            int finishV = genCorridor(tm, bPos, listPieces, Direction.Axis.Z, 1,0,0,0);
            int randomNode = tr.nextInt(0,3);
            if(randomNode == 0) {
                genQuadNode(tm, bPos, listPieces, Direction.SOUTH, finishV);
                UndergroundLabsPieces.Piece lf = (UndergroundLabsPieces.Piece) listPieces.get(listPieces.size() - 1);
                {
                    genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, 1, 0, -3, -4);
                    genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, -1, 0, -5, -4);
                    genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.Z, 1, 0, -4, -3);
                    //сделать дополнительные разветвления на еще один-два уровня
                }
            }
            else if(randomNode == 1) {
                genTripleNode(tm, bPos, listPieces, Direction.SOUTH, finishV, Rotation.CLOCKWISE_180,0,0);
                UndergroundLabsPieces.Piece lf = (UndergroundLabsPieces.Piece) listPieces.get(listPieces.size() - 1);
                {
                    genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, 1,0,-3,-4);
                    genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, -1,0,-5,-4);
                }
            }
            else if(randomNode == 2) {
                Rotation corRot = tr.nextBoolean() ? Rotation.CLOCKWISE_90 : Rotation.CLOCKWISE_180;
                genCornerNode(tm, bPos, listPieces, Direction.SOUTH, finishV, corRot,-4,0);
                UndergroundLabsPieces.Piece lf = (UndergroundLabsPieces.Piece) listPieces.get(listPieces.size() - 1);
                {
                    if (lf.getRotate() == Rotation.CLOCKWISE_90) {
                        genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, -1, 0, -4, 0);
                    }
                    if (lf.getRotate() == Rotation.CLOCKWISE_180) {
                        genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, 1, 0, -3, -4);
                    }
                }
            }
        }

        //gen north wing labs
        {

            genCorridor(tm, bPos, listPieces, Direction.Axis.Z, -1,0,0,0);
            int finishV = genCorridor(tm, bPos, listPieces, Direction.Axis.Z, -1,0,0,0);
            int randomNode = tr.nextInt(0,3);
            if(randomNode == 0) {
                genQuadNode(tm, bPos, listPieces, Direction.NORTH, finishV);
                UndergroundLabsPieces.Piece lf = (UndergroundLabsPieces.Piece) listPieces.get(listPieces.size() - 1);
                {
                   genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, 1, 0, 0, 0);
                    genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, -1, 0, 0, 0);
                    genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.Z, -1, 0, 0, 0);

                }
            }
            else if(randomNode == 1) {
                genTripleNode(tm, bPos, listPieces, Direction.NORTH, finishV, Rotation.NONE,0,-4);
                UndergroundLabsPieces.Piece lf = (UndergroundLabsPieces.Piece) listPieces.get(listPieces.size() - 1);
                {
                    genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, 1,0,0,0);
                    genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, -1,0,0,0);
                }
            }
            else if(randomNode == 2) {
                Rotation corRot = tr.nextBoolean() ? Rotation.CLOCKWISE_90 : Rotation.CLOCKWISE_180;
                genCornerNode(tm, bPos, listPieces, Direction.NORTH, finishV, corRot,0,0);
                UndergroundLabsPieces.Piece lf = (UndergroundLabsPieces.Piece) listPieces.get(listPieces.size() - 1);
                {
                    if (lf.getRotate() == Rotation.CLOCKWISE_90) {
                       genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, 1, 0, -3, 0);
                    }
                    if (lf.getRotate() == Rotation.CLOCKWISE_180) {
                        genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, -1, 0, -4, -4);
                    }
                }
            }
        }

        //gen west wing labs
        {
            genCorridor(tm, bPos, listPieces, Direction.Axis.X, -1,0,0,0);
            int finishV = genCorridor(tm, bPos, listPieces, Direction.Axis.X, -1,0,0,0);
            int randomNode = tr.nextInt(0,3);
            if(randomNode == 0) {
                genQuadNode(tm, bPos, listPieces, Direction.WEST, finishV);
                UndergroundLabsPieces.Piece lf = (UndergroundLabsPieces.Piece) listPieces.get(listPieces.size() - 1);
                {
                  genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.Z, 1, 0, 0, 0);
                   genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.Z, -1, 0, 0, 0);
                   genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, -1, 0, 0, 0);

                }
            }
               else if(randomNode == 1) {
            genTripleNode(tm, bPos, listPieces, Direction.WEST, finishV, Rotation.CLOCKWISE_90,0,-4);
            UndergroundLabsPieces.Piece lf = (UndergroundLabsPieces.Piece) listPieces.get(listPieces.size() - 1);
            {
                genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.Z, 1,0,-4,1);
                genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.Z, -1,0,-4,-1);
            }
        }
               else if(randomNode == 2) {
            Rotation corRot = tr.nextBoolean() ? Rotation.COUNTERCLOCKWISE_90 : Rotation.CLOCKWISE_180;
            genCornerNode(tm, bPos, listPieces, Direction.WEST, finishV, corRot,-5,0);
            UndergroundLabsPieces.Piece lf = (UndergroundLabsPieces.Piece) listPieces.get(listPieces.size() - 1);
            {
                if (lf.getRotate() == Rotation.COUNTERCLOCKWISE_90) {
                    genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.Z, 1,0,0,-3);
                }
                if (lf.getRotate() == Rotation.CLOCKWISE_180) {
                    genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.Z, -1, 0, 0, -4);
                }
            }
        }
        }
        genCorridor(tm, bPos, listPieces, Direction.Axis.X, 1,0,0,0);
        //сделать крылья лабораторий во все стороны / добавить фрагменты с выходом на лестницы и к лифтам / построить фрагменты лестниц, вентшахт, лифтовых шахт
    }



    /**
     * Method of generating straight corridors.
     * @param tm Template Manager
     * @param p Position Template
     * @param l List of pieces
     * @param ax Corridor generation axis
     * @param dirCorrection Sets the direction of the corridor. May be 1 or -1
     */
    public static int genCorridor(TemplateManager tm, BlockPos p, List<StructurePiece> l, Direction.Axis ax, int dirCorrection, int yCorrection, int xCorrection, int zCorrection) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        int randLin = tr.nextInt(6,14);
        int randRare = tr.nextInt(4);
        ResourceLocation linear[] = new ResourceLocation[] {
                linearFragment1,
                linearFragment2,
                linearFragment3,
                linearFragment4
        };
        int finishPZ = 0;
        int finishPX = 0;
        if(ax == Direction.Axis.Z) {
            for (int pZ = 4; pZ < randLin * lenthFragment; pZ += lenthFragment) {
                l.add(new UndergroundLabsPieces.Piece(tm, linear[tr.nextInt(4)], p, Rotation.NONE, Mirror.NONE, xCorrection, yCorrection, zCorrection + pZ * dirCorrection));
                finishPZ = zCorrection + pZ * dirCorrection;

            }
            return finishPZ;
        }
        if(ax == Direction.Axis.X) {
            for (int pX = 4; pX < randLin * lenthFragment; pX += lenthFragment) {
                l.add(new UndergroundLabsPieces.Piece(tm, linear[tr.nextInt(4)], p, Rotation.CLOCKWISE_90, Mirror.NONE, xCorrection + 4 + pX * dirCorrection, yCorrection, zCorrection));
                finishPX = xCorrection + 4 + pX * dirCorrection;

            }

            return finishPX;

        }
        return 0;
    }

    public static void genQuadNode(TemplateManager tm, BlockPos p, List<StructurePiece> l, Direction d, int finishValue) {
        if(d == Direction.SOUTH) {
            l.add(new UndergroundLabsPieces.Piece(tm, quadFragment, p, Rotation.CLOCKWISE_180, Mirror.NONE, 4, 0, 9 + finishValue));
        }
        if(d == Direction.NORTH) {
            l.add(new UndergroundLabsPieces.Piece(tm, quadFragment, p, Rotation.NONE, Mirror.NONE, 0, 0,  finishValue));
        }
        if(d == Direction.WEST) {

            l.add(new UndergroundLabsPieces.Piece(tm, quadFragment, p, Rotation.NONE, Mirror.NONE, finishValue, 0,  0));
        }
        if(d == Direction.EAST) {
            l.add(new UndergroundLabsPieces.Piece(tm, quadFragment, p, Rotation.NONE, Mirror.NONE, finishValue, 0,  0));
        }
    }
    public static void genTripleNode(TemplateManager tm, BlockPos p, List<StructurePiece> l, Direction d, int finishValue, Rotation rot, int zCorrection, int xCorrection) {
        if(d == Direction.SOUTH) {
            l.add(new UndergroundLabsPieces.Piece(tm, tripleFragment, p, rot, Mirror.NONE, 4 + xCorrection, 0, 9 + finishValue + zCorrection));
        }
        if(d == Direction.NORTH) {
            l.add(new UndergroundLabsPieces.Piece(tm, tripleFragment, p, rot, Mirror.NONE, 4 + xCorrection, 0, 9 + finishValue + zCorrection));
        }
        if(d == Direction.WEST) {
            l.add(new UndergroundLabsPieces.Piece(tm, tripleFragmentMirrored, p, rot, Mirror.NONE, 4 + xCorrection + finishValue, 0,  zCorrection));
        }
    }
    public static void genCornerNode(TemplateManager tm, BlockPos p, List<StructurePiece> l, Direction d, int finishValue, Rotation rot, int zCorrection, int xCorrection) {
        if(d == Direction.SOUTH) {
            l.add(new UndergroundLabsPieces.Piece(tm, cornerFragment, p, rot, Mirror.NONE, 4 + xCorrection, 0, 9 + finishValue + zCorrection));
        }
        if(d == Direction.NORTH) {
            l.add(new UndergroundLabsPieces.Piece(tm, cornerFragmentMirrored, p, rot, Mirror.NONE, 4 + xCorrection, 0, 9 + finishValue + zCorrection));
        }
        if(d == Direction.WEST) {
            l.add(new UndergroundLabsPieces.Piece(tm, cornerFragment, p, rot, Mirror.NONE, 4 + finishValue + xCorrection, 0, 9 + zCorrection));
        }
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
        private void tempMngSetup(TemplateManager tm) {
            Template template = tm.getTemplateDefaulted(this.location);
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
            int i = sr.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
            BlockPos blockpos2;

            blockpos2 = this.templatePosition;

            boolean flag = false;

            if(this.location == stairsMid) {

                for (int y2 = 0; y2 < i - 22; y2 += 4) {
                    this.templatePosition = new BlockPos(this.templatePosition.getX(), 21, this.templatePosition.getZ());
                    this.templatePosition = this.templatePosition.add(0, y2, 0);
                    flag = super.func_230383_a_(sr, sm, cg, r, mbb, cp, bp);

                }
            } else if(this.location == stairsTop) {
                this.templatePosition = this.templatePosition.add(0, i-92, 0);
                flag = super.func_230383_a_(sr, sm, cg, r, mbb, cp, bp);
            } else {
                this.templatePosition = this.templatePosition.add(0, -74, 0);
                flag = super.func_230383_a_(sr, sm, cg, r, mbb, cp, bp);
            }
            this.templatePosition = blockpos2;
            return flag;
        }
    }
}
