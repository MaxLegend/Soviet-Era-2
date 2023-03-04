package ru.tesmio.blocks.doors;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import ru.tesmio.utils.VoxelShapeUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class RustyIronDoor extends LockedDoor implements IWaterLoggable {
    Map<String, VoxelShape> SHAPE_MAP = new HashMap<>();
    VoxelShape SHAPES[] = new VoxelShape[] {
            Block.makeCuboidShape(0D, 0D, 12.75D, 16D, 16D, 15.75D),
            Block.makeCuboidShape(0D, 0D, 12.75D, 1D, 16D, 15.75D),
            Block.makeCuboidShape(15D, 0D, 12.75D, 16D, 16D, 15.75D),
            Block.makeCuboidShape(0D, 0D, 12.75D, 16D, 0.5D, 15.75D),
            Block.makeCuboidShape(0D, 15.5D, 12.75D, 16D, 16D, 15.75D),
            Block.makeCuboidShape(1D, 0.5D, 0D, 1.75D, 16D, 14D),
            Block.makeCuboidShape(1D, 0D, 0D, 1.75D, 15.5D, 14D),
            Block.makeCuboidShape(14.25D, 0.5D, 0D, 15D, 16D, 14D),
            Block.makeCuboidShape(14.25D, 0D, 0D, 15D, 15.5D, 14D)
    };
    public RustyIronDoor(Properties builder) {
        super(builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(OPEN, Boolean.valueOf(false)).with(HINGE, DoorHingeSide.LEFT).with(POWERED, Boolean.valueOf(false)).with(HALF, DoubleBlockHalf.LOWER).with(LOCKED, Boolean.valueOf(false)).with(WATERLOGGED, Boolean.valueOf(false)));

    }
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        player.addStat(Stats.BLOCK_MINED.get(this));
        player.addExhaustion(0.005F);
        spawnDrops(state, worldIn, pos, te, player, stack);
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HALF, FACING, OPEN, HINGE, POWERED, LOCKED, WATERLOGGED);
    }
    public void putMapVoxelShape() {
        SHAPE_MAP.put("close", SHAPES[0]);
        SHAPE_MAP.put("right_frame", SHAPES[1]);
        SHAPE_MAP.put("left_frame", SHAPES[2]);
        SHAPE_MAP.put("lower_frame", SHAPES[3]);
        SHAPE_MAP.put("upper_frame", SHAPES[4]);
        SHAPE_MAP.put("open_door_left_lower", SHAPES[5]);
        SHAPE_MAP.put("open_door_left_upper", SHAPES[6]);
        SHAPE_MAP.put("open_door_right_lower", SHAPES[7]);
        SHAPE_MAP.put("open_door_right_upper", SHAPES[8]);
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        putMapVoxelShape();
        Direction direction = state.get(FACING);
        boolean isOpen = state.get(OPEN);
        boolean isHingeRight = state.get(HINGE) == DoorHingeSide.RIGHT;
        boolean isLower = state.get(HALF) == DoubleBlockHalf.LOWER;
        switch (direction) {
            case NORTH:
                if(isOpen) {
                    if(isLower) {
                        if (!isHingeRight) {
                            return VoxelShapes.or(
                                    SHAPE_MAP.get("right_frame"),
                                    SHAPE_MAP.get("left_frame"),
                                    SHAPE_MAP.get("open_door_left_lower"),
                                    SHAPE_MAP.get("lower_frame"));
                        } else {
                            return VoxelShapes.or(
                                    SHAPE_MAP.get("right_frame"),
                                    SHAPE_MAP.get("left_frame"),
                                    SHAPE_MAP.get("open_door_right_lower"),
                                    SHAPE_MAP.get("lower_frame"));
                        }
                    } else {
                        if (!isHingeRight) {
                            return VoxelShapes.or(
                                    SHAPE_MAP.get("right_frame"),
                                    SHAPE_MAP.get("left_frame"),
                                    SHAPE_MAP.get("open_door_left_upper"),
                                    SHAPE_MAP.get("upper_frame"));
                        } else {
                            return VoxelShapes.or(
                                    SHAPE_MAP.get("right_frame"),
                                    SHAPE_MAP.get("left_frame"),
                                    SHAPE_MAP.get("open_door_right_upper"),
                                    SHAPE_MAP.get("upper_frame"));
                        }
                    }
                }
               return SHAPE_MAP.get("close");
            case EAST:
                if(isOpen) {
                    if(isLower) {
                        if (!isHingeRight) {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("open_door_left_lower")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("lower_frame")));
                        } else {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("open_door_right_lower")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("lower_frame")));
                        }
                    } else {
                        if (!isHingeRight) {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("open_door_left_upper")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("upper_frame")));
                        } else {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("open_door_right_upper")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("upper_frame")));
                        }
                    }
                }
                return VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("close"));
            case SOUTH:
                if(isOpen) {
                    if(isLower) {
                        if (!isHingeRight) {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("open_door_left_lower")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("lower_frame")));
                        } else {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("open_door_right_lower")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("lower_frame")));
                        }
                    } else {
                        if (!isHingeRight) {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("open_door_left_upper")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("upper_frame")));
                        } else {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("open_door_right_upper")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("upper_frame")));
                        }
                    }
                }
                return VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("close"));
            case WEST:
                if(isOpen) {
                    if(isLower) {
                        if (isHingeRight) {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("open_door_left_lower")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("lower_frame")));
                        } else {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("open_door_right_lower")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("lower_frame")));
                        }
                    } else {
                        if (isHingeRight) {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("open_door_left_upper")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("upper_frame")));
                        } else {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("open_door_right_upper")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("upper_frame")));
                        }
                    }
                }
                return VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("close"));
        }
        return VoxelShapes.fullCube();
    }
}
