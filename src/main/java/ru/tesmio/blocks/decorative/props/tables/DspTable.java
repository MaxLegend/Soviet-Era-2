package ru.tesmio.blocks.decorative.props.tables;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.decorative.props.LinearTable;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.utils.VoxelShapeUtil;

import java.util.HashMap;
import java.util.Map;

public class DspTable extends LinearTable {
    public DspTable(Properties properties) {
        super(properties);
    }
    Map<String, VoxelShape> SHAPE_MAP = new HashMap<>();
    VoxelShape SHAPES[] = new VoxelShape[]{
            Block.makeCuboidShape(0, 15, 0, 16, 16, 16),
            Block.makeCuboidShape(1, 2, 0.5, 15, 15, 2),
            Block.makeCuboidShape(1, 2, 14, 15, 15, 15.5),
            Block.makeCuboidShape(1.5, 12, 2, 14.5, 15, 14),

            Block.makeCuboidShape(3, 0, 0.75, 4, 2, 1.75),
            Block.makeCuboidShape(12, 0, 0.75, 13, 2, 1.75),
            Block.makeCuboidShape(12, 0, 14.25, 13, 2, 15.25),
            Block.makeCuboidShape(3, 0, 14.25, 4, 2, 15.25),

            Block.makeCuboidShape(1.5, 12, 2, 14.5, 15, 16),
            Block.makeCuboidShape(1, 2, 0, 15, 15, 6),

            Block.makeCuboidShape(3, 0, 2.75, 4, 2, 3.75),
            Block.makeCuboidShape(12, 0, 2.75, 13, 2, 3.75),

            Block.makeCuboidShape(1.5, 12, 0, 14.5, 15, 15),
            Block.makeCuboidShape(1, 2, 5, 15, 15, 16),
            Block.makeCuboidShape(1.5, 12, 0, 14.5, 15, 16),

            Block.makeCuboidShape(1.5, 4, 2, 2.5, 15, 14),
            Block.makeCuboidShape(13.5, 12, 2, 14.5, 15, 14),

            Block.makeCuboidShape(1.5, 4, 0, 2.5, 15, 14),
            Block.makeCuboidShape(13.5, 12, 0, 14.5, 15, 14),

            Block.makeCuboidShape(1.5, 4, 2, 2.5, 15, 16),
            Block.makeCuboidShape(13.5, 12, 2, 14.5, 15, 16),
    };

    public void putMapVoxelShape() {
        SHAPE_MAP.put("top", SHAPES[0]);
        SHAPE_MAP.put("right_side", SHAPES[1]);
        SHAPE_MAP.put("left_side", SHAPES[2]);
        SHAPE_MAP.put("panels", SHAPES[3]);
        SHAPE_MAP.put("right_leg_back", SHAPES[4]);
        SHAPE_MAP.put("right_leg_front", SHAPES[5]);
        SHAPE_MAP.put("left_leg_back", SHAPES[6]);
        SHAPE_MAP.put("left_leg_front", SHAPES[7]);
        SHAPE_MAP.put("panels2", SHAPES[8]);
        SHAPE_MAP.put("right_side2", SHAPES[9]);
        SHAPE_MAP.put("right_leg_back2", SHAPES[10]);
        SHAPE_MAP.put("right_leg_front2", SHAPES[11]);
        SHAPE_MAP.put("panels3", SHAPES[12]);
        SHAPE_MAP.put("left_side2", SHAPES[13]);
        SHAPE_MAP.put("panels4", SHAPES[14]);
        SHAPE_MAP.put("panels5", SHAPES[15]);
        SHAPE_MAP.put("panels6", SHAPES[16]);

        SHAPE_MAP.put("panels7", SHAPES[17]);
        SHAPE_MAP.put("panels8", SHAPES[18]);

        SHAPE_MAP.put("panels9", SHAPES[19]);
        SHAPE_MAP.put("panels10", SHAPES[20]);
    }
    public VoxelShape getShape(BlockState s, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        putMapVoxelShape();
        if (s.getBlock() == RegBlocks.DSP_TABLE.get()) {
            switch (s.get(FACING)) {
                case WEST:
                    switch (s.get(STATES)) {
                        case DEF:
                            return VoxelShapes.or(
                                    SHAPE_MAP.get("top"),
                                    SHAPE_MAP.get("right_side"),
                                    SHAPE_MAP.get("left_side"),
                                    SHAPE_MAP.get("panels"),
                                    SHAPE_MAP.get("right_leg_back"),
                                    SHAPE_MAP.get("right_leg_front"),
                                    SHAPE_MAP.get("left_leg_back"),
                                    SHAPE_MAP.get("left_leg_front"));
                        case RIGHT:
                            return VoxelShapes.or(
                                    SHAPE_MAP.get("top"),
                                    SHAPE_MAP.get("left_side2"),
                                    SHAPE_MAP.get("panels3"),
                                    SHAPE_MAP.get("left_leg_back"),
                                    SHAPE_MAP.get("left_leg_front"));
                        case LEFT:
                            return VoxelShapes.or(
                                    SHAPE_MAP.get("top"),
                                    SHAPE_MAP.get("right_side2"),
                                    SHAPE_MAP.get("panels2"),
                                    SHAPE_MAP.get("right_leg_back2"),
                                    SHAPE_MAP.get("right_leg_front2")

                            );
                        case CENTER:
                            return VoxelShapes.or(
                                    SHAPE_MAP.get("top"),
                                    SHAPE_MAP.get("panels4")
                            );
                    }
                case EAST:
                    switch (s.get(STATES)) {
                        case DEF:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_side")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("left_side")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("panels")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_leg_back")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_leg_front")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("left_leg_back")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("left_leg_front")));
                        case RIGHT:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("left_side2")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("panels3")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("left_leg_back")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("left_leg_front")));
                        case LEFT:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_side2")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("panels2")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_leg_back2")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_leg_front2"))

                            );
                        case CENTER:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("panels4"))
                            );
                    }
                case NORTH:
                    switch (s.get(STATES)) {
                        case DEF:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_side")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("left_side")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("panels")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_leg_back")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_leg_front")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("left_leg_back")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("left_leg_front")));
                        case RIGHT:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("left_side2")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("panels3")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("left_leg_back")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("left_leg_front")));
                        case LEFT:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_side2")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("panels2")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_leg_back2")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_leg_front2"))

                            );
                        case CENTER:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("panels4"))
                            );
                    }
                case SOUTH:
                    switch (s.get(STATES)) {
                        case DEF:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_side")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("left_side")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("panels")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_leg_back")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_leg_front")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("left_leg_back")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("left_leg_front")));
                        case RIGHT:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("left_side2")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("panels3")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("left_leg_back")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("left_leg_front")));
                        case LEFT:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_side2")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("panels2")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_leg_back2")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_leg_front2"))

                            );
                        case CENTER:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("panels4"))
                            );
                    }
            }
        }

        if (s.getBlock() == RegBlocks.DSP_TABLE2.get()) {
            switch (s.get(FACING)) {
                case WEST:
                    switch (s.get(STATES)) {
                        case DEF:
                            return VoxelShapes.or(
                                    SHAPE_MAP.get("top"),
                                    SHAPE_MAP.get("right_side"),
                                    SHAPE_MAP.get("left_side"),
                                    SHAPE_MAP.get("panels5"),
                                    SHAPE_MAP.get("panels6"),
                                    SHAPE_MAP.get("right_leg_back"),
                                    SHAPE_MAP.get("right_leg_front"),
                                    SHAPE_MAP.get("left_leg_back"),
                                    SHAPE_MAP.get("left_leg_front")
                            );
                        case RIGHT:
                            return VoxelShapes.or(
                                    SHAPE_MAP.get("top"),
                                    SHAPE_MAP.get("left_side"),
                                    SHAPE_MAP.get("panels7"),
                                    SHAPE_MAP.get("panels8"),
                                    SHAPE_MAP.get("left_leg_back"),
                                    SHAPE_MAP.get("left_leg_front")
                            );
                        case LEFT:
                            return VoxelShapes.or(
                                    SHAPE_MAP.get("top"),
                                    SHAPE_MAP.get("right_side"),
                                    SHAPE_MAP.get("panels10"),
                                    SHAPE_MAP.get("panels9"),
                                    SHAPE_MAP.get("right_leg_back"),
                                    SHAPE_MAP.get("right_leg_front")
                            );
                        case CENTER:
                            return VoxelShapes.or(
                                    SHAPE_MAP.get("top"),
                                    SHAPE_MAP.get("panels10"),
                                    SHAPE_MAP.get("panels9"),
                                    SHAPE_MAP.get("panels7"),
                                    SHAPE_MAP.get("panels8")
                            );

                    }
                case EAST:
                    switch (s.get(STATES)) {
                        case DEF:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_side")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("left_side")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("panels5")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("panels6")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_leg_back")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_leg_front")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("left_leg_back")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("left_leg_front"))
                            );
                        case RIGHT:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("left_side")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("panels7")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("panels8")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("left_leg_back")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("left_leg_front"))
                            );
                        case LEFT:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_side")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("panels10")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("panels9")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_leg_back")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_leg_front"))
                            );
                        case CENTER:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("panels10")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("panels9")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("panels7")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("panels8"))
                            );

                    }
                case NORTH:
                    switch (s.get(STATES)) {
                        case DEF:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_side")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("left_side")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("panels5")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("panels6")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_leg_back")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_leg_front")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("left_leg_back")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("left_leg_front"))
                            );
                        case RIGHT:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("left_side")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("panels7")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("panels8")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("left_leg_back")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("left_leg_front"))
                            );
                        case LEFT:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_side")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("panels10")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("panels9")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_leg_back")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_leg_front"))
                            );
                        case CENTER:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("panels10")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("panels9")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("panels7")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("panels8"))
                            );

                    }
                case SOUTH:
                    switch (s.get(STATES)) {
                        case DEF:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_side")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("left_side")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("panels5")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("panels6")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_leg_back")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_leg_front")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("left_leg_back")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("left_leg_front"))
                            );
                        case RIGHT:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("left_side")),
                                    VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("panels7"))),
                                    VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("panels8"))),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("left_leg_back")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("left_leg_front"))
                            );
                        case LEFT:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_side")),
                                    VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("panels10"))),
                                    VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("panels9"))),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_leg_back")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_leg_front"))
                            );
                        case CENTER:
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("top")),
                                    VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("panels10"))),
                                    VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("panels9"))),
                                    VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("panels7"))),
                                    VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("panels8")))
                            );

                    }
            }
        }

        return VoxelShapes.fullCube();
    }

}
