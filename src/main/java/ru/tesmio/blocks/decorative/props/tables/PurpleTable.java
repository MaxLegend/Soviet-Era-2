package ru.tesmio.blocks.decorative.props.tables;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.decorative.props.LinearTable;
import ru.tesmio.reg.RegItems;
import ru.tesmio.utils.VoxelShapeUtil;

import java.util.HashMap;
import java.util.Map;

public class PurpleTable extends LinearTable {
    Map<String, VoxelShape> SHAPE_MAP = new HashMap<>();
    VoxelShape SHAPES[] = new VoxelShape[] {
            Block.makeCuboidShape(0,14,0,16,15.75,16),
            Block.makeCuboidShape(1,2,1,3,15.75,15),
            Block.makeCuboidShape(1,0,2,3,2,4),
            Block.makeCuboidShape(1,0,12,3,2,14),
            Block.makeCuboidShape(13,2,1,15,15.75,15),
            Block.makeCuboidShape(13,0,2,15,2,4),
            Block.makeCuboidShape(13,0,12,15,2,14),
            Block.makeCuboidShape(1,2,13,15,14,14)
    };
    public PurpleTable(Properties properties) {
        super(properties);
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {
                new ItemStack(RegItems.WOOD_SCRAP.get(), tr.nextInt(1,3)),
        };
    }
    public void putMapVoxelShape() {
        SHAPE_MAP.put("top", SHAPES[0]);
        SHAPE_MAP.put("right_side", SHAPES[1]);
        SHAPE_MAP.put("right_leg_front", SHAPES[2]);
        SHAPE_MAP.put("right_leg_back", SHAPES[3]);
        SHAPE_MAP.put("left_side", SHAPES[4]);
        SHAPE_MAP.put("left_leg_front", SHAPES[5]);
        SHAPE_MAP.put("left_leg_back", SHAPES[6]);
        SHAPE_MAP.put("back", SHAPES[7]);
    }

    public VoxelShape getShape(BlockState s, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        putMapVoxelShape();
        switch (s.get(FACING)) {
            case WEST:
                switch (s.get(STATES)) {
                    case DEF:
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("top")),
                                VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_side")),
                                VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_leg_back")),
                                VoxelShapeUtil.shapeRotCCW90( SHAPE_MAP.get("right_leg_front")),
                                VoxelShapeUtil.shapeRotCCW90( SHAPE_MAP.get("left_side")),
                                VoxelShapeUtil.shapeRotCCW90( SHAPE_MAP.get("left_leg_back")),
                                VoxelShapeUtil.shapeRotCCW90(  SHAPE_MAP.get("left_leg_front")),
                                VoxelShapeUtil.shapeRotCCW90( SHAPE_MAP.get("back"))
                        );
                    case RIGHT:
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCCW90( SHAPE_MAP.get("top")),
                                VoxelShapeUtil.shapeRotCCW90( SHAPE_MAP.get("left_side")),
                                VoxelShapeUtil.shapeRotCCW90( SHAPE_MAP.get("left_leg_back")),
                                VoxelShapeUtil.shapeRotCCW90( SHAPE_MAP.get("left_leg_front")),
                                VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("back"))
                        );
                    case LEFT:
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("top")),
                                VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_side")),
                                VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_leg_back")),
                                VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_leg_front")),
                                VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("back"))
                        );
                    case CENTER:
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("top")),
                                VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("back"))
                        );
                }
            case EAST:
                switch (s.get(STATES)) {
                    case DEF:
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCW90( SHAPE_MAP.get("top")),
                                VoxelShapeUtil.shapeRotCW90( SHAPE_MAP.get("right_side")),
                                VoxelShapeUtil.shapeRotCW90( SHAPE_MAP.get("right_leg_back")),
                                VoxelShapeUtil.shapeRotCW90( SHAPE_MAP.get("right_leg_front")),
                                VoxelShapeUtil.shapeRotCW90( SHAPE_MAP.get("left_side")),
                                VoxelShapeUtil.shapeRotCW90( SHAPE_MAP.get("left_leg_back")),
                                VoxelShapeUtil.shapeRotCW90(  SHAPE_MAP.get("left_leg_front")),
                                VoxelShapeUtil.shapeRotCW90( SHAPE_MAP.get("back"))
                        );
                    case LEFT:
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCW90( SHAPE_MAP.get("top")),
                                VoxelShapeUtil.shapeRotCW90( SHAPE_MAP.get("left_side")),
                                VoxelShapeUtil.shapeRotCW90( SHAPE_MAP.get("left_leg_back")),
                                VoxelShapeUtil.shapeRotCW90( SHAPE_MAP.get("left_leg_front")),
                                VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("back"))
                        );
                    case RIGHT:
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("top")),
                                VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_side")),
                                VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_leg_back")),
                                VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_leg_front")),
                                VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("back"))
                        );
                    case CENTER:
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("top")),
                                VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("back"))
                        );
                }
            case NORTH:
                switch (s.get(STATES)) {
                    case DEF:
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRot180( SHAPE_MAP.get("top")),
                                VoxelShapeUtil.shapeRot180( SHAPE_MAP.get("right_side")),
                                VoxelShapeUtil.shapeRot180( SHAPE_MAP.get("right_leg_back")),
                                VoxelShapeUtil.shapeRot180( SHAPE_MAP.get("right_leg_front")),
                                VoxelShapeUtil.shapeRot180( SHAPE_MAP.get("left_side")),
                                VoxelShapeUtil.shapeRot180( SHAPE_MAP.get("left_leg_back")),
                                VoxelShapeUtil.shapeRot180(  SHAPE_MAP.get("left_leg_front")),
                                VoxelShapeUtil.shapeRot180( SHAPE_MAP.get("back"))
                        );
                    case RIGHT:
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRot180( SHAPE_MAP.get("top")),
                                VoxelShapeUtil.shapeRot180( SHAPE_MAP.get("left_side")),
                                VoxelShapeUtil.shapeRot180( SHAPE_MAP.get("left_leg_back")),
                                        VoxelShapeUtil.shapeRot180( SHAPE_MAP.get("left_leg_front")),
                                VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("back"))
                        );
                    case LEFT:
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("top")),
                                VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_side")),
                                        VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_leg_back")),
                                                VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_leg_front")),
                                                        VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("back"))
                        );
                    case CENTER:
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("top")),
                                        VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("back"))
                        );
                }
            case SOUTH:
                switch (s.get(STATES)) {
                    case DEF:
                        return VoxelShapes.or(
                                SHAPE_MAP.get("top"),
                                SHAPE_MAP.get("right_side"),
                                SHAPE_MAP.get("right_leg_back"),
                                SHAPE_MAP.get("right_leg_front"),
                                SHAPE_MAP.get("left_side"),
                                SHAPE_MAP.get("left_leg_back"),
                                SHAPE_MAP.get("left_leg_front"),
                                SHAPE_MAP.get("back")
                        );
                    case RIGHT:
                        return VoxelShapes.or(
                                SHAPE_MAP.get("top"),
                                SHAPE_MAP.get("left_side"),
                                SHAPE_MAP.get("left_leg_back"),
                                SHAPE_MAP.get("left_leg_front"),
                                SHAPE_MAP.get("back")
                        );
                    case LEFT:
                        return VoxelShapes.or(
                                SHAPE_MAP.get("top"),
                                SHAPE_MAP.get("right_side"),
                                SHAPE_MAP.get("right_leg_back"),
                                SHAPE_MAP.get("right_leg_front"),
                                SHAPE_MAP.get("back")
                        );
                    case CENTER:
                        return VoxelShapes.or(
                                SHAPE_MAP.get("top"),
                                SHAPE_MAP.get("back")
                        );
                }
        }
        return VoxelShapes.fullCube();
    }
}
