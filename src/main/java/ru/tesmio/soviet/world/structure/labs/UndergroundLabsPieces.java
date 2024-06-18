package ru.tesmio.soviet.world.structure.labs;

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
import ru.tesmio.soviet.core.Config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class UndergroundLabsPieces {

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation location;
        private final Rotation rot;
        private final Mirror mir;

        private int xCorrection, zCorrection;

        public Piece(TemplateManager tm, ResourceLocation rs, BlockPos bp, Rotation rot, Mirror mir, int xCorrection, int zCorrection) {
            super(UG_LABS_PIECES, 0);
            putValueInMap();
            this.location = rs;
            this.rot = rot;
            this.mir = mir;
            this.templatePosition = bp.add(xCorrection, 0, zCorrection);
            this.zCorrection = zCorrection;
            this.xCorrection = xCorrection;
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
//            if ("chest".equals(function)) {
//                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
//                TileEntity tileentity = worldIn.getTileEntity(pos.down());
//                if (tileentity instanceof ChestTileEntity) {
//                    ((ChestTileEntity)tileentity).setLootTable(LootTables.CHESTS_IGLOO_CHEST, rand.nextLong());
//                }
//
//            }
        }

        public boolean func_230383_a_(ISeedReader sr, StructureManager sm, ChunkGenerator cg, Random r, MutableBoundingBox mbb, ChunkPos cp, BlockPos bp) {
            PlacementSettings ps = (new PlacementSettings())
                    .setRotation(Rotation.NONE)
                    .setMirror(Mirror.NONE)
                    .setCenterOffset(UndergroundLabsPieces.POOL.get(this.location))
                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);

            BlockPos blockpos = UndergroundLabsPieces.POOL.get(this.location);
            if (blockpos == null) return false;
            BlockPos blockpos1 = this.templatePosition.add(
                    Template.transformedBlockPos(ps, new BlockPos(blockpos.getX(), 0, blockpos.getZ())));
            int i = sr.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
            BlockPos blockpos2;
            blockpos2 = this.templatePosition;
            boolean flag = false;
            if(this.location == ventilationMid) {
                for (int y2 = 0; y2 < i -28; y2 += 4) {
                    this.templatePosition = new BlockPos(this.templatePosition.getX(), 27, this.templatePosition.getZ());
                    this.templatePosition = this.templatePosition.add(0, y2, 0);
                    flag = super.func_230383_a_(sr, sm, cg, r, mbb, cp, bp);
                }
            } else if(this.location == ventilationTop) {
                this.templatePosition = this.templatePosition.add(0, i-193, 0);
                flag = super.func_230383_a_(sr, sm, cg, r, mbb, cp, bp);
            }
            if(this.location == stairsMid) {
                for (int y2 = 0; y2 < i - 28; y2 += 4) {
                    this.templatePosition = new BlockPos(this.templatePosition.getX(), 25, this.templatePosition.getZ());
                    this.templatePosition = this.templatePosition.add(0, y2, 0);
                    flag = super.func_230383_a_(sr, sm, cg, r, mbb, cp, bp);
                }
            } else if(this.location == stairsTop) {
                this.templatePosition = this.templatePosition.add(0, i-193, 0);
                flag = super.func_230383_a_(sr, sm, cg, r, mbb, cp, bp);
            } else {
                this.templatePosition = this.templatePosition.add(0, Config.heightGenLabs.get(), 0); // y = -170 / -40 test
                flag = super.func_230383_a_(sr, sm, cg, r, mbb, cp, bp);
            }

            this.templatePosition = blockpos2;
            return flag;
        }
    }


    private static final ResourceLocation ventilationTop = new ResourceLocation("soviet:proc_lab/ventilation_top");
    private static final ResourceLocation ventilationMid = new ResourceLocation("soviet:proc_lab/ventilation_mid");
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
    private static final ResourceLocation concertHall = new ResourceLocation("soviet:proc_lab/fragment_concert_hall");
    private static final ResourceLocation filter_room = new ResourceLocation("soviet:proc_lab/fragment_filter_room");
    private static final ResourceLocation diesel_gen = new ResourceLocation("soviet:proc_lab/diesel_gen");
    private static final ResourceLocation unk = new ResourceLocation("soviet:proc_lab/unk");
    private static final ResourceLocation gateway = new ResourceLocation("soviet:proc_lab/gateway");
    private static final ResourceLocation toilet = new ResourceLocation("soviet:proc_lab/fragment_toilet");
    private static final ResourceLocation clean_room = new ResourceLocation("soviet:proc_lab/fragment_clean_room");
    private static final ResourceLocation research = new ResourceLocation("soviet:proc_lab/research");
    private static final ResourceLocation research2 = new ResourceLocation("soviet:proc_lab/research2");
    static ThreadLocalRandom tr = ThreadLocalRandom.current();
    static IStructurePieceType UG_LABS_PIECES = IStructurePieceType.register(UndergroundLabsPieces.Piece::new, "soviet:proc_lab");

    public static final Map<ResourceLocation, BlockPos> POOL = new HashMap<>();
    static int lenthFragment = 5;

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
        POOL.put(ventilationTop, BlockPos.ZERO);
        POOL.put(ventilationMid, BlockPos.ZERO);
        POOL.put(stairsDown, BlockPos.ZERO);
        POOL.put(filter_room, BlockPos.ZERO);
        POOL.put(concertHall, BlockPos.ZERO);
        POOL.put(diesel_gen, BlockPos.ZERO);
        POOL.put(unk, BlockPos.ZERO);
        POOL.put(gateway, BlockPos.ZERO);
        POOL.put(toilet, BlockPos.ZERO);
        POOL.put(clean_room, BlockPos.ZERO);
        POOL.put(research, BlockPos.ZERO);
        POOL.put(research2, BlockPos.ZERO);
    }
    public static ResourceLocation[] poolNode = new ResourceLocation[] {quadFragment, cornerFragment, cornerFragmentMirrored, tripleFragment, tripleFragmentMirrored};
    public static ResourceLocation genRandomNode() {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        int ifnull = tr.nextInt(0,5);
        if(ifnull <= 4) {
            ResourceLocation randNode = poolNode[ifnull];
            return randNode;
        }
        return filter_room;
    }

    public static void addPieces(TemplateManager tm, BlockPos bPos, List<StructurePiece> l) {
    ThreadLocalRandom tr = ThreadLocalRandom.current();
        l.add(new UndergroundLabsPieces.Piece(tm, quadFragment, bPos, Rotation.NONE, Mirror.NONE, 0, 0));
        generateLabWings(tm, bPos, l);
        generateStairs(tm, bPos, l);

    }
    public static void generateStairs(TemplateManager tm, BlockPos bPos, List<StructurePiece> l) {
        l.add(new UndergroundLabsPieces.Piece(tm, stairsDown, bPos, Rotation.NONE,Mirror.NONE, 8,4));
        l.add(new UndergroundLabsPieces.Piece(tm, stairsMid, bPos, Rotation.NONE,Mirror.NONE, 8,4));
        l.add(new UndergroundLabsPieces.Piece(tm, stairsTop, bPos, Rotation.NONE,Mirror.NONE, 8,-1));
    }
    public static void generateLabWings(TemplateManager tm, BlockPos bPos, List<StructurePiece> l) {
        genSouthWing(tm, bPos, l);
        genNorthWing(tm,bPos,l);
        genWestWing(tm,bPos,l);
        genEastWing(tm,bPos,l);
    }
    public static void genSouthWing2(TemplateManager tm, BlockPos bPos, List<StructurePiece> l) {
        BlockPos endCorridorPos = genCorridor(tm, bPos, l, Direction.Axis.Z, 1, 0, 0);
        l.add(new UndergroundLabsPieces.Piece(tm, filter_room, bPos, Rotation.CLOCKWISE_180, Mirror.NONE, 6, endCorridorPos.getZ() + 13));
        int newZ2 = bPos.getZ() + endCorridorPos.getZ() + 13;
        BlockPos newNodePos = new BlockPos(bPos.getX(), bPos.getY(), newZ2);
        l.add(new UndergroundLabsPieces.Piece(tm, ventilationMid, newNodePos, Rotation.NONE,Mirror.NONE, 0,-2));
        l.add(new UndergroundLabsPieces.Piece(tm, ventilationTop, newNodePos, Rotation.NONE,Mirror.NONE, 0,-2));
    }
    public static void genNorthWing2(TemplateManager tm, BlockPos bPos, List<StructurePiece> l) {
        BlockPos endCorridorPos = genCorridor(tm, bPos, l, Direction.Axis.Z, -1, 0, 0);
        l.add(new UndergroundLabsPieces.Piece(tm, filter_room, bPos, Rotation.NONE, Mirror.NONE, -2, endCorridorPos.getZ() - 9));
        int newZ2 = bPos.getZ() + endCorridorPos.getZ() - 9;
        BlockPos newNodePos = new BlockPos(bPos.getX(), bPos.getY(), newZ2);
        if(tr.nextInt(0, Config.chanceGen_clean_room.get()) == 1 || tr.nextInt(0, Config.chanceGen_clean_room.get()) == 0) {
            l.add(new UndergroundLabsPieces.Piece(tm, clean_room, newNodePos, Rotation.COUNTERCLOCKWISE_90, Mirror.NONE, -12, 23));
        }
        l.add(new UndergroundLabsPieces.Piece(tm, ventilationMid, newNodePos, Rotation.NONE,Mirror.NONE, 0,0));
        l.add(new UndergroundLabsPieces.Piece(tm, ventilationTop, newNodePos, Rotation.NONE,Mirror.NONE, 0,0));

    }
    public static void genWestWing2(TemplateManager tm, BlockPos bPos, List<StructurePiece> l) {
        BlockPos endCorridorPos = genCorridor(tm, bPos, l, Direction.Axis.X, -1, 3, 0);
        l.add(new UndergroundLabsPieces.Piece(tm, filter_room, bPos, Rotation.COUNTERCLOCKWISE_90, Mirror.NONE, endCorridorPos.getX() -5, 6));
        int newX2 = bPos.getX() + endCorridorPos.getX() -5;
        BlockPos newNodePos = new BlockPos(newX2, bPos.getY(), bPos.getZ());

        l.add(new UndergroundLabsPieces.Piece(tm, toilet, newNodePos, Rotation.CLOCKWISE_180, Mirror.NONE, 29, 15));
        l.add(new UndergroundLabsPieces.Piece(tm, ventilationMid, newNodePos, Rotation.NONE,Mirror.NONE, 0,0));
        l.add(new UndergroundLabsPieces.Piece(tm, ventilationTop, newNodePos, Rotation.NONE,Mirror.NONE, 0,0));
    }
    public static void genEastWing2(TemplateManager tm, BlockPos bPos, List<StructurePiece> l) {
        BlockPos endCorridorPos = genCorridor(tm, bPos, l, Direction.Axis.X, 1, 5, 0);
        l.add(new UndergroundLabsPieces.Piece(tm, filter_room, bPos, Rotation.CLOCKWISE_90, Mirror.NONE, endCorridorPos.getX() +9, -2));
        int newX2 = bPos.getX() + endCorridorPos.getX() +9;
        BlockPos newNodePos = new BlockPos(newX2, bPos.getY(), bPos.getZ());
        l.add(new UndergroundLabsPieces.Piece(tm, toilet, newNodePos, Rotation.CLOCKWISE_180, Mirror.NONE, -29, 15));
        l.add(new UndergroundLabsPieces.Piece(tm, ventilationMid, newNodePos, Rotation.NONE,Mirror.NONE, -2,0));
        l.add(new UndergroundLabsPieces.Piece(tm, ventilationTop, newNodePos, Rotation.NONE,Mirror.NONE, -2,0));

    }
    public static void genSouthWing(TemplateManager tm, BlockPos bPos, List<StructurePiece> l) {
        {
            BlockPos endCorridorPos = genCorridor(tm, bPos, l, Direction.Axis.Z, 1, 0, 0);
            ResourceLocation rs = genRandomNode();

            int newZ = bPos.getZ() + endCorridorPos.getZ() + 5;
            BlockPos newNodePos = new BlockPos(bPos.getX(), bPos.getY(), newZ);
            if(tr.nextInt(0, Config.chanceGen_research2.get()) == 1 || tr.nextInt(0, Config.chanceGen_research2.get()) == 0) {
                l.add(new UndergroundLabsPieces.Piece(tm, research2, newNodePos, Rotation.NONE, Mirror.NONE, -13, -22));
            }
            if (quadFragment.equals(rs)) {
                l.add(new UndergroundLabsPieces.Piece(tm, rs, bPos, Rotation.NONE, Mirror.NONE, 0, endCorridorPos.getZ() + 5));
                genSouthWing2(tm,newNodePos,l);
                genWestWing2(tm,newNodePos,l);
                genEastWing2(tm,newNodePos,l);
            } else if (tripleFragmentMirrored.equals(rs)) { // с щитами
                l.add(new UndergroundLabsPieces.Piece(tm, rs, bPos, Rotation.NONE, Mirror.NONE, 0, endCorridorPos.getZ() + 5));
                genEastWing2(tm,newNodePos,l);
                genWestWing2(tm,newNodePos,l);
            }else if (tripleFragment.equals(rs)) {
                l.add(new UndergroundLabsPieces.Piece(tm, rs, bPos, Rotation.CLOCKWISE_180, Mirror.NONE, 4, endCorridorPos.getZ() + 9));
                genEastWing2(tm,newNodePos,l);
                genWestWing2(tm,newNodePos,l);
            }
            else if (cornerFragment.equals(rs)) {
                l.add(new UndergroundLabsPieces.Piece(tm, rs, bPos, Rotation.CLOCKWISE_90, Mirror.NONE, 4, endCorridorPos.getZ() + 5));
                genWestWing2(tm,newNodePos,l);
            }
            else if (cornerFragmentMirrored.equals(rs)) {
                l.add(new UndergroundLabsPieces.Piece(tm, rs, bPos, Rotation.NONE, Mirror.NONE, 0, endCorridorPos.getZ() + 5));
                genEastWing2(tm,newNodePos,l);
            }
            else {
                l.add(new UndergroundLabsPieces.Piece(tm, filter_room, bPos, Rotation.CLOCKWISE_180, Mirror.NONE, 6, endCorridorPos.getZ() + 13));
            }
        }
    }
    public static void genNorthWing(TemplateManager tm, BlockPos bPos, List<StructurePiece> l) {
        {
            BlockPos endCorridorPos = genCorridor(tm, bPos, l, Direction.Axis.Z, -1, 0, 0);
            ResourceLocation rs = genRandomNode();
            int newZ = bPos.getZ() + endCorridorPos.getZ() - 5;
            BlockPos newNodePos = new BlockPos(bPos.getX(), bPos.getY(), newZ);
            if(tr.nextInt(0, Config.chanceGen_concertHall.get()) == 1 || tr.nextInt(0, Config.chanceGen_concertHall.get()) == 0) {
                l.add(new UndergroundLabsPieces.Piece(tm, concertHall, newNodePos, Rotation.NONE, Mirror.NONE, 10, -8));
            }
            if(tr.nextInt(0, Config.chanceGen_research.get()) == 1 || tr.nextInt(0, Config.chanceGen_research.get()) == 0) {
                l.add(new UndergroundLabsPieces.Piece(tm, research, newNodePos, Rotation.NONE, Mirror.NONE, 4, 16));
            }
            if (quadFragment.equals(rs)) {
                l.add(new UndergroundLabsPieces.Piece(tm, rs, bPos, Rotation.NONE, Mirror.NONE, 0, endCorridorPos.getZ() - 5));
                genNorthWing2(tm,newNodePos,l);
                genWestWing2(tm,newNodePos,l);
                genEastWing2(tm,newNodePos,l);
            } else if (tripleFragmentMirrored.equals(rs)) {
                l.add(new UndergroundLabsPieces.Piece(tm, rs, bPos, Rotation.CLOCKWISE_180, Mirror.NONE, 4, endCorridorPos.getZ() - 1));
                genWestWing2(tm,newNodePos,l);
                genEastWing2(tm,newNodePos,l);
            }else if (tripleFragment.equals(rs)) {
                l.add(new UndergroundLabsPieces.Piece(tm, rs, bPos, Rotation.NONE, Mirror.NONE, 0, endCorridorPos.getZ() - 5));
                genWestWing2(tm,newNodePos,l);
                genEastWing2(tm,newNodePos,l);
            }
            else if (cornerFragmentMirrored.equals(rs)) {
                l.add(new UndergroundLabsPieces.Piece(tm, rs, bPos, Rotation.CLOCKWISE_90, Mirror.NONE, 4, endCorridorPos.getZ() - 5));
                genEastWing2(tm,newNodePos,l);
            }
            else if (cornerFragment.equals(rs)) {
                l.add(new UndergroundLabsPieces.Piece(tm, rs, bPos, Rotation.NONE, Mirror.NONE, 0, endCorridorPos.getZ() - 5));
                genWestWing2(tm,newNodePos,l);
            }
            else {
                l.add(new UndergroundLabsPieces.Piece(tm, filter_room, bPos, Rotation.NONE, Mirror.NONE, -2, endCorridorPos.getZ() - 9));
            }
        }
    }
    public static void genWestWing(TemplateManager tm, BlockPos bPos, List<StructurePiece> l) {
        {
            BlockPos endCorridorPos = genCorridor(tm, bPos, l, Direction.Axis.X, -1, 3, 0);
            ResourceLocation rs = genRandomNode();
            int newX = bPos.getX() + endCorridorPos.getX() -5;
            BlockPos newNodePos = new BlockPos(newX, bPos.getY(), bPos.getZ());
            if(tr.nextInt(0, Config.chanceGen_unk.get()) == 1 || tr.nextInt(0, Config.chanceGen_unk.get()) == 0) {
                l.add(new UndergroundLabsPieces.Piece(tm, unk, newNodePos, Rotation.NONE, Mirror.NONE, 10, -23));
            }
            if(tr.nextInt(0, Config.chanceGen_gateway.get()) == 1 || tr.nextInt(0, Config.chanceGen_gateway.get()) == 0) {
                l.add(new UndergroundLabsPieces.Piece(tm, gateway, newNodePos, Rotation.NONE, Mirror.NONE, 8, -7));
            }
            if (quadFragment.equals(rs)) {
                l.add(new UndergroundLabsPieces.Piece(tm, rs, bPos, Rotation.NONE, Mirror.NONE, endCorridorPos.getX() -5, 0));
                genWestWing2(tm,newNodePos,l);
                genNorthWing2(tm,newNodePos,l);
                genSouthWing2(tm,newNodePos,l);
            } else if (tripleFragmentMirrored.equals(rs)) {
                l.add(new UndergroundLabsPieces.Piece(tm, rs, bPos, Rotation.CLOCKWISE_90, Mirror.NONE, endCorridorPos.getX() -1, 0));
                genNorthWing2(tm,newNodePos,l);
                genSouthWing2(tm,newNodePos,l);
            }
            else if (cornerFragmentMirrored.equals(rs)) {
                l.add(new UndergroundLabsPieces.Piece(tm, rs, bPos, Rotation.CLOCKWISE_90, Mirror.NONE, endCorridorPos.getX() -1, 0));

                genSouthWing2(tm,newNodePos,l);
            }
            else if (cornerFragment.equals(rs)) {
                l.add(new UndergroundLabsPieces.Piece(tm, rs, bPos, Rotation.CLOCKWISE_180, Mirror.NONE, endCorridorPos.getX() -1, 4));

                genNorthWing2(tm,newNodePos,l);
            }  else {
                l.add(new UndergroundLabsPieces.Piece(tm, filter_room, bPos, Rotation.COUNTERCLOCKWISE_90, Mirror.NONE, endCorridorPos.getX() -5, 6));
            }
        }
    }
    public static void genEastWing(TemplateManager tm, BlockPos bPos, List<StructurePiece> l) {
        BlockPos endCorridorPos = genCorridor(tm, bPos, l, Direction.Axis.X, 1, 5, 0);
        ResourceLocation rs = genRandomNode();
        int newX = bPos.getX() + endCorridorPos.getX();
        BlockPos newNodePos = new BlockPos(newX, bPos.getY(), bPos.getZ());
        if(tr.nextInt(0, Config.chanceGen_diesel_gen.get()) == 1 || tr.nextInt(0, Config.chanceGen_diesel_gen.get()) == 0) {
            l.add(new UndergroundLabsPieces.Piece(tm, diesel_gen, newNodePos, Rotation.NONE, Mirror.NONE, -20, -8));
        }
        if (quadFragment.equals(rs)) {
            l.add(new UndergroundLabsPieces.Piece(tm, rs, bPos, Rotation.NONE, Mirror.NONE, endCorridorPos.getX(), 0));
            genEastWing2(tm,newNodePos,l);
            genNorthWing2(tm,newNodePos,l);
            genSouthWing2(tm,newNodePos,l);
        } else if (tripleFragment.equals(rs)) {
            l.add(new UndergroundLabsPieces.Piece(tm, rs, bPos, Rotation.CLOCKWISE_90, Mirror.NONE, endCorridorPos.getX()+ 4, 0));
           genNorthWing2(tm,newNodePos,l);
           genSouthWing2(tm,newNodePos,l);
        }
        else if (cornerFragment.equals(rs)) {
            l.add(new UndergroundLabsPieces.Piece(tm, rs, bPos, Rotation.CLOCKWISE_90, Mirror.NONE, endCorridorPos.getX()+ 4, 0));

            genNorthWing2(tm,newNodePos,l);
        }
        else if (cornerFragmentMirrored.equals(rs)) {
            l.add(new UndergroundLabsPieces.Piece(tm, rs, bPos, Rotation.CLOCKWISE_180, Mirror.NONE, endCorridorPos.getX()+ 4, 4));
            genSouthWing2(tm,newNodePos,l);
        }  else {
            l.add(new UndergroundLabsPieces.Piece(tm, filter_room, bPos, Rotation.CLOCKWISE_90, Mirror.NONE, endCorridorPos.getX() +9, -2));

        }
    }

    public static BlockPos genCorridor(TemplateManager tm, BlockPos p, List<StructurePiece> l, Direction.Axis ax, int dirCorrection, int xCorrection, int zCorrection) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        int randLin = tr.nextInt(6, 14);
        ResourceLocation[] linear = { linearFragment1, linearFragment2, linearFragment3, linearFragment4 };
        int correction = (ax == Direction.Axis.Z) ? zCorrection : xCorrection;
        Rotation rotation = (ax == Direction.Axis.X) ? Rotation.CLOCKWISE_90 : Rotation.NONE;
        return generateCorridorRecursive(tm, p, l, ax, dirCorrection, xCorrection, zCorrection, linear, rotation, correction, 4, randLin * lenthFragment);
    }
    private static BlockPos generateCorridorRecursive(TemplateManager tm, BlockPos p, List<StructurePiece> l, Direction.Axis ax, int dirCorrection, int xCorrection, int zCorrection, ResourceLocation[] linear, Rotation rotation, int correction, int currentPos, int maxPos) {
        if (currentPos >= maxPos) {
            int finalX = (ax == Direction.Axis.X) ? correction + (currentPos - lenthFragment) * dirCorrection : p.getX();
            int finalZ = (ax == Direction.Axis.Z) ? correction + (currentPos - lenthFragment) * dirCorrection : p.getZ();
            return new BlockPos(finalX, p.getY(), finalZ);
        }
        int pos = correction + currentPos * dirCorrection;
        l.add(new UndergroundLabsPieces.Piece(tm, linear[ThreadLocalRandom.current().nextInt(linear.length)], p, rotation, Mirror.NONE, (ax == Direction.Axis.X) ? pos : xCorrection, (ax == Direction.Axis.Z) ? pos : zCorrection));
        if (currentPos + lenthFragment >= maxPos) {
            return new BlockPos((ax == Direction.Axis.X) ? pos : p.getX(), p.getY(), (ax == Direction.Axis.Z) ? pos : p.getZ());
        }
        return generateCorridorRecursive(tm, p, l, ax, dirCorrection, xCorrection, zCorrection, linear, rotation, correction, currentPos + lenthFragment, maxPos);
    }
}
